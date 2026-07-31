package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.annotation.Annotation;
import com.android.cglib.dx.rop.annotation.NameValuePair;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstAnnotation;
import com.android.cglib.dx.rop.cst.CstArray;
import com.android.cglib.dx.rop.cst.CstBoolean;
import com.android.cglib.dx.rop.cst.CstByte;
import com.android.cglib.dx.rop.cst.CstChar;
import com.android.cglib.dx.rop.cst.CstDouble;
import com.android.cglib.dx.rop.cst.CstEnumRef;
import com.android.cglib.dx.rop.cst.CstFieldRef;
import com.android.cglib.dx.rop.cst.CstFloat;
import com.android.cglib.dx.rop.cst.CstInteger;
import com.android.cglib.dx.rop.cst.CstKnownNull;
import com.android.cglib.dx.rop.cst.CstLiteralBits;
import com.android.cglib.dx.rop.cst.CstLong;
import com.android.cglib.dx.rop.cst.CstMethodRef;
import com.android.cglib.dx.rop.cst.CstShort;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.Collection;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class ValueEncoder {
    private static final int VALUE_ANNOTATION = 29;
    private static final int VALUE_ARRAY = 28;
    private static final int VALUE_BOOLEAN = 31;
    private static final int VALUE_BYTE = 0;
    private static final int VALUE_CHAR = 3;
    private static final int VALUE_DOUBLE = 17;
    private static final int VALUE_ENUM = 27;
    private static final int VALUE_FIELD = 25;
    private static final int VALUE_FLOAT = 16;
    private static final int VALUE_INT = 4;
    private static final int VALUE_LONG = 6;
    private static final int VALUE_METHOD = 26;
    private static final int VALUE_NULL = 30;
    private static final int VALUE_SHORT = 2;
    private static final int VALUE_STRING = 23;
    private static final int VALUE_TYPE = 24;
    private final DexFile file;
    private final AnnotatedOutput out;

    public ValueEncoder(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        Objects.requireNonNull(dexFile, "file == null");
        Objects.requireNonNull(annotatedOutput, "out == null");
        this.file = dexFile;
        this.out = annotatedOutput;
    }

    public static void addContents(DexFile dexFile, Annotation annotation) {
        TypeIdsSection typeIds = dexFile.getTypeIds();
        StringIdsSection stringIds = dexFile.getStringIds();
        typeIds.intern(annotation.getType());
        for (NameValuePair nameValuePair : annotation.getNameValuePairs()) {
            stringIds.intern(nameValuePair.getName());
            addContents(dexFile, nameValuePair.getValue());
        }
    }

    public static void addContents(DexFile dexFile, Constant constant) {
        if (constant instanceof CstAnnotation) {
            addContents(dexFile, ((CstAnnotation) constant).getAnnotation());
            return;
        }
        if (!(constant instanceof CstArray)) {
            dexFile.internIfAppropriate(constant);
            return;
        }
        CstArray.List list = ((CstArray) constant).getList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            addContents(dexFile, list.get(i));
        }
    }

    public static String constantToHuman(Constant constant) {
        if (constantToValueType(constant) == 30) {
            return "null";
        }
        return constant.typeName() + ' ' + constant.toHuman();
    }

    private static int constantToValueType(Constant constant) {
        if (constant instanceof CstByte) {
            return 0;
        }
        if (constant instanceof CstShort) {
            return 2;
        }
        if (constant instanceof CstChar) {
            return 3;
        }
        if (constant instanceof CstInteger) {
            return 4;
        }
        if (constant instanceof CstLong) {
            return 6;
        }
        if (constant instanceof CstFloat) {
            return 16;
        }
        if (constant instanceof CstDouble) {
            return 17;
        }
        if (constant instanceof CstString) {
            return 23;
        }
        if (constant instanceof CstType) {
            return 24;
        }
        if (constant instanceof CstFieldRef) {
            return 25;
        }
        if (constant instanceof CstMethodRef) {
            return 26;
        }
        if (constant instanceof CstEnumRef) {
            return 27;
        }
        if (constant instanceof CstArray) {
            return 28;
        }
        if (constant instanceof CstAnnotation) {
            return 29;
        }
        if (constant instanceof CstKnownNull) {
            return 30;
        }
        if (constant instanceof CstBoolean) {
            return 31;
        }
        throw new RuntimeException("Shouldn't happen");
    }

    private void writeRightZeroExtendedValue(int i, long j) {
        int iNumberOfTrailingZeros = 64 - Long.numberOfTrailingZeros(j);
        if (iNumberOfTrailingZeros == 0) {
            iNumberOfTrailingZeros = 1;
        }
        int i2 = (iNumberOfTrailingZeros + 7) >> 3;
        long j2 = j >> (64 - (i2 * 8));
        this.out.writeByte(i | ((i2 - 1) << 5));
        while (i2 > 0) {
            this.out.writeByte((byte) j2);
            j2 >>= 8;
            i2--;
        }
    }

    private void writeSignedIntegralValue(int i, long j) {
        int iNumberOfLeadingZeros = ((65 - Long.numberOfLeadingZeros((j >> 63) ^ j)) + 7) >> 3;
        this.out.writeByte(i | ((iNumberOfLeadingZeros - 1) << 5));
        while (iNumberOfLeadingZeros > 0) {
            this.out.writeByte((byte) j);
            j >>= 8;
            iNumberOfLeadingZeros--;
        }
    }

    private void writeUnsignedIntegralValue(int i, long j) {
        int iNumberOfLeadingZeros = 64 - Long.numberOfLeadingZeros(j);
        if (iNumberOfLeadingZeros == 0) {
            iNumberOfLeadingZeros = 1;
        }
        int i2 = (iNumberOfLeadingZeros + 7) >> 3;
        this.out.writeByte(i | ((i2 - 1) << 5));
        while (i2 > 0) {
            this.out.writeByte((byte) j);
            j >>= 8;
            i2--;
        }
    }

    public void writeAnnotation(Annotation annotation, boolean z) {
        boolean z2 = z && this.out.annotates();
        StringIdsSection stringIds = this.file.getStringIds();
        TypeIdsSection typeIds = this.file.getTypeIds();
        CstType type = annotation.getType();
        int iIndexOf = typeIds.indexOf(type);
        if (z2) {
            AnnotatedOutput annotatedOutput = this.out;
            StringBuilder sbO = a.o("  type_idx: ");
            sbO.append(Hex.u4(iIndexOf));
            sbO.append(" // ");
            sbO.append(type.toHuman());
            annotatedOutput.annotate(sbO.toString());
        }
        this.out.writeUleb128(typeIds.indexOf(annotation.getType()));
        Collection<NameValuePair> nameValuePairs = annotation.getNameValuePairs();
        int size = nameValuePairs.size();
        if (z2) {
            AnnotatedOutput annotatedOutput2 = this.out;
            StringBuilder sbO2 = a.o("  size: ");
            sbO2.append(Hex.u4(size));
            annotatedOutput2.annotate(sbO2.toString());
        }
        this.out.writeUleb128(size);
        int i = 0;
        for (NameValuePair nameValuePair : nameValuePairs) {
            CstString name = nameValuePair.getName();
            int iIndexOf2 = stringIds.indexOf(name);
            Constant value = nameValuePair.getValue();
            if (z2) {
                this.out.annotate(0, "  elements[" + i + "]:");
                i++;
                AnnotatedOutput annotatedOutput3 = this.out;
                StringBuilder sbO3 = a.o("    name_idx: ");
                sbO3.append(Hex.u4(iIndexOf2));
                sbO3.append(" // ");
                sbO3.append(name.toHuman());
                annotatedOutput3.annotate(sbO3.toString());
            }
            this.out.writeUleb128(iIndexOf2);
            if (z2) {
                AnnotatedOutput annotatedOutput4 = this.out;
                StringBuilder sbO4 = a.o("    value: ");
                sbO4.append(constantToHuman(value));
                annotatedOutput4.annotate(sbO4.toString());
            }
            writeConstant(value);
        }
        if (z2) {
            this.out.endAnnotation();
        }
    }

    public void writeArray(CstArray cstArray, boolean z) {
        boolean z2 = z && this.out.annotates();
        CstArray.List list = cstArray.getList();
        int size = list.size();
        if (z2) {
            AnnotatedOutput annotatedOutput = this.out;
            StringBuilder sbO = a.o("  size: ");
            sbO.append(Hex.u4(size));
            annotatedOutput.annotate(sbO.toString());
        }
        this.out.writeUleb128(size);
        for (int i = 0; i < size; i++) {
            Constant constant = list.get(i);
            if (z2) {
                AnnotatedOutput annotatedOutput2 = this.out;
                StringBuilder sbO2 = a.o("  [");
                sbO2.append(Integer.toHexString(i));
                sbO2.append("] ");
                sbO2.append(constantToHuman(constant));
                annotatedOutput2.annotate(sbO2.toString());
            }
            writeConstant(constant);
        }
        if (z2) {
            this.out.endAnnotation();
        }
    }

    public void writeConstant(Constant constant) {
        long longBits;
        long longBits2;
        int iIndexOf;
        FieldIdsSection fieldIds;
        CstFieldRef fieldRef;
        int iConstantToValueType = constantToValueType(constant);
        if (iConstantToValueType != 0 && iConstantToValueType != 6 && iConstantToValueType != 2) {
            if (iConstantToValueType == 3) {
                longBits = ((CstLiteralBits) constant).getLongBits();
            } else if (iConstantToValueType != 4) {
                if (iConstantToValueType == 16) {
                    longBits2 = ((CstFloat) constant).getLongBits() << 32;
                } else if (iConstantToValueType != 17) {
                    switch (iConstantToValueType) {
                        case 23:
                            iIndexOf = this.file.getStringIds().indexOf((CstString) constant);
                            longBits = iIndexOf;
                            break;
                        case 24:
                            iIndexOf = this.file.getTypeIds().indexOf((CstType) constant);
                            longBits = iIndexOf;
                            break;
                        case 25:
                            fieldIds = this.file.getFieldIds();
                            fieldRef = (CstFieldRef) constant;
                            iIndexOf = fieldIds.indexOf(fieldRef);
                            longBits = iIndexOf;
                            break;
                        case 26:
                            iIndexOf = this.file.getMethodIds().indexOf((CstMethodRef) constant);
                            longBits = iIndexOf;
                            break;
                        case 27:
                            fieldRef = ((CstEnumRef) constant).getFieldRef();
                            fieldIds = this.file.getFieldIds();
                            iIndexOf = fieldIds.indexOf(fieldRef);
                            longBits = iIndexOf;
                            break;
                        case 28:
                            this.out.writeByte(iConstantToValueType);
                            writeArray((CstArray) constant, false);
                            return;
                        case 29:
                            this.out.writeByte(iConstantToValueType);
                            writeAnnotation(((CstAnnotation) constant).getAnnotation(), false);
                            return;
                        case 30:
                            this.out.writeByte(iConstantToValueType);
                            return;
                        case 31:
                            this.out.writeByte((((CstBoolean) constant).getIntBits() << 5) | iConstantToValueType);
                            return;
                        default:
                            throw new RuntimeException("Shouldn't happen");
                    }
                } else {
                    longBits2 = ((CstDouble) constant).getLongBits();
                }
                writeRightZeroExtendedValue(iConstantToValueType, longBits2);
                return;
            }
            writeUnsignedIntegralValue(iConstantToValueType, longBits);
            return;
        }
        writeSignedIntegralValue(iConstantToValueType, ((CstLiteralBits) constant).getLongBits());
    }
}

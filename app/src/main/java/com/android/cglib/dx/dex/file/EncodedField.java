package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.code.AccessFlags;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstFieldRef;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import com.android.cglib.dx.util.Leb128Utils;
import java.io.PrintWriter;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class EncodedField extends EncodedMember implements Comparable<EncodedField> {
    private final CstFieldRef field;

    public EncodedField(CstFieldRef cstFieldRef, int i) {
        super(i);
        Objects.requireNonNull(cstFieldRef, "field == null");
        this.field = cstFieldRef;
    }

    @Override // com.android.cglib.dx.dex.file.EncodedMember
    public void addContents(DexFile dexFile) {
        dexFile.getFieldIds().intern(this.field);
    }

    @Override // java.lang.Comparable
    public int compareTo(EncodedField encodedField) {
        return this.field.compareTo((Constant) encodedField.field);
    }

    @Override // com.android.cglib.dx.dex.file.EncodedMember
    public void debugPrint(PrintWriter printWriter, boolean z) {
        printWriter.println(toString());
    }

    @Override // com.android.cglib.dx.dex.file.EncodedMember
    public int encode(DexFile dexFile, AnnotatedOutput annotatedOutput, int i, int i2) {
        int iIndexOf = dexFile.getFieldIds().indexOf(this.field);
        int i3 = iIndexOf - i;
        int accessFlags = getAccessFlags();
        if (annotatedOutput.annotates()) {
            annotatedOutput.annotate(0, String.format("  [%x] %s", Integer.valueOf(i2), this.field.toHuman()));
            int iUnsignedLeb128Size = Leb128Utils.unsignedLeb128Size(i3);
            StringBuilder sbO = a.o("    field_idx:    ");
            sbO.append(Hex.u4(iIndexOf));
            annotatedOutput.annotate(iUnsignedLeb128Size, sbO.toString());
            int iUnsignedLeb128Size2 = Leb128Utils.unsignedLeb128Size(accessFlags);
            StringBuilder sbO2 = a.o("    access_flags: ");
            sbO2.append(AccessFlags.fieldString(accessFlags));
            annotatedOutput.annotate(iUnsignedLeb128Size2, sbO2.toString());
        }
        annotatedOutput.writeUleb128(i3);
        annotatedOutput.writeUleb128(accessFlags);
        return iIndexOf;
    }

    public boolean equals(Object obj) {
        return (obj instanceof EncodedField) && compareTo((EncodedField) obj) == 0;
    }

    @Override // com.android.cglib.dx.dex.file.EncodedMember
    public CstString getName() {
        return this.field.getNat().getName();
    }

    public CstFieldRef getRef() {
        return this.field;
    }

    public int hashCode() {
        return this.field.hashCode();
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return this.field.toHuman();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(EncodedField.class.getName());
        stringBuffer.append('{');
        stringBuffer.append(Hex.u2(getAccessFlags()));
        stringBuffer.append(' ');
        stringBuffer.append(this.field);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}

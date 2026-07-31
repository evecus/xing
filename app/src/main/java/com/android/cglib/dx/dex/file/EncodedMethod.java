package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.dex.code.DalvCode;
import com.android.cglib.dx.rop.code.AccessFlags;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstMethodRef;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.type.TypeList;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import com.android.cglib.dx.util.Leb128Utils;
import java.io.PrintWriter;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class EncodedMethod extends EncodedMember implements Comparable<EncodedMethod> {
    private final CodeItem code;
    private final CstMethodRef method;

    public EncodedMethod(CstMethodRef cstMethodRef, int i, DalvCode dalvCode, TypeList typeList) {
        super(i);
        Objects.requireNonNull(cstMethodRef, "method == null");
        this.method = cstMethodRef;
        if (dalvCode == null) {
            this.code = null;
        } else {
            this.code = new CodeItem(cstMethodRef, dalvCode, (i & 8) != 0, typeList);
        }
    }

    @Override // com.android.cglib.dx.dex.file.EncodedMember
    public void addContents(DexFile dexFile) {
        MethodIdsSection methodIds = dexFile.getMethodIds();
        MixedItemSection wordData = dexFile.getWordData();
        methodIds.intern(this.method);
        CodeItem codeItem = this.code;
        if (codeItem != null) {
            wordData.add(codeItem);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(EncodedMethod encodedMethod) {
        return this.method.compareTo((Constant) encodedMethod.method);
    }

    @Override // com.android.cglib.dx.dex.file.EncodedMember
    public void debugPrint(PrintWriter printWriter, boolean z) {
        CodeItem codeItem = this.code;
        if (codeItem != null) {
            codeItem.debugPrint(printWriter, "  ", z);
            return;
        }
        printWriter.println(getRef().toHuman() + ": abstract or native");
    }

    @Override // com.android.cglib.dx.dex.file.EncodedMember
    public int encode(DexFile dexFile, AnnotatedOutput annotatedOutput, int i, int i2) {
        int iIndexOf = dexFile.getMethodIds().indexOf(this.method);
        int i3 = iIndexOf - i;
        int accessFlags = getAccessFlags();
        int absoluteOffsetOr0 = OffsettedItem.getAbsoluteOffsetOr0(this.code);
        if ((absoluteOffsetOr0 != 0) != ((accessFlags & 1280) == 0)) {
            throw new UnsupportedOperationException("code vs. access_flags mismatch");
        }
        if (annotatedOutput.annotates()) {
            annotatedOutput.annotate(0, String.format("  [%x] %s", Integer.valueOf(i2), this.method.toHuman()));
            int iUnsignedLeb128Size = Leb128Utils.unsignedLeb128Size(i3);
            StringBuilder sbO = a.o("    method_idx:   ");
            sbO.append(Hex.u4(iIndexOf));
            annotatedOutput.annotate(iUnsignedLeb128Size, sbO.toString());
            int iUnsignedLeb128Size2 = Leb128Utils.unsignedLeb128Size(accessFlags);
            StringBuilder sbO2 = a.o("    access_flags: ");
            sbO2.append(AccessFlags.methodString(accessFlags));
            annotatedOutput.annotate(iUnsignedLeb128Size2, sbO2.toString());
            a.f(absoluteOffsetOr0, a.o("    code_off:     "), annotatedOutput, Leb128Utils.unsignedLeb128Size(absoluteOffsetOr0));
        }
        annotatedOutput.writeUleb128(i3);
        annotatedOutput.writeUleb128(accessFlags);
        annotatedOutput.writeUleb128(absoluteOffsetOr0);
        return iIndexOf;
    }

    public boolean equals(Object obj) {
        return (obj instanceof EncodedMethod) && compareTo((EncodedMethod) obj) == 0;
    }

    @Override // com.android.cglib.dx.dex.file.EncodedMember
    public final CstString getName() {
        return this.method.getNat().getName();
    }

    public final CstMethodRef getRef() {
        return this.method;
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public final String toHuman() {
        return this.method.toHuman();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(EncodedMethod.class.getName());
        stringBuffer.append('{');
        stringBuffer.append(Hex.u2(getAccessFlags()));
        stringBuffer.append(' ');
        stringBuffer.append(this.method);
        if (this.code != null) {
            stringBuffer.append(' ');
            stringBuffer.append(this.code);
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}

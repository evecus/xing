package com.android.cglib.dx.dex.code.form;

import com.android.cglib.dx.dex.code.CstInsn;
import com.android.cglib.dx.dex.code.DalvInsn;
import com.android.cglib.dx.dex.code.InsnFormat;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstMethodRef;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.util.AnnotatedOutput;

/* JADX INFO: loaded from: classes.dex */
public final class Form3rc extends InsnFormat {
    public static final InsnFormat THE_ONE = new Form3rc();

    private Form3rc() {
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public int codeSize() {
        return 3;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn dalvInsn) {
        return InsnFormat.regRangeString(dalvInsn.getRegisters()) + ", " + InsnFormat.cstString(dalvInsn);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn dalvInsn, boolean z) {
        return z ? InsnFormat.cstComment(dalvInsn) : "";
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn dalvInsn) {
        if (!(dalvInsn instanceof CstInsn)) {
            return false;
        }
        CstInsn cstInsn = (CstInsn) dalvInsn;
        int index = cstInsn.getIndex();
        Constant constant = cstInsn.getConstant();
        if (!InsnFormat.unsignedFitsInShort(index)) {
            return false;
        }
        if (!(constant instanceof CstMethodRef) && !(constant instanceof CstType)) {
            return false;
        }
        RegisterSpecList registers = cstInsn.getRegisters();
        registers.size();
        return registers.size() == 0 || (InsnFormat.isRegListSequential(registers) && InsnFormat.unsignedFitsInShort(registers.get(0).getReg()) && InsnFormat.unsignedFitsInByte(registers.getWordCount()));
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput annotatedOutput, DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        InsnFormat.write(annotatedOutput, InsnFormat.opcodeUnit(dalvInsn, registers.getWordCount()), (short) ((CstInsn) dalvInsn).getIndex(), (short) (registers.size() != 0 ? registers.get(0).getReg() : 0));
    }
}

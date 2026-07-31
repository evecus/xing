package com.android.cglib.dx.dex.code.form;

import com.android.cglib.dx.dex.code.CstInsn;
import com.android.cglib.dx.dex.code.DalvInsn;
import com.android.cglib.dx.dex.code.InsnFormat;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstFieldRef;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.util.AnnotatedOutput;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public final class Form52c extends InsnFormat {
    public static final InsnFormat THE_ONE = new Form52c();

    private Form52c() {
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public int codeSize() {
        return 5;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        BitSet bitSet = new BitSet(2);
        bitSet.set(0, InsnFormat.unsignedFitsInShort(registers.get(0).getReg()));
        bitSet.set(1, InsnFormat.unsignedFitsInShort(registers.get(1).getReg()));
        return bitSet;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        return registers.get(0).regString() + ", " + registers.get(1).regString() + ", " + InsnFormat.cstString(dalvInsn);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn dalvInsn, boolean z) {
        return z ? InsnFormat.cstComment(dalvInsn) : "";
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn dalvInsn) {
        if (!InsnFormat.ALLOW_EXTENDED_OPCODES) {
            return false;
        }
        RegisterSpecList registers = dalvInsn.getRegisters();
        if (!(dalvInsn instanceof CstInsn) || registers.size() != 2 || !InsnFormat.unsignedFitsInShort(registers.get(0).getReg()) || !InsnFormat.unsignedFitsInShort(registers.get(1).getReg())) {
            return false;
        }
        Constant constant = ((CstInsn) dalvInsn).getConstant();
        return (constant instanceof CstType) || (constant instanceof CstFieldRef);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput annotatedOutput, DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        InsnFormat.write(annotatedOutput, InsnFormat.opcodeUnit(dalvInsn), ((CstInsn) dalvInsn).getIndex(), (short) registers.get(0).getReg(), (short) registers.get(1).getReg());
    }
}

package com.android.cglib.dx.dex.code.form;

import com.android.cglib.dx.dex.code.CstInsn;
import com.android.cglib.dx.dex.code.DalvInsn;
import com.android.cglib.dx.dex.code.InsnFormat;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstLiteralBits;
import com.android.cglib.dx.util.AnnotatedOutput;
import java.util.BitSet;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Form31i extends InsnFormat {
    public static final InsnFormat THE_ONE = new Form31i();

    private Form31i() {
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public int codeSize() {
        return 3;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        BitSet bitSet = new BitSet(1);
        bitSet.set(0, InsnFormat.unsignedFitsInByte(registers.get(0).getReg()));
        return bitSet;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn dalvInsn) {
        return dalvInsn.getRegisters().get(0).regString() + ", " + InsnFormat.literalBitsString((CstLiteralBits) ((CstInsn) dalvInsn).getConstant());
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn dalvInsn, boolean z) {
        return InsnFormat.literalBitsComment((CstLiteralBits) ((CstInsn) dalvInsn).getConstant(), 32);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        if (!(dalvInsn instanceof CstInsn) || registers.size() != 1 || !a.g(registers, 0)) {
            return false;
        }
        Constant constant = ((CstInsn) dalvInsn).getConstant();
        if (constant instanceof CstLiteralBits) {
            return ((CstLiteralBits) constant).fitsInInt();
        }
        return false;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput annotatedOutput, DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        InsnFormat.write(annotatedOutput, InsnFormat.opcodeUnit(dalvInsn, registers.get(0).getReg()), ((CstLiteralBits) ((CstInsn) dalvInsn).getConstant()).getIntBits());
    }
}

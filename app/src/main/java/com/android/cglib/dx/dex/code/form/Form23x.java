package com.android.cglib.dx.dex.code.form;

import com.android.cglib.dx.dex.code.DalvInsn;
import com.android.cglib.dx.dex.code.InsnFormat;
import com.android.cglib.dx.dex.code.SimpleInsn;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.util.AnnotatedOutput;
import java.util.BitSet;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Form23x extends InsnFormat {
    public static final InsnFormat THE_ONE = new Form23x();

    private Form23x() {
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public int codeSize() {
        return 2;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        BitSet bitSet = new BitSet(3);
        bitSet.set(0, InsnFormat.unsignedFitsInByte(registers.get(0).getReg()));
        bitSet.set(1, InsnFormat.unsignedFitsInByte(registers.get(1).getReg()));
        bitSet.set(2, InsnFormat.unsignedFitsInByte(registers.get(2).getReg()));
        return bitSet;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        return registers.get(0).regString() + ", " + registers.get(1).regString() + ", " + registers.get(2).regString();
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn dalvInsn, boolean z) {
        return "";
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        return (dalvInsn instanceof SimpleInsn) && registers.size() == 3 && a.g(registers, 0) && a.g(registers, 1) && a.g(registers, 2);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput annotatedOutput, DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        InsnFormat.write(annotatedOutput, InsnFormat.opcodeUnit(dalvInsn, registers.get(0).getReg()), InsnFormat.codeUnit(registers.get(1).getReg(), registers.get(2).getReg()));
    }
}

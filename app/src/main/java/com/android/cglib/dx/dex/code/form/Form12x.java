package com.android.cglib.dx.dex.code.form;

import com.android.cglib.dx.dex.code.DalvInsn;
import com.android.cglib.dx.dex.code.InsnFormat;
import com.android.cglib.dx.dex.code.SimpleInsn;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.util.AnnotatedOutput;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public final class Form12x extends InsnFormat {
    public static final InsnFormat THE_ONE = new Form12x();

    private Form12x() {
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public int codeSize() {
        return 1;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        BitSet bitSet = new BitSet(2);
        bitSet.set(0, InsnFormat.unsignedFitsInNibble(registers.get(0).getReg()));
        bitSet.set(1, InsnFormat.unsignedFitsInNibble(registers.get(1).getReg()));
        return bitSet;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        int size = registers.size();
        return registers.get(size - 2).regString() + ", " + registers.get(size - 1).regString();
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn dalvInsn, boolean z) {
        return "";
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn dalvInsn) {
        RegisterSpec registerSpec;
        RegisterSpec registerSpec2;
        if (!(dalvInsn instanceof SimpleInsn)) {
            return false;
        }
        RegisterSpecList registers = dalvInsn.getRegisters();
        int size = registers.size();
        if (size == 2) {
            registerSpec = registers.get(0);
            registerSpec2 = registers.get(1);
        } else {
            if (size != 3) {
                return false;
            }
            registerSpec = registers.get(1);
            registerSpec2 = registers.get(2);
            if (registerSpec.getReg() != registers.get(0).getReg()) {
                return false;
            }
        }
        return InsnFormat.unsignedFitsInNibble(registerSpec.getReg()) && InsnFormat.unsignedFitsInNibble(registerSpec2.getReg());
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput annotatedOutput, DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        int size = registers.size();
        InsnFormat.write(annotatedOutput, InsnFormat.opcodeUnit(dalvInsn, InsnFormat.makeByte(registers.get(size - 2).getReg(), registers.get(size - 1).getReg())));
    }
}

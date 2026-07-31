package com.android.cglib.dx.dex.code.form;

import com.android.cglib.dx.dex.code.CstInsn;
import com.android.cglib.dx.dex.code.DalvInsn;
import com.android.cglib.dx.dex.code.InsnFormat;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstFieldRef;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.util.AnnotatedOutput;
import java.util.BitSet;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Form31c extends InsnFormat {
    public static final InsnFormat THE_ONE = new Form31c();

    private Form31c() {
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public int codeSize() {
        return 3;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        int size = registers.size();
        BitSet bitSet = new BitSet(size);
        boolean zG = a.g(registers, 0);
        if (size == 1) {
            bitSet.set(0, zG);
        } else if (registers.get(0).getReg() == registers.get(1).getReg()) {
            bitSet.set(0, zG);
            bitSet.set(1, zG);
        }
        return bitSet;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn dalvInsn) {
        return dalvInsn.getRegisters().get(0).regString() + ", " + InsnFormat.cstString(dalvInsn);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn dalvInsn, boolean z) {
        return z ? InsnFormat.cstComment(dalvInsn) : "";
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn dalvInsn) {
        RegisterSpec registerSpec;
        if (!(dalvInsn instanceof CstInsn)) {
            return false;
        }
        RegisterSpecList registers = dalvInsn.getRegisters();
        int size = registers.size();
        if (size == 1) {
            registerSpec = registers.get(0);
        } else {
            if (size != 2) {
                return false;
            }
            registerSpec = registers.get(0);
            if (registerSpec.getReg() != registers.get(1).getReg()) {
                return false;
            }
        }
        if (!InsnFormat.unsignedFitsInByte(registerSpec.getReg())) {
            return false;
        }
        Constant constant = ((CstInsn) dalvInsn).getConstant();
        return (constant instanceof CstType) || (constant instanceof CstFieldRef) || (constant instanceof CstString);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput annotatedOutput, DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        InsnFormat.write(annotatedOutput, InsnFormat.opcodeUnit(dalvInsn, registers.get(0).getReg()), ((CstInsn) dalvInsn).getIndex());
    }
}

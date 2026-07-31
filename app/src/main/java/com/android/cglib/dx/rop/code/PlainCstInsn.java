package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.code.Insn;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.type.StdTypeList;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeList;

/* JADX INFO: loaded from: classes.dex */
public final class PlainCstInsn extends CstInsn {
    public PlainCstInsn(Rop rop, SourcePosition sourcePosition, RegisterSpec registerSpec, RegisterSpecList registerSpecList, Constant constant) {
        super(rop, sourcePosition, registerSpec, registerSpecList, constant);
        if (rop.getBranchingness() != 1) {
            throw new IllegalArgumentException("bogus branchingness");
        }
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public void accept(Insn.Visitor visitor) {
        visitor.visitPlainCstInsn(this);
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public TypeList getCatches() {
        return StdTypeList.EMPTY;
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withAddedCatch(Type type) {
        throw new UnsupportedOperationException("unsupported");
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withNewRegisters(RegisterSpec registerSpec, RegisterSpecList registerSpecList) {
        return new PlainCstInsn(getOpcode(), getPosition(), registerSpec, registerSpecList, getConstant());
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withRegisterOffset(int i) {
        return new PlainCstInsn(getOpcode(), getPosition(), getResult().withOffset(i), getSources().withOffset(i), getConstant());
    }
}

package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.cst.Constant;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class CstInsn extends Insn {
    private final Constant cst;

    public CstInsn(Rop rop, SourcePosition sourcePosition, RegisterSpec registerSpec, RegisterSpecList registerSpecList, Constant constant) {
        super(rop, sourcePosition, registerSpec, registerSpecList);
        Objects.requireNonNull(constant, "cst == null");
        this.cst = constant;
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public boolean contentEquals(Insn insn) {
        return super.contentEquals(insn) && this.cst.equals(((CstInsn) insn).getConstant());
    }

    public Constant getConstant() {
        return this.cst;
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public String getInlineString() {
        return this.cst.toHuman();
    }
}

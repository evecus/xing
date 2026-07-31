package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.code.Insn;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeList;
import com.baidu.android.common.util.HanziToPinyin;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class ThrowingCstInsn extends CstInsn {
    private final TypeList catches;

    public ThrowingCstInsn(Rop rop, SourcePosition sourcePosition, RegisterSpecList registerSpecList, TypeList typeList, Constant constant) {
        super(rop, sourcePosition, null, registerSpecList, constant);
        if (rop.getBranchingness() != 6) {
            throw new IllegalArgumentException("bogus branchingness");
        }
        Objects.requireNonNull(typeList, "catches == null");
        this.catches = typeList;
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public void accept(Insn.Visitor visitor) {
        visitor.visitThrowingCstInsn(this);
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public TypeList getCatches() {
        return this.catches;
    }

    @Override // com.android.cglib.dx.rop.code.CstInsn, com.android.cglib.dx.rop.code.Insn
    public String getInlineString() {
        Constant constant = getConstant();
        String human = constant.toHuman();
        if (constant instanceof CstString) {
            human = ((CstString) constant).toQuoted();
        }
        StringBuilder sbD = a.d(human, HanziToPinyin.Token.SEPARATOR);
        sbD.append(ThrowingInsn.toCatchString(this.catches));
        return sbD.toString();
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withAddedCatch(Type type) {
        return new ThrowingCstInsn(getOpcode(), getPosition(), getSources(), this.catches.withAddedType(type), getConstant());
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withNewRegisters(RegisterSpec registerSpec, RegisterSpecList registerSpecList) {
        return new ThrowingCstInsn(getOpcode(), getPosition(), registerSpecList, this.catches, getConstant());
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withRegisterOffset(int i) {
        return new ThrowingCstInsn(getOpcode(), getPosition(), getSources().withOffset(i), this.catches, getConstant());
    }
}

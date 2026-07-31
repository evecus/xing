package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.code.Insn;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstInteger;
import com.android.cglib.dx.rop.type.StdTypeList;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeBearer;
import com.android.cglib.dx.rop.type.TypeList;

/* JADX INFO: loaded from: classes.dex */
public final class PlainInsn extends Insn {
    public PlainInsn(Rop rop, SourcePosition sourcePosition, RegisterSpec registerSpec, RegisterSpec registerSpec2) {
        this(rop, sourcePosition, registerSpec, RegisterSpecList.make(registerSpec2));
    }

    public PlainInsn(Rop rop, SourcePosition sourcePosition, RegisterSpec registerSpec, RegisterSpecList registerSpecList) {
        super(rop, sourcePosition, registerSpec, registerSpecList);
        int branchingness = rop.getBranchingness();
        if (branchingness == 5 || branchingness == 6) {
            throw new IllegalArgumentException("bogus branchingness");
        }
        if (registerSpec != null && rop.getBranchingness() != 1) {
            throw new IllegalArgumentException("can't mix branchingness with result");
        }
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public void accept(Insn.Visitor visitor) {
        visitor.visitPlainInsn(this);
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
        return new PlainInsn(getOpcode(), getPosition(), registerSpec, registerSpecList);
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withRegisterOffset(int i) {
        return new PlainInsn(getOpcode(), getPosition(), getResult().withOffset(i), getSources().withOffset(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withSourceLiteral() {
        RegisterSpecList sources = getSources();
        int size = sources.size();
        if (size != 0) {
            TypeBearer typeBearer = sources.get(size - 1).getTypeBearer();
            if (typeBearer.isConstant()) {
                Constant constantMake = (Constant) typeBearer;
                RegisterSpecList registerSpecListWithoutLast = sources.withoutLast();
                try {
                    int opcode = getOpcode().getOpcode();
                    if (opcode == 15 && (constantMake instanceof CstInteger)) {
                        constantMake = CstInteger.make(-((CstInteger) constantMake).getValue());
                        opcode = 14;
                    }
                    Constant constant = constantMake;
                    return new PlainCstInsn(Rops.ropFor(opcode, getResult(), registerSpecListWithoutLast, constant), getPosition(), getResult(), registerSpecListWithoutLast, constant);
                } catch (IllegalArgumentException e) {
                }
            } else {
                TypeBearer typeBearer2 = sources.get(0).getTypeBearer();
                if (size == 2 && typeBearer2.isConstant()) {
                    Constant constant2 = (Constant) typeBearer2;
                    RegisterSpecList registerSpecListWithoutFirst = sources.withoutFirst();
                    return new PlainCstInsn(Rops.ropFor(getOpcode().getOpcode(), getResult(), registerSpecListWithoutFirst, constant2), getPosition(), getResult(), registerSpecListWithoutFirst, constant2);
                }
            }
        }
        return this;
    }
}

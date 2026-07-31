package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.code.Insn;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeList;
import com.baidu.android.common.util.HanziToPinyin;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ThrowingInsn extends Insn {
    private final TypeList catches;

    public ThrowingInsn(Rop rop, SourcePosition sourcePosition, RegisterSpecList registerSpecList, TypeList typeList) {
        super(rop, sourcePosition, null, registerSpecList);
        if (rop.getBranchingness() != 6) {
            throw new IllegalArgumentException("bogus branchingness");
        }
        Objects.requireNonNull(typeList, "catches == null");
        this.catches = typeList;
    }

    public static String toCatchString(TypeList typeList) {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("catch");
        int size = typeList.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
            stringBuffer.append(typeList.getType(i).toHuman());
        }
        return stringBuffer.toString();
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public void accept(Insn.Visitor visitor) {
        visitor.visitThrowingInsn(this);
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public TypeList getCatches() {
        return this.catches;
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public String getInlineString() {
        return toCatchString(this.catches);
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withAddedCatch(Type type) {
        return new ThrowingInsn(getOpcode(), getPosition(), getSources(), this.catches.withAddedType(type));
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withNewRegisters(RegisterSpec registerSpec, RegisterSpecList registerSpecList) {
        return new ThrowingInsn(getOpcode(), getPosition(), registerSpecList, this.catches);
    }

    @Override // com.android.cglib.dx.rop.code.Insn
    public Insn withRegisterOffset(int i) {
        return new ThrowingInsn(getOpcode(), getPosition(), getSources().withOffset(i), this.catches);
    }
}

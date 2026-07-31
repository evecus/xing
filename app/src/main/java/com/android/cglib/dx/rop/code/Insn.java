package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.type.StdTypeList;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeList;
import com.android.cglib.dx.util.ToHuman;
import com.baidu.android.common.util.HanziToPinyin;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class Insn implements ToHuman {
    private final Rop opcode;
    private final SourcePosition position;
    private final RegisterSpec result;
    private final RegisterSpecList sources;

    public static class BaseVisitor implements Visitor {
        @Override // com.android.cglib.dx.rop.code.Insn.Visitor
        public void visitFillArrayDataInsn(FillArrayDataInsn fillArrayDataInsn) {
        }

        @Override // com.android.cglib.dx.rop.code.Insn.Visitor
        public void visitPlainCstInsn(PlainCstInsn plainCstInsn) {
        }

        @Override // com.android.cglib.dx.rop.code.Insn.Visitor
        public void visitPlainInsn(PlainInsn plainInsn) {
        }

        @Override // com.android.cglib.dx.rop.code.Insn.Visitor
        public void visitSwitchInsn(SwitchInsn switchInsn) {
        }

        @Override // com.android.cglib.dx.rop.code.Insn.Visitor
        public void visitThrowingCstInsn(ThrowingCstInsn throwingCstInsn) {
        }

        @Override // com.android.cglib.dx.rop.code.Insn.Visitor
        public void visitThrowingInsn(ThrowingInsn throwingInsn) {
        }
    }

    public interface Visitor {
        void visitFillArrayDataInsn(FillArrayDataInsn fillArrayDataInsn);

        void visitPlainCstInsn(PlainCstInsn plainCstInsn);

        void visitPlainInsn(PlainInsn plainInsn);

        void visitSwitchInsn(SwitchInsn switchInsn);

        void visitThrowingCstInsn(ThrowingCstInsn throwingCstInsn);

        void visitThrowingInsn(ThrowingInsn throwingInsn);
    }

    public Insn(Rop rop, SourcePosition sourcePosition, RegisterSpec registerSpec, RegisterSpecList registerSpecList) {
        Objects.requireNonNull(rop, "opcode == null");
        Objects.requireNonNull(sourcePosition, "position == null");
        Objects.requireNonNull(registerSpecList, "sources == null");
        this.opcode = rop;
        this.position = sourcePosition;
        this.result = registerSpec;
        this.sources = registerSpecList;
    }

    private static boolean equalsHandleNulls(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public abstract void accept(Visitor visitor);

    public final boolean canThrow() {
        return this.opcode.canThrow();
    }

    public boolean contentEquals(Insn insn) {
        return this.opcode == insn.getOpcode() && this.position.equals(insn.getPosition()) && getClass() == insn.getClass() && equalsHandleNulls(this.result, insn.getResult()) && equalsHandleNulls(this.sources, insn.getSources()) && StdTypeList.equalContents(getCatches(), insn.getCatches());
    }

    public Insn copy() {
        return withRegisterOffset(0);
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public abstract TypeList getCatches();

    public String getInlineString() {
        return null;
    }

    public final RegisterSpec getLocalAssignment() {
        RegisterSpec registerSpec = this.opcode.getOpcode() == 54 ? this.sources.get(0) : this.result;
        if (registerSpec == null || registerSpec.getLocalItem() == null) {
            return null;
        }
        return registerSpec;
    }

    public final Rop getOpcode() {
        return this.opcode;
    }

    public final SourcePosition getPosition() {
        return this.position;
    }

    public final RegisterSpec getResult() {
        return this.result;
    }

    public final RegisterSpecList getSources() {
        return this.sources;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return toHumanWithInline(getInlineString());
    }

    public final String toHumanWithInline(String str) {
        StringBuffer stringBuffer = new StringBuffer(80);
        stringBuffer.append(this.position);
        stringBuffer.append(": ");
        stringBuffer.append(this.opcode.getNickname());
        if (str != null) {
            stringBuffer.append("(");
            stringBuffer.append(str);
            stringBuffer.append(")");
        }
        if (this.result == null) {
            stringBuffer.append(" .");
        } else {
            stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
            stringBuffer.append(this.result.toHuman());
        }
        stringBuffer.append(" <-");
        int size = this.sources.size();
        if (size == 0) {
            stringBuffer.append(" .");
        } else {
            for (int i = 0; i < size; i++) {
                stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
                stringBuffer.append(this.sources.get(i).toHuman());
            }
        }
        return stringBuffer.toString();
    }

    public String toString() {
        return toStringWithInline(getInlineString());
    }

    public final String toStringWithInline(String str) {
        StringBuffer stringBuffer = new StringBuffer(80);
        stringBuffer.append("Insn{");
        stringBuffer.append(this.position);
        stringBuffer.append(' ');
        stringBuffer.append(this.opcode);
        if (str != null) {
            stringBuffer.append(' ');
            stringBuffer.append(str);
        }
        stringBuffer.append(" :: ");
        RegisterSpec registerSpec = this.result;
        if (registerSpec != null) {
            stringBuffer.append(registerSpec);
            stringBuffer.append(" <- ");
        }
        stringBuffer.append(this.sources);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public abstract Insn withAddedCatch(Type type);

    public abstract Insn withNewRegisters(RegisterSpec registerSpec, RegisterSpecList registerSpecList);

    public abstract Insn withRegisterOffset(int i);

    public Insn withSourceLiteral() {
        return this;
    }
}

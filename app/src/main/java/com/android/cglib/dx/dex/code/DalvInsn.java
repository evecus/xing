package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.SourcePosition;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import com.android.cglib.dx.util.TwoColumnOutput;
import com.baidu.android.common.util.HanziToPinyin;
import java.util.BitSet;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class DalvInsn {
    private int address;
    private final Dop opcode;
    private final SourcePosition position;
    private final RegisterSpecList registers;

    public DalvInsn(Dop dop, SourcePosition sourcePosition, RegisterSpecList registerSpecList) {
        Objects.requireNonNull(dop, "opcode == null");
        Objects.requireNonNull(sourcePosition, "position == null");
        Objects.requireNonNull(registerSpecList, "registers == null");
        this.address = -1;
        this.opcode = dop;
        this.position = sourcePosition;
        this.registers = registerSpecList;
    }

    public static SimpleInsn makeMove(SourcePosition sourcePosition, RegisterSpec registerSpec, RegisterSpec registerSpec2) {
        boolean z = registerSpec.getCategory() == 1;
        boolean zIsReference = registerSpec.getType().isReference();
        int reg = registerSpec.getReg();
        return new SimpleInsn((registerSpec2.getReg() | reg) < 16 ? zIsReference ? Dops.MOVE_OBJECT : z ? Dops.MOVE : Dops.MOVE_WIDE : reg < 256 ? zIsReference ? Dops.MOVE_OBJECT_FROM16 : z ? Dops.MOVE_FROM16 : Dops.MOVE_WIDE_FROM16 : zIsReference ? Dops.MOVE_OBJECT_16 : z ? Dops.MOVE_16 : Dops.MOVE_WIDE_16, sourcePosition, RegisterSpecList.make(registerSpec, registerSpec2));
    }

    public abstract String argString();

    public abstract int codeSize();

    public DalvInsn expandedPrefix(BitSet bitSet) {
        RegisterSpecList registerSpecList = this.registers;
        boolean z = bitSet.get(0);
        if (hasResult()) {
            bitSet.set(0);
        }
        RegisterSpecList registerSpecListSubset = registerSpecList.subset(bitSet);
        if (hasResult()) {
            bitSet.set(0, z);
        }
        if (registerSpecListSubset.size() == 0) {
            return null;
        }
        return new HighRegisterPrefix(this.position, registerSpecListSubset);
    }

    public DalvInsn expandedSuffix(BitSet bitSet) {
        if (!hasResult() || bitSet.get(0)) {
            return null;
        }
        RegisterSpec registerSpec = this.registers.get(0);
        return makeMove(this.position, registerSpec, registerSpec.withReg(0));
    }

    public DalvInsn expandedVersion(BitSet bitSet) {
        return withRegisters(this.registers.withExpandedRegisters(0, hasResult(), bitSet));
    }

    public final int getAddress() {
        int i = this.address;
        if (i >= 0) {
            return i;
        }
        throw new RuntimeException("address not yet known");
    }

    public DalvInsn getLowRegVersion() {
        return withRegisters(this.registers.withExpandedRegisters(0, hasResult(), null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.android.cglib.dx.rop.code.RegisterSpecList] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.BitSet] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final int getMinimumRegisterRequirement(BitSet bitSet) {
        ?? HasResult = hasResult();
        int size = this.registers.size();
        int category = 0;
        int category2 = (HasResult == 0 || bitSet.get(0)) ? 0 : this.registers.get(0).getCategory();
        while (HasResult < size) {
            if (!bitSet.get(HasResult)) {
                category += this.registers.get(HasResult).getCategory();
            }
            HasResult++;
        }
        return Math.max(category, category2);
    }

    public final int getNextAddress() {
        return getAddress() + codeSize();
    }

    public final Dop getOpcode() {
        return this.opcode;
    }

    public final SourcePosition getPosition() {
        return this.position;
    }

    public final RegisterSpecList getRegisters() {
        return this.registers;
    }

    public final boolean hasAddress() {
        return this.address >= 0;
    }

    public final boolean hasResult() {
        return this.opcode.hasResult();
    }

    public final String identifierString() {
        int i = this.address;
        return i != -1 ? String.format("%04x", Integer.valueOf(i)) : Hex.u4(System.identityHashCode(this));
    }

    public final String listingString(String str, int i, boolean z) {
        String strListingString0 = listingString0(z);
        if (strListingString0 == null) {
            return null;
        }
        StringBuilder sbO = a.o(str);
        sbO.append(identifierString());
        sbO.append(": ");
        String string = sbO.toString();
        int length = string.length();
        return TwoColumnOutput.toString(string, length, "", strListingString0, i == 0 ? strListingString0.length() : i - length);
    }

    public abstract String listingString0(boolean z);

    public final void setAddress(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("address < 0");
        }
        this.address = i;
    }

    public final String toString() {
        boolean z;
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(identifierString());
        stringBuffer.append(' ');
        stringBuffer.append(this.position);
        stringBuffer.append(": ");
        stringBuffer.append(this.opcode.getName());
        if (this.registers.size() != 0) {
            stringBuffer.append(this.registers.toHuman(HanziToPinyin.Token.SEPARATOR, ", ", null));
            z = true;
        } else {
            z = false;
        }
        String strArgString = argString();
        if (strArgString != null) {
            if (z) {
                stringBuffer.append(',');
            }
            stringBuffer.append(' ');
            stringBuffer.append(strArgString);
        }
        return stringBuffer.toString();
    }

    public abstract DalvInsn withOpcode(Dop dop);

    public abstract DalvInsn withRegisterOffset(int i);

    public abstract DalvInsn withRegisters(RegisterSpecList registerSpecList);

    public abstract void writeTo(AnnotatedOutput annotatedOutput);
}

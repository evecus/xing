package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.SourcePosition;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class TargetInsn extends FixedSizeInsn {
    private CodeAddress target;

    public TargetInsn(Dop dop, SourcePosition sourcePosition, RegisterSpecList registerSpecList, CodeAddress codeAddress) {
        super(dop, sourcePosition, registerSpecList);
        Objects.requireNonNull(codeAddress, "target == null");
        this.target = codeAddress;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String argString() {
        CodeAddress codeAddress = this.target;
        return codeAddress == null ? "????" : codeAddress.identifierString();
    }

    public CodeAddress getTarget() {
        return this.target;
    }

    public int getTargetAddress() {
        return this.target.getAddress();
    }

    public int getTargetOffset() {
        return this.target.getAddress() - getAddress();
    }

    public boolean hasTargetOffset() {
        return hasAddress() && this.target.hasAddress();
    }

    public TargetInsn withNewTargetAndReversed(CodeAddress codeAddress) {
        return new TargetInsn(getOpcode().getOppositeTest(), getPosition(), getRegisters(), codeAddress);
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withOpcode(Dop dop) {
        return new TargetInsn(dop, getPosition(), getRegisters(), this.target);
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList registerSpecList) {
        return new TargetInsn(getOpcode(), getPosition(), registerSpecList, this.target);
    }
}

package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.SourcePosition;

/* JADX INFO: loaded from: classes.dex */
public final class SimpleInsn extends FixedSizeInsn {
    public SimpleInsn(Dop dop, SourcePosition sourcePosition, RegisterSpecList registerSpecList) {
        super(dop, sourcePosition, registerSpecList);
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String argString() {
        return null;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withOpcode(Dop dop) {
        return new SimpleInsn(dop, getPosition(), getRegisters());
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList registerSpecList) {
        return new SimpleInsn(getOpcode(), getPosition(), registerSpecList);
    }
}

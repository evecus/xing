package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.SourcePosition;

/* JADX INFO: loaded from: classes.dex */
public final class CodeAddress extends ZeroSizeInsn {
    public CodeAddress(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String argString() {
        return null;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String listingString0(boolean z) {
        return "code-address";
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public final DalvInsn withRegisters(RegisterSpecList registerSpecList) {
        return new CodeAddress(getPosition());
    }
}

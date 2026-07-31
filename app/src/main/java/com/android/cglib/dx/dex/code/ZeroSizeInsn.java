package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.SourcePosition;
import com.android.cglib.dx.util.AnnotatedOutput;

/* JADX INFO: loaded from: classes.dex */
public abstract class ZeroSizeInsn extends DalvInsn {
    public ZeroSizeInsn(SourcePosition sourcePosition) {
        super(Dops.SPECIAL_FORMAT, sourcePosition, RegisterSpecList.EMPTY);
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public final int codeSize() {
        return 0;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public final DalvInsn withOpcode(Dop dop) {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withRegisterOffset(int i) {
        return withRegisters(getRegisters().withOffset(i));
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public final void writeTo(AnnotatedOutput annotatedOutput) {
    }
}

package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.RegisterSpecSet;
import com.android.cglib.dx.rop.code.SourcePosition;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class LocalSnapshot extends ZeroSizeInsn {
    private final RegisterSpecSet locals;

    public LocalSnapshot(SourcePosition sourcePosition, RegisterSpecSet registerSpecSet) {
        super(sourcePosition);
        Objects.requireNonNull(registerSpecSet, "locals == null");
        this.locals = registerSpecSet;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String argString() {
        return this.locals.toString();
    }

    public RegisterSpecSet getLocals() {
        return this.locals;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String listingString0(boolean z) {
        int size = this.locals.size();
        int maxSize = this.locals.getMaxSize();
        StringBuffer stringBuffer = new StringBuffer((size * 40) + 100);
        stringBuffer.append("local-snapshot");
        for (int i = 0; i < maxSize; i++) {
            RegisterSpec registerSpec = this.locals.get(i);
            if (registerSpec != null) {
                stringBuffer.append("\n  ");
                stringBuffer.append(LocalStart.localString(registerSpec));
            }
        }
        return stringBuffer.toString();
    }

    @Override // com.android.cglib.dx.dex.code.ZeroSizeInsn, com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withRegisterOffset(int i) {
        return new LocalSnapshot(getPosition(), this.locals.withOffset(i));
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList registerSpecList) {
        return new LocalSnapshot(getPosition(), this.locals);
    }
}

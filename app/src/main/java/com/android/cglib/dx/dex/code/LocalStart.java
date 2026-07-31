package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.SourcePosition;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class LocalStart extends ZeroSizeInsn {
    private final RegisterSpec local;

    public LocalStart(SourcePosition sourcePosition, RegisterSpec registerSpec) {
        super(sourcePosition);
        Objects.requireNonNull(registerSpec, "local == null");
        this.local = registerSpec;
    }

    public static String localString(RegisterSpec registerSpec) {
        return registerSpec.regString() + ' ' + registerSpec.getLocalItem().toString() + ": " + registerSpec.getTypeBearer().toHuman();
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String argString() {
        return this.local.toString();
    }

    public RegisterSpec getLocal() {
        return this.local;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String listingString0(boolean z) {
        StringBuilder sbO = a.o("local-start ");
        sbO.append(localString(this.local));
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.dex.code.ZeroSizeInsn, com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withRegisterOffset(int i) {
        return new LocalStart(getPosition(), this.local.withOffset(i));
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList registerSpecList) {
        return new LocalStart(getPosition(), this.local);
    }
}

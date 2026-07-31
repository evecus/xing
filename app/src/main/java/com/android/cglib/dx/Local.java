package com.android.cglib.dx;

import com.android.cglib.dx.rop.code.RegisterSpec;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Local<T> {
    private final Code code;
    private int reg = -1;
    private RegisterSpec spec;
    public final TypeId<T> type;

    private Local(Code code, TypeId<T> typeId) {
        this.code = code;
        this.type = typeId;
    }

    public static <T> Local<T> get(Code code, TypeId<T> typeId) {
        return new Local<>(code, typeId);
    }

    public TypeId getType() {
        return this.type;
    }

    public int initialize(int i) {
        this.reg = i;
        this.spec = RegisterSpec.make(i, this.type.ropType);
        return size();
    }

    public int size() {
        return this.type.ropType.getCategory();
    }

    public RegisterSpec spec() {
        if (this.spec == null) {
            this.code.initializeLocals();
            if (this.spec == null) {
                throw new AssertionError();
            }
        }
        return this.spec;
    }

    public String toString() {
        StringBuilder sbO = a.o("v");
        sbO.append(this.reg);
        sbO.append("(");
        sbO.append(this.type);
        sbO.append(")");
        return sbO.toString();
    }
}

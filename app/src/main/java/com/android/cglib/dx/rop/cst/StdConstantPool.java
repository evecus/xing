package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.util.ExceptionWithContext;
import com.android.cglib.dx.util.Hex;
import com.android.cglib.dx.util.MutabilityControl;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class StdConstantPool extends MutabilityControl implements ConstantPool {
    private final Constant[] entries;

    public StdConstantPool(int i) {
        super(i > 1);
        if (i < 1) {
            throw new IllegalArgumentException("size < 1");
        }
        this.entries = new Constant[i];
    }

    private static Constant throwInvalid(int i) {
        StringBuilder sbO = a.o("invalid constant pool index ");
        sbO.append(Hex.u2(i));
        throw new ExceptionWithContext(sbO.toString());
    }

    @Override // com.android.cglib.dx.rop.cst.ConstantPool
    public Constant get(int i) {
        try {
            Constant constant = this.entries[i];
            if (constant != null) {
                return constant;
            }
            throwInvalid(i);
            return constant;
        } catch (IndexOutOfBoundsException e) {
            return throwInvalid(i);
        }
    }

    @Override // com.android.cglib.dx.rop.cst.ConstantPool
    public Constant get0Ok(int i) {
        if (i == 0) {
            return null;
        }
        return get(i);
    }

    @Override // com.android.cglib.dx.rop.cst.ConstantPool
    public Constant getOrNull(int i) {
        try {
            return this.entries[i];
        } catch (IndexOutOfBoundsException e) {
            return throwInvalid(i);
        }
    }

    public void set(int i, Constant constant) {
        int i2;
        Constant constant2;
        throwIfImmutable();
        boolean z = constant != null && constant.isCategory2();
        if (i < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        if (z) {
            Constant[] constantArr = this.entries;
            if (i == constantArr.length - 1) {
                throw new IllegalArgumentException("(n == size - 1) && cst.isCategory2()");
            }
            constantArr[i + 1] = null;
        }
        if (constant != null) {
            Constant[] constantArr2 = this.entries;
            if (constantArr2[i] == null && (constant2 = constantArr2[i - 1]) != null && constant2.isCategory2()) {
                this.entries[i2] = null;
            }
        }
        this.entries[i] = constant;
    }

    @Override // com.android.cglib.dx.rop.cst.ConstantPool
    public int size() {
        return this.entries.length;
    }
}

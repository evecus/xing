package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.SourcePosition;
import com.android.cglib.dx.rop.cst.Constant;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstInsn extends FixedSizeInsn {
    private int classIndex;
    private final Constant constant;
    private int index;

    public CstInsn(Dop dop, SourcePosition sourcePosition, RegisterSpecList registerSpecList, Constant constant) {
        super(dop, sourcePosition, registerSpecList);
        Objects.requireNonNull(constant, "constant == null");
        this.constant = constant;
        this.index = -1;
        this.classIndex = -1;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String argString() {
        return this.constant.toHuman();
    }

    public int getClassIndex() {
        int i = this.classIndex;
        if (i >= 0) {
            return i;
        }
        throw new RuntimeException("class index not yet set");
    }

    public Constant getConstant() {
        return this.constant;
    }

    public int getIndex() {
        int i = this.index;
        if (i >= 0) {
            return i;
        }
        StringBuilder sbO = a.o("index not yet set for ");
        sbO.append(this.constant);
        throw new RuntimeException(sbO.toString());
    }

    public boolean hasClassIndex() {
        return this.classIndex >= 0;
    }

    public boolean hasIndex() {
        return this.index >= 0;
    }

    public void setClassIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index < 0");
        }
        if (this.classIndex >= 0) {
            throw new RuntimeException("class index already set");
        }
        this.classIndex = i;
    }

    public void setIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index < 0");
        }
        if (this.index >= 0) {
            throw new RuntimeException("index already set");
        }
        this.index = i;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withOpcode(Dop dop) {
        CstInsn cstInsn = new CstInsn(dop, getPosition(), getRegisters(), this.constant);
        int i = this.index;
        if (i >= 0) {
            cstInsn.setIndex(i);
        }
        int i2 = this.classIndex;
        if (i2 >= 0) {
            cstInsn.setClassIndex(i2);
        }
        return cstInsn;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList registerSpecList) {
        CstInsn cstInsn = new CstInsn(getOpcode(), getPosition(), registerSpecList, this.constant);
        int i = this.index;
        if (i >= 0) {
            cstInsn.setIndex(i);
        }
        int i2 = this.classIndex;
        if (i2 >= 0) {
            cstInsn.setClassIndex(i2);
        }
        return cstInsn;
    }
}

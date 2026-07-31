package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.TypeBearer;

/* JADX INFO: loaded from: classes.dex */
public abstract class TypedConstant extends Constant implements TypeBearer {
    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public final int getBasicFrameType() {
        return getType().getBasicFrameType();
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public final int getBasicType() {
        return getType().getBasicType();
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public final TypeBearer getFrameType() {
        return this;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public final boolean isConstant() {
        return true;
    }
}

package com.android.cglib.dx.rop.type;

import com.android.cglib.dx.util.ToHuman;

/* JADX INFO: loaded from: classes.dex */
public interface TypeBearer extends ToHuman {
    int getBasicFrameType();

    int getBasicType();

    TypeBearer getFrameType();

    Type getType();

    boolean isConstant();
}

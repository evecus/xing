package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.util.ToHuman;

/* JADX INFO: loaded from: classes.dex */
public abstract class Constant implements ToHuman, Comparable<Constant> {
    @Override // java.lang.Comparable
    public final int compareTo(Constant constant) {
        Class<?> cls = getClass();
        Class<?> cls2 = constant.getClass();
        return cls != cls2 ? cls.getName().compareTo(cls2.getName()) : compareTo0(constant);
    }

    public abstract int compareTo0(Constant constant);

    public abstract boolean isCategory2();

    public abstract String typeName();
}

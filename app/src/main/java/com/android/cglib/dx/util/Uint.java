package com.android.cglib.dx.util;

/* JADX INFO: loaded from: classes.dex */
public final class Uint implements Comparable<Uint> {
    public final int intValue;

    public Uint(int i) {
        this.intValue = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(Uint uint) {
        return Unsigned.compare(this.intValue, uint.intValue);
    }
}

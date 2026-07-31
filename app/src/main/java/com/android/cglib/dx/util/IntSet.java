package com.android.cglib.dx.util;

/* JADX INFO: loaded from: classes.dex */
public interface IntSet {
    void add(int i);

    int elements();

    boolean has(int i);

    IntIterator iterator();

    void merge(IntSet intSet);

    void remove(int i);
}

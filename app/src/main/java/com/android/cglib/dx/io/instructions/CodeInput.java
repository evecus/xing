package com.android.cglib.dx.io.instructions;

/* JADX INFO: loaded from: classes.dex */
public interface CodeInput extends CodeCursor {
    boolean hasMore();

    int read();

    int readInt();

    long readLong();
}

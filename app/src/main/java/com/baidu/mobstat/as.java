package com.baidu.mobstat;

/* JADX INFO: loaded from: classes.dex */
public class as {
    private long a;

    public as() {
        this(0L);
    }

    public as(long j) {
        this.a = j;
    }

    public boolean a(long j, long j2) {
        long j3 = this.a;
        long j4 = (j & j2) | ((~j2) & j3);
        this.a = j4;
        return (j4 ^ j3) != 0;
    }
}

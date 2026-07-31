package com.baidu.mobstat;

/* JADX INFO: loaded from: classes.dex */
public class dg extends Exception {
    private int a;

    public dg(int i) {
        this.a = i;
    }

    public dg(int i, String str) {
        super(str);
        this.a = i;
    }

    public dg(int i, Throwable th) {
        super(th);
        this.a = i;
    }

    public int a() {
        return this.a;
    }
}

package com.baidu.mobstat;

/* JADX INFO: loaded from: classes.dex */
public class bw extends bu {
    private static bw b = new bw();

    private bw() {
    }

    public static bw c() {
        return b;
    }

    @Override // com.baidu.mobstat.bu
    public String a() {
        return "mtj.autotrace";
    }

    @Override // com.baidu.mobstat.bu
    public boolean b() {
        return false;
    }
}

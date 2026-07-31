package com.baidu.mobstat;

/* JADX INFO: loaded from: classes.dex */
public class bv extends bu {
    private static bv c = new bv();
    private boolean b;

    private bv() {
    }

    public static bv c() {
        return c;
    }

    @Override // com.baidu.mobstat.bu
    public String a() {
        return "BaiduMobStat";
    }

    public void a(boolean z) {
        this.b = z;
    }

    @Override // com.baidu.mobstat.bu
    public boolean b() {
        return this.b;
    }
}

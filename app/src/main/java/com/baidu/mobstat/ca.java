package com.baidu.mobstat;

/* JADX INFO: loaded from: classes.dex */
public class ca extends bu {
    private static ca b = new ca();

    private ca() {
    }

    public static ca c() {
        return b;
    }

    @Override // com.baidu.mobstat.bu
    public String a() {
        return "mtj.fulltrace";
    }

    @Override // com.baidu.mobstat.bu
    public boolean b() {
        return false;
    }
}

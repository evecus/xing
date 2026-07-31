package com.baidu.mobstat;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bj {
    private String a;
    private long b;
    private long c;
    private boolean d;
    private long e;

    public bj(String str, long j, long j2, long j3, boolean z) {
        this.a = str;
        this.b = j;
        this.c = j2;
        this.e = j3;
        this.d = z;
    }

    public String a() {
        return this.a;
    }

    public JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.FEED_LIST_NAME, str);
            jSONObject.put("s", this.b);
            jSONObject.put("e", this.c);
            jSONObject.put("user", this.d ? 1 : 0);
            return jSONObject;
        } catch (Exception e) {
            return null;
        }
    }

    public void a(long j) {
        this.c = j;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.e = j;
    }

    public long c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }
}

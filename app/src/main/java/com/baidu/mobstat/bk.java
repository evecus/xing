package com.baidu.mobstat;

import android.text.TextUtils;
import com.baidu.mobstat.cl;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bk {
    private String a;
    private String b;
    private JSONArray c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    private long h;
    private long i;
    private long j;
    private int k;
    private String l;
    private String m;
    private String n;
    private JSONArray o;

    public bk(String str, String str2, JSONArray jSONArray, String str3, String str4, String str5, String str6, boolean z, int i, long j, long j2, long j3, String str7, String str8, JSONArray jSONArray2) {
        this.a = str;
        this.b = str2;
        this.c = jSONArray;
        this.d = str3;
        this.e = str5;
        this.f = str6;
        this.g = z;
        this.k = i;
        this.h = j;
        this.i = j2;
        this.j = j3;
        this.l = str7;
        this.m = str8;
        this.n = str4;
        this.o = jSONArray2;
    }

    public static String a(String str, String str2, JSONArray jSONArray, String str3, String str4, String str5, boolean z) {
        String string;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Config.FEED_LIST_ITEM_CUSTOM_ID, str);
            jSONObject.put("p", str2);
            jSONObject.put(Config.FEED_LIST_ITEM_PATH, jSONArray);
            jSONObject.put(Config.FEED_LIST_ITEM_TITLE, str3);
            jSONObject.put(Config.FEED_LIST_ITEM_INDEX, str4);
            jSONObject.put("n", str5);
            jSONObject.put("user", z ? 1 : 0);
            string = jSONObject.toString();
        } catch (Exception e) {
            string = "";
        }
        return !TextUtils.isEmpty(string) ? cl.a.a(string.getBytes()) : "";
    }

    public String a() {
        return this.a;
    }

    public JSONObject a(String str, String str2, String str3) {
        String strA = a(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.FEED_LIST_ITEM_CUSTOM_ID, this.a);
            jSONObject.put("d", this.l);
            jSONObject.put("p", str);
            jSONObject.put(Config.FEED_LIST_ITEM_PATH, str3);
            JSONArray jSONArray = this.o;
            jSONObject.put("v5", (jSONArray == null || jSONArray.length() == 0) ? "" : this.o);
            jSONObject.put(Config.FEED_LIST_ITEM_TITLE, this.d);
            jSONObject.put("content", this.n);
            jSONObject.put(Config.FEED_LIST_ITEM_INDEX, this.e);
            jSONObject.put("n", str2);
            jSONObject.put("user", this.g ? 1 : 0);
            jSONObject.put("c", this.k);
            jSONObject.put("t", this.h);
            jSONObject.put("ps", this.m);
            jSONObject.put("sign", strA);
            return jSONObject;
        } catch (Exception e) {
            return null;
        }
    }

    public void a(int i) {
        this.k = i;
    }

    public void a(long j) {
        this.i = j;
    }

    public void a(String str) {
        this.l = str;
    }

    public String b() {
        return this.b;
    }

    public void b(long j) {
        this.j = j;
    }

    public void b(String str) {
        this.m = str;
    }

    public JSONArray c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public int h() {
        return this.k;
    }

    public long i() {
        return this.h;
    }

    public long j() {
        return this.i;
    }

    public long k() {
        return this.j;
    }

    public String l() {
        return this.l;
    }
}

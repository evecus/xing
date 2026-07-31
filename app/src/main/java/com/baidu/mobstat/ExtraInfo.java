package com.baidu.mobstat;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ExtraInfo {
    String a = "";
    String b = "";
    String c = "";
    String d = "";
    String e = "";
    String f = "";
    String g = "";
    String h = "";
    String i = "";
    String j = "";

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return a(str, 1024) ? "" : str;
    }

    private static boolean a(String str, int i) {
        int length;
        if (str == null) {
            return false;
        }
        try {
            length = str.getBytes().length;
        } catch (Exception e) {
            length = 0;
        }
        return length > i;
    }

    public JSONObject dumpToJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.a)) {
                jSONObject.put("v1", this.a);
            }
            if (!TextUtils.isEmpty(this.b)) {
                jSONObject.put("v2", this.b);
            }
            if (!TextUtils.isEmpty(this.c)) {
                jSONObject.put("v3", this.c);
            }
            if (!TextUtils.isEmpty(this.d)) {
                jSONObject.put("v4", this.d);
            }
            if (!TextUtils.isEmpty(this.e)) {
                jSONObject.put("v5", this.e);
            }
            if (!TextUtils.isEmpty(this.f)) {
                jSONObject.put("v6", this.f);
            }
            if (!TextUtils.isEmpty(this.g)) {
                jSONObject.put("v7", this.g);
            }
            if (!TextUtils.isEmpty(this.h)) {
                jSONObject.put("v8", this.h);
            }
            if (!TextUtils.isEmpty(this.i)) {
                jSONObject.put("v9", this.i);
            }
            if (!TextUtils.isEmpty(this.j)) {
                jSONObject.put("v10", this.j);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public String getV1() {
        return this.a;
    }

    public String getV10() {
        return this.j;
    }

    public String getV2() {
        return this.b;
    }

    public String getV3() {
        return this.c;
    }

    public String getV4() {
        return this.d;
    }

    public String getV5() {
        return this.e;
    }

    public String getV6() {
        return this.f;
    }

    public String getV7() {
        return this.g;
    }

    public String getV8() {
        return this.h;
    }

    public String getV9() {
        return this.i;
    }

    public void setV1(String str) {
        this.a = a(str);
    }

    public void setV10(String str) {
        this.j = a(str);
    }

    public void setV2(String str) {
        this.b = a(str);
    }

    public void setV3(String str) {
        this.c = a(str);
    }

    public void setV4(String str) {
        this.d = a(str);
    }

    public void setV5(String str) {
        this.e = a(str);
    }

    public void setV6(String str) {
        this.f = a(str);
    }

    public void setV7(String str) {
        this.g = a(str);
    }

    public void setV8(String str) {
        this.h = a(str);
    }

    public void setV9(String str) {
        this.i = a(str);
    }
}

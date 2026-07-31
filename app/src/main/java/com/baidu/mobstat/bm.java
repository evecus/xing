package com.baidu.mobstat;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bm {
    private static final bm e = new bm();
    private boolean a = false;
    private float b = 50.0f;
    private long c = 500;
    private volatile boolean d;

    public static bm a() {
        return e;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONObject(str).opt("sv");
            if (jSONObject != null) {
                int iOptInt = jSONObject.optInt("close");
                String strOptString = jSONObject.optString("area");
                String strOptString2 = jSONObject.optString("duration");
                this.a = iOptInt != 0;
                if (!TextUtils.isEmpty(strOptString)) {
                    try {
                        this.b = Float.valueOf(strOptString).floatValue();
                    } catch (Exception e2) {
                    }
                }
                if (!TextUtils.isEmpty(strOptString2)) {
                    try {
                        this.c = Long.valueOf(strOptString2).longValue();
                    } catch (Exception e3) {
                    }
                }
            }
        } catch (Exception e4) {
        }
        this.d = true;
    }

    public boolean b() {
        return this.a;
    }

    public float c() {
        float f = this.b;
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 100.0f) {
            f = 100.0f;
        }
        return f / 100.0f;
    }

    public long d() {
        return this.c;
    }
}

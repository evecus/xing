package com.baidu.mobstat;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class br {
    private static final br c = new br();
    private boolean a = false;
    private volatile boolean b;

    public static br a() {
        return c;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONObject(str).opt("full");
            this.a = (jSONObject != null ? jSONObject.optInt("close") : 0) != 0;
        } catch (Exception e) {
        }
        this.b = true;
    }

    public boolean b() {
        return this.a;
    }
}

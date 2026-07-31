package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LaunchInfo {
    private String a;
    private String b;
    private String c;

    public static JSONObject getConvertedJson(int i, String str, String str2) {
        JSONObject jSONObject = null;
        try {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("type", String.valueOf(i));
                if (str == null) {
                    str = "";
                }
                jSONObject2.put(Config.LAUNCH_REFERER, str);
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject2.put(Config.LAUNCH_INFO, str2);
                jSONObject2.put("content", "");
                return jSONObject2;
            } catch (Exception e) {
                jSONObject = jSONObject2;
                return jSONObject;
            }
        } catch (Exception e2) {
        }
    }

    public static String getLauncherHomePkgName(Context context) {
        String strA = cc.a(context);
        return !TextUtils.isEmpty(strA) ? strA : "";
    }

    public int getLaunchType(Context context) {
        if (!TextUtils.isEmpty(this.a)) {
            return 2;
        }
        String packageName = context != null ? context.getPackageName() : "";
        if (TextUtils.isEmpty(this.c) || this.c.equals(packageName)) {
            return 0;
        }
        String strA = cc.a(context);
        return !TextUtils.isEmpty(strA) ? !this.c.equals(strA) ? 1 : 0 : !cc.a(context, this.c) ? 1 : 0;
    }

    public String getPushContent() {
        return !TextUtils.isEmpty(this.b) ? this.b : "";
    }

    public String getPushLandingPage() {
        return !TextUtils.isEmpty(this.a) ? this.a : "";
    }

    public String getRefererPkgName() {
        return !TextUtils.isEmpty(this.c) ? this.c : "";
    }

    public void setPushInfo(String str, String str2) {
        this.a = str;
        this.b = cq.a(str2, 1024);
    }

    public void setRefererPkgName(String str) {
        this.c = str;
    }
}

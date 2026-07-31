package roam.a.a.f.h;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import org.json.JSONException;
import org.json.JSONObject;
import roam.a.a.f.j.g;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public String a;
    public String b;
    public Context c;

    public a(Context context) {
        this.a = "";
        this.b = "";
        this.c = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.a = packageInfo.versionName;
            this.b = packageInfo.packageName;
            this.c = context.getApplicationContext();
        } catch (Exception e) {
        }
    }

    public static String c(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(str2);
        for (int i = 0; i < strArrSplit.length; i++) {
            if (!TextUtils.isEmpty(strArrSplit[i]) && strArrSplit[i].startsWith(str3)) {
                return strArrSplit[i];
            }
        }
        return null;
    }

    public final String a(String str) {
        StringBuilder sb;
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        try {
            if (!str.contains("\"&")) {
                String strC = c(str, "&", "bizcontext=");
                if (TextUtils.isEmpty(strC)) {
                    return str + "&" + d("bizcontext=", "");
                }
                int iIndexOf = str.indexOf(strC);
                String strSubstring = str.substring(0, iIndexOf);
                String strSubstring2 = str.substring(iIndexOf + strC.length());
                sb = new StringBuilder();
                sb.append(strSubstring);
                sb.append(e(strC, "bizcontext=", ""));
                sb.append(strSubstring2);
            } else {
                String strC2 = c(str, "\"&", "bizcontext=\"");
                if (TextUtils.isEmpty(strC2)) {
                    return str + "&" + d("bizcontext=\"", "\"");
                }
                if (!strC2.endsWith("\"")) {
                    strC2 = strC2 + "\"";
                }
                int iIndexOf2 = str.indexOf(strC2);
                String strSubstring3 = str.substring(0, iIndexOf2);
                String strSubstring4 = str.substring(iIndexOf2 + strC2.length());
                sb = new StringBuilder();
                sb.append(strSubstring3);
                sb.append(e(strC2, "bizcontext=\"", "\""));
                sb.append(strSubstring4);
            }
            return sb.toString();
        } catch (Throwable th) {
            return str;
        }
    }

    public final String b(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", "2014052600006128");
            jSONObject.put(Config.EXCEPTION_CRASH_CHANNEL, "and_lite");
            jSONObject.put("sv", "h.a.3.5.5");
            if (!this.b.contains("setting") || !g.i(this.c)) {
                jSONObject.put("an", this.b);
            }
            jSONObject.put("av", this.a);
            jSONObject.put("sdk_start_time", System.currentTimeMillis());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    public final String d(String str, String str2) {
        return roam.a.b.a.a.a.k(str, b("", ""), str2);
    }

    public final String e(String str, String str2, String str3) throws JSONException {
        String strSubstring = str.substring(str2.length());
        JSONObject jSONObject = new JSONObject(strSubstring.substring(0, strSubstring.length() - str3.length()));
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", "2014052600006128");
        }
        if (!jSONObject.has(Config.EXCEPTION_CRASH_CHANNEL)) {
            jSONObject.put(Config.EXCEPTION_CRASH_CHANNEL, "and_lite");
        }
        if (!jSONObject.has("sv")) {
            jSONObject.put("sv", "h.a.3.5.5");
        }
        if (!jSONObject.has("an") && (!this.b.contains("setting") || !g.i(this.c))) {
            jSONObject.put("an", this.b);
        }
        if (!jSONObject.has("av")) {
            jSONObject.put("av", this.a);
        }
        if (!jSONObject.has("sdk_start_time")) {
            jSONObject.put("sdk_start_time", System.currentTimeMillis());
        }
        return roam.a.b.a.a.a.k(str2, jSONObject.toString(), str3);
    }
}

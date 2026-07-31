package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class cj extends cg {
    private static final String a = "__Baidu_Stat_SDK_SendRem";
    private static cj b = new cj();

    private cj() {
    }

    public static cj a() {
        return b;
    }

    @Override // com.baidu.mobstat.cg
    public SharedPreferences a(Context context) {
        return context.getSharedPreferences(a, 0);
    }

    public void a(Context context, int i) {
        b(context, "sendLogtype", i);
    }

    public void a(Context context, long j) {
        b(context, "autotrace_track_js_fetch_time", j);
    }

    public void a(Context context, String str) {
        b(context, "device_id_1", str);
    }

    public void a(Context context, boolean z) {
        b(context, "onlywifi", z);
    }

    public int b(Context context) {
        return a(context, "sendLogtype", 0);
    }

    public void b(Context context, int i) {
        b(context, "timeinterval", i);
    }

    public void b(Context context, long j) {
        b(context, "autotrace_track_js_fetch_interval", j);
    }

    public void b(Context context, String str) {
        if (a(context, "cuid", (String) null) != null) {
            c(context, "cuid");
        }
        b(context, "cuidsec_1", str);
        c(context, "cuidsec_1");
        c(context, "cuidsec_1");
        c(context, "cuidsec_2");
    }

    public void b(Context context, boolean z) {
        b(context, "setchannelwithcode", z);
    }

    public int c(Context context) {
        return a(context, "timeinterval", 1);
    }

    public void c(Context context, long j) {
        b(context, "autotrace_config_fetch_time", j);
    }

    public void c(Context context, boolean z) {
        b(context, "mtjtv", z);
    }

    public void d(Context context, String str) {
        b(context, "setchannelwithcodevalue", str);
    }

    public void d(Context context, boolean z) {
        b(context, "mtjsdkmactrick", z);
    }

    public boolean d(Context context) {
        return a(context, "onlywifi", false);
    }

    public String e(Context context) {
        return a(context, "device_id_1", (String) null);
    }

    public void e(Context context, String str) {
        b(context, "mtjsdkmacss2_1", str);
    }

    public void e(Context context, boolean z) {
        b(context, "bplus", z);
    }

    public String f(Context context) {
        return a(context, "setchannelwithcodevalue", (String) null);
    }

    public void f(Context context, String str) {
        b(context, "mtjsdkmacsstv_1", str);
    }

    public void g(Context context, String str) {
        b(context, "he.ext", str);
    }

    public boolean g(Context context) {
        return a(context, "setchannelwithcode", false);
    }

    public String h(Context context) {
        return a(context, "mtjsdkmacss2_1", (String) null);
    }

    public void h(Context context, String str) {
        b(context, "he.push", str);
    }

    public void i(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        b(context, "custom_userid", str);
    }

    public boolean i(Context context) {
        return a(context, "mtjtv", false);
    }

    public String j(Context context) {
        return a(context, "mtjsdkmacsstv_1", (String) null);
    }

    public void j(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        b(context, "last_custom_userid", str);
    }

    public String k(Context context) {
        return a(context, "he.ext", (String) null);
    }

    public void k(Context context, String str) {
        b(context, "scheme_time", str);
    }

    public String l(Context context) {
        return a(context, "he.push", (String) null);
    }

    public void l(Context context, String str) {
        b(context, "encrypt_device_id", str);
    }

    public void m(Context context, String str) {
        b(context, Config.USER_PROPERTY, str);
    }

    public boolean m(Context context) {
        return a(context, "mtjsdkmactrick", true);
    }

    public long n(Context context) {
        return a(context, "autotrace_track_js_fetch_time", 0L);
    }

    public void n(Context context, String str) {
        b(context, "out_oaid", str);
    }

    public long o(Context context) {
        return a(context, "autotrace_track_js_fetch_interval", 0L);
    }

    public void o(Context context, String str) {
        b(context, "api_oaid", str);
    }

    public long p(Context context) {
        return a(context, "autotrace_config_fetch_time", 0L);
    }

    public String q(Context context) {
        return a(context, "custom_userid", "");
    }

    public String r(Context context) {
        return a(context, "last_custom_userid", "");
    }

    public String s(Context context) {
        return a(context, "scheme_time", "");
    }

    public String t(Context context) {
        return a(context, "encrypt_device_id", "");
    }

    public String u(Context context) {
        return a(context, Config.USER_PROPERTY, "");
    }

    public String v(Context context) {
        return a(context, "out_oaid", "");
    }

    public String w(Context context) {
        return a(context, "api_oaid", "");
    }

    public boolean x(Context context) {
        return a(context, "bplus", true);
    }
}

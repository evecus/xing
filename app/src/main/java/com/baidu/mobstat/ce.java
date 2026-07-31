package com.baidu.mobstat;

import android.app.Activity;
import android.graphics.Rect;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ce {
    private static String a;
    private WeakReference<WebView> b;
    private WeakReference<Activity> c;
    private JSONObject d;
    private boolean e;
    private boolean f;

    private String a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("matchAll", 1);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("meta", jSONObject);
            return jSONObject2.toString();
        } catch (Exception e) {
            return new JSONObject().toString();
        }
    }

    public static String a(Activity activity, WebView webView, Rect rect) {
        a = "";
        b(activity, webView, rect);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 15) {
                return "";
            }
            try {
                Thread.sleep(20L);
            } catch (Exception e) {
            }
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
            i = i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.lang.String r37, android.app.Activity r38, android.webkit.WebView r39) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.ce.a(java.lang.String, android.app.Activity, android.webkit.WebView):void");
    }

    private boolean a(WebView webView, boolean z) {
        WeakReference<WebView> weakReference = this.b;
        return (weakReference == null || weakReference.get() != webView || this.e == z) ? false : true;
    }

    private boolean a(JSONObject jSONObject, String str, String str2, String str3, String str4) {
        boolean z = false;
        if (jSONObject == null || jSONObject.toString().equals(new JSONObject().toString()) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return false;
        }
        try {
            if (((JSONObject) jSONObject.get("meta")).getInt("matchAll") != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get("data");
            boolean z2 = false;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                    String strOptString = jSONObject2.optString("page");
                    String strOptString2 = jSONObject2.optString("layout");
                    String str5 = (String) jSONObject2.opt("url");
                    String str6 = (String) jSONObject2.opt("webLayout");
                    if (str.equals(strOptString) && str2.equals(str5) && str3.equals(strOptString2) && str4.equals(str6)) {
                        z2 = true;
                    }
                } catch (Exception e2) {
                    z = z2;
                    return z;
                }
            }
            return z2;
        } catch (Exception e3) {
        }
    }

    private static void b(Activity activity, final WebView webView, Rect rect) {
        int iA;
        int iA2;
        int iA3;
        int iA4;
        if (rect != null) {
            iA = bb.a(activity, rect.left);
            iA3 = bb.a(activity, rect.top);
            iA4 = bb.a(activity, rect.width());
            iA2 = bb.a(activity, rect.height());
        } else {
            iA = 0;
            iA2 = 0;
            iA3 = 0;
            iA4 = 0;
        }
        final String str = "javascript:window._automtj.getViewportTree('android', '" + ("{\"x\": " + iA + ", \"y\": " + iA3 + ", \"w\": " + iA4 + ", \"h\": " + iA2 + ", \"sw\": " + bb.a(activity, cc.c(activity)) + ", \"sh\": " + bb.a(activity, cc.d(activity)) + "}") + "', 'window.WebViewInterface.setViewportTreeToNative')";
        activity.runOnUiThread(new Runnable() { // from class: com.baidu.mobstat.ce.1
            @Override // java.lang.Runnable
            public void run() {
                webView.loadUrl(str);
            }
        });
    }

    public void a(Activity activity, WebView webView, String str, JSONObject jSONObject, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (z) {
            this.f = z;
            this.d = jSONObject;
        }
        if (z) {
            bw.c().a("injectTrackJs circleConfig: " + jSONObject);
        }
        if (a(webView, z)) {
            if (z) {
                bw.c().a("injectTrackJs, no need to entry");
                return;
            } else {
                ca.c().a("injectTrackJs, no need to entry");
                return;
            }
        }
        if (activity != null) {
            this.c = new WeakReference<>(activity);
        }
        if (webView != null) {
            this.b = new WeakReference<>(webView);
        }
        this.e = z;
        String strA = a();
        if (TextUtils.isEmpty(strA)) {
            strA = new JSONObject().toString();
        }
        if (bw.c().b() && this.f) {
            bw.c().a("injectTrackJs h5Config: " + strA);
        }
        if (ca.c().b()) {
            ca.c().a("injectTrackJs h5Config: " + strA);
        }
        String str2 = "(function(){var h5conf = {\"sdkAPI\": \"window.WebViewInterface.setEventToNative\", \"sdkType\": \"android\", \"events\": " + strA + "};" + str + "})()";
        if (webView != null) {
            webView.loadUrl("javascript:" + str2);
        }
    }

    public void a(WebView webView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        webView.loadUrl("javascript:" + str);
    }

    @JavascriptInterface
    public void setEventToNative(String str) {
        Activity activity;
        WeakReference<WebView> weakReference;
        WebView webView;
        if (bw.c().b() && this.f) {
            bw.c().a("setEventToNative: " + str);
        }
        if (ca.c().b()) {
            ca.c().a("setEventToNative: " + str);
        }
        WeakReference<Activity> weakReference2 = this.c;
        if (weakReference2 == null || (activity = weakReference2.get()) == null || (weakReference = this.b) == null || (webView = weakReference.get()) == null) {
            return;
        }
        a(str, activity, webView);
    }

    @JavascriptInterface
    public void setViewportTreeToNative(String str) {
        if (bw.c().b()) {
            bw.c().a("setViewportTreeToNative " + str);
        }
        a = str;
    }
}

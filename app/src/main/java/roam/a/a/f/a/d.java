package roam.a.a.f.a;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.URLDecoder;
import roam.a.a.f.j.g;

/* JADX INFO: loaded from: classes.dex */
public final class d extends WebViewClient {
    public Activity a;
    public boolean b;
    public Handler c;
    public roam.a.a.f.k.a d;
    public boolean e;
    public Runnable f = new h(this);

    public d(Activity activity) {
        this.a = activity;
        this.c = new Handler(this.a.getMainLooper());
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        if (this.c != null) {
            roam.a.a.f.k.a aVar = this.d;
            if (aVar != null) {
                aVar.a();
            }
            this.d = null;
            this.c.removeCallbacks(this.f);
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.c != null) {
            if (this.d == null) {
                roam.a.a.f.k.a aVar = new roam.a.a.f.k.a(this.a, "正在加载");
                this.d = aVar;
                aVar.d = true;
            }
            roam.a.a.f.k.a aVar2 = this.d;
            Activity activity = aVar2.b;
            if (activity != null) {
                activity.runOnUiThread(new roam.a.a.f.k.b(aVar2));
            }
            this.c.postDelayed(this.f, 30000L);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.e = true;
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        roam.a.a.f.a.l.a.b("net", "SSLError", "证书错误");
        if (!this.b) {
            this.a.runOnUiThread(new e(this, sslErrorHandler));
        } else {
            sslErrorHandler.proceed();
            this.b = false;
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String strSubstring;
        String strD;
        Activity activity = this.a;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (str.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase()) || str.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) {
            try {
                g.a aVarB = roam.a.a.f.j.g.b(activity);
                if (aVarB == null || aVarB.a()) {
                    return true;
                }
                if (str.startsWith("intent://platformapi/startapp")) {
                    str = str.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
                }
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } catch (Throwable th) {
                return true;
            }
        }
        if (TextUtils.equals(str, "sdklite://h5quit") || TextUtils.equals(str, "http://m.alipay.com/?action=h5quit")) {
            roam.a.a.a.b.a.a = roam.a.a.a.b.a.c();
            activity.finish();
            return true;
        }
        if (!str.startsWith("sdklite://h5quit?result=")) {
            webView.loadUrl(str);
            return true;
        }
        try {
            String strSubstring2 = str.substring(str.indexOf("sdklite://h5quit?result=") + 24);
            int i = Integer.parseInt(strSubstring2.substring(strSubstring2.lastIndexOf("&end_code=") + 10));
            if (i == 9000 || i == 8000) {
                if (roam.a.a.f.b.a.b) {
                    StringBuilder sb = new StringBuilder();
                    String strDecode = URLDecoder.decode(str);
                    String strDecode2 = URLDecoder.decode(strDecode);
                    String str2 = strDecode2.substring(strDecode2.indexOf("sdklite://h5quit?result=") + 24, strDecode2.lastIndexOf("&end_code=")).split("&return_url=")[0];
                    int iIndexOf = strDecode.indexOf("&return_url=") + 12;
                    sb.append(str2);
                    sb.append("&return_url=");
                    sb.append(strDecode.substring(iIndexOf, strDecode.indexOf("&", iIndexOf)));
                    sb.append(strDecode.substring(strDecode.indexOf("&", iIndexOf)));
                    strSubstring = sb.toString();
                } else {
                    String strDecode3 = URLDecoder.decode(str);
                    strSubstring = strDecode3.substring(strDecode3.indexOf("sdklite://h5quit?result=") + 24, strDecode3.lastIndexOf("&end_code="));
                }
                k kVarA = k.a(i);
                strD = roam.a.a.a.b.a.d(kVarA.a, kVarA.b, strSubstring);
            } else {
                k kVarA2 = k.a(4000);
                strD = roam.a.a.a.b.a.d(kVarA2.a, kVarA2.b, "");
            }
            roam.a.a.a.b.a.a = strD;
        } catch (Exception e) {
            k kVarA3 = k.a(4001);
            roam.a.a.a.b.a.a = roam.a.a.a.b.a.d(kVarA3.a, kVarA3.b, "");
        }
        activity.runOnUiThread(new roam.a.a.f.j.i(activity));
        return true;
    }
}

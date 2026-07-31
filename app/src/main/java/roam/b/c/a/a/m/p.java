package roam.b.c.a.a.m;

import android.webkit.ValueCallback;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public abstract class p implements z0 {
    public WebView a;

    public p(WebView webView) {
        this.a = webView;
    }

    public void a(String str, ValueCallback<String> valueCallback) {
        this.a.evaluateJavascript(str, new o(this, valueCallback));
    }
}

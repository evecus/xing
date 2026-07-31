package roam.b.c.a.a.m;

import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public class p0 implements u0 {
    public WebView a;
    public q0 b;

    public p0(WebView webView, q0 q0Var) {
        this.a = webView;
        this.b = q0Var;
    }

    public boolean a() {
        q0 q0Var = this.b;
        if (q0Var == null || !q0Var.a()) {
            WebView webView = this.a;
            if (webView == null || !webView.canGoBack()) {
                return false;
            }
            this.a.goBack();
        }
        return true;
    }
}

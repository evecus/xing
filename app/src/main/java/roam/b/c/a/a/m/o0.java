package roam.b.c.a.a.m;

import android.os.Looper;
import android.view.ViewGroup;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public class o0 implements p1 {
    public WebView a;

    public o0(WebView webView) {
        this.a = webView;
    }

    public void a() {
        WebView webView = this.a;
        if (webView != null) {
            webView.resumeTimers();
        }
        WebView webView2 = this.a;
        String str = q.a;
        if (webView2 != null && Looper.myLooper() == Looper.getMainLooper()) {
            webView2.loadUrl("about:blank");
            webView2.stopLoading();
            if (webView2.getHandler() != null) {
                webView2.getHandler().removeCallbacksAndMessages(null);
            }
            webView2.removeAllViews();
            ViewGroup viewGroup = (ViewGroup) webView2.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(webView2);
            }
            webView2.setWebChromeClient(null);
            webView2.setWebViewClient(null);
            webView2.setTag(null);
            webView2.clearHistory();
            webView2.destroy();
        }
    }
}

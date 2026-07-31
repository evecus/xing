package roam.b.c.a.a.m;

import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;
import org.roam.webcore.AgentWeb;

/* JADX INFO: loaded from: classes.dex */
public abstract class d implements t0, q1 {
    public static HashMap<String, String> c;
    public WebSettings a;
    public String b = "default";

    @Override // roam.b.c.a.a.m.q1
    public q1 a(WebView webView, WebChromeClient webChromeClient) {
        webView.setWebChromeClient(webChromeClient);
        return this;
    }

    public q1 b(WebView webView, DownloadListener downloadListener) {
        webView.setDownloadListener(null);
        return this;
    }

    @Override // roam.b.c.a.a.m.q1
    public q1 c(WebView webView, WebViewClient webViewClient) {
        webView.setWebViewClient(webViewClient);
        return this;
    }

    public abstract void d(AgentWeb agentWeb);

    /* JADX WARN: Removed duplicated region for block: B:47:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x019b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(android.webkit.WebView r9) {
        /*
            Method dump skipped, instruction units count: 498
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.b.c.a.a.m.d.e(android.webkit.WebView):void");
    }
}

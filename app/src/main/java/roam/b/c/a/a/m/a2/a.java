package roam.b.c.a.a.m.a2;

import android.util.Log;
import android.webkit.WebView;
import roam.b.c.a.a.m.m1;

/* JADX INFO: loaded from: classes.dex */
public class a extends m1 {
    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        Log.i("CommonWebChromeClient", "onProgressChanged:" + i + "  view:" + webView);
    }
}

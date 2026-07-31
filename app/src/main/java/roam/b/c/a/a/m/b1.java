package roam.b.c.a.a.m;

import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public class b1 extends p {
    public Handler b;

    public b1(WebView webView) {
        super(webView);
        this.b = new Handler(Looper.getMainLooper());
    }

    @Override // roam.b.c.a.a.m.p
    public void a(String str, ValueCallback<String> valueCallback) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.b.post(new a1(this, str, valueCallback));
        } else {
            this.a.evaluateJavascript(str, new o(this, valueCallback));
        }
    }
}

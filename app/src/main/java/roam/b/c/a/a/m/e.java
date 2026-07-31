package roam.b.c.a.a.m;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    public static boolean d;
    public e b;
    public volatile boolean a = false;
    public String c = getClass().getSimpleName();

    static {
        try {
            Class.forName("android.support.design.widget.Snackbar");
            Class.forName("android.support.design.widget.BottomSheetDialog");
            d = true;
        } catch (Throwable th) {
            d = false;
        }
    }

    public abstract void a(t1 t1Var, Activity activity);

    public e b() {
        e wVar = this.b;
        if (wVar == null) {
            wVar = d ? new w() : new k0();
            this.b = wVar;
        }
        return wVar;
    }

    public abstract void c();

    public abstract void d(String str, Handler.Callback callback);

    public abstract void e(WebView webView, String str, String str2);

    public abstract void f(WebView webView, String str, String str2, JsResult jsResult);

    public abstract void g(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult);

    public abstract void h(String str);

    public abstract void i(WebView webView, int i, String str, String str2);

    public abstract void j(WebView webView, String str, Handler.Callback callback);

    public abstract void k(String[] strArr, String str, String str2);

    public abstract void l(WebView webView, String str, String[] strArr, Handler.Callback callback);

    public abstract void m();

    public abstract void n(String str, String str2);

    public void o(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        dialog.dismiss();
    }
}

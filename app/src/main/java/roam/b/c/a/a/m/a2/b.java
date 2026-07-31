package roam.b.c.a.a.m.a2;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import roam.b.c.a.a.m.m;

/* JADX INFO: loaded from: classes.dex */
public class b extends m {
    public b(Activity activity) {
    }

    @Override // roam.b.c.a.a.m.m, roam.b.c.a.a.m.e
    public void l(WebView webView, String str, String[] strArr, Handler.Callback callback) {
        super.l(webView, str, strArr, callback);
    }

    @Override // roam.b.c.a.a.m.m, roam.b.c.a.a.m.e
    public void n(String str, String str2) {
        super.n(str, str2);
        Log.i(this.c, "message:" + str);
    }
}

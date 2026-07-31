package roam.a.a.f.a;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class a extends Activity {
    public WebView a;
    public WebViewClient b;

    @Override // android.app.Activity
    public void finish() {
        synchronized (roam.a.a.f.j.c.class) {
            try {
                try {
                    roam.a.a.f.j.c.class.notify();
                } catch (Throwable th) {
                    throw th;
                }
            } catch (Exception e) {
            }
        }
        super.finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        String strC;
        if (!this.a.canGoBack()) {
            strC = roam.a.a.a.b.a.c();
        } else {
            if (!((d) this.b).e) {
                return;
            }
            k kVarA = k.a(6002);
            strC = roam.a.a.a.b.a.d(kVarA.a, kVarA.b, "");
        }
        roam.a.a.a.b.a.a = strC;
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            requestWindowFeature(1);
        } catch (Throwable th) {
        }
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            String string = extras.getString("url");
            if (Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(string).matches()) {
                try {
                    this.a = roam.a.a.f.j.g.a(this, string, extras.getString("cookie"));
                    d dVar = new d(this);
                    this.b = dVar;
                    this.a.setWebViewClient(dVar);
                } catch (Throwable th2) {
                    roam.a.a.f.a.l.a.c("biz", "GetInstalledAppEx", th2);
                    finish();
                }
            } else {
                finish();
            }
        } catch (Exception e) {
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        WebView webView = this.a;
        if (webView != null) {
            webView.removeAllViews();
            ((ViewGroup) this.a.getParent()).removeAllViews();
            try {
                this.a.destroy();
            } catch (Throwable th) {
            }
            this.a = null;
        }
        WebViewClient webViewClient = this.b;
        if (webViewClient != null) {
            d dVar = (d) webViewClient;
            dVar.c = null;
            dVar.a = null;
        }
    }
}

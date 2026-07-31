package roam.b.c.a.a.m;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.core.view.PointerIconCompat;
import java.lang.ref.WeakReference;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class l0 extends h1 {
    public static final boolean m;
    public static final String n = l0.class.getSimpleName();
    public WeakReference<Activity> c;
    public boolean d;
    public int e;
    public boolean f;
    public WeakReference<e> g;
    public WebView h;
    public Handler.Callback i;
    public Object j;
    public Set<String> k;
    public Set<String> l;

    public class a implements roam.a.a.f.a.b {
        public final WebView a;
        public final l0 b;

        public a(l0 l0Var, WebView webView) {
            this.b = l0Var;
            this.a = webView;
        }
    }

    public static class b {
        public Activity a;
        public boolean b;
        public k1 c;
        public WebView d;
        public boolean e;
        public int f;
    }

    public enum c {
        DERECT(PointerIconCompat.TYPE_CONTEXT_MENU),
        ASK(250),
        DISALLOW(62);

        public int a;

        c(int i) {
            this.a = i;
        }
    }

    static {
        boolean z;
        try {
            Class.forName("a.a.f.a.c");
            z = true;
        } catch (Throwable th) {
            z = false;
        }
        m = z;
        String str = i.a;
    }

    public l0(b bVar) {
        super(null);
        this.c = null;
        this.d = true;
        this.e = 250;
        this.f = true;
        this.g = null;
        this.i = null;
        this.k = new HashSet();
        this.l = new HashSet();
        this.h = bVar.d;
        this.c = new WeakReference<>(bVar.a);
        this.d = bVar.b;
        this.g = new WeakReference<>(q.f(bVar.d));
        this.f = bVar.e;
        int i = bVar.f;
        if (i <= 0) {
            this.e = 250;
        } else {
            this.e = i;
        }
    }

    public final boolean a(String str) {
        int i = this.e;
        if (i == 250) {
            Activity activity = this.c.get();
            if (activity != null) {
                ResolveInfo resolveInfoResolveActivity = null;
                try {
                    Activity activity2 = this.c.get();
                    if (activity2 != null) {
                        resolveInfoResolveActivity = activity2.getPackageManager().resolveActivity(Intent.parseUri(str, 1), 65536);
                    }
                } catch (Throwable th) {
                    String str2 = i.a;
                }
                if (resolveInfoResolveActivity != null) {
                    ActivityInfo activityInfo = resolveInfoResolveActivity.activityInfo;
                    String str3 = activityInfo.packageName;
                    activity.getPackageName();
                    String str4 = i.a;
                    if (!TextUtils.isEmpty(activityInfo.packageName) && activityInfo.packageName.equals(activity.getPackageName())) {
                        return e(str);
                    }
                    if (this.g.get() == null) {
                        return true;
                    }
                    e eVar = this.g.get();
                    WebView webView = this.h;
                    String url = webView.getUrl();
                    Handler.Callback m0Var = this.i;
                    if (m0Var == null) {
                        m0Var = new m0(this, str);
                        this.i = m0Var;
                    }
                    eVar.j(webView, url, m0Var);
                    return true;
                }
            }
        } else if (i == 1001) {
            e(str);
            return true;
        }
        return false;
    }

    public final boolean b(String str) {
        if (!str.startsWith("tel:") && !str.startsWith("sms:") && !str.startsWith("mailto:") && !str.startsWith("geo:0,0?q=")) {
            return false;
        }
        try {
            Activity activity = this.c.get();
            if (activity == null) {
                return false;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            String str2 = i.a;
        }
        return true;
    }

    public final void c(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.startsWith("intent://")) {
                e(str);
            }
        } catch (Throwable th) {
            String str2 = i.a;
        }
    }

    public final boolean d(WebView webView, String str) {
        boolean z;
        try {
            Activity activity = this.c.get();
            if (activity == null) {
                return false;
            }
            if (this.j == null) {
                this.j = new roam.a.a.f.a.c(activity);
            }
            roam.a.a.f.a.c cVar = (roam.a.a.f.a.c) this.j;
            a aVar = new a(this, webView);
            synchronized (cVar) {
                String strE = cVar.e(str);
                if (!TextUtils.isEmpty(strE)) {
                    new Thread(new roam.a.a.f.a.i(cVar, strE, true, aVar)).start();
                }
                z = !TextUtils.isEmpty(strE);
            }
            if (z) {
                String str2 = i.a;
            }
            return z;
        } catch (Throwable th) {
            String str3 = i.a;
            return false;
        }
    }

    public final boolean e(String str) {
        try {
            Activity activity = this.c.get();
            if (activity == null) {
                return true;
            }
            PackageManager packageManager = activity.getPackageManager();
            Intent uri = Intent.parseUri(str, 1);
            if (packageManager.resolveActivity(uri, 65536) != null) {
                activity.startActivity(uri);
                return true;
            }
        } catch (Throwable th) {
            String str2 = i.a;
        }
        return false;
    }

    public final int f(String str) {
        try {
            if (this.c.get() == null) {
                return 0;
            }
            List<ResolveInfo> listQueryIntentActivities = this.c.get().getPackageManager().queryIntentActivities(Intent.parseUri(str, 1), 65536);
            if (listQueryIntentActivities == null) {
                return 0;
            }
            return listQueryIntentActivities.size();
        } catch (URISyntaxException e) {
            String str2 = i.a;
            return 0;
        }
    }

    public final void g(String str) {
        try {
            if (this.c.get() == null) {
                return;
            }
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            this.c.get().startActivity(intent);
        } catch (Exception e) {
            String str2 = i.a;
        }
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        if (this.k.contains(str) || !this.l.contains(str)) {
            webView.setVisibility(0);
        } else if (this.g.get() != null) {
            this.g.get().m();
        }
        if (this.l.contains(str)) {
            this.l.remove(str);
        }
        if (!this.k.isEmpty()) {
            this.k.clear();
        }
        super.onPageFinished(webView, str);
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (!this.l.contains(str)) {
            this.l.add(str);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (webResourceRequest.isForMainFrame() && webResourceRequest.getUrl().toString().equals(webView.getUrl())) {
            int errorCode = webResourceError.getErrorCode();
            String string = webResourceError.getDescription().toString();
            String string2 = webResourceRequest.getUrl().toString();
            this.k.add(string2);
            if (this.g.get() != null) {
                this.g.get().i(webView, errorCode, string, string2);
            }
        }
        StringBuilder sbO = roam.a.b.a.a.a.o("onReceivedError:");
        sbO.append((Object) webResourceError.getDescription());
        sbO.append(" code:");
        sbO.append(webResourceError.getErrorCode());
        sbO.toString();
        String str = i.a;
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f, float f2) {
        String str = i.a;
        if (f2 - f > 7.0f) {
            webView.setInitialScale((int) ((f / f2) * 100.0f));
        }
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return super.shouldInterceptRequest(webView, str);
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        return super.shouldOverrideKeyEvent(webView, keyEvent);
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        String string = webResourceRequest.getUrl().toString();
        if (!string.startsWith("http://") && !string.startsWith("https://")) {
            if (this.d) {
                if (!b(string)) {
                    if (string.startsWith("intent://")) {
                        c(string);
                    } else if (string.startsWith("weixin://wap/pay?")) {
                        String str = i.a;
                        g(string);
                    } else if ((!string.startsWith("alipays://") || !e(string)) && (f(string) <= 0 || !a(string))) {
                        if (this.f) {
                            StringBuilder sbO = roam.a.b.a.a.a.o("intercept UnkownUrl :");
                            sbO.append(webResourceRequest.getUrl());
                            sbO.toString();
                        }
                    }
                    String str2 = i.a;
                }
            }
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }
        if (!this.d || !m || !d(webView, string)) {
            return false;
        }
        return true;
    }

    @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return this.d && m && d(webView, str);
        }
        if (this.d) {
            if (!b(str)) {
                if (str.startsWith("intent://")) {
                    c(str);
                } else if (str.startsWith("weixin://wap/pay?")) {
                    g(str);
                } else if (!str.startsWith("alipays://") || !e(str)) {
                    if ((f(str) <= 0 || !a(str)) && !this.f) {
                        return super.shouldOverrideUrlLoading(webView, str);
                    }
                    String str2 = i.a;
                }
            }
        }
    }
}

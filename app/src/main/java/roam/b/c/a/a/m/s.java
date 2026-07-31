package roam.b.c.a.a.m;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import com.google.android.material.snackbar.Snackbar;
import com.roamexplore.WebViewActionActivity;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.roam.R;
import org.roam.webcore.BaseIndicatorSpec;

/* JADX INFO: loaded from: classes.dex */
public class s extends g1 {
    public WeakReference<Activity> c;
    public String d;
    public boolean e;
    public v0 f;
    public k1 g;
    public WebView h;
    public String i;
    public GeolocationPermissions.Callback j;
    public WeakReference<e> k;
    public x0 l;
    public WebViewActionActivity.b m;

    public class a implements WebViewActionActivity.b {
        public final s a;

        public a(s sVar) {
            this.a = sVar;
        }

        public void a(String[] strArr, int[] iArr, Bundle bundle) {
            if (bundle.getInt("KEY_FROM_INTENTION") == 96) {
                boolean zL = q.l(this.a.c.get(), strArr);
                s sVar = this.a;
                GeolocationPermissions.Callback callback = sVar.j;
                if (callback != null) {
                    String str = sVar.i;
                    if (zL) {
                        callback.invoke(str, true, false);
                    } else {
                        callback.invoke(str, false, true);
                    }
                    s sVar2 = this.a;
                    sVar2.j = null;
                    sVar2.i = null;
                }
                if (zL || this.a.k.get() == null) {
                    return;
                }
                this.a.k.get().k(k.b, "Location", "Location");
            }
        }
    }

    public s(Activity activity, x0 x0Var, WebChromeClient webChromeClient, v0 v0Var, k1 k1Var, WebView webView) {
        super(null);
        this.c = null;
        this.d = s.class.getSimpleName();
        this.e = false;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = new a(this);
        this.l = x0Var;
        this.e = false;
        this.c = new WeakReference<>(activity);
        this.f = v0Var;
        this.g = k1Var;
        this.h = webView;
        this.k = new WeakReference<>(q.f(webView));
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        super.onConsoleMessage(consoleMessage);
        return true;
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(j3 * 2);
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public void onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt();
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public void onGeolocationPermissionsShowPrompt(final String str, final GeolocationPermissions.Callback callback) {
        final Activity activity;
        String host;
        String[] strArr = k.b;
        k1 k1Var = this.g;
        if ((k1Var != null && k1Var.a(this.h.getUrl(), strArr, "location")) || (activity = this.c.get()) == null) {
            callback.invoke(str, false, false);
            return;
        }
        final List<String> listH = q.h(activity, strArr);
        if (listH.isEmpty()) {
            String str2 = i.a;
            callback.invoke(str, true, false);
            return;
        }
        View viewFindViewById = activity.findViewById(R.id.r);
        if (viewFindViewById == null) {
            viewFindViewById = activity.findViewById(android.R.id.content);
        }
        try {
            host = new URL(str).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            host = str;
        }
        Snackbar action = Snackbar.make(viewFindViewById, activity.getString(R.string.r, new Object[]{host}), 0).addCallback(new r(this, callback, str)).setAction(R.string.r, new View.OnClickListener(this, callback, str, listH, activity) { // from class: roam.b.c.a.a.m.a
            public final s a;
            public final GeolocationPermissions.Callback b;
            public final String c;
            public final List d;
            public final Activity e;

            {
                this.a = this;
                this.b = callback;
                this.c = str;
                this.d = listH;
                this.e = activity;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                s sVar = this.a;
                GeolocationPermissions.Callback callback2 = this.b;
                String str3 = this.c;
                List list = this.d;
                Activity activity2 = this.e;
                Objects.requireNonNull(sVar);
                f fVarA = f.a((String[]) list.toArray(new String[0]));
                fVarA.c = 96;
                WebViewActionActivity.c = sVar.m;
                sVar.j = callback2;
                sVar.i = str3;
                WebViewActionActivity.a(activity2, fVarA);
            }
        });
        q.q(activity, action);
        action.show();
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public void onHideCustomView() {
        v0 v0Var = this.f;
        if (v0Var != null) {
            ((l1) v0Var).b();
        }
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        if (this.k.get() != null) {
            this.k.get().e(webView, str, str2);
        }
        jsResult.confirm();
        return true;
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        if (this.k.get() == null) {
            return true;
        }
        this.k.get().f(webView, str, str2, jsResult);
        return true;
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        try {
            if (this.k.get() == null) {
                return true;
            }
            this.k.get().g(this.h, str, str2, str3, jsPromptResult);
            return true;
        } catch (Exception e) {
            String str4 = i.a;
            return true;
        }
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        x0 x0Var = this.l;
        if (x0Var != null) {
            y0 y0Var = (y0) x0Var;
            if (i == 0) {
                BaseIndicatorSpec baseIndicatorSpec = y0Var.a;
                if (baseIndicatorSpec != null) {
                    baseIndicatorSpec.reset();
                    return;
                }
                return;
            }
            if (i > 0 && i <= 10) {
                BaseIndicatorSpec baseIndicatorSpec2 = y0Var.a;
                if (baseIndicatorSpec2 != null) {
                    baseIndicatorSpec2.show();
                    return;
                }
                return;
            }
            if (i > 10 && i < 95) {
                BaseIndicatorSpec baseIndicatorSpec3 = y0Var.a;
                if (baseIndicatorSpec3 != null) {
                    baseIndicatorSpec3.setProgress(i);
                    return;
                }
                return;
            }
            BaseIndicatorSpec baseIndicatorSpec4 = y0Var.a;
            if (baseIndicatorSpec4 != null) {
                baseIndicatorSpec4.setProgress(i);
            }
            BaseIndicatorSpec baseIndicatorSpec5 = y0Var.a;
            if (baseIndicatorSpec5 != null) {
                baseIndicatorSpec5.hide();
            }
        }
    }

    @Override // roam.b.c.a.a.m.n1
    public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(j * 2);
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public void onReceivedIcon(WebView webView, Bitmap bitmap) {
        super.onReceivedIcon(webView, bitmap);
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        if (this.e) {
            super.onReceivedTitle(webView, str);
        }
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        l1 l1Var;
        Activity activity;
        v0 v0Var = this.f;
        if (v0Var == null || (activity = (l1Var = (l1) v0Var).a) == null || activity.isFinishing()) {
            return;
        }
        activity.setRequestedOrientation(0);
        Window window = activity.getWindow();
        if ((window.getAttributes().flags & 128) == 0) {
            Pair<Integer, Integer> pair = new Pair<>(128, 0);
            window.setFlags(128, 128);
            l1Var.c.add(pair);
        }
        if ((window.getAttributes().flags & 16777216) == 0) {
            Pair<Integer, Integer> pair2 = new Pair<>(16777216, 0);
            window.setFlags(16777216, 16777216);
            l1Var.c.add(pair2);
        }
        if (l1Var.d != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        WebView webView = l1Var.b;
        if (webView != null) {
            webView.setVisibility(8);
        }
        if (l1Var.e == null) {
            FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView();
            FrameLayout frameLayout2 = new FrameLayout(activity);
            l1Var.e = frameLayout2;
            frameLayout2.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            frameLayout.addView(l1Var.e);
        }
        l1Var.f = customViewCallback;
        ViewGroup viewGroup = l1Var.e;
        l1Var.d = view;
        viewGroup.addView(view);
        l1Var.e.setVisibility(0);
    }

    @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        String str = i.a;
        StringBuilder sbO = roam.a.b.a.a.a.o("fileChooserParams:");
        sbO.append(fileChooserParams.getAcceptTypes());
        sbO.append("  getTitle:");
        sbO.append((Object) fileChooserParams.getTitle());
        sbO.append(" accept:");
        sbO.append(Arrays.toString(fileChooserParams.getAcceptTypes()));
        sbO.append(" length:");
        sbO.append(fileChooserParams.getAcceptTypes().length);
        sbO.append("  :");
        sbO.append(fileChooserParams.isCaptureEnabled());
        sbO.append("  ");
        sbO.append(fileChooserParams.getFilenameHint());
        sbO.append("  intent:");
        sbO.append(fileChooserParams.createIntent().toString());
        sbO.append("   mode:");
        sbO.append(fileChooserParams.getMode());
        sbO.toString();
        Activity activity = this.c.get();
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        return q.p(activity, webView, valueCallback, fileChooserParams, this.g, null, null, null);
    }
}

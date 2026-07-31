package roam.b.c.a.a.m;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.FrameLayout;
import org.roam.R;
import org.roam.util.UiUtil;
import org.roam.webcore.BaseIndicatorSpec;
import org.roam.webcore.FusionCoreWebView;
import org.roam.webcore.WebIndicator;

/* JADX INFO: loaded from: classes.dex */
public class n0 implements o1 {
    public Activity a;
    public ViewGroup b;
    public boolean c;
    public int d;
    public n e;
    public ViewGroup.LayoutParams f;
    public int g;
    public int h;
    public boolean i;
    public w0 j;
    public BaseIndicatorSpec k;
    public WebView l;
    public FrameLayout m;
    public int n;
    public WebIndicator o;

    public n0(Activity activity, ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams, int i, int i2, int i3, WebView webView, w0 w0Var) {
        this.f = null;
        this.g = -1;
        this.i = false;
        this.l = null;
        this.m = null;
        this.n = 1;
        this.a = activity;
        this.b = viewGroup;
        this.c = true;
        this.d = i;
        this.g = i2;
        this.f = layoutParams;
        this.h = i3;
        this.l = webView;
        this.j = w0Var;
    }

    public n0(Activity activity, ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams, int i, WebView webView, w0 w0Var) {
        this.f = null;
        this.g = -1;
        this.i = false;
        this.l = null;
        this.m = null;
        this.n = 1;
        this.a = activity;
        this.b = viewGroup;
        this.c = false;
        this.d = i;
        this.f = layoutParams;
        this.l = webView;
        this.j = w0Var;
    }

    public n0(Activity activity, ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams, int i, n nVar, WebView webView, w0 w0Var) {
        this.f = null;
        this.g = -1;
        this.i = false;
        this.l = null;
        this.m = null;
        this.n = 1;
        this.a = activity;
        this.b = viewGroup;
        this.c = false;
        this.d = i;
        this.f = layoutParams;
        this.e = nVar;
        this.l = webView;
        this.j = w0Var;
    }

    public final ViewGroup a() {
        FusionCoreWebView fusionCoreWebViewA;
        View view;
        WebIndicator webIndicator;
        FrameLayout.LayoutParams layoutParams;
        Activity activity = this.a;
        t1 t1Var = new t1(activity);
        t1Var.setId(R.id.r);
        t1Var.setBackgroundColor(-1);
        w0 w0Var = this.j;
        int i = 3;
        if (w0Var == null) {
            fusionCoreWebViewA = this.l;
            if (fusionCoreWebViewA == null) {
                fusionCoreWebViewA = new FusionCoreWebView(this.a);
                i = 2;
            }
            this.n = i;
            this.l = fusionCoreWebViewA;
        } else {
            FusionCoreWebView webView = w0Var.getWebView();
            if (webView == null) {
                webView = this.l;
                if (webView == null) {
                    webView = new FusionCoreWebView(this.a);
                    i = 2;
                }
                this.n = i;
                this.j.a().addView((View) webView, -1, -1);
                String str = i.a;
            } else {
                this.n = 3;
            }
            this.l = webView;
            fusionCoreWebViewA = this.j.a();
        }
        t1Var.addView((View) fusionCoreWebViewA, (ViewGroup.LayoutParams) new FrameLayout.LayoutParams(-1, -1));
        WebView webView2 = this.l;
        if (t1Var.e == null) {
            t1Var.e = webView2;
        }
        boolean z = webView2 instanceof FusionCoreWebView;
        String str2 = i.a;
        if (z) {
            this.n = 2;
        }
        ViewStub viewStub = new ViewStub(activity);
        viewStub.setId(R.id.r);
        t1Var.addView(viewStub, new FrameLayout.LayoutParams(-1, -1));
        boolean z2 = this.c;
        if (!z2) {
            if (!z2 && (view = this.e) != null) {
                this.k = view;
                t1Var.addView(view, new FrameLayout.LayoutParams(-1, ((WebIndicator) view).j));
                webIndicator = this.e;
            }
            return t1Var;
        }
        WebIndicator webIndicator2 = new WebIndicator(activity);
        this.o = webIndicator2;
        int i2 = this.h;
        if (i2 > 0) {
            float f = i2;
            String str3 = q.a;
            layoutParams = new FrameLayout.LayoutParams(-2, UiUtil.dp2px(activity, f));
        } else {
            layoutParams = new FrameLayout.LayoutParams(-1, webIndicator2.j);
        }
        int i3 = this.g;
        if (i3 != -1) {
            this.o.setColor(i3);
        }
        layoutParams.gravity = 48;
        View view2 = this.o;
        this.k = view2;
        t1Var.addView(view2, layoutParams);
        webIndicator = this.o;
        webIndicator.setVisibility(8);
        return t1Var;
    }
}

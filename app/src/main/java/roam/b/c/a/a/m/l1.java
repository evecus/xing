package roam.b.c.a.a.m;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.core.util.Pair;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class l1 implements v0, q0 {
    public Activity a;
    public WebView b;
    public Set<Pair<Integer, Integer>> c;
    public View d = null;
    public ViewGroup e = null;
    public WebChromeClient.CustomViewCallback f;

    public l1(Activity activity, WebView webView) {
        this.c = null;
        this.a = activity;
        this.b = webView;
        this.c = new HashSet();
    }

    @Override // roam.b.c.a.a.m.q0
    public boolean a() {
        if (this.d == null) {
            return false;
        }
        b();
        return true;
    }

    public void b() {
        View view;
        if (this.d == null) {
            return;
        }
        Activity activity = this.a;
        if (activity != null && activity.getRequestedOrientation() != 1) {
            this.a.setRequestedOrientation(1);
        }
        if (!this.c.isEmpty()) {
            for (Pair<Integer, Integer> pair : this.c) {
                this.a.getWindow().setFlags(pair.second.intValue(), pair.first.intValue());
            }
            this.c.clear();
        }
        this.d.setVisibility(8);
        ViewGroup viewGroup = this.e;
        if (viewGroup != null && (view = this.d) != null) {
            viewGroup.removeView(view);
        }
        ViewGroup viewGroup2 = this.e;
        if (viewGroup2 != null) {
            viewGroup2.setVisibility(8);
        }
        WebChromeClient.CustomViewCallback customViewCallback = this.f;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
        }
        this.d = null;
        WebView webView = this.b;
        if (webView != null) {
            webView.setVisibility(0);
        }
    }
}

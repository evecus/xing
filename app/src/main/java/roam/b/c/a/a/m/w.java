package roam.b.c.a.a.m;

import android.R;
import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/* JADX INFO: loaded from: classes.dex */
public class w extends k0 {
    public BottomSheetDialog n;
    public Activity o = null;
    public t1 p;
    public LayoutInflater q;

    public static class a extends RecyclerView.ViewHolder {
        public TextView a;

        public a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.text1);
        }
    }

    @Override // roam.b.c.a.a.m.k0, roam.b.c.a.a.m.e
    public void a(t1 t1Var, Activity activity) {
        this.j = activity;
        this.k = t1Var;
        this.m = activity.getResources();
        this.o = activity;
        this.p = t1Var;
        this.q = LayoutInflater.from(activity);
    }

    @Override // roam.b.c.a.a.m.k0, roam.b.c.a.a.m.e
    public void d(String str, Handler.Callback callback) {
        super.d(str, callback);
    }

    @Override // roam.b.c.a.a.m.k0, roam.b.c.a.a.m.e
    public void e(WebView webView, String str, String str2) {
        q(webView, str2);
    }

    @Override // roam.b.c.a.a.m.k0, roam.b.c.a.a.m.e
    public void f(WebView webView, String str, String str2, JsResult jsResult) {
        super.f(webView, str, str2, jsResult);
    }

    @Override // roam.b.c.a.a.m.k0, roam.b.c.a.a.m.e
    public void g(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        super.g(webView, str, str2, str3, jsPromptResult);
    }

    @Override // roam.b.c.a.a.m.k0, roam.b.c.a.a.m.e
    public void l(WebView webView, String str, String[] strArr, Handler.Callback callback) {
        Activity activity = this.o;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        String str2 = strArr[0];
        String str3 = i.a;
        if (this.n == null) {
            this.n = new BottomSheetDialog(activity);
            RecyclerView recyclerView = new RecyclerView(activity);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            recyclerView.setId(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            this.n.setContentView(recyclerView);
        }
        ((RecyclerView) this.n.getDelegate().findViewById(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)).setAdapter(new v(this, strArr, callback));
        this.n.setOnCancelListener(new t(this, callback));
        this.n.show();
    }

    @Override // roam.b.c.a.a.m.k0, roam.b.c.a.a.m.e
    public void n(String str, String str2) {
        Activity activity = this.o;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        if (TextUtils.isEmpty(str2) || !str2.contains("performDownload")) {
            q(this.p.getWebView(), str);
        }
    }

    public final void q(WebView webView, String str) {
        Activity activity = this.o;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        try {
            q.o(webView, str, -1, -1, activity.getResources().getColor(org.roam.R.color.r), null, -1, null);
        } catch (Throwable th) {
            String str2 = i.a;
        }
    }
}

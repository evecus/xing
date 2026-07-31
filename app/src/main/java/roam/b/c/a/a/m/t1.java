package roam.b.c.a.a.m;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import org.roam.R;

/* JADX INFO: loaded from: classes.dex */
public class t1 extends FrameLayout {
    public static final String g = t1.class.getSimpleName();
    public e a;
    public int b;
    public int c;
    public View d;
    public WebView e;
    public FrameLayout f;

    public t1(Context context) {
        super(context, null, -1);
        this.a = null;
        this.c = -1;
        this.f = null;
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("WebParentLayout context must be activity or activity sub class .");
        }
        this.b = R.layout.r;
        String str = i.a;
    }

    public WebView getWebView() {
        return this.e;
    }

    public void setErrorView(View view) {
        this.d = view;
    }
}

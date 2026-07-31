package roam.b.c.a.a.m;

import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class s1 implements View.OnClickListener {
    public final FrameLayout a;
    public final t1 b;

    public s1(t1 t1Var, FrameLayout frameLayout) {
        this.b = t1Var;
        this.a = frameLayout;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.b.getWebView() != null) {
            this.a.setClickable(false);
            this.b.getWebView().reload();
        }
    }
}

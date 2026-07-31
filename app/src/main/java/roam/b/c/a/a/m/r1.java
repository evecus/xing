package roam.b.c.a.a.m;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class r1 implements View.OnClickListener {
    public final View a;
    public final t1 b;

    public r1(t1 t1Var, View view) {
        this.b = t1Var;
        this.a = view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.b.getWebView() != null) {
            this.a.setClickable(false);
            this.b.getWebView().reload();
        }
    }
}

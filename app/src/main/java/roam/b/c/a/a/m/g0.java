package roam.b.c.a.a.m;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* JADX INFO: loaded from: classes.dex */
public class g0 implements DialogInterface.OnClickListener {
    public final k0 a;

    public g0(k0 k0Var) {
        this.a = k0Var;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        k0 k0Var = this.a;
        k0Var.o(k0Var.f);
        JsResult jsResult = this.a.h;
        if (jsResult != null) {
            jsResult.confirm();
        }
    }
}

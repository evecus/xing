package roam.b.c.a.a.m;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public class f0 implements DialogInterface.OnCancelListener {
    public final k0 a;

    public f0(k0 k0Var) {
        this.a = k0Var;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        k0 k0Var = this.a;
        k0.p(k0Var, k0Var.h);
    }
}

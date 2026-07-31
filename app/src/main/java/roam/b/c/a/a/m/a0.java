package roam.b.c.a.a.m;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* JADX INFO: loaded from: classes.dex */
public class a0 implements DialogInterface.OnClickListener {
    public final k0 a;

    public a0(k0 k0Var) {
        this.a = k0Var;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        k0 k0Var = this.a;
        k0Var.o(k0Var.i);
        JsPromptResult jsPromptResult = this.a.g;
        if (jsPromptResult != null) {
            jsPromptResult.cancel();
        }
    }
}

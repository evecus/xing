package roam.b.c.a.a.m;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public class j0 implements DialogInterface.OnClickListener {
    public final EditText a;
    public final k0 b;

    public j0(k0 k0Var, EditText editText) {
        this.b = k0Var;
        this.a = editText;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        k0 k0Var = this.b;
        k0Var.o(k0Var.i);
        JsPromptResult jsPromptResult = this.b.g;
        if (jsPromptResult != null) {
            jsPromptResult.confirm(this.a.getText().toString());
        }
    }
}

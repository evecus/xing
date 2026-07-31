package roam.b.c.a.a.m;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class c0 implements DialogInterface.OnClickListener {
    public final Handler.Callback a;

    public c0(k0 k0Var, Handler.Callback callback) {
        this.a = callback;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
        Handler.Callback callback = this.a;
        if (callback != null) {
            callback.handleMessage(Message.obtain());
        }
    }
}

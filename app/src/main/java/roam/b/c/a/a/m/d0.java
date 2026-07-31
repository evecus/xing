package roam.b.c.a.a.m;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class d0 implements DialogInterface.OnCancelListener {
    public final Handler.Callback a;

    public d0(k0 k0Var, Handler.Callback callback) {
        this.a = callback;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        Handler.Callback callback = this.a;
        if (callback != null) {
            callback.handleMessage(Message.obtain((Handler) null, -1));
        }
    }
}

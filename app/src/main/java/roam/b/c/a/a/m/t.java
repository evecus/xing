package roam.b.c.a.a.m;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class t implements DialogInterface.OnCancelListener {
    public final Handler.Callback a;

    public t(w wVar, Handler.Callback callback) {
        this.a = callback;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        Handler.Callback callback = this.a;
        if (callback != null) {
            callback.handleMessage(Message.obtain((Handler) null, -1));
        }
    }
}

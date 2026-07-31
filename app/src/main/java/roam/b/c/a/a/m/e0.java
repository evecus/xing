package roam.b.c.a.a.m;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class e0 implements DialogInterface.OnClickListener {
    public final Handler.Callback a;
    public final k0 b;

    public e0(k0 k0Var, Handler.Callback callback) {
        this.b = k0Var;
        this.a = callback;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        String str = this.b.c;
        String str2 = i.a;
        if (this.a != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            this.a.handleMessage(messageObtain);
        }
    }
}

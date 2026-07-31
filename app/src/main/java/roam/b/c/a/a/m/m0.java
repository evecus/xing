package roam.b.c.a.a.m;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class m0 implements Handler.Callback {
    public final String a;
    public final l0 b;

    public m0(l0 l0Var, String str) {
        this.b = l0Var;
        this.a = str;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            this.b.e(this.a);
        }
        return true;
    }
}

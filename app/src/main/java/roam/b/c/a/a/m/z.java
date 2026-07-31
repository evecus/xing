package roam.b.c.a.a.m;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class z implements Handler.Callback {
    public final String a;
    public final x b;

    public z(x xVar, String str) {
        this.b = xVar;
        this.a = str;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        x xVar = this.b;
        String str = this.a;
        xVar.b.get(str).a.a = true;
        xVar.c(str);
        return true;
    }
}

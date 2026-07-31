package roam.b.c.a.a.m;

import android.webkit.ValueCallback;

/* JADX INFO: loaded from: classes.dex */
public class a1 implements Runnable {
    public final String a;
    public final ValueCallback b;
    public final b1 c;

    public a1(b1 b1Var, String str, ValueCallback valueCallback) {
        this.c = b1Var;
        this.a = str;
        this.b = valueCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.a(this.a, this.b);
    }
}

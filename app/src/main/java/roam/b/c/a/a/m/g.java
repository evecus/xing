package roam.b.c.a.a.m;

import android.webkit.CookieManager;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        CookieManager.getInstance().flush();
    }
}

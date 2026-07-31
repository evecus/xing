package roam.a.a.f.j;

import android.app.Activity;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Runnable {
    public final Activity a;

    public i(Activity activity) {
        this.a = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.finish();
    }
}

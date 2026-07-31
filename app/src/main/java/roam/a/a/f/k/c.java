package roam.a.a.f.k;

import roam.a.a.f.k.a;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {
    public final a a;

    public c(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a.AlertDialogC0015a alertDialogC0015a = this.a.a;
        if (alertDialogC0015a == null || !alertDialogC0015a.isShowing()) {
            return;
        }
        try {
            this.a.e.removeMessages(1);
            this.a.a.dismiss();
        } catch (Exception e) {
        }
    }
}

package roam.a.a.f.k;

import roam.a.a.f.k.a;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final a a;

    public b(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a aVar = this.a;
        if (aVar.a == null) {
            a aVar2 = this.a;
            aVar.a = new a.AlertDialogC0015a(aVar2, aVar2.b);
            a aVar3 = this.a;
            aVar3.a.setCancelable(aVar3.d);
        }
        try {
            if (this.a.a.isShowing()) {
                return;
            }
            this.a.a.show();
            this.a.e.sendEmptyMessageDelayed(1, 15000L);
        } catch (Exception e) {
        }
    }
}

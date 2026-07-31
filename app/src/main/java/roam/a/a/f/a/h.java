package roam.a.a.f.a;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Runnable {
    public final d a;

    public h(d dVar) {
        this.a = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        d dVar = this.a;
        roam.a.a.f.k.a aVar = dVar.d;
        if (aVar != null) {
            aVar.a();
        }
        dVar.d = null;
    }
}

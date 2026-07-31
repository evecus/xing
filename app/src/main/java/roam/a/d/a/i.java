package roam.a.d.a;

/* JADX INFO: loaded from: classes.dex */
public class i implements Runnable {
    public final h a;

    public i(k kVar, h hVar) {
        this.a = hVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        h hVar = this.a;
        hVar.x.c(hVar.g, hVar.j, hVar.h, hVar.i, hVar.u, hVar);
    }
}

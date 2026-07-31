package roam.a.d.a;

/* JADX INFO: loaded from: classes.dex */
public class j implements Runnable {
    public final h a;
    public final k b;

    public j(k kVar, h hVar) {
        this.b = kVar;
        this.a = hVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.p(this.a);
    }
}

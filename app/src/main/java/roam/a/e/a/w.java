package roam.a.e.a;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
public class w<T> extends x<T> {
    public final x a;

    public w(x xVar) {
        this.a = xVar;
    }

    @Override // roam.a.e.a.x
    public T a(roam.a.e.a.c0.a aVar) {
        if (aVar.v() != roam.a.e.a.c0.b.NULL) {
            return (T) this.a.a(aVar);
        }
        aVar.r();
        return null;
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, T t) {
        if (t == null) {
            cVar.i();
        } else {
            this.a.b(cVar, t);
        }
    }
}

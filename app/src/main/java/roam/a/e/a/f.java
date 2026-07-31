package roam.a.e.a;

/* JADX INFO: loaded from: classes.dex */
public class f extends x<Number> {
    public f(i iVar) {
    }

    @Override // roam.a.e.a.x
    public Number a(roam.a.e.a.c0.a aVar) {
        if (aVar.v() != roam.a.e.a.c0.b.NULL) {
            return Float.valueOf((float) aVar.m());
        }
        aVar.r();
        return null;
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, Number number) {
        Number number2 = number;
        if (number2 == null) {
            cVar.i();
        } else {
            i.a(number2.floatValue());
            cVar.p(number2);
        }
    }
}

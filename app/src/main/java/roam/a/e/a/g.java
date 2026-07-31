package roam.a.e.a;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class g extends x<AtomicLong> {
    public final x a;

    public g(x xVar) {
        this.a = xVar;
    }

    @Override // roam.a.e.a.x
    public AtomicLong a(roam.a.e.a.c0.a aVar) {
        return new AtomicLong(((Number) this.a.a(aVar)).longValue());
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, AtomicLong atomicLong) {
        this.a.b(cVar, Long.valueOf(atomicLong.get()));
    }
}

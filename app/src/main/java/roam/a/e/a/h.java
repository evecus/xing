package roam.a.e.a;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: loaded from: classes.dex */
public class h extends x<AtomicLongArray> {
    public final x a;

    public h(x xVar) {
        this.a = xVar;
    }

    @Override // roam.a.e.a.x
    public AtomicLongArray a(roam.a.e.a.c0.a aVar) {
        ArrayList arrayList = new ArrayList();
        aVar.a();
        while (aVar.i()) {
            arrayList.add(Long.valueOf(((Number) this.a.a(aVar)).longValue()));
        }
        aVar.e();
        int size = arrayList.size();
        AtomicLongArray atomicLongArray = new AtomicLongArray(size);
        for (int i = 0; i < size; i++) {
            atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
        }
        return atomicLongArray;
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, AtomicLongArray atomicLongArray) {
        AtomicLongArray atomicLongArray2 = atomicLongArray;
        cVar.b();
        int length = atomicLongArray2.length();
        for (int i = 0; i < length; i++) {
            this.a.b(cVar, Long.valueOf(atomicLongArray2.get(i)));
        }
        cVar.e();
    }
}

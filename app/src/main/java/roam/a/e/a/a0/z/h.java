package roam.a.e.a.a0.z;

import java.util.ArrayList;
import java.util.Objects;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class h extends x<Object> {
    public static final y b = new a();
    public final roam.a.e.a.i a;

    public class a implements y {
        @Override // roam.a.e.a.y
        public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
            if (aVar.a == Object.class) {
                return new h(iVar);
            }
            return null;
        }
    }

    public h(roam.a.e.a.i iVar) {
        this.a = iVar;
    }

    @Override // roam.a.e.a.x
    public Object a(roam.a.e.a.c0.a aVar) {
        Object obj;
        int iOrdinal = aVar.v().ordinal();
        if (iOrdinal == 0) {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.i()) {
                arrayList.add(a(aVar));
            }
            aVar.e();
            obj = arrayList;
        } else {
            if (iOrdinal != 2) {
                if (iOrdinal == 5) {
                    return aVar.t();
                }
                if (iOrdinal == 6) {
                    return Double.valueOf(aVar.m());
                }
                if (iOrdinal == 7) {
                    return Boolean.valueOf(aVar.l());
                }
                if (iOrdinal != 8) {
                    throw new IllegalStateException();
                }
                aVar.r();
                return null;
            }
            roam.a.e.a.a0.s sVar = new roam.a.e.a.a0.s();
            aVar.b();
            while (aVar.i()) {
                sVar.put(aVar.p(), a(aVar));
            }
            aVar.f();
            obj = sVar;
        }
        return obj;
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, Object obj) {
        if (obj == null) {
            cVar.i();
            return;
        }
        roam.a.e.a.i iVar = this.a;
        Class<?> cls = obj.getClass();
        Objects.requireNonNull(iVar);
        x xVarC = iVar.c(new roam.a.e.a.b0.a(cls));
        if (!(xVarC instanceof h)) {
            xVarC.b(cVar, obj);
        } else {
            cVar.c();
            cVar.f();
        }
    }
}

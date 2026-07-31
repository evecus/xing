package roam.a.e.a.a0.z;

import com.android.cglib.dx.io.Opcodes;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import roam.a.e.a.a0.t;
import roam.a.e.a.v;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class j implements y {
    public final roam.a.e.a.a0.g a;
    public final roam.a.e.a.d b;
    public final roam.a.e.a.a0.o c;
    public final d d;
    public final roam.a.e.a.a0.a0.b e = roam.a.e.a.a0.a0.b.a;

    public static final class a<T> extends x<T> {
        public final t<T> a;
        public final Map<String, b> b;

        public a(t<T> tVar, Map<String, b> map) {
            this.a = tVar;
            this.b = map;
        }

        @Override // roam.a.e.a.x
        public T a(roam.a.e.a.c0.a aVar) throws IOException {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            T tA = this.a.a();
            try {
                aVar.b();
                while (aVar.i()) {
                    b bVar = this.b.get(aVar.p());
                    if (bVar == null || !bVar.c) {
                        aVar.A();
                    } else {
                        bVar.a(aVar, tA);
                    }
                }
                aVar.f();
                return tA;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new v(e2);
            }
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, T t) {
            if (t == null) {
                cVar.i();
                return;
            }
            cVar.c();
            try {
                for (b bVar : this.b.values()) {
                    if (bVar.c(t)) {
                        cVar.g(bVar.a);
                        bVar.b(cVar, t);
                    }
                }
                cVar.f();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static abstract class b {
        public final String a;
        public final boolean b;
        public final boolean c;

        public b(String str, boolean z, boolean z2) {
            this.a = str;
            this.b = z;
            this.c = z2;
        }

        public abstract void a(roam.a.e.a.c0.a aVar, Object obj);

        public abstract void b(roam.a.e.a.c0.c cVar, Object obj);

        public abstract boolean c(Object obj);
    }

    public j(roam.a.e.a.a0.g gVar, roam.a.e.a.d dVar, roam.a.e.a.a0.o oVar, d dVar2) {
        this.a = gVar;
        this.b = dVar;
        this.c = oVar;
        this.d = dVar2;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x018e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0179 A[SYNTHETIC] */
    @Override // roam.a.e.a.y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> roam.a.e.a.x<T> a(roam.a.e.a.i r34, roam.a.e.a.b0.a<T> r35) {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.a0.z.j.a(roam.a.e.a.i, roam.a.e.a.b0.a):roam.a.e.a.x");
    }

    public boolean b(Field field, boolean z) {
        roam.a.e.a.a0.o oVar = this.c;
        Class<?> type = field.getType();
        if (!oVar.c(type) && !oVar.b(type, z) && (field.getModifiers() & Opcodes.FLOAT_TO_LONG) == 0 && !field.isSynthetic() && !oVar.c(field.getType())) {
            List<roam.a.e.a.a> list = z ? oVar.a : oVar.b;
            if (!list.isEmpty()) {
                roam.a.e.a.b bVar = new roam.a.e.a.b(field);
                Iterator<roam.a.e.a.a> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().b(bVar)) {
                    }
                }
            }
            return true;
        }
        return false;
    }
}

package roam.a.e.a.a0.z;

import roam.a.e.a.v;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public class s implements y {
    public final Class a;
    public final x b;

    /* JADX INFO: Add missing generic type declarations: [T1] */
    public class a<T1> extends x<T1> {
        public final Class a;
        public final s b;

        public a(s sVar, Class cls) {
            this.b = sVar;
            this.a = cls;
        }

        @Override // roam.a.e.a.x
        public T1 a(roam.a.e.a.c0.a aVar) {
            T1 t1 = (T1) this.b.b.a(aVar);
            if (t1 == null || this.a.isInstance(t1)) {
                return t1;
            }
            StringBuilder sbO = roam.a.b.a.a.a.o("Expected a ");
            sbO.append(this.a.getName());
            sbO.append(" but was ");
            sbO.append(t1.getClass().getName());
            throw new v(sbO.toString());
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, T1 t1) {
            this.b.b.b(cVar, t1);
        }
    }

    public s(Class cls, x xVar) {
        this.a = cls;
        this.b = xVar;
    }

    @Override // roam.a.e.a.y
    public <T2> x<T2> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T2> aVar) {
        Class<? super T2> cls = aVar.a;
        if (this.a.isAssignableFrom(cls)) {
            return new a(this, cls);
        }
        return null;
    }

    public String toString() {
        StringBuilder sbO = roam.a.b.a.a.a.o("Factory[typeHierarchy=");
        sbO.append(this.a.getName());
        sbO.append(",adapter=");
        sbO.append(this.b);
        sbO.append("]");
        return sbO.toString();
    }
}

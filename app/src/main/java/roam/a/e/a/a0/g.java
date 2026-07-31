package roam.a.e.a.a0;

import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public final Map<Type, roam.a.e.a.j<?>> a;
    public final roam.a.e.a.a0.a0.b b = roam.a.e.a.a0.a0.b.a;

    /* JADX INFO: Add missing generic type declarations: [T] */
    public class a<T> implements t<T> {
        public final roam.a.e.a.j a;
        public final Type b;

        public a(g gVar, roam.a.e.a.j jVar, Type type) {
            this.a = jVar;
            this.b = type;
        }

        @Override // roam.a.e.a.a0.t
        public T a() {
            return (T) this.a.a(this.b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    public class b<T> implements t<T> {
        public final roam.a.e.a.j a;
        public final Type b;

        public b(g gVar, roam.a.e.a.j jVar, Type type) {
            this.a = jVar;
            this.b = type;
        }

        @Override // roam.a.e.a.a0.t
        public T a() {
            return (T) this.a.a(this.b);
        }
    }

    public g(Map<Type, roam.a.e.a.j<?>> map) {
        this.a = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> roam.a.e.a.a0.t<T> a(roam.a.e.a.b0.a<T> r6) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.a0.g.a(roam.a.e.a.b0.a):roam.a.e.a.a0.t");
    }

    public String toString() {
        return this.a.toString();
    }
}

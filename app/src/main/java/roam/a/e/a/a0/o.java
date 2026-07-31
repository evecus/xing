package roam.a.e.a.a0;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class o implements roam.a.e.a.y, Cloneable {
    public static final o c = new o();
    public List<roam.a.e.a.a> a = Collections.emptyList();
    public List<roam.a.e.a.a> b = Collections.emptyList();

    /* JADX INFO: Add missing generic type declarations: [T] */
    public class a<T> extends roam.a.e.a.x<T> {
        public roam.a.e.a.x<T> a;
        public final boolean b;
        public final boolean c;
        public final roam.a.e.a.i d;
        public final roam.a.e.a.b0.a e;
        public final o f;

        public a(o oVar, boolean z, boolean z2, roam.a.e.a.i iVar, roam.a.e.a.b0.a aVar) {
            this.f = oVar;
            this.b = z;
            this.c = z2;
            this.d = iVar;
            this.e = aVar;
        }

        @Override // roam.a.e.a.x
        public T a(roam.a.e.a.c0.a aVar) throws IOException {
            if (this.b) {
                aVar.A();
                return null;
            }
            roam.a.e.a.x<T> xVarD = this.a;
            if (xVarD == null) {
                xVarD = this.d.d(this.f, this.e);
                this.a = xVarD;
            }
            return xVarD.a(aVar);
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, T t) {
            if (this.c) {
                cVar.i();
                return;
            }
            roam.a.e.a.x<T> xVarD = this.a;
            if (xVarD == null) {
                xVarD = this.d.d(this.f, this.e);
                this.a = xVarD;
            }
            xVarD.b(cVar, t);
        }
    }

    @Override // roam.a.e.a.y
    public <T> roam.a.e.a.x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
        Class<? super T> cls = aVar.a;
        boolean zC = c(cls);
        boolean z = zC || b(cls, true);
        boolean z2 = zC || b(cls, false);
        if (z || z2) {
            return new a(this, z2, z, iVar, aVar);
        }
        return null;
    }

    public final boolean b(Class<?> cls, boolean z) {
        Iterator<roam.a.e.a.a> it = (z ? this.a : this.b).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    public final boolean c(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    public Object clone() {
        try {
            return (o) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

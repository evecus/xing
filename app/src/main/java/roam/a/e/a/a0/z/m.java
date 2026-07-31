package roam.a.e.a.a0.z;

import java.io.EOFException;
import java.io.IOException;
import java.util.Objects;
import roam.a.e.a.t;
import roam.a.e.a.u;
import roam.a.e.a.v;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class m<T> extends x<T> {
    public final u<T> a;
    public final roam.a.e.a.m<T> b;
    public final roam.a.e.a.i c;
    public final roam.a.e.a.b0.a<T> d;
    public final m<T>.b e = new b(this, null);
    public x<T> f;

    public final class b implements t, roam.a.e.a.l {
        public b(m mVar, a aVar) {
        }
    }

    public m(u<T> uVar, roam.a.e.a.m<T> mVar, roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar, y yVar) {
        this.a = uVar;
        this.b = mVar;
        this.c = iVar;
        this.d = aVar;
    }

    @Override // roam.a.e.a.x
    public T a(roam.a.e.a.c0.a aVar) {
        boolean z;
        roam.a.e.a.n nVarA;
        if (this.b == null) {
            x<T> xVarD = this.f;
            if (xVarD == null) {
                xVarD = this.c.d(null, this.d);
                this.f = xVarD;
            }
            return xVarD.a(aVar);
        }
        try {
            try {
                aVar.v();
                try {
                    nVarA = o.X.a(aVar);
                } catch (EOFException e) {
                    e = e;
                    z = false;
                    if (!z) {
                        throw new v(e);
                    }
                    nVarA = roam.a.e.a.p.a;
                }
            } catch (IOException e2) {
                throw new roam.a.e.a.o(e2);
            } catch (NumberFormatException e3) {
                throw new v(e3);
            } catch (roam.a.e.a.c0.d e4) {
                throw new v(e4);
            }
        } catch (EOFException e5) {
            e = e5;
            z = true;
        }
        Objects.requireNonNull(nVarA);
        if (nVarA instanceof roam.a.e.a.p) {
            return null;
        }
        return this.b.a(nVarA, this.d.b, this.e);
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, T t) {
        x<T> xVarD;
        u<T> uVar = this.a;
        if (uVar == null) {
            xVarD = this.f;
            if (xVarD == null) {
                xVarD = this.c.d(null, this.d);
                this.f = xVarD;
            }
        } else if (t == null) {
            cVar.i();
            return;
        } else {
            t = (T) uVar.a(t, this.d.b, this.e);
            xVarD = (x<T>) o.X;
        }
        xVarD.b(cVar, t);
    }
}

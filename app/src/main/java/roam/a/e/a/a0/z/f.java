package roam.a.e.a.a0.z;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class f extends roam.a.e.a.c0.c {
    public static final Writer o = new a();
    public static final roam.a.e.a.s p = new roam.a.e.a.s("closed");
    public final List<roam.a.e.a.n> l;
    public String m;
    public roam.a.e.a.n n;

    public class a extends Writer {
        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    public f() {
        super(o);
        this.l = new ArrayList();
        this.n = roam.a.e.a.p.a;
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c b() {
        roam.a.e.a.k kVar = new roam.a.e.a.k();
        u(kVar);
        this.l.add(kVar);
        return this;
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c c() {
        roam.a.e.a.q qVar = new roam.a.e.a.q();
        u(qVar);
        this.l.add(qVar);
        return this;
    }

    @Override // roam.a.e.a.c0.c, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.l.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.l.add(p);
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c e() {
        if (this.l.isEmpty() || this.m != null) {
            throw new IllegalStateException();
        }
        if (!(t() instanceof roam.a.e.a.k)) {
            throw new IllegalStateException();
        }
        this.l.remove(r0.size() - 1);
        return this;
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c f() {
        if (this.l.isEmpty() || this.m != null) {
            throw new IllegalStateException();
        }
        if (!(t() instanceof roam.a.e.a.q)) {
            throw new IllegalStateException();
        }
        this.l.remove(r0.size() - 1);
        return this;
    }

    @Override // roam.a.e.a.c0.c, java.io.Flushable
    public void flush() {
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c g(String str) {
        if (this.l.isEmpty() || this.m != null) {
            throw new IllegalStateException();
        }
        if (!(t() instanceof roam.a.e.a.q)) {
            throw new IllegalStateException();
        }
        this.m = str;
        return this;
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c i() {
        u(roam.a.e.a.p.a);
        return this;
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c n(long j) {
        u(new roam.a.e.a.s(Long.valueOf(j)));
        return this;
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c o(Boolean bool) {
        if (bool == null) {
            u(roam.a.e.a.p.a);
        } else {
            u(new roam.a.e.a.s(bool));
        }
        return this;
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c p(Number number) {
        if (number == null) {
            u(roam.a.e.a.p.a);
        } else {
            if (!this.f) {
                double dDoubleValue = number.doubleValue();
                if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                    throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
                }
            }
            u(new roam.a.e.a.s(number));
        }
        return this;
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c q(String str) {
        if (str == null) {
            u(roam.a.e.a.p.a);
        } else {
            u(new roam.a.e.a.s(str));
        }
        return this;
    }

    @Override // roam.a.e.a.c0.c
    public roam.a.e.a.c0.c r(boolean z) {
        u(new roam.a.e.a.s(Boolean.valueOf(z)));
        return this;
    }

    public final roam.a.e.a.n t() {
        return this.l.get(r0.size() - 1);
    }

    public final void u(roam.a.e.a.n nVar) {
        if (this.m != null) {
            if (!(nVar instanceof roam.a.e.a.p) || this.i) {
                roam.a.e.a.q qVar = (roam.a.e.a.q) t();
                qVar.a.put(this.m, nVar);
            }
            this.m = null;
            return;
        }
        if (this.l.isEmpty()) {
            this.n = nVar;
            return;
        }
        roam.a.e.a.n nVarT = t();
        if (!(nVarT instanceof roam.a.e.a.k)) {
            throw new IllegalStateException();
        }
        ((roam.a.e.a.k) nVarT).a.add(nVar);
    }
}

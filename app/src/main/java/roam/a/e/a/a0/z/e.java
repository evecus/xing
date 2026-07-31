package roam.a.e.a.a0.z;

import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import roam.a.e.a.a0.s;

/* JADX INFO: loaded from: classes.dex */
public final class e extends roam.a.e.a.c0.a {
    public static final Object u;
    public Object[] q;
    public int r;
    public String[] s;
    public int[] t;

    public class a extends Reader {
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    static {
        new a();
        u = new Object();
    }

    private String k() {
        StringBuilder sbO = roam.a.b.a.a.a.o(" at path ");
        sbO.append(h());
        return sbO.toString();
    }

    @Override // roam.a.e.a.c0.a
    public void A() {
        if (v() == roam.a.e.a.c0.b.NAME) {
            p();
            this.s[this.r - 2] = "null";
        } else {
            E();
            int i = this.r;
            if (i > 0) {
                this.s[i - 1] = "null";
            }
        }
        int i2 = this.r;
        if (i2 > 0) {
            int[] iArr = this.t;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    public final void C(roam.a.e.a.c0.b bVar) {
        if (v() == bVar) {
            return;
        }
        throw new IllegalStateException("Expected " + bVar + " but was " + v() + k());
    }

    public final Object D() {
        return this.q[this.r - 1];
    }

    public final Object E() {
        Object[] objArr = this.q;
        int i = this.r - 1;
        this.r = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public final void F(Object obj) {
        int i = this.r;
        Object[] objArr = this.q;
        if (i == objArr.length) {
            int i2 = i * 2;
            this.q = Arrays.copyOf(objArr, i2);
            this.t = Arrays.copyOf(this.t, i2);
            this.s = (String[]) Arrays.copyOf(this.s, i2);
        }
        Object[] objArr2 = this.q;
        int i3 = this.r;
        this.r = i3 + 1;
        objArr2[i3] = obj;
    }

    @Override // roam.a.e.a.c0.a
    public void a() {
        C(roam.a.e.a.c0.b.BEGIN_ARRAY);
        F(((roam.a.e.a.k) D()).iterator());
        this.t[this.r - 1] = 0;
    }

    @Override // roam.a.e.a.c0.a
    public void b() {
        C(roam.a.e.a.c0.b.BEGIN_OBJECT);
        F(new s.b.a((s.b) ((roam.a.e.a.q) D()).a.entrySet()));
    }

    @Override // roam.a.e.a.c0.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.q = new Object[]{u};
        this.r = 1;
    }

    @Override // roam.a.e.a.c0.a
    public void e() {
        C(roam.a.e.a.c0.b.END_ARRAY);
        E();
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // roam.a.e.a.c0.a
    public void f() {
        C(roam.a.e.a.c0.b.END_OBJECT);
        E();
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // roam.a.e.a.c0.a
    public String h() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (i < this.r) {
            Object[] objArr = this.q;
            Object obj = objArr[i];
            if (obj instanceof roam.a.e.a.k) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.t[i]);
                    sb.append(']');
                }
            } else if (obj instanceof roam.a.e.a.q) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String str = this.s[i];
                    if (str != null) {
                        sb.append(str);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    @Override // roam.a.e.a.c0.a
    public boolean i() {
        roam.a.e.a.c0.b bVarV = v();
        return (bVarV == roam.a.e.a.c0.b.END_OBJECT || bVarV == roam.a.e.a.c0.b.END_ARRAY) ? false : true;
    }

    @Override // roam.a.e.a.c0.a
    public boolean l() {
        C(roam.a.e.a.c0.b.BOOLEAN);
        boolean zB = ((roam.a.e.a.s) E()).b();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return zB;
    }

    @Override // roam.a.e.a.c0.a
    public double m() {
        roam.a.e.a.c0.b bVarV = v();
        roam.a.e.a.c0.b bVar = roam.a.e.a.c0.b.NUMBER;
        if (bVarV != bVar && bVarV != roam.a.e.a.c0.b.STRING) {
            throw new IllegalStateException("Expected " + bVar + " but was " + bVarV + k());
        }
        roam.a.e.a.s sVar = (roam.a.e.a.s) D();
        double dDoubleValue = sVar.a instanceof Number ? sVar.c().doubleValue() : Double.parseDouble(sVar.d());
        if (!this.b && (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dDoubleValue);
        }
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return dDoubleValue;
    }

    @Override // roam.a.e.a.c0.a
    public int n() {
        roam.a.e.a.c0.b bVarV = v();
        roam.a.e.a.c0.b bVar = roam.a.e.a.c0.b.NUMBER;
        if (bVarV != bVar && bVarV != roam.a.e.a.c0.b.STRING) {
            throw new IllegalStateException("Expected " + bVar + " but was " + bVarV + k());
        }
        roam.a.e.a.s sVar = (roam.a.e.a.s) D();
        int iIntValue = sVar.a instanceof Number ? sVar.c().intValue() : Integer.parseInt(sVar.d());
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return iIntValue;
    }

    @Override // roam.a.e.a.c0.a
    public long o() {
        roam.a.e.a.c0.b bVarV = v();
        roam.a.e.a.c0.b bVar = roam.a.e.a.c0.b.NUMBER;
        if (bVarV != bVar && bVarV != roam.a.e.a.c0.b.STRING) {
            throw new IllegalStateException("Expected " + bVar + " but was " + bVarV + k());
        }
        roam.a.e.a.s sVar = (roam.a.e.a.s) D();
        long jLongValue = sVar.a instanceof Number ? sVar.c().longValue() : Long.parseLong(sVar.d());
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return jLongValue;
    }

    @Override // roam.a.e.a.c0.a
    public String p() {
        C(roam.a.e.a.c0.b.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) D()).next();
        String str = (String) entry.getKey();
        this.s[this.r - 1] = str;
        F(entry.getValue());
        return str;
    }

    @Override // roam.a.e.a.c0.a
    public void r() {
        C(roam.a.e.a.c0.b.NULL);
        E();
        int i = this.r;
        if (i > 0) {
            int[] iArr = this.t;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // roam.a.e.a.c0.a
    public String t() {
        roam.a.e.a.c0.b bVarV = v();
        roam.a.e.a.c0.b bVar = roam.a.e.a.c0.b.STRING;
        if (bVarV == bVar || bVarV == roam.a.e.a.c0.b.NUMBER) {
            String strD = ((roam.a.e.a.s) E()).d();
            int i = this.r;
            if (i > 0) {
                int[] iArr = this.t;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return strD;
        }
        throw new IllegalStateException("Expected " + bVar + " but was " + bVarV + k());
    }

    @Override // roam.a.e.a.c0.a
    public String toString() {
        return e.class.getSimpleName();
    }

    @Override // roam.a.e.a.c0.a
    public roam.a.e.a.c0.b v() {
        if (this.r == 0) {
            return roam.a.e.a.c0.b.END_DOCUMENT;
        }
        Object objD = D();
        if (objD instanceof Iterator) {
            boolean z = this.q[this.r - 2] instanceof roam.a.e.a.q;
            Iterator it = (Iterator) objD;
            if (!it.hasNext()) {
                return z ? roam.a.e.a.c0.b.END_OBJECT : roam.a.e.a.c0.b.END_ARRAY;
            }
            if (z) {
                return roam.a.e.a.c0.b.NAME;
            }
            F(it.next());
            return v();
        }
        if (objD instanceof roam.a.e.a.q) {
            return roam.a.e.a.c0.b.BEGIN_OBJECT;
        }
        if (objD instanceof roam.a.e.a.k) {
            return roam.a.e.a.c0.b.BEGIN_ARRAY;
        }
        if (!(objD instanceof roam.a.e.a.s)) {
            if (objD instanceof roam.a.e.a.p) {
                return roam.a.e.a.c0.b.NULL;
            }
            if (objD == u) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        Object obj = ((roam.a.e.a.s) objD).a;
        if (obj instanceof String) {
            return roam.a.e.a.c0.b.STRING;
        }
        if (obj instanceof Boolean) {
            return roam.a.e.a.c0.b.BOOLEAN;
        }
        if (obj instanceof Number) {
            return roam.a.e.a.c0.b.NUMBER;
        }
        throw new AssertionError();
    }
}

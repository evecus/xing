package roam.a.e.a.c0;

import com.baidu.mobstat.Config;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class c implements Closeable, Flushable {
    public static final String[] j = new String[128];
    public static final String[] k;
    public final Writer a;
    public int[] b = new int[32];
    public int c = 0;
    public String d;
    public String e;
    public boolean f;
    public boolean g;
    public String h;
    public boolean i;

    static {
        for (int i = 0; i <= 31; i++) {
            j[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = j;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        k = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public c(Writer writer) {
        k(6);
        this.e = Config.TRACE_TODAY_VISIT_SPLIT;
        this.i = true;
        Objects.requireNonNull(writer, "out == null");
        this.a = writer;
    }

    public final void a() throws IOException {
        int iJ = j();
        if (iJ == 1) {
            l(2);
        } else {
            if (iJ != 2) {
                if (iJ == 4) {
                    this.a.append((CharSequence) this.e);
                    l(5);
                    return;
                }
                if (iJ != 6) {
                    if (iJ != 7) {
                        throw new IllegalStateException("Nesting problem.");
                    }
                    if (!this.f) {
                        throw new IllegalStateException("JSON must have only one top-level value.");
                    }
                }
                l(7);
                return;
            }
            this.a.append(',');
        }
        h();
    }

    public c b() {
        s();
        a();
        k(1);
        this.a.write(91);
        return this;
    }

    public c c() {
        s();
        a();
        k(3);
        this.a.write(123);
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.close();
        int i = this.c;
        if (i > 1 || (i == 1 && this.b[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.c = 0;
    }

    public final c d(int i, int i2, char c) throws IOException {
        int iJ = j();
        if (iJ != i2 && iJ != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.h != null) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Dangling name: ");
            sbO.append(this.h);
            throw new IllegalStateException(sbO.toString());
        }
        this.c--;
        if (iJ == i2) {
            h();
        }
        this.a.write(c);
        return this;
    }

    public c e() {
        d(1, 2, ']');
        return this;
    }

    public c f() {
        d(3, 5, '}');
        return this;
    }

    public void flush() throws IOException {
        if (this.c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.a.flush();
    }

    public c g(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.h != null) {
            throw new IllegalStateException();
        }
        if (this.c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.h = str;
        return this;
    }

    public final void h() throws IOException {
        if (this.d == null) {
            return;
        }
        this.a.write(10);
        int i = this.c;
        for (int i2 = 1; i2 < i; i2++) {
            this.a.write(this.d);
        }
    }

    public c i() {
        if (this.h == null) {
            a();
            this.a.write("null");
        } else if (this.i) {
            s();
            a();
            this.a.write("null");
        } else {
            this.h = null;
        }
        return this;
    }

    public final int j() {
        int i = this.c;
        if (i != 0) {
            return this.b[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void k(int i) {
        int i2 = this.c;
        int[] iArr = this.b;
        if (i2 == iArr.length) {
            this.b = Arrays.copyOf(iArr, i2 * 2);
        }
        int[] iArr2 = this.b;
        int i3 = this.c;
        this.c = i3 + 1;
        iArr2[i3] = i;
    }

    public final void l(int i) {
        this.b[this.c - 1] = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.g
            if (r0 == 0) goto L7
            java.lang.String[] r0 = roam.a.e.a.c0.c.k
            goto L9
        L7:
            java.lang.String[] r0 = roam.a.e.a.c0.c.j
        L9:
            java.io.Writer r1 = r8.a
            r2 = 34
            r1.write(r2)
            int r1 = r9.length()
            r3 = 0
            r4 = r3
        L16:
            if (r3 >= r1) goto L45
            char r5 = r9.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L25
            r5 = r0[r5]
            if (r5 != 0) goto L32
            goto L42
        L25:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L2c
            java.lang.String r5 = "\\u2028"
            goto L32
        L2c:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L42
            java.lang.String r5 = "\\u2029"
        L32:
            if (r4 >= r3) goto L3b
            java.io.Writer r6 = r8.a
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.a
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.a
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.a
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.c0.c.m(java.lang.String):void");
    }

    public c n(long j2) {
        s();
        a();
        this.a.write(Long.toString(j2));
        return this;
    }

    public c o(Boolean bool) {
        if (bool == null) {
            return i();
        }
        s();
        a();
        this.a.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public c p(Number number) {
        if (number == null) {
            return i();
        }
        s();
        String string = number.toString();
        if (this.f || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            a();
            this.a.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public c q(String str) {
        if (str == null) {
            return i();
        }
        s();
        a();
        m(str);
        return this;
    }

    public c r(boolean z) {
        s();
        a();
        this.a.write(z ? "true" : "false");
        return this;
    }

    public final void s() throws IOException {
        if (this.h != null) {
            int iJ = j();
            if (iJ == 5) {
                this.a.write(44);
            } else if (iJ != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            h();
            l(4);
            m(this.h);
            this.h = null;
        }
    }
}

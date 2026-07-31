package roam.a.e.a.c0;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;
import roam.a.e.a.a0.q;

/* JADX INFO: loaded from: classes.dex */
public class a implements Closeable {
    public static final char[] p = ")]}'\n".toCharArray();
    public final Reader a;
    public boolean b = false;
    public final char[] c = new char[1024];
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public long i;
    public int j;
    public String k;
    public int[] l;
    public int m;
    public String[] n;
    public int[] o;

    /* JADX INFO: renamed from: roam.a.e.a.c0.a$a, reason: collision with other inner class name */
    public class C0020a extends q {
    }

    static {
        q.a = new C0020a();
    }

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.l = iArr;
        this.m = 0;
        this.m = 1;
        iArr[0] = 6;
        this.n = new String[32];
        this.o = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.a = reader;
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x008e, code lost:
    
        c();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:60:0x0088. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void A() throws java.io.IOException {
        /*
            r10 = this;
            r0 = 0
            r1 = r0
        L2:
            int r2 = r10.h
            if (r2 != 0) goto La
            int r2 = r10.d()
        La:
            r3 = 3
            r4 = 1
            if (r2 != r3) goto L12
            r10.w(r4)
            goto L17
        L12:
            if (r2 != r4) goto L1b
            r10.w(r3)
        L17:
            int r1 = r1 + 1
            goto La7
        L1b:
            r3 = 4
            if (r2 != r3) goto L20
            goto La0
        L20:
            r3 = 2
            if (r2 == r3) goto La0
            r3 = 14
            r5 = 13
            r6 = 12
            r7 = 9
            r8 = 10
            if (r2 == r3) goto L50
            if (r2 != r8) goto L32
            goto L50
        L32:
            r3 = 8
            if (r2 == r3) goto L4a
            if (r2 != r6) goto L39
            goto L4a
        L39:
            if (r2 == r7) goto L47
            if (r2 != r5) goto L3e
            goto L47
        L3e:
            r3 = 16
            if (r2 != r3) goto La7
            int r2 = r10.d
            int r3 = r10.j
            goto L93
        L47:
            r2 = 34
            goto L4c
        L4a:
            r2 = 39
        L4c:
            r10.y(r2)
            goto La7
        L50:
            r2 = r0
        L51:
            int r3 = r10.d
            int r3 = r3 + r2
            int r9 = r10.e
            if (r3 >= r9) goto L97
            char[] r9 = r10.c
            char r3 = r9[r3]
            if (r3 == r7) goto L91
            if (r3 == r8) goto L91
            if (r3 == r6) goto L91
            if (r3 == r5) goto L91
            r9 = 32
            if (r3 == r9) goto L91
            r9 = 35
            if (r3 == r9) goto L8e
            r9 = 44
            if (r3 == r9) goto L91
            r9 = 47
            if (r3 == r9) goto L8e
            r9 = 61
            if (r3 == r9) goto L8e
            r9 = 123(0x7b, float:1.72E-43)
            if (r3 == r9) goto L91
            r9 = 125(0x7d, float:1.75E-43)
            if (r3 == r9) goto L91
            r9 = 58
            if (r3 == r9) goto L91
            r9 = 59
            if (r3 == r9) goto L8e
            switch(r3) {
                case 91: goto L91;
                case 92: goto L8e;
                case 93: goto L91;
                default: goto L8b;
            }
        L8b:
            int r2 = r2 + 1
            goto L51
        L8e:
            r10.c()
        L91:
            int r3 = r10.d
        L93:
            int r2 = r2 + r3
            r10.d = r2
            goto La7
        L97:
            r10.d = r3
            boolean r2 = r10.g(r4)
            if (r2 != 0) goto L50
            goto La7
        La0:
            int r2 = r10.m
            int r2 = r2 - r4
            r10.m = r2
            int r1 = r1 + (-1)
        La7:
            r10.h = r0
            if (r1 != 0) goto L2
            int[] r0 = r10.o
            int r1 = r10.m
            int r1 = r1 - r4
            r2 = r0[r1]
            int r2 = r2 + r4
            r0[r1] = r2
            java.lang.String[] r0 = r10.n
            java.lang.String r2 = "null"
            r0[r1] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.c0.a.A():void");
    }

    public final IOException B(String str) throws d {
        StringBuilder sbO = roam.a.b.a.a.a.o(str);
        sbO.append(k());
        throw new d(sbO.toString());
    }

    public void a() {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 3) {
            w(1);
            this.o[this.m - 1] = 0;
            this.h = 0;
        } else {
            StringBuilder sbO = roam.a.b.a.a.a.o("Expected BEGIN_ARRAY but was ");
            sbO.append(v());
            sbO.append(k());
            throw new IllegalStateException(sbO.toString());
        }
    }

    public void b() {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 1) {
            w(3);
            this.h = 0;
        } else {
            StringBuilder sbO = roam.a.b.a.a.a.o("Expected BEGIN_OBJECT but was ");
            sbO.append(v());
            sbO.append(k());
            throw new IllegalStateException(sbO.toString());
        }
    }

    public final void c() throws d {
        if (this.b) {
            return;
        }
        B("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h = 0;
        this.l[0] = 8;
        this.m = 1;
        this.a.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:267:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int d() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 799
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.c0.a.d():int");
    }

    public void e() {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD != 4) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Expected END_ARRAY but was ");
            sbO.append(v());
            sbO.append(k());
            throw new IllegalStateException(sbO.toString());
        }
        int i = this.m - 1;
        this.m = i;
        int[] iArr = this.o;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.h = 0;
    }

    public void f() {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD != 2) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Expected END_OBJECT but was ");
            sbO.append(v());
            sbO.append(k());
            throw new IllegalStateException(sbO.toString());
        }
        int i = this.m - 1;
        this.m = i;
        this.n[i] = null;
        int[] iArr = this.o;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.h = 0;
    }

    public final boolean g(int i) throws IOException {
        int i2;
        int i3;
        char[] cArr = this.c;
        int i4 = this.g;
        int i5 = this.d;
        this.g = i4 - i5;
        int i6 = this.e;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.e = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.e = 0;
        }
        this.d = 0;
        do {
            Reader reader = this.a;
            int i8 = this.e;
            int i9 = reader.read(cArr, i8, cArr.length - i8);
            if (i9 == -1) {
                return false;
            }
            i2 = i9 + this.e;
            this.e = i2;
            if (this.f == 0 && (i3 = this.g) == 0 && i2 > 0 && cArr[0] == 65279) {
                this.d++;
                this.g = i3 + 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    public String h() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.l[i2];
            if (i3 == 1 || i3 == 2) {
                sb.append('[');
                sb.append(this.o[i2]);
                sb.append(']');
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                sb.append('.');
                String str = this.n[i2];
                if (str != null) {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    public boolean i() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        return (iD == 2 || iD == 4) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean j(char r2) throws roam.a.e.a.c0.d {
        /*
            r1 = this;
            r0 = 9
            if (r2 == r0) goto L3c
            r0 = 10
            if (r2 == r0) goto L3c
            r0 = 12
            if (r2 == r0) goto L3c
            r0 = 13
            if (r2 == r0) goto L3c
            r0 = 32
            if (r2 == r0) goto L3c
            r0 = 35
            if (r2 == r0) goto L39
            r0 = 44
            if (r2 == r0) goto L3c
            r0 = 47
            if (r2 == r0) goto L39
            r0 = 61
            if (r2 == r0) goto L39
            r0 = 123(0x7b, float:1.72E-43)
            if (r2 == r0) goto L3c
            r0 = 125(0x7d, float:1.75E-43)
            if (r2 == r0) goto L3c
            r0 = 58
            if (r2 == r0) goto L3c
            r0 = 59
            if (r2 == r0) goto L39
            switch(r2) {
                case 91: goto L3c;
                case 92: goto L39;
                case 93: goto L3c;
                default: goto L37;
            }
        L37:
            r2 = 1
            goto L3d
        L39:
            r1.c()
        L3c:
            r2 = 0
        L3d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.c0.a.j(char):boolean");
    }

    public String k() {
        return " at line " + (this.f + 1) + " column " + ((this.d - this.g) + 1) + " path " + h();
    }

    public boolean l() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 5) {
            this.h = 0;
            int[] iArr = this.o;
            int i = this.m - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iD != 6) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Expected a boolean but was ");
            sbO.append(v());
            sbO.append(k());
            throw new IllegalStateException(sbO.toString());
        }
        this.h = 0;
        int[] iArr2 = this.o;
        int i2 = this.m - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return false;
    }

    public double m() throws IOException {
        String strS;
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 15) {
            this.h = 0;
            int[] iArr = this.o;
            int i = this.m - 1;
            iArr[i] = iArr[i] + 1;
            return this.i;
        }
        if (iD == 16) {
            this.k = new String(this.c, this.d, this.j);
            this.d += this.j;
        } else {
            if (iD == 8 || iD == 9) {
                strS = s(iD == 8 ? '\'' : '\"');
            } else if (iD == 10) {
                strS = u();
            } else if (iD != 11) {
                StringBuilder sbO = roam.a.b.a.a.a.o("Expected a double but was ");
                sbO.append(v());
                sbO.append(k());
                throw new IllegalStateException(sbO.toString());
            }
            this.k = strS;
        }
        this.h = 11;
        double d = Double.parseDouble(this.k);
        if (!this.b && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new d("JSON forbids NaN and infinities: " + d + k());
        }
        this.k = null;
        this.h = 0;
        int[] iArr2 = this.o;
        int i2 = this.m - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int n() {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.c0.a.n():int");
    }

    public long o() throws IOException {
        String strS;
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 15) {
            this.h = 0;
            int[] iArr = this.o;
            int i = this.m - 1;
            iArr[i] = iArr[i] + 1;
            return this.i;
        }
        if (iD == 16) {
            this.k = new String(this.c, this.d, this.j);
            this.d += this.j;
        } else {
            if (iD != 8 && iD != 9 && iD != 10) {
                StringBuilder sbO = roam.a.b.a.a.a.o("Expected a long but was ");
                sbO.append(v());
                sbO.append(k());
                throw new IllegalStateException(sbO.toString());
            }
            if (iD == 10) {
                strS = u();
            } else {
                strS = s(iD == 8 ? '\'' : '\"');
            }
            this.k = strS;
            try {
                long j = Long.parseLong(strS);
                this.h = 0;
                int[] iArr2 = this.o;
                int i2 = this.m - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            } catch (NumberFormatException e) {
            }
        }
        this.h = 11;
        double d = Double.parseDouble(this.k);
        long j2 = (long) d;
        if (j2 != d) {
            StringBuilder sbO2 = roam.a.b.a.a.a.o("Expected a long but was ");
            sbO2.append(this.k);
            sbO2.append(k());
            throw new NumberFormatException(sbO2.toString());
        }
        this.k = null;
        this.h = 0;
        int[] iArr3 = this.o;
        int i3 = this.m - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return j2;
    }

    public String p() {
        char c;
        String strS;
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 14) {
            strS = u();
        } else {
            if (iD == 12) {
                c = '\'';
            } else {
                if (iD != 13) {
                    StringBuilder sbO = roam.a.b.a.a.a.o("Expected a name but was ");
                    sbO.append(v());
                    sbO.append(k());
                    throw new IllegalStateException(sbO.toString());
                }
                c = '\"';
            }
            strS = s(c);
        }
        this.h = 0;
        this.n[this.m - 1] = strS;
        return strS;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
    
        r9.d = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
    
        if (r1 != '/') goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
    
        if (r4 != r2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0057, code lost:
    
        r9.d = r4 - 1;
        r2 = g(2);
        r9.d++;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
    
        if (r2 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0066, code lost:
    
        c();
        r2 = r9.d;
        r4 = r0[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006f, code lost:
    
        if (r4 == '*') goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0071, code lost:
    
        if (r4 != '/') goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0073, code lost:
    
        r9.d = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0078, code lost:
    
        r9.d = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0081, code lost:
    
        if ((r9.d + 2) <= r9.e) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0087, code lost:
    
        if (g(2) == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008a, code lost:
    
        B("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0090, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0091, code lost:
    
        r1 = r9.c;
        r2 = r9.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0097, code lost:
    
        if (r1[r2] != '\n') goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0099, code lost:
    
        r9.f++;
        r9.g = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a3, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a4, code lost:
    
        if (r1 >= 2) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b3, code lost:
    
        if (r9.c[r9.d + r1] == "*\/".charAt(r1)) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b5, code lost:
    
        r9.d++;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00bb, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00c5, code lost:
    
        if (r1 != '#') goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c7, code lost:
    
        c();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int q(boolean r10) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.c0.a.q(boolean):int");
    }

    public void r() {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD != 7) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Expected null but was ");
            sbO.append(v());
            sbO.append(k());
            throw new IllegalStateException(sbO.toString());
        }
        this.h = 0;
        int[] iArr = this.o;
        int i = this.m - 1;
        iArr[i] = iArr[i] + 1;
    }

    public final String s(char c) throws d {
        char[] cArr = this.c;
        StringBuilder sb = null;
        while (true) {
            int i = this.d;
            int i2 = this.e;
            int i3 = i;
            while (true) {
                if (i3 < i2) {
                    int i4 = i3 + 1;
                    char c2 = cArr[i3];
                    if (c2 == c) {
                        this.d = i4;
                        int i5 = (i4 - i) - 1;
                        if (sb == null) {
                            return new String(cArr, i, i5);
                        }
                        sb.append(cArr, i, i5);
                        return sb.toString();
                    }
                    if (c2 == '\\') {
                        this.d = i4;
                        int i6 = (i4 - i) - 1;
                        if (sb == null) {
                            sb = new StringBuilder(Math.max((i6 + 1) * 2, 16));
                        }
                        sb.append(cArr, i, i6);
                        sb.append(x());
                    } else {
                        if (c2 == '\n') {
                            this.f++;
                            this.g = i4;
                        }
                        i3 = i4;
                    }
                } else {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max((i3 - i) * 2, 16));
                    }
                    sb.append(cArr, i, i3 - i);
                    this.d = i3;
                    if (!g(1)) {
                        B("Unterminated string");
                        throw null;
                    }
                }
            }
        }
    }

    public String t() {
        String str;
        char c;
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 10) {
            str = u();
        } else {
            if (iD == 8) {
                c = '\'';
            } else if (iD == 9) {
                c = '\"';
            } else if (iD == 11) {
                str = this.k;
                this.k = null;
            } else if (iD == 15) {
                str = Long.toString(this.i);
            } else {
                if (iD != 16) {
                    StringBuilder sbO = roam.a.b.a.a.a.o("Expected a string but was ");
                    sbO.append(v());
                    sbO.append(k());
                    throw new IllegalStateException(sbO.toString());
                }
                str = new String(this.c, this.d, this.j);
                this.d += this.j;
            }
            str = s(c);
        }
        this.h = 0;
        int[] iArr = this.o;
        int i = this.m - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    public String toString() {
        return getClass().getSimpleName() + k();
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0048, code lost:
    
        c();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0042. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String u() throws roam.a.e.a.c0.d {
        /*
            r5 = this;
            r0 = 0
        L1:
            r1 = 0
            r2 = r1
        L3:
            int r3 = r5.d
            int r3 = r3 + r2
            int r4 = r5.e
            if (r3 >= r4) goto L4c
            char[] r4 = r5.c
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L5a
            r4 = 10
            if (r3 == r4) goto L5a
            r4 = 12
            if (r3 == r4) goto L5a
            r4 = 13
            if (r3 == r4) goto L5a
            r4 = 32
            if (r3 == r4) goto L5a
            r4 = 35
            if (r3 == r4) goto L48
            r4 = 44
            if (r3 == r4) goto L5a
            r4 = 47
            if (r3 == r4) goto L48
            r4 = 61
            if (r3 == r4) goto L48
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5a
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5a
            r4 = 58
            if (r3 == r4) goto L5a
            r4 = 59
            if (r3 == r4) goto L48
            switch(r3) {
                case 91: goto L5a;
                case 92: goto L48;
                case 93: goto L5a;
                default: goto L45;
            }
        L45:
            int r2 = r2 + 1
            goto L3
        L48:
            r5.c()
            goto L5a
        L4c:
            char[] r3 = r5.c
            int r3 = r3.length
            if (r2 >= r3) goto L5c
            int r3 = r2 + 1
            boolean r3 = r5.g(r3)
            if (r3 == 0) goto L5a
            goto L3
        L5a:
            r1 = r2
            goto L7c
        L5c:
            if (r0 != 0) goto L69
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r0.<init>(r3)
        L69:
            char[] r3 = r5.c
            int r4 = r5.d
            r0.append(r3, r4, r2)
            int r3 = r5.d
            int r2 = r2 + r3
            r5.d = r2
            r2 = 1
            boolean r2 = r5.g(r2)
            if (r2 != 0) goto L1
        L7c:
            if (r0 != 0) goto L88
            java.lang.String r0 = new java.lang.String
            char[] r2 = r5.c
            int r3 = r5.d
            r0.<init>(r2, r3, r1)
            goto L93
        L88:
            char[] r2 = r5.c
            int r3 = r5.d
            r0.append(r2, r3, r1)
            java.lang.String r0 = r0.toString()
        L93:
            int r2 = r5.d
            int r1 = r1 + r2
            r5.d = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.c0.a.u():java.lang.String");
    }

    public b v() {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        switch (iD) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
                return b.NAME;
            case 15:
            case 16:
                return b.NUMBER;
            case 17:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void w(int i) {
        int i2 = this.m;
        int[] iArr = this.l;
        if (i2 == iArr.length) {
            int i3 = i2 * 2;
            this.l = Arrays.copyOf(iArr, i3);
            this.o = Arrays.copyOf(this.o, i3);
            this.n = (String[]) Arrays.copyOf(this.n, i3);
        }
        int[] iArr2 = this.l;
        int i4 = this.m;
        this.m = i4 + 1;
        iArr2[i4] = i;
    }

    public final char x() throws d {
        int i;
        int i2;
        if (this.d == this.e && !g(1)) {
            B("Unterminated escape sequence");
            throw null;
        }
        char[] cArr = this.c;
        int i3 = this.d;
        int i4 = i3 + 1;
        this.d = i4;
        char c = cArr[i3];
        if (c == '\n') {
            this.f++;
            this.g = i4;
            return c;
        }
        if (c == '\"' || c == '\'' || c == '/' || c == '\\') {
            return c;
        }
        if (c == 'b') {
            return '\b';
        }
        if (c == 'f') {
            return '\f';
        }
        if (c == 'n') {
            return '\n';
        }
        if (c == 'r') {
            return '\r';
        }
        if (c == 't') {
            return '\t';
        }
        if (c != 'u') {
            B("Invalid escape sequence");
            throw null;
        }
        if (i4 + 4 > this.e && !g(4)) {
            B("Unterminated escape sequence");
            throw null;
        }
        int i5 = this.d;
        char c2 = 0;
        for (int i6 = i5; i6 < i5 + 4; i6++) {
            char c3 = this.c[i6];
            char c4 = (char) (c2 << 4);
            if (c3 < '0' || c3 > '9') {
                if (c3 >= 'a' && c3 <= 'f') {
                    i = c3 - 'a';
                } else {
                    if (c3 < 'A' || c3 > 'F') {
                        StringBuilder sbO = roam.a.b.a.a.a.o("\\u");
                        sbO.append(new String(this.c, this.d, 4));
                        throw new NumberFormatException(sbO.toString());
                    }
                    i = c3 - 'A';
                }
                i2 = i + 10;
            } else {
                i2 = c3 - '0';
            }
            c2 = (char) (i2 + c4);
        }
        this.d += 4;
        return c2;
    }

    public final void y(char c) throws d {
        char[] cArr = this.c;
        while (true) {
            int i = this.d;
            int i2 = this.e;
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.d = i3;
                        return;
                    }
                    if (c2 == '\\') {
                        this.d = i3;
                        x();
                        break;
                    } else {
                        if (c2 == '\n') {
                            this.f++;
                            this.g = i3;
                        }
                        i = i3;
                    }
                } else {
                    this.d = i;
                    if (!g(1)) {
                        B("Unterminated string");
                        throw null;
                    }
                }
            }
        }
    }

    public final void z() {
        char c;
        do {
            if (this.d >= this.e && !g(1)) {
                return;
            }
            char[] cArr = this.c;
            int i = this.d;
            int i2 = i + 1;
            this.d = i2;
            c = cArr[i];
            if (c == '\n') {
                this.f++;
                this.g = i2;
                return;
            }
        } while (c != '\r');
    }
}

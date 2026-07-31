package com.baidu.mobstat;

import android.content.pm.PackageInfo;
import android.system.Os;
import android.text.TextUtils;
import com.baidu.mobstat.ap;
import com.baidu.mobstat.av;
import com.baidu.mobstat.l;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class n extends l {
    ap.a d;
    private b e;

    static class a {
        static boolean a(ap.a aVar, ap apVar) {
            while (aVar != null) {
                if (!a(aVar.b())) {
                    return false;
                }
                aVar = aVar.d();
            }
            return a(apVar.a());
        }

        private static boolean a(File file) {
            try {
                int i = Os.stat(file.getAbsolutePath()).st_mode;
                if ((i & 1) == 0) {
                    Os.chmod(file.getAbsolutePath(), i | 1);
                }
                return true;
            } catch (Throwable th) {
                return false;
            }
        }
    }

    class b {
        private long c;
        private av.a d;
        private boolean e;
        private int g;
        private as b = new as();
        private boolean f = true;

        b() {
        }

        private boolean a(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    this.c = jSONObject.getLong("pub_lst_ts");
                    this.d = av.a(jSONObject.getString("pub_info"));
                    this.g = jSONObject.getInt("d_form_ver");
                    this.e = false;
                    return true;
                } catch (Exception e) {
                }
            }
            return false;
        }

        public long a() {
            return this.c;
        }

        public void a(long j) {
            if (this.c != j) {
                this.c = j;
                this.e = true;
            }
        }

        public void a(long j, long j2) {
            if (this.b.a(j, j2)) {
                this.e = true;
            }
        }

        public void a(av.a aVar) {
            if (aVar.equals(this.d)) {
                return;
            }
            this.d = aVar;
            this.e = true;
        }

        public boolean a(PackageInfo packageInfo) {
            String strA = n.this.d.a(new File(packageInfo.applicationInfo.dataDir)).a("pub.dat", true);
            this.f = false;
            return a(strA);
        }

        public av.a b() {
            return this.d;
        }

        public boolean c() {
            return a(n.this.d.a("pub.dat", true));
        }

        public boolean d() {
            if (!this.f) {
                throw new IllegalStateException();
            }
            if (this.e) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("pub_info", this.d.j());
                    jSONObject.put("pub_lst_ts", this.c);
                    jSONObject.put("d_form_ver", 1);
                    n.this.d.a("pub.dat", jSONObject.toString(), true);
                    this.e = false;
                    return true;
                } catch (Exception e) {
                }
            }
            return false;
        }

        public boolean e() {
            return n.b(n.this.d.b("pub.dat"), true);
        }
    }

    class c extends l.b {
        private int b;
        private String c;
        private long d;
        private long e;
        private long f;
        private av.a g;

        public c(String str) {
            super(n.this.d, str);
        }

        public void a(b bVar) {
            a(bVar.b());
            b(bVar.a());
        }

        @Override // com.baidu.mobstat.l.b
        public void a(JSONObject jSONObject) {
            this.c = jSONObject.getString(Config.INPUT_DEF_PKG);
            this.e = jSONObject.getInt("tar_pkg_lst_pub_ts");
            this.d = jSONObject.getLong("last_fe_ts");
            this.g = av.a(jSONObject.getString(Config.LAUNCH_INFO));
            this.f = jSONObject.getLong("tar_pkg_lst_up_ts");
            this.b = jSONObject.getInt("d_form_ver");
        }

        public boolean a(long j) {
            if (this.d == j) {
                return false;
            }
            this.d = j;
            a(true);
            return true;
        }

        public boolean a(av.a aVar) {
            if (aVar.equals(this.g)) {
                return false;
            }
            this.g = aVar;
            a(true);
            return true;
        }

        public boolean a(String str) {
            if (str.equals(this.c)) {
                return false;
            }
            this.c = str;
            a(true);
            return true;
        }

        @Override // com.baidu.mobstat.l.b
        public void b(JSONObject jSONObject) throws JSONException {
            jSONObject.put(Config.INPUT_DEF_PKG, this.c);
            jSONObject.put("last_fe_ts", this.d);
            jSONObject.put("tar_pkg_lst_pub_ts", this.e);
            jSONObject.put(Config.LAUNCH_INFO, this.g.j());
            jSONObject.put("tar_pkg_lst_up_ts", this.f);
            jSONObject.put("d_form_ver", 1);
        }

        public boolean b(long j) {
            if (this.e == j) {
                return false;
            }
            this.e = j;
            a(true);
            return true;
        }

        public String c() {
            return this.c;
        }

        public boolean c(long j) {
            if (this.f == j) {
                return false;
            }
            this.f = j;
            a(true);
            return true;
        }

        public av.a d() {
            return this.g;
        }

        public long e() {
            return this.f;
        }
    }

    public n() {
        super("isc", 8000000L);
        this.e = new b();
    }

    private l.e b(l.d dVar, av.a aVar) {
        this.e.c();
        this.d.a();
        if (aVar.equals(this.e.b())) {
            return l.e.a();
        }
        this.e.a(aVar);
        this.e.a(System.currentTimeMillis());
        return l.e.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(File file, boolean z) {
        try {
            Os.chmod(file.getAbsolutePath(), z ? 436 : 432);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private void c() {
        this.e.a(a.a(this.d, this.a.b) ? 1 : 2, 3L);
    }

    @Override // com.baidu.mobstat.l
    public l.e a(l.d dVar, av.a aVar) {
        if (this.a.a.getApplicationInfo().targetSdkVersion >= 28) {
            return l.e.a(-100);
        }
        this.e.c();
        try {
            return b(dVar, aVar);
        } finally {
            this.e.d();
            c();
            this.e.d();
            this.e.e();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0061 A[PHI: r2
  0x0061: PHI (r2v1 com.baidu.mobstat.n$c) = (r2v0 com.baidu.mobstat.n$c), (r2v2 com.baidu.mobstat.n$c), (r2v2 com.baidu.mobstat.n$c), (r2v2 com.baidu.mobstat.n$c) binds: [B:12:0x001b, B:14:0x002d, B:25:0x0054, B:26:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.baidu.mobstat.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.mobstat.l.g a(java.lang.String r10, com.baidu.mobstat.l.f r11) {
        /*
            r9 = this;
            com.baidu.mobstat.l$a r0 = r9.a
            android.content.Context r0 = r0.a
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            r1 = 0
            r2 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r10, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lf
            goto L11
        Lf:
            r0 = move-exception
            r0 = r2
        L11:
            r3 = -2
            if (r0 != 0) goto L19
            com.baidu.mobstat.l$g r10 = com.baidu.mobstat.l.g.a(r3)
            return r10
        L19:
            boolean r4 = r11.a
            if (r4 == 0) goto L61
            com.baidu.mobstat.n$c r2 = new com.baidu.mobstat.n$c
            r2.<init>(r10)
            r2.a()
            java.lang.String r4 = r2.c()
            boolean r4 = r10.equals(r4)
            if (r4 == 0) goto L61
            com.baidu.mobstat.av$a r4 = r2.d()
            long r5 = r0.lastUpdateTime
            long r7 = r2.e()
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            r6 = 1
            if (r5 != 0) goto L40
            r5 = r6
            goto L41
        L40:
            r5 = r1
        L41:
            if (r4 == 0) goto L54
            boolean r7 = r4.d()
            if (r7 == 0) goto L54
            java.lang.String r4 = r4.e()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L54
            r1 = r6
        L54:
            if (r5 == 0) goto L61
            if (r1 == 0) goto L61
            com.baidu.mobstat.av$a r10 = r2.d()
        L5c:
            com.baidu.mobstat.l$g r10 = com.baidu.mobstat.l.g.a(r10)
            return r10
        L61:
            com.baidu.mobstat.n$b r1 = new com.baidu.mobstat.n$b
            r1.<init>()
            boolean r4 = r1.a(r0)
            if (r4 != 0) goto L71
            com.baidu.mobstat.l$g r10 = com.baidu.mobstat.l.g.a(r3)
            return r10
        L71:
            boolean r11 = r11.a
            if (r11 == 0) goto L8c
            if (r2 == 0) goto L8c
            r2.a(r1)
            long r3 = java.lang.System.currentTimeMillis()
            r2.a(r3)
            long r3 = r0.lastUpdateTime
            r2.c(r3)
            r2.a(r10)
            r2.b()
        L8c:
            com.baidu.mobstat.av$a r10 = r1.b()
            goto L5c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.n.a(java.lang.String, com.baidu.mobstat.l$f):com.baidu.mobstat.l$g");
    }

    @Override // com.baidu.mobstat.l
    public void a(l.c cVar) {
        this.d = this.b.a("isc");
    }
}

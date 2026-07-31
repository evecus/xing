package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.ap;
import com.baidu.mobstat.av;
import java.util.Comparator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {
    public static Comparator<l> c = new Comparator<l>() { // from class: com.baidu.mobstat.l.1
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(l lVar, l lVar2) {
            long jB = lVar.b() - lVar2.b();
            return jB != 0 ? jB > 0 ? -1 : 1 : lVar.a().compareTo(lVar2.a());
        }
    };
    protected a a;
    protected ap.a b;
    private final String d;
    private long e;

    public static class a {
        public Context a;
        public ap b;
    }

    public static abstract class b {
        private ap.a a;
        private String b;
        private String c;
        private boolean d = true;

        public b(ap.a aVar, String str) {
            this.a = aVar;
            this.b = str;
            this.c = "target-pkg-" + Base64.encodeToString(str.getBytes(), 3);
        }

        public abstract void a(JSONObject jSONObject);

        public void a(boolean z) {
            this.d = z;
        }

        public boolean a() {
            String strA = this.a.a(this.c, true);
            if (!TextUtils.isEmpty(strA)) {
                try {
                    a(new JSONObject(strA));
                    a(false);
                    return true;
                } catch (Exception e) {
                }
            }
            return false;
        }

        public abstract void b(JSONObject jSONObject);

        public boolean b() {
            if (this.d) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    b(jSONObject);
                    this.a.a(this.c, jSONObject.toString(), true);
                    a(false);
                    return true;
                } catch (Exception e) {
                }
            }
            return false;
        }
    }

    public static class c {
    }

    public static class d {
    }

    public static class e {
        public Exception a;
        private int b;
        private int c;

        public e(int i, int i2, Exception exc) {
            this.b = i;
            this.c = i2;
            this.a = exc;
        }

        public static e a() {
            return new e(0, 0, null);
        }

        public static e a(int i) {
            return new e(-1, i, null);
        }

        public static e b() {
            return a(0);
        }
    }

    public static class f {
        public boolean a;
    }

    public static class g {
        public av.a a;
        public int b;
        public Exception c;

        public g(int i, av.a aVar, Exception exc) {
            this.b = i;
            this.a = aVar;
            this.c = exc;
        }

        public static g a(int i) {
            return new g(i, null, null);
        }

        public static g a(av.a aVar) {
            return new g(0, aVar, null);
        }

        public static g b() {
            return new g(-1, null, null);
        }

        public boolean a() {
            return this.b == 0;
        }
    }

    public l(String str, long j) {
        this.d = str;
        this.e = j;
    }

    public abstract e a(d dVar, av.a aVar);

    public abstract g a(String str, f fVar);

    public String a() {
        return this.d;
    }

    public final void a(a aVar) {
        this.a = aVar;
        this.b = aVar.b.b().a("cs");
    }

    public abstract void a(c cVar);

    public long b() {
        return this.e;
    }
}

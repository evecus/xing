package roam.a.a.c.g;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import roam.a.a.c.e.d;
import roam.a.a.c.e.f;
import roam.a.a.c.e.g;
import roam.a.a.c.e.h;
import roam.a.a.c.f.c;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static b b;
    public static Object c = new Object();
    public Context a;

    public interface a {
        void a(C0014b c0014b);
    }

    /* JADX INFO: renamed from: roam.a.a.c.g.b$b, reason: collision with other inner class name */
    public class C0014b {
        public String a;
        public String b;
        public String c;

        public C0014b(b bVar) {
        }
    }

    public b(Context context) {
        this.a = context;
    }

    public C0014b a() {
        C0014b c0014b;
        synchronized (this) {
            c0014b = new C0014b(this);
            try {
                c0014b.a = roam.a.a.c.a.a.b(this.a, "");
                c0014b.b = g.a(this.a);
                c0014b.c = roam.a.a.c.a.a.a(this.a);
                synchronized (roam.a.a.c.h.a.class) {
                }
                if (roam.a.a.a.b.a.o(c0014b.c) || roam.a.a.a.b.a.o(c0014b.a) || roam.a.a.a.b.a.o(c0014b.b)) {
                    b(0, new HashMap(), null);
                }
            } catch (Throwable th) {
            }
        }
        return c0014b;
    }

    public void b(int i, Map<String, String> map, a aVar) {
        roam.a.a.c.b.a aVar2 = roam.a.a.c.b.a.b;
        aVar2.a = i;
        String strA = roam.a.a.g.a.a.c.a.a(this.a, "vkeyid_settings", "last_apdid_env");
        String strA2 = aVar2.a();
        if (roam.a.a.a.b.a.v(strA) && !roam.a.a.a.b.a.p(strA, strA2)) {
            Context context = this.a;
            synchronized (roam.a.a.c.e.a.class) {
                try {
                    roam.a.a.c.f.a.c(context, "vkeyid_profiles_v3", "deviceid", "");
                    roam.a.a.c.f.a.d("wxcasxx_v3", "wxcasxx", "");
                } catch (Throwable th) {
                    throw th;
                }
            }
            Context context2 = this.a;
            synchronized (d.class) {
                try {
                    roam.a.a.c.f.a.c(context2, "vkeyid_profiles_v4", "key_deviceid_v4", "");
                    roam.a.a.c.f.a.d("wxcasxx_v4", "key_wxcasxx_v4", "");
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            Context context3 = this.a;
            synchronized (f.class) {
                try {
                    SharedPreferences.Editor editorEdit = context3.getSharedPreferences("openapi_file_pri", 0).edit();
                    if (editorEdit != null) {
                        editorEdit.clear();
                        editorEdit.commit();
                    }
                } catch (Throwable th3) {
                    throw th3;
                }
            }
            h.f.clear();
            h.a = "";
            h.b = "";
            h.d = "";
            h.e = "";
            h.c = "";
        }
        if (!roam.a.a.a.b.a.p(strA, strA2)) {
            roam.a.a.g.a.a.c.a.b(this.a, "vkeyid_settings", "last_apdid_env", strA2);
        }
        String strG = roam.a.a.a.b.a.g(map, "utdid", "");
        String strG2 = roam.a.a.a.b.a.g(map, "tid", "");
        String strG3 = roam.a.a.a.b.a.g(map, "userId", "");
        if (roam.a.a.a.b.a.o(strG)) {
            strG = "";
        }
        HashMap map2 = new HashMap();
        map2.put("utdid", strG);
        map2.put("tid", strG2);
        map2.put("userId", strG3);
        map2.put("appName", "");
        map2.put("appKeyClient", "");
        map2.put("appchannel", "");
        map2.put("rpcVersion", "8");
        roam.a.a.c.f.b bVar = roam.a.a.c.f.b.c;
        roam.a.a.c.g.a aVar3 = new roam.a.a.c.g.a(this, map2, null);
        synchronized (bVar) {
            bVar.b.add(aVar3);
            if (bVar.a == null) {
                Thread thread = new Thread(new c(bVar));
                bVar.a = thread;
                thread.start();
            }
        }
    }
}

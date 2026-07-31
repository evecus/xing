package roam.a.a.e.a;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import roam.a.a.c.g.b;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(Context context, Map<String, String> map) {
        String strA;
        synchronized (a.class) {
            try {
                HashMap map2 = new HashMap();
                map2.put("utdid", roam.a.a.a.b.a.g(map, "utdid", ""));
                map2.put("tid", roam.a.a.a.b.a.g(map, "tid", ""));
                map2.put("userId", roam.a.a.a.b.a.g(map, "userId", ""));
                if (b.b == null) {
                    synchronized (b.c) {
                        if (b.b == null) {
                            b.b = new b(context);
                        }
                    }
                }
                b.b.b(0, map2, null);
                strA = roam.a.a.c.a.a.a(context);
            } catch (Throwable th) {
                throw th;
            }
        }
        return strA;
    }
}

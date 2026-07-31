package roam.a.a.c.d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import roam.a.a.c.e.g;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static Map<String, String> a(Context context, Map<String, String> map) {
        HashMap map2;
        synchronized (b.class) {
            try {
                map2 = new HashMap();
                String strG = roam.a.a.a.b.a.g(map, "tid", "");
                String strG2 = roam.a.a.a.b.a.g(map, "utdid", "");
                String strG3 = roam.a.a.a.b.a.g(map, "userId", "");
                String strG4 = roam.a.a.a.b.a.g(map, "appName", "");
                String strG5 = roam.a.a.a.b.a.g(map, "appKeyClient", "");
                String strG6 = roam.a.a.a.b.a.g(map, "tmxSessionId", "");
                String strA = g.a(context);
                String strG7 = roam.a.a.a.b.a.g(map, "sessionId", "");
                map2.put("AC1", strG);
                map2.put("AC2", strG2);
                map2.put("AC3", "");
                map2.put("AC4", strA);
                map2.put("AC5", strG3);
                map2.put("AC6", strG6);
                map2.put("AC7", "");
                map2.put("AC8", strG4);
                map2.put("AC9", strG5);
                if (roam.a.a.a.b.a.v(strG7)) {
                    map2.put("AC10", strG7);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return map2;
    }
}

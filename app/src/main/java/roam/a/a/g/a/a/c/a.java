package roam.a.a.g.a.a.c;

import android.content.Context;
import java.util.HashMap;
import roam.a.a.g.a.a.a.a.b;

/* JADX INFO: loaded from: classes.dex */
public class a {
    /* JADX WARN: Removed duplicated region for block: B:21:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r2, java.lang.String r3, java.lang.String r4) {
        /*
            java.lang.Class<roam.a.a.g.a.a.c.a> r0 = roam.a.a.g.a.a.c.a.class
            monitor-enter(r0)
            r0 = 0
            if (r2 == 0) goto L35
            boolean r1 = roam.a.a.a.b.a.o(r3)     // Catch: java.lang.Throwable -> L39
            if (r1 != 0) goto L35
            boolean r1 = roam.a.a.a.b.a.o(r4)     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L13
            goto L35
        L13:
            r1 = 0
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r3, r1)     // Catch: java.lang.Throwable -> L31
            java.lang.String r3 = ""
            java.lang.String r2 = r2.getString(r4, r3)     // Catch: java.lang.Throwable -> L31
            boolean r3 = roam.a.a.a.b.a.o(r2)     // Catch: java.lang.Throwable -> L31
            if (r3 == 0) goto L28
            java.lang.Class<roam.a.a.g.a.a.c.a> r2 = roam.a.a.g.a.a.c.a.class
        L26:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L39
            goto L38
        L28:
            java.lang.String r3 = roam.a.a.g.a.a.a.a.b.a()     // Catch: java.lang.Throwable -> L31
            java.lang.String r0 = roam.a.a.g.a.a.a.a.b.e(r3, r2)     // Catch: java.lang.Throwable -> L31
            goto L32
        L31:
            r2 = move-exception
        L32:
            java.lang.Class<roam.a.a.g.a.a.c.a> r2 = roam.a.a.g.a.a.c.a.class
            goto L26
        L35:
            java.lang.Class<roam.a.a.g.a.a.c.a> r2 = roam.a.a.g.a.a.c.a.class
            goto L26
        L38:
            return r0
        L39:
            r2 = move-exception
            java.lang.Class<roam.a.a.g.a.a.c.a> r3 = roam.a.a.g.a.a.c.a.class
            monitor-exit(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.g.a.a.c.a.a(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    public static void b(Context context, String str, String str2, String str3) {
        Class<a> cls;
        synchronized (a.class) {
            try {
                if (roam.a.a.a.b.a.o(str) || roam.a.a.a.b.a.o(str2) || context == null) {
                    cls = a.class;
                } else {
                    try {
                        String strB = b.b(b.a(), str3);
                        HashMap map = new HashMap();
                        map.put(str2, strB);
                        roam.a.a.a.b.a.j(context, str, map);
                    } catch (Throwable th) {
                    }
                    cls = a.class;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}

package roam.a.a.c.e;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static String a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = "";
    public static Map<String, String> f = new HashMap();

    public static String a(String str) {
        synchronized (h.class) {
            try {
                String str2 = "apdidTokenCache" + str;
                if (f.containsKey(str2)) {
                    String str3 = f.get(str2);
                    if (roam.a.a.a.b.a.v(str3)) {
                        return str3;
                    }
                }
                return "";
            } finally {
            }
        }
    }

    public static void b(c cVar) {
        synchronized (h.class) {
            try {
                a = cVar.a;
                b = cVar.b;
                d = cVar.d;
                e = cVar.e;
                c = cVar.c;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void c(String str, String str2) {
        synchronized (h.class) {
            try {
                String str3 = "apdidTokenCache" + str;
                if (f.containsKey(str3)) {
                    f.remove(str3);
                }
                f.put(str3, str2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean d(android.content.Context r8, java.lang.String r9) {
        /*
            java.lang.Class<roam.a.a.c.e.h> r0 = roam.a.a.c.e.h.class
            monitor-enter(r0)
            r0 = 86400000(0x5265c00, double:4.2687272E-316)
            r2 = 0
            java.lang.String r4 = "vkeyid_settings"
            java.lang.String r5 = "update_time_interval"
            java.lang.String r4 = roam.a.a.g.a.a.c.a.a(r8, r4, r5)     // Catch: java.lang.Throwable -> L24
            boolean r5 = roam.a.a.a.b.a.v(r4)     // Catch: java.lang.Throwable -> L24
            if (r5 == 0) goto L1c
            long r4 = java.lang.Long.parseLong(r4)     // Catch: java.lang.Exception -> L1b java.lang.Throwable -> L24
            goto L1d
        L1b:
            r4 = move-exception
        L1c:
            r4 = r0
        L1d:
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 >= 0) goto L22
            goto L25
        L22:
            r0 = r4
            goto L25
        L24:
            r4 = move-exception
        L25:
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L58
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L49
            java.lang.String r7 = "vkey_valid"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L49
            r6.append(r9)     // Catch: java.lang.Throwable -> L49
            java.lang.String r9 = "vkeyid_settings"
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L49
            java.lang.String r8 = roam.a.a.g.a.a.c.a.a(r8, r9, r6)     // Catch: java.lang.Throwable -> L49
            boolean r9 = roam.a.a.a.b.a.o(r8)     // Catch: java.lang.Throwable -> L49
            if (r9 == 0) goto L44
            goto L4a
        L44:
            long r2 = java.lang.Long.parseLong(r8)     // Catch: java.lang.Throwable -> L49
            goto L4a
        L49:
            r8 = move-exception
        L4a:
            long r4 = r4 - r2
            long r8 = java.lang.Math.abs(r4)     // Catch: java.lang.Throwable -> L58
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 >= 0) goto L5c
            java.lang.Class<roam.a.a.c.e.h> r8 = roam.a.a.c.e.h.class
            monitor-exit(r8)
            r8 = 1
            goto L60
        L58:
            r8 = move-exception
            roam.a.a.a.b.a.l(r8)     // Catch: java.lang.Throwable -> L61
        L5c:
            java.lang.Class<roam.a.a.c.e.h> r8 = roam.a.a.c.e.h.class
            monitor-exit(r8)
            r8 = 0
        L60:
            return r8
        L61:
            r8 = move-exception
            java.lang.Class<roam.a.a.c.e.h> r9 = roam.a.a.c.e.h.class
            monitor-exit(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.c.e.h.d(android.content.Context, java.lang.String):boolean");
    }

    public static String e() {
        String str;
        synchronized (h.class) {
            try {
                str = d;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    public static String f() {
        String str;
        synchronized (h.class) {
            try {
                str = e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}

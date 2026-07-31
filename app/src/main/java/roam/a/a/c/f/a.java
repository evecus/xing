package roam.a.a.c.f;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(Context context, String str, String str2) {
        if (context != null && !roam.a.a.a.b.a.o(str) && !roam.a.a.a.b.a.o(str2)) {
            try {
                String string = context.getSharedPreferences(str, 0).getString(str2, "");
                if (!roam.a.a.a.b.a.o(string)) {
                    return roam.a.a.g.a.a.a.a.b.e(roam.a.a.g.a.a.a.a.b.a(), string);
                }
            } catch (Throwable th) {
            }
        }
        return null;
    }

    public static String b(String str, String str2) {
        Class<a> cls;
        String strU;
        String strE;
        synchronized (a.class) {
            try {
                if (roam.a.a.a.b.a.o(str) || roam.a.a.a.b.a.o(str2)) {
                    cls = a.class;
                } else {
                    try {
                        strU = roam.a.a.a.b.a.u(str);
                    } catch (Throwable th) {
                    }
                    if (!roam.a.a.a.b.a.o(strU)) {
                        String string = new JSONObject(strU).getString(str2);
                        if (roam.a.a.a.b.a.o(string)) {
                            cls = a.class;
                        } else {
                            strE = roam.a.a.g.a.a.a.a.b.e(roam.a.a.g.a.a.a.a.b.a(), string);
                        }
                        return null;
                    }
                }
                return null;
            } finally {
            }
        }
        return strE;
    }

    public static void c(Context context, String str, String str2, String str3) {
        if (roam.a.a.a.b.a.o(str) || roam.a.a.a.b.a.o(str2) || context == null) {
            return;
        }
        try {
            String strB = roam.a.a.g.a.a.a.a.b.b(roam.a.a.g.a.a.a.a.b.a(), str3);
            HashMap map = new HashMap();
            map.put(str2, strB);
            roam.a.a.a.b.a.j(context, str, map);
        } catch (Throwable th) {
        }
    }

    public static void d(String str, String str2, String str3) {
        Class<a> cls;
        synchronized (a.class) {
            try {
                if (roam.a.a.a.b.a.o(str) || roam.a.a.a.b.a.o(str2)) {
                    cls = a.class;
                } else {
                    try {
                        String strU = roam.a.a.a.b.a.u(str);
                        JSONObject jSONObject = new JSONObject();
                        if (roam.a.a.a.b.a.v(strU)) {
                            try {
                                jSONObject = new JSONObject(strU);
                            } catch (Exception e) {
                                jSONObject = new JSONObject();
                            }
                        }
                        jSONObject.put(str2, roam.a.a.g.a.a.a.a.b.b(roam.a.a.g.a.a.a.a.b.a(), str3));
                        jSONObject.toString();
                        try {
                            System.clearProperty(str);
                        } catch (Throwable th) {
                        }
                        if (roam.a.a.a.b.a.m()) {
                            String str4 = ".SystemConfig" + File.separator + str;
                            try {
                                if (roam.a.a.a.b.a.m()) {
                                    File file = new File(Environment.getExternalStorageDirectory(), str4);
                                    if (file.exists() && file.isFile()) {
                                        file.delete();
                                    }
                                }
                            } catch (Exception e2) {
                            }
                        }
                    } catch (Throwable th2) {
                    }
                    cls = a.class;
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }
}

package roam.a.a.c.e;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static String a(Context context, String str) {
        synchronized (f.class) {
            try {
                String string = context.getSharedPreferences("openapi_file_pri", 0).getString("openApi" + str, "");
                if (!roam.a.a.a.b.a.o(string)) {
                    String strE = roam.a.a.g.a.a.a.a.b.e(roam.a.a.g.a.a.a.a.b.a(), string);
                    if (!roam.a.a.a.b.a.o(strE)) {
                        return strE;
                    }
                }
                return "";
            } finally {
            }
        }
    }

    public static void b() {
        synchronized (f.class) {
        }
    }

    public static void c(Context context, String str, String str2) {
        synchronized (f.class) {
            try {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences("openapi_file_pri", 0).edit();
                if (editorEdit != null) {
                    editorEdit.putString("openApi" + str, roam.a.a.g.a.a.a.a.b.b(roam.a.a.g.a.a.a.a.b.a(), str2));
                    editorEdit.commit();
                }
            } catch (Throwable th) {
            }
        }
    }
}

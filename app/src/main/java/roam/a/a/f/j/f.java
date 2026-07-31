package roam.a.a.f.j;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static String a;

    public static String a(Context context) {
        String packageName;
        if (TextUtils.isEmpty(a)) {
            try {
                packageName = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                packageName = "";
            }
            a = (packageName + "0000000000000000000000000000").substring(0, 24);
        }
        return a;
    }

    public static void b(Context context, String str, String str2) {
        String strA;
        try {
            try {
                strA = roam.a.a.f.d.a.a(roam.a.a.f.d.b.a(a(context), str2.getBytes()));
            } catch (Exception e) {
                strA = null;
            }
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(strA)) {
                roam.a.a.f.a.l.a.b("cp", "TriDesDecryptError", String.format("%s,%s", str, str2));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, strA).commit();
        } catch (Throwable th) {
        }
    }

    public static String c(Context context, String str, String str2) {
        String str3 = null;
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, null);
            if (!TextUtils.isEmpty(string)) {
                try {
                    str3 = new String(roam.a.a.f.d.b.b(a(context), roam.a.a.f.d.a.b(string)));
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(str3)) {
                roam.a.a.f.a.l.a.b("cp", "TriDesEncryptError", String.format("%s,%s", str, string));
            }
        } catch (Exception e2) {
        }
        return str3;
    }
}

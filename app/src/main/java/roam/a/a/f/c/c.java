package roam.a.a.f.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static c c;
    public String a;
    public String b;

    public static c a() {
        c cVar;
        synchronized (c.class) {
            try {
                if (c == null) {
                    c = new c();
                }
                cVar = c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return cVar;
    }

    public static String b(Context context) {
        if (context != null) {
            try {
                StringBuilder sb = new StringBuilder();
                String packageName = context.getPackageName();
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                sb.append("(");
                sb.append(packageName);
                sb.append(";");
                sb.append(packageInfo.versionCode);
                sb.append(")");
                return sb.toString();
            } catch (Exception e) {
            }
        }
        return "";
    }

    public static String c() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        Random random = new Random();
        StringBuilder sbO = roam.a.b.a.a.a.o(hexString);
        sbO.append(random.nextInt(9000) + 1000);
        return sbO.toString();
    }
}

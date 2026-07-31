package roam.a.a.c.a;

import android.content.Context;
import android.os.Environment;
import com.baidu.android.common.util.HanziToPinyin;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import roam.a.a.c.e.b;
import roam.a.a.c.e.c;
import roam.a.a.c.e.d;
import roam.a.a.c.e.f;
import roam.a.a.c.e.g;
import roam.a.a.c.e.h;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public Context a;
    public int b = 4;

    public a(Context context) {
        this.a = context;
    }

    public static String a(Context context) {
        String strE = e(context);
        return roam.a.a.a.b.a.o(strE) ? g.a(context) : strE;
    }

    public static String b(Context context, String str) {
        try {
            f();
            String strA = h.a(str);
            if (!roam.a.a.a.b.a.o(strA)) {
                return strA;
            }
            String strA2 = f.a(context, str);
            h.c(str, strA2);
            if (!roam.a.a.a.b.a.o(strA2)) {
                return strA2;
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public static boolean c() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int iRandom = (int) (Math.random() * 24.0d * 60.0d * 60.0d);
        for (int i = 0; i < 3; i++) {
            try {
                String[] strArrSplit = new String[]{"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"}[i].split(HanziToPinyin.Token.SEPARATOR);
                if (strArrSplit != null && strArrSplit.length == 2) {
                    Date date = new Date();
                    Date date2 = simpleDateFormat.parse(strArrSplit[0] + " 00:00:00");
                    Date date3 = simpleDateFormat.parse(strArrSplit[1] + " 23:59:59");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date3);
                    calendar.add(13, iRandom * 1);
                    Date time = calendar.getTime();
                    if (date.after(date2) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public static String e(Context context) {
        String str;
        c cVarA;
        b bVarA;
        String str2;
        try {
            String str3 = h.a;
            synchronized (h.class) {
                try {
                    str = h.a;
                } finally {
                }
            }
            if (!roam.a.a.a.b.a.o(str)) {
                return str;
            }
            synchronized (d.class) {
                try {
                    String strA = roam.a.a.c.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
                    if (roam.a.a.a.b.a.o(strA)) {
                        strA = roam.a.a.c.f.a.b("wxcasxx_v4", "key_wxcasxx_v4");
                    }
                    cVarA = d.a(strA);
                } finally {
                }
            }
            if (cVarA != null) {
                h.b(cVarA);
                String str4 = cVarA.a;
                if (roam.a.a.a.b.a.v(str4)) {
                    return str4;
                }
            }
            synchronized (roam.a.a.c.e.a.class) {
                try {
                    String strA2 = roam.a.a.c.f.a.a(context, "vkeyid_profiles_v3", "deviceid");
                    if (roam.a.a.a.b.a.o(strA2)) {
                        strA2 = roam.a.a.c.f.a.b("wxcasxx_v3", "wxcasxx");
                    }
                    bVarA = roam.a.a.c.e.a.a(strA2);
                } finally {
                }
            }
            if (bVarA != null) {
                synchronized (h.class) {
                    try {
                        str2 = bVarA.a;
                        h.a = str2;
                        h.b = bVarA.b;
                        h.c = bVarA.c;
                    } finally {
                    }
                }
                if (roam.a.a.a.b.a.v(str2)) {
                    return str2;
                }
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public static void f() {
        for (int i = 0; i < 5; i++) {
            try {
                String str = new String[]{"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"}[i];
                File file = new File(Environment.getExternalStorageDirectory(), ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            } catch (Throwable th) {
                return;
            }
        }
    }

    public final roam.a.a.g.a.a.e.d.a d(Map<String, String> map) {
        c cVarA;
        c cVarA2;
        b bVarA;
        b bVarA2;
        try {
            Context context = this.a;
            roam.a.a.g.a.a.e.d.b bVar = new roam.a.a.g.a.a.e.d.b();
            String strG = roam.a.a.a.b.a.g(map, "appName", "");
            String strG2 = roam.a.a.a.b.a.g(map, "sessionId", "");
            String strG3 = roam.a.a.a.b.a.g(map, "rpcVersion", "");
            String strB = b(context, strG);
            synchronized (roam.a.a.c.h.a.class) {
            }
            String strA = roam.a.a.g.a.a.c.a.a(context, "vkeyid_settings", "dynamic_key");
            if (roam.a.a.a.b.a.v(strG2)) {
                bVar.c = strG2;
            } else {
                bVar.c = strB;
            }
            bVar.d = "";
            bVar.e = strA;
            bVar.a = "android";
            synchronized (d.class) {
                try {
                    String strA2 = roam.a.a.c.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
                    cVarA = roam.a.a.a.b.a.o(strA2) ? null : d.a(strA2);
                } finally {
                }
            }
            String str = cVarA != null ? cVarA.a : "";
            if (roam.a.a.a.b.a.o(str)) {
                synchronized (roam.a.a.c.e.a.class) {
                    try {
                        String strA3 = roam.a.a.c.f.a.a(context, "vkeyid_profiles_v3", "deviceid");
                        bVarA2 = roam.a.a.a.b.a.o(strA3) ? null : roam.a.a.c.e.a.a(strA3);
                    } finally {
                    }
                }
                if (bVarA2 != null) {
                    str = bVarA2.a;
                }
            }
            synchronized (d.class) {
                try {
                    String strB2 = roam.a.a.c.f.a.b("wxcasxx_v4", "key_wxcasxx_v4");
                    cVarA2 = roam.a.a.a.b.a.o(strB2) ? null : d.a(strB2);
                } finally {
                }
            }
            String str2 = cVarA2 != null ? cVarA2.a : "";
            if (roam.a.a.a.b.a.o(str2)) {
                synchronized (roam.a.a.c.e.a.class) {
                    try {
                        String strB3 = roam.a.a.c.f.a.b("wxcasxx_v3", "wxcasxx");
                        bVarA = roam.a.a.a.b.a.o(strB3) ? null : roam.a.a.c.e.a.a(strB3);
                    } finally {
                    }
                }
                if (bVarA != null) {
                    str2 = bVarA.a;
                }
            }
            bVar.g = strG3;
            if (roam.a.a.a.b.a.o(str)) {
                bVar.b = str2;
            } else {
                bVar.b = str;
            }
            bVar.f = roam.a.a.c.d.d.b(context, map);
            return ((roam.a.a.g.a.a.e.e.b) roam.a.a.a.b.a.a(this.a, roam.a.a.c.b.a.b.a())).a(bVar);
        } catch (Throwable th) {
            roam.a.a.a.b.a.l(th);
            return null;
        }
    }
}

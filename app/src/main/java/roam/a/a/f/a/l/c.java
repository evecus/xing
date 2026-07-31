package roam.a.a.f.a.l;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i = "";
    public String j;

    public c(Context context) {
        String packageName;
        String str;
        String string = "";
        context = context != null ? context.getApplicationContext() : context;
        this.a = String.format("123456789,%s", new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                packageName = applicationContext.getPackageName();
                try {
                    str = applicationContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
                } catch (Throwable th) {
                    str = "-";
                }
            } catch (Throwable th2) {
                str = "-";
                packageName = str;
            }
        } else {
            str = "-";
            packageName = str;
        }
        this.c = String.format("%s,%s,-,-,-", packageName, str);
        this.d = String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", a("15.5.5"), a("h.a.3.5.5"));
        this.e = String.format("%s,%s,-,-,-", a(roam.a.a.f.i.b.a().a), a(roam.a.a.f.h.b.a().d()));
        if (context != null) {
            try {
                string = context.getResources().getConfiguration().locale.toString();
            } catch (Throwable th3) {
            }
        }
        this.f = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", a(string), "android", a(Build.VERSION.RELEASE), a(Build.MODEL), "-", a(roam.a.a.f.j.a.a(context).b()), a(roam.a.a.f.j.a.c(context).b), "gw", a(roam.a.a.f.j.a.a(context).d()));
        this.g = "-";
        this.h = "-";
        this.j = "-";
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("-", "=").replace("^", "~");
    }

    public final void b(String str, String str2, String str3) {
        StringBuilder sbO = roam.a.b.a.a.a.o(!TextUtils.isEmpty(this.i) ? "^" : "");
        sbO.append(String.format("%s,%s,%s,%s", str, str2, a(str3), "-"));
        this.i = roam.a.b.a.a.a.m(new StringBuilder(), this.i, sbO.toString());
    }

    public final void c(String str, String str2, Throwable th) {
        String string;
        if (th == null) {
            string = "";
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                stringBuffer.append(th.getClass().getName());
                stringBuffer.append(Config.TRACE_TODAY_VISIT_SPLIT);
                stringBuffer.append(th.getMessage());
                stringBuffer.append(" 》 ");
                StackTraceElement[] stackTrace = th.getStackTrace();
                if (stackTrace != null) {
                    for (StackTraceElement stackTraceElement : stackTrace) {
                        stringBuffer.append(stackTraceElement.toString() + " 》 ");
                    }
                }
            } catch (Throwable th2) {
            }
            string = stringBuffer.toString();
        }
        b(str, str2, string);
    }
}

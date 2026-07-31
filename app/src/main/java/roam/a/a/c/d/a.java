package roam.a.a.c.d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static Map<String, String> a(Context context, Map<String, String> map) {
        HashMap map2;
        String str;
        synchronized (a.class) {
            try {
                String strG = roam.a.a.a.b.a.g(map, "appchannel", "");
                map2 = new HashMap();
                map2.put("AA1", context.getPackageName());
                try {
                    str = context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
                } catch (Exception e) {
                    str = "0.0.0";
                }
                map2.put("AA2", str);
                map2.put("AA3", "APPSecuritySDK-ALIPAY");
                map2.put("AA4", "3.2.2-20180331");
                map2.put("AA6", strG);
            } catch (Throwable th) {
                throw th;
            }
        }
        return map2;
    }
}

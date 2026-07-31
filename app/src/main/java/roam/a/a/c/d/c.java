package roam.a.a.c.d;

import android.content.Context;
import android.os.Build;
import com.baidu.mobstat.PropertyType;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static Map<String, String> a(Context context) {
        HashMap map;
        synchronized (c.class) {
            try {
                map = new HashMap();
                map.put("AE1", "android");
                StringBuilder sb = new StringBuilder();
                sb.append(roam.a.a.g.a.a.b.c.b() ? "1" : PropertyType.UID_PROPERTRY);
                map.put("AE2", sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(roam.a.a.g.a.a.b.c.a(context) ? "1" : PropertyType.UID_PROPERTRY);
                map.put("AE3", sb2.toString());
                map.put("AE4", Build.BOARD);
                map.put("AE5", Build.BRAND);
                map.put("AE6", Build.DEVICE);
                map.put("AE7", Build.DISPLAY);
                map.put("AE8", Build.VERSION.INCREMENTAL);
                map.put("AE9", Build.MANUFACTURER);
                map.put("AE10", Build.MODEL);
                map.put("AE11", Build.PRODUCT);
                map.put("AE12", Build.VERSION.RELEASE);
                map.put("AE13", Build.VERSION.SDK);
                map.put("AE14", Build.TAGS);
                map.put("AE15", roam.a.a.g.a.a.b.c.c());
                map.put("AE21", roam.a.a.g.a.a.b.a.g());
            } finally {
            }
        }
        return map;
    }
}

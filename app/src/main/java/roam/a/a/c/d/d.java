package roam.a.a.c.d;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static Map<String, String> a;
    public static final String[] b = {"AD1", "AD2", "AD3", "AD8", "AD9", "AD10", "AD11", "AD12", "AD14", "AD15", "AD16", "AD18", "AD20", "AD21", "AD23", "AD24", "AD26", "AD27", "AD28", "AD29", "AD30", "AD31", "AD34", "AA1", "AA2", "AA3", "AA4", "AC4", "AC10", "AE1", "AE2", "AE3", "AE4", "AE5", "AE6", "AE7", "AE8", "AE9", "AE10", "AE11", "AE12", "AE13", "AE14", "AE15"};

    public static String a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            String str2 = map.get(str);
            String str3 = "";
            if (str2 == null) {
                str2 = "";
            }
            StringBuilder sb = new StringBuilder();
            if (i != 0) {
                str3 = "&";
            }
            stringBuffer.append(roam.a.b.a.a.a.n(sb, str3, str, "=", str2));
        }
        return stringBuffer.toString();
    }

    public static Map<String, String> b(Context context, Map<String, String> map) {
        HashMap map2;
        Map<String, String> map3;
        synchronized (d.class) {
            try {
                if (a == null) {
                    d(context, map);
                }
                Map<String, String> map4 = a;
                synchronized (c.class) {
                    try {
                        map2 = new HashMap();
                        try {
                            map2.put("AE16", "");
                        } catch (Throwable th) {
                        }
                    } finally {
                    }
                }
                map4.putAll(map2);
                map3 = a;
            } catch (Throwable th2) {
                throw th2;
            }
        }
        return map3;
    }

    public static String c(Context context, Map<String, String> map) {
        String strT;
        synchronized (d.class) {
            try {
                b(context, map);
                TreeMap treeMap = new TreeMap();
                for (String str : b) {
                    if (a.containsKey(str)) {
                        treeMap.put(str, a.get(str));
                    }
                }
                strT = roam.a.a.a.b.a.t(a(treeMap));
            } catch (Throwable th) {
                throw th;
            }
        }
        return strT;
    }

    public static void d(Context context, Map<String, String> map) {
        synchronized (d.class) {
            try {
                TreeMap treeMap = new TreeMap();
                a = treeMap;
                treeMap.putAll(b.a(context, map));
                a.putAll(c.a(context));
                a.putAll(roam.a.a.a.b.a.h(context));
                a.putAll(a.a(context, map));
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

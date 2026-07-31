package roam.a.a.a.a;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static List<i> a;

    static {
        ArrayList arrayList = new ArrayList();
        a = arrayList;
        arrayList.add(new l());
        a.add(new d());
        a.add(new c());
        a.add(new h());
        a.add(new k());
        a.add(new b());
        a.add(new a());
        a.add(new g());
    }

    public static final <T> T a(Object obj, Type type) {
        T t;
        for (i iVar : a) {
            if (iVar.a(roam.a.a.a.b.a.b(type)) && (t = (T) iVar.c(obj, type)) != null) {
                return t;
            }
        }
        return null;
    }

    public static final Object b(String str, Type type) {
        d.b.a.b cVar;
        if (str == null || str.length() == 0) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith("[") && strTrim.endsWith("]")) {
            cVar = new d.b.a.b(new d.b.a.d(strTrim));
        } else {
            if (!strTrim.startsWith("{") || !strTrim.endsWith("}")) {
                return a(strTrim, type);
            }
            cVar = new d.b.a.c(new d.b.a.d(strTrim));
        }
        return a(cVar, type);
    }
}

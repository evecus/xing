package roam.a.a.a.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static List<j> a;

    static {
        ArrayList arrayList = new ArrayList();
        a = arrayList;
        arrayList.add(new l());
        a.add(new d());
        a.add(new c());
        a.add(new h());
        a.add(new b());
        a.add(new a());
        a.add(new g());
    }

    public static String a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object objB = b(obj);
        if (roam.a.a.a.b.a.n(objB.getClass())) {
            return d.b.a.c.d(objB.toString());
        }
        if (Collection.class.isAssignableFrom(objB.getClass())) {
            return new d.b.a.b((List) objB).toString();
        }
        if (Map.class.isAssignableFrom(objB.getClass())) {
            return new d.b.a.c((Map) objB).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + objB.getClass());
    }

    public static Object b(Object obj) {
        Object objB;
        if (obj == null) {
            return null;
        }
        for (j jVar : a) {
            if (jVar.a(obj.getClass()) && (objB = jVar.b(obj)) != null) {
                return objB;
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}

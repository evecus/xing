package roam.a.a.a.a;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class k implements i {
    @Override // roam.a.a.a.a.i, roam.a.a.a.a.j
    public final boolean a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }

    @Override // roam.a.a.a.a.i
    public final Object c(Object obj, Type type) {
        if (!obj.getClass().equals(d.b.a.b.class)) {
            return null;
        }
        d.b.a.b bVar = (d.b.a.b) obj;
        HashSet hashSet = new HashSet();
        Class cls = type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class;
        for (int i = 0; i < bVar.a(); i++) {
            hashSet.add(e.a(bVar.b(i), cls));
        }
        return hashSet;
    }
}

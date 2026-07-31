package roam.a.a.a.a;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class d implements i, j {
    @Override // roam.a.a.a.a.i, roam.a.a.a.a.j
    public final boolean a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }

    @Override // roam.a.a.a.a.j
    public final Object b(Object obj) {
        return ((Enum) obj).name();
    }

    @Override // roam.a.a.a.a.i
    public final Object c(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }
}

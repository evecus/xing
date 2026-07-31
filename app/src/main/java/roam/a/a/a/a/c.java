package roam.a.a.a.a;

import java.lang.reflect.Type;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class c implements i, j {
    @Override // roam.a.a.a.a.i, roam.a.a.a.a.j
    public final boolean a(Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }

    @Override // roam.a.a.a.a.j
    public final Object b(Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }

    @Override // roam.a.a.a.a.i
    public final Object c(Object obj, Type type) {
        return new Date(((Long) obj).longValue());
    }
}

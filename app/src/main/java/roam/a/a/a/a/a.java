package roam.a.a.a.a;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a implements i, j {
    @Override // roam.a.a.a.a.i, roam.a.a.a.a.j
    public final boolean a(Class<?> cls) {
        return cls.isArray();
    }

    @Override // roam.a.a.a.a.j
    public final Object b(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (Object[]) obj) {
            arrayList.add(f.b(obj2));
        }
        return arrayList;
    }

    @Override // roam.a.a.a.a.i
    public final Object c(Object obj, Type type) {
        if (!obj.getClass().equals(d.b.a.b.class)) {
            return null;
        }
        d.b.a.b bVar = (d.b.a.b) obj;
        if (type instanceof GenericArrayType) {
            throw new IllegalArgumentException("Does not support generic array!");
        }
        Class<?> componentType = ((Class) type).getComponentType();
        int iA = bVar.a();
        Object objNewInstance = Array.newInstance(componentType, iA);
        for (int i = 0; i < iA; i++) {
            Array.set(objNewInstance, i, e.a(bVar.b(i), componentType));
        }
        return objNewInstance;
    }
}

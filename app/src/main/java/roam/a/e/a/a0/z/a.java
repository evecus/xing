package roam.a.e.a.a0.z;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class a<E> extends x<Object> {
    public static final y c = new C0017a();
    public final Class<E> a;
    public final x<E> b;

    /* JADX INFO: renamed from: roam.a.e.a.a0.z.a$a, reason: collision with other inner class name */
    public class C0017a implements y {
        @Override // roam.a.e.a.y
        public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
            Type type = aVar.b;
            boolean z = type instanceof GenericArrayType;
            if (!z && (!(type instanceof Class) || !((Class) type).isArray())) {
                return null;
            }
            Type genericComponentType = z ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
            return new a(iVar, iVar.c(new roam.a.e.a.b0.a<>(genericComponentType)), roam.a.e.a.a0.a.e(genericComponentType));
        }
    }

    public a(roam.a.e.a.i iVar, x<E> xVar, Class<E> cls) {
        this.b = new n(iVar, xVar, cls);
        this.a = cls;
    }

    @Override // roam.a.e.a.x
    public Object a(roam.a.e.a.c0.a aVar) {
        if (aVar.v() == roam.a.e.a.c0.b.NULL) {
            aVar.r();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.a();
        while (aVar.i()) {
            arrayList.add(this.b.a(aVar));
        }
        aVar.e();
        int size = arrayList.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.a, size);
        for (int i = 0; i < size; i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, Object obj) {
        if (obj == null) {
            cVar.i();
            return;
        }
        cVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.b.b(cVar, (E) Array.get(obj, i));
        }
        cVar.e();
    }
}

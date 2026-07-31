package roam.a.e.a.a0.z;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.Iterator;
import roam.a.e.a.a0.t;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class b implements y {
    public final roam.a.e.a.a0.g a;

    public static final class a<E> extends x<Collection<E>> {
        public final x<E> a;
        public final t<? extends Collection<E>> b;

        public a(roam.a.e.a.i iVar, Type type, x<E> xVar, t<? extends Collection<E>> tVar) {
            this.a = new n(iVar, xVar, type);
            this.b = tVar;
        }

        @Override // roam.a.e.a.x
        public Object a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            Collection<E> collectionA = this.b.a();
            aVar.a();
            while (aVar.i()) {
                collectionA.add(this.a.a(aVar));
            }
            aVar.e();
            return collectionA;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Object obj) {
            Collection collection = (Collection) obj;
            if (collection == null) {
                cVar.i();
                return;
            }
            cVar.b();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.a.b(cVar, it.next());
            }
            cVar.e();
        }
    }

    public b(roam.a.e.a.a0.g gVar) {
        this.a = gVar;
    }

    @Override // roam.a.e.a.y
    public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
        Type type = aVar.b;
        Class<? super T> cls = aVar.a;
        if (!Collection.class.isAssignableFrom(cls)) {
            return null;
        }
        Type typeF = roam.a.e.a.a0.a.f(type, cls, Collection.class);
        if (typeF instanceof WildcardType) {
            typeF = ((WildcardType) typeF).getUpperBounds()[0];
        }
        Class cls2 = typeF instanceof ParameterizedType ? ((ParameterizedType) typeF).getActualTypeArguments()[0] : Object.class;
        return new a(iVar, cls2, iVar.c(new roam.a.e.a.b0.a<>(cls2)), this.a.a(aVar));
    }
}

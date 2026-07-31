package roam.a.a.a.a;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class b implements i, j {
    @Override // roam.a.a.a.a.i, roam.a.a.a.a.j
    public final boolean a(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls);
    }

    @Override // roam.a.a.a.a.j
    public final Object b(Object obj) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((Iterable) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(f.b(it.next()));
        }
        return arrayList;
    }

    @Override // roam.a.a.a.a.i
    public final Object c(Object obj, Type type) {
        Collection collectionNoneOf;
        if (!obj.getClass().equals(d.b.a.b.class)) {
            return null;
        }
        Class<?> clsB = roam.a.a.a.b.a.b(type);
        d.b.a.b bVar = (d.b.a.b) obj;
        if (clsB == AbstractCollection.class) {
            collectionNoneOf = new ArrayList();
        } else if (clsB.isAssignableFrom(HashSet.class)) {
            collectionNoneOf = new HashSet();
        } else if (clsB.isAssignableFrom(LinkedHashSet.class)) {
            collectionNoneOf = new LinkedHashSet();
        } else if (clsB.isAssignableFrom(TreeSet.class)) {
            collectionNoneOf = new TreeSet();
        } else if (clsB.isAssignableFrom(ArrayList.class)) {
            collectionNoneOf = new ArrayList();
        } else if (clsB.isAssignableFrom(EnumSet.class)) {
            collectionNoneOf = EnumSet.noneOf((Class) (type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class));
        } else {
            try {
                collectionNoneOf = (Collection) clsB.newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException("create instane error, class " + clsB.getName());
            }
        }
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Does not support the implement for generics.");
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        for (int i = 0; i < bVar.a(); i++) {
            collectionNoneOf.add(e.a(bVar.b(i), type2));
        }
        return collectionNoneOf;
    }
}

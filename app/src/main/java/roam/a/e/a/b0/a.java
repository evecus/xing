package roam.a.e.a.b0;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class a<T> {
    public final Class<? super T> a;
    public final Type b;
    public final int c;

    public a() {
        Type genericSuperclass = a.class.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        Type typeA = roam.a.e.a.a0.a.a(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        this.b = typeA;
        this.a = (Class<? super T>) roam.a.e.a.a0.a.e(typeA);
        this.c = typeA.hashCode();
    }

    public a(Type type) {
        Objects.requireNonNull(type);
        Type typeA = roam.a.e.a.a0.a.a(type);
        this.b = typeA;
        this.a = (Class<? super T>) roam.a.e.a.a0.a.e(typeA);
        this.c = typeA.hashCode();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && roam.a.e.a.a0.a.c(this.b, ((a) obj).b);
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return roam.a.e.a.a0.a.i(this.b);
    }
}

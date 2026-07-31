package roam.a.e.a.a0;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
public class j<T> implements t<T> {
    public final Type a;

    public j(g gVar, Type type) {
        this.a = type;
    }

    @Override // roam.a.e.a.a0.t
    public T a() {
        Type type = this.a;
        if (!(type instanceof ParameterizedType)) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Invalid EnumSet type: ");
            sbO.append(this.a.toString());
            throw new roam.a.e.a.o(sbO.toString());
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof Class) {
            return (T) EnumSet.noneOf((Class) type2);
        }
        StringBuilder sbO2 = roam.a.b.a.a.a.o("Invalid EnumSet type: ");
        sbO2.append(this.a.toString());
        throw new roam.a.e.a.o(sbO2.toString());
    }
}

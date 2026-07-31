package roam.a.e.a.a0.z;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import roam.a.e.a.a0.z.j;
import roam.a.e.a.x;

/* JADX INFO: loaded from: classes.dex */
public final class n<T> extends x<T> {
    public final roam.a.e.a.i a;
    public final x<T> b;
    public final Type c;

    public n(roam.a.e.a.i iVar, x<T> xVar, Type type) {
        this.a = iVar;
        this.b = xVar;
        this.c = type;
    }

    @Override // roam.a.e.a.x
    public T a(roam.a.e.a.c0.a aVar) {
        return this.b.a(aVar);
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, T t) {
        x<T> xVarC = this.b;
        Type type = this.c;
        if (t != null && (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class))) {
            type = t.getClass();
        }
        if (type != this.c) {
            xVarC = this.a.c(new roam.a.e.a.b0.a<>(type));
            if (xVarC instanceof j.a) {
                x<T> xVar = this.b;
                if (!(xVar instanceof j.a)) {
                    xVarC = xVar;
                }
            }
        }
        xVarC.b(cVar, t);
    }
}

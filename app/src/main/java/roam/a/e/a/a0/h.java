package roam.a.e.a.a0;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
public class h<T> implements t<T> {
    public final Constructor a;

    public h(g gVar, Constructor constructor) {
        this.a = constructor;
    }

    @Override // roam.a.e.a.a0.t
    public T a() {
        try {
            return (T) this.a.newInstance(null);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Failed to invoke ");
            sbO.append(this.a);
            sbO.append(" with no args");
            throw new RuntimeException(sbO.toString(), e2);
        } catch (InvocationTargetException e3) {
            StringBuilder sbO2 = roam.a.b.a.a.a.o("Failed to invoke ");
            sbO2.append(this.a);
            sbO2.append(" with no args");
            throw new RuntimeException(sbO2.toString(), e3.getTargetException());
        }
    }
}

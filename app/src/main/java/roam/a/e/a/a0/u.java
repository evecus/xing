package roam.a.e.a.a0;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class u extends y {
    public final Method a;
    public final Object b;

    public u(Method method, Object obj) {
        this.a = method;
        this.b = obj;
    }

    @Override // roam.a.e.a.a0.y
    public <T> T b(Class<T> cls) {
        y.a(cls);
        return (T) this.a.invoke(this.b, cls);
    }
}

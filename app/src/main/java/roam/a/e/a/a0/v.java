package roam.a.e.a.a0;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class v extends y {
    public final Method a;
    public final int b;

    public v(Method method, int i) {
        this.a = method;
        this.b = i;
    }

    @Override // roam.a.e.a.a0.y
    public <T> T b(Class<T> cls) {
        y.a(cls);
        return (T) this.a.invoke(null, cls, Integer.valueOf(this.b));
    }
}

package roam.a.e.a.a0;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class w extends y {
    public final Method a;

    public w(Method method) {
        this.a = method;
    }

    @Override // roam.a.e.a.a0.y
    public <T> T b(Class<T> cls) {
        y.a(cls);
        return (T) this.a.invoke(null, cls, Object.class);
    }
}

package roam.a.a.b.b.a.a;

import android.content.Context;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: classes.dex */
public final class n {
    public Context a;

    public n(Context context) {
        this.a = context;
    }

    public final <T> T a(Class<T> cls, f fVar) {
        z zVar = new z(new o(this, fVar));
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new a0(zVar.a, cls, zVar.b));
    }
}

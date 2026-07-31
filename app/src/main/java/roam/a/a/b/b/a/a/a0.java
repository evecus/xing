package roam.a.a.b.b.a.a;

import android.os.Looper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import roam.a.a.b.b.a.a.d.d;

/* JADX INFO: loaded from: classes.dex */
public final class a0 implements InvocationHandler {
    public b0 a;

    public a0(o oVar, Class<?> cls, b0 b0Var) {
        this.a = b0Var;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        b0 b0Var = this.a;
        Objects.requireNonNull(b0Var);
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        roam.a.a.d.a.a.a.a aVar = (roam.a.a.d.a.a.a.a) method.getAnnotation(roam.a.a.d.a.a.a.a.class);
        boolean z = method.getAnnotation(roam.a.a.d.a.a.a.b.class) != null;
        Type genericReturnType = method.getGenericReturnType();
        method.getAnnotations();
        ThreadLocal<Object> threadLocal = b0.c;
        threadLocal.set(null);
        ThreadLocal<Map<String, Object>> threadLocal2 = b0.d;
        threadLocal2.set(null);
        if (aVar == null) {
            throw new IllegalStateException("OperationType must be set.");
        }
        String strValue = aVar.value();
        int iIncrementAndGet = b0Var.a.incrementAndGet();
        d dVar = new d(iIncrementAndGet, strValue, objArr);
        if (threadLocal2.get() != null) {
            dVar.d = threadLocal2.get();
        }
        byte[] bArr = (byte[]) new p(b0Var.b.a, method, iIncrementAndGet, strValue, dVar.a(), z).a();
        threadLocal2.set(null);
        Object objA = new roam.a.a.b.b.a.a.d.c(genericReturnType, bArr).a();
        if (genericReturnType != Void.TYPE) {
            threadLocal.set(objA);
        }
        return threadLocal.get();
    }
}

package com.android.cglib.proxy;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class MethodProxyExecuter {
    public static final String EXECUTE_INTERCEPTOR = "executeInterceptor";
    public static final String EXECUTE_METHOD = "executeMethod";

    public static Object executeInterceptor(MethodInterceptor methodInterceptor, Class<?> cls, String str, Class[] clsArr, Object[] objArr, Object obj) {
        if (objArr == null) {
            objArr = new Object[0];
        }
        if (clsArr == null) {
            clsArr = new Class[0];
        }
        if (methodInterceptor == null) {
            return executeMethod(cls, str, clsArr, objArr, obj);
        }
        try {
            return methodInterceptor.intercept(obj, objArr, new MethodProxy(cls, str, clsArr));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProxyException(e.getMessage());
        }
    }

    public static Object executeMethod(Class cls, String str, Class[] clsArr, Object[] objArr, Object obj) {
        try {
            Method method = cls.getMethod(str + Const.SUBCLASS_INVOKE_SUPER_SUFFIX, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProxyException(e.getCause());
        }
    }
}

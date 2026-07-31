package com.android.cglib.proxy;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class MethodProxy {
    private Class[] argsType;
    private String methodName;
    private Class subClass;

    public MethodProxy(Class cls, String str, Class[] clsArr) {
        this.subClass = cls;
        this.methodName = str;
        this.argsType = clsArr;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public Method getOriginalMethod() {
        try {
            return this.subClass.getMethod(this.methodName, this.argsType);
        } catch (NoSuchMethodException e) {
            throw new ProxyException(e.getMessage());
        }
    }

    public Method getProxyMethod() {
        try {
            return this.subClass.getMethod(this.methodName + Const.SUBCLASS_INVOKE_SUPER_SUFFIX, this.argsType);
        } catch (NoSuchMethodException e) {
            throw new ProxyException(e.getMessage());
        }
    }

    public Object invokeSuper(Object obj, Object[] objArr) {
        return ((EnhancerInterface) obj).executeSuperMethod_Enhancer(this.methodName, this.argsType, objArr);
    }
}

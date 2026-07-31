package com.luajava;

import com.android.cglib.proxy.MethodInterceptor;
import com.android.cglib.proxy.MethodProxy;
import com.androlua.LuaContext;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class LuaAbstractMethodInterceptor implements MethodInterceptor {
    private final LuaContext a;
    private LuaObject b;

    public LuaAbstractMethodInterceptor(LuaObject luaObject) {
        this.b = luaObject;
        this.a = luaObject.getLuaState().getContext();
    }

    @Override // com.android.cglib.proxy.MethodInterceptor
    public Object intercept(Object obj, Object[] objArr, MethodProxy methodProxy) {
        synchronized (this.b.b) {
            Method originalMethod = methodProxy.getOriginalMethod();
            String name = originalMethod.getName();
            LuaObject field = this.b.isFunction() ? this.b : this.b.getField(name);
            Class<?> returnType = originalMethod.getReturnType();
            Object objCall = null;
            if (field.isNil()) {
                if (!returnType.equals(Boolean.TYPE) && !returnType.equals(Boolean.class)) {
                    if (!returnType.isPrimitive()) {
                        if (Number.class.isAssignableFrom(returnType)) {
                        }
                    }
                    return 0;
                }
                objCall = Boolean.FALSE;
                return objCall;
            }
            try {
                if (returnType.equals(Void.class) || returnType.equals(Void.TYPE)) {
                    field.call(objArr);
                } else {
                    objCall = field.call(objArr);
                    if (objCall != null && (objCall instanceof Double)) {
                        objCall = LuaState.convertLuaNumber((Double) objCall, returnType);
                    }
                }
            } catch (LuaException e) {
                this.a.sendError(name, e);
            }
            if (objCall == null) {
                if (returnType.equals(Boolean.TYPE) || returnType.equals(Boolean.class)) {
                    objCall = Boolean.FALSE;
                } else if (returnType.isPrimitive() || Number.class.isAssignableFrom(returnType)) {
                    return 0;
                }
            }
            return objCall;
        }
    }
}

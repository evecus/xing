package com.androlua;

import com.android.cglib.proxy.Enhancer;
import com.android.cglib.proxy.EnhancerInterface;
import com.android.cglib.proxy.MethodFilter;
import com.android.cglib.proxy.MethodInterceptor;

/* JADX INFO: loaded from: classes.dex */
public class LuaEnhancer {
    private Enhancer mEnhancer;

    public LuaEnhancer(Class<?> cls) {
        Enhancer enhancer = new Enhancer(LuaApplication.getInstance());
        this.mEnhancer = enhancer;
        enhancer.setSuperclass(cls);
    }

    public LuaEnhancer(String str) {
        this(Class.forName(str));
    }

    public Class<?> create() {
        try {
            return this.mEnhancer.create();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class<?> create(MethodFilter methodFilter) {
        try {
            this.mEnhancer.setMethodFilter(methodFilter);
            return this.mEnhancer.create();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setInterceptor(EnhancerInterface enhancerInterface, MethodInterceptor methodInterceptor) {
        enhancerInterface.setMethodInterceptor_Enhancer(methodInterceptor);
    }
}

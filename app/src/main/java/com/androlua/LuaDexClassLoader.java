package com.androlua;

import dalvik.system.DexClassLoader;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class LuaDexClassLoader extends DexClassLoader {
    private HashMap<String, Class<?>> classCache;
    private String mDexPath;

    public LuaDexClassLoader(String str, String str2, String str3, ClassLoader classLoader) {
        super(str, str2, str3, classLoader);
        this.classCache = new HashMap<>();
        this.mDexPath = str;
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public Class<?> findClass(String str) {
        Class<?> cls = this.classCache.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> clsFindClass = super.findClass(str);
        this.classCache.put(str, clsFindClass);
        return clsFindClass;
    }

    public String getDexPath() {
        return this.mDexPath;
    }
}

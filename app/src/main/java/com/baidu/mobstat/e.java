package com.baidu.mobstat;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class e {

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }

        public a(Throwable th) {
            super(th);
        }
    }

    public static String a(byte[] bArr) {
        byte[] bArrA = w.a();
        return new String(s.b(bArrA, bArrA, bArr));
    }

    public static Method a(Class<?> cls, String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}

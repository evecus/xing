package com.baidu.mobstat;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class cv {
    private static Object a;
    private static Class<?> b;
    private static Method c;

    static {
        c = null;
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            b = cls;
            a = cls.newInstance();
            c = b.getMethod("getOAID", Context.class);
        } catch (Exception e) {
        }
    }

    public static String a(Context context) {
        return a(context, c);
    }

    private static String a(Context context, Method method) {
        Object obj = a;
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object objInvoke = method.invoke(obj, context);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Exception e) {
            Log.e("IdentifierManager", "invoke exception!", e);
            return null;
        }
    }
}

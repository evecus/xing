package com.android.cglib.proxy;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public interface MethodFilter {
    boolean filter(Method method, String str);
}

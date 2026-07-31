package com.baidu.android.common.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class CommonParam {
    private static final boolean a = false;
    private static final String b = CommonParam.class.getSimpleName();

    @Deprecated
    public static String getCUID(Context context) {
        return DeviceId.getCUID(context);
    }
}

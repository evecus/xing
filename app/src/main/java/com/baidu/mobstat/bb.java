package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class bb {
    public static int a(Context context, float f) {
        if (context == null) {
            return 0;
        }
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static float b(Context context, float f) {
        if (context == null) {
            return 0.0f;
        }
        return (f / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static int c(Context context, float f) {
        if (context == null) {
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}

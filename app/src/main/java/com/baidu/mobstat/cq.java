package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.common.util.DeviceId;
import com.baidu.mobstat.util.CuidUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class cq {
    public static String a(long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        return simpleDateFormat.format(date);
    }

    public static String a(Context context) {
        String cuid = "";
        if (!cn.a().a(false)) {
            return "";
        }
        try {
            cuid = DeviceId.getCUID(context);
        } catch (Throwable th) {
        }
        return TextUtils.isEmpty(cuid) ? CuidUtil.getCuid3(context) : cuid;
    }

    public static String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return str.length() > i ? str.substring(0, i) : str;
    }

    public static HashMap<String, String> a(Map<String, String> map) {
        if (map != null) {
            return new HashMap<>(map);
        }
        return null;
    }

    public static boolean a(Class<?> cls, String str) {
        Class<?> cls2;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String methodName = stackTraceElement.getMethodName();
            if (!TextUtils.isEmpty(methodName) && cls != null && methodName.equals(str)) {
                try {
                    cls2 = Class.forName(stackTraceElement.getClassName());
                } catch (Throwable th) {
                    cls2 = null;
                }
                if (cls2 != null && cls.isAssignableFrom(cls2)) {
                    return true;
                }
            }
        }
        return false;
    }
}

package com.baidu.mobstat.util;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.helios.OnGetIdResultCallback;
import com.baidu.mobstat.cn;

/* JADX INFO: loaded from: classes.dex */
public class CuidUtil {

    /* JADX INFO: renamed from: com.baidu.mobstat.util.CuidUtil$1, reason: invalid class name */
    static class AnonymousClass1 implements OnGetIdResultCallback<String> {
        AnonymousClass1() {
        }

        public void onError(int i, Throwable th, Bundle bundle) {
        }

        public void onResult(String str, Bundle bundle) {
        }
    }

    /* JADX INFO: renamed from: com.baidu.mobstat.util.CuidUtil$2, reason: invalid class name */
    static class AnonymousClass2 implements OnGetIdResultCallback<String> {
        AnonymousClass2() {
        }

        public void onError(int i, Throwable th, Bundle bundle) {
        }

        public void onResult(String str, Bundle bundle) {
        }
    }

    /* JADX INFO: renamed from: com.baidu.mobstat.util.CuidUtil$3, reason: invalid class name */
    static class AnonymousClass3 implements OnGetIdResultCallback<String> {
        AnonymousClass3() {
        }

        public void onError(int i, Throwable th, Bundle bundle) {
        }

        public void onResult(String str, Bundle bundle) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            cn.a().a(str);
        }
    }

    public static String getCuid3(Context context) {
        return "";
    }

    public static String getGaid(Context context) {
        return "";
    }

    public static String getIid(Context context) {
        return "";
    }

    public static String getOaid(Context context) {
        return "";
    }

    public static String getSsaid(Context context) {
        return "";
    }
}

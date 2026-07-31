package com.baidu.android.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import androidx.appcompat.widget.ActivityChooserModel;
import com.baidu.mobstat.am;

/* JADX INFO: loaded from: classes.dex */
public final class Util {
    private static final boolean a = false;
    private static final String b = "Util";

    private Util() {
    }

    public static boolean hasOtherServiceRuninMyPid(Context context, String str) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getApplicationContext().getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY)).getRunningServices(100)) {
            if (runningServiceInfo.pid == Process.myPid() && !TextUtils.equals(runningServiceInfo.service.getClassName(), str)) {
                return true;
            }
        }
        return false;
    }

    public static String toHexString(byte[] bArr, String str, boolean z) {
        return am.a(bArr, str, z);
    }

    public static String toMd5(byte[] bArr, boolean z) {
        return am.a(bArr, z);
    }
}

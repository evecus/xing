package com.baidu.android.common.util;

import android.content.Context;
import com.baidu.mobstat.av;

/* JADX INFO: loaded from: classes.dex */
public class InternalDeviceIdApis {

    public static class TargetPackageCuidV270Info {
        public String iscChannelCuid;
        public String upcChannelCuid;
    }

    private static av a(Context context) {
        DeviceId.getCUID(context);
        return DeviceId.a(context).a();
    }

    public static String getSelfC270Ids(Context context) {
        av.a aVarA = a(context).a();
        if (aVarA != null) {
            return aVarA.k();
        }
        return null;
    }

    public static TargetPackageCuidV270Info getTargetPackageCuid270Info(Context context, String str) {
        av avVarA = a(context);
        TargetPackageCuidV270Info targetPackageCuidV270Info = new TargetPackageCuidV270Info();
        av.a aVarA = avVarA.a(str, "upc");
        if (aVarA != null) {
            targetPackageCuidV270Info.upcChannelCuid = aVarA.k();
        }
        av.a aVarA2 = avVarA.a(str, "isc");
        if (aVarA2 != null) {
            targetPackageCuidV270Info.iscChannelCuid = aVarA2.k();
        }
        return targetPackageCuidV270Info;
    }
}

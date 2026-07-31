package roam.a.a.g.a.a.b;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.baidu.mobstat.PropertyType;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static boolean a(Context context) {
        int length;
        try {
            if (!Build.HARDWARE.contains("goldfish") && !Build.PRODUCT.contains("sdk") && !Build.FINGERPRINT.contains("generic")) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    String deviceId = telephonyManager.getDeviceId();
                    if (deviceId != null && (length = deviceId.length()) != 0) {
                        for (int i = 0; i < length; i++) {
                            if (Character.isWhitespace(deviceId.charAt(i)) || deviceId.charAt(i) == '0') {
                            }
                        }
                    }
                }
                return roam.a.a.a.b.a.o(Settings.Secure.getString(context.getContentResolver(), "android_id"));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean b() {
        for (int i = 0; i < 5; i++) {
            try {
                if (new File(new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"}[i] + "su").exists()) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public static String c() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, "ro.kernel.qemu", PropertyType.UID_PROPERTRY);
        } catch (Exception e) {
            return PropertyType.UID_PROPERTRY;
        }
    }
}

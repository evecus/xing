package roam.a.a.f.j;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.baidu.mobstat.Config;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static a d;
    public String a;
    public String b;
    public String c;

    public a(Context context) {
        try {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
                e(telephonyManager.getDeviceId());
                String subscriberId = telephonyManager.getSubscriberId();
                if (subscriberId != null) {
                    subscriberId = (subscriberId + Config.NULL_DEVICE_ID).substring(0, 15);
                }
                this.a = subscriberId;
                String macAddress = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
                this.c = macAddress;
                if (!TextUtils.isEmpty(macAddress)) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (!TextUtils.isEmpty(this.c)) {
                    return;
                }
            }
            this.c = "00:00:00:00:00:00";
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.c)) {
                this.c = "00:00:00:00:00:00";
            }
            throw th;
        }
    }

    public static a a(Context context) {
        if (d == null) {
            d = new a(context);
        }
        return d;
    }

    public static b c(Context context) {
        b bVar = b.NONE;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 0) {
                return (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) ? bVar : b.WIFI;
            }
            int subtype = activeNetworkInfo.getSubtype();
            b[] bVarArrValues = b.values();
            for (int i = 0; i < 15; i++) {
                b bVar2 = bVarArrValues[i];
                if (bVar2.a == subtype) {
                    return bVar2;
                }
            }
            return bVar;
        } catch (Exception e) {
            return bVar;
        }
    }

    public static String f(Context context) {
        a aVarA = a(context);
        String strJ = roam.a.b.a.a.a.j(aVarA.d(), "|");
        String strB = aVarA.b();
        if (TextUtils.isEmpty(strB)) {
            strB = Config.NULL_DEVICE_ID;
        }
        return roam.a.b.a.a.a.j(strJ, strB).substring(0, 8);
    }

    public final String b() {
        if (TextUtils.isEmpty(this.a)) {
            this.a = Config.NULL_DEVICE_ID;
        }
        return this.a;
    }

    public final String d() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = Config.NULL_DEVICE_ID;
        }
        return this.b;
    }

    public final void e(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                if (b < 48 || b > 57) {
                    bytes[i] = 48;
                }
            }
            str = (new String(bytes) + Config.NULL_DEVICE_ID).substring(0, 15);
        }
        this.b = str;
    }
}

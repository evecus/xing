package roam.a.a.g.a.a.b;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.PropertyType;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static a a = new a();

    public static String a() {
        Enumeration<NetworkInterface> networkInterfaces;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (Throwable th) {
        }
        while (networkInterfaces.hasMoreElements()) {
            Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddressNextElement = inetAddresses.nextElement();
                if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                    return inetAddressNextElement.getHostAddress().toString();
                }
                return "";
            }
        }
        return "";
    }

    public static boolean b(Context context, String str) {
        return !(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
    }

    public static String c() {
        long availableBlocks;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            availableBlocks = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            availableBlocks = 0;
        }
        return String.valueOf(availableBlocks);
    }

    public static String d() {
        long availableBlocks = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                File file = null;
                try {
                    file = (File) Environment.class.getMethod(new String(roam.a.a.g.a.a.a.a.a.a("Z2V0RXh0ZXJuYWxTdG9yYWdlRGlyZWN0b3J5")), new Class[0]).invoke(null, new Object[0]);
                } catch (Exception e) {
                }
                StatFs statFs = new StatFs(file.getPath());
                availableBlocks = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            }
        } catch (Throwable th) {
        }
        return String.valueOf(availableBlocks);
    }

    public static String e(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1 ? "1" : PropertyType.UID_PROPERTRY;
    }

    public static String f(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            int i = audioManager.getRingerMode() == 0 ? 1 : 0;
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put(NotificationCompat.CATEGORY_CALL, String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put(NotificationCompat.CATEGORY_ALARM, String.valueOf(streamVolume5));
        } catch (Throwable th) {
        }
        return jSONObject.toString();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|47|3|(2:41|4)|(5:39|5|(3:7|8|(6:10|43|11|49|14|55))|53|18)|45|34|37|(1:(0))) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String g() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L37
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L37
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L33
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L33
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L31
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r0 = r0.split(r3, r4)     // Catch: java.lang.Throwable -> L31
            if (r0 == 0) goto L2b
            int r3 = r0.length     // Catch: java.lang.Throwable -> L31
            r4 = 1
            if (r3 <= r4) goto L2b
            r0 = r0[r4]
            r1.close()     // Catch: java.lang.Throwable -> L24
            goto L25
        L24:
            r1 = move-exception
        L25:
            r2.close()     // Catch: java.lang.Throwable -> L29
            goto L49
        L29:
            r1 = move-exception
            goto L49
        L2b:
            r1.close()     // Catch: java.lang.Throwable -> L2f
            goto L42
        L2f:
            r0 = move-exception
            goto L42
        L31:
            r0 = move-exception
            goto L35
        L33:
            r2 = move-exception
            r2 = r0
        L35:
            r0 = r1
            goto L39
        L37:
            r1 = move-exception
            r2 = r0
        L39:
            if (r0 == 0) goto L40
            r0.close()     // Catch: java.lang.Throwable -> L3f
            goto L40
        L3f:
            r0 = move-exception
        L40:
            if (r2 == 0) goto L47
        L42:
            r2.close()     // Catch: java.lang.Throwable -> L46
            goto L47
        L46:
            r0 = move-exception
        L47:
            java.lang.String r0 = ""
        L49:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.g.a.a.b.a.g():java.lang.String");
    }

    public static String h() {
        String string;
        try {
            string = Locale.getDefault().toString();
        } catch (Throwable th) {
            string = "";
        }
        return string == null ? "" : string;
    }

    public static String i() {
        String displayName;
        try {
            displayName = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable th) {
            displayName = "";
        }
        return displayName == null ? "" : displayName;
    }

    public static String j() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            StringBuilder sb = new StringBuilder();
            sb.append(jCurrentTimeMillis - (jCurrentTimeMillis % 1000));
            return sb.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    public static String k(Context context) {
        WifiManager wifiManager;
        if (b(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            wifiManager = (WifiManager) context.getSystemService("wifi");
        } catch (Throwable th) {
        }
        String bssid = wifiManager.isWifiEnabled() ? wifiManager.getConnectionInfo().getBSSID() : "";
        return bssid == null ? "" : bssid;
    }

    public static String l(Context context) {
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
            int intExtra2 = intentRegisterReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
            boolean z = intExtra2 == 2 || intExtra2 == 5;
            StringBuilder sb = new StringBuilder();
            sb.append(z ? "1" : PropertyType.UID_PROPERTRY);
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(intExtra);
            return sb.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    public static String m(Context context) {
        if (b(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return "WIFI";
                }
                if (activeNetworkInfo.getType() == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    return (subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7 || subtype == 11) ? "2G" : (subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15) ? "3G" : subtype == 13 ? "4G" : "UNKNOW";
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String n() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list == null) {
                return Config.DEF_MAC_ID;
            }
            for (NetworkInterface networkInterface : list) {
                if (networkInterface != null && networkInterface.getName() != null && networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return Config.DEF_MAC_ID;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Integer.valueOf(b & ExifInterface.MARKER)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return Config.DEF_MAC_ID;
        } catch (Throwable th) {
            return Config.DEF_MAC_ID;
        }
    }
}

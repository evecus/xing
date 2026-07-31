package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.baidu.mobstat.cf;
import com.baidu.mobstat.cl;
import com.baidu.mobstat.util.CuidUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class cp {
    private static String a = null;
    private static String b = null;
    private static String c = null;
    private static final Pattern d = Pattern.compile("\\s*|\t|\r|\n");
    private static String e = "";

    public static String a() throws Throwable {
        InputStreamReader inputStreamReader;
        StringBuffer stringBuffer = new StringBuffer();
        InputStreamReader inputStreamReader2 = null;
        try {
            char[] cArr = new char[20];
            inputStreamReader = new InputStreamReader(new FileInputStream("/sys/class/net/eth0/address"));
            while (true) {
                try {
                    int i = inputStreamReader.read(cArr);
                    if (i == -1) {
                        break;
                    }
                    if (i != 20 || cArr[19] == '\r') {
                        for (int i2 = 0; i2 < i; i2++) {
                            char c2 = cArr[i2];
                            if (c2 != '\r') {
                                stringBuffer.append(c2);
                            }
                        }
                    } else {
                        System.out.print(cArr);
                    }
                } catch (Exception e2) {
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (Exception e3) {
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    inputStreamReader2 = inputStreamReader;
                    if (inputStreamReader2 != null) {
                        try {
                            inputStreamReader2.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            }
            String strReplaceAll = stringBuffer.toString().trim().replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
            try {
                inputStreamReader.close();
            } catch (Exception e5) {
            }
            return strReplaceAll;
        } catch (Exception e6) {
            inputStreamReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String a(byte b2) {
        return ("00" + Integer.toHexString(b2) + Config.TRACE_TODAY_VISIT_SPLIT).substring(r2.length() - 3);
    }

    public static String a(int i, Context context) {
        return cf.b.c(i, CuidUtil.getOaid(context).getBytes());
    }

    public static String a(Context context) {
        String str = e;
        if (str != null && !"".equals(str)) {
            return e;
        }
        String strReplaceAll = d.matcher(cq.a(context)).replaceAll("");
        if (strReplaceAll == null || "".equals(strReplaceAll)) {
            return strReplaceAll;
        }
        e = strReplaceAll;
        return strReplaceAll;
    }

    public static String a(Context context, int i) {
        String strK = k(context);
        return TextUtils.isEmpty(strK) ? "" : cf.b.c(i, strK.getBytes());
    }

    public static String a(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return "";
            }
            Object obj = applicationInfo.metaData != null ? applicationInfo.metaData.get(str) : null;
            if (obj != null) {
                return obj.toString();
            }
            bv.c().a("can't find information in AndroidManifest.xml for key " + str);
            return "";
        } catch (Exception e2) {
            return "";
        }
    }

    private static String a(String str) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        Process processExec;
        String line = null;
        try {
            processExec = Runtime.getRuntime().exec("getprop " + str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()), 1024);
                try {
                    line = bufferedReader.readLine();
                    try {
                        bufferedReader.close();
                    } catch (Exception e2) {
                    }
                } catch (Exception e3) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e4) {
                        }
                    }
                    if (processExec != null) {
                    }
                    return line;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (processExec == null) {
                        throw th;
                    }
                    processExec.destroy();
                    throw th;
                }
            } catch (Exception e6) {
                bufferedReader = null;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
            }
        } catch (Exception e7) {
            processExec = null;
            bufferedReader = null;
        } catch (Throwable th4) {
            bufferedReader = null;
            th = th4;
            processExec = null;
        }
        if (processExec != null) {
            processExec.destroy();
        }
        return line;
    }

    public static String b() throws Throwable {
        String str = c;
        if (str != null) {
            return str;
        }
        String str2 = !TextUtils.isEmpty(a("ro.miui.ui.version.name")) ? "miui" : !TextUtils.isEmpty(a("ro.build.version.opporom")) ? "coloros" : !TextUtils.isEmpty(a("ro.build.version.emui")) ? "emui" : !TextUtils.isEmpty(a("ro.vivo.os.version")) ? "funtouch" : !TextUtils.isEmpty(a("ro.smartisan.version")) ? "smartisan" : "";
        if (TextUtils.isEmpty(str2)) {
            String strA = a("ro.build.display.id");
            if (!TextUtils.isEmpty(strA) && strA.contains("Flyme")) {
                str2 = "flyme";
            }
        }
        c = str2;
        return str2;
    }

    public static String b(int i, final Context context) {
        String strV = cj.a().v(context);
        if (TextUtils.isEmpty(strV)) {
            strV = cj.a().w(context);
            ct.a().a(context, new cs() { // from class: com.baidu.mobstat.cp.1
                @Override // com.baidu.mobstat.cs
                public void a(String str) {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    cj.a().o(context, str);
                }
            });
        }
        if (TextUtils.isEmpty(strV)) {
            strV = "";
        }
        return cf.b.c(i, strV.getBytes());
    }

    public static String b(Context context) {
        return cl.a.a(a(context).getBytes()).toUpperCase(Locale.US);
    }

    private static String b(Context context, String str) {
        int iLastIndexOf;
        int i;
        if (str != null && (iLastIndexOf = str.lastIndexOf(58)) > 0 && (i = iLastIndexOf + 1) < str.length()) {
            return str.substring(i);
        }
        return null;
    }

    public static int c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = e(context);
        } catch (Exception e2) {
        }
        return displayMetrics.widthPixels;
    }

    private static String c() {
        try {
        } catch (Throwable th) {
        }
        for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress == null) {
                    return "";
                }
                StringBuilder sb = new StringBuilder();
                for (byte b2 : hardwareAddress) {
                    sb.append(String.format("%02x:", Byte.valueOf(b2)));
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            return "";
        }
        return "";
    }

    public static String c(int i, Context context) {
        return cf.b.c(i, CuidUtil.getIid(context).getBytes());
    }

    private static String c(Context context, String str) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        String str2 = applicationInfo.processName;
        if (str2 == null || str2.equals(str)) {
            return null;
        }
        return str;
    }

    public static int d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = e(context);
        } catch (Exception e2) {
        }
        return displayMetrics.heightPixels;
    }

    public static String d(int i, Context context) {
        return cf.b.c(i, CuidUtil.getGaid(context).getBytes());
    }

    public static DisplayMetrics e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String e(int i, Context context) {
        return cf.b.c(i, CuidUtil.getCuid3(context).getBytes());
    }

    public static int f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e2) {
            return 1;
        }
    }

    public static String f(int i, Context context) {
        return cf.b.c(i, CuidUtil.getSsaid(context).getBytes());
    }

    public static String g(int i, Context context) {
        if (!cn.a().b()) {
            return "";
        }
        String strJ = j(context);
        return TextUtils.isEmpty(strJ) ? "" : cf.b.c(i, strJ.getBytes());
    }

    public static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            return "";
        }
    }

    public static String h(int i, Context context) {
        if (!cn.a().b()) {
            return "";
        }
        String strI = i(i, context);
        String strC = !TextUtils.isEmpty(strI) ? cf.b.c(i, strI.getBytes()) : null;
        return TextUtils.isEmpty(strC) ? "" : strC;
    }

    public static String h(Context context) {
        try {
            return (String) context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
        } catch (Exception e2) {
            return "";
        }
    }

    public static String i(int i, Context context) throws Throwable {
        if (!cn.a().b()) {
            return "";
        }
        String strA = a();
        if (TextUtils.isEmpty(strA)) {
            strA = j(i, context);
        }
        return TextUtils.isEmpty(strA) ? "" : strA;
    }

    public static String i(Context context) {
        return !cn.a().b() ? "" : c();
    }

    public static String j(int i, Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] hardwareAddress = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        if (!inetAddressNextElement.isAnyLocalAddress() && (inetAddressNextElement instanceof Inet4Address) && !inetAddressNextElement.isLoopbackAddress()) {
                            if (!inetAddressNextElement.isSiteLocalAddress()) {
                                if (!inetAddressNextElement.isLinkLocalAddress()) {
                                    hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                                    break;
                                }
                            } else {
                                hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
        }
        if (hardwareAddress == null) {
            String strG = g(i, context);
            return strG != null ? strG.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "") : strG;
        }
        for (byte b2 : hardwareAddress) {
            stringBuffer.append(a(b2));
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1).replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
    }

    public static String j(Context context) {
        cn.a().b();
        return "";
    }

    public static String k(int i, Context context) {
        if (!cn.a().b()) {
            return "";
        }
        String strL = l(context);
        return TextUtils.isEmpty(strL) ? "" : cf.b.c(i, strL.getBytes());
    }

    public static String k(Context context) {
        return "";
    }

    public static String l(int i, Context context) {
        if (!cn.a().b()) {
            return "";
        }
        String strM = m(context);
        return TextUtils.isEmpty(strM) ? "" : cf.b.d(i, strM.getBytes());
    }

    public static String l(Context context) {
        cn.a().b();
        return "";
    }

    public static String m(int i, Context context) {
        String strQ = q(context);
        if (TextUtils.isEmpty(strQ)) {
            return "";
        }
        try {
            return cf.b.c(i, strQ.getBytes());
        } catch (Exception e2) {
            return "";
        }
    }

    public static String m(Context context) {
        return "";
    }

    public static boolean n(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo == null || !networkInfo.isAvailable()) {
                return false;
            }
            return networkInfo.isConnected();
        } catch (Exception e2) {
            return false;
        }
    }

    public static String o(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "";
            }
            String typeName = activeNetworkInfo.getTypeName();
            return (typeName.equals("WIFI") || activeNetworkInfo.getSubtypeName() == null) ? typeName : activeNetworkInfo.getSubtypeName();
        } catch (Exception e2) {
            return "";
        }
    }

    public static boolean p(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (!ch.e(context, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isAvailable();
        } catch (Exception e2) {
            return false;
        }
    }

    public static String q(Context context) {
        return context != null ? context.getPackageName() : "";
    }

    public static String r(Context context) {
        String str = b;
        if (str == null) {
            String strU = u(context);
            String strB = b(context, strU);
            if (TextUtils.isEmpty(strB)) {
                strB = c(context, strU);
            }
            str = strB == null ? "" : strB;
            b = str;
        }
        return str;
    }

    public static String s(Context context) {
        PackageInfo packageInfo;
        ServiceInfo[] serviceInfoArr;
        String str;
        String strU = u(context);
        if (strU == null) {
            return "";
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
        } catch (Exception e2) {
            packageInfo = null;
        }
        if (packageInfo == null || (serviceInfoArr = packageInfo.services) == null) {
            return "";
        }
        int length = serviceInfoArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str = "";
                break;
            }
            ServiceInfo serviceInfo = serviceInfoArr[i];
            if (strU.equals(serviceInfo.processName)) {
                str = serviceInfo.name;
                break;
            }
            i++;
        }
        return str == null ? "" : str;
    }

    public static boolean t(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        } catch (Exception e2) {
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        r0 = r2.processName;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String u(android.content.Context r5) {
        /*
            java.lang.String r0 = com.baidu.mobstat.cp.a
            if (r0 != 0) goto L39
            java.lang.String r1 = "activity"
            java.lang.Object r5 = r5.getSystemService(r1)     // Catch: java.lang.Exception -> L31
            android.app.ActivityManager r5 = (android.app.ActivityManager) r5     // Catch: java.lang.Exception -> L31
            java.util.List r5 = r5.getRunningAppProcesses()     // Catch: java.lang.Exception -> L31
            r1 = 0
        L11:
            if (r5 == 0) goto L30
            int r2 = r5.size()     // Catch: java.lang.Exception -> L31
            if (r1 >= r2) goto L30
            java.lang.Object r2 = r5.get(r1)     // Catch: java.lang.Exception -> L31
            android.app.ActivityManager$RunningAppProcessInfo r2 = (android.app.ActivityManager.RunningAppProcessInfo) r2     // Catch: java.lang.Exception -> L31
            if (r2 == 0) goto L2d
            int r3 = r2.pid     // Catch: java.lang.Exception -> L31
            int r4 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L31
            if (r3 != r4) goto L2d
            java.lang.String r5 = r2.processName     // Catch: java.lang.Exception -> L31
            r0 = r5
            goto L30
        L2d:
            int r1 = r1 + 1
            goto L11
        L30:
            goto L32
        L31:
            r5 = move-exception
        L32:
            if (r0 != 0) goto L37
            java.lang.String r5 = ""
            r0 = r5
        L37:
            com.baidu.mobstat.cp.a = r0
        L39:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.cp.u(android.content.Context):java.lang.String");
    }
}

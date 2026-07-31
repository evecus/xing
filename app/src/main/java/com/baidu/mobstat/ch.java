package com.baidu.mobstat;

import android.content.Context;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: loaded from: classes.dex */
public final class ch {
    private static final Proxy a = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80));
    private static final Proxy b = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80));

    public static String a(Context context, String str) {
        FileInputStream fileInputStreamOpenFileInput = null;
        try {
            fileInputStreamOpenFileInput = context.openFileInput(str);
            byte[] bArrA = a(fileInputStreamOpenFileInput);
            if (bArrA != null) {
                String str2 = new String(bArrA, "utf-8");
                cm.a(fileInputStreamOpenFileInput);
                return str2;
            }
        } catch (Exception e) {
        } catch (Throwable th) {
            cm.a(fileInputStreamOpenFileInput);
            throw th;
        }
        cm.a(fileInputStreamOpenFileInput);
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.net.HttpURLConnection a(android.content.Context r2, java.lang.String r3, int r4, int r5) throws java.io.IOException {
        /*
            java.net.URL r0 = new java.net.URL
            r0.<init>(r3)
            java.lang.String r3 = "connectivity"
            java.lang.Object r2 = r2.getSystemService(r3)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            r3 = 0
            android.net.NetworkInfo r3 = r2.getNetworkInfo(r3)
            r1 = 1
            android.net.NetworkInfo r2 = r2.getNetworkInfo(r1)
            if (r2 == 0) goto L27
            boolean r2 = r2.isAvailable()
            if (r2 == 0) goto L27
            java.net.URLConnection r2 = r0.openConnection()
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
            goto L70
        L27:
            if (r3 == 0) goto L6f
            boolean r2 = r3.isAvailable()
            if (r2 == 0) goto L6f
            java.lang.String r2 = r3.getExtraInfo()
            if (r2 == 0) goto L3a
            java.lang.String r2 = r2.toLowerCase()
            goto L3c
        L3a:
            java.lang.String r2 = ""
        L3c:
            java.lang.String r3 = "cmwap"
            boolean r3 = r2.startsWith(r3)
            if (r3 != 0) goto L66
            java.lang.String r3 = "uniwap"
            boolean r3 = r2.startsWith(r3)
            if (r3 != 0) goto L66
            java.lang.String r3 = "3gwap"
            boolean r3 = r2.startsWith(r3)
            if (r3 == 0) goto L55
            goto L66
        L55:
            java.lang.String r3 = "ctwap"
            boolean r2 = r2.startsWith(r3)
            if (r2 == 0) goto L6f
            java.net.Proxy r2 = com.baidu.mobstat.ch.b
            java.net.URLConnection r2 = r0.openConnection(r2)
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
            goto L70
        L66:
            java.net.Proxy r2 = com.baidu.mobstat.ch.a
            java.net.URLConnection r2 = r0.openConnection(r2)
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
            goto L70
        L6f:
            r2 = 0
        L70:
            if (r2 != 0) goto L78
            java.net.URLConnection r2 = r0.openConnection()
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
        L78:
            r2.setConnectTimeout(r4)
            r2.setReadTimeout(r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.ch.a(android.content.Context, java.lang.String, int, int):java.net.HttpURLConnection");
    }

    public static void a(Context context, String str, String str2, boolean z) {
        if (context == null) {
            return;
        }
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            fileOutputStreamOpenFileOutput = context.openFileOutput(str, z ? 32768 : 0);
            cm.a(new ByteArrayInputStream(str2.getBytes("utf-8")), fileOutputStreamOpenFileOutput);
        } catch (Exception e) {
        } catch (Throwable th) {
            cm.a(fileOutputStreamOpenFileOutput);
            throw th;
        }
        cm.a(fileOutputStreamOpenFileOutput);
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (cm.a(inputStream, byteArrayOutputStream)) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public static boolean b(Context context, String str) {
        return context.deleteFile(str);
    }

    public static boolean c(Context context, String str) {
        return context.getFileStreamPath(str).exists();
    }

    public static HttpURLConnection d(Context context, String str) throws IOException {
        return a(context, str, 50000, 50000);
    }

    public static boolean e(Context context, String str) {
        boolean z = false;
        try {
            if (context.checkCallingOrSelfPermission(str) == 0) {
                z = true;
            }
        } catch (Exception e) {
        }
        if (!z) {
            bv.c().b("[WARNING] not have permission " + str + ", please add it in AndroidManifest.xml according our developer doc");
        }
        return z;
    }
}

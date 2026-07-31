package com.androlua.util;

import android.util.Log;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class RootUtil {
    private static final String TAG = "linin.root";
    private static boolean mHaveRoot;

    /* JADX WARN: Can't wrap try/catch for region: R(16:0|2|66|3|64|4|68|5|(5:6|(1:8)(1:70)|62|35|39)|9|56|10|62|35|39|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0063, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0064, code lost:
    
        r7.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008c, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008d, code lost:
    
        r7.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String execRootCmd(java.lang.String r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "\n"
            java.lang.String r1 = ""
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L77
            java.lang.String r4 = "su"
            java.lang.Process r3 = r3.exec(r4)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L77
            java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L77
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L77
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L77
            java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L70
            java.io.InputStream r6 = r3.getInputStream()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L70
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L70
            java.lang.String r2 = "linin.root"
            android.util.Log.i(r2, r7)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.<init>()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.append(r7)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.append(r0)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            java.lang.String r7 = r2.toString()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r4.writeBytes(r7)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r4.flush()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            java.lang.String r7 = "exit\n"
            r4.writeBytes(r7)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r4.flush()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
        L43:
            java.lang.String r7 = r5.readLine()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            if (r7 == 0) goto L5c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.<init>()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.append(r1)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.append(r7)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.append(r0)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            goto L43
        L5c:
            r3.waitFor()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r4.close()     // Catch: java.io.IOException -> L63
            goto L88
        L63:
            r7 = move-exception
            r7.printStackTrace()
            goto L88
        L68:
            r7 = move-exception
            goto L6e
        L6a:
            r7 = move-exception
            goto L72
        L6c:
            r7 = move-exception
            r5 = r2
        L6e:
            r2 = r4
            goto L92
        L70:
            r7 = move-exception
            r5 = r2
        L72:
            r2 = r4
            goto L79
        L74:
            r7 = move-exception
            r5 = r2
            goto L92
        L77:
            r7 = move-exception
            r5 = r2
        L79:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L91
            if (r2 == 0) goto L86
            r2.close()     // Catch: java.io.IOException -> L82
            goto L86
        L82:
            r7 = move-exception
            r7.printStackTrace()
        L86:
            if (r5 == 0) goto L90
        L88:
            r5.close()     // Catch: java.io.IOException -> L8c
            goto L90
        L8c:
            r7 = move-exception
            r7.printStackTrace()
        L90:
            return r1
        L91:
            r7 = move-exception
        L92:
            if (r2 == 0) goto L9c
            r2.close()     // Catch: java.io.IOException -> L98
            goto L9c
        L98:
            r0 = move-exception
            r0.printStackTrace()
        L9c:
            if (r5 == 0) goto La6
            r5.close()     // Catch: java.io.IOException -> La2
            goto La6
        La2:
            r0 = move-exception
            r0.printStackTrace()
        La6:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.util.RootUtil.execRootCmd(java.lang.String):java.lang.String");
    }

    public static int execRootCmdSilent(String str) throws Throwable {
        DataOutputStream dataOutputStream = null;
        try {
            try {
                Process processExec = Runtime.getRuntime().exec("su");
                DataOutputStream dataOutputStream2 = new DataOutputStream(processExec.getOutputStream());
                try {
                    Log.i(TAG, str);
                    dataOutputStream2.writeBytes(str + "\n");
                    dataOutputStream2.flush();
                    dataOutputStream2.writeBytes("exit\n");
                    dataOutputStream2.flush();
                    processExec.waitFor();
                    int iExitValue = processExec.exitValue();
                    try {
                        dataOutputStream2.close();
                        return iExitValue;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return iExitValue;
                    }
                } catch (Exception e2) {
                    e = e2;
                    dataOutputStream = dataOutputStream2;
                    e.printStackTrace();
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return -1;
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }

    public static boolean haveRoot() {
        String str;
        if (mHaveRoot) {
            str = "mHaveRoot = true, have root!";
        } else {
            if (execRootCmdSilent("echo test") != -1) {
                Log.i(TAG, "have root!");
                mHaveRoot = true;
                return mHaveRoot;
            }
            str = "not root!";
        }
        Log.i(TAG, str);
        return mHaveRoot;
    }

    public static boolean root() {
        try {
            Runtime.getRuntime().exec(new String[]{"/system/bin/su", "-c", "chmod 777 /dev/graphics/fb0"});
            Log.i(TAG, "root success!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "root fail!");
            return false;
        }
    }
}

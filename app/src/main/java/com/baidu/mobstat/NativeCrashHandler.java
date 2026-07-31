package com.baidu.mobstat;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class NativeCrashHandler {
    private static boolean a;
    private static Context b;

    static {
        a = false;
        try {
            System.loadLibrary("crash_analysis");
            a = true;
        } catch (Throwable th) {
        }
    }

    private NativeCrashHandler() {
    }

    public static void doNativeCrash() {
        if (a) {
            try {
                nativeException();
            } catch (Throwable th) {
            }
        }
    }

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        b = context;
        if (a) {
            File cacheDir = context.getCacheDir();
            if (cacheDir.exists() && cacheDir.isDirectory()) {
                try {
                    nativeInit(cacheDir.getAbsolutePath());
                } catch (Throwable th) {
                }
            }
        }
    }

    private static native void nativeException();

    private static native void nativeInit(String str);

    private static native void nativeProcess(String str);

    private static native void nativeUnint();

    public static void onCrashCallbackFromNative(String str) {
        ExceptionAnalysis.getInstance().saveCrashInfo(b, System.currentTimeMillis(), str, "NativeException", 1, 0);
    }

    public static void process(String str) {
        if (str == null || str.length() == 0 || !a) {
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            try {
                nativeProcess(str);
            } catch (Throwable th) {
            }
        }
    }

    public static void uninit() {
        if (a) {
            try {
                nativeUnint();
            } catch (Throwable th) {
            }
        }
    }
}

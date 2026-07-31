package com.androlua;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler INSTANCE = new CrashHandler();
    public static final String TAG = "CrashHandler";
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Map<String, String> infos = new LinkedHashMap();
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    private boolean handleException(Throwable th) {
        if (th == null) {
            return false;
        }
        collectDeviceInfo(this.mContext);
        saveCrashInfo2File(th);
        return true;
    }

    private String saveCrashInfo2File(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.infos.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "\n");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        do {
            th.printStackTrace(printWriter);
            th = th.getCause();
        } while (th != null);
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str = "crash-" + this.formatter.format(new Date()) + "-" + jCurrentTimeMillis + ".log";
            if (!Environment.getExternalStorageState().equals("mounted")) {
                return str;
            }
            File externalFilesDir = LuaApplication.getInstance().getExternalFilesDir("crash");
            if (!externalFilesDir.exists()) {
                externalFilesDir.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(externalFilesDir.getAbsolutePath() + File.separatorChar + str);
            fileOutputStream.write(stringBuffer.toString().getBytes());
            Log.e("crash", stringBuffer.toString());
            fileOutputStream.close();
            return str;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
            return null;
        }
    }

    public void collectDeviceInfo(Context context) {
        Map<String, String> map;
        String name;
        String string;
        Map<String, String> map2;
        String name2;
        String string2;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.versionName;
                if (str == null) {
                    str = "null";
                }
                String str2 = packageInfo.versionCode + "";
                this.infos.put("versionName", str);
                this.infos.put("versionCode", str2);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (obj instanceof String[]) {
                    map2 = this.infos;
                    name2 = field.getName();
                    string2 = Arrays.toString((String[]) obj);
                } else {
                    map2 = this.infos;
                    name2 = field.getName();
                    string2 = obj.toString();
                }
                map2.put(name2, string2);
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e2) {
                Log.e(TAG, "an error occured when collect crash info", e2);
            }
        }
        for (Field field2 : Build.VERSION.class.getDeclaredFields()) {
            try {
                field2.setAccessible(true);
                Object obj2 = field2.get(null);
                if (obj2 instanceof String[]) {
                    map = this.infos;
                    name = field2.getName();
                    string = Arrays.toString((String[]) obj2);
                } else {
                    map = this.infos;
                    name = field2.getName();
                    string = obj2.toString();
                }
                map.put(name, string);
                Log.d(TAG, field2.getName() + " : " + field2.get(null));
            } catch (Exception e3) {
                Log.e(TAG, "an error occured when collect crash info", e3);
            }
        }
    }

    public void init(Context context) {
        this.mContext = context;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (handleException(th) || (uncaughtExceptionHandler = this.mDefaultHandler) == null) {
            return;
        }
        uncaughtExceptionHandler.uncaughtException(thread, th);
    }
}

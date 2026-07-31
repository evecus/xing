package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.system.Os;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class CarUUID {
    private static final Pattern a = Pattern.compile("(\\w{32})");

    private static String a(Context context) {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static String a(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (file != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    String str = new String(bArr, 0, fileInputStream.read(bArr));
                    String str2 = a.matcher(str).matches() ? str : null;
                    cm.a(fileInputStream);
                    return str2;
                } catch (Exception e) {
                    cm.a(fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    cm.a(fileInputStream2);
                    throw th;
                }
            } catch (Exception e2) {
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }

    private static boolean a(Context context, String str) {
        boolean z = false;
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            fileOutputStreamOpenFileOutput = context.openFileOutput("libdueros_uuid.so", 0);
            if (a(fileOutputStreamOpenFileOutput, str)) {
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                File fileStreamPath = context.getFileStreamPath("libdueros_uuid.so");
                if (a(new File(applicationInfo.dataDir), 457)) {
                    if (a(fileStreamPath, 484)) {
                        z = true;
                    }
                }
                cm.a(fileOutputStreamOpenFileOutput);
                return z;
            }
        } catch (Exception e) {
        } catch (Throwable th) {
            cm.a(fileOutputStreamOpenFileOutput);
            throw th;
        }
        cm.a(fileOutputStreamOpenFileOutput);
        return false;
    }

    private static boolean a(File file, int i) {
        try {
            Os.chmod(file.getAbsolutePath(), i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean a(FileOutputStream fileOutputStream, String str) {
        try {
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String b(Context context) {
        return a(context.getFileStreamPath("libdueros_uuid.so"));
    }

    private static String c(Context context) {
        String strA;
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        for (ApplicationInfo applicationInfo2 : installedApplications) {
            if (!applicationInfo.packageName.equals(applicationInfo2.packageName) && (strA = a(new File(new File(applicationInfo2.dataDir, "files"), "libdueros_uuid.so"))) != null) {
                return strA;
            }
        }
        return null;
    }

    public static String optUUID(Context context) {
        String strB = b(context);
        if (strB != null) {
            return strB;
        }
        String strC = c(context);
        if (strC != null) {
            a(context, strC);
            return strC;
        }
        String strA = a(context);
        if (strA == null) {
            return "";
        }
        a(context, strA);
        return strA;
    }
}

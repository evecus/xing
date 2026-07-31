package roam.a.a.f.h;

import android.content.Context;
import android.text.TextUtils;
import com.ta.utdid2.device.UTDevice;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static b b;
    public Context a;

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    public static String b(String[] strArr) {
        Process processStart;
        String line = "";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(strArr);
            processBuilder.redirectErrorStream(false);
            processStart = processBuilder.start();
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(processStart.getOutputStream());
                line = new DataInputStream(processStart.getInputStream()).readLine();
                dataOutputStream.writeBytes("exit\n");
                dataOutputStream.flush();
                processStart.waitFor();
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            processStart = null;
        }
        try {
            processStart.destroy();
        } catch (Exception e) {
        }
        return line;
    }

    public static boolean c() {
        for (int i = 0; i < 5; i++) {
            try {
                String str = new String[]{"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/"}[i] + "su";
                if (new File(str).exists()) {
                    String strB = b(new String[]{"ls", "-l", str});
                    if (TextUtils.isEmpty(strB)) {
                        return false;
                    }
                    return strB.indexOf("root") != strB.lastIndexOf("root");
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public final String d() {
        try {
            return UTDevice.getUtdid(this.a);
        } catch (Throwable th) {
            roam.a.a.f.a.l.a.c("third", "GetUtdidEx", th);
            return "";
        }
    }
}

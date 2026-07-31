package com.baidu.mobstat;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings;
import android.system.Os;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.android.common.util.DeviceId;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class au {
    private Context a;
    private ak b;

    static class a {
        static boolean a(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (Exception e) {
                at.a(e);
                return false;
            }
        }
    }

    public au(Context context, ak akVar) {
        this.a = context;
        this.b = akVar;
    }

    private aq a(Context context) {
        List<p> listB = this.b.b(context);
        aq aqVarB = null;
        if (listB != null) {
            File filesDir = context.getFilesDir();
            String name = "files";
            if (!"files".equals(filesDir.getName())) {
                Log.e("CuidV266Manager", "fetal error:: app files dir name is unexpectedly :: " + filesDir.getAbsolutePath());
                name = filesDir.getName();
            }
            for (p pVar : listB) {
                if (!pVar.d) {
                    File file = new File(new File(pVar.a.dataDir, name), "libcuid.so");
                    if (file.exists() && (aqVarB = aq.b(at.a(file))) != null) {
                        break;
                    }
                }
            }
        }
        return aqVarB;
    }

    private boolean a() {
        return c("android.permission.WRITE_SETTINGS");
    }

    private boolean a(String str, String str2) {
        try {
            return Settings.System.putString(this.a.getContentResolver(), str, str2);
        } catch (Exception e) {
            at.a(e);
            return false;
        }
    }

    private aq b() {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (file.exists()) {
            return aq.b(at.a(file));
        }
        return null;
    }

    private static void b(String str, String str2) {
        File file;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            byte[] bArrA = w.a();
            fileWriter.write(al.a(s.a(bArrA, bArrA, (str + "=" + str2).getBytes()), "utf-8"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
        } catch (Exception e2) {
        }
    }

    private boolean b(String str) {
        String absolutePath;
        int i;
        boolean z = DeviceId.sDataCuidInfoShable;
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            try {
                fileOutputStreamOpenFileOutput = this.a.openFileOutput("libcuid.so", 0);
                fileOutputStreamOpenFileOutput.write(str.getBytes());
                fileOutputStreamOpenFileOutput.flush();
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e) {
                        at.a(e);
                    }
                }
                if (DeviceId.sDataCuidInfoShable) {
                    absolutePath = new File(this.a.getFilesDir(), "libcuid.so").getAbsolutePath();
                    i = 436;
                } else {
                    if (DeviceId.sDataCuidInfoShable) {
                        return true;
                    }
                    absolutePath = new File(this.a.getFilesDir(), "libcuid.so").getAbsolutePath();
                    i = 432;
                }
                return a.a(absolutePath, i);
            } catch (Exception e2) {
                at.a(e2);
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e3) {
                        at.a(e3);
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (Exception e4) {
                    at.a(e4);
                }
            }
            throw th;
        }
    }

    private aq c() {
        return aq.a(d("com.baidu.deviceid"), d("bd_setting_i"));
    }

    private boolean c(String str) {
        return this.a.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private String d(String str) {
        try {
            return Settings.System.getString(this.a.getContentResolver(), str);
        } catch (Exception e) {
            at.a(e);
            return null;
        }
    }

    private static void e(String str) {
        File file;
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid2");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
        } catch (Exception e2) {
        }
    }

    private String f(String str) {
        return PropertyType.UID_PROPERTRY;
    }

    private aq g(String str) {
        String str2;
        BufferedReader bufferedReader;
        StringBuilder sb;
        String str3 = "";
        File file = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
        if (!file.exists()) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            sb = new StringBuilder();
        } catch (FileNotFoundException e) {
        } catch (IOException e2) {
        } catch (Exception e3) {
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            sb.append("\r\n");
            str2 = "";
            return aq.a(str3, str2);
        }
        bufferedReader.close();
        byte[] bArrA = w.a();
        String[] strArrSplit = new String(s.b(bArrA, bArrA, al.a(sb.toString().getBytes()))).split("=");
        if (strArrSplit == null || strArrSplit.length != 2) {
            str2 = "";
        } else {
            str2 = strArrSplit[0];
            try {
                str3 = strArrSplit[1];
            } catch (FileNotFoundException e4) {
            } catch (IOException e5) {
            } catch (Exception e6) {
            }
        }
        return aq.a(str3, str2);
    }

    public aq a(String str) {
        boolean z;
        aq aqVarA = a(this.a);
        if (aqVarA == null) {
            aqVarA = aq.b(d("com.baidu.deviceid.v2"));
        }
        boolean zC = c("android.permission.READ_EXTERNAL_STORAGE");
        if (aqVarA == null && zC) {
            aqVarA = b();
        }
        if (aqVarA == null) {
            aqVarA = c();
        }
        if (aqVarA == null && zC) {
            aqVarA = g(f(""));
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            f("");
        }
        if (aqVarA != null) {
            aqVarA.d();
        }
        return aqVarA;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007c A[PHI: r6
  0x007c: PHI (r6v11 java.lang.String) = (r6v1 java.lang.String), (r6v1 java.lang.String), (r6v10 java.lang.String) binds: [B:27:0x007a, B:34:0x0091, B:33:0x008c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.baidu.mobstat.aq r6) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.au.a(com.baidu.mobstat.aq):void");
    }
}

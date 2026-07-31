package com.baidu.mobstat;

import android.content.Context;
import com.bumptech.glide.load.Key;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ap {
    private Context a;
    private a b;

    public final class a {
        private File b;
        private String c;
        private a d;
        private boolean e;

        a(File file) {
            this.e = false;
            this.e = true;
            this.b = file;
            this.c = file.getName();
        }

        a(String str, a aVar) {
            this.e = false;
            this.c = str;
            this.d = aVar;
            this.e = false;
        }

        public a a(File file) {
            if (this.e) {
                throw new IllegalStateException("isolate session is not support");
            }
            ArrayList arrayList = new ArrayList();
            a aVarD = this;
            do {
                arrayList.add(aVarD.c());
                aVarD = aVarD.d();
            } while (aVarD != null);
            int size = arrayList.size() - 1;
            while (size >= 0) {
                File file2 = new File(file, (String) arrayList.get(size));
                size--;
                file = file2;
            }
            return ap.this.new a(file);
        }

        public a a(String str) {
            return ap.this.new a(str, this);
        }

        public String a(String str, boolean z) {
            return ap.a(b(), str, Key.STRING_CHARSET_NAME, z);
        }

        public void a() {
            b().mkdirs();
        }

        public boolean a(String str, String str2, boolean z) {
            return ap.a(b(), str, str2, Key.STRING_CHARSET_NAME, z);
        }

        public File b() {
            File file = this.b;
            if (file != null) {
                return file;
            }
            File file2 = this.d == null ? new File(ap.this.a(), this.c) : new File(this.d.b(), this.c);
            this.b = file2;
            return file2;
        }

        public File b(String str) {
            return new File(this.b, str);
        }

        public String c() {
            return this.c;
        }

        public a d() {
            return this.d;
        }
    }

    public ap(Context context) {
        this.a = context;
        c().mkdirs();
    }

    public static String a(File file, String str, String str2, boolean z) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        a(file);
        File file2 = new File(file, str);
        FileInputStream fileInputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                fileInputStream = new FileInputStream(file2);
            } catch (Exception e) {
            } catch (Throwable th2) {
                fileInputStream = null;
                th = th2;
            }
        } catch (Exception e2) {
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            byteArrayOutputStream = null;
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (z) {
                byteArray = new ab().b(byteArray);
            }
            String str3 = new String(byteArray, str2);
            at.a(fileInputStream);
            at.a(byteArrayOutputStream);
            return str3;
        } catch (Exception e3) {
            fileInputStream2 = fileInputStream;
            at.a(fileInputStream2);
            at.a(byteArrayOutputStream);
            return "";
        } catch (Throwable th4) {
            th = th4;
            at.a(fileInputStream);
            at.a(byteArrayOutputStream);
            throw th;
        }
    }

    public static void a(File file) {
        file.mkdirs();
    }

    public static boolean a(File file, String str, String str2, String str3, boolean z) throws Throwable {
        FileOutputStream fileOutputStream;
        Throwable th;
        a(file);
        File file2 = new File(file, str);
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file2);
        } catch (Exception e) {
        } catch (Throwable th2) {
            fileOutputStream = null;
            th = th2;
        }
        try {
            if (z) {
                fileOutputStream.write(new ab().a(str2.getBytes()));
            } else {
                fileOutputStream.write(str2.getBytes(str3));
            }
            at.a(fileOutputStream);
            return true;
        } catch (Exception e2) {
            fileOutputStream2 = fileOutputStream;
            at.a(fileOutputStream2);
            return false;
        } catch (Throwable th3) {
            th = th3;
            at.a(fileOutputStream);
            throw th;
        }
    }

    private File c() {
        return new File(a(), ".cesium");
    }

    public File a() {
        return new File(this.a.getApplicationInfo().dataDir);
    }

    public synchronized a b() {
        if (this.b == null) {
            this.b = new a(".cesium", null);
        }
        return this.b;
    }
}

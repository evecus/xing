package com.baidu.mobstat;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.baidu.mobstat.ap;
import com.baidu.mobstat.l;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class av {
    private static boolean b = false;
    private static String h = null;
    ak a;
    private Context c;
    private ap.a d;
    private volatile FileLock e;
    private volatile RandomAccessFile f;
    private m g;

    public static class a {
        public static final String[] a = {ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "O", PropertyType.UID_PROPERTRY};
        private String b;
        private String c;
        private String d;
        private long e;
        private String f;
        private boolean g;
        private String h;
        private String j;
        private boolean i = true;
        private int k = 1;

        public String a() {
            return this.b;
        }

        public void a(String str) {
            this.j = str;
        }

        public synchronized void a(boolean z) {
            this.i = z;
        }

        public String b() {
            return this.f;
        }

        public String c() {
            return this.c;
        }

        public boolean d() {
            return this.g;
        }

        public String e() {
            return this.h;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (this.k == aVar.k && this.b.equals(aVar.b) && this.c.equals(aVar.c) && this.d.equals(aVar.d) && this.g == aVar.g && this.h.equals(aVar.h)) {
                String str = this.f;
                String str2 = aVar.f;
                if (str == str2) {
                    return true;
                }
                if (str != null && str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public synchronized boolean f() {
            return this.i;
        }

        public String g() {
            return this.j;
        }

        public void h() {
            String strB = av.b();
            if (TextUtils.isEmpty(strB)) {
                return;
            }
            this.g = true;
            this.h = strB;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.b, this.c, this.d, Boolean.valueOf(this.g), this.h, this.f, Integer.valueOf(this.k)});
        }

        public aq i() {
            aq aqVar = new aq();
            aqVar.a = this.b;
            StringBuilder sb = new StringBuilder();
            sb.append(this.c);
            if (ExifInterface.GPS_MEASUREMENT_INTERRUPTED.equals(this.c)) {
                sb.append(this.d);
            }
            if (!TextUtils.isEmpty(this.f)) {
                sb.append(this.f);
            }
            aqVar.b = sb.toString().trim();
            return aqVar;
        }

        public String j() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("dik", this.b);
                jSONObject.put("v270fk", this.c);
                jSONObject.put("cck", this.d);
                jSONObject.put("vsk", this.k);
                jSONObject.put("ctk", this.e);
                jSONObject.put("csk", this.g);
                if (!TextUtils.isEmpty(this.h)) {
                    jSONObject.put("pmk", this.h);
                }
                if (!TextUtils.isEmpty(this.j)) {
                    jSONObject.put("ock", this.j);
                }
                jSONObject.put("hrk", this.i);
                jSONObject.put("ek", this.f);
                return jSONObject.toString();
            } catch (JSONException e) {
                at.a(e);
                return null;
            }
        }

        public String k() {
            String str = this.c;
            if (TextUtils.isEmpty(str)) {
                str = PropertyType.UID_PROPERTRY;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.b).append("|").append(str);
            if (ExifInterface.GPS_MEASUREMENT_INTERRUPTED.equals(str)) {
                sb.append(this.d);
            }
            if (!TextUtils.isEmpty(this.f)) {
                sb.append(this.f);
            }
            return sb.toString().trim();
        }
    }

    public av(Context context, ap apVar, ak akVar) {
        if (context == null) {
            throw new NullPointerException("context should not be null!!!");
        }
        this.c = context.getApplicationContext();
        ap.a aVarA = apVar.b().a("bohrium");
        this.d = aVarA;
        aVarA.a();
        this.a = akVar;
        a(apVar);
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("dik", "");
            String strOptString2 = jSONObject.optString("cck", "");
            long jOptLong = jSONObject.optLong("ctk", 0L);
            int iOptInt = jSONObject.optInt("vsk", 1);
            boolean zOptBoolean = jSONObject.optBoolean("csk", false);
            String strOptString3 = jSONObject.optString("pmk", "");
            boolean zOptBoolean2 = jSONObject.optBoolean("hrk", true);
            String strOptString4 = jSONObject.optString("ock", null);
            String strOptString5 = jSONObject.optString("ek", "");
            String strOptString6 = jSONObject.optString("v270fk", ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            if (!TextUtils.isEmpty(strOptString)) {
                a aVar = new a();
                aVar.b = strOptString;
                aVar.d = strOptString2;
                aVar.e = jOptLong;
                aVar.k = iOptInt;
                aVar.f = strOptString5;
                aVar.c = strOptString6;
                aVar.g = zOptBoolean;
                aVar.h = strOptString3;
                aVar.i = zOptBoolean2;
                aVar.j = strOptString4;
                return aVar;
            }
        } catch (Exception e) {
            at.a(e);
        }
        return null;
    }

    public static a a(String str, String str2, String str3, boolean z, String str4) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                String strD = d(str);
                long jCurrentTimeMillis = System.currentTimeMillis();
                a aVar = new a();
                aVar.b = str;
                aVar.d = strD;
                aVar.e = jCurrentTimeMillis;
                aVar.k = 1;
                aVar.f = str3;
                aVar.c = str2;
                aVar.g = z;
                aVar.h = str4;
                return aVar;
            } catch (Exception e) {
                at.a(e);
            }
        }
        return null;
    }

    private String a(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    private String a(boolean z) {
        return this.d.a("libbh.so", z);
    }

    private void a(ap apVar) {
        m mVar = new m(new k());
        l.a aVar = new l.a();
        aVar.a = this.c;
        aVar.b = apVar;
        l.c cVar = new l.c();
        for (l lVar : mVar.a()) {
            lVar.a(aVar);
            lVar.a(cVar);
        }
        this.g = mVar;
    }

    public static String b() {
        String str = h;
        if (str != null) {
            return str;
        }
        if (TextUtils.isEmpty(android.os.Build.MODEL)) {
            return "";
        }
        String strSubstring = am.a(android.os.Build.MODEL.getBytes(), false).substring(3, 15);
        h = strSubstring;
        return strSubstring;
    }

    private static String d(String str) {
        try {
            return new ar("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567=", false, false).a(new com.baidu.mobstat.a().a(str.getBytes(Key.STRING_CHARSET_NAME)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public a a() {
        if (new File(this.d.b(), "libbh.so").exists()) {
            return a(a(true));
        }
        return null;
    }

    public a a(aq aqVar) {
        if (aqVar == null) {
            throw new IllegalArgumentException("arg non-nullable is expected");
        }
        a aVar = new a();
        aVar.e = System.currentTimeMillis();
        aVar.k = 1;
        try {
            int i = 0;
            aVar.c = aqVar.b.substring(0, 1);
            aVar.b = aqVar.a;
            aVar.d = d(aqVar.a);
            String[] strArr = a.a;
            int length = strArr.length;
            while (true) {
                if (i < length) {
                    if (strArr[i].equals(aVar.c)) {
                        break;
                    }
                    i++;
                } else if (aqVar.b != null && aqVar.b.length() >= 2) {
                    aVar.f = aqVar.b.substring(1);
                }
            }
            return aVar;
        } catch (Exception e) {
            return null;
        }
    }

    public a a(String str, String str2) {
        l lVarA = this.g.a(str2);
        l.f fVar = new l.f();
        fVar.a = true;
        l.g gVarA = lVarA.a(str, fVar);
        if (gVarA == null || !gVarA.a()) {
            return null;
        }
        return gVarA.a;
    }

    public void a(a aVar) {
        l.d dVar = new l.d();
        Iterator<l> it = this.g.a().iterator();
        while (it.hasNext()) {
            it.next().a(dVar, aVar);
        }
    }

    public boolean a(a aVar, boolean z, boolean z2) {
        a aVarA;
        if (aVar == null || TextUtils.isEmpty(aVar.b)) {
            throw new NullPointerException("content should not be null");
        }
        if (!z2) {
            try {
                if (new File(this.d.b(), "libbh.so").exists() && (aVarA = a(a(true))) != null) {
                    String strK = aVarA.k();
                    boolean z3 = !TextUtils.isEmpty(strK) && strK.equals(aVar.k());
                    boolean z4 = aVarA.d() && !TextUtils.isEmpty(aVarA.e()) && TextUtils.equals(aVarA.e(), b());
                    if (z3 && z4) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return this.d.a("libbh.so", aVar.j(), z);
    }

    public a b(String str) {
        String strA = am.a(("com.baidu" + a(this.c)).getBytes(), true);
        String strB = b();
        a aVar = new a();
        aVar.e = System.currentTimeMillis();
        aVar.k = 1;
        aVar.b = strA;
        aVar.c = ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
        aVar.d = d(strA);
        aVar.g = true;
        aVar.h = strB;
        aVar.f = null;
        return aVar;
    }

    public a c(String str) {
        l.f fVar = new l.f();
        fVar.a = true;
        List<l> listA = this.g.a();
        Collections.sort(listA, l.c);
        List<p> listB = this.a.b(this.c);
        if (listB == null) {
            return null;
        }
        for (p pVar : listB) {
            if (!pVar.d && pVar.c) {
                Iterator<l> it = listA.iterator();
                while (it.hasNext()) {
                    l.g gVarA = it.next().a(pVar.a.packageName, fVar);
                    if (gVarA != null && gVarA.a() && gVarA.a != null) {
                        a aVar = gVarA.a;
                        if (!TextUtils.equals(aVar.a(), str) && (!aVar.d() || TextUtils.equals(b(), aVar.e()))) {
                            return gVarA.a;
                        }
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0025 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean c() {
        /*
            r6 = this;
            monitor-enter(r6)
            com.baidu.mobstat.ap$a r0 = r6.d     // Catch: java.lang.Throwable -> L4d
            java.lang.String r1 = ".lock"
            java.io.File r0 = r0.b(r1)     // Catch: java.lang.Throwable -> L4d
            boolean r1 = r0.exists()     // Catch: java.lang.Throwable -> L4d
            if (r1 != 0) goto L17
            r0.createNewFile()     // Catch: java.io.IOException -> L13 java.lang.Throwable -> L4d
            goto L17
        L13:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L4d
        L17:
            r1 = 0
            r2 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch: java.lang.Exception -> L40 java.lang.Throwable -> L4d
            java.lang.String r4 = "rw"
            r3.<init>(r0, r4)     // Catch: java.lang.Exception -> L40 java.lang.Throwable -> L4d
            r0 = r1
        L21:
            r2 = 100
            if (r0 >= r2) goto L4b
            java.nio.channels.FileChannel r2 = r3.getChannel()     // Catch: java.lang.Exception -> L34 java.nio.channels.OverlappingFileLockException -> L37 java.lang.Throwable -> L4d
            java.nio.channels.FileLock r2 = r2.lock()     // Catch: java.lang.Exception -> L34 java.nio.channels.OverlappingFileLockException -> L37 java.lang.Throwable -> L4d
            r6.e = r2     // Catch: java.lang.Exception -> L34 java.nio.channels.OverlappingFileLockException -> L37 java.lang.Throwable -> L4d
            r6.f = r3     // Catch: java.lang.Exception -> L34 java.nio.channels.OverlappingFileLockException -> L37 java.lang.Throwable -> L4d
            monitor-exit(r6)
            r0 = 1
            return r0
        L34:
            r0 = move-exception
            r2 = r3
            goto L41
        L37:
            r2 = move-exception
            r4 = 100
            java.lang.Thread.sleep(r4)     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L4d
            int r0 = r0 + 1
            goto L21
        L40:
            r0 = move-exception
        L41:
            com.baidu.mobstat.at.a(r0)     // Catch: java.lang.Throwable -> L4d
            java.nio.channels.FileLock r0 = r6.e     // Catch: java.lang.Throwable -> L4d
            if (r0 != 0) goto L4b
            com.baidu.mobstat.at.a(r2)     // Catch: java.lang.Throwable -> L4d
        L4b:
            monitor-exit(r6)
            return r1
        L4d:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.av.c():boolean");
    }

    public synchronized void d() {
        if (this.e != null) {
            try {
                this.e.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.e = null;
            at.a(this.f);
            this.f = null;
        } else {
            at.a(this.f);
            this.f = null;
        }
    }
}

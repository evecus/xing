package roam.a.d.a;

import android.R;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.PointerIconCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import roam.a.d.a.l;
import roam.a.d.a.n;

/* JADX INFO: loaded from: classes.dex */
public class k extends AsyncTask<Void, Integer, Integer> implements m {
    public static final String r;
    public static final SparseArray<String> s;
    public static final Executor t;
    public static final Handler u;
    public volatile h a;
    public volatile Throwable h;
    public g k;
    public volatile long b = 0;
    public volatile long c = -1;
    public long d = 0;
    public long e = 0;
    public long f = 0;
    public volatile long g = 0;
    public long i = RecyclerView.FOREVER_NS;
    public long j = 10000;
    public AtomicBoolean l = new AtomicBoolean(false);
    public AtomicBoolean m = new AtomicBoolean(false);
    public AtomicBoolean n = new AtomicBoolean(false);
    public volatile boolean o = false;
    public boolean p = false;
    public boolean q = false;

    public final class a extends RandomAccessFile {
        public final k a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(k kVar, File file) {
            super(file, "rw");
            this.a = kVar;
        }

        @Override // java.io.RandomAccessFile, java.io.DataOutput
        public void write(byte[] bArr, int i, int i2) throws IOException {
            super.write(bArr, i, i2);
            this.a.b += (long) i2;
            if (this.a.a != null) {
                long j = this.a.d;
            }
            if (this.a.o) {
                boolean z = this.a.q;
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                if (!z) {
                    k kVar = this.a;
                    if (jElapsedRealtime - kVar.f >= 1200) {
                        kVar.f = jElapsedRealtime;
                        if (kVar.p) {
                            kVar.publishProgress(1);
                            return;
                        } else {
                            kVar.onProgressUpdate(1);
                            return;
                        }
                    }
                    return;
                }
                k kVar2 = this.a;
                if (jElapsedRealtime - kVar2.f < 1200) {
                    if (kVar2.p) {
                        kVar2.publishProgress(0);
                        return;
                    } else {
                        kVar2.onProgressUpdate(0);
                        return;
                    }
                }
                kVar2.f = jElapsedRealtime;
                if (kVar2.p) {
                    kVar2.publishProgress(1);
                } else {
                    kVar2.onProgressUpdate(1);
                }
            }
        }
    }

    static {
        StringBuilder sbO = roam.a.b.a.a.a.o("Download-");
        sbO.append(k.class.getSimpleName());
        r = sbO.toString();
        SparseArray<String> sparseArray = new SparseArray<>(12);
        s = sparseArray;
        t = new s();
        u = new Handler(Looper.getMainLooper());
        sparseArray.append(1024, "Network connection error . ");
        sparseArray.append(InputDeviceCompat.SOURCE_GAMEPAD, "Response code non-200 or non-206 . ");
        sparseArray.append(1026, "Insufficient memory space . ");
        sparseArray.append(1031, "Shutdown . ");
        sparseArray.append(1027, "Download time is overtime . ");
        sparseArray.append(1030, "The user canceled the download . ");
        sparseArray.append(1040, "Resource not found . ");
        sparseArray.append(1028, "paused . ");
        sparseArray.append(1033, "IO Error . ");
        sparseArray.append(1283, "Service Unavailable . ");
        sparseArray.append(1032, "Too many redirects . ");
        sparseArray.append(1041, "Md5 check fails . ");
        sparseArray.append(512, "Download successful . ");
    }

    @Override // roam.a.d.a.m
    public h a() {
        try {
            return this.a;
        } finally {
            this.l.set(true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e7  */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Integer doInBackground(java.lang.Void[] r10) {
        /*
            Method dump skipped, instruction units count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.d.a.k.doInBackground(java.lang.Object[]):java.lang.Object");
    }

    public final boolean e() {
        long blockSizeLong;
        h hVar = this.a;
        long j = hVar.u;
        long length = hVar.w.length();
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().toString());
            blockSizeLong = statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
        } catch (RuntimeException e) {
            blockSizeLong = 0;
        }
        if (j - length <= blockSizeLong - 104857600) {
            return true;
        }
        r rVar = r.h;
        String str = r;
        Objects.requireNonNull(rVar);
        Log.e(str, " 空间不足");
        return false;
    }

    public void f(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final HttpURLConnection g(URL url) {
        h hVar = this.a;
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout((int) this.j);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout((int) hVar.o);
        httpURLConnection.setRequestProperty("Accept", "*/*");
        httpURLConnection.setRequestProperty("Accept-Encoding", "deflate,gzip");
        return httpURLConnection;
    }

    public void h() {
        h hVar;
        if (this.l.get() || this.m.get() || (hVar = this.a) == null) {
            return;
        }
        hVar.t = -1;
        hVar.g = null;
        hVar.v = null;
        hVar.w = null;
        hVar.e = false;
        hVar.a = false;
        hVar.b = true;
        hVar.c = R.drawable.stat_sys_download;
        hVar.d = R.drawable.stat_sys_download_done;
        hVar.e = true;
        hVar.f = true;
        hVar.j = "";
        hVar.h = "";
        hVar.i = "";
        Map<String, String> map = hVar.k;
        if (map != null) {
            map.clear();
            hVar.k = null;
        }
        hVar.s = 3;
        hVar.r = "";
        hVar.q = "";
    }

    public final boolean i(Integer num) {
        c cVar;
        h hVar = this.a;
        e eVar = hVar.x;
        if (eVar == null) {
            return false;
        }
        Objects.requireNonNull(r.h);
        if (this.h != null) {
            this.h.printStackTrace();
        }
        if (num.intValue() <= 512) {
            cVar = null;
        } else {
            int iIntValue = num.intValue();
            StringBuilder sbO = roam.a.b.a.a.a.o("failed , cause:");
            sbO.append(s.get(num.intValue()));
            cVar = new c(iIntValue, sbO.toString());
        }
        return eVar.b(cVar, Uri.fromFile(hVar.w), hVar.g, this.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:129:0x0349, code lost:
    
        if (r7 == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x034b, code lost:
    
        r25.c = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0358, code lost:
    
        if (r3.w.length() < r11) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x035a, code lost:
    
        r25.c = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x035e, code lost:
    
        r3.u = r25.c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0362, code lost:
    
        if (r7 != false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0368, code lost:
    
        if (e() != false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x036c, code lost:
    
        q(r6);
        r3.u = r25.c;
        r0 = t(m(r6), new roam.a.d.a.k.a(r25, r3.w), false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:?, code lost:
    
        return 1033;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:?, code lost:
    
        return 1026;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:?, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0159, code lost:
    
        android.util.Log.e(r8, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x017b, code lost:
    
        r6.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x019f, code lost:
    
        r6.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01b7, code lost:
    
        r6.disconnect();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int j() {
        /*
            Method dump skipped, instruction units count: 938
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.d.a.k.j():int");
    }

    public final String k() {
        String str = this.a.g;
        r rVar = r.h;
        String strH = rVar.h(str);
        Context context = ((b) rVar.f(this.a.v)).a;
        Objects.requireNonNull(rVar);
        String string = context.getSharedPreferences("Downloader", 0).getString(strH, "-1");
        if (TextUtils.isEmpty(string) || "-1".equals(string)) {
            return null;
        }
        return string;
    }

    public final long l(HttpURLConnection httpURLConnection, String str) {
        String headerField = httpURLConnection.getHeaderField(str);
        if (headerField != null) {
            try {
                return Long.parseLong(headerField);
            } catch (NumberFormatException e) {
                Objects.requireNonNull(r.h);
                e.printStackTrace();
            }
        }
        return -1L;
    }

    public final InputStream m(HttpURLConnection httpURLConnection) {
        return "gzip".equalsIgnoreCase(httpURLConnection.getContentEncoding()) ? new GZIPInputStream(httpURLConnection.getInputStream()) : "deflate".equalsIgnoreCase(httpURLConnection.getContentEncoding()) ? new InflaterInputStream(httpURLConnection.getInputStream(), new Inflater(true)) : httpURLConnection.getInputStream();
    }

    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public void onProgressUpdate(Integer... numArr) {
        h hVar = this.a;
        try {
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.g;
            this.e = jElapsedRealtime;
            if (jElapsedRealtime != 0) {
                long j = (this.b * 1000) / this.e;
            }
            if (numArr != null && numArr.length > 0 && numArr[0].intValue() == 1 && this.k != null) {
                if (this.c > 0) {
                    this.k.i((int) (((this.d + this.b) / Float.valueOf(this.c).floatValue()) * 100.0f));
                } else {
                    this.k.h(this.d + this.b);
                }
            }
            if (hVar.x != null) {
                hVar.y.a(hVar.g, this.d + this.b, this.c, hVar.e());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void o(h hVar, HttpURLConnection httpURLConnection) {
        File file = hVar.w;
        if (file != null && file.length() > 0) {
            StringBuilder sbO = roam.a.b.a.a.a.o("bytes=");
            long length = hVar.w.length();
            this.d = length;
            sbO.append(length);
            sbO.append("-");
            httpURLConnection.setRequestProperty("Range", sbO.toString());
        }
        httpURLConnection.setRequestProperty("Connection", "close");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0081 A[Catch: all -> 0x0121, TryCatch #1 {all -> 0x0121, blocks: (B:3:0x0004, B:5:0x0008, B:6:0x0016, B:8:0x001e, B:10:0x0030, B:11:0x0033, B:13:0x0037, B:23:0x004e, B:25:0x0056, B:29:0x0069, B:31:0x0075, B:33:0x0081, B:35:0x0085, B:45:0x00a0, B:48:0x00a6, B:58:0x00c3, B:60:0x00c7, B:61:0x00ca, B:72:0x00e1, B:83:0x00fe, B:85:0x0104, B:86:0x0109, B:26:0x005c, B:28:0x0064, B:30:0x006d), top: B:121:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a0 A[Catch: all -> 0x0121, TRY_ENTER, TryCatch #1 {all -> 0x0121, blocks: (B:3:0x0004, B:5:0x0008, B:6:0x0016, B:8:0x001e, B:10:0x0030, B:11:0x0033, B:13:0x0037, B:23:0x004e, B:25:0x0056, B:29:0x0069, B:31:0x0075, B:33:0x0081, B:35:0x0085, B:45:0x00a0, B:48:0x00a6, B:58:0x00c3, B:60:0x00c7, B:61:0x00ca, B:72:0x00e1, B:83:0x00fe, B:85:0x0104, B:86:0x0109, B:26:0x005c, B:28:0x0064, B:30:0x006d), top: B:121:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e1 A[Catch: all -> 0x0121, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0121, blocks: (B:3:0x0004, B:5:0x0008, B:6:0x0016, B:8:0x001e, B:10:0x0030, B:11:0x0033, B:13:0x0037, B:23:0x004e, B:25:0x0056, B:29:0x0069, B:31:0x0075, B:33:0x0081, B:35:0x0085, B:45:0x00a0, B:48:0x00a6, B:58:0x00c3, B:60:0x00c7, B:61:0x00ca, B:72:0x00e1, B:83:0x00fe, B:85:0x0104, B:86:0x0109, B:26:0x005c, B:28:0x0064, B:30:0x006d), top: B:121:0x0004 }] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onPostExecute(java.lang.Integer r10) {
        /*
            Method dump skipped, instruction units count: 343
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.d.a.k.onPostExecute(java.lang.Object):void");
    }

    @Override // android.os.AsyncTask
    public void onPreExecute() {
        File fileB;
        r rVar;
        h hVar = this.a;
        Objects.requireNonNull(hVar, "DownloadTask can't be null ");
        File file = hVar.w;
        File file2 = null;
        if (file == null) {
            if (hVar.E) {
                rVar = r.h;
                fileB = rVar.i(hVar, file2);
                hVar.w = fileB;
            } else {
                fileB = r.h.b(hVar.v, hVar, null);
                hVar.w = fileB;
            }
        } else if (file.isDirectory()) {
            if (hVar.E) {
                rVar = r.h;
                file2 = hVar.w;
                fileB = rVar.i(hVar, file2);
                hVar.w = fileB;
            } else {
                fileB = r.h.b(hVar.v, hVar, hVar.w);
                hVar.w = fileB;
            }
        } else if (!hVar.w.exists()) {
            try {
                hVar.w.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                hVar.w = null;
            }
        }
        if (hVar.w == null) {
            throw new RuntimeException("target file can't be created . ");
        }
        h hVar2 = this.a;
        Context applicationContext = hVar2.v.getApplicationContext();
        if (applicationContext != null && hVar2.b) {
            g gVar = new g(applicationContext, hVar2.t);
            this.k = gVar;
            String strD = gVar.d(hVar2);
            gVar.i = hVar2;
            gVar.d.setContentIntent(PendingIntent.getActivity(gVar.e, 200, new Intent(), 134217728));
            gVar.d.setSmallIcon(gVar.i.c);
            gVar.d.setTicker(gVar.e.getString(com.download.library.R.string.download_trickter));
            gVar.d.setContentTitle(strD);
            gVar.d.setContentText(gVar.e.getString(com.download.library.R.string.download_coming_soon_download));
            gVar.d.setWhen(System.currentTimeMillis());
            gVar.d.setAutoCancel(true);
            gVar.d.setPriority(-1);
            gVar.d.setDeleteIntent(gVar.a(gVar.e, hVar2.t, hVar2.g));
            gVar.d.setDefaults(0);
        }
        g gVar2 = this.k;
        if (gVar2 != null) {
            gVar2.k();
        }
    }

    public final void p(h hVar) {
        Objects.requireNonNull(hVar, "downloadTask can't be null.");
        Objects.requireNonNull(hVar.v, "context can't be null.");
        try {
            this.a = hVar;
            this.c = this.a.u;
            this.i = this.a.m;
            this.j = this.a.n;
            this.q = this.a.p;
            boolean z = true;
            this.o = this.a.b || this.a.y != null;
            r rVar = r.h;
            String str = r;
            String str2 = " enableProgress:" + this.o + " quickProgress:" + this.q;
            Objects.requireNonNull(rVar);
            Log.i(str, str2);
            if (this.a.y != null) {
                try {
                    Class<?> cls = this.a.y.getClass();
                    Class<?> cls2 = Long.TYPE;
                    if (cls.getDeclaredMethod("onProgress", String.class, cls2, cls2, cls2).getAnnotation(l.a.class) == null) {
                        z = false;
                    }
                    this.p = z;
                    Log.i(str, " callback in main-Thread:" + this.p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (hVar.d() != 1003) {
                hVar.A = 0L;
                hVar.B = 0L;
                hVar.C = 0L;
                hVar.D = 0L;
            }
            hVar.f(PointerIconCompat.TYPE_CONTEXT_MENU);
            if (hVar.e) {
                executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
            } else {
                executeOnExecutor(t, new Void[0]);
            }
        } catch (Throwable th) {
            if (!TextUtils.isEmpty(hVar.g)) {
                synchronized (k.class) {
                    try {
                        if (!TextUtils.isEmpty(hVar.g)) {
                            n.b.a.b(hVar.g);
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
            }
            th.printStackTrace();
            throw th;
        }
    }

    public final void q(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("ETag");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        String str = this.a.g;
        r rVar = r.h;
        String strH = rVar.h(str);
        Log.i(r, "save etag:" + headerField);
        SharedPreferences.Editor editorEdit = ((b) rVar.f(this.a.v)).a.getSharedPreferences("Downloader", 0).edit();
        editorEdit.putString(strH, headerField);
        editorEdit.apply();
    }

    public final void r(h hVar, HttpURLConnection httpURLConnection) {
        Map<String, String> map = hVar.k;
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }
        String strK = k();
        if (!TextUtils.isEmpty(strK)) {
            r rVar = r.h;
            Objects.requireNonNull(rVar);
            Log.i(r, "Etag:" + strK);
            httpURLConnection.setRequestProperty("If-Match", k());
        }
        r rVar2 = r.h;
        String str = r;
        Objects.requireNonNull(rVar2);
        Log.i(str, "settingHeaders");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0058 A[PHI: r1 r2
  0x0058: PHI (r1v20 roam.a.d.a.h) = (r1v19 roam.a.d.a.h), (r1v22 roam.a.d.a.h) binds: [B:18:0x0056, B:12:0x0043] A[DONT_GENERATE, DONT_INLINE]
  0x0058: PHI (r2v7 roam.a.d.a.g) = (r2v6 roam.a.d.a.g), (r2v9 roam.a.d.a.g) binds: [B:18:0x0056, B:12:0x0043] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s(java.net.HttpURLConnection r5) {
        /*
            r4 = this;
            roam.a.d.a.h r0 = r4.a
            java.lang.String r1 = r0.h
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L61
            java.lang.String r1 = "Content-Disposition"
            java.lang.String r1 = r5.getHeaderField(r1)
            r0.h = r1
            roam.a.d.a.r r2 = roam.a.d.a.r.h
            java.lang.String r1 = r2.e(r1)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L61
            java.io.File r2 = r0.w
            java.lang.String r2 = r2.getName()
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L61
            java.io.File r2 = new java.io.File
            java.io.File r3 = r0.w
            java.lang.String r3 = r3.getParent()
            r2.<init>(r3, r1)
            boolean r1 = r2.exists()
            if (r1 == 0) goto L46
            r0.w = r2
            roam.a.d.a.h r1 = r4.a
            roam.a.d.a.g r2 = r4.k
            if (r2 == 0) goto L61
            if (r1 == 0) goto L61
            goto L58
        L46:
            java.io.File r1 = r0.w
            boolean r1 = r1.renameTo(r2)
            if (r1 == 0) goto L61
            r0.w = r2
            roam.a.d.a.h r1 = r4.a
            roam.a.d.a.g r2 = r4.k
            if (r2 == 0) goto L61
            if (r1 == 0) goto L61
        L58:
            java.lang.String r1 = r2.d(r1)
            androidx.core.app.NotificationCompat$Builder r2 = r2.d
            r2.setContentTitle(r1)
        L61:
            java.lang.String r1 = r0.i
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L71
            java.lang.String r1 = "Content-Type"
            java.lang.String r1 = r5.getHeaderField(r1)
            r0.i = r1
        L71:
            java.lang.String r1 = r0.j
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L85
            java.lang.String r1 = "User-Agent"
            java.lang.String r1 = r5.getHeaderField(r1)
            if (r1 != 0) goto L83
            java.lang.String r1 = ""
        L83:
            r0.j = r1
        L85:
            java.lang.String r0 = "Content-Length"
            r4.l(r5, r0)
            roam.a.d.a.h r5 = r4.a
            if (r5 == 0) goto L9c
            roam.a.d.a.e r0 = r5.x
            if (r0 == 0) goto L9c
            android.os.Handler r0 = roam.a.d.a.k.u
            roam.a.d.a.i r1 = new roam.a.d.a.i
            r1.<init>(r4, r5)
            r0.post(r1)
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.d.a.k.s(java.net.HttpURLConnection):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int t(java.io.InputStream r9, java.io.RandomAccessFile r10, boolean r11) {
        /*
            r8 = this;
            r0 = 8192(0x2000, float:1.148E-41)
            byte[] r1 = new byte[r0]
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream
            r2.<init>(r9, r0)
            roam.a.d.a.h r3 = r8.a
            if (r11 == 0) goto L15
            long r4 = r10.length()     // Catch: java.lang.Throwable -> Lba
            r10.seek(r4)     // Catch: java.lang.Throwable -> Lba
            goto L1c
        L15:
            r4 = 0
            r10.seek(r4)     // Catch: java.lang.Throwable -> Lba
            r8.d = r4     // Catch: java.lang.Throwable -> Lba
        L1c:
            java.util.concurrent.atomic.AtomicBoolean r11 = r8.l     // Catch: java.lang.Throwable -> Lba
            boolean r11 = r11.get()     // Catch: java.lang.Throwable -> Lba
            if (r11 != 0) goto L50
            java.util.concurrent.atomic.AtomicBoolean r11 = r8.n     // Catch: java.lang.Throwable -> Lba
            boolean r11 = r11.get()     // Catch: java.lang.Throwable -> Lba
            if (r11 != 0) goto L50
            java.util.concurrent.atomic.AtomicBoolean r11 = r8.m     // Catch: java.lang.Throwable -> Lba
            boolean r11 = r11.get()     // Catch: java.lang.Throwable -> Lba
            if (r11 != 0) goto L50
            r11 = 0
            int r4 = r2.read(r1, r11, r0)     // Catch: java.lang.Throwable -> Lba
            r5 = -1
            if (r4 != r5) goto L3d
            goto L50
        L3d:
            r10.write(r1, r11, r4)     // Catch: java.lang.Throwable -> Lba
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> Lba
            long r6 = r8.g     // Catch: java.lang.Throwable -> Lba
            long r4 = r4 - r6
            long r6 = r8.i     // Catch: java.lang.Throwable -> Lba
            int r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r11 <= 0) goto L1c
            r11 = 1027(0x403, float:1.439E-42)
            goto Lb0
        L50:
            java.util.concurrent.atomic.AtomicBoolean r11 = r8.m     // Catch: java.lang.Throwable -> Lba
            boolean r11 = r11.get()     // Catch: java.lang.Throwable -> Lba
            if (r11 == 0) goto L5b
            r11 = 1028(0x404, float:1.44E-42)
            goto Lb0
        L5b:
            java.util.concurrent.atomic.AtomicBoolean r11 = r8.l     // Catch: java.lang.Throwable -> Lba
            boolean r11 = r11.get()     // Catch: java.lang.Throwable -> Lba
            if (r11 == 0) goto L66
            r11 = 1030(0x406, float:1.443E-42)
            goto Lb0
        L66:
            java.util.concurrent.atomic.AtomicBoolean r11 = r8.n     // Catch: java.lang.Throwable -> Lba
            boolean r11 = r11.get()     // Catch: java.lang.Throwable -> Lba
            if (r11 == 0) goto L71
            r11 = 1031(0x407, float:1.445E-42)
            goto Lb0
        L71:
            java.lang.String r11 = r3.a()     // Catch: java.lang.Throwable -> Lba
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> Lba
            if (r11 != 0) goto Lae
            roam.a.d.a.r r11 = roam.a.d.a.r.h     // Catch: java.lang.Throwable -> Lba
            roam.a.d.a.h r0 = r8.a     // Catch: java.lang.Throwable -> Lba
            java.io.File r0 = r0.w     // Catch: java.lang.Throwable -> Lba
            java.lang.String r0 = r11.g(r0)     // Catch: java.lang.Throwable -> Lba
            roam.a.d.a.h r1 = r8.a     // Catch: java.lang.Throwable -> Lba
            r1.r = r0     // Catch: java.lang.Throwable -> Lba
            java.lang.String r0 = r3.a()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r1 = r3.r     // Catch: java.lang.Throwable -> Lba
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> Lba
            if (r1 == 0) goto La3
            java.io.File r1 = r3.w     // Catch: java.lang.Throwable -> Lba
            java.lang.String r11 = r11.g(r1)     // Catch: java.lang.Throwable -> Lba
            r3.r = r11     // Catch: java.lang.Throwable -> Lba
            if (r11 != 0) goto La3
            java.lang.String r11 = ""
            r3.r = r11     // Catch: java.lang.Throwable -> Lba
        La3:
            java.lang.String r11 = r3.r     // Catch: java.lang.Throwable -> Lba
            boolean r11 = r0.equalsIgnoreCase(r11)     // Catch: java.lang.Throwable -> Lba
            if (r11 != 0) goto Lae
            r11 = 1041(0x411, float:1.459E-42)
            goto Lb0
        Lae:
            r11 = 512(0x200, float:7.17E-43)
        Lb0:
            r8.f(r10)
            r8.f(r2)
            r8.f(r9)
            return r11
        Lba:
            r11 = move-exception
            r8.f(r10)
            r8.f(r2)
            r8.f(r9)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.d.a.k.t(java.io.InputStream, java.io.RandomAccessFile, boolean):int");
    }
}

package roam.b.c.a.a.m.b2;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.roamexplore.WebViewActionActivity;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONArray;
import org.json.JSONObject;
import roam.b.c.a.a.m.k;
import roam.b.c.a.a.m.k1;
import roam.b.c.a.a.m.q;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public Activity a;
    public ValueCallback<Uri> b;
    public ValueCallback<Uri[]> c;
    public boolean d;
    public WebChromeClient.FileChooserParams e;
    public f f;
    public boolean g;
    public WebView h;
    public k1 k;
    public WeakReference<roam.b.c.a.a.m.e> l;
    public String m;
    public boolean i = false;
    public boolean j = false;
    public WebViewActionActivity.b n = new a(this);

    public class a implements WebViewActionActivity.b {
        public final d a;

        public a(d dVar) {
            this.a = dVar;
        }

        public void a(String[] strArr, int[] iArr, Bundle bundle) {
            boolean zK = q.k(this.a.a, Arrays.asList(strArr));
            d dVar = this.a;
            int i = bundle.getInt("KEY_FROM_INTENTION");
            Objects.requireNonNull(dVar);
            if (i == 5) {
                if (zK) {
                    dVar.g();
                    return;
                }
                dVar.a();
                if (dVar.l.get() != null) {
                    dVar.l.get().k(k.c, "Storage", "Open file chooser");
                    return;
                }
                return;
            }
            if (i == 2) {
                if (zK) {
                    dVar.d();
                    return;
                }
                dVar.a();
                if (dVar.l.get() != null) {
                    dVar.l.get().k(k.a, "Camera", "Take photo");
                }
            }
        }
    }

    public static final class b implements Handler.Callback {
        public ValueCallback<Uri[]> a;
        public Uri[] b;
        public WeakReference<roam.b.c.a.a.m.e> c;

        public class a implements Runnable {
            public final Message a;
            public final b b;

            public a(b bVar, Message message) {
                this.b = bVar;
                this.a = message;
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = this.b;
                ValueCallback<Uri[]> valueCallback = bVar.a;
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(bVar.b);
                }
                WeakReference<roam.b.c.a.a.m.e> weakReference = bVar.c;
                if (weakReference == null || weakReference.get() == null) {
                    return;
                }
                bVar.c.get().c();
            }
        }

        public b(ValueCallback valueCallback, Uri[] uriArr, WeakReference weakReference, roam.b.c.a.a.m.b2.b bVar) {
            this.a = valueCallback;
            this.b = uriArr;
            this.c = weakReference;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            q.n(new a(this, message));
            return false;
        }
    }

    public static final class c {
        public Activity a;
        public ValueCallback<Uri> b;
        public ValueCallback<Uri[]> c;
        public WebChromeClient.FileChooserParams e;
        public WebView g;
        public k1 h;
        public Handler.Callback j;
        public boolean d = false;
        public boolean f = false;
        public String i = "*/*";
    }

    /* JADX INFO: renamed from: roam.b.c.a.a.m.b2.d$d, reason: collision with other inner class name */
    public static class C0024d extends Thread {
        public WeakReference<f> a;
        public String[] b;

        public C0024d(f fVar, String[] strArr, roam.b.c.a.a.m.b2.b bVar) {
            super("agentweb-thread");
            this.a = new WeakReference<>(fVar);
            this.b = strArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                String strC = d.c(d.b(this.b));
                WeakReference<f> weakReference = this.a;
                if (weakReference == null || weakReference.get() == null) {
                    return;
                }
                this.a.get().a(strC);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class e implements Runnable {
        public String a;
        public Queue<roam.b.c.a.a.m.b2.e> b;
        public CountDownLatch c;
        public int d;

        public e(String str, Queue<roam.b.c.a.a.m.b2.e> queue, CountDownLatch countDownLatch, int i) {
            this.a = str;
            this.b = queue;
            this.c = countDownLatch;
            this.d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            FileInputStream fileInputStream;
            ByteArrayOutputStream byteArrayOutputStream;
            Throwable th;
            File file;
            byte[] bArr;
            FileInputStream fileInputStream2 = null;
            try {
                file = new File(this.a);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
            }
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream = null;
                    th = th;
                    try {
                        th.printStackTrace();
                        q.c(fileInputStream);
                        q.c(byteArrayOutputStream);
                        this.c.countDown();
                    } catch (Throwable th4) {
                        q.c(fileInputStream);
                        q.c(byteArrayOutputStream);
                        this.c.countDown();
                        throw th4;
                    }
                }
                try {
                    bArr = new byte[1024];
                } catch (Throwable th5) {
                    th = th5;
                    th.printStackTrace();
                    q.c(fileInputStream);
                }
                while (true) {
                    int i = fileInputStream.read(bArr, 0, 1024);
                    if (i == -1) {
                        break;
                    } else {
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                    q.c(byteArrayOutputStream);
                    this.c.countDown();
                }
                this.b.offer(new roam.b.c.a.a.m.b2.e(this.d, file.getAbsolutePath(), Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0)));
                fileInputStream2 = fileInputStream;
            } else {
                byteArrayOutputStream = null;
            }
            q.c(fileInputStream2);
            q.c(byteArrayOutputStream);
            this.c.countDown();
        }
    }

    public static class f {
        public WeakReference<Handler.Callback> a;

        public f(Handler.Callback callback) {
            this.a = null;
            this.a = new WeakReference<>(callback);
        }

        public void a(String str) {
            WeakReference<Handler.Callback> weakReference = this.a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.a.get().handleMessage(Message.obtain(null, 2077613503, str));
        }
    }

    public static final class g implements Runnable {
        public String a;
        public Handler.Callback b;

        public g(String str, Handler.Callback callback, roam.b.c.a.a.m.b2.b bVar) {
            this.a = str;
            this.b = callback;
        }

        @Override // java.lang.Runnable
        public void run() {
            Handler.Callback callback;
            if (TextUtils.isEmpty(this.a) || !new File(this.a).exists()) {
                Handler.Callback callback2 = this.b;
                if (callback2 != null) {
                    callback2.handleMessage(Message.obtain((Handler) null, -1));
                    return;
                }
                return;
            }
            int i = 0;
            while (true) {
                if (i > 8000) {
                    break;
                }
                i += 300;
                SystemClock.sleep(300L);
                if (new File(this.a).length() > 0) {
                    Handler.Callback callback3 = this.b;
                    if (callback3 != null) {
                        callback3.handleMessage(Message.obtain((Handler) null, 1));
                        this.b = null;
                    }
                }
            }
            if (i > 8000 && (callback = this.b) != null) {
                callback.handleMessage(Message.obtain((Handler) null, -1));
            }
            this.b = null;
            this.a = null;
        }
    }

    public d(c cVar) {
        this.d = false;
        this.g = false;
        this.l = null;
        this.m = "*/*";
        this.a = cVar.a;
        this.b = cVar.b;
        this.c = cVar.c;
        this.d = cVar.d;
        boolean z = cVar.f;
        this.g = z;
        this.e = cVar.e;
        if (z) {
            this.f = new f(cVar.j);
        }
        this.h = cVar.g;
        this.k = cVar.h;
        this.m = cVar.i;
        this.l = new WeakReference<>(q.f(this.h));
    }

    public static Queue<roam.b.c.a.a.m.b2.e> b(String[] strArr) throws InterruptedException {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        int i = 1;
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors() + 1;
        if (strArr.length <= iAvailableProcessors) {
            iAvailableProcessors = strArr.length;
        }
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(iAvailableProcessors);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        CountDownLatch countDownLatch = new CountDownLatch(strArr.length);
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                countDownLatch.countDown();
            } else {
                executorServiceNewFixedThreadPool.execute(new e(str, linkedBlockingQueue, countDownLatch, i));
                i++;
            }
        }
        countDownLatch.await();
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorServiceNewFixedThreadPool;
        if (threadPoolExecutor.isShutdown()) {
            return linkedBlockingQueue;
        }
        threadPoolExecutor.shutdownNow();
        return linkedBlockingQueue;
    }

    public static String c(Collection<roam.b.c.a.a.m.b2.e> collection) {
        if (collection == null || collection.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (roam.b.c.a.a.m.b2.e eVar : collection) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("contentPath", eVar.b);
                jSONObject.put("fileBase64", eVar.c);
                jSONObject.put("mId", eVar.a);
                jSONArray.put(jSONObject);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return jSONArray + "";
    }

    public final void a() {
        if (this.g) {
            this.f.a(null);
            return;
        }
        ValueCallback<Uri> valueCallback = this.b;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
        }
        ValueCallback<Uri[]> valueCallback2 = this.c;
        if (valueCallback2 != null) {
            valueCallback2.onReceiveValue(null);
        }
    }

    public final void d() {
        roam.b.c.a.a.m.f fVar = new roam.b.c.a.a.m.f();
        fVar.b = this.j ? 4 : 3;
        WebViewActionActivity.d = new roam.b.c.a.a.m.b2.b(this);
        WebViewActionActivity.a(this.a, fVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e() {
        /*
            r8 = this;
            boolean r0 = roam.b.c.a.a.m.q.m()
            if (r0 != 0) goto L10
            roam.b.c.a.a.m.b2.a r0 = new roam.b.c.a.a.m.b2.a
            r0.<init>(r8)
            roam.b.c.a.a.m.q.n(r0)
            goto La5
        L10:
            boolean r0 = r8.d
            java.lang.String r1 = "image/"
        */
        //  java.lang.String r2 = "*/"
        /*
            if (r0 == 0) goto L54
            android.webkit.WebChromeClient$FileChooserParams r0 = r8.e
            if (r0 == 0) goto L54
            java.lang.String[] r0 = r0.getAcceptTypes()
            if (r0 == 0) goto L54
            android.webkit.WebChromeClient$FileChooserParams r0 = r8.e
            java.lang.String[] r0 = r0.getAcceptTypes()
            int r3 = r0.length
            r4 = 0
            r5 = r4
        L2b:
            if (r5 >= r3) goto L51
            r6 = r0[r5]
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L36
            goto L4e
        L36:
            boolean r7 = r6.contains(r2)
            if (r7 != 0) goto L54
            boolean r7 = r6.contains(r1)
            if (r7 == 0) goto L43
            goto L54
        L43:
            java.lang.String r7 = "video/"
            boolean r6 = r6.contains(r7)
            if (r6 == 0) goto L4e
            r4 = 1
            r8.j = r4
        L4e:
            int r5 = r5 + 1
            goto L2b
        L51:
            if (r4 != 0) goto L54
            goto L6d
        L54:
            java.lang.String r0 = r8.m
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L71
            java.lang.String r0 = r8.m
            boolean r0 = r0.contains(r2)
            if (r0 != 0) goto L71
            java.lang.String r0 = r8.m
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L6d
            goto L71
        L6d:
            r8.g()
            goto La5
        L71:
            java.lang.ref.WeakReference<roam.b.c.a.a.m.e> r0 = r8.l
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto La5
            java.lang.ref.WeakReference<roam.b.c.a.a.m.e> r0 = r8.l
            java.lang.Object r0 = r0.get()
            roam.b.c.a.a.m.e r0 = (roam.b.c.a.a.m.e) r0
            android.webkit.WebView r1 = r8.h
            java.lang.String r2 = r1.getUrl()
            android.app.Activity r3 = r8.a
            r4 = 2131689500(0x7f0f001c, float:1.9008017E38)
            java.lang.String r3 = r3.getString(r4)
            android.app.Activity r4 = r8.a
            r5 = 2131689510(0x7f0f0026, float:1.9008037E38)
            java.lang.String r4 = r4.getString(r5)
            roam.b.c.a.a.m.b2.c r5 = new roam.b.c.a.a.m.b2.c
            r5.<init>(r8)
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}
            r0.l(r1, r2, r3, r5)
        La5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.b.c.a.a.m.b2.d.e():void");
    }

    public final Uri[] f(Intent intent) {
        String dataString = intent.getDataString();
        if (!TextUtils.isEmpty(dataString)) {
            return new Uri[]{Uri.parse(dataString)};
        }
        ClipData clipData = intent.getClipData();
        if (clipData == null || clipData.getItemCount() <= 0) {
            return null;
        }
        Uri[] uriArr = new Uri[clipData.getItemCount()];
        for (int i = 0; i < clipData.getItemCount(); i++) {
            uriArr[i] = clipData.getItemAt(i).getUri();
        }
        return uriArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0059 A[PHI: r3
  0x0059: PHI (r3v14 java.lang.String) = (r3v11 java.lang.String), (r3v13 java.lang.String) binds: [B:15:0x0057, B:18:0x006b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g() {
        /*
            r7 = this;
            roam.b.c.a.a.m.f r0 = new roam.b.c.a.a.m.f
            r0.<init>()
            r1 = 2
            r0.b = r1
            roam.b.c.a.a.m.b2.b r1 = new roam.b.c.a.a.m.b2.b
            r1.<init>(r7)
            com.roamexplore.WebViewActionActivity.d = r1
            android.app.Activity r1 = r7.a
            android.content.Intent r2 = new android.content.Intent
            android.app.Activity r3 = r7.a
            java.lang.Class<com.roamexplore.WebViewActionActivity> r4 = com.roamexplore.WebViewActionActivity.class
            r2.<init>(r3, r4)
            java.lang.String r3 = "KEY_ACTION"
            android.content.Intent r0 = r2.putExtra(r3, r0)
            boolean r2 = r7.d
            java.lang.String r3 = "android.intent.action.OPEN_DOCUMENT"
        */
        //  java.lang.String r4 = "*/*"
        /*
            if (r2 == 0) goto L71
            android.webkit.WebChromeClient$FileChooserParams r2 = r7.e
            if (r2 == 0) goto L71
            android.content.Intent r2 = r2.createIntent()
            if (r2 == 0) goto L71
            java.lang.String r5 = r2.getAction()
            java.lang.String r6 = "android.intent.action.GET_CONTENT"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L96
            r2.setAction(r3)
            android.webkit.WebChromeClient$FileChooserParams r3 = r7.e
            java.lang.String[] r3 = r3.getAcceptTypes()
            if (r3 == 0) goto L96
            int r5 = r3.length
            if (r5 == 0) goto L96
            r5 = 0
            r3 = r3[r5]
            android.webkit.MimeTypeMap r5 = android.webkit.MimeTypeMap.getSingleton()
            boolean r5 = r5.hasMimeType(r3)
            if (r5 == 0) goto L5b
        L59:
            r4 = r3
            goto L6d
        L5b:
            java.lang.String r3 = android.webkit.MimeTypeMap.getFileExtensionFromUrl(r3)
            android.webkit.MimeTypeMap r5 = android.webkit.MimeTypeMap.getSingleton()
            java.lang.String r3 = r5.getMimeTypeFromExtension(r3)
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 == 0) goto L59
        L6d:
            r2.setType(r4)
            goto L96
        L71:
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            r2.setAction(r3)
            java.lang.String r3 = "android.intent.category.OPENABLE"
            r2.addCategory(r3)
            java.lang.String r3 = r7.m
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L87
            goto L89
        L87:
            java.lang.String r4 = r7.m
        L89:
            r2.setType(r4)
            r3 = 1
            r2.addFlags(r3)
            java.lang.String r3 = ""
            android.content.Intent r2 = android.content.Intent.createChooser(r2, r3)
        L96:
            java.lang.String r3 = "KEY_FILE_CHOOSER_INTENT"
            android.content.Intent r0 = r0.putExtra(r3, r2)
            r1.startActivity(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.b.c.a.a.m.b2.d.g():void");
    }
}

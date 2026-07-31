package roam.b.c.a.a.m;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import androidx.collection.ArrayMap;
import com.roamexplore.WebViewActionActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.roam.R;

/* JADX INFO: loaded from: classes.dex */
public class x implements DownloadListener {
    public static final String g = x.class.getSimpleName();
    public static Handler h = new Handler(Looper.getMainLooper());
    public Context a;
    public ConcurrentHashMap<String, roam.a.d.a.q> b = new ConcurrentHashMap<>();
    public WeakReference<Activity> c;
    public k1 d;
    public WeakReference<e> e;
    public boolean f;

    public class a implements Runnable {
        public final String a;
        public final String b;
        public final String c;
        public final String d;
        public final long e;
        public final x f;

        public a(x xVar, String str, String str2, String str3, String str4, long j) {
            this.f = xVar;
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            x xVar = this.f;
            String str = this.a;
            if (xVar.c.get() == null || xVar.c.get().isFinishing()) {
                return;
            }
            k1 k1Var = xVar.d;
            if (k1Var == null || !k1Var.a(str, k.c, "download")) {
                xVar.b.put(str, xVar.b(str));
                ArrayList arrayList = (ArrayList) xVar.a();
                if (arrayList.isEmpty()) {
                    xVar.d(str);
                    return;
                }
                f fVarA = f.a((String[]) arrayList.toArray(new String[0]));
                WebViewActionActivity.c = new y(xVar, str);
                WebViewActionActivity.a(xVar.c.get(), fVarA);
            }
        }
    }

    public class b extends roam.a.d.a.f {
        public final x a;

        public b(x xVar) {
            this.a = xVar;
        }

        @Override // roam.a.d.a.e
        public boolean b(Throwable th, Uri uri, String str, roam.a.d.a.o oVar) {
            this.a.b.remove(str);
            return false;
        }
    }

    public x(Activity activity, WebView webView, k1 k1Var) {
        this.c = null;
        this.d = null;
        this.a = activity.getApplicationContext();
        this.c = new WeakReference<>(activity);
        this.d = k1Var;
        this.e = new WeakReference<>(q.f(webView));
        try {
            roam.a.d.a.d dVar = roam.a.d.a.d.b;
            Context context = this.a;
            Objects.requireNonNull(dVar);
            if (context != null) {
                roam.a.d.a.d.c = context.getApplicationContext();
            }
            roam.a.d.a.q.c(roam.a.d.a.d.c);
            this.f = true;
        } catch (Throwable th) {
            String str = i.a;
            this.f = false;
        }
    }

    public List<String> a() {
        ArrayList arrayList = new ArrayList();
        Activity activity = this.c.get();
        String[] strArr = k.c;
        if (!q.l(activity, strArr)) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return arrayList;
    }

    public roam.a.d.a.q b(String str) {
        Objects.requireNonNull(roam.a.d.a.d.b);
        Context context = roam.a.d.a.d.c;
        Objects.requireNonNull(context, "Context can't be null . ");
        roam.a.d.a.q qVarC = roam.a.d.a.q.c(context);
        roam.a.d.a.h hVar = qVarC.a;
        hVar.g = str;
        hVar.b = true;
        qVarC.a();
        return qVarC;
    }

    public void c(String str) {
        try {
            roam.a.d.a.d dVar = roam.a.d.a.d.b;
            dVar.b(str);
            String str2 = i.a;
            if (dVar.b(str)) {
                if (this.e.get() != null) {
                    this.e.get().n(this.c.get().getString(R.string.r), "preDownload");
                    return;
                }
                return;
            }
            roam.a.d.a.q qVar = this.b.get(str);
            String cookie = CookieManager.getInstance() == null ? null : CookieManager.getInstance().getCookie(str);
            roam.a.d.a.h hVar = qVar.a;
            if (hVar.k == null) {
                hVar.k = new ArrayMap();
            }
            qVar.a.k.put("Cookie", cookie);
            e(qVar);
        } catch (Throwable th) {
            String str3 = i.a;
        }
    }

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Unknown Source)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public void d(java.lang.String r3) {
        /*
            r2 = this;
            java.util.concurrent.ConcurrentHashMap<java.lang.String, roam.a.d.a.q> r0 = r2.b
            java.lang.Object r0 = r0.get(r3)
            roam.a.d.a.q r0 = (roam.a.d.a.q) r0
            if (r0 == 0) goto L10
            roam.a.d.a.h r0 = r0.a
            boolean r0 = r0.a
            if (r0 != 0) goto L61
        L10:
            android.content.Context r0 = r2.a
            java.lang.String r1 = roam.b.c.a.a.m.q.a
            java.lang.String r1 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()
            if (r0 != 0) goto L23
            goto L61
        L23:
            int r1 = r0.getType()
            if (r1 == 0) goto L32
            r0 = 1
            if (r1 == r0) goto L61
            r0 = 6
            if (r1 == r0) goto L61
            r0 = 9
            goto L61
        L32:
            int r0 = r0.getSubtype()
            switch(r0) {
                case 1: goto L3d;
                case 2: goto L3d;
                case 3: goto L3d;
                case 4: goto L3d;
                case 5: goto L3d;
                case 6: goto L3d;
                default: goto L39;
            }
        L39:
            switch(r0) {
                case 12: goto L3d;
                case 13: goto L3d;
                case 14: goto L3d;
                case 15: goto L3d;
                default: goto L3c;
            }
        L3c:
            goto L61
        L3d:
            java.lang.ref.WeakReference<android.app.Activity> r0 = r2.c
            java.lang.Object r0 = r0.get()
            android.app.Activity r0 = (android.app.Activity) r0
            if (r0 == 0) goto L64
            boolean r0 = r0.isFinishing()
            if (r0 == 0) goto L4e
            goto L64
        L4e:
            java.lang.ref.WeakReference<roam.b.c.a.a.m.e> r0 = r2.e
            java.lang.Object r0 = r0.get()
            roam.b.c.a.a.m.e r0 = (roam.b.c.a.a.m.e) r0
            if (r0 == 0) goto L64
            roam.b.c.a.a.m.z r1 = new roam.b.c.a.a.m.z
            r1.<init>(r2, r3)
            r0.d(r3, r1)
            goto L64
        L61:
            r2.c(r3)
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.b.c.a.a.m.x.d(java.lang.String):void");
    }

    public void e(roam.a.d.a.q qVar) {
        qVar.b(new b(this));
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        if (this.f) {
            h.post(new a(this, str, str2, str3, str4, j));
        } else {
            String str5 = i.a;
        }
    }
}

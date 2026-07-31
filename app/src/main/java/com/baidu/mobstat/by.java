package com.baidu.mobstat;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import com.baidu.mobstat.cd;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class by {
    private static volatile int a = 0;
    private static final by k = new by();
    private WeakReference<Activity> b;
    private int c;
    private boolean d;
    private JSONObject e;
    private boolean f;
    private final Handler h;
    private a i;
    private final Handler g = new Handler(Looper.getMainLooper());
    private bx j = new bx();

    static class a implements ViewTreeObserver.OnGlobalLayoutListener, Runnable {
        private final WeakReference<View> c;
        private final cd d;
        private final Handler e;
        private final Handler f;
        private JSONObject g;
        private WeakReference<Activity> h;
        private boolean i;
        private boolean j;
        private boolean k;
        private Runnable l = null;
        private boolean b = true;
        private volatile boolean a = false;

        public a(Activity activity, View view, cd cdVar, Handler handler, Handler handler2, JSONObject jSONObject, boolean z, boolean z2, boolean z3) {
            this.h = new WeakReference<>(activity);
            this.g = jSONObject;
            this.d = cdVar;
            this.c = new WeakReference<>(view);
            this.e = handler;
            this.f = handler2;
            this.i = z;
            this.j = z2;
            this.k = z3;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(this);
            }
            run();
        }

        private void a(final cd cdVar, Handler handler) {
            if (cdVar == null || handler == null) {
                return;
            }
            handler.postDelayed(new Runnable() { // from class: com.baidu.mobstat.by.a.2
                @Override // java.lang.Runnable
                public void run() {
                    cdVar.a();
                }
            }, 500L);
        }

        private void a(final WeakReference<Activity> weakReference, final JSONObject jSONObject, final cd cdVar, Handler handler, final boolean z) {
            if (cdVar == null || handler == null) {
                return;
            }
            Runnable runnable = new Runnable() { // from class: com.baidu.mobstat.by.a.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!bt.c()) {
                        if (bw.c().b() && a.this.k) {
                            bw.c().a("no touch, skip doViewVisit");
                        }
                        if (ca.c().b()) {
                            ca.c().a("no touch, skip doViewVisit");
                            return;
                        }
                        return;
                    }
                    if (by.c() >= 3) {
                        bt.a(false);
                    }
                    Activity activity = (Activity) weakReference.get();
                    if (activity != null) {
                        bl.c(activity, z);
                        cdVar.a(activity, jSONObject, z);
                    }
                }
            };
            Runnable runnable2 = this.l;
            if (runnable2 != null) {
                handler.removeCallbacks(runnable2);
            }
            this.l = runnable;
            handler.postDelayed(runnable, 500L);
        }

        private void b() {
            if (this.b) {
                View view = this.c.get();
                if (view != null) {
                    ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    }
                }
                a(this.d, this.f);
            }
            this.b = false;
        }

        public void a() {
            if (this.a) {
                return;
            }
            this.a = true;
            this.e.post(this);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (CooperService.instance().isCloseTrace()) {
                b();
            } else {
                run();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.b) {
                if (this.c.get() == null || this.a) {
                    b();
                    return;
                }
                if (bw.c().b() && this.k) {
                    bw.c().a("onGlobalLayout");
                }
                if (ca.c().b()) {
                    ca.c().a("onGlobalLayout");
                }
                if (ay.b()) {
                    if (bt.c()) {
                        Activity activity = this.h.get();
                        if (activity != null) {
                            by.b(activity, this.i, this.k);
                            a(this.h, this.g, this.d, this.f, this.j);
                        }
                    } else {
                        if (bw.c().b() && this.k) {
                            bw.c().a("no touch, skip onGlobalLayout");
                        }
                        if (ca.c().b()) {
                            ca.c().a("no touch, skip onGlobalLayout");
                        }
                    }
                }
                this.e.removeCallbacks(this);
            }
        }
    }

    private by() {
        HandlerThread handlerThread = new HandlerThread("visitorThread");
        handlerThread.start();
        this.h = new Handler(handlerThread.getLooper());
    }

    public static by a() {
        return k;
    }

    private static void a(Activity activity, View view, boolean z) {
        if (view == null || cc.c(activity, view)) {
            return;
        }
        if (!(view instanceof WebView)) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    a(activity, viewGroup.getChildAt(i), z);
                }
                return;
            }
            return;
        }
        WebView webView = (WebView) view;
        if (webView.getTag(-96001) == null) {
            if (bw.c().b() && z) {
                bw.c().a("webview auto set " + activity.getClass().getName());
            }
            if (ca.c().b()) {
                ca.c().a("webview auto set " + activity.getClass().getName());
            }
            StatService.trackWebView(activity.getApplicationContext(), webView, null);
        }
    }

    private boolean a(Activity activity, int i) {
        WeakReference<Activity> weakReference = this.b;
        return weakReference != null && weakReference.get() == activity && this.c == i;
    }

    public static void b() {
        a = 0;
    }

    private static void b(Activity activity, boolean z) {
        a(activity, cc.a(activity), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Activity activity, boolean z, boolean z2) {
        if (z) {
            b(activity, z2);
        }
    }

    static /* synthetic */ int c() {
        int i = a + 1;
        a = i;
        return i;
    }

    public void a(Activity activity, boolean z) {
        bl.b(activity, !z);
        if (a(activity, 2)) {
            return;
        }
        this.b = new WeakReference<>(activity);
        this.c = 2;
        a aVar = this.i;
        if (aVar != null) {
            aVar.a();
        }
    }

    public void a(Activity activity, boolean z, JSONObject jSONObject, boolean z2) {
        a aVar;
        bl.a(activity, !z);
        if (!this.d) {
            this.d = z2;
        }
        if (z) {
            this.f = z;
            this.e = jSONObject;
        }
        if (a(activity, 1)) {
            return;
        }
        if (this.b != null && (aVar = this.i) != null) {
            aVar.a();
        }
        WeakReference<Activity> weakReference = new WeakReference<>(activity);
        this.b = weakReference;
        this.c = 1;
        this.i = new a(activity, cc.a(activity), new cd.a(1, weakReference, this.j), this.g, this.h, this.e, this.d, true, this.f);
    }
}

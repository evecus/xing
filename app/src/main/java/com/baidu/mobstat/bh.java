package com.baidu.mobstat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.constraintlayout.motion.widget.Key;
import com.android.cglib.dx.io.Opcodes;
import com.baidu.mobstat.cl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bh {
    private static volatile String c;
    private static volatile int d = 0;
    private final Handler b = new Handler(Looper.getMainLooper());
    private final b a = new b();

    static class a {
        private final Paint b = new Paint(2);
        private Bitmap a = null;

        /* JADX WARN: Removed duplicated region for block: B:19:0x002b A[Catch: all -> 0x003a, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000b, B:17:0x0027, B:19:0x002b, B:9:0x0013, B:14:0x0020, B:16:0x0024, B:13:0x001e), top: B:27:0x0001, inners: #0 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public synchronized void a(int r2, int r3, int r4, android.graphics.Bitmap r5) {
            /*
                r1 = this;
                monitor-enter(r1)
                android.graphics.Bitmap r0 = r1.a     // Catch: java.lang.Throwable -> L3a
                if (r0 == 0) goto L13
                int r0 = r0.getWidth()     // Catch: java.lang.Throwable -> L3a
                if (r0 != r2) goto L13
                android.graphics.Bitmap r0 = r1.a     // Catch: java.lang.Throwable -> L3a
                int r0 = r0.getHeight()     // Catch: java.lang.Throwable -> L3a
                if (r0 == r3) goto L27
            L13:
                android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.RGB_565     // Catch: java.lang.OutOfMemoryError -> L1c java.lang.Throwable -> L3a
                android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r2, r3, r0)     // Catch: java.lang.OutOfMemoryError -> L1c java.lang.Throwable -> L3a
                r1.a = r2     // Catch: java.lang.OutOfMemoryError -> L1c java.lang.Throwable -> L3a
                goto L20
            L1c:
                r2 = move-exception
                r2 = 0
                r1.a = r2     // Catch: java.lang.Throwable -> L3a
            L20:
                android.graphics.Bitmap r2 = r1.a     // Catch: java.lang.Throwable -> L3a
                if (r2 == 0) goto L27
                r2.setDensity(r4)     // Catch: java.lang.Throwable -> L3a
            L27:
                android.graphics.Bitmap r2 = r1.a     // Catch: java.lang.Throwable -> L3a
                if (r2 == 0) goto L38
                android.graphics.Canvas r2 = new android.graphics.Canvas     // Catch: java.lang.Throwable -> L3a
                android.graphics.Bitmap r3 = r1.a     // Catch: java.lang.Throwable -> L3a
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L3a
                android.graphics.Paint r3 = r1.b     // Catch: java.lang.Throwable -> L3a
                r4 = 0
                r2.drawBitmap(r5, r4, r4, r3)     // Catch: java.lang.Throwable -> L3a
            L38:
                monitor-exit(r1)
                return
            L3a:
                r2 = move-exception
                monitor-exit(r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.bh.a.a(int, int, int, android.graphics.Bitmap):void");
        }
    }

    static class b implements Callable<List<c>> {
        private Activity a;
        private final int e = Opcodes.AND_LONG;
        private final DisplayMetrics c = new DisplayMetrics();
        private final List<c> b = new ArrayList();
        private final a d = new a();

        private void a(c cVar) {
            Bitmap drawingCache;
            View view = cVar.b;
            Boolean boolValueOf = null;
            try {
                Method declaredMethod = View.class.getDeclaredMethod("createSnapshot", Bitmap.Config.class, Integer.TYPE, Boolean.TYPE);
                declaredMethod.setAccessible(true);
                drawingCache = (Bitmap) declaredMethod.invoke(view, Bitmap.Config.RGB_565, -1, false);
            } catch (ClassCastException e) {
                bv.c().d("autotrace: createSnapshot didn't return a bitmap", e);
                drawingCache = null;
            } catch (IllegalAccessException e2) {
                bv.c().d("autotrace: Can't access createSnapshot, using drawCache", e2);
                drawingCache = null;
            } catch (IllegalArgumentException e3) {
                bv.c().b("autotrace: Can't call createSnapshot with arguments", e3);
                drawingCache = null;
            } catch (NoSuchMethodException e4) {
                bv.c().a("autotrace: Can't call createSnapshot, will use drawCache", e4);
                drawingCache = null;
            } catch (InvocationTargetException e5) {
                bv.c().d("autotrace: Exception when calling createSnapshot", e5);
                drawingCache = null;
            } catch (Exception e6) {
                bv.c().d(" autotrace:createSnapshot encounter exception", e6);
                drawingCache = null;
            }
            if (drawingCache == null) {
                try {
                    boolValueOf = Boolean.valueOf(view.isDrawingCacheEnabled());
                    view.setDrawingCacheEnabled(true);
                    view.buildDrawingCache(true);
                    drawingCache = view.getDrawingCache();
                } catch (Exception e7) {
                    bv.c().a("autotrace: Can't take a bitmap snapshot of view " + view + ", skipping for now.", e7);
                }
            }
            if (drawingCache != null) {
                int density = drawingCache.getDensity();
                f = density != 0 ? 160.0f / density : 1.0f;
                int width = drawingCache.getWidth();
                int height = drawingCache.getHeight();
                int width2 = (int) (((double) (drawingCache.getWidth() * f)) + 0.5d);
                int height2 = (int) (((double) (drawingCache.getHeight() * f)) + 0.5d);
                if (width > 0 && height > 0 && width2 > 0 && height2 > 0) {
                    this.d.a(width2, height2, Opcodes.AND_LONG, drawingCache);
                }
            }
            if (boolValueOf != null && !boolValueOf.booleanValue()) {
                view.setDrawingCacheEnabled(false);
            }
            cVar.d = f;
            cVar.c = this.d;
        }

        private void b() {
            bc.a(this.a, false);
        }

        private void c() {
            bc.a(this.a, true);
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<c> call() throws Exception {
            this.b.clear();
            HashSet<Activity> hashSet = new HashSet(1);
            hashSet.add(this.a);
            for (Activity activity : hashSet) {
                String canonicalName = activity.getClass().getCanonicalName();
                View viewB = cc.b(activity);
                activity.getWindowManager().getDefaultDisplay().getMetrics(this.c);
                this.b.add(new c(canonicalName, viewB));
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                c cVar = this.b.get(i);
                b();
                a(cVar);
                c();
            }
            return this.b;
        }

        public void a(Activity activity) {
            this.a = activity;
        }
    }

    static class c {
        public final String a;
        public final View b;
        public a c = null;
        public float d = 1.0f;

        public c(String str, View view) {
            this.a = str;
            this.b = view;
        }
    }

    public static void a() {
        d = 0;
    }

    private void a(Activity activity, View view, JSONArray jSONArray, String str, View view2) throws Exception {
        Rect rectE;
        String strA;
        long jLongValue;
        String strOptString;
        Object jSONArray2;
        if (view == null || (rectE = cc.e(view)) == null || bc.a(view)) {
            return;
        }
        String strM = cc.m(view);
        if (TextUtils.isEmpty(strM) || cc.c(activity, view)) {
            return;
        }
        String strC = cc.c(view);
        if (TextUtils.isEmpty(strC)) {
            String strA2 = cc.a(view, str);
            strA = TextUtils.isEmpty(strA2) ? cc.a(view, view2) : strA2;
        } else {
            strA = strC;
        }
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        try {
            jLongValue = Long.valueOf(strA).longValue();
        } catch (Exception e) {
            jLongValue = -1;
        }
        if (jLongValue < 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray3 = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("p", strM);
        jSONObject2.put("i", strA);
        String strB = cc.b(view);
        jSONObject2.put("t", strB);
        jSONArray3.put(jSONObject2);
        jSONObject.put(Config.FEED_LIST_ITEM_PATH, jSONArray3);
        jSONObject.put("type", strB);
        jSONObject.put("value", cc.a(view));
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(Config.EVENT_HEAT_X, bb.a(activity, rectE.left));
        jSONObject3.put("y", bb.a(activity, rectE.top));
        jSONObject3.put(Config.DEVICE_WIDTH, bb.a(activity, rectE.width()));
        jSONObject3.put("h", bb.a(activity, rectE.height()));
        jSONObject.put("frame", jSONObject3);
        jSONObject.put(Key.ALPHA, cc.i(view));
        jSONObject.put("page", activity.getClass().getName());
        jSONObject.put("z", cc.j(view));
        boolean z = view instanceof WebView;
        if (z) {
            String strA3 = ce.a(activity, (WebView) view, rectE);
            if (TextUtils.isEmpty(strA3)) {
                strOptString = "";
                jSONArray2 = null;
            } else {
                JSONObject jSONObject4 = new JSONObject(strA3);
                strOptString = jSONObject4.optString("url");
                jSONArray2 = jSONObject4.optJSONArray("objects");
            }
            if (jSONArray2 == null) {
                jSONArray2 = new JSONArray();
            }
            jSONObject.put("child", jSONArray2);
            if (TextUtils.isEmpty(strOptString)) {
                jSONObject.put("url", "/");
            } else {
                jSONObject.put("url", strOptString);
            }
        }
        jSONObject.put("edit", cc.b(view, str) ? 1 : 0);
        jSONArray.put(jSONObject);
        if (z) {
            return;
        }
        if (!(view instanceof ViewGroup)) {
            jSONObject.put("child", new JSONArray());
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        JSONArray jSONArray4 = new JSONArray();
        jSONObject.put("child", jSONArray4);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            a(activity, viewGroup.getChildAt(i), jSONArray4, strB, view2);
        }
    }

    public static void b() {
        c = "";
    }

    private JSONArray c(Activity activity) throws Exception {
        JSONArray jSONArray = new JSONArray();
        View viewA = cc.a(activity);
        a(activity, viewA, jSONArray, "", viewA);
        return jSONArray;
    }

    public JSONObject a(Activity activity) {
        JSONObject jSONObject = null;
        if (activity == null) {
            return null;
        }
        try {
            if (!be.a()) {
                return null;
            }
            int i = d + 1;
            d = i;
            if (i >= 3) {
                be.a(false);
            }
            Bitmap bitmapB = b(activity);
            if (bitmapB == null) {
                return null;
            }
            JSONArray jSONArrayC = c(activity);
            String strA = cl.a.a(jSONArrayC.toString().getBytes());
            if (c != null && c.equals(strA)) {
                return null;
            }
            c = strA;
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("screenshot", cc.a(bitmapB));
                jSONObject2.put("hash", cc.b(bitmapB));
                new JSONObject();
                jSONObject2.put("page", activity.getClass().getName());
                jSONObject2.put("objects", jSONArrayC);
                return jSONObject2;
            } catch (Throwable th) {
                jSONObject = jSONObject2;
            }
        } catch (Throwable th2) {
        }
        return jSONObject;
    }

    public Bitmap b(Activity activity) {
        this.a.a(activity);
        FutureTask futureTask = new FutureTask(this.a);
        this.b.post(futureTask);
        List listEmptyList = Collections.emptyList();
        try {
            listEmptyList = (List) futureTask.get(2L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            bv.c().b("autotrace: Screenshot interrupted, no screenshot will be sent.", e);
        } catch (TimeoutException e2) {
            bv.c().c("autotrace: Screenshot took more than 2 second to be scheduled and executed. No screenshot will be sent.", e2);
        } catch (Exception e3) {
            bv.c().d("autotrace: Exception thrown during screenshot attempt", e3);
        }
        if (listEmptyList.size() == 0) {
            return null;
        }
        return ((c) listEmptyList.get(0)).c.a;
    }
}

package com.baidu.mobstat;

import android.app.Activity;
import android.view.View;
import com.baidu.mobstat.cb;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class cd implements cb.a {
    private cb a;

    public static class a extends cd {
        private WeakReference<Activity> a;
        private b b;
        private final WeakHashMap<View, C0007a> c = new WeakHashMap<>();

        /* JADX INFO: renamed from: com.baidu.mobstat.cd$a$a, reason: collision with other inner class name */
        class C0007a extends View.AccessibilityDelegate {
            private View.AccessibilityDelegate b;
            private View c;
            private volatile boolean d;
            private long e;
            private long f;

            public C0007a(WeakReference<Activity> weakReference, View view, String str, View.AccessibilityDelegate accessibilityDelegate, boolean z) {
                this.b = accessibilityDelegate;
                a.this.a = weakReference;
                this.c = view;
                this.d = z;
            }

            public View.AccessibilityDelegate a() {
                return this.b;
            }

            public void a(boolean z) {
                this.d = z;
            }

            @Override // android.view.View.AccessibilityDelegate
            public void sendAccessibilityEvent(View view, int i) {
                Activity activity;
                try {
                    if (CooperService.instance().isCloseTrace()) {
                        a.this.a();
                        return;
                    }
                    this.e = System.currentTimeMillis();
                    if (view == this.c && i == 1) {
                        if (bw.c().b() && this.d) {
                            bw.c().a("watch view  OnEvent:" + view.getClass().getName());
                        }
                        if (ca.c().b()) {
                            ca.c().a("watch view  OnEvent:" + view.getClass().getName());
                        }
                        if (a.this.a != null && (activity = (Activity) a.this.a.get()) != null) {
                            a.this.b.a(view, this.d, activity);
                        }
                    }
                    if (this.e - this.f < 100) {
                        return;
                    }
                    this.f = System.currentTimeMillis();
                    View.AccessibilityDelegate accessibilityDelegate = this.b;
                    if (accessibilityDelegate == null || (accessibilityDelegate instanceof C0007a) || accessibilityDelegate == this) {
                        super.sendAccessibilityEvent(view, i);
                    } else {
                        accessibilityDelegate.sendAccessibilityEvent(view, i);
                    }
                } catch (Throwable th) {
                    a.this.a();
                    CooperService.instance().setEnableAutoEvent(false);
                }
            }
        }

        public a(int i, WeakReference<Activity> weakReference, b bVar) {
            this.a = weakReference;
            this.b = bVar;
        }

        private View.AccessibilityDelegate a(View view) {
            try {
                return (View.AccessibilityDelegate) view.getClass().getMethod("getAccessibilityDelegate", new Class[0]).invoke(view, new Object[0]);
            } catch (Exception e) {
                return null;
            }
        }

        @Override // com.baidu.mobstat.cd
        public void a() {
            WeakHashMap<View, C0007a> weakHashMap = this.c;
            if (weakHashMap == null) {
                return;
            }
            for (Map.Entry<View, C0007a> entry : weakHashMap.entrySet()) {
                entry.getKey().setAccessibilityDelegate(entry.getValue().a());
            }
            this.c.clear();
        }

        @Override // com.baidu.mobstat.cb.a
        public void a(View view, boolean z) {
            a(this.a, view, cc.a(view), z);
        }

        public void a(WeakReference<Activity> weakReference, View view, String str, boolean z) {
            View.AccessibilityDelegate accessibilityDelegateA = a(view);
            if (accessibilityDelegateA instanceof C0007a) {
                ((C0007a) accessibilityDelegateA).a(z);
                return;
            }
            C0007a c0007a = new C0007a(weakReference, view, str, accessibilityDelegateA, z);
            view.setAccessibilityDelegate(c0007a);
            this.c.put(view, c0007a);
        }
    }

    public interface b {
        void a(View view, boolean z, Activity activity);
    }

    public abstract void a();

    public void a(Activity activity, JSONObject jSONObject, boolean z) {
        if (this.a == null) {
            cb cbVar = new cb(activity, this, z);
            this.a = cbVar;
            cbVar.a(jSONObject);
        }
        this.a.a(activity);
    }
}

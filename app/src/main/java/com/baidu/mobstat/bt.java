package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.webkit.WebView;
import com.baidu.mobstat.bf;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bt {
    private static final bt k = new bt();
    private static volatile boolean l = true;
    private Context a;
    private Activity b;
    private volatile boolean c;
    private volatile boolean d;
    private volatile String e;
    private long f;
    private long g;
    private String h;
    private PointF i;
    private by j = by.a();

    private bt() {
    }

    private Window.Callback a(Window.Callback callback) {
        while (callback != null && (callback instanceof bf)) {
            callback = ((bf) callback).a();
        }
        return callback;
    }

    public static bt a() {
        return k;
    }

    public static void a(boolean z) {
        if (z) {
            by.b();
        }
        l = z;
    }

    private void b(WebView webView, String str, ce ceVar) {
        if (ceVar == null) {
            return;
        }
        ceVar.a(this.b, webView, str, (JSONObject) null, false);
    }

    private void c(Activity activity) {
        Window window;
        Window.Callback callback;
        if (activity == null || (window = activity.getWindow()) == null || (callback = window.getCallback()) == null) {
            return;
        }
        window.setCallback(new bf(callback, new bf.a() { // from class: com.baidu.mobstat.bt.1
            @Override // com.baidu.mobstat.bf.a
            public void a(KeyEvent keyEvent) {
                bl.a(keyEvent);
            }

            @Override // com.baidu.mobstat.bf.a
            public void a(MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()) {
                    case 1:
                        bt.a(true);
                        if (bt.this.i == null) {
                            bt.this.i = new PointF();
                        }
                        bt.this.i.set(motionEvent.getRawX(), motionEvent.getRawY());
                        break;
                }
            }
        }));
    }

    public static boolean c() {
        return l;
    }

    private void d(Activity activity) {
        Window window;
        if (activity == null || (window = activity.getWindow()) == null) {
            return;
        }
        window.setCallback(a(window.getCallback()));
    }

    private boolean d() {
        return !TextUtils.isEmpty(this.h);
    }

    private void e() {
        if (this.c) {
            return;
        }
        if (!this.d) {
            this.e = ch.a(this.a, ba.b);
            this.d = true;
        }
        if (this.f == 0) {
            this.f = cj.a().n(this.a);
            this.g = cj.a().o(this.a);
        }
        long j = this.g;
        if (!(this.d && TextUtils.isEmpty(this.e)) && System.currentTimeMillis() - this.f <= j) {
            return;
        }
        f();
    }

    private void f() {
        if (cp.p(this.a)) {
            Thread thread = new Thread(new Runnable() { // from class: com.baidu.mobstat.bt.2
                @Override // java.lang.Runnable
                public void run() {
                    if (bt.this.c) {
                        return;
                    }
                    boolean zA = bz.a(bt.this.a, bt.this.h, 1, false);
                    bt.this.c = true;
                    if (zA) {
                        bt btVar = bt.this;
                        btVar.e = ch.a(btVar.a, ba.b);
                    }
                }
            });
            thread.setName("downloadThread");
            thread.start();
        }
    }

    public void a(Activity activity) {
        if (d()) {
            a(true);
            this.a = activity.getApplicationContext();
            this.b = activity;
            e();
            c(activity);
            a(activity, true);
        }
    }

    public void a(Activity activity, boolean z) {
        if (activity instanceof IIgnoreAutoEvent) {
            return;
        }
        if (z) {
            this.j.a(activity, false, null, false);
        } else {
            this.j.a(activity, false);
        }
    }

    public void a(WebView webView, String str, ce ceVar) {
        if (TextUtils.isEmpty(this.e)) {
            this.e = ch.a(this.a, ba.b);
        }
        b(webView, this.e, ceVar);
    }

    public void a(String str) {
        br.a().a(str);
    }

    public PointF b() {
        return this.i;
    }

    public void b(Activity activity) {
        if (d()) {
            d(this.b);
            this.b = null;
            a(activity, false);
        }
    }

    public void b(String str) {
        this.h = str;
    }
}

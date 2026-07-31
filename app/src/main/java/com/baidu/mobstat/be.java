package com.baidu.mobstat;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import com.baidu.mobstat.bf;

/* JADX INFO: loaded from: classes.dex */
public class be {
    private static volatile boolean a = true;
    private a b;
    private Activity c;
    private Handler d = new Handler(Looper.getMainLooper()) { // from class: com.baidu.mobstat.be.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    if (be.this.b != null) {
                        be.this.b.a();
                    }
                    break;
            }
        }
    };

    public interface a {
        void a();
    }

    public be(a aVar) {
        this.b = aVar;
    }

    private Window.Callback a(Window.Callback callback) {
        while (callback != null && (callback instanceof bf)) {
            callback = ((bf) callback).a();
        }
        return callback;
    }

    public static void a(boolean z) {
        if (z) {
            bh.a();
        }
        a = z;
    }

    public static boolean a() {
        return a;
    }

    private void b(Activity activity) {
        d(activity);
    }

    private void c(Activity activity) {
        Window window;
        if (activity == null || (window = activity.getWindow()) == null) {
            return;
        }
        window.setCallback(a(window.getCallback()));
    }

    private void d(Activity activity) {
        Window.Callback callback;
        Window window = activity.getWindow();
        if (window == null || (callback = window.getCallback()) == null) {
            return;
        }
        window.setCallback(new bf(callback, new bf.a() { // from class: com.baidu.mobstat.be.2
            @Override // com.baidu.mobstat.bf.a
            public void a(KeyEvent keyEvent) {
            }

            @Override // com.baidu.mobstat.bf.a
            public void a(MotionEvent motionEvent) {
                be.a(true);
                switch (motionEvent.getActionMasked()) {
                    case 5:
                        int pointerCount = motionEvent.getPointerCount();
                        if (pointerCount == 3 && motionEvent.getEventTime() - motionEvent.getDownTime() <= 50) {
                            be.this.d.sendEmptyMessageDelayed(100, 2500L);
                        } else if (pointerCount > 3) {
                            be.this.d.removeMessages(100);
                        }
                        break;
                    case 6:
                        if (motionEvent.getEventTime() - motionEvent.getDownTime() < 2500) {
                            be.this.d.removeMessages(100);
                        }
                        break;
                }
            }
        }));
    }

    public void a(Activity activity) {
        if (activity != null) {
            this.c = activity;
            b(activity);
        }
    }

    public void b() {
        c(this.c);
        this.c = null;
    }
}

package com.baidu.mobstat;

import android.content.Context;
import java.lang.Thread;

/* JADX INFO: loaded from: classes.dex */
class aw implements Thread.UncaughtExceptionHandler {
    private static final aw a = new aw();
    private Thread.UncaughtExceptionHandler b;
    private Context c;

    private aw() {
    }

    public static aw a() {
        return a;
    }

    public void a(Context context) {
        this.c = context;
        if (this.b == null) {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (!CooperService.instance().isCloseTrace()) {
            ExceptionAnalysis.getInstance().saveCrashInfo(this.c, th, true);
        }
        if (this.b.equals(this)) {
            return;
        }
        this.b.uncaughtException(thread, th);
    }
}

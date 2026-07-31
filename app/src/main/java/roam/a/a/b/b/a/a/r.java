package roam.a.a.b.b.a.a;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class r implements g {
    public static r h;
    public static final ThreadFactory i = new t();
    public Context a;
    public ThreadPoolExecutor b;
    public i c = i.a("android");
    public long d;
    public long e;
    public long f;
    public int g;

    public r(Context context) {
        this.a = context;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 11, 3L, TimeUnit.SECONDS, new ArrayBlockingQueue(20), i, new ThreadPoolExecutor.CallerRunsPolicy());
        this.b = threadPoolExecutor;
        try {
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        } catch (Exception e) {
        }
        CookieSyncManager.createInstance(this.a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    public final i a() {
        return this.c;
    }

    public final Future<y> b(u uVar) {
        boolean zBooleanValue;
        Context context = this.a;
        Boolean bool = x.a;
        if (bool != null) {
            zBooleanValue = bool.booleanValue();
        } else {
            try {
                Boolean boolValueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
                x.a = boolValueOf;
                zBooleanValue = boolValueOf.booleanValue();
            } catch (Exception e) {
            }
        }
        if (zBooleanValue) {
            String str = "HttpManager" + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times";
            int activeCount = this.b.getActiveCount();
            long completedTaskCount = this.b.getCompletedTaskCount();
            long taskCount = this.b.getTaskCount();
            long j = this.f;
            long j2 = j == 0 ? 0L : ((this.d * 1000) / j) >> 10;
            int i2 = this.g;
            String.format(str, Integer.valueOf(activeCount), Long.valueOf(completedTaskCount), Long.valueOf(taskCount), Long.valueOf(j2), Long.valueOf(i2 != 0 ? this.e / ((long) i2) : 0L), Long.valueOf(this.d), Long.valueOf(this.e), Long.valueOf(this.f), Integer.valueOf(this.g));
        }
        w wVar = new w(this, uVar);
        s sVar = new s(this, wVar, wVar);
        this.b.execute(sVar);
        return sVar;
    }

    public final void c(long j) {
        this.e += j;
        this.g++;
    }
}

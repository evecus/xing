package com.baidu.android.common.util;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.baidu.mobstat.ak;
import com.baidu.mobstat.ap;
import com.baidu.mobstat.aq;
import com.baidu.mobstat.at;
import com.baidu.mobstat.au;
import com.baidu.mobstat.av;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceId {
    private static final String a = "DeviceId";
    private static final boolean b = false;
    private static av.a d;
    private static volatile DeviceId g;
    private static CuidChangeCallback i;
    public static boolean sDataCuidInfoShable = true;
    private final Context c;
    private av e;
    private au f;
    private ak h;
    private Executor j = new ThreadPoolExecutor(0, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public interface CuidChangeCallback {
        void onCuidChanged(String str, String str2, CuidChangeReceivedCallback cuidChangeReceivedCallback);
    }

    public interface CuidChangeReceivedCallback {
        void onCuidChangeReceived();
    }

    private DeviceId(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        this.h = new ak();
        this.e = new av(applicationContext, new ap(applicationContext), this.h);
        this.f = new au(applicationContext, this.h);
    }

    static DeviceId a(Context context) {
        DeviceId deviceId;
        synchronized (aq.class) {
            if (g == null) {
                g = new DeviceId(context);
            }
            deviceId = g;
        }
        return deviceId;
    }

    private av.a a(String str) {
        return this.e.b(str);
    }

    private av.a a(String str, String str2) {
        av.a aVarC = this.e.c(str2);
        return aVarC == null ? b(str, str2) : aVarC;
    }

    private boolean a(av.a aVar) {
        return (aVar == null || !aVar.d() || TextUtils.isEmpty(aVar.e()) || TextUtils.equals(aVar.e(), av.b())) ? false : true;
    }

    private static av.a b(Context context) {
        if (d == null) {
            synchronized (aq.class) {
                if (d == null) {
                    SystemClock.uptimeMillis();
                    d = a(context).c();
                    SystemClock.uptimeMillis();
                }
            }
        }
        a(context).d();
        return d;
    }

    private av.a b(String str, String str2) {
        aq aqVarA = this.f.a(str);
        if (aqVarA == null || TextUtils.equals(str2, aqVarA.a)) {
            return null;
        }
        return this.e.a(aqVarA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final av.a aVar) {
        this.j.execute(new Runnable() { // from class: com.baidu.android.common.util.DeviceId.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (aq.class) {
                    if (DeviceId.i == null) {
                        return;
                    }
                    DeviceId.this.e.c();
                    try {
                        aVar.a(true);
                        DeviceId.this.e.a(aVar, true, true);
                        CuidChangeCallback unused = DeviceId.i = null;
                    } finally {
                        DeviceId.this.e.d();
                    }
                }
            }
        });
    }

    private av.a c() {
        this.e.c();
        try {
            av.a aVarE = e();
            if (!a(aVarE)) {
                if (aVarE == null) {
                    aVarE = a((String) null, (String) null);
                }
                if (aVarE == null) {
                    aVarE = a((String) null);
                }
                c(aVarE);
                return aVarE;
            }
            av.a aVarA = a((String) null, aVarE.a());
            if (aVarA == null) {
                aVarA = a((String) null);
            }
            aVarA.a(false);
            aVarA.a(aVarE.k());
            c(aVarA);
            return aVarA;
        } catch (Throwable th) {
            this.e.d();
            throw th;
        }
    }

    private synchronized void c(av.a aVar) {
        this.j.execute(d(aVar));
    }

    private Runnable d(final av.a aVar) {
        return new Runnable() { // from class: com.baidu.android.common.util.DeviceId.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DeviceId.this.e(aVar);
                } finally {
                    DeviceId.this.e.d();
                }
            }
        };
    }

    private void d() {
        final av.a aVar = d;
        if (i == null) {
            return;
        }
        if (aVar == null || aVar.f() || TextUtils.isEmpty(aVar.g())) {
            i = null;
        } else {
            this.j.execute(new Runnable() { // from class: com.baidu.android.common.util.DeviceId.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DeviceId.i == null) {
                        return;
                    }
                    av.a aVar2 = aVar;
                    if (aVar2 == null || aVar2.f() || TextUtils.isEmpty(aVar.g())) {
                        CuidChangeCallback unused = DeviceId.i = null;
                    } else {
                        DeviceId.i.onCuidChanged(aVar.k(), aVar.g(), new CuidChangeReceivedCallback() { // from class: com.baidu.android.common.util.DeviceId.1.1
                            @Override // com.baidu.android.common.util.DeviceId.CuidChangeReceivedCallback
                            public void onCuidChangeReceived() {
                                DeviceId.this.b(aVar);
                            }
                        });
                    }
                }
            });
        }
    }

    private av.a e() {
        av.a aVarF = f();
        return aVarF == null ? g() : aVarF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(av.a aVar) {
        if (aVar == null) {
            throw new NullPointerException("cuidV270Info should not be null");
        }
        aq aqVarI = aVar.i();
        if (!aVar.d() || TextUtils.isEmpty(aVar.e())) {
            aVar.h();
        }
        this.e.a(aVar, true, false);
        this.f.a(aqVarI);
        this.e.a(aVar);
    }

    private av.a f() {
        return this.e.a();
    }

    private av.a g() {
        aq aqVarB;
        File file = new File(this.c.getFilesDir(), "libcuid.so");
        if (!file.exists() || (aqVarB = aq.b(at.a(file))) == null) {
            return null;
        }
        return this.e.a(aqVarB);
    }

    public static String getCUID(Context context) {
        return b(context).k();
    }

    public static String getDeviceID(Context context) {
        return b(context).a();
    }

    public static String getOldCUID(Context context) {
        return b(context).g();
    }

    public static boolean isMySelfTrusted(Context context) {
        return a(context).h.a(context.getApplicationContext());
    }

    public static void registerCuidChangeEvent(Context context, CuidChangeCallback cuidChangeCallback) {
        i = cuidChangeCallback;
        b(context);
    }

    @Deprecated
    public static void setCuidDataShable(Context context, boolean z) {
    }

    av a() {
        return this.e;
    }
}

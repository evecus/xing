package roam.a.a.f.j;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import roam.a.a.f.j.g;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public Activity a;
    public roam.a.a.b.a.a b;
    public boolean d;
    public a e;
    public final Object c = roam.a.a.b.a.a.class;
    public ServiceConnection f = new d(this);
    public roam.a.a.b.a.b g = new e(this);

    public interface a {
    }

    public c(Activity activity, a aVar) {
        this.a = activity;
        this.e = aVar;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v23, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v24, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.lang.Throwable] */
    public final String a(String str) {
        String strC;
        Activity activity;
        PackageInfo packageInfo;
        try {
            packageInfo = this.a.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
        } catch (Throwable th) {
        }
        try {
            if (packageInfo != null) {
                String str2 = packageInfo.versionName;
                String[] strArr = g.a;
                if (!TextUtils.equals(str2, strArr[0])) {
                    if (!TextUtils.equals(str2, strArr[1])) {
                    }
                }
                return "failed";
            }
            g.a aVarB = g.b(this.a);
            if (aVarB.a()) {
                return "failed";
            }
            if (aVarB.b > 78) {
                Intent intent = new Intent();
                intent.setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.TransProcessPayActivity");
                this.a.startActivity(intent);
                Thread.sleep(200L);
            }
        } catch (Throwable th2) {
            roam.a.a.f.a.l.a.c("biz", "CheckClientSignEx", th2);
        }
        Intent intent2 = new Intent();
        intent2.setPackage("com.eg.android.AlipayGphone");
        intent2.setAction("com.eg.android.AlipayGphone.IAlixPay");
        String strK = g.k(this.a);
        try {
            if (!this.a.getApplicationContext().bindService(intent2, this.f, 1)) {
                throw new Throwable("bindService fail");
            }
            synchronized (this.c) {
                if (this.b == null) {
                    try {
                        this.c.wait(roam.a.a.f.c.a.c().a());
                    } catch (InterruptedException e) {
                        roam.a.a.f.a.l.a.c("biz", "BindWaitTimeoutEx", e);
                    }
                }
            }
            try {
                if (this.b == null) {
                    roam.a.a.f.a.l.a.b("biz", "ClientBindFailed", strK + "|" + g.k(this.a) + "|" + g.l(this.a));
                    strC = "failed";
                    try {
                        this.b.c(this.g);
                    } catch (Throwable th3) {
                    }
                    try {
                        this.a.getApplicationContext().unbindService(this.f);
                    } catch (Throwable th4) {
                    }
                    this.e = null;
                    this.g = null;
                    this.f = null;
                    this.b = null;
                    if (!this.d || (activity = this.a) == null) {
                        return "failed";
                    }
                } else {
                    if (this.a.getRequestedOrientation() == 0) {
                        this.a.setRequestedOrientation(1);
                        this.d = true;
                    }
                    this.b.a(this.g);
                    strC = this.b.b(str);
                    try {
                        this.b.c(this.g);
                    } catch (Throwable th5) {
                    }
                    try {
                        this.a.getApplicationContext().unbindService(this.f);
                    } catch (Throwable th6) {
                    }
                    this.e = null;
                    this.g = null;
                    this.f = null;
                    this.b = null;
                    if (!this.d || (activity = this.a) == null) {
                        return strC;
                    }
                }
            } catch (Throwable th7) {
                try {
                    roam.a.a.f.a.l.a.c("biz", "ClientBindException", th7);
                    strC = roam.a.a.a.b.a.c();
                    try {
                        this.b.c(this.g);
                    } catch (Throwable th8) {
                    }
                    try {
                        this.a.getApplicationContext().unbindService(this.f);
                    } catch (Throwable th9) {
                    }
                    this.e = null;
                    this.g = null;
                    this.f = null;
                    this.b = null;
                    if (!this.d || (activity = this.a) == null) {
                        return strC;
                    }
                } finally {
                }
            }
            activity.setRequestedOrientation(0);
            this.d = false;
            return strC;
        } catch (Throwable th10) {
            roam.a.a.f.a.l.a.c("biz", "ClientBindServiceFailed", th10);
        }
    }
}

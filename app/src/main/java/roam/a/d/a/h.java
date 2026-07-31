package roam.a.d.a;

import android.content.Context;
import android.os.SystemClock;
import java.io.File;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class h extends o implements Serializable, Cloneable {
    public static final String H;
    public long u;
    public Context v;
    public File w;
    public e x;
    public l y;
    public int t = r.h.b.getAndIncrement();
    public String z = "";
    public long A = 0;
    public long B = 0;
    public long C = 0;
    public long D = 0;
    public boolean E = true;
    public int F = 0;
    public volatile int G = 1000;

    static {
        StringBuilder sbO = roam.a.b.a.a.a.o("Download-");
        sbO.append(h.class.getSimpleName());
        H = sbO.toString();
    }

    @Override // roam.a.d.a.o
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public h clone() {
        try {
            h hVar = (h) super.clone();
            hVar.t = r.h.b.getAndIncrement();
            return hVar;
        } catch (Throwable th) {
            th.printStackTrace();
            return new h();
        }
    }

    public void c() {
        this.C = SystemClock.elapsedRealtime();
    }

    public int d() {
        int i;
        synchronized (this) {
            i = this.G;
        }
        return i;
    }

    public long e() {
        long jElapsedRealtime;
        if (this.G == 1002) {
            if (this.A <= 0) {
                return 0L;
            }
            jElapsedRealtime = SystemClock.elapsedRealtime();
        } else if (this.G == 1005) {
            jElapsedRealtime = this.C;
        } else if (this.G == 1001) {
            jElapsedRealtime = this.B;
            if (jElapsedRealtime <= 0) {
                return 0L;
            }
        } else if (this.G == 1003) {
            jElapsedRealtime = this.B;
        } else if (this.G == 1000) {
            jElapsedRealtime = this.B;
            if (jElapsedRealtime <= 0) {
                return 0L;
            }
        } else {
            if (this.G != 1004 && this.G != 1006) {
                return 0L;
            }
            jElapsedRealtime = this.C;
        }
        return (jElapsedRealtime - this.A) - this.D;
    }

    public void f(int i) {
        synchronized (this) {
            this.G = i;
        }
    }
}

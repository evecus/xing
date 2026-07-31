package roam.a.a.b.b.a.a;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    public final f a;
    public final n b;

    public o(n nVar, f fVar) {
        this.b = nVar;
        this.a = fVar;
    }

    public final g a() {
        Context applicationContext = this.b.a.getApplicationContext();
        r rVar = r.h;
        if (rVar == null) {
            synchronized (r.class) {
                try {
                    rVar = r.h;
                    if (rVar == null) {
                        rVar = new r(applicationContext);
                        r.h = rVar;
                    }
                } finally {
                }
            }
        }
        return rVar;
    }
}

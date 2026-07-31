package roam.a.d.a;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.util.Objects;
import roam.a.d.a.h;
import roam.a.d.a.n;

/* JADX INFO: loaded from: classes.dex */
public class q<T extends h> {
    public h a;

    public static q c(Context context) {
        h hVarClone;
        q qVar = new q();
        r rVar = r.h;
        synchronized (rVar) {
            if (rVar.a == null) {
                rVar.a();
            }
            hVarClone = rVar.a.clone();
        }
        qVar.a = hVarClone;
        Objects.requireNonNull(hVarClone);
        hVarClone.v = context.getApplicationContext();
        return qVar;
    }

    public q a() {
        h hVar = this.a;
        hVar.l = true;
        if (hVar.w != null && TextUtils.isEmpty(hVar.z)) {
            r rVar = r.h;
            String str = h.H;
            Objects.requireNonNull(rVar);
            Log.e(str, "Custom file path, you must specify authority, otherwise the auto open should be closed. ");
            hVar.l = false;
        }
        return this;
    }

    public void b(f fVar) {
        h hVar = this.a;
        hVar.x = fVar;
        hVar.y = fVar;
        Objects.requireNonNull(d.b);
        Objects.requireNonNull(hVar.v, "context can't be null .");
        if (TextUtils.isEmpty(hVar.g)) {
            throw new NullPointerException("url can't be empty .");
        }
        k kVar = new k();
        synchronized (k.class) {
            try {
                if (!TextUtils.isEmpty(hVar.g)) {
                    n nVar = n.b.a;
                    if (!nVar.a(hVar.g)) {
                        String str = hVar.g;
                        if (str != null) {
                            nVar.a.put(str, kVar);
                        }
                        if (Looper.getMainLooper() != Looper.myLooper()) {
                            k.u.post(new j(kVar, hVar));
                        } else {
                            kVar.p(hVar);
                        }
                    }
                }
            } finally {
            }
        }
    }
}

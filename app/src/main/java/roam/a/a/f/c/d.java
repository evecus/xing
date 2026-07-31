package roam.a.a.f.c;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Callable<String> {
    public final Context a;
    public final HashMap b;

    public d(c cVar, Context context, HashMap map) {
        this.a = context;
        this.b = map;
    }

    @Override // java.util.concurrent.Callable
    public final String call() {
        String strA;
        try {
            strA = roam.a.a.e.a.a.a(this.a, this.b);
        } catch (Throwable th) {
            roam.a.a.f.a.l.a.c("third", "GetApdidEx", th);
            strA = "";
        }
        if (TextUtils.isEmpty(strA)) {
            roam.a.a.f.a.l.a.b("third", "GetApdidNull", "apdid == null");
        }
        return strA;
    }
}

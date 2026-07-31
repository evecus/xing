package roam.a.d.a;

import android.content.Context;
import java.util.concurrent.ConcurrentHashMap;
import roam.a.d.a.n;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static final d b = new d();
    public static Context c;
    public final ConcurrentHashMap<String, h> a = new ConcurrentHashMap<>();

    /* JADX WARN: Finally extract failed */
    public h a(String str) {
        try {
            m mVar = n.b.a.a.get(str);
            h hVarA = mVar != null ? mVar.a() : null;
            h hVar = this.a.get(str);
            if (hVar != null && hVar.d() == 1003) {
                hVar.f(1005);
                g.b(hVar);
                hVarA = hVar;
            }
            this.a.remove(str);
            return hVarA;
        } catch (Throwable th) {
            h hVar2 = this.a.get(str);
            if (hVar2 != null && hVar2.d() == 1003) {
                hVar2.f(1005);
                g.b(hVar2);
            }
            this.a.remove(str);
            throw th;
        }
    }

    public boolean b(String str) {
        return n.b.a.a(str) || this.a.contains(str);
    }
}

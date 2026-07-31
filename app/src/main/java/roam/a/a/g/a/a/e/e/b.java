package roam.a.a.g.a.a.e.e;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;
import roam.a.a.g.a.a.e.c;

/* JADX INFO: loaded from: classes.dex */
public final class b implements a {
    public static a a;
    public static roam.a.a.g.a.a.e.a b;

    public final roam.a.a.g.a.a.e.d.a a(roam.a.a.g.a.a.e.d.b bVar) throws InterruptedException {
        roam.a.a.h.a.a.b.a.b.a aVar = new roam.a.a.h.a.a.b.a.b.a();
        HashMap map = new HashMap();
        aVar.a = map;
        String str = bVar.b;
        if (str == null) {
            str = "";
        }
        map.put("apdid", str);
        Map<String, String> map2 = aVar.a;
        String str2 = bVar.c;
        if (str2 == null) {
            str2 = "";
        }
        map2.put("apdidToken", str2);
        Map<String, String> map3 = aVar.a;
        String str3 = bVar.d;
        map3.put("umidToken", str3 != null ? str3 : "");
        aVar.a.put("dynamicKey", bVar.e);
        if (bVar.f == null) {
            new HashMap();
        }
        roam.a.a.g.a.a.e.b bVar2 = (roam.a.a.g.a.a.e.b) b;
        if (bVar2.c != null) {
            roam.a.a.g.a.a.e.b.e = null;
            new Thread(new c(bVar2, aVar)).start();
            for (int i = 300000; roam.a.a.g.a.a.e.b.e == null && i >= 0; i -= 50) {
                Thread.sleep(50L);
            }
        }
        roam.a.a.h.a.a.b.a.b.b bVar3 = roam.a.a.g.a.a.e.b.e;
        roam.a.a.g.a.a.e.d.a aVar2 = new roam.a.a.g.a.a.e.d.a();
        if (bVar3 == null) {
            return null;
        }
        aVar2.a = bVar3.a;
        return aVar2;
    }

    public final boolean b(String str) {
        roam.a.a.h.a.a.a.a aVar;
        String strA;
        roam.a.a.g.a.a.e.b bVar = (roam.a.a.g.a.a.e.b) b;
        Objects.requireNonNull(bVar);
        if (roam.a.a.a.b.a.o(str) || (aVar = bVar.b) == null) {
            return false;
        }
        try {
            roam.a.a.a.b.a.F(str);
            strA = aVar.a();
        } catch (Throwable th) {
            strA = null;
        }
        if (roam.a.a.a.b.a.o(strA)) {
            return false;
        }
        return ((Boolean) new JSONObject(strA).get("success")).booleanValue();
    }
}

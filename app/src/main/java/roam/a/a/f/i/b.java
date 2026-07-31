package roam.a.a.f.i;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static b c;
    public String a;
    public String b;

    public static b a() {
        b bVar;
        synchronized (b.class) {
            try {
                if (c == null) {
                    c = new b();
                    Context context = roam.a.a.f.h.b.a().a;
                    a aVar = new a(context);
                    String strB = roam.a.a.f.j.a.a(context).b();
                    String strD = roam.a.a.f.j.a.a(context).d();
                    c.a = aVar.a(strB, strD);
                    c.b = aVar.e(strB, strD);
                    if (TextUtils.isEmpty(c.b)) {
                        b bVar2 = c;
                        String hexString = Long.toHexString(System.currentTimeMillis());
                        if (hexString.length() > 10) {
                            hexString = hexString.substring(hexString.length() - 10);
                        }
                        bVar2.b = hexString;
                    }
                    b bVar3 = c;
                    aVar.c(strB, strD, bVar3.a, bVar3.b);
                }
                bVar = c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return bVar;
    }
}

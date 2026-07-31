package roam.b.c.a.a.m.b2;

import android.os.Handler;
import android.os.Message;
import com.roamexplore.WebViewActionActivity;
import java.util.ArrayList;
import java.util.Arrays;
import roam.b.c.a.a.m.f;
import roam.b.c.a.a.m.k;
import roam.b.c.a.a.m.k1;
import roam.b.c.a.a.m.q;

/* JADX INFO: loaded from: classes.dex */
public class c implements Handler.Callback {
    public final d a;

    public c(d dVar) {
        this.a = dVar;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        d dVar;
        String[] strArr = k.c;
        int i = message.what;
        if (i == 0) {
            dVar = this.a;
            dVar.i = true;
            String[] strArr2 = k.a;
            if (dVar.a != null) {
                k1 k1Var = dVar.k;
                if (k1Var == null || !k1Var.a(dVar.h.getUrl(), strArr2, "camera")) {
                    f fVar = new f();
                    ArrayList arrayList = new ArrayList();
                    if (!q.l(dVar.a, strArr2)) {
                        arrayList.add(strArr2[0]);
                    }
                    if (!q.l(dVar.a, strArr)) {
                        arrayList.addAll(Arrays.asList(strArr));
                    }
                    if (arrayList.isEmpty()) {
                        dVar.d();
                    } else {
                        fVar.b = 1;
                        fVar.a = new ArrayList<>(Arrays.asList((String[]) arrayList.toArray(new String[0])));
                        fVar.c = 2;
                        WebViewActionActivity.c = dVar.n;
                        WebViewActionActivity.a(dVar.a, fVar);
                    }
                } else {
                    dVar.a();
                }
            }
        } else if (i != 1) {
            dVar = this.a;
            dVar.a();
        } else {
            d dVar2 = this.a;
            dVar2.i = false;
            if (q.h(dVar2.a, strArr).isEmpty()) {
                dVar2.g();
            } else {
                f fVarA = f.a(strArr);
                fVarA.c = 5;
                WebViewActionActivity.c = dVar2.n;
                WebViewActionActivity.a(dVar2.a, fVarA);
            }
        }
        return true;
    }
}

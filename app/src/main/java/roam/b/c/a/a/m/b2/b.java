package roam.b.c.a.a.m.b2;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import com.roamexplore.WebViewActionActivity;
import java.io.File;
import java.util.Objects;
import org.roam.R;
import roam.b.c.a.a.m.b2.d;
import roam.b.c.a.a.m.i;
import roam.b.c.a.a.m.q;

/* JADX INFO: loaded from: classes.dex */
public class b implements WebViewActionActivity.a {
    public final d a;

    public b(d dVar) {
        this.a = dVar;
    }

    public void a(int i, int i2, Intent intent) {
        String[] strArrS;
        String[] strArrS2;
        d dVar = this.a;
        Objects.requireNonNull(dVar);
        if (596 != i) {
            return;
        }
        if (i2 != 0 && intent != null && i2 == -1) {
            if (dVar.g) {
                Uri[] uriArrF = dVar.i ? new Uri[]{(Uri) intent.getParcelableExtra("KEY_URI")} : dVar.f(intent);
                if (uriArrF != null && uriArrF.length != 0 && (strArrS2 = q.s(dVar.a, uriArrF)) != null && strArrS2.length != 0) {
                    int length = 0;
                    for (String str : strArrS2) {
                        if (!TextUtils.isEmpty(str)) {
                            File file = new File(str);
                            if (file.exists()) {
                                length = (int) (file.length() + ((long) length));
                            }
                        }
                    }
                    if (length <= i.e) {
                        new d.C0024d(dVar.f, strArrS2, null).start();
                        return;
                    } else if (dVar.l.get() != null) {
                        dVar.l.get().n(dVar.a.getString(R.string.r, new Object[]{((i.e / 1024) / 1024) + ""}), "convertFileAndCallback");
                    }
                }
                dVar.f.a(null);
                return;
            }
            if (dVar.d) {
                Uri[] uriArrF2 = dVar.i ? new Uri[]{(Uri) intent.getParcelableExtra("KEY_URI")} : dVar.f(intent);
                boolean z = dVar.i;
                ValueCallback<Uri[]> valueCallback = dVar.c;
                if (valueCallback != null) {
                    if (!z) {
                        if (uriArrF2 == null) {
                            uriArrF2 = new Uri[0];
                        }
                        valueCallback.onReceiveValue(uriArrF2);
                        return;
                    } else {
                        if (dVar.l.get() == null || (strArrS = q.s(dVar.a, uriArrF2)) == null || strArrS.length == 0) {
                            dVar.c.onReceiveValue(null);
                            return;
                        }
                        String str2 = strArrS[0];
                        dVar.l.get().h(dVar.a.getString(R.string.r));
                        AsyncTask.THREAD_POOL_EXECUTOR.execute(new d.g(str2, new d.b(dVar.c, uriArrF2, dVar.l, null), null));
                        return;
                    }
                }
                return;
            }
            ValueCallback<Uri> valueCallback2 = dVar.b;
            if (valueCallback2 != null) {
                if (dVar.i) {
                    valueCallback2.onReceiveValue((Uri) intent.getParcelableExtra("KEY_URI"));
                    return;
                }
                Uri data = intent.getData();
                ValueCallback<Uri> valueCallback3 = dVar.b;
                if (valueCallback3 != null) {
                    valueCallback3.onReceiveValue(data);
                    return;
                }
                return;
            }
        }
        dVar.a();
    }
}

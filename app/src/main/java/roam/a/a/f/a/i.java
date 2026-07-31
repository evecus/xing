package roam.a.a.f.a;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.google.android.material.snackbar.Snackbar;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Objects;
import org.roam.R;
import roam.a.a.f.a.c;
import roam.b.c.a.a.m.l0;
import roam.b.c.a.a.m.q;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Runnable {
    public final String a;
    public final boolean b;
    public final b c;
    public final c d;

    public i(c cVar, String str, boolean z, b bVar) {
        this.d = cVar;
        this.a = str;
        this.b = z;
        this.c = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final String strC;
        String str;
        String strReplace;
        c cVar = this.d;
        String str2 = this.a;
        boolean z = this.b;
        synchronized (cVar) {
            try {
                str2.trim();
                String[] strArrSplit = cVar.f(str2, z).split(";");
                HashMap map = new HashMap();
                for (String str3 : strArrSplit) {
                    String strSubstring = str3.substring(0, str3.indexOf("={"));
                    String str4 = strSubstring + "={";
                    map.put(strSubstring, str3.substring(str4.length() + str3.indexOf(str4), str3.lastIndexOf("}")));
                }
                str = map.containsKey("resultStatus") ? (String) map.get("resultStatus") : null;
                if (map.containsKey("callBackUrl")) {
                    strC = (String) map.get("callBackUrl");
                } else if (map.containsKey("result")) {
                    String str5 = (String) map.get("result");
                    if (str5.length() > 15) {
                        c.a aVar = cVar.c.get(str2);
                        if (aVar != null) {
                            strReplace = TextUtils.isEmpty(aVar.b) ? aVar.a : roam.a.a.f.c.a.c().b.replace("$OrderId$", aVar.b);
                        } else {
                            strC = roam.a.a.f.j.g.c("&callBackUrl=\"", "\"", str5);
                            if (TextUtils.isEmpty(strC)) {
                                strC = roam.a.a.f.j.g.c("&call_back_url=\"", "\"", str5);
                                if (TextUtils.isEmpty(strC)) {
                                    strC = roam.a.a.f.j.g.c("&return_url=\"", "\"", str5);
                                    if (TextUtils.isEmpty(strC)) {
                                        strC = URLDecoder.decode(roam.a.a.f.j.g.c("&return_url=", "&", str5), "utf-8");
                                        if (TextUtils.isEmpty(strC)) {
                                            strC = URLDecoder.decode(roam.a.a.f.j.g.c("&callBackUrl=", "&", str5), "utf-8");
                                        }
                                    }
                                }
                            }
                            if (TextUtils.isEmpty(strC) && !TextUtils.isEmpty(str5) && str5.contains("call_back_url")) {
                                try {
                                    int iIndexOf = str5.indexOf("call_back_url=\"") + 15;
                                    int iIndexOf2 = TextUtils.isEmpty("\"") ? 0 : str5.indexOf("\"", iIndexOf);
                                    strC = iIndexOf2 <= 0 ? str5.substring(iIndexOf) : str5.substring(iIndexOf, iIndexOf2);
                                } catch (Throwable th) {
                                    strC = "";
                                }
                            }
                            if (TextUtils.isEmpty(strC)) {
                                strC = roam.a.a.f.c.a.c().b;
                            }
                        }
                    } else {
                        c.a aVar2 = cVar.c.get(str2);
                        if (aVar2 != null) {
                            strReplace = aVar2.a;
                        }
                        strC = null;
                    }
                    try {
                        cVar.c.remove(str2);
                    } catch (Throwable th2) {
                    }
                    strC = strReplace;
                } else {
                    strC = null;
                }
            } catch (Throwable th3) {
                strC = null;
                str = null;
            }
        }
        l0.a aVar3 = (l0.a) this.c;
        Objects.requireNonNull(aVar3);
        if (str != null && str.equals("9000")) {
            View viewFindViewById = aVar3.b.c.get().findViewById(R.id.r);
            if (viewFindViewById == null) {
                viewFindViewById = aVar3.b.c.get().findViewById(android.R.id.content);
            }
            Snackbar action = Snackbar.make(viewFindViewById, R.string.r, -1).setAction(R.string.r, (View.OnClickListener) null);
            q.q(aVar3.b.c.get(), action);
            action.show();
        }
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        final WebView webView = aVar3.a;
        q.n(new Runnable(webView, strC) { // from class: roam.b.c.a.a.m.c
            public final WebView a;
            public final String b;

            {
                this.a = webView;
                this.b = strC;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.loadUrl(this.b);
            }
        });
    }
}

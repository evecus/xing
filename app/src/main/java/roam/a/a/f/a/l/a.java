package roam.a.a.f.a.l;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static c a;

    public static void a(Context context, String str) {
        String strReplace;
        String strReplace2;
        String str2;
        synchronized (a.class) {
            try {
                c cVar = a;
                if (cVar != null) {
                    if (TextUtils.isEmpty(cVar.i)) {
                        str2 = "";
                    } else {
                        String[] strArrSplit = str.split("&");
                        if (strArrSplit != null) {
                            strReplace = null;
                            strReplace2 = null;
                            for (String str3 : strArrSplit) {
                                String[] strArrSplit2 = str3.split("=");
                                if (strArrSplit2 != null && strArrSplit2.length == 2) {
                                    if (strArrSplit2[0].equalsIgnoreCase("partner")) {
                                        strArrSplit2[1].replace("\"", "");
                                    } else if (strArrSplit2[0].equalsIgnoreCase("out_trade_no")) {
                                        strReplace = strArrSplit2[1].replace("\"", "");
                                    } else if (strArrSplit2[0].equalsIgnoreCase("trade_no")) {
                                        strReplace2 = strArrSplit2[1].replace("\"", "");
                                    }
                                }
                            }
                        } else {
                            strReplace = null;
                            strReplace2 = null;
                        }
                        String strA = c.a(strReplace2);
                        String strA2 = c.a(strReplace);
                        String str4 = String.format("%s,%s,-,%s,-,-,-", strA, strA2, c.a(strA2));
                        cVar.b = str4;
                        str2 = String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", cVar.a, str4, cVar.c, cVar.d, cVar.e, cVar.f, cVar.g, cVar.h, cVar.i, cVar.j);
                    }
                    new Thread(new b(context, str2)).start();
                    a = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void b(String str, String str2, String str3) {
        c cVar = a;
        if (cVar == null) {
            return;
        }
        cVar.b(str, str2, str3);
    }

    public static void c(String str, String str2, Throwable th) {
        c cVar = a;
        if (cVar == null) {
            return;
        }
        cVar.c(str, str2, th);
    }
}

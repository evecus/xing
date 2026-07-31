package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ct {
    private static ct a = new ct();
    private static String b = "";

    public static ct a() {
        return a;
    }

    public void a(Context context, final cs csVar) {
        String strA = cv.a(context);
        b = strA;
        if (TextUtils.isEmpty(strA)) {
            cr.a(context, new cu() { // from class: com.baidu.mobstat.ct.1
                @Override // com.baidu.mobstat.cu
                public void a(String str) {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    String unused = ct.b = str;
                    cs csVar2 = csVar;
                    if (csVar2 != null) {
                        csVar2.a(ct.b);
                    }
                }
            });
        } else if (csVar != null) {
            csVar.a(b);
        }
    }
}

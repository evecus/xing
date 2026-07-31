package roam.a.a.c.e;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public static String a = "";

    public static String a(Context context) {
        String str;
        SharedPreferences.Editor editorEdit;
        synchronized (g.class) {
            try {
                if (roam.a.a.a.b.a.o(a)) {
                    String string = context.getSharedPreferences("alipay_vkey_random", 0).getString("random", "");
                    a = string;
                    if (roam.a.a.a.b.a.o(string)) {
                        String strT = roam.a.a.a.b.a.t(UUID.randomUUID().toString());
                        a = strT;
                        if (strT != null && (editorEdit = context.getSharedPreferences("alipay_vkey_random", 0).edit()) != null) {
                            editorEdit.putString("random", strT);
                            editorEdit.commit();
                        }
                    }
                }
                str = a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}

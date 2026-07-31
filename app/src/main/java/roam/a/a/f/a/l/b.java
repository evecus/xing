package roam.a.a.f.a.l;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.io.IOException;
import roam.a.a.f.j.f;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final Context a;
    public final String b;

    public b(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        roam.a.a.f.f.e.b bVar = new roam.a.a.f.f.e.b();
        try {
            String strC = f.c(this.a, "alipay_cashier_statistic_record", null);
            if (!TextUtils.isEmpty(strC) && bVar.j(this.a, strC) != null) {
                PreferenceManager.getDefaultSharedPreferences(this.a).edit().remove("alipay_cashier_statistic_record").commit();
            }
        } catch (Throwable th) {
        }
        try {
            if (TextUtils.isEmpty(this.b)) {
                return;
            }
            bVar.j(this.a, this.b);
        } catch (IOException e) {
            f.b(this.a, "alipay_cashier_statistic_record", this.b);
        } catch (Throwable th2) {
        }
    }
}

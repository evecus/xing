package roam.a.a.f.c;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;
import roam.a.a.f.j.f;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static a c;
    public int a = 3500;
    public String b = "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&";

    public static a c() {
        if (c == null) {
            a aVar = new a();
            c = aVar;
            String strC = f.c(roam.a.a.f.h.b.a().a, "alipay_cashier_dynamic_config", null);
            if (!TextUtils.isEmpty(strC)) {
                try {
                    JSONObject jSONObject = new JSONObject(strC);
                    aVar.a = jSONObject.optInt("timeout", 3500);
                    aVar.b = jSONObject.optString("tbreturl", "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&").trim();
                } catch (Throwable th) {
                }
            }
        }
        return c;
    }

    public final int a() {
        int i = this.a;
        if (i < 1000 || i > 20000) {
            return 3500;
        }
        new StringBuilder("DynamicConfig::getJumpTimeout >").append(this.a);
        return this.a;
    }

    public final void b(Context context) {
        new Thread(new b(this, context)).start();
    }
}

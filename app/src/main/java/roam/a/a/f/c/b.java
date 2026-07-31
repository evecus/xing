package roam.a.a.f.c;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;
import roam.a.a.f.j.f;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final Context a;
    public final a b;

    public b(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            roam.a.a.f.f.e.a aVar = new roam.a.a.f.f.e.a();
            Context context = this.a;
            roam.a.a.a.b.a.e(context);
            roam.a.a.f.f.a aVarA = aVar.a(context, "", "https://mobilegw.alipay.com/mgw.htm", true);
            if (aVarA != null) {
                a aVar2 = this.b;
                String str = aVarA.b;
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("st_sdk_config");
                        aVar2.a = jSONObjectOptJSONObject.optInt("timeout", 3500);
                        aVar2.b = jSONObjectOptJSONObject.optString("tbreturl", "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&").trim();
                    } catch (Throwable th) {
                    }
                }
                a aVar3 = this.b;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("timeout", aVar3.a());
                    jSONObject.put("tbreturl", aVar3.b);
                    f.b(roam.a.a.f.h.b.a().a, "alipay_cashier_dynamic_config", jSONObject.toString());
                } catch (Exception e) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}

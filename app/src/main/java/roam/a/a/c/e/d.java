package roam.a.a.c.e;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static c a(String str) {
        try {
            if (roam.a.a.a.b.a.o(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            return new c(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"), jSONObject.optString("tid"), jSONObject.optString("utdid"));
        } catch (Exception e) {
            roam.a.a.a.b.a.l(e);
            return null;
        }
    }
}

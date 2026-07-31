package roam.a.a.c.e;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static b a(String str) {
        try {
            if (!roam.a.a.a.b.a.o(str)) {
                JSONObject jSONObject = new JSONObject(str);
                return new b(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"));
            }
        } catch (Exception e) {
            roam.a.a.a.b.a.l(e);
        }
        return null;
    }
}

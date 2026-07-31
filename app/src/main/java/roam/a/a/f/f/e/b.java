package roam.a.a.f.f.e;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b extends roam.a.a.f.f.c {
    @Override // roam.a.a.f.f.c
    public final String b(String str, JSONObject jSONObject) {
        return str;
    }

    @Override // roam.a.a.f.f.c
    public final List<Header> d(boolean z, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicHeader("msp-gzip", String.valueOf(z)));
        arrayList.add(new BasicHeader("content-type", "application/octet-stream"));
        arrayList.add(new BasicHeader("des-mode", "CBC"));
        return arrayList;
    }

    @Override // roam.a.a.f.f.c
    public final JSONObject e() {
        return null;
    }

    @Override // roam.a.a.f.f.c
    public final String i() {
        HashMap map = new HashMap();
        map.put("api_name", "/sdk/log");
        map.put("api_version", "1.0.0");
        HashMap map2 = new HashMap();
        map2.put("log_v", "1.0");
        return roam.a.a.f.f.c.c(map, map2);
    }

    public final roam.a.a.f.f.a j(Context context, String str) {
        return a(context, str, "https://mcgw.alipay.com/sdklog.do", true);
    }
}

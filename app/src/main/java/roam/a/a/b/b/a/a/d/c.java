package roam.a.a.b.b.a.a.d;

import com.baidu.mobstat.Config;
import java.lang.reflect.Type;
import org.json.JSONObject;
import roam.a.a.a.a.e;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a {
    public c(Type type, byte[] bArr) {
        super(type, bArr);
    }

    public final Object a() {
        try {
            String str = new String(this.b);
            StringBuilder sb = new StringBuilder("threadid = ");
            sb.append(Thread.currentThread().getId());
            sb.append("; rpc response:  ");
            sb.append(str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("resultStatus");
            if (i == 1000) {
                return this.a == String.class ? jSONObject.optString("result") : e.b(jSONObject.optString("result"), this.a);
            }
            throw new roam.a.a.b.b.a.a.c(Integer.valueOf(i), jSONObject.optString("tips"));
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("response  =");
            sb2.append(new String(this.b));
            sb2.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb2.append(e);
            throw new roam.a.a.b.b.a.a.c(10, sb2.toString() == null ? "" : e.getMessage());
        }
    }
}

package roam.a.a.b.b.a.a.d;

import com.baidu.mobstat.Config;
import java.util.ArrayList;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import roam.a.a.a.a.f;

/* JADX INFO: loaded from: classes.dex */
public final class d extends b {
    public int c;
    public Object d;

    public d(int i, String str, Object obj) {
        super(str, obj);
        this.c = i;
    }

    public final byte[] a() {
        try {
            ArrayList arrayList = new ArrayList();
            if (this.d != null) {
                arrayList.add(new BasicNameValuePair("extParam", f.a(this.d)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.a));
            StringBuilder sb = new StringBuilder();
            sb.append(this.c);
            arrayList.add(new BasicNameValuePair(Config.FEED_LIST_ITEM_CUSTOM_ID, sb.toString()));
            new StringBuilder("mParams is:").append(this.b);
            Object obj = this.b;
            arrayList.add(new BasicNameValuePair("requestData", obj == null ? "[]" : f.a(obj)));
            return URLEncodedUtils.format(arrayList, "utf-8").getBytes();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("request  =");
            sb2.append(this.b);
            sb2.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb2.append(e);
            throw new roam.a.a.b.b.a.a.c(9, sb2.toString() == null ? "" : e.getMessage(), e);
        }
    }
}

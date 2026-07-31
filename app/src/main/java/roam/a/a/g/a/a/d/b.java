package roam.a.a.g.a.a.d;

import com.baidu.mobstat.Config;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public File a;
    public roam.a.a.g.a.a.e.e.a b;

    public b(String str, roam.a.a.g.a.a.e.e.a aVar) {
        this.a = null;
        this.b = null;
        this.a = new File(str);
        this.b = aVar;
    }

    public final void a() {
        String string;
        synchronized (this) {
            File file = this.a;
            if (file != null && file.exists() && this.a.isDirectory() && this.a.list().length != 0) {
                ArrayList arrayList = new ArrayList();
                for (String str : this.a.list()) {
                    arrayList.add(str);
                }
                Collections.sort(arrayList);
                String str2 = (String) arrayList.get(arrayList.size() - 1);
                int size = arrayList.size();
                if (str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log")) {
                    if (arrayList.size() >= 2) {
                        str2 = (String) arrayList.get(arrayList.size() - 2);
                        size--;
                    }
                }
                String strF = roam.a.a.a.b.a.f(this.a.getAbsolutePath(), str2);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("type", Config.FEED_LIST_ITEM_CUSTOM_ID);
                    jSONObject.put("error", strF);
                    string = jSONObject.toString();
                } catch (Exception e) {
                    string = "";
                }
                if (!((roam.a.a.g.a.a.e.e.b) this.b).b(string)) {
                    size--;
                }
                for (int i = 0; i < size; i++) {
                    new File(this.a, (String) arrayList.get(i)).delete();
                }
            }
        }
    }
}

package roam.a.d.a;

import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class n {
    public ConcurrentHashMap<String, m> a;

    public static class b {
        public static final n a = new n(null);
    }

    public n(a aVar) {
        this.a = null;
        this.a = new ConcurrentHashMap<>();
    }

    public boolean a(String str) {
        return (TextUtils.isEmpty(str) || this.a.get(str) == null) ? false : true;
    }

    public void b(String str) {
        if (str != null) {
            this.a.remove(str);
        }
    }
}

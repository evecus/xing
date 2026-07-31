package roam.a.a.b.b.a.a;

import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

/* JADX INFO: loaded from: classes.dex */
public final class u {
    public String b;
    public byte[] c;
    public boolean g;
    public boolean a = false;
    public ArrayList<Header> e = new ArrayList<>();
    public Map<String, String> f = new HashMap();
    public String d = "application/x-www-form-urlencoded";

    public u(String str) {
        this.b = str;
    }

    public final String a() {
        return this.b;
    }

    public final void b(String str, String str2) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put(str, str2);
    }

    public final ArrayList<Header> c() {
        return this.e;
    }

    public final boolean d() {
        return this.g;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj != null && u.class == obj.getClass()) {
                u uVar = (u) obj;
                byte[] bArr = this.c;
                if (bArr != null ? bArr.equals(uVar.c) : uVar.c == null) {
                    String str = this.b;
                    String str2 = uVar.b;
                    if (str != null ? str.equals(str2) : str2 == null) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Map<String, String> map = this.f;
        int iHashCode = (map == null || !map.containsKey(Config.FEED_LIST_ITEM_CUSTOM_ID)) ? 1 : this.f.get(Config.FEED_LIST_ITEM_CUSTOM_ID).hashCode() + 31;
        String str = this.b;
        return (iHashCode * 31) + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", this.b, this.e);
    }
}

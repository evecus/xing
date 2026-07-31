package roam.b.c.a.a.m;

import androidx.collection.ArrayMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class s0 {
    public final Map<String, Map<String, String>> a = new ArrayMap();

    public String toString() {
        StringBuilder sbO = roam.a.b.a.a.a.o("HttpHeaders{mHeaders=");
        sbO.append(this.a);
        sbO.append('}');
        return sbO.toString();
    }
}

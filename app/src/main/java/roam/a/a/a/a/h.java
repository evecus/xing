package roam.a.a.a.a;

import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class h implements i, j {
    @Override // roam.a.a.a.a.i, roam.a.a.a.a.j
    public final boolean a(Class<?> cls) {
        return Map.class.isAssignableFrom(cls);
    }

    @Override // roam.a.a.a.a.j
    public final Object b(Object obj) {
        TreeMap treeMap = new TreeMap();
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            if (!(entry.getKey() instanceof String)) {
                throw new IllegalArgumentException("Map key must be String!");
            }
            treeMap.put((String) entry.getKey(), f.b(entry.getValue()));
        }
        return treeMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x009c, code lost:
    
        r0 = new java.util.HashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a2, code lost:
    
        r0 = new java.util.concurrent.ConcurrentHashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a8, code lost:
    
        r0 = new java.util.TreeMap();
     */
    @Override // roam.a.a.a.a.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(java.lang.Object r5, java.lang.reflect.Type r6) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.a.a.h.c(java.lang.Object, java.lang.reflect.Type):java.lang.Object");
    }
}

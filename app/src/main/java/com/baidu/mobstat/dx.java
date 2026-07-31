package com.baidu.mobstat;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class dx implements dt {
    private byte[] a;
    private TreeMap<String, String> b = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    @Override // com.baidu.mobstat.dt
    public void a(String str, String str2) {
        this.b.put(str, str2);
    }

    @Override // com.baidu.mobstat.dw
    public String b(String str) {
        String str2 = this.b.get(str);
        return str2 == null ? "" : str2;
    }

    @Override // com.baidu.mobstat.dw
    public Iterator<String> b() {
        return Collections.unmodifiableSet(this.b.keySet()).iterator();
    }

    @Override // com.baidu.mobstat.dw
    public boolean c(String str) {
        return this.b.containsKey(str);
    }

    @Override // com.baidu.mobstat.dw
    public byte[] c() {
        return this.a;
    }
}

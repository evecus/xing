package com.baidu.mobstat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class m {
    private a a;
    private Map<String, l> b = new HashMap();

    public interface a {
        List<l> a();
    }

    public m(a aVar) {
        this.a = aVar;
        for (l lVar : aVar.a()) {
            this.b.put(lVar.a(), lVar);
        }
    }

    public l a(String str) {
        return this.b.get(str);
    }

    public List<l> a() {
        return new ArrayList(this.b.values());
    }
}

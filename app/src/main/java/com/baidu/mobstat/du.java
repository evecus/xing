package com.baidu.mobstat;

import androidx.webkit.ProxyConfig;

/* JADX INFO: loaded from: classes.dex */
public class du extends dx implements ds {
    private String a = ProxyConfig.MATCH_ALL_SCHEMES;

    @Override // com.baidu.mobstat.dr
    public String a() {
        return this.a;
    }

    @Override // com.baidu.mobstat.ds
    public void a(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("http resource descriptor must not be null");
        }
        this.a = str;
    }
}

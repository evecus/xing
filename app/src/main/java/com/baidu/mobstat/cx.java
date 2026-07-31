package com.baidu.mobstat;

import java.net.InetSocketAddress;

/* JADX INFO: loaded from: classes.dex */
public interface cx {

    public enum a {
        NOT_YET_CONNECTED,
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED
    }

    public enum b {
        CLIENT
    }

    InetSocketAddress a();

    void a(dp dpVar);
}

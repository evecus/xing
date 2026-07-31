package com.baidu.mobstat;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface dp {

    public enum a {
        CONTINUOUS,
        TEXT,
        BINARY,
        PING,
        PONG,
        CLOSING
    }

    void a(dp dpVar) throws dh;

    ByteBuffer c();

    boolean d();

    boolean e();

    a f();
}

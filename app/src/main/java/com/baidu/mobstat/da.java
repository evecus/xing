package com.baidu.mobstat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface da {
    String a(cx cxVar) throws dg;

    void a(cx cxVar, int i, String str);

    void a(cx cxVar, int i, String str, boolean z);

    void a(cx cxVar, dp dpVar);

    void a(cx cxVar, dr drVar) throws dg;

    void a(cx cxVar, dr drVar, dy dyVar) throws dg;

    void a(cx cxVar, dw dwVar);

    void a(cx cxVar, Exception exc);

    void a(cx cxVar, String str);

    void a(cx cxVar, ByteBuffer byteBuffer);

    void b(cx cxVar);

    void b(cx cxVar, int i, String str, boolean z);

    void b(cx cxVar, dp dpVar);

    InetSocketAddress c(cx cxVar);

    void c(cx cxVar, dp dpVar);
}

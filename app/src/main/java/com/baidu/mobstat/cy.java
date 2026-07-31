package com.baidu.mobstat;

import com.baidu.mobstat.dp;
import java.net.InetSocketAddress;

/* JADX INFO: loaded from: classes.dex */
public abstract class cy implements da {
    @Override // com.baidu.mobstat.da
    public String a(cx cxVar) throws dg {
        InetSocketAddress inetSocketAddressA = cxVar.a();
        if (inetSocketAddressA != null) {
            return "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"" + inetSocketAddressA.getPort() + "\" /></cross-domain-policy>\u0000";
        }
        throw new di("socket not bound");
    }

    @Override // com.baidu.mobstat.da
    public void a(cx cxVar, dp dpVar) {
    }

    @Override // com.baidu.mobstat.da
    public void a(cx cxVar, dr drVar) throws dg {
    }

    @Override // com.baidu.mobstat.da
    public void a(cx cxVar, dr drVar, dy dyVar) throws dg {
    }

    @Override // com.baidu.mobstat.da
    public void b(cx cxVar, dp dpVar) {
        dq dqVar = new dq(dpVar);
        dqVar.a(dp.a.PONG);
        cxVar.a(dqVar);
    }

    @Override // com.baidu.mobstat.da
    public void c(cx cxVar, dp dpVar) {
    }
}

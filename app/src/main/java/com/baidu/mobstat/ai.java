package com.baidu.mobstat;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class ai implements ah {
    private BigInteger a;
    private BigInteger b;

    public ai(byte[] bArr, byte[] bArr2) {
        this.a = new BigInteger(bArr);
        this.b = new BigInteger(bArr2);
    }

    @Override // com.baidu.mobstat.ah
    public BigInteger a() {
        return this.a;
    }

    @Override // com.baidu.mobstat.ah
    public BigInteger b() {
        return this.b;
    }
}

package com.baidu.mobstat;

import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class s {
    public static final SecureRandom a = new SecureRandom();
    private u b;

    public s() {
        this.b = null;
        this.b = new u(new r(), 16);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws InvalidAlgorithmParameterException {
        s sVar = new s();
        sVar.a(1, bArr, bArr2);
        return sVar.a(bArr3);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) throws InvalidAlgorithmParameterException {
        s sVar = new s();
        sVar.a(2, bArr, bArr2);
        return sVar.a(bArr3);
    }

    public void a(int i, byte[] bArr, byte[] bArr2) throws InvalidAlgorithmParameterException {
        this.b.a(i, bArr, bArr2, a);
    }

    public final byte[] a(byte[] bArr) {
        if (bArr != null) {
            return this.b.a(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("Null input buffer");
    }
}

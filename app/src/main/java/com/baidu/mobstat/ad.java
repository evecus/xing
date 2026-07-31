package com.baidu.mobstat;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class ad {
    private static byte[] a;
    private static byte[] b;

    public static byte[] a() {
        byte[] bArr = a;
        if (bArr != null) {
            return bArr;
        }
        byte[] byteArray = new BigInteger(ac.a).modPow(new BigInteger(ac.b), new BigInteger(ac.e)).toByteArray();
        a = byteArray;
        return byteArray;
    }

    public static byte[] b() {
        byte[] bArr = b;
        if (bArr != null) {
            return bArr;
        }
        byte[] byteArray = new BigInteger(ac.c).modPow(new BigInteger(ac.d), new BigInteger(ac.e)).toByteArray();
        b = byteArray;
        return byteArray;
    }
}

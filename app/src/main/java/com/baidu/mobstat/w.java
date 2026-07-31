package com.baidu.mobstat;

import androidx.exifinterface.media.ExifInterface;

/* JADX INFO: loaded from: classes.dex */
public class w {
    private static volatile byte[] a;

    public static byte[] a() {
        if (a == null) {
            synchronized (w.class) {
                if (a == null) {
                    byte[] bArr = new byte[16];
                    System.arraycopy(ad.b(), 0, bArr, 0, 16);
                    s sVar = new s();
                    sVar.a(2, bArr, bArr);
                    a = sVar.a(new byte[]{-71, -100, -115, 26, 39, -124, 14, 14, ExifInterface.MARKER_APP1, -46, -56, 1, 25, -127, -99, -107, -54, 51, 46, 14, 68, -68, -19, 28, 66, 19, -113, 5, 25, -11, -123, 50});
                }
            }
        }
        return a;
    }
}

package com.baidu.mobstat;

import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class cl {

    public static class a {
        public static String a(byte[] bArr) {
            try {
                return cl.b(MessageDigest.getInstance("md5"), bArr);
            } catch (Exception e) {
                return "";
            }
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            int i = (b >> 4) & 15;
            int i2 = b & 15;
            sb.append((char) (i >= 10 ? (i + 97) - 10 : i + 48));
            sb.append((char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        return a(messageDigest.digest());
    }
}

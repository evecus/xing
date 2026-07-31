package com.baidu.mobstat;

import android.text.TextUtils;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class cf {

    public static class a {
        public static byte[] a() throws Exception {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom());
            return keyGenerator.generateKey().getEncoded();
        }

        public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr3);
        }

        public static byte[] b() {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            return bArr;
        }
    }

    public static class b {
        public static byte[] a(int i, byte[] bArr) throws Exception {
            int i2 = i - 1;
            if (i2 < 0 || ck.a.length <= i2) {
                return new byte[0];
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(ck.a[i2].getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, secretKeySpec);
            return cipher.doFinal(bArr);
        }

        public static byte[] b(int i, byte[] bArr) throws Exception {
            int i2 = i - 1;
            if (i2 < 0 || ck.a.length <= i2) {
                return new byte[0];
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(ck.a[i2].getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, secretKeySpec);
            return cipher.doFinal(bArr);
        }

        public static String c(int i, byte[] bArr) {
            try {
                return ci.b(a(i, bArr));
            } catch (Exception e) {
                return "";
            }
        }

        public static String d(int i, byte[] bArr) {
            String strC = c(i, bArr);
            return TextUtils.isEmpty(strC) ? "" : strC + "|" + i;
        }
    }
}

package roam.a.a.g.a.a.a.a;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static String a = new String("idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#");

    public static String a() {
        String str = new String();
        for (int i = 0; i < a.length() - 1; i += 4) {
            StringBuilder sbO = roam.a.b.a.a.a.o(str);
            sbO.append(a.charAt(i));
            str = sbO.toString();
        }
        return str;
    }

    public static String b(String str, String str2) {
        byte[] bArrDoFinal;
        try {
            PBEKeySpec pBEKeySpecC = c(str);
            byte[] bytes = str2.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(pBEKeySpecC).getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            byte[] salt = pBEKeySpecC.getSalt();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(salt.length + cipher.getOutputSize(bytes.length));
            byteBufferAllocate.put(salt);
            cipher.doFinal(ByteBuffer.wrap(bytes), byteBufferAllocate);
            return f(byteBufferAllocate.array());
        } catch (Exception e) {
            try {
                byte[] bArrD = d(str.getBytes());
                byte[] bytes2 = str2.getBytes();
                try {
                    SecretKeySpec secretKeySpec2 = new SecretKeySpec(bArrD, "AES");
                    Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher2.init(1, secretKeySpec2, new IvParameterSpec(new byte[cipher2.getBlockSize()]));
                    bArrDoFinal = cipher2.doFinal(bytes2);
                } catch (Throwable th) {
                    bArrDoFinal = null;
                }
                return f(bArrDoFinal);
            } catch (Exception e2) {
                return null;
            }
        }
    }

    public static PBEKeySpec c(String str) throws IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Class<?> cls = Class.forName(new String(a.a("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        Object objNewInstance = cls.newInstance();
        byte[] bArr = new byte[16];
        Method method = cls.getMethod("nextBytes", bArr.getClass());
        method.setAccessible(true);
        method.invoke(objNewInstance, bArr);
        return new PBEKeySpec(str.toCharArray(), bArr, 10, 128);
    }

    public static byte[] d(byte[] bArr) throws IllegalAccessException, NoSuchMethodException, NoSuchAlgorithmException, ClassNotFoundException, InvocationTargetException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        Class<?> cls = Class.forName(new String(a.a("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        Object objInvoke = cls.getMethod("getInstance", String.class, String.class).invoke(null, "SHA1PRNG", "Crypto");
        Method method = cls.getMethod("setSeed", bArr.getClass());
        method.setAccessible(true);
        method.invoke(objInvoke, bArr);
        KeyGenerator.class.getMethod("init", Integer.TYPE, cls).invoke(keyGenerator, 128, objInvoke);
        return keyGenerator.generateKey().getEncoded();
    }

    public static String e(String str, String str2) {
        byte[] bArrDoFinal;
        try {
            PBEKeySpec pBEKeySpecC = c(str);
            byte[] bArrG = g(str2);
            if (bArrG.length <= 16) {
                bArrDoFinal = null;
            } else {
                SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(pBEKeySpecC.getPassword(), Arrays.copyOf(bArrG, 16), 10, 128)).getEncoded(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(2, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
                bArrDoFinal = cipher.doFinal(bArrG, 16, bArrG.length - 16);
            }
        } catch (Exception e) {
        }
        if (bArrDoFinal == null) {
            throw new Exception();
        }
        String str3 = new String(bArrDoFinal);
        if (roam.a.a.a.b.a.y(str3)) {
            return str3;
        }
        try {
            byte[] bArrD = d(str.getBytes());
            byte[] bArrG2 = g(str2);
            SecretKeySpec secretKeySpec2 = new SecretKeySpec(bArrD, "AES");
            Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher2.init(2, secretKeySpec2, new IvParameterSpec(new byte[cipher2.getBlockSize()]));
            String str4 = new String(cipher2.doFinal(bArrG2));
            if (roam.a.a.a.b.a.y(str4)) {
                return str4;
            }
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public static String f(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15));
            stringBuffer.append("0123456789ABCDEF".charAt(b & 15));
        }
        return stringBuffer.toString();
    }

    public static byte[] g(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }
}

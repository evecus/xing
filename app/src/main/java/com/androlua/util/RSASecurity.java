package com.androlua.util;

import com.androlua.LuaApplication;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes.dex */
public class RSASecurity {
    private static final String ALGORITHM = "RSA";
    private static final int KEY_SIZE = 1024;
    private static String PUBLIC_KEY_FILE = LuaApplication.getInstance().getLuaExtDir("PublicKey");
    private static String PRIVATE_KEY_FILE = LuaApplication.getInstance().getLuaExtDir("PrivateKey");

    public static byte[] decrypt(byte[] bArr) throws Exception {
        ObjectInputStream objectInputStream = null;
        try {
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
                try {
                    Key key = (Key) objectInputStream2.readObject();
                    objectInputStream2.close();
                    Cipher cipher = Cipher.getInstance(ALGORITHM);
                    cipher.init(2, key);
                    return cipher.doFinal(bArr);
                } catch (Exception e) {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    objectInputStream = objectInputStream2;
                    objectInputStream.close();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static byte[] encrypt(String str) throws Exception {
        generateKeyPair();
        ObjectInputStream objectInputStream = null;
        try {
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
                try {
                    Key key = (Key) objectInputStream2.readObject();
                    objectInputStream2.close();
                    Cipher cipher = Cipher.getInstance(ALGORITHM);
                    cipher.init(1, key);
                    return cipher.doFinal(str.getBytes());
                } catch (Exception e) {
                    objectInputStream = objectInputStream2;
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    objectInputStream = objectInputStream2;
                    objectInputStream.close();
                    throw th;
                }
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void generateKeyPair() throws Throwable {
        ObjectOutputStream objectOutputStream;
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(1024, secureRandom);
        keyPairGenerator.initialize(1024);
        KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPairGenerateKeyPair.getPublic();
        PrivateKey privateKey = keyPairGenerateKeyPair.getPrivate();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            try {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
                try {
                    objectOutputStream3.writeObject(publicKey);
                    objectOutputStream.writeObject(privateKey);
                    objectOutputStream3.close();
                    objectOutputStream.close();
                } catch (Exception e) {
                    e = e;
                    objectOutputStream2 = objectOutputStream3;
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        objectOutputStream2.close();
                        objectOutputStream.close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream2 = objectOutputStream3;
                    objectOutputStream2.close();
                    objectOutputStream.close();
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                objectOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                objectOutputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            objectOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            objectOutputStream = null;
        }
    }
}

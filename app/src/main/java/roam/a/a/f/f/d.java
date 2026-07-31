package roam.a.a.f.f;

import com.bumptech.glide.load.Key;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public String a;

    public d(boolean z) {
        double dRandom;
        double d;
        String strValueOf;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            int iNextInt = random.nextInt(3);
            if (iNextInt == 0) {
                dRandom = Math.random() * 25.0d;
                d = 65.0d;
            } else if (iNextInt == 1) {
                dRandom = Math.random() * 25.0d;
                d = 97.0d;
            } else if (iNextInt == 2) {
                strValueOf = String.valueOf(new Random().nextInt(10));
                sb.append(strValueOf);
            }
            strValueOf = String.valueOf((char) Math.round(d + dRandom));
            sb.append(strValueOf);
        }
        this.a = sb.toString();
    }

    public static byte[] c(byte[]... bArr) throws Throwable {
        DataOutputStream dataOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] byteArray = null;
        byteArray = null;
        byteArray = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bArr.length != 0) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    for (int i = 0; i < bArr.length; i++) {
                        try {
                            dataOutputStream.write(String.format(Locale.getDefault(), "%05d", Integer.valueOf(bArr[i].length)).getBytes());
                            dataOutputStream.write(bArr[i]);
                        } catch (Exception e) {
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception e2) {
                                }
                            }
                            if (dataOutputStream != null) {
                            }
                            return byteArray;
                        } catch (Throwable th) {
                            th = th;
                            byteArrayOutputStream2 = byteArrayOutputStream;
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArrayOutputStream2.close();
                                } catch (Exception e3) {
                                }
                            }
                            if (dataOutputStream == null) {
                                throw th;
                            }
                            try {
                                dataOutputStream.close();
                                throw th;
                            } catch (Exception e4) {
                                throw th;
                            }
                        }
                    }
                    dataOutputStream.flush();
                    byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e5) {
                    }
                } catch (Exception e6) {
                    dataOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream = null;
                }
            } catch (Exception e7) {
                byteArrayOutputStream = null;
                dataOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                dataOutputStream = null;
            }
            try {
                dataOutputStream.close();
            } catch (Exception e8) {
            }
        }
        return byteArray;
    }

    public final a a(b bVar) {
        ByteArrayInputStream byteArrayInputStream;
        String str;
        String str2;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bVar.b);
        } catch (Exception e) {
            byteArrayInputStream = null;
            str = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                byte[] bArr = new byte[5];
                byteArrayInputStream.read(bArr);
                byte[] bArr2 = new byte[Integer.parseInt(new String(bArr))];
                byteArrayInputStream.read(bArr2);
                str = new String(bArr2);
                try {
                    byte[] bArr3 = new byte[5];
                    byteArrayInputStream.read(bArr3);
                    int i = Integer.parseInt(new String(bArr3));
                    if (i > 0) {
                        byte[] bArr4 = new byte[i];
                        byteArrayInputStream.read(bArr4);
                        byte[] bArrB = roam.a.a.f.d.b.b(this.a, bArr4);
                        if (bVar.a) {
                            bArrB = roam.a.a.a.b.a.w(bArrB);
                        }
                        str2 = new String(bArrB);
                    } else {
                        str2 = null;
                    }
                    try {
                        byteArrayInputStream.close();
                    } catch (Exception e2) {
                    }
                } catch (Exception e3) {
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    str2 = null;
                }
            } catch (Exception e5) {
                str = null;
            }
            if (str == null && str2 == null) {
                return null;
            }
            return new a(str, str2);
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream2 = byteArrayInputStream;
            if (byteArrayInputStream2 != null) {
                try {
                    byteArrayInputStream2.close();
                } catch (Exception e6) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final b b(a aVar, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bytes = aVar.a.getBytes();
        byte[] bytes2 = aVar.b.getBytes();
        if (z) {
            try {
                bytes2 = roam.a.a.a.b.a.r(bytes2);
            } catch (Exception e) {
                z = false;
            }
        }
        String str = this.a;
        ByteArrayOutputStream byteArray = null;
        try {
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(roam.a.a.f.d.a.b(roam.a.a.f.b.a.a)));
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, publicKeyGeneratePublic);
            byte[] bytes3 = str.getBytes(Key.STRING_CHARSET_NAME);
            int blockSize = cipher.getBlockSize();
            byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = 0; i < bytes3.length; i += blockSize) {
                try {
                    byteArrayOutputStream.write(cipher.doFinal(bytes3, i, bytes3.length - i < blockSize ? bytes3.length - i : blockSize));
                } catch (Exception e2) {
                    if (byteArrayOutputStream != null) {
                    }
                    return new b(z, c(bytes, byteArray, roam.a.a.f.d.b.a(this.a, bytes2)));
                } catch (Throwable th) {
                    th = th;
                    byteArray = byteArrayOutputStream;
                    if (byteArray != null) {
                        try {
                            byteArray.close();
                        } catch (IOException e3) {
                        }
                    }
                    throw th;
                }
            }
            byteArray = byteArrayOutputStream.toByteArray();
        } catch (Exception e4) {
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException e5) {
        }
        return new b(z, c(bytes, byteArray, roam.a.a.f.d.b.a(this.a, bytes2)));
    }
}

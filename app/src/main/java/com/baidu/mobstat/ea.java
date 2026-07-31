package com.baidu.mobstat;

import androidx.exifinterface.media.ExifInterface;
import com.android.cglib.dx.io.Opcodes;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class ea {
    static final /* synthetic */ boolean a = true;
    private static final byte[] b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] c = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, ExifInterface.START_CODE, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] d = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] e = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, ExifInterface.START_CODE, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] f = {45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
    private static final byte[] g = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, ExifInterface.START_CODE, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    public static class a extends FilterOutputStream {
        private boolean a;
        private int b;
        private byte[] c;
        private int d;
        private int e;
        private boolean f;
        private byte[] g;
        private boolean h;
        private int i;
        private byte[] j;

        public a(OutputStream outputStream, int i) {
            super(outputStream);
            this.f = (i & 8) != 0;
            boolean z = (i & 1) != 0;
            this.a = z;
            int i2 = z ? 3 : 4;
            this.d = i2;
            this.c = new byte[i2];
            this.b = 0;
            this.e = 0;
            this.h = false;
            this.g = new byte[4];
            this.i = i;
            this.j = ea.c(i);
        }

        public void a() throws IOException {
            if (this.b > 0) {
                if (!this.a) {
                    throw new IOException("Base64 input not properly padded.");
                }
                this.out.write(ea.b(this.g, this.c, this.b, this.i));
                this.b = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a();
            super.close();
            this.c = null;
            this.out = null;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            if (this.h) {
                this.out.write(i);
                return;
            }
            if (this.a) {
                byte[] bArr = this.c;
                int i2 = this.b;
                int i3 = i2 + 1;
                this.b = i3;
                bArr[i2] = (byte) i;
                if (i3 >= this.d) {
                    this.out.write(ea.b(this.g, this.c, this.d, this.i));
                    int i4 = this.e + 4;
                    this.e = i4;
                    if (this.f && i4 >= 76) {
                        this.out.write(10);
                        this.e = 0;
                    }
                    this.b = 0;
                    return;
                }
                return;
            }
            byte b = this.j[i & Opcodes.NEG_FLOAT];
            if (b <= -5) {
                if (b != -5) {
                    throw new IOException("Invalid character in Base64 data.");
                }
                return;
            }
            byte[] bArr2 = this.c;
            int i5 = this.b;
            int i6 = i5 + 1;
            this.b = i6;
            bArr2[i5] = (byte) i;
            if (i6 >= this.d) {
                this.out.write(this.g, 0, ea.b(bArr2, 0, this.g, 0, this.i));
                this.b = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.h) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }
    }

    private ea() {
    }

    public static String a(byte[] bArr) throws Throwable {
        String strA;
        try {
            strA = a(bArr, 0, bArr.length, 0);
        } catch (IOException e2) {
            if (!a) {
                throw new AssertionError(e2.getMessage());
            }
            strA = null;
        }
        if (a || strA != null) {
            return strA;
        }
        throw new AssertionError();
    }

    public static String a(byte[] bArr, int i, int i2, int i3) throws Throwable {
        byte[] bArrB = b(bArr, i, i2, i3);
        try {
            return new String(bArrB, "US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            return new String(bArrB);
        }
    }

    private static byte[] a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] bArrB = b(i4);
        int i5 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 2 ? (bArr[i + 2] << 24) >>> 24 : 0);
        switch (i2) {
            case 1:
                bArr2[i3] = bArrB[i5 >>> 18];
                bArr2[i3 + 1] = bArrB[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = 61;
                bArr2[i3 + 3] = 61;
                break;
            case 2:
                bArr2[i3] = bArrB[i5 >>> 18];
                bArr2[i3 + 1] = bArrB[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = bArrB[(i5 >>> 6) & 63];
                bArr2[i3 + 3] = 61;
                break;
            case 3:
                bArr2[i3] = bArrB[i5 >>> 18];
                bArr2[i3 + 1] = bArrB[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = bArrB[(i5 >>> 6) & 63];
                bArr2[i3 + 3] = bArrB[i5 & 63];
                break;
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5;
        if (bArr == null) {
            throw new NullPointerException("Source array was null.");
        }
        if (bArr2 == null) {
            throw new NullPointerException("Destination array was null.");
        }
        if (i < 0 || (i4 = i + 3) >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i)));
        }
        if (i2 < 0 || (i5 = i2 + 2) >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i2)));
        }
        byte[] bArrC = c(i3);
        byte b2 = bArr[i + 2];
        if (b2 == 61) {
            bArr2[i2] = (byte) ((((bArrC[bArr[i + 1]] & ExifInterface.MARKER) << 12) | ((bArrC[bArr[i]] & ExifInterface.MARKER) << 18)) >>> 16);
            return 1;
        }
        byte b3 = bArr[i4];
        if (b3 == 61) {
            int i6 = ((bArrC[bArr[i + 1]] & ExifInterface.MARKER) << 12) | ((bArrC[bArr[i]] & ExifInterface.MARKER) << 18) | ((bArrC[b2] & ExifInterface.MARKER) << 6);
            bArr2[i2] = (byte) (i6 >>> 16);
            bArr2[i2 + 1] = (byte) (i6 >>> 8);
            return 2;
        }
        int i7 = ((bArrC[bArr[i + 1]] & ExifInterface.MARKER) << 12) | ((bArrC[bArr[i]] & ExifInterface.MARKER) << 18) | ((bArrC[b2] & ExifInterface.MARKER) << 6) | (bArrC[b3] & ExifInterface.MARKER);
        bArr2[i2] = (byte) (i7 >> 16);
        bArr2[i2 + 1] = (byte) (i7 >> 8);
        bArr2[i5] = (byte) i7;
        return 3;
    }

    private static final byte[] b(int i) {
        return (i & 16) == 16 ? d : (i & 32) == 32 ? f : b;
    }

    public static byte[] b(byte[] bArr, int i, int i2, int i3) throws Throwable {
        int i4;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        a aVar;
        GZIPOutputStream gZIPOutputStream;
        Throwable th2;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i2);
        }
        if (i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)));
        }
        if ((i3 & 2) == 0) {
            boolean z = (i3 & 8) != 0;
            int i5 = ((i2 / 3) * 4) + (i2 % 3 > 0 ? 4 : 0);
            int i6 = z ? i5 + (i5 / 76) : i5;
            byte[] bArr2 = new byte[i6];
            int i7 = i2 - 2;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i8 < i7) {
                int i11 = i8;
                a(bArr, i8 + i, 3, bArr2, i9, i3);
                int i12 = i10 + 4;
                if (!z || i12 < 76) {
                    i10 = i12;
                } else {
                    bArr2[i9 + 4] = 10;
                    i9++;
                    i10 = 0;
                }
                i8 = i11 + 3;
                i9 += 4;
            }
            int i13 = i8;
            if (i13 < i2) {
                a(bArr, i13 + i, i2 - i13, bArr2, i9, i3);
                i4 = i9 + 4;
            } else {
                i4 = i9;
            }
            if (i4 > i6 - 1) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i4];
            System.arraycopy(bArr2, 0, bArr3, 0, i4);
            return bArr3;
        }
        GZIPOutputStream gZIPOutputStream2 = null;
        gZIPOutputStream2 = null;
        gZIPOutputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException e2) {
            e = e2;
            aVar = null;
            gZIPOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            aVar = null;
        }
        try {
            aVar = new a(byteArrayOutputStream, i3 | 1);
            try {
                gZIPOutputStream = new GZIPOutputStream(aVar);
            } catch (IOException e3) {
                e = e3;
                gZIPOutputStream = null;
            } catch (Throwable th4) {
                th2 = th4;
                th = th2;
                try {
                    gZIPOutputStream2.close();
                } catch (Exception e4) {
                }
                try {
                    aVar.close();
                } catch (Exception e5) {
                }
                try {
                    byteArrayOutputStream.close();
                    throw th;
                } catch (Exception e6) {
                    throw th;
                }
            }
        } catch (IOException e7) {
            e = e7;
            aVar = null;
            gZIPOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            aVar = null;
            gZIPOutputStream2.close();
            aVar.close();
            byteArrayOutputStream.close();
            throw th;
        }
        try {
            gZIPOutputStream.write(bArr, i, i2);
            gZIPOutputStream.close();
            try {
                gZIPOutputStream.close();
            } catch (Exception e8) {
            }
            try {
                aVar.close();
            } catch (Exception e9) {
            }
            try {
                byteArrayOutputStream.close();
            } catch (Exception e10) {
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e11) {
            e = e11;
            byteArrayOutputStream2 = byteArrayOutputStream;
            try {
                throw e;
            } catch (Throwable th6) {
                th2 = th6;
                byteArrayOutputStream = byteArrayOutputStream2;
                gZIPOutputStream2 = gZIPOutputStream;
                th = th2;
                gZIPOutputStream2.close();
                aVar.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (Throwable th7) {
            th2 = th7;
            gZIPOutputStream2 = gZIPOutputStream;
            th = th2;
            gZIPOutputStream2.close();
            aVar.close();
            byteArrayOutputStream.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(byte[] bArr, byte[] bArr2, int i, int i2) {
        a(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] c(int i) {
        return (i & 16) == 16 ? e : (i & 32) == 32 ? g : c;
    }
}

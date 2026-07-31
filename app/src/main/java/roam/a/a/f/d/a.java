package roam.a.a.f.d;

import com.android.cglib.dx.io.Opcodes;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final byte[] a = new byte[128];
    public static final char[] b = new char[64];

    static {
        int i;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < 128; i4++) {
            a[i4] = -1;
        }
        for (int i5 = 90; i5 >= 65; i5--) {
            a[i5] = (byte) (i5 - 65);
        }
        int i6 = 122;
        while (true) {
            i = 26;
            if (i6 < 97) {
                break;
            }
            a[i6] = (byte) ((i6 - 97) + 26);
            i6--;
        }
        int i7 = 57;
        while (true) {
            i2 = 52;
            if (i7 < 48) {
                break;
            }
            a[i7] = (byte) ((i7 - 48) + 52);
            i7--;
        }
        byte[] bArr = a;
        bArr[43] = 62;
        bArr[47] = 63;
        for (int i8 = 0; i8 <= 25; i8++) {
            b[i8] = (char) (i8 + 65);
        }
        int i9 = 0;
        while (i <= 51) {
            b[i] = (char) (i9 + 97);
            i++;
            i9++;
        }
        while (i2 <= 61) {
            b[i2] = (char) (i3 + 48);
            i2++;
            i3++;
        }
        char[] cArr = b;
        cArr[62] = '+';
        cArr[63] = '/';
    }

    public static String a(byte[] bArr) {
        char[] cArr = b;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i = length % 24;
        int i2 = length / 24;
        char[] cArr2 = new char[(i != 0 ? i2 + 1 : i2) * 4];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i3 + 1;
            byte b2 = bArr[i3];
            int i7 = i6 + 1;
            byte b3 = bArr[i6];
            byte b4 = bArr[i7];
            byte b5 = (byte) (b3 & 15);
            byte b6 = (byte) (b2 & 3);
            int i8 = b2 >> 2;
            if ((b2 & (-128)) != 0) {
                i8 ^= Opcodes.AND_LONG_2ADDR;
            }
            byte b7 = (byte) i8;
            int i9 = b3 >> 4;
            if ((b3 & (-128)) != 0) {
                i9 ^= 240;
            }
            byte b8 = (byte) i9;
            int i10 = (b4 & (-128)) == 0 ? b4 >> 6 : (b4 >> 6) ^ 252;
            int i11 = i4 + 1;
            cArr2[i4] = cArr[b7];
            int i12 = i11 + 1;
            cArr2[i11] = cArr[(b6 << 4) | b8];
            int i13 = i12 + 1;
            cArr2[i12] = cArr[(b5 << 2) | ((byte) i10)];
            cArr2[i13] = cArr[b4 & 63];
            i4 = i13 + 1;
            i3 = i7 + 1;
        }
        if (i == 8) {
            byte b9 = bArr[i3];
            byte b10 = (byte) (b9 & 3);
            int i14 = b9 >> 2;
            if ((b9 & (-128)) != 0) {
                i14 ^= Opcodes.AND_LONG_2ADDR;
            }
            byte b11 = (byte) i14;
            int i15 = i4 + 1;
            cArr2[i4] = cArr[b11];
            int i16 = i15 + 1;
            cArr2[i15] = cArr[b10 << 4];
            cArr2[i16] = '=';
            cArr2[i16 + 1] = '=';
        } else if (i == 16) {
            byte b12 = bArr[i3];
            byte b13 = bArr[i3 + 1];
            byte b14 = (byte) (b13 & 15);
            byte b15 = (byte) (b12 & 3);
            int i17 = b12 >> 2;
            if ((b12 & (-128)) != 0) {
                i17 ^= Opcodes.AND_LONG_2ADDR;
            }
            byte b16 = (byte) i17;
            int i18 = b13 >> 4;
            if ((b13 & (-128)) != 0) {
                i18 ^= 240;
            }
            byte b17 = (byte) i18;
            int i19 = i4 + 1;
            cArr2[i4] = cArr[b16];
            int i20 = i19 + 1;
            cArr2[i19] = cArr[b17 | (b15 << 4)];
            cArr2[i20] = cArr[b14 << 2];
            cArr2[i20 + 1] = '=';
        }
        return new String(cArr2);
    }

    public static byte[] b(String str) {
        int i;
        byte[] bArr = a;
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        if (charArray == null) {
            i = 0;
        } else {
            i = 0;
            for (char c : charArray) {
                if (c != ' ' && c != '\r' && c != '\n' && c != '\t') {
                    charArray[i] = c;
                    i++;
                }
            }
        }
        if (i % 4 != 0) {
            return null;
        }
        int i2 = i / 4;
        if (i2 == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[i2 * 3];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < i2 - 1) {
            int i6 = i4 + 1;
            char c2 = charArray[i4];
            if (!d(c2)) {
                return null;
            }
            int i7 = i6 + 1;
            char c3 = charArray[i6];
            if (!d(c3)) {
                return null;
            }
            int i8 = i7 + 1;
            char c4 = charArray[i7];
            if (!d(c4)) {
                return null;
            }
            char c5 = charArray[i8];
            if (!d(c5)) {
                return null;
            }
            byte b2 = bArr[c2];
            byte b3 = bArr[c3];
            byte b4 = bArr[c4];
            byte b5 = bArr[c5];
            int i9 = i5 + 1;
            bArr2[i5] = (byte) ((b2 << 2) | (b3 >> 4));
            int i10 = i9 + 1;
            bArr2[i9] = (byte) (((b3 & 15) << 4) | ((b4 >> 2) & 15));
            i5 = i10 + 1;
            bArr2[i10] = (byte) ((b4 << 6) | b5);
            i3++;
            i4 = i8 + 1;
        }
        int i11 = i4 + 1;
        char c6 = charArray[i4];
        if (!d(c6)) {
            return null;
        }
        int i12 = i11 + 1;
        char c7 = charArray[i11];
        if (!d(c7)) {
            return null;
        }
        byte b6 = bArr[c6];
        byte b7 = bArr[c7];
        char c8 = charArray[i12];
        char c9 = charArray[i12 + 1];
        if (d(c8) && d(c9)) {
            byte b8 = bArr[c8];
            byte b9 = bArr[c9];
            int i13 = i5 + 1;
            bArr2[i5] = (byte) ((b6 << 2) | (b7 >> 4));
            bArr2[i13] = (byte) (((b7 & 15) << 4) | ((b8 >> 2) & 15));
            bArr2[i13 + 1] = (byte) (b9 | (b8 << 6));
            return bArr2;
        }
        if (c(c8) && c(c9)) {
            if ((b7 & 15) != 0) {
                return null;
            }
            int i14 = i3 * 3;
            byte[] bArr3 = new byte[i14 + 1];
            System.arraycopy(bArr2, 0, bArr3, 0, i14);
            bArr3[i5] = (byte) ((b6 << 2) | (b7 >> 4));
            return bArr3;
        }
        if (c(c8) || !c(c9)) {
            return null;
        }
        byte b10 = bArr[c8];
        if ((b10 & 3) != 0) {
            return null;
        }
        int i15 = i3 * 3;
        byte[] bArr4 = new byte[i15 + 2];
        System.arraycopy(bArr2, 0, bArr4, 0, i15);
        bArr4[i5] = (byte) ((b6 << 2) | (b7 >> 4));
        bArr4[i5 + 1] = (byte) (((b10 >> 2) & 15) | ((b7 & 15) << 4));
        return bArr4;
    }

    public static boolean c(char c) {
        return c == '=';
    }

    public static boolean d(char c) {
        return c < 128 && a[c] != -1;
    }
}

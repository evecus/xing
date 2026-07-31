package com.android.cglib.dx.util;

import androidx.exifinterface.media.ExifInterface;
import com.android.cglib.dx.io.Opcodes;
import java.io.UTFDataFormatException;

/* JADX INFO: loaded from: classes.dex */
public final class Mutf8 {
    private Mutf8() {
    }

    private static long countBytes(String str, boolean z) throws UTFDataFormatException {
        int length = str.length();
        long j = 0;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            j += (cCharAt == 0 || cCharAt > 127) ? cCharAt <= 2047 ? 2L : 3L : 1L;
            if (z && j > 65535) {
                throw new UTFDataFormatException("String more than 65535 UTF bytes long");
            }
        }
        return j;
    }

    public static String decode(ByteInput byteInput, char[] cArr) throws UTFDataFormatException {
        int i = 0;
        while (true) {
            char c = (char) (byteInput.readByte() & ExifInterface.MARKER);
            if (c == 0) {
                return new String(cArr, 0, i);
            }
            cArr[i] = c;
            if (c >= 128) {
                if ((c & 224) == 192) {
                    int i2 = byteInput.readByte() & ExifInterface.MARKER;
                    if ((i2 & Opcodes.AND_LONG_2ADDR) != 128) {
                        throw new UTFDataFormatException("bad second byte");
                    }
                    cArr[i] = (char) (((c & 31) << 6) | (i2 & 63));
                } else {
                    if ((c & 240) != 224) {
                        throw new UTFDataFormatException("bad byte");
                    }
                    int i3 = byteInput.readByte() & ExifInterface.MARKER;
                    int i4 = byteInput.readByte() & ExifInterface.MARKER;
                    if ((i3 & Opcodes.AND_LONG_2ADDR) != 128 || (i4 & Opcodes.AND_LONG_2ADDR) != 128) {
                        break;
                    }
                    cArr[i] = (char) (((c & 15) << 12) | ((i3 & 63) << 6) | (i4 & 63));
                }
            }
            i++;
        }
        throw new UTFDataFormatException("bad second or third byte");
    }

    public static void encode(byte[] bArr, int i, String str) {
        int i2;
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            if (cCharAt != 0 && cCharAt <= 127) {
                i2 = i + 1;
                bArr[i] = (byte) cCharAt;
            } else if (cCharAt <= 2047) {
                int i4 = i + 1;
                bArr[i] = (byte) (((cCharAt >> 6) & 31) | Opcodes.AND_LONG_2ADDR);
                i = i4 + 1;
                bArr[i4] = (byte) ((cCharAt & '?') | 128);
            } else {
                int i5 = i + 1;
                bArr[i] = (byte) (((cCharAt >> '\f') & 15) | Opcodes.SHL_INT_LIT8);
                int i6 = i5 + 1;
                bArr[i5] = (byte) (((cCharAt >> 6) & 63) | 128);
                i2 = i6 + 1;
                bArr[i6] = (byte) ((cCharAt & '?') | 128);
            }
            i = i2;
        }
    }

    public static byte[] encode(String str) {
        byte[] bArr = new byte[(int) countBytes(str, true)];
        encode(bArr, 0, str);
        return bArr;
    }
}

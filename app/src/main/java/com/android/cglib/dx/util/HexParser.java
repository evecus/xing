package com.android.cglib.dx.util;

import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class HexParser {
    private HexParser() {
    }

    public static byte[] parse(String str) {
        int iIndexOf;
        int length = str.length();
        int i = length / 2;
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int iIndexOf2 = str.indexOf(10, i3);
            if (iIndexOf2 < 0) {
                iIndexOf2 = length;
            }
            int iIndexOf3 = str.indexOf(35, i3);
            String strSubstring = (iIndexOf3 < 0 || iIndexOf3 >= iIndexOf2) ? str.substring(i3, iIndexOf2) : str.substring(i3, iIndexOf3);
            int iIndexOf4 = strSubstring.indexOf(58);
            if (iIndexOf4 != -1 && ((iIndexOf = strSubstring.indexOf(34)) == -1 || iIndexOf >= iIndexOf4)) {
                String strTrim = strSubstring.substring(i2, iIndexOf4).trim();
                strSubstring = strSubstring.substring(iIndexOf4 + 1);
                if (Integer.parseInt(strTrim, 16) != i4) {
                    throw new RuntimeException(a.j("bogus offset marker: ", strTrim));
                }
            }
            int length2 = strSubstring.length();
            int i5 = i2;
            int i6 = i5;
            int i7 = -1;
            while (i5 < length2) {
                char cCharAt = strSubstring.charAt(i5);
                if (i6 != 0) {
                    if (cCharAt == '\"') {
                        i6 = 0;
                    } else {
                        bArr[i4] = (byte) cCharAt;
                        i4++;
                    }
                } else if (cCharAt > ' ') {
                    if (cCharAt != '\"') {
                        int iDigit = Character.digit(cCharAt, 16);
                        if (iDigit == -1) {
                            throw new RuntimeException("bogus digit character: \"" + cCharAt + "\"");
                        }
                        if (i7 == -1) {
                            i7 = iDigit;
                        } else {
                            bArr[i4] = (byte) ((i7 << 4) | iDigit);
                            i4++;
                            i7 = -1;
                        }
                    } else {
                        if (i7 != -1) {
                            StringBuilder sbO = a.o("spare digit around offset ");
                            sbO.append(Hex.u4(i4));
                            throw new RuntimeException(sbO.toString());
                        }
                        i6 = 1;
                    }
                }
                i5++;
            }
            if (i7 != -1) {
                StringBuilder sbO2 = a.o("spare digit around offset ");
                sbO2.append(Hex.u4(i4));
                throw new RuntimeException(sbO2.toString());
            }
            if (i6 != 0) {
                StringBuilder sbO3 = a.o("unterminated quote around offset ");
                sbO3.append(Hex.u4(i4));
                throw new RuntimeException(sbO3.toString());
            }
            i3 = iIndexOf2 + 1;
            i2 = 0;
        }
        if (i4 >= i) {
            return bArr;
        }
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, 0, bArr2, 0, i4);
        return bArr2;
    }
}

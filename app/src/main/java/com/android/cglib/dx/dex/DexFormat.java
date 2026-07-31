package com.android.cglib.dx.dex;

import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class DexFormat {
    public static final int API_CURRENT = 14;
    public static final int API_NO_EXTENDED_OPCODES = 13;
    public static final String DEX_IN_JAR_NAME = "classes.dex";
    public static final int ENDIAN_TAG = 305419896;
    public static final String MAGIC_PREFIX = "dex\n";
    public static final String MAGIC_SUFFIX = "\u0000";
    public static final String VERSION_CURRENT = "036";
    public static final String VERSION_FOR_API_13 = "035";

    private DexFormat() {
    }

    public static String apiToMagic(int i) {
        return a.k(MAGIC_PREFIX, i >= 14 ? VERSION_CURRENT : VERSION_FOR_API_13, "\u0000");
    }

    public static int magicToApi(byte[] bArr) {
        if (bArr.length == 8 && bArr[0] == 100 && bArr[1] == 101 && bArr[2] == 120 && bArr[3] == 10 && bArr[7] == 0) {
            StringBuilder sbO = a.o("");
            sbO.append((char) bArr[4]);
            sbO.append((char) bArr[5]);
            sbO.append((char) bArr[6]);
            String string = sbO.toString();
            if (string.equals(VERSION_CURRENT)) {
                return 14;
            }
            if (string.equals(VERSION_FOR_API_13)) {
                return 13;
            }
        }
        return -1;
    }
}

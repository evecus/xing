package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.io.Opcodes;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.ByteArray;
import com.android.cglib.dx.util.Hex;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstString extends TypedConstant {
    public static final CstString EMPTY_STRING = new CstString("");
    private final ByteArray bytes;
    private final String string;

    public CstString(ByteArray byteArray) {
        Objects.requireNonNull(byteArray, "bytes == null");
        this.bytes = byteArray;
        this.string = utf8BytesToString(byteArray).intern();
    }

    public CstString(String str) {
        Objects.requireNonNull(str, "string == null");
        this.string = str.intern();
        this.bytes = new ByteArray(stringToUtf8Bytes(str));
    }

    public static byte[] stringToUtf8Bytes(String str) {
        int length = str.length();
        byte[] bArr = new byte[length * 3];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt != 0 && cCharAt < 128) {
                bArr[i] = (byte) cCharAt;
                i++;
            } else if (cCharAt < 2048) {
                bArr[i] = (byte) (((cCharAt >> 6) & 31) | Opcodes.AND_LONG_2ADDR);
                bArr[i + 1] = (byte) ((cCharAt & '?') | 128);
                i += 2;
            } else {
                bArr[i] = (byte) (((cCharAt >> '\f') & 15) | Opcodes.SHL_INT_LIT8);
                bArr[i + 1] = (byte) (((cCharAt >> 6) & 63) | 128);
                bArr[i + 2] = (byte) ((cCharAt & '?') | 128);
                i += 3;
            }
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    private static String throwBadUtf8(int i, int i2) {
        StringBuilder sbO = a.o("bad utf-8 byte ");
        sbO.append(Hex.u1(i));
        sbO.append(" at offset ");
        sbO.append(Hex.u4(i2));
        throw new IllegalArgumentException(sbO.toString());
    }

    public static String utf8BytesToString(ByteArray byteArray) {
        char c;
        int i;
        int unsignedByte;
        int i2;
        int i3;
        int size = byteArray.size();
        char[] cArr = new char[size];
        int i4 = 0;
        int i5 = 0;
        while (size > 0) {
            int unsignedByte2 = byteArray.getUnsignedByte(i5);
            switch (unsignedByte2 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    size--;
                    if (unsignedByte2 == 0) {
                        return throwBadUtf8(unsignedByte2, i5);
                    }
                    c = (char) unsignedByte2;
                    i5++;
                    cArr[i4] = c;
                    i4++;
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    return throwBadUtf8(unsignedByte2, i5);
                case 12:
                case 13:
                    size -= 2;
                    if (size < 0) {
                        return throwBadUtf8(unsignedByte2, i5);
                    }
                    i = i5 + 1;
                    unsignedByte = byteArray.getUnsignedByte(i);
                    if ((unsignedByte & Opcodes.AND_LONG_2ADDR) != 128 || ((i2 = ((unsignedByte2 & 31) << 6) | (unsignedByte & 63)) != 0 && i2 < 128)) {
                        return throwBadUtf8(unsignedByte, i);
                    }
                    c = (char) i2;
                    i5 += 2;
                    cArr[i4] = c;
                    i4++;
                    break;
                    break;
                case 14:
                    size -= 3;
                    if (size < 0) {
                        return throwBadUtf8(unsignedByte2, i5);
                    }
                    i = i5 + 1;
                    unsignedByte = byteArray.getUnsignedByte(i);
                    int i6 = unsignedByte & Opcodes.AND_LONG_2ADDR;
                    if (i6 != 128) {
                        return throwBadUtf8(unsignedByte, i);
                    }
                    int i7 = i5 + 2;
                    int unsignedByte3 = byteArray.getUnsignedByte(i7);
                    if (i6 != 128 || (i3 = ((unsignedByte2 & 15) << 12) | ((unsignedByte & 63) << 6) | (unsignedByte3 & 63)) < 2048) {
                        return throwBadUtf8(unsignedByte3, i7);
                    }
                    c = (char) i3;
                    i5 += 3;
                    cArr[i4] = c;
                    i4++;
                    break;
                    break;
            }
        }
        return new String(cArr, 0, i4);
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public int compareTo0(Constant constant) {
        return this.string.compareTo(((CstString) constant).string);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CstString) {
            return this.string.equals(((CstString) obj).string);
        }
        return false;
    }

    public ByteArray getBytes() {
        return this.bytes;
    }

    public String getString() {
        return this.string;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.STRING;
    }

    public int getUtf16Size() {
        return this.string.length();
    }

    public int getUtf8Size() {
        return this.bytes.size();
    }

    public int hashCode() {
        return this.string.hashCode();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        String str;
        int length = this.string.length();
        StringBuilder sb = new StringBuilder((length * 3) / 2);
        int i = 0;
        while (i < length) {
            char cCharAt = this.string.charAt(i);
            if (cCharAt < ' ' || cCharAt >= 127) {
                if (cCharAt <= 127) {
                    if (cCharAt == '\t') {
                        str = "\\t";
                    } else if (cCharAt == '\n') {
                        str = "\\n";
                    } else if (cCharAt != '\r') {
                        char cCharAt2 = i < length + (-1) ? this.string.charAt(i + 1) : (char) 0;
                        boolean z = cCharAt2 >= '0' && cCharAt2 <= '7';
                        sb.append('\\');
                        for (int i2 = 6; i2 >= 0; i2 -= 3) {
                            char c = (char) (((cCharAt >> i2) & 7) + 48);
                            if (c != '0' || z) {
                                sb.append(c);
                                z = true;
                            }
                        }
                        if (!z) {
                            sb.append('0');
                        }
                        i++;
                    } else {
                        str = "\\r";
                    }
                    sb.append(str);
                    i++;
                } else {
                    sb.append("\\u");
                    sb.append(Character.forDigit(cCharAt >> '\f', 16));
                    sb.append(Character.forDigit((cCharAt >> '\b') & 15, 16));
                    sb.append(Character.forDigit((cCharAt >> 4) & 15, 16));
                    cCharAt = Character.forDigit(cCharAt & 15, 16);
                }
            } else if (cCharAt == '\'' || cCharAt == '\"' || cCharAt == '\\') {
                sb.append('\\');
            }
            sb.append(cCharAt);
            i++;
        }
        return sb.toString();
    }

    public String toQuoted() {
        return '\"' + toHuman() + '\"';
    }

    public String toQuoted(int i) {
        String str;
        String human = toHuman();
        if (human.length() <= i - 2) {
            str = "";
        } else {
            human = human.substring(0, i - 5);
            str = "...";
        }
        return '\"' + human + str + '\"';
    }

    public String toString() {
        StringBuilder sbO = a.o("string{\"");
        sbO.append(toHuman());
        sbO.append("\"}");
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "utf8";
    }
}

package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.Hex;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstByte extends CstLiteral32 {
    public static final CstByte VALUE_0 = make((byte) 0);

    private CstByte(byte b) {
        super(b);
    }

    public static CstByte make(byte b) {
        return new CstByte(b);
    }

    public static CstByte make(int i) {
        byte b = (byte) i;
        if (b == i) {
            return make(b);
        }
        throw new IllegalArgumentException(a.h("bogus byte value: ", i));
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.BYTE;
    }

    public byte getValue() {
        return (byte) getIntBits();
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return Integer.toString(getIntBits());
    }

    public String toString() {
        int intBits = getIntBits();
        StringBuilder sbO = a.o("byte{0x");
        sbO.append(Hex.u1(intBits));
        sbO.append(" / ");
        sbO.append(intBits);
        sbO.append('}');
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "byte";
    }
}

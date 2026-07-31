package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.Hex;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstShort extends CstLiteral32 {
    public static final CstShort VALUE_0 = make((short) 0);

    private CstShort(short s) {
        super(s);
    }

    public static CstShort make(int i) {
        short s = (short) i;
        if (s == i) {
            return make(s);
        }
        throw new IllegalArgumentException(a.h("bogus short value: ", i));
    }

    public static CstShort make(short s) {
        return new CstShort(s);
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.SHORT;
    }

    public short getValue() {
        return (short) getIntBits();
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return Integer.toString(getIntBits());
    }

    public String toString() {
        int intBits = getIntBits();
        StringBuilder sbO = a.o("short{0x");
        sbO.append(Hex.u2(intBits));
        sbO.append(" / ");
        sbO.append(intBits);
        sbO.append('}');
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "short";
    }
}

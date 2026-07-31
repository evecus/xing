package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.Hex;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstFloat extends CstLiteral32 {
    public static final CstFloat VALUE_0 = make(Float.floatToIntBits(0.0f));
    public static final CstFloat VALUE_1 = make(Float.floatToIntBits(1.0f));
    public static final CstFloat VALUE_2 = make(Float.floatToIntBits(2.0f));

    private CstFloat(int i) {
        super(i);
    }

    public static CstFloat make(int i) {
        return new CstFloat(i);
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.FLOAT;
    }

    public float getValue() {
        return Float.intBitsToFloat(getIntBits());
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return Float.toString(Float.intBitsToFloat(getIntBits()));
    }

    public String toString() {
        int intBits = getIntBits();
        StringBuilder sbO = a.o("float{0x");
        sbO.append(Hex.u4(intBits));
        sbO.append(" / ");
        sbO.append(Float.intBitsToFloat(intBits));
        sbO.append('}');
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "float";
    }
}

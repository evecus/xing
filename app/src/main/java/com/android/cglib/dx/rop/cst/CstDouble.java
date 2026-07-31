package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.Hex;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstDouble extends CstLiteral64 {
    public static final CstDouble VALUE_0 = new CstDouble(Double.doubleToLongBits(0.0d));
    public static final CstDouble VALUE_1 = new CstDouble(Double.doubleToLongBits(1.0d));

    private CstDouble(long j) {
        super(j);
    }

    public static CstDouble make(long j) {
        return new CstDouble(j);
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.DOUBLE;
    }

    public double getValue() {
        return Double.longBitsToDouble(getLongBits());
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return Double.toString(Double.longBitsToDouble(getLongBits()));
    }

    public String toString() {
        long longBits = getLongBits();
        StringBuilder sbO = a.o("double{0x");
        sbO.append(Hex.u8(longBits));
        sbO.append(" / ");
        sbO.append(Double.longBitsToDouble(longBits));
        sbO.append('}');
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "double";
    }
}

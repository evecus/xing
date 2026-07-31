package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.Hex;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstLong extends CstLiteral64 {
    public static final CstLong VALUE_0 = make(0);
    public static final CstLong VALUE_1 = make(1);

    private CstLong(long j) {
        super(j);
    }

    public static CstLong make(long j) {
        return new CstLong(j);
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.LONG;
    }

    public long getValue() {
        return getLongBits();
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return Long.toString(getLongBits());
    }

    public String toString() {
        long longBits = getLongBits();
        StringBuilder sbO = a.o("long{0x");
        sbO.append(Hex.u8(longBits));
        sbO.append(" / ");
        sbO.append(longBits);
        sbO.append('}');
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "long";
    }
}

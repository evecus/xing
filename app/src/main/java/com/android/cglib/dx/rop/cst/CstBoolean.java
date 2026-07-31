package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstBoolean extends CstLiteral32 {
    public static final CstBoolean VALUE_FALSE = new CstBoolean(false);
    public static final CstBoolean VALUE_TRUE = new CstBoolean(true);

    private CstBoolean(boolean z) {
        super(z ? 1 : 0);
    }

    public static CstBoolean make(int i) {
        if (i == 0) {
            return VALUE_FALSE;
        }
        if (i == 1) {
            return VALUE_TRUE;
        }
        throw new IllegalArgumentException(a.h("bogus value: ", i));
    }

    public static CstBoolean make(boolean z) {
        return z ? VALUE_TRUE : VALUE_FALSE;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.BOOLEAN;
    }

    public boolean getValue() {
        return getIntBits() != 0;
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return getValue() ? "true" : "false";
    }

    public String toString() {
        return getValue() ? "boolean{true}" : "boolean{false}";
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "boolean";
    }
}

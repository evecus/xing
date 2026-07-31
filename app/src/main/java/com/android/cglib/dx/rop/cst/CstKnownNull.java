package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;

/* JADX INFO: loaded from: classes.dex */
public final class CstKnownNull extends CstLiteralBits {
    public static final CstKnownNull THE_ONE = new CstKnownNull();

    private CstKnownNull() {
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public int compareTo0(Constant constant) {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof CstKnownNull;
    }

    @Override // com.android.cglib.dx.rop.cst.CstLiteralBits
    public boolean fitsInInt() {
        return true;
    }

    @Override // com.android.cglib.dx.rop.cst.CstLiteralBits
    public int getIntBits() {
        return 0;
    }

    @Override // com.android.cglib.dx.rop.cst.CstLiteralBits
    public long getLongBits() {
        return 0L;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.KNOWN_NULL;
    }

    public int hashCode() {
        return 1147565434;
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return "null";
    }

    public String toString() {
        return "known-null";
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "known-null";
    }
}

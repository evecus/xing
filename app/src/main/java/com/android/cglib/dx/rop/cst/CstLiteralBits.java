package com.android.cglib.dx.rop.cst;

/* JADX INFO: loaded from: classes.dex */
public abstract class CstLiteralBits extends TypedConstant {
    public boolean fitsIn16Bits() {
        if (fitsInInt()) {
            int intBits = getIntBits();
            if (((short) intBits) == intBits) {
                return true;
            }
        }
        return false;
    }

    public boolean fitsIn8Bits() {
        if (fitsInInt()) {
            int intBits = getIntBits();
            if (((byte) intBits) == intBits) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean fitsInInt();

    public abstract int getIntBits();

    public abstract long getLongBits();
}

package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.Hex;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstChar extends CstLiteral32 {
    public static final CstChar VALUE_0 = make((char) 0);

    private CstChar(char c) {
        super(c);
    }

    public static CstChar make(char c) {
        return new CstChar(c);
    }

    public static CstChar make(int i) {
        char c = (char) i;
        if (c == i) {
            return make(c);
        }
        throw new IllegalArgumentException(a.h("bogus char value: ", i));
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.CHAR;
    }

    public char getValue() {
        return (char) getIntBits();
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return Integer.toString(getIntBits());
    }

    public String toString() {
        int intBits = getIntBits();
        StringBuilder sbO = a.o("char{0x");
        sbO.append(Hex.u2(intBits));
        sbO.append(" / ");
        sbO.append(intBits);
        sbO.append('}');
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "char";
    }
}

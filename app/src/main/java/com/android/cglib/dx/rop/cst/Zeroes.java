package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Zeroes {
    private Zeroes() {
    }

    public static Constant zeroFor(Type type) {
        switch (type.getBasicType()) {
            case 1:
                return CstBoolean.VALUE_FALSE;
            case 2:
                return CstByte.VALUE_0;
            case 3:
                return CstChar.VALUE_0;
            case 4:
                return CstDouble.VALUE_0;
            case 5:
                return CstFloat.VALUE_0;
            case 6:
                return CstInteger.VALUE_0;
            case 7:
                return CstLong.VALUE_0;
            case 8:
                return CstShort.VALUE_0;
            case 9:
                return CstKnownNull.THE_ONE;
            default:
                StringBuilder sbO = a.o("no zero for type: ");
                sbO.append(type.toHuman());
                throw new UnsupportedOperationException(sbO.toString());
        }
    }
}

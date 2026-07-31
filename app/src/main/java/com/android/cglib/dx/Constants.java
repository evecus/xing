package com.android.cglib.dx;

import com.android.cglib.dx.rop.cst.CstBoolean;
import com.android.cglib.dx.rop.cst.CstByte;
import com.android.cglib.dx.rop.cst.CstChar;
import com.android.cglib.dx.rop.cst.CstDouble;
import com.android.cglib.dx.rop.cst.CstFloat;
import com.android.cglib.dx.rop.cst.CstInteger;
import com.android.cglib.dx.rop.cst.CstKnownNull;
import com.android.cglib.dx.rop.cst.CstLong;
import com.android.cglib.dx.rop.cst.CstShort;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.rop.cst.TypedConstant;

/* JADX INFO: loaded from: classes.dex */
public final class Constants {
    private Constants() {
    }

    public static TypedConstant getConstant(Object obj) {
        TypedConstant cstType;
        if (obj == null) {
            return CstKnownNull.THE_ONE;
        }
        if (obj instanceof Boolean) {
            return CstBoolean.make(((Boolean) obj).booleanValue());
        }
        if (obj instanceof Byte) {
            return CstByte.make(((Byte) obj).byteValue());
        }
        if (obj instanceof Character) {
            return CstChar.make(((Character) obj).charValue());
        }
        if (obj instanceof Double) {
            return CstDouble.make(Double.doubleToLongBits(((Double) obj).doubleValue()));
        }
        if (obj instanceof Float) {
            return CstFloat.make(Float.floatToIntBits(((Float) obj).floatValue()));
        }
        if (obj instanceof Integer) {
            return CstInteger.make(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            return CstLong.make(((Long) obj).longValue());
        }
        if (obj instanceof Short) {
            return CstShort.make(((Short) obj).shortValue());
        }
        if (obj instanceof String) {
            cstType = new CstString((String) obj);
        } else if (obj instanceof Class) {
            cstType = new CstType(TypeId.get((Class) obj).ropType);
        } else {
            if (!(obj instanceof TypeId)) {
                throw new UnsupportedOperationException("Not a constant: " + obj);
            }
            cstType = new CstType(((TypeId) obj).ropType);
        }
        return cstType;
    }
}

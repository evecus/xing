package com.android.cglib.dx.cf.code;

import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeBearer;

/* JADX INFO: loaded from: classes.dex */
public final class Merger {
    public static TypeBearer mergeType(TypeBearer typeBearer, TypeBearer typeBearer2) {
        TypeBearer typeBearerMergeType;
        if (typeBearer == null || typeBearer.equals(typeBearer2)) {
            return typeBearer;
        }
        if (typeBearer2 != null) {
            Type type = typeBearer.getType();
            Type type2 = typeBearer2.getType();
            if (type == type2) {
                return type;
            }
            if (type.isReference() && type2.isReference()) {
                Type type3 = Type.KNOWN_NULL;
                return type != type3 ? type2 != type3 ? (type.isArray() && type2.isArray() && (typeBearerMergeType = mergeType(type.getComponentType(), type2.getComponentType())) != null) ? ((Type) typeBearerMergeType).getArrayType() : Type.OBJECT : type : type2;
            }
            if (type.isIntlike() && type2.isIntlike()) {
                return Type.INT;
            }
        }
        return null;
    }
}

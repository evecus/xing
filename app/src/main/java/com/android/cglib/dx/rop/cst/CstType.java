package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import java.util.HashMap;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstType extends TypedConstant {
    private CstString descriptor;
    private final Type type;
    private static final HashMap<Type, CstType> interns = new HashMap<>(100);
    public static final CstType OBJECT = intern(Type.OBJECT);
    public static final CstType BOOLEAN = intern(Type.BOOLEAN_CLASS);
    public static final CstType BYTE = intern(Type.BYTE_CLASS);
    public static final CstType CHARACTER = intern(Type.CHARACTER_CLASS);
    public static final CstType DOUBLE = intern(Type.DOUBLE_CLASS);
    public static final CstType FLOAT = intern(Type.FLOAT_CLASS);
    public static final CstType LONG = intern(Type.LONG_CLASS);
    public static final CstType INTEGER = intern(Type.INTEGER_CLASS);
    public static final CstType SHORT = intern(Type.SHORT_CLASS);
    public static final CstType VOID = intern(Type.VOID_CLASS);
    public static final CstType BOOLEAN_ARRAY = intern(Type.BOOLEAN_ARRAY);
    public static final CstType BYTE_ARRAY = intern(Type.BYTE_ARRAY);
    public static final CstType CHAR_ARRAY = intern(Type.CHAR_ARRAY);
    public static final CstType DOUBLE_ARRAY = intern(Type.DOUBLE_ARRAY);
    public static final CstType FLOAT_ARRAY = intern(Type.FLOAT_ARRAY);
    public static final CstType LONG_ARRAY = intern(Type.LONG_ARRAY);
    public static final CstType INT_ARRAY = intern(Type.INT_ARRAY);
    public static final CstType SHORT_ARRAY = intern(Type.SHORT_ARRAY);

    public CstType(Type type) {
        Objects.requireNonNull(type, "type == null");
        if (type == Type.KNOWN_NULL) {
            throw new UnsupportedOperationException("KNOWN_NULL is not representable");
        }
        this.type = type;
        this.descriptor = null;
    }

    public static CstType forBoxedPrimitiveType(Type type) {
        switch (type.getBasicType()) {
            case 0:
                return VOID;
            case 1:
                return BOOLEAN;
            case 2:
                return BYTE;
            case 3:
                return CHARACTER;
            case 4:
                return DOUBLE;
            case 5:
                return FLOAT;
            case 6:
                return INTEGER;
            case 7:
                return LONG;
            case 8:
                return SHORT;
            default:
                throw new IllegalArgumentException("not primitive: " + type);
        }
    }

    public static CstType intern(Type type) {
        CstType cstType;
        HashMap<Type, CstType> map = interns;
        synchronized (map) {
            cstType = map.get(type);
            if (cstType == null) {
                cstType = new CstType(type);
                map.put(type, cstType);
            }
        }
        return cstType;
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public int compareTo0(Constant constant) {
        return this.type.getDescriptor().compareTo(((CstType) constant).type.getDescriptor());
    }

    public boolean equals(Object obj) {
        return (obj instanceof CstType) && this.type == ((CstType) obj).type;
    }

    public Type getClassType() {
        return this.type;
    }

    public CstString getDescriptor() {
        if (this.descriptor == null) {
            this.descriptor = new CstString(this.type.getDescriptor());
        }
        return this.descriptor;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.CLASS;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return this.type.toHuman();
    }

    public String toString() {
        StringBuilder sbO = a.o("type{");
        sbO.append(toHuman());
        sbO.append('}');
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "type";
    }
}

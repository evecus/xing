package com.android.cglib.dx.rop.type;

import androidx.exifinterface.media.ExifInterface;
import com.android.cglib.dx.util.Hex;
import java.util.HashMap;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Type implements TypeBearer, Comparable<Type> {
    public static final Type ANNOTATION;
    public static final Type BOOLEAN;
    public static final Type BOOLEAN_ARRAY;
    public static final Type BOOLEAN_CLASS;
    public static final int BT_ADDR = 10;
    public static final int BT_BOOLEAN = 1;
    public static final int BT_BYTE = 2;
    public static final int BT_CHAR = 3;
    public static final int BT_COUNT = 11;
    public static final int BT_DOUBLE = 4;
    public static final int BT_FLOAT = 5;
    public static final int BT_INT = 6;
    public static final int BT_LONG = 7;
    public static final int BT_OBJECT = 9;
    public static final int BT_SHORT = 8;
    public static final int BT_VOID = 0;
    public static final Type BYTE;
    public static final Type BYTE_ARRAY;
    public static final Type BYTE_CLASS;
    public static final Type CHAR;
    public static final Type CHARACTER_CLASS;
    public static final Type CHAR_ARRAY;
    public static final Type CLASS;
    public static final Type CLONEABLE;
    public static final Type DOUBLE;
    public static final Type DOUBLE_ARRAY;
    public static final Type DOUBLE_CLASS;
    public static final Type FLOAT;
    public static final Type FLOAT_ARRAY;
    public static final Type FLOAT_CLASS;
    public static final Type INT;
    public static final Type INTEGER_CLASS;
    public static final Type INT_ARRAY;
    public static final Type KNOWN_NULL;
    public static final Type LONG;
    public static final Type LONG_ARRAY;
    public static final Type LONG_CLASS;
    public static final Type OBJECT;
    public static final Type OBJECT_ARRAY;
    public static final Type RETURN_ADDRESS;
    public static final Type SERIALIZABLE;
    public static final Type SHORT;
    public static final Type SHORT_ARRAY;
    public static final Type SHORT_CLASS;
    public static final Type STRING;
    public static final Type THROWABLE;
    public static final Type VOID;
    public static final Type VOID_CLASS;
    private static final HashMap<String, Type> internTable = new HashMap<>(500);
    private Type arrayType;
    private final int basicType;
    private String className;
    private Type componentType;
    private final String descriptor;
    private Type initializedType;
    private final int newAt;

    static {
        Type type = new Type("Z", 1);
        BOOLEAN = type;
        Type type2 = new Type("B", 2);
        BYTE = type2;
        Type type3 = new Type("C", 3);
        CHAR = type3;
        Type type4 = new Type("D", 4);
        DOUBLE = type4;
        Type type5 = new Type("F", 5);
        FLOAT = type5;
        Type type6 = new Type("I", 6);
        INT = type6;
        Type type7 = new Type("J", 7);
        LONG = type7;
        Type type8 = new Type(ExifInterface.LATITUDE_SOUTH, 8);
        SHORT = type8;
        VOID = new Type(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, 0);
        KNOWN_NULL = new Type("<null>", 9);
        RETURN_ADDRESS = new Type("<addr>", 10);
        putIntern(type);
        putIntern(type2);
        putIntern(type3);
        putIntern(type4);
        putIntern(type5);
        putIntern(type6);
        putIntern(type7);
        putIntern(type8);
        ANNOTATION = intern("Ljava/lang/annotation/Annotation;");
        CLASS = intern("Ljava/lang/Class;");
        CLONEABLE = intern("Ljava/lang/Cloneable;");
        Type typeIntern = intern("Ljava/lang/Object;");
        OBJECT = typeIntern;
        SERIALIZABLE = intern("Ljava/io/Serializable;");
        STRING = intern("Ljava/lang/String;");
        THROWABLE = intern("Ljava/lang/Throwable;");
        BOOLEAN_CLASS = intern("Ljava/lang/Boolean;");
        BYTE_CLASS = intern("Ljava/lang/Byte;");
        CHARACTER_CLASS = intern("Ljava/lang/Character;");
        DOUBLE_CLASS = intern("Ljava/lang/Double;");
        FLOAT_CLASS = intern("Ljava/lang/Float;");
        INTEGER_CLASS = intern("Ljava/lang/Integer;");
        LONG_CLASS = intern("Ljava/lang/Long;");
        SHORT_CLASS = intern("Ljava/lang/Short;");
        VOID_CLASS = intern("Ljava/lang/Void;");
        BOOLEAN_ARRAY = type.getArrayType();
        BYTE_ARRAY = type2.getArrayType();
        CHAR_ARRAY = type3.getArrayType();
        DOUBLE_ARRAY = type4.getArrayType();
        FLOAT_ARRAY = type5.getArrayType();
        INT_ARRAY = type6.getArrayType();
        LONG_ARRAY = type7.getArrayType();
        OBJECT_ARRAY = typeIntern.getArrayType();
        SHORT_ARRAY = type8.getArrayType();
    }

    private Type(String str, int i) {
        this(str, i, -1);
    }

    private Type(String str, int i, int i2) {
        Objects.requireNonNull(str, "descriptor == null");
        if (i < 0 || i >= 11) {
            throw new IllegalArgumentException("bad basicType");
        }
        if (i2 < -1) {
            throw new IllegalArgumentException("newAt < -1");
        }
        this.descriptor = str;
        this.basicType = i;
        this.newAt = i2;
        this.arrayType = null;
        this.componentType = null;
        this.initializedType = null;
    }

    public static Type intern(String str) {
        Type type;
        HashMap<String, Type> map = internTable;
        synchronized (map) {
            type = map.get(str);
        }
        if (type != null) {
            return type;
        }
        try {
            char cCharAt = str.charAt(0);
            if (cCharAt == '[') {
                return intern(str.substring(1)).getArrayType();
            }
            int length = str.length();
            if (cCharAt == 'L') {
                int i = length - 1;
                if (str.charAt(i) == ';') {
                    for (int i2 = 1; i2 < i; i2++) {
                        char cCharAt2 = str.charAt(i2);
                        if (cCharAt2 != '(' && cCharAt2 != ')' && cCharAt2 != '.') {
                            if (cCharAt2 == '/') {
                                if (i2 == 1 || i2 == i || str.charAt(i2 - 1) == '/') {
                                    throw new IllegalArgumentException(a.j("bad descriptor: ", str));
                                }
                            } else if (cCharAt2 == ';' || cCharAt2 == '[') {
                            }
                        }
                        throw new IllegalArgumentException(a.j("bad descriptor: ", str));
                    }
                    return putIntern(new Type(str, 9));
                }
            }
            throw new IllegalArgumentException(a.j("bad descriptor: ", str));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("descriptor is empty");
        } catch (NullPointerException e2) {
            throw new NullPointerException("descriptor == null");
        }
    }

    public static Type internClassName(String str) {
        Objects.requireNonNull(str, "name == null");
        if (!str.startsWith("[")) {
            str = 'L' + str + ';';
        }
        return intern(str);
    }

    public static Type internReturnType(String str) {
        try {
            return str.equals(ExifInterface.GPS_MEASUREMENT_INTERRUPTED) ? VOID : intern(str);
        } catch (NullPointerException e) {
            throw new NullPointerException("descriptor == null");
        }
    }

    private static Type putIntern(Type type) {
        HashMap<String, Type> map = internTable;
        synchronized (map) {
            String descriptor = type.getDescriptor();
            Type type2 = map.get(descriptor);
            if (type2 != null) {
                return type2;
            }
            map.put(descriptor, type);
            return type;
        }
    }

    public Type asUninitialized(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("newAt < 0");
        }
        if (!isReference()) {
            StringBuilder sbO = a.o("not a reference type: ");
            sbO.append(this.descriptor);
            throw new IllegalArgumentException(sbO.toString());
        }
        if (isUninitialized()) {
            StringBuilder sbO2 = a.o("already uninitialized: ");
            sbO2.append(this.descriptor);
            throw new IllegalArgumentException(sbO2.toString());
        }
        Type type = new Type('N' + Hex.u2(i) + this.descriptor, 9, i);
        type.initializedType = this;
        return putIntern(type);
    }

    @Override // java.lang.Comparable
    public int compareTo(Type type) {
        return this.descriptor.compareTo(type.descriptor);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Type) {
            return this.descriptor.equals(((Type) obj).descriptor);
        }
        return false;
    }

    public Type getArrayType() {
        if (this.arrayType == null) {
            this.arrayType = putIntern(new Type('[' + this.descriptor, 9));
        }
        return this.arrayType;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public int getBasicFrameType() {
        int i = this.basicType;
        if (i == 1 || i == 2 || i == 3 || i == 6 || i == 8) {
            return 6;
        }
        return i;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public int getBasicType() {
        return this.basicType;
    }

    public int getCategory() {
        int i = this.basicType;
        return (i == 4 || i == 7) ? 2 : 1;
    }

    public String getClassName() {
        String strSubstring;
        if (this.className == null) {
            if (!isReference()) {
                StringBuilder sbO = a.o("not an object type: ");
                sbO.append(this.descriptor);
                throw new IllegalArgumentException(sbO.toString());
            }
            if (this.descriptor.charAt(0) == '[') {
                strSubstring = this.descriptor;
            } else {
                String str = this.descriptor;
                strSubstring = str.substring(1, str.length() - 1);
            }
            this.className = strSubstring;
        }
        return this.className;
    }

    public Type getComponentType() {
        if (this.componentType == null) {
            if (this.descriptor.charAt(0) != '[') {
                StringBuilder sbO = a.o("not an array type: ");
                sbO.append(this.descriptor);
                throw new IllegalArgumentException(sbO.toString());
            }
            this.componentType = intern(this.descriptor.substring(1));
        }
        return this.componentType;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getFrameType() {
        int i = this.basicType;
        return (i == 1 || i == 2 || i == 3 || i == 6 || i == 8) ? INT : this;
    }

    public Type getInitializedType() {
        Type type = this.initializedType;
        if (type != null) {
            return type;
        }
        StringBuilder sbO = a.o("initialized type: ");
        sbO.append(this.descriptor);
        throw new IllegalArgumentException(sbO.toString());
    }

    public int getNewAt() {
        return this.newAt;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return this;
    }

    public int hashCode() {
        return this.descriptor.hashCode();
    }

    public boolean isArray() {
        return this.descriptor.charAt(0) == '[';
    }

    public boolean isArrayOrKnownNull() {
        return isArray() || equals(KNOWN_NULL);
    }

    public boolean isCategory1() {
        int i = this.basicType;
        return (i == 4 || i == 7) ? false : true;
    }

    public boolean isCategory2() {
        int i = this.basicType;
        return i == 4 || i == 7;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public boolean isConstant() {
        return false;
    }

    public boolean isIntlike() {
        int i = this.basicType;
        return i == 1 || i == 2 || i == 3 || i == 6 || i == 8;
    }

    public boolean isPrimitive() {
        switch (this.basicType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    public boolean isReference() {
        return this.basicType == 9;
    }

    public boolean isUninitialized() {
        return this.newAt >= 0;
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        switch (this.basicType) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "byte";
            case 3:
                return "char";
            case 4:
                return "double";
            case 5:
                return "float";
            case 6:
                return "int";
            case 7:
                return "long";
            case 8:
                return "short";
            case 9:
                if (!isArray()) {
                    return getClassName().replace("/", ".");
                }
                return getComponentType().toHuman() + "[]";
            default:
                return this.descriptor;
        }
    }

    public String toString() {
        return this.descriptor;
    }
}

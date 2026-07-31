package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.type.StdTypeList;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeList;
import com.android.cglib.dx.util.Hex;
import com.baidu.android.common.util.HanziToPinyin;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Rop {
    public static final int BRANCH_GOTO = 3;
    public static final int BRANCH_IF = 4;
    public static final int BRANCH_MAX = 6;
    public static final int BRANCH_MIN = 1;
    public static final int BRANCH_NONE = 1;
    public static final int BRANCH_RETURN = 2;
    public static final int BRANCH_SWITCH = 5;
    public static final int BRANCH_THROW = 6;
    private final int branchingness;
    private final TypeList exceptions;
    private final boolean isCallLike;
    private final String nickname;
    private final int opcode;
    private final Type result;
    private final TypeList sources;

    public Rop(int i, Type type, TypeList typeList, int i2, String str) {
        this(i, type, typeList, StdTypeList.EMPTY, i2, false, str);
    }

    public Rop(int i, Type type, TypeList typeList, TypeList typeList2, int i2, String str) {
        this(i, type, typeList, typeList2, i2, false, str);
    }

    public Rop(int i, Type type, TypeList typeList, TypeList typeList2, int i2, boolean z, String str) {
        Objects.requireNonNull(type, "result == null");
        Objects.requireNonNull(typeList, "sources == null");
        Objects.requireNonNull(typeList2, "exceptions == null");
        if (i2 < 1 || i2 > 6) {
            throw new IllegalArgumentException("bogus branchingness");
        }
        if (typeList2.size() != 0 && i2 != 6) {
            throw new IllegalArgumentException("exceptions / branchingness mismatch");
        }
        this.opcode = i;
        this.result = type;
        this.sources = typeList;
        this.exceptions = typeList2;
        this.branchingness = i2;
        this.isCallLike = z;
        this.nickname = str;
    }

    public Rop(int i, Type type, TypeList typeList, TypeList typeList2, String str) {
        this(i, type, typeList, typeList2, 6, false, str);
    }

    public Rop(int i, Type type, TypeList typeList, String str) {
        this(i, type, typeList, StdTypeList.EMPTY, 1, false, str);
    }

    public Rop(int i, TypeList typeList, TypeList typeList2) {
        this(i, Type.VOID, typeList, typeList2, 6, true, null);
    }

    public final boolean canThrow() {
        return this.exceptions.size() != 0;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Rop) {
                Rop rop = (Rop) obj;
                if (this.opcode != rop.opcode || this.branchingness != rop.branchingness || this.result != rop.result || !this.sources.equals(rop.sources) || !this.exceptions.equals(rop.exceptions)) {
                }
            }
            return false;
        }
        return true;
    }

    public int getBranchingness() {
        return this.branchingness;
    }

    public TypeList getExceptions() {
        return this.exceptions;
    }

    public String getNickname() {
        String str = this.nickname;
        return str != null ? str : toString();
    }

    public int getOpcode() {
        return this.opcode;
    }

    public Type getResult() {
        return this.result;
    }

    public TypeList getSources() {
        return this.sources;
    }

    public int hashCode() {
        return (((((((this.opcode * 31) + this.branchingness) * 31) + this.result.hashCode()) * 31) + this.sources.hashCode()) * 31) + this.exceptions.hashCode();
    }

    public boolean isCallLike() {
        return this.isCallLike;
    }

    public boolean isCommutative() {
        int i = this.opcode;
        if (i != 14 && i != 16) {
            switch (i) {
                case 20:
                case 21:
                case 22:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public String toString() {
        String string;
        StringBuffer stringBuffer = new StringBuffer(40);
        stringBuffer.append("Rop{");
        stringBuffer.append(RegOps.opName(this.opcode));
        if (this.result != Type.VOID) {
            stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
            stringBuffer.append(this.result);
        } else {
            stringBuffer.append(" .");
        }
        stringBuffer.append(" <-");
        int size = this.sources.size();
        if (size == 0) {
            stringBuffer.append(" .");
        } else {
            for (int i = 0; i < size; i++) {
                stringBuffer.append(' ');
                stringBuffer.append(this.sources.getType(i));
            }
        }
        if (this.isCallLike) {
            stringBuffer.append(" call");
        }
        int size2 = this.exceptions.size();
        if (size2 != 0) {
            stringBuffer.append(" throws");
            for (int i2 = 0; i2 < size2; i2++) {
                stringBuffer.append(' ');
                if (this.exceptions.getType(i2) == Type.THROWABLE) {
                    stringBuffer.append("<any>");
                } else {
                    stringBuffer.append(this.exceptions.getType(i2));
                }
            }
        } else {
            int i3 = this.branchingness;
            if (i3 == 1) {
                string = " flows";
            } else if (i3 == 2) {
                string = " returns";
            } else if (i3 == 3) {
                string = " gotos";
            } else if (i3 == 4) {
                string = " ifs";
            } else if (i3 != 5) {
                StringBuilder sbO = a.o(HanziToPinyin.Token.SEPARATOR);
                sbO.append(Hex.u1(this.branchingness));
                string = sbO.toString();
            } else {
                string = " switches";
            }
            stringBuffer.append(string);
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}

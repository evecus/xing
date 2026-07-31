package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeBearer;
import com.android.cglib.dx.util.ToHuman;
import java.util.HashMap;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class RegisterSpec implements TypeBearer, ToHuman, Comparable<RegisterSpec> {
    public static final String PREFIX = "v";
    private final LocalItem local;
    private final int reg;
    private final TypeBearer type;
    private static final HashMap<Object, RegisterSpec> theInterns = new HashMap<>(1000);
    private static final ForComparison theInterningItem = new ForComparison();

    public static class ForComparison {
        private LocalItem local;
        private int reg;
        private TypeBearer type;

        private ForComparison() {
        }

        public boolean equals(Object obj) {
            if (obj instanceof RegisterSpec) {
                return ((RegisterSpec) obj).equals(this.reg, this.type, this.local);
            }
            return false;
        }

        public int hashCode() {
            return RegisterSpec.hashCodeOf(this.reg, this.type, this.local);
        }

        public void set(int i, TypeBearer typeBearer, LocalItem localItem) {
            this.reg = i;
            this.type = typeBearer;
            this.local = localItem;
        }

        public RegisterSpec toRegisterSpec() {
            return new RegisterSpec(this.reg, this.type, this.local);
        }
    }

    private RegisterSpec(int i, TypeBearer typeBearer, LocalItem localItem) {
        if (i < 0) {
            throw new IllegalArgumentException("reg < 0");
        }
        Objects.requireNonNull(typeBearer, "type == null");
        this.reg = i;
        this.type = typeBearer;
        this.local = localItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean equals(int i, TypeBearer typeBearer, LocalItem localItem) {
        LocalItem localItem2;
        return this.reg == i && this.type.equals(typeBearer) && ((localItem2 = this.local) == localItem || (localItem2 != null && localItem2.equals(localItem)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int hashCodeOf(int i, TypeBearer typeBearer, LocalItem localItem) {
        return ((((localItem != null ? localItem.hashCode() : 0) * 31) + typeBearer.hashCode()) * 31) + i;
    }

    private static RegisterSpec intern(int i, TypeBearer typeBearer, LocalItem localItem) {
        RegisterSpec registerSpec;
        HashMap<Object, RegisterSpec> map = theInterns;
        synchronized (map) {
            ForComparison forComparison = theInterningItem;
            forComparison.set(i, typeBearer, localItem);
            registerSpec = map.get(forComparison);
            if (registerSpec == null) {
                registerSpec = forComparison.toRegisterSpec();
                map.put(registerSpec, registerSpec);
            }
        }
        return registerSpec;
    }

    public static RegisterSpec make(int i, TypeBearer typeBearer) {
        return intern(i, typeBearer, null);
    }

    public static RegisterSpec make(int i, TypeBearer typeBearer, LocalItem localItem) {
        Objects.requireNonNull(localItem, "local  == null");
        return intern(i, typeBearer, localItem);
    }

    public static RegisterSpec makeLocalOptional(int i, TypeBearer typeBearer, LocalItem localItem) {
        return intern(i, typeBearer, localItem);
    }

    public static String regString(int i) {
        return a.h("v", i);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String toString0(boolean r4) {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r1 = 40
            r0.<init>(r1)
            java.lang.String r1 = r3.regString()
            r0.append(r1)
            java.lang.String r1 = ":"
            r0.append(r1)
            com.android.cglib.dx.rop.code.LocalItem r1 = r3.local
            if (r1 == 0) goto L1e
            java.lang.String r1 = r1.toString()
            r0.append(r1)
        L1e:
            com.android.cglib.dx.rop.type.TypeBearer r1 = r3.type
            com.android.cglib.dx.rop.type.Type r1 = r1.getType()
            r0.append(r1)
            com.android.cglib.dx.rop.type.TypeBearer r2 = r3.type
            if (r1 == r2) goto L54
            java.lang.String r1 = "="
            r0.append(r1)
            if (r4 == 0) goto L3f
            com.android.cglib.dx.rop.type.TypeBearer r1 = r3.type
            boolean r2 = r1 instanceof com.android.cglib.dx.rop.cst.CstString
            if (r2 == 0) goto L3f
            com.android.cglib.dx.rop.cst.CstString r1 = (com.android.cglib.dx.rop.cst.CstString) r1
            java.lang.String r4 = r1.toQuoted()
            goto L4b
        L3f:
            if (r4 == 0) goto L4f
            com.android.cglib.dx.rop.type.TypeBearer r4 = r3.type
            boolean r1 = r4 instanceof com.android.cglib.dx.rop.cst.Constant
            if (r1 == 0) goto L4f
            java.lang.String r4 = r4.toHuman()
        L4b:
            r0.append(r4)
            goto L54
        L4f:
            com.android.cglib.dx.rop.type.TypeBearer r4 = r3.type
            r0.append(r4)
        L54:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.rop.code.RegisterSpec.toString0(boolean):java.lang.String");
    }

    @Override // java.lang.Comparable
    public int compareTo(RegisterSpec registerSpec) {
        int i = this.reg;
        int i2 = registerSpec.reg;
        if (i >= i2) {
            if (i <= i2) {
                int iCompareTo = this.type.getType().compareTo(registerSpec.type.getType());
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
                LocalItem localItem = this.local;
                LocalItem localItem2 = registerSpec.local;
                if (localItem == null) {
                    if (localItem2 == null) {
                        return 0;
                    }
                } else if (localItem2 != null) {
                    return localItem.compareTo(localItem2);
                }
            }
            return 1;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        int i;
        TypeBearer typeBearer;
        LocalItem localItem;
        if (obj instanceof RegisterSpec) {
            RegisterSpec registerSpec = (RegisterSpec) obj;
            i = registerSpec.reg;
            typeBearer = registerSpec.type;
            localItem = registerSpec.local;
        } else {
            if (!(obj instanceof ForComparison)) {
                return false;
            }
            ForComparison forComparison = (ForComparison) obj;
            i = forComparison.reg;
            typeBearer = forComparison.type;
            localItem = forComparison.local;
        }
        return equals(i, typeBearer, localItem);
    }

    public boolean equalsUsingSimpleType(RegisterSpec registerSpec) {
        return matchesVariable(registerSpec) && this.reg == registerSpec.reg;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public final int getBasicFrameType() {
        return this.type.getBasicFrameType();
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public final int getBasicType() {
        return this.type.getBasicType();
    }

    public int getCategory() {
        return this.type.getType().getCategory();
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public TypeBearer getFrameType() {
        return this.type.getFrameType();
    }

    public LocalItem getLocalItem() {
        return this.local;
    }

    public int getNextReg() {
        return this.reg + getCategory();
    }

    public int getReg() {
        return this.reg;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return this.type.getType();
    }

    public TypeBearer getTypeBearer() {
        return this.type;
    }

    public int hashCode() {
        return hashCodeOf(this.reg, this.type, this.local);
    }

    public RegisterSpec intersect(RegisterSpec registerSpec, boolean z) {
        TypeBearer type;
        if (this != registerSpec) {
            if (registerSpec == null || this.reg != registerSpec.getReg()) {
                return null;
            }
            LocalItem localItem = this.local;
            LocalItem localItem2 = (localItem == null || !localItem.equals(registerSpec.getLocalItem())) ? null : this.local;
            boolean z2 = localItem2 == this.local;
            if ((z && !z2) || (type = getType()) != registerSpec.getType()) {
                return null;
            }
            if (this.type.equals(registerSpec.getTypeBearer())) {
                type = this.type;
            }
            if (type != this.type || !z2) {
                int i = this.reg;
                return localItem2 == null ? make(i, type) : make(i, type, localItem2);
            }
        }
        return this;
    }

    public boolean isCategory1() {
        return this.type.getType().isCategory1();
    }

    public boolean isCategory2() {
        return this.type.getType().isCategory2();
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public final boolean isConstant() {
        return false;
    }

    public boolean matchesVariable(RegisterSpec registerSpec) {
        LocalItem localItem;
        LocalItem localItem2;
        return registerSpec != null && this.type.getType().equals(registerSpec.type.getType()) && ((localItem = this.local) == (localItem2 = registerSpec.local) || (localItem != null && localItem.equals(localItem2)));
    }

    public String regString() {
        return regString(this.reg);
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return toString0(true);
    }

    public String toString() {
        return toString0(false);
    }

    public RegisterSpec withLocalItem(LocalItem localItem) {
        LocalItem localItem2 = this.local;
        return (localItem2 == localItem || (localItem2 != null && localItem2.equals(localItem))) ? this : makeLocalOptional(this.reg, this.type, localItem);
    }

    public RegisterSpec withOffset(int i) {
        return i == 0 ? this : withReg(this.reg + i);
    }

    public RegisterSpec withReg(int i) {
        return this.reg == i ? this : makeLocalOptional(i, this.type, this.local);
    }

    public RegisterSpec withSimpleType() {
        TypeBearer typeBearer = this.type;
        Type type = typeBearer instanceof Type ? (Type) typeBearer : typeBearer.getType();
        if (type.isUninitialized()) {
            type = type.getInitializedType();
        }
        return type == typeBearer ? this : makeLocalOptional(this.reg, type, this.local);
    }

    public RegisterSpec withType(TypeBearer typeBearer) {
        return makeLocalOptional(this.reg, typeBearer, this.local);
    }
}

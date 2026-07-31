package com.android.cglib.dx.rop.cst;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class CstMemberRef extends TypedConstant {
    private final CstType definingClass;
    private final CstNat nat;

    public CstMemberRef(CstType cstType, CstNat cstNat) {
        Objects.requireNonNull(cstType, "definingClass == null");
        Objects.requireNonNull(cstNat, "nat == null");
        this.definingClass = cstType;
        this.nat = cstNat;
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public int compareTo0(Constant constant) {
        CstMemberRef cstMemberRef = (CstMemberRef) constant;
        int iCompareTo = this.definingClass.compareTo((Constant) cstMemberRef.definingClass);
        return iCompareTo != 0 ? iCompareTo : this.nat.getName().compareTo((Constant) cstMemberRef.nat.getName());
    }

    public final boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            CstMemberRef cstMemberRef = (CstMemberRef) obj;
            if (this.definingClass.equals(cstMemberRef.definingClass) && this.nat.equals(cstMemberRef.nat)) {
                return true;
            }
        }
        return false;
    }

    public final CstType getDefiningClass() {
        return this.definingClass;
    }

    public final CstNat getNat() {
        return this.nat;
    }

    public final int hashCode() {
        return (this.definingClass.hashCode() * 31) ^ this.nat.hashCode();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public final boolean isCategory2() {
        return false;
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public final String toHuman() {
        return this.definingClass.toHuman() + '.' + this.nat.toHuman();
    }

    public final String toString() {
        return typeName() + '{' + toHuman() + '}';
    }
}

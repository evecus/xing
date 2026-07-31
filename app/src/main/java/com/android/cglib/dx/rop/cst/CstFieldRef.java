package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;

/* JADX INFO: loaded from: classes.dex */
public final class CstFieldRef extends CstMemberRef {
    public CstFieldRef(CstType cstType, CstNat cstNat) {
        super(cstType, cstNat);
    }

    public static CstFieldRef forPrimitiveType(Type type) {
        return new CstFieldRef(CstType.forBoxedPrimitiveType(type), CstNat.PRIMITIVE_TYPE_NAT);
    }

    @Override // com.android.cglib.dx.rop.cst.CstMemberRef, com.android.cglib.dx.rop.cst.Constant
    public int compareTo0(Constant constant) {
        int iCompareTo0 = super.compareTo0(constant);
        return iCompareTo0 != 0 ? iCompareTo0 : getNat().getDescriptor().compareTo((Constant) ((CstFieldRef) constant).getNat().getDescriptor());
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public Type getType() {
        return getNat().getFieldType();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "field";
    }
}

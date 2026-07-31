package com.android.cglib.dx.rop.cst;

/* JADX INFO: loaded from: classes.dex */
public final class CstInterfaceMethodRef extends CstBaseMethodRef {
    private CstMethodRef methodRef;

    public CstInterfaceMethodRef(CstType cstType, CstNat cstNat) {
        super(cstType, cstNat);
        this.methodRef = null;
    }

    public CstMethodRef toMethodRef() {
        if (this.methodRef == null) {
            this.methodRef = new CstMethodRef(getDefiningClass(), getNat());
        }
        return this.methodRef;
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "ifaceMethod";
    }
}

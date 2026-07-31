package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Prototype;
import com.android.cglib.dx.rop.type.Type;

/* JADX INFO: loaded from: classes.dex */
public abstract class CstBaseMethodRef extends CstMemberRef {
    private Prototype instancePrototype;
    private final Prototype prototype;

    public CstBaseMethodRef(CstType cstType, CstNat cstNat) {
        super(cstType, cstNat);
        this.prototype = Prototype.intern(getNat().getDescriptor().getString());
        this.instancePrototype = null;
    }

    @Override // com.android.cglib.dx.rop.cst.CstMemberRef, com.android.cglib.dx.rop.cst.Constant
    public final int compareTo0(Constant constant) {
        int iCompareTo0 = super.compareTo0(constant);
        return iCompareTo0 != 0 ? iCompareTo0 : this.prototype.compareTo(((CstBaseMethodRef) constant).prototype);
    }

    public final int getParameterWordCount(boolean z) {
        return getPrototype(z).getParameterTypes().getWordCount();
    }

    public final Prototype getPrototype() {
        return this.prototype;
    }

    public final Prototype getPrototype(boolean z) {
        if (z) {
            return this.prototype;
        }
        if (this.instancePrototype == null) {
            this.instancePrototype = this.prototype.withFirstParameter(getDefiningClass().getClassType());
        }
        return this.instancePrototype;
    }

    @Override // com.android.cglib.dx.rop.type.TypeBearer
    public final Type getType() {
        return this.prototype.getReturnType();
    }

    public final boolean isClassInit() {
        return getNat().isClassInit();
    }

    public final boolean isInstanceInit() {
        return getNat().isInstanceInit();
    }
}

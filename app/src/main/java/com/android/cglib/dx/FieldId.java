package com.android.cglib.dx;

import com.android.cglib.dx.rop.cst.CstFieldRef;
import com.android.cglib.dx.rop.cst.CstNat;
import com.android.cglib.dx.rop.cst.CstString;

/* JADX INFO: loaded from: classes.dex */
public final class FieldId<D, V> {
    public final CstFieldRef constant;
    public final TypeId<D> declaringType;
    public final String name;
    public final CstNat nat;
    public final TypeId<V> type;

    public FieldId(TypeId<D> typeId, TypeId<V> typeId2, String str) {
        if (typeId == null || typeId2 == null || str == null) {
            throw null;
        }
        this.declaringType = typeId;
        this.type = typeId2;
        this.name = str;
        CstNat cstNat = new CstNat(new CstString(str), new CstString(typeId2.name));
        this.nat = cstNat;
        this.constant = new CstFieldRef(typeId.constant, cstNat);
    }

    public boolean equals(Object obj) {
        if (obj instanceof FieldId) {
            FieldId fieldId = (FieldId) obj;
            if (fieldId.declaringType.equals(this.declaringType) && fieldId.name.equals(this.name)) {
                return true;
            }
        }
        return false;
    }

    public TypeId<D> getDeclaringType() {
        return this.declaringType;
    }

    public String getName() {
        return this.name;
    }

    public TypeId<V> getType() {
        return this.type;
    }

    public int hashCode() {
        return this.declaringType.hashCode() + (this.name.hashCode() * 37);
    }

    public String toString() {
        return this.declaringType + "." + this.name;
    }
}

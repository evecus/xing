package com.android.cglib.dx;

import com.android.cglib.dx.rop.cst.CstMethodRef;
import com.android.cglib.dx.rop.cst.CstNat;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.type.Prototype;
import java.util.List;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class MethodId<D, R> {
    public final CstMethodRef constant;
    public final TypeId<D> declaringType;
    public final String name;
    public final CstNat nat;
    public final TypeList parameters;
    public final TypeId<R> returnType;

    public MethodId(TypeId<D> typeId, TypeId<R> typeId2, String str, TypeList typeList) {
        if (typeId == null || typeId2 == null || str == null || typeList == null) {
            throw null;
        }
        this.declaringType = typeId;
        this.returnType = typeId2;
        this.name = str;
        this.parameters = typeList;
        CstNat cstNat = new CstNat(new CstString(str), new CstString(descriptor(false)));
        this.nat = cstNat;
        this.constant = new CstMethodRef(typeId.constant, cstNat);
    }

    public String descriptor(boolean z) {
        StringBuilder sbO = a.o("(");
        if (z) {
            sbO.append(this.declaringType.name);
        }
        for (TypeId<?> typeId : this.parameters.types) {
            sbO.append(typeId.name);
        }
        sbO.append(")");
        sbO.append(this.returnType.name);
        return sbO.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MethodId) {
            MethodId methodId = (MethodId) obj;
            if (methodId.declaringType.equals(this.declaringType) && methodId.name.equals(this.name) && methodId.parameters.equals(this.parameters) && methodId.returnType.equals(this.returnType)) {
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

    public List<TypeId<?>> getParameters() {
        return this.parameters.asList();
    }

    public TypeId<R> getReturnType() {
        return this.returnType;
    }

    public int hashCode() {
        return ((((((this.declaringType.hashCode() + 527) * 31) + this.name.hashCode()) * 31) + this.parameters.hashCode()) * 31) + this.returnType.hashCode();
    }

    public boolean isConstructor() {
        return this.name.equals("<init>");
    }

    public Prototype prototype(boolean z) {
        return Prototype.intern(descriptor(z));
    }

    public String toString() {
        return this.declaringType + "." + this.name + "(" + this.parameters + ")";
    }
}

package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.type.Type;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CstNat extends Constant {
    public static final CstNat PRIMITIVE_TYPE_NAT = new CstNat(new CstString("TYPE"), new CstString("Ljava/lang/Class;"));
    private final CstString descriptor;
    private final CstString name;

    public CstNat(CstString cstString, CstString cstString2) {
        Objects.requireNonNull(cstString, "name == null");
        Objects.requireNonNull(cstString2, "descriptor == null");
        this.name = cstString;
        this.descriptor = cstString2;
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public int compareTo0(Constant constant) {
        CstNat cstNat = (CstNat) constant;
        int iCompareTo = this.name.compareTo((Constant) cstNat.name);
        return iCompareTo != 0 ? iCompareTo : this.descriptor.compareTo((Constant) cstNat.descriptor);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CstNat) {
            CstNat cstNat = (CstNat) obj;
            if (this.name.equals(cstNat.name) && this.descriptor.equals(cstNat.descriptor)) {
                return true;
            }
        }
        return false;
    }

    public CstString getDescriptor() {
        return this.descriptor;
    }

    public Type getFieldType() {
        return Type.intern(this.descriptor.getString());
    }

    public CstString getName() {
        return this.name;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) ^ this.descriptor.hashCode();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    public final boolean isClassInit() {
        return this.name.getString().equals("<clinit>");
    }

    public final boolean isInstanceInit() {
        return this.name.getString().equals("<init>");
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return this.name.toHuman() + ':' + this.descriptor.toHuman();
    }

    public String toString() {
        StringBuilder sbO = a.o("nat{");
        sbO.append(toHuman());
        sbO.append('}');
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "nat";
    }
}

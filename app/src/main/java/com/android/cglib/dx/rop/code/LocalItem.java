package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstString;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LocalItem implements Comparable<LocalItem> {
    private final CstString name;
    private final CstString signature;

    private LocalItem(CstString cstString, CstString cstString2) {
        this.name = cstString;
        this.signature = cstString2;
    }

    private static int compareHandlesNulls(CstString cstString, CstString cstString2) {
        if (cstString == cstString2) {
            return 0;
        }
        if (cstString == null) {
            return -1;
        }
        if (cstString2 == null) {
            return 1;
        }
        return cstString.compareTo((Constant) cstString2);
    }

    public static LocalItem make(CstString cstString, CstString cstString2) {
        if (cstString == null && cstString2 == null) {
            return null;
        }
        return new LocalItem(cstString, cstString2);
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalItem localItem) {
        int iCompareHandlesNulls = compareHandlesNulls(this.name, localItem.name);
        return iCompareHandlesNulls != 0 ? iCompareHandlesNulls : compareHandlesNulls(this.signature, localItem.signature);
    }

    public boolean equals(Object obj) {
        return (obj instanceof LocalItem) && compareTo((LocalItem) obj) == 0;
    }

    public CstString getName() {
        return this.name;
    }

    public CstString getSignature() {
        return this.signature;
    }

    public int hashCode() {
        CstString cstString = this.name;
        int iHashCode = cstString == null ? 0 : cstString.hashCode();
        CstString cstString2 = this.signature;
        return (iHashCode * 31) + (cstString2 != null ? cstString2.hashCode() : 0);
    }

    public String toString() {
        CstString cstString = this.name;
        if (cstString != null && this.signature == null) {
            return cstString.toQuoted();
        }
        if (cstString == null && this.signature == null) {
            return "";
        }
        StringBuilder sbO = a.o("[");
        CstString cstString2 = this.name;
        sbO.append(cstString2 == null ? "" : cstString2.toQuoted());
        sbO.append("|");
        CstString cstString3 = this.signature;
        sbO.append(cstString3 != null ? cstString3.toQuoted() : "");
        return sbO.toString();
    }
}

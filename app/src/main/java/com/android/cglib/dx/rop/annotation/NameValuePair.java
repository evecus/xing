package com.android.cglib.dx.rop.annotation;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstString;
import com.baidu.mobstat.Config;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class NameValuePair implements Comparable<NameValuePair> {
    private final CstString name;
    private final Constant value;

    public NameValuePair(CstString cstString, Constant constant) {
        Objects.requireNonNull(cstString, "name == null");
        Objects.requireNonNull(constant, "value == null");
        this.name = cstString;
        this.value = constant;
    }

    @Override // java.lang.Comparable
    public int compareTo(NameValuePair nameValuePair) {
        int iCompareTo = this.name.compareTo((Constant) nameValuePair.name);
        return iCompareTo != 0 ? iCompareTo : this.value.compareTo(nameValuePair.value);
    }

    public boolean equals(Object obj) {
        if (obj instanceof NameValuePair) {
            NameValuePair nameValuePair = (NameValuePair) obj;
            if (this.name.equals(nameValuePair.name) && this.value.equals(nameValuePair.value)) {
                return true;
            }
        }
        return false;
    }

    public CstString getName() {
        return this.name;
    }

    public Constant getValue() {
        return this.value;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return this.name.toHuman() + Config.TRACE_TODAY_VISIT_SPLIT + this.value;
    }
}

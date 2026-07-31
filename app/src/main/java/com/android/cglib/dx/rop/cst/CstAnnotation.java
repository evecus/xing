package com.android.cglib.dx.rop.cst;

import com.android.cglib.dx.rop.annotation.Annotation;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class CstAnnotation extends Constant {
    private final Annotation annotation;

    public CstAnnotation(Annotation annotation) {
        Objects.requireNonNull(annotation, "annotation == null");
        annotation.throwIfMutable();
        this.annotation = annotation;
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public int compareTo0(Constant constant) {
        return this.annotation.compareTo(((CstAnnotation) constant).annotation);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CstAnnotation) {
            return this.annotation.equals(((CstAnnotation) obj).annotation);
        }
        return false;
    }

    public Annotation getAnnotation() {
        return this.annotation;
    }

    public int hashCode() {
        return this.annotation.hashCode();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return this.annotation.toString();
    }

    public String toString() {
        return this.annotation.toString();
    }

    @Override // com.android.cglib.dx.rop.cst.Constant
    public String typeName() {
        return "annotation";
    }
}

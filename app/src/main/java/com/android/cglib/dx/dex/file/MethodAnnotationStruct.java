package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.annotation.Annotations;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstMethodRef;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import com.android.cglib.dx.util.ToHuman;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class MethodAnnotationStruct implements ToHuman, Comparable<MethodAnnotationStruct> {
    private AnnotationSetItem annotations;
    private final CstMethodRef method;

    public MethodAnnotationStruct(CstMethodRef cstMethodRef, AnnotationSetItem annotationSetItem) {
        Objects.requireNonNull(cstMethodRef, "method == null");
        Objects.requireNonNull(annotationSetItem, "annotations == null");
        this.method = cstMethodRef;
        this.annotations = annotationSetItem;
    }

    public void addContents(DexFile dexFile) {
        MethodIdsSection methodIds = dexFile.getMethodIds();
        MixedItemSection wordData = dexFile.getWordData();
        methodIds.intern(this.method);
        this.annotations = (AnnotationSetItem) wordData.intern(this.annotations);
    }

    @Override // java.lang.Comparable
    public int compareTo(MethodAnnotationStruct methodAnnotationStruct) {
        return this.method.compareTo((Constant) methodAnnotationStruct.method);
    }

    public boolean equals(Object obj) {
        if (obj instanceof MethodAnnotationStruct) {
            return this.method.equals(((MethodAnnotationStruct) obj).method);
        }
        return false;
    }

    public Annotations getAnnotations() {
        return this.annotations.getAnnotations();
    }

    public CstMethodRef getMethod() {
        return this.method;
    }

    public int hashCode() {
        return this.method.hashCode();
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return this.method.toHuman() + ": " + this.annotations;
    }

    public void writeTo(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        int iIndexOf = dexFile.getMethodIds().indexOf(this.method);
        int absoluteOffset = this.annotations.getAbsoluteOffset();
        if (annotatedOutput.annotates()) {
            StringBuilder sbO = a.o("    ");
            sbO.append(this.method.toHuman());
            annotatedOutput.annotate(0, sbO.toString());
            annotatedOutput.annotate(4, "      method_idx:      " + Hex.u4(iIndexOf));
            StringBuilder sb = new StringBuilder();
            sb.append("      annotations_off: ");
            a.f(absoluteOffset, sb, annotatedOutput, 4);
        }
        annotatedOutput.writeInt(iIndexOf);
        annotatedOutput.writeInt(absoluteOffset);
    }
}

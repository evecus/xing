package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.util.AnnotatedOutput;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AnnotationSetRefItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int WRITE_SIZE = 4;
    private AnnotationSetItem annotations;

    public AnnotationSetRefItem(AnnotationSetItem annotationSetItem) {
        super(4, 4);
        Objects.requireNonNull(annotationSetItem, "annotations == null");
        this.annotations = annotationSetItem;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        this.annotations = (AnnotationSetItem) dexFile.getWordData().intern(this.annotations);
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ANNOTATION_SET_REF_ITEM;
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.annotations.toHuman();
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        int absoluteOffset = this.annotations.getAbsoluteOffset();
        if (annotatedOutput.annotates()) {
            a.f(absoluteOffset, a.o("  annotations_off: "), annotatedOutput, 4);
        }
        annotatedOutput.writeInt(absoluteOffset);
    }
}

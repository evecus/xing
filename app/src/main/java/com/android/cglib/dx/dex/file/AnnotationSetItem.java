package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.annotation.Annotation;
import com.android.cglib.dx.rop.annotation.Annotations;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.Iterator;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AnnotationSetItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int ENTRY_WRITE_SIZE = 4;
    private final Annotations annotations;
    private final AnnotationItem[] items;

    public AnnotationSetItem(Annotations annotations) {
        super(4, writeSize(annotations));
        this.annotations = annotations;
        this.items = new AnnotationItem[annotations.size()];
        Iterator<Annotation> it = annotations.getAnnotations().iterator();
        int i = 0;
        while (it.hasNext()) {
            this.items[i] = new AnnotationItem(it.next());
            i++;
        }
    }

    private static int writeSize(Annotations annotations) {
        try {
            return (annotations.size() * 4) + 4;
        } catch (NullPointerException e) {
            throw new NullPointerException("list == null");
        }
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        MixedItemSection byteData = dexFile.getByteData();
        int length = this.items.length;
        for (int i = 0; i < length; i++) {
            AnnotationItem[] annotationItemArr = this.items;
            annotationItemArr[i] = (AnnotationItem) byteData.intern(annotationItemArr[i]);
        }
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem offsettedItem) {
        return this.annotations.compareTo(((AnnotationSetItem) offsettedItem).annotations);
    }

    public Annotations getAnnotations() {
        return this.annotations;
    }

    public int hashCode() {
        return this.annotations.hashCode();
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ANNOTATION_SET_ITEM;
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void place0(Section section, int i) {
        AnnotationItem.sortByTypeIdIndex(this.items);
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.annotations.toString();
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        boolean zAnnotates = annotatedOutput.annotates();
        int length = this.items.length;
        if (zAnnotates) {
            annotatedOutput.annotate(0, offsetString() + " annotation set");
            annotatedOutput.annotate(4, "  size: " + Hex.u4(length));
        }
        annotatedOutput.writeInt(length);
        for (int i = 0; i < length; i++) {
            int absoluteOffset = this.items[i].getAbsoluteOffset();
            if (zAnnotates) {
                StringBuilder sbO = a.o("  entries[");
                sbO.append(Integer.toHexString(i));
                sbO.append("]: ");
                sbO.append(Hex.u4(absoluteOffset));
                annotatedOutput.annotate(4, sbO.toString());
                this.items[i].annotateTo(annotatedOutput, "    ");
            }
            annotatedOutput.writeInt(absoluteOffset);
        }
    }
}

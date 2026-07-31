package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.annotation.Annotation;
import com.android.cglib.dx.rop.annotation.AnnotationVisibility;
import com.android.cglib.dx.rop.annotation.NameValuePair;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.ByteArrayAnnotatedOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AnnotationItem extends OffsettedItem {
    private static final int ALIGNMENT = 1;
    private static final TypeIdSorter TYPE_ID_SORTER = new TypeIdSorter(null);
    private static final int VISIBILITY_BUILD = 0;
    private static final int VISIBILITY_RUNTIME = 1;
    private static final int VISIBILITY_SYSTEM = 2;
    private final Annotation annotation;
    private byte[] encodedForm;
    private TypeIdItem type;

    /* JADX INFO: renamed from: com.android.cglib.dx.dex.file.AnnotationItem$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final int[] $SwitchMap$com$android$cglib$dx$rop$annotation$AnnotationVisibility;

        static {
            AnnotationVisibility.values();
            int[] iArr = new int[4];
            $SwitchMap$com$android$cglib$dx$rop$annotation$AnnotationVisibility = iArr;
            try {
                AnnotationVisibility annotationVisibility = AnnotationVisibility.BUILD;
                iArr[1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                int[] iArr2 = $SwitchMap$com$android$cglib$dx$rop$annotation$AnnotationVisibility;
                AnnotationVisibility annotationVisibility2 = AnnotationVisibility.RUNTIME;
                iArr2[0] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                int[] iArr3 = $SwitchMap$com$android$cglib$dx$rop$annotation$AnnotationVisibility;
                AnnotationVisibility annotationVisibility3 = AnnotationVisibility.SYSTEM;
                iArr3[2] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static class TypeIdSorter implements Comparator<AnnotationItem> {
        private TypeIdSorter() {
        }

        public /* synthetic */ TypeIdSorter(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.Comparator
        public int compare(AnnotationItem annotationItem, AnnotationItem annotationItem2) {
            int index = annotationItem.type.getIndex();
            int index2 = annotationItem2.type.getIndex();
            if (index < index2) {
                return -1;
            }
            return index > index2 ? 1 : 0;
        }
    }

    public AnnotationItem(Annotation annotation) {
        super(1, -1);
        Objects.requireNonNull(annotation, "annotation == null");
        this.annotation = annotation;
        this.type = null;
        this.encodedForm = null;
    }

    public static void sortByTypeIdIndex(AnnotationItem[] annotationItemArr) {
        Arrays.sort(annotationItemArr, TYPE_ID_SORTER);
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        this.type = dexFile.getTypeIds().intern(this.annotation.getType());
        ValueEncoder.addContents(dexFile, this.annotation);
    }

    public void annotateTo(AnnotatedOutput annotatedOutput, String str) {
        StringBuilder sbD = a.d(str, "visibility: ");
        sbD.append(this.annotation.getVisibility().toHuman());
        annotatedOutput.annotate(0, sbD.toString());
        annotatedOutput.annotate(0, str + "type: " + this.annotation.getType().toHuman());
        for (NameValuePair nameValuePair : this.annotation.getNameValuePairs()) {
            CstString name = nameValuePair.getName();
            Constant value = nameValuePair.getValue();
            StringBuilder sbO = a.o(str);
            sbO.append(name.toHuman());
            sbO.append(": ");
            sbO.append(ValueEncoder.constantToHuman(value));
            annotatedOutput.annotate(0, sbO.toString());
        }
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem offsettedItem) {
        return this.annotation.compareTo(((AnnotationItem) offsettedItem).annotation);
    }

    public int hashCode() {
        return this.annotation.hashCode();
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ANNOTATION_ITEM;
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void place0(Section section, int i) {
        ByteArrayAnnotatedOutput byteArrayAnnotatedOutput = new ByteArrayAnnotatedOutput();
        new ValueEncoder(section.getFile(), byteArrayAnnotatedOutput).writeAnnotation(this.annotation, false);
        byte[] byteArray = byteArrayAnnotatedOutput.toByteArray();
        this.encodedForm = byteArray;
        setWriteSize(byteArray.length + 1);
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.annotation.toHuman();
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        boolean zAnnotates = annotatedOutput.annotates();
        AnnotationVisibility visibility = this.annotation.getVisibility();
        int i = 0;
        if (zAnnotates) {
            annotatedOutput.annotate(0, offsetString() + " annotation");
            annotatedOutput.annotate(1, "  visibility: VISBILITY_" + visibility);
        }
        int iOrdinal = visibility.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                i = 2;
                if (iOrdinal != 2) {
                    throw new RuntimeException("shouldn't happen");
                }
            }
            annotatedOutput.writeByte(i);
        } else {
            annotatedOutput.writeByte(1);
        }
        if (zAnnotates) {
            new ValueEncoder(dexFile, annotatedOutput).writeAnnotation(this.annotation, true);
        } else {
            annotatedOutput.write(this.encodedForm);
        }
    }
}

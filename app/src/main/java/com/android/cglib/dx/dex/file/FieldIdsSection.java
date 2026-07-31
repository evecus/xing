package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstFieldRef;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.Collection;
import java.util.Objects;
import java.util.TreeMap;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class FieldIdsSection extends MemberIdsSection {
    private final TreeMap<CstFieldRef, FieldIdItem> fieldIds;

    public FieldIdsSection(DexFile dexFile) {
        super("field_ids", dexFile);
        this.fieldIds = new TreeMap<>();
    }

    @Override // com.android.cglib.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant constant) {
        Objects.requireNonNull(constant, "cst == null");
        throwIfNotPrepared();
        FieldIdItem fieldIdItem = this.fieldIds.get((CstFieldRef) constant);
        if (fieldIdItem != null) {
            return fieldIdItem;
        }
        throw new IllegalArgumentException("not found");
    }

    public int indexOf(CstFieldRef cstFieldRef) {
        Objects.requireNonNull(cstFieldRef, "ref == null");
        throwIfNotPrepared();
        FieldIdItem fieldIdItem = this.fieldIds.get(cstFieldRef);
        if (fieldIdItem != null) {
            return fieldIdItem.getIndex();
        }
        throw new IllegalArgumentException("not found");
    }

    public FieldIdItem intern(CstFieldRef cstFieldRef) {
        Objects.requireNonNull(cstFieldRef, "field == null");
        throwIfPrepared();
        FieldIdItem fieldIdItem = this.fieldIds.get(cstFieldRef);
        if (fieldIdItem != null) {
            return fieldIdItem;
        }
        FieldIdItem fieldIdItem2 = new FieldIdItem(cstFieldRef);
        this.fieldIds.put(cstFieldRef, fieldIdItem2);
        return fieldIdItem2;
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.fieldIds.values();
    }

    public void writeHeaderPart(AnnotatedOutput annotatedOutput) {
        throwIfNotPrepared();
        int size = this.fieldIds.size();
        int fileOffset = size == 0 ? 0 : getFileOffset();
        if (annotatedOutput.annotates()) {
            StringBuilder sbO = a.o("field_ids_size:  ");
            sbO.append(Hex.u4(size));
            annotatedOutput.annotate(4, sbO.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("field_ids_off:   ");
            a.f(fileOffset, sb, annotatedOutput, 4);
        }
        annotatedOutput.writeInt(size);
        annotatedOutput.writeInt(fileOffset);
    }
}

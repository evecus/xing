package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class TypeIdsSection extends UniformItemSection {
    private final TreeMap<Type, TypeIdItem> typeIds;

    public TypeIdsSection(DexFile dexFile) {
        super("type_ids", dexFile, 4);
        this.typeIds = new TreeMap<>();
    }

    @Override // com.android.cglib.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant constant) {
        Objects.requireNonNull(constant, "cst == null");
        throwIfNotPrepared();
        TypeIdItem typeIdItem = this.typeIds.get(((CstType) constant).getClassType());
        if (typeIdItem != null) {
            return typeIdItem;
        }
        throw new IllegalArgumentException("not found: " + constant);
    }

    public int indexOf(CstType cstType) {
        Objects.requireNonNull(cstType, "type == null");
        return indexOf(cstType.getClassType());
    }

    public int indexOf(Type type) {
        Objects.requireNonNull(type, "type == null");
        throwIfNotPrepared();
        TypeIdItem typeIdItem = this.typeIds.get(type);
        if (typeIdItem != null) {
            return typeIdItem.getIndex();
        }
        throw new IllegalArgumentException("not found: " + type);
    }

    public TypeIdItem intern(CstType cstType) {
        Objects.requireNonNull(cstType, "type == null");
        throwIfPrepared();
        Type classType = cstType.getClassType();
        TypeIdItem typeIdItem = this.typeIds.get(classType);
        if (typeIdItem != null) {
            return typeIdItem;
        }
        TypeIdItem typeIdItem2 = new TypeIdItem(cstType);
        this.typeIds.put(classType, typeIdItem2);
        return typeIdItem2;
    }

    public TypeIdItem intern(Type type) {
        Objects.requireNonNull(type, "type == null");
        throwIfPrepared();
        TypeIdItem typeIdItem = this.typeIds.get(type);
        if (typeIdItem != null) {
            return typeIdItem;
        }
        TypeIdItem typeIdItem2 = new TypeIdItem(new CstType(type));
        this.typeIds.put(type, typeIdItem2);
        return typeIdItem2;
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.typeIds.values();
    }

    @Override // com.android.cglib.dx.dex.file.UniformItemSection
    public void orderItems() {
        Iterator<? extends Item> it = items().iterator();
        int i = 0;
        while (it.hasNext()) {
            ((TypeIdItem) it.next()).setIndex(i);
            i++;
        }
    }

    public void writeHeaderPart(AnnotatedOutput annotatedOutput) {
        throwIfNotPrepared();
        int size = this.typeIds.size();
        int fileOffset = size == 0 ? 0 : getFileOffset();
        if (size > 65536) {
            throw new UnsupportedOperationException("too many type ids");
        }
        if (annotatedOutput.annotates()) {
            StringBuilder sbO = a.o("type_ids_size:   ");
            sbO.append(Hex.u4(size));
            annotatedOutput.annotate(4, sbO.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("type_ids_off:    ");
            a.f(fileOffset, sb, annotatedOutput, 4);
        }
        annotatedOutput.writeInt(size);
        annotatedOutput.writeInt(fileOffset);
    }
}

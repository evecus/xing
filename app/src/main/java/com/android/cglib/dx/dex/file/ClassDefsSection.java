package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeList;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class ClassDefsSection extends UniformItemSection {
    private final TreeMap<Type, ClassDefItem> classDefs;
    private ArrayList<ClassDefItem> orderedDefs;

    public ClassDefsSection(DexFile dexFile) {
        super("class_defs", dexFile, 4);
        this.classDefs = new TreeMap<>();
        this.orderedDefs = null;
    }

    private int orderItems0(Type type, int i, int i2) {
        ClassDefItem classDefItem = this.classDefs.get(type);
        if (classDefItem == null || classDefItem.hasIndex()) {
            return i;
        }
        if (i2 < 0) {
            throw new RuntimeException("class circularity with " + type);
        }
        int i3 = i2 - 1;
        CstType superclass = classDefItem.getSuperclass();
        if (superclass != null) {
            i = orderItems0(superclass.getClassType(), i, i3);
        }
        TypeList interfaces = classDefItem.getInterfaces();
        int size = interfaces.size();
        for (int i4 = 0; i4 < size; i4++) {
            i = orderItems0(interfaces.getType(i4), i, i3);
        }
        classDefItem.setIndex(i);
        this.orderedDefs.add(classDefItem);
        return i + 1;
    }

    public void add(ClassDefItem classDefItem) {
        try {
            Type classType = classDefItem.getThisClass().getClassType();
            throwIfPrepared();
            if (this.classDefs.get(classType) == null) {
                this.classDefs.put(classType, classDefItem);
                return;
            }
            throw new IllegalArgumentException("already added: " + classType);
        } catch (NullPointerException e) {
            throw new NullPointerException("clazz == null");
        }
    }

    @Override // com.android.cglib.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant constant) {
        Objects.requireNonNull(constant, "cst == null");
        throwIfNotPrepared();
        ClassDefItem classDefItem = this.classDefs.get(((CstType) constant).getClassType());
        if (classDefItem != null) {
            return classDefItem;
        }
        throw new IllegalArgumentException("not found");
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public Collection<? extends Item> items() {
        ArrayList<ClassDefItem> arrayList = this.orderedDefs;
        return arrayList != null ? arrayList : this.classDefs.values();
    }

    @Override // com.android.cglib.dx.dex.file.UniformItemSection
    public void orderItems() {
        int size = this.classDefs.size();
        this.orderedDefs = new ArrayList<>(size);
        Iterator<Type> it = this.classDefs.keySet().iterator();
        int iOrderItems0 = 0;
        while (it.hasNext()) {
            iOrderItems0 = orderItems0(it.next(), iOrderItems0, size - iOrderItems0);
        }
    }

    public void writeHeaderPart(AnnotatedOutput annotatedOutput) {
        throwIfNotPrepared();
        int size = this.classDefs.size();
        int fileOffset = size == 0 ? 0 : getFileOffset();
        if (annotatedOutput.annotates()) {
            StringBuilder sbO = a.o("class_defs_size: ");
            sbO.append(Hex.u4(size));
            annotatedOutput.annotate(4, sbO.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("class_defs_off:  ");
            a.f(fileOffset, sb, annotatedOutput, 4);
        }
        annotatedOutput.writeInt(size);
        annotatedOutput.writeInt(fileOffset);
    }
}

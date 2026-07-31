package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstBaseMethodRef;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.Collection;
import java.util.Objects;
import java.util.TreeMap;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class MethodIdsSection extends MemberIdsSection {
    private final TreeMap<CstBaseMethodRef, MethodIdItem> methodIds;

    public MethodIdsSection(DexFile dexFile) {
        super("method_ids", dexFile);
        this.methodIds = new TreeMap<>();
    }

    @Override // com.android.cglib.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant constant) {
        Objects.requireNonNull(constant, "cst == null");
        throwIfNotPrepared();
        MethodIdItem methodIdItem = this.methodIds.get((CstBaseMethodRef) constant);
        if (methodIdItem != null) {
            return methodIdItem;
        }
        throw new IllegalArgumentException("not found");
    }

    public int indexOf(CstBaseMethodRef cstBaseMethodRef) {
        Objects.requireNonNull(cstBaseMethodRef, "ref == null");
        throwIfNotPrepared();
        MethodIdItem methodIdItem = this.methodIds.get(cstBaseMethodRef);
        if (methodIdItem != null) {
            return methodIdItem.getIndex();
        }
        throw new IllegalArgumentException("not found");
    }

    public MethodIdItem intern(CstBaseMethodRef cstBaseMethodRef) {
        Objects.requireNonNull(cstBaseMethodRef, "method == null");
        throwIfPrepared();
        MethodIdItem methodIdItem = this.methodIds.get(cstBaseMethodRef);
        if (methodIdItem != null) {
            return methodIdItem;
        }
        MethodIdItem methodIdItem2 = new MethodIdItem(cstBaseMethodRef);
        this.methodIds.put(cstBaseMethodRef, methodIdItem2);
        return methodIdItem2;
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.methodIds.values();
    }

    public void writeHeaderPart(AnnotatedOutput annotatedOutput) {
        throwIfNotPrepared();
        int size = this.methodIds.size();
        int fileOffset = size == 0 ? 0 : getFileOffset();
        if (annotatedOutput.annotates()) {
            StringBuilder sbO = a.o("method_ids_size: ");
            sbO.append(Hex.u4(size));
            annotatedOutput.annotate(4, sbO.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("method_ids_off:  ");
            a.f(fileOffset, sb, annotatedOutput, 4);
        }
        annotatedOutput.writeInt(size);
        annotatedOutput.writeInt(fileOffset);
    }
}

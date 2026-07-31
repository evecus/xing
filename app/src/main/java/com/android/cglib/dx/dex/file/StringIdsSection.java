package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstNat;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class StringIdsSection extends UniformItemSection {
    private final TreeMap<CstString, StringIdItem> strings;

    public StringIdsSection(DexFile dexFile) {
        super("string_ids", dexFile, 4);
        this.strings = new TreeMap<>();
    }

    @Override // com.android.cglib.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant constant) {
        Objects.requireNonNull(constant, "cst == null");
        throwIfNotPrepared();
        StringIdItem stringIdItem = this.strings.get((CstString) constant);
        if (stringIdItem != null) {
            return stringIdItem;
        }
        throw new IllegalArgumentException("not found");
    }

    public int indexOf(CstString cstString) {
        Objects.requireNonNull(cstString, "string == null");
        throwIfNotPrepared();
        StringIdItem stringIdItem = this.strings.get(cstString);
        if (stringIdItem != null) {
            return stringIdItem.getIndex();
        }
        throw new IllegalArgumentException("not found");
    }

    public StringIdItem intern(StringIdItem stringIdItem) {
        Objects.requireNonNull(stringIdItem, "string == null");
        throwIfPrepared();
        CstString value = stringIdItem.getValue();
        StringIdItem stringIdItem2 = this.strings.get(value);
        if (stringIdItem2 != null) {
            return stringIdItem2;
        }
        this.strings.put(value, stringIdItem);
        return stringIdItem;
    }

    public StringIdItem intern(CstString cstString) {
        return intern(new StringIdItem(cstString));
    }

    public StringIdItem intern(String str) {
        return intern(new StringIdItem(new CstString(str)));
    }

    public void intern(CstNat cstNat) {
        intern(cstNat.getName());
        intern(cstNat.getDescriptor());
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.strings.values();
    }

    @Override // com.android.cglib.dx.dex.file.UniformItemSection
    public void orderItems() {
        Iterator<StringIdItem> it = this.strings.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next().setIndex(i);
            i++;
        }
    }

    public void writeHeaderPart(AnnotatedOutput annotatedOutput) {
        throwIfNotPrepared();
        int size = this.strings.size();
        int fileOffset = size == 0 ? 0 : getFileOffset();
        if (annotatedOutput.annotates()) {
            StringBuilder sbO = a.o("string_ids_size: ");
            sbO.append(Hex.u4(size));
            annotatedOutput.annotate(4, sbO.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("string_ids_off:  ");
            a.f(fileOffset, sb, annotatedOutput, 4);
        }
        annotatedOutput.writeInt(size);
        annotatedOutput.writeInt(fileOffset);
    }
}

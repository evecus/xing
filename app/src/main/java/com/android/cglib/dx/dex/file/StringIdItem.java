package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class StringIdItem extends IndexedItem implements Comparable {
    private StringDataItem data;
    private final CstString value;

    public StringIdItem(CstString cstString) {
        Objects.requireNonNull(cstString, "value == null");
        this.value = cstString;
        this.data = null;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        if (this.data == null) {
            MixedItemSection stringData = dexFile.getStringData();
            StringDataItem stringDataItem = new StringDataItem(this.value);
            this.data = stringDataItem;
            stringData.add(stringDataItem);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return this.value.compareTo((Constant) ((StringIdItem) obj).value);
    }

    public boolean equals(Object obj) {
        if (obj instanceof StringIdItem) {
            return this.value.equals(((StringIdItem) obj).value);
        }
        return false;
    }

    public StringDataItem getData() {
        return this.data;
    }

    public CstString getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_STRING_ID_ITEM;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public int writeSize() {
        return 4;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void writeTo(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        int absoluteOffset = this.data.getAbsoluteOffset();
        if (annotatedOutput.annotates()) {
            annotatedOutput.annotate(0, indexString() + ' ' + this.value.toQuoted(100));
            StringBuilder sb = new StringBuilder();
            sb.append("  string_data_off: ");
            sb.append(Hex.u4(absoluteOffset));
            annotatedOutput.annotate(4, sb.toString());
        }
        annotatedOutput.writeInt(absoluteOffset);
    }
}

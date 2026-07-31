package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.dex.file.OffsettedItem;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import com.baidu.android.common.util.HanziToPinyin;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class UniformListItem<T extends OffsettedItem> extends OffsettedItem {
    private static final int HEADER_SIZE = 4;
    private final ItemType itemType;
    private final List<T> items;

    public UniformListItem(ItemType itemType, List<T> list) {
        super(getAlignment(list), writeSize(list));
        Objects.requireNonNull(itemType, "itemType == null");
        this.items = list;
        this.itemType = itemType;
    }

    private static int getAlignment(List<? extends OffsettedItem> list) {
        try {
            return Math.max(4, list.get(0).getAlignment());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("items.size() == 0");
        } catch (NullPointerException e2) {
            throw new NullPointerException("items == null");
        }
    }

    private int headerSize() {
        return getAlignment();
    }

    private static int writeSize(List<? extends OffsettedItem> list) {
        OffsettedItem offsettedItem = list.get(0);
        return (offsettedItem.writeSize() * list.size()) + getAlignment(list);
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        Iterator<T> it = this.items.iterator();
        while (it.hasNext()) {
            it.next().addContents(dexFile);
        }
    }

    public final List<T> getItems() {
        return this.items;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return this.itemType;
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void place0(Section section, int i) {
        int iHeaderSize = headerSize();
        int iPlace = i + iHeaderSize;
        int i2 = -1;
        boolean z = true;
        int alignment = -1;
        for (T t : this.items) {
            int iWriteSize = t.writeSize();
            if (z) {
                z = false;
                alignment = t.getAlignment();
                i2 = iWriteSize;
            } else {
                if (iWriteSize != i2) {
                    throw new UnsupportedOperationException("item size mismatch");
                }
                if (t.getAlignment() != alignment) {
                    throw new UnsupportedOperationException("item alignment mismatch");
                }
            }
            iPlace = t.place(section, iPlace) + iWriteSize;
        }
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public final String toHuman() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("{");
        boolean z = true;
        for (T t : this.items) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(", ");
            }
            stringBuffer.append(t.toHuman());
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(UniformListItem.class.getName());
        stringBuffer.append(this.items);
        return stringBuffer.toString();
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        int size = this.items.size();
        if (annotatedOutput.annotates()) {
            annotatedOutput.annotate(0, offsetString() + HanziToPinyin.Token.SEPARATOR + typeName());
            StringBuilder sb = new StringBuilder();
            sb.append("  size: ");
            sb.append(Hex.u4(size));
            annotatedOutput.annotate(4, sb.toString());
        }
        annotatedOutput.writeInt(size);
        Iterator<T> it = this.items.iterator();
        while (it.hasNext()) {
            it.next().writeTo(dexFile, annotatedOutput);
        }
    }
}

package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.ArrayList;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class MapItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int WRITE_SIZE = 12;
    private final Item firstItem;
    private final int itemCount;
    private final Item lastItem;
    private final Section section;
    private final ItemType type;

    private MapItem(ItemType itemType, Section section, Item item, Item item2, int i) {
        super(4, 12);
        Objects.requireNonNull(itemType, "type == null");
        Objects.requireNonNull(section, "section == null");
        Objects.requireNonNull(item, "firstItem == null");
        Objects.requireNonNull(item2, "lastItem == null");
        if (i <= 0) {
            throw new IllegalArgumentException("itemCount <= 0");
        }
        this.type = itemType;
        this.section = section;
        this.firstItem = item;
        this.lastItem = item2;
        this.itemCount = i;
    }

    private MapItem(Section section) {
        super(4, 12);
        Objects.requireNonNull(section, "section == null");
        this.type = ItemType.TYPE_MAP_LIST;
        this.section = section;
        this.firstItem = null;
        this.lastItem = null;
        this.itemCount = 1;
    }

    public static void addMap(Section[] sectionArr, MixedItemSection mixedItemSection) {
        MapItem mapItem;
        Objects.requireNonNull(sectionArr, "sections == null");
        if (mixedItemSection.items().size() != 0) {
            throw new IllegalArgumentException("mapSection.items().size() != 0");
        }
        ArrayList arrayList = new ArrayList(50);
        for (Section section : sectionArr) {
            ItemType itemType = null;
            Item item = null;
            Item item2 = null;
            int i = 0;
            for (Item item3 : section.items()) {
                ItemType itemType2 = item3.itemType();
                if (itemType2 != itemType) {
                    if (i != 0) {
                        arrayList.add(new MapItem(itemType, section, item, item2, i));
                    }
                    item = item3;
                    itemType = itemType2;
                    i = 0;
                }
                i++;
                item2 = item3;
            }
            if (i != 0) {
                mapItem = new MapItem(itemType, section, item, item2, i);
            } else if (section == mixedItemSection) {
                mapItem = new MapItem(mixedItemSection);
            }
            arrayList.add(mapItem);
        }
        mixedItemSection.add(new UniformListItem(ItemType.TYPE_MAP_LIST, arrayList));
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_MAP_ITEM;
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public final String toHuman() {
        return toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(MapItem.class.getName());
        stringBuffer.append('{');
        stringBuffer.append(this.section.toString());
        stringBuffer.append(' ');
        stringBuffer.append(this.type.toHuman());
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        int mapValue = this.type.getMapValue();
        Item item = this.firstItem;
        int fileOffset = item == null ? this.section.getFileOffset() : this.section.getAbsoluteItemOffset(item);
        if (annotatedOutput.annotates()) {
            annotatedOutput.annotate(0, offsetString() + ' ' + this.type.getTypeName() + " map");
            annotatedOutput.annotate(2, "  type:   " + Hex.u2(mapValue) + " // " + this.type.toString());
            annotatedOutput.annotate(2, "  unused: 0");
            StringBuilder sb = new StringBuilder();
            sb.append("  size:   ");
            sb.append(Hex.u4(this.itemCount));
            annotatedOutput.annotate(4, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  offset: ");
            a.f(fileOffset, sb2, annotatedOutput, 4);
        }
        annotatedOutput.writeShort(mapValue);
        annotatedOutput.writeShort(0);
        annotatedOutput.writeInt(this.itemCount);
        annotatedOutput.writeInt(fileOffset);
    }
}

package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.type.StdTypeList;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeList;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class TypeListItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int ELEMENT_SIZE = 2;
    private static final int HEADER_SIZE = 4;
    private final TypeList list;

    public TypeListItem(TypeList typeList) {
        super(4, (typeList.size() * 2) + 4);
        this.list = typeList;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        TypeIdsSection typeIds = dexFile.getTypeIds();
        int size = this.list.size();
        for (int i = 0; i < size; i++) {
            typeIds.intern(this.list.getType(i));
        }
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem offsettedItem) {
        return StdTypeList.compareContents(this.list, ((TypeListItem) offsettedItem).list);
    }

    public TypeList getList() {
        return this.list;
    }

    public int hashCode() {
        return StdTypeList.hashContents(this.list);
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_TYPE_LIST;
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public String toHuman() {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        TypeIdsSection typeIds = dexFile.getTypeIds();
        int size = this.list.size();
        if (annotatedOutput.annotates()) {
            annotatedOutput.annotate(0, offsetString() + " type_list");
            annotatedOutput.annotate(4, "  size: " + Hex.u4(size));
            for (int i = 0; i < size; i++) {
                Type type = this.list.getType(i);
                int iIndexOf = typeIds.indexOf(type);
                StringBuilder sbO = a.o("  ");
                sbO.append(Hex.u2(iIndexOf));
                sbO.append(" // ");
                sbO.append(type.toHuman());
                annotatedOutput.annotate(2, sbO.toString());
            }
        }
        annotatedOutput.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            annotatedOutput.writeShort(typeIds.indexOf(this.list.getType(i2)));
        }
    }
}

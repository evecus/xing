package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.CstFieldRef;

/* JADX INFO: loaded from: classes.dex */
public final class FieldIdItem extends MemberIdItem {
    public FieldIdItem(CstFieldRef cstFieldRef) {
        super(cstFieldRef);
    }

    @Override // com.android.cglib.dx.dex.file.MemberIdItem, com.android.cglib.dx.dex.file.IdItem, com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        super.addContents(dexFile);
        dexFile.getTypeIds().intern(getFieldRef().getType());
    }

    public CstFieldRef getFieldRef() {
        return (CstFieldRef) getRef();
    }

    @Override // com.android.cglib.dx.dex.file.MemberIdItem
    public int getTypoidIdx(DexFile dexFile) {
        return dexFile.getTypeIds().indexOf(getFieldRef().getType());
    }

    @Override // com.android.cglib.dx.dex.file.MemberIdItem
    public String getTypoidName() {
        return "type_idx";
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_FIELD_ID_ITEM;
    }
}

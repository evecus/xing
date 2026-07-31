package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.util.AnnotatedOutput;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class TypeIdItem extends IdItem {
    public TypeIdItem(CstType cstType) {
        super(cstType);
    }

    @Override // com.android.cglib.dx.dex.file.IdItem, com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        dexFile.getStringIds().intern(getDefiningClass().getDescriptor());
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_TYPE_ID_ITEM;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public int writeSize() {
        return 4;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void writeTo(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        CstString descriptor = getDefiningClass().getDescriptor();
        int iIndexOf = dexFile.getStringIds().indexOf(descriptor);
        if (annotatedOutput.annotates()) {
            annotatedOutput.annotate(0, indexString() + ' ' + descriptor.toHuman());
            StringBuilder sb = new StringBuilder();
            sb.append("  descriptor_idx: ");
            a.f(iIndexOf, sb, annotatedOutput, 4);
        }
        annotatedOutput.writeInt(iIndexOf);
    }
}

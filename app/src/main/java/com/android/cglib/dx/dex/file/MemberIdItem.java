package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.CstMemberRef;
import com.android.cglib.dx.rop.cst.CstNat;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class MemberIdItem extends IdItem {
    private final CstMemberRef cst;

    public MemberIdItem(CstMemberRef cstMemberRef) {
        super(cstMemberRef.getDefiningClass());
        this.cst = cstMemberRef;
    }

    @Override // com.android.cglib.dx.dex.file.IdItem, com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        super.addContents(dexFile);
        dexFile.getStringIds().intern(getRef().getNat().getName());
    }

    public final CstMemberRef getRef() {
        return this.cst;
    }

    public abstract int getTypoidIdx(DexFile dexFile);

    public abstract String getTypoidName();

    @Override // com.android.cglib.dx.dex.file.Item
    public int writeSize() {
        return 8;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public final void writeTo(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        TypeIdsSection typeIds = dexFile.getTypeIds();
        StringIdsSection stringIds = dexFile.getStringIds();
        CstNat nat = this.cst.getNat();
        int iIndexOf = typeIds.indexOf(getDefiningClass());
        int iIndexOf2 = stringIds.indexOf(nat.getName());
        int typoidIdx = getTypoidIdx(dexFile);
        if (annotatedOutput.annotates()) {
            annotatedOutput.annotate(0, indexString() + ' ' + this.cst.toHuman());
            StringBuilder sb = new StringBuilder();
            sb.append("  class_idx: ");
            sb.append(Hex.u2(iIndexOf));
            annotatedOutput.annotate(2, sb.toString());
            annotatedOutput.annotate(2, String.format("  %-10s %s", getTypoidName() + ':', Hex.u2(typoidIdx)));
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  name_idx:  ");
            a.f(iIndexOf2, sb2, annotatedOutput, 4);
        }
        annotatedOutput.writeShort(iIndexOf);
        annotatedOutput.writeShort(typoidIdx);
        annotatedOutput.writeInt(iIndexOf2);
    }
}

package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.ByteArray;
import com.android.cglib.dx.util.Hex;
import com.android.cglib.dx.util.Leb128Utils;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class StringDataItem extends OffsettedItem {
    private final CstString value;

    public StringDataItem(CstString cstString) {
        super(1, writeSize(cstString));
        this.value = cstString;
    }

    private static int writeSize(CstString cstString) {
        return Leb128Utils.unsignedLeb128Size(cstString.getUtf16Size()) + cstString.getUtf8Size() + 1;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem offsettedItem) {
        return this.value.compareTo((Constant) ((StringDataItem) offsettedItem).value);
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_STRING_DATA_ITEM;
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.value.toQuoted();
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        ByteArray bytes = this.value.getBytes();
        int utf16Size = this.value.getUtf16Size();
        if (annotatedOutput.annotates()) {
            int iUnsignedLeb128Size = Leb128Utils.unsignedLeb128Size(utf16Size);
            StringBuilder sbO = a.o("utf16_size: ");
            sbO.append(Hex.u4(utf16Size));
            annotatedOutput.annotate(iUnsignedLeb128Size, sbO.toString());
            annotatedOutput.annotate(bytes.size() + 1, this.value.toQuoted());
        }
        annotatedOutput.writeUleb128(utf16Size);
        annotatedOutput.write(bytes);
        annotatedOutput.writeByte(0);
    }
}

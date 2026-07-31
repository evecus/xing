package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.dex.DexFormat;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class HeaderItem extends IndexedItem {
    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_HEADER_ITEM;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public int writeSize() {
        return 112;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void writeTo(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        int fileOffset = dexFile.getMap().getFileOffset();
        Section firstDataSection = dexFile.getFirstDataSection();
        Section lastDataSection = dexFile.getLastDataSection();
        int fileOffset2 = firstDataSection.getFileOffset();
        int fileOffset3 = (lastDataSection.getFileOffset() + lastDataSection.writeSize()) - fileOffset2;
        String magic = dexFile.getDexOptions().getMagic();
        if (annotatedOutput.annotates()) {
            StringBuilder sbO = a.o("magic: ");
            sbO.append(new CstString(magic).toQuoted());
            annotatedOutput.annotate(8, sbO.toString());
            annotatedOutput.annotate(4, "checksum");
            annotatedOutput.annotate(20, "signature");
            annotatedOutput.annotate(4, "file_size:       " + Hex.u4(dexFile.getFileSize()));
            annotatedOutput.annotate(4, "header_size:     " + Hex.u4(112));
            annotatedOutput.annotate(4, "endian_tag:      " + Hex.u4(DexFormat.ENDIAN_TAG));
            annotatedOutput.annotate(4, "link_size:       0");
            annotatedOutput.annotate(4, "link_off:        0");
            StringBuilder sb = new StringBuilder();
            sb.append("map_off:         ");
            a.f(fileOffset, sb, annotatedOutput, 4);
        }
        for (int i = 0; i < 8; i++) {
            annotatedOutput.writeByte(magic.charAt(i));
        }
        annotatedOutput.writeZeroes(24);
        annotatedOutput.writeInt(dexFile.getFileSize());
        annotatedOutput.writeInt(112);
        annotatedOutput.writeInt(DexFormat.ENDIAN_TAG);
        annotatedOutput.writeZeroes(8);
        annotatedOutput.writeInt(fileOffset);
        dexFile.getStringIds().writeHeaderPart(annotatedOutput);
        dexFile.getTypeIds().writeHeaderPart(annotatedOutput);
        dexFile.getProtoIds().writeHeaderPart(annotatedOutput);
        dexFile.getFieldIds().writeHeaderPart(annotatedOutput);
        dexFile.getMethodIds().writeHeaderPart(annotatedOutput);
        dexFile.getClassDefs().writeHeaderPart(annotatedOutput);
        if (annotatedOutput.annotates()) {
            StringBuilder sbO2 = a.o("data_size:       ");
            sbO2.append(Hex.u4(fileOffset3));
            annotatedOutput.annotate(4, sbO2.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("data_off:        ");
            a.f(fileOffset2, sb2, annotatedOutput, 4);
        }
        annotatedOutput.writeInt(fileOffset3);
        annotatedOutput.writeInt(fileOffset2);
    }
}

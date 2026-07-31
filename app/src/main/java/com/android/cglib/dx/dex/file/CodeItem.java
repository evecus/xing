package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.dex.code.DalvCode;
import com.android.cglib.dx.dex.code.DalvInsnList;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstMethodRef;
import com.android.cglib.dx.rop.type.StdTypeList;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeList;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.ExceptionWithContext;
import com.android.cglib.dx.util.Hex;
import com.baidu.mobstat.Config;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class CodeItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int HEADER_SIZE = 16;
    private CatchStructs catches;
    private final DalvCode code;
    private DebugInfoItem debugInfo;
    private final boolean isStatic;
    private final CstMethodRef ref;
    private final TypeList throwsList;

    public CodeItem(CstMethodRef cstMethodRef, DalvCode dalvCode, boolean z, TypeList typeList) {
        super(4, -1);
        Objects.requireNonNull(cstMethodRef, "ref == null");
        Objects.requireNonNull(dalvCode, "code == null");
        Objects.requireNonNull(typeList, "throwsList == null");
        this.ref = cstMethodRef;
        this.code = dalvCode;
        this.isStatic = z;
        this.throwsList = typeList;
        this.catches = null;
        this.debugInfo = null;
    }

    private int getInsSize() {
        return this.ref.getParameterWordCount(this.isStatic);
    }

    private int getOutsSize() {
        return this.code.getInsns().getOutsSize();
    }

    private int getRegistersSize() {
        return this.code.getInsns().getRegistersSize();
    }

    private void writeCodes(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        try {
            this.code.getInsns().writeTo(annotatedOutput);
        } catch (RuntimeException e) {
            StringBuilder sbO = a.o("...while writing instructions for ");
            sbO.append(this.ref.toHuman());
            throw ExceptionWithContext.withContext(e, sbO.toString());
        }
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public void addContents(DexFile dexFile) {
        MixedItemSection byteData = dexFile.getByteData();
        TypeIdsSection typeIds = dexFile.getTypeIds();
        if (this.code.hasPositions() || this.code.hasLocals()) {
            DebugInfoItem debugInfoItem = new DebugInfoItem(this.code, this.isStatic, this.ref);
            this.debugInfo = debugInfoItem;
            byteData.add(debugInfoItem);
        }
        if (this.code.hasAnyCatches()) {
            Iterator<Type> it = this.code.getCatchTypes().iterator();
            while (it.hasNext()) {
                typeIds.intern(it.next());
            }
            this.catches = new CatchStructs(this.code);
        }
        Iterator<Constant> it2 = this.code.getInsnConstants().iterator();
        while (it2.hasNext()) {
            dexFile.internIfAppropriate(it2.next());
        }
    }

    public void debugPrint(PrintWriter printWriter, String str, boolean z) {
        printWriter.println(this.ref.toHuman() + Config.TRACE_TODAY_VISIT_SPLIT);
        DalvInsnList insns = this.code.getInsns();
        StringBuilder sbO = a.o("regs: ");
        sbO.append(Hex.u2(getRegistersSize()));
        sbO.append("; ins: ");
        sbO.append(Hex.u2(getInsSize()));
        sbO.append("; outs: ");
        sbO.append(Hex.u2(getOutsSize()));
        printWriter.println(sbO.toString());
        insns.debugPrint(printWriter, str, z);
        String str2 = str + "  ";
        if (this.catches != null) {
            printWriter.print(str);
            printWriter.println("catches");
            this.catches.debugPrint(printWriter, str2);
        }
        if (this.debugInfo != null) {
            printWriter.print(str);
            printWriter.println("debug info");
            this.debugInfo.debugPrint(printWriter, str2);
        }
    }

    public CstMethodRef getRef() {
        return this.ref;
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_CODE_ITEM;
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void place0(Section section, int i) {
        int iWriteSize;
        DexFile file = section.getFile();
        this.code.assignIndices(new DalvCode.AssignIndicesCallback(this, file) { // from class: com.android.cglib.dx.dex.file.CodeItem.1
            public final CodeItem this$0;
            public final DexFile val$file;

            {
                this.this$0 = this;
                this.val$file = file;
            }

            @Override // com.android.cglib.dx.dex.code.DalvCode.AssignIndicesCallback
            public int getIndex(Constant constant) {
                IndexedItem indexedItemFindItemOrNull = this.val$file.findItemOrNull(constant);
                if (indexedItemFindItemOrNull == null) {
                    return -1;
                }
                return indexedItemFindItemOrNull.getIndex();
            }
        });
        CatchStructs catchStructs = this.catches;
        if (catchStructs != null) {
            catchStructs.encode(file);
            iWriteSize = this.catches.writeSize();
        } else {
            iWriteSize = 0;
        }
        int iCodeSize = this.code.getInsns().codeSize();
        if ((iCodeSize & 1) != 0) {
            iCodeSize++;
        }
        setWriteSize(iWriteSize + (iCodeSize * 2) + 16);
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.ref.toHuman();
    }

    public String toString() {
        StringBuilder sbO = a.o("CodeItem{");
        sbO.append(toHuman());
        sbO.append("}");
        return sbO.toString();
    }

    @Override // com.android.cglib.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        boolean zAnnotates = annotatedOutput.annotates();
        int registersSize = getRegistersSize();
        int outsSize = getOutsSize();
        int insSize = getInsSize();
        int iCodeSize = this.code.getInsns().codeSize();
        boolean z = (iCodeSize & 1) != 0;
        CatchStructs catchStructs = this.catches;
        int iTriesSize = catchStructs == null ? 0 : catchStructs.triesSize();
        DebugInfoItem debugInfoItem = this.debugInfo;
        int absoluteOffset = debugInfoItem == null ? 0 : debugInfoItem.getAbsoluteOffset();
        if (zAnnotates) {
            annotatedOutput.annotate(0, offsetString() + ' ' + this.ref.toHuman());
            StringBuilder sb = new StringBuilder();
            sb.append("  registers_size: ");
            sb.append(Hex.u2(registersSize));
            annotatedOutput.annotate(2, sb.toString());
            annotatedOutput.annotate(2, "  ins_size:       " + Hex.u2(insSize));
            annotatedOutput.annotate(2, "  outs_size:      " + Hex.u2(outsSize));
            annotatedOutput.annotate(2, "  tries_size:     " + Hex.u2(iTriesSize));
            annotatedOutput.annotate(4, "  debug_off:      " + Hex.u4(absoluteOffset));
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  insns_size:     ");
            a.f(iCodeSize, sb2, annotatedOutput, 4);
            if (this.throwsList.size() != 0) {
                StringBuilder sbO = a.o("  throws ");
                sbO.append(StdTypeList.toHuman(this.throwsList));
                annotatedOutput.annotate(0, sbO.toString());
            }
        }
        annotatedOutput.writeShort(registersSize);
        annotatedOutput.writeShort(insSize);
        annotatedOutput.writeShort(outsSize);
        annotatedOutput.writeShort(iTriesSize);
        annotatedOutput.writeInt(absoluteOffset);
        annotatedOutput.writeInt(iCodeSize);
        writeCodes(dexFile, annotatedOutput);
        if (this.catches != null) {
            if (z) {
                if (zAnnotates) {
                    annotatedOutput.annotate(2, "  padding: 0");
                }
                annotatedOutput.writeShort(0);
            }
            this.catches.writeTo(dexFile, annotatedOutput);
        }
        if (!zAnnotates || this.debugInfo == null) {
            return;
        }
        annotatedOutput.annotate(0, "  debug info");
        this.debugInfo.annotateTo(dexFile, annotatedOutput, "    ");
    }
}

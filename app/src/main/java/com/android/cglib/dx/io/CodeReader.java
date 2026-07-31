package com.android.cglib.dx.io;

import com.android.cglib.dx.io.instructions.DecodedInstruction;

/* JADX INFO: loaded from: classes.dex */
public final class CodeReader {
    private Visitor fallbackVisitor = null;
    private Visitor stringVisitor = null;
    private Visitor typeVisitor = null;
    private Visitor fieldVisitor = null;
    private Visitor methodVisitor = null;

    /* JADX INFO: renamed from: com.android.cglib.dx.io.CodeReader$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final int[] $SwitchMap$com$android$cglib$dx$io$IndexType;

        static {
            IndexType.values();
            int[] iArr = new int[10];
            $SwitchMap$com$android$cglib$dx$io$IndexType = iArr;
            try {
                IndexType indexType = IndexType.STRING_REF;
                iArr[4] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                int[] iArr2 = $SwitchMap$com$android$cglib$dx$io$IndexType;
                IndexType indexType2 = IndexType.TYPE_REF;
                iArr2[3] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                int[] iArr3 = $SwitchMap$com$android$cglib$dx$io$IndexType;
                IndexType indexType3 = IndexType.FIELD_REF;
                iArr3[6] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                int[] iArr4 = $SwitchMap$com$android$cglib$dx$io$IndexType;
                IndexType indexType4 = IndexType.METHOD_REF;
                iArr4[5] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public interface Visitor {
        void visit(DecodedInstruction[] decodedInstructionArr, DecodedInstruction decodedInstruction);
    }

    private void callVisit(DecodedInstruction[] decodedInstructionArr, DecodedInstruction decodedInstruction) {
        int iOrdinal = OpcodeInfo.getIndexType(decodedInstruction.getOpcode()).ordinal();
        Visitor visitor = iOrdinal != 3 ? iOrdinal != 4 ? iOrdinal != 5 ? iOrdinal != 6 ? null : this.fieldVisitor : this.methodVisitor : this.stringVisitor : this.typeVisitor;
        if (visitor == null) {
            visitor = this.fallbackVisitor;
        }
        if (visitor != null) {
            visitor.visit(decodedInstructionArr, decodedInstruction);
        }
    }

    public void setAllVisitors(Visitor visitor) {
        this.fallbackVisitor = visitor;
        this.stringVisitor = visitor;
        this.typeVisitor = visitor;
        this.fieldVisitor = visitor;
        this.methodVisitor = visitor;
    }

    public void setFallbackVisitor(Visitor visitor) {
        this.fallbackVisitor = visitor;
    }

    public void setFieldVisitor(Visitor visitor) {
        this.fieldVisitor = visitor;
    }

    public void setMethodVisitor(Visitor visitor) {
        this.methodVisitor = visitor;
    }

    public void setStringVisitor(Visitor visitor) {
        this.stringVisitor = visitor;
    }

    public void setTypeVisitor(Visitor visitor) {
        this.typeVisitor = visitor;
    }

    public void visitAll(DecodedInstruction[] decodedInstructionArr) {
        for (DecodedInstruction decodedInstruction : decodedInstructionArr) {
            if (decodedInstruction != null) {
                callVisit(decodedInstructionArr, decodedInstruction);
            }
        }
    }

    public void visitAll(short[] sArr) {
        visitAll(DecodedInstruction.decodeAll(sArr));
    }
}

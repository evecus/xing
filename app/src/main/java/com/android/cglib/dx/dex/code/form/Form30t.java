package com.android.cglib.dx.dex.code.form;

import com.android.cglib.dx.dex.code.DalvInsn;
import com.android.cglib.dx.dex.code.InsnFormat;
import com.android.cglib.dx.dex.code.TargetInsn;
import com.android.cglib.dx.util.AnnotatedOutput;

/* JADX INFO: loaded from: classes.dex */
public final class Form30t extends InsnFormat {
    public static final InsnFormat THE_ONE = new Form30t();

    private Form30t() {
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public boolean branchFits(TargetInsn targetInsn) {
        return true;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public int codeSize() {
        return 3;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn dalvInsn) {
        return InsnFormat.branchString(dalvInsn);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn dalvInsn, boolean z) {
        return InsnFormat.branchComment(dalvInsn);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn dalvInsn) {
        return (dalvInsn instanceof TargetInsn) && dalvInsn.getRegisters().size() == 0;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput annotatedOutput, DalvInsn dalvInsn) {
        InsnFormat.write(annotatedOutput, InsnFormat.opcodeUnit(dalvInsn, 0), ((TargetInsn) dalvInsn).getTargetOffset());
    }
}

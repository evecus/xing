package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.SourcePosition;
import com.android.cglib.dx.util.AnnotatedOutput;

/* JADX INFO: loaded from: classes.dex */
public final class OddSpacer extends VariableSizeInsn {
    public OddSpacer(SourcePosition sourcePosition) {
        super(sourcePosition, RegisterSpecList.EMPTY);
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String argString() {
        return null;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public int codeSize() {
        return getAddress() & 1;
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public String listingString0(boolean z) {
        if (codeSize() == 0) {
            return null;
        }
        return "nop // spacer";
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList registerSpecList) {
        return new OddSpacer(getPosition());
    }

    @Override // com.android.cglib.dx.dex.code.DalvInsn
    public void writeTo(AnnotatedOutput annotatedOutput) {
        if (codeSize() != 0) {
            annotatedOutput.writeShort(InsnFormat.codeUnit(0, 0));
        }
    }
}

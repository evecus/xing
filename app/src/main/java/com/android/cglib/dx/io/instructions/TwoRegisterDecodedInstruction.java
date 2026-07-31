package com.android.cglib.dx.io.instructions;

import com.android.cglib.dx.io.IndexType;

/* JADX INFO: loaded from: classes.dex */
public final class TwoRegisterDecodedInstruction extends DecodedInstruction {
    private final int a;
    private final int b;

    public TwoRegisterDecodedInstruction(InstructionCodec instructionCodec, int i, int i2, IndexType indexType, int i3, long j, int i4, int i5) {
        super(instructionCodec, i, i2, indexType, i3, j);
        this.a = i4;
        this.b = i5;
    }

    @Override // com.android.cglib.dx.io.instructions.DecodedInstruction
    public int getA() {
        return this.a;
    }

    @Override // com.android.cglib.dx.io.instructions.DecodedInstruction
    public int getB() {
        return this.b;
    }

    @Override // com.android.cglib.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return 2;
    }

    @Override // com.android.cglib.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int i) {
        return new TwoRegisterDecodedInstruction(getFormat(), getOpcode(), i, getIndexType(), getTarget(), getLiteral(), this.a, this.b);
    }
}

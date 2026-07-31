package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.util.Bits;
import com.android.cglib.dx.util.IntList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class LocalVariableExtractor {
    private final BasicBlockList blocks;
    private final RopMethod method;
    private final LocalVariableInfo resultInfo;
    private final int[] workSet;

    private LocalVariableExtractor(RopMethod ropMethod) {
        Objects.requireNonNull(ropMethod, "method == null");
        BasicBlockList blocks = ropMethod.getBlocks();
        int maxLabel = blocks.getMaxLabel();
        this.method = ropMethod;
        this.blocks = blocks;
        this.resultInfo = new LocalVariableInfo(ropMethod);
        this.workSet = Bits.makeBitSet(maxLabel);
    }

    private LocalVariableInfo doit() {
        int firstLabel = this.method.getFirstLabel();
        while (firstLabel >= 0) {
            Bits.clear(this.workSet, firstLabel);
            processBlock(firstLabel);
            firstLabel = Bits.findFirst(this.workSet, 0);
        }
        this.resultInfo.setImmutable();
        return this.resultInfo;
    }

    public static LocalVariableInfo extract(RopMethod ropMethod) {
        return new LocalVariableExtractor(ropMethod).doit();
    }

    private void processBlock(int i) {
        RegisterSpecSet registerSpecSetMutableCopyOfStarts = this.resultInfo.mutableCopyOfStarts(i);
        BasicBlock basicBlockLabelToBlock = this.blocks.labelToBlock(i);
        InsnList insns = basicBlockLabelToBlock.getInsns();
        int size = insns.size();
        boolean z = basicBlockLabelToBlock.hasExceptionHandlers() && insns.getLast().getResult() != null;
        RegisterSpecSet registerSpecSetMutableCopy = registerSpecSetMutableCopyOfStarts;
        for (int i2 = 0; i2 < size; i2++) {
            if (z && i2 == size - 1) {
                registerSpecSetMutableCopy.setImmutable();
                registerSpecSetMutableCopy = registerSpecSetMutableCopy.mutableCopy();
            }
            Insn insn = insns.get(i2);
            RegisterSpec localAssignment = insn.getLocalAssignment();
            if (localAssignment == null) {
                RegisterSpec result = insn.getResult();
                if (result != null && registerSpecSetMutableCopy.get(result.getReg()) != null) {
                    registerSpecSetMutableCopy.remove(registerSpecSetMutableCopy.get(result.getReg()));
                }
            } else {
                RegisterSpec registerSpecWithSimpleType = localAssignment.withSimpleType();
                if (!registerSpecWithSimpleType.equals(registerSpecSetMutableCopy.get(registerSpecWithSimpleType))) {
                    RegisterSpec registerSpecLocalItemToSpec = registerSpecSetMutableCopy.localItemToSpec(registerSpecWithSimpleType.getLocalItem());
                    if (registerSpecLocalItemToSpec != null && registerSpecLocalItemToSpec.getReg() != registerSpecWithSimpleType.getReg()) {
                        registerSpecSetMutableCopy.remove(registerSpecLocalItemToSpec);
                    }
                    this.resultInfo.addAssignment(insn, registerSpecWithSimpleType);
                    registerSpecSetMutableCopy.put(registerSpecWithSimpleType);
                }
            }
        }
        registerSpecSetMutableCopy.setImmutable();
        IntList successors = basicBlockLabelToBlock.getSuccessors();
        int size2 = successors.size();
        int primarySuccessor = basicBlockLabelToBlock.getPrimarySuccessor();
        for (int i3 = 0; i3 < size2; i3++) {
            int i4 = successors.get(i3);
            if (this.resultInfo.mergeStarts(i4, i4 == primarySuccessor ? registerSpecSetMutableCopy : registerSpecSetMutableCopyOfStarts)) {
                Bits.set(this.workSet, i4);
            }
        }
    }
}

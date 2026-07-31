package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.type.TypeList;
import com.android.cglib.dx.util.Hex;
import com.android.cglib.dx.util.IntList;
import com.android.cglib.dx.util.LabeledItem;

/* JADX INFO: loaded from: classes.dex */
public final class BasicBlock implements LabeledItem {
    private final InsnList insns;
    private final int label;
    private final int primarySuccessor;
    private final IntList successors;

    public interface Visitor {
        void visitBlock(BasicBlock basicBlock);
    }

    public BasicBlock(int i, InsnList insnList, IntList intList, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("label < 0");
        }
        try {
            insnList.throwIfMutable();
            int size = insnList.size();
            if (size == 0) {
                throw new IllegalArgumentException("insns.size() == 0");
            }
            for (int i3 = size - 2; i3 >= 0; i3--) {
                if (insnList.get(i3).getOpcode().getBranchingness() != 1) {
                    throw new IllegalArgumentException("insns[" + i3 + "] is a branch or can throw");
                }
            }
            if (insnList.get(size - 1).getOpcode().getBranchingness() == 1) {
                throw new IllegalArgumentException("insns does not end with a branch or throwing instruction");
            }
            try {
                intList.throwIfMutable();
                if (i2 < -1) {
                    throw new IllegalArgumentException("primarySuccessor < -1");
                }
                if (i2 < 0 || intList.contains(i2)) {
                    this.label = i;
                    this.insns = insnList;
                    this.successors = intList;
                    this.primarySuccessor = i2;
                    return;
                }
                throw new IllegalArgumentException("primarySuccessor " + i2 + " not in successors " + intList);
            } catch (NullPointerException e) {
                throw new NullPointerException("successors == null");
            }
        } catch (NullPointerException e2) {
            throw new NullPointerException("insns == null");
        }
    }

    public boolean canThrow() {
        return this.insns.getLast().canThrow();
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public TypeList getExceptionHandlerTypes() {
        return this.insns.getLast().getCatches();
    }

    public Insn getFirstInsn() {
        return this.insns.get(0);
    }

    public InsnList getInsns() {
        return this.insns;
    }

    @Override // com.android.cglib.dx.util.LabeledItem
    public int getLabel() {
        return this.label;
    }

    public Insn getLastInsn() {
        return this.insns.getLast();
    }

    public int getPrimarySuccessor() {
        return this.primarySuccessor;
    }

    public int getSecondarySuccessor() {
        if (this.successors.size() != 2) {
            throw new UnsupportedOperationException("block doesn't have exactly two successors");
        }
        int i = this.successors.get(0);
        return i == this.primarySuccessor ? this.successors.get(1) : i;
    }

    public IntList getSuccessors() {
        return this.successors;
    }

    public boolean hasExceptionHandlers() {
        return this.insns.getLast().getCatches().size() != 0;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public String toString() {
        return '{' + Hex.u2(this.label) + '}';
    }

    public BasicBlock withRegisterOffset(int i) {
        return new BasicBlock(this.label, this.insns.withRegisterOffset(i), this.successors, this.primarySuccessor);
    }
}

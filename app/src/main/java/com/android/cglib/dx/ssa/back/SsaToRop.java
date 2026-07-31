package com.android.cglib.dx.ssa.back;

import com.android.cglib.dx.rop.code.BasicBlock;
import com.android.cglib.dx.rop.code.BasicBlockList;
import com.android.cglib.dx.rop.code.InsnList;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.Rop;
import com.android.cglib.dx.rop.code.RopMethod;
import com.android.cglib.dx.rop.code.Rops;
import com.android.cglib.dx.ssa.BasicRegisterMapper;
import com.android.cglib.dx.ssa.PhiInsn;
import com.android.cglib.dx.ssa.RegisterMapper;
import com.android.cglib.dx.ssa.SsaBasicBlock;
import com.android.cglib.dx.ssa.SsaInsn;
import com.android.cglib.dx.ssa.SsaMethod;
import com.android.cglib.dx.util.Hex;
import com.android.cglib.dx.util.IntList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Iterator;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class SsaToRop {
    private static final boolean DEBUG = false;
    private final InterferenceGraph interference;
    private final boolean minimizeRegisters;
    private final SsaMethod ssaMeth;

    public static class PhiVisitor implements PhiInsn.Visitor {
        private final ArrayList<SsaBasicBlock> blocks;

        public PhiVisitor(ArrayList<SsaBasicBlock> arrayList) {
            this.blocks = arrayList;
        }

        @Override // com.android.cglib.dx.ssa.PhiInsn.Visitor
        public void visitPhiInsn(PhiInsn phiInsn) {
            RegisterSpecList sources = phiInsn.getSources();
            RegisterSpec result = phiInsn.getResult();
            int size = sources.size();
            for (int i = 0; i < size; i++) {
                this.blocks.get(phiInsn.predBlockIndexForSourcesIndex(i)).addMoveToEnd(result, sources.get(i));
            }
        }
    }

    private SsaToRop(SsaMethod ssaMethod, boolean z) {
        this.minimizeRegisters = z;
        this.ssaMeth = ssaMethod;
        this.interference = LivenessAnalyzer.constructInterferenceGraph(ssaMethod);
    }

    private RopMethod convert() {
        FirstFitLocalCombiningAllocator firstFitLocalCombiningAllocator = new FirstFitLocalCombiningAllocator(this.ssaMeth, this.interference, this.minimizeRegisters);
        RegisterMapper registerMapperAllocateRegisters = firstFitLocalCombiningAllocator.allocateRegisters();
        this.ssaMeth.setBackMode();
        this.ssaMeth.mapRegisters(registerMapperAllocateRegisters);
        removePhiFunctions();
        if (firstFitLocalCombiningAllocator.wantsParamsMovedHigh()) {
            moveParametersToHighRegisters();
        }
        removeEmptyGotos();
        BasicBlockList basicBlockListConvertBasicBlocks = convertBasicBlocks();
        SsaMethod ssaMethod = this.ssaMeth;
        return new IdenticalBlockCombiner(new RopMethod(basicBlockListConvertBasicBlocks, ssaMethod.blockIndexToRopLabel(ssaMethod.getEntryBlockIndex()))).process();
    }

    private BasicBlock convertBasicBlock(SsaBasicBlock ssaBasicBlock) {
        IntList ropLabelSuccessorList = ssaBasicBlock.getRopLabelSuccessorList();
        int primarySuccessorRopLabel = ssaBasicBlock.getPrimarySuccessorRopLabel();
        SsaBasicBlock exitBlock = this.ssaMeth.getExitBlock();
        if (ropLabelSuccessorList.contains(exitBlock == null ? -1 : exitBlock.getRopLabel())) {
            if (ropLabelSuccessorList.size() > 1) {
                StringBuilder sbO = a.o("Exit predecessor must have no other successors");
                sbO.append(Hex.u2(ssaBasicBlock.getRopLabel()));
                throw new RuntimeException(sbO.toString());
            }
            ropLabelSuccessorList = IntList.EMPTY;
            verifyValidExitPredecessor(ssaBasicBlock);
            primarySuccessorRopLabel = -1;
        }
        ropLabelSuccessorList.setImmutable();
        return new BasicBlock(ssaBasicBlock.getRopLabel(), convertInsns(ssaBasicBlock.getInsns()), ropLabelSuccessorList, primarySuccessorRopLabel);
    }

    private BasicBlockList convertBasicBlocks() {
        ArrayList<SsaBasicBlock> blocks = this.ssaMeth.getBlocks();
        SsaBasicBlock exitBlock = this.ssaMeth.getExitBlock();
        this.ssaMeth.computeReachability();
        int i = 0;
        BasicBlockList basicBlockList = new BasicBlockList(this.ssaMeth.getCountReachableBlocks() - ((exitBlock == null || !exitBlock.isReachable()) ? 0 : 1));
        for (SsaBasicBlock ssaBasicBlock : blocks) {
            if (ssaBasicBlock.isReachable() && ssaBasicBlock != exitBlock) {
                basicBlockList.set(i, convertBasicBlock(ssaBasicBlock));
                i++;
            }
        }
        if (exitBlock == null || exitBlock.getInsns().size() == 0) {
            return basicBlockList;
        }
        throw new RuntimeException("Exit block must have no insns when leaving SSA form");
    }

    private InsnList convertInsns(ArrayList<SsaInsn> arrayList) {
        int size = arrayList.size();
        InsnList insnList = new InsnList(size);
        for (int i = 0; i < size; i++) {
            insnList.set(i, arrayList.get(i).toRopInsn());
        }
        insnList.setImmutable();
        return insnList;
    }

    public static RopMethod convertToRopMethod(SsaMethod ssaMethod, boolean z) {
        return new SsaToRop(ssaMethod, z).convert();
    }

    private void moveParametersToHighRegisters() {
        int paramWidth = this.ssaMeth.getParamWidth();
        BasicRegisterMapper basicRegisterMapper = new BasicRegisterMapper(this.ssaMeth.getRegCount());
        int regCount = this.ssaMeth.getRegCount();
        int i = 0;
        while (i < regCount) {
            basicRegisterMapper.addMapping(i, i < paramWidth ? (regCount - paramWidth) + i : i - paramWidth, 1);
            i++;
        }
        this.ssaMeth.mapRegisters(basicRegisterMapper);
    }

    private void removeEmptyGotos() {
        this.ssaMeth.forEachBlockDepthFirst(false, new SsaBasicBlock.Visitor(this, this.ssaMeth.getBlocks()) { // from class: com.android.cglib.dx.ssa.back.SsaToRop.1
            public final SsaToRop this$0;
            public final ArrayList val$blocks;

            {
                this.this$0 = this;
                this.val$blocks = arrayList;
            }

            @Override // com.android.cglib.dx.ssa.SsaBasicBlock.Visitor
            public void visitBlock(SsaBasicBlock ssaBasicBlock, SsaBasicBlock ssaBasicBlock2) {
                ArrayList<SsaInsn> insns = ssaBasicBlock.getInsns();
                if (insns.size() != 1) {
                    return;
                }
                int i = 0;
                if (insns.get(0).getOpcode() != Rops.GOTO) {
                    return;
                }
                BitSet bitSet = (BitSet) ssaBasicBlock.getPredecessors().clone();
                while (true) {
                    int iNextSetBit = bitSet.nextSetBit(i);
                    if (iNextSetBit < 0) {
                        return;
                    }
                    ((SsaBasicBlock) this.val$blocks.get(iNextSetBit)).replaceSuccessor(ssaBasicBlock.getIndex(), ssaBasicBlock.getPrimarySuccessorIndex());
                    i = iNextSetBit + 1;
                }
            }
        });
    }

    private void removePhiFunctions() {
        ArrayList<SsaBasicBlock> blocks = this.ssaMeth.getBlocks();
        for (SsaBasicBlock ssaBasicBlock : blocks) {
            ssaBasicBlock.forEachPhiInsn(new PhiVisitor(blocks));
            ssaBasicBlock.removeAllPhiInsns();
        }
        Iterator<SsaBasicBlock> it = blocks.iterator();
        while (it.hasNext()) {
            it.next().scheduleMovesFromPhis();
        }
    }

    private void verifyValidExitPredecessor(SsaBasicBlock ssaBasicBlock) {
        Rop opcode = ssaBasicBlock.getInsns().get(r3.size() - 1).getOpcode();
        if (opcode.getBranchingness() != 2 && opcode != Rops.THROW) {
            throw new RuntimeException("Exit predecessor must end in valid exit statement.");
        }
    }

    public int[] getRegistersByFrequency() {
        int regCount = this.ssaMeth.getRegCount();
        Integer[] numArr = new Integer[regCount];
        for (int i = 0; i < regCount; i++) {
            numArr[i] = Integer.valueOf(i);
        }
        Arrays.sort(numArr, new Comparator<Integer>(this) { // from class: com.android.cglib.dx.ssa.back.SsaToRop.2
            public final SsaToRop this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.Comparator
            public int compare(Integer num, Integer num2) {
                return this.this$0.ssaMeth.getUseListForRegister(num2.intValue()).size() - this.this$0.ssaMeth.getUseListForRegister(num.intValue()).size();
            }
        });
        int[] iArr = new int[regCount];
        for (int i2 = 0; i2 < regCount; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
        return iArr;
    }
}

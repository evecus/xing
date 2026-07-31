package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RopMethod;
import com.android.cglib.dx.ssa.DomFront;
import com.android.cglib.dx.util.IntIterator;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class SsaConverter {
    public static final boolean DEBUG = false;

    public static SsaMethod convertToSsaMethod(RopMethod ropMethod, int i, boolean z) {
        SsaMethod ssaMethodNewFromRopMethod = SsaMethod.newFromRopMethod(ropMethod, i, z);
        edgeSplit(ssaMethodNewFromRopMethod);
        placePhiFunctions(ssaMethodNewFromRopMethod, LocalVariableExtractor.extract(ssaMethodNewFromRopMethod), 0);
        new SsaRenamer(ssaMethodNewFromRopMethod).run();
        ssaMethodNewFromRopMethod.makeExitBlock();
        return ssaMethodNewFromRopMethod;
    }

    private static void edgeSplit(SsaMethod ssaMethod) {
        edgeSplitPredecessors(ssaMethod);
        edgeSplitMoveExceptionsAndResults(ssaMethod);
        edgeSplitSuccessors(ssaMethod);
    }

    private static void edgeSplitMoveExceptionsAndResults(SsaMethod ssaMethod) {
        ArrayList<SsaBasicBlock> blocks = ssaMethod.getBlocks();
        for (int size = blocks.size() - 1; size >= 0; size--) {
            SsaBasicBlock ssaBasicBlock = blocks.get(size);
            if (!ssaBasicBlock.isExitBlock() && ssaBasicBlock.getPredecessors().cardinality() > 1 && ssaBasicBlock.getInsns().get(0).isMoveException()) {
                BitSet bitSet = (BitSet) ssaBasicBlock.getPredecessors().clone();
                for (int iNextSetBit = bitSet.nextSetBit(0); iNextSetBit >= 0; iNextSetBit = bitSet.nextSetBit(iNextSetBit + 1)) {
                    blocks.get(iNextSetBit).insertNewSuccessor(ssaBasicBlock).getInsns().add(0, ssaBasicBlock.getInsns().get(0).mo6clone());
                }
                ssaBasicBlock.getInsns().remove(0);
            }
        }
    }

    private static void edgeSplitPredecessors(SsaMethod ssaMethod) {
        ArrayList<SsaBasicBlock> blocks = ssaMethod.getBlocks();
        for (int size = blocks.size() - 1; size >= 0; size--) {
            SsaBasicBlock ssaBasicBlock = blocks.get(size);
            if (nodeNeedsUniquePredecessor(ssaBasicBlock)) {
                ssaBasicBlock.insertNewPredecessor();
            }
        }
    }

    private static void edgeSplitSuccessors(SsaMethod ssaMethod) {
        ArrayList<SsaBasicBlock> blocks = ssaMethod.getBlocks();
        for (int size = blocks.size() - 1; size >= 0; size--) {
            SsaBasicBlock ssaBasicBlock = blocks.get(size);
            BitSet bitSet = (BitSet) ssaBasicBlock.getSuccessors().clone();
            int i = 0;
            while (true) {
                int iNextSetBit = bitSet.nextSetBit(i);
                if (iNextSetBit >= 0) {
                    SsaBasicBlock ssaBasicBlock2 = blocks.get(iNextSetBit);
                    if (needsNewSuccessor(ssaBasicBlock, ssaBasicBlock2)) {
                        ssaBasicBlock.insertNewSuccessor(ssaBasicBlock2);
                    }
                    i = iNextSetBit + 1;
                }
            }
        }
    }

    private static boolean needsNewSuccessor(SsaBasicBlock ssaBasicBlock, SsaBasicBlock ssaBasicBlock2) {
        ArrayList<SsaInsn> insns = ssaBasicBlock.getInsns();
        SsaInsn ssaInsn = insns.get(insns.size() - 1);
        return (ssaInsn.getResult() != null || ssaInsn.getSources().size() > 0) && ssaBasicBlock2.getPredecessors().cardinality() > 1;
    }

    private static boolean nodeNeedsUniquePredecessor(SsaBasicBlock ssaBasicBlock) {
        return ssaBasicBlock.getPredecessors().cardinality() > 1 && ssaBasicBlock.getSuccessors().cardinality() > 1;
    }

    private static void placePhiFunctions(SsaMethod ssaMethod, LocalVariableInfo localVariableInfo, int i) {
        ArrayList<SsaBasicBlock> blocks = ssaMethod.getBlocks();
        int size = blocks.size();
        int regCount = ssaMethod.getRegCount() - i;
        DomFront.DomInfo[] domInfoArrRun = new DomFront(ssaMethod).run();
        BitSet[] bitSetArr = new BitSet[regCount];
        BitSet[] bitSetArr2 = new BitSet[regCount];
        for (int i2 = 0; i2 < regCount; i2++) {
            bitSetArr[i2] = new BitSet(size);
            bitSetArr2[i2] = new BitSet(size);
        }
        int size2 = blocks.size();
        for (int i3 = 0; i3 < size2; i3++) {
            Iterator<SsaInsn> it = blocks.get(i3).getInsns().iterator();
            while (it.hasNext()) {
                RegisterSpec result = it.next().getResult();
                if (result != null && result.getReg() - i >= 0) {
                    bitSetArr[result.getReg() - i].set(i3);
                }
            }
        }
        for (int i4 = 0; i4 < regCount; i4++) {
            BitSet bitSet = (BitSet) bitSetArr[i4].clone();
            while (true) {
                int iNextSetBit = bitSet.nextSetBit(0);
                if (iNextSetBit >= 0) {
                    bitSet.clear(iNextSetBit);
                    IntIterator it2 = domInfoArrRun[iNextSetBit].dominanceFrontiers.iterator();
                    while (it2.hasNext()) {
                        int next = it2.next();
                        if (!bitSetArr2[i4].get(next)) {
                            bitSetArr2[i4].set(next);
                            int i5 = i4 + i;
                            RegisterSpec registerSpec = localVariableInfo.getStarts(next).get(i5);
                            if (registerSpec == null) {
                                blocks.get(next).addPhiInsnForReg(i5);
                            } else {
                                blocks.get(next).addPhiInsnForReg(registerSpec);
                            }
                            if (!bitSetArr[i4].get(next)) {
                                bitSet.set(next);
                            }
                        }
                    }
                }
            }
        }
    }

    public static SsaMethod testEdgeSplit(RopMethod ropMethod, int i, boolean z) {
        SsaMethod ssaMethodNewFromRopMethod = SsaMethod.newFromRopMethod(ropMethod, i, z);
        edgeSplit(ssaMethodNewFromRopMethod);
        return ssaMethodNewFromRopMethod;
    }

    public static SsaMethod testPhiPlacement(RopMethod ropMethod, int i, boolean z) {
        SsaMethod ssaMethodNewFromRopMethod = SsaMethod.newFromRopMethod(ropMethod, i, z);
        edgeSplit(ssaMethodNewFromRopMethod);
        placePhiFunctions(ssaMethodNewFromRopMethod, LocalVariableExtractor.extract(ssaMethodNewFromRopMethod), 0);
        return ssaMethodNewFromRopMethod;
    }

    public static void updateSsaMethod(SsaMethod ssaMethod, int i) {
        placePhiFunctions(ssaMethod, LocalVariableExtractor.extract(ssaMethod), i);
        new SsaRenamer(ssaMethod, i).run();
    }
}

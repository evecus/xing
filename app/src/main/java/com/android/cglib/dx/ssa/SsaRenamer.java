package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.LocalItem;
import com.android.cglib.dx.rop.code.PlainInsn;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.Rops;
import com.android.cglib.dx.rop.code.SourcePosition;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.ssa.PhiInsn;
import com.android.cglib.dx.ssa.SsaBasicBlock;
import com.android.cglib.dx.ssa.SsaInsn;
import com.android.cglib.dx.util.IntList;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class SsaRenamer implements Runnable {
    private static final boolean DEBUG = false;
    private int nextSsaReg;
    private final int ropRegCount;
    private final SsaMethod ssaMeth;
    private final ArrayList<LocalItem> ssaRegToLocalItems;
    private IntList ssaRegToRopReg;
    private final RegisterSpec[][] startsForBlocks;
    private int threshold;

    public class BlockRenamer implements SsaInsn.Visitor {
        private final SsaBasicBlock block;
        private final RegisterSpec[] currentMapping;
        public final SsaRenamer this$0;
        private final HashSet<SsaInsn> movesToKeep = new HashSet<>();
        private final HashMap<SsaInsn, SsaInsn> insnsToReplace = new HashMap<>();
        private final RenamingMapper mapper = new RenamingMapper(this);

        public class RenamingMapper extends RegisterMapper {
            public final BlockRenamer this$1;

            public RenamingMapper(BlockRenamer blockRenamer) {
                this.this$1 = blockRenamer;
            }

            @Override // com.android.cglib.dx.ssa.RegisterMapper
            public int getNewRegisterCount() {
                return this.this$1.this$0.nextSsaReg;
            }

            @Override // com.android.cglib.dx.ssa.RegisterMapper
            public RegisterSpec map(RegisterSpec registerSpec) {
                if (registerSpec == null) {
                    return null;
                }
                return registerSpec.withReg(this.this$1.currentMapping[registerSpec.getReg()].getReg());
            }
        }

        public BlockRenamer(SsaRenamer ssaRenamer, SsaBasicBlock ssaBasicBlock) {
            this.this$0 = ssaRenamer;
            this.block = ssaBasicBlock;
            this.currentMapping = ssaRenamer.startsForBlocks[ssaBasicBlock.getIndex()];
            ssaRenamer.startsForBlocks[ssaBasicBlock.getIndex()] = null;
        }

        private void addMapping(int i, RegisterSpec registerSpec) {
            int reg = registerSpec.getReg();
            LocalItem localItem = registerSpec.getLocalItem();
            RegisterSpec[] registerSpecArr = this.currentMapping;
            registerSpecArr[i] = registerSpec;
            for (int length = registerSpecArr.length - 1; length >= 0; length--) {
                if (reg == this.currentMapping[length].getReg()) {
                    this.currentMapping[length] = registerSpec;
                }
            }
            if (localItem == null) {
                return;
            }
            this.this$0.setNameForSsaReg(registerSpec);
            for (int length2 = this.currentMapping.length - 1; length2 >= 0; length2--) {
                RegisterSpec registerSpec2 = this.currentMapping[length2];
                if (reg != registerSpec2.getReg() && localItem.equals(registerSpec2.getLocalItem())) {
                    this.currentMapping[length2] = registerSpec2.withLocalItem(null);
                }
            }
        }

        private void updateSuccessorPhis() {
            PhiInsn.Visitor visitor = new PhiInsn.Visitor(this) { // from class: com.android.cglib.dx.ssa.SsaRenamer.BlockRenamer.1
                public final BlockRenamer this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.android.cglib.dx.ssa.PhiInsn.Visitor
                public void visitPhiInsn(PhiInsn phiInsn) {
                    int ropResultReg = phiInsn.getRopResultReg();
                    if (this.this$1.this$0.isBelowThresholdRegister(ropResultReg)) {
                        return;
                    }
                    RegisterSpec registerSpec = this.this$1.currentMapping[ropResultReg];
                    if (this.this$1.this$0.isVersionZeroRegister(registerSpec.getReg())) {
                        return;
                    }
                    phiInsn.addPhiOperand(registerSpec, this.this$1.block);
                }
            };
            BitSet successors = this.block.getSuccessors();
            int i = 0;
            while (true) {
                int iNextSetBit = successors.nextSetBit(i);
                if (iNextSetBit < 0) {
                    return;
                }
                this.this$0.ssaMeth.getBlocks().get(iNextSetBit).forEachPhiInsn(visitor);
                i = iNextSetBit + 1;
            }
        }

        public void process() {
            this.block.forEachInsn(this);
            updateSuccessorPhis();
            ArrayList<SsaInsn> insns = this.block.getInsns();
            boolean z = true;
            for (int size = insns.size() - 1; size >= 0; size--) {
                SsaInsn ssaInsn = insns.get(size);
                SsaInsn ssaInsn2 = this.insnsToReplace.get(ssaInsn);
                if (ssaInsn2 != null) {
                    insns.set(size, ssaInsn2);
                } else if (ssaInsn.isNormalMoveInsn() && !this.movesToKeep.contains(ssaInsn)) {
                    insns.remove(size);
                }
            }
            for (SsaBasicBlock ssaBasicBlock : this.block.getDomChildren()) {
                if (ssaBasicBlock != this.block) {
                    this.this$0.startsForBlocks[ssaBasicBlock.getIndex()] = z ? this.currentMapping : SsaRenamer.dupArray(this.currentMapping);
                    z = false;
                }
            }
        }

        public void processResultReg(SsaInsn ssaInsn) {
            RegisterSpec result = ssaInsn.getResult();
            if (result == null) {
                return;
            }
            int reg = result.getReg();
            if (this.this$0.isBelowThresholdRegister(reg)) {
                return;
            }
            ssaInsn.changeResultReg(this.this$0.nextSsaReg);
            addMapping(reg, ssaInsn.getResult());
            SsaRenamer.access$108(this.this$0);
        }

        @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn normalSsaInsn) {
            RegisterSpec result = normalSsaInsn.getResult();
            int reg = result.getReg();
            int reg2 = normalSsaInsn.getSources().get(0).getReg();
            normalSsaInsn.mapSourceRegisters(this.mapper);
            int reg3 = normalSsaInsn.getSources().get(0).getReg();
            LocalItem localItem = this.currentMapping[reg2].getLocalItem();
            LocalItem localItem2 = result.getLocalItem();
            if (localItem2 == null) {
                localItem2 = localItem;
            }
            LocalItem localForNewReg = this.this$0.getLocalForNewReg(reg3);
            boolean z = localForNewReg == null || localItem2 == null || localItem2.equals(localForNewReg);
            RegisterSpec registerSpecMakeLocalOptional = RegisterSpec.makeLocalOptional(reg3, result.getType(), localItem2);
            if (Optimizer.getPreserveLocals() && (!z || !SsaRenamer.equalsHandlesNulls(localItem2, localItem) || this.this$0.threshold != 0)) {
                if (!z || localItem != null || this.this$0.threshold != 0) {
                    processResultReg(normalSsaInsn);
                    this.movesToKeep.add(normalSsaInsn);
                    return;
                }
                this.insnsToReplace.put(normalSsaInsn, SsaInsn.makeFromRop(new PlainInsn(Rops.opMarkLocal(registerSpecMakeLocalOptional), SourcePosition.NO_INFO, (RegisterSpec) null, RegisterSpecList.make(RegisterSpec.make(registerSpecMakeLocalOptional.getReg(), registerSpecMakeLocalOptional.getType(), localItem2))), this.block));
            }
            addMapping(reg, registerSpecMakeLocalOptional);
        }

        @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn normalSsaInsn) {
            normalSsaInsn.mapSourceRegisters(this.mapper);
            processResultReg(normalSsaInsn);
        }

        @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn phiInsn) {
            processResultReg(phiInsn);
        }
    }

    public SsaRenamer(SsaMethod ssaMethod) {
        int regCount = ssaMethod.getRegCount();
        this.ropRegCount = regCount;
        this.ssaMeth = ssaMethod;
        this.nextSsaReg = regCount;
        this.threshold = 0;
        this.startsForBlocks = new RegisterSpec[ssaMethod.getBlocks().size()][];
        this.ssaRegToLocalItems = new ArrayList<>();
        RegisterSpec[] registerSpecArr = new RegisterSpec[regCount];
        for (int i = 0; i < this.ropRegCount; i++) {
            registerSpecArr[i] = RegisterSpec.make(i, Type.VOID);
        }
        this.startsForBlocks[ssaMethod.getEntryBlockIndex()] = registerSpecArr;
    }

    public SsaRenamer(SsaMethod ssaMethod, int i) {
        this(ssaMethod);
        this.threshold = i;
    }

    public static /* synthetic */ int access$108(SsaRenamer ssaRenamer) {
        int i = ssaRenamer.nextSsaReg;
        ssaRenamer.nextSsaReg = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RegisterSpec[] dupArray(RegisterSpec[] registerSpecArr) {
        RegisterSpec[] registerSpecArr2 = new RegisterSpec[registerSpecArr.length];
        System.arraycopy(registerSpecArr, 0, registerSpecArr2, 0, registerSpecArr.length);
        return registerSpecArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean equalsHandlesNulls(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocalItem getLocalForNewReg(int i) {
        if (i < this.ssaRegToLocalItems.size()) {
            return this.ssaRegToLocalItems.get(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBelowThresholdRegister(int i) {
        return i < this.threshold;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVersionZeroRegister(int i) {
        return i < this.ropRegCount;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameForSsaReg(RegisterSpec registerSpec) {
        int reg = registerSpec.getReg();
        LocalItem localItem = registerSpec.getLocalItem();
        this.ssaRegToLocalItems.ensureCapacity(reg + 1);
        while (this.ssaRegToLocalItems.size() <= reg) {
            this.ssaRegToLocalItems.add(null);
        }
        this.ssaRegToLocalItems.set(reg, localItem);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.ssaMeth.forEachBlockDepthFirstDom(new SsaBasicBlock.Visitor(this) { // from class: com.android.cglib.dx.ssa.SsaRenamer.1
            public final SsaRenamer this$0;

            {
                this.this$0 = this;
            }

            @Override // com.android.cglib.dx.ssa.SsaBasicBlock.Visitor
            public void visitBlock(SsaBasicBlock ssaBasicBlock, SsaBasicBlock ssaBasicBlock2) {
                new BlockRenamer(this.this$0, ssaBasicBlock).process();
            }
        });
        this.ssaMeth.setNewRegCount(this.nextSsaReg);
        this.ssaMeth.onInsnsChanged();
    }
}

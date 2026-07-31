package com.android.cglib.dx.ssa.back;

import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.ssa.PhiInsn;
import com.android.cglib.dx.ssa.SsaBasicBlock;
import com.android.cglib.dx.ssa.SsaInsn;
import com.android.cglib.dx.ssa.SsaMethod;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LivenessAnalyzer {
    private SsaBasicBlock blockN;
    private final InterferenceGraph interference;
    private final BitSet liveOutBlocks;
    private NextFunction nextFunction;
    private final int regV;
    private final SsaMethod ssaMeth;
    private int statementIndex;
    private final BitSet visitedBlocks;

    /* JADX INFO: renamed from: com.android.cglib.dx.ssa.back.LivenessAnalyzer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final int[] $SwitchMap$com$android$cglib$dx$ssa$back$LivenessAnalyzer$NextFunction;

        static {
            NextFunction.values();
            int[] iArr = new int[4];
            $SwitchMap$com$android$cglib$dx$ssa$back$LivenessAnalyzer$NextFunction = iArr;
            try {
                NextFunction nextFunction = NextFunction.LIVE_IN_AT_STATEMENT;
                iArr[0] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                int[] iArr2 = $SwitchMap$com$android$cglib$dx$ssa$back$LivenessAnalyzer$NextFunction;
                NextFunction nextFunction2 = NextFunction.LIVE_OUT_AT_STATEMENT;
                iArr2[1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                int[] iArr3 = $SwitchMap$com$android$cglib$dx$ssa$back$LivenessAnalyzer$NextFunction;
                NextFunction nextFunction3 = NextFunction.LIVE_OUT_AT_BLOCK;
                iArr3[2] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum NextFunction {
        LIVE_IN_AT_STATEMENT,
        LIVE_OUT_AT_STATEMENT,
        LIVE_OUT_AT_BLOCK,
        DONE
    }

    private LivenessAnalyzer(SsaMethod ssaMethod, int i, InterferenceGraph interferenceGraph) {
        int size = ssaMethod.getBlocks().size();
        this.ssaMeth = ssaMethod;
        this.regV = i;
        this.visitedBlocks = new BitSet(size);
        this.liveOutBlocks = new BitSet(size);
        this.interference = interferenceGraph;
    }

    private static void coInterferePhis(SsaMethod ssaMethod, InterferenceGraph interferenceGraph) {
        Iterator<SsaBasicBlock> it = ssaMethod.getBlocks().iterator();
        while (it.hasNext()) {
            List<SsaInsn> phiInsns = it.next().getPhiInsns();
            int size = phiInsns.size();
            for (int i = 0; i < size; i++) {
                for (int i2 = 0; i2 < size; i2++) {
                    if (i != i2) {
                        interferenceGraph.add(phiInsns.get(i).getResult().getReg(), phiInsns.get(i2).getResult().getReg());
                    }
                }
            }
        }
    }

    public static InterferenceGraph constructInterferenceGraph(SsaMethod ssaMethod) {
        int regCount = ssaMethod.getRegCount();
        InterferenceGraph interferenceGraph = new InterferenceGraph(regCount);
        for (int i = 0; i < regCount; i++) {
            new LivenessAnalyzer(ssaMethod, i, interferenceGraph).run();
        }
        coInterferePhis(ssaMethod, interferenceGraph);
        return interferenceGraph;
    }

    private void handleTailRecursion() {
        while (true) {
            NextFunction nextFunction = this.nextFunction;
            NextFunction nextFunction2 = NextFunction.DONE;
            if (nextFunction == nextFunction2) {
                return;
            }
            int iOrdinal = nextFunction.ordinal();
            if (iOrdinal == 0) {
                this.nextFunction = nextFunction2;
                liveInAtStatement();
            } else if (iOrdinal == 1) {
                this.nextFunction = nextFunction2;
                liveOutAtStatement();
            } else if (iOrdinal == 2) {
                this.nextFunction = nextFunction2;
                liveOutAtBlock();
            }
        }
    }

    private void liveInAtStatement() {
        int i = this.statementIndex;
        if (i != 0) {
            this.statementIndex = i - 1;
            this.nextFunction = NextFunction.LIVE_OUT_AT_STATEMENT;
        } else {
            this.blockN.addLiveIn(this.regV);
            this.liveOutBlocks.or(this.blockN.getPredecessors());
        }
    }

    private void liveOutAtBlock() {
        if (this.visitedBlocks.get(this.blockN.getIndex())) {
            return;
        }
        this.visitedBlocks.set(this.blockN.getIndex());
        this.blockN.addLiveOut(this.regV);
        this.statementIndex = this.blockN.getInsns().size() - 1;
        this.nextFunction = NextFunction.LIVE_OUT_AT_STATEMENT;
    }

    private void liveOutAtStatement() {
        SsaInsn ssaInsn = this.blockN.getInsns().get(this.statementIndex);
        RegisterSpec result = ssaInsn.getResult();
        if (ssaInsn.isResultReg(this.regV)) {
            return;
        }
        if (result != null) {
            this.interference.add(this.regV, result.getReg());
        }
        this.nextFunction = NextFunction.LIVE_IN_AT_STATEMENT;
    }

    public void run() {
        for (SsaInsn ssaInsn : this.ssaMeth.getUseListForRegister(this.regV)) {
            this.nextFunction = NextFunction.DONE;
            if (ssaInsn instanceof PhiInsn) {
                Iterator<SsaBasicBlock> it = ((PhiInsn) ssaInsn).predBlocksForReg(this.regV, this.ssaMeth).iterator();
                while (it.hasNext()) {
                    this.blockN = it.next();
                    this.nextFunction = NextFunction.LIVE_OUT_AT_BLOCK;
                    handleTailRecursion();
                }
            } else {
                SsaBasicBlock block = ssaInsn.getBlock();
                this.blockN = block;
                int iIndexOf = block.getInsns().indexOf(ssaInsn);
                this.statementIndex = iIndexOf;
                if (iIndexOf < 0) {
                    throw new RuntimeException("insn not found in it's own block");
                }
                this.nextFunction = NextFunction.LIVE_IN_AT_STATEMENT;
                handleTailRecursion();
            }
        }
        while (true) {
            int iNextSetBit = this.liveOutBlocks.nextSetBit(0);
            if (iNextSetBit < 0) {
                return;
            }
            this.blockN = this.ssaMeth.getBlocks().get(iNextSetBit);
            this.liveOutBlocks.clear(iNextSetBit);
            this.nextFunction = NextFunction.LIVE_OUT_AT_BLOCK;
            handleTailRecursion();
        }
    }
}

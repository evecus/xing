package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.LocalItem;
import com.android.cglib.dx.rop.code.PlainCstInsn;
import com.android.cglib.dx.rop.code.PlainInsn;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.Rop;
import com.android.cglib.dx.rop.code.Rops;
import com.android.cglib.dx.rop.code.SourcePosition;
import com.android.cglib.dx.rop.code.ThrowingCstInsn;
import com.android.cglib.dx.rop.cst.TypedConstant;
import com.android.cglib.dx.rop.type.StdTypeList;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ConstCollector {
    private static boolean COLLECT_ONE_LOCAL = false;
    private static boolean COLLECT_STRINGS = false;
    private static final int MAX_COLLECTED_CONSTANTS = 5;
    private final SsaMethod ssaMeth;

    private ConstCollector(SsaMethod ssaMethod) {
        this.ssaMeth = ssaMethod;
    }

    private void fixLocalAssignment(RegisterSpec registerSpec, RegisterSpec registerSpec2) {
        for (SsaInsn ssaInsn : this.ssaMeth.getUseListForRegister(registerSpec.getReg())) {
            RegisterSpec localAssignment = ssaInsn.getLocalAssignment();
            if (localAssignment != null && ssaInsn.getResult() != null) {
                LocalItem localItem = localAssignment.getLocalItem();
                ssaInsn.setResultLocal(null);
                registerSpec2 = registerSpec2.withLocalItem(localItem);
                SsaInsn ssaInsnMakeFromRop = SsaInsn.makeFromRop(new PlainInsn(Rops.opMarkLocal(registerSpec2), SourcePosition.NO_INFO, (RegisterSpec) null, RegisterSpecList.make(registerSpec2)), ssaInsn.getBlock());
                ArrayList<SsaInsn> insns = ssaInsn.getBlock().getInsns();
                insns.add(insns.indexOf(ssaInsn) + 1, ssaInsnMakeFromRop);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.ArrayList<com.android.cglib.dx.rop.cst.TypedConstant> getConstsSortedByCountUse() {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.ssa.ConstCollector.getConstsSortedByCountUse():java.util.ArrayList");
    }

    public static void process(SsaMethod ssaMethod) {
        new ConstCollector(ssaMethod).run();
    }

    private void run() {
        ArrayList<TypedConstant> arrayList;
        int i;
        SsaBasicBlock ssaBasicBlock;
        int regCount = this.ssaMeth.getRegCount();
        ArrayList<TypedConstant> constsSortedByCountUse = getConstsSortedByCountUse();
        int iMin = Math.min(constsSortedByCountUse.size(), 5);
        SsaBasicBlock entryBlock = this.ssaMeth.getEntryBlock();
        HashMap<TypedConstant, RegisterSpec> map = new HashMap<>(iMin);
        int i2 = 0;
        while (i2 < iMin) {
            TypedConstant typedConstant = constsSortedByCountUse.get(i2);
            RegisterSpec registerSpecMake = RegisterSpec.make(this.ssaMeth.makeNewSsaReg(), typedConstant);
            Rop ropOpConst = Rops.opConst(typedConstant);
            if (ropOpConst.getBranchingness() == 1) {
                entryBlock.addInsnToHead(new PlainCstInsn(Rops.opConst(typedConstant), SourcePosition.NO_INFO, registerSpecMake, RegisterSpecList.EMPTY, typedConstant));
                arrayList = constsSortedByCountUse;
                i = iMin;
                ssaBasicBlock = entryBlock;
            } else {
                SsaBasicBlock entryBlock2 = this.ssaMeth.getEntryBlock();
                SsaBasicBlock primarySuccessor = entryBlock2.getPrimarySuccessor();
                SsaBasicBlock ssaBasicBlockInsertNewSuccessor = entryBlock2.insertNewSuccessor(primarySuccessor);
                SourcePosition sourcePosition = SourcePosition.NO_INFO;
                RegisterSpecList registerSpecList = RegisterSpecList.EMPTY;
                arrayList = constsSortedByCountUse;
                i = iMin;
                ssaBasicBlock = entryBlock;
                ssaBasicBlockInsertNewSuccessor.replaceLastInsn(new ThrowingCstInsn(ropOpConst, sourcePosition, registerSpecList, StdTypeList.EMPTY, typedConstant));
                ssaBasicBlockInsertNewSuccessor.insertNewSuccessor(primarySuccessor).addInsnToHead(new PlainInsn(Rops.opMoveResultPseudo(registerSpecMake.getTypeBearer()), sourcePosition, registerSpecMake, registerSpecList));
            }
            map.put(typedConstant, registerSpecMake);
            i2++;
            entryBlock = ssaBasicBlock;
            constsSortedByCountUse = arrayList;
            iMin = i;
        }
        updateConstUses(map, regCount);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateConstUses(java.util.HashMap<com.android.cglib.dx.rop.cst.TypedConstant, com.android.cglib.dx.rop.code.RegisterSpec> r9, int r10) {
        /*
            r8 = this;
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            com.android.cglib.dx.ssa.SsaMethod r1 = r8.ssaMeth
            java.util.ArrayList[] r1 = r1.getUseListCopy()
            r2 = 0
        Lc:
            if (r2 >= r10) goto L88
            com.android.cglib.dx.ssa.SsaMethod r3 = r8.ssaMeth
            com.android.cglib.dx.ssa.SsaInsn r3 = r3.getDefinitionForRegister(r2)
            if (r3 != 0) goto L17
            goto L85
        L17:
            com.android.cglib.dx.rop.code.RegisterSpec r4 = r3.getResult()
            com.android.cglib.dx.rop.code.RegisterSpec r3 = r3.getResult()
            com.android.cglib.dx.rop.type.TypeBearer r3 = r3.getTypeBearer()
            boolean r5 = r3.isConstant()
            if (r5 == 0) goto L85
            com.android.cglib.dx.rop.cst.TypedConstant r3 = (com.android.cglib.dx.rop.cst.TypedConstant) r3
            java.lang.Object r5 = r9.get(r3)
            com.android.cglib.dx.rop.code.RegisterSpec r5 = (com.android.cglib.dx.rop.code.RegisterSpec) r5
            if (r5 == 0) goto L85
            com.android.cglib.dx.ssa.SsaMethod r6 = r8.ssaMeth
            boolean r6 = r6.isRegALocal(r4)
            if (r6 == 0) goto L51
            boolean r6 = com.android.cglib.dx.ssa.ConstCollector.COLLECT_ONE_LOCAL
            if (r6 == 0) goto L85
            boolean r6 = r0.contains(r3)
            if (r6 != 0) goto L85
            r0.add(r3)
            java.lang.Object r3 = r9.get(r3)
            com.android.cglib.dx.rop.code.RegisterSpec r3 = (com.android.cglib.dx.rop.code.RegisterSpec) r3
            r8.fixLocalAssignment(r4, r3)
        L51:
            com.android.cglib.dx.ssa.ConstCollector$2 r3 = new com.android.cglib.dx.ssa.ConstCollector$2
            r3.<init>(r8, r4, r5)
            int r4 = r4.getReg()
            r4 = r1[r4]
            java.util.Iterator r4 = r4.iterator()
        L60:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L85
            java.lang.Object r5 = r4.next()
            com.android.cglib.dx.ssa.SsaInsn r5 = (com.android.cglib.dx.ssa.SsaInsn) r5
            boolean r6 = r5.canThrow()
            if (r6 == 0) goto L81
            com.android.cglib.dx.ssa.SsaBasicBlock r6 = r5.getBlock()
            java.util.BitSet r6 = r6.getSuccessors()
            int r6 = r6.cardinality()
            r7 = 1
            if (r6 > r7) goto L60
        L81:
            r5.mapSourceRegisters(r3)
            goto L60
        L85:
            int r2 = r2 + 1
            goto Lc
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.ssa.ConstCollector.updateConstUses(java.util.HashMap, int):void");
    }
}

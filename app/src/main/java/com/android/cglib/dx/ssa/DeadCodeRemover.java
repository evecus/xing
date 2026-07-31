package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.ssa.SsaInsn;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class DeadCodeRemover {
    private final int regCount;
    private final SsaMethod ssaMeth;
    private final ArrayList<SsaInsn>[] useList;
    private final BitSet worklist;

    public static class NoSideEffectVisitor implements SsaInsn.Visitor {
        public BitSet noSideEffectRegs;

        public NoSideEffectVisitor(BitSet bitSet) {
            this.noSideEffectRegs = bitSet;
        }

        @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn normalSsaInsn) {
            if (DeadCodeRemover.hasSideEffect(normalSsaInsn)) {
                return;
            }
            this.noSideEffectRegs.set(normalSsaInsn.getResult().getReg());
        }

        @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn normalSsaInsn) {
            RegisterSpec result = normalSsaInsn.getResult();
            if (DeadCodeRemover.hasSideEffect(normalSsaInsn) || result == null) {
                return;
            }
            this.noSideEffectRegs.set(result.getReg());
        }

        @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn phiInsn) {
            if (DeadCodeRemover.hasSideEffect(phiInsn)) {
                return;
            }
            this.noSideEffectRegs.set(phiInsn.getResult().getReg());
        }
    }

    private DeadCodeRemover(SsaMethod ssaMethod) {
        this.ssaMeth = ssaMethod;
        int regCount = ssaMethod.getRegCount();
        this.regCount = regCount;
        this.worklist = new BitSet(regCount);
        this.useList = ssaMethod.getUseListCopy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean hasSideEffect(SsaInsn ssaInsn) {
        if (ssaInsn == null) {
            return true;
        }
        return ssaInsn.hasSideEffect();
    }

    private boolean isCircularNoSideEffect(int i, BitSet bitSet) {
        if (bitSet != null && bitSet.get(i)) {
            return true;
        }
        Iterator<SsaInsn> it = this.useList[i].iterator();
        while (true) {
            if (!it.hasNext()) {
                if (bitSet == null) {
                    bitSet = new BitSet(this.regCount);
                }
                bitSet.set(i);
                Iterator<SsaInsn> it2 = this.useList[i].iterator();
                while (it2.hasNext()) {
                    RegisterSpec result = it2.next().getResult();
                    if (result == null || !isCircularNoSideEffect(result.getReg(), bitSet)) {
                    }
                }
                return true;
            }
            if (hasSideEffect(it.next())) {
                break;
            }
        }
        return false;
    }

    public static void process(SsaMethod ssaMethod) {
        new DeadCodeRemover(ssaMethod).run();
    }

    private void pruneDeadInstructions() {
        HashSet hashSet = new HashSet();
        this.ssaMeth.computeReachability();
        for (SsaBasicBlock ssaBasicBlock : this.ssaMeth.getBlocks()) {
            if (!ssaBasicBlock.isReachable()) {
                for (int i = 0; i < ssaBasicBlock.getInsns().size(); i++) {
                    SsaInsn ssaInsn = ssaBasicBlock.getInsns().get(i);
                    RegisterSpecList sources = ssaInsn.getSources();
                    int size = sources.size();
                    if (size != 0) {
                        hashSet.add(ssaInsn);
                    }
                    for (int i2 = 0; i2 < size; i2++) {
                        this.useList[sources.get(i2).getReg()].remove(ssaInsn);
                    }
                    RegisterSpec result = ssaInsn.getResult();
                    if (result != null) {
                        for (SsaInsn ssaInsn2 : this.useList[result.getReg()]) {
                            if (ssaInsn2 instanceof PhiInsn) {
                                ((PhiInsn) ssaInsn2).removePhiRegister(result);
                            }
                        }
                    }
                }
            }
        }
        this.ssaMeth.deleteInsns(hashSet);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void run() {
        pruneDeadInstructions();
        HashSet hashSet = new HashSet();
        this.ssaMeth.forEachInsn(new NoSideEffectVisitor(this.worklist));
        while (true) {
            int iNextSetBit = this.worklist.nextSetBit(0);
            if (iNextSetBit < 0) {
                this.ssaMeth.deleteInsns(hashSet);
                return;
            }
            this.worklist.clear(iNextSetBit);
            if (this.useList[iNextSetBit].size() == 0 || isCircularNoSideEffect(iNextSetBit, null)) {
                SsaInsn definitionForRegister = this.ssaMeth.getDefinitionForRegister(iNextSetBit);
                if (!hashSet.contains(definitionForRegister)) {
                    RegisterSpecList sources = definitionForRegister.getSources();
                    int size = sources.size();
                    for (int i = 0; i < size; i++) {
                        RegisterSpec registerSpec = sources.get(i);
                        this.useList[registerSpec.getReg()].remove(definitionForRegister);
                        if (!hasSideEffect(this.ssaMeth.getDefinitionForRegister(registerSpec.getReg()))) {
                            this.worklist.set(registerSpec.getReg());
                        }
                    }
                    hashSet.add(definitionForRegister);
                }
            }
        }
    }
}

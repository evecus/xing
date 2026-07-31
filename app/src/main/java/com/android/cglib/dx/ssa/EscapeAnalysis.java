package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.Exceptions;
import com.android.cglib.dx.rop.code.FillArrayDataInsn;
import com.android.cglib.dx.rop.code.Insn;
import com.android.cglib.dx.rop.code.PlainCstInsn;
import com.android.cglib.dx.rop.code.PlainInsn;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.Rop;
import com.android.cglib.dx.rop.code.Rops;
import com.android.cglib.dx.rop.code.ThrowingCstInsn;
import com.android.cglib.dx.rop.code.ThrowingInsn;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstLiteralBits;
import com.android.cglib.dx.rop.cst.CstMethodRef;
import com.android.cglib.dx.rop.cst.CstNat;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.rop.cst.TypedConstant;
import com.android.cglib.dx.rop.cst.Zeroes;
import com.android.cglib.dx.rop.type.StdTypeList;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeBearer;
import com.android.cglib.dx.ssa.SsaBasicBlock;
import com.android.cglib.dx.ssa.SsaInsn;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class EscapeAnalysis {
    private ArrayList<EscapeSet> latticeValues = new ArrayList<>();
    private int regCount;
    private SsaMethod ssaMeth;

    /* JADX INFO: renamed from: com.android.cglib.dx.ssa.EscapeAnalysis$2, reason: invalid class name */
    public class AnonymousClass2 implements SsaBasicBlock.Visitor {
        public final EscapeAnalysis this$0;

        public AnonymousClass2(EscapeAnalysis escapeAnalysis) {
            this.this$0 = escapeAnalysis;
        }

        @Override // com.android.cglib.dx.ssa.SsaBasicBlock.Visitor
        public void visitBlock(SsaBasicBlock ssaBasicBlock, SsaBasicBlock ssaBasicBlock2) {
            ssaBasicBlock.forEachInsn(new SsaInsn.Visitor(this) { // from class: com.android.cglib.dx.ssa.EscapeAnalysis.2.1
                public final AnonymousClass2 this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
                public void visitMoveInsn(NormalSsaInsn normalSsaInsn) {
                }

                @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
                public void visitNonMoveInsn(NormalSsaInsn normalSsaInsn) {
                    this.this$1.this$0.processInsn(normalSsaInsn);
                }

                @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
                public void visitPhiInsn(PhiInsn phiInsn) {
                }
            });
        }
    }

    public static class EscapeSet {
        public ArrayList<EscapeSet> childSets;
        public EscapeState escape;
        public ArrayList<EscapeSet> parentSets;
        public BitSet regSet;
        public boolean replaceableArray;

        public EscapeSet(int i, int i2, EscapeState escapeState) {
            BitSet bitSet = new BitSet(i2);
            this.regSet = bitSet;
            bitSet.set(i);
            this.escape = escapeState;
            this.childSets = new ArrayList<>();
            this.parentSets = new ArrayList<>();
            this.replaceableArray = false;
        }
    }

    public enum EscapeState {
        TOP,
        NONE,
        METHOD,
        INTER,
        GLOBAL
    }

    private EscapeAnalysis(SsaMethod ssaMethod) {
        this.ssaMeth = ssaMethod;
        this.regCount = ssaMethod.getRegCount();
    }

    private void addEdge(EscapeSet escapeSet, EscapeSet escapeSet2) {
        if (!escapeSet2.parentSets.contains(escapeSet)) {
            escapeSet2.parentSets.add(escapeSet);
        }
        if (escapeSet.childSets.contains(escapeSet2)) {
            return;
        }
        escapeSet.childSets.add(escapeSet2);
    }

    private int findSetIndex(RegisterSpec registerSpec) {
        int i = 0;
        while (i < this.latticeValues.size() && !this.latticeValues.get(i).regSet.get(registerSpec.getReg())) {
            i++;
        }
        return i;
    }

    private SsaInsn getInsnForMove(SsaInsn ssaInsn) {
        return this.ssaMeth.getBlocks().get(ssaInsn.getBlock().getPredecessors().nextSetBit(0)).getInsns().get(r2.size() - 1);
    }

    private SsaInsn getMoveForInsn(SsaInsn ssaInsn) {
        return this.ssaMeth.getBlocks().get(ssaInsn.getBlock().getSuccessors().nextSetBit(0)).getInsns().get(0);
    }

    private void insertExceptionThrow(SsaInsn ssaInsn, RegisterSpec registerSpec, HashSet<SsaInsn> hashSet) {
        CstType cstType = new CstType(Exceptions.TYPE_ArrayIndexOutOfBoundsException);
        RegisterSpecList registerSpecList = RegisterSpecList.EMPTY;
        insertThrowingInsnBefore(ssaInsn, registerSpecList, null, 40, cstType);
        SsaBasicBlock block = ssaInsn.getBlock();
        SsaBasicBlock ssaBasicBlockInsertNewSuccessor = block.insertNewSuccessor(block.getPrimarySuccessor());
        SsaInsn ssaInsn2 = ssaBasicBlockInsertNewSuccessor.getInsns().get(0);
        RegisterSpec registerSpecMake = RegisterSpec.make(this.ssaMeth.makeNewSsaReg(), cstType);
        insertPlainInsnBefore(ssaInsn2, registerSpecList, registerSpecMake, 56, null);
        SsaBasicBlock ssaBasicBlockInsertNewSuccessor2 = ssaBasicBlockInsertNewSuccessor.insertNewSuccessor(ssaBasicBlockInsertNewSuccessor.getPrimarySuccessor());
        SsaInsn ssaInsn3 = ssaBasicBlockInsertNewSuccessor2.getInsns().get(0);
        insertThrowingInsnBefore(ssaInsn3, RegisterSpecList.make(registerSpecMake, registerSpec), null, 52, new CstMethodRef(cstType, new CstNat(new CstString("<init>"), new CstString("(I)V"))));
        hashSet.add(ssaInsn3);
        SsaBasicBlock ssaBasicBlockInsertNewSuccessor3 = ssaBasicBlockInsertNewSuccessor2.insertNewSuccessor(ssaBasicBlockInsertNewSuccessor2.getPrimarySuccessor());
        SsaInsn ssaInsn4 = ssaBasicBlockInsertNewSuccessor3.getInsns().get(0);
        insertThrowingInsnBefore(ssaInsn4, RegisterSpecList.make(registerSpecMake), null, 35, null);
        ssaBasicBlockInsertNewSuccessor3.replaceSuccessor(ssaBasicBlockInsertNewSuccessor3.getPrimarySuccessorIndex(), this.ssaMeth.getExitBlock().getIndex());
        hashSet.add(ssaInsn4);
    }

    private void insertPlainInsnBefore(SsaInsn ssaInsn, RegisterSpecList registerSpecList, RegisterSpec registerSpec, int i, Constant constant) {
        Insn originalRopInsn = ssaInsn.getOriginalRopInsn();
        Rop ropOpMoveResultPseudo = i == 56 ? Rops.opMoveResultPseudo(registerSpec.getType()) : Rops.ropFor(i, registerSpec, registerSpecList, constant);
        NormalSsaInsn normalSsaInsn = new NormalSsaInsn(constant == null ? new PlainInsn(ropOpMoveResultPseudo, originalRopInsn.getPosition(), registerSpec, registerSpecList) : new PlainCstInsn(ropOpMoveResultPseudo, originalRopInsn.getPosition(), registerSpec, registerSpecList, constant), ssaInsn.getBlock());
        ArrayList<SsaInsn> insns = ssaInsn.getBlock().getInsns();
        insns.add(insns.lastIndexOf(ssaInsn), normalSsaInsn);
        this.ssaMeth.onInsnAdded(normalSsaInsn);
    }

    private void insertThrowingInsnBefore(SsaInsn ssaInsn, RegisterSpecList registerSpecList, RegisterSpec registerSpec, int i, Constant constant) {
        Insn originalRopInsn = ssaInsn.getOriginalRopInsn();
        Rop ropRopFor = Rops.ropFor(i, registerSpec, registerSpecList, constant);
        NormalSsaInsn normalSsaInsn = new NormalSsaInsn(constant == null ? new ThrowingInsn(ropRopFor, originalRopInsn.getPosition(), registerSpecList, StdTypeList.EMPTY) : new ThrowingCstInsn(ropRopFor, originalRopInsn.getPosition(), registerSpecList, StdTypeList.EMPTY, constant), ssaInsn.getBlock());
        ArrayList<SsaInsn> insns = ssaInsn.getBlock().getInsns();
        insns.add(insns.lastIndexOf(ssaInsn), normalSsaInsn);
        this.ssaMeth.onInsnAdded(normalSsaInsn);
    }

    private void movePropagate() {
        for (int i = 0; i < this.ssaMeth.getRegCount(); i++) {
            SsaInsn definitionForRegister = this.ssaMeth.getDefinitionForRegister(i);
            if (definitionForRegister != null && definitionForRegister.getOpcode() != null && definitionForRegister.getOpcode().getOpcode() == 2) {
                ArrayList<SsaInsn>[] useListCopy = this.ssaMeth.getUseListCopy();
                RegisterSpec registerSpec = definitionForRegister.getSources().get(0);
                RegisterSpec result = definitionForRegister.getResult();
                if (registerSpec.getReg() >= this.regCount || result.getReg() >= this.regCount) {
                    RegisterMapper registerMapper = new RegisterMapper(this, result, registerSpec) { // from class: com.android.cglib.dx.ssa.EscapeAnalysis.1
                        public final EscapeAnalysis this$0;
                        public final RegisterSpec val$result;
                        public final RegisterSpec val$source;

                        {
                            this.this$0 = this;
                            this.val$result = result;
                            this.val$source = registerSpec;
                        }

                        @Override // com.android.cglib.dx.ssa.RegisterMapper
                        public int getNewRegisterCount() {
                            return this.this$0.ssaMeth.getRegCount();
                        }

                        @Override // com.android.cglib.dx.ssa.RegisterMapper
                        public RegisterSpec map(RegisterSpec registerSpec2) {
                            return registerSpec2.getReg() == this.val$result.getReg() ? this.val$source : registerSpec2;
                        }
                    };
                    Iterator<SsaInsn> it = useListCopy[result.getReg()].iterator();
                    while (it.hasNext()) {
                        it.next().mapSourceRegisters(registerMapper);
                    }
                }
            }
        }
    }

    public static void process(SsaMethod ssaMethod) {
        new EscapeAnalysis(ssaMethod).run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processInsn(SsaInsn ssaInsn) {
        EscapeSet escapeSet;
        int opcode = ssaInsn.getOpcode().getOpcode();
        RegisterSpec result = ssaInsn.getResult();
        if (opcode == 56 && result.getTypeBearer().getBasicType() == 9) {
            escapeSet = processMoveResultPseudoInsn(ssaInsn);
        } else {
            if (opcode == 3 && result.getTypeBearer().getBasicType() == 9) {
                escapeSet = new EscapeSet(result.getReg(), this.regCount, EscapeState.NONE);
            } else if (opcode != 55 || result.getTypeBearer().getBasicType() != 9) {
                return;
            } else {
                escapeSet = new EscapeSet(result.getReg(), this.regCount, EscapeState.NONE);
            }
            this.latticeValues.add(escapeSet);
        }
        processRegister(result, escapeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.android.cglib.dx.ssa.EscapeAnalysis.EscapeSet processMoveResultPseudoInsn(com.android.cglib.dx.ssa.SsaInsn r5) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.ssa.EscapeAnalysis.processMoveResultPseudoInsn(com.android.cglib.dx.ssa.SsaInsn):com.android.cglib.dx.ssa.EscapeAnalysis$EscapeSet");
    }

    private void processPhiUse(SsaInsn ssaInsn, EscapeSet escapeSet, ArrayList<RegisterSpec> arrayList) {
        int iFindSetIndex = findSetIndex(ssaInsn.getResult());
        if (iFindSetIndex == this.latticeValues.size()) {
            escapeSet.regSet.set(ssaInsn.getResult().getReg());
            arrayList.add(ssaInsn.getResult());
            return;
        }
        EscapeSet escapeSet2 = this.latticeValues.get(iFindSetIndex);
        if (escapeSet2 != escapeSet) {
            escapeSet.replaceableArray = false;
            escapeSet.regSet.or(escapeSet2.regSet);
            if (escapeSet.escape.compareTo(escapeSet2.escape) < 0) {
                escapeSet.escape = escapeSet2.escape;
            }
            replaceNode(escapeSet, escapeSet2);
            this.latticeValues.remove(iFindSetIndex);
        }
    }

    private void processRegister(RegisterSpec registerSpec, EscapeSet escapeSet) {
        ArrayList<RegisterSpec> arrayList = new ArrayList<>();
        arrayList.add(registerSpec);
        while (!arrayList.isEmpty()) {
            RegisterSpec registerSpecRemove = arrayList.remove(arrayList.size() - 1);
            for (SsaInsn ssaInsn : this.ssaMeth.getUseListForRegister(registerSpecRemove.getReg())) {
                if (ssaInsn.getOpcode() == null) {
                    processPhiUse(ssaInsn, escapeSet, arrayList);
                } else {
                    processUse(registerSpecRemove, ssaInsn, escapeSet, arrayList);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void processUse(com.android.cglib.dx.rop.code.RegisterSpec r5, com.android.cglib.dx.ssa.SsaInsn r6, com.android.cglib.dx.ssa.EscapeAnalysis.EscapeSet r7, java.util.ArrayList<com.android.cglib.dx.rop.code.RegisterSpec> r8) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.ssa.EscapeAnalysis.processUse(com.android.cglib.dx.rop.code.RegisterSpec, com.android.cglib.dx.ssa.SsaInsn, com.android.cglib.dx.ssa.EscapeAnalysis$EscapeSet, java.util.ArrayList):void");
    }

    private void replaceDef(SsaInsn ssaInsn, SsaInsn ssaInsn2, int i, ArrayList<RegisterSpec> arrayList) {
        Type type = ssaInsn.getResult().getType();
        for (int i2 = 0; i2 < i; i2++) {
            Constant constantZeroFor = Zeroes.zeroFor(type.getComponentType());
            RegisterSpec registerSpecMake = RegisterSpec.make(this.ssaMeth.makeNewSsaReg(), (TypedConstant) constantZeroFor);
            arrayList.add(registerSpecMake);
            insertPlainInsnBefore(ssaInsn, RegisterSpecList.EMPTY, registerSpecMake, 5, constantZeroFor);
        }
    }

    private void replaceNode(EscapeSet escapeSet, EscapeSet escapeSet2) {
        for (EscapeSet escapeSet3 : escapeSet2.parentSets) {
            escapeSet3.childSets.remove(escapeSet2);
            escapeSet3.childSets.add(escapeSet);
            escapeSet.parentSets.add(escapeSet3);
        }
        for (EscapeSet escapeSet4 : escapeSet2.childSets) {
            escapeSet4.parentSets.remove(escapeSet2);
            escapeSet4.parentSets.add(escapeSet);
            escapeSet.childSets.add(escapeSet4);
        }
    }

    private void replaceUse(SsaInsn ssaInsn, SsaInsn ssaInsn2, ArrayList<RegisterSpec> arrayList, HashSet<SsaInsn> hashSet) {
        int size = arrayList.size();
        int opcode = ssaInsn.getOpcode().getOpcode();
        if (opcode == 34) {
            Object typeBearer = ssaInsn2.getSources().get(0).getTypeBearer();
            SsaInsn moveForInsn = getMoveForInsn(ssaInsn);
            insertPlainInsnBefore(moveForInsn, RegisterSpecList.EMPTY, moveForInsn.getResult(), 5, (Constant) typeBearer);
            hashSet.add(moveForInsn);
            return;
        }
        if (opcode == 57) {
            ArrayList<Constant> initValues = ((FillArrayDataInsn) ssaInsn.getOriginalRopInsn()).getInitValues();
            for (int i = 0; i < size; i++) {
                RegisterSpec registerSpecMake = RegisterSpec.make(arrayList.get(i).getReg(), (TypeBearer) initValues.get(i));
                insertPlainInsnBefore(ssaInsn, RegisterSpecList.EMPTY, registerSpecMake, 5, initValues.get(i));
                arrayList.set(i, registerSpecMake);
            }
            return;
        }
        if (opcode == 38) {
            SsaInsn moveForInsn2 = getMoveForInsn(ssaInsn);
            RegisterSpecList sources = ssaInsn.getSources();
            int intBits = ((CstLiteralBits) sources.get(1).getTypeBearer()).getIntBits();
            if (intBits < size) {
                RegisterSpec registerSpec = arrayList.get(intBits);
                insertPlainInsnBefore(moveForInsn2, RegisterSpecList.make(registerSpec), registerSpec.withReg(moveForInsn2.getResult().getReg()), 2, null);
            } else {
                insertExceptionThrow(moveForInsn2, sources.get(1), hashSet);
                hashSet.add(moveForInsn2.getBlock().getInsns().get(2));
            }
            hashSet.add(moveForInsn2);
            return;
        }
        if (opcode != 39) {
            return;
        }
        RegisterSpecList sources2 = ssaInsn.getSources();
        int intBits2 = ((CstLiteralBits) sources2.get(2).getTypeBearer()).getIntBits();
        if (intBits2 >= size) {
            insertExceptionThrow(ssaInsn, sources2.get(2), hashSet);
            return;
        }
        RegisterSpec registerSpec2 = sources2.get(0);
        RegisterSpec registerSpecWithReg = registerSpec2.withReg(arrayList.get(intBits2).getReg());
        insertPlainInsnBefore(ssaInsn, RegisterSpecList.make(registerSpec2), registerSpecWithReg, 2, null);
        arrayList.set(intBits2, registerSpecWithReg.withSimpleType());
    }

    private void run() {
        this.ssaMeth.forEachBlockDepthFirstDom(new AnonymousClass2(this));
        for (EscapeSet escapeSet : this.latticeValues) {
            if (escapeSet.escape != EscapeState.NONE) {
                for (EscapeSet escapeSet2 : escapeSet.childSets) {
                    if (escapeSet.escape.compareTo(escapeSet2.escape) > 0) {
                        escapeSet2.escape = escapeSet.escape;
                    }
                }
            }
        }
        scalarReplacement();
    }

    private void scalarReplacement() {
        for (EscapeSet escapeSet : this.latticeValues) {
            if (escapeSet.replaceableArray && escapeSet.escape == EscapeState.NONE) {
                int iNextSetBit = escapeSet.regSet.nextSetBit(0);
                SsaInsn definitionForRegister = this.ssaMeth.getDefinitionForRegister(iNextSetBit);
                SsaInsn insnForMove = getInsnForMove(definitionForRegister);
                int intBits = ((CstLiteralBits) insnForMove.getSources().get(0).getTypeBearer()).getIntBits();
                ArrayList<RegisterSpec> arrayList = new ArrayList<>(intBits);
                HashSet<SsaInsn> hashSet = new HashSet<>();
                replaceDef(definitionForRegister, insnForMove, intBits, arrayList);
                hashSet.add(insnForMove);
                hashSet.add(definitionForRegister);
                for (SsaInsn ssaInsn : this.ssaMeth.getUseListForRegister(iNextSetBit)) {
                    replaceUse(ssaInsn, insnForMove, arrayList, hashSet);
                    hashSet.add(ssaInsn);
                }
                this.ssaMeth.deleteInsns(hashSet);
                this.ssaMeth.onInsnsChanged();
                SsaConverter.updateSsaMethod(this.ssaMeth, this.regCount);
                movePropagate();
            }
        }
    }
}

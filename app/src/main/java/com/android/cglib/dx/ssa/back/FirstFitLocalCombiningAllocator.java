package com.android.cglib.dx.ssa.back;

import com.android.cglib.dx.rop.code.CstInsn;
import com.android.cglib.dx.rop.code.LocalItem;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.Rop;
import com.android.cglib.dx.rop.cst.CstInteger;
import com.android.cglib.dx.ssa.InterferenceRegisterMapper;
import com.android.cglib.dx.ssa.NormalSsaInsn;
import com.android.cglib.dx.ssa.Optimizer;
import com.android.cglib.dx.ssa.PhiInsn;
import com.android.cglib.dx.ssa.RegisterMapper;
import com.android.cglib.dx.ssa.SsaInsn;
import com.android.cglib.dx.ssa.SsaMethod;
import com.android.cglib.dx.util.IntIterator;
import com.android.cglib.dx.util.IntSet;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class FirstFitLocalCombiningAllocator extends RegisterAllocator {
    private static final boolean DEBUG = false;
    private final ArrayList<NormalSsaInsn> invokeRangeInsns;
    private final Map<LocalItem, ArrayList<RegisterSpec>> localVariables;
    private final InterferenceRegisterMapper mapper;
    private final boolean minimizeRegisters;
    private final ArrayList<NormalSsaInsn> moveResultPseudoInsns;
    private final int paramRangeEnd;
    private final ArrayList<PhiInsn> phiInsns;
    private final BitSet reservedRopRegs;
    private final BitSet ssaRegsMapped;
    private final BitSet usedRopRegs;

    public static class Multiset {
        private final int[] count;
        private final int[] reg;
        private int size = 0;

        public Multiset(int i) {
            this.reg = new int[i];
            this.count = new int[i];
        }

        public void add(int i) {
            int i2 = 0;
            while (true) {
                int i3 = this.size;
                if (i2 >= i3) {
                    this.reg[i3] = i;
                    this.count[i3] = 1;
                    this.size = i3 + 1;
                    return;
                } else {
                    if (this.reg[i2] == i) {
                        int[] iArr = this.count;
                        iArr[i2] = iArr[i2] + 1;
                        return;
                    }
                    i2++;
                }
            }
        }

        public int getAndRemoveHighestCount() {
            int i = -1;
            int i2 = -1;
            int i3 = 0;
            for (int i4 = 0; i4 < this.size; i4++) {
                int i5 = this.count[i4];
                if (i3 < i5) {
                    i2 = this.reg[i4];
                    i = i4;
                    i3 = i5;
                }
            }
            this.count[i] = 0;
            return i2;
        }

        public int getSize() {
            return this.size;
        }
    }

    public FirstFitLocalCombiningAllocator(SsaMethod ssaMethod, InterferenceGraph interferenceGraph, boolean z) {
        super(ssaMethod, interferenceGraph);
        this.ssaRegsMapped = new BitSet(ssaMethod.getRegCount());
        this.mapper = new InterferenceRegisterMapper(interferenceGraph, ssaMethod.getRegCount());
        this.minimizeRegisters = z;
        int paramWidth = ssaMethod.getParamWidth();
        this.paramRangeEnd = paramWidth;
        int i = paramWidth * 2;
        BitSet bitSet = new BitSet(i);
        this.reservedRopRegs = bitSet;
        bitSet.set(0, paramWidth);
        this.usedRopRegs = new BitSet(i);
        this.localVariables = new TreeMap();
        this.moveResultPseudoInsns = new ArrayList<>();
        this.invokeRangeInsns = new ArrayList<>();
        this.phiInsns = new ArrayList<>();
    }

    private void addMapping(RegisterSpec registerSpec, int i) {
        int reg = registerSpec.getReg();
        if (this.ssaRegsMapped.get(reg) || !canMapReg(registerSpec, i)) {
            throw new RuntimeException("attempt to add invalid register mapping");
        }
        int category = registerSpec.getCategory();
        this.mapper.addMapping(registerSpec.getReg(), i, category);
        this.ssaRegsMapped.set(reg);
        this.usedRopRegs.set(i, category + i);
    }

    private void adjustAndMapSourceRangeRange(NormalSsaInsn normalSsaInsn) {
        int iFindRangeAndAdjust = findRangeAndAdjust(normalSsaInsn);
        RegisterSpecList sources = normalSsaInsn.getSources();
        int size = sources.size();
        int i = 0;
        while (i < size) {
            RegisterSpec registerSpec = sources.get(i);
            int reg = registerSpec.getReg();
            int category = registerSpec.getCategory();
            if (!this.ssaRegsMapped.get(reg)) {
                LocalItem localItemForReg = getLocalItemForReg(reg);
                addMapping(registerSpec, iFindRangeAndAdjust);
                if (localItemForReg != null) {
                    markReserved(iFindRangeAndAdjust, category);
                    ArrayList<RegisterSpec> arrayList = this.localVariables.get(localItemForReg);
                    int size2 = arrayList.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        RegisterSpec registerSpec2 = arrayList.get(i2);
                        if (-1 == sources.indexOfRegister(registerSpec2.getReg())) {
                            tryMapReg(registerSpec2, iFindRangeAndAdjust, category);
                        }
                    }
                }
            }
            i++;
            iFindRangeAndAdjust += category;
        }
    }

    private void analyzeInstructions() {
        this.ssaMeth.forEachInsn(new SsaInsn.Visitor(this) { // from class: com.android.cglib.dx.ssa.back.FirstFitLocalCombiningAllocator.1
            public final FirstFitLocalCombiningAllocator this$0;

            {
                this.this$0 = this;
            }

            private void processInsn(SsaInsn ssaInsn) {
                ArrayList arrayList;
                Cloneable cloneable;
                RegisterSpec localAssignment = ssaInsn.getLocalAssignment();
                if (localAssignment != null) {
                    LocalItem localItem = localAssignment.getLocalItem();
                    ArrayList arrayList2 = (ArrayList) this.this$0.localVariables.get(localItem);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        this.this$0.localVariables.put(localItem, arrayList2);
                    }
                    arrayList2.add(localAssignment);
                }
                if (ssaInsn instanceof NormalSsaInsn) {
                    if (ssaInsn.getOpcode().getOpcode() == 56) {
                        arrayList = this.this$0.moveResultPseudoInsns;
                    } else if (!Optimizer.getAdvice().requiresSourcesInOrder(ssaInsn.getOriginalRopInsn().getOpcode(), ssaInsn.getSources())) {
                        return;
                    } else {
                        arrayList = this.this$0.invokeRangeInsns;
                    }
                    cloneable = (NormalSsaInsn) ssaInsn;
                } else {
                    if (!(ssaInsn instanceof PhiInsn)) {
                        return;
                    }
                    arrayList = this.this$0.phiInsns;
                    cloneable = (PhiInsn) ssaInsn;
                }
                arrayList.add(cloneable);
            }

            @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
            public void visitMoveInsn(NormalSsaInsn normalSsaInsn) {
                processInsn(normalSsaInsn);
            }

            @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
            public void visitNonMoveInsn(NormalSsaInsn normalSsaInsn) {
                processInsn(normalSsaInsn);
            }

            @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
            public void visitPhiInsn(PhiInsn phiInsn) {
                processInsn(phiInsn);
            }
        });
    }

    private boolean canMapReg(RegisterSpec registerSpec, int i) {
        return (spansParamRange(i, registerSpec.getCategory()) || this.mapper.interferes(registerSpec, i)) ? false : true;
    }

    private boolean canMapRegs(ArrayList<RegisterSpec> arrayList, int i) {
        for (RegisterSpec registerSpec : arrayList) {
            if (!this.ssaRegsMapped.get(registerSpec.getReg()) && !canMapReg(registerSpec, i)) {
                return false;
            }
        }
        return true;
    }

    private int findAnyFittingRange(NormalSsaInsn normalSsaInsn, int i, int[] iArr, BitSet bitSet) {
        int i2 = this.paramRangeEnd;
        while (true) {
            int iFindNextUnreservedRopReg = findNextUnreservedRopReg(i2, i);
            if (fitPlanForRange(iFindNextUnreservedRopReg, normalSsaInsn, iArr, bitSet) >= 0) {
                return iFindNextUnreservedRopReg;
            }
            i2 = iFindNextUnreservedRopReg + 1;
            bitSet.clear();
        }
    }

    private int findNextUnreservedRopReg(int i, int i2) {
        int iNextClearBit = this.reservedRopRegs.nextClearBit(i);
        while (true) {
            int i3 = 1;
            while (i3 < i2 && !this.reservedRopRegs.get(iNextClearBit + i3)) {
                i3++;
            }
            if (i3 == i2) {
                return iNextClearBit;
            }
            iNextClearBit = this.reservedRopRegs.nextClearBit(iNextClearBit + i3);
        }
    }

    private int findRangeAndAdjust(NormalSsaInsn normalSsaInsn) {
        int iOldToNew;
        BitSet bitSet;
        int iFitPlanForRange;
        RegisterSpecList sources = normalSsaInsn.getSources();
        int size = sources.size();
        int[] iArr = new int[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int category = sources.get(i2).getCategory();
            iArr[i2] = category;
            i += category;
        }
        int i3 = Integer.MIN_VALUE;
        BitSet bitSet2 = null;
        int iFindAnyFittingRange = -1;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            int reg = sources.get(i5).getReg();
            if (i5 != 0) {
                i4 -= iArr[i5 - 1];
            }
            if (this.ssaRegsMapped.get(reg) && (iOldToNew = this.mapper.oldToNew(reg) + i4) >= 0 && !spansParamRange(iOldToNew, i) && (iFitPlanForRange = fitPlanForRange(iOldToNew, normalSsaInsn, iArr, (bitSet = new BitSet(size)))) >= 0) {
                int iCardinality = iFitPlanForRange - bitSet.cardinality();
                if (iCardinality > i3) {
                    i3 = iCardinality;
                    iFindAnyFittingRange = iOldToNew;
                    bitSet2 = bitSet;
                }
                if (iFitPlanForRange == i) {
                    break;
                }
            }
        }
        if (iFindAnyFittingRange == -1) {
            bitSet2 = new BitSet(size);
            iFindAnyFittingRange = findAnyFittingRange(normalSsaInsn, i, iArr, bitSet2);
        }
        int i6 = 0;
        while (true) {
            int iNextSetBit = bitSet2.nextSetBit(i6);
            if (iNextSetBit < 0) {
                return iFindAnyFittingRange;
            }
            normalSsaInsn.changeOneSource(iNextSetBit, insertMoveBefore(normalSsaInsn, sources.get(iNextSetBit)));
            i6 = iNextSetBit + 1;
        }
    }

    private int findRopRegForLocal(int i, int i2) {
        int iNextClearBit = this.usedRopRegs.nextClearBit(i);
        while (true) {
            int i3 = 1;
            while (i3 < i2 && !this.usedRopRegs.get(iNextClearBit + i3)) {
                i3++;
            }
            if (i3 == i2) {
                return iNextClearBit;
            }
            iNextClearBit = this.usedRopRegs.nextClearBit(iNextClearBit + i3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int fitPlanForRange(int r10, com.android.cglib.dx.ssa.NormalSsaInsn r11, int[] r12, java.util.BitSet r13) {
        /*
            r9 = this;
            com.android.cglib.dx.rop.code.RegisterSpecList r0 = r11.getSources()
            int r1 = r0.size()
            com.android.cglib.dx.ssa.SsaBasicBlock r11 = r11.getBlock()
            com.android.cglib.dx.util.IntSet r11 = r11.getLiveOutRegs()
            com.android.cglib.dx.rop.code.RegisterSpecList r11 = r9.ssaSetToSpecs(r11)
            java.util.BitSet r2 = new java.util.BitSet
            com.android.cglib.dx.ssa.SsaMethod r3 = r9.ssaMeth
            int r3 = r3.getRegCount()
            r2.<init>(r3)
            r3 = 0
            r4 = r3
        L21:
            if (r4 >= r1) goto L7d
            com.android.cglib.dx.rop.code.RegisterSpec r5 = r0.get(r4)
            int r6 = r5.getReg()
            r7 = r12[r4]
            if (r4 == 0) goto L34
            int r8 = r4 + (-1)
            r8 = r12[r8]
            int r10 = r10 + r8
        L34:
            java.util.BitSet r8 = r9.ssaRegsMapped
            boolean r8 = r8.get(r6)
            if (r8 == 0) goto L45
            com.android.cglib.dx.ssa.InterferenceRegisterMapper r8 = r9.mapper
            int r8 = r8.oldToNew(r6)
            if (r8 != r10) goto L45
            goto L61
        L45:
            boolean r8 = r9.rangeContainsReserved(r10, r7)
            if (r8 == 0) goto L4c
            goto L7c
        L4c:
            java.util.BitSet r8 = r9.ssaRegsMapped
            boolean r8 = r8.get(r6)
            if (r8 != 0) goto L63
            boolean r5 = r9.canMapReg(r5, r10)
            if (r5 == 0) goto L63
            boolean r5 = r2.get(r6)
            if (r5 == 0) goto L61
            goto L63
        L61:
            int r3 = r3 + r7
            goto L76
        L63:
            com.android.cglib.dx.ssa.InterferenceRegisterMapper r5 = r9.mapper
            boolean r5 = r5.areAnyPinned(r11, r10, r7)
            if (r5 != 0) goto L7c
            com.android.cglib.dx.ssa.InterferenceRegisterMapper r5 = r9.mapper
            boolean r5 = r5.areAnyPinned(r0, r10, r7)
            if (r5 != 0) goto L7c
            r13.set(r4)
        L76:
            r2.set(r6)
            int r4 = r4 + 1
            goto L21
        L7c:
            r3 = -1
        L7d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.ssa.back.FirstFitLocalCombiningAllocator.fitPlanForRange(int, com.android.cglib.dx.ssa.NormalSsaInsn, int[], java.util.BitSet):int");
    }

    private LocalItem getLocalItemForReg(int i) {
        for (Map.Entry<LocalItem, ArrayList<RegisterSpec>> entry : this.localVariables.entrySet()) {
            Iterator<RegisterSpec> it = entry.getValue().iterator();
            while (it.hasNext()) {
                if (it.next().getReg() == i) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    private int getParameterIndexForReg(int i) {
        Rop opcode;
        SsaInsn definitionForRegister = this.ssaMeth.getDefinitionForRegister(i);
        if (definitionForRegister == null || (opcode = definitionForRegister.getOpcode()) == null || opcode.getOpcode() != 3) {
            return -1;
        }
        return ((CstInteger) ((CstInsn) definitionForRegister.getOriginalRopInsn()).getConstant()).getValue();
    }

    private void handleCheckCastResults() {
        for (NormalSsaInsn normalSsaInsn : this.moveResultPseudoInsns) {
            RegisterSpec result = normalSsaInsn.getResult();
            int reg = result.getReg();
            BitSet predecessors = normalSsaInsn.getBlock().getPredecessors();
            if (predecessors.cardinality() == 1) {
                ArrayList<SsaInsn> insns = this.ssaMeth.getBlocks().get(predecessors.nextSetBit(0)).getInsns();
                SsaInsn ssaInsn = insns.get(insns.size() - 1);
                if (ssaInsn.getOpcode().getOpcode() == 43) {
                    RegisterSpec registerSpec = ssaInsn.getSources().get(0);
                    int reg2 = registerSpec.getReg();
                    int category = registerSpec.getCategory();
                    boolean zTryMapReg = this.ssaRegsMapped.get(reg);
                    boolean zTryMapReg2 = this.ssaRegsMapped.get(reg2);
                    if ((!zTryMapReg2) & zTryMapReg) {
                        zTryMapReg2 = tryMapReg(registerSpec, this.mapper.oldToNew(reg), category);
                    }
                    if ((!zTryMapReg) & zTryMapReg2) {
                        zTryMapReg = tryMapReg(result, this.mapper.oldToNew(reg2), category);
                    }
                    if (!zTryMapReg || !zTryMapReg2) {
                        int iFindNextUnreservedRopReg = findNextUnreservedRopReg(this.paramRangeEnd, category);
                        ArrayList<RegisterSpec> arrayList = new ArrayList<>(2);
                        arrayList.add(result);
                        arrayList.add(registerSpec);
                        while (!tryMapRegs(arrayList, iFindNextUnreservedRopReg, category, false)) {
                            iFindNextUnreservedRopReg = findNextUnreservedRopReg(iFindNextUnreservedRopReg + 1, category);
                        }
                    }
                    boolean z = ssaInsn.getOriginalRopInsn().getCatches().size() != 0;
                    int iOldToNew = this.mapper.oldToNew(reg);
                    if (iOldToNew != this.mapper.oldToNew(reg2) && !z) {
                        ((NormalSsaInsn) ssaInsn).changeOneSource(0, insertMoveBefore(ssaInsn, registerSpec));
                        addMapping(ssaInsn.getSources().get(0), iOldToNew);
                    }
                }
            }
        }
    }

    private void handleInvokeRangeInsns() {
        Iterator<NormalSsaInsn> it = this.invokeRangeInsns.iterator();
        while (it.hasNext()) {
            adjustAndMapSourceRangeRange(it.next());
        }
    }

    private void handleLocalAssociatedOther() {
        for (ArrayList<RegisterSpec> arrayList : this.localVariables.values()) {
            int i = this.paramRangeEnd;
            boolean zTryMapRegs = false;
            while (true) {
                int size = arrayList.size();
                int i2 = 1;
                for (int i3 = 0; i3 < size; i3++) {
                    RegisterSpec registerSpec = arrayList.get(i3);
                    int category = registerSpec.getCategory();
                    if (!this.ssaRegsMapped.get(registerSpec.getReg()) && category > i2) {
                        i2 = category;
                    }
                }
                int iFindRopRegForLocal = findRopRegForLocal(i, i2);
                if (canMapRegs(arrayList, iFindRopRegForLocal)) {
                    zTryMapRegs = tryMapRegs(arrayList, iFindRopRegForLocal, i2, true);
                }
                if (!zTryMapRegs) {
                    i = iFindRopRegForLocal + 1;
                }
            }
        }
    }

    private void handleLocalAssociatedParams() {
        for (ArrayList<RegisterSpec> arrayList : this.localVariables.values()) {
            int size = arrayList.size();
            int category = 0;
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                RegisterSpec registerSpec = arrayList.get(i2);
                int parameterIndexForReg = getParameterIndexForReg(registerSpec.getReg());
                if (parameterIndexForReg >= 0) {
                    category = registerSpec.getCategory();
                    addMapping(registerSpec, parameterIndexForReg);
                    i = parameterIndexForReg;
                    break;
                }
                i2++;
                i = parameterIndexForReg;
            }
            if (i >= 0) {
                tryMapRegs(arrayList, i, category, true);
            }
        }
    }

    private void handleNormalUnassociated() {
        RegisterSpec definitionSpecForSsaReg;
        int iFindNextUnreservedRopReg;
        int regCount = this.ssaMeth.getRegCount();
        for (int i = 0; i < regCount; i++) {
            if (!this.ssaRegsMapped.get(i) && (definitionSpecForSsaReg = getDefinitionSpecForSsaReg(i)) != null) {
                int category = definitionSpecForSsaReg.getCategory();
                int i2 = this.paramRangeEnd;
                while (true) {
                    iFindNextUnreservedRopReg = findNextUnreservedRopReg(i2, category);
                    if (canMapReg(definitionSpecForSsaReg, iFindNextUnreservedRopReg)) {
                        break;
                    } else {
                        i2 = iFindNextUnreservedRopReg + 1;
                    }
                }
                addMapping(definitionSpecForSsaReg, iFindNextUnreservedRopReg);
            }
        }
    }

    private void handlePhiInsns() {
        Iterator<PhiInsn> it = this.phiInsns.iterator();
        while (it.hasNext()) {
            processPhiInsn(it.next());
        }
    }

    private void handleUnassociatedParameters() {
        int regCount = this.ssaMeth.getRegCount();
        for (int i = 0; i < regCount; i++) {
            if (!this.ssaRegsMapped.get(i)) {
                int parameterIndexForReg = getParameterIndexForReg(i);
                RegisterSpec definitionSpecForSsaReg = getDefinitionSpecForSsaReg(i);
                if (parameterIndexForReg >= 0) {
                    addMapping(definitionSpecForSsaReg, parameterIndexForReg);
                }
            }
        }
    }

    private boolean isThisPointerReg(int i) {
        return i == 0 && !this.ssaMeth.isStatic();
    }

    private void markReserved(int i, int i2) {
        this.reservedRopRegs.set(i, i2 + i, true);
    }

    private void printLocalVars() {
        System.out.println("Printing local vars");
        for (Map.Entry<LocalItem, ArrayList<RegisterSpec>> entry : this.localVariables.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            sb.append(' ');
            for (RegisterSpec registerSpec : entry.getValue()) {
                sb.append('v');
                sb.append(registerSpec.getReg());
                sb.append(' ');
            }
            sb.append('}');
            System.out.printf("Local: %s Registers: %s\n", entry.getKey(), sb);
        }
    }

    private void processPhiInsn(PhiInsn phiInsn) {
        RegisterSpec result = phiInsn.getResult();
        int reg = result.getReg();
        int category = result.getCategory();
        RegisterSpecList sources = phiInsn.getSources();
        int size = sources.size();
        ArrayList<RegisterSpec> arrayList = new ArrayList<>();
        Multiset multiset = new Multiset(size + 1);
        if (this.ssaRegsMapped.get(reg)) {
            multiset.add(this.mapper.oldToNew(reg));
        } else {
            arrayList.add(result);
        }
        for (int i = 0; i < size; i++) {
            RegisterSpec result2 = this.ssaMeth.getDefinitionForRegister(sources.get(i).getReg()).getResult();
            int reg2 = result2.getReg();
            if (this.ssaRegsMapped.get(reg2)) {
                multiset.add(this.mapper.oldToNew(reg2));
            } else {
                arrayList.add(result2);
            }
        }
        for (int i2 = 0; i2 < multiset.getSize(); i2++) {
            tryMapRegs(arrayList, multiset.getAndRemoveHighestCount(), category, false);
        }
        int i3 = this.paramRangeEnd;
        while (true) {
            int iFindNextUnreservedRopReg = findNextUnreservedRopReg(i3, category);
            if (tryMapRegs(arrayList, iFindNextUnreservedRopReg, category, false)) {
                return;
            } else {
                i3 = iFindNextUnreservedRopReg + 1;
            }
        }
    }

    private boolean rangeContainsReserved(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            if (this.reservedRopRegs.get(i3)) {
                return true;
            }
        }
        return false;
    }

    private boolean spansParamRange(int i, int i2) {
        int i3 = this.paramRangeEnd;
        return i < i3 && i + i2 > i3;
    }

    private boolean tryMapReg(RegisterSpec registerSpec, int i, int i2) {
        if (registerSpec.getCategory() > i2 || this.ssaRegsMapped.get(registerSpec.getReg()) || !canMapReg(registerSpec, i)) {
            return false;
        }
        addMapping(registerSpec, i);
        return true;
    }

    private boolean tryMapRegs(ArrayList<RegisterSpec> arrayList, int i, int i2, boolean z) {
        boolean z2 = false;
        for (RegisterSpec registerSpec : arrayList) {
            if (!this.ssaRegsMapped.get(registerSpec.getReg())) {
                boolean zTryMapReg = tryMapReg(registerSpec, i, i2);
                z2 = !zTryMapReg || z2;
                if (zTryMapReg && z) {
                    markReserved(i, registerSpec.getCategory());
                }
            }
        }
        return !z2;
    }

    @Override // com.android.cglib.dx.ssa.back.RegisterAllocator
    public RegisterMapper allocateRegisters() {
        analyzeInstructions();
        handleLocalAssociatedParams();
        handleUnassociatedParameters();
        handleInvokeRangeInsns();
        handleLocalAssociatedOther();
        handleCheckCastResults();
        handlePhiInsns();
        handleNormalUnassociated();
        return this.mapper;
    }

    public RegisterSpecList ssaSetToSpecs(IntSet intSet) {
        RegisterSpecList registerSpecList = new RegisterSpecList(intSet.elements());
        IntIterator it = intSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            registerSpecList.set(i, getDefinitionSpecForSsaReg(it.next()));
            i++;
        }
        return registerSpecList;
    }

    @Override // com.android.cglib.dx.ssa.back.RegisterAllocator
    public boolean wantsParamsMovedHigh() {
        return true;
    }
}

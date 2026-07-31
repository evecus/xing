package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.dex.DexOptions;
import com.android.cglib.dx.dex.code.DalvCode;
import com.android.cglib.dx.rop.code.LocalItem;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.RegisterSpecSet;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstMemberRef;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.DexException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OutputFinisher {
    private final DexOptions dexOptions;
    private ArrayList<DalvInsn> insns;
    private final int unreservedRegCount;
    private int reservedCount = -1;
    private boolean hasAnyPositionInfo = false;
    private boolean hasAnyLocalInfo = false;

    public OutputFinisher(DexOptions dexOptions, int i, int i2) {
        this.dexOptions = dexOptions;
        this.unreservedRegCount = i2;
        this.insns = new ArrayList<>(i);
    }

    private static void addConstants(HashSet<Constant> hashSet, DalvInsn dalvInsn) {
        if (dalvInsn instanceof CstInsn) {
            hashSet.add(((CstInsn) dalvInsn).getConstant());
            return;
        }
        if (!(dalvInsn instanceof LocalSnapshot)) {
            if (dalvInsn instanceof LocalStart) {
                addConstants(hashSet, ((LocalStart) dalvInsn).getLocal());
            }
        } else {
            RegisterSpecSet locals = ((LocalSnapshot) dalvInsn).getLocals();
            int size = locals.size();
            for (int i = 0; i < size; i++) {
                addConstants(hashSet, locals.get(i));
            }
        }
    }

    private static void addConstants(HashSet<Constant> hashSet, RegisterSpec registerSpec) {
        if (registerSpec == null) {
            return;
        }
        LocalItem localItem = registerSpec.getLocalItem();
        CstString name = localItem.getName();
        CstString signature = localItem.getSignature();
        Type type = registerSpec.getType();
        if (type != Type.KNOWN_NULL) {
            hashSet.add(CstType.intern(type));
        }
        if (name != null) {
            hashSet.add(name);
        }
        if (signature != null) {
            hashSet.add(signature);
        }
    }

    private void assignAddresses() {
        int size = this.insns.size();
        int i = 0;
        int iCodeSize = 0;
        while (i < size) {
            DalvInsn dalvInsn = this.insns.get(i);
            dalvInsn.setAddress(iCodeSize);
            i++;
            iCodeSize += dalvInsn.codeSize();
        }
    }

    private void assignAddressesAndFixBranches() {
        do {
            assignAddresses();
        } while (fixBranches());
    }

    private static void assignIndices(CstInsn cstInsn, DalvCode.AssignIndicesCallback assignIndicesCallback) {
        int index;
        Constant constant = cstInsn.getConstant();
        int index2 = assignIndicesCallback.getIndex(constant);
        if (index2 >= 0) {
            cstInsn.setIndex(index2);
        }
        if (!(constant instanceof CstMemberRef) || (index = assignIndicesCallback.getIndex(((CstMemberRef) constant).getDefiningClass())) < 0) {
            return;
        }
        cstInsn.setClassIndex(index);
    }

    private int calculateReservedCount(Dop[] dopArr) {
        int size = this.insns.size();
        int i = this.reservedCount;
        for (int i2 = 0; i2 < size; i2++) {
            DalvInsn dalvInsn = this.insns.get(i2);
            Dop dop = dopArr[i2];
            Dop dopFindOpcodeForInsn = findOpcodeForInsn(dalvInsn, dop);
            if (dopFindOpcodeForInsn == null) {
                int minimumRegisterRequirement = dalvInsn.getMinimumRegisterRequirement(findExpandedOpcodeForInsn(dalvInsn).getFormat().compatibleRegs(dalvInsn));
                if (minimumRegisterRequirement > i) {
                    i = minimumRegisterRequirement;
                }
            } else {
                if (dop != dopFindOpcodeForInsn) {
                }
            }
            dopArr[i2] = dopFindOpcodeForInsn;
        }
        return i;
    }

    private Dop findExpandedOpcodeForInsn(DalvInsn dalvInsn) {
        Dop dopFindOpcodeForInsn = findOpcodeForInsn(dalvInsn.getLowRegVersion(), dalvInsn.getOpcode());
        if (dopFindOpcodeForInsn != null) {
            return dopFindOpcodeForInsn;
        }
        throw new DexException("No expanded opcode for " + dalvInsn);
    }

    private Dop findOpcodeForInsn(DalvInsn dalvInsn, Dop dop) {
        while (dop != null && !dop.getFormat().isCompatible(dalvInsn)) {
            dop = Dops.getNextOrNull(dop, this.dexOptions);
        }
        return dop;
    }

    private boolean fixBranches() {
        int size = this.insns.size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            DalvInsn dalvInsn = this.insns.get(i);
            if (dalvInsn instanceof TargetInsn) {
                Dop opcode = dalvInsn.getOpcode();
                TargetInsn targetInsn = (TargetInsn) dalvInsn;
                if (opcode.getFormat().branchFits(targetInsn)) {
                    continue;
                } else {
                    if (opcode.getFamily() == 40) {
                        Dop dopFindOpcodeForInsn = findOpcodeForInsn(dalvInsn, opcode);
                        if (dopFindOpcodeForInsn == null) {
                            throw new UnsupportedOperationException("method too long");
                        }
                        this.insns.set(i, dalvInsn.withOpcode(dopFindOpcodeForInsn));
                    } else {
                        try {
                            int i2 = i + 1;
                            CodeAddress codeAddress = (CodeAddress) this.insns.get(i2);
                            this.insns.set(i, new TargetInsn(Dops.GOTO, targetInsn.getPosition(), RegisterSpecList.EMPTY, targetInsn.getTarget()));
                            this.insns.add(i, targetInsn.withNewTargetAndReversed(codeAddress));
                            size++;
                            i = i2;
                        } catch (ClassCastException e) {
                            throw new IllegalStateException("unpaired TargetInsn");
                        } catch (IndexOutOfBoundsException e2) {
                            throw new IllegalStateException("unpaired TargetInsn (dangling)");
                        }
                    }
                    z = true;
                }
            }
            i++;
        }
        return z;
    }

    private static boolean hasLocalInfo(DalvInsn dalvInsn) {
        if (dalvInsn instanceof LocalSnapshot) {
            RegisterSpecSet locals = ((LocalSnapshot) dalvInsn).getLocals();
            int size = locals.size();
            for (int i = 0; i < size; i++) {
                if (!hasLocalInfo(locals.get(i))) {
                }
            }
            return false;
        }
        if (!(dalvInsn instanceof LocalStart) || !hasLocalInfo(((LocalStart) dalvInsn).getLocal())) {
            return false;
        }
        return true;
    }

    private static boolean hasLocalInfo(RegisterSpec registerSpec) {
        return (registerSpec == null || registerSpec.getLocalItem().getName() == null) ? false : true;
    }

    private Dop[] makeOpcodesArray() {
        int size = this.insns.size();
        Dop[] dopArr = new Dop[size];
        for (int i = 0; i < size; i++) {
            dopArr[i] = this.insns.get(i).getOpcode();
        }
        return dopArr;
    }

    private void massageInstructions(Dop[] dopArr) {
        if (this.reservedCount != 0) {
            this.insns = performExpansion(dopArr);
            return;
        }
        int size = this.insns.size();
        for (int i = 0; i < size; i++) {
            DalvInsn dalvInsn = this.insns.get(i);
            Dop opcode = dalvInsn.getOpcode();
            Dop dop = dopArr[i];
            if (opcode != dop) {
                this.insns.set(i, dalvInsn.withOpcode(dop));
            }
        }
    }

    private ArrayList<DalvInsn> performExpansion(Dop[] dopArr) {
        DalvInsn dalvInsnExpandedSuffix;
        DalvInsn dalvInsn;
        int size = this.insns.size();
        ArrayList<DalvInsn> arrayList = new ArrayList<>(size * 2);
        for (int i = 0; i < size; i++) {
            DalvInsn dalvInsnExpandedVersion = this.insns.get(i);
            Dop opcode = dalvInsnExpandedVersion.getOpcode();
            Dop dopFindExpandedOpcodeForInsn = dopArr[i];
            if (dopFindExpandedOpcodeForInsn != null) {
                dalvInsn = null;
                dalvInsnExpandedSuffix = null;
            } else {
                dopFindExpandedOpcodeForInsn = findExpandedOpcodeForInsn(dalvInsnExpandedVersion);
                BitSet bitSetCompatibleRegs = dopFindExpandedOpcodeForInsn.getFormat().compatibleRegs(dalvInsnExpandedVersion);
                DalvInsn dalvInsnExpandedPrefix = dalvInsnExpandedVersion.expandedPrefix(bitSetCompatibleRegs);
                dalvInsnExpandedSuffix = dalvInsnExpandedVersion.expandedSuffix(bitSetCompatibleRegs);
                dalvInsnExpandedVersion = dalvInsnExpandedVersion.expandedVersion(bitSetCompatibleRegs);
                dalvInsn = dalvInsnExpandedPrefix;
            }
            if (dalvInsn != null) {
                arrayList.add(dalvInsn);
            }
            if (dopFindExpandedOpcodeForInsn != opcode) {
                dalvInsnExpandedVersion = dalvInsnExpandedVersion.withOpcode(dopFindExpandedOpcodeForInsn);
            }
            arrayList.add(dalvInsnExpandedVersion);
            if (dalvInsnExpandedSuffix != null) {
                arrayList.add(dalvInsnExpandedSuffix);
            }
        }
        return arrayList;
    }

    private void reserveRegisters(Dop[] dopArr) {
        int i = this.reservedCount;
        if (i < 0) {
            i = 0;
        }
        while (true) {
            int iCalculateReservedCount = calculateReservedCount(dopArr);
            if (i >= iCalculateReservedCount) {
                this.reservedCount = i;
                return;
            }
            int size = this.insns.size();
            for (int i2 = 0; i2 < size; i2++) {
                DalvInsn dalvInsn = this.insns.get(i2);
                if (!(dalvInsn instanceof CodeAddress)) {
                    this.insns.set(i2, dalvInsn.withRegisterOffset(iCalculateReservedCount - i));
                }
            }
            i = iCalculateReservedCount;
        }
    }

    private void updateInfo(DalvInsn dalvInsn) {
        if (!this.hasAnyPositionInfo && dalvInsn.getPosition().getLine() >= 0) {
            this.hasAnyPositionInfo = true;
        }
        if (this.hasAnyLocalInfo || !hasLocalInfo(dalvInsn)) {
            return;
        }
        this.hasAnyLocalInfo = true;
    }

    public void add(DalvInsn dalvInsn) {
        this.insns.add(dalvInsn);
        updateInfo(dalvInsn);
    }

    public void assignIndices(DalvCode.AssignIndicesCallback assignIndicesCallback) {
        for (DalvInsn dalvInsn : this.insns) {
            if (dalvInsn instanceof CstInsn) {
                assignIndices((CstInsn) dalvInsn, assignIndicesCallback);
            }
        }
    }

    public DalvInsnList finishProcessingAndGetList() {
        if (this.reservedCount >= 0) {
            throw new UnsupportedOperationException("already processed");
        }
        Dop[] dopArrMakeOpcodesArray = makeOpcodesArray();
        reserveRegisters(dopArrMakeOpcodesArray);
        massageInstructions(dopArrMakeOpcodesArray);
        assignAddressesAndFixBranches();
        return DalvInsnList.makeImmutable(this.insns, this.reservedCount + this.unreservedRegCount);
    }

    public HashSet<Constant> getAllConstants() {
        HashSet<Constant> hashSet = new HashSet<>(20);
        Iterator<DalvInsn> it = this.insns.iterator();
        while (it.hasNext()) {
            addConstants(hashSet, it.next());
        }
        return hashSet;
    }

    public boolean hasAnyLocalInfo() {
        return this.hasAnyLocalInfo;
    }

    public boolean hasAnyPositionInfo() {
        return this.hasAnyPositionInfo;
    }

    public void insert(int i, DalvInsn dalvInsn) {
        this.insns.add(i, dalvInsn);
        updateInfo(dalvInsn);
    }

    public void reverseBranch(int i, CodeAddress codeAddress) {
        int size = (this.insns.size() - i) - 1;
        try {
            this.insns.set(size, ((TargetInsn) this.insns.get(size)).withNewTargetAndReversed(codeAddress));
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("non-reversible instruction");
        } catch (IndexOutOfBoundsException e2) {
            throw new IllegalArgumentException("too few instructions");
        }
    }
}

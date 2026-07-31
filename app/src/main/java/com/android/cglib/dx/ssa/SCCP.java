package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.CstInsn;
import com.android.cglib.dx.rop.code.Insn;
import com.android.cglib.dx.rop.code.PlainInsn;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.Rops;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.TypedConstant;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class SCCP {
    private static final int CONSTANT = 1;
    private static final int TOP = 0;
    private static final int VARYING = 2;
    private ArrayList<SsaInsn> branchWorklist;
    private ArrayList<SsaBasicBlock> cfgPhiWorklist;
    private ArrayList<SsaBasicBlock> cfgWorklist;
    private BitSet executableBlocks;
    private Constant[] latticeConstants;
    private int[] latticeValues;
    private int regCount;
    private SsaMethod ssaMeth;
    private ArrayList<SsaInsn> ssaWorklist;
    private ArrayList<SsaInsn> varyingWorklist;

    private SCCP(SsaMethod ssaMethod) {
        this.ssaMeth = ssaMethod;
        int regCount = ssaMethod.getRegCount();
        this.regCount = regCount;
        this.latticeValues = new int[regCount];
        this.latticeConstants = new Constant[regCount];
        this.cfgWorklist = new ArrayList<>();
        this.cfgPhiWorklist = new ArrayList<>();
        this.executableBlocks = new BitSet(ssaMethod.getBlocks().size());
        this.ssaWorklist = new ArrayList<>();
        this.varyingWorklist = new ArrayList<>();
        this.branchWorklist = new ArrayList<>();
        for (int i = 0; i < this.regCount; i++) {
            this.latticeValues[i] = 0;
            this.latticeConstants[i] = null;
        }
    }

    private void addBlockToWorklist(SsaBasicBlock ssaBasicBlock) {
        if (this.executableBlocks.get(ssaBasicBlock.getIndex())) {
            this.cfgPhiWorklist.add(ssaBasicBlock);
        } else {
            this.cfgWorklist.add(ssaBasicBlock);
            this.executableBlocks.set(ssaBasicBlock.getIndex());
        }
    }

    private void addUsersToWorklist(int i, int i2) {
        if (i2 == 2) {
            Iterator<SsaInsn> it = this.ssaMeth.getUseListForRegister(i).iterator();
            while (it.hasNext()) {
                this.varyingWorklist.add(it.next());
            }
            return;
        }
        Iterator<SsaInsn> it2 = this.ssaMeth.getUseListForRegister(i).iterator();
        while (it2.hasNext()) {
            this.ssaWorklist.add(it2.next());
        }
    }

    private static String latticeValName(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "UNKNOWN" : "VARYING" : "CONSTANT" : "TOP";
    }

    public static void process(SsaMethod ssaMethod) {
        new SCCP(ssaMethod).run();
    }

    private void replaceBranches() {
        for (SsaInsn ssaInsn : this.branchWorklist) {
            SsaBasicBlock block = ssaInsn.getBlock();
            int size = block.getSuccessorList().size();
            int i = -1;
            for (int i2 = 0; i2 < size; i2++) {
                int i3 = block.getSuccessorList().get(i2);
                if (!this.executableBlocks.get(i3)) {
                    i = i3;
                }
            }
            if (size == 2 && i != -1) {
                block.replaceLastInsn(new PlainInsn(Rops.GOTO, ssaInsn.getOriginalRopInsn().getPosition(), (RegisterSpec) null, RegisterSpecList.EMPTY));
                block.removeSuccessor(i);
            }
        }
    }

    private void replaceConstants() {
        for (int i = 0; i < this.regCount; i++) {
            if (this.latticeValues[i] == 1 && (this.latticeConstants[i] instanceof TypedConstant)) {
                SsaInsn definitionForRegister = this.ssaMeth.getDefinitionForRegister(i);
                if (!definitionForRegister.getResult().getTypeBearer().isConstant()) {
                    definitionForRegister.setResult(definitionForRegister.getResult().withType((TypedConstant) this.latticeConstants[i]));
                    for (SsaInsn ssaInsn : this.ssaMeth.getUseListForRegister(i)) {
                        if (!ssaInsn.isPhiOrMove()) {
                            NormalSsaInsn normalSsaInsn = (NormalSsaInsn) ssaInsn;
                            RegisterSpecList sources = ssaInsn.getSources();
                            int iIndexOfRegister = sources.indexOfRegister(i);
                            normalSsaInsn.changeOneSource(iIndexOfRegister, sources.get(iIndexOfRegister).withType((TypedConstant) this.latticeConstants[i]));
                        }
                    }
                }
            }
        }
    }

    private void run() {
        addBlockToWorklist(this.ssaMeth.getEntryBlock());
        while (true) {
            if (this.cfgWorklist.isEmpty() && this.cfgPhiWorklist.isEmpty() && this.ssaWorklist.isEmpty() && this.varyingWorklist.isEmpty()) {
                replaceConstants();
                replaceBranches();
                return;
            }
            while (!this.cfgWorklist.isEmpty()) {
                simulateBlock(this.cfgWorklist.remove(this.cfgWorklist.size() - 1));
            }
            while (!this.cfgPhiWorklist.isEmpty()) {
                simulatePhiBlock(this.cfgPhiWorklist.remove(this.cfgPhiWorklist.size() - 1));
            }
            while (!this.varyingWorklist.isEmpty()) {
                SsaInsn ssaInsnRemove = this.varyingWorklist.remove(this.varyingWorklist.size() - 1);
                if (this.executableBlocks.get(ssaInsnRemove.getBlock().getIndex())) {
                    if (ssaInsnRemove instanceof PhiInsn) {
                        simulatePhi((PhiInsn) ssaInsnRemove);
                    } else {
                        simulateStmt(ssaInsnRemove);
                    }
                }
            }
            while (!this.ssaWorklist.isEmpty()) {
                SsaInsn ssaInsnRemove2 = this.ssaWorklist.remove(this.ssaWorklist.size() - 1);
                if (this.executableBlocks.get(ssaInsnRemove2.getBlock().getIndex())) {
                    if (ssaInsnRemove2 instanceof PhiInsn) {
                        simulatePhi((PhiInsn) ssaInsnRemove2);
                    } else {
                        simulateStmt(ssaInsnRemove2);
                    }
                }
            }
        }
    }

    private boolean setLatticeValueTo(int i, int i2, Constant constant) {
        if (i2 != 1) {
            int[] iArr = this.latticeValues;
            if (iArr[i] == i2) {
                return false;
            }
            iArr[i] = i2;
        } else {
            if (this.latticeValues[i] == i2 && this.latticeConstants[i].equals(constant)) {
                return false;
            }
            this.latticeValues[i] = i2;
            this.latticeConstants[i] = constant;
        }
        return true;
    }

    private void simulateBlock(SsaBasicBlock ssaBasicBlock) {
        for (SsaInsn ssaInsn : ssaBasicBlock.getInsns()) {
            if (ssaInsn instanceof PhiInsn) {
                simulatePhi((PhiInsn) ssaInsn);
            } else {
                simulateStmt(ssaInsn);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void simulateBranch(com.android.cglib.dx.ssa.SsaInsn r10) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.ssa.SCCP.simulateBranch(com.android.cglib.dx.ssa.SsaInsn):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.android.cglib.dx.rop.cst.Constant simulateMath(com.android.cglib.dx.ssa.SsaInsn r8, int r9) {
        /*
            r7 = this;
            com.android.cglib.dx.rop.code.Insn r0 = r8.getOriginalRopInsn()
            com.android.cglib.dx.rop.code.Rop r1 = r8.getOpcode()
            int r1 = r1.getOpcode()
            com.android.cglib.dx.rop.code.RegisterSpecList r8 = r8.getSources()
            r2 = 0
            com.android.cglib.dx.rop.code.RegisterSpec r3 = r8.get(r2)
            int r3 = r3.getReg()
            int[] r4 = r7.latticeValues
            r4 = r4[r3]
            r5 = 0
            r6 = 1
            if (r4 == r6) goto L23
            r3 = r5
            goto L27
        L23:
            com.android.cglib.dx.rop.cst.Constant[] r4 = r7.latticeConstants
            r3 = r4[r3]
        L27:
            int r4 = r8.size()
            if (r4 != r6) goto L34
            com.android.cglib.dx.rop.code.CstInsn r0 = (com.android.cglib.dx.rop.code.CstInsn) r0
            com.android.cglib.dx.rop.cst.Constant r0 = r0.getConstant()
            goto L48
        L34:
            com.android.cglib.dx.rop.code.RegisterSpec r0 = r8.get(r6)
            int r0 = r0.getReg()
            int[] r4 = r7.latticeValues
            r4 = r4[r0]
            if (r4 == r6) goto L44
            r0 = r5
            goto L48
        L44:
            com.android.cglib.dx.rop.cst.Constant[] r4 = r7.latticeConstants
            r0 = r4[r0]
        L48:
            if (r3 == 0) goto L9a
            if (r0 != 0) goto L4d
            goto L9a
        L4d:
            r4 = 6
            if (r9 != r4) goto L9a
            com.android.cglib.dx.rop.cst.CstInteger r3 = (com.android.cglib.dx.rop.cst.CstInteger) r3
            int r9 = r3.getValue()
            com.android.cglib.dx.rop.cst.CstInteger r0 = (com.android.cglib.dx.rop.cst.CstInteger) r0
            int r0 = r0.getValue()
            switch(r1) {
                case 14: goto L92;
                case 15: goto L87;
                case 16: goto L85;
                case 17: goto L7e;
                case 18: goto L79;
                case 19: goto L5f;
                case 20: goto L76;
                case 21: goto L73;
                case 22: goto L70;
                case 23: goto L6d;
                case 24: goto L6a;
                case 25: goto L67;
                default: goto L5f;
            }
        L5f:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r9 = "Unexpected op"
            r8.<init>(r9)
            throw r8
        L67:
            int r8 = r9 >>> r0
            goto L94
        L6a:
            int r8 = r9 >> r0
            goto L94
        L6d:
            int r8 = r9 << r0
            goto L94
        L70:
            r8 = r0 ^ r9
            goto L94
        L73:
            r8 = r0 | r9
            goto L94
        L76:
            r8 = r0 & r9
            goto L94
        L79:
            if (r0 != 0) goto L7c
            goto L82
        L7c:
            int r9 = r9 % r0
            goto L90
        L7e:
            if (r0 == 0) goto L82
            int r9 = r9 / r0
            goto L90
        L82:
            r8 = r2
            r2 = r6
            goto L94
        L85:
            int r0 = r0 * r9
            goto L93
        L87:
            int r8 = r8.size()
            if (r8 != r6) goto L8f
            int r0 = r0 - r9
            goto L93
        L8f:
            int r9 = r9 - r0
        L90:
            r8 = r9
            goto L94
        L92:
            int r0 = r0 + r9
        L93:
            r8 = r0
        L94:
            if (r2 != 0) goto L9a
            com.android.cglib.dx.rop.cst.CstInteger r5 = com.android.cglib.dx.rop.cst.CstInteger.make(r8)
        L9a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.ssa.SCCP.simulateMath(com.android.cglib.dx.ssa.SsaInsn, int):com.android.cglib.dx.rop.cst.Constant");
    }

    private void simulatePhi(PhiInsn phiInsn) {
        int reg = phiInsn.getResult().getReg();
        int i = 2;
        if (this.latticeValues[reg] == 2) {
            return;
        }
        RegisterSpecList sources = phiInsn.getSources();
        int size = sources.size();
        int i2 = 0;
        Constant constant = null;
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                i = i2;
                break;
            }
            int iPredBlockIndexForSourcesIndex = phiInsn.predBlockIndexForSourcesIndex(i3);
            int reg2 = sources.get(i3).getReg();
            int i4 = this.latticeValues[reg2];
            if (this.executableBlocks.get(iPredBlockIndexForSourcesIndex)) {
                if (i4 != 1) {
                    i = i4;
                    break;
                } else if (constant == null) {
                    constant = this.latticeConstants[reg2];
                    i2 = 1;
                } else if (!this.latticeConstants[reg2].equals(constant)) {
                    break;
                }
            }
            i3++;
        }
        if (setLatticeValueTo(reg, i, constant)) {
            addUsersToWorklist(reg, i);
        }
    }

    private void simulatePhiBlock(SsaBasicBlock ssaBasicBlock) {
        for (SsaInsn ssaInsn : ssaBasicBlock.getInsns()) {
            if (!(ssaInsn instanceof PhiInsn)) {
                return;
            } else {
                simulatePhi((PhiInsn) ssaInsn);
            }
        }
    }

    private void simulateStmt(SsaInsn ssaInsn) {
        Insn originalRopInsn = ssaInsn.getOriginalRopInsn();
        int i = 1;
        if (originalRopInsn.getOpcode().getBranchingness() != 1 || originalRopInsn.getOpcode().isCallLike()) {
            simulateBranch(ssaInsn);
        }
        int opcode = ssaInsn.getOpcode().getOpcode();
        RegisterSpec result = ssaInsn.getResult();
        if (result == null) {
            if (opcode != 17 && opcode != 18) {
                return;
            } else {
                result = ssaInsn.getBlock().getPrimarySuccessor().getInsns().get(0).getResult();
            }
        }
        int reg = result.getReg();
        Constant constant = null;
        if (opcode != 2) {
            if (opcode == 5) {
                constant = ((CstInsn) originalRopInsn).getConstant();
            } else if (opcode != 56) {
                switch (opcode) {
                    default:
                        switch (opcode) {
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                                break;
                            default:
                                i = 2;
                                break;
                        }
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        constant = simulateMath(ssaInsn, result.getBasicType());
                        if (constant == null) {
                            i = 2;
                        }
                        break;
                }
            } else {
                int i2 = this.latticeValues[reg];
                if (i2 == 1) {
                    constant = this.latticeConstants[reg];
                    i = i2;
                } else {
                    i = 2;
                }
            }
        } else if (ssaInsn.getSources().size() == 1) {
            int reg2 = ssaInsn.getSources().get(0).getReg();
            i = this.latticeValues[reg2];
            constant = this.latticeConstants[reg2];
        } else {
            i = 2;
        }
        if (setLatticeValueTo(reg, i, constant)) {
            addUsersToWorklist(reg, i);
        }
    }
}

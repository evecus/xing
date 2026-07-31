package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.Insn;
import com.android.cglib.dx.rop.code.LocalItem;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.Rop;
import com.android.cglib.dx.rop.code.SourcePosition;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeBearer;
import com.android.cglib.dx.ssa.SsaInsn;
import com.android.cglib.dx.util.Hex;
import com.baidu.android.common.util.HanziToPinyin;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PhiInsn extends SsaInsn {
    private final ArrayList<Operand> operands;
    private final int ropResultReg;
    private RegisterSpecList sources;

    public static class Operand {
        public final int blockIndex;
        public RegisterSpec regSpec;
        public final int ropLabel;

        public Operand(RegisterSpec registerSpec, int i, int i2) {
            this.regSpec = registerSpec;
            this.blockIndex = i;
            this.ropLabel = i2;
        }
    }

    public interface Visitor {
        void visitPhiInsn(PhiInsn phiInsn);
    }

    public PhiInsn(int i, SsaBasicBlock ssaBasicBlock) {
        super(RegisterSpec.make(i, Type.VOID), ssaBasicBlock);
        this.operands = new ArrayList<>();
        this.ropResultReg = i;
    }

    public PhiInsn(RegisterSpec registerSpec, SsaBasicBlock ssaBasicBlock) {
        super(registerSpec, ssaBasicBlock);
        this.operands = new ArrayList<>();
        this.ropResultReg = registerSpec.getReg();
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public void accept(SsaInsn.Visitor visitor) {
        visitor.visitPhiInsn(this);
    }

    public void addPhiOperand(RegisterSpec registerSpec, SsaBasicBlock ssaBasicBlock) {
        this.operands.add(new Operand(registerSpec, ssaBasicBlock.getIndex(), ssaBasicBlock.getRopLabel()));
        this.sources = null;
    }

    public boolean areAllOperandsEqual() {
        if (this.operands.size() == 0) {
            return true;
        }
        int reg = this.operands.get(0).regSpec.getReg();
        Iterator<Operand> it = this.operands.iterator();
        while (it.hasNext()) {
            if (reg != it.next().regSpec.getReg()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public boolean canThrow() {
        return false;
    }

    public void changeResultType(TypeBearer typeBearer, LocalItem localItem) {
        setResult(RegisterSpec.makeLocalOptional(getResult().getReg(), typeBearer, localItem));
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    /* JADX INFO: renamed from: clone */
    public PhiInsn mo6clone() {
        throw new UnsupportedOperationException("can't clone phi");
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public Rop getOpcode() {
        return null;
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public Insn getOriginalRopInsn() {
        return null;
    }

    public int getRopResultReg() {
        return this.ropResultReg;
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public RegisterSpecList getSources() {
        RegisterSpecList registerSpecList = this.sources;
        if (registerSpecList != null) {
            return registerSpecList;
        }
        if (this.operands.size() == 0) {
            return RegisterSpecList.EMPTY;
        }
        int size = this.operands.size();
        this.sources = new RegisterSpecList(size);
        for (int i = 0; i < size; i++) {
            this.sources.set(i, this.operands.get(i).regSpec);
        }
        this.sources.setImmutable();
        return this.sources;
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public boolean hasSideEffect() {
        return Optimizer.getPreserveLocals() && getLocalAssignment() != null;
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public boolean isPhiOrMove() {
        return true;
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public boolean isRegASource(int i) {
        Iterator<Operand> it = this.operands.iterator();
        while (it.hasNext()) {
            if (it.next().regSpec.getReg() == i) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public final void mapSourceRegisters(RegisterMapper registerMapper) {
        for (Operand operand : this.operands) {
            RegisterSpec registerSpec = operand.regSpec;
            RegisterSpec map = registerMapper.map(registerSpec);
            operand.regSpec = map;
            if (registerSpec != map) {
                getBlock().getParent().onSourceChanged(this, registerSpec, operand.regSpec);
            }
        }
        this.sources = null;
    }

    public int predBlockIndexForSourcesIndex(int i) {
        return this.operands.get(i).blockIndex;
    }

    public List<SsaBasicBlock> predBlocksForReg(int i, SsaMethod ssaMethod) {
        ArrayList arrayList = new ArrayList();
        for (Operand operand : this.operands) {
            if (operand.regSpec.getReg() == i) {
                arrayList.add(ssaMethod.getBlocks().get(operand.blockIndex));
            }
        }
        return arrayList;
    }

    public void removePhiRegister(RegisterSpec registerSpec) {
        ArrayList arrayList = new ArrayList();
        for (Operand operand : this.operands) {
            if (operand.regSpec.getReg() == registerSpec.getReg()) {
                arrayList.add(operand);
            }
        }
        this.operands.removeAll(arrayList);
        this.sources = null;
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return toHumanWithInline(null);
    }

    public final String toHumanWithInline(String str) {
        StringBuffer stringBuffer = new StringBuffer(80);
        stringBuffer.append(SourcePosition.NO_INFO);
        stringBuffer.append(": phi");
        if (str != null) {
            stringBuffer.append("(");
            stringBuffer.append(str);
            stringBuffer.append(")");
        }
        RegisterSpec result = getResult();
        if (result == null) {
            stringBuffer.append(" .");
        } else {
            stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
            stringBuffer.append(result.toHuman());
        }
        stringBuffer.append(" <-");
        int size = getSources().size();
        if (size == 0) {
            stringBuffer.append(" .");
        } else {
            for (int i = 0; i < size; i++) {
                stringBuffer.append(HanziToPinyin.Token.SEPARATOR);
                stringBuffer.append(this.sources.get(i).toHuman() + "[b=" + Hex.u2(this.operands.get(i).ropLabel) + "]");
            }
        }
        return stringBuffer.toString();
    }

    @Override // com.android.cglib.dx.ssa.SsaInsn
    public Insn toRopInsn() {
        throw new IllegalArgumentException("Cannot convert phi insns to rop form");
    }

    public void updateSourcesToDefinitions(SsaMethod ssaMethod) {
        for (Operand operand : this.operands) {
            operand.regSpec = operand.regSpec.withType(ssaMethod.getDefinitionForRegister(operand.regSpec.getReg()).getResult().getType());
        }
        this.sources = null;
    }
}

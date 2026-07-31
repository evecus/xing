package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.Insn;
import com.android.cglib.dx.rop.code.PlainCstInsn;
import com.android.cglib.dx.rop.code.PlainInsn;
import com.android.cglib.dx.rop.code.RegOps;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.code.Rop;
import com.android.cglib.dx.rop.code.Rops;
import com.android.cglib.dx.rop.code.TranslationAdvice;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstLiteralBits;
import com.android.cglib.dx.rop.type.TypeBearer;
import com.android.cglib.dx.ssa.SsaInsn;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class LiteralOpUpgrader {
    private final SsaMethod ssaMeth;

    private LiteralOpUpgrader(SsaMethod ssaMethod) {
        this.ssaMeth = ssaMethod;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isConstIntZeroOrKnownNull(RegisterSpec registerSpec) {
        TypeBearer typeBearer = registerSpec.getTypeBearer();
        return (typeBearer instanceof CstLiteralBits) && ((CstLiteralBits) typeBearer).getLongBits() == 0;
    }

    public static void process(SsaMethod ssaMethod) {
        new LiteralOpUpgrader(ssaMethod).run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replacePlainInsn(NormalSsaInsn normalSsaInsn, RegisterSpecList registerSpecList, int i, Constant constant) {
        Insn originalRopInsn = normalSsaInsn.getOriginalRopInsn();
        Rop ropRopFor = Rops.ropFor(i, normalSsaInsn.getResult(), registerSpecList, constant);
        NormalSsaInsn normalSsaInsn2 = new NormalSsaInsn(constant == null ? new PlainInsn(ropRopFor, originalRopInsn.getPosition(), normalSsaInsn.getResult(), registerSpecList) : new PlainCstInsn(ropRopFor, originalRopInsn.getPosition(), normalSsaInsn.getResult(), registerSpecList, constant), normalSsaInsn.getBlock());
        ArrayList<SsaInsn> insns = normalSsaInsn.getBlock().getInsns();
        this.ssaMeth.onInsnRemoved(normalSsaInsn);
        insns.set(insns.lastIndexOf(normalSsaInsn), normalSsaInsn2);
        this.ssaMeth.onInsnAdded(normalSsaInsn2);
    }

    private void run() {
        this.ssaMeth.forEachInsn(new SsaInsn.Visitor(this, Optimizer.getAdvice()) { // from class: com.android.cglib.dx.ssa.LiteralOpUpgrader.1
            public final LiteralOpUpgrader this$0;
            public final TranslationAdvice val$advice;

            {
                this.this$0 = this;
                this.val$advice = translationAdvice;
            }

            @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
            public void visitMoveInsn(NormalSsaInsn normalSsaInsn) {
            }

            @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
            public void visitNonMoveInsn(NormalSsaInsn normalSsaInsn) {
                LiteralOpUpgrader literalOpUpgrader;
                RegisterSpecList registerSpecListWithoutLast;
                int opcode;
                Rop opcode2 = normalSsaInsn.getOriginalRopInsn().getOpcode();
                RegisterSpecList sources = normalSsaInsn.getSources();
                if (!this.this$0.tryReplacingWithConstant(normalSsaInsn) && sources.size() == 2) {
                    if (opcode2.getBranchingness() != 4) {
                        if (!this.val$advice.hasConstantOperation(opcode2, sources.get(0), sources.get(1))) {
                            if (!opcode2.isCommutative() || !this.val$advice.hasConstantOperation(opcode2, sources.get(1), sources.get(0))) {
                                return;
                            } else {
                                normalSsaInsn.setNewSources(RegisterSpecList.make(sources.get(1), sources.get(0)));
                            }
                        }
                        normalSsaInsn.upgradeToLiteral();
                        return;
                    }
                    if (LiteralOpUpgrader.isConstIntZeroOrKnownNull(sources.get(0))) {
                        literalOpUpgrader = this.this$0;
                        registerSpecListWithoutLast = sources.withoutFirst();
                        opcode = RegOps.flippedIfOpcode(opcode2.getOpcode());
                    } else {
                        if (!LiteralOpUpgrader.isConstIntZeroOrKnownNull(sources.get(1))) {
                            return;
                        }
                        literalOpUpgrader = this.this$0;
                        registerSpecListWithoutLast = sources.withoutLast();
                        opcode = opcode2.getOpcode();
                    }
                    literalOpUpgrader.replacePlainInsn(normalSsaInsn, registerSpecListWithoutLast, opcode, null);
                }
            }

            @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
            public void visitPhiInsn(PhiInsn phiInsn) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean tryReplacingWithConstant(NormalSsaInsn normalSsaInsn) {
        Rop opcode = normalSsaInsn.getOriginalRopInsn().getOpcode();
        RegisterSpec result = normalSsaInsn.getResult();
        if (result == null || this.ssaMeth.isRegALocal(result) || opcode.getOpcode() == 5) {
            return false;
        }
        TypeBearer typeBearer = normalSsaInsn.getResult().getTypeBearer();
        if (!typeBearer.isConstant() || typeBearer.getBasicType() != 6) {
            return false;
        }
        RegisterSpecList registerSpecList = RegisterSpecList.EMPTY;
        replacePlainInsn(normalSsaInsn, registerSpecList, 5, (Constant) typeBearer);
        if (opcode.getOpcode() == 56) {
            ArrayList<SsaInsn> insns = this.ssaMeth.getBlocks().get(normalSsaInsn.getBlock().getPredecessors().nextSetBit(0)).getInsns();
            replacePlainInsn((NormalSsaInsn) insns.get(insns.size() - 1), registerSpecList, 6, null);
        }
        return true;
    }
}

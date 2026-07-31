package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.CstInsn;
import com.android.cglib.dx.rop.code.LocalItem;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.cst.CstInteger;
import com.android.cglib.dx.ssa.SsaInsn;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MoveParamCombiner {
    private final SsaMethod ssaMeth;

    /* JADX INFO: renamed from: com.android.cglib.dx.ssa.MoveParamCombiner$1, reason: invalid class name */
    public class AnonymousClass1 implements SsaInsn.Visitor {
        public final MoveParamCombiner this$0;
        public final HashSet val$deletedInsns;
        public final RegisterSpec[] val$paramSpecs;

        public AnonymousClass1(MoveParamCombiner moveParamCombiner, RegisterSpec[] registerSpecArr, HashSet hashSet) {
            this.this$0 = moveParamCombiner;
            this.val$paramSpecs = registerSpecArr;
            this.val$deletedInsns = hashSet;
        }

        @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn normalSsaInsn) {
        }

        @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn normalSsaInsn) {
            if (normalSsaInsn.getOpcode().getOpcode() != 3) {
                return;
            }
            int paramIndex = this.this$0.getParamIndex(normalSsaInsn);
            RegisterSpec[] registerSpecArr = this.val$paramSpecs;
            RegisterSpec registerSpec = registerSpecArr[paramIndex];
            if (registerSpec == null) {
                registerSpecArr[paramIndex] = normalSsaInsn.getResult();
                return;
            }
            RegisterSpec result = normalSsaInsn.getResult();
            LocalItem localItem = registerSpec.getLocalItem();
            LocalItem localItem2 = result.getLocalItem();
            if (localItem == null) {
                localItem = localItem2;
            } else if (localItem2 != null && !localItem.equals(localItem2)) {
                return;
            }
            this.this$0.ssaMeth.getDefinitionForRegister(registerSpec.getReg()).setResultLocal(localItem);
            RegisterMapper registerMapper = new RegisterMapper(this, result, registerSpec) { // from class: com.android.cglib.dx.ssa.MoveParamCombiner.1.1
                public final AnonymousClass1 this$1;
                public final RegisterSpec val$specA;
                public final RegisterSpec val$specB;

                {
                    this.this$1 = this;
                    this.val$specB = result;
                    this.val$specA = registerSpec;
                }

                @Override // com.android.cglib.dx.ssa.RegisterMapper
                public int getNewRegisterCount() {
                    return this.this$1.this$0.ssaMeth.getRegCount();
                }

                @Override // com.android.cglib.dx.ssa.RegisterMapper
                public RegisterSpec map(RegisterSpec registerSpec2) {
                    return registerSpec2.getReg() == this.val$specB.getReg() ? this.val$specA : registerSpec2;
                }
            };
            List<SsaInsn> useListForRegister = this.this$0.ssaMeth.getUseListForRegister(result.getReg());
            for (int size = useListForRegister.size() - 1; size >= 0; size--) {
                useListForRegister.get(size).mapSourceRegisters(registerMapper);
            }
            this.val$deletedInsns.add(normalSsaInsn);
        }

        @Override // com.android.cglib.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn phiInsn) {
        }
    }

    private MoveParamCombiner(SsaMethod ssaMethod) {
        this.ssaMeth = ssaMethod;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getParamIndex(NormalSsaInsn normalSsaInsn) {
        return ((CstInteger) ((CstInsn) normalSsaInsn.getOriginalRopInsn()).getConstant()).getValue();
    }

    public static void process(SsaMethod ssaMethod) {
        new MoveParamCombiner(ssaMethod).run();
    }

    private void run() {
        RegisterSpec[] registerSpecArr = new RegisterSpec[this.ssaMeth.getParamWidth()];
        HashSet hashSet = new HashSet();
        this.ssaMeth.forEachInsn(new AnonymousClass1(this, registerSpecArr, hashSet));
        this.ssaMeth.deleteInsns(hashSet);
    }
}

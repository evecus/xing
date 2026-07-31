package com.android.cglib.dx.dex.code.form;

import com.android.cglib.dx.dex.code.CstInsn;
import com.android.cglib.dx.dex.code.DalvInsn;
import com.android.cglib.dx.dex.code.InsnFormat;
import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstMethodRef;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.AnnotatedOutput;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public final class Form35c extends InsnFormat {
    private static final int MAX_NUM_OPS = 5;
    public static final InsnFormat THE_ONE = new Form35c();

    private Form35c() {
    }

    private static RegisterSpecList explicitize(RegisterSpecList registerSpecList) {
        int iWordCount = wordCount(registerSpecList);
        int size = registerSpecList.size();
        if (iWordCount == size) {
            return registerSpecList;
        }
        RegisterSpecList registerSpecList2 = new RegisterSpecList(iWordCount);
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RegisterSpec registerSpec = registerSpecList.get(i2);
            registerSpecList2.set(i, registerSpec);
            if (registerSpec.getCategory() == 2) {
                registerSpecList2.set(i + 1, RegisterSpec.make(registerSpec.getReg() + 1, Type.VOID));
                i += 2;
            } else {
                i++;
            }
        }
        registerSpecList2.setImmutable();
        return registerSpecList2;
    }

    private static int wordCount(RegisterSpecList registerSpecList) {
        int size = registerSpecList.size();
        if (size <= 5) {
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i < size) {
                    int category = registerSpecList.get(i).getCategory();
                    if (!InsnFormat.unsignedFitsInNibble((r4.getReg() + r4.getCategory()) - 1)) {
                        break;
                    }
                    i++;
                    i2 += category;
                } else if (i2 <= 5) {
                    return i2;
                }
            }
        }
        return -1;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public int codeSize() {
        return 3;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public BitSet compatibleRegs(DalvInsn dalvInsn) {
        RegisterSpecList registers = dalvInsn.getRegisters();
        int size = registers.size();
        BitSet bitSet = new BitSet(size);
        for (int i = 0; i < size; i++) {
            RegisterSpec registerSpec = registers.get(i);
            bitSet.set(i, InsnFormat.unsignedFitsInNibble((registerSpec.getCategory() + registerSpec.getReg()) - 1));
        }
        return bitSet;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnArgString(DalvInsn dalvInsn) {
        return InsnFormat.regListString(explicitize(dalvInsn.getRegisters())) + ", " + InsnFormat.cstString(dalvInsn);
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public String insnCommentString(DalvInsn dalvInsn, boolean z) {
        return z ? InsnFormat.cstComment(dalvInsn) : "";
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public boolean isCompatible(DalvInsn dalvInsn) {
        if (dalvInsn instanceof CstInsn) {
            CstInsn cstInsn = (CstInsn) dalvInsn;
            if (InsnFormat.unsignedFitsInShort(cstInsn.getIndex())) {
                Constant constant = cstInsn.getConstant();
                if (((constant instanceof CstMethodRef) || (constant instanceof CstType)) && wordCount(cstInsn.getRegisters()) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.android.cglib.dx.dex.code.InsnFormat
    public void writeTo(AnnotatedOutput annotatedOutput, DalvInsn dalvInsn) {
        int index = ((CstInsn) dalvInsn).getIndex();
        RegisterSpecList registerSpecListExplicitize = explicitize(dalvInsn.getRegisters());
        int size = registerSpecListExplicitize.size();
        InsnFormat.write(annotatedOutput, InsnFormat.opcodeUnit(dalvInsn, InsnFormat.makeByte(size > 4 ? registerSpecListExplicitize.get(4).getReg() : 0, size)), (short) index, InsnFormat.codeUnit(size > 0 ? registerSpecListExplicitize.get(0).getReg() : 0, size > 1 ? registerSpecListExplicitize.get(1).getReg() : 0, size > 2 ? registerSpecListExplicitize.get(2).getReg() : 0, size > 3 ? registerSpecListExplicitize.get(3).getReg() : 0));
    }
}

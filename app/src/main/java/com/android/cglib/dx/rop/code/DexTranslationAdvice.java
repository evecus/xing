package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.cst.CstInteger;
import com.android.cglib.dx.rop.type.Type;

/* JADX INFO: loaded from: classes.dex */
public final class DexTranslationAdvice implements TranslationAdvice {
    private static final int MIN_INVOKE_IN_ORDER = 6;
    private final boolean disableSourcesInOrder;
    public static final DexTranslationAdvice THE_ONE = new DexTranslationAdvice();
    public static final DexTranslationAdvice NO_SOURCES_IN_ORDER = new DexTranslationAdvice(true);

    private DexTranslationAdvice() {
        this.disableSourcesInOrder = false;
    }

    private DexTranslationAdvice(boolean z) {
        this.disableSourcesInOrder = z;
    }

    private int totalRopWidth(RegisterSpecList registerSpecList) {
        int size = registerSpecList.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int category = registerSpecList.get(i).getCategory();
            i++;
            i2 += category;
        }
        return i2;
    }

    @Override // com.android.cglib.dx.rop.code.TranslationAdvice
    public int getMaxOptimalRegisterCount() {
        return 16;
    }

    @Override // com.android.cglib.dx.rop.code.TranslationAdvice
    public boolean hasConstantOperation(Rop rop, RegisterSpec registerSpec, RegisterSpec registerSpec2) {
        CstInteger cstIntegerMake;
        if (registerSpec.getType() != Type.INT) {
            return false;
        }
        if (registerSpec2.getTypeBearer() instanceof CstInteger) {
            CstInteger cstInteger = (CstInteger) registerSpec2.getTypeBearer();
            switch (rop.getOpcode()) {
                case 14:
                case 16:
                case 17:
                case 18:
                case 20:
                case 21:
                case 22:
                    return cstInteger.fitsIn16Bits();
                case 15:
                    cstIntegerMake = CstInteger.make(-cstInteger.getValue());
                    break;
                case 19:
                default:
                    return false;
                case 23:
                case 24:
                case 25:
                    return cstInteger.fitsIn8Bits();
            }
        } else {
            if (!(registerSpec.getTypeBearer() instanceof CstInteger) || rop.getOpcode() != 15) {
                return false;
            }
            cstIntegerMake = (CstInteger) registerSpec.getTypeBearer();
        }
        return cstIntegerMake.fitsIn16Bits();
    }

    @Override // com.android.cglib.dx.rop.code.TranslationAdvice
    public boolean requiresSourcesInOrder(Rop rop, RegisterSpecList registerSpecList) {
        return !this.disableSourcesInOrder && rop.isCallLike() && totalRopWidth(registerSpecList) >= 6;
    }
}

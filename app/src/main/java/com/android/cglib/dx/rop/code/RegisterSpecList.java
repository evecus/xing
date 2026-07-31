package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.rop.type.TypeList;
import com.android.cglib.dx.util.FixedSizeList;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public final class RegisterSpecList extends FixedSizeList implements TypeList {
    public static final RegisterSpecList EMPTY = new RegisterSpecList(0);

    public RegisterSpecList(int i) {
        super(i);
    }

    public static RegisterSpecList make(RegisterSpec registerSpec) {
        RegisterSpecList registerSpecList = new RegisterSpecList(1);
        registerSpecList.set(0, registerSpec);
        return registerSpecList;
    }

    public static RegisterSpecList make(RegisterSpec registerSpec, RegisterSpec registerSpec2) {
        RegisterSpecList registerSpecList = new RegisterSpecList(2);
        registerSpecList.set(0, registerSpec);
        registerSpecList.set(1, registerSpec2);
        return registerSpecList;
    }

    public static RegisterSpecList make(RegisterSpec registerSpec, RegisterSpec registerSpec2, RegisterSpec registerSpec3) {
        RegisterSpecList registerSpecList = new RegisterSpecList(3);
        registerSpecList.set(0, registerSpec);
        registerSpecList.set(1, registerSpec2);
        registerSpecList.set(2, registerSpec3);
        return registerSpecList;
    }

    public static RegisterSpecList make(RegisterSpec registerSpec, RegisterSpec registerSpec2, RegisterSpec registerSpec3, RegisterSpec registerSpec4) {
        RegisterSpecList registerSpecList = new RegisterSpecList(4);
        registerSpecList.set(0, registerSpec);
        registerSpecList.set(1, registerSpec2);
        registerSpecList.set(2, registerSpec3);
        registerSpecList.set(3, registerSpec4);
        return registerSpecList;
    }

    public RegisterSpec get(int i) {
        return (RegisterSpec) get0(i);
    }

    public int getRegistersSize() {
        int nextReg;
        int size = size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RegisterSpec registerSpec = (RegisterSpec) get0(i2);
            if (registerSpec != null && (nextReg = registerSpec.getNextReg()) > i) {
                i = nextReg;
            }
        }
        return i;
    }

    @Override // com.android.cglib.dx.rop.type.TypeList
    public Type getType(int i) {
        return get(i).getType().getType();
    }

    @Override // com.android.cglib.dx.rop.type.TypeList
    public int getWordCount() {
        int size = size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int category = getType(i).getCategory();
            i++;
            i2 += category;
        }
        return i2;
    }

    public int indexOfRegister(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (get(i2).getReg() == i) {
                return i2;
            }
        }
        return -1;
    }

    public void set(int i, RegisterSpec registerSpec) {
        set0(i, registerSpec);
    }

    public RegisterSpec specForRegister(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            RegisterSpec registerSpec = get(i2);
            if (registerSpec.getReg() == i) {
                return registerSpec;
            }
        }
        return null;
    }

    public RegisterSpecList subset(BitSet bitSet) {
        int size = size() - bitSet.cardinality();
        if (size == 0) {
            return EMPTY;
        }
        RegisterSpecList registerSpecList = new RegisterSpecList(size);
        int i = 0;
        for (int i2 = 0; i2 < size(); i2++) {
            if (!bitSet.get(i2)) {
                registerSpecList.set0(i, get0(i2));
                i++;
            }
        }
        if (isImmutable()) {
            registerSpecList.setImmutable();
        }
        return registerSpecList;
    }

    @Override // com.android.cglib.dx.rop.type.TypeList
    public TypeList withAddedType(Type type) {
        throw new UnsupportedOperationException("unsupported");
    }

    public RegisterSpecList withExpandedRegisters(int i, boolean z, BitSet bitSet) {
        int size = size();
        if (size == 0) {
            return this;
        }
        RegisterSpecList registerSpecList = new RegisterSpecList(size);
        for (int i2 = 0; i2 < size; i2++) {
            RegisterSpec registerSpec = (RegisterSpec) get0(i2);
            if (bitSet != null && bitSet.get(i2)) {
                registerSpecList.set0(i2, registerSpec);
            } else {
                registerSpecList.set0(i2, registerSpec.withReg(i));
                if (!z) {
                    i = registerSpec.getCategory() + i;
                }
            }
            if (z) {
                z = false;
            }
        }
        if (!isImmutable()) {
            return registerSpecList;
        }
        registerSpecList.setImmutable();
        return registerSpecList;
    }

    public RegisterSpecList withFirst(RegisterSpec registerSpec) {
        int size = size();
        RegisterSpecList registerSpecList = new RegisterSpecList(size + 1);
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            registerSpecList.set0(i2, get0(i));
            i = i2;
        }
        registerSpecList.set0(0, registerSpec);
        if (isImmutable()) {
            registerSpecList.setImmutable();
        }
        return registerSpecList;
    }

    public RegisterSpecList withOffset(int i) {
        int size = size();
        if (size == 0) {
            return this;
        }
        RegisterSpecList registerSpecList = new RegisterSpecList(size);
        for (int i2 = 0; i2 < size; i2++) {
            RegisterSpec registerSpec = (RegisterSpec) get0(i2);
            if (registerSpec != null) {
                registerSpecList.set0(i2, registerSpec.withOffset(i));
            }
        }
        if (!isImmutable()) {
            return registerSpecList;
        }
        registerSpecList.setImmutable();
        return registerSpecList;
    }

    public RegisterSpecList withoutFirst() {
        int size = size() - 1;
        if (size == 0) {
            return EMPTY;
        }
        RegisterSpecList registerSpecList = new RegisterSpecList(size);
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            registerSpecList.set0(i, get0(i2));
            i = i2;
        }
        if (isImmutable()) {
            registerSpecList.setImmutable();
        }
        return registerSpecList;
    }

    public RegisterSpecList withoutLast() {
        int size = size() - 1;
        if (size == 0) {
            return EMPTY;
        }
        RegisterSpecList registerSpecList = new RegisterSpecList(size);
        for (int i = 0; i < size; i++) {
            registerSpecList.set0(i, get0(i));
        }
        if (isImmutable()) {
            registerSpecList.setImmutable();
        }
        return registerSpecList;
    }
}

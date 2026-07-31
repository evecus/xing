package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.ssa.back.InterferenceGraph;
import com.android.cglib.dx.util.BitIntSet;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class InterferenceRegisterMapper extends BasicRegisterMapper {
    private final ArrayList<BitIntSet> newRegInterference;
    private final InterferenceGraph oldRegInterference;

    public InterferenceRegisterMapper(InterferenceGraph interferenceGraph, int i) {
        super(i);
        this.newRegInterference = new ArrayList<>();
        this.oldRegInterference = interferenceGraph;
    }

    private void addInterfence(int i, int i2) {
        int i3 = i + 1;
        this.newRegInterference.ensureCapacity(i3);
        while (i >= this.newRegInterference.size()) {
            this.newRegInterference.add(new BitIntSet(i3));
        }
        this.oldRegInterference.mergeInterferenceSet(i2, this.newRegInterference.get(i));
    }

    @Override // com.android.cglib.dx.ssa.BasicRegisterMapper
    public void addMapping(int i, int i2, int i3) {
        super.addMapping(i, i2, i3);
        addInterfence(i2, i);
        if (i3 == 2) {
            addInterfence(i2 + 1, i);
        }
    }

    public boolean areAnyPinned(RegisterSpecList registerSpecList, int i, int i2) {
        int size = registerSpecList.size();
        for (int i3 = 0; i3 < size; i3++) {
            RegisterSpec registerSpec = registerSpecList.get(i3);
            int iOldToNew = oldToNew(registerSpec.getReg());
            if (iOldToNew == i || ((registerSpec.getCategory() == 2 && iOldToNew + 1 == i) || (i2 == 2 && iOldToNew == i + 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean interferes(int i, int i2, int i3) {
        BitIntSet bitIntSet;
        if (i2 < this.newRegInterference.size() && (bitIntSet = this.newRegInterference.get(i2)) != null) {
            return i3 == 1 ? bitIntSet.has(i) : bitIntSet.has(i) || interferes(i, i2 + 1, i3 - 1);
        }
        return false;
    }

    public boolean interferes(RegisterSpec registerSpec, int i) {
        return interferes(registerSpec.getReg(), i, registerSpec.getCategory());
    }
}

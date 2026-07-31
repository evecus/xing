package com.android.cglib.dx.ssa;

import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.util.IntList;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class BasicRegisterMapper extends RegisterMapper {
    private IntList oldToNew;
    private int runningCountNewRegisters;

    public BasicRegisterMapper(int i) {
        this.oldToNew = new IntList(i);
    }

    public void addMapping(int i, int i2, int i3) {
        if (i >= this.oldToNew.size()) {
            for (int size = i - this.oldToNew.size(); size >= 0; size--) {
                this.oldToNew.add(-1);
            }
        }
        this.oldToNew.set(i, i2);
        int i4 = i2 + i3;
        if (this.runningCountNewRegisters < i4) {
            this.runningCountNewRegisters = i4;
        }
    }

    @Override // com.android.cglib.dx.ssa.RegisterMapper
    public int getNewRegisterCount() {
        return this.runningCountNewRegisters;
    }

    @Override // com.android.cglib.dx.ssa.RegisterMapper
    public RegisterSpec map(RegisterSpec registerSpec) {
        int i;
        if (registerSpec == null) {
            return null;
        }
        try {
            i = this.oldToNew.get(registerSpec.getReg());
        } catch (IndexOutOfBoundsException e) {
            i = -1;
        }
        if (i >= 0) {
            return registerSpec.withReg(i);
        }
        throw new RuntimeException("no mapping specified for register");
    }

    public int oldToNew(int i) {
        if (i >= this.oldToNew.size()) {
            return -1;
        }
        return this.oldToNew.get(i);
    }

    public String toHuman() {
        StringBuilder sbO = a.o("Old\tNew\n");
        int size = this.oldToNew.size();
        for (int i = 0; i < size; i++) {
            sbO.append(i);
            sbO.append('\t');
            sbO.append(this.oldToNew.get(i));
            sbO.append('\n');
        }
        sbO.append("new reg count:");
        sbO.append(this.runningCountNewRegisters);
        sbO.append('\n');
        return sbO.toString();
    }
}

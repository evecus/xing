package com.android.cglib.dx;

import com.android.cglib.dx.rop.code.BasicBlock;
import com.android.cglib.dx.rop.code.Insn;
import com.android.cglib.dx.rop.code.InsnList;
import com.android.cglib.dx.util.IntList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Label {
    public Label alternateSuccessor;
    public Code code;
    public Label primarySuccessor;
    public final List<Insn> instructions = new ArrayList();
    public boolean marked = false;
    public List<Label> catchLabels = Collections.emptyList();
    public int id = -1;

    public void compact() {
        for (int i = 0; i < this.catchLabels.size(); i++) {
            while (this.catchLabels.get(i).isEmpty()) {
                List<Label> list = this.catchLabels;
                list.set(i, list.get(i).primarySuccessor);
            }
        }
        while (true) {
            Label label = this.primarySuccessor;
            if (label == null || !label.isEmpty()) {
                break;
            } else {
                this.primarySuccessor = this.primarySuccessor.primarySuccessor;
            }
        }
        while (true) {
            Label label2 = this.alternateSuccessor;
            if (label2 == null || !label2.isEmpty()) {
                return;
            } else {
                this.alternateSuccessor = this.alternateSuccessor.primarySuccessor;
            }
        }
    }

    public boolean isEmpty() {
        return this.instructions.isEmpty();
    }

    public BasicBlock toBasicBlock() {
        int i;
        InsnList insnList = new InsnList(this.instructions.size());
        for (int i2 = 0; i2 < this.instructions.size(); i2++) {
            insnList.set(i2, this.instructions.get(i2));
        }
        insnList.setImmutable();
        IntList intList = new IntList();
        Iterator<Label> it = this.catchLabels.iterator();
        while (it.hasNext()) {
            intList.add(it.next().id);
        }
        Label label = this.primarySuccessor;
        if (label != null) {
            i = label.id;
            intList.add(i);
        } else {
            i = -1;
        }
        Label label2 = this.alternateSuccessor;
        if (label2 != null) {
            intList.add(label2.id);
        }
        intList.setImmutable();
        return new BasicBlock(this.id, insnList, intList, i);
    }
}

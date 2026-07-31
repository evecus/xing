package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.dex.DexOptions;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class OutputCollector {
    private final OutputFinisher finisher;
    private ArrayList<DalvInsn> suffix;

    public OutputCollector(DexOptions dexOptions, int i, int i2, int i3) {
        this.finisher = new OutputFinisher(dexOptions, i, i3);
        this.suffix = new ArrayList<>(i2);
    }

    private void appendSuffixToOutput() {
        int size = this.suffix.size();
        for (int i = 0; i < size; i++) {
            this.finisher.add(this.suffix.get(i));
        }
        this.suffix = null;
    }

    public void add(DalvInsn dalvInsn) {
        this.finisher.add(dalvInsn);
    }

    public void addSuffix(DalvInsn dalvInsn) {
        this.suffix.add(dalvInsn);
    }

    public OutputFinisher getFinisher() {
        if (this.suffix == null) {
            throw new UnsupportedOperationException("already processed");
        }
        appendSuffixToOutput();
        return this.finisher;
    }

    public void reverseBranch(int i, CodeAddress codeAddress) {
        this.finisher.reverseBranch(i, codeAddress);
    }
}

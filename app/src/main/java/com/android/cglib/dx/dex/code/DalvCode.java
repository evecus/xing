package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.type.Type;
import java.util.HashSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class DalvCode {
    private CatchTable catches;
    private DalvInsnList insns;
    private LocalList locals;
    private final int positionInfo;
    private PositionList positions;
    private CatchBuilder unprocessedCatches;
    private OutputFinisher unprocessedInsns;

    public interface AssignIndicesCallback {
        int getIndex(Constant constant);
    }

    public DalvCode(int i, OutputFinisher outputFinisher, CatchBuilder catchBuilder) {
        Objects.requireNonNull(outputFinisher, "unprocessedInsns == null");
        Objects.requireNonNull(catchBuilder, "unprocessedCatches == null");
        this.positionInfo = i;
        this.unprocessedInsns = outputFinisher;
        this.unprocessedCatches = catchBuilder;
        this.catches = null;
        this.positions = null;
        this.locals = null;
        this.insns = null;
    }

    private void finishProcessingIfNecessary() {
        if (this.insns != null) {
            return;
        }
        DalvInsnList dalvInsnListFinishProcessingAndGetList = this.unprocessedInsns.finishProcessingAndGetList();
        this.insns = dalvInsnListFinishProcessingAndGetList;
        this.positions = PositionList.make(dalvInsnListFinishProcessingAndGetList, this.positionInfo);
        this.locals = LocalList.make(this.insns);
        this.catches = this.unprocessedCatches.build();
        this.unprocessedInsns = null;
        this.unprocessedCatches = null;
    }

    public void assignIndices(AssignIndicesCallback assignIndicesCallback) {
        this.unprocessedInsns.assignIndices(assignIndicesCallback);
    }

    public HashSet<Type> getCatchTypes() {
        return this.unprocessedCatches.getCatchTypes();
    }

    public CatchTable getCatches() {
        finishProcessingIfNecessary();
        return this.catches;
    }

    public HashSet<Constant> getInsnConstants() {
        return this.unprocessedInsns.getAllConstants();
    }

    public DalvInsnList getInsns() {
        finishProcessingIfNecessary();
        return this.insns;
    }

    public LocalList getLocals() {
        finishProcessingIfNecessary();
        return this.locals;
    }

    public PositionList getPositions() {
        finishProcessingIfNecessary();
        return this.positions;
    }

    public boolean hasAnyCatches() {
        return this.unprocessedCatches.hasAnyCatches();
    }

    public boolean hasLocals() {
        return this.unprocessedInsns.hasAnyLocalInfo();
    }

    public boolean hasPositions() {
        return this.positionInfo != 1 && this.unprocessedInsns.hasAnyPositionInfo();
    }
}

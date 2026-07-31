package com.android.cglib.dx;

import com.android.cglib.dx.rop.code.Rop;
import com.android.cglib.dx.rop.code.Rops;

/* JADX INFO: loaded from: classes.dex */
public enum Comparison {
    LT { // from class: com.android.cglib.dx.Comparison.1
        @Override // com.android.cglib.dx.Comparison
        public Rop rop(com.android.cglib.dx.rop.type.TypeList typeList) {
            return Rops.opIfLt(typeList);
        }
    },
    LE { // from class: com.android.cglib.dx.Comparison.2
        @Override // com.android.cglib.dx.Comparison
        public Rop rop(com.android.cglib.dx.rop.type.TypeList typeList) {
            return Rops.opIfLe(typeList);
        }
    },
    EQ { // from class: com.android.cglib.dx.Comparison.3
        @Override // com.android.cglib.dx.Comparison
        public Rop rop(com.android.cglib.dx.rop.type.TypeList typeList) {
            return Rops.opIfEq(typeList);
        }
    },
    GE { // from class: com.android.cglib.dx.Comparison.4
        @Override // com.android.cglib.dx.Comparison
        public Rop rop(com.android.cglib.dx.rop.type.TypeList typeList) {
            return Rops.opIfGe(typeList);
        }
    },
    GT { // from class: com.android.cglib.dx.Comparison.5
        @Override // com.android.cglib.dx.Comparison
        public Rop rop(com.android.cglib.dx.rop.type.TypeList typeList) {
            return Rops.opIfGt(typeList);
        }
    },
    NE { // from class: com.android.cglib.dx.Comparison.6
        @Override // com.android.cglib.dx.Comparison
        public Rop rop(com.android.cglib.dx.rop.type.TypeList typeList) {
            return Rops.opIfNe(typeList);
        }
    };

    public abstract Rop rop(com.android.cglib.dx.rop.type.TypeList typeList);
}

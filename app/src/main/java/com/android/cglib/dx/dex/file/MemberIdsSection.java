package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.util.DexException;
import java.util.Iterator;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class MemberIdsSection extends UniformItemSection {
    private static final int MAX_MEMBERS = 65536;

    public MemberIdsSection(String str, DexFile dexFile) {
        super(str, dexFile, 4);
    }

    @Override // com.android.cglib.dx.dex.file.UniformItemSection
    public void orderItems() {
        if (items().size() > 65536) {
            StringBuilder sbE = a.e("Too many ", this instanceof MethodIdsSection ? "methods" : "fields", ": ");
            sbE.append(items().size());
            sbE.append("; max is ");
            sbE.append(65536);
            throw new DexException(sbE.toString());
        }
        Iterator<? extends Item> it = items().iterator();
        int i = 0;
        while (it.hasNext()) {
            ((MemberIdItem) it.next()).setIndex(i);
            i++;
        }
    }
}

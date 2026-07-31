package com.android.cglib.dx.dex;

/* JADX INFO: loaded from: classes.dex */
public class DexOptions {
    public int targetApiLevel = 13;

    public boolean canUseExtendedOpcodes() {
        return this.targetApiLevel >= 14;
    }

    public String getMagic() {
        return DexFormat.apiToMagic(this.targetApiLevel);
    }
}

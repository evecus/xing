package com.baidu.mobstat;

/* JADX INFO: loaded from: classes.dex */
public class ae {
    private ag a;

    private ae() {
    }

    public static ae a() {
        ae aeVar = new ae();
        ag agVar = new ag();
        aeVar.a = agVar;
        agVar.a("PKCS1Padding");
        return aeVar;
    }

    public void a(int i, ah ahVar) {
        this.a.a(i, ahVar, af.a);
    }

    public final byte[] a(byte[] bArr) {
        if (bArr != null) {
            return this.a.a(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("Null input buffer");
    }
}

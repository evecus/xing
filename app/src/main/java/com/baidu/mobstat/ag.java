package com.baidu.mobstat;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.util.Locale;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

/* JADX INFO: loaded from: classes.dex */
public final class ag {
    private static final byte[] a = new byte[0];
    private int b;
    private aj d;
    private byte[] f;
    private int g;
    private int h;
    private ah i;
    private OAEPParameterSpec e = null;
    private String j = "SHA-1";
    private String c = "PKCS1Padding";

    private void a(int i, ah ahVar, SecureRandom secureRandom, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        boolean z;
        OAEPParameterSpec oAEPParameterSpec;
        switch (i) {
            case 1:
            case 3:
                z = true;
                break;
            case 2:
            case 4:
                z = false;
                break;
            default:
                throw new InvalidKeyException("Unknown mode: " + i);
        }
        if (!(ahVar instanceof ah)) {
            throw new InvalidKeyException("only support helios key");
        }
        this.b = z ? 1 : 4;
        this.i = ahVar;
        int iA = af.a(ahVar.a());
        this.h = iA;
        this.g = 0;
        String str = this.c;
        if (str == "NoPadding") {
            if (algorithmParameterSpec != null) {
                throw new InvalidAlgorithmParameterException("Parameters not supported");
            }
            this.d = aj.a(3, iA, secureRandom);
            this.f = new byte[iA];
            return;
        }
        if (str == "PKCS1Padding") {
            if (algorithmParameterSpec != null) {
                throw new InvalidAlgorithmParameterException("Parameters not supported");
            }
            aj ajVarA = aj.a(this.b <= 2 ? 2 : 1, iA, secureRandom);
            this.d = ajVarA;
            if (z) {
                this.f = new byte[ajVarA.a()];
                return;
            } else {
                this.f = new byte[iA];
                return;
            }
        }
        int i2 = this.b;
        if (i2 == 3 || i2 == 4) {
            throw new InvalidKeyException("OAEP cannot be used to sign or verify signatures");
        }
        if (algorithmParameterSpec == null) {
            oAEPParameterSpec = new OAEPParameterSpec(this.j, "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
        } else {
            if (!(algorithmParameterSpec instanceof OAEPParameterSpec)) {
                throw new InvalidAlgorithmParameterException("Wrong Parameters for OAEP Padding");
            }
            oAEPParameterSpec = (OAEPParameterSpec) algorithmParameterSpec;
        }
        aj ajVarA2 = aj.a(4, iA, secureRandom, oAEPParameterSpec);
        this.d = ajVarA2;
        if (z) {
            this.f = new byte[ajVarA2.a()];
        } else {
            this.f = new byte[iA];
        }
    }

    private byte[] a() throws IllegalBlockSizeException {
        int i = this.g;
        byte[] bArr = this.f;
        if (i > bArr.length) {
            throw new IllegalBlockSizeException("Data must not be longer than " + this.f.length + " bytes");
        }
        try {
            switch (this.b) {
                case 1:
                    return af.a(this.d.a(bArr, 0, i), this.i);
                case 2:
                    throw new UnsupportedOperationException("only verify supported");
                case 3:
                    throw new UnsupportedOperationException("only verify supported");
                case 4:
                    return this.d.b(af.a(af.a(bArr, 0, i), this.i));
                default:
                    throw new AssertionError("Internal error");
            }
        } finally {
            this.g = 0;
        }
    }

    private void b(byte[] bArr, int i, int i2) {
        int length;
        if (i2 == 0 || bArr == null) {
            return;
        }
        int i3 = this.g;
        int i4 = i3 + i2;
        byte[] bArr2 = this.f;
        if (i4 > bArr2.length) {
            length = bArr2.length + 1;
        } else {
            System.arraycopy(bArr, i, bArr2, i3, i2);
            length = this.g + i2;
        }
        this.g = length;
    }

    public void a(int i, ah ahVar, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            a(i, ahVar, secureRandom, null);
        } catch (InvalidAlgorithmParameterException e) {
            InvalidKeyException invalidKeyException = new InvalidKeyException("Wrong parameters");
            invalidKeyException.initCause(e);
            throw invalidKeyException;
        }
    }

    public void a(String str) throws NoSuchPaddingException {
        String str2 = "NoPadding";
        if (!str.equalsIgnoreCase("NoPadding")) {
            str2 = "PKCS1Padding";
            if (!str.equalsIgnoreCase("PKCS1Padding")) {
                String lowerCase = str.toLowerCase(Locale.ENGLISH);
                if (lowerCase.equals("oaeppadding")) {
                    this.c = "OAEP";
                    return;
                } else {
                    if (!lowerCase.startsWith("oaepwith") || !lowerCase.endsWith("andmgf1padding")) {
                        throw new NoSuchPaddingException("Padding " + str + " not supported");
                    }
                    this.c = "OAEP";
                    this.j = str.substring(8, str.length() - 14);
                    throw new NoSuchPaddingException("MessageDigest not available for " + str);
                }
            }
        }
        this.c = str2;
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        b(bArr, i, i2);
        return a();
    }
}

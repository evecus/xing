package com.android.cglib.dx.util;

/* JADX INFO: loaded from: classes.dex */
public final class ByteArrayByteInput implements ByteInput {
    private final byte[] bytes;
    private int position;

    public ByteArrayByteInput(byte... bArr) {
        this.bytes = bArr;
    }

    @Override // com.android.cglib.dx.util.ByteInput
    public byte readByte() {
        byte[] bArr = this.bytes;
        int i = this.position;
        this.position = i + 1;
        return bArr[i];
    }
}

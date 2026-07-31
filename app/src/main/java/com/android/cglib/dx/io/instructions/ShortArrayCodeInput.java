package com.android.cglib.dx.io.instructions;

import java.io.EOFException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ShortArrayCodeInput extends BaseCodeCursor implements CodeInput {
    private final short[] array;

    public ShortArrayCodeInput(short[] sArr) {
        Objects.requireNonNull(sArr, "array == null");
        this.array = sArr;
    }

    @Override // com.android.cglib.dx.io.instructions.CodeInput
    public boolean hasMore() {
        return cursor() < this.array.length;
    }

    @Override // com.android.cglib.dx.io.instructions.CodeInput
    public int read() throws EOFException {
        try {
            short s = this.array[cursor()];
            advance(1);
            return s & 65535;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EOFException();
        }
    }

    @Override // com.android.cglib.dx.io.instructions.CodeInput
    public int readInt() {
        return read() | (read() << 16);
    }

    @Override // com.android.cglib.dx.io.instructions.CodeInput
    public long readLong() {
        return ((long) read()) | (((long) read()) << 16) | (((long) read()) << 32) | (((long) read()) << 48);
    }
}

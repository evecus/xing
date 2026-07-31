package com.android.cglib.dx.io.instructions;

import androidx.core.view.InputDeviceCompat;
import com.android.cglib.dx.io.IndexType;
import com.android.cglib.dx.io.OpcodeInfo;
import com.android.cglib.dx.io.Opcodes;
import com.android.cglib.dx.util.DexException;
import com.android.cglib.dx.util.Hex;
import java.io.EOFException;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class DecodedInstruction {
    private final InstructionCodec format;
    private final int index;
    private final IndexType indexType;
    private final long literal;
    private final int opcode;
    private final int target;

    public DecodedInstruction(InstructionCodec instructionCodec, int i, int i2, IndexType indexType, int i3, long j) {
        Objects.requireNonNull(instructionCodec, "format == null");
        if (!Opcodes.isValidShape(i)) {
            throw new IllegalArgumentException("invalid opcode");
        }
        this.format = instructionCodec;
        this.opcode = i;
        this.index = i2;
        this.indexType = indexType;
        this.target = i3;
        this.literal = j;
    }

    public static DecodedInstruction decode(CodeInput codeInput) {
        int i = codeInput.read();
        return OpcodeInfo.getFormat(Opcodes.extractOpcodeFromUnit(i)).decode(i, codeInput);
    }

    public static DecodedInstruction[] decodeAll(short[] sArr) {
        DecodedInstruction[] decodedInstructionArr = new DecodedInstruction[sArr.length];
        ShortArrayCodeInput shortArrayCodeInput = new ShortArrayCodeInput(sArr);
        while (shortArrayCodeInput.hasMore()) {
            try {
                decodedInstructionArr[shortArrayCodeInput.cursor()] = decode(shortArrayCodeInput);
            } catch (EOFException e) {
                throw new DexException(e);
            }
        }
        return decodedInstructionArr;
    }

    public final void encode(CodeOutput codeOutput) {
        this.format.encode(this, codeOutput);
    }

    public int getA() {
        return 0;
    }

    public final short getAByte() {
        int a = getA();
        if ((a & InputDeviceCompat.SOURCE_ANY) == 0) {
            return (short) a;
        }
        StringBuilder sbO = a.o("Register A out of range: ");
        sbO.append(Hex.u8(a));
        throw new DexException(sbO.toString());
    }

    public final short getANibble() {
        int a = getA();
        if ((a & (-16)) == 0) {
            return (short) a;
        }
        StringBuilder sbO = a.o("Register A out of range: ");
        sbO.append(Hex.u8(a));
        throw new DexException(sbO.toString());
    }

    public final short getAUnit() {
        int a = getA();
        if (((-65536) & a) == 0) {
            return (short) a;
        }
        StringBuilder sbO = a.o("Register A out of range: ");
        sbO.append(Hex.u8(a));
        throw new DexException(sbO.toString());
    }

    public int getB() {
        return 0;
    }

    public final short getBByte() {
        int b = getB();
        if ((b & InputDeviceCompat.SOURCE_ANY) == 0) {
            return (short) b;
        }
        StringBuilder sbO = a.o("Register B out of range: ");
        sbO.append(Hex.u8(b));
        throw new DexException(sbO.toString());
    }

    public final short getBNibble() {
        int b = getB();
        if ((b & (-16)) == 0) {
            return (short) b;
        }
        StringBuilder sbO = a.o("Register B out of range: ");
        sbO.append(Hex.u8(b));
        throw new DexException(sbO.toString());
    }

    public final short getBUnit() {
        int b = getB();
        if (((-65536) & b) == 0) {
            return (short) b;
        }
        StringBuilder sbO = a.o("Register B out of range: ");
        sbO.append(Hex.u8(b));
        throw new DexException(sbO.toString());
    }

    public int getC() {
        return 0;
    }

    public final short getCByte() {
        int c = getC();
        if ((c & InputDeviceCompat.SOURCE_ANY) == 0) {
            return (short) c;
        }
        StringBuilder sbO = a.o("Register C out of range: ");
        sbO.append(Hex.u8(c));
        throw new DexException(sbO.toString());
    }

    public final short getCNibble() {
        int c = getC();
        if ((c & (-16)) == 0) {
            return (short) c;
        }
        StringBuilder sbO = a.o("Register C out of range: ");
        sbO.append(Hex.u8(c));
        throw new DexException(sbO.toString());
    }

    public final short getCUnit() {
        int c = getC();
        if (((-65536) & c) == 0) {
            return (short) c;
        }
        StringBuilder sbO = a.o("Register C out of range: ");
        sbO.append(Hex.u8(c));
        throw new DexException(sbO.toString());
    }

    public int getD() {
        return 0;
    }

    public final short getDByte() {
        int d = getD();
        if ((d & InputDeviceCompat.SOURCE_ANY) == 0) {
            return (short) d;
        }
        StringBuilder sbO = a.o("Register D out of range: ");
        sbO.append(Hex.u8(d));
        throw new DexException(sbO.toString());
    }

    public final short getDNibble() {
        int d = getD();
        if ((d & (-16)) == 0) {
            return (short) d;
        }
        StringBuilder sbO = a.o("Register D out of range: ");
        sbO.append(Hex.u8(d));
        throw new DexException(sbO.toString());
    }

    public final short getDUnit() {
        int d = getD();
        if (((-65536) & d) == 0) {
            return (short) d;
        }
        StringBuilder sbO = a.o("Register D out of range: ");
        sbO.append(Hex.u8(d));
        throw new DexException(sbO.toString());
    }

    public int getE() {
        return 0;
    }

    public final short getENibble() {
        int e = getE();
        if ((e & (-16)) == 0) {
            return (short) e;
        }
        StringBuilder sbO = a.o("Register E out of range: ");
        sbO.append(Hex.u8(e));
        throw new DexException(sbO.toString());
    }

    public final InstructionCodec getFormat() {
        return this.format;
    }

    public final int getIndex() {
        return this.index;
    }

    public final IndexType getIndexType() {
        return this.indexType;
    }

    public final short getIndexUnit() {
        return (short) this.index;
    }

    public final long getLiteral() {
        return this.literal;
    }

    public final int getLiteralByte() {
        long j = this.literal;
        int i = (int) j;
        if (j == ((byte) i)) {
            return i & 255;
        }
        StringBuilder sbO = a.o("Literal out of range: ");
        sbO.append(Hex.u8(this.literal));
        throw new DexException(sbO.toString());
    }

    public final int getLiteralInt() {
        long j = this.literal;
        int i = (int) j;
        if (j == i) {
            return i;
        }
        StringBuilder sbO = a.o("Literal out of range: ");
        sbO.append(Hex.u8(this.literal));
        throw new DexException(sbO.toString());
    }

    public final int getLiteralNibble() {
        long j = this.literal;
        if (j >= -8 && j <= 7) {
            return ((int) j) & 15;
        }
        StringBuilder sbO = a.o("Literal out of range: ");
        sbO.append(Hex.u8(this.literal));
        throw new DexException(sbO.toString());
    }

    public final short getLiteralUnit() {
        long j = this.literal;
        short s = (short) j;
        if (j == s) {
            return s;
        }
        StringBuilder sbO = a.o("Literal out of range: ");
        sbO.append(Hex.u8(this.literal));
        throw new DexException(sbO.toString());
    }

    public final int getOpcode() {
        return this.opcode;
    }

    public final short getOpcodeUnit() {
        return (short) this.opcode;
    }

    public abstract int getRegisterCount();

    public final short getRegisterCountUnit() {
        int registerCount = getRegisterCount();
        if (((-65536) & registerCount) == 0) {
            return (short) registerCount;
        }
        StringBuilder sbO = a.o("Register count out of range: ");
        sbO.append(Hex.u8(registerCount));
        throw new DexException(sbO.toString());
    }

    public final int getTarget() {
        return this.target;
    }

    public final int getTarget(int i) {
        return this.target - i;
    }

    public final int getTargetByte(int i) {
        int target = getTarget(i);
        if (target == ((byte) target)) {
            return target & 255;
        }
        StringBuilder sbO = a.o("Target out of range: ");
        sbO.append(Hex.s4(target));
        throw new DexException(sbO.toString());
    }

    public final short getTargetUnit(int i) {
        int target = getTarget(i);
        short s = (short) target;
        if (target == s) {
            return s;
        }
        StringBuilder sbO = a.o("Target out of range: ");
        sbO.append(Hex.s4(target));
        throw new DexException(sbO.toString());
    }

    public abstract DecodedInstruction withIndex(int i);
}

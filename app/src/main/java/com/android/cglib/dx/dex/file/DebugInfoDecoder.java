package com.android.cglib.dx.dex.file;

import androidx.exifinterface.media.ExifInterface;
import com.android.cglib.dx.dex.code.DalvCode;
import com.android.cglib.dx.dex.code.DalvInsnList;
import com.android.cglib.dx.dex.code.LocalList;
import com.android.cglib.dx.dex.code.PositionList;
import com.android.cglib.dx.rop.cst.CstMethodRef;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.type.Prototype;
import com.android.cglib.dx.rop.type.StdTypeList;
import com.android.cglib.dx.rop.type.Type;
import com.android.cglib.dx.util.ByteArrayByteInput;
import com.android.cglib.dx.util.ByteInput;
import com.android.cglib.dx.util.ExceptionWithContext;
import com.android.cglib.dx.util.Leb128Utils;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class DebugInfoDecoder {
    private final int codesize;
    private final Prototype desc;
    private final byte[] encoded;
    private final DexFile file;
    private final boolean isStatic;
    private final LocalEntry[] lastEntryForReg;
    private final ArrayList<LocalEntry> locals;
    private final ArrayList<PositionEntry> positions;
    private final int regSize;
    private final int thisStringIdx;
    private int line = 1;
    private int address = 0;

    public static class LocalEntry {
        public int address;
        public boolean isStart;
        public int nameIndex;
        public int reg;
        public int signatureIndex;
        public int typeIndex;

        public LocalEntry(int i, boolean z, int i2, int i3, int i4, int i5) {
            this.address = i;
            this.isStart = z;
            this.reg = i2;
            this.nameIndex = i3;
            this.typeIndex = i4;
            this.signatureIndex = i5;
        }

        public String toString() {
            int i = this.address;
            return String.format("[%x %s v%d %04x %04x %04x]", Integer.valueOf(i), this.isStart ? "start" : "end", Integer.valueOf(this.reg), Integer.valueOf(this.nameIndex), Integer.valueOf(this.typeIndex), Integer.valueOf(this.signatureIndex));
        }
    }

    public static class PositionEntry {
        public int address;
        public int line;

        public PositionEntry(int i, int i2) {
            this.address = i;
            this.line = i2;
        }
    }

    public DebugInfoDecoder(byte[] bArr, int i, int i2, boolean z, CstMethodRef cstMethodRef, DexFile dexFile) {
        int iIndexOf;
        Objects.requireNonNull(bArr, "encoded == null");
        this.encoded = bArr;
        this.isStatic = z;
        this.desc = cstMethodRef.getPrototype();
        this.file = dexFile;
        this.regSize = i2;
        this.positions = new ArrayList<>();
        this.locals = new ArrayList<>();
        this.codesize = i;
        this.lastEntryForReg = new LocalEntry[i2];
        try {
            iIndexOf = dexFile.getStringIds().indexOf(new CstString("this"));
        } catch (IllegalArgumentException e) {
            iIndexOf = -1;
        }
        this.thisStringIdx = iIndexOf;
    }

    private void decode0() {
        ByteArrayByteInput byteArrayByteInput = new ByteArrayByteInput(this.encoded);
        this.line = Leb128Utils.readUnsignedLeb128(byteArrayByteInput);
        int unsignedLeb128 = Leb128Utils.readUnsignedLeb128(byteArrayByteInput);
        StdTypeList parameterTypes = this.desc.getParameterTypes();
        int paramBase = getParamBase();
        if (unsignedLeb128 != parameterTypes.size()) {
            throw new RuntimeException("Mismatch between parameters_size and prototype");
        }
        if (!this.isStatic) {
            LocalEntry localEntry = new LocalEntry(0, true, paramBase, this.thisStringIdx, 0, 0);
            this.locals.add(localEntry);
            this.lastEntryForReg[paramBase] = localEntry;
            paramBase++;
        }
        int category = paramBase;
        for (int i = 0; i < unsignedLeb128; i++) {
            Type type = parameterTypes.getType(i);
            int stringIndex = readStringIndex(byteArrayByteInput);
            LocalEntry localEntry2 = stringIndex == -1 ? new LocalEntry(0, true, category, -1, 0, 0) : new LocalEntry(0, true, category, stringIndex, 0, 0);
            this.locals.add(localEntry2);
            this.lastEntryForReg[category] = localEntry2;
            category += type.getCategory();
        }
        while (true) {
            int i2 = byteArrayByteInput.readByte() & ExifInterface.MARKER;
            switch (i2) {
                case 0:
                    return;
                case 1:
                    this.address += Leb128Utils.readUnsignedLeb128(byteArrayByteInput);
                    break;
                case 2:
                    this.line += Leb128Utils.readSignedLeb128(byteArrayByteInput);
                    break;
                case 3:
                    int unsignedLeb1282 = Leb128Utils.readUnsignedLeb128(byteArrayByteInput);
                    LocalEntry localEntry3 = new LocalEntry(this.address, true, unsignedLeb1282, readStringIndex(byteArrayByteInput), readStringIndex(byteArrayByteInput), 0);
                    this.locals.add(localEntry3);
                    this.lastEntryForReg[unsignedLeb1282] = localEntry3;
                    break;
                case 4:
                    int unsignedLeb1283 = Leb128Utils.readUnsignedLeb128(byteArrayByteInput);
                    LocalEntry localEntry4 = new LocalEntry(this.address, true, unsignedLeb1283, readStringIndex(byteArrayByteInput), readStringIndex(byteArrayByteInput), readStringIndex(byteArrayByteInput));
                    this.locals.add(localEntry4);
                    this.lastEntryForReg[unsignedLeb1283] = localEntry4;
                    break;
                case 5:
                    int unsignedLeb1284 = Leb128Utils.readUnsignedLeb128(byteArrayByteInput);
                    try {
                        LocalEntry localEntry5 = this.lastEntryForReg[unsignedLeb1284];
                        if (!localEntry5.isStart) {
                            throw new RuntimeException("nonsensical END_LOCAL on dead register v" + unsignedLeb1284);
                        }
                        LocalEntry localEntry6 = new LocalEntry(this.address, false, unsignedLeb1284, localEntry5.nameIndex, localEntry5.typeIndex, localEntry5.signatureIndex);
                        this.locals.add(localEntry6);
                        this.lastEntryForReg[unsignedLeb1284] = localEntry6;
                    } catch (NullPointerException e) {
                        throw new RuntimeException(a.h("Encountered END_LOCAL on new v", unsignedLeb1284));
                    }
                    break;
                case 6:
                    int unsignedLeb1285 = Leb128Utils.readUnsignedLeb128(byteArrayByteInput);
                    try {
                        LocalEntry localEntry7 = this.lastEntryForReg[unsignedLeb1285];
                        if (localEntry7.isStart) {
                            throw new RuntimeException("nonsensical RESTART_LOCAL on live register v" + unsignedLeb1285);
                        }
                        LocalEntry localEntry8 = new LocalEntry(this.address, true, unsignedLeb1285, localEntry7.nameIndex, localEntry7.typeIndex, 0);
                        this.locals.add(localEntry8);
                        this.lastEntryForReg[unsignedLeb1285] = localEntry8;
                    } catch (NullPointerException e2) {
                        throw new RuntimeException(a.h("Encountered RESTART_LOCAL on new v", unsignedLeb1285));
                    }
                    break;
                case 7:
                case 8:
                case 9:
                    break;
                default:
                    if (i2 < 10) {
                        throw new RuntimeException(a.h("Invalid extended opcode encountered ", i2));
                    }
                    int i3 = this.address + ((i2 - 10) / 15);
                    this.address = i3;
                    int i4 = ((r2 % 15) - 4) + this.line;
                    this.line = i4;
                    this.positions.add(new PositionEntry(i3, i4));
                    break;
                    break;
            }
        }
    }

    private int getParamBase() {
        return (this.regSize - this.desc.getParameterTypes().getWordCount()) - (!this.isStatic ? 1 : 0);
    }

    private int readStringIndex(ByteInput byteInput) {
        return Leb128Utils.readUnsignedLeb128(byteInput) - 1;
    }

    public static void validateEncode(byte[] bArr, DexFile dexFile, CstMethodRef cstMethodRef, DalvCode dalvCode, boolean z) {
        PositionList positions = dalvCode.getPositions();
        LocalList locals = dalvCode.getLocals();
        DalvInsnList insns = dalvCode.getInsns();
        try {
            validateEncode0(bArr, insns.codeSize(), insns.getRegistersSize(), z, cstMethodRef, dexFile, positions, locals);
        } catch (RuntimeException e) {
            System.err.println("instructions:");
            insns.debugPrint((OutputStream) System.err, "  ", true);
            System.err.println("local list:");
            locals.debugPrint(System.err, "  ");
            throw ExceptionWithContext.withContext(e, "while processing " + cstMethodRef.toHuman());
        }
    }

    private static void validateEncode0(byte[] bArr, int i, int i2, boolean z, CstMethodRef cstMethodRef, DexFile dexFile, PositionList positionList, LocalList localList) {
        LocalEntry localEntry;
        PrintStream printStream;
        StringBuilder sb;
        String str;
        DebugInfoDecoder debugInfoDecoder = new DebugInfoDecoder(bArr, i, i2, z, cstMethodRef, dexFile);
        debugInfoDecoder.decode();
        List<PositionEntry> positionList2 = debugInfoDecoder.getPositionList();
        if (positionList2.size() != positionList.size()) {
            StringBuilder sbO = a.o("Decoded positions table not same size was ");
            sbO.append(positionList2.size());
            sbO.append(" expected ");
            sbO.append(positionList.size());
            throw new RuntimeException(sbO.toString());
        }
        for (PositionEntry positionEntry : positionList2) {
            for (int size = positionList.size() - 1; size >= 0; size--) {
                PositionList.Entry entry = positionList.get(size);
                if (positionEntry.line != entry.getPosition().getLine() || positionEntry.address != entry.getAddress()) {
                }
            }
            StringBuilder sbO2 = a.o("Could not match position entry: ");
            sbO2.append(positionEntry.address);
            sbO2.append(", ");
            sbO2.append(positionEntry.line);
            throw new RuntimeException(sbO2.toString());
        }
        List<LocalEntry> locals = debugInfoDecoder.getLocals();
        int i3 = debugInfoDecoder.thisStringIdx;
        int size2 = locals.size();
        int paramBase = debugInfoDecoder.getParamBase();
        for (int i4 = 0; i4 < size2; i4++) {
            LocalEntry localEntry2 = locals.get(i4);
            int i5 = localEntry2.nameIndex;
            if (i5 < 0 || i5 == i3) {
                int i6 = i4 + 1;
                while (true) {
                    if (i6 < size2) {
                        LocalEntry localEntry3 = locals.get(i6);
                        if (localEntry3.address == 0) {
                            if (localEntry2.reg == localEntry3.reg && localEntry3.isStart) {
                                locals.set(i4, localEntry3);
                                locals.remove(i6);
                                size2--;
                                break;
                            }
                            i6++;
                        }
                    }
                }
            }
        }
        int size3 = localList.size();
        int i7 = 0;
        for (int i8 = 0; i8 < size3; i8++) {
            LocalList.Entry entry2 = localList.get(i8);
            if (entry2.getDisposition() != LocalList.Disposition.END_REPLACED) {
                do {
                    localEntry = locals.get(i7);
                    if (localEntry.nameIndex >= 0) {
                        break;
                    } else {
                        i7++;
                    }
                } while (i7 < size2);
                int i9 = localEntry.address;
                if (localEntry.reg != entry2.getRegister()) {
                    printStream = System.err;
                    sb = new StringBuilder();
                    str = "local register mismatch at orig ";
                } else if (localEntry.isStart != entry2.isStart()) {
                    printStream = System.err;
                    sb = new StringBuilder();
                    str = "local start/end mismatch at orig ";
                } else if (i9 == entry2.getAddress() || (i9 == 0 && localEntry.reg >= paramBase)) {
                    i7++;
                } else {
                    printStream = System.err;
                    sb = new StringBuilder();
                    str = "local address mismatch at orig ";
                }
                sb.append(str);
                sb.append(i8);
                sb.append(" / decoded ");
                sb.append(i7);
                printStream.println(sb.toString());
                System.err.println("decoded locals:");
                for (LocalEntry localEntry4 : locals) {
                    System.err.println("  " + localEntry4);
                }
                throw new RuntimeException("local table problem");
            }
        }
    }

    public void decode() {
        try {
            decode0();
        } catch (Exception e) {
            throw ExceptionWithContext.withContext(e, "...while decoding debug info");
        }
    }

    public List<LocalEntry> getLocals() {
        return this.locals;
    }

    public List<PositionEntry> getPositionList() {
        return this.positions;
    }
}

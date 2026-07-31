package com.android.cglib.dx.io;

/* JADX INFO: loaded from: classes.dex */
public final class Code {
    private final CatchHandler[] catchHandlers;
    private final int debugInfoOffset;
    private final int insSize;
    private final short[] instructions;
    private final int outsSize;
    private final int registersSize;
    private final Try[] tries;

    public static class CatchHandler {
        public final int[] addresses;
        public final int catchAllAddress;
        public final int[] typeIndexes;

        public CatchHandler(int[] iArr, int[] iArr2, int i) {
            this.typeIndexes = iArr;
            this.addresses = iArr2;
            this.catchAllAddress = i;
        }

        public int[] getAddresses() {
            return this.addresses;
        }

        public int getCatchAllAddress() {
            return this.catchAllAddress;
        }

        public int[] getTypeIndexes() {
            return this.typeIndexes;
        }
    }

    public static class Try {
        public final int handlerOffset;
        public final int instructionCount;
        public final int startAddress;

        public Try(int i, int i2, int i3) {
            this.startAddress = i;
            this.instructionCount = i2;
            this.handlerOffset = i3;
        }

        public int getHandlerOffset() {
            return this.handlerOffset;
        }

        public int getInstructionCount() {
            return this.instructionCount;
        }

        public int getStartAddress() {
            return this.startAddress;
        }
    }

    public Code(int i, int i2, int i3, int i4, short[] sArr, Try[] tryArr, CatchHandler[] catchHandlerArr) {
        this.registersSize = i;
        this.insSize = i2;
        this.outsSize = i3;
        this.debugInfoOffset = i4;
        this.instructions = sArr;
        this.tries = tryArr;
        this.catchHandlers = catchHandlerArr;
    }

    public CatchHandler[] getCatchHandlers() {
        return this.catchHandlers;
    }

    public int getDebugInfoOffset() {
        return this.debugInfoOffset;
    }

    public int getInsSize() {
        return this.insSize;
    }

    public short[] getInstructions() {
        return this.instructions;
    }

    public int getOutsSize() {
        return this.outsSize;
    }

    public int getRegistersSize() {
        return this.registersSize;
    }

    public Try[] getTries() {
        return this.tries;
    }
}

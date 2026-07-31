package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.code.RegisterSpec;
import com.android.cglib.dx.rop.code.RegisterSpecList;
import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstInteger;
import com.android.cglib.dx.rop.cst.CstKnownNull;
import com.android.cglib.dx.rop.cst.CstLiteral64;
import com.android.cglib.dx.rop.cst.CstLiteralBits;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.Hex;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class InsnFormat {
    public static boolean ALLOW_EXTENDED_OPCODES = true;

    public static int argIndex(DalvInsn dalvInsn) {
        int value = ((CstInteger) ((CstInsn) dalvInsn).getConstant()).getValue();
        if (value >= 0) {
            return value;
        }
        throw new IllegalArgumentException("bogus insn");
    }

    public static String branchComment(DalvInsn dalvInsn) {
        int targetOffset = ((TargetInsn) dalvInsn).getTargetOffset();
        return targetOffset == ((short) targetOffset) ? Hex.s2(targetOffset) : Hex.s4(targetOffset);
    }

    public static String branchString(DalvInsn dalvInsn) {
        int targetAddress = ((TargetInsn) dalvInsn).getTargetAddress();
        return targetAddress == ((char) targetAddress) ? Hex.u2(targetAddress) : Hex.u4(targetAddress);
    }

    public static short codeUnit(int i, int i2) {
        if ((i & 255) != i) {
            throw new IllegalArgumentException("low out of range 0..255");
        }
        if ((i2 & 255) == i2) {
            return (short) (i | (i2 << 8));
        }
        throw new IllegalArgumentException("high out of range 0..255");
    }

    public static short codeUnit(int i, int i2, int i3, int i4) {
        if ((i & 15) != i) {
            throw new IllegalArgumentException("n0 out of range 0..15");
        }
        if ((i2 & 15) != i2) {
            throw new IllegalArgumentException("n1 out of range 0..15");
        }
        if ((i3 & 15) != i3) {
            throw new IllegalArgumentException("n2 out of range 0..15");
        }
        if ((i4 & 15) == i4) {
            return (short) (i | (i2 << 4) | (i3 << 8) | (i4 << 12));
        }
        throw new IllegalArgumentException("n3 out of range 0..15");
    }

    public static String cstComment(DalvInsn dalvInsn) {
        CstInsn cstInsn = (CstInsn) dalvInsn;
        if (!cstInsn.hasIndex()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(20);
        int index = cstInsn.getIndex();
        sb.append(cstInsn.getConstant().typeName());
        sb.append('@');
        sb.append(index < 65536 ? Hex.u2(index) : Hex.u4(index));
        return sb.toString();
    }

    public static String cstString(DalvInsn dalvInsn) {
        Constant constant = ((CstInsn) dalvInsn).getConstant();
        return constant instanceof CstString ? ((CstString) constant).toQuoted() : constant.toHuman();
    }

    public static boolean isRegListSequential(RegisterSpecList registerSpecList) {
        int size = registerSpecList.size();
        if (size >= 2) {
            int reg = registerSpecList.get(0).getReg();
            for (int i = 0; i < size; i++) {
                RegisterSpec registerSpec = registerSpecList.get(i);
                if (registerSpec.getReg() != reg) {
                    return false;
                }
                reg += registerSpec.getCategory();
            }
        }
        return true;
    }

    public static String literalBitsComment(CstLiteralBits cstLiteralBits, int i) {
        String strUNibble;
        StringBuffer stringBuffer = new StringBuffer(20);
        stringBuffer.append("#");
        long longBits = cstLiteralBits instanceof CstLiteral64 ? ((CstLiteral64) cstLiteralBits).getLongBits() : cstLiteralBits.getIntBits();
        if (i == 4) {
            strUNibble = Hex.uNibble((int) longBits);
        } else if (i == 8) {
            strUNibble = Hex.u1((int) longBits);
        } else if (i == 16) {
            strUNibble = Hex.u2((int) longBits);
        } else if (i == 32) {
            strUNibble = Hex.u4((int) longBits);
        } else {
            if (i != 64) {
                throw new RuntimeException("shouldn't happen");
            }
            strUNibble = Hex.u8(longBits);
        }
        stringBuffer.append(strUNibble);
        return stringBuffer.toString();
    }

    public static String literalBitsString(CstLiteralBits cstLiteralBits) {
        String human;
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append('#');
        if (cstLiteralBits instanceof CstKnownNull) {
            human = "null";
        } else {
            stringBuffer.append(cstLiteralBits.typeName());
            stringBuffer.append(' ');
            human = cstLiteralBits.toHuman();
        }
        stringBuffer.append(human);
        return stringBuffer.toString();
    }

    public static int makeByte(int i, int i2) {
        if ((i & 15) != i) {
            throw new IllegalArgumentException("low out of range 0..15");
        }
        if ((i2 & 15) == i2) {
            return i | (i2 << 4);
        }
        throw new IllegalArgumentException("high out of range 0..15");
    }

    public static short opcodeUnit(DalvInsn dalvInsn) {
        int opcode = dalvInsn.getOpcode().getOpcode();
        if (opcode < 255 || opcode > 65535) {
            throw new IllegalArgumentException("extended opcode out of range 255..65535");
        }
        return (short) opcode;
    }

    public static short opcodeUnit(DalvInsn dalvInsn, int i) {
        if ((i & 255) != i) {
            throw new IllegalArgumentException("arg out of range 0..255");
        }
        int opcode = dalvInsn.getOpcode().getOpcode();
        if ((opcode & 255) == opcode) {
            return (short) (opcode | (i << 8));
        }
        throw new IllegalArgumentException("opcode out of range 0..255");
    }

    public static String regListString(RegisterSpecList registerSpecList) {
        int size = registerSpecList.size();
        StringBuffer stringBuffer = new StringBuffer((size * 5) + 2);
        stringBuffer.append('{');
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(registerSpecList.get(i).regString());
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public static String regRangeString(RegisterSpecList registerSpecList) {
        String strRegString;
        int size = registerSpecList.size();
        StringBuilder sb = new StringBuilder(30);
        sb.append("{");
        if (size != 0) {
            if (size != 1) {
                RegisterSpec registerSpecWithOffset = registerSpecList.get(size - 1);
                if (registerSpecWithOffset.getCategory() == 2) {
                    registerSpecWithOffset = registerSpecWithOffset.withOffset(1);
                }
                sb.append(registerSpecList.get(0).regString());
                sb.append("..");
                strRegString = registerSpecWithOffset.regString();
            } else {
                strRegString = registerSpecList.get(0).regString();
            }
            sb.append(strRegString);
        }
        sb.append("}");
        return sb.toString();
    }

    public static boolean signedFitsInByte(int i) {
        return ((byte) i) == i;
    }

    public static boolean signedFitsInNibble(int i) {
        return i >= -8 && i <= 7;
    }

    public static boolean signedFitsInShort(int i) {
        return ((short) i) == i;
    }

    public static boolean unsignedFitsInByte(int i) {
        return i == (i & 255);
    }

    public static boolean unsignedFitsInNibble(int i) {
        return i == (i & 15);
    }

    public static boolean unsignedFitsInShort(int i) {
        return i == (65535 & i);
    }

    public static void write(AnnotatedOutput annotatedOutput, short s) {
        annotatedOutput.writeShort(s);
    }

    public static void write(AnnotatedOutput annotatedOutput, short s, int i) {
        write(annotatedOutput, s, (short) i, (short) (i >> 16));
    }

    public static void write(AnnotatedOutput annotatedOutput, short s, int i, short s2) {
        write(annotatedOutput, s, (short) i, (short) (i >> 16), s2);
    }

    public static void write(AnnotatedOutput annotatedOutput, short s, int i, short s2, short s3) {
        write(annotatedOutput, s, (short) i, (short) (i >> 16), s2, s3);
    }

    public static void write(AnnotatedOutput annotatedOutput, short s, long j) {
        write(annotatedOutput, s, (short) j, (short) (j >> 16), (short) (j >> 32), (short) (j >> 48));
    }

    public static void write(AnnotatedOutput annotatedOutput, short s, short s2) {
        annotatedOutput.writeShort(s);
        annotatedOutput.writeShort(s2);
    }

    public static void write(AnnotatedOutput annotatedOutput, short s, short s2, short s3) {
        annotatedOutput.writeShort(s);
        annotatedOutput.writeShort(s2);
        annotatedOutput.writeShort(s3);
    }

    public static void write(AnnotatedOutput annotatedOutput, short s, short s2, short s3, short s4) {
        annotatedOutput.writeShort(s);
        annotatedOutput.writeShort(s2);
        annotatedOutput.writeShort(s3);
        annotatedOutput.writeShort(s4);
    }

    public static void write(AnnotatedOutput annotatedOutput, short s, short s2, short s3, short s4, short s5) {
        annotatedOutput.writeShort(s);
        annotatedOutput.writeShort(s2);
        annotatedOutput.writeShort(s3);
        annotatedOutput.writeShort(s4);
        annotatedOutput.writeShort(s5);
    }

    public boolean branchFits(TargetInsn targetInsn) {
        return false;
    }

    public abstract int codeSize();

    public BitSet compatibleRegs(DalvInsn dalvInsn) {
        return new BitSet();
    }

    public abstract String insnArgString(DalvInsn dalvInsn);

    public abstract String insnCommentString(DalvInsn dalvInsn, boolean z);

    public abstract boolean isCompatible(DalvInsn dalvInsn);

    public final String listingString(DalvInsn dalvInsn, boolean z) {
        String name = dalvInsn.getOpcode().getName();
        String strInsnArgString = insnArgString(dalvInsn);
        String strInsnCommentString = insnCommentString(dalvInsn, z);
        StringBuilder sb = new StringBuilder(100);
        sb.append(name);
        if (strInsnArgString.length() != 0) {
            sb.append(' ');
            sb.append(strInsnArgString);
        }
        if (strInsnCommentString.length() != 0) {
            sb.append(" // ");
            sb.append(strInsnCommentString);
        }
        return sb.toString();
    }

    public abstract void writeTo(AnnotatedOutput annotatedOutput, DalvInsn dalvInsn);
}

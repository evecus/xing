package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstBaseMethodRef;
import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.ExceptionWithContext;
import com.android.cglib.dx.util.FixedSizeList;
import com.android.cglib.dx.util.IndentingWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class DalvInsnList extends FixedSizeList {
    private final int regCount;

    public DalvInsnList(int i, int i2) {
        super(i);
        this.regCount = i2;
    }

    public static DalvInsnList makeImmutable(ArrayList<DalvInsn> arrayList, int i) {
        int size = arrayList.size();
        DalvInsnList dalvInsnList = new DalvInsnList(size, i);
        for (int i2 = 0; i2 < size; i2++) {
            dalvInsnList.set(i2, arrayList.get(i2));
        }
        dalvInsnList.setImmutable();
        return dalvInsnList;
    }

    public int codeSize() {
        int size = size();
        if (size == 0) {
            return 0;
        }
        return get(size - 1).getNextAddress();
    }

    public void debugPrint(OutputStream outputStream, String str, boolean z) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        debugPrint(outputStreamWriter, str, z);
        try {
            outputStreamWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void debugPrint(Writer writer, String str, boolean z) {
        IndentingWriter indentingWriter = new IndentingWriter(writer, 0, str);
        int size = size();
        for (int i = 0; i < size; i++) {
            try {
                DalvInsn dalvInsn = (DalvInsn) get0(i);
                String strListingString = (dalvInsn.codeSize() != 0 || z) ? dalvInsn.listingString("", 0, z) : null;
                if (strListingString != null) {
                    indentingWriter.write(strListingString);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        indentingWriter.flush();
    }

    public DalvInsn get(int i) {
        return (DalvInsn) get0(i);
    }

    public int getOutsSize() {
        int size = size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            DalvInsn dalvInsn = (DalvInsn) get0(i2);
            if (dalvInsn instanceof CstInsn) {
                Constant constant = ((CstInsn) dalvInsn).getConstant();
                if (constant instanceof CstBaseMethodRef) {
                    int parameterWordCount = ((CstBaseMethodRef) constant).getParameterWordCount(dalvInsn.getOpcode().getFamily() == 113);
                    if (parameterWordCount > i) {
                        i = parameterWordCount;
                    }
                }
            }
        }
        return i;
    }

    public int getRegistersSize() {
        return this.regCount;
    }

    public void set(int i, DalvInsn dalvInsn) {
        set0(i, dalvInsn);
    }

    public void writeTo(AnnotatedOutput annotatedOutput) {
        int cursor = annotatedOutput.getCursor();
        int size = size();
        if (annotatedOutput.annotates()) {
            boolean zIsVerbose = annotatedOutput.isVerbose();
            for (int i = 0; i < size; i++) {
                DalvInsn dalvInsn = (DalvInsn) get0(i);
                int iCodeSize = dalvInsn.codeSize() * 2;
                String strListingString = (iCodeSize != 0 || zIsVerbose) ? dalvInsn.listingString("  ", annotatedOutput.getAnnotationWidth(), true) : null;
                if (strListingString != null) {
                    annotatedOutput.annotate(iCodeSize, strListingString);
                } else if (iCodeSize != 0) {
                    strListingString = "";
                    annotatedOutput.annotate(iCodeSize, strListingString);
                }
            }
        }
        for (int i2 = 0; i2 < size; i2++) {
            DalvInsn dalvInsn2 = (DalvInsn) get0(i2);
            try {
                dalvInsn2.writeTo(annotatedOutput);
            } catch (RuntimeException e) {
                throw ExceptionWithContext.withContext(e, "...while writing " + dalvInsn2);
            }
        }
        int cursor2 = (annotatedOutput.getCursor() - cursor) / 2;
        if (cursor2 == codeSize()) {
            return;
        }
        StringBuilder sbO = a.o("write length mismatch; expected ");
        sbO.append(codeSize());
        sbO.append(" but actually wrote ");
        sbO.append(cursor2);
        throw new RuntimeException(sbO.toString());
    }
}

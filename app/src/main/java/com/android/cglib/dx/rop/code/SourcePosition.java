package com.android.cglib.dx.rop.code;

import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.util.Hex;
import com.baidu.mobstat.Config;

/* JADX INFO: loaded from: classes.dex */
public final class SourcePosition {
    public static final SourcePosition NO_INFO = new SourcePosition(null, -1, -1);
    private final int address;
    private final int line;
    private final CstString sourceFile;

    public SourcePosition(CstString cstString, int i, int i2) {
        if (i < -1) {
            throw new IllegalArgumentException("address < -1");
        }
        if (i2 < -1) {
            throw new IllegalArgumentException("line < -1");
        }
        this.sourceFile = cstString;
        this.address = i;
        this.line = i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SourcePosition) {
            if (this != obj) {
                SourcePosition sourcePosition = (SourcePosition) obj;
                if (this.address != sourcePosition.address || !sameLineAndFile(sourcePosition)) {
                }
            }
            return true;
        }
        return false;
    }

    public int getAddress() {
        return this.address;
    }

    public int getLine() {
        return this.line;
    }

    public CstString getSourceFile() {
        return this.sourceFile;
    }

    public int hashCode() {
        return this.sourceFile.hashCode() + this.address + this.line;
    }

    public boolean sameLine(SourcePosition sourcePosition) {
        return this.line == sourcePosition.line;
    }

    public boolean sameLineAndFile(SourcePosition sourcePosition) {
        CstString cstString;
        CstString cstString2;
        return this.line == sourcePosition.line && ((cstString = this.sourceFile) == (cstString2 = sourcePosition.sourceFile) || (cstString != null && cstString.equals(cstString2)));
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(50);
        CstString cstString = this.sourceFile;
        if (cstString != null) {
            stringBuffer.append(cstString.toHuman());
            stringBuffer.append(Config.TRACE_TODAY_VISIT_SPLIT);
        }
        int i = this.line;
        if (i >= 0) {
            stringBuffer.append(i);
        }
        stringBuffer.append('@');
        int i2 = this.address;
        stringBuffer.append(i2 < 0 ? "????" : Hex.u2(i2));
        return stringBuffer.toString();
    }
}

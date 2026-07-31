package com.android.cglib.dx.dex.code;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.util.FixedSizeList;
import com.android.cglib.dx.util.Hex;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class CatchHandlerList extends FixedSizeList implements Comparable<CatchHandlerList> {
    public static final CatchHandlerList EMPTY = new CatchHandlerList(0);

    public static class Entry implements Comparable<Entry> {
        private final CstType exceptionType;
        private final int handler;

        public Entry(CstType cstType, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("handler < 0");
            }
            Objects.requireNonNull(cstType, "exceptionType == null");
            this.handler = i;
            this.exceptionType = cstType;
        }

        @Override // java.lang.Comparable
        public int compareTo(Entry entry) {
            int i = this.handler;
            int i2 = entry.handler;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            return this.exceptionType.compareTo((Constant) entry.exceptionType);
        }

        public boolean equals(Object obj) {
            return (obj instanceof Entry) && compareTo((Entry) obj) == 0;
        }

        public CstType getExceptionType() {
            return this.exceptionType;
        }

        public int getHandler() {
            return this.handler;
        }

        public int hashCode() {
            return (this.handler * 31) + this.exceptionType.hashCode();
        }
    }

    public CatchHandlerList(int i) {
        super(i);
    }

    public boolean catchesAll() {
        int size = size();
        if (size == 0) {
            return false;
        }
        return get(size - 1).getExceptionType().equals(CstType.OBJECT);
    }

    @Override // java.lang.Comparable
    public int compareTo(CatchHandlerList catchHandlerList) {
        if (this == catchHandlerList) {
            return 0;
        }
        int size = size();
        int size2 = catchHandlerList.size();
        int iMin = Math.min(size, size2);
        for (int i = 0; i < iMin; i++) {
            int iCompareTo = get(i).compareTo(catchHandlerList.get(i));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (size < size2) {
            return -1;
        }
        return size > size2 ? 1 : 0;
    }

    public Entry get(int i) {
        return (Entry) get0(i);
    }

    public void set(int i, Entry entry) {
        set0(i, entry);
    }

    public void set(int i, CstType cstType, int i2) {
        set0(i, new Entry(cstType, i2));
    }

    @Override // com.android.cglib.dx.util.FixedSizeList, com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        return toHuman("", "");
    }

    public String toHuman(String str, String str2) {
        StringBuilder sb = new StringBuilder(100);
        int size = size();
        sb.append(str);
        sb.append(str2);
        sb.append("catch ");
        int i = 0;
        while (i < size) {
            Entry entry = get(i);
            if (i != 0) {
                sb.append(",\n");
                sb.append(str);
                sb.append("  ");
            }
            sb.append((i == size + (-1) && catchesAll()) ? "<any>" : entry.getExceptionType().toHuman());
            sb.append(" -> ");
            sb.append(Hex.u2or4(entry.getHandler()));
            i++;
        }
        return sb.toString();
    }
}

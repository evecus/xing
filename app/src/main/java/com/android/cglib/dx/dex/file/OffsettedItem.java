package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.ExceptionWithContext;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class OffsettedItem extends Item implements Comparable<OffsettedItem> {
    private Section addedTo;
    private final int alignment;
    private int offset;
    private int writeSize;

    public OffsettedItem(int i, int i2) {
        Section.validateAlignment(i);
        if (i2 < -1) {
            throw new IllegalArgumentException("writeSize < -1");
        }
        this.alignment = i;
        this.writeSize = i2;
        this.addedTo = null;
        this.offset = -1;
    }

    public static int getAbsoluteOffsetOr0(OffsettedItem offsettedItem) {
        if (offsettedItem == null) {
            return 0;
        }
        return offsettedItem.getAbsoluteOffset();
    }

    @Override // java.lang.Comparable
    public final int compareTo(OffsettedItem offsettedItem) {
        if (this == offsettedItem) {
            return 0;
        }
        ItemType itemType = itemType();
        ItemType itemType2 = offsettedItem.itemType();
        return itemType != itemType2 ? itemType.compareTo(itemType2) : compareTo0(offsettedItem);
    }

    public int compareTo0(OffsettedItem offsettedItem) {
        throw new UnsupportedOperationException("unsupported");
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            OffsettedItem offsettedItem = (OffsettedItem) obj;
            if (itemType() != offsettedItem.itemType() || compareTo0(offsettedItem) != 0) {
                return false;
            }
        }
        return true;
    }

    public final int getAbsoluteOffset() {
        int i = this.offset;
        if (i >= 0) {
            return this.addedTo.getAbsoluteOffset(i);
        }
        throw new RuntimeException("offset not yet known");
    }

    public final int getAlignment() {
        return this.alignment;
    }

    public final int getRelativeOffset() {
        int i = this.offset;
        if (i >= 0) {
            return i;
        }
        throw new RuntimeException("offset not yet known");
    }

    public final String offsetString() {
        return '[' + Integer.toHexString(getAbsoluteOffset()) + ']';
    }

    public final int place(Section section, int i) {
        Objects.requireNonNull(section, "addedTo == null");
        if (i < 0) {
            throw new IllegalArgumentException("offset < 0");
        }
        if (this.addedTo != null) {
            throw new RuntimeException("already written");
        }
        int i2 = this.alignment - 1;
        int i3 = (~i2) & (i2 + i);
        this.addedTo = section;
        this.offset = i3;
        place0(section, i3);
        return i3;
    }

    public void place0(Section section, int i) {
    }

    public final void setWriteSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("writeSize < 0");
        }
        if (this.writeSize >= 0) {
            throw new UnsupportedOperationException("writeSize already set");
        }
        this.writeSize = i;
    }

    public abstract String toHuman();

    @Override // com.android.cglib.dx.dex.file.Item
    public final int writeSize() {
        int i = this.writeSize;
        if (i >= 0) {
            return i;
        }
        throw new UnsupportedOperationException("writeSize is unknown");
    }

    @Override // com.android.cglib.dx.dex.file.Item
    public final void writeTo(DexFile dexFile, AnnotatedOutput annotatedOutput) {
        annotatedOutput.alignTo(this.alignment);
        try {
            if (this.writeSize < 0) {
                throw new UnsupportedOperationException("writeSize is unknown");
            }
            annotatedOutput.assertCursor(getAbsoluteOffset());
            writeTo0(dexFile, annotatedOutput);
        } catch (RuntimeException e) {
            throw ExceptionWithContext.withContext(e, "...while writing " + this);
        }
    }

    public abstract void writeTo0(DexFile dexFile, AnnotatedOutput annotatedOutput);
}

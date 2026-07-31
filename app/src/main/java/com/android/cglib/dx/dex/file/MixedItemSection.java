package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.util.AnnotatedOutput;
import com.android.cglib.dx.util.ExceptionWithContext;
import com.android.cglib.dx.util.Hex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class MixedItemSection extends Section {
    private static final Comparator<OffsettedItem> TYPE_SORTER = new Comparator<OffsettedItem>() { // from class: com.android.cglib.dx.dex.file.MixedItemSection.1
        @Override // java.util.Comparator
        public int compare(OffsettedItem offsettedItem, OffsettedItem offsettedItem2) {
            return offsettedItem.itemType().compareTo(offsettedItem2.itemType());
        }
    };
    private final HashMap<OffsettedItem, OffsettedItem> interns;
    private final ArrayList<OffsettedItem> items;
    private final SortType sort;
    private int writeSize;

    /* JADX INFO: renamed from: com.android.cglib.dx.dex.file.MixedItemSection$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final int[] $SwitchMap$com$android$cglib$dx$dex$file$MixedItemSection$SortType;

        static {
            SortType.values();
            int[] iArr = new int[3];
            $SwitchMap$com$android$cglib$dx$dex$file$MixedItemSection$SortType = iArr;
            try {
                SortType sortType = SortType.INSTANCE;
                iArr[2] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                int[] iArr2 = $SwitchMap$com$android$cglib$dx$dex$file$MixedItemSection$SortType;
                SortType sortType2 = SortType.TYPE;
                iArr2[1] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum SortType {
        NONE,
        TYPE,
        INSTANCE
    }

    public MixedItemSection(String str, DexFile dexFile, int i, SortType sortType) {
        super(str, dexFile, i);
        this.items = new ArrayList<>(100);
        this.interns = new HashMap<>(100);
        this.sort = sortType;
        this.writeSize = -1;
    }

    public void add(OffsettedItem offsettedItem) {
        throwIfPrepared();
        try {
            if (offsettedItem.getAlignment() > getAlignment()) {
                throw new IllegalArgumentException("incompatible item alignment");
            }
            this.items.add(offsettedItem);
        } catch (NullPointerException e) {
            throw new NullPointerException("item == null");
        }
    }

    public <T extends OffsettedItem> T get(T t) {
        throwIfNotPrepared();
        T t2 = (T) this.interns.get(t);
        if (t2 != null) {
            return t2;
        }
        throw new NoSuchElementException(t.toString());
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public int getAbsoluteItemOffset(Item item) {
        return ((OffsettedItem) item).getAbsoluteOffset();
    }

    public <T extends OffsettedItem> T intern(T t) {
        throwIfPrepared();
        T t2 = (T) this.interns.get(t);
        if (t2 != null) {
            return t2;
        }
        add(t);
        this.interns.put(t, t);
        return t;
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.items;
    }

    public void placeItems() {
        throwIfNotPrepared();
        int iOrdinal = this.sort.ordinal();
        if (iOrdinal == 1) {
            Collections.sort(this.items, TYPE_SORTER);
        } else if (iOrdinal == 2) {
            Collections.sort(this.items);
        }
        int size = this.items.size();
        int i = 0;
        int iWriteSize = 0;
        while (i < size) {
            OffsettedItem offsettedItem = this.items.get(i);
            try {
                int iPlace = offsettedItem.place(this, iWriteSize);
                if (iPlace < iWriteSize) {
                    throw new RuntimeException("bogus place() result for " + offsettedItem);
                }
                i++;
                iWriteSize = offsettedItem.writeSize() + iPlace;
            } catch (RuntimeException e) {
                throw ExceptionWithContext.withContext(e, "...while placing " + offsettedItem);
            }
        }
        this.writeSize = iWriteSize;
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public void prepare0() {
        DexFile file = getFile();
        int i = 0;
        while (true) {
            int size = this.items.size();
            if (i >= size) {
                return;
            }
            while (i < size) {
                this.items.get(i).addContents(file);
                i++;
            }
        }
    }

    public int size() {
        return this.items.size();
    }

    public void writeHeaderPart(AnnotatedOutput annotatedOutput) {
        throwIfNotPrepared();
        int i = this.writeSize;
        if (i == -1) {
            throw new RuntimeException("write size not yet set");
        }
        int fileOffset = i == 0 ? 0 : getFileOffset();
        String name = getName();
        if (name == null) {
            name = "<unnamed>";
        }
        char[] cArr = new char[15 - name.length()];
        Arrays.fill(cArr, ' ');
        String str = new String(cArr);
        if (annotatedOutput.annotates()) {
            StringBuilder sbE = a.e(name, "_size:", str);
            sbE.append(Hex.u4(i));
            annotatedOutput.annotate(4, sbE.toString());
            annotatedOutput.annotate(4, name + "_off: " + str + Hex.u4(fileOffset));
        }
        annotatedOutput.writeInt(i);
        annotatedOutput.writeInt(fileOffset);
    }

    public void writeIndexAnnotation(AnnotatedOutput annotatedOutput, ItemType itemType, String str) {
        throwIfNotPrepared();
        TreeMap treeMap = new TreeMap();
        for (OffsettedItem offsettedItem : this.items) {
            if (offsettedItem.itemType() == itemType) {
                treeMap.put(offsettedItem.toHuman(), offsettedItem);
            }
        }
        if (treeMap.size() == 0) {
            return;
        }
        annotatedOutput.annotate(0, str);
        for (Map.Entry entry : treeMap.entrySet()) {
            annotatedOutput.annotate(0, ((OffsettedItem) entry.getValue()).offsetString() + ' ' + ((String) entry.getKey()) + '\n');
        }
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public int writeSize() {
        throwIfNotPrepared();
        return this.writeSize;
    }

    @Override // com.android.cglib.dx.dex.file.Section
    public void writeTo0(AnnotatedOutput annotatedOutput) {
        boolean zAnnotates = annotatedOutput.annotates();
        DexFile file = getFile();
        boolean z = true;
        int iWriteSize = 0;
        for (OffsettedItem offsettedItem : this.items) {
            if (zAnnotates) {
                if (z) {
                    z = false;
                } else {
                    annotatedOutput.annotate(0, "\n");
                }
            }
            int alignment = offsettedItem.getAlignment() - 1;
            int i = (~alignment) & (alignment + iWriteSize);
            if (iWriteSize != i) {
                annotatedOutput.writeZeroes(i - iWriteSize);
                iWriteSize = i;
            }
            offsettedItem.writeTo(file, annotatedOutput);
            iWriteSize += offsettedItem.writeSize();
        }
        if (iWriteSize != this.writeSize) {
            throw new RuntimeException("output size mismatch");
        }
    }
}

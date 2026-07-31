package com.android.cglib.dx.dex.file;

import com.android.cglib.dx.util.AnnotatedOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Statistics {
    private final HashMap<String, Data> dataMap = new HashMap<>(50);

    public static class Data {
        private int count;
        private int largestSize;
        private final String name;
        private int smallestSize;
        private int totalSize;

        public Data(Item item, String str) {
            int iWriteSize = item.writeSize();
            this.name = str;
            this.count = 1;
            this.totalSize = iWriteSize;
            this.largestSize = iWriteSize;
            this.smallestSize = iWriteSize;
        }

        public void add(Item item) {
            int iWriteSize = item.writeSize();
            this.count++;
            this.totalSize += iWriteSize;
            if (iWriteSize > this.largestSize) {
                this.largestSize = iWriteSize;
            }
            if (iWriteSize < this.smallestSize) {
                this.smallestSize = iWriteSize;
            }
        }

        public String toHuman() {
            String string;
            StringBuilder sb = new StringBuilder();
            StringBuilder sbO = a.o("  ");
            sbO.append(this.name);
            sbO.append(": ");
            sbO.append(this.count);
            sbO.append(" item");
            sbO.append(this.count == 1 ? "" : "s");
            sbO.append("; ");
            sbO.append(this.totalSize);
            sbO.append(" bytes total\n");
            sb.append(sbO.toString());
            if (this.smallestSize == this.largestSize) {
                StringBuilder sbO2 = a.o("    ");
                sbO2.append(this.smallestSize);
                sbO2.append(" bytes/item\n");
                string = sbO2.toString();
            } else {
                int i = this.totalSize / this.count;
                StringBuilder sbO3 = a.o("    ");
                sbO3.append(this.smallestSize);
                sbO3.append("..");
                sbO3.append(this.largestSize);
                sbO3.append(" bytes/item; average ");
                sbO3.append(i);
                sbO3.append("\n");
                string = sbO3.toString();
            }
            sb.append(string);
            return sb.toString();
        }

        public void writeAnnotation(AnnotatedOutput annotatedOutput) {
            annotatedOutput.annotate(toHuman());
        }
    }

    public void add(Item item) {
        String strTypeName = item.typeName();
        Data data = this.dataMap.get(strTypeName);
        if (data == null) {
            this.dataMap.put(strTypeName, new Data(item, strTypeName));
        } else {
            data.add(item);
        }
    }

    public void addAll(Section section) {
        Iterator<? extends Item> it = section.items().iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    public String toHuman() {
        StringBuilder sbO = a.o("Statistics:\n");
        TreeMap treeMap = new TreeMap();
        for (Data data : this.dataMap.values()) {
            treeMap.put(data.name, data);
        }
        Iterator it = treeMap.values().iterator();
        while (it.hasNext()) {
            sbO.append(((Data) it.next()).toHuman());
        }
        return sbO.toString();
    }

    public final void writeAnnotation(AnnotatedOutput annotatedOutput) {
        if (this.dataMap.size() == 0) {
            return;
        }
        annotatedOutput.annotate(0, "\nstatistics:\n");
        TreeMap treeMap = new TreeMap();
        for (Data data : this.dataMap.values()) {
            treeMap.put(data.name, data);
        }
        Iterator it = treeMap.values().iterator();
        while (it.hasNext()) {
            ((Data) it.next()).writeAnnotation(annotatedOutput);
        }
    }
}

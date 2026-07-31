package com.android.cglib.dx.rop.annotation;

import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.util.MutabilityControl;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Annotations extends MutabilityControl implements Comparable<Annotations> {
    public static final Annotations EMPTY;
    private final TreeMap<CstType, Annotation> annotations = new TreeMap<>();

    static {
        Annotations annotations = new Annotations();
        EMPTY = annotations;
        annotations.setImmutable();
    }

    public static Annotations combine(Annotations annotations, Annotation annotation) {
        Annotations annotations2 = new Annotations();
        annotations2.addAll(annotations);
        annotations2.add(annotation);
        annotations2.setImmutable();
        return annotations2;
    }

    public static Annotations combine(Annotations annotations, Annotations annotations2) {
        Annotations annotations3 = new Annotations();
        annotations3.addAll(annotations);
        annotations3.addAll(annotations2);
        annotations3.setImmutable();
        return annotations3;
    }

    public void add(Annotation annotation) {
        throwIfImmutable();
        Objects.requireNonNull(annotation, "annotation == null");
        CstType type = annotation.getType();
        if (!this.annotations.containsKey(type)) {
            this.annotations.put(type, annotation);
        } else {
            StringBuilder sbO = a.o("duplicate type: ");
            sbO.append(type.toHuman());
            throw new IllegalArgumentException(sbO.toString());
        }
    }

    public void addAll(Annotations annotations) {
        throwIfImmutable();
        Objects.requireNonNull(annotations, "toAdd == null");
        Iterator<Annotation> it = annotations.annotations.values().iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Annotations annotations) {
        Iterator<Annotation> it = this.annotations.values().iterator();
        Iterator<Annotation> it2 = annotations.annotations.values().iterator();
        while (it.hasNext() && it2.hasNext()) {
            int iCompareTo = it.next().compareTo(it2.next());
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (it.hasNext()) {
            return 1;
        }
        return it2.hasNext() ? -1 : 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Annotations) {
            return this.annotations.equals(((Annotations) obj).annotations);
        }
        return false;
    }

    public Collection<Annotation> getAnnotations() {
        return Collections.unmodifiableCollection(this.annotations.values());
    }

    public int hashCode() {
        return this.annotations.hashCode();
    }

    public int size() {
        return this.annotations.size();
    }

    public String toString() {
        StringBuilder sbO = a.o("annotations{");
        boolean z = true;
        for (Annotation annotation : this.annotations.values()) {
            if (z) {
                z = false;
            } else {
                sbO.append(", ");
            }
            sbO.append(annotation.toHuman());
        }
        sbO.append("}");
        return sbO.toString();
    }
}

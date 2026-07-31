package com.android.cglib.dx.rop.annotation;

import com.android.cglib.dx.rop.cst.Constant;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.cst.CstType;
import com.android.cglib.dx.util.MutabilityControl;
import com.android.cglib.dx.util.ToHuman;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class Annotation extends MutabilityControl implements Comparable<Annotation>, ToHuman {
    private final TreeMap<CstString, NameValuePair> elements;
    private final CstType type;
    private final AnnotationVisibility visibility;

    public Annotation(CstType cstType, AnnotationVisibility annotationVisibility) {
        Objects.requireNonNull(cstType, "type == null");
        Objects.requireNonNull(annotationVisibility, "visibility == null");
        this.type = cstType;
        this.visibility = annotationVisibility;
        this.elements = new TreeMap<>();
    }

    public void add(NameValuePair nameValuePair) {
        throwIfImmutable();
        Objects.requireNonNull(nameValuePair, "pair == null");
        CstString name = nameValuePair.getName();
        if (this.elements.get(name) == null) {
            this.elements.put(name, nameValuePair);
            return;
        }
        throw new IllegalArgumentException("name already added: " + name);
    }

    @Override // java.lang.Comparable
    public int compareTo(Annotation annotation) {
        int iCompareTo = this.type.compareTo((Constant) annotation.type);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompareTo2 = this.visibility.compareTo(annotation.visibility);
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        Iterator<NameValuePair> it = this.elements.values().iterator();
        Iterator<NameValuePair> it2 = annotation.elements.values().iterator();
        while (it.hasNext() && it2.hasNext()) {
            int iCompareTo3 = it.next().compareTo(it2.next());
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        if (it.hasNext()) {
            return 1;
        }
        return it2.hasNext() ? -1 : 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Annotation) {
            Annotation annotation = (Annotation) obj;
            if (this.type.equals(annotation.type) && this.visibility == annotation.visibility) {
                return this.elements.equals(annotation.elements);
            }
        }
        return false;
    }

    public Collection<NameValuePair> getNameValuePairs() {
        return Collections.unmodifiableCollection(this.elements.values());
    }

    public CstType getType() {
        return this.type;
    }

    public AnnotationVisibility getVisibility() {
        return this.visibility;
    }

    public int hashCode() {
        return (((this.type.hashCode() * 31) + this.elements.hashCode()) * 31) + this.visibility.hashCode();
    }

    public void put(NameValuePair nameValuePair) {
        throwIfImmutable();
        Objects.requireNonNull(nameValuePair, "pair == null");
        this.elements.put(nameValuePair.getName(), nameValuePair);
    }

    @Override // com.android.cglib.dx.util.ToHuman
    public String toHuman() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.visibility.toHuman());
        sb.append("-annotation ");
        sb.append(this.type.toHuman());
        sb.append(" {");
        boolean z = true;
        for (NameValuePair nameValuePair : this.elements.values()) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(nameValuePair.getName().toHuman());
            sb.append(": ");
            sb.append(nameValuePair.getValue().toHuman());
        }
        sb.append("}");
        return sb.toString();
    }

    public String toString() {
        return toHuman();
    }
}

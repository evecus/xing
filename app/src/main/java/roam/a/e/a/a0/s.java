package roam.a.e.a.a0;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class s<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final Comparator<Comparable> h = new a();
    public Comparator<? super K> a;
    public e<K, V> b;
    public int c;
    public int d;
    public final e<K, V> e;
    public s<K, V>.b f;
    public s<K, V>.c g;

    public class a implements Comparator<Comparable> {
        @Override // java.util.Comparator
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    public class b extends AbstractSet<Map.Entry<K, V>> {
        public final s a;

        public class a extends s<K, V>.d<Map.Entry<K, V>> {
            public a(b bVar) {
                super(bVar.a);
            }

            @Override // java.util.Iterator
            public Object next() {
                return a();
            }
        }

        public b(s sVar) {
            this.a = sVar;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.a.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && this.a.b((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            e<K, V> eVarB;
            if (!(obj instanceof Map.Entry) || (eVarB = this.a.b((Map.Entry) obj)) == null) {
                return false;
            }
            this.a.e(eVarB, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.a.c;
        }
    }

    public final class c extends AbstractSet<K> {
        public final s a;

        public class a extends s<K, V>.d<K> {
            public a(c cVar) {
                super(cVar.a);
            }

            @Override // java.util.Iterator
            public K next() {
                return a().f;
            }
        }

        public c(s sVar) {
            this.a = sVar;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.a.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.a.c(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            s sVar = this.a;
            e<K, V> eVarC = sVar.c(obj);
            if (eVarC != null) {
                sVar.e(eVarC, true);
            }
            return eVarC != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.a.c;
        }
    }

    public abstract class d<T> implements Iterator<T> {
        public e<K, V> a;
        public e<K, V> b = null;
        public int c;
        public final s d;

        public d(s sVar) {
            this.d = sVar;
            this.a = sVar.e.d;
            this.c = sVar.d;
        }

        public final e<K, V> a() {
            e<K, V> eVar = this.a;
            s sVar = this.d;
            if (eVar == sVar.e) {
                throw new NoSuchElementException();
            }
            if (sVar.d != this.c) {
                throw new ConcurrentModificationException();
            }
            this.a = eVar.d;
            this.b = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.a != this.d.e;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.b;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            this.d.e(eVar, true);
            this.b = null;
            this.c = this.d.d;
        }
    }

    public static final class e<K, V> implements Map.Entry<K, V> {
        public e<K, V> a;
        public e<K, V> b;
        public e<K, V> c;
        public e<K, V> d;
        public e<K, V> e;
        public final K f;
        public V g;
        public int h;

        public e() {
            this.f = null;
            this.e = this;
            this.d = this;
        }

        public e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.a = eVar;
            this.f = k;
            this.h = 1;
            this.d = eVar2;
            this.e = eVar3;
            eVar3.d = this;
            eVar2.e = this;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                K k = this.f;
                if (k != null ? k.equals(entry.getKey()) : entry.getKey() == null) {
                    V v = this.g;
                    Object value = entry.getValue();
                    if (v != null ? v.equals(value) : value == null) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.g;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.f;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.g;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.g;
            this.g = v;
            return v2;
        }

        public String toString() {
            return this.f + "=" + this.g;
        }
    }

    public s() {
        Comparator<Comparable> comparator = h;
        this.c = 0;
        this.d = 0;
        this.e = new e<>();
        this.a = comparator;
    }

    public e<K, V> a(K k, boolean z) {
        int iCompareTo;
        e<K, V> eVar;
        Comparator<Comparable> comparator = h;
        Comparator<? super K> comparator2 = this.a;
        e<K, V> eVar2 = this.b;
        if (eVar2 != null) {
            Comparable comparable = comparator2 == comparator ? (Comparable) k : null;
            while (true) {
                K k2 = eVar2.f;
                iCompareTo = comparable != null ? comparable.compareTo(k2) : comparator2.compare(k, k2);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = iCompareTo < 0 ? eVar2.b : eVar2.c;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        e<K, V> eVar4 = this.e;
        if (eVar2 != null) {
            eVar = new e<>(eVar2, k, eVar4, eVar4.e);
            if (iCompareTo < 0) {
                eVar2.b = eVar;
            } else {
                eVar2.c = eVar;
            }
            d(eVar2, true);
        } else {
            if (comparator2 == comparator && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k, eVar4, eVar4.e);
            this.b = eVar;
        }
        this.c++;
        this.d++;
        return eVar;
    }

    public e<K, V> b(Map.Entry<?, ?> entry) {
        e<K, V> eVarC = c(entry.getKey());
        if (eVarC != null) {
            V v = eVarC.g;
            Object value = entry.getValue();
            if (v == value) {
                return eVarC;
            }
            if (v != null && v.equals(value)) {
                return eVarC;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e<K, V> c(Object obj) {
        if (obj != 0) {
            try {
                return a(obj, false);
            } catch (ClassCastException e2) {
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.b = null;
        this.c = 0;
        this.d++;
        e<K, V> eVar = this.e;
        eVar.e = eVar;
        eVar.d = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return c(obj) != null;
    }

    public final void d(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.b;
            e<K, V> eVar3 = eVar.c;
            int i = eVar2 != null ? eVar2.h : 0;
            int i2 = eVar3 != null ? eVar3.h : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                e<K, V> eVar4 = eVar3.b;
                e<K, V> eVar5 = eVar3.c;
                int i4 = (eVar4 != null ? eVar4.h : 0) - (eVar5 != null ? eVar5.h : 0);
                if (i4 != -1 && (i4 != 0 || z)) {
                    h(eVar3);
                }
                g(eVar);
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                e<K, V> eVar6 = eVar2.b;
                e<K, V> eVar7 = eVar2.c;
                int i5 = (eVar6 != null ? eVar6.h : 0) - (eVar7 != null ? eVar7.h : 0);
                if (i5 != 1 && (i5 != 0 || z)) {
                    g(eVar2);
                }
                h(eVar);
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                eVar.h = i + 1;
                if (z) {
                    return;
                }
            } else {
                eVar.h = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.a;
        }
    }

    public void e(e<K, V> eVar, boolean z) {
        int i;
        if (z) {
            e<K, V> eVar2 = eVar.e;
            eVar2.d = eVar.d;
            eVar.d.e = eVar2;
        }
        e<K, V> eVar3 = eVar.b;
        e<K, V> eVar4 = eVar.c;
        e<K, V> eVar5 = eVar.a;
        int i2 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                f(eVar, eVar3);
                eVar.b = null;
            } else if (eVar4 != null) {
                f(eVar, eVar4);
                eVar.c = null;
            } else {
                f(eVar, null);
            }
            d(eVar5, false);
            this.c--;
            this.d++;
            return;
        }
        if (eVar3.h > eVar4.h) {
            while (true) {
                e<K, V> eVar6 = eVar3.c;
                if (eVar6 == null) {
                    break;
                } else {
                    eVar3 = eVar6;
                }
            }
        } else {
            while (true) {
                e<K, V> eVar7 = eVar4.b;
                if (eVar7 == null) {
                    break;
                } else {
                    eVar4 = eVar7;
                }
            }
            eVar3 = eVar4;
        }
        e(eVar3, false);
        e<K, V> eVar8 = eVar.b;
        if (eVar8 != null) {
            i = eVar8.h;
            eVar3.b = eVar8;
            eVar8.a = eVar3;
            eVar.b = null;
        } else {
            i = 0;
        }
        e<K, V> eVar9 = eVar.c;
        if (eVar9 != null) {
            i2 = eVar9.h;
            eVar3.c = eVar9;
            eVar9.a = eVar3;
            eVar.c = null;
        }
        eVar3.h = Math.max(i, i2) + 1;
        f(eVar, eVar3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        s<K, V>.b bVar = this.f;
        if (bVar != null) {
            return bVar;
        }
        s<K, V>.b bVar2 = new b(this);
        this.f = bVar2;
        return bVar2;
    }

    public final void f(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.a;
        eVar.a = null;
        if (eVar2 != null) {
            eVar2.a = eVar3;
        }
        if (eVar3 == null) {
            this.b = eVar2;
        } else if (eVar3.b == eVar) {
            eVar3.b = eVar2;
        } else {
            eVar3.c = eVar2;
        }
    }

    public final void g(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.b;
        e<K, V> eVar3 = eVar.c;
        e<K, V> eVar4 = eVar3.b;
        e<K, V> eVar5 = eVar3.c;
        eVar.c = eVar4;
        if (eVar4 != null) {
            eVar4.a = eVar;
        }
        f(eVar, eVar3);
        eVar3.b = eVar;
        eVar.a = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.h : 0, eVar4 != null ? eVar4.h : 0) + 1;
        eVar.h = iMax;
        eVar3.h = Math.max(iMax, eVar5 != null ? eVar5.h : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        e<K, V> eVarC = c(obj);
        if (eVarC != null) {
            return eVarC.g;
        }
        return null;
    }

    public final void h(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.b;
        e<K, V> eVar3 = eVar.c;
        e<K, V> eVar4 = eVar2.b;
        e<K, V> eVar5 = eVar2.c;
        eVar.b = eVar5;
        if (eVar5 != null) {
            eVar5.a = eVar;
        }
        f(eVar, eVar2);
        eVar2.c = eVar;
        eVar.a = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.h : 0, eVar5 != null ? eVar5.h : 0) + 1;
        eVar.h = iMax;
        eVar2.h = Math.max(iMax, eVar4 != null ? eVar4.h : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        s<K, V>.c cVar = this.g;
        if (cVar != null) {
            return cVar;
        }
        s<K, V>.c cVar2 = new c(this);
        this.g = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        Objects.requireNonNull(k, "key == null");
        e<K, V> eVarA = a(k, true);
        V v2 = eVarA.g;
        eVarA.g = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> eVarC = c(obj);
        if (eVarC != null) {
            e(eVarC, true);
        }
        if (eVarC != null) {
            return eVarC.g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.c;
    }
}

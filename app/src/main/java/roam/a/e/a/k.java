package roam.a.e.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class k extends n implements Iterable<n> {
    public final List<n> a = new ArrayList();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof k) && ((k) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator<n> iterator() {
        return this.a.iterator();
    }
}

package roam.a.e.a;

/* JADX INFO: loaded from: classes.dex */
public final class q extends n {
    public final roam.a.e.a.a0.s<String, n> a = new roam.a.e.a.a0.s<>();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof q) && ((q) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}

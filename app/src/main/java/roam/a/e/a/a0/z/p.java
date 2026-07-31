package roam.a.e.a.a0.z;

import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public class p implements y {
    public final Class a;
    public final x b;

    public p(Class cls, x xVar) {
        this.a = cls;
        this.b = xVar;
    }

    @Override // roam.a.e.a.y
    public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
        if (aVar.a == this.a) {
            return this.b;
        }
        return null;
    }

    public String toString() {
        StringBuilder sbO = roam.a.b.a.a.a.o("Factory[type=");
        sbO.append(this.a.getName());
        sbO.append(",adapter=");
        sbO.append(this.b);
        sbO.append("]");
        return sbO.toString();
    }
}

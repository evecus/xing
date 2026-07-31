package roam.a.e.a.a0.z;

import com.google.android.material.badge.BadgeDrawable;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public class q implements y {
    public final Class a;
    public final Class b;
    public final x c;

    public q(Class cls, Class cls2, x xVar) {
        this.a = cls;
        this.b = cls2;
        this.c = xVar;
    }

    @Override // roam.a.e.a.y
    public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
        Class<? super T> cls = aVar.a;
        if (cls == this.a || cls == this.b) {
            return this.c;
        }
        return null;
    }

    public String toString() {
        StringBuilder sbO = roam.a.b.a.a.a.o("Factory[type=");
        sbO.append(this.b.getName());
        sbO.append(BadgeDrawable.DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX);
        sbO.append(this.a.getName());
        sbO.append(",adapter=");
        sbO.append(this.c);
        sbO.append("]");
        return sbO.toString();
    }
}

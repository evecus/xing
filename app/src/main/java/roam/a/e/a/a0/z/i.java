package roam.a.e.a.a0.z;

import java.lang.reflect.Field;
import roam.a.e.a.a0.z.j;
import roam.a.e.a.x;

/* JADX INFO: loaded from: classes.dex */
public class i extends j.b {
    public final Field d;
    public final boolean e;
    public final x f;
    public final roam.a.e.a.i g;
    public final roam.a.e.a.b0.a h;
    public final boolean i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(j jVar, String str, boolean z, boolean z2, Field field, boolean z3, x xVar, roam.a.e.a.i iVar, roam.a.e.a.b0.a aVar, boolean z4) {
        super(str, z, z2);
        this.d = field;
        this.e = z3;
        this.f = xVar;
        this.g = iVar;
        this.h = aVar;
        this.i = z4;
    }

    @Override // roam.a.e.a.a0.z.j.b
    public void a(roam.a.e.a.c0.a aVar, Object obj) throws IllegalAccessException {
        Object objA = this.f.a(aVar);
        if (objA == null && this.i) {
            return;
        }
        this.d.set(obj, objA);
    }

    @Override // roam.a.e.a.a0.z.j.b
    public void b(roam.a.e.a.c0.c cVar, Object obj) throws IllegalAccessException {
        (this.e ? this.f : new n(this.g, this.f, this.h.b)).b(cVar, this.d.get(obj));
    }

    @Override // roam.a.e.a.a0.z.j.b
    public boolean c(Object obj) {
        return this.b && this.d.get(obj) != obj;
    }
}

package roam.a.e.a.a0.z;

import roam.a.e.a.u;
import roam.a.e.a.w;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class d implements y {
    public final roam.a.e.a.a0.g a;

    public d(roam.a.e.a.a0.g gVar) {
        this.a = gVar;
    }

    @Override // roam.a.e.a.y
    public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
        roam.a.e.a.z.a aVar2 = (roam.a.e.a.z.a) aVar.a.getAnnotation(roam.a.e.a.z.a.class);
        if (aVar2 == null) {
            return null;
        }
        return (x<T>) b(this.a, iVar, aVar, aVar2);
    }

    public x<?> b(roam.a.e.a.a0.g gVar, roam.a.e.a.i iVar, roam.a.e.a.b0.a<?> aVar, roam.a.e.a.z.a aVar2) {
        x<?> mVar;
        Object objA = gVar.a(new roam.a.e.a.b0.a(aVar2.value())).a();
        if (objA instanceof x) {
            mVar = (x) objA;
        } else if (objA instanceof y) {
            mVar = ((y) objA).a(iVar, aVar);
        } else {
            boolean z = objA instanceof u;
            if (!z && !(objA instanceof roam.a.e.a.m)) {
                StringBuilder sbO = roam.a.b.a.a.a.o("Invalid attempt to bind an instance of ");
                sbO.append(objA.getClass().getName());
                sbO.append(" as a @JsonAdapter for ");
                sbO.append(aVar.toString());
                sbO.append(". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
                throw new IllegalArgumentException(sbO.toString());
            }
            mVar = new m<>(z ? (u) objA : null, objA instanceof roam.a.e.a.m ? (roam.a.e.a.m) objA : null, iVar, aVar, null);
        }
        return (mVar == null || !aVar2.nullSafe()) ? mVar : new w(mVar);
    }
}

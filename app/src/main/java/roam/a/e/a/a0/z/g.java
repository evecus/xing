package roam.a.e.a.a0.z;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import roam.a.e.a.a0.t;
import roam.a.e.a.c0.a;
import roam.a.e.a.v;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class g implements y {
    public final roam.a.e.a.a0.g a;
    public final boolean b;

    public final class a<K, V> extends x<Map<K, V>> {
        public final x<K> a;
        public final x<V> b;
        public final t<? extends Map<K, V>> c;
        public final g d;

        public a(g gVar, roam.a.e.a.i iVar, Type type, x<K> xVar, Type type2, x<V> xVar2, t<? extends Map<K, V>> tVar) {
            this.d = gVar;
            this.a = new n(iVar, xVar, type);
            this.b = new n(iVar, xVar2, type2);
            this.c = tVar;
        }

        @Override // roam.a.e.a.x
        public Object a(roam.a.e.a.c0.a aVar) throws IOException {
            int i;
            roam.a.e.a.c0.b bVarV = aVar.v();
            if (bVarV == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            Map<K, V> mapA = this.c.a();
            if (bVarV == roam.a.e.a.c0.b.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.i()) {
                    aVar.a();
                    K kA = this.a.a(aVar);
                    if (mapA.put(kA, this.b.a(aVar)) != null) {
                        throw new v("duplicate key: " + kA);
                    }
                    aVar.e();
                }
                aVar.e();
            } else {
                aVar.b();
                while (aVar.i()) {
                    Objects.requireNonNull((a.C0020a) roam.a.e.a.a0.q.a);
                    if (aVar instanceof e) {
                        e eVar = (e) aVar;
                        eVar.C(roam.a.e.a.c0.b.NAME);
                        Map.Entry entry = (Map.Entry) ((Iterator) eVar.D()).next();
                        eVar.F(entry.getValue());
                        eVar.F(new roam.a.e.a.s((String) entry.getKey()));
                    } else {
                        int iD = aVar.h;
                        if (iD == 0) {
                            iD = aVar.d();
                        }
                        if (iD == 13) {
                            i = 9;
                        } else if (iD == 12) {
                            i = 8;
                        } else {
                            if (iD != 14) {
                                StringBuilder sbO = roam.a.b.a.a.a.o("Expected a name but was ");
                                sbO.append(aVar.v());
                                sbO.append(aVar.k());
                                throw new IllegalStateException(sbO.toString());
                            }
                            i = 10;
                        }
                        aVar.h = i;
                    }
                    K kA2 = this.a.a(aVar);
                    if (mapA.put(kA2, this.b.a(aVar)) != null) {
                        throw new v("duplicate key: " + kA2);
                    }
                }
                aVar.f();
            }
            return mapA;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Object obj) {
            String strD;
            Map map = (Map) obj;
            if (map == null) {
                cVar.i();
                return;
            }
            if (this.d.b) {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i = 0;
                boolean z = false;
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    x<K> xVar = this.a;
                    K key = entry.getKey();
                    Objects.requireNonNull(xVar);
                    try {
                        f fVar = new f();
                        xVar.b(fVar, key);
                        if (!fVar.l.isEmpty()) {
                            throw new IllegalStateException("Expected one JSON element but was " + fVar.l);
                        }
                        roam.a.e.a.n nVar = fVar.n;
                        arrayList.add(nVar);
                        arrayList2.add(entry.getValue());
                        Objects.requireNonNull(nVar);
                        z |= (nVar instanceof roam.a.e.a.k) || (nVar instanceof roam.a.e.a.q);
                    } catch (IOException e) {
                        throw new roam.a.e.a.o(e);
                    }
                }
                if (z) {
                    cVar.b();
                    int size = arrayList.size();
                    while (i < size) {
                        cVar.b();
                        o.X.b(cVar, (roam.a.e.a.n) arrayList.get(i));
                        this.b.b(cVar, (V) arrayList2.get(i));
                        cVar.e();
                        i++;
                    }
                    cVar.e();
                    return;
                }
                cVar.c();
                int size2 = arrayList.size();
                while (i < size2) {
                    roam.a.e.a.n nVar2 = (roam.a.e.a.n) arrayList.get(i);
                    Objects.requireNonNull(nVar2);
                    if (nVar2 instanceof roam.a.e.a.s) {
                        roam.a.e.a.s sVarA = nVar2.a();
                        Object obj2 = sVarA.a;
                        if (obj2 instanceof Number) {
                            strD = String.valueOf(sVarA.c());
                        } else if (obj2 instanceof Boolean) {
                            strD = Boolean.toString(sVarA.b());
                        } else {
                            if (!(obj2 instanceof String)) {
                                throw new AssertionError();
                            }
                            strD = sVarA.d();
                        }
                    } else {
                        if (!(nVar2 instanceof roam.a.e.a.p)) {
                            throw new AssertionError();
                        }
                        strD = "null";
                    }
                    cVar.g(strD);
                    this.b.b(cVar, (V) arrayList2.get(i));
                    i++;
                }
            } else {
                cVar.c();
                for (Map.Entry<K, V> entry2 : map.entrySet()) {
                    cVar.g(String.valueOf(entry2.getKey()));
                    this.b.b(cVar, entry2.getValue());
                }
            }
            cVar.f();
        }
    }

    public g(roam.a.e.a.a0.g gVar, boolean z) {
        this.a = gVar;
        this.b = z;
    }

    @Override // roam.a.e.a.y
    public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
        Type[] actualTypeArguments;
        Type type = aVar.b;
        if (!Map.class.isAssignableFrom(aVar.a)) {
            return null;
        }
        Class<?> clsE = roam.a.e.a.a0.a.e(type);
        if (type == Properties.class) {
            actualTypeArguments = new Type[]{String.class, String.class};
        } else {
            Type typeF = roam.a.e.a.a0.a.f(type, clsE, Map.class);
            actualTypeArguments = typeF instanceof ParameterizedType ? ((ParameterizedType) typeF).getActualTypeArguments() : new Type[]{Object.class, Object.class};
        }
        Type type2 = actualTypeArguments[0];
        return new a(this, iVar, actualTypeArguments[0], (type2 == Boolean.TYPE || type2 == Boolean.class) ? o.f : iVar.c(new roam.a.e.a.b0.a<>(type2)), actualTypeArguments[1], iVar.c(new roam.a.e.a.b0.a<>(actualTypeArguments[1])), this.a.a(aVar));
    }
}

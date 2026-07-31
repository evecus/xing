package roam.a.e.a;

import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static final roam.a.e.a.b0.a<?> g = new roam.a.e.a.b0.a<>(Object.class);
    public final ThreadLocal<Map<roam.a.e.a.b0.a<?>, a<?>>> a;
    public final Map<roam.a.e.a.b0.a<?>, x<?>> b;
    public final roam.a.e.a.a0.g c;
    public final roam.a.e.a.a0.z.d d;
    public final List<y> e;
    public final boolean f;

    public static class a<T> extends x<T> {
        public x<T> a;

        @Override // roam.a.e.a.x
        public T a(roam.a.e.a.c0.a aVar) {
            x<T> xVar = this.a;
            if (xVar != null) {
                return xVar.a(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, T t) {
            x<T> xVar = this.a;
            if (xVar == null) {
                throw new IllegalStateException();
            }
            xVar.b(cVar, t);
        }
    }

    public i() {
        roam.a.e.a.a0.o oVar = roam.a.e.a.a0.o.c;
        c cVar = c.a;
        Map mapEmptyMap = Collections.emptyMap();
        Collections.emptyList();
        Collections.emptyList();
        List listEmptyList = Collections.emptyList();
        this.a = new ThreadLocal<>();
        this.b = new ConcurrentHashMap();
        roam.a.e.a.a0.g gVar = new roam.a.e.a.a0.g(mapEmptyMap);
        this.c = gVar;
        this.f = true;
        ArrayList arrayList = new ArrayList();
        arrayList.add(roam.a.e.a.a0.z.o.Y);
        arrayList.add(roam.a.e.a.a0.z.h.b);
        arrayList.add(oVar);
        arrayList.addAll(listEmptyList);
        arrayList.add(roam.a.e.a.a0.z.o.D);
        arrayList.add(roam.a.e.a.a0.z.o.m);
        arrayList.add(roam.a.e.a.a0.z.o.g);
        arrayList.add(roam.a.e.a.a0.z.o.i);
        arrayList.add(roam.a.e.a.a0.z.o.k);
        x<Number> xVar = roam.a.e.a.a0.z.o.t;
        arrayList.add(new roam.a.e.a.a0.z.q(Long.TYPE, Long.class, xVar));
        arrayList.add(new roam.a.e.a.a0.z.q(Double.TYPE, Double.class, new e(this)));
        arrayList.add(new roam.a.e.a.a0.z.q(Float.TYPE, Float.class, new f(this)));
        arrayList.add(roam.a.e.a.a0.z.o.x);
        arrayList.add(roam.a.e.a.a0.z.o.o);
        arrayList.add(roam.a.e.a.a0.z.o.q);
        arrayList.add(new roam.a.e.a.a0.z.p(AtomicLong.class, new w(new g(xVar))));
        arrayList.add(new roam.a.e.a.a0.z.p(AtomicLongArray.class, new w(new h(xVar))));
        arrayList.add(roam.a.e.a.a0.z.o.s);
        arrayList.add(roam.a.e.a.a0.z.o.z);
        arrayList.add(roam.a.e.a.a0.z.o.F);
        arrayList.add(roam.a.e.a.a0.z.o.H);
        arrayList.add(new roam.a.e.a.a0.z.p(BigDecimal.class, roam.a.e.a.a0.z.o.B));
        arrayList.add(new roam.a.e.a.a0.z.p(BigInteger.class, roam.a.e.a.a0.z.o.C));
        arrayList.add(roam.a.e.a.a0.z.o.J);
        arrayList.add(roam.a.e.a.a0.z.o.L);
        arrayList.add(roam.a.e.a.a0.z.o.P);
        arrayList.add(roam.a.e.a.a0.z.o.R);
        arrayList.add(roam.a.e.a.a0.z.o.W);
        arrayList.add(roam.a.e.a.a0.z.o.N);
        arrayList.add(roam.a.e.a.a0.z.o.d);
        arrayList.add(roam.a.e.a.a0.z.c.b);
        arrayList.add(roam.a.e.a.a0.z.o.U);
        arrayList.add(roam.a.e.a.a0.z.l.b);
        arrayList.add(roam.a.e.a.a0.z.k.b);
        arrayList.add(roam.a.e.a.a0.z.o.S);
        arrayList.add(roam.a.e.a.a0.z.a.c);
        arrayList.add(roam.a.e.a.a0.z.o.b);
        arrayList.add(new roam.a.e.a.a0.z.b(gVar));
        arrayList.add(new roam.a.e.a.a0.z.g(gVar, false));
        roam.a.e.a.a0.z.d dVar = new roam.a.e.a.a0.z.d(gVar);
        this.d = dVar;
        arrayList.add(dVar);
        arrayList.add(roam.a.e.a.a0.z.o.Z);
        arrayList.add(new roam.a.e.a.a0.z.j(gVar, cVar, oVar, dVar));
        this.e = Collections.unmodifiableList(arrayList);
    }

    public static void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    /* JADX WARN: Finally extract failed */
    public <T> T b(String str, Class<T> cls) {
        T tA = null;
        if (str != null) {
            roam.a.e.a.c0.a aVar = new roam.a.e.a.c0.a(new StringReader(str));
            aVar.b = false;
            boolean z = true;
            aVar.b = true;
            try {
                try {
                    try {
                        try {
                            aVar.v();
                            try {
                                tA = c(new roam.a.e.a.b0.a<>(cls)).a(aVar);
                            } catch (EOFException e) {
                                e = e;
                                z = false;
                                if (!z) {
                                    throw new v(e);
                                }
                            }
                        } catch (AssertionError e2) {
                            AssertionError assertionError = new AssertionError("AssertionError (GSON 2.8.6): " + e2.getMessage());
                            assertionError.initCause(e2);
                            throw assertionError;
                        } catch (IllegalStateException e3) {
                            throw new v(e3);
                        }
                    } catch (IOException e4) {
                        throw new v(e4);
                    }
                } catch (EOFException e5) {
                    e = e5;
                }
                aVar.b = false;
                if (tA != null) {
                    try {
                        if (aVar.v() != roam.a.e.a.c0.b.END_DOCUMENT) {
                            throw new o("JSON document was not fully consumed.");
                        }
                    } catch (roam.a.e.a.c0.d e6) {
                        throw new v(e6);
                    } catch (IOException e7) {
                        throw new o(e7);
                    }
                }
            } catch (Throwable th) {
                aVar.b = false;
                throw th;
            }
        }
        if (cls == Integer.TYPE) {
            cls = (Class<T>) Integer.class;
        } else if (cls == Float.TYPE) {
            cls = (Class<T>) Float.class;
        } else if (cls == Byte.TYPE) {
            cls = (Class<T>) Byte.class;
        } else if (cls == Double.TYPE) {
            cls = (Class<T>) Double.class;
        } else if (cls == Long.TYPE) {
            cls = (Class<T>) Long.class;
        } else if (cls == Character.TYPE) {
            cls = (Class<T>) Character.class;
        } else if (cls == Boolean.TYPE) {
            cls = (Class<T>) Boolean.class;
        } else if (cls == Short.TYPE) {
            cls = (Class<T>) Short.class;
        } else if (cls == Void.TYPE) {
            cls = (Class<T>) Void.class;
        }
        return cls.cast(tA);
    }

    public <T> x<T> c(roam.a.e.a.b0.a<T> aVar) {
        boolean z;
        x<T> xVar = (x) this.b.get(aVar);
        if (xVar != null) {
            return xVar;
        }
        Map<roam.a.e.a.b0.a<?>, a<?>> map = this.a.get();
        if (map == null) {
            map = new HashMap<>();
            this.a.set(map);
            z = true;
        } else {
            z = false;
        }
        a<?> aVar2 = map.get(aVar);
        if (aVar2 != null) {
            return aVar2;
        }
        try {
            a<?> aVar3 = new a<>();
            map.put(aVar, aVar3);
            Iterator<y> it = this.e.iterator();
            while (it.hasNext()) {
                x<T> xVarA = it.next().a(this, aVar);
                if (xVarA != null) {
                    if (aVar3.a != null) {
                        throw new AssertionError();
                    }
                    aVar3.a = xVarA;
                    this.b.put(aVar, xVarA);
                    return xVarA;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.6) cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z) {
                this.a.remove();
            }
        }
    }

    public <T> x<T> d(y yVar, roam.a.e.a.b0.a<T> aVar) {
        if (!this.e.contains(yVar)) {
            yVar = this.d;
        }
        boolean z = false;
        for (y yVar2 : this.e) {
            if (z) {
                x<T> xVarA = yVar2.a(this, aVar);
                if (xVarA != null) {
                    return xVarA;
                }
            } else if (yVar2 == yVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public roam.a.e.a.c0.c e(Writer writer) {
        roam.a.e.a.c0.c cVar = new roam.a.e.a.c0.c(writer);
        cVar.i = false;
        return cVar;
    }

    public String f(Object obj) {
        if (obj == null) {
            n nVar = p.a;
            StringWriter stringWriter = new StringWriter();
            try {
                g(nVar, e(stringWriter));
                return stringWriter.toString();
            } catch (IOException e) {
                throw new o(e);
            }
        }
        Type type = obj.getClass();
        StringWriter stringWriter2 = new StringWriter();
        try {
            h(obj, type, e(stringWriter2));
            return stringWriter2.toString();
        } catch (IOException e2) {
            throw new o(e2);
        }
    }

    public void g(n nVar, roam.a.e.a.c0.c cVar) {
        boolean z = cVar.f;
        cVar.f = true;
        boolean z2 = cVar.g;
        cVar.g = this.f;
        boolean z3 = cVar.i;
        cVar.i = false;
        try {
            try {
                roam.a.e.a.a0.z.o.X.b(cVar, nVar);
            } catch (IOException e) {
                throw new o(e);
            } catch (AssertionError e2) {
                AssertionError assertionError = new AssertionError("AssertionError (GSON 2.8.6): " + e2.getMessage());
                assertionError.initCause(e2);
                throw assertionError;
            }
        } finally {
            cVar.f = z;
            cVar.g = z2;
            cVar.i = z3;
        }
    }

    public void h(Object obj, Type type, roam.a.e.a.c0.c cVar) {
        x xVarC = c(new roam.a.e.a.b0.a(type));
        boolean z = cVar.f;
        cVar.f = true;
        boolean z2 = cVar.g;
        cVar.g = this.f;
        boolean z3 = cVar.i;
        cVar.i = false;
        try {
            try {
                try {
                    xVarC.b(cVar, obj);
                } catch (AssertionError e) {
                    AssertionError assertionError = new AssertionError("AssertionError (GSON 2.8.6): " + e.getMessage());
                    assertionError.initCause(e);
                    throw assertionError;
                }
            } catch (IOException e2) {
                throw new o(e2);
            }
        } finally {
            cVar.f = z;
            cVar.g = z2;
            cVar.i = z3;
        }
    }

    public String toString() {
        return "{serializeNulls:false,factories:" + this.e + ",instanceCreators:" + this.c + "}";
    }
}

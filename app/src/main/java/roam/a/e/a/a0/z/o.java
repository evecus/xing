package roam.a.e.a.a0.z;

import com.baidu.mobstat.Config;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import roam.a.e.a.a0.s;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    public static final roam.a.e.a.x<String> A;
    public static final roam.a.e.a.x<BigDecimal> B;
    public static final roam.a.e.a.x<BigInteger> C;
    public static final roam.a.e.a.y D;
    public static final roam.a.e.a.x<StringBuilder> E;
    public static final roam.a.e.a.y F;
    public static final roam.a.e.a.x<StringBuffer> G;
    public static final roam.a.e.a.y H;
    public static final roam.a.e.a.x<URL> I;
    public static final roam.a.e.a.y J;
    public static final roam.a.e.a.x<URI> K;
    public static final roam.a.e.a.y L;
    public static final roam.a.e.a.x<InetAddress> M;
    public static final roam.a.e.a.y N;
    public static final roam.a.e.a.x<UUID> O;
    public static final roam.a.e.a.y P;
    public static final roam.a.e.a.x<Currency> Q;
    public static final roam.a.e.a.y R;
    public static final roam.a.e.a.y S;
    public static final roam.a.e.a.x<Calendar> T;
    public static final roam.a.e.a.y U;
    public static final roam.a.e.a.x<Locale> V;
    public static final roam.a.e.a.y W;
    public static final roam.a.e.a.x<roam.a.e.a.n> X;
    public static final roam.a.e.a.y Y;
    public static final roam.a.e.a.y Z;
    public static final roam.a.e.a.x<Class> a;
    public static final roam.a.e.a.y b;
    public static final roam.a.e.a.x<BitSet> c;
    public static final roam.a.e.a.y d;
    public static final roam.a.e.a.x<Boolean> e;
    public static final roam.a.e.a.x<Boolean> f;
    public static final roam.a.e.a.y g;
    public static final roam.a.e.a.x<Number> h;
    public static final roam.a.e.a.y i;
    public static final roam.a.e.a.x<Number> j;
    public static final roam.a.e.a.y k;
    public static final roam.a.e.a.x<Number> l;
    public static final roam.a.e.a.y m;
    public static final roam.a.e.a.x<AtomicInteger> n;
    public static final roam.a.e.a.y o;
    public static final roam.a.e.a.x<AtomicBoolean> p;
    public static final roam.a.e.a.y q;
    public static final roam.a.e.a.x<AtomicIntegerArray> r;
    public static final roam.a.e.a.y s;
    public static final roam.a.e.a.x<Number> t;
    public static final roam.a.e.a.x<Number> u;
    public static final roam.a.e.a.x<Number> v;
    public static final roam.a.e.a.x<Number> w;
    public static final roam.a.e.a.y x;
    public static final roam.a.e.a.x<Character> y;
    public static final roam.a.e.a.y z;

    public class a extends roam.a.e.a.x<AtomicIntegerArray> {
        @Override // roam.a.e.a.x
        public AtomicIntegerArray a(roam.a.e.a.c0.a aVar) {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.i()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.n()));
                } catch (NumberFormatException e) {
                    throw new roam.a.e.a.v(e);
                }
            }
            aVar.e();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, AtomicIntegerArray atomicIntegerArray) {
            cVar.b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                cVar.n(r6.get(i));
            }
            cVar.e();
        }
    }

    public class a0 extends roam.a.e.a.x<Number> {
        @Override // roam.a.e.a.x
        public Number a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.n());
            } catch (NumberFormatException e) {
                throw new roam.a.e.a.v(e);
            }
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Number number) {
            cVar.p(number);
        }
    }

    public class b extends roam.a.e.a.x<Number> {
        @Override // roam.a.e.a.x
        public Number a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            try {
                return Long.valueOf(aVar.o());
            } catch (NumberFormatException e) {
                throw new roam.a.e.a.v(e);
            }
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Number number) {
            cVar.p(number);
        }
    }

    public class b0 extends roam.a.e.a.x<Number> {
        @Override // roam.a.e.a.x
        public Number a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            try {
                return Integer.valueOf(aVar.n());
            } catch (NumberFormatException e) {
                throw new roam.a.e.a.v(e);
            }
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Number number) {
            cVar.p(number);
        }
    }

    public class c extends roam.a.e.a.x<Number> {
        @Override // roam.a.e.a.x
        public Number a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() != roam.a.e.a.c0.b.NULL) {
                return Float.valueOf((float) aVar.m());
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Number number) {
            cVar.p(number);
        }
    }

    public class c0 extends roam.a.e.a.x<AtomicInteger> {
        @Override // roam.a.e.a.x
        public AtomicInteger a(roam.a.e.a.c0.a aVar) {
            try {
                return new AtomicInteger(aVar.n());
            } catch (NumberFormatException e) {
                throw new roam.a.e.a.v(e);
            }
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, AtomicInteger atomicInteger) {
            cVar.n(atomicInteger.get());
        }
    }

    public class d extends roam.a.e.a.x<Number> {
        @Override // roam.a.e.a.x
        public Number a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() != roam.a.e.a.c0.b.NULL) {
                return Double.valueOf(aVar.m());
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Number number) {
            cVar.p(number);
        }
    }

    public class d0 extends roam.a.e.a.x<AtomicBoolean> {
        @Override // roam.a.e.a.x
        public AtomicBoolean a(roam.a.e.a.c0.a aVar) {
            return new AtomicBoolean(aVar.l());
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, AtomicBoolean atomicBoolean) {
            cVar.r(atomicBoolean.get());
        }
    }

    public class e extends roam.a.e.a.x<Number> {
        @Override // roam.a.e.a.x
        public Number a(roam.a.e.a.c0.a aVar) {
            roam.a.e.a.c0.b bVarV = aVar.v();
            int iOrdinal = bVarV.ordinal();
            if (iOrdinal == 5 || iOrdinal == 6) {
                return new roam.a.e.a.a0.r(aVar.t());
            }
            if (iOrdinal == 8) {
                aVar.r();
                return null;
            }
            throw new roam.a.e.a.v("Expecting number, got: " + bVarV);
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Number number) {
            cVar.p(number);
        }
    }

    public static final class e0<T extends Enum<T>> extends roam.a.e.a.x<T> {
        public final Map<String, T> a = new HashMap();
        public final Map<T, String> b = new HashMap();

        public e0(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String strName = t.name();
                    roam.a.e.a.z.b bVar = (roam.a.e.a.z.b) cls.getField(strName).getAnnotation(roam.a.e.a.z.b.class);
                    if (bVar != null) {
                        strName = bVar.value();
                        for (String str : bVar.alternate()) {
                            this.a.put(str, t);
                        }
                    }
                    this.a.put(strName, t);
                    this.b.put(t, strName);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        @Override // roam.a.e.a.x
        public Object a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() != roam.a.e.a.c0.b.NULL) {
                return this.a.get(aVar.t());
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Object obj) {
            Enum r3 = (Enum) obj;
            cVar.q(r3 == null ? null : this.b.get(r3));
        }
    }

    public class f extends roam.a.e.a.x<Character> {
        @Override // roam.a.e.a.x
        public Character a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            String strT = aVar.t();
            if (strT.length() == 1) {
                return Character.valueOf(strT.charAt(0));
            }
            throw new roam.a.e.a.v(roam.a.b.a.a.a.j("Expecting character, got: ", strT));
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Character ch) {
            Character ch2 = ch;
            cVar.q(ch2 == null ? null : String.valueOf(ch2));
        }
    }

    public class g extends roam.a.e.a.x<String> {
        @Override // roam.a.e.a.x
        public String a(roam.a.e.a.c0.a aVar) {
            roam.a.e.a.c0.b bVarV = aVar.v();
            if (bVarV != roam.a.e.a.c0.b.NULL) {
                return bVarV == roam.a.e.a.c0.b.BOOLEAN ? Boolean.toString(aVar.l()) : aVar.t();
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, String str) {
            cVar.q(str);
        }
    }

    public class h extends roam.a.e.a.x<BigDecimal> {
        @Override // roam.a.e.a.x
        public BigDecimal a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            try {
                return new BigDecimal(aVar.t());
            } catch (NumberFormatException e) {
                throw new roam.a.e.a.v(e);
            }
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, BigDecimal bigDecimal) {
            cVar.p(bigDecimal);
        }
    }

    public class i extends roam.a.e.a.x<BigInteger> {
        @Override // roam.a.e.a.x
        public BigInteger a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            try {
                return new BigInteger(aVar.t());
            } catch (NumberFormatException e) {
                throw new roam.a.e.a.v(e);
            }
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, BigInteger bigInteger) {
            cVar.p(bigInteger);
        }
    }

    public class j extends roam.a.e.a.x<StringBuilder> {
        @Override // roam.a.e.a.x
        public StringBuilder a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() != roam.a.e.a.c0.b.NULL) {
                return new StringBuilder(aVar.t());
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, StringBuilder sb) {
            StringBuilder sb2 = sb;
            cVar.q(sb2 == null ? null : sb2.toString());
        }
    }

    public class k extends roam.a.e.a.x<Class> {
        @Override // roam.a.e.a.x
        public Class a(roam.a.e.a.c0.a aVar) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Class cls) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Attempted to serialize java.lang.Class: ");
            sbO.append(cls.getName());
            sbO.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(sbO.toString());
        }
    }

    public class l extends roam.a.e.a.x<StringBuffer> {
        @Override // roam.a.e.a.x
        public StringBuffer a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() != roam.a.e.a.c0.b.NULL) {
                return new StringBuffer(aVar.t());
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, StringBuffer stringBuffer) {
            StringBuffer stringBuffer2 = stringBuffer;
            cVar.q(stringBuffer2 == null ? null : stringBuffer2.toString());
        }
    }

    public class m extends roam.a.e.a.x<URL> {
        @Override // roam.a.e.a.x
        public URL a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
            } else {
                String strT = aVar.t();
                if (!"null".equals(strT)) {
                    return new URL(strT);
                }
            }
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, URL url) {
            URL url2 = url;
            cVar.q(url2 == null ? null : url2.toExternalForm());
        }
    }

    public class n extends roam.a.e.a.x<URI> {
        @Override // roam.a.e.a.x
        public URI a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
            } else {
                try {
                    String strT = aVar.t();
                    if (!"null".equals(strT)) {
                        return new URI(strT);
                    }
                } catch (URISyntaxException e) {
                    throw new roam.a.e.a.o(e);
                }
            }
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, URI uri) {
            URI uri2 = uri;
            cVar.q(uri2 == null ? null : uri2.toASCIIString());
        }
    }

    /* JADX INFO: renamed from: roam.a.e.a.a0.z.o$o, reason: collision with other inner class name */
    public class C0018o extends roam.a.e.a.x<InetAddress> {
        @Override // roam.a.e.a.x
        public InetAddress a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() != roam.a.e.a.c0.b.NULL) {
                return InetAddress.getByName(aVar.t());
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, InetAddress inetAddress) {
            InetAddress inetAddress2 = inetAddress;
            cVar.q(inetAddress2 == null ? null : inetAddress2.getHostAddress());
        }
    }

    public class p extends roam.a.e.a.x<UUID> {
        @Override // roam.a.e.a.x
        public UUID a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() != roam.a.e.a.c0.b.NULL) {
                return UUID.fromString(aVar.t());
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, UUID uuid) {
            UUID uuid2 = uuid;
            cVar.q(uuid2 == null ? null : uuid2.toString());
        }
    }

    public class q extends roam.a.e.a.x<Currency> {
        @Override // roam.a.e.a.x
        public Currency a(roam.a.e.a.c0.a aVar) {
            return Currency.getInstance(aVar.t());
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Currency currency) {
            cVar.q(currency.getCurrencyCode());
        }
    }

    public class r implements roam.a.e.a.y {

        public class a extends roam.a.e.a.x<Timestamp> {
            public final roam.a.e.a.x a;

            public a(r rVar, roam.a.e.a.x xVar) {
                this.a = xVar;
            }

            @Override // roam.a.e.a.x
            public Timestamp a(roam.a.e.a.c0.a aVar) {
                Date date = (Date) this.a.a(aVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // roam.a.e.a.x
            public void b(roam.a.e.a.c0.c cVar, Timestamp timestamp) {
                this.a.b(cVar, timestamp);
            }
        }

        @Override // roam.a.e.a.y
        public <T> roam.a.e.a.x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
            if (aVar.a != Timestamp.class) {
                return null;
            }
            Objects.requireNonNull(iVar);
            return new a(this, iVar.c(new roam.a.e.a.b0.a<>(Date.class)));
        }
    }

    public class s extends roam.a.e.a.x<Calendar> {
        @Override // roam.a.e.a.x
        public Calendar a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            aVar.b();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.v() != roam.a.e.a.c0.b.END_OBJECT) {
                String strP = aVar.p();
                int iN = aVar.n();
                if ("year".equals(strP)) {
                    i = iN;
                } else if ("month".equals(strP)) {
                    i2 = iN;
                } else if ("dayOfMonth".equals(strP)) {
                    i3 = iN;
                } else if ("hourOfDay".equals(strP)) {
                    i4 = iN;
                } else if ("minute".equals(strP)) {
                    i5 = iN;
                } else if ("second".equals(strP)) {
                    i6 = iN;
                }
            }
            aVar.f();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Calendar calendar) {
            if (calendar == null) {
                cVar.i();
                return;
            }
            cVar.c();
            cVar.g("year");
            cVar.n(r4.get(1));
            cVar.g("month");
            cVar.n(r4.get(2));
            cVar.g("dayOfMonth");
            cVar.n(r4.get(5));
            cVar.g("hourOfDay");
            cVar.n(r4.get(11));
            cVar.g("minute");
            cVar.n(r4.get(12));
            cVar.g("second");
            cVar.n(r4.get(13));
            cVar.f();
        }
    }

    public class t extends roam.a.e.a.x<Locale> {
        @Override // roam.a.e.a.x
        public Locale a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.t(), Config.replace);
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (strNextToken2 == null && strNextToken3 == null) {
                return new Locale(strNextToken);
            }
            return strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Locale locale) {
            Locale locale2 = locale;
            cVar.q(locale2 == null ? null : locale2.toString());
        }
    }

    public class u extends roam.a.e.a.x<roam.a.e.a.n> {
        @Override // roam.a.e.a.x
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public roam.a.e.a.n a(roam.a.e.a.c0.a aVar) {
            roam.a.e.a.p pVar = roam.a.e.a.p.a;
            int iOrdinal = aVar.v().ordinal();
            if (iOrdinal == 0) {
                roam.a.e.a.k kVar = new roam.a.e.a.k();
                aVar.a();
                while (aVar.i()) {
                    kVar.a.add(a(aVar));
                }
                aVar.e();
                return kVar;
            }
            if (iOrdinal == 2) {
                roam.a.e.a.q qVar = new roam.a.e.a.q();
                aVar.b();
                while (aVar.i()) {
                    qVar.a.put(aVar.p(), a(aVar));
                }
                aVar.f();
                return qVar;
            }
            if (iOrdinal == 5) {
                return new roam.a.e.a.s(aVar.t());
            }
            if (iOrdinal == 6) {
                return new roam.a.e.a.s(new roam.a.e.a.a0.r(aVar.t()));
            }
            if (iOrdinal == 7) {
                return new roam.a.e.a.s(Boolean.valueOf(aVar.l()));
            }
            if (iOrdinal != 8) {
                throw new IllegalArgumentException();
            }
            aVar.r();
            return pVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // roam.a.e.a.x
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(roam.a.e.a.c0.c cVar, roam.a.e.a.n nVar) {
            if (nVar == null || (nVar instanceof roam.a.e.a.p)) {
                cVar.i();
                return;
            }
            if (nVar instanceof roam.a.e.a.s) {
                roam.a.e.a.s sVarA = nVar.a();
                Object obj = sVarA.a;
                if (obj instanceof Number) {
                    cVar.p(sVarA.c());
                    return;
                } else if (obj instanceof Boolean) {
                    cVar.r(sVarA.b());
                    return;
                } else {
                    cVar.q(sVarA.d());
                    return;
                }
            }
            boolean z = nVar instanceof roam.a.e.a.k;
            if (z) {
                cVar.b();
                if (!z) {
                    throw new IllegalStateException("Not a JSON Array: " + nVar);
                }
                Iterator<roam.a.e.a.n> it = ((roam.a.e.a.k) nVar).iterator();
                while (it.hasNext()) {
                    b(cVar, it.next());
                }
                cVar.e();
                return;
            }
            boolean z2 = nVar instanceof roam.a.e.a.q;
            if (!z2) {
                StringBuilder sbO = roam.a.b.a.a.a.o("Couldn't write ");
                sbO.append(nVar.getClass());
                throw new IllegalArgumentException(sbO.toString());
            }
            cVar.c();
            if (!z2) {
                throw new IllegalStateException("Not a JSON Object: " + nVar);
            }
            roam.a.e.a.a0.s sVar = ((s.b) ((roam.a.e.a.q) nVar).a.entrySet()).a;
            s.e eVar = sVar.e.d;
            int i = sVar.d;
            while (true) {
                s.e eVar2 = sVar.e;
                if (eVar == eVar2) {
                    cVar.f();
                    return;
                }
                if (eVar == eVar2) {
                    throw new NoSuchElementException();
                }
                if (sVar.d != i) {
                    throw new ConcurrentModificationException();
                }
                s.e eVar3 = eVar.d;
                cVar.g((String) eVar.f);
                b(cVar, (roam.a.e.a.n) eVar.g);
                eVar = eVar3;
            }
        }
    }

    public class v extends roam.a.e.a.x<BitSet> {
        /* JADX WARN: Removed duplicated region for block: B:23:0x004e  */
        @Override // roam.a.e.a.x
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.BitSet a(roam.a.e.a.c0.a r6) {
            /*
                r5 = this;
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r6.a()
                roam.a.e.a.c0.b r1 = r6.v()
                r2 = 0
            Ld:
                roam.a.e.a.c0.b r3 = roam.a.e.a.c0.b.END_ARRAY
                if (r1 == r3) goto L65
                int r3 = r1.ordinal()
                r4 = 5
                if (r3 == r4) goto L43
                r4 = 6
                if (r3 == r4) goto L3c
                r4 = 7
                if (r3 != r4) goto L25
                boolean r1 = r6.l()
                if (r1 == 0) goto L51
                goto L4e
            L25:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r0 = "Invalid bitset value type: "
                r6.append(r0)
                r6.append(r1)
                roam.a.e.a.v r0 = new roam.a.e.a.v
                java.lang.String r6 = r6.toString()
                r0.<init>(r6)
                throw r0
            L3c:
                int r1 = r6.n()
                if (r1 == 0) goto L51
                goto L4e
            L43:
                java.lang.String r1 = r6.t()
                int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L58
                if (r1 != 0) goto L4e
                goto L51
            L4e:
                r0.set(r2)
            L51:
                int r2 = r2 + 1
                roam.a.e.a.c0.b r1 = r6.v()
                goto Ld
            L58:
                r6 = move-exception
                roam.a.e.a.v r6 = new roam.a.e.a.v
                java.lang.String r0 = "Error: Expecting: bitset number value (1, 0), Found: "
                java.lang.String r0 = roam.a.b.a.a.a.j(r0, r1)
                r6.<init>(r0)
                throw r6
            L65:
                r6.e()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.a0.z.o.v.a(roam.a.e.a.c0.a):java.lang.Object");
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, BitSet bitSet) {
            BitSet bitSet2 = bitSet;
            cVar.b();
            int length = bitSet2.length();
            for (int i = 0; i < length; i++) {
                cVar.n(bitSet2.get(i) ? 1L : 0L);
            }
            cVar.e();
        }
    }

    public class w implements roam.a.e.a.y {
        @Override // roam.a.e.a.y
        public <T> roam.a.e.a.x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
            Class<? super T> superclass = aVar.a;
            if (!Enum.class.isAssignableFrom(superclass) || superclass == Enum.class) {
                return null;
            }
            if (!superclass.isEnum()) {
                superclass = superclass.getSuperclass();
            }
            return new e0(superclass);
        }
    }

    public class x extends roam.a.e.a.x<Boolean> {
        @Override // roam.a.e.a.x
        public Boolean a(roam.a.e.a.c0.a aVar) {
            roam.a.e.a.c0.b bVarV = aVar.v();
            if (bVarV != roam.a.e.a.c0.b.NULL) {
                return Boolean.valueOf(bVarV == roam.a.e.a.c0.b.STRING ? Boolean.parseBoolean(aVar.t()) : aVar.l());
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Boolean bool) {
            cVar.o(bool);
        }
    }

    public class y extends roam.a.e.a.x<Boolean> {
        @Override // roam.a.e.a.x
        public Boolean a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() != roam.a.e.a.c0.b.NULL) {
                return Boolean.valueOf(aVar.t());
            }
            aVar.r();
            return null;
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Boolean bool) {
            Boolean bool2 = bool;
            cVar.q(bool2 == null ? "null" : bool2.toString());
        }
    }

    public class z extends roam.a.e.a.x<Number> {
        @Override // roam.a.e.a.x
        public Number a(roam.a.e.a.c0.a aVar) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.n());
            } catch (NumberFormatException e) {
                throw new roam.a.e.a.v(e);
            }
        }

        @Override // roam.a.e.a.x
        public void b(roam.a.e.a.c0.c cVar, Number number) {
            cVar.p(number);
        }
    }

    static {
        roam.a.e.a.w wVar = new roam.a.e.a.w(new k());
        a = wVar;
        b = new roam.a.e.a.a0.z.p(Class.class, wVar);
        roam.a.e.a.w wVar2 = new roam.a.e.a.w(new v());
        c = wVar2;
        d = new roam.a.e.a.a0.z.p(BitSet.class, wVar2);
        x xVar = new x();
        e = xVar;
        f = new y();
        g = new roam.a.e.a.a0.z.q(Boolean.TYPE, Boolean.class, xVar);
        z zVar = new z();
        h = zVar;
        i = new roam.a.e.a.a0.z.q(Byte.TYPE, Byte.class, zVar);
        a0 a0Var = new a0();
        j = a0Var;
        k = new roam.a.e.a.a0.z.q(Short.TYPE, Short.class, a0Var);
        b0 b0Var = new b0();
        l = b0Var;
        m = new roam.a.e.a.a0.z.q(Integer.TYPE, Integer.class, b0Var);
        roam.a.e.a.w wVar3 = new roam.a.e.a.w(new c0());
        n = wVar3;
        o = new roam.a.e.a.a0.z.p(AtomicInteger.class, wVar3);
        roam.a.e.a.w wVar4 = new roam.a.e.a.w(new d0());
        p = wVar4;
        q = new roam.a.e.a.a0.z.p(AtomicBoolean.class, wVar4);
        roam.a.e.a.w wVar5 = new roam.a.e.a.w(new a());
        r = wVar5;
        s = new roam.a.e.a.a0.z.p(AtomicIntegerArray.class, wVar5);
        t = new b();
        u = new c();
        v = new d();
        e eVar = new e();
        w = eVar;
        x = new roam.a.e.a.a0.z.p(Number.class, eVar);
        f fVar = new f();
        y = fVar;
        z = new roam.a.e.a.a0.z.q(Character.TYPE, Character.class, fVar);
        g gVar = new g();
        A = gVar;
        B = new h();
        C = new i();
        D = new roam.a.e.a.a0.z.p(String.class, gVar);
        j jVar = new j();
        E = jVar;
        F = new roam.a.e.a.a0.z.p(StringBuilder.class, jVar);
        l lVar = new l();
        G = lVar;
        H = new roam.a.e.a.a0.z.p(StringBuffer.class, lVar);
        m mVar = new m();
        I = mVar;
        J = new roam.a.e.a.a0.z.p(URL.class, mVar);
        n nVar = new n();
        K = nVar;
        L = new roam.a.e.a.a0.z.p(URI.class, nVar);
        C0018o c0018o = new C0018o();
        M = c0018o;
        N = new roam.a.e.a.a0.z.s(InetAddress.class, c0018o);
        p pVar = new p();
        O = pVar;
        P = new roam.a.e.a.a0.z.p(UUID.class, pVar);
        roam.a.e.a.w wVar6 = new roam.a.e.a.w(new q());
        Q = wVar6;
        R = new roam.a.e.a.a0.z.p(Currency.class, wVar6);
        S = new r();
        s sVar = new s();
        T = sVar;
        U = new roam.a.e.a.a0.z.r(Calendar.class, GregorianCalendar.class, sVar);
        t tVar = new t();
        V = tVar;
        W = new roam.a.e.a.a0.z.p(Locale.class, tVar);
        u uVar = new u();
        X = uVar;
        Y = new roam.a.e.a.a0.z.s(roam.a.e.a.n.class, uVar);
        Z = new w();
    }
}

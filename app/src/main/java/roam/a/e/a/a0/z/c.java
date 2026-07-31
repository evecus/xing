package roam.a.e.a.a0.z;

import com.baidu.android.common.util.HanziToPinyin;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class c extends x<Date> {
    public static final y b = new a();
    public final List<DateFormat> a;

    public class a implements y {
        @Override // roam.a.e.a.y
        public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
            if (aVar.a == Date.class) {
                return new c();
            }
            return null;
        }
    }

    public c() {
        ArrayList arrayList = new ArrayList();
        this.a = arrayList;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (roam.a.e.a.a0.p.a >= 9) {
            arrayList.add(new SimpleDateFormat("MMM d, yyyy" + HanziToPinyin.Token.SEPARATOR + "h:mm:ss a", locale));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002b, code lost:
    
        r3 = roam.a.e.a.a0.z.t.a.b(r3, new java.text.ParsePosition(0));
     */
    @Override // roam.a.e.a.x
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Date a(roam.a.e.a.c0.a r3) {
        /*
            r2 = this;
            roam.a.e.a.c0.b r0 = r3.v()
            roam.a.e.a.c0.b r1 = roam.a.e.a.c0.b.NULL
            if (r0 != r1) goto Ld
            r3.r()
            r3 = 0
            goto L36
        Ld:
            java.lang.String r3 = r3.t()
            monitor-enter(r2)
            java.util.List<java.text.DateFormat> r0 = r2.a     // Catch: java.lang.Throwable -> L3e
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L3e
        L18:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto L2b
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L3e
            java.text.DateFormat r1 = (java.text.DateFormat) r1     // Catch: java.lang.Throwable -> L3e
            java.util.Date r3 = r1.parse(r3)     // Catch: java.text.ParseException -> L29 java.lang.Throwable -> L3e
            goto L35
        L29:
            r1 = move-exception
            goto L18
        L2b:
            java.text.ParsePosition r0 = new java.text.ParsePosition     // Catch: java.text.ParseException -> L37 java.lang.Throwable -> L3e
            r1 = 0
            r0.<init>(r1)     // Catch: java.text.ParseException -> L37 java.lang.Throwable -> L3e
            java.util.Date r3 = roam.a.e.a.a0.z.t.a.b(r3, r0)     // Catch: java.text.ParseException -> L37 java.lang.Throwable -> L3e
        L35:
            monitor-exit(r2)
        L36:
            return r3
        L37:
            r0 = move-exception
            roam.a.e.a.v r1 = new roam.a.e.a.v     // Catch: java.lang.Throwable -> L3e
            r1.<init>(r3, r0)     // Catch: java.lang.Throwable -> L3e
            throw r1     // Catch: java.lang.Throwable -> L3e
        L3e:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.a0.z.c.a(roam.a.e.a.c0.a):java.lang.Object");
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, Date date) {
        Date date2 = date;
        synchronized (this) {
            if (date2 == null) {
                cVar.i();
            } else {
                cVar.q(this.a.get(0).format(date2));
            }
        }
    }
}

package roam.a.e.a.a0.z;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import roam.a.e.a.v;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class k extends x<Date> {
    public static final y b = new a();
    public final DateFormat a = new SimpleDateFormat("MMM d, yyyy");

    public class a implements y {
        @Override // roam.a.e.a.y
        public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
            if (aVar.a == Date.class) {
                return new k();
            }
            return null;
        }
    }

    @Override // roam.a.e.a.x
    public Date a(roam.a.e.a.c0.a aVar) {
        Date date;
        synchronized (this) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                date = null;
            } else {
                try {
                    date = new Date(this.a.parse(aVar.t()).getTime());
                } catch (ParseException e) {
                    throw new v(e);
                }
            }
        }
        return date;
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, Date date) {
        Date date2 = date;
        synchronized (this) {
            cVar.q(date2 == null ? null : this.a.format((java.util.Date) date2));
        }
    }
}

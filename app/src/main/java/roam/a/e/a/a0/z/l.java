package roam.a.e.a.a0.z;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import roam.a.e.a.v;
import roam.a.e.a.x;
import roam.a.e.a.y;

/* JADX INFO: loaded from: classes.dex */
public final class l extends x<Time> {
    public static final y b = new a();
    public final DateFormat a = new SimpleDateFormat("hh:mm:ss a");

    public class a implements y {
        @Override // roam.a.e.a.y
        public <T> x<T> a(roam.a.e.a.i iVar, roam.a.e.a.b0.a<T> aVar) {
            if (aVar.a == Time.class) {
                return new l();
            }
            return null;
        }
    }

    @Override // roam.a.e.a.x
    public Time a(roam.a.e.a.c0.a aVar) {
        synchronized (this) {
            if (aVar.v() == roam.a.e.a.c0.b.NULL) {
                aVar.r();
                return null;
            }
            try {
                return new Time(this.a.parse(aVar.t()).getTime());
            } catch (ParseException e) {
                throw new v(e);
            }
        }
    }

    @Override // roam.a.e.a.x
    public void b(roam.a.e.a.c0.c cVar, Time time) {
        Time time2 = time;
        synchronized (this) {
            cVar.q(time2 == null ? null : this.a.format((Date) time2));
        }
    }
}

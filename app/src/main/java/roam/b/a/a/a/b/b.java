package roam.b.a.a.a.b;

import android.graphics.RectF;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class b implements Runnable {
    public double a = 0.0d;
    public double b = 0.0d;
    public final double c;
    public final long d;
    public final double e;
    public final double f;
    public final a g;

    public b(a aVar, double d, long j, double d2, double d3) {
        this.g = aVar;
        this.c = d;
        this.d = j;
        this.e = d2;
        this.f = d3;
    }

    @Override // java.lang.Runnable
    public void run() {
        double dMin = Math.min(this.c, System.currentTimeMillis() - this.d);
        roam.b.a.a.a.a.a aVar = this.g.a;
        double d = this.e;
        double d2 = this.c;
        Objects.requireNonNull(aVar);
        double d3 = (dMin / d2) - 1.0d;
        double d4 = (((d3 * d3 * d3) + 1.0d) * d) + 0.0d;
        roam.b.a.a.a.a.a aVar2 = this.g.a;
        double d5 = this.f;
        double d6 = this.c;
        Objects.requireNonNull(aVar2);
        double d7 = (dMin / d6) - 1.0d;
        double d8 = (((d7 * d7 * d7) + 1.0d) * d5) + 0.0d;
        this.g.j(d4 - this.a, d8 - this.b);
        this.a = d4;
        this.b = d8;
        if (dMin < this.c) {
            this.g.e.post(this);
            return;
        }
        a aVar3 = this.g;
        RectF rectFD = aVar3.d(aVar3.c, true, true);
        float f = rectFD.left;
        if (f == 0.0f && rectFD.top == 0.0f) {
            return;
        }
        this.g.j(f, rectFD.top);
    }
}

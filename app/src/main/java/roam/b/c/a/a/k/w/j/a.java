package roam.b.c.a.a.k.w.j;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class a extends roam.b.c.b.a.a.d.a.e.a {
    public float c;

    public a(Context context) {
        super(context);
        this.c = 0.9f;
    }

    @Override // roam.b.c.b.a.a.d.a.e.a, roam.b.c.b.a.a.d.a.e.c, roam.b.c.b.a.a.d.a.b.d
    public void b(int i, int i2, float f, boolean z) {
        setTextColor(roam.a.a.a.b.a.E(f, this.b, this.a));
        float f2 = this.c;
        setScaleX(f2 + ((1.0f - f2) * f));
        float f3 = this.c;
        setScaleY(f3 + ((1.0f - f3) * f));
    }

    @Override // roam.b.c.b.a.a.d.a.e.a, roam.b.c.b.a.a.d.a.e.c, roam.b.c.b.a.a.d.a.b.d
    public void d(int i, int i2, float f, boolean z) {
        setTextColor(roam.a.a.a.b.a.E(f, this.a, this.b));
        setScaleX(((this.c - 1.0f) * f) + 1.0f);
        setScaleY(((this.c - 1.0f) * f) + 1.0f);
    }

    public float getMinScale() {
        return this.c;
    }

    public void setMinScale(float f) {
        this.c = f;
    }
}

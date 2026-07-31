package roam.b.c.b.a.a.d.a.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a extends View implements roam.b.c.b.a.a.d.a.b.c {
    public List<roam.b.c.b.a.a.d.a.d.a> a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public Paint i;
    public Path j;
    public List<Integer> k;
    public Interpolator l;
    public Interpolator m;

    public a(Context context) {
        super(context);
        this.j = new Path();
        this.l = new AccelerateInterpolator();
        this.m = new DecelerateInterpolator();
        Paint paint = new Paint(1);
        this.i = paint;
        paint.setStyle(Paint.Style.FILL);
        this.g = roam.a.a.a.b.a.B(context, 3.5d);
        this.h = roam.a.a.a.b.a.B(context, 2.0d);
        this.f = roam.a.a.a.b.a.B(context, 1.5d);
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void a(List<roam.b.c.b.a.a.d.a.d.a> list) {
        this.a = list;
    }

    public float getMaxCircleRadius() {
        return this.g;
    }

    public float getMinCircleRadius() {
        return this.h;
    }

    public float getYOffset() {
        return this.f;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(this.c, (getHeight() - this.f) - this.g, this.b, this.i);
        canvas.drawCircle(this.e, (getHeight() - this.f) - this.g, this.d, this.i);
        this.j.reset();
        float height = (getHeight() - this.f) - this.g;
        this.j.moveTo(this.e, height);
        this.j.lineTo(this.e, height - this.d);
        Path path = this.j;
        float f = this.e;
        float f2 = this.c;
        path.quadTo(f + ((f2 - f) / 2.0f), height, f2, height - this.b);
        this.j.lineTo(this.c, this.b + height);
        Path path2 = this.j;
        float f3 = this.e;
        path2.quadTo(((this.c - f3) / 2.0f) + f3, height, f3, this.d + height);
        this.j.close();
        canvas.drawPath(this.j, this.i);
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void onPageScrollStateChanged(int i) {
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void onPageScrolled(int i, float f, int i2) {
        List<roam.b.c.b.a.a.d.a.d.a> list = this.a;
        if (list == null || list.isEmpty()) {
            return;
        }
        List<Integer> list2 = this.k;
        if (list2 != null && list2.size() > 0) {
            this.i.setColor(roam.a.a.a.b.a.E(f, this.k.get(Math.abs(i) % this.k.size()).intValue(), this.k.get(Math.abs(i + 1) % this.k.size()).intValue()));
        }
        roam.b.c.b.a.a.d.a.d.a aVarG = roam.a.a.a.b.a.G(this.a, i);
        roam.b.c.b.a.a.d.a.d.a aVarG2 = roam.a.a.a.b.a.G(this.a, i + 1);
        int i3 = aVarG.a;
        float f2 = ((aVarG.c - i3) / 2) + i3;
        int i4 = aVarG2.a;
        float f3 = (((aVarG2.c - i4) / 2) + i4) - f2;
        this.c = (this.l.getInterpolation(f) * f3) + f2;
        this.e = f2 + (f3 * this.m.getInterpolation(f));
        float f4 = this.g;
        this.b = f4 + ((this.h - f4) * this.m.getInterpolation(f));
        float f5 = this.h;
        this.d = f5 + ((this.g - f5) * this.l.getInterpolation(f));
        invalidate();
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void onPageSelected(int i) {
    }

    public void setColors(Integer... numArr) {
        this.k = Arrays.asList(numArr);
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.m = interpolator;
        if (interpolator == null) {
            this.m = new DecelerateInterpolator();
        }
    }

    public void setMaxCircleRadius(float f) {
        this.g = f;
    }

    public void setMinCircleRadius(float f) {
        this.h = f;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.l = interpolator;
        if (interpolator == null) {
            this.l = new AccelerateInterpolator();
        }
    }

    public void setYOffset(float f) {
        this.f = f;
    }
}

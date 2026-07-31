package roam.b.c.b.a.a.d.a.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b extends View implements roam.b.c.b.a.a.d.a.b.c {
    public int a;
    public Interpolator b;
    public Interpolator c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public Paint i;
    public List<roam.b.c.b.a.a.d.a.d.a> j;
    public List<Integer> k;
    public RectF l;

    public b(Context context) {
        super(context);
        this.b = new LinearInterpolator();
        this.c = new LinearInterpolator();
        this.l = new RectF();
        Paint paint = new Paint(1);
        this.i = paint;
        paint.setStyle(Paint.Style.FILL);
        this.e = roam.a.a.a.b.a.B(context, 3.0d);
        this.g = roam.a.a.a.b.a.B(context, 10.0d);
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void a(List<roam.b.c.b.a.a.d.a.d.a> list) {
        this.j = list;
    }

    public List<Integer> getColors() {
        return this.k;
    }

    public Interpolator getEndInterpolator() {
        return this.c;
    }

    public float getLineHeight() {
        return this.e;
    }

    public float getLineWidth() {
        return this.g;
    }

    public int getMode() {
        return this.a;
    }

    public Paint getPaint() {
        return this.i;
    }

    public float getRoundRadius() {
        return this.h;
    }

    public Interpolator getStartInterpolator() {
        return this.b;
    }

    public float getXOffset() {
        return this.f;
    }

    public float getYOffset() {
        return this.d;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        RectF rectF = this.l;
        float f = this.h;
        canvas.drawRoundRect(rectF, f, f, this.i);
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void onPageScrollStateChanged(int i) {
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void onPageScrolled(int i, float f, int i2) {
        float fB;
        float fB2;
        float fB3;
        float f2;
        float f3;
        int i3;
        List<roam.b.c.b.a.a.d.a.d.a> list = this.j;
        if (list == null || list.isEmpty()) {
            return;
        }
        List<Integer> list2 = this.k;
        if (list2 != null && list2.size() > 0) {
            this.i.setColor(roam.a.a.a.b.a.E(f, this.k.get(Math.abs(i) % this.k.size()).intValue(), this.k.get(Math.abs(i + 1) % this.k.size()).intValue()));
        }
        roam.b.c.b.a.a.d.a.d.a aVarG = roam.a.a.a.b.a.G(this.j, i);
        roam.b.c.b.a.a.d.a.d.a aVarG2 = roam.a.a.a.b.a.G(this.j, i + 1);
        int i4 = this.a;
        if (i4 == 0) {
            float f4 = aVarG.a;
            f3 = this.f;
            fB = f4 + f3;
            f2 = aVarG2.a + f3;
            fB2 = aVarG.c - f3;
            i3 = aVarG2.c;
        } else {
            if (i4 != 1) {
                fB = aVarG.a + ((aVarG.b() - this.g) / 2.0f);
                float f5 = aVarG2.a;
                float fB4 = (aVarG2.b() - this.g) / 2.0f;
                fB2 = ((aVarG.b() + this.g) / 2.0f) + aVarG.a;
                fB3 = ((aVarG2.b() + this.g) / 2.0f) + aVarG2.a;
                f2 = f5 + fB4;
                this.l.left = fB + ((f2 - fB) * this.b.getInterpolation(f));
                this.l.right = ((fB3 - fB2) * this.c.getInterpolation(f)) + fB2;
                this.l.top = (getHeight() - this.e) - this.d;
                this.l.bottom = getHeight() - this.d;
                invalidate();
            }
            float f6 = aVarG.e;
            f3 = this.f;
            fB = f6 + f3;
            f2 = aVarG2.e + f3;
            fB2 = aVarG.g - f3;
            i3 = aVarG2.g;
        }
        fB3 = i3 - f3;
        this.l.left = fB + ((f2 - fB) * this.b.getInterpolation(f));
        this.l.right = ((fB3 - fB2) * this.c.getInterpolation(f)) + fB2;
        this.l.top = (getHeight() - this.e) - this.d;
        this.l.bottom = getHeight() - this.d;
        invalidate();
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void onPageSelected(int i) {
    }

    public void setColors(Integer... numArr) {
        this.k = Arrays.asList(numArr);
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.c = interpolator;
        if (interpolator == null) {
            this.c = new LinearInterpolator();
        }
    }

    public void setLineHeight(float f) {
        this.e = f;
    }

    public void setLineWidth(float f) {
        this.g = f;
    }

    public void setMode(int i) {
        if (i == 2 || i == 0 || i == 1) {
            this.a = i;
            return;
        }
        throw new IllegalArgumentException("mode " + i + " not supported.");
    }

    public void setRoundRadius(float f) {
        this.h = f;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.b = interpolator;
        if (interpolator == null) {
            this.b = new LinearInterpolator();
        }
    }

    public void setXOffset(float f) {
        this.f = f;
    }

    public void setYOffset(float f) {
        this.d = f;
    }
}

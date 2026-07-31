package roam.b.c.b.a.a.d.a.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c extends View implements roam.b.c.b.a.a.d.a.b.c {
    public int a;
    public int b;
    public int c;
    public float d;
    public Interpolator e;
    public Interpolator f;
    public List<roam.b.c.b.a.a.d.a.d.a> g;
    public Paint h;
    public RectF i;
    public boolean j;

    public c(Context context) {
        super(context);
        this.e = new LinearInterpolator();
        this.f = new LinearInterpolator();
        this.i = new RectF();
        Paint paint = new Paint(1);
        this.h = paint;
        paint.setStyle(Paint.Style.FILL);
        this.a = roam.a.a.a.b.a.B(context, 6.0d);
        this.b = roam.a.a.a.b.a.B(context, 10.0d);
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void a(List<roam.b.c.b.a.a.d.a.d.a> list) {
        this.g = list;
    }

    public Interpolator getEndInterpolator() {
        return this.f;
    }

    public int getFillColor() {
        return this.c;
    }

    public int getHorizontalPadding() {
        return this.b;
    }

    public Paint getPaint() {
        return this.h;
    }

    public float getRoundRadius() {
        return this.d;
    }

    public Interpolator getStartInterpolator() {
        return this.e;
    }

    public int getVerticalPadding() {
        return this.a;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        this.h.setColor(this.c);
        RectF rectF = this.i;
        float f = this.d;
        canvas.drawRoundRect(rectF, f, f, this.h);
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void onPageScrollStateChanged(int i) {
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void onPageScrolled(int i, float f, int i2) {
        List<roam.b.c.b.a.a.d.a.d.a> list = this.g;
        if (list == null || list.isEmpty()) {
            return;
        }
        roam.b.c.b.a.a.d.a.d.a aVarG = roam.a.a.a.b.a.G(this.g, i);
        roam.b.c.b.a.a.d.a.d.a aVarG2 = roam.a.a.a.b.a.G(this.g, i + 1);
        RectF rectF = this.i;
        int i3 = aVarG.e;
        rectF.left = ((aVarG2.e - i3) * this.f.getInterpolation(f)) + (i3 - this.b);
        RectF rectF2 = this.i;
        rectF2.top = aVarG.f - this.a;
        int i4 = aVarG.g;
        rectF2.right = ((aVarG2.g - i4) * this.e.getInterpolation(f)) + this.b + i4;
        RectF rectF3 = this.i;
        rectF3.bottom = aVarG.h + this.a;
        if (!this.j) {
            this.d = rectF3.height() / 2.0f;
        }
        invalidate();
    }

    @Override // roam.b.c.b.a.a.d.a.b.c
    public void onPageSelected(int i) {
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.f = interpolator;
        if (interpolator == null) {
            this.f = new LinearInterpolator();
        }
    }

    public void setFillColor(int i) {
        this.c = i;
    }

    public void setHorizontalPadding(int i) {
        this.b = i;
    }

    public void setRoundRadius(float f) {
        this.d = f;
        this.j = true;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.e = interpolator;
        if (interpolator == null) {
            this.e = new LinearInterpolator();
        }
    }

    public void setVerticalPadding(int i) {
        this.a = i;
    }
}

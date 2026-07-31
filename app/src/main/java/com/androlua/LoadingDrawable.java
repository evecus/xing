package com.androlua;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes.dex */
public class LoadingDrawable extends Drawable {
    public static final int STATE_FAIL = -1;
    public static final int STATE_LOADING = 0;
    public static final int STATE_SUCCESS = 1;
    private final DisplayMetrics dm;
    private int mState;
    private Paint p;
    private int n = 0;
    private int m = 0;
    private int x = 0;
    private int y = 0;
    private int sn = 3;
    private int sm = 1;

    public LoadingDrawable(Context context) {
        this.dm = context.getResources().getDisplayMetrics();
        Paint paint = new Paint();
        this.p = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.p.setAntiAlias(true);
        this.p.setStrokeWidth(dp(8.0f));
        this.p.setColor(-2004318072);
    }

    private int dp(float f) {
        return (int) TypedValue.applyDimension(1, f, this.dm);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i;
        int i2;
        Rect rect = new Rect(getBounds());
        int i3 = rect.right;
        int i4 = rect.bottom;
        rect.right = iMin;
        rect.bottom = iMin;
        canvas.save();
        canvas.translate((i3 - iMin) / 2, (i4 - iMin) / 2);
        float f = iMin;
        float f2 = 0.15f * f;
        float f3 = f * 0.85f;
        RectF rectF = new RectF(f2, f2, f3, f3);
        int i5 = this.n;
        if (i5 >= 360 && this.mState == 0) {
            this.sm = 8;
            this.sn = -6;
        } else if (i5 <= 6) {
            this.sn = 6;
            this.sm = 2;
        }
        if (i5 < 360 || this.mState == 0) {
            if (this.mState == 0) {
                this.n = i5 + this.sn;
                i = this.m;
                i2 = this.sm;
            } else {
                this.n = i5 + (this.sn * 2);
                i = this.m;
                i2 = this.sm * 2;
            }
            int i6 = i + i2;
            this.m = i6;
            this.m = i6 % 360;
        }
        canvas.drawArc(rectF, this.m, this.n, false, this.p);
        if (this.n >= 360) {
            this.sn = -6;
            this.sm = 8;
            int i7 = this.mState;
            if (i7 == 1) {
                Path path = new Path();
                path.moveTo(rect.right * 0.3f, rect.bottom * 0.5f);
                path.lineTo(rect.right * 0.45f, rect.bottom * 0.7f);
                path.lineTo(rect.right * 0.75f, rect.bottom * 0.4f);
                canvas.drawPath(path, this.p);
            } else if (i7 == -1) {
                float f4 = rect.right / 2;
                float f5 = rect.bottom;
                canvas.drawLine(f4, f5 * 0.25f, f4, f5 * 0.65f, this.p);
                float f6 = rect.right / 2;
                float f7 = rect.bottom;
                canvas.drawLine(f6, f7 * 0.7f, f6, f7 * 0.75f, this.p);
            }
        }
        canvas.restore();
        invalidateSelf();
    }

    public void fail() {
        this.mState = -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public void loading() {
        reset();
    }

    public void reset() {
        this.mState = 0;
        this.sn = 3;
        this.sm = 1;
        this.n = 0;
        this.m = 0;
        this.x = 0;
        this.y = 0;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.p.setAlpha(i);
    }

    public void setColor(int i) {
        this.p.setColor(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.p.setColorFilter(colorFilter);
    }

    public void setState(int i) {
        this.mState = i;
    }

    public void setStrokeWidth(float f) {
        this.p.setStrokeWidth(f);
    }

    public void succe() {
        this.mState = 1;
    }
}

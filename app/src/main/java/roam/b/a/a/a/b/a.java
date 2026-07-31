package roam.b.a.a.a.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ImageView {
    public roam.b.a.a.a.a.a a;
    public Matrix b;
    public Matrix c;
    public Matrix d;
    public Handler e;
    public Runnable f;
    public boolean g;
    public float h;
    public float i;
    public boolean j;
    public boolean k;
    public final Matrix l;
    public final float[] m;
    public int n;
    public int o;
    public PointF p;
    public c q;
    public boolean r;
    public boolean s;
    public RectF t;
    public RectF u;
    public RectF v;
    public d w;
    public e x;

    /* JADX INFO: renamed from: roam.b.a.a.a.b.a$a, reason: collision with other inner class name */
    public class RunnableC0021a implements Runnable {
        public final Drawable a;
        public final Matrix b;
        public final float c;
        public final float d;
        public final a e;

        public RunnableC0021a(a aVar, Drawable drawable, Matrix matrix, float f, float f2) {
            this.e = aVar;
            this.a = drawable;
            this.b = matrix;
            this.c = f;
            this.d = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.e.l(this.a, this.b, this.c, this.d);
        }
    }

    public class b implements Runnable {
        public final float a;
        public final long b;
        public final float c;
        public final float d;
        public final float e;
        public final float f;
        public final a g;

        public b(a aVar, float f, long j, float f2, float f3, float f4, float f5) {
            this.g = aVar;
            this.a = f;
            this.b = j;
            this.c = f2;
            this.d = f3;
            this.e = f4;
            this.f = f5;
        }

        @Override // java.lang.Runnable
        public void run() {
            float fMin = Math.min(this.a, System.currentTimeMillis() - this.b);
            roam.b.a.a.a.a.a aVar = this.g.a;
            double d = this.c;
            double d2 = this.a;
            Objects.requireNonNull(aVar);
            double d3 = ((double) fMin) / (d2 / 2.0d);
            double d4 = d / 2.0d;
            if (d3 < 1.0d) {
                d4 = d4 * d3 * d3;
            } else {
                double d5 = d3 - 2.0d;
                d3 = (d5 * d5 * d5) + 2.0d;
            }
            this.g.n(((float) ((d3 * d4) + 0.0d)) + this.d, this.e, this.f);
            if (fMin < this.a) {
                this.g.e.post(this);
                return;
            }
            a aVar2 = this.g;
            aVar2.i(aVar2.getScale());
            this.g.b(true, true);
        }
    }

    public enum c {
        NONE,
        FIT_TO_SCREEN,
        FIT_IF_BIGGER
    }

    public interface d {
        void a(Drawable drawable);
    }

    public interface e {
        void a(boolean z, int i, int i2, int i3, int i4);
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new roam.b.a.a.a.a.a();
        this.b = new Matrix();
        this.c = new Matrix();
        this.e = new Handler();
        this.f = null;
        this.g = false;
        this.h = -1.0f;
        this.i = -1.0f;
        this.l = new Matrix();
        this.m = new float[9];
        this.n = -1;
        this.o = -1;
        this.p = new PointF();
        this.q = c.NONE;
        this.t = new RectF();
        this.u = new RectF();
        this.v = new RectF();
        g(context, attributeSet, i);
    }

    public void a(Drawable drawable, Matrix matrix, float f, float f2) {
        if (drawable == null) {
            this.b.reset();
            drawable = null;
        }
        super.setImageDrawable(drawable);
        if (f == -1.0f || f2 == -1.0f) {
            this.i = -1.0f;
            this.h = -1.0f;
            this.k = false;
            this.j = false;
        } else {
            float fMin = Math.min(f, f2);
            float fMax = Math.max(fMin, f2);
            this.i = fMin;
            this.h = fMax;
            this.k = true;
            this.j = true;
            c cVar = this.q;
            if (cVar == c.FIT_TO_SCREEN || cVar == c.FIT_IF_BIGGER) {
                if (fMin >= 1.0f) {
                    this.k = false;
                    this.i = -1.0f;
                }
                if (fMax <= 1.0f) {
                    this.j = true;
                    this.h = -1.0f;
                }
            }
        }
        if (matrix != null) {
            this.d = new Matrix(matrix);
        }
        this.s = true;
        requestLayout();
    }

    public void b(boolean z, boolean z2) {
        if (getDrawable() == null) {
            return;
        }
        RectF rectFD = d(this.c, z, z2);
        float f = rectFD.left;
        if (f == 0.0f && rectFD.top == 0.0f) {
            return;
        }
        k(f, rectFD.top);
    }

    public RectF c(Matrix matrix) {
        if (getDrawable() == null) {
            return null;
        }
        this.l.set(this.b);
        this.l.postConcat(matrix);
        Matrix matrix2 = this.l;
        this.t.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        matrix2.mapRect(this.t);
        return this.t;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.RectF d(android.graphics.Matrix r6, boolean r7, boolean r8) {
        /*
            r5 = this;
            android.graphics.drawable.Drawable r0 = r5.getDrawable()
            r1 = 0
            if (r0 != 0) goto Ld
            android.graphics.RectF r6 = new android.graphics.RectF
            r6.<init>(r1, r1, r1, r1)
            goto L67
        Ld:
            android.graphics.RectF r0 = r5.u
            r0.set(r1, r1, r1, r1)
            android.graphics.RectF r6 = r5.c(r6)
            float r0 = r6.height()
            float r2 = r6.width()
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L3e
            int r8 = r5.o
            float r8 = (float) r8
            int r4 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r4 >= 0) goto L2f
            float r8 = r8 - r0
            float r8 = r8 / r3
            float r0 = r6.top
        L2d:
            float r8 = r8 - r0
            goto L3f
        L2f:
            float r0 = r6.top
            int r4 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r4 <= 0) goto L37
            float r8 = -r0
            goto L3f
        L37:
            float r0 = r6.bottom
            int r4 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r4 >= 0) goto L3e
            goto L2d
        L3e:
            r8 = r1
        L3f:
            if (r7 == 0) goto L5f
            int r7 = r5.n
            float r7 = (float) r7
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 >= 0) goto L4d
            float r7 = r7 - r2
            float r7 = r7 / r3
            float r6 = r6.left
            goto L5c
        L4d:
            float r0 = r6.left
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 <= 0) goto L55
            float r6 = -r0
            goto L60
        L55:
            float r6 = r6.right
            int r0 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r0 < 0) goto L5c
            goto L5f
        L5c:
            float r6 = r7 - r6
            goto L60
        L5f:
            r6 = r1
        L60:
            android.graphics.RectF r7 = r5.u
            r7.set(r6, r8, r1, r1)
            android.graphics.RectF r6 = r5.u
        L67:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.b.a.a.a.b.a.d(android.graphics.Matrix, boolean, boolean):android.graphics.RectF");
    }

    public float e(c cVar) {
        if (cVar == c.FIT_TO_SCREEN) {
            return 1.0f;
        }
        return cVar == c.FIT_IF_BIGGER ? Math.min(1.0f, 1.0f / f(this.b)) : 1.0f / f(this.b);
    }

    public float f(Matrix matrix) {
        matrix.getValues(this.m);
        return this.m[0];
    }

    public void g(Context context, AttributeSet attributeSet, int i) {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public float getBaseScale() {
        return f(this.b);
    }

    public RectF getBitmapRect() {
        return c(this.c);
    }

    public PointF getCenter() {
        return this.p;
    }

    public Matrix getDisplayMatrix() {
        return new Matrix(this.c);
    }

    public c getDisplayType() {
        return this.q;
    }

    public Matrix getImageViewMatrix() {
        Matrix matrix = this.c;
        this.l.set(this.b);
        this.l.postConcat(matrix);
        return this.l;
    }

    public float getMaxScale() {
        if (this.h == -1.0f) {
            this.h = getDrawable() == null ? 1.0f : Math.max(r0.getIntrinsicWidth() / this.n, r0.getIntrinsicHeight() / this.o) * 8.0f;
        }
        return this.h;
    }

    public float getMinScale() {
        if (this.i == -1.0f) {
            this.i = getDrawable() != null ? Math.min(1.0f, 1.0f / f(this.b)) : 1.0f;
        }
        return this.i;
    }

    @Override // android.view.View
    public float getRotation() {
        return 0.0f;
    }

    public float getScale() {
        return f(this.c);
    }

    public void h() {
    }

    public void i(float f) {
    }

    public void j(double d2, double d3) {
        RectF bitmapRect = getBitmapRect();
        this.v.set((float) d2, (float) d3, 0.0f, 0.0f);
        RectF rectF = this.v;
        if (bitmapRect != null) {
            if (bitmapRect.top >= 0.0f && bitmapRect.bottom <= this.o) {
                rectF.top = 0.0f;
            }
            if (bitmapRect.left >= 0.0f && bitmapRect.right <= this.n) {
                rectF.left = 0.0f;
            }
            if (rectF.top + bitmapRect.top >= 0.0f && bitmapRect.bottom > this.o) {
                rectF.top = (int) (0.0f - r4);
            }
            float f = bitmapRect.bottom;
            float f2 = rectF.top;
            if (f2 + f <= this.o + 0 && bitmapRect.top < 0.0f) {
                rectF.top = (int) (r1 - f);
            }
            if (rectF.left + bitmapRect.left >= 0.0f) {
                rectF.left = (int) (0.0f - r4);
            }
            float f3 = bitmapRect.right;
            float f4 = rectF.left;
            if (f4 + f3 <= this.n + 0) {
                rectF.left = (int) (r6 - f3);
            }
        }
        k(rectF.left, rectF.top);
        b(true, true);
    }

    public void k(float f, float f2) {
        if (f == 0.0f && f2 == 0.0f) {
            return;
        }
        this.c.postTranslate(f, f2);
        setImageMatrix(getImageViewMatrix());
    }

    public void l(Drawable drawable, Matrix matrix, float f, float f2) {
        if (getWidth() <= 0) {
            this.f = new RunnableC0021a(this, drawable, matrix, f, f2);
        } else {
            a(drawable, matrix, f, f2);
        }
    }

    public void m(float f) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        if (f < getMinScale()) {
            f = getMinScale();
        }
        PointF center = getCenter();
        n(f, center.x, center.y);
    }

    public void n(float f, float f2, float f3) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        float scale = f / getScale();
        this.c.postScale(scale, scale, f2, f3);
        setImageMatrix(getImageViewMatrix());
        getScale();
        h();
        b(true, true);
    }

    public void o(float f, float f2, float f3, float f4) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        float scale = getScale();
        Matrix matrix = new Matrix(this.c);
        matrix.postScale(f, f, f2, f3);
        RectF rectFD = d(matrix, true, true);
        this.e.post(new b(this, f4, jCurrentTimeMillis, f - scale, scale, (rectFD.left * f) + f2, (rectFD.top * f) + f3));
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r17, int r18, int r19, int r20, int r21) {
        /*
            Method dump skipped, instruction units count: 390
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.b.a.a.a.b.a.onLayout(boolean, int, int, int, int):void");
    }

    public void setDisplayType(c cVar) {
        if (cVar != this.q) {
            this.g = false;
            this.q = cVar;
            this.r = true;
            requestLayout();
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            l(new roam.b.a.a.a.b.c.a(bitmap), null, -1.0f, -1.0f);
        } else {
            l(null, null, -1.0f, -1.0f);
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        l(drawable, null, -1.0f, -1.0f);
    }

    @Override // android.widget.ImageView
    public void setImageMatrix(Matrix matrix) {
        Matrix imageMatrix = getImageMatrix();
        if ((matrix != null || imageMatrix.isIdentity()) && matrix != null) {
            imageMatrix.equals(matrix);
        }
        super.setImageMatrix(matrix);
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        setImageDrawable(getContext().getResources().getDrawable(i));
    }

    public void setMaxScale(float f) {
        this.h = f;
    }

    public void setMinScale(float f) {
        this.i = f;
    }

    public void setOnDrawableChangedListener(d dVar) {
        this.w = dVar;
    }

    public void setOnLayoutChangeListener(e eVar) {
        this.x = eVar;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == ImageView.ScaleType.MATRIX) {
            super.setScaleType(scaleType);
        } else {
            Log.w("ImageViewTouchBase", "Unsupported scaletype. Only MATRIX can be used");
        }
    }
}

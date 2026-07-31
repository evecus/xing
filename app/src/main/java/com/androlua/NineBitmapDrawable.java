package com.androlua;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class NineBitmapDrawable extends Drawable implements LuaGcable {
    private Bitmap mBitmap;
    private boolean mGc;
    private Paint mPaint;
    private Rect mRect1;
    private Rect mRect2;
    private Rect mRect3;
    private Rect mRect4;
    private Rect mRect5;
    private Rect mRect6;
    private Rect mRect7;
    private Rect mRect8;
    private Rect mRect9;
    private int mX1;
    private int mX2;
    private int mY1;
    private int mY2;

    public NineBitmapDrawable(Bitmap bitmap) {
        int i;
        int i2;
        int i3;
        this.mPaint = new Paint();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i5 >= width) {
                i = 0;
                break;
            } else {
                if (bitmap.getPixel(i5, 0) == -16777216) {
                    i = i5;
                    break;
                }
                i5++;
            }
        }
        if (i == 0 || i == width - 1) {
            throw new IllegalArgumentException("not found x1");
        }
        int i6 = i;
        while (true) {
            if (i6 >= width) {
                i2 = 0;
                break;
            } else {
                if (bitmap.getPixel(i6, 0) != -16777216) {
                    i2 = width - i6;
                    break;
                }
                i6++;
            }
        }
        if (i2 == 0 || i2 == 1) {
            throw new IllegalArgumentException("not found x2");
        }
        int i7 = 0;
        while (true) {
            if (i7 >= height) {
                i3 = 0;
                break;
            } else {
                if (bitmap.getPixel(0, i7) == -16777216) {
                    i3 = i7;
                    break;
                }
                i7++;
            }
        }
        if (i3 == 0 || i3 == height - 1) {
            throw new IllegalArgumentException("not found y1");
        }
        int i8 = i3;
        while (true) {
            if (i8 >= height) {
                break;
            }
            if (bitmap.getPixel(0, i8) != -16777216) {
                i4 = height - i8;
                break;
            }
            i8++;
        }
        int i9 = i4;
        if (i9 == 0 || i9 == 1) {
            throw new IllegalArgumentException("not found y2");
        }
        init(bitmap, i, i3, i2, i9);
    }

    public NineBitmapDrawable(Bitmap bitmap, int i, int i2, int i3, int i4) {
        this.mPaint = new Paint();
        init(bitmap, i, i2, i3, i4);
    }

    public NineBitmapDrawable(String str) {
        this(LuaBitmap.getLocalBitmap(str));
    }

    private void init(Bitmap bitmap, int i, int i2, int i3, int i4) {
        this.mBitmap = bitmap;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.mX1 = i;
        this.mY1 = i2;
        this.mX2 = i3;
        this.mY2 = i4;
        int i5 = width - i3;
        int i6 = height - i4;
        this.mRect1 = new Rect(1, 1, i, i2);
        this.mRect2 = new Rect(i, 1, i5, i2);
        int i7 = width - 1;
        this.mRect3 = new Rect(i5, 1, i7, i2);
        this.mRect4 = new Rect(1, i2, i, i6);
        this.mRect5 = new Rect(i, i2, i5, i6);
        this.mRect6 = new Rect(i5, i2, i7, i6);
        int i8 = height - 1;
        this.mRect7 = new Rect(1, i6, i, i8);
        this.mRect8 = new Rect(i, i6, i5, i8);
        this.mRect9 = new Rect(i5, i6, i7, i8);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i = bounds.right;
        int i2 = bounds.bottom;
        Rect rect = new Rect(0, 0, this.mX1, this.mY1);
        Rect rect2 = new Rect(this.mX1, 0, i - this.mX2, this.mY1);
        Rect rect3 = new Rect(i - this.mX2, 0, i, this.mY1);
        Rect rect4 = new Rect(0, this.mY1, this.mX1, i2 - this.mY2);
        Rect rect5 = new Rect(this.mX1, this.mY1, i - this.mX2, i2 - this.mY2);
        Rect rect6 = new Rect(i - this.mX2, this.mY1, i, i2 - this.mY2);
        Rect rect7 = new Rect(0, i2 - this.mY2, this.mX1, i2);
        Rect rect8 = new Rect(this.mX1, i2 - this.mY2, i - this.mX2, i2);
        Rect rect9 = new Rect(i - this.mX2, i2 - this.mY2, i, i2);
        canvas.drawBitmap(this.mBitmap, this.mRect1, rect, this.mPaint);
        canvas.drawBitmap(this.mBitmap, this.mRect2, rect2, this.mPaint);
        canvas.drawBitmap(this.mBitmap, this.mRect3, rect3, this.mPaint);
        canvas.drawBitmap(this.mBitmap, this.mRect4, rect4, this.mPaint);
        canvas.drawBitmap(this.mBitmap, this.mRect5, rect5, this.mPaint);
        canvas.drawBitmap(this.mBitmap, this.mRect6, rect6, this.mPaint);
        canvas.drawBitmap(this.mBitmap, this.mRect7, rect7, this.mPaint);
        canvas.drawBitmap(this.mBitmap, this.mRect8, rect8, this.mPaint);
        canvas.drawBitmap(this.mBitmap, this.mRect9, rect9, this.mPaint);
    }

    @Override // com.androlua.LuaGcable
    public void gc() {
        try {
            this.mBitmap.recycle();
            this.mGc = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // com.androlua.LuaGcable
    public boolean isGc() {
        return this.mGc;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }
}

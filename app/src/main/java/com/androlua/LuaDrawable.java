package com.androlua;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaObject;

/* JADX INFO: loaded from: classes.dex */
public class LuaDrawable extends Drawable {
    private final LuaContext mContext;
    private LuaObject mDraw;
    private LuaFunction mOnDraw;
    private Paint mPaint = new Paint();

    public LuaDrawable(LuaFunction luaFunction) {
        this.mDraw = luaFunction;
        this.mContext = this.mDraw.getLuaState().getContext();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Object objCall;
        try {
            if (this.mOnDraw == null && (objCall = this.mDraw.call(canvas, this.mPaint, this)) != null && (objCall instanceof LuaFunction)) {
                this.mOnDraw = (LuaFunction) objCall;
            }
            LuaFunction luaFunction = this.mOnDraw;
            if (luaFunction != null) {
                luaFunction.call(canvas);
            }
        } catch (LuaException e) {
            this.mContext.sendError("onDraw", e);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Paint getPaint() {
        return this.mPaint;
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

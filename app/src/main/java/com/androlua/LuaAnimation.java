package com.androlua;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.luajava.LuaException;
import com.luajava.LuaFunction;

/* JADX INFO: loaded from: classes.dex */
public class LuaAnimation extends Animation {
    private LuaFunction mAnimation;
    private LuaFunction mApplyTransformation;
    private final LuaContext mContext;

    public LuaAnimation(LuaFunction luaFunction) {
        this.mAnimation = luaFunction;
        this.mContext = luaFunction.getLuaState().getContext();
    }

    @Override // android.view.animation.Animation
    public void applyTransformation(float f, Transformation transformation) {
        Object objCall;
        super.applyTransformation(f, transformation);
        try {
            this.mAnimation.call(Float.valueOf(f), transformation);
            if (this.mApplyTransformation == null && (objCall = this.mAnimation.call(Float.valueOf(f), transformation, this)) != null && (objCall instanceof LuaFunction)) {
                this.mApplyTransformation = (LuaFunction) objCall;
            }
            LuaFunction luaFunction = this.mApplyTransformation;
            if (luaFunction != null) {
                luaFunction.call(Float.valueOf(f), transformation);
            }
        } catch (LuaException e) {
            this.mContext.sendError("applyTransformation", e);
        }
    }

    @Override // android.view.animation.Animation
    public float resolveSize(int i, float f, int i2, int i3) {
        return super.resolveSize(i, f, i2, i3);
    }
}

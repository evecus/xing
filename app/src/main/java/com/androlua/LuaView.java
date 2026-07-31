package com.androlua;

import android.content.Context;
import android.view.View;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaTable;

/* JADX INFO: loaded from: classes.dex */
public class LuaView extends View {
    private LuaObject mOnMeasure;
    private LuaTable mTable;

    public LuaView(Context context) {
        super(context);
    }

    public LuaView(Context context, LuaTable luaTable) {
        super(context);
        this.mTable = luaTable;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        LuaTable luaTable = this.mTable;
        if (luaTable != null) {
            try {
                LuaObject field = luaTable.getField("onMeasure");
                this.mOnMeasure = field;
                if (field.isFunction()) {
                    this.mOnMeasure.call(Integer.valueOf(i), Integer.valueOf(i2), this);
                    return;
                }
            } catch (LuaException e) {
                e.printStackTrace();
            }
        }
        super.onMeasure(i, i2);
    }
}

package com.androlua;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public class LuaContentObserver extends ContentObserver implements LuaGcable {
    private boolean mGc;
    private OnChangeListener mOnChangeListener;

    public interface OnChangeListener {
        void onChange(boolean z, Uri uri, Cursor cursor);
    }

    private LuaContentObserver(Handler handler) {
        super(handler);
    }

    public LuaContentObserver(LuaContext luaContext, Uri uri) {
        this(new Handler(LuaApplication.getInstance().getMainLooper()));
        luaContext.regGc(this);
        LuaApplication.getInstance().getContentResolver().registerContentObserver(uri, true, this);
    }

    public LuaContentObserver(LuaContext luaContext, String str) {
        this(new Handler(LuaApplication.getInstance().getMainLooper()));
        Uri uri = Uri.parse(str);
        luaContext.regGc(this);
        LuaApplication.getInstance().getContentResolver().registerContentObserver(uri, true, this);
    }

    @Override // com.androlua.LuaGcable
    public void gc() {
        LuaApplication.getInstance().getContentResolver().unregisterContentObserver(this);
        this.mGc = true;
    }

    @Override // com.androlua.LuaGcable
    public boolean isGc() {
        return this.mGc;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        if (this.mOnChangeListener != null) {
            Cursor cursorQuery = LuaApplication.getInstance().getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery != null) {
                cursorQuery.moveToFirst();
            }
            this.mOnChangeListener.onChange(z, uri, cursorQuery);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }
}

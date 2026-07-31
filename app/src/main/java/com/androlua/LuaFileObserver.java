package com.androlua;

import android.os.FileObserver;

/* JADX INFO: loaded from: classes.dex */
public class LuaFileObserver extends FileObserver {
    private OnEventListener mOnEventListener;

    public interface OnEventListener {
        void onEvent(int i, String str);
    }

    public LuaFileObserver(String str) {
        super(str);
    }

    public LuaFileObserver(String str, int i) {
        super(str, i);
    }

    @Override // android.os.FileObserver
    public void onEvent(int i, String str) {
        OnEventListener onEventListener = this.mOnEventListener;
        if (onEventListener != null) {
            onEventListener.onEvent(i, str);
        }
    }

    public void setOnEventListener(OnEventListener onEventListener) {
        this.mOnEventListener = onEventListener;
    }
}

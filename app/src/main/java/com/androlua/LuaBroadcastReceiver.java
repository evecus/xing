package com.androlua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class LuaBroadcastReceiver extends BroadcastReceiver {
    private OnReceiveListener mRlt;

    public interface OnReceiveListener {
        void onReceive(Context context, Intent intent);
    }

    public LuaBroadcastReceiver(OnReceiveListener onReceiveListener) {
        this.mRlt = onReceiveListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.mRlt.onReceive(context, intent);
    }
}

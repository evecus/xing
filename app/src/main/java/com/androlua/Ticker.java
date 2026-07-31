package com.androlua;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class Ticker {
    private Handler mHandler;
    private long mLast;
    private long mOffset;
    private OnTickListener mOnTickListener;
    private Thread mThread;
    private long mPeriod = 1000;
    private boolean mEnabled = true;
    private boolean isRun = false;

    public interface OnTickListener {
        void onTick();
    }

    public Ticker() {
        init();
    }

    private void init() {
        this.mHandler = new Handler(this) { // from class: com.androlua.Ticker.1
            public final Ticker this$0;

            {
                this.this$0 = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (this.this$0.mOnTickListener != null) {
                    this.this$0.mOnTickListener.onTick();
                }
            }
        };
        this.mThread = new Thread(this) { // from class: com.androlua.Ticker.2
            public final Ticker this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                this.this$0.isRun = true;
                while (this.this$0.isRun) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (!this.this$0.mEnabled) {
                        Ticker ticker = this.this$0;
                        ticker.mLast = jCurrentTimeMillis - ticker.mOffset;
                    }
                    if (jCurrentTimeMillis - this.this$0.mLast >= this.this$0.mPeriod) {
                        this.this$0.mLast = jCurrentTimeMillis;
                        this.this$0.mHandler.sendEmptyMessage(0);
                    }
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
    }

    public boolean getEnabled() {
        return this.mEnabled;
    }

    public long getInterval() {
        return this.mPeriod;
    }

    public long getPeriod() {
        return this.mPeriod;
    }

    public boolean isRun() {
        return this.isRun;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        if (z) {
            return;
        }
        this.mOffset = System.currentTimeMillis() - this.mLast;
    }

    public void setInterval(long j) {
        this.mLast = System.currentTimeMillis();
        this.mPeriod = j;
    }

    public void setOnTickListener(OnTickListener onTickListener) {
        this.mOnTickListener = onTickListener;
    }

    public void setPeriod(long j) {
        this.mLast = System.currentTimeMillis();
        this.mPeriod = j;
    }

    public void start() {
        this.mThread.start();
    }

    public void stop() {
        this.isRun = false;
    }
}

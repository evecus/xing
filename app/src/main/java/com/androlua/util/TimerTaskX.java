package com.androlua.util;

/* JADX INFO: loaded from: classes.dex */
public abstract class TimerTaskX implements Runnable {
    public boolean cancelled;
    public boolean fixedRate;
    public final Object lock = new Object();
    private boolean mEnabled;
    public long period;
    private long scheduledTime;
    public long when;

    public boolean cancel() {
        boolean z;
        synchronized (this.lock) {
            z = !this.cancelled && this.when > 0;
            this.cancelled = true;
        }
        return z;
    }

    public long getPeriod() {
        return this.period;
    }

    public long getWhen() {
        long j;
        synchronized (this.lock) {
            j = this.when;
        }
        return j;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isScheduled() {
        boolean z;
        synchronized (this.lock) {
            z = this.when > 0 || this.scheduledTime > 0;
        }
        return z;
    }

    @Override // java.lang.Runnable
    public abstract void run();

    public long scheduledExecutionTime() {
        long j;
        synchronized (this.lock) {
            j = this.scheduledTime;
        }
        return j;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public void setPeriod(long j) {
        this.period = j;
    }

    public void setScheduledTime(long j) {
        synchronized (this.lock) {
            this.scheduledTime = j;
        }
    }
}

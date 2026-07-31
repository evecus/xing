package com.androlua.util;

/* JADX INFO: loaded from: classes.dex */
public class VolatileDispose<T> {
    private volatile T mValue;

    public T blockedGet() {
        synchronized (this) {
            if (this.mValue != null) {
                return this.mValue;
            }
            try {
                wait(1000L);
                return this.mValue;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public T blockedGetOrThrow(Class<? extends RuntimeException> cls) {
        synchronized (this) {
            if (this.mValue != null) {
                return this.mValue;
            }
            try {
                wait();
                return this.mValue;
            } catch (InterruptedException e) {
                try {
                    throw cls.newInstance();
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                } catch (InstantiationException e3) {
                    throw new RuntimeException(e3);
                }
            }
        }
    }

    public void setAndNotify(T t) {
        synchronized (this) {
            this.mValue = t;
            notify();
        }
    }
}

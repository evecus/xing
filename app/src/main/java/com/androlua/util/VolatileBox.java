package com.androlua.util;

/* JADX INFO: loaded from: classes.dex */
public class VolatileBox<T> {
    private volatile T mValue;

    public VolatileBox() {
    }

    public VolatileBox(T t) {
        set(t);
    }

    public T blockedGet() {
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this.mValue;
    }

    public T blockedGetOrThrow(Class<? extends RuntimeException> cls) {
        synchronized (this) {
            try {
                wait();
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
        return this.mValue;
    }

    public T get() {
        return this.mValue;
    }

    public boolean isNull() {
        return this.mValue == null;
    }

    public boolean notNull() {
        return this.mValue != null;
    }

    public void set(T t) {
        this.mValue = t;
    }

    public void setAndNotify(T t) {
        this.mValue = t;
        synchronized (this) {
            notify();
        }
    }
}

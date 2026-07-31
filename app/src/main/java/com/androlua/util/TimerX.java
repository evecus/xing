package com.androlua.util;

import java.util.Date;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class TimerX {
    private static long timerId;
    private final FinalizerHelper finalizer;
    private final TimerImpl impl;

    public static final class FinalizerHelper {
        private final TimerImpl impl;

        public FinalizerHelper(TimerImpl timerImpl) {
            this.impl = timerImpl;
        }

        public void finalize() throws Throwable {
            try {
                synchronized (this.impl) {
                    this.impl.finished = true;
                    this.impl.notify();
                }
            } finally {
                super.finalize();
            }
        }
    }

    public static final class TimerImpl extends Thread {
        private boolean cancelled;
        private boolean finished;
        private TimerHeap tasks = new TimerHeap();

        public static final class TimerHeap {
            private int DEFAULT_HEAP_SIZE;
            private int deletedCancelledNumber;
            private int size;
            private TimerTaskX[] timers;

            private TimerHeap() {
                this.DEFAULT_HEAP_SIZE = 256;
                this.timers = new TimerTaskX[256];
                this.size = 0;
                this.deletedCancelledNumber = 0;
            }

            private void downHeap(int i) {
                int i2 = (i * 2) + 1;
                while (true) {
                    int i3 = this.size;
                    if (i2 >= i3 || i3 <= 0) {
                        return;
                    }
                    int i4 = i2 + 1;
                    if (i4 < i3) {
                        TimerTaskX[] timerTaskXArr = this.timers;
                        if (timerTaskXArr[i4].when < timerTaskXArr[i2].when) {
                            i2 = i4;
                        }
                    }
                    TimerTaskX[] timerTaskXArr2 = this.timers;
                    if (timerTaskXArr2[i].when < timerTaskXArr2[i2].when) {
                        return;
                    }
                    TimerTaskX timerTaskX = timerTaskXArr2[i];
                    timerTaskXArr2[i] = timerTaskXArr2[i2];
                    timerTaskXArr2[i2] = timerTaskX;
                    int i5 = i2;
                    i2 = (i2 * 2) + 1;
                    i = i5;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public int getTask(TimerTaskX timerTaskX) {
                int i = 0;
                while (true) {
                    TimerTaskX[] timerTaskXArr = this.timers;
                    if (i >= timerTaskXArr.length) {
                        return -1;
                    }
                    if (timerTaskXArr[i] == timerTaskX) {
                        return i;
                    }
                    i++;
                }
            }

            private void upHeap() {
                int i = this.size - 1;
                int i2 = (i - 1) / 2;
                while (true) {
                    TimerTaskX[] timerTaskXArr = this.timers;
                    if (timerTaskXArr[i].when >= timerTaskXArr[i2].when) {
                        return;
                    }
                    TimerTaskX timerTaskX = timerTaskXArr[i];
                    timerTaskXArr[i] = timerTaskXArr[i2];
                    timerTaskXArr[i2] = timerTaskX;
                    int i3 = i2;
                    i2 = (i2 - 1) / 2;
                    i = i3;
                }
            }

            public void adjustMinimum() {
                downHeap(0);
            }

            public void delete(int i) {
                int i2;
                if (i < 0 || i >= (i2 = this.size)) {
                    return;
                }
                TimerTaskX[] timerTaskXArr = this.timers;
                int i3 = i2 - 1;
                this.size = i3;
                timerTaskXArr[i] = timerTaskXArr[i3];
                timerTaskXArr[i3] = null;
                downHeap(i);
            }

            public void deleteIfCancelled() {
                int i = 0;
                while (i < this.size) {
                    if (this.timers[i].cancelled) {
                        this.deletedCancelledNumber++;
                        delete(i);
                        i--;
                    }
                    i++;
                }
            }

            public void insert(TimerTaskX timerTaskX) {
                TimerTaskX[] timerTaskXArr = this.timers;
                int length = timerTaskXArr.length;
                int i = this.size;
                if (length == i) {
                    TimerTaskX[] timerTaskXArr2 = new TimerTaskX[i * 2];
                    System.arraycopy(timerTaskXArr, 0, timerTaskXArr2, 0, i);
                    this.timers = timerTaskXArr2;
                }
                TimerTaskX[] timerTaskXArr3 = this.timers;
                int i2 = this.size;
                this.size = i2 + 1;
                timerTaskXArr3[i2] = timerTaskX;
                upHeap();
            }

            public boolean isEmpty() {
                return this.size == 0;
            }

            public TimerTaskX minimum() {
                return this.timers[0];
            }

            public void reset() {
                this.timers = new TimerTaskX[this.DEFAULT_HEAP_SIZE];
                this.size = 0;
            }
        }

        public TimerImpl(String str, boolean z) {
            setName(str);
            setDaemon(z);
            start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void insertTask(TimerTaskX timerTaskX) {
            this.tasks.insert(timerTaskX);
            notify();
        }

        public void cancel() {
            synchronized (this) {
                this.cancelled = true;
                this.tasks.reset();
                notify();
            }
        }

        public int purge() {
            if (this.tasks.isEmpty()) {
                return 0;
            }
            this.tasks.deletedCancelledNumber = 0;
            this.tasks.deleteIfCancelled();
            return this.tasks.deletedCancelledNumber;
        }

        /* JADX WARN: Code restructure failed: missing block: B:52:0x0095, code lost:
        
            if (r2.isEnabled() == false) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x0097, code lost:
        
            r2.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x009c, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x009d, code lost:
        
            monitor-enter(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x009f, code lost:
        
            r10.cancelled = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00a2, code lost:
        
            throw r0;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r10 = this;
            L0:
                monitor-enter(r10)
                boolean r0 = r10.cancelled     // Catch: java.lang.Throwable -> Lac
                if (r0 == 0) goto L7
            L5:
                monitor-exit(r10)     // Catch: java.lang.Throwable -> Lac
                goto L14
            L7:
                com.androlua.util.TimerX$TimerImpl$TimerHeap r0 = r10.tasks     // Catch: java.lang.Throwable -> Lac
                boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> Lac
                if (r0 == 0) goto L1b
                boolean r0 = r10.finished     // Catch: java.lang.Throwable -> Lac
                if (r0 == 0) goto L15
                goto L5
            L14:
                return
            L15:
                r10.wait()     // Catch: java.lang.InterruptedException -> L19 java.lang.Throwable -> Lac
                goto L67
            L19:
                r0 = move-exception
                goto L67
            L1b:
                long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lac
                com.androlua.util.TimerX$TimerImpl$TimerHeap r2 = r10.tasks     // Catch: java.lang.Throwable -> Lac
                com.androlua.util.TimerTaskX r2 = r2.minimum()     // Catch: java.lang.Throwable -> Lac
                java.lang.Object r3 = r2.lock     // Catch: java.lang.Throwable -> Lac
                monitor-enter(r3)     // Catch: java.lang.Throwable -> Lac
                boolean r4 = r2.cancelled     // Catch: java.lang.Throwable -> La9
                r5 = 0
                if (r4 == 0) goto L34
                com.androlua.util.TimerX$TimerImpl$TimerHeap r0 = r10.tasks     // Catch: java.lang.Throwable -> La9
                r0.delete(r5)     // Catch: java.lang.Throwable -> La9
                monitor-exit(r3)     // Catch: java.lang.Throwable -> La9
                goto L67
            L34:
                long r6 = r2.when     // Catch: java.lang.Throwable -> La9
                long r6 = r6 - r0
                monitor-exit(r3)     // Catch: java.lang.Throwable -> La9
                r0 = 0
                int r3 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                if (r3 <= 0) goto L42
                r10.wait(r6)     // Catch: java.lang.InterruptedException -> L19 java.lang.Throwable -> Lac
                goto L67
            L42:
                java.lang.Object r3 = r2.lock     // Catch: java.lang.Throwable -> Lac
                monitor-enter(r3)     // Catch: java.lang.Throwable -> Lac
                com.androlua.util.TimerX$TimerImpl$TimerHeap r4 = r10.tasks     // Catch: java.lang.Throwable -> La6
                com.androlua.util.TimerTaskX r4 = r4.minimum()     // Catch: java.lang.Throwable -> La6
                long r6 = r4.when     // Catch: java.lang.Throwable -> La6
                long r8 = r2.when     // Catch: java.lang.Throwable -> La6
                int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r4 == 0) goto L59
                com.androlua.util.TimerX$TimerImpl$TimerHeap r4 = r10.tasks     // Catch: java.lang.Throwable -> La6
                int r5 = com.androlua.util.TimerX.TimerImpl.TimerHeap.access$100(r4, r2)     // Catch: java.lang.Throwable -> La6
            L59:
                boolean r4 = r2.cancelled     // Catch: java.lang.Throwable -> La6
                if (r4 == 0) goto L69
                com.androlua.util.TimerX$TimerImpl$TimerHeap r0 = r10.tasks     // Catch: java.lang.Throwable -> La6
                int r1 = com.androlua.util.TimerX.TimerImpl.TimerHeap.access$100(r0, r2)     // Catch: java.lang.Throwable -> La6
                r0.delete(r1)     // Catch: java.lang.Throwable -> La6
                monitor-exit(r3)     // Catch: java.lang.Throwable -> La6
            L67:
                monitor-exit(r10)     // Catch: java.lang.Throwable -> Lac
                goto L0
            L69:
                long r6 = r2.when     // Catch: java.lang.Throwable -> La6
                r2.setScheduledTime(r6)     // Catch: java.lang.Throwable -> La6
                com.androlua.util.TimerX$TimerImpl$TimerHeap r4 = r10.tasks     // Catch: java.lang.Throwable -> La6
                r4.delete(r5)     // Catch: java.lang.Throwable -> La6
                long r4 = r2.period     // Catch: java.lang.Throwable -> La6
                int r6 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r6 < 0) goto L8d
                boolean r0 = r2.fixedRate     // Catch: java.lang.Throwable -> La6
                if (r0 == 0) goto L80
                long r0 = r2.when     // Catch: java.lang.Throwable -> La6
                goto L86
            L80:
                long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> La6
                long r4 = r2.period     // Catch: java.lang.Throwable -> La6
            L86:
                long r0 = r0 + r4
                r2.when = r0     // Catch: java.lang.Throwable -> La6
                r10.insertTask(r2)     // Catch: java.lang.Throwable -> La6
                goto L8f
            L8d:
                r2.when = r0     // Catch: java.lang.Throwable -> La6
            L8f:
                monitor-exit(r3)     // Catch: java.lang.Throwable -> La6
                monitor-exit(r10)     // Catch: java.lang.Throwable -> Lac
                boolean r0 = r2.isEnabled()     // Catch: java.lang.Throwable -> L9c
                if (r0 == 0) goto L0
                r2.run()     // Catch: java.lang.Throwable -> L9c
                goto L0
            L9c:
                r0 = move-exception
                monitor-enter(r10)
                r1 = 1
                r10.cancelled = r1     // Catch: java.lang.Throwable -> La3
                monitor-exit(r10)     // Catch: java.lang.Throwable -> La3
                throw r0
            La3:
                r0 = move-exception
                monitor-exit(r10)     // Catch: java.lang.Throwable -> La3
                throw r0
            La6:
                r0 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> La6
                throw r0     // Catch: java.lang.Throwable -> Lac
            La9:
                r0 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> La9
                throw r0     // Catch: java.lang.Throwable -> Lac
            Lac:
                r0 = move-exception
                monitor-exit(r10)     // Catch: java.lang.Throwable -> Lac
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.androlua.util.TimerX.TimerImpl.run():void");
        }
    }

    public TimerX() {
        this(false);
    }

    public TimerX(String str) {
        this(str, false);
    }

    public TimerX(String str, boolean z) {
        Objects.requireNonNull(str, "name is null");
        TimerImpl timerImpl = new TimerImpl(str, z);
        this.impl = timerImpl;
        this.finalizer = new FinalizerHelper(timerImpl);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TimerX(boolean z) {
        StringBuilder sbO = a.o("Timer-");
        sbO.append(nextId());
        this(sbO.toString(), z);
    }

    private static long nextId() {
        long j;
        synchronized (TimerX.class) {
            try {
                j = timerId;
                timerId = 1 + j;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j;
    }

    private void scheduleImpl(TimerTaskX timerTaskX, long j, long j2, boolean z) {
        synchronized (this.impl) {
            if (this.impl.cancelled) {
                throw new IllegalStateException("Timer was canceled");
            }
            long jCurrentTimeMillis = System.currentTimeMillis() + j;
            if (jCurrentTimeMillis < 0) {
                throw new IllegalArgumentException("Illegal delay to start the TimerTask: " + jCurrentTimeMillis);
            }
            synchronized (timerTaskX.lock) {
                if (timerTaskX.isScheduled()) {
                    throw new IllegalStateException("TimerTask is scheduled already");
                }
                if (timerTaskX.cancelled) {
                    throw new IllegalStateException("TimerTask is canceled");
                }
                timerTaskX.when = jCurrentTimeMillis;
                timerTaskX.period = j2;
                timerTaskX.fixedRate = z;
            }
            this.impl.insertTask(timerTaskX);
        }
    }

    public void cancel() {
        this.impl.cancel();
    }

    public int purge() {
        int iPurge;
        synchronized (this.impl) {
            iPurge = this.impl.purge();
        }
        return iPurge;
    }

    public void schedule(TimerTaskX timerTaskX, long j) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        scheduleImpl(timerTaskX, j, -1L, false);
    }

    public void schedule(TimerTaskX timerTaskX, long j, long j2) {
        if (j < 0 || j2 <= 0) {
            throw new IllegalArgumentException();
        }
        scheduleImpl(timerTaskX, j, j2, false);
    }

    public void schedule(TimerTaskX timerTaskX, Date date) {
        if (date.getTime() < 0) {
            throw new IllegalArgumentException();
        }
        long time = date.getTime() - System.currentTimeMillis();
        scheduleImpl(timerTaskX, time < 0 ? 0L : time, -1L, false);
    }

    public void schedule(TimerTaskX timerTaskX, Date date, long j) {
        if (j <= 0 || date.getTime() < 0) {
            throw new IllegalArgumentException();
        }
        long time = date.getTime() - System.currentTimeMillis();
        scheduleImpl(timerTaskX, time < 0 ? 0L : time, j, false);
    }

    public void scheduleAtFixedRate(TimerTaskX timerTaskX, long j, long j2) {
        if (j < 0 || j2 <= 0) {
            throw new IllegalArgumentException();
        }
        scheduleImpl(timerTaskX, j, j2, true);
    }

    public void scheduleAtFixedRate(TimerTaskX timerTaskX, Date date, long j) {
        if (j <= 0 || date.getTime() < 0) {
            throw new IllegalArgumentException();
        }
        scheduleImpl(timerTaskX, date.getTime() - System.currentTimeMillis(), j, true);
    }
}

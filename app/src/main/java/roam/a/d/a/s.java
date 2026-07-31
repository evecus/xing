package roam.a.d.a;

import android.os.AsyncTask;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class s implements Executor {
    public static final Executor c = AsyncTask.THREAD_POOL_EXECUTOR;
    public final ArrayDeque<Runnable> a = new ArrayDeque<>();
    public Runnable b;

    public class a implements Runnable {
        public final Runnable a;
        public final s b;

        public a(s sVar, Runnable runnable) {
            this.b = sVar;
            this.a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.a.run();
            } finally {
                this.b.a();
            }
        }
    }

    public void a() {
        synchronized (this) {
            Runnable runnablePoll = this.a.poll();
            this.b = runnablePoll;
            if (runnablePoll != null) {
                c.execute(runnablePoll);
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        synchronized (this) {
            this.a.offer(new a(this, runnable));
            if (this.b == null) {
                a();
            }
        }
    }
}

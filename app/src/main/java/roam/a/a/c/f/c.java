package roam.a.a.c.f;

import android.os.Process;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {
    public final b a;

    public c(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!this.a.b.isEmpty()) {
                Runnable runnable = this.a.b.get(0);
                this.a.b.remove(0);
                if (runnable != null) {
                    runnable.run();
                }
            }
        } catch (Exception e) {
        } catch (Throwable th) {
            this.a.a = null;
            throw th;
        }
        this.a.a = null;
    }
}

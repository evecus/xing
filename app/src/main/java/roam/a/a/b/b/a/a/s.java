package roam.a.a.b.b.a.a;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public final class s extends FutureTask<y> {
    public final w a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(r rVar, Callable callable, w wVar) {
        super(callable);
        this.a = wVar;
    }

    @Override // java.util.concurrent.FutureTask
    public final void done() {
        Objects.requireNonNull(this.a.c);
        super.done();
    }
}

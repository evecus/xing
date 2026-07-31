package roam.a.a.b.b.a.a;

import com.baidu.mobstat.Config;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.http.message.BasicHeader;

/* JADX INFO: loaded from: classes.dex */
public final class p extends e {
    public o f;

    public p(o oVar, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.f = oVar;
    }

    public final Object a() {
        u uVar = new u(this.f.a.a);
        uVar.c = this.a;
        uVar.d = this.d;
        uVar.g = this.e;
        uVar.b(Config.FEED_LIST_ITEM_CUSTOM_ID, String.valueOf(this.c));
        uVar.b("operationType", this.b);
        Objects.requireNonNull(this.f.a);
        uVar.b("gzip", String.valueOf(false));
        uVar.e.add(new BasicHeader("uuid", UUID.randomUUID().toString()));
        Objects.requireNonNull(this.f.a);
        StringBuilder sb = new StringBuilder("threadid = ");
        sb.append(Thread.currentThread().getId());
        sb.append("; ");
        sb.append(uVar.toString());
        try {
            y yVar = (y) ((FutureTask) ((r) this.f.a()).b(uVar)).get();
            if (yVar != null) {
                return yVar.a;
            }
            throw new c(9, "response is null");
        } catch (InterruptedException e) {
            throw new c(13, "", e);
        } catch (CancellationException e2) {
            throw new c(13, "", e2);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause == null || !(cause instanceof a)) {
                throw new c(9, "", e3);
            }
            a aVar = (a) cause;
            int i = aVar.a;
            switch (i) {
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 3;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 5;
                    break;
                case 5:
                    i = 6;
                    break;
                case 6:
                    i = 7;
                    break;
                case 7:
                    i = 8;
                    break;
                case 8:
                    i = 15;
                    break;
                case 9:
                    i = 16;
                    break;
            }
            throw new c(Integer.valueOf(i), aVar.b);
        }
    }
}

package roam.a.a.g.a.a.e;

import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {
    public final b a;

    public c(b bVar, roam.a.a.h.a.a.b.a.b.a aVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            b.e = this.a.c.a();
        } catch (Throwable th) {
            roam.a.a.h.a.a.b.a.b.b bVar = new roam.a.a.h.a.a.b.a.b.b();
            b.e = bVar;
            StringBuilder sb = new StringBuilder("static data rpc upload error, ");
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            sb.append(stringWriter.toString());
            bVar.a = sb.toString();
            StringWriter stringWriter2 = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter2));
            stringWriter2.toString();
        }
    }
}

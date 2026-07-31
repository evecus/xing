package roam.a.e.a;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {
    public s a() {
        if (this instanceof s) {
            return (s) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            roam.a.e.a.c0.c cVar = new roam.a.e.a.c0.c(stringWriter);
            cVar.f = true;
            roam.a.e.a.a0.z.o.X.b(cVar, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}

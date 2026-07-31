package roam.b.c.a.a.k.v;

import android.graphics.Bitmap;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import org.roam.ui.fragment.WebViewMenuSupport;
import org.roam.util.ThreadSupport;

/* JADX INFO: loaded from: classes.dex */
public class q extends SimpleTarget<Bitmap> {
    public final WebViewMenuSupport.a a;

    public q(WebViewMenuSupport.a aVar) {
        this.a = aVar;
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onResourceReady(Object obj, Transition transition) {
        final Bitmap bitmap = (Bitmap) obj;
        ThreadSupport threadSupport = new ThreadSupport();
        threadSupport.setRunnable(new ThreadSupport.a(this, bitmap) { // from class: roam.b.c.a.a.k.v.h
            public final q a;
            public final Bitmap b;

            {
                this.a = this;
                this.b = bitmap;
            }

            /* JADX WARN: Removed duplicated region for block: B:9:0x001e  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void a(org.roam.util.ThreadSupport r4, java.lang.Object[] r5) {
                /*
                    r3 = this;
                    roam.b.c.a.a.k.v.q r5 = r3.a
                    android.graphics.Bitmap r0 = r3.b
                    org.roam.ui.fragment.WebViewMenuSupport$a r1 = r5.a
                    org.roam.ui.fragment.WebViewMenuSupport r1 = r1.a
                    androidx.appcompat.app.AppCompatActivity r1 = r1.f
                    java.io.File r1 = roam.b.c.a.a.l.b.a(r1)
                    if (r0 != 0) goto L11
                    goto L1e
                L11:
                    java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Exception -> L1d
                    r2.<init>(r1)     // Catch: java.lang.Exception -> L1d
                    boolean r2 = roam.a.a.a.b.a.I(r0, r2)     // Catch: java.lang.Exception -> L1d
                    if (r2 != 0) goto L26
                    goto L1e
                L1d:
                    r2 = move-exception
                L1e:
                    roam.b.c.a.a.k.v.g r2 = new roam.b.c.a.a.k.v.g
                    r2.<init>(r5)
                    r4.call(r2)
                L26:
                    boolean r2 = r0.isRecycled()
                    if (r2 != 0) goto L2f
                    r0.recycle()
                L2f:
                    roam.b.c.a.a.k.v.f r0 = new roam.b.c.a.a.k.v.f
                    r0.<init>(r5, r1)
                    r4.call(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: roam.b.c.a.a.k.v.h.a(org.roam.util.ThreadSupport, java.lang.Object[]):void");
            }
        });
        threadSupport.start(new Object[0]);
    }
}

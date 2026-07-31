package roam.b.c.a.a.k.v;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import java.util.Objects;
import org.roam.R;
import roam.a.d.a.l;
import roam.b.c.a.a.k.v.m;
import roam.b.c.a.a.m.k1;
import roam.b.c.a.a.m.x;

/* JADX INFO: loaded from: classes.dex */
public class n implements View.OnClickListener {
    public final WebView a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final long f;
    public final m.b g;

    public class a extends x {
        public final n i;

        /* JADX INFO: renamed from: roam.b.c.a.a.k.v.n$a$a, reason: collision with other inner class name */
        public class C0023a extends roam.a.d.a.f {
            public final a a;

            public C0023a(a aVar) {
                this.a = aVar;
            }

            @Override // roam.a.d.a.f, roam.a.d.a.l
            @l.a
            public void a(String str, long j, long j2, long j3) {
            }

            @Override // roam.a.d.a.e
            public boolean b(Throwable th, Uri uri, String str, roam.a.d.a.o oVar) {
                Toast.makeText(this.a.i.g.d.requireActivity(), R.string.r, 0).show();
                return false;
            }

            @Override // roam.a.d.a.f, roam.a.d.a.e
            public void c(String str, String str2, String str3, String str4, long j, roam.a.d.a.o oVar) {
                Toast.makeText(this.a.i.g.d.requireActivity(), R.string.r, 0).show();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(n nVar, Activity activity, WebView webView, k1 k1Var) {
            super(activity, webView, k1Var);
            this.i = nVar;
        }

        @Override // roam.b.c.a.a.m.x
        public roam.a.d.a.q b(String str) {
            roam.a.d.a.d dVar = roam.a.d.a.d.b;
            Context applicationContext = this.i.g.d.getActivity().getApplicationContext();
            Objects.requireNonNull(dVar);
            if (applicationContext != null) {
                roam.a.d.a.d.c = applicationContext.getApplicationContext();
            }
            roam.a.d.a.q qVarC = roam.a.d.a.q.c(roam.a.d.a.d.c);
            roam.a.d.a.h hVar = qVarC.a;
            hVar.g = str;
            hVar.p = true;
            hVar.b = true;
            qVarC.a();
            roam.a.d.a.h hVar2 = qVarC.a;
            hVar2.s = 5;
            hVar2.o = 100000L;
            return qVarC;
        }

        @Override // roam.b.c.a.a.m.x
        public void e(roam.a.d.a.q qVar) {
            qVar.b(new C0023a(this));
        }
    }

    public n(m.b bVar, WebView webView, String str, String str2, String str3, String str4, long j) {
        this.g = bVar;
        this.a = webView;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = j;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        new a(this, this.g.d.getActivity(), this.a, this.g.d.c.getPermissionInterceptor()).onDownloadStart(this.b, this.c, this.d, this.e, this.f);
    }
}

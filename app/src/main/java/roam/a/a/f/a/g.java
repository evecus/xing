package roam.a.a.f.a;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class g implements DialogInterface.OnClickListener {
    public final e a;

    public g(e eVar) {
        this.a = eVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.cancel();
        this.a.b.b = false;
        roam.a.a.a.b.a.a = roam.a.a.a.b.a.c();
        this.a.b.a.finish();
    }
}

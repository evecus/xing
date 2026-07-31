package roam.a.a.f.a;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class f implements DialogInterface.OnClickListener {
    public final e a;

    public f(e eVar) {
        this.a = eVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        e eVar = this.a;
        eVar.b.b = true;
        eVar.a.proceed();
        dialogInterface.dismiss();
    }
}

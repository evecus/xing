package roam.a.a.f.j;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import roam.a.a.b.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class d implements ServiceConnection {
    public final c a;

    public d(c cVar) {
        this.a = cVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        roam.a.a.b.a.a c0013a;
        synchronized (this.a.c) {
            c cVar = this.a;
            int i = a.AbstractBinderC0012a.a;
            if (iBinder == null) {
                c0013a = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.alipay.android.app.IAlixPay");
                c0013a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof roam.a.a.b.a.a)) ? new a.AbstractBinderC0012a.C0013a(iBinder) : (roam.a.a.b.a.a) iInterfaceQueryLocalInterface;
            }
            cVar.b = c0013a;
            this.a.c.notify();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.b = null;
    }
}

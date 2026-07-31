package roam.a.a.b.a;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import roam.a.a.f.a.j;
import roam.a.a.f.j.e;

/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    public static abstract class a extends Binder implements b {
        public a() {
            attachInterface(this, "com.alipay.android.app.IRemoteServiceCallback");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
                String string = parcel.readString();
                String string2 = parcel.readString();
                int i3 = parcel.readInt();
                Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                e eVar = (e) this;
                Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
                if (bundle == null) {
                    bundle = new Bundle();
                }
                try {
                    bundle.putInt("CallingPid", i3);
                    intent.putExtras(bundle);
                } catch (Exception e) {
                }
                intent.setClassName(string, string2);
                Activity activity = eVar.a.a;
                if (activity != null) {
                    activity.startActivity(intent);
                }
                ((j) eVar.a.e).a.d();
            } else {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 1598968902) {
                            return super.onTransact(i, parcel, parcel2, i2);
                        }
                        parcel2.writeString("com.alipay.android.app.IRemoteServiceCallback");
                        return true;
                    }
                    parcel.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
                    parcel2.writeNoException();
                    parcel2.writeInt(0);
                    return true;
                }
                parcel.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
                parcel.readInt();
                parcel.readString();
            }
            parcel2.writeNoException();
            return true;
        }
    }
}

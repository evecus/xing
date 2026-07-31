package roam.a.a.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import roam.a.a.b.a.b;

/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: roam.a.a.b.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0012a extends Binder implements a {
        public static final int a = 0;

        /* JADX INFO: renamed from: roam.a.a.b.a.a$a$a, reason: collision with other inner class name */
        public static final class C0013a implements a {
            public IBinder a;

            public C0013a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // roam.a.a.b.a.a
            public final void a(b bVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alipay.android.app.IAlixPay");
                    parcelObtain.writeStrongBinder(bVar != null ? (b.a) bVar : null);
                    this.a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.a;
            }

            @Override // roam.a.a.b.a.a
            public final String b(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alipay.android.app.IAlixPay");
                    parcelObtain.writeString(str);
                    this.a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // roam.a.a.b.a.a
            public final void c(b bVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.alipay.android.app.IAlixPay");
                    parcelObtain.writeStrongBinder(bVar != null ? (b.a) bVar : null);
                    this.a.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }

    void a(b bVar);

    String b(String str);

    void c(b bVar);
}

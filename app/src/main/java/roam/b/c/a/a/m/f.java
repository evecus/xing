package roam.b.c.a.a.m;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new a();
    public ArrayList<String> a;
    public int b;
    public int c;

    public static final class a implements Parcelable.Creator<f> {
        @Override // android.os.Parcelable.Creator
        public f createFromParcel(Parcel parcel) {
            return new f(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public f[] newArray(int i) {
            return new f[i];
        }
    }

    public f() {
        this.a = new ArrayList<>();
    }

    public f(Parcel parcel) {
        this.a = new ArrayList<>();
        this.a = parcel.createStringArrayList();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
    }

    public static f a(String[] strArr) {
        f fVar = new f();
        fVar.b = 1;
        fVar.a = new ArrayList<>(Arrays.asList(strArr));
        return fVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
    }
}

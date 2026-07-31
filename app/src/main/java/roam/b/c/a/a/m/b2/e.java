package roam.b.c.a.a.m.b2;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new a();
    public int a;
    public String b;
    public String c;

    public static final class a implements Parcelable.Creator<e> {
        @Override // android.os.Parcelable.Creator
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public e[] newArray(int i) {
            return new e[i];
        }
    }

    public e(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    public e(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sbO = roam.a.b.a.a.a.o("FileParcel{mId=");
        sbO.append(this.a);
        sbO.append(", mContentPath='");
        sbO.append(this.b);
        sbO.append('\'');
        sbO.append(", mFileBase64='");
        sbO.append(this.c);
        sbO.append('\'');
        sbO.append('}');
        return sbO.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }
}

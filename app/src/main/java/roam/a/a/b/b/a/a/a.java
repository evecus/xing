package roam.a.a.b.b.a.a;

/* JADX INFO: loaded from: classes.dex */
public class a extends Exception {
    public int a;
    public String b;

    /* JADX WARN: Illegal instructions before constructor call */
    public a(Integer num, String str) {
        StringBuilder sbO = roam.a.b.a.a.a.o("Http Transport error");
        if (num != null) {
            sbO.append("[");
            sbO.append(num);
            sbO.append("]");
        }
        sbO.append(" : ");
        if (str != null) {
            sbO.append(str);
        }
        super(sbO.toString());
        this.a = num.intValue();
        this.b = str;
    }
}

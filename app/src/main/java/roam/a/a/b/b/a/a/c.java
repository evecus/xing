package roam.a.a.b.b.a.a;

/* JADX INFO: loaded from: classes.dex */
public class c extends RuntimeException {
    public c(Integer num, String str) {
        super(a(num, str));
        num.intValue();
    }

    public c(Integer num, String str, Throwable th) {
        super(a(num, str), th);
        num.intValue();
    }

    public static String a(Integer num, String str) {
        StringBuilder sbO = roam.a.b.a.a.a.o("RPCException: ");
        if (num != null) {
            sbO.append("[");
            sbO.append(num);
            sbO.append("]");
        }
        sbO.append(" : ");
        if (str != null) {
            sbO.append(str);
        }
        return sbO.toString();
    }
}

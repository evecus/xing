package roam.a.e.a.a0;

/* JADX INFO: loaded from: classes.dex */
public final class p {
    public static final int a;

    static {
        int i;
        String property = System.getProperty("java.version");
        try {
            String[] strArrSplit = property.split("[._]");
            i = Integer.parseInt(strArrSplit[0]);
            if (i == 1 && strArrSplit.length > 1) {
                i = Integer.parseInt(strArrSplit[1]);
            }
        } catch (NumberFormatException e) {
            i = -1;
        }
        if (i == -1) {
            try {
                StringBuilder sb = new StringBuilder();
                for (int i2 = 0; i2 < property.length(); i2++) {
                    char cCharAt = property.charAt(i2);
                    if (!Character.isDigit(cCharAt)) {
                        break;
                    }
                    sb.append(cCharAt);
                }
                i = Integer.parseInt(sb.toString());
            } catch (NumberFormatException e2) {
                i = -1;
            }
        }
        if (i == -1) {
            i = 6;
        }
        a = i;
    }
}

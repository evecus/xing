package roam.a.e.a.a0.z.t;

import com.google.android.material.datepicker.UtcDates;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static final TimeZone a = TimeZone.getTimeZone(UtcDates.UTC);

    public static boolean a(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00e2 A[Catch: IllegalArgumentException -> 0x01d1, NumberFormatException -> 0x01d3, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01d5, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01d5, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:16:0x0051, B:18:0x0061, B:19:0x0063, B:21:0x006f, B:22:0x0071, B:24:0x0077, B:28:0x0081, B:33:0x0091, B:35:0x0099, B:36:0x009d, B:38:0x00a3, B:43:0x00b0, B:45:0x00ba, B:56:0x00dc, B:58:0x00e2, B:60:0x00e9, B:85:0x0198, B:65:0x00f5, B:66:0x0110, B:67:0x0111, B:71:0x012d, B:73:0x013a, B:76:0x0143, B:78:0x0162, B:81:0x0171, B:82:0x0193, B:84:0x0196, B:70:0x011c, B:87:0x01c9, B:88:0x01d0, B:49:0x00ca, B:50:0x00cd, B:44:0x00b5), top: B:104:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c9 A[Catch: IllegalArgumentException -> 0x01d1, NumberFormatException -> 0x01d3, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01d5, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01d5, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:16:0x0051, B:18:0x0061, B:19:0x0063, B:21:0x006f, B:22:0x0071, B:24:0x0077, B:28:0x0081, B:33:0x0091, B:35:0x0099, B:36:0x009d, B:38:0x00a3, B:43:0x00b0, B:45:0x00ba, B:56:0x00dc, B:58:0x00e2, B:60:0x00e9, B:85:0x0198, B:65:0x00f5, B:66:0x0110, B:67:0x0111, B:71:0x012d, B:73:0x013a, B:76:0x0143, B:78:0x0162, B:81:0x0171, B:82:0x0193, B:84:0x0196, B:70:0x011c, B:87:0x01c9, B:88:0x01d0, B:49:0x00ca, B:50:0x00cd, B:44:0x00b5), top: B:104:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Date b(java.lang.String r17, java.text.ParsePosition r18) throws java.text.ParseException {
        /*
            Method dump skipped, instruction units count: 570
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.a0.z.t.a.b(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    public static int c(String str, int i, int i2) {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int iDigit = Character.digit(str.charAt(i), 10);
            if (iDigit < 0) {
                StringBuilder sbO = roam.a.b.a.a.a.o("Invalid number: ");
                sbO.append(str.substring(i, i2));
                throw new NumberFormatException(sbO.toString());
            }
            i3 = -iDigit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int iDigit2 = Character.digit(str.charAt(i4), 10);
            if (iDigit2 < 0) {
                StringBuilder sbO2 = roam.a.b.a.a.a.o("Invalid number: ");
                sbO2.append(str.substring(i, i2));
                throw new NumberFormatException(sbO2.toString());
            }
            i3 = (i3 * 10) - iDigit2;
            i4++;
        }
        return -i3;
    }
}

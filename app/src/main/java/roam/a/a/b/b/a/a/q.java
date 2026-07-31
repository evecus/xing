package roam.a.a.b.b.a.a;

import androidx.recyclerview.widget.RecyclerView;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class q {
    public static final Pattern a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
    public static final Pattern b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    public static final class a {
        public int a;
        public int b;
        public int c;

        public a(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    public static int a(String str) {
        return str.length() == 2 ? ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0') : str.charAt(0) - '0';
    }

    public static int b(String str) {
        int lowerCase = ((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291;
        if (lowerCase == 9) {
            return 11;
        }
        if (lowerCase == 10) {
            return 1;
        }
        if (lowerCase == 22) {
            return 0;
        }
        if (lowerCase == 26) {
            return 7;
        }
        if (lowerCase == 29) {
            return 2;
        }
        if (lowerCase == 32) {
            return 3;
        }
        if (lowerCase == 40) {
            return 6;
        }
        if (lowerCase == 42) {
            return 5;
        }
        if (lowerCase == 48) {
            return 10;
        }
        switch (lowerCase) {
            case 35:
                return 9;
            case 36:
                return 4;
            case 37:
                return 8;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int c(String str) {
        int iCharAt;
        if (str.length() == 2) {
            iCharAt = ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
            if (iCharAt < 70) {
                return iCharAt + RecyclerView.MAX_SCROLL_DURATION;
            }
        } else {
            if (str.length() != 3) {
                if (str.length() == 4) {
                    return ((str.charAt(0) - '0') * 1000) + ((str.charAt(1) - '0') * 100) + ((str.charAt(2) - '0') * 10) + (str.charAt(3) - '0');
                }
                return 1970;
            }
            iCharAt = ((str.charAt(0) - '0') * 100) + ((str.charAt(1) - '0') * 10) + (str.charAt(2) - '0');
        }
        return iCharAt + 1900;
    }

    public static a d(String str) {
        int i;
        int iCharAt = str.charAt(0) - '0';
        if (str.charAt(1) != ':') {
            iCharAt = (iCharAt * 10) + (str.charAt(1) - '0');
            i = 2;
        } else {
            i = 1;
        }
        int i2 = i + 1;
        int i3 = i2 + 1;
        char cCharAt = str.charAt(i2);
        char cCharAt2 = str.charAt(i3);
        int i4 = i3 + 1 + 1;
        return new a(iCharAt, ((cCharAt - '0') * 10) + (cCharAt2 - '0'), (str.charAt(i4 + 1) - '0') + ((str.charAt(i4) - '0') * 10));
    }
}

package roam.a.e.a;

import com.baidu.android.common.util.HanziToPinyin;
import com.baidu.mobstat.Config;
import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class c implements roam.a.e.a.d {
    public static final c a;
    public static final c b;
    public static final c c;
    public static final c d;
    public static final c e;
    public static final c f;
    public static final c[] g;

    public enum a extends c {
        public a(String str, int i) {
            super(str, i, null);
        }

        @Override // roam.a.e.a.d
        public String a(Field field) {
            return field.getName();
        }
    }

    static {
        a aVar = new a("IDENTITY", 0);
        a = aVar;
        c cVar = new c("UPPER_CAMEL_CASE", 1) { // from class: roam.a.e.a.c.b
            @Override // roam.a.e.a.d
            public String a(Field field) {
                return c.c(field.getName());
            }
        };
        b = cVar;
        c cVar2 = new c("UPPER_CAMEL_CASE_WITH_SPACES", 2) { // from class: roam.a.e.a.c.c
            @Override // roam.a.e.a.d
            public String a(Field field) {
                return c.c(c.b(field.getName(), HanziToPinyin.Token.SEPARATOR));
            }
        };
        c = cVar2;
        c cVar3 = new c("LOWER_CASE_WITH_UNDERSCORES", 3) { // from class: roam.a.e.a.c.d
            @Override // roam.a.e.a.d
            public String a(Field field) {
                return c.b(field.getName(), Config.replace).toLowerCase(Locale.ENGLISH);
            }
        };
        d = cVar3;
        c cVar4 = new c("LOWER_CASE_WITH_DASHES", 4) { // from class: roam.a.e.a.c.e
            @Override // roam.a.e.a.d
            public String a(Field field) {
                return c.b(field.getName(), "-").toLowerCase(Locale.ENGLISH);
            }
        };
        e = cVar4;
        c cVar5 = new c("LOWER_CASE_WITH_DOTS", 5) { // from class: roam.a.e.a.c.f
            @Override // roam.a.e.a.d
            public String a(Field field) {
                return c.b(field.getName(), ".").toLowerCase(Locale.ENGLISH);
            }
        };
        f = cVar5;
        g = new c[]{aVar, cVar, cVar2, cVar3, cVar4, cVar5};
    }

    public c(String str, int i, a aVar) {
    }

    public static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static String c(String str) {
        int length = str.length();
        int i = 0;
        while (!Character.isLetter(str.charAt(i)) && i < length - 1) {
            i++;
        }
        char cCharAt = str.charAt(i);
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        char upperCase = Character.toUpperCase(cCharAt);
        if (i == 0) {
            return upperCase + str.substring(1);
        }
        return str.substring(0, i) + upperCase + str.substring(i + 1);
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) g.clone();
    }
}

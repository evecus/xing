package roam.b.c.a.a.m;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class i {
    public static String b;
    public static final String a = roam.a.b.a.a.a.m(new StringBuilder(), File.separator, "web-cache");
    public static volatile boolean c = false;
    public static final String d = i.class.getSimpleName();
    public static int e = 5242880;

    public static String a(Context context) {
        return context.getCacheDir().getAbsolutePath() + a;
    }
}

package roam.b.c.a.a.l;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static File a(Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(context.getExternalCacheDir().getAbsolutePath());
        File file = new File(roam.a.b.a.a.a.l(sb, File.separatorChar, "temporary"));
        if (!file.exists()) {
            file.mkdirs();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(file.getAbsolutePath());
        return new File(roam.a.b.a.a.a.l(sb2, File.separatorChar, String.valueOf(jCurrentTimeMillis)));
    }
}

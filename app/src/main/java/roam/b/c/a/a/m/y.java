package roam.b.c.a.a.m;

import android.os.Bundle;
import com.roamexplore.WebViewActionActivity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class y implements WebViewActionActivity.b {
    public final String a;
    public final x b;

    public y(x xVar, String str) {
        this.b = xVar;
        this.a = str;
    }

    public void a(String[] strArr, int[] iArr, Bundle bundle) {
        if (((ArrayList) this.b.a()).isEmpty()) {
            this.b.d(this.a);
            return;
        }
        if (this.b.e.get() != null) {
            this.b.e.get().k((String[]) ((ArrayList) this.b.a()).toArray(new String[0]), "Storage", "Download");
        }
        String str = x.g;
        String str2 = x.g;
        String str3 = i.a;
    }
}

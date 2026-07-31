package roam.b.c.a.a.m;

import android.webkit.GeolocationPermissions;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/* JADX INFO: loaded from: classes.dex */
public class r extends BaseTransientBottomBar.BaseCallback<Snackbar> {
    public final GeolocationPermissions.Callback a;
    public final String b;

    public r(s sVar, GeolocationPermissions.Callback callback, String str) {
        this.a = callback;
        this.b = str;
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
    public void onDismissed(Snackbar snackbar, int i) {
        Snackbar snackbar2 = snackbar;
        if (i == 0) {
            this.a.invoke(this.b, false, true);
        }
        super.onDismissed(snackbar2, i);
    }
}

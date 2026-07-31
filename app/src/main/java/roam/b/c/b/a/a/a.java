package roam.b.c.b.a.a;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class a extends FrameLayout {
    public roam.b.c.b.a.a.c.a a;

    public a(Context context) {
        super(context);
    }

    public roam.b.c.b.a.a.c.a getNavigator() {
        return this.a;
    }

    public void setNavigator(roam.b.c.b.a.a.c.a aVar) {
        roam.b.c.b.a.a.c.a aVar2 = this.a;
        if (aVar2 == aVar) {
            return;
        }
        if (aVar2 != null) {
            Objects.requireNonNull((roam.b.c.b.a.a.d.a.a) aVar2);
        }
        this.a = aVar;
        removeAllViews();
        if (this.a instanceof View) {
            addView((View) this.a, new FrameLayout.LayoutParams(-1, -1));
            ((roam.b.c.b.a.a.d.a.a) this.a).a();
        }
    }
}

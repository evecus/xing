package roam.b.c.a.a.k.w;

import android.content.Context;
import org.roam.ui.indicator.IndicatorController;
import org.roam.util.UiUtil;

/* JADX INFO: loaded from: classes.dex */
public class f extends roam.b.c.b.a.a.d.a.b.a {
    public final IndicatorController b;

    public f(IndicatorController indicatorController) {
        this.b = indicatorController;
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public int a() {
        return this.b.b.size();
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public roam.b.c.b.a.a.d.a.b.c b(Context context) {
        return IndicatorController.c(this.b, context);
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public roam.b.c.b.a.a.d.a.b.d c(Context context, int i) {
        roam.b.c.b.a.a.d.a.e.a aVar = new roam.b.c.b.a.a.d.a.e.a(context);
        aVar.setNormalColor(this.b.c);
        aVar.setSelectedColor(this.b.f);
        aVar.setBackground(UiUtil.getRippleDrawable(context));
        aVar.setText(this.b.b.get(i).getIndTitle());
        aVar.setTextSize(this.b.k);
        IndicatorController.d(this.b, aVar);
        IndicatorController.b(this.b, aVar, i);
        return aVar;
    }
}

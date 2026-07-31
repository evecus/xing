package roam.b.c.a.a.k.w;

import android.content.Context;
import java.util.List;
import org.roam.config.ViewConfig;
import org.roam.ui.indicator.IndicatorController;
import org.roam.util.UiUtil;

/* JADX INFO: loaded from: classes.dex */
public class g extends roam.b.c.b.a.a.d.a.b.a {
    public final IndicatorController b;

    public g(IndicatorController indicatorController) {
        this.b = indicatorController;
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public int a() {
        List<ViewConfig.ViewPagerBean.PagesBean> list = this.b.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public roam.b.c.b.a.a.d.a.b.c b(Context context) {
        roam.b.c.b.a.a.d.a.c.c cVar = new roam.b.c.b.a.a.d.a.c.c(context);
        cVar.setFillColor(this.b.h.get(0).intValue());
        return cVar;
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public roam.b.c.b.a.a.d.a.b.d c(Context context, int i) {
        roam.b.c.b.a.a.d.a.e.c cVar = new roam.b.c.b.a.a.d.a.e.c(context);
        cVar.setText(this.b.b.get(i).getIndTitle());
        cVar.setNormalColor(this.b.c);
        cVar.setSelectedColor(this.b.f);
        cVar.setTextSize(this.b.k);
        IndicatorController.d(this.b, cVar);
        int iDp2px = UiUtil.dp2px(12.0f);
        cVar.setPadding(iDp2px, 0, iDp2px, 0);
        IndicatorController.b(this.b, cVar, i);
        return cVar;
    }
}

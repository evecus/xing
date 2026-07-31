package roam.b.c.a.a.k.w;

import android.content.Context;
import java.util.List;
import org.roam.config.ViewConfig;
import org.roam.ui.indicator.IndicatorController;
import org.roam.util.UiUtil;

/* JADX INFO: loaded from: classes.dex */
public class h extends roam.b.c.b.a.a.d.a.b.a {
    public final IndicatorController b;

    public h(IndicatorController indicatorController) {
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
        roam.b.c.b.a.a.d.a.c.b bVarC = IndicatorController.c(this.b, context);
        bVarC.setMode(0);
        return bVarC;
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public roam.b.c.b.a.a.d.a.b.d c(Context context, int i) {
        roam.b.c.a.a.k.w.j.a aVar = new roam.b.c.a.a.k.w.j.a(context);
        aVar.setText(this.b.b.get(i).getIndTitle());
        aVar.setNormalColor(this.b.c);
        aVar.setSelectedColor(this.b.f);
        aVar.setTextSize(this.b.k);
        aVar.setBackground(UiUtil.getRippleBorderlessDrawable(context));
        IndicatorController.d(this.b, aVar);
        IndicatorController.b(this.b, aVar, i);
        return aVar;
    }
}

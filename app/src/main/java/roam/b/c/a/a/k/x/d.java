package roam.b.c.a.a.k.x;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public class d implements ViewPager.PageTransformer {
    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        float height = view.getHeight();
        float width = view.getWidth();
        float fMin = Math.min(f > 0.0f ? 1.0f : Math.abs(f + 1.0f), 1.0f);
        view.setScaleX(fMin);
        view.setScaleY(fMin);
        view.setPivotX(width * 0.5f);
        view.setPivotY(height * 0.5f);
        view.setTranslationX(f > 0.0f ? width * f : (-width) * f * 0.25f);
    }
}

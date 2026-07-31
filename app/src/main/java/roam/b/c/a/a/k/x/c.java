package roam.b.c.a.a.k.x;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public class c implements ViewPager.PageTransformer {
    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        int width = view.getWidth();
        if (f >= -1.0f) {
            float fAbs = 1.0f;
            if (f <= 0.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
            } else if (f <= 1.0f) {
                view.setAlpha(1.0f - f);
                view.setTranslationX(width * (-f));
                fAbs = ((1.0f - Math.abs(f)) * 0.25f) + 0.75f;
            }
            view.setScaleX(fAbs);
            view.setScaleY(fAbs);
            return;
        }
        view.setAlpha(0.0f);
    }
}

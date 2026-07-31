package org.roam.ui.indicator;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import java.util.List;
import java.util.Objects;
import org.roam.config.ViewConfig;
import org.roam.loader.Loader;
import org.roam.ui.indicator.IndicatorController;
import org.roam.util.UiUtil;
import roam.b.c.b.a.a.d.a.a;
import roam.b.c.b.a.a.d.a.b.c;
import roam.b.c.b.a.a.d.a.b.d;
import roam.b.c.b.a.a.d.a.c.b;

/* JADX INFO: loaded from: classes.dex */
public class IndicatorController {
    public static final int m = UiUtil.dp2px(48.0f);
    public Activity a;
    public List<ViewConfig.ViewPagerBean.PagesBean> b;
    public int c;
    public int d;
    public ViewPager e;
    public int f;
    public a g;
    public List<Integer> h;
    public Loader j;
    public OnClickListener l;
    public int i = 0;
    public int k = -1;

    public interface OnClickListener {
        void onClick(View view, int i);

        boolean onLongClick(View view, int i);
    }

    public IndicatorController(Activity activity, ViewPager viewPager, List<ViewConfig.ViewPagerBean.PagesBean> list, int i) {
        this.e = viewPager;
        this.b = list;
        this.d = i;
        this.a = activity;
    }

    public static void a(IndicatorController indicatorController, View view, boolean z, float f) {
        float f2;
        float f3;
        Objects.requireNonNull(indicatorController);
        if (z) {
            f2 = f * 0.14999998f;
            f3 = 0.85f;
        } else {
            f2 = f * (-0.14999998f);
            f3 = 1.0f;
        }
        float f4 = f2 + f3;
        view.setScaleX(f4);
        view.setScaleY(f4);
    }

    public static void b(final IndicatorController indicatorController, final View view, final int i) {
        Objects.requireNonNull(indicatorController);
        View.OnClickListener onClickListener = new View.OnClickListener(indicatorController, i) { // from class: roam.b.c.a.a.k.w.b
            public final IndicatorController a;
            public final int b;

            {
                this.a = indicatorController;
                this.b = i;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                IndicatorController indicatorController2 = this.a;
                int i2 = this.b;
                IndicatorController.OnClickListener onClickListener2 = indicatorController2.l;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(view2, i2);
                    return;
                }
                ViewPager viewPager = indicatorController2.e;
                if (viewPager != null) {
                    viewPager.setCurrentItem(i2);
                }
            }
        };
        view.setOnLongClickListener(new View.OnLongClickListener(indicatorController, view, i) { // from class: roam.b.c.a.a.k.w.a
            public final IndicatorController a;
            public final View b;
            public final int c;

            {
                this.a = indicatorController;
                this.b = view;
                this.c = i;
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                IndicatorController indicatorController2 = this.a;
                View view3 = this.b;
                int i2 = this.c;
                IndicatorController.OnClickListener onClickListener2 = indicatorController2.l;
                if (onClickListener2 != null) {
                    return onClickListener2.onLongClick(view3, i2);
                }
                return false;
            }
        });
        view.setOnClickListener(onClickListener);
    }

    public static b c(IndicatorController indicatorController, Context context) {
        Objects.requireNonNull(indicatorController);
        b bVar = new b(context);
        bVar.setMode(1);
        bVar.setColors((Integer[]) indicatorController.h.toArray(new Integer[0]));
        return bVar;
    }

    public static void d(IndicatorController indicatorController, TextView textView) {
        int i = indicatorController.k;
        if (i != -1) {
            textView.setTextSize(2, i);
        }
    }

    public void e(List<Integer> list) {
        this.h = list;
        a aVar = this.g;
        if (aVar == null || aVar.getAdapter() == null) {
            return;
        }
        c pagerIndicator = this.g.getPagerIndicator();
        if (pagerIndicator instanceof b) {
            ((b) pagerIndicator).setColors((Integer[]) list.toArray(new Integer[0]));
        } else if (pagerIndicator instanceof roam.b.c.b.a.a.d.a.c.a) {
            ((roam.b.c.b.a.a.d.a.c.a) pagerIndicator).setColors((Integer[]) list.toArray(new Integer[0]));
        } else if (pagerIndicator instanceof roam.b.c.b.a.a.d.a.c.c) {
            ((roam.b.c.b.a.a.d.a.c.c) pagerIndicator).setFillColor(list.get(0).intValue());
        }
    }

    public final void f(int i) {
        for (int i2 = 0; i2 < this.g.getAdapter().a(); i2++) {
            LinearLayout linearLayout = this.g.b;
            d dVar = linearLayout == null ? null : (d) linearLayout.getChildAt(i2);
            if (dVar instanceof roam.b.c.b.a.a.d.a.e.c) {
                ((roam.b.c.b.a.a.d.a.e.c) dVar).setSelectedColor(i);
            }
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.l = onClickListener;
    }
}

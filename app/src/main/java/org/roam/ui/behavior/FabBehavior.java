package org.roam.ui.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import roam.b.c.a.a.k.t.a;
import roam.b.c.a.a.k.t.b;

/* JADX INFO: loaded from: classes.dex */
public class FabBehavior extends FloatingActionButton.Behavior {
    public static final Interpolator c = new FastOutSlowInInterpolator();
    public boolean a = false;
    public boolean b = false;

    public FabBehavior(Context context, AttributeSet attributeSet) {
    }

    public boolean a(int i) {
        return i == 2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompatWithLayer;
        ViewPropertyAnimatorListener bVar;
        FloatingActionButton floatingActionButton = (FloatingActionButton) view;
        super.onNestedScroll(coordinatorLayout, floatingActionButton, view2, i, i2, i3, i4, i5, iArr);
        if (i2 > 0 && !this.a) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompatAnimate = ViewCompat.animate(floatingActionButton);
            int height = floatingActionButton.getHeight();
            viewPropertyAnimatorCompatWithLayer = viewPropertyAnimatorCompatAnimate.translationY((floatingActionButton.getLayoutParams() instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) r2).bottomMargin : 0) + height).setInterpolator(c).withLayer();
            bVar = new a(this);
        } else {
            if (i2 >= 0 || this.b) {
                return;
            }
            viewPropertyAnimatorCompatWithLayer = ViewCompat.animate(floatingActionButton).translationY(0.0f).setInterpolator(c).withLayer();
            bVar = new b(this);
        }
        viewPropertyAnimatorCompatWithLayer.setListener(bVar).start();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        return a(i);
    }
}

package org.roam.ui.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<View> {
    public int a;

    public BottomNavigationViewBehavior() {
        this.a = 0;
    }

    public BottomNavigationViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return view2 instanceof AppBarLayout;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        int i;
        int topAndBottomOffset = ((AppBarLayout.Behavior) ((CoordinatorLayout.LayoutParams) view2.getLayoutParams()).getBehavior()).getTopAndBottomOffset();
        int i2 = -((view.getMeasuredHeight() * topAndBottomOffset) / view2.getMeasuredHeight());
        if (topAndBottomOffset == 0 || (i = this.a) == 0) {
            i = 0;
        }
        ViewCompat.setTranslationY(view, i + i2);
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) view.getLayoutParams())).topMargin = coordinatorLayout.getMeasuredHeight() - view.getMeasuredHeight();
        return super.onLayoutChild(coordinatorLayout, view, i);
    }
}

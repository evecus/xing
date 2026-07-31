package org.roam.ui.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class AppBarLayoutBehavior extends AppBarLayout.Behavior {
    public boolean a;
    public boolean b;

    public AppBarLayoutBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final Field a() {
        try {
            return getClass().getSuperclass().getSuperclass().getDeclaredField("mFlingRunnable");
        } catch (NoSuchFieldException e) {
            return getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("flingRunnable");
        }
    }

    public final Field b() {
        try {
            return getClass().getSuperclass().getSuperclass().getDeclaredField("mScroller");
        } catch (NoSuchFieldException e) {
            return getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("scroller");
        }
    }

    public final void c(AppBarLayout appBarLayout) {
        try {
            Field fieldA = a();
            Field fieldB = b();
            fieldA.setAccessible(true);
            fieldB.setAccessible(true);
            Runnable runnable = (Runnable) fieldA.get(this);
            OverScroller overScroller = (OverScroller) fieldB.get(this);
            if (runnable != null) {
                appBarLayout.removeCallbacks(runnable);
                fieldA.set(this, null);
            }
            if (overScroller == null || overScroller.isFinished()) {
                return;
            }
            overScroller.abortAnimation();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override // com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        AppBarLayout appBarLayout = (AppBarLayout) view;
        this.b = this.a;
        if (motionEvent.getActionMasked() == 0) {
            c(appBarLayout);
        }
        return super.onInterceptTouchEvent(coordinatorLayout, appBarLayout, motionEvent);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.Behavior, com.google.android.material.appbar.AppBarLayout.BaseBehavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 1) {
            this.a = true;
        }
        if (this.b) {
            return;
        }
        super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i, i2, iArr, i3);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5) {
        AppBarLayout appBarLayout = (AppBarLayout) view;
        if (this.b) {
            return;
        }
        super.onNestedScroll(coordinatorLayout, appBarLayout, view2, i, i2, i3, i4, i5);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.Behavior, com.google.android.material.appbar.AppBarLayout.BaseBehavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i, int i2) {
        c(appBarLayout);
        return super.onStartNestedScroll(coordinatorLayout, appBarLayout, view, view2, i, i2);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.Behavior, com.google.android.material.appbar.AppBarLayout.BaseBehavior
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
        super.onStopNestedScroll(coordinatorLayout, appBarLayout, view, i);
        this.a = false;
        this.b = false;
    }
}

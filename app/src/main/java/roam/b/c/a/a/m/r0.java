package roam.b.c.a.a.m;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public class r0 extends WebView {
    public r0(Context context) {
        super(context);
    }

    public r0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ViewParent a(View view) {
        ViewParent parent = view.getParent();
        if (parent == 0) {
            return null;
        }
        if ((parent instanceof ViewPager) || (parent instanceof AbsListView) || (parent instanceof ScrollView) || (parent instanceof HorizontalScrollView) || !(parent instanceof View)) {
            return parent;
        }
        a((View) parent);
        return parent;
    }

    @Override // android.webkit.WebView, android.view.View
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (z) {
            a(this).requestDisallowInterceptTouchEvent(false);
        }
        super.onOverScrolled(i, i2, z, z2);
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent viewParentA;
        if (motionEvent.getAction() == 0 && (viewParentA = a(this)) != null) {
            viewParentA.requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(motionEvent);
    }
}

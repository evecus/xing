package roam.b.c.a.a.m;

import android.content.Context;
import android.view.MotionEvent;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import org.roam.webcore.FusionCoreWebView;

/* JADX INFO: loaded from: classes.dex */
public class i1 extends FusionCoreWebView implements NestedScrollingChild {
    public int g;
    public final int[] h;
    public final int[] i;
    public int j;
    public NestedScrollingChildHelper k;

    /* JADX WARN: Multi-variable type inference failed */
    public i1(Context context) {
        super(context);
        this.h = new int[2];
        this.i = new int[2];
        this.k = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override // androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.k.dispatchNestedFling(f, f2, z);
    }

    @Override // androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.k.dispatchNestedPreFling(f, f2);
    }

    @Override // androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.k.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.k.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.k.hasNestedScrollingParent();
    }

    @Override // androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.k.isNestedScrollingEnabled();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.j = 0;
        }
        int y = (int) motionEvent.getY();
        motionEvent.offsetLocation(0.0f, this.j);
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int i = this.g - y;
                    if (dispatchNestedPreScroll(0, i, this.i, this.h)) {
                        i -= this.i[1];
                        motionEventObtain.offsetLocation(0.0f, this.h[1]);
                        this.j += this.h[1];
                    }
                    this.g = y - this.h[1];
                    int scrollY = getScrollY();
                    int iMax = Math.max(0, scrollY + i) - scrollY;
                    if (dispatchNestedScroll(0, iMax, 0, i - iMax, this.h)) {
                        int i2 = this.g;
                        int i3 = this.h[1];
                        this.g = i2 - i3;
                        motionEventObtain.offsetLocation(0.0f, i3);
                        this.j += this.h[1];
                    }
                    boolean zOnTouchEvent = super/*roam.b.c.a.a.m.r0*/.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                    return zOnTouchEvent;
                }
                if (actionMasked != 3 && actionMasked != 5) {
                    return false;
                }
            }
            stopNestedScroll();
        } else {
            this.g = y;
            startNestedScroll(2);
        }
        return super/*roam.b.c.a.a.m.r0*/.onTouchEvent(motionEvent);
    }

    @Override // androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.k.setNestedScrollingEnabled(z);
    }

    @Override // androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return this.k.startNestedScroll(i);
    }

    @Override // androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.k.stopNestedScroll();
    }
}

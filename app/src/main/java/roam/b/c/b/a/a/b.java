package roam.b.c.b.a.a;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import roam.b.c.b.a.a.d.a.b.d;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public SparseBooleanArray a = new SparseBooleanArray();
    public SparseArray<Float> b = new SparseArray<>();
    public int c;
    public int d;
    public int e;
    public float f;
    public int g;
    public boolean h;
    public a i;

    public interface a {
    }

    public final void a(int i) {
        a aVar = this.i;
        if (aVar != null) {
            int i2 = this.c;
            LinearLayout linearLayout = ((roam.b.c.b.a.a.d.a.a) aVar).b;
            if (linearLayout != null) {
                KeyEvent.Callback childAt = linearLayout.getChildAt(i);
                if (childAt instanceof d) {
                    ((d) childAt).a(i, i2);
                }
            }
        }
        this.a.put(i, true);
    }

    public final void b(int i, float f, boolean z, boolean z2) {
        if (this.h || i == this.d || this.g == 1 || z2) {
            a aVar = this.i;
            if (aVar != null) {
                int i2 = this.c;
                LinearLayout linearLayout = ((roam.b.c.b.a.a.d.a.a) aVar).b;
                if (linearLayout != null) {
                    KeyEvent.Callback childAt = linearLayout.getChildAt(i);
                    if (childAt instanceof d) {
                        ((d) childAt).b(i, i2, f, z);
                    }
                }
            }
            this.b.put(i, Float.valueOf(1.0f - f));
        }
    }

    public final void c(int i, float f, boolean z, boolean z2) {
        if (!this.h && i != this.e && this.g != 1) {
            int i2 = this.d;
            if (((i != i2 - 1 && i != i2 + 1) || this.b.get(i, Float.valueOf(0.0f)).floatValue() == 1.0f) && !z2) {
                return;
            }
        }
        a aVar = this.i;
        if (aVar != null) {
            int i3 = this.c;
            LinearLayout linearLayout = ((roam.b.c.b.a.a.d.a.a) aVar).b;
            if (linearLayout != null) {
                KeyEvent.Callback childAt = linearLayout.getChildAt(i);
                if (childAt instanceof d) {
                    ((d) childAt).d(i, i3, f, z);
                }
            }
        }
        this.b.put(i, Float.valueOf(f));
    }

    public final void d(int i) {
        int width;
        HorizontalScrollView horizontalScrollView;
        a aVar = this.i;
        if (aVar != null) {
            int i2 = this.c;
            roam.b.c.b.a.a.d.a.a aVar2 = (roam.b.c.b.a.a.d.a.a) aVar;
            LinearLayout linearLayout = aVar2.b;
            if (linearLayout != null) {
                KeyEvent.Callback childAt = linearLayout.getChildAt(i);
                if (childAt instanceof d) {
                    ((d) childAt).c(i, i2);
                }
                if (!aVar2.g && !aVar2.k && aVar2.a != null && aVar2.p.size() > 0) {
                    roam.b.c.b.a.a.d.a.d.a aVar3 = aVar2.p.get(Math.min(aVar2.p.size() - 1, i));
                    if (aVar2.h) {
                        float fA = aVar3.a();
                        float width2 = aVar2.a.getWidth();
                        float f = aVar2.i;
                        boolean z = aVar2.j;
                        horizontalScrollView = aVar2.a;
                        width = (int) (fA - (width2 * f));
                        if (z) {
                            horizontalScrollView.smoothScrollTo(width, 0);
                        } else {
                            horizontalScrollView.scrollTo(width, 0);
                        }
                    } else {
                        int scrollX = aVar2.a.getScrollX();
                        int i3 = aVar3.a;
                        if (scrollX > i3) {
                            boolean z2 = aVar2.j;
                            HorizontalScrollView horizontalScrollView2 = aVar2.a;
                            if (z2) {
                                horizontalScrollView2.smoothScrollTo(i3, 0);
                            } else {
                                horizontalScrollView2.scrollTo(i3, 0);
                            }
                        } else {
                            int scrollX2 = aVar2.a.getScrollX();
                            int width3 = aVar2.getWidth();
                            int i4 = aVar3.c;
                            if (width3 + scrollX2 < i4) {
                                if (aVar2.j) {
                                    HorizontalScrollView horizontalScrollView3 = aVar2.a;
                                    width = i4 - aVar2.getWidth();
                                    horizontalScrollView = horizontalScrollView3;
                                    horizontalScrollView.smoothScrollTo(width, 0);
                                } else {
                                    HorizontalScrollView horizontalScrollView4 = aVar2.a;
                                    width = i4 - aVar2.getWidth();
                                    horizontalScrollView = horizontalScrollView4;
                                    horizontalScrollView.scrollTo(width, 0);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.a.put(i, false);
    }

    public void e(int i) {
        this.c = i;
        this.a.clear();
        this.b.clear();
    }
}

package roam.b.c.b.a.a.d.a;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.lucode.hackware.magicindicator.R;
import roam.b.c.b.a.a.b;
import roam.b.c.b.a.a.d.a.b.c;

/* JADX INFO: loaded from: classes.dex */
public class a extends FrameLayout implements roam.b.c.b.a.a.c.a, b.a {
    public HorizontalScrollView a;
    public LinearLayout b;
    public LinearLayout c;
    public c d;
    public roam.b.c.b.a.a.d.a.b.a e;
    public b f;
    public boolean g;
    public boolean h;
    public float i;
    public boolean j;
    public boolean k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;
    public List<roam.b.c.b.a.a.d.a.d.a> p;
    public DataSetObserver q;

    /* JADX INFO: renamed from: roam.b.c.b.a.a.d.a.a$a, reason: collision with other inner class name */
    public class C0025a extends DataSetObserver {
        public final a a;

        public C0025a(a aVar) {
            this.a = aVar;
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = this.a;
            aVar.f.e(aVar.e.a());
            this.a.a();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
        }
    }

    public a(Context context) {
        super(context);
        this.i = 0.5f;
        this.j = true;
        this.k = true;
        this.o = true;
        this.p = new ArrayList();
        this.q = new C0025a(this);
        b bVar = new b();
        this.f = bVar;
        bVar.i = this;
    }

    public final void a() {
        LayoutInflater layoutInflaterFrom;
        int i;
        LinearLayout.LayoutParams layoutParams;
        removeAllViews();
        if (this.g) {
            layoutInflaterFrom = LayoutInflater.from(getContext());
            i = R.layout.pager_navigator_layout_no_scroll;
        } else {
            layoutInflaterFrom = LayoutInflater.from(getContext());
            i = R.layout.pager_navigator_layout;
        }
        View viewInflate = layoutInflaterFrom.inflate(i, this);
        this.a = (HorizontalScrollView) viewInflate.findViewById(R.id.scroll_view);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.title_container);
        this.b = linearLayout;
        linearLayout.setPadding(this.m, 0, this.l, 0);
        LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(R.id.indicator_container);
        this.c = linearLayout2;
        if (this.n) {
            linearLayout2.getParent().bringChildToFront(this.c);
        }
        int i2 = this.f.c;
        for (int i3 = 0; i3 < i2; i3++) {
            Object objC = this.e.c(getContext(), i3);
            if (objC instanceof View) {
                View view = (View) objC;
                if (this.g) {
                    layoutParams = new LinearLayout.LayoutParams(0, -1);
                    roam.b.c.b.a.a.d.a.b.a aVar = this.e;
                    getContext();
                    Objects.requireNonNull(aVar);
                    layoutParams.weight = 1.0f;
                } else {
                    layoutParams = new LinearLayout.LayoutParams(-2, -1);
                }
                this.b.addView(view, layoutParams);
            }
        }
        roam.b.c.b.a.a.d.a.b.a aVar2 = this.e;
        if (aVar2 != null) {
            c cVarB = aVar2.b(getContext());
            this.d = cVarB;
            if (cVarB instanceof View) {
                this.c.addView((View) this.d, new FrameLayout.LayoutParams(-1, -1));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(int r13, float r14, int r15) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.b.c.b.a.a.d.a.a.b(int, float, int):void");
    }

    public void c(int i) {
        if (this.e != null) {
            b bVar = this.f;
            bVar.e = bVar.d;
            bVar.d = i;
            bVar.d(i);
            for (int i2 = 0; i2 < bVar.c; i2++) {
                if (i2 != bVar.d && !bVar.a.get(i2)) {
                    bVar.a(i2);
                }
            }
            c cVar = this.d;
            if (cVar != null) {
                cVar.onPageSelected(i);
            }
        }
    }

    public roam.b.c.b.a.a.d.a.b.a getAdapter() {
        return this.e;
    }

    public int getLeftPadding() {
        return this.m;
    }

    public c getPagerIndicator() {
        return this.d;
    }

    public int getRightPadding() {
        return this.l;
    }

    public float getScrollPivotX() {
        return this.i;
    }

    public LinearLayout getTitleContainer() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.e != null) {
            this.p.clear();
            int i5 = this.f.c;
            for (int i6 = 0; i6 < i5; i6++) {
                roam.b.c.b.a.a.d.a.d.a aVar = new roam.b.c.b.a.a.d.a.d.a();
                View childAt = this.b.getChildAt(i6);
                if (childAt != 0) {
                    aVar.a = childAt.getLeft();
                    aVar.b = childAt.getTop();
                    aVar.c = childAt.getRight();
                    int bottom = childAt.getBottom();
                    aVar.d = bottom;
                    if (childAt instanceof roam.b.c.b.a.a.d.a.b.b) {
                        roam.b.c.b.a.a.d.a.b.b bVar = (roam.b.c.b.a.a.d.a.b.b) childAt;
                        aVar.e = bVar.getContentLeft();
                        aVar.f = bVar.getContentTop();
                        aVar.g = bVar.getContentRight();
                        aVar.h = bVar.getContentBottom();
                    } else {
                        aVar.e = aVar.a;
                        aVar.f = aVar.b;
                        aVar.g = aVar.c;
                        aVar.h = bottom;
                    }
                }
                this.p.add(aVar);
            }
            c cVar = this.d;
            if (cVar != null) {
                cVar.a(this.p);
            }
            if (this.o) {
                b bVar2 = this.f;
                if (bVar2.g == 0) {
                    c(bVar2.d);
                    b(this.f.d, 0.0f, 0);
                }
            }
        }
    }

    public void setAdapter(roam.b.c.b.a.a.d.a.b.a aVar) {
        roam.b.c.b.a.a.d.a.b.a aVar2 = this.e;
        if (aVar2 == aVar) {
            return;
        }
        if (aVar2 != null) {
            aVar2.a.unregisterObserver(this.q);
        }
        this.e = aVar;
        if (aVar == null) {
            this.f.e(0);
            a();
            return;
        }
        aVar.a.registerObserver(this.q);
        this.f.e(this.e.a());
        if (this.b != null) {
            this.e.a.notifyChanged();
        }
    }

    public void setAdjustMode(boolean z) {
        this.g = z;
    }

    public void setEnablePivotScroll(boolean z) {
        this.h = z;
    }

    public void setFollowTouch(boolean z) {
        this.k = z;
    }

    public void setIndicatorOnTop(boolean z) {
        this.n = z;
    }

    public void setLeftPadding(int i) {
        this.m = i;
    }

    public void setReselectWhenLayout(boolean z) {
        this.o = z;
    }

    public void setRightPadding(int i) {
        this.l = i;
    }

    public void setScrollPivotX(float f) {
        this.i = f;
    }

    public void setSkimOver(boolean z) {
        this.f.h = z;
    }

    public void setSmoothScroll(boolean z) {
        this.j = z;
    }
}

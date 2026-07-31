package roam.b.c.a.a.k.w;

import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public final class i implements ViewPager.OnPageChangeListener {
    public final roam.b.c.b.a.a.a a;

    public i(roam.b.c.b.a.a.a aVar) {
        this.a = aVar;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        roam.b.c.b.a.a.c.a aVar = this.a.a;
        if (aVar != null) {
            roam.b.c.b.a.a.d.a.a aVar2 = (roam.b.c.b.a.a.d.a.a) aVar;
            if (aVar2.e != null) {
                aVar2.f.g = i;
                roam.b.c.b.a.a.d.a.b.c cVar = aVar2.d;
                if (cVar != null) {
                    cVar.onPageScrollStateChanged(i);
                }
            }
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        roam.b.c.b.a.a.c.a aVar = this.a.a;
        if (aVar != null) {
            ((roam.b.c.b.a.a.d.a.a) aVar).b(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        roam.b.c.b.a.a.c.a aVar = this.a.a;
        if (aVar != null) {
            ((roam.b.c.b.a.a.d.a.a) aVar).c(i);
        }
    }
}

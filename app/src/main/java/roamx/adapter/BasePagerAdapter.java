package roamx.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BasePagerAdapter extends PagerAdapter {
    public List<View> mViews;

    public BasePagerAdapter(List<View> list) {
        this.mViews = list;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.mViews.get(i));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mViews.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView(this.mViews.get(i));
        return this.mViews.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}

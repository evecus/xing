package roam.b.c.a.a.k.s;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.roam.Application;
import org.roam.config.ViewConfig;
import roam.b.c.a.a.k.v.l;
import roam.b.c.a.a.k.v.m;

/* JADX INFO: loaded from: classes.dex */
public class c extends FragmentStatePagerAdapter {
    public final List<m> a;
    public FragmentManager b;

    public c(Application application, List<String> list, l lVar, ViewPager viewPager, ViewConfig.WebViewBean webViewBean) {
        super(application.getActivity().getSupportFragmentManager());
        this.a = new ArrayList();
        this.b = application.getActivity().getSupportFragmentManager();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            m mVar = new m(application, it.next(), lVar, webViewBean);
            mVar.g = viewPager;
            this.a.add(mVar);
        }
    }

    public void a() {
        try {
            FragmentTransaction fragmentTransactionBeginTransaction = this.b.beginTransaction();
            Iterator<m> it = this.a.iterator();
            while (it.hasNext()) {
                fragmentTransactionBeginTransaction.remove(it.next());
            }
            this.a.clear();
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            notifyDataSetChanged();
        } catch (Exception e) {
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.a.size();
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        return this.a.get(i);
    }
}

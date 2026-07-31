package roamx.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import org.roam.R;
import org.roam.config.ThemeConfig;
import org.roam.config.ViewConfig;
import org.roam.loader.Loader;
import org.roam.ui.ViewShader;
import roam.b.c.a.a.k.s.a;
import roam.b.c.a.a.k.s.b;
import roam.b.c.a.a.k.s.e;

/* JADX INFO: loaded from: classes.dex */
public class DrawerListAdapter extends RecyclerView.Adapter<b> implements e {
    public List<List<ViewConfig.DrawerBean.ListBean>> a;
    public Activity b;
    public Loader c;
    public ThemeConfig.DrawerStyleBean d;
    public ViewShader e;
    public e f;

    public DrawerListAdapter(Activity activity, List<List<ViewConfig.DrawerBean.ListBean>> list, Loader loader, ThemeConfig.DrawerStyleBean drawerStyleBean, ViewShader viewShader) {
        this.b = activity;
        this.a = list;
        this.c = loader;
        this.d = drawerStyleBean;
        this.e = viewShader;
    }

    public b a(ViewGroup viewGroup) {
        return new b(this.b.getLayoutInflater().inflate(R.layout.r, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        List<ViewConfig.DrawerBean.ListBean> list = this.a.get(i);
        RecyclerView recyclerView = ((b) viewHolder).a;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.b));
        a aVar = new a(this.b, list, this.c, this.d, this.e);
        aVar.e = this;
        aVar.g = i;
        recyclerView.setAdapter(aVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup);
    }

    @Override // roam.b.c.a.a.k.s.e
    public void onDrawerItemClick(List<List<ViewConfig.DrawerBean.ListBean>> list, int i, int i2) {
        e eVar = this.f;
        if (eVar != null) {
            eVar.onDrawerItemClick(this.a, i, i2);
        }
    }

    public void setOnDrawerItemClickListener(e eVar) {
        this.f = eVar;
    }
}

package roam.b.c.a.a.k.s;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import org.roam.R;
import org.roam.config.ThemeConfig;
import org.roam.config.ViewConfig;
import org.roam.loader.Loader;
import org.roam.ui.ViewShader;

/* JADX INFO: loaded from: classes.dex */
public class a extends RecyclerView.Adapter<ViewOnClickListenerC0022a> {
    public Activity a;
    public List<ViewConfig.DrawerBean.ListBean> b;
    public Loader c;
    public ThemeConfig.DrawerStyleBean d;
    public e e;
    public ViewShader f;
    public int g;

    /* JADX INFO: renamed from: roam.b.c.a.a.k.s.a$a, reason: collision with other inner class name */
    public class ViewOnClickListenerC0022a extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView a;
        public ImageView b;
        public SwitchCompat c;
        public int d;
        public final a e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewOnClickListenerC0022a(a aVar, View view, int i) {
            super(view);
            this.e = aVar;
            this.d = i;
            this.a = (TextView) view.findViewById(R.id.r_res_0x7f090181);
            this.b = (ImageView) view.findViewById(R.id.r_res_0x7f0900c4);
            if (i == 1) {
                this.c = (SwitchCompat) view.findViewById(R.id.r);
            }
            view.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a aVar = this.e;
            e eVar = aVar.e;
            if (eVar != null) {
                eVar.onDrawerItemClick(null, aVar.g, getAdapterPosition());
            }
        }
    }

    public a(Activity activity, List<ViewConfig.DrawerBean.ListBean> list, Loader loader, ThemeConfig.DrawerStyleBean drawerStyleBean, ViewShader viewShader) {
        this.a = activity;
        this.b = list;
        this.c = loader;
        this.d = drawerStyleBean;
        this.f = viewShader;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.b.get(i).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewOnClickListenerC0022a viewOnClickListenerC0022a = (ViewOnClickListenerC0022a) viewHolder;
        ViewConfig.DrawerBean.ListBean listBean = this.b.get(i);
        this.c.loadImage(viewOnClickListenerC0022a.b, listBean.getIcon());
        viewOnClickListenerC0022a.b.setColorFilter(Color.parseColor(this.d.getItemIconTint()));
        viewOnClickListenerC0022a.a.setText(listBean.getTitle());
        viewOnClickListenerC0022a.a.setTextColor(Color.parseColor(this.d.getItemTextColor()));
        if (viewOnClickListenerC0022a.d == 1) {
            viewOnClickListenerC0022a.c.setChecked(listBean.isChecked());
            this.f.tintSwitch(viewOnClickListenerC0022a.c);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewOnClickListenerC0022a(this, this.a.getLayoutInflater().inflate(i != 0 ? i != 1 ? -1 : R.layout.r : R.layout.r, viewGroup, false), i);
    }
}

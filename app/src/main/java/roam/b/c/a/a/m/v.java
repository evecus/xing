package roam.b.c.a.a.m;

import android.R;
import android.os.Handler;
import android.util.TypedValue;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import roam.b.c.a.a.m.w;

/* JADX INFO: loaded from: classes.dex */
public class v extends RecyclerView.Adapter<w.a> {
    public final String[] a;
    public final Handler.Callback b;
    public final w c;

    public v(w wVar, String[] strArr, Handler.Callback callback) {
        this.c = wVar;
        this.a = strArr;
        this.b = callback;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.length;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        w.a aVar = (w.a) viewHolder;
        TypedValue typedValue = new TypedValue();
        this.c.o.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        aVar.a.setBackgroundResource(typedValue.resourceId);
        aVar.a.setText(this.a[i]);
        aVar.a.setOnClickListener(new u(this, i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new w.a(this.c.q.inflate(R.layout.simple_list_item_1, viewGroup, false));
    }
}

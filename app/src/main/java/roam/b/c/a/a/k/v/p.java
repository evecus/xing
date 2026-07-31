package roam.b.c.a.a.k.v;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import org.roam.R;

/* JADX INFO: loaded from: classes.dex */
public class p extends RecyclerView.Adapter<a> {
    public List<String> a;
    public LayoutInflater b;
    public b c;

    public static class a extends RecyclerView.ViewHolder {
        public TextView a;

        public a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.r_res_0x7f090172);
        }
    }

    public interface b {
    }

    public p(List<String> list, LayoutInflater layoutInflater) {
        this.a = list;
        this.b = layoutInflater;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((a) viewHolder).a.setText(this.a.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View viewInflate = this.b.inflate(R.layout.r, viewGroup, false);
        a aVar = new a(viewInflate);
        viewInflate.setOnClickListener(new o(this, aVar));
        return aVar;
    }
}

package roam.b.c.a.a.k.w;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.roam.R;
import org.roam.ui.indicator.IndicatorController;
import roam.b.c.b.a.a.d.a.e.b;

/* JADX INFO: loaded from: classes.dex */
public class c extends roam.b.c.b.a.a.d.a.b.a {
    public final boolean b;
    public final IndicatorController c;

    public class a implements b.InterfaceC0026b {
        public final ImageView a;
        public final c b;

        public a(c cVar, ImageView imageView) {
            this.b = cVar;
            this.a = imageView;
        }

        @Override // roam.b.c.b.a.a.d.a.e.b.InterfaceC0026b
        public void a(int i, int i2) {
            this.a.setColorFilter(this.b.c.c);
        }

        @Override // roam.b.c.b.a.a.d.a.e.b.InterfaceC0026b
        public void b(int i, int i2, float f, boolean z) {
            IndicatorController.a(this.b.c, this.a, true, f);
        }

        @Override // roam.b.c.b.a.a.d.a.e.b.InterfaceC0026b
        public void c(int i, int i2) {
            this.a.setColorFilter(this.b.c.f);
        }

        @Override // roam.b.c.b.a.a.d.a.e.b.InterfaceC0026b
        public void d(int i, int i2, float f, boolean z) {
            IndicatorController.a(this.b.c, this.a, false, f);
        }
    }

    public c(IndicatorController indicatorController, boolean z) {
        this.c = indicatorController;
        this.b = z;
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public int a() {
        return this.c.b.size();
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public roam.b.c.b.a.a.d.a.b.c b(Context context) {
        if (this.b) {
            return IndicatorController.c(this.c, context);
        }
        return null;
    }

    @Override // roam.b.c.b.a.a.d.a.b.a
    public roam.b.c.b.a.a.d.a.b.d c(Context context, int i) {
        roam.b.c.b.a.a.d.a.e.b bVar = new roam.b.c.b.a.a.d.a.e.b(context);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.r, (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.r_res_0x7f0900c4);
        IndicatorController indicatorController = this.c;
        indicatorController.j.loadImage(imageView, indicatorController.b.get(i).getIndIcon());
        bVar.setContentView(viewInflate);
        bVar.setOnPagerTitleChangeListener(new a(this, imageView));
        IndicatorController.b(this.c, bVar, i);
        return bVar;
    }
}

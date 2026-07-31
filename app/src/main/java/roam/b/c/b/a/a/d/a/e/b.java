package roam.b.c.b.a.a.d.a.e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class b extends FrameLayout implements roam.b.c.b.a.a.d.a.b.b {
    public InterfaceC0026b a;
    public a b;

    public interface a {
        int getContentBottom();

        int getContentLeft();

        int getContentRight();

        int getContentTop();
    }

    /* JADX INFO: renamed from: roam.b.c.b.a.a.d.a.e.b$b, reason: collision with other inner class name */
    public interface InterfaceC0026b {
        void a(int i, int i2);

        void b(int i, int i2, float f, boolean z);

        void c(int i, int i2);

        void d(int i, int i2, float f, boolean z);
    }

    public b(Context context) {
        super(context);
    }

    @Override // roam.b.c.b.a.a.d.a.b.d
    public void a(int i, int i2) {
        InterfaceC0026b interfaceC0026b = this.a;
        if (interfaceC0026b != null) {
            interfaceC0026b.a(i, i2);
        }
    }

    @Override // roam.b.c.b.a.a.d.a.b.d
    public void b(int i, int i2, float f, boolean z) {
        InterfaceC0026b interfaceC0026b = this.a;
        if (interfaceC0026b != null) {
            interfaceC0026b.b(i, i2, f, z);
        }
    }

    @Override // roam.b.c.b.a.a.d.a.b.d
    public void c(int i, int i2) {
        InterfaceC0026b interfaceC0026b = this.a;
        if (interfaceC0026b != null) {
            interfaceC0026b.c(i, i2);
        }
    }

    @Override // roam.b.c.b.a.a.d.a.b.d
    public void d(int i, int i2, float f, boolean z) {
        InterfaceC0026b interfaceC0026b = this.a;
        if (interfaceC0026b != null) {
            interfaceC0026b.d(i, i2, f, z);
        }
    }

    @Override // roam.b.c.b.a.a.d.a.b.b
    public int getContentBottom() {
        a aVar = this.b;
        return aVar != null ? aVar.getContentBottom() : getBottom();
    }

    @Override // roam.b.c.b.a.a.d.a.b.b
    public int getContentLeft() {
        a aVar = this.b;
        return aVar != null ? aVar.getContentLeft() : getLeft();
    }

    public a getContentPositionDataProvider() {
        return this.b;
    }

    @Override // roam.b.c.b.a.a.d.a.b.b
    public int getContentRight() {
        a aVar = this.b;
        return aVar != null ? aVar.getContentRight() : getRight();
    }

    @Override // roam.b.c.b.a.a.d.a.b.b
    public int getContentTop() {
        a aVar = this.b;
        return aVar != null ? aVar.getContentTop() : getTop();
    }

    public InterfaceC0026b getOnPagerTitleChangeListener() {
        return this.a;
    }

    public void setContentPositionDataProvider(a aVar) {
        this.b = aVar;
    }

    public void setContentView(int i) {
        View viewInflate = LayoutInflater.from(getContext()).inflate(i, (ViewGroup) null);
        removeAllViews();
        if (viewInflate != null) {
            addView(viewInflate, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    public void setContentView(View view) {
        removeAllViews();
        if (view != null) {
            addView(view, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    public void setOnPagerTitleChangeListener(InterfaceC0026b interfaceC0026b) {
        this.a = interfaceC0026b;
    }
}

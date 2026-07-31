package roam.b.c.b.a.a.d.a.e;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class c extends TextView implements roam.b.c.b.a.a.d.a.b.b {
    public int a;
    public int b;

    public c(Context context) {
        super(context, null);
        setGravity(17);
        int iB = roam.a.a.a.b.a.B(context, 10.0d);
        setPadding(iB, 0, iB, 0);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    public void a(int i, int i2) {
        setTextColor(this.b);
    }

    public void b(int i, int i2, float f, boolean z) {
    }

    public void c(int i, int i2) {
        setTextColor(this.a);
    }

    public void d(int i, int i2, float f, boolean z) {
    }

    @Override // roam.b.c.b.a.a.d.a.b.b
    public int getContentBottom() {
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) (((fontMetrics.bottom - fontMetrics.top) / 2.0f) + (getHeight() / 2));
    }

    @Override // roam.b.c.b.a.a.d.a.b.b
    public int getContentLeft() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return (getLeft() + (getWidth() / 2)) - (rect.width() / 2);
    }

    @Override // roam.b.c.b.a.a.d.a.b.b
    public int getContentRight() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return (rect.width() / 2) + getLeft() + (getWidth() / 2);
    }

    @Override // roam.b.c.b.a.a.d.a.b.b
    public int getContentTop() {
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) ((getHeight() / 2) - ((fontMetrics.bottom - fontMetrics.top) / 2.0f));
    }

    public int getNormalColor() {
        return this.b;
    }

    public int getSelectedColor() {
        return this.a;
    }

    public void setNormalColor(int i) {
        this.b = i;
    }

    public void setSelectedColor(int i) {
        this.a = i;
    }
}

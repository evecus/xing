package roam.b.c.a.a.k;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class r {
    public List<View> a;
    public TypeEvaluator b;
    public Integer c = 200;
    public Activity d;
    public a e;

    public interface a {
    }

    public r(Activity activity, View... viewArr) {
        this.a = viewArr.length == 0 ? new ArrayList<>() : Arrays.asList(viewArr);
        this.d = activity;
        this.b = new ArgbEvaluator();
    }

    public static Integer a(View view) {
        if (view == null) {
            return null;
        }
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            return Integer.valueOf(((ColorDrawable) background).getColor());
        }
        view.setDrawingCacheEnabled(true);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getDrawingCache());
        if (bitmapCreateBitmap == null) {
            return null;
        }
        int pixel = bitmapCreateBitmap.getPixel(5, 5);
        view.setDrawingCacheEnabled(false);
        return Integer.valueOf(pixel);
    }

    public final void b(int i, ViewGroup viewGroup) {
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof ViewGroup) {
                b(i, (ViewGroup) childAt);
            } else if (childAt instanceof TextView) {
                ((TextView) childAt).setTextColor(i);
            } else if (childAt instanceof ImageView) {
                ((ImageView) childAt).setColorFilter(i);
            } else if (childAt instanceof roam.b.c.b.a.a.d.a.c.b) {
                ((roam.b.c.b.a.a.d.a.c.b) childAt).setColors(Integer.valueOf(i));
            }
        }
    }
}

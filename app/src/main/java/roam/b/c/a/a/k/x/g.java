package roam.b.c.a.a.k.x;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public class g implements ViewPager.PageTransformer {
    public static final Matrix a = new Matrix();
    public static final Camera b = new Camera();
    public static final float[] c = new float[2];

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        float fAbs = Math.abs(f) * (f < 0.0f ? 30.0f : -30.0f);
        int width = view.getWidth();
        int height = view.getHeight();
        Matrix matrix = a;
        matrix.reset();
        Camera camera = b;
        camera.save();
        camera.rotateY(Math.abs(fAbs));
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate((-width) * 0.5f, (-height) * 0.5f);
        float f2 = width;
        float f3 = height;
        matrix.postTranslate(f2 * 0.5f, f3 * 0.5f);
        float[] fArr = c;
        fArr[0] = f2;
        fArr[1] = f3;
        matrix.mapPoints(fArr);
        view.setTranslationX((fAbs > 0.0f ? 1.0f : -1.0f) * (f2 - fArr[0]));
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(0.0f);
        view.setRotationY(fAbs);
    }
}

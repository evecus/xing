package roam.b.c.a.a.k;

import android.animation.Animator;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.roam.ui.FusionUiCoreManger;
import org.roam.ui.indicator.IndicatorController;
import org.roam.util.UiUtil;
import roam.b.c.a.a.k.r;

/* JADX INFO: loaded from: classes.dex */
public class q implements Animator.AnimatorListener {
    public final int a;
    public final r b;

    public q(r rVar, int i) {
        this.b = rVar;
        this.a = i;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        int i;
        FusionUiCoreManger fusionUiCoreManger;
        IndicatorController indicatorController;
        int i2 = this.a;
        if ((Color.green(i2) / 2) + (Color.red(i2) / 2) + (Color.blue(i2) / 2) > 300) {
            UiUtil.setLightStatusBar(true, this.b.d.getWindow().getDecorView());
            i = -12303292;
        } else {
            UiUtil.setLightStatusBar(false, this.b.d.getWindow().getDecorView());
            i = -1;
        }
        r rVar = this.b;
        for (View view : rVar.a) {
            if (view instanceof ViewGroup) {
                rVar.b(i, (ViewGroup) view);
            }
        }
        this.b.d.getWindow().setStatusBarColor(this.a);
        r.a aVar = this.b.e;
        if (aVar == null || (indicatorController = (fusionUiCoreManger = ((h) aVar).a).r) == null) {
            return;
        }
        indicatorController.c = i;
        roam.b.c.b.a.a.d.a.a aVar2 = indicatorController.g;
        if (aVar2 != null && aVar2.getAdapter() != null) {
            indicatorController.f(i);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        fusionUiCoreManger.r.e(arrayList);
        IndicatorController indicatorController2 = fusionUiCoreManger.r;
        indicatorController2.f = i;
        roam.b.c.b.a.a.d.a.a aVar3 = indicatorController2.g;
        if (aVar3 == null || aVar3.getAdapter() == null) {
            return;
        }
        indicatorController2.f(i);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }
}

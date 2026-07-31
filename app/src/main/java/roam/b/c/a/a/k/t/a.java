package roam.b.c.a.a.k.t;

import android.view.View;
import androidx.core.view.ViewPropertyAnimatorListener;
import org.roam.ui.behavior.FabBehavior;

/* JADX INFO: loaded from: classes.dex */
public class a implements ViewPropertyAnimatorListener {
    public final FabBehavior a;

    public a(FabBehavior fabBehavior) {
        this.a = fabBehavior;
    }

    @Override // androidx.core.view.ViewPropertyAnimatorListener
    public void onAnimationCancel(View view) {
        this.a.a = false;
    }

    @Override // androidx.core.view.ViewPropertyAnimatorListener
    public void onAnimationEnd(View view) {
        this.a.a = false;
    }

    @Override // androidx.core.view.ViewPropertyAnimatorListener
    public void onAnimationStart(View view) {
        this.a.a = true;
    }
}

package roam.b.c.a.a.k.t;

import android.view.View;
import androidx.core.view.ViewPropertyAnimatorListener;
import org.roam.ui.behavior.FabBehavior;

/* JADX INFO: loaded from: classes.dex */
public class b implements ViewPropertyAnimatorListener {
    public final FabBehavior a;

    public b(FabBehavior fabBehavior) {
        this.a = fabBehavior;
    }

    @Override // androidx.core.view.ViewPropertyAnimatorListener
    public void onAnimationCancel(View view) {
        this.a.b = false;
    }

    @Override // androidx.core.view.ViewPropertyAnimatorListener
    public void onAnimationEnd(View view) {
        this.a.b = false;
    }

    @Override // androidx.core.view.ViewPropertyAnimatorListener
    public void onAnimationStart(View view) {
        this.a.b = true;
    }
}

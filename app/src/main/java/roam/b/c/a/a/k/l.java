package roam.b.c.a.a.k;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;

/* JADX INFO: loaded from: classes.dex */
public class l {
    public View a;
    public boolean b;

    public class a implements Animator.AnimatorListener {
        public final l a;

        public a(l lVar) {
            this.a = lVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            l lVar = this.a;
            lVar.b = false;
            if (lVar.a.getAlpha() == 0.0f) {
                this.a.a.setVisibility(8);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            l lVar = this.a;
            lVar.b = true;
            if (lVar.a.getAlpha() == 0.0f) {
                this.a.a.setVisibility(0);
            }
        }
    }

    public l(View view) {
        this.a = view;
    }

    public void a(int i, int i2) {
        if (this.b) {
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.a, Key.ALPHA, i, i2);
        objectAnimatorOfFloat.setDuration(800L);
        objectAnimatorOfFloat.addListener(new a(this));
        objectAnimatorOfFloat.start();
    }
}

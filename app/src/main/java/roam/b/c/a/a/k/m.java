package roam.b.c.a.a.k;

import android.animation.Animator;
import android.widget.EditText;
import org.roam.util.UiUtil;

/* JADX INFO: loaded from: classes.dex */
public final class m implements Animator.AnimatorListener {
    public final EditText a;

    public m(EditText editText) {
        this.a = editText;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.a.setFocusable(true);
        this.a.setFocusableInTouchMode(true);
        this.a.requestFocus();
        UiUtil.showKeyboard(this.a.getContext());
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }
}

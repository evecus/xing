package roam.b.c.a.a.k;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.androlua.LuaApplication;
import org.roam.ui.FusionToolbar;

/* JADX INFO: loaded from: classes.dex */
public class n implements View.OnClickListener {
    public final ImageView a;
    public final ViewGroup b;
    public final EditText c;

    public n(FusionToolbar fusionToolbar, ImageView imageView, ViewGroup viewGroup, EditText editText) {
        this.a = imageView;
        this.b = viewGroup;
        this.c = editText;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int left = ((View) this.a.getParent()).getLeft();
        int left2 = this.a.getLeft();
        ViewGroup viewGroup = this.b;
        m mVar = new m(this.c);
        int width = view.getWidth() / 2;
        int top = (view.getTop() + view.getBottom()) / 2;
        int screenWidth = LuaApplication.getInstance().getScreenWidth();
        int height = viewGroup.getHeight();
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(viewGroup, left + left2 + width, top, 0, ((int) Math.sqrt((screenWidth * screenWidth) + (height * height))) + 1);
        animatorCreateCircularReveal.addListener(mVar);
        viewGroup.setVisibility(0);
        animatorCreateCircularReveal.setDuration(500L);
        animatorCreateCircularReveal.start();
    }
}

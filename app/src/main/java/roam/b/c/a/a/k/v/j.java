package roam.b.c.a.a.k.v;

import android.view.MotionEvent;
import android.view.View;
import java.util.Objects;
import org.roam.ui.fragment.WebViewMenuSupport;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j implements View.OnTouchListener {
    public final WebViewMenuSupport a;

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        WebViewMenuSupport webViewMenuSupport = this.a;
        Objects.requireNonNull(webViewMenuSupport);
        if (motionEvent.getAction() != 0) {
            return false;
        }
        webViewMenuSupport.b = motionEvent.getX();
        webViewMenuSupport.c = motionEvent.getY();
        return false;
    }
}

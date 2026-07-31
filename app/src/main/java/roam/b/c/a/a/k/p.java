package roam.b.c.a.a.k;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public interface p extends TextView.OnEditorActionListener {
    @Override // android.widget.TextView.OnEditorActionListener
    boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent);

    void onHomeButtonClick(View view);

    void onMenuItemClick(String str);
}

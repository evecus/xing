package roam.b.c.a.a.k;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import org.roam.ui.FusionToolbar;

/* JADX INFO: loaded from: classes.dex */
public class o implements TextWatcher {
    public final ImageView a;
    public final l b;

    public o(FusionToolbar fusionToolbar, ImageView imageView, l lVar) {
        this.a = imageView;
        this.b = lVar;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() == 0 && this.a.getAlpha() == 1.0f) {
            this.b.a(1, 0);
        } else {
            if (this.a.getAlpha() != 0.0f || charSequence.length() == 0) {
                return;
            }
            this.b.a(0, 1);
        }
    }
}

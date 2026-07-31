package roam.b.c.a.a.m;

import android.os.Message;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/* JADX INFO: loaded from: classes.dex */
public class u implements View.OnClickListener {
    public final int a;
    public final v b;

    public u(v vVar, int i) {
        this.b = vVar;
        this.a = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        BottomSheetDialog bottomSheetDialog = this.b.c.n;
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            this.b.c.n.dismiss();
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = this.a;
        this.b.b.handleMessage(messageObtain);
    }
}

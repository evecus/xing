package roam.b.c.a.a.k.v;

import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.URLUtil;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import org.roam.ui.fragment.WebViewMenuSupport;

/* JADX INFO: loaded from: classes.dex */
public class r extends SimpleTarget<Bitmap> {
    public final WebViewMenuSupport.a a;

    public r(WebViewMenuSupport.a aVar) {
        this.a = aVar;
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onResourceReady(Object obj, Transition transition) {
        Bitmap bitmap = (Bitmap) obj;
        String strGuessFileName = URLUtil.guessFileName(this.a.a.o, null, null);
        if (strGuessFileName == null) {
            strGuessFileName = System.currentTimeMillis() + ".jpg";
        }
        k kVar = this.a.a.q;
        Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.TITLE", strGuessFileName);
        kVar.startActivityForResult(intent, 4);
        this.a.a.q.a = new i(this, bitmap);
    }
}

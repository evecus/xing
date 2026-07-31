package roam.b.c.a.a.k.u;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.request.target.ImageViewTarget;

/* JADX INFO: loaded from: classes.dex */
public class l extends ImageViewTarget<Bitmap> {
    public final m a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(m mVar, ImageView imageView) {
        super(imageView);
        this.a = mVar;
    }

    @Override // com.bumptech.glide.request.target.ImageViewTarget
    public void setResource(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        m mVar = this.a;
        mVar.d = bitmap2;
        mVar.a.setImageBitmap(bitmap2);
    }
}

package roam.b.c.a.a.k.v;

import android.content.Intent;
import android.graphics.Bitmap;
import androidx.fragment.app.Fragment;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class k extends Fragment {
    public a a;

    public interface a {
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        a aVar = this.a;
        if (aVar != null) {
            i iVar = (i) aVar;
            r rVar = iVar.a;
            Bitmap bitmap = iVar.b;
            Objects.requireNonNull(rVar);
            if (i2 == -1 && i == 4 && intent != null) {
                roam.b.c.a.a.k.u.n.a(bitmap, intent.getData(), rVar.a.a.f, null);
            }
            this.a = null;
        }
    }
}

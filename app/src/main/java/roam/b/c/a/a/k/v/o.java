package roam.b.c.a.a.k.v;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.webkit.internal.AssetHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.roam.R;
import org.roam.ui.fragment.WebViewMenuSupport;
import org.roam.util.AppUtil;
import org.roam.util.UiUtil;
import roam.b.c.a.a.k.v.p;

/* JADX INFO: loaded from: classes.dex */
public class o implements View.OnClickListener {
    public final p.a a;
    public final p b;

    public o(p pVar, p.a aVar) {
        this.b = pVar;
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WebViewMenuSupport webViewMenuSupport;
        SimpleTarget rVar;
        if (this.b.c == null) {
            return;
        }
        int adapterPosition = this.a.getAdapterPosition();
        p pVar = this.b;
        WebViewMenuSupport.a aVar = pVar.c;
        aVar.a.p.dismiss();
        String str = pVar.a.get(adapterPosition);
        WebViewMenuSupport.Interface r0 = aVar.a.g;
        if (r0 == null || !r0.onMenuItemClick(str)) {
            if (str.equals(aVar.a.i)) {
                WebViewMenuSupport webViewMenuSupport2 = aVar.a;
                AppUtil.copyText(webViewMenuSupport2.f, webViewMenuSupport2.o);
                UiUtil.notify(aVar.a.f, R.string.r);
                return;
            }
            if (str.equals(aVar.a.h)) {
                WebViewMenuSupport webViewMenuSupport3 = aVar.a;
                String url = webViewMenuSupport3.a.getUrl();
                AlertDialog alertDialogCreate = new MaterialAlertDialogBuilder(webViewMenuSupport3.f).setTitle(R.string.r).setMessage((CharSequence) webViewMenuSupport3.e.getString(R.string.r, webViewMenuSupport3.o, webViewMenuSupport3.a.getTitle(), url)).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).create();
                alertDialogCreate.show();
                ((TextView) alertDialogCreate.findViewById(android.R.id.message)).setTextIsSelectable(true);
                return;
            }
            if (str.equals(aVar.a.j)) {
                WebViewMenuSupport webViewMenuSupport4 = aVar.a;
                webViewMenuSupport4.a.loadUrl(webViewMenuSupport4.o);
                return;
            }
            if (str.equals(aVar.a.k)) {
                ShareCompat.IntentBuilder.from(aVar.a.f).setText(aVar.a.o).setType(AssetHelper.DEFAULT_MIME_TYPE).startChooser();
                return;
            }
            if (str.equals(aVar.a.l)) {
                roam.b.c.a.a.k.u.n nVar = new roam.b.c.a.a.k.u.n(aVar.a.f);
                nVar.a = aVar.a.o;
                nVar.show(nVar.b.getSupportFragmentManager(), (String) null);
                return;
            }
            if (str.equals(aVar.a.n)) {
                webViewMenuSupport = aVar.a;
                rVar = new q(aVar);
            } else {
                if (!str.equals(aVar.a.m)) {
                    return;
                }
                webViewMenuSupport = aVar.a;
                rVar = new r(aVar);
            }
            Glide.with((FragmentActivity) webViewMenuSupport.f).asBitmap().load(webViewMenuSupport.o).into(rVar);
        }
    }
}

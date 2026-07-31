package roam.a.a.f.a;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {
    public final SslErrorHandler a;
    public final d b;

    public e(d dVar, SslErrorHandler sslErrorHandler) {
        this.b = dVar;
        this.a = sslErrorHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Activity activity = this.b.a;
        f fVar = new f(this);
        g gVar = new g(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (!TextUtils.isEmpty("退出")) {
            builder.setPositiveButton("退出", gVar);
        }
        if (!TextUtils.isEmpty("继续")) {
            builder.setNegativeButton("继续", fVar);
        }
        builder.setTitle("安全警告");
        builder.setMessage("安全连接证书校验无效，将无法保证访问数据的安全性，可能存在风险，请选择是否继续？");
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setOnKeyListener(new roam.a.a.f.k.e());
        try {
            alertDialogCreate.show();
        } catch (Throwable th) {
        }
    }
}

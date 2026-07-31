package roam.b.c.a.a.m;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import org.roam.R;

/* JADX INFO: loaded from: classes.dex */
public class k0 extends e {
    public AlertDialog e;
    public AlertDialog f;
    public Activity j;
    public t1 k;
    public ProgressDialog l;
    public JsPromptResult g = null;
    public JsResult h = null;
    public AlertDialog i = null;
    public Resources m = null;

    public static void p(k0 k0Var, JsResult jsResult) {
        Objects.requireNonNull(k0Var);
        if (jsResult != null) {
            jsResult.cancel();
        }
    }

    @Override // roam.b.c.a.a.m.e
    public void a(t1 t1Var, Activity activity) {
        this.j = activity;
        this.k = t1Var;
        this.m = activity.getResources();
    }

    @Override // roam.b.c.a.a.m.e
    public void c() {
        Activity activity = this.j;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        ProgressDialog progressDialog = this.l;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.l.dismiss();
        }
        this.l = null;
    }

    @Override // roam.b.c.a.a.m.e
    public void d(String str, Handler.Callback callback) {
        Activity activity = this.j;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        new MaterialAlertDialogBuilder(activity).setTitle((CharSequence) this.m.getString(R.string.r)).setMessage((CharSequence) this.m.getString(R.string.r)).setNegativeButton((CharSequence) this.m.getString(R.string.r), (DialogInterface.OnClickListener) new c0(this, callback)).setPositiveButton((CharSequence) this.m.getString(R.string.r), (DialogInterface.OnClickListener) new b0(this)).create().show();
    }

    @Override // roam.b.c.a.a.m.e
    public void e(WebView webView, String str, String str2) {
        new MaterialAlertDialogBuilder(webView.getContext()).setTitle(R.string.r).setMessage((CharSequence) str2).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    @Override // roam.b.c.a.a.m.e
    public void f(WebView webView, String str, String str2, JsResult jsResult) {
        this.j.hashCode();
        String str3 = i.a;
        Activity activity = this.j;
        if (activity == null || activity.isFinishing()) {
            if (jsResult == null) {
                return;
            }
        } else {
            if (!activity.isDestroyed()) {
                if (this.f == null) {
                    this.f = new MaterialAlertDialogBuilder(activity).setMessage((CharSequence) str2).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) new h0(this)).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) new g0(this)).setOnCancelListener((DialogInterface.OnCancelListener) new f0(this)).create();
                }
                this.f.setMessage(str2);
                this.h = jsResult;
                this.f.show();
                return;
            }
            if (jsResult == null) {
                return;
            }
        }
        jsResult.cancel();
    }

    @Override // roam.b.c.a.a.m.e
    public void g(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        Activity activity = this.j;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            jsPromptResult.cancel();
            return;
        }
        if (this.i == null) {
            EditText editText = new EditText(activity);
            editText.setText(str3);
            this.i = new MaterialAlertDialogBuilder(activity).setView((View) editText).setTitle((CharSequence) str2).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) new a0(this)).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) new j0(this, editText)).setOnCancelListener((DialogInterface.OnCancelListener) new i0(this)).create();
        }
        this.g = jsPromptResult;
        this.i.show();
    }

    @Override // roam.b.c.a.a.m.e
    public void h(String str) {
        Activity activity = this.j;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        if (this.l == null) {
            this.l = new ProgressDialog(activity);
        }
        this.l.setCancelable(false);
        this.l.setCanceledOnTouchOutside(false);
        this.l.setMessage(str);
        this.l.show();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // roam.b.c.a.a.m.e
    public void i(WebView webView, int i, String str, String str2) {
        View viewFindViewById;
        View viewFindViewById2;
        StringBuilder sbO = roam.a.b.a.a.a.o("mWebParentLayout onMainFrameError:");
        sbO.append(this.k);
        sbO.toString();
        String str3 = i.a;
        t1 t1Var = this.k;
        if (t1Var != null) {
            FrameLayout frameLayout = t1Var.f;
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            } else {
                FrameLayout frameLayout2 = new FrameLayout(t1Var.getContext());
                frameLayout2.setBackgroundColor(-1);
                frameLayout2.setId(R.id.r);
                View view = t1Var.d;
                if (view == null) {
                    LayoutInflater.from(t1Var.getContext()).inflate(t1Var.b, (ViewGroup) frameLayout2, true);
                } else {
                    frameLayout2.addView(view);
                }
                ViewStub viewStub = (ViewStub) t1Var.findViewById(R.id.r);
                int iIndexOfChild = t1Var.indexOfChild(viewStub);
                t1Var.removeViewInLayout(viewStub);
                ViewGroup.LayoutParams layoutParams = t1Var.getLayoutParams();
                t1Var.f = frameLayout2;
                if (layoutParams != null) {
                    t1Var.addView(frameLayout2, iIndexOfChild, layoutParams);
                } else {
                    t1Var.addView(frameLayout2, iIndexOfChild);
                }
                frameLayout2.setVisibility(0);
                int i2 = t1Var.c;
                if (i2 == -1 || (viewFindViewById = frameLayout2.findViewById(i2)) == null) {
                    frameLayout2.setOnClickListener(new s1(t1Var, frameLayout2));
                } else {
                    viewFindViewById.setOnClickListener(new r1(t1Var, viewFindViewById));
                }
                frameLayout = t1Var.f;
            }
            int i3 = t1Var.c;
            if (i3 == -1 || (viewFindViewById2 = frameLayout.findViewById(i3)) == null) {
                frameLayout.setClickable(true);
            } else {
                viewFindViewById2.setClickable(true);
            }
        }
    }

    @Override // roam.b.c.a.a.m.e
    public void j(WebView webView, String str, final Handler.Callback callback) {
        Activity activity = this.j;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) activity.findViewById(R.id.r);
        String str2 = q.a;
        try {
            str = new URL(str).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Snackbar action = Snackbar.make(coordinatorLayout, activity.getString(R.string.r, new Object[]{str}), -1).setAction(R.string.r, new View.OnClickListener(callback) { // from class: roam.b.c.a.a.m.b
            public final Handler.Callback a;

            {
                this.a = callback;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Handler.Callback callback2 = this.a;
                if (callback2 != null) {
                    callback2.handleMessage(Message.obtain((Handler) null, 1));
                }
            }
        });
        q.q(activity, action);
        action.show();
    }

    @Override // roam.b.c.a.a.m.e
    public void k(String[] strArr, String str, String str2) {
    }

    @Override // roam.b.c.a.a.m.e
    public void l(WebView webView, String str, String[] strArr, Handler.Callback callback) {
        Activity activity = this.j;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        AlertDialog alertDialogCreate = new MaterialAlertDialogBuilder(activity).setSingleChoiceItems((CharSequence[]) strArr, -1, (DialogInterface.OnClickListener) new e0(this, callback)).setOnCancelListener((DialogInterface.OnCancelListener) new d0(this, callback)).create();
        this.e = alertDialogCreate;
        alertDialogCreate.show();
    }

    @Override // roam.b.c.a.a.m.e
    public void m() {
        View viewFindViewById;
        t1 t1Var = this.k;
        if (t1Var == null || (viewFindViewById = t1Var.findViewById(R.id.r)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    @Override // roam.b.c.a.a.m.e
    public void n(String str, String str2) {
        if (TextUtils.isEmpty(str2) || !str2.contains("performDownload")) {
            Context applicationContext = this.j.getApplicationContext();
            Toast toast = q.d;
            if (toast == null) {
                q.d = Toast.makeText(applicationContext.getApplicationContext(), str, 0);
            } else {
                toast.setText(str);
            }
            q.d.show();
        }
    }
}

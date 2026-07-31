package roam.b.c.a.a.k.u;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.URLUtil;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.androlua.LuaUtil;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;
import org.roam.R;
import org.roam.util.ThreadSupport;
import org.roam.util.UiUtil;
import roam.b.a.a.a.b.a;

/* JADX INFO: loaded from: classes.dex */
public class m extends AlertDialog.Builder {
    public ImageViewTouch a;
    public Activity b;
    public ContentLoadingProgressBar c;
    public Bitmap d;
    public String e;
    public File f;
    public Fragment g;

    public m(Fragment fragment, int i) {
        super(fragment.requireActivity(), i);
        FragmentActivity fragmentActivityRequireActivity = fragment.requireActivity();
        this.b = fragmentActivityRequireActivity;
        this.g = fragment;
        View viewInflate = fragmentActivityRequireActivity.getLayoutInflater().inflate(R.layout.r, (ViewGroup) null, false);
        setView(viewInflate);
        ImageViewTouch imageViewTouchFindViewById = viewInflate.findViewById(R.id.r_res_0x7f0900c9);
        this.a = imageViewTouchFindViewById;
        imageViewTouchFindViewById.setDisplayType(a.c.FIT_TO_SCREEN);
        viewInflate.findViewById(R.id.r_res_0x7f0900cd).setOnClickListener(new View.OnClickListener(this) { // from class: roam.b.c.a.a.k.u.k
            public final m a;

            {
                this.a = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                m mVar = this.a;
                Objects.requireNonNull(mVar);
                ThreadSupport threadSupport = new ThreadSupport();
                mVar.c.show();
                threadSupport.setRunnable(new ThreadSupport.a(mVar) { // from class: roam.b.c.a.a.k.u.b
                    public final m a;

                    {
                        this.a = mVar;
                    }

                    public final void a(ThreadSupport threadSupport2, Object[] objArr) {
                        final m mVar2 = this.a;
                        File fileA = mVar2.a();
                        final String fileSize = Formatter.formatFileSize(mVar2.b, fileA.length());
                        final String fileMD5 = LuaUtil.getFileMD5(fileA);
                        threadSupport2.call(new Runnable(mVar2, fileSize, fileMD5) { // from class: roam.b.c.a.a.k.u.c
                            public final m a;
                            public final String b;
                            public final String c;

                            {
                                this.a = mVar2;
                                this.b = fileSize;
                                this.c = fileMD5;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                m mVar3 = this.a;
                                String str = this.b;
                                String str2 = this.c;
                                new MaterialAlertDialogBuilder(mVar3.b).setTitle(R.string.r).setMessage((CharSequence) mVar3.b.getString(R.string.r, new Object[]{mVar3.e, str, String.format("%dx%d", Integer.valueOf(mVar3.d.getWidth()), Integer.valueOf(mVar3.d.getHeight())), str2})).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).show();
                                mVar3.c.hide();
                            }
                        });
                    }
                });
                threadSupport.start(new Object[0]);
            }
        });
        viewInflate.findViewById(R.id.r).setOnClickListener(new View.OnClickListener(this) { // from class: roam.b.c.a.a.k.u.i
            public final m a;

            {
                this.a = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                m mVar = this.a;
                String strGuessFileName = URLUtil.guessFileName(mVar.e, null, null);
                if (strGuessFileName == null) {
                    strGuessFileName = System.currentTimeMillis() + ".jpg";
                }
                Fragment fragment2 = mVar.g;
                Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("image/*");
                intent.putExtra("android.intent.extra.TITLE", strGuessFileName);
                fragment2.startActivityForResult(intent, 5);
            }
        });
        viewInflate.findViewById(R.id.r).setOnClickListener(new View.OnClickListener(this) { // from class: roam.b.c.a.a.k.u.j
            public final m a;

            {
                this.a = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                m mVar = this.a;
                Objects.requireNonNull(mVar);
                ThreadSupport threadSupport = new ThreadSupport();
                mVar.c.show();
                threadSupport.setRunnable(new ThreadSupport.a(mVar) { // from class: roam.b.c.a.a.k.u.a
                    public final m a;

                    {
                        this.a = mVar;
                    }

                    public final void a(ThreadSupport threadSupport2, Object[] objArr) {
                        final m mVar2 = this.a;
                        final File fileA = mVar2.a();
                        threadSupport2.call(new Runnable(mVar2, fileA) { // from class: roam.b.c.a.a.k.u.d
                            public final m a;
                            public final File b;

                            {
                                this.a = mVar2;
                                this.b = fileA;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                m mVar3 = this.a;
                                mVar3.g.startActivity(roam.a.a.a.b.a.x(mVar3.b, this.b));
                                mVar3.c.hide();
                            }
                        });
                    }
                });
                threadSupport.start(new Object[0]);
            }
        });
        this.c = (ContentLoadingProgressBar) viewInflate.findViewById(R.id.r);
    }

    public final File a() {
        File fileA;
        if (this.f == null) {
            if (this.d == null) {
                fileA = null;
            } else {
                fileA = roam.b.c.a.a.l.b.a(this.b);
                Bitmap bitmap = this.d;
                if (bitmap != null) {
                    try {
                        roam.a.a.a.b.a.I(bitmap, new FileOutputStream(fileA));
                    } catch (Exception e) {
                    }
                }
            }
            this.f = fileA;
        }
        return this.f;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public AlertDialog show() {
        AlertDialog alertDialogShow = super.show();
        Window window = alertDialogShow.getWindow();
        UiUtil.setTranslucentStatus(window);
        window.setWindowAnimations(android.R.style.Animation.Dialog);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setAttributes(attributes);
        return alertDialogShow;
    }
}

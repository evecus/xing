package roam.b.c.a.a.k.u;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.bumptech.glide.Glide;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Objects;
import org.roam.R;
import org.roam.util.ThreadSupport;

/* JADX INFO: loaded from: classes.dex */
public class n extends AppCompatDialogFragment {
    public String a;
    public AppCompatActivity b;
    public m c;

    public class a implements Runnable {
        public final n a;

        public a(n nVar) {
            this.a = nVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.c.c.hide();
        }
    }

    public n(AppCompatActivity appCompatActivity) {
        this.b = appCompatActivity;
    }

    public static void a(final Bitmap bitmap, final Uri uri, final Activity activity, final Runnable runnable) {
        final ThreadSupport threadSupport = new ThreadSupport();
        threadSupport.setRunnable(new ThreadSupport.a(activity, uri, threadSupport, bitmap, runnable) { // from class: roam.b.c.a.a.k.u.g
            public final Activity a;
            public final Uri b;
            public final ThreadSupport c;
            public final Bitmap d;
            public final Runnable e;

            {
                this.a = activity;
                this.b = uri;
                this.c = threadSupport;
                this.d = bitmap;
                this.e = runnable;
            }

            public final void a(ThreadSupport threadSupport2, Object[] objArr) {
                final Activity activity2 = this.a;
                Uri uri2 = this.b;
                ThreadSupport threadSupport3 = this.c;
                Bitmap bitmap2 = this.d;
                Runnable runnable2 = this.e;
                try {
                    ContentResolver contentResolver = activity2.getContentResolver();
                    Objects.requireNonNull(uri2);
                    OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uri2);
                    if (outputStreamOpenOutputStream == null) {
                        threadSupport3.call(new Runnable(activity2) { // from class: roam.b.c.a.a.k.u.f
                            public final Activity a;

                            {
                                this.a = activity2;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                Toast.makeText(this.a, "OutputStream is Null", 0).show();
                            }
                        });
                        DocumentsContract.deleteDocument(activity2.getContentResolver(), uri2);
                    }
                    roam.a.a.a.b.a.I(bitmap2, outputStreamOpenOutputStream);
                    threadSupport3.call(new Runnable(activity2) { // from class: roam.b.c.a.a.k.u.e
                        public final Activity a;

                        {
                            this.a = activity2;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            Toast.makeText(this.a, R.string.r, 0).show();
                        }
                    });
                } catch (Exception e) {
                    threadSupport3.call(new Runnable(activity2, e) { // from class: roam.b.c.a.a.k.u.h
                        public final Activity a;
                        public final Exception b;

                        {
                            this.a = activity2;
                            this.b = e;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            Toast.makeText(this.a, String.format("Save File : %s", this.b.toString()), 0).show();
                        }
                    });
                    e.printStackTrace();
                    try {
                        DocumentsContract.deleteDocument(activity2.getContentResolver(), uri2);
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                }
                if (runnable2 != null) {
                    threadSupport2.call(runnable2);
                }
            }
        });
        threadSupport.start(new Object[0]);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        m mVar;
        Bitmap bitmap;
        super.onActivityResult(i, i2, intent);
        if (i2 != -1 || i != 5 || intent == null || (bitmap = (mVar = this.c).d) == null) {
            return;
        }
        mVar.c.show();
        a(bitmap, intent.getData(), this.b, new a(this));
    }

    @Override // androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        m mVar = new m(this, R.style.r_res_0x7f1001d4);
        this.c = mVar;
        String str = this.a;
        Objects.requireNonNull(mVar);
        if (str != null) {
            mVar.e = str;
            Glide.with(mVar.b).asBitmap().load(str).into(new l(mVar, mVar.a));
        }
        return this.c.create();
    }
}

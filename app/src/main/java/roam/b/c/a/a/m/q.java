package roam.b.c.a.a.m;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;
import com.baidu.mobstat.Config;
import com.google.android.material.snackbar.Snackbar;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.roam.Application;
import org.roam.R;
import org.roam.ui.UiManager;
import roam.b.c.a.a.m.b2.d;

/* JADX INFO: loaded from: classes.dex */
public class q {
    public static final String a = "q";
    public static Handler b;
    public static WeakReference<Snackbar> c;
    public static Toast d;

    public static int a(File file, int i) {
        StringBuilder sbO = roam.a.b.a.a.a.o("dir:");
        sbO.append(file.getAbsolutePath());
        Log.i("Info", sbO.toString());
        int iA = 0;
        if (file.isDirectory()) {
            try {
                for (File file2 : file.listFiles()) {
                    if (file2.isDirectory()) {
                        iA += a(file2, i);
                    }
                    if (file2.lastModified() < new Date().getTime() - (((long) i) * 86400000)) {
                        Log.i(a, "file name:" + file2.getName());
                        if (file2.delete()) {
                            iA++;
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("Info", String.format("Failed to clean the cache, result %s", e.getMessage()));
            }
        }
        return iA;
    }

    public static void b(Context context, WebView webView) {
        try {
            String str = i.a;
            CookieManager.getInstance().removeAllCookies(new h());
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new g());
            webView.getSettings().setCacheMode(2);
            webView.clearCache(true);
            webView.clearHistory();
            webView.clearFormData();
            a(new File(i.a(context)), 0);
        } catch (Exception e) {
            String str2 = i.a;
        }
    }

    public static void c(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static File d(Context context, String str, boolean z) throws IOException {
        String strE = e(context);
        if (TextUtils.isEmpty(strE)) {
            return null;
        }
        File file = new File(strE, str);
        if (!file.exists()) {
            file.createNewFile();
        } else if (z) {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    public static String e(Context context) {
        if (!TextUtils.isEmpty(i.b)) {
            return i.b;
        }
        File externalCacheDir = context.getExternalCacheDir();
        File file = new File("mounted".equals(EnvironmentCompat.getStorageState(externalCacheDir)) ? externalCacheDir.getAbsolutePath() : null, "web-cache");
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Throwable th) {
            String str = i.a;
        }
        file.getAbsolutePath();
        file.getPath();
        String str2 = i.a;
        String absolutePath = file.getAbsolutePath();
        i.b = absolutePath;
        return absolutePath;
    }

    public static e f(WebView webView) {
        if (!(webView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("please check webcreator's create method was be called ?");
        }
        ViewParent parent = webView.getParent();
        while (true) {
            for (ViewGroup viewGroup = (ViewGroup) parent; viewGroup != null; viewGroup = null) {
                String str = "ViewGroup:" + viewGroup;
                String str2 = i.a;
                if (viewGroup.getId() == R.id.r) {
                    return ((t1) viewGroup).a;
                }
                parent = viewGroup.getParent();
                if (!(parent instanceof ViewGroup)) {
                }
            }
            throw new IllegalStateException("please check webcreator's create method was be called ?");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String g(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_data"
            r1 = 0
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L30
            r8 = 1
            java.lang.String[] r4 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> L30
            r8 = 0
            r4[r8] = r0     // Catch: java.lang.Throwable -> L30
            r7 = 0
            r3 = r9
            r5 = r10
            r6 = r11
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L30
            if (r8 == 0) goto L2c
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L29
            if (r9 == 0) goto L2c
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L29
            java.lang.String r1 = r8.getString(r9)     // Catch: java.lang.Throwable -> L29
        L25:
            r8.close()
            goto L2f
        L29:
            r9 = move-exception
            r1 = r8
            goto L32
        L2c:
            if (r8 == 0) goto L2f
            goto L25
        L2f:
            return r1
        L30:
            r8 = move-exception
            r9 = r8
        L32:
            if (r1 == 0) goto L37
            r1.close()
        L37:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.b.c.a.a.m.q.g(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static List<String> h(Activity activity, String[] strArr) {
        if (strArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            if (!l(activity, strArr[i])) {
                arrayList.add(strArr[i]);
            }
        }
        return arrayList;
    }

    public static String i(Activity activity, Uri uri) {
        Uri uri2 = null;
        if (uri == null) {
            return null;
        }
        uri.getAuthority();
        uri.getHost();
        uri.getPath();
        uri.getScheme();
        uri.getQuery();
        String str = i.a;
        if (!DocumentsContract.isDocumentUri(activity, uri)) {
            if (uri.getAuthority().equalsIgnoreCase(activity.getPackageName() + ".FileProvider")) {
                String path = uri.getPath();
                return e(activity) + File.separator + path.substring(path.lastIndexOf("/") + 1, path.length());
            }
            if (!"content".equalsIgnoreCase(uri.getScheme())) {
                if ("file".equalsIgnoreCase(uri.getScheme())) {
                    return uri.getPath();
                }
                return null;
            }
            if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
                return uri.getLastPathSegment();
            }
        } else {
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(Config.TRACE_TODAY_VISIT_SPLIT);
                if (!"primary".equalsIgnoreCase(strArrSplit[0])) {
                    return null;
                }
                return Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
            }
            if (!"com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                if (!"com.android.providers.media.documents".equals(uri.getAuthority())) {
                    return null;
                }
                String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(Config.TRACE_TODAY_VISIT_SPLIT);
                String str2 = strArrSplit2[0];
                if ("image".equals(str2)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str2)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str2)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return g(activity, uri2, "_id=?", new String[]{strArrSplit2[1]});
            }
            uri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue());
        }
        return g(activity, uri, null, null);
    }

    public static Uri j(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".FileProvider", file);
    }

    public static boolean k(Context context, List<String> list) {
        for (String str : list) {
            if (ContextCompat.checkSelfPermission(context, str) == -1) {
                return false;
            }
            String strPermissionToOp = AppOpsManagerCompat.permissionToOp(str);
            if (!TextUtils.isEmpty(strPermissionToOp) && AppOpsManagerCompat.noteProxyOp(context, strPermissionToOp, context.getPackageName()) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean l(Context context, String... strArr) {
        return k(context, Arrays.asList(strArr));
    }

    public static boolean m() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void n(Runnable runnable) {
        if (b == null) {
            b = new Handler(Looper.getMainLooper());
        }
        b.post(runnable);
    }

    public static void o(View view, CharSequence charSequence, int i, int i2, int i3, CharSequence charSequence2, int i4, View.OnClickListener onClickListener) {
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new ForegroundColorSpan(i2), 0, spannableString.length(), 33);
        WeakReference<Snackbar> weakReference = new WeakReference<>(Snackbar.make(view, spannableString, i));
        c = weakReference;
        Snackbar snackbar = weakReference.get();
        snackbar.getView().setBackgroundColor(i3);
        snackbar.show();
    }

    public static boolean p(Activity activity, WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams, k1 k1Var, ValueCallback valueCallback2, String str, Handler.Callback callback) {
        try {
            d.c cVar = new d.c();
            cVar.a = activity;
            cVar.g = webView;
            if (valueCallback != null) {
                cVar.c = valueCallback;
                cVar.d = true;
                cVar.b = null;
                cVar.f = false;
            }
            if (fileChooserParams != null) {
                cVar.e = fileChooserParams;
            }
            if (!TextUtils.isEmpty(str)) {
                cVar.i = str;
            }
            if (callback != null) {
                cVar.j = callback;
                cVar.f = true;
                cVar.b = null;
                cVar.c = null;
            }
            if (k1Var != null) {
                cVar.h = k1Var;
            }
            new roam.b.c.a.a.m.b2.d(cVar).e();
        } catch (Throwable th) {
            Log.d("fa2", String.valueOf(th));
            String str2 = i.a;
            boolean z = th instanceof ClassNotFoundException;
            if (valueCallback != null) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Snackbar q(Context context, Snackbar snackbar) {
        if (context instanceof Application) {
            r((Application) context, snackbar);
        }
        return snackbar;
    }

    public static Snackbar r(Application application, Snackbar snackbar) {
        UiManager uiManager = application.getUiManager();
        if (uiManager != null) {
            try {
                int color = Color.parseColor(uiManager.getColors().getColorAccent());
                if (color != -1) {
                    snackbar.setActionTextColor(color);
                }
            } catch (Exception e) {
            }
        }
        return snackbar;
    }

    public static String[] s(Activity activity, Uri[] uriArr) {
        if (activity != null && uriArr != null && uriArr.length != 0) {
            try {
                String[] strArr = new String[uriArr.length];
                int length = uriArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    strArr[i2] = i(activity, uriArr[i]);
                    i++;
                    i2++;
                }
                return strArr;
            } catch (Throwable th) {
                String str = i.a;
            }
        }
        return null;
    }
}

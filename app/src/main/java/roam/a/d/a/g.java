package roam.a.d.a;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.download.library.NotificationCancelReceiver;
import com.download.library.R;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public static final String k;
    public static long l;
    public static final Handler m;
    public int a;
    public NotificationManager b;
    public Notification c;
    public NotificationCompat.Builder d;
    public Context e;
    public String f;
    public volatile boolean g;
    public NotificationCompat.Action h;
    public h i;
    public String j;

    public class a implements Runnable {
        public final g a;

        public a(g gVar) {
            this.a = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.k();
        }
    }

    public class b implements Runnable {
        public final g a;

        public b(g gVar) {
            this.a = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.k();
        }
    }

    static {
        StringBuilder sbO = roam.a.b.a.a.a.o("Download-");
        sbO.append(g.class.getSimpleName());
        k = sbO.toString();
        l = SystemClock.elapsedRealtime();
        m = new Handler(Looper.getMainLooper());
    }

    public g(Context context, int i) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager;
        SystemClock.uptimeMillis();
        this.f = "";
        this.g = false;
        this.j = "";
        this.a = i;
        r rVar = r.h;
        String str = k;
        StringBuilder sbO = roam.a.b.a.a.a.o(" DownloadNotifier:");
        sbO.append(this.a);
        String string = sbO.toString();
        Objects.requireNonNull(rVar);
        Log.i(str, string);
        this.e = context;
        this.b = (NotificationManager) context.getSystemService("notification");
        try {
            Context context2 = this.e;
            String packageName = context2.getPackageName();
            Objects.requireNonNull(rVar);
            String strConcat = packageName.concat("4.1.3");
            this.f = strConcat;
            this.d = new NotificationCompat.Builder(context2, strConcat);
            String str2 = this.f;
            Objects.requireNonNull(rVar);
            try {
                packageManager = context.getApplicationContext().getPackageManager();
                try {
                    applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    applicationInfo = null;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                applicationInfo = null;
                packageManager = null;
            }
            NotificationChannel notificationChannel = new NotificationChannel(str2, (String) packageManager.getApplicationLabel(applicationInfo), 2);
            ((NotificationManager) this.e.getSystemService("notification")).createNotificationChannel(notificationChannel);
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationChannel.setSound(null, null);
        } catch (Throwable th) {
            Objects.requireNonNull(r.h);
            th.printStackTrace();
        }
    }

    public static void b(h hVar) {
        ((NotificationManager) hVar.v.getSystemService("notification")).cancel(hVar.t);
        e eVar = hVar.x;
        if (eVar != null) {
            eVar.b(new c(1030, k.s.get(1030)), Uri.fromFile(hVar.w), hVar.g, hVar);
        }
    }

    public final PendingIntent a(Context context, int i, String str) {
        Intent intent = new Intent(context, (Class<?>) NotificationCancelReceiver.class);
        intent.setAction("com.download.cancelled");
        intent.putExtra("TAG", str);
        int i2 = i * 1000;
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i2, intent, 134217728);
        r rVar = r.h;
        Objects.requireNonNull(rVar);
        Log.i(k, "buildCancelContent id:" + i2);
        return broadcast;
    }

    public final long c() {
        synchronized (g.class) {
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                long j = l;
                if (jElapsedRealtime >= j + 500) {
                    l = jElapsedRealtime;
                    return 0L;
                }
                long j2 = 500 - (jElapsedRealtime - j);
                l = j + j2;
                return j2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String d(h hVar) {
        File file = hVar.w;
        String string = (file == null || TextUtils.isEmpty(file.getName())) ? this.e.getString(R.string.download_file_download) : hVar.w.getName();
        if (string.length() <= 20) {
            return string;
        }
        StringBuilder sbO = roam.a.b.a.a.a.o("...");
        sbO.append(string.substring(string.length() - 20, string.length()));
        return sbO.toString();
    }

    public final boolean e() {
        return this.d.getNotification().deleteIntent != null;
    }

    public void f() {
        j();
        Intent intentC = r.h.c(this.e, this.i);
        l(null);
        if (intentC != null) {
            if (!(this.e instanceof Activity)) {
                intentC.addFlags(268435456);
            }
            PendingIntent activity = PendingIntent.getActivity(this.e, this.a * 10000, intentC, 134217728);
            this.d.setSmallIcon(this.i.d);
            this.d.setContentText(this.e.getString(R.string.download_click_open));
            this.d.setProgress(100, 100, false);
            this.d.setContentIntent(activity);
            m.postDelayed(new b(this), c());
        }
    }

    public void g() {
        r rVar = r.h;
        String str = k;
        StringBuilder sbO = roam.a.b.a.a.a.o(" onDownloadPaused:");
        sbO.append(this.i.g);
        String string = sbO.toString();
        Objects.requireNonNull(rVar);
        Log.i(str, string);
        if (!e()) {
            l(a(this.e, this.a, this.i.g));
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "";
        }
        this.d.setContentText(this.j.concat("(").concat(this.e.getString(R.string.download_paused)).concat(")"));
        this.d.setSmallIcon(this.i.d);
        j();
        this.g = false;
        m.postDelayed(new a(this), c());
    }

    public void h(long j) {
        if (!e()) {
            l(a(this.e, this.a, this.i.g));
        }
        if (!this.g) {
            this.g = true;
            NotificationCompat.Action action = new NotificationCompat.Action(this.i.c, this.e.getString(android.R.string.cancel), a(this.e, this.a, this.i.g));
            this.h = action;
            this.d.addAction(action);
        }
        NotificationCompat.Builder builder = this.d;
        String string = this.e.getString(R.string.download_current_downloaded_length, j < 0 ? "shouldn't be less than zero!" : j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID ? String.format(Locale.getDefault(), "%.1fB", Double.valueOf(j)) : j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED ? String.format(Locale.getDefault(), "%.1fKB", Double.valueOf(j / 1024.0d)) : j < 1073741824 ? String.format(Locale.getDefault(), "%.1fMB", Double.valueOf(j / 1048576.0d)) : String.format(Locale.getDefault(), "%.1fGB", Double.valueOf(j / 1.073741824E9d)));
        this.j = string;
        builder.setContentText(string);
        this.d.setProgress(100, 20, true);
        k();
        k();
    }

    public void i(int i) {
        if (!e()) {
            l(a(this.e, this.a, this.i.g));
        }
        if (!this.g) {
            this.g = true;
            NotificationCompat.Action action = new NotificationCompat.Action(android.R.color.transparent, this.e.getString(android.R.string.cancel), a(this.e, this.a, this.i.g));
            this.h = action;
            this.d.addAction(action);
        }
        NotificationCompat.Builder builder = this.d;
        String string = this.e.getString(R.string.download_current_downloading_progress, i + "%");
        this.j = string;
        builder.setContentText(string);
        this.d.setProgress(100, i, false);
        k();
        k();
    }

    public final void j() {
        int iIndexOf;
        try {
            Field declaredField = this.d.getClass().getDeclaredField("mActions");
            ArrayList arrayList = declaredField != null ? (ArrayList) declaredField.get(this.d) : null;
            if (arrayList == null || (iIndexOf = arrayList.indexOf(this.h)) == -1) {
                return;
            }
            arrayList.remove(iIndexOf);
        } catch (Throwable th) {
            Objects.requireNonNull(r.h);
            th.printStackTrace();
        }
    }

    public final void k() {
        Notification notificationBuild = this.d.build();
        this.c = notificationBuild;
        this.b.notify(this.a, notificationBuild);
    }

    public final void l(PendingIntent pendingIntent) {
        this.d.getNotification().deleteIntent = pendingIntent;
    }
}

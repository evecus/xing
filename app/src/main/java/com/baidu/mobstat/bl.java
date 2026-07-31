package com.baidu.mobstat;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import com.baidu.mobstat.MtjConfig;

/* JADX INFO: loaded from: classes.dex */
public class bl {
    public static void a(Activity activity, boolean z) {
        if (!z || bm.a().b() || bn.a().b()) {
            return;
        }
        bn.a().a(activity);
    }

    public static void a(KeyEvent keyEvent) {
        if (bm.a().b() || bn.a().b()) {
            return;
        }
        bn.a().a(keyEvent);
    }

    public static void a(View view, Activity activity) {
        if (bm.a().b() || bn.a().b()) {
            return;
        }
        bn.a().a(view, activity);
    }

    public static void a(MtjConfig.FeedTrackStrategy feedTrackStrategy) {
        bn.a().a(feedTrackStrategy);
    }

    public static void a(String str) {
        if (bn.a().b()) {
            return;
        }
        bn.a().a(str);
    }

    public static void b(Activity activity, boolean z) {
        if (!z || bm.a().b() || bn.a().b()) {
            return;
        }
        bn.a().b(activity);
    }

    public static void c(Activity activity, boolean z) {
        if (!z || bm.a().b() || bn.a().b()) {
            return;
        }
        bn.a().c(activity);
    }
}

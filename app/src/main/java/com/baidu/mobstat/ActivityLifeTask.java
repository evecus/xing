package com.baidu.mobstat;

import android.content.Context;
import com.baidu.mobstat.ActivityLifeObserver;
import com.baidu.mobstat.AutoTrack;
import com.baidu.mobstat.ay;
import com.baidu.mobstat.bp;

/* JADX INFO: loaded from: classes.dex */
public class ActivityLifeTask {
    private static boolean a = false;
    private static ActivityLifeObserver.IActivityLifeCallback b;
    private static ActivityLifeObserver.IActivityLifeCallback c;
    private static ActivityLifeObserver.IActivityLifeCallback d;
    private static ActivityLifeObserver.IActivityLifeCallback e;

    private static synchronized void a(Context context) {
        b = new AutoTrack.MyActivityLifeCallback(1);
        d = new ay.a();
        c = new bp.a();
        e = new AutoTrack.MyActivityLifeCallback(2);
    }

    public static synchronized void registerActivityLifeCallback(Context context) {
        if (a) {
            return;
        }
        a(context);
        ActivityLifeObserver.instance().clearObservers();
        ActivityLifeObserver.instance().addObserver(b);
        ActivityLifeObserver.instance().addObserver(d);
        ActivityLifeObserver.instance().addObserver(c);
        ActivityLifeObserver.instance().addObserver(e);
        ActivityLifeObserver.instance().registerActivityLifeCallback(context);
        a = true;
    }
}

package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.baidu.mobstat.ActivityLifeObserver;

/* JADX INFO: loaded from: classes.dex */
public class AutoTrack {
    public static final int JOB_TYPE_BGSEND = 2;
    public static final int JOB_TYPE_SESSIONTIME = 1;

    public static class MyActivityLifeCallback implements ActivityLifeObserver.IActivityLifeCallback {
        private int a;

        public MyActivityLifeCallback(int i) {
            this.a = i;
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityPaused(Activity activity) {
            Context applicationContext = activity.getApplicationContext();
            int i = this.a;
            if (i == 1) {
                if (CooperService.instance().isCloseTrace()) {
                    return;
                }
                BDStatCore.instance().autoTrackSessionEndTime(applicationContext);
            } else if (i == 2) {
                try {
                    if (CooperService.instance().isCloseTrace()) {
                        ActivityLifeObserver.instance().clearObservers();
                        ActivityLifeObserver.instance().unRegister(activity);
                    } else {
                        BDStatCore.instance().doSendLogCheck(applicationContext);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityResumed(Activity activity) {
            Context applicationContext = activity.getApplicationContext();
            int i = this.a;
            if (i == 1) {
                BDStatCore.instance().autoTrackSessionStartTime(applicationContext);
            } else if (i == 2) {
                BDStatCore.instance().cancelSendLogCheck();
            }
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityStarted(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityStopped(Activity activity) {
        }
    }
}

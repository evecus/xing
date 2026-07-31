package com.roamexplore;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.androlua.LuaApplication;
import org.roam.loader.Loader;
import roam.b.c.a.a.i;

/* JADX INFO: loaded from: classes.dex */
public class SplashActivity extends AppCompatActivity {
    public static final int b = 0;
    public Thread a;

    public final void a() {
        Intent intent = MainActivity.getIntent(this, LuaApplication.getInstance().getFusionDir(), Loader.LAUNCH_PAGE);
        String dataString = getIntent().getDataString();
        Bundle bundle = new Bundle();
        bundle.putString("SchemeData", dataString);
        startActivity(intent.putExtras(bundle));
        finish();
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Intent intent;
        super.onCreate(bundle);
        getWindow();
        if (!isTaskRoot() && (intent = getIntent()) != null) {
            String action = intent.getAction();
            if (intent.hasCategory("android.intent.category.LAUNCHER") && "android.intent.action.MAIN".equals(action)) {
                finish();
                return;
            }
        }
        long j = 0;
        long j2 = LuaApplication.getSharedPreferences(this).getLong("__app_last_update_time", 0L);
        try {
            j = getPackageManager().getPackageInfo(getPackageName(), 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (j2 >= j) {
            a();
            return;
        }
        LuaApplication.getSharedPreferences(this).edit().putLong("__app_last_update_time", j).apply();
        i iVar = new i(this);
        this.a = iVar;
        iVar.start();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Thread thread = this.a;
        if (thread != null) {
            thread.interrupt();
            this.a = null;
        }
        super.onDestroy();
    }
}

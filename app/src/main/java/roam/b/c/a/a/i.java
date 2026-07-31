package roam.b.c.a.a;

import android.widget.Toast;
import com.androlua.LuaApplication;
import com.roamexplore.SplashActivity;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class i extends Thread {
    public final SplashActivity a;

    public i(SplashActivity splashActivity) {
        this.a = splashActivity;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        try {
            roam.a.a.a.b.a.J("assets", LuaApplication.getInstance().getFusionDir());
            roam.a.a.a.b.a.J("lua", LuaApplication.getInstance().getMdDir());
            this.a.runOnUiThread(new Runnable(this) { // from class: roam.b.c.a.a.e
                public final i a;

                {
                    this.a = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    SplashActivity splashActivity = this.a.a;
                    int i = SplashActivity.b;
                    splashActivity.a();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            this.a.runOnUiThread(new Runnable(this, e) { // from class: roam.b.c.a.a.f
                public final i a;
                public final IOException b;

                {
                    this.a = this;
                    this.b = e;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    Toast.makeText(this.a.a, this.b.toString(), 0).show();
                }
            });
        }
    }
}

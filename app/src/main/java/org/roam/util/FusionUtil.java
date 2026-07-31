package org.roam.util;

import android.content.Context;
import android.util.Log;
import com.androlua.LuaApplication;
import java.io.File;
import org.roam.config.AppConfig;
import org.roam.config.ThemeConfig;
import org.roam.loader.Loader;
import roam.a.a.a.b.a;
import roam.a.e.a.i;

/* JADX INFO: loaded from: classes.dex */
public class FusionUtil {
    public static void changeTheme(String str, String str2) {
        try {
            String str3 = str + File.separatorChar + Loader.CONFIG_APP;
            AppConfig appConfig = (AppConfig) new i().b(FileUtil.read(new File(str3)), AppConfig.class);
            appConfig.setTheme(str2);
            FileUtil.write(new File(str3), new i().f(appConfig));
        } catch (Exception e) {
            Log.v("fa2", e.toString());
        }
    }

    public static ThemeConfig getThemeConfigFormAssets(Context context) {
        AppConfig appConfig;
        String strH = a.H(context, Loader.CONFIG_APP);
        if (strH == null || (appConfig = (AppConfig) new i().b(strH, AppConfig.class)) == null) {
            return null;
        }
        String theme = appConfig.getTheme();
        StringBuilder sbO = roam.a.b.a.a.a.o(Loader.THEME_DIR);
        sbO.append(File.separatorChar);
        sbO.append(theme);
        return (ThemeConfig) new i().b(a.H(context, sbO.toString()), ThemeConfig.class);
    }

    public static void setNightMode(boolean z) {
        LuaApplication.getInstance().setSharedData("nightmode", Integer.valueOf(z ? 2 : 1));
    }
}

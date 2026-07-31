package com.roamexplore;

import androidx.appcompat.app.AppCompatDelegate;
import com.androlua.LuaApplication;
import org.roam.R;
import org.roam.config.ThemeConfig;
import org.roam.util.FusionUtil;

/* JADX INFO: loaded from: classes.dex */
public class Container extends LuaApplication {
    public void a(int i) {
        int i2;
        int i3 = 1;
        if (i == 1) {
            i2 = R.style.AppTheme;
        } else {
            i3 = 2;
            if (i != 2) {
                return;
            } else {
                i2 = R.style.r;
            }
        }
        setTheme(i2);
        AppCompatDelegate.setDefaultNightMode(i3);
    }

    @Override // com.androlua.LuaApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        int iIntValue = LuaApplication.getSharedPreferences(this).getInt("nightmode", -1);
        if (iIntValue == -1) {
            ThemeConfig themeConfigFormAssets = FusionUtil.getThemeConfigFormAssets(this);
            if (themeConfigFormAssets == null) {
                return;
            }
            setSharedData("nightmode", themeConfigFormAssets.isNightMode() ? 2 : 1);
            iIntValue = ((Integer) getSharedData("nightmode", 1)).intValue();
        }
        a(iIntValue);
    }
}

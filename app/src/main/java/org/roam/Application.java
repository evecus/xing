package org.roam;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.androlua.LuaContext;
import java.util.List;
import org.roam.config.ViewConfig;
import org.roam.loader.Loader;
import org.roam.ui.UiManager;
import roam.b.c.a.a.k.p;
import roam.b.c.a.a.k.s.e;

/* JADX INFO: loaded from: classes.dex */
public interface Application extends p, e, View.OnClickListener {
    AppCompatActivity getActivity();

    Loader getLoader();

    LuaContext getLuaSupport();

    UiManager getUiManager();

    ViewConfig getViewConfig();

    boolean isDevMode();

    /* synthetic */ void onDrawerItemClick(List<List<ViewConfig.DrawerBean.ListBean>> list, int i, int i2);

    @Override // roam.b.c.a.a.k.p, android.widget.TextView.OnEditorActionListener
    /* synthetic */ boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent);

    @Override // roam.b.c.a.a.k.p
    /* synthetic */ void onHomeButtonClick(View view);

    @Override // roam.b.c.a.a.k.p
    /* synthetic */ void onMenuItemClick(String str);
}

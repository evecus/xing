package org.roam.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.roam.config.ThemeConfig;
import org.roam.config.ViewConfig;
import org.roam.ui.fragment.IFusionPage;
import roam.b.c.a.a.k.r;
import roam.b.c.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public interface UiManager {
    void closeSearchBar();

    AppBarLayout getAppBarLayout();

    ThemeConfig.ColorsBean getColors();

    CoordinatorLayout getCoordinatorLayout();

    @Deprecated
    IFusionPage getCurrentFragment();

    IFusionPage getCurrentPage();

    ImageView getDrawerHeaderAvatarImageView();

    TextView getDrawerHeaderMainTextView();

    TextView getDrawerHeaderSecondaryTextView();

    DrawerLayout getDrawerLayout();

    RecyclerView getDrawerRecyclerView();

    ImageView getDrawerWallpaper();

    FloatingActionButton getFloatingActionButton();

    @Deprecated
    IFusionPage getFragment(int i);

    a getIndicatorView();

    IFusionPage getPage(int i);

    PagerAdapter getPagerAdapter();

    View getRootView();

    EditText getSearchEditText();

    ThemeConfig getThemeConfig();

    FusionToolbar getToolbar();

    r getUiColorChanger();

    ViewConfig getViewConfig();

    ViewPager getViewPager();

    ViewShader getViewShader();

    boolean isNeedPost();

    boolean isUnfoldSearchBar();

    void removeAllViews();

    void toggleDrawer();
}

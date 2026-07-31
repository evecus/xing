package org.roam.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.androlua.LuaApplication;
import com.baidu.mobstat.PropertyType;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.roam.Application;
import org.roam.R;
import org.roam.config.ThemeConfig;
import org.roam.config.ViewConfig;
import org.roam.config.WebControlBean;
import org.roam.loader.Loader;
import org.roam.ui.FusionUiCoreManger;
import org.roam.ui.behavior.BottomNavigationViewBehavior;
import org.roam.ui.fragment.IFusionPage;
import org.roam.ui.indicator.IndicatorController;
import org.roam.ui.view.ExtendViewPager;
import org.roam.util.FileUtil;
import org.roam.util.UiUtil;
import roam.a.e.a.i;
import roam.b.c.a.a.k.h;
import roam.b.c.a.a.k.r;
import roam.b.c.a.a.k.s.c;
import roam.b.c.a.a.k.v.l;
import roam.b.c.a.a.k.x.b;
import roam.b.c.a.a.k.x.d;
import roam.b.c.a.a.k.x.e;
import roam.b.c.a.a.k.x.f;
import roam.b.c.a.a.k.x.g;
import roamx.adapter.DrawerListAdapter;

/* JADX INFO: loaded from: classes.dex */
public class FusionUiCoreManger implements UiManager {
    public AppCompatActivity a;
    public ViewGroup b;
    public ViewConfig c;
    public ThemeConfig d;
    public ThemeConfig.ColorsBean e;
    public Loader f;
    public DrawerLayout g;
    public ViewShader h;
    public ExtendViewPager i;
    public Application j;
    public AppBarLayout k;
    public FusionToolbar l;
    public RecyclerView m;
    public c n;
    public CoordinatorLayout o;
    public roam.b.c.b.a.a.a p;
    public r q;
    public IndicatorController r;
    public ConstraintLayout s;
    public AppCompatImageView t;
    public ShapeableImageView u;
    public AppCompatTextView v;
    public AppCompatTextView w;
    public FloatingActionButton x;
    public int y;

    public static class a extends Exception {
        public a(String str) {
            super(roam.a.b.a.a.a.j("ParserException: ", str));
        }
    }

    public FusionUiCoreManger(Application application) {
        this.a = application.getActivity();
        this.j = application;
    }

    public final ConstraintLayout a(ViewGroup viewGroup) {
        if (this.s == null && viewGroup != null) {
            ConstraintLayout constraintLayout = new ConstraintLayout(this.a);
            this.s = constraintLayout;
            viewGroup.addView(constraintLayout, 0, new LinearLayout.LayoutParams(-1, Integer.parseInt(new DecimalFormat(PropertyType.UID_PROPERTRY).format(((double) viewGroup.getLayoutParams().width) * 0.6d))));
        }
        return this.s;
    }

    public FusionUiCoreManger b(Loader loader) {
        this.f = loader;
        try {
            ThemeConfig themeConfig = (ThemeConfig) new i().b(loader.getThemeString(), ThemeConfig.class);
            this.d = themeConfig;
            if (themeConfig.getDrawerStyle() == null) {
                themeConfig.setDrawerStyle(new ThemeConfig.DrawerStyleBean());
            }
            this.e = this.d.getColors();
            UiUtil.setLightStatusBar(this.d.isStatusBarDark(), this.a.getWindow().getDecorView());
            f();
            this.h = new ViewShader(Color.parseColor(this.e.getColorPrimary()), Color.parseColor(this.e.getColorAccent()));
            try {
                ViewConfig viewConfig = (ViewConfig) new i().b(loader.getConfigString(), ViewConfig.class);
                this.c = viewConfig;
                if (viewConfig.getDrawer().isEnabled()) {
                    this.a.getWindow().addFlags(67108864);
                }
                return this;
            } catch (Exception e) {
                StringBuilder sbO = roam.a.b.a.a.a.o("parse config failed :");
                sbO.append(e.toString());
                sbO.append("");
                throw new a(sbO.toString());
            }
        } catch (Exception e2) {
            StringBuilder sbO2 = roam.a.b.a.a.a.o("parse theme failed: ");
            sbO2.append(e2.toString());
            sbO2.append("");
            throw new a(sbO2.toString());
        }
    }

    public void c(ExtendViewPager extendViewPager) {
        roam.b.c.b.a.a.d.a.b.a hVar;
        roam.b.c.b.a.a.d.a.a aVar;
        roam.b.c.b.a.a.d.a.b.a dVar;
        ViewPager.PageTransformer cVar;
        extendViewPager.setBackgroundColor(Color.parseColor(this.e.getWindowBackground()));
        FrameLayout frameLayout = (FrameLayout) this.b.findViewById(R.id.r);
        FrameLayout frameLayout2 = (FrameLayout) this.b.findViewById(R.id.r);
        ViewConfig.ViewPagerBean viewPager = this.c.getViewPager();
        List<ViewConfig.ViewPagerBean.PagesBean> pages = viewPager.getPages();
        this.p = new roam.b.c.b.a.a.a(this.a);
        ArrayList arrayList = new ArrayList();
        int darkMode = getViewConfig().getWebView().getDarkMode();
        boolean zIsNightMode = darkMode == 1 ? this.d.isNightMode() : darkMode == 2;
        l lVar = new l();
        lVar.b = zIsNightMode;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f.getFusionDir());
        File file = new File(roam.a.b.a.a.a.l(sb, File.separatorChar, Loader.WEB_CONTROL));
        if (file.exists()) {
            lVar.c = Arrays.asList((WebControlBean[]) new i().b(FileUtil.read(file), WebControlBean[].class));
        }
        lVar.a = Color.parseColor(this.e.getColorAccent());
        Iterator<ViewConfig.ViewPagerBean.PagesBean> it = pages.iterator();
        while (it.hasNext()) {
            String url = it.next().getUrl();
            if (url.contains("{fusiondir}")) {
                url = url.replace("{fusiondir}", this.f.getFusionDir());
            }
            arrayList.add(url);
        }
        if (getViewConfig().getWebView().isColorMode()) {
            this.q = new r(this.a, new View[0]);
            if (getViewConfig().getViewPager().getIndGravity() == 1) {
                r rVar = this.q;
                roam.b.c.b.a.a.a aVar2 = this.p;
                Objects.requireNonNull(rVar);
                if (aVar2 != null) {
                    rVar.a.add(aVar2);
                }
            }
            AppBarLayout appBarLayout = this.k;
            if (appBarLayout != null) {
                r rVar2 = this.q;
                Objects.requireNonNull(rVar2);
                rVar2.a.add(appBarLayout);
            }
            this.q.e = new h(this);
        }
        c cVar2 = new c(this.j, arrayList, lVar, this.i, this.c.getWebView());
        this.n = cVar2;
        extendViewPager.setAdapter(cVar2);
        extendViewPager.setSaveEnabled(false);
        int transformer = this.c.getViewPager().getTransformer();
        if (transformer >= 1 && transformer <= 8) {
            ExtendViewPager extendViewPager2 = this.i;
            switch (transformer) {
                case 1:
                    cVar = new roam.b.c.a.a.k.x.c();
                    break;
                case 2:
                    cVar = new roam.b.c.a.a.k.x.h();
                    break;
                case 3:
                    cVar = new e();
                    break;
                case 4:
                    cVar = new f();
                    break;
                case 5:
                    cVar = new b();
                    break;
                case 6:
                    cVar = new g();
                    break;
                case 7:
                    cVar = new roam.b.c.a.a.k.x.a();
                    break;
                case 8:
                    cVar = new d();
                    break;
                default:
                    cVar = null;
                    break;
            }
            extendViewPager2.setPageTransformer(true, cVar);
        }
        int offscreenPageLimit = this.c.getViewPager().getOffscreenPageLimit();
        if (offscreenPageLimit == 0) {
            offscreenPageLimit = 1;
        }
        extendViewPager.setOffscreenPageLimit(offscreenPageLimit);
        extendViewPager.setUserInputEnabled(getViewConfig().getViewPager().isUserInputEnabled());
        if (pages.size() <= 1) {
            e(frameLayout2);
            e(frameLayout);
            return;
        }
        if (viewPager.getIndGravity() == 0) {
            frameLayout.addView(this.p);
            e(frameLayout2);
        } else {
            frameLayout2.addView(this.p);
            frameLayout2.setBackgroundColor(Color.parseColor(this.d.getIndicatorStyle().getBackground()));
            e(frameLayout);
            if (this.c.getToolbar().isAutoHide() && (frameLayout2.getParent() instanceof CoordinatorLayout)) {
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) frameLayout2.getLayoutParams();
                BottomNavigationViewBehavior bottomNavigationViewBehavior = new BottomNavigationViewBehavior();
                bottomNavigationViewBehavior.a = this.k.getPaddingTop();
                layoutParams.setBehavior(bottomNavigationViewBehavior);
            }
        }
        IndicatorController indicatorController = new IndicatorController(this.a, extendViewPager, pages, viewPager.getIndStyle());
        this.r = indicatorController;
        List<String> lineColors = this.d.getIndicatorStyle().getLineColors();
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it2 = lineColors.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Integer.valueOf(Color.parseColor(it2.next())));
        }
        indicatorController.e(arrayList2);
        IndicatorController indicatorController2 = this.r;
        int color = Color.parseColor(this.d.getIndicatorStyle().getTitleSelectedColor());
        indicatorController2.f = color;
        roam.b.c.b.a.a.d.a.a aVar3 = indicatorController2.g;
        if (aVar3 != null && aVar3.getAdapter() != null) {
            indicatorController2.f(color);
        }
        IndicatorController indicatorController3 = this.r;
        int color2 = Color.parseColor(this.d.getIndicatorStyle().getTitleNormalColor());
        indicatorController3.c = color2;
        roam.b.c.b.a.a.d.a.a aVar4 = indicatorController3.g;
        if (aVar4 != null && aVar4.getAdapter() != null) {
            indicatorController3.f(color2);
        }
        IndicatorController indicatorController4 = this.r;
        indicatorController4.j = this.f;
        indicatorController4.k = this.d.getIndicatorStyle().getTextSize();
        IndicatorController indicatorController5 = this.r;
        roam.b.c.b.a.a.a aVar5 = this.p;
        if (indicatorController5.g == null) {
            roam.b.c.b.a.a.d.a.a aVar6 = new roam.b.c.b.a.a.d.a.a(indicatorController5.a);
            indicatorController5.g = aVar6;
            switch (indicatorController5.d) {
                case 0:
                    aVar6.setAdjustMode(true);
                    aVar6 = indicatorController5.g;
                    hVar = new roam.b.c.a.a.k.w.h(indicatorController5);
                    aVar6.setAdapter(hVar);
                    break;
                case 1:
                    hVar = new roam.b.c.a.a.k.w.f(indicatorController5);
                    aVar6.setAdapter(hVar);
                    break;
                case 2:
                    aVar6.setAdapter(new roam.b.c.a.a.k.w.e(indicatorController5));
                    ((FrameLayout.LayoutParams) aVar5.getLayoutParams()).bottomMargin = UiUtil.dp2px(4.0f);
                    break;
                case 3:
                    aVar6.setScrollPivotX(0.35f);
                    indicatorController5.g.setAdapter(new roam.b.c.a.a.k.w.g(indicatorController5));
                    aVar5.setPadding(UiUtil.dp2px(indicatorController5.a, 16.0f), 0, 0, 0);
                    break;
                case 4:
                    aVar6.setAdjustMode(true);
                    aVar = indicatorController5.g;
                    dVar = new roam.b.c.a.a.k.w.d(indicatorController5, false);
                    aVar.setAdapter(dVar);
                    indicatorController5.i = UiUtil.dp2px(56.0f);
                    break;
                case 5:
                    aVar6.setAdjustMode(true);
                    aVar = indicatorController5.g;
                    dVar = new roam.b.c.a.a.k.w.c(indicatorController5, false);
                    aVar.setAdapter(dVar);
                    indicatorController5.i = UiUtil.dp2px(56.0f);
                    break;
                case 6:
                    aVar6.setAdjustMode(true);
                    aVar = indicatorController5.g;
                    dVar = new roam.b.c.a.a.k.w.d(indicatorController5, true);
                    aVar.setAdapter(dVar);
                    indicatorController5.i = UiUtil.dp2px(56.0f);
                    break;
                case 7:
                    aVar6.setAdjustMode(true);
                    aVar = indicatorController5.g;
                    dVar = new roam.b.c.a.a.k.w.c(indicatorController5, true);
                    aVar.setAdapter(dVar);
                    indicatorController5.i = UiUtil.dp2px(56.0f);
                    break;
            }
        }
        aVar5.setNavigator(indicatorController5.g);
        ViewGroup.LayoutParams layoutParams2 = aVar5.getLayoutParams();
        int i = indicatorController5.i;
        if (i == 0) {
            i = IndicatorController.m;
        }
        layoutParams2.height = i;
        int i2 = this.r.i;
        if (i2 == 0) {
            i2 = IndicatorController.m;
        }
        this.y = i2;
        extendViewPager.addOnPageChangeListener(new roam.b.c.a.a.k.w.i(this.p));
    }

    @Override // org.roam.ui.UiManager
    public void closeSearchBar() {
        ImageView imageView = this.l.a;
        if (imageView != null) {
            imageView.performClick();
        }
    }

    public FusionUiCoreManger d() {
        this.a.setContentView(this.c.getToolbar().isAutoHide() ? this.c.getDrawer().isEnabled() ? R.layout.r : R.layout.r : this.c.getDrawer().isEnabled() ? R.layout.r : R.layout.r);
        this.b = (ViewGroup) this.a.findViewById(R.id.r);
        this.k = (AppBarLayout) this.a.findViewById(R.id.r);
        this.i = (ExtendViewPager) this.b.findViewById(R.id.r);
        this.l = (FusionToolbar) this.b.findViewById(R.id.r);
        this.o = (CoordinatorLayout) this.b.findViewById(R.id.r);
        if (!this.c.getDrawer().isEnabled()) {
            this.a.getWindow().setStatusBarColor(Color.parseColor(this.e.getColorPrimary()));
        } else if (this.c.getToolbar().isEnabled()) {
            this.k.setPadding(0, UiUtil.getStatusBarHeight(this.a), 0, 0);
        }
        if (this.c.getToolbar().isEnabled()) {
            FusionToolbar fusionToolbar = this.l;
            this.a.setSupportActionBar(fusionToolbar);
            this.k.setBackgroundColor(Color.parseColor(this.e.getColorPrimary()));
            Loader loader = this.f;
            Application application = this.j;
            ViewConfig viewConfig = this.c;
            ThemeConfig themeConfig = this.d;
            ViewConfig.ToolbarBean toolbar = viewConfig.getToolbar();
            fusionToolbar.setLoader(loader);
            fusionToolbar.setOnToolbarWidgetClickListener(application);
            fusionToolbar.setHomeButtonEnabled(toolbar.isHomeButtonEnabled());
            fusionToolbar.setSearchEnable(viewConfig.getToolbar().isSearchEnabled());
            fusionToolbar.setDrawerEnable(viewConfig.getDrawer().isEnabled());
            fusionToolbar.setToolbarSubtitleColor(Color.parseColor(themeConfig.getColors().getTextColorSecondary()));
            fusionToolbar.setToolbarTextColor(Color.parseColor(themeConfig.getColors().getTextColorPrimary()));
            fusionToolbar.setSearchBarTextColor(Color.parseColor(themeConfig.getToolbarStyle().getSearchBarTextColor()));
            fusionToolbar.setSearchBarBackgroundColor(Color.parseColor(themeConfig.getToolbarStyle().getSearchBarBackgroundColor()));
            fusionToolbar.setStyle(toolbar.getStyle());
            fusionToolbar.setMenus(toolbar.getMenus());
            fusionToolbar.setTitleText(toolbar.getTitle());
            fusionToolbar.setSubtitleText(toolbar.getSubTitle());
            if (fusionToolbar.getTitleView() != null && fusionToolbar.getSubtitleView() != null) {
                fusionToolbar.getTitleView().getPaint().setFakeBoldText(themeConfig.getToolbarStyle().isToolbarTitleBold());
                fusionToolbar.getTitleView().setTextSize(2, themeConfig.getToolbarStyle().getToolbarTitleSize());
                fusionToolbar.getSubtitleView().setTextSize(2, themeConfig.getToolbarStyle().getToolbarSubtitleSize());
            }
        } else if (this.c.getViewPager().getPages().size() <= 1 || this.c.getViewPager().getIndGravity() == 1) {
            this.k.post(new Runnable(this) { // from class: roam.b.c.a.a.k.j
                public final FusionUiCoreManger a;

                {
                    this.a = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    FusionUiCoreManger fusionUiCoreManger = this.a;
                    fusionUiCoreManger.e(fusionUiCoreManger.k);
                }
            });
        }
        if (this.c.getDrawer().isEnabled()) {
            this.g = (DrawerLayout) this.b.findViewById(R.id.r);
            LinearLayout linearLayout = (LinearLayout) this.b.findViewById(R.id.r);
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: roam.b.c.a.a.k.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                }
            });
            ViewConfig.DrawerBean drawer = this.c.getDrawer();
            ThemeConfig.DrawerStyleBean drawerStyle = this.d.getDrawerStyle();
            linearLayout.setBackgroundColor(Color.parseColor(this.e.getWindowBackground()));
            RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(R.id.r);
            this.m = recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(this.a));
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.width = Double.valueOf(roam.a.a.a.b.a.z(UiUtil.getScreenWidth(this.a), this.d.getDrawerStyle().getWidth())).intValue();
            linearLayout.setLayoutParams(layoutParams);
            int color = Color.parseColor(this.d.getDrawerStyle().getDividerColor());
            Drawable drawable = ContextCompat.getDrawable(this.a, R.drawable.r);
            if (drawable != null) {
                drawable.setTint(color);
                this.m.addItemDecoration(new roam.b.c.a.a.k.s.d(this.a, 1, drawable, 0));
            }
            DrawerListAdapter drawerListAdapter = new DrawerListAdapter(this.a, this.c.getDrawer().getList(), this.f, this.d.getDrawerStyle(), this.h);
            this.m.setAdapter(drawerListAdapter);
            drawerListAdapter.setOnDrawerItemClickListener(this.j);
            if (getViewConfig().getDrawer().isWallpaperEnabled()) {
                File appImagesDir = this.f.getAppImagesDir("drawer_wallpaper");
                if (!appImagesDir.exists()) {
                    appImagesDir = null;
                }
                if (appImagesDir != null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(this.a);
                    this.t = appCompatImageView;
                    appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    this.t.setAdjustViewBounds(false);
                    ConstraintLayout constraintLayoutA = a(linearLayout);
                    ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(0, 0);
                    layoutParams2.topToTop = 0;
                    layoutParams2.bottomToBottom = 0;
                    layoutParams2.leftToLeft = 0;
                    layoutParams2.rightToRight = 0;
                    constraintLayoutA.addView(this.t, layoutParams2);
                    Glide.with((FragmentActivity) this.a).load(appImagesDir).into(this.t);
                }
            }
            if (getViewConfig().getDrawer().isAvatarEnabled()) {
                File appImagesDir2 = this.f.getAppImagesDir("drawer_avatar");
                File file = appImagesDir2.exists() ? appImagesDir2 : null;
                if (file != null) {
                    this.u = new ShapeableImageView(this.a);
                    ConstraintLayout constraintLayoutA2 = a(linearLayout);
                    ConstraintLayout.LayoutParams layoutParams3 = new ConstraintLayout.LayoutParams(-2, -2);
                    layoutParams3.topToTop = 0;
                    layoutParams3.leftToLeft = 0;
                    int dimensionPixelSize = this.a.getResources().getDimensionPixelSize(R.dimen.r);
                    ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin = dimensionPixelSize;
                    ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = dimensionPixelSize + UiUtil.getStatusBarHeight(this.a);
                    int iDp2px = UiUtil.dp2px(this.d.getDrawerStyle().getAvatarSize());
                    ((ViewGroup.MarginLayoutParams) layoutParams3).width = iDp2px;
                    ((ViewGroup.MarginLayoutParams) layoutParams3).height = iDp2px;
                    int iIntValue = Double.valueOf(roam.a.a.a.b.a.z(iDp2px, this.d.getDrawerStyle().getAvatarCornerSize())).intValue();
                    ShapeableImageView shapeableImageView = this.u;
                    shapeableImageView.setShapeAppearanceModel(shapeableImageView.getShapeAppearanceModel().toBuilder().setAllCorners(0, iIntValue).build());
                    constraintLayoutA2.addView(this.u, layoutParams3);
                    Glide.with((FragmentActivity) this.a).load(file).into(this.u);
                }
            }
            String headerMainText = drawer.getHeaderMainText();
            if (!TextUtils.isEmpty(headerMainText)) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(this.a);
                this.v = appCompatTextView;
                appCompatTextView.setText(headerMainText);
                this.v.setTextColor(Color.parseColor(this.e.getTextColorPrimary()));
                ConstraintLayout.LayoutParams layoutParams4 = new ConstraintLayout.LayoutParams(0, -2);
                layoutParams4.bottomToBottom = 0;
                int dimensionPixelSize2 = this.a.getResources().getDimensionPixelSize(R.dimen.r);
                ((ViewGroup.MarginLayoutParams) layoutParams4).leftMargin = dimensionPixelSize2;
                ((ViewGroup.MarginLayoutParams) layoutParams4).topMargin = dimensionPixelSize2;
                layoutParams4.rightToRight = 0;
                ((ViewGroup.MarginLayoutParams) layoutParams4).bottomMargin = dimensionPixelSize2 / 2;
                ((ViewGroup.MarginLayoutParams) layoutParams4).rightMargin = dimensionPixelSize2;
                this.v.setSingleLine();
                this.v.setGravity(GravityCompat.START);
                this.v.setEllipsize(TextUtils.TruncateAt.END);
                layoutParams4.leftToLeft = 0;
                this.v.getPaint().setFakeBoldText(drawerStyle.isHeaderMainTextBold());
                this.v.setTextSize(2, drawerStyle.getHeaderMainTextSize());
                a(linearLayout).addView(this.v, layoutParams4);
            }
            String headerSecondaryText = drawer.getHeaderSecondaryText();
            if (!TextUtils.isEmpty(headerSecondaryText)) {
                AppCompatTextView appCompatTextView2 = new AppCompatTextView(this.a);
                this.w = appCompatTextView2;
                appCompatTextView2.setText(headerSecondaryText);
                this.w.setTextColor(Color.parseColor(this.e.getTextColorSecondary()));
                ConstraintLayout.LayoutParams layoutParams5 = new ConstraintLayout.LayoutParams(0, -2);
                layoutParams5.bottomToBottom = 0;
                this.w.setMaxLines(2);
                this.w.setEllipsize(TextUtils.TruncateAt.END);
                int dimensionPixelSize3 = this.a.getResources().getDimensionPixelSize(R.dimen.r);
                ((ViewGroup.MarginLayoutParams) layoutParams5).leftMargin = dimensionPixelSize3;
                layoutParams5.rightToRight = 0;
                ((ViewGroup.MarginLayoutParams) layoutParams5).bottomMargin = dimensionPixelSize3 / 2;
                ((ViewGroup.MarginLayoutParams) layoutParams5).rightMargin = dimensionPixelSize3;
                layoutParams5.leftToLeft = 0;
                this.w.setTextSize(2, drawerStyle.getHeaderSecondaryTextSize());
                a(linearLayout).addView(this.w, layoutParams5);
                if (this.v != null) {
                    this.w.setId(R.id.r);
                    ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) this.v.getLayoutParams();
                    layoutParams6.bottomToTop = R.id.r;
                    ((ViewGroup.MarginLayoutParams) layoutParams6).bottomMargin = dimensionPixelSize3 / 8;
                }
            }
            if (this.t == null) {
                ShapeableImageView shapeableImageView2 = this.u;
                AppCompatTextView appCompatTextView3 = this.v;
                AppCompatTextView appCompatTextView4 = this.w;
                int i = 0;
                while (true) {
                    if (i >= 3) {
                        break;
                    }
                    if (new Object[]{shapeableImageView2, appCompatTextView3, appCompatTextView4}[i] != null) {
                        ViewCompat.setBackground(this.s, new ColorDrawable(Color.parseColor(this.e.getColorPrimary())));
                        break;
                    }
                    i++;
                }
            }
            if (linearLayout.getChildAt(0).equals(this.m)) {
                LinearLayout.LayoutParams layoutParams7 = (LinearLayout.LayoutParams) this.m.getLayoutParams();
                layoutParams7.topMargin = UiUtil.getStatusBarHeight(this.a);
                this.m.setLayoutParams(layoutParams7);
            }
        }
        c(this.i);
        if (getViewConfig().getFab().isEnabled()) {
            this.x = (FloatingActionButton) LayoutInflater.from(this.a).inflate(R.layout.r, (ViewGroup) this.o, true).findViewById(R.id.r);
            Glide.with((FragmentActivity) this.a).load(this.f.getImagesDir(getViewConfig().getFab().getSrc())).into(this.x);
            this.h.tintFloatingActionButton(this.x);
            if (getViewConfig().getViewPager().getIndGravity() == 1) {
                ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) this.x.getLayoutParams())).bottomMargin += this.y;
            }
            this.x.setOnClickListener(this.j);
        }
        return this;
    }

    public void e(View view) {
        ((ViewGroup) view.getParent()).removeView(view);
    }

    public final void f() {
        AppCompatActivity appCompatActivity;
        int i;
        SharedPreferences sharedPreferences = LuaApplication.getSharedPreferences(this.a);
        int i2 = sharedPreferences.getInt("nightmode", -1);
        if (this.d.isNightMode()) {
            if (i2 != 2) {
                sharedPreferences.edit().putInt("nightmode", 2).apply();
            }
            appCompatActivity = this.a;
            i = R.style.r;
        } else {
            if (i2 != 1) {
                sharedPreferences.edit().putInt("nightmode", 1).apply();
            }
            appCompatActivity = this.a;
            i = R.style.AppTheme;
        }
        appCompatActivity.setTheme(i);
    }

    @Override // org.roam.ui.UiManager
    public AppBarLayout getAppBarLayout() {
        return this.k;
    }

    @Override // org.roam.ui.UiManager
    public ThemeConfig.ColorsBean getColors() {
        return this.e;
    }

    @Override // org.roam.ui.UiManager
    public CoordinatorLayout getCoordinatorLayout() {
        return this.o;
    }

    @Override // org.roam.ui.UiManager
    public IFusionPage getCurrentFragment() {
        if (this.c.getViewPager().getPages().size() == 0) {
            return null;
        }
        return getFragment(this.i.getCurrentItem());
    }

    @Override // org.roam.ui.UiManager
    public IFusionPage getCurrentPage() {
        return getCurrentFragment();
    }

    @Override // org.roam.ui.UiManager
    public ImageView getDrawerHeaderAvatarImageView() {
        return this.u;
    }

    @Override // org.roam.ui.UiManager
    public TextView getDrawerHeaderMainTextView() {
        return this.v;
    }

    @Override // org.roam.ui.UiManager
    public TextView getDrawerHeaderSecondaryTextView() {
        return this.w;
    }

    @Override // org.roam.ui.UiManager
    public DrawerLayout getDrawerLayout() {
        return this.g;
    }

    @Override // org.roam.ui.UiManager
    public RecyclerView getDrawerRecyclerView() {
        return this.m;
    }

    @Override // org.roam.ui.UiManager
    public ImageView getDrawerWallpaper() {
        return this.t;
    }

    @Override // org.roam.ui.UiManager
    public FloatingActionButton getFloatingActionButton() {
        return this.x;
    }

    @Override // org.roam.ui.UiManager
    public IFusionPage getFragment(int i) {
        return ((c) this.i.getAdapter()).a.get(i);
    }

    public IndicatorController getIndicatorController() {
        return this.r;
    }

    @Override // org.roam.ui.UiManager
    public roam.b.c.b.a.a.a getIndicatorView() {
        return this.p;
    }

    @Override // org.roam.ui.UiManager
    public IFusionPage getPage(int i) {
        return getFragment(i);
    }

    @Override // org.roam.ui.UiManager
    public PagerAdapter getPagerAdapter() {
        return this.n;
    }

    @Override // org.roam.ui.UiManager
    public View getRootView() {
        return this.b;
    }

    @Override // org.roam.ui.UiManager
    public EditText getSearchEditText() {
        return this.l.getSearchEditText();
    }

    @Override // org.roam.ui.UiManager
    public ThemeConfig getThemeConfig() {
        return this.d;
    }

    @Override // org.roam.ui.UiManager
    public FusionToolbar getToolbar() {
        return this.l;
    }

    @Override // org.roam.ui.UiManager
    public r getUiColorChanger() {
        return this.q;
    }

    @Override // org.roam.ui.UiManager
    public ViewConfig getViewConfig() {
        return this.c;
    }

    @Override // org.roam.ui.UiManager
    public ViewPager getViewPager() {
        return this.i;
    }

    @Override // org.roam.ui.UiManager
    public ViewShader getViewShader() {
        return this.h;
    }

    @Override // org.roam.ui.UiManager
    public boolean isNeedPost() {
        return (getViewConfig() == null || getViewConfig().getViewPager() == null || getViewConfig().getViewPager().getPages() == null || getViewConfig().getViewPager().getPages().size() <= 0) ? false : true;
    }

    @Override // org.roam.ui.UiManager
    public boolean isUnfoldSearchBar() {
        FusionToolbar fusionToolbar;
        ImageView imageView;
        if ((this.c.getToolbar().isSearchEnabled() || this.l != null) && this.l.getStyle() == 0 && (imageView = (fusionToolbar = this.l).a) != null && fusionToolbar.b == 0) {
            return ((View) imageView.getParent()).isShown();
        }
        return false;
    }

    @Override // org.roam.ui.UiManager
    public void removeAllViews() {
        c cVar = this.n;
        if (cVar != null) {
            cVar.a();
            this.n = null;
        }
        ExtendViewPager extendViewPager = this.i;
        if (extendViewPager != null) {
            extendViewPager.removeAllViews();
            this.i.setAdapter(null);
        }
        View viewFindViewById = this.a.findViewById(android.R.id.content);
        if (viewFindViewById instanceof ViewGroup) {
            ((ViewGroup) viewFindViewById).removeAllViews();
        }
    }

    @Override // org.roam.ui.UiManager
    public void toggleDrawer() {
        if (this.g.isDrawerOpen(GravityCompat.START)) {
            this.g.closeDrawer(GravityCompat.START);
        } else {
            this.g.openDrawer(GravityCompat.START);
        }
    }
}

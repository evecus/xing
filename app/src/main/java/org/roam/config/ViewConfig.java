package org.roam.config;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ViewConfig {
    private DrawerBean drawer;
    private FabBean fab;
    private ToolbarBean toolbar;
    private ViewPagerBean viewPager;
    private WebViewBean webView;

    public static class DrawerBean {
        private boolean avatarEnabled;
        private boolean enabled;
        private String headerMainText;
        private String headerSecondaryText;
        private List<List<ListBean>> list;
        private boolean wallpaperEnabled;

        public static class ListBean {
            private boolean checked;
            private String icon;
            private String title;
            private int type;

            public String getIcon() {
                return this.icon;
            }

            public String getTitle() {
                return this.title;
            }

            public int getType() {
                return this.type;
            }

            public boolean isChecked() {
                return this.checked;
            }

            public void setChecked(boolean z) {
                this.checked = z;
            }

            public void setIcon(String str) {
                this.icon = str;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public void setType(int i) {
                this.type = i;
            }
        }

        public String getHeaderMainText() {
            return this.headerMainText;
        }

        public String getHeaderSecondaryText() {
            return this.headerSecondaryText;
        }

        public List<List<ListBean>> getList() {
            return this.list;
        }

        public boolean isAvatarEnabled() {
            return this.avatarEnabled;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public boolean isWallpaperEnabled() {
            return this.wallpaperEnabled;
        }

        public void setAvatarEnabled(boolean z) {
            this.avatarEnabled = z;
        }

        public void setEnabled(boolean z) {
            this.enabled = z;
        }

        public void setHeaderMainText(String str) {
            this.headerMainText = str;
        }

        public void setHeaderSecondaryText(String str) {
            this.headerSecondaryText = str;
        }

        public void setList(List<List<ListBean>> list) {
            this.list = list;
        }

        public void setWallpaperEnabled(boolean z) {
            this.wallpaperEnabled = z;
        }
    }

    public static class FabBean {
        private boolean enabled;
        private String src;

        public String getSrc() {
            return this.src;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean z) {
            this.enabled = z;
        }

        public void setSrc(String str) {
            this.src = str;
        }
    }

    public static class ToolbarBean {
        private boolean autoHide;
        private boolean enabled;
        private boolean homeButtonEnabled;
        private List<MenusBean> menus;
        private boolean searchEnabled;
        private int style;
        private String subTitle;
        private String title;

        public static class MenusBean {
            private String icon;
            private String title;
            private int type;

            public String getIcon() {
                return this.icon;
            }

            public String getTitle() {
                return this.title;
            }

            public int getType() {
                return this.type;
            }

            public void setIcon(String str) {
                this.icon = str;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public void setType(int i) {
                this.type = i;
            }
        }

        public List<MenusBean> getMenus() {
            return this.menus;
        }

        public int getStyle() {
            return this.style;
        }

        public String getSubTitle() {
            return this.subTitle;
        }

        public String getTitle() {
            return this.title;
        }

        public boolean isAutoHide() {
            return this.autoHide;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public boolean isHomeButtonEnabled() {
            return this.homeButtonEnabled;
        }

        public boolean isSearchEnabled() {
            return this.searchEnabled;
        }

        public void setAutoHide(boolean z) {
            this.autoHide = z;
        }

        public void setEnabled(boolean z) {
            this.enabled = z;
        }

        public void setHomeButtonEnabled(boolean z) {
            this.homeButtonEnabled = z;
        }

        public void setMenus(List<MenusBean> list) {
            this.menus = list;
        }

        public void setSearchEnabled(boolean z) {
            this.searchEnabled = z;
        }

        public void setStyle(int i) {
            this.style = i;
        }

        public void setSubTitle(String str) {
            this.subTitle = str;
        }

        public void setTitle(String str) {
            this.title = str;
        }
    }

    public static class ViewPagerBean {
        private int indGravity;
        private int indStyle;
        private int offscreenPageLimit;
        private List<PagesBean> pages;
        private int transformer;
        private boolean userInputEnabled;

        public static class PagesBean {
            private String indIcon;
            private String indTitle;
            private String url;

            public String getIndIcon() {
                return this.indIcon;
            }

            public String getIndTitle() {
                return this.indTitle;
            }

            public String getUrl() {
                return this.url;
            }

            public void setIndIcon(String str) {
                this.indIcon = str;
            }

            public void setIndTitle(String str) {
                this.indTitle = str;
            }

            public void setUrl(String str) {
                this.url = str;
            }
        }

        public int getIndGravity() {
            return this.indGravity;
        }

        public int getIndStyle() {
            return this.indStyle;
        }

        public int getOffscreenPageLimit() {
            return this.offscreenPageLimit;
        }

        public List<PagesBean> getPages() {
            return this.pages;
        }

        public int getTransformer() {
            return this.transformer;
        }

        public boolean isUserInputEnabled() {
            return this.userInputEnabled;
        }

        public void setIndGravity(int i) {
            this.indGravity = i;
        }

        public void setIndStyle(int i) {
            this.indStyle = i;
        }

        public void setOffscreenPageLimit(int i) {
            this.offscreenPageLimit = i;
        }

        public void setPages(List<PagesBean> list) {
            this.pages = list;
        }

        public void setTransformer(int i) {
            this.transformer = i;
        }

        public void setUserInputEnabled(boolean z) {
            this.userInputEnabled = z;
        }
    }

    public static class WebViewBean {
        private boolean colorMode;
        private int darkMode;
        private boolean javaScriptEnabled;
        private int openOtherPageWays;
        private boolean pcMode;
        private String userAgent;

        public int getDarkMode() {
            return this.darkMode;
        }

        public int getOpenOtherPageWays() {
            return this.openOtherPageWays;
        }

        public String getUserAgent() {
            return this.userAgent;
        }

        public boolean isColorMode() {
            return this.colorMode;
        }

        public boolean isJavaScriptEnabled() {
            return this.javaScriptEnabled;
        }

        public boolean isPcMode() {
            return this.pcMode;
        }

        public void setColorMode(boolean z) {
            this.colorMode = z;
        }

        public void setDarkMode(int i) {
            this.darkMode = i;
        }

        public void setJavaScriptEnabled(boolean z) {
            this.javaScriptEnabled = z;
        }

        public void setOpenOtherPageWays(int i) {
            this.openOtherPageWays = i;
        }

        public void setPcMode(boolean z) {
            this.pcMode = z;
        }

        public void setUserAgent(String str) {
            this.userAgent = str;
        }
    }

    public DrawerBean getDrawer() {
        return this.drawer;
    }

    public FabBean getFab() {
        return this.fab;
    }

    public ToolbarBean getToolbar() {
        return this.toolbar;
    }

    public ViewPagerBean getViewPager() {
        return this.viewPager;
    }

    public WebViewBean getWebView() {
        return this.webView;
    }

    public void setDrawer(DrawerBean drawerBean) {
        this.drawer = drawerBean;
    }

    public void setFab(FabBean fabBean) {
        this.fab = fabBean;
    }

    public void setToolbar(ToolbarBean toolbarBean) {
        this.toolbar = toolbarBean;
    }

    public void setViewPager(ViewPagerBean viewPagerBean) {
        this.viewPager = viewPagerBean;
    }

    public void setWebView(WebViewBean webViewBean) {
        this.webView = webViewBean;
    }
}

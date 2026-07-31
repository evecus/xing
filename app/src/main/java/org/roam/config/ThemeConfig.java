package org.roam.config;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ThemeConfig {
    private ColorsBean colors;
    private DrawerStyleBean drawerStyle;
    private IndicatorStyleBean indicatorStyle;
    private boolean nightMode;
    private boolean statusBarDark;
    private ToolbarStyleBean toolbarStyle;

    public static class ColorsBean {
        private String colorAccent;
        private String colorPrimary;
        private String textColorPrimary;
        private String textColorSecondary;
        private String windowBackground;

        public String getColorAccent() {
            return this.colorAccent;
        }

        public String getColorPrimary() {
            return this.colorPrimary;
        }

        public String getTextColorPrimary() {
            return this.textColorPrimary;
        }

        public String getTextColorSecondary() {
            return this.textColorSecondary;
        }

        public String getWindowBackground() {
            return this.windowBackground;
        }

        public void setColorAccent(String str) {
            this.colorAccent = str;
        }

        public void setColorPrimary(String str) {
            this.colorPrimary = str;
        }

        public void setTextColorPrimary(String str) {
            this.textColorPrimary = str;
        }

        public void setTextColorSecondary(String str) {
            this.textColorSecondary = str;
        }

        public void setWindowBackground(String str) {
            this.windowBackground = str;
        }
    }

    public static class DrawerStyleBean {
        private int avatarCornerSize;
        private int avatarSize;
        private String dividerColor;
        private boolean headerMainTextBold;
        private int headerMainTextSize;
        private int headerSecondaryTextSize;
        private String itemIconTint;
        private String itemTextColor;
        private int width;

        public int getAvatarCornerSize() {
            return this.avatarCornerSize;
        }

        public int getAvatarSize() {
            return this.avatarSize;
        }

        public String getDividerColor() {
            return this.dividerColor;
        }

        public int getHeaderMainTextSize() {
            return this.headerMainTextSize;
        }

        public int getHeaderSecondaryTextSize() {
            return this.headerSecondaryTextSize;
        }

        public String getItemIconTint() {
            return this.itemIconTint;
        }

        public String getItemTextColor() {
            return this.itemTextColor;
        }

        public int getWidth() {
            return this.width;
        }

        public boolean isHeaderMainTextBold() {
            return this.headerMainTextBold;
        }

        public void setAvatarCornerSize(int i) {
            this.avatarCornerSize = i;
        }

        public void setAvatarSize(int i) {
            this.avatarSize = i;
        }

        public void setDividerColor(String str) {
            this.dividerColor = str;
        }

        public void setHeaderMainTextBold(boolean z) {
            this.headerMainTextBold = z;
        }

        public void setHeaderMainTextSize(int i) {
            this.headerMainTextSize = i;
        }

        public void setHeaderSecondaryTextSize(int i) {
            this.headerSecondaryTextSize = i;
        }

        public void setItemIconTint(String str) {
            this.itemIconTint = str;
        }

        public void setItemTextColor(String str) {
            this.itemTextColor = str;
        }

        public void setWidth(int i) {
            this.width = i;
        }
    }

    public static class IndicatorStyleBean {
        private String background;
        private List<String> lineColors;
        private int textSize;
        private String titleNormalColor;
        private String titleSelectedColor;

        public String getBackground() {
            return this.background;
        }

        public List<String> getLineColors() {
            return this.lineColors;
        }

        public int getTextSize() {
            return this.textSize;
        }

        public String getTitleNormalColor() {
            return this.titleNormalColor;
        }

        public String getTitleSelectedColor() {
            return this.titleSelectedColor;
        }

        public void setBackground(String str) {
            this.background = str;
        }

        public void setLineColors(List<String> list) {
            this.lineColors = list;
        }

        public void setTextSize(int i) {
            this.textSize = i;
        }

        public void setTitleNormalColor(String str) {
            this.titleNormalColor = str;
        }

        public void setTitleSelectedColor(String str) {
            this.titleSelectedColor = str;
        }
    }

    public static class ToolbarStyleBean {
        private String searchBarBackgroundColor;
        private String searchBarTextColor;
        private int toolbarSubtitleSize;
        private boolean toolbarTitleBold;
        private int toolbarTitleSize;

        public String getSearchBarBackgroundColor() {
            return this.searchBarBackgroundColor;
        }

        public String getSearchBarTextColor() {
            return this.searchBarTextColor;
        }

        public int getToolbarSubtitleSize() {
            return this.toolbarSubtitleSize;
        }

        public int getToolbarTitleSize() {
            return this.toolbarTitleSize;
        }

        public boolean isToolbarTitleBold() {
            return this.toolbarTitleBold;
        }

        public void setSearchBarBackgroundColor(String str) {
            this.searchBarBackgroundColor = str;
        }

        public void setSearchBarTextColor(String str) {
            this.searchBarTextColor = str;
        }

        public void setToolbarSubtitleSize(int i) {
            this.toolbarSubtitleSize = i;
        }

        public void setToolbarTitleBold(boolean z) {
            this.toolbarTitleBold = z;
        }

        public void setToolbarTitleSize(int i) {
            this.toolbarTitleSize = i;
        }
    }

    public ColorsBean getColors() {
        return this.colors;
    }

    public DrawerStyleBean getDrawerStyle() {
        return this.drawerStyle;
    }

    public IndicatorStyleBean getIndicatorStyle() {
        return this.indicatorStyle;
    }

    public ToolbarStyleBean getToolbarStyle() {
        return this.toolbarStyle;
    }

    public boolean isNightMode() {
        return this.nightMode;
    }

    public boolean isStatusBarDark() {
        return this.statusBarDark;
    }

    public void setColors(ColorsBean colorsBean) {
        this.colors = colorsBean;
    }

    public void setDrawerStyle(DrawerStyleBean drawerStyleBean) {
        this.drawerStyle = drawerStyleBean;
    }

    public void setIndicatorStyle(IndicatorStyleBean indicatorStyleBean) {
        this.indicatorStyle = indicatorStyleBean;
    }

    public void setNightMode(boolean z) {
        this.nightMode = z;
    }

    public void setStatusBarDark(boolean z) {
        this.statusBarDark = z;
    }

    public void setToolbarStyle(ToolbarStyleBean toolbarStyleBean) {
        this.toolbarStyle = toolbarStyleBean;
    }
}

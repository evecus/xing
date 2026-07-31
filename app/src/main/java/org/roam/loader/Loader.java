package org.roam.loader;

import android.widget.ImageView;
import java.io.File;
import org.roam.config.AppConfig;

/* JADX INFO: loaded from: classes.dex */
public abstract class Loader {
    public static final String CONFIG_APP = "client.json";
    public static final String FIXED_APP_IMAGE = "private_app";
    public static final String IMAGES_DIR = "images";
    public static final String LAUNCH_PAGE = "main";
    public static final String PAGES = "pages";
    public static final String THEME_DIR = "theme";
    public static final String VIEW_CONFIG_NAME = "view.json";
    public static final String WEB_CONTROL = "control.json";

    public abstract AppConfig getAppConfig();

    public abstract File getAppImagesDir(String str);

    public abstract String getConfigString();

    public abstract String getFusionDir();

    public abstract String getImagePath(String str);

    public abstract String getImagesDir(String str);

    public abstract String getPageName();

    public abstract File getProjectDir();

    public abstract String getThemeString();

    public abstract boolean isConfigAvailable();

    public abstract void loadAppImage(ImageView imageView, String str);

    public abstract void loadImage(ImageView imageView, String str);
}

package org.roam.loader;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.io.File;
import org.roam.Application;
import org.roam.config.AppConfig;
import org.roam.config.ViewConfig;
import org.roam.util.FileUtil;
import roam.a.b.a.a.a;
import roam.a.e.a.i;

/* JADX INFO: loaded from: classes.dex */
public class StorageLoader extends Loader {
    public Application a;
    public File b;
    public File c;
    public File d;
    public String e;
    public Context f;
    public AppConfig g;

    public StorageLoader(Application application, String str, String str2) {
        this.a = application;
        this.f = application.getActivity();
        this.b = new File(str);
        this.e = str2;
        StringBuilder sbO = a.o(str);
        sbO.append(File.separatorChar);
        sbO.append(Loader.PAGES);
        this.d = new File(a.l(sbO, File.separatorChar, str2));
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.getAbsolutePath());
        this.c = new File(a.l(sb, File.separatorChar, Loader.VIEW_CONFIG_NAME));
    }

    @Override // org.roam.loader.Loader
    public AppConfig getAppConfig() {
        if (this.g == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.b.getAbsolutePath());
            this.g = (AppConfig) new i().b(FileUtil.read(new File(a.l(sb, File.separatorChar, Loader.CONFIG_APP))), AppConfig.class);
        }
        return this.g;
    }

    @Override // org.roam.loader.Loader
    public File getAppImagesDir(String str) {
        File file = new File(getImagesDir(Loader.FIXED_APP_IMAGE));
        if (!file.exists()) {
            file.mkdir();
        }
        if (str == null || str.trim().isEmpty()) {
            return file;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(file.getAbsolutePath());
        return new File(a.l(sb, File.separatorChar, str));
    }

    @Override // org.roam.loader.Loader
    public String getConfigString() {
        File file = this.c;
        if (file == null || !file.exists()) {
            return null;
        }
        return FileUtil.read(this.c);
    }

    @Override // org.roam.loader.Loader
    public String getFusionDir() {
        return this.d.getAbsolutePath();
    }

    @Override // org.roam.loader.Loader
    public String getImagePath(String str) {
        String imagesDir = getImagesDir(str);
        if (new File(imagesDir).exists()) {
            return imagesDir;
        }
        if (!new File(getFusionDir() + "/" + str).exists()) {
            return null;
        }
        return getFusionDir() + "/" + str;
    }

    @Override // org.roam.loader.Loader
    public String getImagesDir(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b.getAbsolutePath());
        String strL = a.l(sb, File.separatorChar, Loader.IMAGES_DIR);
        return str != null ? a.l(a.o(strL), File.separatorChar, str) : strL;
    }

    @Override // org.roam.loader.Loader
    public String getPageName() {
        return this.e;
    }

    @Override // org.roam.loader.Loader
    public File getProjectDir() {
        return this.b;
    }

    @Override // org.roam.loader.Loader
    public String getThemeString() {
        try {
            return FileUtil.read(new File(this.b.getAbsolutePath() + File.separatorChar + Loader.THEME_DIR + File.separatorChar + getAppConfig().getTheme()));
        } catch (Exception e) {
            return null;
        }
    }

    @Override // org.roam.loader.Loader
    public boolean isConfigAvailable() {
        File file = this.c;
        return file != null && file.exists();
    }

    @Override // org.roam.loader.Loader
    public void loadAppImage(ImageView imageView, String str) {
        File appImagesDir = getAppImagesDir(str);
        if (appImagesDir.exists()) {
            Glide.with(this.f).load(appImagesDir).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imageView);
            return;
        }
        if (roam.b.c.a.a.j.a.b == null) {
            roam.b.c.a.a.j.a.b = new roam.b.c.a.a.j.a();
        }
        if (roam.b.c.a.a.j.a.b.a.containsKey(str)) {
            if (roam.b.c.a.a.j.a.b == null) {
                roam.b.c.a.a.j.a.b = new roam.b.c.a.a.j.a();
            }
            imageView.setImageResource(roam.b.c.a.a.j.a.b.a.get(str).intValue());
        }
    }

    @Override // org.roam.loader.Loader
    public void loadImage(ImageView imageView, String str) {
        Glide.with(this.f).load(getImagesDir(str)).into(imageView);
    }

    public void updatePageConfig() {
        String strF = new i().f(this.a.getViewConfig());
        StringBuilder sb = new StringBuilder();
        sb.append(getFusionDir());
        FileUtil.write(new File(a.l(sb, File.separatorChar, Loader.VIEW_CONFIG_NAME)), strF);
    }

    public void updatePageConfig(ViewConfig viewConfig) {
        String strF = new i().f(viewConfig);
        StringBuilder sb = new StringBuilder();
        sb.append(getFusionDir());
        FileUtil.write(new File(a.l(sb, File.separatorChar, Loader.VIEW_CONFIG_NAME)), strF);
    }
}

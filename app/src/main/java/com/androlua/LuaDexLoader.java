package com.androlua;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.baidu.mobstat.PropertyType;
import com.luajava.LuaException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaDexLoader {
    private static HashMap<String, LuaDexClassLoader> dexCache = new HashMap<>();
    private String luaDir;
    private AssetManager mAssetManager;
    private LuaContext mContext;
    private LuaResources mResources;
    private Resources.Theme mTheme;
    private ArrayList<ClassLoader> dexList = new ArrayList<>();
    private HashMap<String, String> libCache = new HashMap<>();
    private String odexDir = LuaApplication.getInstance().getOdexDir();

    public LuaDexLoader(LuaContext luaContext) {
        this.mContext = luaContext;
        this.luaDir = luaContext.getLuaDir();
    }

    public AssetManager getAssets() {
        return this.mAssetManager;
    }

    public ArrayList<ClassLoader> getClassLoaders() {
        return this.dexList;
    }

    public HashMap<String, String> getLibrarys() {
        return this.libCache;
    }

    public Resources getResources() {
        return this.mResources;
    }

    public Resources.Theme getTheme() {
        return this.mTheme;
    }

    public LuaDexClassLoader loadApp(String str) {
        try {
            LuaDexClassLoader luaDexClassLoader = dexCache.get(str);
            if (luaDexClassLoader == null) {
                ApplicationInfo applicationInfo = this.mContext.getContext().getPackageManager().getPackageInfo(str, 0).applicationInfo;
                LuaDexClassLoader luaDexClassLoader2 = new LuaDexClassLoader(applicationInfo.publicSourceDir, LuaApplication.getInstance().getOdexDir(), applicationInfo.nativeLibraryDir, this.mContext.getContext().getClassLoader());
                dexCache.put(str, luaDexClassLoader2);
                luaDexClassLoader = luaDexClassLoader2;
            }
            if (this.dexList.contains(luaDexClassLoader)) {
                return luaDexClassLoader;
            }
            this.dexList.add(luaDexClassLoader);
            return luaDexClassLoader;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DexClassLoader loadDex(String str) throws LuaException {
        String string;
        StringBuilder sbD;
        LuaDexClassLoader luaDexClassLoaderLoadApp = dexCache.get(str);
        if (luaDexClassLoaderLoadApp == null) {
            luaDexClassLoaderLoadApp = loadApp(str);
        }
        if (luaDexClassLoaderLoadApp == null) {
            if (str.charAt(0) != '/') {
                string = this.luaDir + "/" + str;
            } else {
                string = str;
            }
            if (!new File(string).exists()) {
                if (new File(a.j(string, ".dex")).exists()) {
                    sbD = a.d(string, ".dex");
                } else {
                    if (!new File(a.j(string, ".jar")).exists()) {
                        throw new LuaException(a.j(string, " not found"));
                    }
                    sbD = a.d(string, ".jar");
                }
                string = sbD.toString();
            }
            String fileMD5 = LuaUtil.getFileMD5(string);
            if (fileMD5 == null || !fileMD5.equals(PropertyType.UID_PROPERTRY)) {
                str = fileMD5;
            }
            LuaDexClassLoader luaDexClassLoader = dexCache.get(str);
            if (luaDexClassLoader == null) {
                luaDexClassLoader = new LuaDexClassLoader(string, this.odexDir, LuaApplication.getInstance().getApplicationInfo().nativeLibraryDir, this.mContext.getContext().getClassLoader());
                dexCache.put(str, luaDexClassLoader);
            }
            luaDexClassLoaderLoadApp = luaDexClassLoader;
        }
        if (!this.dexList.contains(luaDexClassLoaderLoadApp)) {
            this.dexList.add(luaDexClassLoaderLoadApp);
            String dexPath = luaDexClassLoaderLoadApp.getDexPath();
            if (dexPath.endsWith(".jar")) {
                loadResources(dexPath);
            }
        }
        return luaDexClassLoaderLoadApp;
    }

    public void loadLib(String str) throws LuaException {
        int iIndexOf = str.indexOf(".");
        String strSubstring = iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
        if (strSubstring.startsWith("lib")) {
            strSubstring = strSubstring.substring(3);
        }
        String str2 = this.mContext.getContext().getDir(strSubstring, 0).getAbsolutePath() + "/lib" + strSubstring + ".so";
        if (!new File(str2).exists()) {
            if (!new File(a.n(new StringBuilder(), this.luaDir, "/libs/lib", strSubstring, ".so")).exists()) {
                throw new LuaException(a.j("can not find lib ", str));
            }
            LuaUtil.copyFile(a.n(new StringBuilder(), this.luaDir, "/libs/lib", strSubstring, ".so"), str2);
        }
        this.libCache.put(strSubstring, str2);
    }

    public void loadLibs() throws LuaException {
        File[] fileArrListFiles = new File(this.mContext.getProjectDir() + "/libs").listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (!file.isDirectory()) {
                if (file.getAbsolutePath().endsWith(".so")) {
                    loadLib(file.getName());
                } else {
                    loadDex(file.getAbsolutePath());
                }
            }
        }
    }

    public void loadResources(String str) {
        try {
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            if (((Integer) assetManager.getClass().getMethod("addAssetPath", String.class).invoke(assetManager, str)).intValue() == 0) {
                return;
            }
            this.mAssetManager = assetManager;
            Resources resources = this.mContext.getContext().getResources();
            LuaResources luaResources = new LuaResources(this.mAssetManager, resources.getDisplayMetrics(), resources.getConfiguration());
            this.mResources = luaResources;
            luaResources.setSuperResources(resources);
            Resources.Theme themeNewTheme = this.mResources.newTheme();
            this.mTheme = themeNewTheme;
            themeNewTheme.setTo(this.mContext.getContext().getTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

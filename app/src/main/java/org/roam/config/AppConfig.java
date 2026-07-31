package org.roam.config;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppConfig {
    private List<String> modules = new ArrayList();
    private String name;
    private String packageName;
    private String theme;
    private List<String> userPermission;
    private int versionCode;
    private String versionName;

    public List<String> getModules() {
        return this.modules;
    }

    public String getName() {
        return this.name;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getTheme() {
        return this.theme;
    }

    public List<String> getUserPermission() {
        return this.userPermission;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setModules(List<String> list) {
        this.modules = list;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public void setUserPermission(List<String> list) {
        this.userPermission = list;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }
}

package com.baidu.mobstat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class cn {
    private static volatile cn b;
    private static OnAppBackgroundListener j;
    private String f;
    private static final Pattern c = Pattern.compile("\\s*|\t|\r|\n");
    private static boolean h = true;
    private static boolean i = false;
    private static boolean k = true;
    private List<PermissionEnum> d = new ArrayList();
    private String e = "android.permission.APP_LIST";
    public final int a = 100;
    private List<JSONObject> g = new ArrayList();

    public static cn a() {
        if (b == null) {
            synchronized (cn.class) {
                if (b == null) {
                    b = new cn();
                }
            }
        }
        return b;
    }

    private boolean d() {
        OnAppBackgroundListener onAppBackgroundListener = j;
        return onAppBackgroundListener != null ? onAppBackgroundListener.isBackground() : i;
    }

    public void a(OnAppBackgroundListener onAppBackgroundListener) {
        j = onAppBackgroundListener;
    }

    public void a(String str) {
        this.f = str;
    }

    public boolean a(boolean z) {
        return z ? h && !d() : h;
    }

    public void b(boolean z) {
        h = z;
    }

    public boolean b() {
        return a(true);
    }

    public void c(boolean z) {
        k = z;
    }

    public boolean c() {
        return k;
    }
}

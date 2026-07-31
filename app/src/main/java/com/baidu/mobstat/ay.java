package com.baidu.mobstat;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import com.baidu.mobstat.ActivityLifeObserver;
import com.baidu.mobstat.BaiduStatJSInterface;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class ay {
    private static volatile boolean a = true;
    private static volatile boolean b = false;

    public static class a implements ActivityLifeObserver.IActivityLifeCallback {
        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (bw.c().b()) {
                bw.c().a("onActivityCreated");
            }
            bg.a().a(activity);
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityPaused(Activity activity) {
            if (bw.c().b()) {
                bw.c().a("onActivityPaused");
            }
            bg.a().c(activity);
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityResumed(Activity activity) {
            if (bw.c().b()) {
                bw.c().a("onActivityResumed");
            }
            bg.a().b(activity);
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityStarted(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityStopped(Activity activity) {
        }
    }

    public static class b implements BaiduStatJSInterface.IWebviewPageLoadCallback {
        @Override // com.baidu.mobstat.BaiduStatJSInterface.IWebviewPageLoadCallback
        public void onPageFinished(WebView webView, String str, ce ceVar) {
            if (bw.c().b()) {
                bw.c().a("WebView onPageFinished");
            }
            webView.addJavascriptInterface(ceVar, "WebViewInterface");
            bg.a().a(webView, str, ceVar);
        }

        @Override // com.baidu.mobstat.BaiduStatJSInterface.IWebviewPageLoadCallback
        public void onPageStarted(WebView webView, String str, ce ceVar) {
            if (bw.c().b()) {
                bw.c().a("WebView onPageStarted");
            }
            webView.addJavascriptInterface(ceVar, "WebViewInterface");
        }
    }

    public static void a(String str) {
        b = true;
        bg.a().a(str);
    }

    public static void a(boolean z) {
        bg.a().a(z);
    }

    public static boolean a() {
        return b;
    }

    public static boolean b() {
        return a;
    }

    public static JSONArray c() {
        return bg.a().e();
    }
}

package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import com.baidu.mobstat.ActivityLifeObserver;
import com.baidu.mobstat.BaiduStatJSInterface;
import com.baidu.mobstat.MtjConfig;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bp {

    public static class a implements ActivityLifeObserver.IActivityLifeCallback {
        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityPaused(Activity activity) {
            if (ay.a() && !br.a().b()) {
                if (ca.c().b()) {
                    ca.c().a("onActivityPaused");
                }
                bt.a().b(activity);
            }
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityResumed(Activity activity) {
            if (ay.a() && !br.a().b()) {
                if (ca.c().b()) {
                    ca.c().a("onActivityResumed");
                }
                bt.a().a(activity);
            }
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
            if (ay.a() && !br.a().b()) {
                if (ca.c().b()) {
                    ca.c().a("WebView onPageFinished");
                }
                webView.addJavascriptInterface(ceVar, "WebViewInterface");
                bt.a().a(webView, str, ceVar);
            }
        }

        @Override // com.baidu.mobstat.BaiduStatJSInterface.IWebviewPageLoadCallback
        public void onPageStarted(WebView webView, String str, ce ceVar) {
            if (ay.a() && !br.a().b()) {
                if (ca.c().b()) {
                    ca.c().a("WebView onPageStarted");
                }
                webView.addJavascriptInterface(ceVar, "WebViewInterface");
            }
        }
    }

    public static void a(Context context) {
        if (ay.a() && !br.a().b()) {
            bq.a().a(context);
        }
    }

    public static void a(Context context, boolean z) {
        if (ay.a() && !br.a().b()) {
            bq.a().a(context, z);
        }
    }

    public static void a(MtjConfig.FeedTrackStrategy feedTrackStrategy) {
        if (br.a().b()) {
            return;
        }
        bl.a(feedTrackStrategy);
    }

    public static void a(String str) {
        if (ay.a() && !br.a().b()) {
            bt.a().b(str);
        }
    }

    public static void a(JSONObject jSONObject) {
        if (ay.a() && !br.a().b()) {
            bq.a().a(jSONObject);
        }
    }

    public static void b(String str) {
        if (ay.a()) {
            bt.a().a(str);
        }
    }
}

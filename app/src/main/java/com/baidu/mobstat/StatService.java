package com.baidu.mobstat;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.baidu.mobstat.BaiduStatJSInterface;
import com.baidu.mobstat.MtjConfig;
import com.baidu.mobstat.ay;
import com.baidu.mobstat.bp;
import com.bumptech.glide.load.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class StatService {
    public static final int EXCEPTION_LOG = 1;
    public static final int JAVA_EXCEPTION_LOG = 16;
    private static boolean a = false;
    private static boolean b = false;
    private static boolean c;

    public interface OnZidReceiveListener {
        String getZid();
    }

    public interface WearListener {
        boolean onSendLogData(String str);
    }

    private static void a(Context context, WebView webView, WebViewClient webViewClient, WebChromeClient webChromeClient, boolean z) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        if (context == null) {
            bv.c().c("[WARNING] context is null, invalid");
            return;
        }
        if (webView == null) {
            bv.c().c("[WARNING] webview is null, invalid");
            return;
        }
        if (a(context)) {
            return;
        }
        a(webView);
        WebSettings settings = webView.getSettings();
        if (b) {
            settings.setAllowFileAccess(false);
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName(Key.STRING_CHARSET_NAME);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (z) {
            ce ceVar = new ce();
            webView.addJavascriptInterface(ceVar, "WebViewInterface");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new ay.b());
            arrayList.add(new bp.b());
            WebChromeClient customWebChromeViewClient = new BaiduStatJSInterface.CustomWebChromeViewClient(context, webChromeClient, arrayList, ceVar);
            webView.setWebChromeClient(customWebChromeViewClient);
            webView.setTag(-96001, customWebChromeViewClient);
        } else {
            webView.setWebViewClient(new BaiduStatJSInterface.CustomWebViewClient(context, webViewClient, null, null));
        }
        BDStatCore.instance().init(context);
    }

    private static synchronized void a(Context context, String str, ExtraInfo extraInfo) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (CooperService.instance().isCloseTrace()) {
                    return;
                }
                if (a(context)) {
                    return;
                }
                BDStatCore.instance().onPageEnd(context, str, extraInfo);
                return;
            }
        }
        bv.c().c("[WARNING] onPageEnd parameter invalid");
    }

    private static void a(Context context, String str, String str2, int i, ExtraInfo extraInfo, Map<String, String> map) {
        if (!a(context, "onEvent(...)") || TextUtils.isEmpty(str) || CooperService.instance().isCloseTrace()) {
            return;
        }
        boolean zA = cq.a((Class<?>) Application.class, "onCreate");
        if (zA) {
            bv.c().c("[WARNING] onEvent 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
        }
        if (a(context)) {
            return;
        }
        BDStatCore.instance().onEvent(context.getApplicationContext(), str, str2, i, extraInfo, cq.a(map), zA);
    }

    private static void a(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map) {
        if (!a(context, "onEventDuration(...)") || CooperService.instance().isCloseTrace() || TextUtils.isEmpty(str)) {
            return;
        }
        if (j <= 0) {
            bv.c().b("[WARNING] onEventDuration duration must be greater than zero");
            return;
        }
        boolean zA = cq.a((Class<?>) Application.class, "onCreate");
        if (zA) {
            bv.c().c("[WARNING] onEventDuration 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
        }
        if (a(context)) {
            return;
        }
        BDStatCore.instance().onEventDuration(context.getApplicationContext(), str, str2, j, extraInfo, cq.a(map), zA);
    }

    private static void a(Context context, String str, String str2, ExtraInfo extraInfo) {
        a(context, str, str2, 1, extraInfo, (Map<String, String>) null);
    }

    private static void a(Context context, String str, String str2, ExtraInfo extraInfo, Map<String, String> map) {
        if (!a(context, "onEventEnd(...)") || CooperService.instance().isCloseTrace() || TextUtils.isEmpty(str) || a(context)) {
            return;
        }
        BDStatCore.instance().onEventEnd(context.getApplicationContext(), str, str2, extraInfo, cq.a(map));
    }

    private static void a(Context context, boolean z) {
        if (!a(context, "onError(...)") || CooperService.instance().isCloseTrace() || a(context)) {
            return;
        }
        BDStatCore.instance().init(context);
        ExceptionAnalysis.getInstance().openExceptionAnalysis(context.getApplicationContext(), z);
    }

    private static void a(WebView webView) {
        CooperService.instance().isCloseTrace();
    }

    private static boolean a(Context context) {
        String strR = cp.r(context);
        return !TextUtils.isEmpty(strR) && strR.contains("helios");
    }

    private static boolean a(Context context, String str) {
        if (context != null) {
            return true;
        }
        bv.c().b("[WARNING] " + str + ", context is null, invalid");
        return false;
    }

    private static boolean a(Class<?> cls, String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean z = false;
        for (int i = 2; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.getMethodName().equals(str)) {
                try {
                    for (Class<?> cls2 = Class.forName(stackTraceElement.getClassName()); cls2.getSuperclass() != null && cls2.getSuperclass() != cls; cls2 = cls2.getSuperclass()) {
                    }
                    z = true;
                } catch (Exception e) {
                }
            }
        }
        return z;
    }

    public static void autoTrace(Context context) {
        autoTrace(context, true, false);
    }

    public static void autoTrace(Context context, boolean z, boolean z2) {
        if (z && !CooperService.instance().isCloseTrace() && a(context, "autoTrace(...)") && !a(context)) {
            String appKey = CooperService.instance().getAppKey(context);
            if (TextUtils.isEmpty(appKey)) {
                bv.c().c("[WARNING] AppKey is invalid, auto trace will do not take effect");
                return;
            }
            ay.a(appKey);
            ay.a(z2);
            if (!c) {
                setFeedTrack(MtjConfig.FeedTrackStrategy.TRACK_ALL);
            }
            BDStatCore.instance().init(context);
        }
    }

    public static void bindJSInterface(Context context, WebView webView) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        bindJSInterface(context, webView, null);
    }

    public static void bindJSInterface(Context context, WebView webView, WebViewClient webViewClient) {
        a(context, webView, webViewClient, (WebChromeClient) null, false);
    }

    public static void browseMode(boolean z) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        cn.a().c(!z);
    }

    public static void closeTrace() {
        CooperService.instance().closeTrace();
    }

    public static void crashEnableSendLog(boolean z) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        ExceptionAnalysis.getInstance().setEnableSend(z);
    }

    public static void enableAppList(Context context, boolean z) {
        CooperService.instance().setEnableBplus(context, z);
    }

    public static void enableDeviceMac(Context context, boolean z) {
        if (context == null || CooperService.instance().isCloseTrace() || a(context)) {
            return;
        }
        CooperService.instance().enableDeviceMac(context, z);
        BDStatCore.instance().init(context);
    }

    public static void enableListTrack(View view) {
        if (CooperService.instance().isCloseTrace() || view == null) {
            return;
        }
        view.setTag(-97002, true);
    }

    public static String getAppKey(Context context) {
        return CooperService.instance().isCloseTrace() ? "" : PrefOperate.getAppKey(context);
    }

    public static String getSdkVersion() {
        return CooperService.instance().isCloseTrace() ? "" : CooperService.instance().getMTJSDKVersion();
    }

    public static String getTestDeviceId(Context context) {
        return CooperService.instance().isCloseTrace() ? "" : cp.b(context);
    }

    public static void init(Context context, String str, String str2) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        PrefOperate.setAppKey(str);
        PrefOperate.setAppChannel(context, str2, true);
    }

    public static void onErised(Context context, String str, String str2, String str3) {
        if (!CooperService.instance().isCloseTrace() && a(context, "onErised(...)")) {
            if (str == null || "".equals(str)) {
                bv.c().c("[WARNING] AppKey is invalid");
            } else {
                BDStatCore.instance().onErised(context, str, str2, str3);
            }
        }
    }

    public static void onEvent(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static void onEvent(Context context, String str, String str2, int i) {
        a(context, str, str2, i, (ExtraInfo) null, (Map<String, String>) null);
    }

    public static void onEvent(Context context, String str, String str2, int i, Map<String, String> map) {
        a(context, str, str2, i, (ExtraInfo) null, map);
    }

    public static void onEventDuration(Context context, String str, String str2, long j) {
        a(context, str, str2, j, (ExtraInfo) null, (Map<String, String>) null);
    }

    public static void onEventDuration(Context context, String str, String str2, long j, Map<String, String> map) {
        a(context, str, str2, j, (ExtraInfo) null, map);
    }

    public static void onEventEnd(Context context, String str, String str2) {
        a(context, str, str2, (ExtraInfo) null, (Map<String, String>) null);
    }

    public static void onEventEnd(Context context, String str, String str2, Map<String, String> map) {
        a(context.getApplicationContext(), str, str2, (ExtraInfo) null, map);
    }

    public static void onEventStart(Context context, String str, String str2) {
        if (!a(context, "onEventStart(...)") || CooperService.instance().isCloseTrace() || TextUtils.isEmpty(str) || a(context)) {
            return;
        }
        BDStatCore.instance().onEventStart(context.getApplicationContext(), str, str2, false);
    }

    public static synchronized void onPageEnd(Context context, String str) {
        a(context, str, null);
    }

    public static synchronized void onPageStart(Context context, String str) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (CooperService.instance().isCloseTrace()) {
                    return;
                }
                if (a(context)) {
                    return;
                }
                BDStatCore.instance().onPageStart(context, str);
                return;
            }
        }
        bv.c().c("[WARNING] onPageStart parameter invalid");
    }

    public static synchronized void onPause(Activity activity) {
        onPause(activity, null);
    }

    public static synchronized void onPause(Activity activity, ExtraInfo extraInfo) {
        if (a(activity, "onPause(...)")) {
            if (CooperService.instance().isCloseTrace()) {
                return;
            }
            if (!a((Class<?>) Activity.class, "onPause")) {
                bv.c().c("[WARNING] onPause must be called in Activity.onPause");
            } else {
                if (a(activity)) {
                    return;
                }
                BDStatCore.instance().onPause(activity, false, extraInfo);
            }
        }
    }

    public static synchronized void onResume(Activity activity) {
        if (a(activity, "onResume(...)")) {
            if (CooperService.instance().isCloseTrace()) {
                return;
            }
            if (!a((Class<?>) Activity.class, "onResume")) {
                bv.c().c("[WARNING] onResume must be called in Activity.onResume()");
            } else {
                if (a(activity)) {
                    return;
                }
                BDStatCore.instance().onResume(activity, false);
            }
        }
    }

    public static void platformType(int i) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        CooperService.instance().setPlatformType(i);
    }

    public static void recordException(Context context, Throwable th) {
        if (context == null || CooperService.instance().isCloseTrace() || th == null) {
            return;
        }
        ExceptionAnalysis.getInstance().saveCrashInfo(context, th, false);
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        if (context == null || CooperService.instance().isCloseTrace() || a(context)) {
            return;
        }
        PrefOperate.setAppChannel(context, str, z);
        BDStatCore.instance().init(context);
    }

    @Deprecated
    public static void setAppChannel(String str) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        PrefOperate.setAppChannel(str);
    }

    public static void setAppKey(String str) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        PrefOperate.setAppKey(str);
    }

    public static void setAppVersionName(Context context, String str) {
        if (CooperService.instance().isCloseTrace() || TextUtils.isEmpty(str)) {
            return;
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        CooperService.instance().setAppVersionName(context, str);
    }

    public static void setAttributes(View view, Map<String, String> map) {
        if (CooperService.instance().isCloseTrace() || view == null) {
            return;
        }
        view.setTag(-96000, map);
    }

    public static void setAuthorizedState(Context context, boolean z) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        cn.a().b(z);
    }

    public static void setAutoEventProperty(Context context, Map<String, String> map) {
        if (context == null || CooperService.instance().isCloseTrace() || a(context)) {
            return;
        }
        DataCore.instance().setPydProperty(context, cq.a(map), "3", "3");
        BDStatCore.instance().init(context);
    }

    public static void setContentId(View view, String str) {
        if (view == null || CooperService.instance().isCloseTrace()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        view.setTag(-97004, str);
    }

    public static void setContentTitle(View view, String str) {
        if (view == null || CooperService.instance().isCloseTrace()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        view.setTag(-97003, str);
    }

    public static void setCrashExtraInfo(String str) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        ExceptionAnalysis.getInstance().setCrashExtraInfo(str);
    }

    public static void setDebugOn(boolean z) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        bv.c().a(z);
    }

    public static void setEnableBackgroundSendLog(Context context, boolean z) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        BDStatCore.instance().setAutoSendLog(context, z);
    }

    public static void setFeedTrack(MtjConfig.FeedTrackStrategy feedTrackStrategy) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        bp.a(feedTrackStrategy);
        c = true;
    }

    public static void setForTv(Context context, boolean z) {
        if (context == null || CooperService.instance().isCloseTrace() || a(context)) {
            return;
        }
        cj.a().c(context, z);
        BDStatCore.instance().init(context);
    }

    public static synchronized void setGlobalExtraInfo(Context context, ExtraInfo extraInfo) {
        if (context == null) {
            return;
        }
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        if (a(context)) {
            return;
        }
        CooperService.instance().setHeaderExt(context, extraInfo);
        BDStatCore.instance().init(context);
    }

    public static void setListName(View view, String str) {
        if (view == null || CooperService.instance().isCloseTrace()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        view.setTag(-97001, str);
    }

    public static void setLogSenderDelayed(int i) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        LogSender.instance().setLogSenderDelayed(i);
    }

    public static void setOaid(Context context, String str) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        cj.a().n(context, str);
    }

    public static void setOn(Context context, int i) {
        if (!a(context, "setOn(...)") || CooperService.instance().isCloseTrace() || a || a(context)) {
            return;
        }
        a = true;
        if ((i & 1) != 0) {
            a(context, false);
        } else if ((i & 16) != 0) {
            a(context, true);
        }
        BDStatCore.instance().init(context);
    }

    public static void setOnAppBackgroundListener(OnAppBackgroundListener onAppBackgroundListener) {
        if (CooperService.instance().isCloseTrace() || onAppBackgroundListener == null) {
            return;
        }
        cn.a().a(onAppBackgroundListener);
    }

    public static void setPageProperty(Context context, Map<String, String> map) {
        if (context == null || CooperService.instance().isCloseTrace() || a(context)) {
            return;
        }
        DataCore.instance().setPydProperty(context, cq.a(map), "3", PropertyType.PAGE_PROPERTRY);
        BDStatCore.instance().init(context);
    }

    public static synchronized void setPushId(Context context, MtjConfig.PushPlatform pushPlatform, String str) {
        if (context == null) {
            return;
        }
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        if (pushPlatform == null) {
            return;
        }
        if (a(context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 1024) {
            str = str.substring(0, 1024);
        }
        CooperService.instance().setPushId(context, pushPlatform.value(), pushPlatform.showName(), str);
        BDStatCore.instance().init(context);
    }

    @Deprecated
    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i) {
        setSendLogStrategy(context, sendStrategyEnum, i, false);
    }

    @Deprecated
    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (a(context, "setSendLogStrategy(...)") && !CooperService.instance().isCloseTrace()) {
            boolean zA = cq.a((Class<?>) Application.class, "onCreate");
            if (zA) {
                bv.c().c("[WARNING] setSendLogStrategy 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            if (a(context)) {
                return;
            }
            BDStatCore.instance().onSessionStart(context, zA);
            LogSender.instance().setSendLogStrategy(context.getApplicationContext(), sendStrategyEnum, i, z);
        }
    }

    public static void setSessionProperty(Context context, Map<String, String> map) {
        if (context == null || CooperService.instance().isCloseTrace() || a(context)) {
            return;
        }
        DataCore.instance().setPydProperty(context, cq.a(map), "2", "2");
        BDStatCore.instance().init(context);
    }

    public static void setSessionTimeOut(int i) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        BDStatCore.instance().setSessionTimeOut(i);
    }

    public static synchronized void setStartType(boolean z) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        CooperService.instance().setStartType(z);
    }

    public static void setUserId(Context context, String str) {
        if (CooperService.instance().isCloseTrace() || context == null || a(context)) {
            return;
        }
        CooperService.instance().setUserId(context, str);
        HashMap map = new HashMap();
        map.put("uid_", str);
        if (TextUtils.isEmpty(str)) {
            map = null;
        }
        DataCore.instance().setPydProperty(context, cq.a(map), "1", PropertyType.UID_PROPERTRY);
        BDStatCore.instance().init(context);
    }

    public static void setUserProperty(Context context, Map<String, String> map) {
        if (context == null || CooperService.instance().isCloseTrace() || a(context)) {
            return;
        }
        CooperService.instance().setUserProperty(context, cq.a(map));
        DataCore.instance().setPydProperty(context, cq.a(map), "1", "1");
        BDStatCore.instance().init(context);
    }

    public static void setWebViewDisableFileUrls(boolean z) {
        b = z;
    }

    public static void start(Context context) {
        if (TextUtils.isEmpty(CooperService.instance().getAppKey(context))) {
            bv.c().c("[WARNING] AppKey is empty, start will do not take effect");
            return;
        }
        if (a(context, "start(...)") && !CooperService.instance().isCloseTrace()) {
            boolean zA = cq.a((Class<?>) Application.class, "onCreate");
            if (zA) {
                bv.c().c("[WARNING] start 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            if (a(context)) {
                return;
            }
            BDStatCore.instance().onSessionStart(context, zA);
        }
    }

    public static void trackWebView(Context context, WebView webView, WebChromeClient webChromeClient) {
        if (CooperService.instance().isCloseTrace()) {
            return;
        }
        a(context, webView, (WebViewClient) null, webChromeClient, true);
    }
}

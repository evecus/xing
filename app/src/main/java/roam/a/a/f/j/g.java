package roam.a.a.f.j;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.webkit.ProxyConfig;
import com.android.cglib.dx.io.Opcodes;
import com.baidu.android.common.util.HanziToPinyin;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.roam.loader.Loader;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final String[] a = {"10.1.5.1013151", "10.1.5.1013148"};

    public static final class a {
        public Signature[] a;
        public int b;

        public final boolean a() {
            String string;
            Signature[] signatureArr = this.a;
            if (signatureArr == null || signatureArr.length <= 0) {
                return false;
            }
            int i = 0;
            while (true) {
                Signature[] signatureArr2 = this.a;
                if (i >= signatureArr2.length) {
                    return false;
                }
                try {
                    string = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr2[i].toByteArray()))).getPublicKey().toString();
                } catch (Exception e) {
                    roam.a.a.f.a.l.a.c("auth", "GetPublicKeyFromSignEx", e);
                }
                String strTrim = string.indexOf("modulus") != -1 ? string.substring(string.indexOf("modulus") + 8, string.lastIndexOf(",")).trim() : null;
                if (strTrim != null && !TextUtils.equals(strTrim, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")) {
                    roam.a.a.f.a.l.a.b("biz", "PublicKeyUnmatch", strTrim);
                    return true;
                }
                i++;
            }
        }
    }

    public static WebView a(Activity activity, String str, String str2) {
        Context applicationContext = activity.getApplicationContext();
        if (!TextUtils.isEmpty(str2)) {
            CookieSyncManager.createInstance(applicationContext).sync();
            CookieManager.getInstance().setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
        LinearLayout linearLayout = new LinearLayout(applicationContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        activity.setContentView(linearLayout, layoutParams);
        WebView webView = new WebView(applicationContext);
        layoutParams.weight = 1.0f;
        webView.setVisibility(0);
        linearLayout.addView(webView, layoutParams);
        WebSettings settings = webView.getSettings();
        StringBuilder sb = new StringBuilder();
        sb.append(settings.getUserAgentString());
        String str3 = "Android " + Build.VERSION.RELEASE;
        String strG = g();
        String string = applicationContext.getResources().getConfiguration().locale.toString();
        String strJ = j(applicationContext);
        StringBuilder sb2 = new StringBuilder(" (");
        sb2.append(str3);
        sb2.append(";");
        sb2.append(strG);
        sb2.append(";");
        sb.append(roam.a.b.a.a.a.n(sb2, string, ";;", strJ, ")(sdk android)"));
        settings.setUserAgentString(sb.toString());
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        webView.setVerticalScrollbarOverlay(true);
        webView.setDownloadListener(new h(applicationContext));
        try {
            Method method = webView.getSettings().getClass().getMethod("setDomStorageEnabled", Boolean.TYPE);
            if (method != null) {
                method.invoke(webView.getSettings(), Boolean.TRUE);
            }
        } catch (Exception e) {
        }
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable th) {
            try {
                Method method2 = webView.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                if (method2 != null) {
                    method2.invoke(webView, "searchBoxJavaBridge_");
                    method2.invoke(webView, "accessibility");
                    method2.invoke(webView, "accessibilityTraversal");
                }
            } catch (Throwable th2) {
            }
        }
        webView.getSettings().setCacheMode(2);
        webView.loadUrl(str);
        return webView;
    }

    public static a b(Context context) {
        boolean zE;
        PackageInfo packageInfoF;
        try {
            packageInfoF = context.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", Opcodes.AND_LONG_2ADDR);
            if (!e(packageInfoF)) {
                try {
                    packageInfoF = f(context, "com.eg.android.AlipayGphone");
                } catch (Throwable th) {
                    roam.a.a.f.a.l.a.c("auth", "GetInstalledPackagesEx", th);
                }
            }
        } catch (Throwable th2) {
            try {
                roam.a.a.f.a.l.a.c("auth", "GetPackageInfoEx", th2);
                if (zE) {
                    packageInfoF = null;
                } else {
                    try {
                    } catch (Throwable th3) {
                        packageInfoF = null;
                    }
                }
            } finally {
                if (!e(null)) {
                    try {
                        f(context, "com.eg.android.AlipayGphone");
                    } catch (Throwable th32) {
                        roam.a.a.f.a.l.a.c("auth", "GetInstalledPackagesEx", th32);
                    }
                }
            }
        }
        if (!e(packageInfoF) || packageInfoF == null) {
            return null;
        }
        a aVar = new a();
        aVar.a = packageInfoF.signatures;
        aVar.b = packageInfoF.versionCode;
        return aVar;
    }

    public static String c(String str, String str2, String str3) {
        try {
            int length = str.length() + str3.indexOf(str);
            if (length <= str.length()) {
                return "";
            }
            int iIndexOf = !TextUtils.isEmpty(str2) ? str3.indexOf(str2, length) : 0;
            return iIndexOf <= 0 ? str3.substring(length) : str3.substring(length, iIndexOf);
        } catch (Throwable th) {
            return "";
        }
    }

    public static Map<String, String> d(String str) {
        HashMap map = new HashMap();
        for (String str2 : str.split("&")) {
            int iIndexOf = str2.indexOf("=", 1);
            map.put(str2.substring(0, iIndexOf), URLDecoder.decode(str2.substring(iIndexOf + 1)));
        }
        return map;
    }

    public static boolean e(PackageInfo packageInfo) {
        String str;
        boolean z = false;
        if (packageInfo == null) {
            str = "info == null";
        } else {
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null) {
                str = "info.signatures == null";
            } else if (signatureArr.length <= 0) {
                str = "info.signatures.length <= 0";
            } else {
                z = true;
                str = "";
            }
        }
        if (!z) {
            roam.a.a.f.a.l.a.b("auth", "NotIncludeSignatures", str);
        }
        return z;
    }

    public static PackageInfo f(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(Opcodes.AND_LONG_2ADDR)) {
            if (packageInfo.packageName.equals(str)) {
                return packageInfo;
            }
        }
        return null;
    }

    public static String g() {
        String strSubstring;
        Matcher matcher;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(line);
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        } catch (IOException e) {
        }
        if (matcher.matches() && matcher.groupCount() >= 4) {
            strSubstring = matcher.group(1) + "\n" + matcher.group(2) + HanziToPinyin.Token.SEPARATOR + matcher.group(3) + "\n" + matcher.group(4);
        } else {
            strSubstring = "Unavailable";
        }
        int iIndexOf = strSubstring.indexOf("-");
        if (iIndexOf != -1) {
            strSubstring = strSubstring.substring(0, iIndexOf);
        }
        int iIndexOf2 = strSubstring.indexOf("\n");
        if (iIndexOf2 != -1) {
            strSubstring = strSubstring.substring(0, iIndexOf2);
        }
        return "Linux " + strSubstring;
    }

    public static boolean h(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
            if (packageInfo != null) {
                if (packageInfo.versionCode > 73) {
                    return true;
                }
            }
        } catch (Throwable th) {
            roam.a.a.f.a.l.a.c("biz", "CheckClientExistEx", th);
        }
        return false;
    }

    public static boolean i(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
            if (packageInfo != null) {
                if (packageInfo.versionCode < 99) {
                    return true;
                }
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public static String j(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels + ProxyConfig.MATCH_ALL_SCHEMES + displayMetrics.heightPixels;
    }

    public static String k(Context context) {
        String strSubstring = "";
        try {
            String str = "";
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals("com.eg.android.AlipayGphone")) {
                    str = str + "#M";
                } else if (runningAppProcessInfo.processName.startsWith("com.eg.android.AlipayGphone:")) {
                    str = str + "#" + runningAppProcessInfo.processName.replace("com.eg.android.AlipayGphone:", "");
                }
            }
            strSubstring = str;
        } catch (Throwable th) {
        }
        if (strSubstring.length() > 0) {
            strSubstring = strSubstring.substring(1);
        }
        return strSubstring.length() == 0 ? "N" : strSubstring;
    }

    public static String l(Context context) {
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < installedPackages.size(); i++) {
                PackageInfo packageInfo = installedPackages.get(i);
                int i2 = packageInfo.applicationInfo.flags;
                if ((i2 & 1) == 0 && (i2 & 128) == 0) {
                    if (packageInfo.packageName.equals("com.eg.android.AlipayGphone")) {
                        sb.append(packageInfo.packageName);
                        sb.append(packageInfo.versionCode);
                    } else if (!packageInfo.packageName.contains(Loader.THEME_DIR) && !packageInfo.packageName.startsWith("com.google.") && !packageInfo.packageName.startsWith("com.android.")) {
                        sb.append(packageInfo.packageName);
                    }
                    sb.append("-");
                }
            }
            return sb.toString();
        } catch (Throwable th) {
            roam.a.a.f.a.l.a.c("biz", "GetInstalledAppEx", th);
            return "";
        }
    }
}

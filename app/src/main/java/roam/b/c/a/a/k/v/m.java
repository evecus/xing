package roam.b.c.a.a.k.v;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.roamexplore.MainActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.roam.Application;
import org.roam.R;
import org.roam.config.ViewConfig;
import org.roam.config.WebControlBean;
import org.roam.ui.fragment.IFusionPage;
import org.roam.ui.fragment.WebInterface;
import org.roam.ui.fragment.WebViewMenuSupport;
import org.roam.webcore.AgentWeb;
import org.roam.webcore.FusionCoreWebView;
import org.roam.webcore.IUrlLoader;
import org.roam.webcore.WebIndicator;
import roam.b.c.a.a.k.v.m;
import roam.b.c.a.a.m.i1;
import roam.b.c.a.a.m.j1;
import roam.b.c.a.a.m.k1;
import roam.b.c.a.a.m.l0;
import roam.b.c.a.a.m.n0;
import roam.b.c.a.a.m.o0;
import roam.b.c.a.a.m.q1;
import roam.b.c.a.a.m.t0;
import roam.b.c.a.a.m.y1;

/* JADX INFO: loaded from: classes.dex */
public class m extends k implements IFusionPage {
    public static final String o = m.class.getSimpleName();
    public Application b;
    public AgentWeb c;
    public String d;
    public l f;
    public ViewPager g;
    public WebInterface h;
    public WebViewMenuSupport.Interface i;
    public ViewConfig.WebViewBean j;
    public WebViewMenuSupport k;
    public roam.a.e.a.i e = new roam.a.e.a.i();
    public k1 l = new k1(this) { // from class: roam.b.c.a.a.k.v.e
        public final m a;

        {
            this.a = this;
        }

        @Override // roam.b.c.a.a.m.k1
        public final boolean a(String str, String[] strArr, String str2) {
            m mVar = this.a;
            Objects.requireNonNull(mVar);
            String str3 = m.o;
            StringBuilder sbE = roam.a.b.a.a.a.e("mUrl:", str, "  permission:");
            sbE.append(mVar.e.f(strArr));
            sbE.append(" action:");
            sbE.append(str2);
            Log.i(str3, sbE.toString());
            return false;
        }
    };
    public String m = "";
    public y1 n = new c(this);

    public class a extends roam.b.c.a.a.m.a2.a {
        public final m c;

        public a(m mVar) {
            this.c = mVar;
        }

        @Override // roam.b.c.a.a.m.n1, android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            ((MainActivity) this.c.getActivity()).setTitle(str);
            WebInterface webInterface = this.c.h;
            if (webInterface != null) {
                webInterface.onReceivedTitle(webView, str);
            }
        }
    }

    public class b extends roam.b.c.a.a.m.d {
        public final m d;

        public b(m mVar) {
            this.d = mVar;
        }

        @Override // roam.b.c.a.a.m.d, roam.b.c.a.a.m.q1
        public q1 b(final WebView webView, DownloadListener downloadListener) {
            webView.setDownloadListener(new DownloadListener(this, webView) { // from class: roam.b.c.a.a.k.v.a
                public final m.b a;
                public final WebView b;

                {
                    this.a = this;
                    this.b = webView;
                }

                @Override // android.webkit.DownloadListener
                public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                    m.b bVar = this.a;
                    WebView webView2 = this.b;
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) bVar.d.requireActivity().findViewById(R.id.r);
                    String strGuessFileName = URLUtil.guessFileName(str, null, null);
                    m mVar = bVar.d;
                    Application application = mVar.b;
                    Snackbar action = Snackbar.make(coordinatorLayout, mVar.requireActivity().getString(R.string.r, new Object[]{strGuessFileName}), -2).setAnimationMode(1).setAction(R.string.r, new n(bVar, webView2, str, str2, str3, str4, j));
                    roam.b.c.a.a.m.q.r(application, action);
                    action.show();
                }
            });
            return this;
        }

        @Override // roam.b.c.a.a.m.d
        public void d(AgentWeb agentWeb) {
        }

        @Override // roam.b.c.a.a.m.d
        public void e(WebView webView) {
            super.e(webView);
            this.a.setLoadWithOverviewMode(this.d.j.isPcMode());
            this.a.setUseWideViewPort(this.d.j.isPcMode());
            this.a.setBuiltInZoomControls(true);
            this.a.setDisplayZoomControls(false);
            this.a.setJavaScriptEnabled(this.d.j.isJavaScriptEnabled());
        }
    }

    public class c extends y1 {
        public HashMap<String, Long> c = new HashMap<>();
        public final m d;

        public c(m mVar) {
            this.d = mVar;
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            super.onLoadResource(webView, str);
            WebInterface webInterface = this.d.h;
            if (webInterface != null) {
                webInterface.onLoadResource(webView, str);
            }
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            roam.b.c.a.a.k.r uiColorChanger;
            Integer numA;
            super.onPageFinished(webView, str);
            WebInterface webInterface = this.d.h;
            if (webInterface != null) {
                webInterface.onPageFinished(webView, str);
            }
            ViewPager viewPager = this.d.g;
            if ((viewPager != null && viewPager.getAdapter() != null && ((roam.b.c.a.a.k.s.c) viewPager.getAdapter()).a.indexOf(this.d) != viewPager.getCurrentItem()) || (uiColorChanger = this.d.b.getUiManager().getUiColorChanger()) == null || (numA = roam.b.c.a.a.k.r.a(webView)) == null) {
                return;
            }
            int iIntValue = numA.intValue();
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            for (View view : uiColorChanger.a) {
                ObjectAnimator objectAnimatorOfArgb = ObjectAnimator.ofArgb(view, "backgroundColor", roam.b.c.a.a.k.r.a(view).intValue(), iIntValue);
                objectAnimatorOfArgb.setEvaluator(uiColorChanger.b);
                arrayList.add(objectAnimatorOfArgb);
            }
            animatorSet.setDuration(uiColorChanger.c.intValue());
            animatorSet.playTogether(arrayList);
            animatorSet.addListener(new roam.b.c.a.a.k.q(uiColorChanger, iIntValue));
            animatorSet.start();
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            WebInterface webInterface = this.d.h;
            if (webInterface != null) {
                webInterface.onPageStarted(webView, str, bitmap);
            }
            String str2 = m.o;
            StringBuilder sbE = roam.a.b.a.a.a.e("mUrl:", str, " onPageStarted  target:");
            sbE.append(this.d.getUrl());
            Log.i(str2, sbE.toString());
            this.c.put(str, Long.valueOf(System.currentTimeMillis()));
            str.equals(this.d.getUrl());
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            WebInterface webInterface = this.d.h;
            if (webInterface == null || !webInterface.onReceivedSslError(webView, sslErrorHandler, sslError)) {
                new MaterialAlertDialogBuilder(this.d.requireActivity()).setTitle((CharSequence) "SSL Error").setMessage(sslError.getPrimaryError()).setPositiveButton((CharSequence) "ignore", new DialogInterface.OnClickListener(sslErrorHandler) { // from class: roam.b.c.a.a.k.v.c
                    public final SslErrorHandler a;

                    {
                        this.a = sslErrorHandler;
                    }

                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.a.proceed();
                    }
                }).setNegativeButton((CharSequence) "stop", new DialogInterface.OnClickListener(sslErrorHandler) { // from class: roam.b.c.a.a.k.v.b
                    public final SslErrorHandler a;

                    {
                        this.a = sslErrorHandler;
                    }

                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.a.cancel();
                    }
                }).show();
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            if (renderProcessGoneDetail.didCrash()) {
                Log.e("MY_APP_TAG", "The WebView rendering process crashed!");
                return false;
            }
            Log.e("MY_APP_TAG", "System killed the WebView rendering process to reclaim memory. Recreating...");
            if (webView != null) {
                ((o0) this.d.c.getWebLifeCycle()).a();
            }
            return true;
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            WebInterface webInterface = this.d.h;
            WebResourceResponse webResourceResponseShouldInterceptRequest = webInterface != null ? webInterface.shouldInterceptRequest(webView, webResourceRequest) : null;
            return webResourceResponseShouldInterceptRequest == null ? super.shouldInterceptRequest(webView, webResourceRequest) : webResourceResponseShouldInterceptRequest;
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        @Override // roam.b.c.a.a.m.z1, android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.startsWith("intent://") && str.contains("com.youku.phone")) {
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    public m() {
    }

    public m(Application application, String str, l lVar, ViewConfig.WebViewBean webViewBean) {
        this.f = lVar;
        this.d = str;
        this.b = application;
        this.j = webViewBean;
    }

    public l0.c getOpenOtherPageWays(int i) {
        return i != 0 ? i != 1 ? l0.c.DISALLOW : l0.c.DERECT : l0.c.ASK;
    }

    public t0 getSettings() {
        b bVar = new b(this);
        String userAgent = this.j.getUserAgent();
        if (userAgent != null) {
            bVar.b = userAgent;
        }
        return bVar;
    }

    public String getUrl() {
        String str = this.d;
        return TextUtils.isEmpty(str) ? "http://www.fusionapp.net/" : str;
    }

    public IUrlLoader getUrlLoader() {
        return this.c.getUrlLoader();
    }

    public ViewPager getViewPager() {
        return this.g;
    }

    public WebIndicator getWebIndicator() {
        return this.c.getWebIndicator();
    }

    public WebView getWebView() {
        return ((n0) this.c.getWebCreator()).l;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.r, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        AgentWeb agentWeb = this.c;
        if (agentWeb != null) {
            ((o0) agentWeb.getWebLifeCycle()).a();
        }
        super.onDestroyView();
    }

    public boolean onFragmentKeyDown(int i, KeyEvent keyEvent) {
        AgentWeb agentWeb = this.c;
        if (agentWeb == null) {
            return false;
        }
        return agentWeb.handleKeyEvent(i, keyEvent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        o0 o0Var;
        WebView webView;
        AgentWeb agentWeb = this.c;
        if (agentWeb != null && (webView = (o0Var = (o0) agentWeb.getWebLifeCycle()).a) != null) {
            webView.onPause();
            o0Var.a.pauseTimers();
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        o0 o0Var;
        WebView webView;
        AgentWeb agentWeb = this.c;
        if (agentWeb != null && (webView = (o0Var = (o0) agentWeb.getWebLifeCycle()).a) != null) {
            webView.onResume();
            o0Var.a.resumeTimers();
        }
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        String string;
        super.onViewCreated(view, bundle);
        if (isAdded() && !getUrl().equals("about:none")) {
            final FusionCoreWebView i1Var = new i1(getActivity());
            i1Var.addJavascriptInterface(new FusionCoreWebView.LuaJavaScriptInterface((roam.b.c.a.a.h) this.b.getLuaSupport()), "androlua");
            i1Var.setOnUrlLoadListener(new j1(this, i1Var) { // from class: roam.b.c.a.a.k.v.d
                public final m a;
                public final FusionCoreWebView b;

                {
                    this.a = this;
                    this.b = i1Var;
                }

                public final boolean a(String str) {
                    m mVar = this.a;
                    FusionCoreWebView fusionCoreWebView = this.b;
                    WebInterface webInterface = mVar.h;
                    if (webInterface != null) {
                        return webInterface.onUrlLoad(fusionCoreWebView, str);
                    }
                    if (!str.equals("about:empty")) {
                        return false;
                    }
                    fusionCoreWebView.stopLoading();
                    fusionCoreWebView.goBack();
                    return true;
                }
            });
            l lVar = this.f;
            if (lVar != null) {
                List<WebControlBean> list = lVar.c;
                if (list != null && list.size() > 0) {
                    for (WebControlBean webControlBean : list) {
                        String removes = webControlBean.getRemoves();
                        String str = (TextUtils.isEmpty(removes) || removes.trim().equals("")) ? null : String.format("(function () {\n  function _____blockElements() {\n    try {\n      const eleCollectionStr = \"%s\";\n      for (var selector of eleCollectionStr.split(\",\")) {\n        if (selector == \"\")\n          break;\n        for (var ele of document.querySelectorAll(selector)) {\n          if (ele === undefined)\n            break;\n          ele.remove()\n        }\n      }\n    } catch (_) { console.error(\"Fatal: 元素屏蔽脚本出现问题 (blockElementFunc):\", _) }\n  }\n  try {\n    window.onload = _____blockElements();\n    _____blockElements();\n    var MutationObserver = window.MutationObserver || window.WebKitMutationObserver;\n    var observer = new MutationObserver(function (records, instance) {\n      _____blockElements();\n    });\n    window.____isInject === undefined && observer.observe(document, {\n      childList: true,\n      subtree: true,\n      attributes: false,\n      attributeOldValue: false,\n      characterData: false\n    });\n    window.____isInject = 0;\n  } catch (_) { console.error(\"Fatal: 元素屏蔽脚本出现问题:\", _) }\n})()", removes);
                        if (str != null) {
                            i1Var.b(webControlBean.getHost(), str);
                        }
                        String script = webControlBean.getScript();
                        if (!TextUtils.isEmpty(script) && !script.trim().equals("")) {
                            String host = webControlBean.getHost();
                            StringBuilder sbO = roam.a.b.a.a.a.o("\n");
                            sbO.append(webControlBean.getScript());
                            i1Var.b(host, sbO.toString());
                        }
                    }
                }
                i1Var.setDarkMode(this.f.b);
                AgentWeb.b bVarWith = AgentWeb.with(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                bVarWith.b = (LinearLayout) view;
                bVarWith.d = layoutParams;
                bVarWith.c = -1;
                bVarWith.g = this.f.a;
                bVarWith.i = 3;
                bVarWith.j = i1Var;
                b bVar = new b(this);
                String userAgent = this.j.getUserAgent();
                if (userAgent != null) {
                    bVar.b = userAgent;
                }
                bVarWith.h = bVar;
                bVarWith.e = this.n;
                bVarWith.f = new a(this);
                bVarWith.k = this.l;
                bVarWith.l = new roam.b.c.a.a.m.a2.b(getActivity());
                bVarWith.o = R.layout.r;
                bVarWith.p = -1;
                int openOtherPageWays = this.j.getOpenOtherPageWays();
                bVarWith.m = openOtherPageWays != 0 ? openOtherPageWays != 1 ? l0.c.DISALLOW : l0.c.DERECT : l0.c.ASK;
                bVarWith.n = true;
                if (bVarWith.q == 1) {
                    Objects.requireNonNull(bVarWith.b, "ViewGroup is null,Please check your parameters .");
                }
                AgentWeb agentWeb = new AgentWeb(bVarWith, (AgentWeb.a) null);
                AgentWeb.access$3100(agentWeb);
                AgentWeb agentWebAccess$3200 = AgentWeb.access$3200(agentWeb, getUrl());
                this.c = agentWebAccess$3200;
                ((n0) agentWebAccess$3200.getWebCreator()).l.setOverScrollMode(2);
                if (this.j.isPcMode()) {
                    i1Var.setScrollBarStyle(33554432);
                    i1Var.setScrollbarFadingEnabled(false);
                    i1Var.c("if(document&&!document.getElementById('var_inject')){var meta=document.createElement('meta');meta.id='var_inject';meta.setAttribute('name','viewport');meta.setAttribute('content','minimum-scale=0.1, initial-scale=0.1, maximum-scale=10, user-scalable=yes');var o=document.getElementsByTagName('head');if(o.length>0&&o[0].appendChild(meta)){document.body.style.zoom=1}}");
                }
                WebViewMenuSupport webViewMenuSupport = new WebViewMenuSupport(this, i1Var);
                this.k = webViewMenuSupport;
                i1Var.setOnTouchListener(webViewMenuSupport.t);
                i1Var.setOnLongClickListener(this.k.s);
                WebViewMenuSupport.Interface r7 = this.i;
                if (r7 != null) {
                    this.k.g = r7;
                }
                if (this.b.isDevMode()) {
                    if (TextUtils.isEmpty(this.m)) {
                        try {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getContext().getAssets().open("preloadjs/vconsole.js")));
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                } else {
                                    sb.append(line);
                                }
                            }
                            string = sb.toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                            string = "(function() {})";
                        }
                    } else {
                        string = this.m;
                    }
                    String str2 = String.format("__injectFlag_%1$s__", "vconsolejs");
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("try{(function(){if(window.");
                    sb2.append(str2);
                    sb2.append("){console.log('");
                    sb2.append(str2);
                    sb2.append(" has been injected');return;}window.");
                    i1Var.c(roam.a.b.a.a.a.n(sb2, str2, "=true;", string, "}())}catch(e){console.warn(e)}"));
                }
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }
    }

    public void setMenuInterface(WebViewMenuSupport.Interface r2) {
        WebViewMenuSupport webViewMenuSupport = this.k;
        if (webViewMenuSupport == null) {
            this.i = r2;
        } else {
            webViewMenuSupport.g = r2;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.g = viewPager;
    }

    public void setWebInterface(WebInterface webInterface) {
        this.h = webInterface;
    }

    public void toCleanWebCache() {
        AgentWeb agentWeb = this.c;
        if (agentWeb != null) {
            agentWeb.clearWebCache();
        }
    }
}

package roam.b.c.a.a.m;

import android.webkit.DownloadListener;
import android.webkit.WebView;
import org.roam.webcore.AgentWeb;

/* JADX INFO: loaded from: classes.dex */
public class l extends d {
    public AgentWeb d;

    @Override // roam.b.c.a.a.m.d, roam.b.c.a.a.m.q1
    public q1 b(WebView webView, DownloadListener downloadListener) {
        webView.setDownloadListener(new x(this.d.getActivity(), webView, this.d.getPermissionInterceptor()));
        return this;
    }

    @Override // roam.b.c.a.a.m.d
    public void d(AgentWeb agentWeb) {
        this.d = agentWeb;
    }
}

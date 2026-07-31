package roam.b.c.a.a.m;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import java.lang.ref.WeakReference;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.roam.webcore.AgentWeb;

/* JADX INFO: loaded from: classes.dex */
public class j {
    public WeakReference<AgentWeb> a;
    public WeakReference<Activity> b;
    public String c = getClass().getSimpleName();

    public class a implements Handler.Callback {
        public final j a;

        public a(j jVar) {
            this.a = jVar;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (this.a.a.get() == null) {
                return true;
            }
            z0 jsAccessEntrace = this.a.a.get().getJsAccessEntrace();
            Object obj = message.obj;
            String str = obj instanceof String ? (String) obj : null;
            p pVar = (p) jsAccessEntrace;
            Objects.requireNonNull(pVar);
            StringBuilder sb = new StringBuilder();
            sb.append("javascript:uploadFileResult");
            sb.append("(");
            StringBuilder sb2 = new StringBuilder();
            String str2 = new String[]{str}[0];
            String str3 = q.a;
            if (TextUtils.isEmpty(str2)) {
                sb2.append("\"");
                sb2.append(str2);
                sb2.append("\"");
            } else {
                try {
                    if (str2.startsWith("[")) {
                        new JSONArray(str2);
                    } else {
                        new JSONObject(str2);
                    }
                    sb2.append(str2);
                } catch (JSONException e) {
                    sb2.append("\"");
                    sb2.append(str2);
                    sb2.append("\"");
                }
            }
            sb.append(sb2.toString());
            sb.append(")");
            pVar.a(sb.toString(), null);
            return true;
        }
    }

    public j(AgentWeb agentWeb, Activity activity) {
        this.a = null;
        this.b = null;
        this.a = new WeakReference<>(agentWeb);
        this.b = new WeakReference<>(activity);
    }

    @JavascriptInterface
    public void uploadFile() {
        uploadFile("*/*");
    }

    @JavascriptInterface
    public void uploadFile(String str) {
        StringBuilder sbD = roam.a.b.a.a.a.d(str, "  ");
        sbD.append(this.b.get());
        sbD.append("  ");
        sbD.append(this.a.get());
        sbD.toString();
        String str2 = i.a;
        if (this.b.get() == null || this.a.get() == null) {
            return;
        }
        q.p(this.b.get(), ((n0) this.a.get().getWebCreator()).l, null, null, this.a.get().getPermissionInterceptor(), null, str, new a(this));
    }
}

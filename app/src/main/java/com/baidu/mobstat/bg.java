package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.WebView;
import com.baidu.mobstat.bd;
import com.baidu.mobstat.be;
import com.bumptech.glide.load.Key;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bg {
    private static final bg B = new bg();
    private Context a;
    private be b;
    private bd c;
    private Activity d;
    private Handler f;
    private HandlerThread g;
    private Handler h;
    private HandlerThread i;
    private volatile boolean j;
    private volatile boolean k;
    private volatile boolean l;
    private volatile boolean m;
    private volatile boolean n;
    private volatile boolean o;
    private volatile String p;
    private volatile String q;
    private volatile String r;
    private long s;
    private long t;
    private long u;
    private String v;
    private boolean w;
    private String x;
    private JSONObject y = new JSONObject();
    private JSONObject z = new JSONObject();
    private by A = by.a();
    private Handler C = new Handler(Looper.getMainLooper()) { // from class: com.baidu.mobstat.bg.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 32:
                    bg.this.b();
                    break;
                case 33:
                    bg.this.c();
                    break;
                case 34:
                    bg.this.h();
                    break;
            }
        }
    };
    private be.a D = new be.a() { // from class: com.baidu.mobstat.bg.2
        @Override // com.baidu.mobstat.be.a
        public void a() {
            if (bw.c().b()) {
                bw.c().a("onGesture");
            }
            bg.this.i();
        }
    };
    private boolean E = true;
    private JSONArray F = new JSONArray();
    private Object G = new Object();
    private bh e = new bh();

    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 21:
                    bg.this.l();
                    break;
                case 22:
                    bg.this.m();
                    break;
                case 23:
                    bg.this.n();
                    break;
                case 24:
                    Bundle data = message.getData();
                    if (data != null) {
                        bg.this.b(data.getString("autoconfig.key"));
                    }
                    break;
            }
        }
    }

    class b implements bd.a {
        private b() {
        }

        @Override // com.baidu.mobstat.bd.a
        public void a() {
            bg.this.j();
        }

        @Override // com.baidu.mobstat.bd.a
        public void a(String str) {
            Message messageObtainMessage = bg.this.f.obtainMessage(24);
            Bundle bundle = new Bundle();
            bundle.putString("autoconfig.key", str);
            messageObtainMessage.setData(bundle);
            bg.this.f.sendMessage(messageObtainMessage);
        }

        @Override // com.baidu.mobstat.bd.a
        public void a(boolean z) {
            bg.this.b(z);
        }

        @Override // com.baidu.mobstat.bd.a
        public void b() {
            bg.this.k();
        }
    }

    class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    bg.this.o();
                    break;
                case 2:
                    bg.this.s();
                    break;
            }
        }
    }

    private bg() {
        HandlerThread handlerThread = new HandlerThread("crawlerThread");
        this.i = handlerThread;
        handlerThread.start();
        this.h = new c(this.i.getLooper());
        HandlerThread handlerThread2 = new HandlerThread("downloadThread");
        this.g = handlerThread2;
        handlerThread2.start();
        this.f = new a(this.g.getLooper());
    }

    public static bg a() {
        return B;
    }

    private String a(Context context) {
        ArrayList<Pair> arrayList = new ArrayList();
        arrayList.add(new Pair("appKey", "" + this.v));
        arrayList.add(new Pair("appVersion", cp.g(context)));
        arrayList.add(new Pair("appName", cp.h(context)));
        arrayList.add(new Pair("packageName", context.getPackageName()));
        arrayList.add(new Pair("sdkVersion", StatService.getSdkVersion()));
        arrayList.add(new Pair("deviceName", cp.k(context)));
        arrayList.add(new Pair("platform", "Android"));
        arrayList.add(new Pair("model", CooperService.instance().getPhoneModel()));
        CooperService.instance().getCUID(context, false);
        arrayList.add(new Pair("cuid", ""));
        arrayList.add(new Pair("auto", "1"));
        if (!TextUtils.isEmpty(this.x)) {
            arrayList.add(new Pair("token", this.x));
        }
        StringBuilder sb = new StringBuilder();
        for (Pair pair : arrayList) {
            try {
                String strEncode = URLEncoder.encode(((String) pair.first).toString(), Key.STRING_CHARSET_NAME);
                String strEncode2 = URLEncoder.encode(((String) pair.second).toString(), Key.STRING_CHARSET_NAME);
                if (TextUtils.isEmpty(sb.toString())) {
                    sb.append(strEncode + "=" + strEncode2);
                } else {
                    sb.append("&" + strEncode + "=" + strEncode2);
                }
            } catch (Exception e) {
            }
        }
        String str = "wss://mtjsocket.baidu.com/app?" + sb.toString();
        this.x = null;
        return str;
    }

    private JSONObject a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            return jSONObject2;
        }
        try {
            jSONObject2.put("type", "upload");
            jSONObject2.put("data", jSONObject);
        } catch (Exception e) {
        }
        return jSONObject2;
    }

    private JSONObject a(JSONObject jSONObject, String str) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) jSONObject.get("meta");
            int i = jSONObject2.getInt("matchAll");
            JSONArray jSONArray = (JSONArray) jSONObject.get("data");
            JSONArray jSONArray2 = new JSONArray();
            boolean z = false;
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                if (str.equals((String) jSONObject3.get("page"))) {
                    jSONArray2.put(jSONObject3);
                }
            }
            if (i != 0) {
                z = true;
            } else if (i == 0 && jSONArray2.length() != 0) {
                z = true;
            }
            if (!z) {
                return null;
            }
            JSONObject jSONObject4 = new JSONObject();
            try {
                jSONObject4.put("meta", jSONObject2);
                jSONObject4.put("data", jSONArray2);
                return jSONObject4;
            } catch (Exception e) {
                return jSONObject4;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    private void b(Activity activity, boolean z) {
        if ((activity instanceof IIgnoreAutoTrace) || CooperService.instance().isCloseTrace()) {
            return;
        }
        if (z) {
            BDStatCore.instance().onResume(activity, true);
        } else {
            BDStatCore.instance().onPause(activity, true, null);
        }
    }

    private void b(WebView webView, String str, ce ceVar) {
        if (ceVar == null) {
            return;
        }
        ceVar.a(webView, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (this.a == null || TextUtils.isEmpty(str)) {
            return;
        }
        cj.a().c(this.a, System.currentTimeMillis());
        ch.a(this.a, ba.c, str, false);
        this.C.sendMessage(this.C.obtainMessage(34));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        this.j = false;
        bh.b();
        this.h.removeMessages(2);
        this.C.sendMessage(this.C.obtainMessage(33));
    }

    private void c(WebView webView, String str, ce ceVar) {
        if (ceVar == null) {
            return;
        }
        ceVar.a(this.d, webView, str, a(this.y, r()), true);
    }

    private void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = (JSONObject) jSONObject.get("meta");
            JSONArray jSONArray = (JSONArray) jSONObject.get("data");
            JSONArray jSONArray2 = new JSONArray();
            JSONArray jSONArray3 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = (JSONObject) jSONArray.get(i);
                String str2 = (String) jSONObject3.opt("webLayout");
                String str3 = (String) jSONObject3.opt("url");
                if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
                    jSONArray3.put(jSONObject3);
                } else {
                    jSONArray2.put(jSONObject3);
                }
            }
            this.y.put("meta", jSONObject2);
            this.y.put("data", jSONArray2);
            this.z.put("meta", jSONObject2);
            this.z.put("data", jSONArray3);
        } catch (Exception e) {
        }
    }

    private void d(Activity activity) {
        Intent intent;
        if (activity == null || (intent = activity.getIntent()) == null) {
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra(MtjConfig.BAIDU_MTJ_PUSH_CALL, false);
        String stringExtra = intent.getStringExtra(MtjConfig.BAIDU_MTJ_PUSH_MSG);
        if (this.E) {
            LaunchInfo launchInfo = new LaunchInfo();
            if (booleanExtra) {
                launchInfo.setPushInfo(cc.e(activity), stringExtra);
            }
            String strG = cc.g(activity);
            if (!TextUtils.isEmpty(strG)) {
                launchInfo.setRefererPkgName(strG);
            }
            BDStatCore.instance().autoTrackLaunchInfo(this.a, launchInfo, true);
        } else {
            LaunchInfo launchInfo2 = new LaunchInfo();
            if (booleanExtra) {
                launchInfo2.setPushInfo(cc.e(activity), stringExtra);
            }
            String strG2 = cc.g(activity);
            if (!TextUtils.isEmpty(strG2)) {
                launchInfo2.setRefererPkgName(strG2);
            }
            BDStatCore.instance().autoTrackLaunchInfo(this.a, launchInfo2, false);
        }
        this.E = false;
    }

    private void e(Activity activity) {
        if (bw.c().b()) {
            bw.c().a("installConnectionTracker");
        }
        be beVar = new be(this.D);
        this.b = beVar;
        beVar.a(activity);
    }

    private void f() {
        if (bw.c().b()) {
            bw.c().a("uninstallConnectionTracker");
        }
        be beVar = this.b;
        if (beVar != null) {
            beVar.b();
            this.b = null;
        }
    }

    private void g() {
        if (p() && this.j) {
            b();
        } else {
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.r = ch.a(this.a, ba.c);
        c(this.r);
        bp.b(this.r);
        bl.a(this.r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (p()) {
            return;
        }
        bv.c().a("autotrace: gesture success");
        a(0);
        if (!cp.p(this.a)) {
            bv.c().a("autotrace: network invalid, failed to connect to circle server");
        } else {
            this.h.sendMessage(this.h.obtainMessage(1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.k) {
            return;
        }
        this.f.sendMessage(this.f.obtainMessage(21));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.j = true;
        if (p() && this.j) {
            this.C.sendMessage(this.C.obtainMessage(32));
            this.h.sendMessage(this.h.obtainMessage(2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.k) {
            return;
        }
        boolean zA = bz.a(this.a, this.v, 0, true);
        this.k = true;
        if (zA) {
            this.p = ch.a(this.a, ba.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (!this.l && cp.p(this.a)) {
            boolean zA = bz.a(this.a, this.v, 1, true);
            this.l = true;
            if (zA) {
                this.q = ch.a(this.a, ba.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (!this.m && cp.p(this.a)) {
            boolean zA = bz.a(this.a, this.v, 2, true);
            this.m = true;
            if (zA) {
                this.C.sendMessage(this.C.obtainMessage(34));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        bv.c().a("autotrace: start to connect");
        a(1);
        if (p()) {
            bv.c().a("autotrace: connect established, no need to duplicate connect");
            return;
        }
        String strA = a(this.a);
        if (bw.c().b()) {
            bw.c().a(TextUtils.isEmpty(strA) ? "url:" : "url:" + strA);
        }
        try {
            this.c = new bd(URI.create(strA), new b());
        } catch (Exception e) {
        }
    }

    private boolean p() {
        bd bdVar = this.c;
        return bdVar != null && bdVar.b();
    }

    private boolean q() {
        return !TextUtils.isEmpty(this.v);
    }

    private String r() {
        Activity activity = this.d;
        if (activity != null) {
            return activity.getClass().getName();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (p() && this.j) {
            JSONObject jSONObjectA = a(this.e.a(this.d));
            if (jSONObjectA != null) {
                if (bw.c().b()) {
                    bw.c().a("doSendSnapshot:" + jSONObjectA.toString());
                }
                try {
                    this.c.a(jSONObjectA);
                } catch (Exception e) {
                }
            }
            this.h.sendMessageDelayed(this.h.obtainMessage(2), 2000L);
        }
    }

    private void t() {
        if (this.m) {
            return;
        }
        if (this.u == 0) {
            this.u = cj.a().p(this.a);
        }
        if (System.currentTimeMillis() - this.u > 86400000) {
            this.f.sendMessage(this.f.obtainMessage(23));
        }
    }

    private void u() {
        if (this.l) {
            return;
        }
        if (!this.n) {
            this.q = ch.a(this.a, ba.b);
            this.n = true;
        }
        if (this.s == 0) {
            this.s = cj.a().n(this.a);
            this.t = cj.a().o(this.a);
        }
        long j = this.t;
        if (!(this.n && TextUtils.isEmpty(this.q)) && System.currentTimeMillis() - this.s <= j) {
            return;
        }
        this.f.sendMessage(this.f.obtainMessage(22));
    }

    private void v() {
        if (this.o) {
            return;
        }
        if (TextUtils.isEmpty(this.r)) {
            h();
        }
        this.o = true;
    }

    public void a(int i) {
        a(i, "");
    }

    public void a(int i, String str) {
        synchronized (this.G) {
            if (this.a == null) {
                return;
            }
            if (str == null) {
                str = "";
            }
            this.F.put(i + Config.replace + System.currentTimeMillis() + Config.replace + ((cp.p(this.a) ? 1 : 0) + "|" + str));
            ch.a(this.a, ba.d, this.F.toString(), false);
        }
    }

    public void a(Activity activity) {
    }

    public void a(Activity activity, boolean z) {
        if (activity instanceof IIgnoreAutoEvent) {
            return;
        }
        if (z) {
            this.A.a(activity, true, this.z, this.w);
        } else {
            this.A.a(activity, true);
        }
    }

    public void a(WebView webView, String str, ce ceVar) {
        if (TextUtils.isEmpty(this.p)) {
            this.p = ch.a(this.a, ba.a);
        }
        b(webView, this.p, ceVar);
        if (TextUtils.isEmpty(this.q)) {
            this.q = ch.a(this.a, ba.b);
        }
        c(webView, this.q, ceVar);
    }

    public void a(String str) {
        this.v = str;
    }

    public void a(boolean z) {
        this.w = z;
    }

    public boolean a(Activity activity, Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            return false;
        }
        String scheme = data.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            return false;
        }
        try {
            String queryParameter = data.getQueryParameter("token");
            String queryParameter2 = data.getQueryParameter("time");
            if (!scheme.startsWith("mtj") || scheme.length() <= "mtj".length()) {
                return false;
            }
            String strSubstring = scheme.substring("mtj".length());
            if (TextUtils.isEmpty(strSubstring) || !strSubstring.equals(this.v.toLowerCase()) || TextUtils.isEmpty(queryParameter)) {
                return false;
            }
            this.x = queryParameter;
            String strS = cj.a().s(activity);
            if (TextUtils.isEmpty(queryParameter2) || queryParameter2.equals(strS)) {
                return false;
            }
            cj.a().k(activity, queryParameter2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void b() {
        Activity activity = this.d;
        if (activity == null) {
            return;
        }
        bc.b(activity);
    }

    public void b(Activity activity) {
        Intent intent;
        if (q()) {
            this.a = activity.getApplicationContext();
            if (activity != null && (intent = activity.getIntent()) != null && a(activity, intent)) {
                a().i();
            }
            if (this.d != null) {
                c();
            }
            this.d = activity;
            if (cn.a().c()) {
                d(activity);
            }
            v();
            t();
            u();
            b(activity, true);
            e(activity);
            g();
            a(activity, true);
        }
    }

    public void c() {
        Activity activity = this.d;
        if (activity == null) {
            return;
        }
        bc.a(activity);
    }

    public void c(Activity activity) {
        if (q()) {
            this.d = null;
            b(activity, false);
            f();
            a(activity, false);
        }
    }

    public void d() {
        if (p()) {
            this.c.a();
        }
    }

    public JSONArray e() {
        synchronized (this.G) {
            Context context = this.a;
            if (context == null) {
                return new JSONArray();
            }
            String strA = ch.a(context, ba.d);
            JSONArray jSONArray = null;
            try {
                if (!TextUtils.isEmpty(strA)) {
                    jSONArray = new JSONArray(strA);
                }
            } catch (Exception e) {
            }
            if (jSONArray == null) {
                jSONArray = new JSONArray();
            }
            this.F = new JSONArray();
            ch.a(this.a, ba.d, this.F.toString(), false);
            return jSONArray;
        }
    }
}

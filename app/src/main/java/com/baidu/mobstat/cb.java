package com.baidu.mobstat;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class cb {
    private boolean a;
    private List<b> b = new ArrayList();
    private String c;
    private cd d;
    private boolean e;

    public interface a {
        void a(View view, boolean z);
    }

    public class b {
        public String a;
        public String b;
        public boolean c;
        public int d;

        public b(String str, String str2, boolean z, int i) {
            this.a = str;
            this.b = str2;
            this.c = z;
            this.d = i;
        }
    }

    static class c {
        public String a;
        public String b;
        public String c;
        public c d;

        public c(View view, c cVar, View view2) {
            this.d = cVar;
            this.a = cc.m(view);
            this.b = cc.b(view);
            String strC = cc.c(view);
            if (TextUtils.isEmpty(strC)) {
                strC = cc.a(view, c());
                if (TextUtils.isEmpty(strC)) {
                    strC = cc.a(view, view2);
                }
            }
            this.c = strC;
        }

        public String a() {
            StringBuilder sb = new StringBuilder();
            for (c cVar = this; cVar != null; cVar = cVar.d) {
                sb.insert(0, cVar.a(false));
            }
            return sb.toString();
        }

        public String a(boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append("/");
            sb.append(this.a);
            if (!z) {
                sb.append("[");
                sb.append(this.c);
                sb.append("]");
            }
            return sb.toString();
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String b() {
            /*
                r6 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r1 = 0
                r2 = r6
                r3 = r1
            La:
                if (r2 == 0) goto L3c
            Ld:
                if (r3 != 0) goto L2f
                java.lang.String r4 = r2.c()
                java.lang.String r5 = "ListView"
                boolean r5 = r5.equals(r4)
                if (r5 != 0) goto L2b
                java.lang.String r5 = "RecyclerView"
                boolean r5 = r5.equals(r4)
                if (r5 != 0) goto L2b
                java.lang.String r5 = "GridView"
                boolean r4 = r5.equals(r4)
                if (r4 == 0) goto L2f
            L2b:
            L2c:
                r3 = 1
                r4 = r3
                goto L31
            L2f:
                r4 = r3
                r3 = r1
            L31:
                java.lang.String r3 = r2.a(r3)
                r0.insert(r1, r3)
                com.baidu.mobstat.cb$c r2 = r2.d
                r3 = r4
                goto La
            L3c:
                java.lang.String r0 = r0.toString()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.cb.c.b():java.lang.String");
        }

        public String c() {
            c cVar = this.d;
            return cVar == null ? "" : cVar.b;
        }
    }

    public cb(Activity activity, cd cdVar, boolean z) {
        this.c = activity.getClass().getName();
        this.d = cdVar;
        this.e = z;
    }

    private void a(Activity activity, View view, c cVar, View view2) {
        if (view == null || bc.a(view) || cc.c(activity, view)) {
            return;
        }
        c cVar2 = new c(view, cVar, view2);
        if (cVar != null) {
            boolean zB = this.a ? cc.b(view, cVar2.c()) : a(this.b, cVar2.a(), cVar2.b());
            if (zB || this.e) {
                if (bw.c().b() && zB) {
                    bw.c().a("accumulate view:" + view.getClass().getName() + "; content:" + cc.h(view));
                }
                if (ca.c().b()) {
                    ca.c().a("accumulate view:" + view.getClass().getName() + "; content:" + cc.h(view));
                }
                this.d.a(view, zB);
            }
        }
        if (!(view instanceof WebView) && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                a(activity, viewGroup.getChildAt(i), cVar2, view2);
            }
        }
    }

    private boolean a(List<b> list, String str, String str2) {
        for (b bVar : list) {
            String str3 = bVar.c ? str2 : str;
            if (!TextUtils.isEmpty(str3) && str3.equals(bVar.b)) {
                return true;
            }
        }
        return false;
    }

    public void a(Activity activity) {
        List<b> list;
        if (this.e || this.a || !((list = this.b) == null || list.size() == 0)) {
            View viewA = cc.a(activity);
            a(activity, viewA, null, viewA);
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            this.a = ((JSONObject) jSONObject.get("meta")).getInt("matchAll") != 0;
        } catch (Exception e) {
        }
        if (this.a) {
            return;
        }
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get("data");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                String strOptString = jSONObject2.optString("page");
                String strOptString2 = jSONObject2.optString("layout");
                int iOptInt = jSONObject2.optInt("contentAsLabel");
                boolean z = jSONObject2.optInt("ignoreCellIndex") != 0;
                if (this.c.equals(strOptString)) {
                    this.b.add(new b(strOptString, strOptString2, z, iOptInt));
                }
            }
        } catch (Exception e2) {
        }
    }
}

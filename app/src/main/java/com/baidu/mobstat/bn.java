package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;
import com.baidu.mobstat.MtjConfig;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class bn {
    private static final bn u = new bn();
    private Context a;
    private WeakReference<Activity> b;
    private Handler c;
    private long g;
    private long h;
    private long i;
    private String j;
    private String k;
    private String l;
    private String m;
    private boolean n;
    private String o;
    private boolean p;
    private boolean q;
    private a v;
    private ViewTreeObserver.OnScrollChangedListener w;
    private boolean d = true;
    private List<WeakReference<View>> e = Collections.synchronizedList(new ArrayList());
    private volatile MtjConfig.FeedTrackStrategy f = MtjConfig.FeedTrackStrategy.TRACK_ALL;
    private List<WeakReference<View>> r = new ArrayList();
    private HashMap<WeakReference<View>, ArrayList<bj>> s = new HashMap<>();
    private HashMap<WeakReference<View>, HashMap<String, ArrayList<bk>>> t = new HashMap<>();
    private Runnable x = null;
    private float y = 0.0f;
    private float z = 0.0f;
    private Object A = new Object();

    public interface a {
        void a(bi biVar);

        void a(ArrayList<bj> arrayList);

        void b(ArrayList<bk> arrayList);
    }

    private bn() {
        HandlerThread handlerThread = new HandlerThread("feedViewCrawlerThread");
        handlerThread.start();
        this.c = new Handler(handlerThread.getLooper());
    }

    private bk a(ArrayList<bk> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        Collections.sort(arrayList, new Comparator<bk>() { // from class: com.baidu.mobstat.bn.6
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(bk bkVar, bk bkVar2) {
                long jI = bkVar.i() - bkVar2.i();
                if (jI > 0) {
                    return 1;
                }
                return jI < 0 ? -1 : 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        bk bkVar = null;
        long j = 0;
        int iH = 0;
        for (bk bkVar2 : arrayList) {
            long jI = bkVar2.i();
            String strL = bkVar2.l();
            if (Long.valueOf(strL).longValue() >= bm.a().d()) {
                if (j == 0) {
                    bkVar = bkVar2;
                    j = jI;
                }
                long j2 = jI - j;
                if (j2 < 0) {
                    j2 = 0;
                }
                if (TextUtils.isEmpty(sb2.toString())) {
                    sb2.append(strL);
                } else {
                    sb2.append("|" + strL);
                }
                if (TextUtils.isEmpty(sb.toString())) {
                    sb.append("" + j2);
                } else {
                    sb.append("|" + j2);
                }
                iH += bkVar2.h();
            }
        }
        if (bkVar != null) {
            bkVar.a(sb2.toString());
            bkVar.b(sb.toString());
            bkVar.a(iH);
        }
        return bkVar;
    }

    public static bn a() {
        return u;
    }

    private String a(bk bkVar) {
        return bk.a(bkVar.a(), bkVar.b(), bkVar.c(), bkVar.d(), bkVar.e(), bkVar.f(), bkVar.g());
    }

    private ArrayList<WeakReference<View>> a(HashMap<View, Integer> map) {
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<View, Integer>>() { // from class: com.baidu.mobstat.bn.3
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<View, Integer> entry, Map.Entry<View, Integer> entry2) {
                return entry2.getValue().compareTo(entry.getValue());
            }
        });
        ArrayList<WeakReference<View>> arrayList2 = new ArrayList<>(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new WeakReference<>(((Map.Entry) it.next()).getKey()));
        }
        return arrayList2;
    }

    private LinkedHashMap<WeakReference<View>, ArrayList<bj>> a(HashMap<WeakReference<View>, ArrayList<bj>> map, View view) {
        View view2;
        for (Map.Entry<WeakReference<View>, ArrayList<bj>> entry : map.entrySet()) {
            WeakReference<View> key = entry.getKey();
            if (key != null && (view2 = key.get()) != null && view2 == view) {
                ArrayList<bj> value = entry.getValue();
                LinkedHashMap<WeakReference<View>, ArrayList<bj>> linkedHashMap = new LinkedHashMap<>(1);
                linkedHashMap.put(key, value);
                return linkedHashMap;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, long j) {
        this.a = activity.getApplicationContext();
        this.b = new WeakReference<>(activity);
        this.g = j;
        String strE = cc.e(activity);
        if (cc.a(strE, this.j)) {
            this.p = false;
            if (a(strE, this.k, this.j, this.i, this.g, activity)) {
                this.p = true;
            }
        }
    }

    private void a(Activity activity, long j, long j2, List<WeakReference<View>> list) {
        View view;
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            list.add(new WeakReference<>(cc.a(activity)));
        }
        Iterator<WeakReference<View>> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                view = null;
                break;
            }
            WeakReference<View> next = it.next();
            if (next != null && (view = next.get()) != null) {
                break;
            }
        }
        if (TextUtils.isEmpty(this.l)) {
            return;
        }
        String str = this.m;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strE = cc.e(activity);
        String strF = cc.f(activity);
        ArrayList<Integer> arrayListA = a(activity, view);
        int iA = bb.a(this.a, arrayListA.get(0).intValue());
        int iA2 = bb.a(this.a, arrayListA.get(1).intValue());
        ArrayList<Integer> arrayListB = cc.b(activity, view);
        int iA3 = bb.a(this.a, arrayListB.get(0).intValue());
        int iA4 = bb.a(this.a, arrayListB.get(1).intValue());
        if (iA3 > iA) {
            iA = iA3;
        }
        if (iA4 > iA2) {
            iA2 = iA4;
        }
        if (iA == 0 || iA2 == 0) {
            return;
        }
        a(this.a, new bi(strE, strF, this.l, j2 - j, j, iA3, iA4, iA, iA2, str, this.n, this.o));
    }

    private void a(Activity activity, View view, HashMap<View, Integer> map, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        int width;
        if (view == null || cc.c(activity, view)) {
            return;
        }
        boolean zN = cc.n(view);
        if (zN && !a(view) && cc.d(view)) {
            arrayList.add(view);
        }
        if (zN) {
            arrayList2.add(view);
        }
        if ((zN || (view instanceof WebView) || (view instanceof ScrollView)) && (width = view.getWidth() * view.getHeight()) != 0) {
            map.put(view, Integer.valueOf(width));
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                try {
                    a(activity, viewGroup.getChildAt(i), map, arrayList, arrayList2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void a(Activity activity, final WebView webView) {
        activity.runOnUiThread(new Runnable() { // from class: com.baidu.mobstat.bn.7
            @Override // java.lang.Runnable
            public void run() {
                synchronized (bn.this.A) {
                    bn.this.y = webView.getContentHeight();
                    bn.this.z = webView.getScale();
                    bn.this.A.notifyAll();
                }
            }
        });
    }

    private void a(Context context, bi biVar) {
        if (biVar == null) {
            return;
        }
        a aVar = this.v;
        if (aVar != null) {
            aVar.a(biVar);
        }
        bq.a().a(context, biVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view, Activity activity, long j) {
        View viewA = cc.a(view, activity);
        View viewO = cc.o(viewA);
        if (viewO == null) {
            if (this.p) {
                return;
            }
            c();
            return;
        }
        if (a(viewO)) {
            if (this.p) {
                return;
            }
            c();
            return;
        }
        this.i = j;
        this.k = cc.e(activity);
        this.l = "";
        Map<String, String> mapT = cc.t(viewA);
        if (mapT != null && mapT.size() > 0 && !TextUtils.isEmpty(mapT.get(Config.FEED_LIST_ITEM_TITLE))) {
            this.l = mapT.get(Config.FEED_LIST_ITEM_TITLE);
        }
        this.m = cc.c(viewO, this.k);
        this.n = cc.s(viewO);
        this.o = a(activity, viewA, viewO);
    }

    private void a(View view, View view2, Activity activity, long j) {
        String str;
        if (view == null || view2 == null || !cc.a(view2, bm.a().c())) {
            return;
        }
        Map<String, String> mapT = cc.t(view2);
        String str2 = "";
        if (mapT == null || mapT.size() <= 0) {
            str = "";
        } else {
            String str3 = !TextUtils.isEmpty(mapT.get(Config.FEED_LIST_ITEM_TITLE)) ? mapT.get(Config.FEED_LIST_ITEM_TITLE) : "";
            if (TextUtils.isEmpty(mapT.get("content"))) {
                str = str3;
            } else {
                str2 = mapT.get("content");
                str = str3;
            }
        }
        String str4 = str2;
        bk bkVar = new bk(cc.u(view2), cc.e(activity), cc.a(activity, view2), str, str4, cc.a(view2, cc.b(view)), cc.c(view, cc.e(activity)), cc.s(view), 1, j, j, j, String.valueOf(j - j), "", new JSONArray());
        bq.a().b(str2);
        a(this.t, view, bkVar);
    }

    private void a(View view, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        ViewTreeObserver viewTreeObserver;
        if (view == null || (viewTreeObserver = view.getViewTreeObserver()) == null || !viewTreeObserver.isAlive() || onScrollChangedListener == null) {
            return;
        }
        viewTreeObserver.removeOnScrollChangedListener(onScrollChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener, List<WeakReference<View>> list) {
        ViewTreeObserver viewTreeObserver;
        if (view == null || a(list, view) || a(view) || (viewTreeObserver = view.getViewTreeObserver()) == null || !viewTreeObserver.isAlive() || onScrollChangedListener == null || list == null) {
            return;
        }
        try {
            viewTreeObserver.addOnScrollChangedListener(onScrollChangedListener);
            list.add(new WeakReference<>(view));
        } catch (Exception e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WeakReference<Activity> weakReference, long j) {
        Activity activity;
        if (weakReference == null || (activity = weakReference.get()) == null) {
            return;
        }
        d(activity, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HashMap<WeakReference<View>, ArrayList<bj>> map, long j) {
        if (map == null || map.size() == 0) {
            return;
        }
        Iterator<Map.Entry<WeakReference<View>, ArrayList<bj>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            ArrayList<bj> value = it.next().getValue();
            if (value != null && value.size() != 0) {
                for (bj bjVar : value) {
                    if (bjVar.e() == bjVar.c()) {
                        bjVar.a(j);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.util.HashMap<java.lang.ref.WeakReference<android.view.View>, java.util.ArrayList<com.baidu.mobstat.bj>> r17, android.view.View r18, com.baidu.mobstat.bj r19) {
        /*
            r16 = this;
            java.lang.String r0 = r19.a()
            long r1 = r19.c()
            long r3 = r19.e()
            boolean r5 = r19.d()
            boolean r6 = android.text.TextUtils.isEmpty(r0)
            if (r6 == 0) goto L17
            return
        L17:
            java.util.LinkedHashMap r6 = r16.a(r17, r18)
            if (r6 == 0) goto L47
            int r8 = r6.size()
            if (r8 <= 0) goto L47
            java.util.Set r6 = r6.entrySet()
            java.util.Iterator r6 = r6.iterator()
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L47
            java.lang.Object r6 = r6.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r8 = r6.getKey()
            java.lang.ref.WeakReference r8 = (java.lang.ref.WeakReference) r8
            java.lang.Object r6 = r6.getValue()
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            goto L49
        L47:
            r6 = 0
            r8 = 0
        L49:
            if (r6 == 0) goto L81
            java.util.Iterator r9 = r6.iterator()
        L4f:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L81
            java.lang.Object r10 = r9.next()
            com.baidu.mobstat.bj r10 = (com.baidu.mobstat.bj) r10
            java.lang.String r11 = r10.a()
            long r12 = r10.c()
            long r14 = r10.e()
            boolean r7 = r10.d()
            boolean r11 = r0.equals(r11)
            if (r11 != 0) goto L72
            goto L4f
        L72:
            if (r5 == r7) goto L75
            goto L4f
        L75:
            int r7 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r7 >= 0) goto L7e
            int r7 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r7 == 0) goto L7e
            goto L4f
        L7e:
            r7 = r10
            goto L82
        L81:
            r7 = 0
        L82:
            if (r7 != 0) goto L9f
            if (r6 != 0) goto L8b
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L8b:
            r0 = r19
            r6.add(r0)
            if (r8 != 0) goto L99
            java.lang.ref.WeakReference r8 = new java.lang.ref.WeakReference
            r0 = r18
            r8.<init>(r0)
        L99:
            r0 = r17
            r0.put(r8, r6)
            goto La5
        L9f:
            r7.a(r1)
            r7.b(r3)
        La5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.bn.a(java.util.HashMap, android.view.View, com.baidu.mobstat.bj):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.util.HashMap<java.lang.ref.WeakReference<android.view.View>, java.util.HashMap<java.lang.String, java.util.ArrayList<com.baidu.mobstat.bk>>> r19, android.view.View r20, com.baidu.mobstat.bk r21) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.bn.a(java.util.HashMap, android.view.View, com.baidu.mobstat.bk):void");
    }

    private void a(HashMap<WeakReference<View>, ArrayList<bj>> map, HashMap<WeakReference<View>, HashMap<String, ArrayList<bk>>> map2, long j) {
        HashMap<String, ArrayList<bk>> value;
        ArrayList<bj> value2;
        try {
            Iterator<Map.Entry<WeakReference<View>, ArrayList<bj>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                try {
                    value2 = it.next().getValue();
                } catch (Exception e) {
                    value2 = null;
                }
                if (value2 != null && value2.size() != 0) {
                    for (bj bjVar : value2) {
                        if (bjVar.e() == bjVar.c()) {
                            bjVar.a(j);
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        try {
            Iterator<Map.Entry<WeakReference<View>, HashMap<String, ArrayList<bk>>>> it2 = map2.entrySet().iterator();
            while (it2.hasNext()) {
                try {
                    value = it2.next().getValue();
                } catch (Exception e2) {
                    value = null;
                }
                if (value != null && value.size() != 0) {
                    Iterator<Map.Entry<String, ArrayList<bk>>> it3 = value.entrySet().iterator();
                    while (it3.hasNext()) {
                        ArrayList<bk> value3 = it3.next().getValue();
                        if (value3 != null && value3.size() != 0) {
                            for (bk bkVar : value3) {
                                if (bkVar.k() == bkVar.j()) {
                                    bkVar.a(j);
                                    bkVar.a(String.valueOf(bkVar.j() - bkVar.i()));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable th2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<WeakReference<View>> list) {
        WeakReference<View> weakReference;
        if (list == null || list.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            try {
                weakReference = list.get(i);
            } catch (Exception e) {
                weakReference = null;
            }
            if (weakReference == null) {
                arrayList.add(weakReference);
            } else {
                View view = weakReference.get();
                if (view == null) {
                    arrayList.add(weakReference);
                } else {
                    ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                    if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
                        arrayList.add(weakReference);
                    }
                }
            }
        }
        list.removeAll(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<WeakReference<View>> list, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        WeakReference<View> weakReference;
        if (list == null || list.size() <= 0) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                weakReference = list.get(i);
            } catch (Exception e) {
                weakReference = null;
            }
            if (weakReference != null) {
                a(weakReference.get(), onScrollChangedListener);
            }
        }
        list.clear();
    }

    private boolean a(long j, long j2) {
        long j3 = j2 - j;
        return j3 > 0 && j3 > 50;
    }

    private boolean a(View view) {
        if (this.f == MtjConfig.FeedTrackStrategy.TRACK_ALL) {
            return false;
        }
        return (this.f == MtjConfig.FeedTrackStrategy.TRACK_SINGLE && cc.v(view)) ? false : true;
    }

    private boolean a(bk bkVar, bk bkVar2) {
        return a(bkVar.a(), bkVar2.a()) && a(bkVar.b(), bkVar2.b()) && a(bkVar.c(), bkVar2.c()) && a(bkVar.d(), bkVar2.d()) && a(bkVar.e(), bkVar2.e()) && a(bkVar.f(), bkVar2.f()) && bkVar.g() == bkVar2.g();
    }

    private boolean a(String str, String str2) {
        if (str == str2) {
            return true;
        }
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.equals(str2)) ? false : true;
    }

    private boolean a(String str, String str2, String str3, long j, long j2, Activity activity) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || str.equals(str2) || !str2.equals(str3) || (activity instanceof IIgnoreAutoTrace)) {
            return false;
        }
        long j3 = j2 - j;
        return j3 > 0 && j3 < Config.BPLUS_DELAY_TIME;
    }

    private boolean a(List<WeakReference<View>> list, View view) {
        WeakReference<View> weakReference;
        if (list == null || list.size() <= 0) {
            return false;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                weakReference = list.get(i);
            } catch (Exception e) {
                weakReference = null;
            }
            if (weakReference != null && view == weakReference.get()) {
                return true;
            }
        }
        return false;
    }

    private boolean a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        if (jSONArray == null || jSONArray2 == null) {
            return false;
        }
        return a(jSONArray.toString(), jSONArray2.toString());
    }

    private LinkedHashMap<WeakReference<View>, HashMap<String, ArrayList<bk>>> b(HashMap<WeakReference<View>, HashMap<String, ArrayList<bk>>> map, View view) {
        View view2;
        for (Map.Entry<WeakReference<View>, HashMap<String, ArrayList<bk>>> entry : map.entrySet()) {
            WeakReference<View> key = entry.getKey();
            if (key != null && (view2 = key.get()) != null && view2 == view) {
                LinkedHashMap<WeakReference<View>, HashMap<String, ArrayList<bk>>> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put(key, entry.getValue());
                return linkedHashMap;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Activity activity, long j) {
        this.h = j;
        String strE = cc.e(activity);
        this.j = strE;
        if (!TextUtils.isEmpty(this.k) && !this.k.equals(strE)) {
            this.i = 0L;
        }
        if (this.p) {
            a(activity, this.g, j, this.r);
            this.r.clear();
            this.q = false;
        }
        a(this.s, this.t, j);
        b(this.s);
        c(this.s);
        e(this.t);
        f(this.t);
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.baidu.mobstat.bn.9
                @Override // java.lang.Runnable
                public void run() {
                    bn bnVar = bn.this;
                    bnVar.a((List<WeakReference<View>>) bnVar.e, bn.this.d());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(View view, Activity activity, long j) {
        if (view != null && cc.d(view)) {
            a(this.s, view, new bj(cc.c(view, cc.e(activity)), j, j, j, cc.s(view)));
        }
    }

    private void b(HashMap<WeakReference<View>, ArrayList<bj>> map) {
        d(map);
    }

    private void b(HashMap<WeakReference<View>, HashMap<String, ArrayList<bk>>> map, long j) {
        if (map == null || map.size() == 0) {
            return;
        }
        Iterator<Map.Entry<WeakReference<View>, HashMap<String, ArrayList<bk>>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            HashMap<String, ArrayList<bk>> value = it.next().getValue();
            if (value != null) {
                Iterator<Map.Entry<String, ArrayList<bk>>> it2 = value.entrySet().iterator();
                while (it2.hasNext()) {
                    ArrayList<bk> value2 = it2.next().getValue();
                    if (value2 != null && value2.size() != 0) {
                        for (bk bkVar : value2) {
                            if (bkVar.k() == bkVar.j()) {
                                bkVar.a(j);
                                bkVar.a(String.valueOf(bkVar.j() - bkVar.i()));
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.i = 0L;
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = false;
        this.o = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final Activity activity, final long j) {
        HashMap<View, Integer> map = new HashMap<>();
        final ArrayList<View> arrayList = new ArrayList<>();
        final ArrayList<View> arrayList2 = new ArrayList<>();
        View viewA = cc.a(activity);
        a(activity, viewA, map, arrayList, arrayList2);
        if (this.p && !this.q && a(this.g, j)) {
            ArrayList<WeakReference<View>> arrayListA = a(map);
            arrayListA.add(new WeakReference<>(viewA));
            this.r = arrayListA;
            this.q = true;
        }
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.baidu.mobstat.bn.2
                @Override // java.lang.Runnable
                public void run() {
                    for (View view : arrayList2) {
                        bn bnVar = bn.this;
                        bnVar.a(view, bnVar.d(), (List<WeakReference<View>>) bn.this.e);
                    }
                    bn.this.c.post(new Runnable() { // from class: com.baidu.mobstat.bn.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            bn.this.a((List<WeakReference<View>>) bn.this.e);
                            if (bn.this.d) {
                                bn.this.a((WeakReference<Activity>) bn.this.b, j);
                                bn.this.d = false;
                            }
                            bn.this.a((HashMap<WeakReference<View>, ArrayList<bj>>) bn.this.s, j);
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                bn.this.b((View) it.next(), activity, j);
                            }
                        }
                    });
                }
            });
        }
    }

    private void c(HashMap<WeakReference<View>, ArrayList<bj>> map) {
        Iterator<Map.Entry<WeakReference<View>, ArrayList<bj>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            ArrayList<bj> value = it.next().getValue();
            if (value != null) {
                value.clear();
            }
        }
        map.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ViewTreeObserver.OnScrollChangedListener d() {
        if (this.w == null) {
            this.w = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.baidu.mobstat.bn.14
                @Override // android.view.ViewTreeObserver.OnScrollChangedListener
                public void onScrollChanged() {
                    bn bnVar = bn.this;
                    bnVar.a(bnVar.b);
                }
            };
        }
        return this.w;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Activity activity, long j) {
        WeakReference<View> weakReference;
        View view;
        b(this.t, j);
        List<WeakReference<View>> list = this.e;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.e.size(); i++) {
            try {
                weakReference = this.e.get(i);
            } catch (Exception e) {
                weakReference = null;
            }
            if (weakReference != null && (view = weakReference.get()) != null && cc.d(view) && !a(view) && (view instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    View childAt = viewGroup.getChildAt(i2);
                    if (childAt != null && cc.d(childAt)) {
                        a(view, childAt, activity, j);
                    }
                }
            }
        }
    }

    private void d(HashMap<WeakReference<View>, ArrayList<bj>> map) {
        ArrayList<bj> arrayList = new ArrayList<>();
        Iterator<Map.Entry<WeakReference<View>, ArrayList<bj>>> it = this.s.entrySet().iterator();
        while (it.hasNext()) {
            ArrayList<bj> value = it.next().getValue();
            if (value != null && value.size() != 0) {
                arrayList.addAll(value);
            }
        }
        Collections.sort(arrayList, new Comparator<bj>() { // from class: com.baidu.mobstat.bn.4
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(bj bjVar, bj bjVar2) {
                long jB = bjVar.b() - bjVar2.b();
                if (jB > 0) {
                    return 1;
                }
                return jB < 0 ? -1 : 0;
            }
        });
        a aVar = this.v;
        if (aVar != null) {
            aVar.a(arrayList);
        }
        bq.a().a(this.a, arrayList);
    }

    private void e(HashMap<WeakReference<View>, HashMap<String, ArrayList<bk>>> map) {
        g(map);
    }

    private void f(HashMap<WeakReference<View>, HashMap<String, ArrayList<bk>>> map) {
        Iterator<Map.Entry<WeakReference<View>, HashMap<String, ArrayList<bk>>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            HashMap<String, ArrayList<bk>> value = it.next().getValue();
            if (value != null) {
                Iterator<Map.Entry<String, ArrayList<bk>>> it2 = value.entrySet().iterator();
                while (it2.hasNext()) {
                    it2.next().getValue().clear();
                }
                value.clear();
            }
        }
        map.clear();
    }

    private void g(HashMap<WeakReference<View>, HashMap<String, ArrayList<bk>>> map) {
        ArrayList<bk> arrayList = new ArrayList<>();
        Iterator<Map.Entry<WeakReference<View>, HashMap<String, ArrayList<bk>>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll(h(it.next().getValue()));
        }
        Collections.sort(arrayList, new Comparator<bk>() { // from class: com.baidu.mobstat.bn.5
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(bk bkVar, bk bkVar2) {
                long jI = bkVar.i() - bkVar2.i();
                if (jI > 0) {
                    return 1;
                }
                return jI < 0 ? -1 : 0;
            }
        });
        a aVar = this.v;
        if (aVar != null) {
            aVar.b(arrayList);
        }
        bq.a().b(this.a, arrayList);
    }

    private ArrayList<bk> h(HashMap<String, ArrayList<bk>> map) {
        ArrayList<bk> arrayList = new ArrayList<>();
        Iterator<Map.Entry<String, ArrayList<bk>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            bk bkVarA = a(it.next().getValue());
            if (bkVarA != null) {
                arrayList.add(bkVarA);
            }
        }
        return arrayList;
    }

    public String a(Activity activity, View view, View view2) {
        Map<String, String> mapT = cc.t(view);
        String str = (mapT == null || mapT.size() <= 0 || TextUtils.isEmpty(mapT.get(Config.FEED_LIST_ITEM_TITLE))) ? "" : mapT.get(Config.FEED_LIST_ITEM_TITLE);
        return bk.a(cc.u(view), cc.e(activity), cc.a(activity, view), str, cc.a(view, cc.b(view2)), cc.c(view2, cc.e(activity)), cc.s(view2));
    }

    public ArrayList<Integer> a(Activity activity, View view) {
        int iComputeHorizontalScrollRange;
        int iComputeVerticalScrollRange;
        int i;
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (view == null || activity == null) {
            arrayList.add(0);
            arrayList.add(0);
            return arrayList;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (view instanceof WebView) {
            synchronized (this.A) {
                a(activity, (WebView) view);
                try {
                    this.A.wait(Config.BPLUS_DELAY_TIME);
                } catch (Exception e) {
                }
                i = (int) (this.y * this.z);
            }
            iComputeVerticalScrollRange = i;
            iComputeHorizontalScrollRange = 0;
        } else if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            if (scrollView.getChildCount() > 0) {
                iComputeHorizontalScrollRange = scrollView.getChildAt(0).getWidth();
                iComputeVerticalScrollRange = scrollView.getChildAt(0).getHeight();
            } else {
                iComputeHorizontalScrollRange = 0;
                iComputeVerticalScrollRange = 0;
            }
        } else if (view instanceof ListView) {
            iComputeVerticalScrollRange = cc.a((ListView) view);
            iComputeHorizontalScrollRange = 0;
        } else if (view instanceof GridView) {
            iComputeVerticalScrollRange = cc.a((GridView) view);
            iComputeHorizontalScrollRange = 0;
        } else if (cc.r(view)) {
            try {
                RecyclerView recyclerView = (RecyclerView) view;
                iComputeHorizontalScrollRange = recyclerView.computeHorizontalScrollRange();
                try {
                    iComputeVerticalScrollRange = recyclerView.computeVerticalScrollRange();
                } catch (Exception e2) {
                    iComputeVerticalScrollRange = 0;
                }
            } catch (Exception e3) {
                iComputeHorizontalScrollRange = 0;
            }
        } else {
            iComputeHorizontalScrollRange = 0;
            iComputeVerticalScrollRange = 0;
        }
        if (iComputeHorizontalScrollRange != 0) {
            width = iComputeHorizontalScrollRange;
        }
        if (iComputeVerticalScrollRange != 0) {
            height = iComputeVerticalScrollRange;
        }
        if (width <= 0) {
            width = 0;
        }
        int i2 = height > 0 ? height : 0;
        arrayList.add(Integer.valueOf(width));
        arrayList.add(Integer.valueOf(i2));
        return arrayList;
    }

    public void a(Activity activity) {
        if (activity == null) {
            return;
        }
        final WeakReference weakReference = new WeakReference(activity);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.c.post(new Runnable() { // from class: com.baidu.mobstat.bn.1
            @Override // java.lang.Runnable
            public void run() {
                bn.this.d = true;
                Activity activity2 = (Activity) weakReference.get();
                if (activity2 == null) {
                    return;
                }
                bn.this.a(activity2, jCurrentTimeMillis);
            }
        });
    }

    public void a(KeyEvent keyEvent) {
        if (keyEvent != null && keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            this.c.post(new Runnable() { // from class: com.baidu.mobstat.bn.12
                @Override // java.lang.Runnable
                public void run() {
                    Activity activity;
                    if (bn.this.b == null || (activity = (Activity) bn.this.b.get()) == null) {
                        return;
                    }
                    String strE = cc.e(activity);
                    if (TextUtils.isEmpty(bn.this.k) || !bn.this.k.equals(strE)) {
                        return;
                    }
                    bn.this.c();
                }
            });
        }
    }

    public void a(final View view, Activity activity) {
        if (view == null || activity == null) {
            return;
        }
        final WeakReference weakReference = new WeakReference(activity);
        final WeakReference weakReference2 = new WeakReference(view);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.c.post(new Runnable() { // from class: com.baidu.mobstat.bn.11
            @Override // java.lang.Runnable
            public void run() {
                Activity activity2 = (Activity) weakReference.get();
                if (activity2 == null || ((View) weakReference2.get()) == null) {
                    return;
                }
                bn.this.a(view, activity2, jCurrentTimeMillis);
            }
        });
    }

    public void a(MtjConfig.FeedTrackStrategy feedTrackStrategy) {
        this.f = feedTrackStrategy;
    }

    public void a(final String str) {
        this.c.post(new Runnable() { // from class: com.baidu.mobstat.bn.13
            @Override // java.lang.Runnable
            public void run() {
                bm.a().a(str);
            }
        });
    }

    public void a(final WeakReference<Activity> weakReference) {
        if (weakReference == null) {
            return;
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        Runnable runnable = new Runnable() { // from class: com.baidu.mobstat.bn.15
            @Override // java.lang.Runnable
            public void run() {
                Activity activity = (Activity) weakReference.get();
                if (activity == null) {
                    return;
                }
                bn.this.d(activity, jCurrentTimeMillis);
            }
        };
        Runnable runnable2 = this.x;
        if (runnable2 != null) {
            this.c.removeCallbacks(runnable2);
        }
        this.x = runnable;
        this.c.postDelayed(runnable, 350L);
    }

    public void b(Activity activity) {
        if (activity == null) {
            return;
        }
        final WeakReference weakReference = new WeakReference(activity);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.c.post(new Runnable() { // from class: com.baidu.mobstat.bn.8
            @Override // java.lang.Runnable
            public void run() {
                bn.this.d = false;
                Activity activity2 = (Activity) weakReference.get();
                if (activity2 == null) {
                    return;
                }
                bn.this.b(activity2, jCurrentTimeMillis);
            }
        });
    }

    public boolean b() {
        return this.f == MtjConfig.FeedTrackStrategy.TRACK_NONE;
    }

    public void c(Activity activity) {
        if (activity == null) {
            return;
        }
        final WeakReference weakReference = new WeakReference(activity);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.c.post(new Runnable() { // from class: com.baidu.mobstat.bn.10
            @Override // java.lang.Runnable
            public void run() {
                Activity activity2 = (Activity) weakReference.get();
                if (activity2 == null) {
                    return;
                }
                bn.this.c(activity2, jCurrentTimeMillis);
            }
        });
    }
}

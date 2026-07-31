package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.bo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bq {
    private static bq b = new bq();
    private static String l = "";
    public a a;
    private Handler d;
    private volatile int e;
    private int f;
    private HandlerThread c = new HandlerThread("fullTraceHandleThread");
    private JSONObject g = new JSONObject();
    private JSONArray h = new JSONArray();
    private JSONArray i = new JSONArray();
    private JSONArray j = new JSONArray();
    private JSONArray k = new JSONArray();
    private boolean m = false;
    private List<JSONObject> n = new ArrayList();
    private List<String> o = new ArrayList();
    private List<String> p = new ArrayList();

    public interface a {
        void a(JSONObject jSONObject);
    }

    private bq() {
        this.c.start();
        this.c.setPriority(10);
        this.d = new Handler(this.c.getLooper());
    }

    private long a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return 0L;
        }
        try {
            return jSONArray.getJSONObject(0).optLong("s");
        } catch (Exception e) {
            return 0L;
        }
    }

    public static bq a() {
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, long j, String str, String str2, String str3, int i, long j2, String str4, JSONArray jSONArray, String str5, JSONArray jSONArray2, String str6, Map<String, String> map, boolean z, JSONObject jSONObject, String str7, JSONArray jSONArray3) {
        a(context, EventAnalysis.getEvent(context, j, str, str2, str3, i, j2, 0L, "", null, null, cc.a(str4), cc.a(str5), str6, Config.EventViewType.EDIT.getValue(), 3, null, map, cc.c(jSONArray), cc.d(jSONArray2), z, jSONObject, str7, jSONArray3));
        c(context);
    }

    private void a(Context context, JSONArray jSONArray) {
        if (context == null || this.j == null || jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        if (ca.c().b()) {
            ca.c().a("putFeedList: " + jSONArray.toString());
        }
        String string = jSONArray.toString();
        if (b(context, string)) {
            if (ca.c().b()) {
                ca.c().a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.e + "; addedSize:" + string.length());
            }
            d(context);
        }
        a(this.j, jSONArray);
    }

    private void a(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (ca.c().b()) {
            ca.c().a("putEvent: " + jSONObject.toString());
        }
        String string = jSONObject.toString();
        if (b(context, string)) {
            if (ca.c().b()) {
                ca.c().a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.e + "; addedSize:" + string.length());
            }
            d(context);
        }
        try {
            jSONObject.put(Config.EVENT_NEXT_PAGENAME, "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        EventAnalysis.doEventMerge(this.h, jSONObject);
    }

    private void a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null || jSONArray2 == null) {
            return;
        }
        for (int i = 0; i < jSONArray2.length(); i++) {
            try {
                jSONArray.put(jSONArray2.getJSONObject(i));
            } catch (Exception e) {
                return;
            }
        }
    }

    private void a(JSONArray jSONArray, JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONArray jSONArrayOptJSONArray = null;
        try {
            jSONObject2 = jSONArray.getJSONObject(0);
        } catch (Exception e) {
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            try {
                jSONArrayOptJSONArray = jSONObject2.optJSONArray("p");
            } catch (Exception e2) {
            }
        }
        if (jSONArrayOptJSONArray != null) {
            jSONArrayOptJSONArray.put(jSONObject);
            return;
        }
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put(jSONObject);
        if (jSONObject2 != null) {
            try {
                jSONObject2.put("p", jSONArray2);
            } catch (Exception e3) {
            }
        }
    }

    private boolean a(String str, String str2) {
        if (str == str2) {
            return true;
        }
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.equals(str2)) ? false : true;
    }

    private boolean a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return false;
        }
        String strOptString = jSONObject.optString(Config.FEED_LIST_ITEM_CUSTOM_ID);
        jSONObject.optString("d");
        String strOptString2 = jSONObject.optString("p");
        String strOptString3 = jSONObject.optString(Config.FEED_LIST_ITEM_PATH);
        String strOptString4 = jSONObject.optString(Config.FEED_LIST_ITEM_TITLE);
        String strOptString5 = jSONObject.optString(Config.FEED_LIST_ITEM_INDEX);
        String strOptString6 = jSONObject.optString("n");
        int iOptInt = jSONObject.optInt("user");
        jSONObject.optInt("c");
        jSONObject.optLong("t");
        jSONObject.optString("ps");
        String strOptString7 = jSONObject2.optString(Config.FEED_LIST_ITEM_CUSTOM_ID);
        jSONObject2.optString("d");
        String strOptString8 = jSONObject2.optString("p");
        String strOptString9 = jSONObject2.optString(Config.FEED_LIST_ITEM_PATH);
        String strOptString10 = jSONObject2.optString(Config.FEED_LIST_ITEM_TITLE);
        String strOptString11 = jSONObject2.optString(Config.FEED_LIST_ITEM_INDEX);
        String strOptString12 = jSONObject2.optString("n");
        int iOptInt2 = jSONObject2.optInt("user");
        jSONObject2.optInt("c");
        jSONObject2.optLong("t");
        jSONObject2.optString("ps");
        return a(strOptString, strOptString7) && a(strOptString2, strOptString8) && a(strOptString3, strOptString9) && a(strOptString4, strOptString10) && a(strOptString5, strOptString11) && a(strOptString6, strOptString12) && iOptInt == iOptInt2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray b(JSONArray jSONArray, JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        JSONArray jSONArray2;
        JSONObject jSONObject4;
        if (jSONObject == null || jSONArray == null || jSONObject.optLong("s") <= 0) {
            return jSONArray;
        }
        JSONArray jSONArray3 = new JSONArray();
        JSONObject jSONObject5 = null;
        if (jSONArray.length() == 0) {
            try {
                jSONObject2 = new JSONObject(jSONObject.toString());
                try {
                    jSONObject2.put("p", new JSONArray());
                } catch (Exception e) {
                    jSONObject5 = jSONObject2;
                    jSONObject2 = jSONObject5;
                }
            } catch (Exception e2) {
            }
            if (jSONObject2 != null) {
                jSONArray3.put(jSONObject2);
            }
        } else {
            try {
                jSONObject3 = jSONArray.getJSONObject(0);
            } catch (Exception e3) {
                jSONObject3 = null;
            }
            if (jSONObject3 != null) {
                try {
                    jSONArray2 = jSONObject3.getJSONArray("p");
                } catch (Exception e4) {
                    jSONArray2 = null;
                }
            } else {
                jSONArray2 = null;
            }
            try {
                jSONObject4 = new JSONObject(jSONObject.toString());
                if (jSONArray2 != null) {
                    try {
                        jSONObject4.put("p", jSONArray2);
                    } catch (Exception e5) {
                        jSONObject5 = jSONObject4;
                        jSONObject4 = jSONObject5;
                    }
                }
            } catch (Exception e6) {
            }
            if (jSONObject4 != null) {
                jSONArray3.put(jSONObject4);
            }
        }
        return jSONArray3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, bi biVar) {
        if (context == null || biVar == null) {
            return;
        }
        JSONArray jSONArrayB = b(this.i, BDStatCore.instance().getPageSessionHead());
        this.i = jSONArrayB;
        if (jSONArrayB.length() == 0) {
            return;
        }
        long jA = a(this.i);
        if (jA <= 0) {
            return;
        }
        d(context, biVar.a(jA, bo.a().a(biVar.a(), bo.a.b), bo.a().a(biVar.b(), bo.a.c)));
        c(context);
    }

    private void b(Context context, JSONArray jSONArray) {
        if (context == null || this.k == null || jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        if (ca.c().b()) {
            ca.c().a("putFeedListItem: " + jSONArray.toString());
        }
        String string = jSONArray.toString();
        if (b(context, string)) {
            if (ca.c().b()) {
                ca.c().a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.e + "; addedSize:" + string.length());
            }
            d(context);
        }
        b(this.k, jSONArray);
    }

    private void b(Context context, JSONObject jSONObject) {
        CooperService.instance().getHeadObject().installHeader(context, jSONObject);
        try {
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put(Config.SEQUENCE_INDEX, this.f);
            jSONObject.put("ss", BDStatCore.instance().getSessionStartTime());
            jSONObject.put("at", "1");
            jSONObject.put("sign", CooperService.instance().getUUID());
            jSONObject.put(Config.PY, DataCore.instance().getHeadSessionPy());
            jSONObject.put(Config.PLT, CooperService.instance().getPlatformType());
        } catch (Exception e) {
        }
    }

    private void b(JSONArray jSONArray, JSONArray jSONArray2) {
        JSONObject jSONObject;
        if (jSONArray == null || jSONArray2 == null) {
            return;
        }
        for (int i = 0; i < jSONArray2.length(); i++) {
            try {
                JSONObject jSONObject2 = jSONArray2.getJSONObject(i);
                if (jSONObject2 != null && jSONObject2.length() != 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            jSONObject = null;
                            break;
                        }
                        jSONObject = jSONArray.getJSONObject(i2);
                        if (jSONObject != null && jSONObject.length() != 0 && a(jSONObject, jSONObject2)) {
                            break;
                        }
                        i2++;
                    }
                    if (jSONObject == null) {
                        jSONArray.put(jSONObject2);
                    } else {
                        b(jSONObject, jSONObject2);
                    }
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    private void b(JSONObject jSONObject) {
    }

    private void b(JSONObject jSONObject, JSONObject jSONObject2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        long jLongValue;
        long jLongValue2;
        String str6 = "d";
        String strOptString = jSONObject.optString("d");
        int iOptInt = jSONObject.optInt("c");
        long jOptLong = jSONObject.optLong("t");
        String strOptString2 = jSONObject.optString("ps");
        String strOptString3 = jSONObject2.optString("d");
        int iOptInt2 = jSONObject2.optInt("c");
        long jOptLong2 = jSONObject2.optLong("t");
        String strOptString4 = jSONObject2.optString("ps");
        int i = iOptInt + iOptInt2;
        long j = jOptLong <= jOptLong2 ? jOptLong : jOptLong2;
        if (jOptLong <= jOptLong2) {
            str = "ps";
            str2 = strOptString + "|" + strOptString3;
        } else {
            str = "ps";
            str2 = strOptString3 + "|" + strOptString;
        }
        int i2 = 0;
        long jLongValue3 = 0;
        if (jOptLong <= jOptLong2) {
            long j2 = jOptLong2 - jOptLong;
            StringBuilder sb = new StringBuilder();
            String[] strArrSplit = strOptString4.split("\\|");
            if (strArrSplit == null || strArrSplit.length == 0) {
                str3 = "d";
                str4 = str2;
                try {
                    jLongValue3 = Long.valueOf(strOptString4).longValue();
                } catch (Exception e) {
                }
                sb.append(j2 + jLongValue3);
            } else {
                int length = strArrSplit.length;
                while (i2 < length) {
                    String str7 = strArrSplit[i2];
                    if (!TextUtils.isEmpty(sb.toString())) {
                        sb.append("|");
                    }
                    try {
                        jLongValue2 = Long.valueOf(str7).longValue();
                    } catch (Exception e2) {
                        jLongValue2 = 0;
                    }
                    sb.append(j2 + jLongValue2);
                    i2++;
                    str2 = str2;
                    str6 = str6;
                }
                str3 = str6;
                str4 = str2;
            }
            str5 = strOptString2 + "|" + sb.toString();
        } else {
            str3 = "d";
            str4 = str2;
            long j3 = jOptLong - jOptLong2;
            StringBuilder sb2 = new StringBuilder();
            String[] strArrSplit2 = strOptString2.split("\\|");
            if (strArrSplit2 == null || strArrSplit2.length == 0) {
                try {
                    jLongValue3 = Long.valueOf(strOptString2).longValue();
                } catch (Exception e3) {
                }
                sb2.append(j3 + jLongValue3);
            } else {
                int length2 = strArrSplit2.length;
                while (i2 < length2) {
                    String str8 = strArrSplit2[i2];
                    if (!TextUtils.isEmpty(sb2.toString())) {
                        sb2.append("|");
                    }
                    try {
                        jLongValue = Long.valueOf(str8).longValue();
                    } catch (Exception e4) {
                        jLongValue = 0;
                    }
                    sb2.append(jLongValue + j3);
                    i2++;
                }
            }
            str5 = strOptString4 + "|" + sb2.toString();
        }
        try {
            jSONObject.put("c", i);
            jSONObject.put("t", j);
            jSONObject.put(str3, str4);
            jSONObject.put(str, str5);
        } catch (Exception e5) {
        }
    }

    private boolean b(Context context, String str) {
        return (str != null ? str.getBytes().length : 0) + this.e > 184320;
    }

    private void c(Context context, String str) {
        LogSender.instance().saveLogData(context, str, true);
        if (this.a != null) {
            try {
                this.a.a(new JSONObject(str));
            } catch (Exception e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, ArrayList<bj> arrayList) {
        if (context == null || arrayList == null || arrayList.size() == 0) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (bj bjVar : arrayList) {
            JSONObject jSONObjectA = bjVar.a(bo.a().a(bjVar.a(), bo.a.c));
            if (jSONObjectA != null) {
                jSONArray.put(jSONObjectA);
            }
        }
        a(context, jSONArray);
        c(context);
    }

    private void c(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
        } catch (Exception e) {
        }
        try {
            jSONObject.put(Config.TRACE_PART, jSONObject2);
        } catch (Exception e2) {
        }
    }

    private void d() {
        this.f++;
    }

    private void d(Context context) {
        this.i = b(this.i, BDStatCore.instance().getPageSessionHead());
        b(context, false);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, ArrayList<bk> arrayList) {
        if (context == null || arrayList == null || arrayList.size() == 0) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (bk bkVar : arrayList) {
            JSONObject jSONObjectA = bkVar.a(bo.a().a(bkVar.b(), bo.a.b), bo.a().a(bkVar.f(), bo.a.c), cc.c(bkVar.c()));
            if (jSONObjectA != null) {
                jSONArray.put(jSONObjectA);
            }
        }
        b(context, jSONArray);
        c(context);
    }

    private void d(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (ca.c().b()) {
            ca.c().a("putPage: " + jSONObject.toString());
        }
        String string = jSONObject.toString();
        if (b(context, string)) {
            if (ca.c().b()) {
                ca.c().a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.e + "; addedSize:" + string.length());
            }
            d(context);
        }
        a(this.i, jSONObject);
    }

    private void e() {
        this.f = 0;
    }

    public void a(Context context) {
        JSONObject jSONObject;
        JSONArray jSONArrayOptJSONArray;
        JSONArray jSONArrayOptJSONArray2;
        JSONArray jSONArrayOptJSONArray3;
        JSONArray jSONArrayOptJSONArray4;
        if (context == null) {
            return;
        }
        try {
            this.m = true;
            e();
            String str = cp.r(context) + Config.STAT_FULL_CACHE_FILE_NAME;
            if (ch.c(context, str)) {
                String strA = ch.a(context, str);
                if (TextUtils.isEmpty(strA)) {
                    return;
                }
                try {
                    jSONObject = new JSONObject(strA);
                } catch (Exception e) {
                    e.printStackTrace();
                    jSONObject = null;
                }
                if (jSONObject == null) {
                    return;
                }
                try {
                    jSONArrayOptJSONArray = jSONObject.optJSONArray(Config.EVENT_PART);
                    jSONArrayOptJSONArray2 = jSONObject.optJSONArray(Config.PRINCIPAL_PART);
                    jSONArrayOptJSONArray3 = jSONObject.optJSONArray(Config.FEED_LIST_PART);
                    jSONArrayOptJSONArray4 = jSONObject.optJSONArray("sv");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if ((jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() != 0) || ((jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() != 0) || ((jSONArrayOptJSONArray3 != null && jSONArrayOptJSONArray3.length() != 0) || (jSONArrayOptJSONArray4 != null && jSONArrayOptJSONArray4.length() != 0)))) {
                    b(context, jSONObject.getJSONObject(Config.HEADER_PART));
                    c(context, jSONObject);
                    b(jSONObject);
                    strA = jSONObject.toString();
                    if (ca.c().b()) {
                        ca.c().a("saveLastCacheToSend content: " + strA);
                    }
                    c(context, strA);
                }
                if (ca.c().b()) {
                    ca.c().a("saveLastCacheToSend content:empty, return");
                    return;
                }
                return;
            }
            return;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        c(context, false);
        this.m = false;
    }

    public void a(final Context context, final bi biVar) {
        if (!CooperService.instance().isCloseTrace() && cn.a().c()) {
            this.d.post(new Runnable() { // from class: com.baidu.mobstat.bq.4
                @Override // java.lang.Runnable
                public void run() {
                    if (BDStatCore.instance().getSessionStartTime() <= 0) {
                        return;
                    }
                    bq.this.b(context, biVar);
                }
            });
        }
    }

    public void a(Context context, String str) {
        JSONArray jSONArray = this.h;
        if (jSONArray == null || jSONArray.length() == 0 || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
            JSONObject jSONObject = (JSONObject) this.h.get(r1.length() - 1);
            if (jSONObject != null) {
                String strOptString = jSONObject.optString(Config.EVENT_NEXT_PAGENAME);
                long jOptLong = jSONObject.optLong("t");
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - jOptLong <= 1500 && TextUtils.isEmpty(strOptString)) {
                    jSONObject.put(Config.EVENT_NEXT_PAGENAME, str + "|" + jCurrentTimeMillis);
                    this.h.put(r11.length() - 1, jSONObject);
                    c(context);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, String str, String str2, String str3, int i, long j, String str4, JSONArray jSONArray, String str5, JSONArray jSONArray2, String str6, Map<String, String> map, JSONObject jSONObject, String str7, JSONArray jSONArray3) {
        a(context, str, str2, str3, i, j, str4, jSONArray, str5, jSONArray2, str6, map, false, jSONObject, str7, jSONArray3);
    }

    public void a(final Context context, final String str, final String str2, final String str3, final int i, final long j, final String str4, final JSONArray jSONArray, final String str5, final JSONArray jSONArray2, final String str6, final Map<String, String> map, final boolean z, final JSONObject jSONObject, final String str7, final JSONArray jSONArray3) {
        if (cn.a().c()) {
            this.d.post(new Runnable() { // from class: com.baidu.mobstat.bq.1
                @Override // java.lang.Runnable
                public void run() {
                    long sessionStartTime = BDStatCore.instance().getSessionStartTime();
                    if (sessionStartTime <= 0) {
                        return;
                    }
                    bq.this.a(context, sessionStartTime, str, str2, str3, i, j, str4, jSONArray, str5, jSONArray2, str6, map, z, jSONObject, str7, jSONArray3);
                }
            });
        }
    }

    public void a(final Context context, final ArrayList<bj> arrayList) {
        if (!CooperService.instance().isCloseTrace() && cn.a().c()) {
            this.d.post(new Runnable() { // from class: com.baidu.mobstat.bq.5
                @Override // java.lang.Runnable
                public void run() {
                    bq.this.c(context, (ArrayList<bj>) arrayList);
                }
            });
        }
    }

    public void a(final Context context, final boolean z) {
        this.d.post(new Runnable() { // from class: com.baidu.mobstat.bq.2
            @Override // java.lang.Runnable
            public void run() {
                bq.this.b(context, z);
            }
        });
    }

    public void a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            l = str;
        } catch (Exception e) {
        }
    }

    public void a(final JSONObject jSONObject) {
        this.d.post(new Runnable() { // from class: com.baidu.mobstat.bq.3
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObject2 = jSONObject;
                if (jSONObject2 == null || jSONObject2.length() == 0) {
                    return;
                }
                bq bqVar = bq.this;
                bqVar.i = bqVar.b(bqVar.i, jSONObject);
            }
        });
    }

    public int b() {
        return this.f;
    }

    public void b(Context context) {
        CooperService.instance().getHeadObject().installHeader(context, this.g);
    }

    public void b(final Context context, final ArrayList<bk> arrayList) {
        if (!CooperService.instance().isCloseTrace() && cn.a().c()) {
            this.d.post(new Runnable() { // from class: com.baidu.mobstat.bq.6
                @Override // java.lang.Runnable
                public void run() {
                    bq.this.d(context, (ArrayList<bk>) arrayList);
                }
            });
        }
    }

    public void b(Context context, boolean z) {
        try {
            if (z) {
                e();
            } else {
                d();
            }
            try {
                b(context, this.g);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.h.length() == 0 && this.i.length() == 0 && this.j.length() == 0 && this.k.length() == 0) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Config.HEADER_PART, this.g);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                jSONObject.put(Config.PRINCIPAL_PART, this.i);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            try {
                jSONObject.put(Config.EVENT_PART, this.h);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            try {
                jSONObject.put(Config.FEED_LIST_PART, this.j);
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            try {
                jSONObject.put("sv", this.k);
            } catch (Exception e6) {
                e6.printStackTrace();
            }
            try {
                jSONObject.put(Config.EVENT_PAGE_MAPPING, bo.a().a(bo.a.b));
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            try {
                jSONObject.put(Config.EVENT_PATH_MAPPING, bo.a().a(bo.a.a));
            } catch (Exception e8) {
                e8.printStackTrace();
            }
            try {
                jSONObject.put(Config.FEED_LIST_MAPPING, bo.a().a(bo.a.c));
            } catch (Exception e9) {
                e9.printStackTrace();
            }
            try {
                jSONObject.put(Config.PYD, l);
            } catch (Exception e10) {
                e10.printStackTrace();
            }
            c(context, jSONObject);
            b(jSONObject);
            String string = jSONObject.toString();
            if (ca.c().b()) {
                ca.c().a("saveCurrentCacheToSend content: " + string);
            }
            c(context, string);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        c(context, !z);
        this.m = true;
    }

    public void b(String str) {
    }

    public void c(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Config.HEADER_PART, new JSONObject(this.g.toString()));
                jSONObject.put(Config.PRINCIPAL_PART, new JSONArray(this.i.toString()));
                jSONObject.put(Config.EVENT_PART, new JSONArray(this.h.toString()));
                jSONObject.put(Config.FEED_LIST_PART, new JSONArray(this.j.toString()));
                jSONObject.put("sv", new JSONArray(this.k.toString()));
                jSONObject.put(Config.PYD, l);
                jSONObject.put(Config.EVENT_PAGE_MAPPING, bo.a().a(bo.a.b));
                jSONObject.put(Config.EVENT_PATH_MAPPING, bo.a().a(bo.a.a));
                jSONObject.put(Config.FEED_LIST_MAPPING, bo.a().a(bo.a.c));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String string = jSONObject.toString();
            int length = string.getBytes().length;
            if (length >= 184320) {
                return;
            }
            this.e = length;
            ch.a(context, cp.r(context) + Config.STAT_FULL_CACHE_FILE_NAME, string, false);
        } catch (Throwable th) {
            th.printStackTrace();
            StatService.closeTrace();
        }
    }

    public void c(Context context, boolean z) {
        this.g = new JSONObject();
        b(context);
        this.i = new JSONArray();
        this.h = new JSONArray();
        this.j = new JSONArray();
        this.k = new JSONArray();
        if (!z) {
            bo.a().b();
        }
        c(context);
    }

    public boolean c() {
        return this.m;
    }
}

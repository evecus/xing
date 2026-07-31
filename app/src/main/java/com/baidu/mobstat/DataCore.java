package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.StatService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DataCore {
    private static JSONObject a = new JSONObject();
    private static String b = "";
    private static DataCore c = new DataCore();
    private StatService.WearListener i;
    private JSONObject j;
    private JSONArray d = new JSONArray();
    private JSONArray e = new JSONArray();
    private JSONArray f = new JSONArray();
    private boolean g = false;
    private volatile int h = 0;
    private Object k = new Object();
    private boolean l = false;
    private HashMap<String, String> m = new HashMap<>();
    private List<String> n = Collections.synchronizedList(new ArrayList());
    private JSONObject o = new JSONObject();

    private DataCore() {
    }

    private int a(JSONObject jSONObject) {
        int i;
        if (jSONObject == null) {
            return 0;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.HEADER_PART);
            i = (jSONObject2.getLong("ss") <= 0 || jSONObject2.getLong(Config.SEQUENCE_INDEX) != 0) ? 0 : 1;
        } catch (Exception e) {
            i = 0;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Config.PRINCIPAL_PART);
            if (jSONArray != null && jSONArray.length() != 0) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                    long j = jSONObject3.getLong("c");
                    if (jSONObject3.getLong("e") != 0 && j == 0) {
                        i++;
                    }
                }
            }
        } catch (Exception e2) {
        }
        return i;
    }

    private JSONArray a(Context context, long j, long j2) {
        List arrayList = new ArrayList();
        String strF = ax.a().f(context);
        boolean z = false;
        if (!TextUtils.isEmpty(strF)) {
            try {
                JSONArray jSONArray = new JSONArray(strF);
                if (jSONArray.length() != 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add((JSONObject) jSONArray.get(i));
                    }
                }
            } catch (Exception e) {
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            if (((JSONObject) it.next()).getLong(Config.TRACE_VISIT_RECENT_DAY) == j) {
                break;
            }
        }
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Config.TRACE_VISIT_RECENT_DAY, j);
                jSONObject.put(Config.TRACE_VISIT_RECENT_COUNT, j2);
                arrayList.add(jSONObject);
            } catch (Exception e2) {
            }
        }
        int size = arrayList.size();
        if (size > 5) {
            arrayList = arrayList.subList(size - 5, size);
        }
        return new JSONArray((Collection) arrayList);
    }

    private void a(Context context) {
        synchronized (this.e) {
            this.e = new JSONArray();
        }
        synchronized (this.d) {
            this.d = new JSONArray();
        }
        synchronized (this.f) {
            this.f = new JSONArray();
        }
        flush(context);
    }

    private void a(Context context, String str, boolean z, boolean z2) {
        StatService.WearListener wearListener = this.i;
        if (wearListener != null && wearListener.onSendLogData(str)) {
            bv.c().a("Log has been passed to app level, log: " + str);
            return;
        }
        boolean z3 = false;
        LogSender.instance().saveLogData(context, str, false);
        bv.c().a("Save log: " + str);
        if (z) {
            bp.a(context);
            return;
        }
        if (bq.a().b() == 0 && bq.a().c()) {
            z3 = true;
        }
        bp.a(context, z3);
    }

    private void a(Context context, JSONObject jSONObject) {
    }

    private void a(Context context, JSONObject jSONObject, long j, int i) {
        long jLongValue;
        String str;
        String str2;
        long jIntValue;
        Object jSONArray;
        String[] strArrSplit;
        long jLongValue2 = ax.a().b(context).longValue();
        if (jLongValue2 <= 0 && i != 0) {
            ax.a().a(context, j);
            jLongValue2 = j;
        }
        a(jSONObject, Config.TRACE_VISIT_FIRST, Long.valueOf(jLongValue2));
        if (i != 0) {
            long jLongValue3 = ax.a().c(context).longValue();
            jLongValue = j - jLongValue3;
            if (jLongValue3 != 0 && jLongValue <= 0) {
                jLongValue = -1;
            } else if (jLongValue3 == 0) {
                jLongValue = 0;
            }
            ax.a().b(context, j);
            ax.a().c(context, jLongValue);
        } else {
            jLongValue = ax.a().d(context).longValue();
        }
        a(jSONObject, Config.TRACE_VISIT_SESSION_LAST_INTERVAL, Long.valueOf(jLongValue));
        String strE = ax.a().e(context);
        int iIntValue = 0;
        if (TextUtils.isEmpty(strE) || !strE.contains(Config.TRACE_TODAY_VISIT_SPLIT) || (strArrSplit = strE.split(Config.TRACE_TODAY_VISIT_SPLIT)) == null || strArrSplit.length != 2) {
            str = "";
            str2 = "";
        } else {
            str = strArrSplit[0];
            str2 = strArrSplit[1];
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                iIntValue = Integer.valueOf(str2).intValue();
            } catch (Exception e) {
            }
        }
        String strA = cq.a(j);
        int i2 = (TextUtils.isEmpty(str) || strA.equals(str)) ? i + iIntValue : i;
        if (i != 0) {
            ax.a().a(context, strA + Config.TRACE_TODAY_VISIT_SPLIT + i2);
        }
        a(jSONObject, Config.TRACE_VISIT_SESSION_TODAY_COUNT, Integer.valueOf(i2));
        if (TextUtils.isEmpty(str)) {
            jIntValue = 0;
        } else {
            try {
                jIntValue = Integer.valueOf(str).intValue();
            } catch (Exception e2) {
                jIntValue = 0;
            }
        }
        if (jIntValue != 0 && !TextUtils.isEmpty(str) && !strA.equals(str) && i != 0) {
            JSONArray jSONArrayA = a(context, jIntValue, iIntValue);
            ax.a().b(context, jSONArrayA.toString());
            a(jSONObject, Config.TRACE_VISIT_RECENT, jSONArrayA);
            return;
        }
        String strF = ax.a().f(context);
        if (TextUtils.isEmpty(strF)) {
            jSONArray = null;
        } else {
            try {
                jSONArray = new JSONArray(strF);
            } catch (Exception e3) {
                jSONArray = null;
            }
        }
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        a(jSONObject, Config.TRACE_VISIT_RECENT, jSONArray);
    }

    private void a(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        long j;
        int iA = a(jSONObject);
        try {
            JSONObject jSONObject3 = jSONObject.getJSONObject(Config.HEADER_PART);
            j = jSONObject3 != null ? jSONObject3.getLong("ss") : 0L;
        } catch (Exception e) {
            j = 0;
        }
        a(context, jSONObject2, j == 0 ? System.currentTimeMillis() : j, iA);
    }

    private void a(Context context, JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        boolean z2 = true;
        try {
            jSONObject2.put(Config.TRACE_APPLICATION_SESSION, z ? 1 : 0);
        } catch (Exception e) {
        }
        try {
            jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
        } catch (Exception e2) {
        }
        try {
            jSONObject2.put(Config.TRACE_CIRCLE, ay.c());
        } catch (Exception e3) {
        }
        try {
            jSONObject.put(Config.TRACE_PART, jSONObject2);
        } catch (Exception e4) {
            z2 = false;
        }
        if (z2) {
            a(context, jSONObject, jSONObject2);
        }
    }

    private void a(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject == null) {
            return;
        }
        if (!jSONObject.has(Config.TRACE_VISIT)) {
            try {
                jSONObject.put(Config.TRACE_VISIT, new JSONObject());
            } catch (Exception e) {
            }
        }
        try {
            ((JSONObject) jSONObject.get(Config.TRACE_VISIT)).put(str, obj);
        } catch (Exception e2) {
        }
    }

    private void a(boolean z) {
        this.g = z;
    }

    private boolean a() {
        return this.g;
    }

    private boolean a(String str) {
        return (str.getBytes().length + BDStatCore.instance().getSessionSize()) + this.h > 184320;
    }

    private void b(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject.length() == 0 || jSONObject2 == null || jSONObject2.length() == 0) {
            return;
        }
        try {
            jSONObject.put(Config.LAUNCH, jSONObject2);
        } catch (Exception e) {
        }
    }

    private void b(JSONObject jSONObject) {
    }

    public static DataCore instance() {
        return c;
    }

    public void clearCache(Context context) {
        a(false);
        String strOptString = a.optString(Config.DEVICE_ID_SEC);
        if (!TextUtils.isEmpty(strOptString)) {
            cj.a().l(context, strOptString);
        }
        synchronized (a) {
            a = new JSONObject();
        }
        installHeader(context);
        a(context);
    }

    public void clearProperty(String str) {
        if (str.equals(PropertyType.UID_PROPERTRY)) {
            this.m.put(Config.UID_PY, "");
            return;
        }
        if (str.equals("1")) {
            this.m.put(Config.USER_PY, "");
            return;
        }
        if (str.equals("2")) {
            this.m.put(Config.SESSION_PY, "");
            this.m.put(Config.SESSION_JSON_PY, "");
        } else if (str.equals("3")) {
            this.m.put(Config.EVENT_PY, "");
        } else if (str.equals(PropertyType.PAGE_PROPERTRY)) {
            this.m.put(Config.PAGE_PY, "");
        }
    }

    public String constructLogWithEmptyBody(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        HeadObject headObject = CooperService.instance().getHeadObject();
        if (TextUtils.isEmpty(headObject.e)) {
            headObject.installHeader(context, jSONObject2);
        } else {
            headObject.updateHeader(context, jSONObject2);
        }
        JSONArray jSONArray = new JSONArray();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject2.put("t", jCurrentTimeMillis);
            jSONObject2.put("ss", jCurrentTimeMillis);
            jSONObject2.put(Config.WIFI_LOCATION, jSONArray);
            jSONObject2.put(Config.SEQUENCE_INDEX, 0);
            jSONObject2.put("sign", CooperService.instance().getUUID());
            jSONObject2.put(Config.APP_KEY, str);
            jSONObject.put(Config.HEADER_PART, jSONObject2);
            try {
                jSONObject.put(Config.PRINCIPAL_PART, jSONArray);
                try {
                    jSONObject.put(Config.EVENT_PART, jSONArray);
                    try {
                        jSONObject.put(Config.EXCEPTION_PART, jSONArray);
                        return jSONObject.toString();
                    } catch (JSONException e) {
                        return null;
                    }
                } catch (JSONException e2) {
                    return null;
                }
            } catch (JSONException e3) {
                return null;
            }
        } catch (Exception e4) {
            return null;
        }
    }

    public void flush(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            synchronized (this.d) {
                jSONObject.put(Config.PRINCIPAL_PART, new JSONArray(this.d.toString()));
            }
            synchronized (this.e) {
                jSONObject.put(Config.EVENT_PART, new JSONArray(this.e.toString()));
            }
            synchronized (a) {
                jSONObject.put(Config.HEADER_PART, new JSONObject(a.toString()));
            }
            jSONObject.put(Config.PYD, b);
        } catch (Exception e) {
        }
        String string = jSONObject.toString();
        if (a()) {
            bv.c().a("[WARNING] stat cache exceed 184320 Bytes, ignored");
            return;
        }
        int length = string.getBytes().length;
        if (length >= 184320) {
            a(true);
            return;
        }
        this.h = length;
        ch.a(context, cp.r(context) + Config.STAT_CACHE_FILE_NAME, string, false);
        synchronized (this.f) {
            ch.a(context, Config.LAST_AP_INFO_FILE_NAME, this.f.toString(), false);
        }
    }

    public int getCacheFileSzie() {
        return this.h;
    }

    public String getEventPy() {
        String str = this.m.get(Config.EVENT_PY);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return replace(getSessionPy() + Config.replace + str, Config.replace);
    }

    public String getHeadSessionPy() {
        String str = this.m.get(Config.SESSION_JSON_PY);
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public JSONObject getLogData() {
        return this.j;
    }

    public String getPagePy() {
        String str = this.m.get(Config.PAGE_PY);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return replace(getSessionPy() + Config.replace + str, Config.replace);
    }

    public String getSessionPy() {
        String str = this.m.get(Config.SESSION_PY);
        String str2 = this.m.get(Config.USER_PY);
        String str3 = this.m.get(Config.UID_PY);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        return replace(replace(str + Config.replace + str3, Config.replace) + Config.replace + str2, Config.replace);
    }

    public String getTempPyd() {
        JSONObject jSONObject = this.o;
        return (jSONObject == null || jSONObject.length() <= 0) ? "" : this.o.toString();
    }

    public void init(Context context) {
        instance().loadWifiData(context);
        instance().loadStatData(context);
        instance().loadLastSession(context);
        instance().installHeader(context);
    }

    public void installHeader(Context context) {
        synchronized (a) {
            CooperService.instance().getHeadObject().installHeader(context, a);
        }
    }

    public void loadLastSession(Context context) {
        if (context == null) {
            return;
        }
        String str = cp.r(context) + Config.LAST_SESSION_FILE_NAME;
        if (ch.c(context, str)) {
            String strA = ch.a(context, str);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            ch.a(context, str, new JSONObject().toString(), false);
            putSession(strA);
            flush(context);
        }
    }

    public void loadProperty(Context context) {
        String strU = cj.a().u(context);
        if (!TextUtils.isEmpty(strU)) {
            HashMap map = new HashMap();
            try {
                JSONObject jSONObject = new JSONObject(strU);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    JSONArray jSONArray = (JSONArray) jSONObject.get(next);
                    if (jSONArray != null && jSONArray.length() > 0) {
                        map.put(next, jSONArray.optString(0));
                    }
                }
            } catch (Exception e) {
            }
            if (map.size() > 0) {
                setPydProperty(context, cq.a(map), "1", "1");
            }
        }
        String strQ = cj.a().q(context);
        if (TextUtils.isEmpty(strQ)) {
            return;
        }
        HashMap map2 = new HashMap();
        map2.put("uid_", strQ);
        setPydProperty(context, cq.a(map2), "1", PropertyType.UID_PROPERTRY);
    }

    public void loadStatData(Context context) {
        JSONObject jSONObject;
        if (context == null) {
            return;
        }
        String str = cp.r(context) + Config.STAT_CACHE_FILE_NAME;
        if (ch.c(context, str)) {
            String strA = ch.a(context, str);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            try {
                jSONObject = new JSONObject(strA);
            } catch (Exception e) {
                jSONObject = null;
            }
            if (jSONObject == null) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(Config.PRINCIPAL_PART);
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jCurrentTimeMillis - jSONObject2.getLong("s") <= Config.MAX_LOG_DATA_EXSIT_TIME) {
                            putSession(jSONObject2);
                        }
                    }
                }
            } catch (Exception e2) {
            }
            try {
                JSONArray jSONArray2 = jSONObject.getJSONArray(Config.EVENT_PART);
                if (jSONArray2 != null) {
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                        if (jCurrentTimeMillis - jSONObject3.getLong("t") <= Config.MAX_LOG_DATA_EXSIT_TIME) {
                            putEvent(context, jSONObject3);
                        }
                    }
                }
            } catch (Exception e3) {
            }
            try {
                JSONObject jSONObject4 = jSONObject.getJSONObject(Config.HEADER_PART);
                if (jSONObject4 != null) {
                    synchronized (a) {
                        a = jSONObject4;
                    }
                }
            } catch (Exception e4) {
            }
            try {
                String strOptString = jSONObject.optString(Config.PYD);
                if (TextUtils.isEmpty(strOptString)) {
                    strOptString = "";
                }
                b = strOptString;
            } catch (Exception e5) {
            }
        }
    }

    public void loadWifiData(Context context) {
        if (context != null && ch.c(context, Config.LAST_AP_INFO_FILE_NAME)) {
            try {
                JSONArray jSONArray = new JSONArray(ch.a(context, Config.LAST_AP_INFO_FILE_NAME));
                int length = jSONArray.length();
                if (length >= 10) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i = length - 10; i < length; i++) {
                        jSONArray2.put(jSONArray.get(i));
                    }
                    jSONArray = jSONArray2;
                }
                String strL = cp.l(1, context);
                if (!TextUtils.isEmpty(strL)) {
                    jSONArray.put(strL);
                }
                synchronized (this.f) {
                    this.f = jSONArray;
                }
            } catch (JSONException e) {
            }
        }
    }

    public void putEvent(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (a(jSONObject.toString())) {
            bv.c().b("[WARNING] data to put exceed limit, ignored");
            return;
        }
        synchronized (this.e) {
            EventAnalysis.doEventMerge(this.e, jSONObject);
        }
    }

    public void putSession(Session session) {
        putSession(session.constructJSONObject());
    }

    public void putSession(String str) {
        if (TextUtils.isEmpty(str) || str.equals(new JSONObject().toString())) {
            return;
        }
        try {
            putSession(new JSONObject(str));
        } catch (JSONException e) {
        }
    }

    public void putSession(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (a(jSONObject.toString())) {
            bv.c().b("[WARNING] data to put exceed limit, ignored");
            return;
        }
        synchronized (this.d) {
            try {
                this.d.put(this.d.length(), jSONObject);
            } catch (JSONException e) {
            }
        }
    }

    public String replace(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            if (str.startsWith(str2)) {
                str = str.replaceFirst(str2, "");
            }
            if (str.endsWith(str2)) {
                str = str.substring(0, str.length() - 1);
            }
            str = str.replace("null", "");
            if (TextUtils.isEmpty(str)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public void saveLogData(Context context, boolean z, boolean z2, long j, boolean z3) {
        saveLogData(context, z, z2, j, z3, null);
    }

    public void saveLogData(Context context, boolean z, boolean z2, long j, boolean z3, JSONObject jSONObject) {
        HeadObject headObject = CooperService.instance().getHeadObject();
        if (headObject != null) {
            synchronized (a) {
                if (TextUtils.isEmpty(headObject.e)) {
                    headObject.installHeader(context, a);
                } else {
                    headObject.updateHeader(context, a);
                }
            }
            if (TextUtils.isEmpty(headObject.e)) {
                bv.c().c("[WARNING] 无法找到有效APP Key, 请参考文档配置");
                return;
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        synchronized (a) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                String strOptString = a.optString("at");
                String strOptString2 = a.optString(Config.CUSTOM_USER_ID);
                if (!TextUtils.isEmpty(strOptString) && strOptString.equals(PropertyType.UID_PROPERTRY)) {
                    if (strOptString2.equals(CooperService.instance().getLastUserId(context))) {
                        a.put(Config.UID_CHANGE, "");
                    } else {
                        a.put(Config.UID_CHANGE, strOptString2);
                    }
                    CooperService.instance().setLastUserId(context, strOptString2);
                }
                a.put("t", jCurrentTimeMillis);
                a.put(Config.SEQUENCE_INDEX, z ? 0 : 1);
                a.put("ss", j);
                synchronized (this.f) {
                    a.put(Config.WIFI_LOCATION, this.f);
                }
                a.put("sign", CooperService.instance().getUUID());
                b(context, a, jSONObject);
                jSONObject2.put(Config.HEADER_PART, a);
                synchronized (this.d) {
                    try {
                        try {
                            jSONObject2.put(Config.PRINCIPAL_PART, this.d);
                            synchronized (this.e) {
                                try {
                                    jSONObject2.put(Config.EVENT_PART, this.e);
                                    try {
                                        jSONObject2.put(Config.EXCEPTION_PART, new JSONArray());
                                        try {
                                            jSONObject2.put(Config.PYD, b);
                                            a(context, jSONObject2, z2);
                                            b(jSONObject2);
                                            a(context, jSONObject2);
                                            a(context, jSONObject2.toString(), z, z3);
                                            this.j = jSONObject2;
                                            clearCache(context);
                                            if (!this.l) {
                                                this.l = true;
                                                updatePyd("");
                                                loadProperty(context);
                                                updatePyd(getTempPyd());
                                            }
                                        } catch (JSONException e) {
                                        }
                                    } catch (JSONException e2) {
                                    }
                                } catch (JSONException e3) {
                                }
                            }
                        } catch (JSONException e4) {
                        }
                    } finally {
                    }
                }
            } catch (Exception e5) {
            }
        }
    }

    public void saveLogDataAndSendForRaven(Context context) {
        synchronized (this.k) {
        }
    }

    public void sendDataForDueros(Context context) {
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0134 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0135  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setPydProperty(android.content.Context r27, java.util.Map<java.lang.String, java.lang.String> r28, java.lang.String r29, java.lang.String r30) {
        /*
            Method dump skipped, instruction units count: 649
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.setPydProperty(android.content.Context, java.util.Map, java.lang.String, java.lang.String):void");
    }

    public void updatePropertyKey(String str, StringBuffer stringBuffer, String str2, int i) {
        if (i >= 0 && str.equals(str2)) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append(Config.replace);
            }
            stringBuffer.append(i);
        }
    }

    public void updatePyd(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            b = str;
            bq.a().a(str);
        } catch (Exception e) {
        }
    }
}

package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Session {
    private volatile long a = 0;
    private volatile long b = 0;
    private volatile long c = 0;
    private volatile long d = 0;
    private volatile long e = 0;
    private volatile int f = 0;
    private List<a> g = new ArrayList();
    private volatile JSONObject h = null;

    static class a {
        private String a;
        private String b;
        private String c;
        private long d;
        private long e;
        private boolean f;
        private JSONObject g;
        private boolean h;
        private String i;

        public a(String str, String str2, String str3, long j, long j2, boolean z, ExtraInfo extraInfo, boolean z2, String str4) {
            this.b = str;
            this.c = str2;
            this.a = str3;
            this.d = j;
            this.e = j2;
            this.f = z;
            this.i = str4;
            this.g = extraInfo != null ? extraInfo.dumpToJson() : new JSONObject();
            this.h = z2;
        }

        public String a() {
            return this.b;
        }

        public void a(a aVar) {
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
            this.e = aVar.e;
            this.f = aVar.f;
            this.g = aVar.g;
            this.h = aVar.h;
            this.i = aVar.i;
        }

        public String b() {
            return this.c;
        }

        public long c() {
            return this.d;
        }

        public long d() {
            return this.e;
        }

        public JSONObject e() {
            return this.g;
        }

        public boolean f() {
            return this.f;
        }

        public String g() {
            return this.i;
        }
    }

    private void a(List<a> list, a aVar) {
        try {
            if (!cn.a().c() || list == null || aVar == null) {
                return;
            }
            int size = list.size();
            if (size == 0) {
                list.add(aVar);
                return;
            }
            a aVar2 = list.get(size - 1);
            if (!TextUtils.isEmpty(aVar2.a) && !TextUtils.isEmpty(aVar.a)) {
                if (aVar2.a.equals(aVar.a) && aVar2.f != aVar.f) {
                    if (aVar2.f) {
                        aVar2.a(aVar);
                    }
                    return;
                }
                list.add(aVar);
                return;
            }
            list.add(aVar);
        } catch (Exception e) {
        }
    }

    public static JSONObject getPVJson(a aVar, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", aVar.a());
            jSONObject.put("d", aVar.c());
            long jD = aVar.d() - j;
            if (jD < 0) {
                jD = 0;
            }
            jSONObject.put("ps", jD);
            jSONObject.put("t", aVar.b());
            int i = 1;
            jSONObject.put("at", aVar.f() ? 1 : 0);
            JSONObject jSONObjectE = aVar.e();
            if (jSONObjectE != null && jSONObjectE.length() != 0) {
                jSONObject.put("ext", jSONObjectE);
            }
            if (!aVar.h) {
                i = 0;
            }
            jSONObject.put("h5", i);
            jSONObject.put(Config.PY, aVar.g());
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public void addPageView(a aVar) {
        a(this.g, aVar);
    }

    public void addPageView(String str, String str2, String str3, long j, long j2, boolean z, ExtraInfo extraInfo, boolean z2, String str4) {
        a(this.g, new a(str, str2, str3, j, j2, z, extraInfo, z2, str4));
    }

    public JSONObject constructJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", this.a);
            jSONObject.put("e", this.b);
            jSONObject.put("i", this.e);
            jSONObject.put("c", 1);
            jSONObject.put(Config.SESSTION_TRACK_START_TIME, this.c == 0 ? this.a : this.c);
            jSONObject.put(Config.SESSTION_TRACK_END_TIME, this.d == 0 ? this.b : this.d);
            jSONObject.put(Config.SESSTION_TRIGGER_CATEGORY, this.f);
            if (this.h != null && this.h.length() != 0) {
                jSONObject.put(Config.LAUNCH, this.h);
            }
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.g.size(); i++) {
                jSONArray.put(getPVJson(this.g.get(i), this.a));
            }
            if (cn.a().c()) {
                jSONObject.put("p", jSONArray);
            }
            jSONObject.put(Config.PY, DataCore.instance().getSessionPy());
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public JSONObject getPageSessionHead() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", this.a);
            jSONObject.put("e", this.b);
            jSONObject.put("i", this.e);
            jSONObject.put("c", 1);
            jSONObject.put(Config.SESSTION_TRACK_START_TIME, this.c == 0 ? this.a : this.c);
            jSONObject.put(Config.SESSTION_TRACK_END_TIME, this.d == 0 ? this.b : this.d);
            jSONObject.put(Config.SESSTION_TRIGGER_CATEGORY, this.f);
            jSONObject.put(Config.PY, DataCore.instance().getSessionPy());
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public long getStartTime() {
        return this.a;
    }

    public long getTrackEndTime() {
        return this.d;
    }

    public long getTrackStartTime() {
        return this.c;
    }

    public boolean hasEnd() {
        return this.b > 0;
    }

    public boolean hasStart() {
        return this.a > 0;
    }

    public void reset() {
        this.a = 0L;
        this.b = 0L;
        this.c = 0L;
        this.d = 0L;
        this.f = 0;
        this.g.clear();
    }

    public void setEndTime(long j) {
        this.b = j;
    }

    public void setInvokeType(int i) {
        this.f = i;
    }

    public void setLaunchInfo(JSONObject jSONObject) {
        this.h = jSONObject;
    }

    public void setStartTime(long j) {
        if (this.a > 0) {
            return;
        }
        this.a = j;
        this.e = j;
    }

    public void setTrackEndTime(long j) {
        this.d = j;
    }

    public void setTrackStartTime(long j) {
        if (this.c > 0) {
            return;
        }
        this.c = j;
    }

    public String toString() {
        return constructJSONObject().toString();
    }
}

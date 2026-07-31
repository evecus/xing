package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.baidu.mobstat.cf;
import com.google.android.material.badge.BadgeDrawable;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CooperService implements ICooperService {
    private static CooperService a;
    private HeadObject b = new HeadObject();
    private boolean c = true;
    private String d = PropertyType.UID_PROPERTRY;
    private boolean e = false;
    private boolean f = true;
    private int g = 1;
    private int h = 10;

    private static String a(Context context) {
        if (!cn.a().b()) {
            return Config.DEF_MAC_ID;
        }
        String strJ = cp.j(context);
        return !TextUtils.isEmpty(strJ) ? strJ.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "") : Config.DEF_MAC_ID;
    }

    private String a(Context context, boolean z) {
        String strB = z ? b(context) : a(context);
        return TextUtils.isEmpty(strB) ? "" : strB;
    }

    private static String b(Context context) {
        if (!cn.a().b()) {
            return "";
        }
        String strI = cp.i(context);
        return !TextUtils.isEmpty(strI) ? strI.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "") : strI;
    }

    private String c(Context context) {
        String strE = cj.a().e(context);
        if (!TextUtils.isEmpty(strE) && !strE.equals(Config.NULL_DEVICE_ID)) {
            return strE;
        }
        String str = "hol" + (new Date().getTime() + "").hashCode() + "mes";
        cj.a().a(context, str);
        return str;
    }

    private String d(Context context) {
        try {
            if (this.b.l == null || this.b.l.equals("")) {
                boolean zG = cj.a().g(context);
                if (zG) {
                    this.b.l = cj.a().f(context);
                }
                if (!zG || this.b.l == null || this.b.l.equals("")) {
                    this.b.l = cp.a(context, Config.CHANNEL_META_NAME);
                }
            }
        } catch (Exception e) {
        }
        return this.b.l;
    }

    public static synchronized CooperService instance() {
        if (a == null) {
            a = new CooperService();
        }
        return a;
    }

    @Override // com.baidu.mobstat.ICooperService
    public boolean checkWifiLocationSetting(Context context) {
        return "true".equalsIgnoreCase(cp.a(context, Config.GET_WIFI_LOCATION));
    }

    public void closeTrace() {
        this.e = true;
    }

    public void deleteCacheImei(Context context) {
        try {
            String strT = cj.a().t(context);
            if (TextUtils.isEmpty(strT)) {
                return;
            }
            String str = new String(cf.b.b(1, ci.a(strT.getBytes())));
            if (TextUtils.isEmpty(str) || str.contains("hol") || str.contains("0200")) {
                return;
            }
            cj.a().l(context, "");
        } catch (Throwable th) {
        }
    }

    public void enableDeviceMac(Context context, boolean z) {
        cj.a().d(context, z);
    }

    public boolean filterCuid(Context context, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = "";
        String strReplace = str.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        if (!strReplace.equals(Config.DEF_MAC_ID.replace(Config.TRACE_TODAY_VISIT_SPLIT, ""))) {
            this.b.i = getSecretValue(strReplace);
            return true;
        }
        if (TextUtils.isEmpty(this.b.f)) {
            this.b.i = getSecretValue(c(context));
            return true;
        }
        try {
            str2 = new String(cf.b.b(1, ci.a(this.b.f.getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str2)) {
            this.b.i = getSecretValue(c(context));
            z = true;
        } else {
            this.b.i = getSecretValue(strReplace);
        }
        return z;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getAppChannel(Context context) {
        return d(context);
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getAppKey(Context context) {
        if (this.b.e == null) {
            this.b.e = cp.a(context, Config.APPKEY_META_NAME);
        }
        return this.b.e;
    }

    @Override // com.baidu.mobstat.ICooperService
    public int getAppVersionCode(Context context) {
        if (this.b.g == -1) {
            this.b.g = cp.f(context);
        }
        return this.b.g;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(this.b.h)) {
            this.b.h = cp.g(context);
        }
        return this.b.h;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getCUID(Context context, boolean z) {
        cj.a().b(context, "");
        if (this.b.f == null || "".equalsIgnoreCase(this.b.f)) {
            try {
                this.b.f = cq.a(context);
                Matcher matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(this.b.f);
                this.b.f = matcher.replaceAll("");
                HeadObject headObject = this.b;
                headObject.f = getSecretValue(headObject.f);
            } catch (Exception e) {
            }
        }
        if (z) {
            return this.b.f;
        }
        try {
            String str = this.b.f;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return new String(cf.b.b(1, ci.a(str.getBytes())));
        } catch (Exception e2) {
            return null;
        }
    }

    public int getCollectTitleMaxLevel() {
        return this.g;
    }

    public String getDevicImei(Context context) {
        cn.a().b();
        return "";
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getDeviceId(TelephonyManager telephonyManager, Context context) {
        boolean z;
        String str;
        boolean z2;
        if (!TextUtils.isEmpty(this.b.i)) {
            return this.b.i;
        }
        if (cj.a().i(context)) {
            this.b.i = getMacIdForTv(context);
            return this.b.i;
        }
        String strT = cj.a().t(context);
        if (!TextUtils.isEmpty(strT)) {
            try {
                str = new String(cf.b.b(1, ci.a(strT.getBytes())));
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
                str = "";
            }
            if (!z) {
                try {
                    str = new String(cf.b.b(2, ci.a(strT.getBytes())));
                    z2 = true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    z2 = false;
                }
                if (!z2) {
                    this.b.i = strT;
                    return this.b.i;
                }
                if (TextUtils.isEmpty(str)) {
                    strT = "";
                    str = strT;
                } else if (str.contains(BadgeDrawable.DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX) || str.contains("=") || str.length() > 30) {
                    strT = "";
                    str = strT;
                }
            } else if (TextUtils.isEmpty(str)) {
                strT = "";
                str = strT;
            } else if (str.contains(BadgeDrawable.DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX) || str.contains("=") || str.length() > 30) {
                strT = "";
                str = strT;
            }
            if (!TextUtils.isEmpty(str)) {
                filterCuid(context, str, true);
                return this.b.i;
            }
        }
        if (telephonyManager != null && cn.a().b()) {
            Pattern patternCompile = Pattern.compile("\\s*|\t|\r|\n");
            try {
                String deviceId = ch.e(context, "android.permission.READ_PHONE_STATE") ? telephonyManager.getDeviceId() : "";
                if (deviceId != null) {
                    strT = patternCompile.matcher(deviceId).replaceAll("");
                }
            } catch (Exception e3) {
            }
        }
        if (TextUtils.isEmpty(strT) || strT.equals(Config.NULL_DEVICE_ID)) {
            return filterCuid(context, a(context), false) ? this.b.i : this.b.i;
        }
        this.b.i = getSecretValue(strT);
        return this.b.i;
    }

    public int getEvAutoSize() {
        return this.h;
    }

    public HeadObject getHeadObject() {
        return this.b;
    }

    public JSONObject getHeaderExt(Context context) {
        String strK = cj.a().k(context);
        if (!TextUtils.isEmpty(strK)) {
            try {
                return new JSONObject(strK);
            } catch (JSONException e) {
            }
        }
        return null;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getHost() {
        return Config.LOG_SEND_URL;
    }

    public String getLastUserId(Context context) {
        return cj.a().r(context);
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getLinkedWay(Context context) {
        if (TextUtils.isEmpty(this.b.p)) {
            this.b.p = cp.o(context);
        }
        return this.b.p;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getMTJSDKVersion() {
        return "4.0.11.0";
    }

    public String getMacIdForTv(Context context) {
        if (!cn.a().b()) {
            return "";
        }
        if (!TextUtils.isEmpty(this.b.r)) {
            return this.b.r;
        }
        String strJ = cj.a().j(context);
        if (!TextUtils.isEmpty(strJ)) {
            this.b.r = strJ;
            return this.b.r;
        }
        String strH = cp.h(1, context);
        if (TextUtils.isEmpty(strH)) {
            this.b.r = "";
            return this.b.r;
        }
        this.b.r = strH;
        cj.a().f(context, strH);
        return this.b.r;
    }

    public String getManufacturer() {
        if (TextUtils.isEmpty(this.b.o)) {
            this.b.o = android.os.Build.MANUFACTURER;
        }
        return this.b.o;
    }

    public String getOSSysVersion() {
        if (TextUtils.isEmpty(this.b.c)) {
            this.b.c = Build.VERSION.RELEASE;
        }
        return this.b.c;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getOSVersion() {
        if (TextUtils.isEmpty(this.b.b)) {
            this.b.b = Integer.toString(Build.VERSION.SDK_INT);
        }
        return this.b.b;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getOperator(TelephonyManager telephonyManager) {
        if (!cn.a().b()) {
            return "";
        }
        if (TextUtils.isEmpty(this.b.m)) {
            this.b.m = telephonyManager.getNetworkOperator();
        }
        return this.b.m;
    }

    public String getPhoneAddress(Context context, boolean z) {
        String strReplace = Config.DEF_MAC_ID.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        if (!z) {
            return getSecretValue(strReplace);
        }
        if (!TextUtils.isEmpty(this.b.q)) {
            return this.b.q;
        }
        String strH = cj.a().h(context);
        if (!TextUtils.isEmpty(strH)) {
            this.b.q = strH;
            return this.b.q;
        }
        String strA = a(context, z);
        if (TextUtils.isEmpty(strA) || strReplace.equals(strA)) {
            this.b.q = "";
            return this.b.q;
        }
        this.b.q = getSecretValue(strA);
        cj.a().e(context, this.b.q);
        return this.b.q;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getPhoneModel() {
        if (TextUtils.isEmpty(this.b.n)) {
            this.b.n = android.os.Build.MODEL;
        }
        return this.b.n;
    }

    public String getPlainDeviceIdForCar(Context context) {
        String strOptUUID = CarUUID.optUUID(context);
        if (TextUtils.isEmpty(strOptUUID)) {
            strOptUUID = c(context);
        }
        return TextUtils.isEmpty(strOptUUID) ? "" : strOptUUID;
    }

    public String getPlatformType() {
        return this.d;
    }

    public JSONObject getPushId(Context context) {
        String strL = cj.a().l(context);
        if (!TextUtils.isEmpty(strL)) {
            try {
                return new JSONObject(strL);
            } catch (JSONException e) {
            }
        }
        return null;
    }

    @Override // com.baidu.mobstat.ICooperService
    public String getSecretValue(String str) {
        return cf.b.c(1, str.getBytes());
    }

    @Override // com.baidu.mobstat.ICooperService
    public int getTagValue() {
        return 1;
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String getUserId(Context context) {
        return cj.a().q(context);
    }

    @Override // com.baidu.mobstat.ICooperService
    public void installHeader(Context context, JSONObject jSONObject) {
        this.b.installHeader(context, jSONObject);
    }

    public boolean isCloseTrace() {
        return this.e;
    }

    public boolean isDeviceMacEnabled(Context context) {
        return cj.a().m(context);
    }

    public boolean isEnableBplus(Context context) {
        return cj.a().x(context);
    }

    public boolean isEnableDownloadJs() {
        return this.f;
    }

    public boolean isEnabledAutoEvent() {
        return this.c;
    }

    public void resetHeadSign() {
        this.b.x = instance().getUUID();
    }

    public void setAppVersionName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.b.h = str;
    }

    public void setCollectTitleMaxLevel(int i) {
        this.g = i;
    }

    public void setEnableAutoEvent(boolean z) {
        this.c = z;
    }

    public void setEnableBplus(Context context, boolean z) {
        cj.a().e(context, z);
    }

    public void setEnableDownloadJs(boolean z) {
        this.f = z;
    }

    public void setEvAutoSize(int i) {
        this.h = i;
    }

    public void setHeaderExt(Context context, ExtraInfo extraInfo) {
        JSONObject jSONObject = new JSONObject();
        if (extraInfo != null) {
            jSONObject = extraInfo.dumpToJson();
        }
        this.b.setHeaderExt(jSONObject);
        cj.a().g(context, jSONObject.toString());
        bv.c().a(extraInfo != null ? "Set global ExtraInfo: " + jSONObject : "Clear global ExtraInfo");
    }

    public void setHeaderPy(String str) {
        this.b.setHeaderPy(str);
    }

    public void setLastUserId(Context context, String str) {
        cj.a().j(context, str);
    }

    public void setPlatformType(int i) {
        this.d = i + "";
    }

    public void setPushId(Context context, String str, String str2, String str3) {
        JSONObject pushId = getPushId(context);
        if (pushId == null) {
            pushId = new JSONObject();
        }
        try {
            if (TextUtils.isEmpty(str3)) {
                pushId.remove(str);
            } else {
                pushId.put(str, str3);
            }
        } catch (Exception e) {
        }
        this.b.setPushInfo(pushId);
        cj.a().h(context, pushId.toString());
        bv.c().a(str3 != null ? "Set platform:" + str2 + " pushId: " + str3 : "Clear platform:" + str2 + " pushId");
    }

    public void setStartType(boolean z) {
        this.b.setStartType(z);
    }

    public void setUserId(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        cj.a().i(context, str);
        this.b.setUserId(str);
        bv.c().a("Set user id " + str);
    }

    public void setUserProperty(Context context, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        boolean z = false;
        try {
        } catch (Exception e) {
            bv.c().c("[Exception] " + e.getMessage());
            e.printStackTrace();
        }
        if (map == null) {
            cj.a().m(context, "");
            this.b.setUserProperty("");
            return;
        }
        if (map.size() > 100) {
            bv.c().c("[WARNING] setUserProperty failed,map size can not over 100 !");
            return;
        }
        boolean z2 = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            JSONArray jSONArray = new JSONArray();
            String key = entry.getKey();
            String value = entry.getValue();
            if (TextUtils.isEmpty(key) || value == null) {
                bv.c().c("[WARNING] setUserProperty failed,key or value can not null !");
                z2 = false;
            } else {
                if (key.length() <= 256 && (TextUtils.isEmpty(value) || value.length() <= 256)) {
                    jSONArray.put(value);
                    jSONArray.put("1");
                    jSONObject.put(key, jSONArray);
                }
                bv.c().c("[WARNING] setUserProperty failed,key or value can not over 256 bytes !");
                z2 = false;
            }
        }
        z = z2;
        if (z) {
            cj.a().m(context, jSONObject.toString());
            this.b.setUserProperty(jSONObject.toString());
        }
    }

    public void setZid(String str) {
    }
}

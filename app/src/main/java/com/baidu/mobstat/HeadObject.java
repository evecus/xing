package com.baidu.mobstat;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HeadObject {
    String A;
    int B;
    String D;
    String b;
    String c;
    String h;
    String i;
    int j;
    int k;
    String m;
    String n;
    String o;
    String p;
    String q;
    String r;
    String s;
    String t;
    String u;
    String v;
    String w;
    String x;
    JSONObject y;
    JSONObject z;
    boolean a = false;
    String d = PropertyType.UID_PROPERTRY;
    String e = null;
    String f = null;
    int g = -1;
    String l = null;
    String C = "";
    String E = "";

    private synchronized void a(Context context) {
        if (this.a) {
            return;
        }
        ch.e(context, "android.permission.READ_PHONE_STATE");
        ch.e(context, "android.permission.INTERNET");
        ch.e(context, "android.permission.ACCESS_NETWORK_STATE");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.b = CooperService.instance().getOSVersion();
        this.c = CooperService.instance().getOSSysVersion();
        this.n = CooperService.instance().getPhoneModel();
        this.o = CooperService.instance().getManufacturer();
        this.x = CooperService.instance().getUUID();
        this.y = CooperService.instance().getHeaderExt(context);
        this.z = CooperService.instance().getPushId(context);
        this.f = CooperService.instance().getCUID(context, true);
        this.i = CooperService.instance().getDeviceId(telephonyManager, context);
        this.d = cj.a().i(context) ? "1" : PropertyType.UID_PROPERTRY;
        if (cp.t(context)) {
            this.d = "2";
        }
        this.d += "-14";
        if (cn.a().b()) {
            try {
                this.q = CooperService.instance().getPhoneAddress(context, CooperService.instance().isDeviceMacEnabled(context));
            } catch (Exception e) {
            }
            try {
                this.s = cp.k(1, context);
            } catch (Exception e2) {
            }
        }
        try {
            this.t = cp.a(context, 1);
        } catch (Exception e3) {
        }
        try {
            this.m = CooperService.instance().getOperator(telephonyManager);
        } catch (Exception e4) {
        }
        try {
            this.j = cp.c(context);
            this.k = cp.d(context);
            if (context.getResources().getConfiguration().orientation == 2) {
                int i = this.j;
                int i2 = this.k;
                int i3 = i ^ i2;
                this.j = i3;
                int i4 = i2 ^ i3;
                this.k = i4;
                this.j = i3 ^ i4;
            }
        } catch (Exception e5) {
        }
        this.l = CooperService.instance().getAppChannel(context);
        this.e = CooperService.instance().getAppKey(context);
        try {
            this.g = CooperService.instance().getAppVersionCode(context);
            this.h = CooperService.instance().getAppVersionName(context);
        } catch (Exception e6) {
        }
        try {
            this.p = CooperService.instance().getLinkedWay(context);
        } catch (Exception e7) {
        }
        this.u = cp.b();
        this.v = android.os.Build.BOARD;
        this.w = android.os.Build.BRAND;
        this.A = CooperService.instance().getUserId(context);
        this.a = true;
        this.C = cj.a().u(context);
    }

    public synchronized void installHeader(Context context, JSONObject jSONObject) {
        a(context);
        if (jSONObject.length() > 10) {
            return;
        }
        updateHeader(context, jSONObject);
    }

    public void setHeaderExt(JSONObject jSONObject) {
        this.y = jSONObject;
    }

    public void setHeaderPy(String str) {
        this.E = str;
    }

    public void setPushInfo(JSONObject jSONObject) {
        this.z = jSONObject;
    }

    public void setStartType(boolean z) {
        if (z) {
            this.B = 1;
        } else {
            this.B = 0;
        }
    }

    public void setUserId(String str) {
        this.A = str;
    }

    public void setUserProperty(String str) {
        this.C = str;
    }

    public void setZid(String str) {
        this.D = str;
    }

    public synchronized void updateHeader(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put(Config.OS, "Android");
            int i = 0;
            jSONObject.put(Config.STAT_SDK_TYPE, 0);
            Object obj = this.b;
            if (obj == null) {
                obj = "";
            }
            jSONObject.put("s", obj);
            Object obj2 = this.c;
            if (obj2 == null) {
                obj2 = "";
            }
            jSONObject.put("sv", obj2);
            Object obj3 = this.e;
            if (obj3 == null) {
                obj3 = "";
            }
            jSONObject.put(Config.APP_KEY, obj3);
            Object obj4 = this.d;
            if (obj4 == null) {
                obj4 = PropertyType.UID_PROPERTRY;
            }
            jSONObject.put(Config.PLATFORM_TYPE, obj4);
            jSONObject.put("i", "");
            jSONObject.put("v", "4.0.11.0");
            jSONObject.put(Config.STAT_SDK_CHANNEL, 14);
            jSONObject.put(Config.APP_VERSION_CODE, this.g);
            Object obj5 = this.h;
            if (obj5 == null) {
                obj5 = "";
            }
            jSONObject.put("n", obj5);
            jSONObject.put("d", "");
            Object obj6 = this.q;
            if (obj6 == null) {
                obj6 = "";
            }
            jSONObject.put(Config.DEVICE_MAC_ID, obj6);
            Object obj7 = this.s;
            if (obj7 == null) {
                obj7 = "";
            }
            jSONObject.put(Config.DEVICE_BLUETOOTH_MAC, obj7);
            Object obj8 = this.i;
            if (obj8 == null) {
                obj8 = "";
            }
            jSONObject.put(Config.DEVICE_ID_SEC, obj8);
            Object obj9 = this.f;
            if (obj9 == null) {
                obj9 = "";
            }
            jSONObject.put(Config.CUID_SEC, obj9);
            jSONObject.put(Config.SDK_TAG, 1);
            jSONObject.put(Config.DEVICE_WIDTH, this.j);
            jSONObject.put("h", this.k);
            Object obj10 = this.t;
            if (obj10 == null) {
                obj10 = "";
            }
            jSONObject.put(Config.DEVICE_NAME, obj10);
            Object obj11 = this.l;
            if (obj11 == null) {
                obj11 = "";
            }
            jSONObject.put("c", obj11);
            Object obj12 = this.m;
            if (obj12 == null) {
                obj12 = "";
            }
            jSONObject.put(Config.OPERATOR, obj12);
            Object obj13 = this.n;
            if (obj13 == null) {
                obj13 = "";
            }
            jSONObject.put(Config.MODEL, obj13);
            Object obj14 = this.o;
            if (obj14 == null) {
                obj14 = "";
            }
            jSONObject.put(Config.MANUFACTURER, obj14);
            Object obj15 = this.p;
            if (obj15 == null) {
                obj15 = "";
            }
            jSONObject.put("l", obj15);
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put(Config.PACKAGE_NAME, cp.m(1, context));
            Object obj16 = this.u;
            if (obj16 == null) {
                obj16 = "";
            }
            jSONObject.put(Config.ROM, obj16);
            Object obj17 = this.v;
            if (obj17 == null) {
                obj17 = "";
            }
            jSONObject.put(Config.DEVICE_BOARD, obj17);
            Object obj18 = this.w;
            if (obj18 == null) {
                obj18 = "";
            }
            jSONObject.put(Config.DEVICE_BRAND, obj18);
            jSONObject.put(Config.TEST_DEVICE_ID, cp.b(context));
            if (context != null && context.getApplicationInfo() != null) {
                i = context.getApplicationInfo().targetSdkVersion;
            }
            jSONObject.put(Config.TARGET_SDK_VERSION, i);
            jSONObject.put(Config.USER_PROPERTY, this.C);
            jSONObject.put(Config.OAID, cp.a(1, context));
            jSONObject.put(Config.OUT_OAID, cp.b(1, context));
            jSONObject.put(Config.FROM, PropertyType.UID_PROPERTRY);
            jSONObject.put(Config.GAID, cp.d(1, context));
            jSONObject.put(Config.IID, cp.c(1, context));
            jSONObject.put(Config.CUID3, cp.e(1, context));
            jSONObject.put(Config.SSAID, cp.f(1, context));
            jSONObject.put(Config.PY, this.E);
            jSONObject.put(Config.PLT, CooperService.instance().getPlatformType());
            if (!TextUtils.isEmpty(this.A)) {
                JSONObject jSONObject2 = !TextUtils.isEmpty(this.C) ? new JSONObject(this.C) : new JSONObject();
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(this.A);
                jSONArray.put("1");
                jSONObject2.put("uid_", jSONArray);
                jSONObject.put(Config.USER_PROPERTY, jSONObject2.toString());
            }
            jSONObject.put(Config.UID_CHANGE, "");
            jSONObject.put("at", PropertyType.UID_PROPERTRY);
            String strR = cp.r(context);
            jSONObject.put(Config.PROCESS_LABEL, strR);
            Object objS = !TextUtils.isEmpty(strR) ? cp.s(context) : null;
            if (objS == null) {
                objS = "";
            }
            jSONObject.put(Config.PROCESS_CLASS, objS);
            Object obj19 = this.x;
            if (obj19 == null) {
                obj19 = "";
            }
            jSONObject.put("sign", obj19);
            JSONObject jSONObject3 = this.y;
            if (jSONObject3 == null || jSONObject3.length() == 0) {
                jSONObject.remove("ext");
            } else {
                jSONObject.put("ext", this.y);
            }
            if (this.z == null) {
                this.z = new JSONObject();
            }
            jSONObject.put(Config.PUSH, this.z);
            jSONObject.put(Config.CUSTOM_USER_ID, this.A);
            jSONObject.put(Config.START_TYPE, String.valueOf(this.B));
        } catch (Exception e) {
        }
    }
}

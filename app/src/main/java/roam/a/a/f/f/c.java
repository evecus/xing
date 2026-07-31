package roam.a.a.f.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.baidu.android.common.util.HanziToPinyin;
import com.baidu.mobstat.Config;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import roam.a.a.f.j.g;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static roam.a.a.f.e.a a;

    public static String c(HashMap<String, String> map, HashMap<String, String> map2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jSONObject2.put(entry.getKey(), entry.getValue());
        }
        JSONObject jSONObject3 = new JSONObject();
        for (Map.Entry<String, String> entry2 : map2.entrySet()) {
            jSONObject3.put(entry2.getKey(), entry2.getValue());
        }
        jSONObject2.put("params", jSONObject3);
        jSONObject.put("data", jSONObject2);
        return jSONObject.toString();
    }

    public static JSONObject f(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put("method", str2);
        jSONObject.put("action", jSONObject2);
        return jSONObject;
    }

    public static boolean g(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str).getJSONObject("data");
                if (jSONObject.has("params")) {
                    String strOptString = jSONObject.getJSONObject("params").optString("public_key", null);
                    if (!TextUtils.isEmpty(strOptString)) {
                        roam.a.a.f.h.b.a();
                        synchronized (roam.a.a.f.c.c.a()) {
                            if (!TextUtils.isEmpty(strOptString)) {
                                PreferenceManager.getDefaultSharedPreferences(roam.a.a.f.h.b.a().a).edit().putString("trideskey", strOptString).commit();
                                roam.a.a.f.b.a.a = strOptString;
                            }
                        }
                        return true;
                    }
                }
            } catch (JSONException e) {
            }
        }
        return false;
    }

    public final a a(Context context, String str, String str2, boolean z) throws Throwable {
        String value;
        ByteArrayOutputStream byteArrayOutputStream;
        Header[] allHeaders;
        String name;
        d dVar = new d(true);
        b bVarB = dVar.b(new a(i(), b(str, e())), true);
        roam.a.a.f.e.a aVar = a;
        if (aVar == null) {
            a = new roam.a.a.f.e.a(context, str2);
        } else if (!TextUtils.equals(str2, aVar.b)) {
            a.b = str2;
        }
        HttpResponse httpResponseA = a.a(bVarB.b, d(bVarB.a, str));
        InputStream inputStream = null;
        if (httpResponseA == null || (allHeaders = httpResponseA.getAllHeaders()) == null || allHeaders.length <= 0) {
            value = null;
        } else {
            for (Header header : allHeaders) {
                if (header != null && (name = header.getName()) != null && name.equalsIgnoreCase("msp-gzip")) {
                    value = header.getValue();
                    break;
                }
            }
            value = null;
        }
        boolean zBooleanValue = Boolean.valueOf(value).booleanValue();
        byte[] bArr = new byte[1024];
        try {
            InputStream content = httpResponseA.getEntity().getContent();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int i = content.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    } catch (Throwable th) {
                        th = th;
                        inputStream = content;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e) {
                            }
                        }
                        if (byteArrayOutputStream == null) {
                            throw th;
                        }
                        try {
                            byteArrayOutputStream.close();
                            throw th;
                        } catch (Exception e2) {
                            throw th;
                        }
                    }
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    content.close();
                } catch (Exception e3) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e4) {
                }
                a aVarA = dVar.a(new b(zBooleanValue, byteArray));
                return (aVarA != null && g(aVarA.a) && z) ? a(context, str, str2, false) : aVarA;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }

    public String b(String str, JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        String str2;
        String strG;
        roam.a.a.f.h.b bVar;
        String string;
        String str3;
        Object obj;
        String str4;
        String str5;
        String strB;
        roam.a.a.f.h.b bVarA = roam.a.a.f.h.b.a();
        roam.a.a.f.i.b bVarA2 = roam.a.a.f.i.b.a();
        JSONObject jSONObject4 = new JSONObject();
        JSONObject jSONObject5 = new JSONObject();
        for (int i = 0; i < 2; i++) {
            try {
                JSONObject jSONObject6 = new JSONObject[]{jSONObject4, jSONObject}[i];
                if (jSONObject6 != null) {
                    Iterator<String> itKeys = jSONObject6.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        jSONObject5.put(next, jSONObject6.get(next));
                    }
                }
            } catch (JSONException e) {
            }
        }
        try {
            jSONObject5.put("tid", bVarA2.a);
            roam.a.a.f.c.c cVarA = roam.a.a.f.c.c.a();
            Context context = roam.a.a.f.h.b.a().a;
            roam.a.a.f.j.a aVarA = roam.a.a.f.j.a.a(context);
            if (TextUtils.isEmpty(cVarA.a)) {
                try {
                    str2 = "Android " + Build.VERSION.RELEASE;
                    strG = g.g();
                    bVar = bVarA;
                    string = context.getResources().getConfiguration().locale.toString();
                    roam.a.a.a.b.a.e(context);
                    jSONObject3 = jSONObject5;
                } catch (Throwable th) {
                    jSONObject3 = jSONObject5;
                    jSONObject2 = jSONObject3;
                }
                try {
                    String strJ = g.j(context);
                    str3 = "utdid";
                    String string2 = Float.toString(new TextView(context).getTextSize());
                    obj = "tid";
                    StringBuilder sb = new StringBuilder();
                    str4 = "wifi";
                    sb.append("Msp/15.5.5");
                    sb.append(" (");
                    sb.append(str2);
                    sb.append(";");
                    sb.append(strG);
                    sb.append(";");
                    sb.append(string);
                    sb.append(";");
                    sb.append("https");
                    sb.append(";");
                    sb.append(strJ);
                    sb.append(";");
                    sb.append(string2);
                    cVarA.a = sb.toString();
                } catch (Throwable th2) {
                    jSONObject2 = jSONObject3;
                }
            } else {
                str3 = "utdid";
                str4 = "wifi";
                obj = "tid";
                bVar = bVarA;
                jSONObject3 = jSONObject5;
            }
            String str6 = roam.a.a.f.j.a.c(context).b;
            String strB2 = aVarA.b();
            String strD = aVarA.d();
            Context context2 = roam.a.a.f.h.b.a().a;
            SharedPreferences sharedPreferences = context2.getSharedPreferences("virtualImeiAndImsi", 0);
            String string3 = sharedPreferences.getString("virtual_imsi", null);
            if (TextUtils.isEmpty(string3)) {
                if (TextUtils.isEmpty(roam.a.a.f.i.b.a().a)) {
                    String strD2 = roam.a.a.f.h.b.a().d();
                    strB = TextUtils.isEmpty(strD2) ? roam.a.a.f.c.c.c() : strD2.substring(3, 18);
                } else {
                    strB = roam.a.a.f.j.a.a(context2).b();
                }
                string3 = strB;
                sharedPreferences.edit().putString("virtual_imsi", string3).commit();
            }
            Context context3 = roam.a.a.f.h.b.a().a;
            SharedPreferences sharedPreferences2 = context3.getSharedPreferences("virtualImeiAndImsi", 0);
            String string4 = sharedPreferences2.getString("virtual_imei", null);
            if (TextUtils.isEmpty(string4)) {
                string4 = TextUtils.isEmpty(roam.a.a.f.i.b.a().a) ? roam.a.a.f.c.c.c() : roam.a.a.f.j.a.a(context3).d();
                sharedPreferences2.edit().putString("virtual_imei", string4).commit();
            }
            cVarA.b = bVarA2.b;
            String strReplace = Build.MANUFACTURER.replace(";", HanziToPinyin.Token.SEPARATOR);
            String strReplace2 = Build.MODEL.replace(";", HanziToPinyin.Token.SEPARATOR);
            boolean zC = roam.a.a.f.h.b.c();
            String str7 = aVarA.c;
            String str8 = str4;
            WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService(str8)).getConnectionInfo();
            String ssid = connectionInfo != null ? connectionInfo.getSSID() : "-1";
            WifiInfo connectionInfo2 = ((WifiManager) context.getApplicationContext().getSystemService(str8)).getConnectionInfo();
            String bssid = connectionInfo2 != null ? connectionInfo2.getBSSID() : "00";
            StringBuilder sb2 = new StringBuilder();
            sb2.append(cVarA.a);
            sb2.append(";");
            sb2.append(str6);
            sb2.append(";");
            sb2.append("-1;-1");
            sb2.append(";");
            sb2.append("1");
            sb2.append(";");
            sb2.append(strB2);
            sb2.append(";");
            sb2.append(strD);
            sb2.append(";");
            sb2.append(cVarA.b);
            sb2.append(";");
            sb2.append(strReplace);
            sb2.append(";");
            sb2.append(strReplace2);
            sb2.append(";");
            sb2.append(zC);
            sb2.append(";");
            sb2.append(str7);
            sb2.append(";-1;-1;");
            sb2.append("sdk-and-lite");
            sb2.append(";");
            sb2.append(string3);
            sb2.append(";");
            sb2.append(string4);
            sb2.append(";");
            sb2.append(ssid);
            sb2.append(";");
            sb2.append(bssid);
            HashMap map = new HashMap();
            map.put(obj, bVarA2.a);
            String str9 = str3;
            map.put(str9, roam.a.a.f.h.b.a().d());
            try {
                str5 = (String) Executors.newFixedThreadPool(2).submit(new roam.a.a.f.c.d(cVarA, context, map)).get(3000L, TimeUnit.MILLISECONDS);
            } catch (Throwable th3) {
                roam.a.a.f.a.l.a.c("third", "GetApdidTimeout", th3);
                str5 = "";
            }
            if (!TextUtils.isEmpty(str5)) {
                sb2.append(";");
                sb2.append(str5);
            }
            sb2.append(")");
            jSONObject2 = jSONObject3;
            try {
                jSONObject2.put("user_agent", sb2.toString());
                roam.a.a.f.h.b bVar2 = bVar;
                jSONObject2.put("has_alipay", g.h(bVar2.a));
                boolean z = bVar2.a.getPackageManager().getPackageInfo("com.alipay.android.app", 128) != null;
                jSONObject2.put("has_msp_app", z);
                jSONObject2.put("external_info", str);
                jSONObject2.put("app_key", "2014052600006128");
                jSONObject2.put(str9, bVar2.d());
                jSONObject2.put("new_client_key", bVarA2.b);
                roam.a.a.f.c.c.a();
                jSONObject2.put("pa", roam.a.a.f.c.c.b(bVar2.a));
            } catch (Throwable th4) {
            }
        } catch (Throwable th5) {
            jSONObject2 = jSONObject5;
        }
        return jSONObject2.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0140  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<org.apache.http.Header> d(boolean r11, java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.f.f.c.d(boolean, java.lang.String):java.util.List");
    }

    public abstract JSONObject e();

    public String h() {
        return "4.9.0";
    }

    public String i() {
        HashMap map = new HashMap();
        map.put(Config.DEVICE_PART, Build.MODEL);
        map.put("namespace", "com.alipay.mobilecashier");
        map.put("api_name", "com.alipay.mcpay");
        map.put("api_version", h());
        return c(map, new HashMap());
    }
}

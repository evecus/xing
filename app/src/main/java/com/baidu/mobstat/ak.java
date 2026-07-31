package com.baidu.mobstat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ak {
    private ah a;
    private List<p> b;

    public ak() {
        a();
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String string = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & ExifInterface.MARKER);
            string = (hexString.length() == 1 ? new StringBuilder().append(string).append(PropertyType.UID_PROPERTRY) : new StringBuilder().append(string)).append(hexString).toString();
        }
        return string.toLowerCase();
    }

    private void a() {
        this.a = new ai(ao.a(), ao.b());
    }

    private boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(str);
        }
        HashSet hashSet2 = new HashSet();
        for (String str2 : strArr2) {
            hashSet2.add(str2);
        }
        return hashSet.equals(hashSet2);
    }

    private static byte[] a(byte[] bArr, ah ahVar) {
        ae aeVarA = ae.a();
        aeVarA.a(2, ahVar);
        return aeVarA.a(bArr);
    }

    private String[] a(Signature[] signatureArr) {
        int length = signatureArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = a(an.a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    List<p> a(Context context, Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : listQueryBroadcastReceivers) {
                if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.applicationInfo != null) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] bArrA = al.a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(bArrA));
                                p pVar = new p();
                                pVar.b = jSONObject.getInt("priority");
                                pVar.a = resolveInfo.activityInfo.applicationInfo;
                                if (context.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    pVar.d = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString("galaxy_sf");
                                    if (!TextUtils.isEmpty(string2)) {
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        int length = jSONArray.length();
                                        String[] strArr = new String[length];
                                        for (int i = 0; i < length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (a(strArr, a(packageInfo.signatures))) {
                                            byte[] bArrA2 = a(al.a(string2.getBytes()), this.a);
                                            byte[] bArrA3 = an.a(bArrA);
                                            if (bArrA2 != null && Arrays.equals(bArrA2, bArrA3)) {
                                                pVar.c = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(pVar);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new Comparator<p>() { // from class: com.baidu.mobstat.ak.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(p pVar2, p pVar3) {
                int i2 = pVar3.b - pVar2.b;
                if (i2 == 0) {
                    if (pVar2.d && pVar3.d) {
                        return 0;
                    }
                    if (pVar2.d) {
                        return -1;
                    }
                    if (pVar3.d) {
                        return 1;
                    }
                }
                return i2;
            }
        });
        return arrayList;
    }

    public boolean a(Context context) {
        List<p> listA = a(context, new Intent("com.baidu.intent.action.GALAXY").setPackage(context.getPackageName()), true);
        if (listA == null || listA.size() == 0) {
            for (int i = 0; i < 3; i++) {
                Log.w("CuidBuddyInfoManager", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            return false;
        }
        p pVar = listA.get(0);
        boolean z = pVar.c;
        if (!pVar.c) {
            for (int i2 = 0; i2 < 3; i2++) {
                Log.w("CuidBuddyInfoManager", "galaxy config err, In the release version of the signature should be matched");
            }
        }
        return z;
    }

    List<p> b(Context context) {
        List<p> list = this.b;
        if (list != null) {
            return list;
        }
        a(context);
        List<p> listA = a(context, new Intent("com.baidu.intent.action.GALAXY"), true);
        this.b = listA;
        return listA;
    }
}

package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.text.TextUtils;
import android.view.View;
import com.baidu.mobstat.cd;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bx implements cd.b {
    private String a(Activity activity, View view) {
        View viewA;
        View viewO;
        if (activity == null || view == null || (viewO = cc.o((viewA = cc.a(view, activity)))) == null) {
            return "";
        }
        String strA = bn.a().a(activity, viewA, viewO);
        return !TextUtils.isEmpty(strA) ? strA : "";
    }

    private JSONObject a(Activity activity, View view, PointF pointF) {
        if (pointF == null) {
            return null;
        }
        view.getLocationOnScreen(new int[2]);
        float f = pointF.x - r1[0];
        float f2 = pointF.y - r1[1];
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        float fB = bb.b(activity, f);
        float fB2 = bb.b(activity, f2);
        float fA = bb.a(activity, cc.p(view));
        float fA2 = bb.a(activity, cc.q(view));
        if (fA == 0.0f || fA2 == 0.0f) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator('.');
            decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            jSONObject.put(Config.EVENT_HEAT_X, decimalFormat.format(fB));
            jSONObject.put("y", decimalFormat.format(fB2));
            jSONObject.put(Config.EVENT_HEAT_XP, decimalFormat.format((fB * 100.0f) / fA));
            jSONObject.put(Config.EVENT_HEAT_YP, decimalFormat.format((fB2 * 100.0f) / fA2));
        } catch (Exception e) {
        }
        return jSONObject;
    }

    @Override // com.baidu.mobstat.cd.b
    public void a(View view, boolean z, Activity activity) {
        String str;
        if (activity == null || view == null) {
            return;
        }
        bl.a(view, activity);
        if (bw.c().b() && z) {
            bw.c().a("OnEvent view:" + view.getClass().getName() + "; content:" + cc.h(view) + "; activity:" + activity.getClass().getName());
        }
        if (ca.c().b()) {
            ca.c().a("OnEvent view:" + view.getClass().getName() + "; content:" + cc.h(view) + "; activity:" + activity.getClass().getName());
        }
        JSONArray jSONArrayA = cc.a(activity, view);
        String strF = cc.f(view);
        Map<String, String> mapG = cc.g(view);
        String strA = cc.a(view);
        Context applicationContext = activity.getApplicationContext();
        long jCurrentTimeMillis = System.currentTimeMillis();
        JSONArray jSONArray = new JSONArray();
        String name = activity.getClass().getName();
        if (z) {
            BDStatCore.instance().onEvent(applicationContext, "", strA, 1, jCurrentTimeMillis, jSONArrayA, jSONArray, name, "", strF, mapG);
        }
        JSONObject jSONObjectA = a(activity, view, bt.a().b());
        String strA2 = a(activity, view);
        String strL = cc.l(view);
        JSONArray jSONArray2 = new JSONArray();
        Map<String, String> mapA = cc.a(cc.a(view, activity), false);
        if (TextUtils.isEmpty(strA2) || mapA == null || mapA.size() <= 0) {
            str = "";
        } else {
            str = TextUtils.isEmpty(mapA.get("content")) ? "" : mapA.get("content");
        }
        bq.a().a(applicationContext, "", strL, str, 1, jCurrentTimeMillis, name, jSONArrayA, "", jSONArray, strF, mapG, jSONObjectA, strA2, jSONArray2);
    }
}

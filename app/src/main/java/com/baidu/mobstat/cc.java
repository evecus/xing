package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import com.baidu.mobstat.bo;
import com.baidu.mobstat.cl;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class cc {
    public static int a(GridView gridView) {
        int height = gridView.getHeight();
        if (gridView.getChildCount() <= 0) {
            return height;
        }
        int height2 = gridView.getChildAt(0).getHeight();
        ListAdapter adapter = gridView.getAdapter();
        int numColumns = gridView.getNumColumns();
        int iCeil = height2 * ((adapter == null || numColumns == 0) ? 1 : (int) Math.ceil(((double) adapter.getCount()) / ((double) numColumns)));
        return iCeil >= height ? iCeil : height;
    }

    public static int a(ListView listView) {
        int i = 0;
        try {
            int height = listView.getHeight();
            try {
                if (listView.getChildCount() <= 0) {
                    return height;
                }
                int height2 = listView.getChildAt(0).getHeight();
                ListAdapter adapter = listView.getAdapter();
                int count = height2 * (adapter != null ? adapter.getCount() : 1);
                return count >= height ? count : height;
            } catch (Exception e) {
                e = e;
                i = height;
                e.printStackTrace();
                return i;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static View a(Activity activity) {
        Window window;
        if (activity == null || (window = activity.getWindow()) == null) {
            return null;
        }
        return window.getDecorView();
    }

    public static View a(View view, Activity activity) {
        View viewA;
        if (view == null || activity == null) {
            return null;
        }
        try {
            viewA = a(activity);
        } catch (Exception e) {
            viewA = null;
        }
        if (viewA == null) {
            return null;
        }
        while (view != null && view != viewA && view.getParent() != null && (view.getParent() instanceof View)) {
            View view2 = (View) view.getParent();
            if (n(view2)) {
                return view;
            }
            view = view2;
        }
        return null;
    }

    public static String a(Context context) {
        ResolveInfo resolveInfoResolveActivity;
        ActivityInfo activityInfo;
        if (context == null) {
            return "";
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        try {
            resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
        } catch (Exception e) {
            resolveInfoResolveActivity = null;
        }
        if (resolveInfoResolveActivity == null || (activityInfo = resolveInfoResolveActivity.activityInfo) == null) {
            return "";
        }
        String str = activityInfo.packageName;
        return ("android".equals(str) || TextUtils.isEmpty(str)) ? "" : str;
    }

    public static String a(Bitmap bitmap) throws Throwable {
        byte[] bArrC = c(bitmap);
        if (bArrC == null) {
            return "";
        }
        try {
            return ci.b(bArrC);
        } catch (Exception e) {
            return "";
        }
    }

    public static String a(View view) {
        CharSequence text;
        String string = "";
        if (view == null) {
            return "";
        }
        if (view instanceof TextView) {
            if (!(view instanceof EditText) && (text = ((TextView) view).getText()) != null) {
                string = text.toString();
            }
            if (view instanceof Switch) {
                Switch r3 = (Switch) view;
                CharSequence textOn = r3.isChecked() ? r3.getTextOn() : r3.getTextOff();
                if (textOn != null) {
                    string = textOn.toString();
                }
            }
        } else if (view instanceof Spinner) {
            Spinner spinner = (Spinner) view;
            Object selectedItem = spinner.getSelectedItem();
            if (selectedItem == null || !(selectedItem instanceof String)) {
                return a(spinner.getSelectedView());
            }
            string = (String) selectedItem;
        }
        byte[] bytes = string.getBytes();
        return bytes.length > 4096 ? new String(Arrays.copyOf(bytes, 4096)) : string;
    }

    public static String a(View view, View view2) {
        if (view != null && view != view2) {
            ViewParent parent = view.getParent();
            if (parent == null || !(parent instanceof ViewGroup)) {
                return String.valueOf(0);
            }
            Class<?> cls = view.getClass();
            if (cls == null) {
                return String.valueOf(0);
            }
            String strB = b(cls);
            if (TextUtils.isEmpty(strB)) {
                return String.valueOf(0);
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            int i = 0;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt != null) {
                    if (childAt == view) {
                        break;
                    }
                    if (childAt.getClass() != null && strB.equals(b(childAt.getClass()))) {
                        i++;
                    }
                }
            }
            return String.valueOf(i);
        }
        return String.valueOf(0);
    }

    public static String a(View view, String str) {
        RecyclerView parent;
        if (TextUtils.isEmpty(str) || view == null || (parent = view.getParent()) == null || !(parent instanceof View)) {
            return "";
        }
        RecyclerView recyclerView = (View) parent;
        if (ListView.class.getSimpleName().equals(str)) {
            try {
                return (!(recyclerView instanceof ListView) || view.getParent() == null) ? "" : String.valueOf(((ListView) recyclerView).getPositionForView(view));
            } catch (Throwable th) {
                return "";
            }
        }
        if (GridView.class.getSimpleName().equals(str)) {
            try {
                return (!(recyclerView instanceof GridView) || view.getParent() == null) ? "" : String.valueOf(((GridView) recyclerView).getPositionForView(view));
            } catch (Throwable th2) {
                return "";
            }
        }
        if (!androidx.recyclerview.widget.RecyclerView.TAG.equals(str)) {
            return "";
        }
        try {
            return String.valueOf(recyclerView.getChildLayoutPosition(view));
        } catch (Throwable th3) {
            return "";
        }
    }

    public static String a(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        Package r1 = cls.getPackage();
        String name = r1 != null ? r1.getName() : "";
        return name == null ? "" : name;
    }

    private static String a(Class<?> cls, boolean z) {
        if (!cls.isAnonymousClass()) {
            return z ? cls.getSimpleName() : cls.getName();
        }
        Class<? super Object> superclass = cls.getSuperclass();
        return superclass != null ? z ? superclass.getSimpleName() : superclass.getName() : "";
    }

    public static String a(String str) {
        String strA = bo.a().a(str, bo.a.b);
        return strA == null ? "" : strA;
    }

    public static String a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                sb.append("/" + jSONObject.getString("p") + "[" + jSONObject.getString("i") + "]");
            } catch (Exception e) {
                return "";
            }
        }
        return sb.toString();
    }

    public static Map<String, String> a(View view, boolean z) {
        HashMap map = new HashMap();
        if (view == null) {
            return map;
        }
        Object tag = view.getTag(-97003);
        String str = (tag == null || !(tag instanceof String)) ? "" : (String) tag;
        Map<String, String> mapB = b(view, z);
        if (TextUtils.isEmpty(str)) {
            map.put(Config.FEED_LIST_ITEM_TITLE, mapB.get(Config.FEED_LIST_ITEM_TITLE));
        } else {
            map.put(Config.FEED_LIST_ITEM_TITLE, str);
        }
        map.put("content", mapB.get("content"));
        return map;
    }

    public static JSONArray a(Activity activity, View view) {
        View viewA;
        JSONArray jSONArray = new JSONArray();
        if (activity == null || view == null) {
            return jSONArray;
        }
        try {
            viewA = a(activity);
        } catch (Exception e) {
            viewA = null;
        }
        if (viewA == null) {
            return jSONArray;
        }
        while (view != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("p", m(view));
                String strC = c(view);
                if (TextUtils.isEmpty(strC)) {
                    String strB = "";
                    Object parent = view.getParent();
                    if (parent != null && (parent instanceof View)) {
                        strB = b((View) parent);
                    }
                    strC = a(view, strB);
                    if (TextUtils.isEmpty(strC)) {
                        strC = a(view, viewA);
                    }
                }
                jSONObject.put("i", strC);
                jSONObject.put("t", b(view));
                jSONArray.put(jSONObject);
                Object parent2 = view.getParent();
                if (parent2 == null || view == viewA || !(parent2 instanceof View) || x(view) || jSONArray.length() > 1000) {
                    break;
                }
                view = (View) parent2;
            } catch (Exception e2) {
                jSONArray = new JSONArray();
            }
        }
        JSONArray jSONArray2 = new JSONArray();
        try {
            for (int length = jSONArray.length() - 1; length >= 0; length--) {
                jSONArray2.put(jSONArray.get(length));
            }
        } catch (Exception e3) {
        }
        return jSONArray2;
    }

    private static void a(View view, LinkedHashMap<View, Integer> linkedHashMap) {
        if (view == null) {
            return;
        }
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (textView.getVisibility() == 0) {
                linkedHashMap.put(view, Integer.valueOf((int) (textView.getTextSize() * 10.0f)));
                return;
            }
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                a(viewGroup.getChildAt(i), linkedHashMap);
            }
        }
    }

    public static boolean a(Context context, String str) {
        PackageManager packageManager;
        List<ResolveInfo> listQueryIntentActivities;
        if (context == null || TextUtils.isEmpty(str) || (packageManager = context.getPackageManager()) == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        try {
            listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        } catch (Exception e) {
            listQueryIntentActivities = null;
        }
        if (listQueryIntentActivities == null) {
            return false;
        }
        Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && str.equals(activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(View view, float f) {
        Rect rectW;
        if (view == null) {
            return false;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        return width * height > 0 && (rectW = w(view)) != null && ((float) (rectW.width() * rectW.height())) >= (f * ((float) width)) * ((float) height);
    }

    private static boolean a(View view, Rect rect) {
        if (view == null || rect == null) {
            return false;
        }
        try {
            return view.getGlobalVisibleRect(rect);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean a(String str, String str2) {
        return (TextUtils.isEmpty(str) || str.equals(str2)) ? false : true;
    }

    public static int b(GridView gridView) {
        if (gridView == null || gridView.getChildCount() <= 0) {
            return 0;
        }
        View childAt = gridView.getChildAt(0);
        int numColumns = gridView.getNumColumns();
        return (-childAt.getTop()) + ((numColumns != 0 ? gridView.getFirstVisiblePosition() / numColumns : 1) * childAt.getHeight());
    }

    public static int b(ListView listView) {
        if (listView == null || listView.getChildCount() <= 0) {
            return 0;
        }
        View childAt = listView.getChildAt(0);
        return (-childAt.getTop()) + (listView.getFirstVisiblePosition() * childAt.getHeight());
    }

    public static View b(Activity activity) {
        View viewA = a(activity);
        if (viewA != null) {
            return viewA.getRootView();
        }
        return null;
    }

    public static String b(Bitmap bitmap) throws Throwable {
        byte[] bArrC = c(bitmap);
        return bArrC != null ? cl.a.a(bArrC) : "";
    }

    public static String b(View view) {
        Class<?> cls;
        String simpleName = view instanceof ListView ? ListView.class.getSimpleName() : view instanceof WebView ? WebView.class.getSimpleName() : "";
        if (TextUtils.isEmpty(simpleName)) {
            String strA = a(view.getClass());
            if (!"android.widget".equals(strA) && !"android.view".equals(strA)) {
                try {
                    cls = Class.forName("android.support.v7.widget.RecyclerView");
                } catch (Exception e) {
                    cls = null;
                }
                if (cls != null && cls.isAssignableFrom(view.getClass())) {
                    simpleName = androidx.recyclerview.widget.RecyclerView.TAG;
                }
            }
        }
        if (TextUtils.isEmpty(simpleName)) {
            simpleName = c(view.getClass());
        }
        return TextUtils.isEmpty(simpleName) ? "Object" : simpleName;
    }

    public static String b(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        String strA = a(cls, false);
        if (!TextUtils.isEmpty(strA) && cls.isAnonymousClass()) {
            strA = strA + "$";
        }
        return strA == null ? "" : strA;
    }

    private static String b(String str) {
        String strA = bs.a().a(str);
        if (TextUtils.isEmpty(strA)) {
            strA = bo.a().a(str, bo.a.a);
        }
        return strA == null ? "" : strA;
    }

    public static String b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                sb.append("/" + jSONObject.getString("p") + "[" + jSONObject.getString("i") + "]");
                String strOptString = jSONObject.optString("d");
                if (!TextUtils.isEmpty(strOptString)) {
                    sb.append("#" + strOptString);
                }
            } catch (Exception e) {
                return "";
            }
        }
        return sb.toString();
    }

    public static ArrayList<Integer> b(Activity activity, View view) {
        int iComputeHorizontalScrollOffset;
        int iComputeVerticalScrollOffset;
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (view == null) {
            arrayList.add(0);
            arrayList.add(0);
            return arrayList;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (view instanceof WebView) {
            iComputeHorizontalScrollOffset = view.getScrollX();
            iComputeVerticalScrollOffset = view.getScrollY();
        } else if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            if (scrollView.getChildCount() > 0) {
                iComputeHorizontalScrollOffset = scrollView.getScrollX();
                iComputeVerticalScrollOffset = scrollView.getScrollY();
            } else {
                iComputeVerticalScrollOffset = 0;
                iComputeHorizontalScrollOffset = 0;
            }
        } else if (view instanceof ListView) {
            iComputeVerticalScrollOffset = b((ListView) view);
            iComputeHorizontalScrollOffset = 0;
        } else if (view instanceof GridView) {
            iComputeVerticalScrollOffset = b((GridView) view);
            iComputeHorizontalScrollOffset = 0;
        } else if (r(view)) {
            try {
                RecyclerView recyclerView = (RecyclerView) view;
                iComputeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
                try {
                    iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
                } catch (Exception e) {
                    iComputeVerticalScrollOffset = 0;
                }
            } catch (Exception e2) {
                iComputeHorizontalScrollOffset = 0;
            }
        } else {
            iComputeVerticalScrollOffset = 0;
            iComputeHorizontalScrollOffset = 0;
        }
        int i = width + iComputeHorizontalScrollOffset;
        int i2 = height + iComputeVerticalScrollOffset;
        if (i <= 0) {
            i = 0;
        }
        int i3 = i2 > 0 ? i2 : 0;
        arrayList.add(Integer.valueOf(i));
        arrayList.add(Integer.valueOf(i3));
        return arrayList;
    }

    public static Map<String, String> b(View view, boolean z) {
        View view2;
        HashMap map = new HashMap();
        if (view == null) {
            return map;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        a(view, (LinkedHashMap<View, Integer>) linkedHashMap);
        StringBuilder sb = new StringBuilder();
        if (linkedHashMap.size() == 0) {
            return map;
        }
        ArrayList<Map.Entry> arrayList = new ArrayList(linkedHashMap.entrySet());
        Iterator it = arrayList.iterator();
        int i = 0;
        boolean z2 = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            int iIntValue = ((Integer) entry.getValue()).intValue();
            if (iIntValue > i) {
                i = iIntValue;
            }
            View view3 = (View) entry.getKey();
            if (view3 != null && (view3 instanceof TextView)) {
                CharSequence text = ((TextView) view3).getText();
                String string = text != null ? text.toString() : "";
                if (!TextUtils.isEmpty(string)) {
                    if (!TextUtils.isEmpty(sb.toString())) {
                        sb.append(Config.replace);
                    }
                    sb.append(string);
                    if (!z) {
                        z2 = true;
                    } else if (string.contains("广告")) {
                        z2 = true;
                    }
                }
            }
        }
        String string2 = sb.toString();
        if (!TextUtils.isEmpty(string2) && z2) {
            if (string2.length() > 256) {
                string2 = string2.substring(0, 256);
            }
            map.put("content", string2);
        }
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry entry2 : arrayList) {
            if (((Integer) entry2.getValue()).intValue() >= i && (view2 = (View) entry2.getKey()) != null && (view2 instanceof TextView)) {
                CharSequence text2 = ((TextView) view2).getText();
                String string3 = text2 != null ? text2.toString() : "";
                if (!TextUtils.isEmpty(string3)) {
                    if (!TextUtils.isEmpty(sb2.toString())) {
                        sb2.append(Config.replace);
                    }
                    sb2.append(string3);
                }
            }
        }
        String string4 = sb2.toString();
        if (!TextUtils.isEmpty(string4)) {
            if (string4.length() > 256) {
                string4 = string4.substring(0, 256);
            }
            map.put(Config.FEED_LIST_ITEM_TITLE, string4);
        }
        return map;
    }

    public static boolean b(View view, String str) {
        return "ListView".equals(str) || androidx.recyclerview.widget.RecyclerView.TAG.equals(str) || "GridView".equals(str) || view.isClickable();
    }

    public static int c(Activity activity) {
        if (activity == null) {
            return 0;
        }
        WindowManager windowManager = activity.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static String c(View view) {
        ViewPager parent;
        Class<?> cls;
        if (view == null || (parent = view.getParent()) == null || !(parent instanceof ViewGroup)) {
            return "";
        }
        String strA = a(parent.getClass());
        if ("android.widget".equals(strA) || "android.view".equals(strA)) {
            return "";
        }
        ViewPager viewPager = (ViewGroup) parent;
        try {
            cls = Class.forName("android.support.v4.view.ViewPager");
        } catch (ClassNotFoundException e) {
            cls = null;
        }
        if (cls == null || !cls.isAssignableFrom(viewPager.getClass())) {
            return "";
        }
        try {
            ViewPager viewPager2 = viewPager;
            ArrayList arrayList = new ArrayList();
            int childCount = viewPager2.getChildCount();
            int i = 0;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewPager2.getChildAt(i2);
                arrayList.add(childAt);
                if (e(childAt) != null) {
                    i++;
                }
            }
            if (arrayList.size() < 2 || i < 2) {
                return String.valueOf(viewPager2.getCurrentItem());
            }
            try {
                Collections.sort(arrayList, new Comparator<View>() { // from class: com.baidu.mobstat.cc.1
                    @Override // java.util.Comparator
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(View view2, View view3) {
                        return view2.getLeft() - view3.getLeft();
                    }
                });
            } catch (Exception e2) {
            }
            int left = view.getLeft() / Math.abs(((View) arrayList.get(1)).getLeft() - ((View) arrayList.get(0)).getLeft());
            int count = viewPager2.getAdapter().getCount();
            if (count != 0) {
                left %= count;
            }
            return String.valueOf(left);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String c(View view, String str) {
        Object tag;
        String str2 = (view == null || (tag = view.getTag(-97001)) == null || !(tag instanceof String)) ? "" : (String) tag;
        return (str == null || !TextUtils.isEmpty(str2)) ? str2 : str;
    }

    private static String c(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        String strA = a(cls);
        return ("android.widget".equals(strA) || "android.view".equals(strA)) ? d(cls) : c(cls.getSuperclass());
    }

    public static String c(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                sb.append("/" + b(jSONObject.getString("p")) + "[" + jSONObject.getString("i") + "]");
            } catch (Exception e) {
                return "";
            }
        }
        return sb.toString();
    }

    public static boolean c(Activity activity, View view) {
        View viewA;
        return (activity == null || view == null || (viewA = a(activity)) == null || !x(view) || viewA == view) ? false : true;
    }

    private static byte[] c(Bitmap bitmap) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] byteArray = null;
        byteArray = null;
        byteArray = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            if (bitmap == null) {
                return null;
            }
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e) {
                byteArrayOutputStream = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
            } catch (Exception e2) {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return byteArray;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream2 = byteArrayOutputStream;
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e3) {
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
        }
        return byteArray;
    }

    public static int d(Activity activity) {
        if (activity == null) {
            return 0;
        }
        WindowManager windowManager = activity.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private static String d(Class<?> cls) {
        return a(cls, true);
    }

    public static String d(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                sb.append("/" + b(jSONObject.getString("p")) + "[" + jSONObject.getString("i") + "]");
                String strOptString = jSONObject.optString("d");
                if (!TextUtils.isEmpty(strOptString)) {
                    sb.append("#" + strOptString);
                }
            } catch (Exception e) {
                return "";
            }
        }
        return sb.toString();
    }

    public static boolean d(View view) {
        if (view.getVisibility() != 0) {
            return false;
        }
        return a(view, new Rect());
    }

    public static Rect e(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        Rect rect = new Rect();
        if (a(view, rect) && rect.right > rect.left && rect.bottom > rect.top) {
            return rect;
        }
        return null;
    }

    public static String e(Activity activity) {
        if (activity == null || activity.getClass() == null) {
            return "";
        }
        String name = activity.getClass().getName();
        return !TextUtils.isEmpty(name) ? name : "";
    }

    public static String f(Activity activity) {
        CharSequence title;
        String string = (activity == null || (title = activity.getTitle()) == null) ? "" : title.toString();
        String str = TextUtils.isEmpty(string) ? "" : string;
        return str.length() > 256 ? str.substring(0, 256) : str;
    }

    public static String f(View view) {
        int iLastIndexOf;
        int length;
        String strSubstring = null;
        try {
            if (view.getId() != 0) {
                strSubstring = view.getResources().getResourceName(view.getId());
            }
        } catch (Exception e) {
        }
        if (!TextUtils.isEmpty(strSubstring) && strSubstring.contains(":id/") && (iLastIndexOf = strSubstring.lastIndexOf(":id/")) != -1 && (length = iLastIndexOf + ":id/".length()) < strSubstring.length()) {
            strSubstring = strSubstring.substring(length);
        }
        return strSubstring == null ? "" : strSubstring;
    }

    public static String g(Activity activity) {
        if (activity == null) {
            return "";
        }
        String strH = h(activity);
        if (!TextUtils.isEmpty(strH)) {
            return strH;
        }
        Uri uriI = i(activity);
        if (uriI == null) {
            return "";
        }
        String host = uriI.getHost();
        return !TextUtils.isEmpty(host) ? host : "";
    }

    public static Map<String, String> g(View view) {
        Map<String, String> map;
        Object tag = view.getTag(-96000);
        if (tag == null || !(tag instanceof Map)) {
            return null;
        }
        try {
            map = (Map) tag;
        } catch (Exception e) {
            map = null;
        }
        if (map == null || map.size() == 0) {
            return null;
        }
        return map;
    }

    private static String h(Activity activity) {
        return activity.getCallingPackage();
    }

    public static String h(View view) {
        String string = null;
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text != null) {
                string = text.toString();
            }
        } else if (view instanceof ViewGroup) {
            StringBuilder sb = new StringBuilder();
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            boolean z = false;
            for (int i = 0; i < childCount && sb.length() < 128; i++) {
                String strH = h(viewGroup.getChildAt(i));
                if (strH != null && strH.length() > 0) {
                    if (z) {
                        sb.append(", ");
                    }
                    sb.append(strH);
                    z = true;
                }
            }
            if (sb.length() > 128) {
                string = sb.substring(0, 128);
            } else if (z) {
                string = sb.toString();
            }
        }
        return TextUtils.isEmpty(string) ? "" : string;
    }

    public static int i(View view) {
        return (int) (view.getAlpha() * (view.getBackground() != null ? r1.getAlpha() : 0));
    }

    private static Uri i(Activity activity) {
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.REFERRER");
        if (uri != null) {
            return uri;
        }
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return !TextUtils.isEmpty(stringExtra) ? Uri.parse(stringExtra) : activity.getReferrer();
    }

    public static float j(View view) {
        return view.getZ();
    }

    public static String k(View view) {
        String string = null;
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text != null) {
                string = text.toString();
            }
        } else if (view instanceof ViewGroup) {
            StringBuilder sb = new StringBuilder();
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            boolean z = false;
            for (int i = 0; i < childCount && sb.length() < 128; i++) {
                String strK = k(viewGroup.getChildAt(i));
                if (strK != null && strK.length() > 0) {
                    if (z) {
                        sb.append("| ");
                    }
                    sb.append(strK);
                    z = true;
                }
            }
            if (sb.length() > 4096) {
                string = sb.substring(0, 4096);
            } else if (z) {
                string = sb.toString();
            }
        }
        return TextUtils.isEmpty(string) ? "" : string;
    }

    public static String l(View view) {
        String strK;
        try {
            strK = k(view);
        } catch (Throwable th) {
            strK = null;
        }
        return TextUtils.isEmpty(strK) ? "" : strK;
    }

    public static String m(View view) {
        Class<?> cls;
        if (view == null || (cls = view.getClass()) == null) {
            return "";
        }
        String strD = d(cls);
        if (!TextUtils.isEmpty(strD) && cls.isAnonymousClass()) {
            strD = strD + "$";
        }
        return strD == null ? "" : strD;
    }

    public static boolean n(View view) {
        Class<?> cls;
        if (view == null) {
            return false;
        }
        if ((view instanceof ListView) || (view instanceof GridView)) {
            return true;
        }
        String strA = a(view.getClass());
        if ("android.widget".equals(strA) || "android.view".equals(strA)) {
            return false;
        }
        try {
            cls = Class.forName("android.support.v7.widget.RecyclerView");
        } catch (Exception e) {
            cls = null;
        }
        return cls != null && cls.isAssignableFrom(view.getClass());
    }

    public static View o(View view) {
        View view2;
        if (view == null) {
            return null;
        }
        Object parent = view.getParent();
        if ((parent instanceof View) && (view2 = (View) parent) != null && n(view2)) {
            return view2;
        }
        return null;
    }

    public static int p(View view) {
        if (view != null) {
            return view.getWidth();
        }
        return 0;
    }

    public static int q(View view) {
        if (view != null) {
            return view.getHeight();
        }
        return 0;
    }

    public static boolean r(View view) {
        Class<?> cls;
        if (view == null) {
            return false;
        }
        String strA = a(view.getClass());
        if ("android.widget".equals(strA) || "android.view".equals(strA)) {
            return false;
        }
        try {
            cls = Class.forName("android.support.v7.widget.RecyclerView");
        } catch (Exception e) {
            cls = null;
        }
        return cls != null && cls.isAssignableFrom(view.getClass());
    }

    public static boolean s(View view) {
        Object tag;
        return (view == null || (tag = view.getTag(-97001)) == null || !(tag instanceof String)) ? false : true;
    }

    public static Map<String, String> t(View view) {
        return a(view, true);
    }

    public static String u(View view) {
        Object tag;
        return (view == null || (tag = view.getTag(-97004)) == null || !(tag instanceof String)) ? "" : (String) tag;
    }

    public static boolean v(View view) {
        Object tag;
        return (view == null || (tag = view.getTag(-97002)) == null || !(tag instanceof Boolean)) ? false : true;
    }

    private static Rect w(View view) {
        if (view == null || view.getVisibility() != 0) {
            return null;
        }
        Rect rect = new Rect();
        a(view, rect);
        return rect;
    }

    private static boolean x(View view) {
        return view != null && "com.android.internal.policy".equals(a(view.getClass())) && "DecorView".equals(m(view));
    }
}

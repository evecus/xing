package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bo {
    private static final bo a = new bo();
    private HashMap<String, String> b = new HashMap<>();
    private HashMap<Character, Integer> c = new HashMap<>();
    private HashMap<String, String> d = new HashMap<>();
    private HashMap<Character, Integer> e = new HashMap<>();
    private HashMap<String, String> f = new HashMap<>();
    private HashMap<Character, Integer> g = new HashMap<>();

    public static class a {
        public static int a = 0;
        public static int b = 1;
        public static int c = 2;
    }

    public static bo a() {
        return a;
    }

    private void a(String str, HashMap<Character, Integer> map, HashMap<String, String> map2) {
        char lowerCase = Character.toLowerCase(str.charAt(0));
        Integer num = map.get(Character.valueOf(lowerCase));
        int iIntValue = num != null ? num.intValue() + 1 : 0;
        String str2 = Character.toString(lowerCase) + iIntValue;
        map.put(Character.valueOf(lowerCase), Integer.valueOf(iIntValue));
        map2.put(str, str2);
    }

    public String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (i == a.a) {
            String str2 = this.b.get(str);
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
            a(str, this.c, this.b);
            return this.b.get(str);
        }
        if (i == a.c) {
            String str3 = this.f.get(str);
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
            a(str, this.g, this.f);
            return this.f.get(str);
        }
        String str4 = this.d.get(str);
        if (!TextUtils.isEmpty(str4)) {
            return str4;
        }
        a(str, this.e, this.d);
        return this.d.get(str);
    }

    public JSONObject a(int i) {
        HashMap<String, String> map = i == a.a ? this.b : i == a.c ? this.f : this.d;
        JSONObject jSONObject = new JSONObject();
        if (map == null) {
            return jSONObject;
        }
        ArrayList<Map.Entry> arrayList = new ArrayList(map.entrySet());
        try {
            Collections.sort(arrayList, new Comparator<Map.Entry<String, String>>() { // from class: com.baidu.mobstat.bo.1
                @Override // java.util.Comparator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(Map.Entry<String, String> entry, Map.Entry<String, String> entry2) {
                    return entry.getValue().compareTo(entry2.getValue());
                }
            });
        } catch (Exception e) {
        }
        for (Map.Entry entry : arrayList) {
            try {
                jSONObject.put((String) entry.getValue(), (String) entry.getKey());
            } catch (Exception e2) {
            }
        }
        return jSONObject;
    }

    public void b() {
        b(a.a);
        b(a.c);
        b(a.b);
    }

    public void b(int i) {
        if (i == a.a) {
            this.c.clear();
            this.b.clear();
        } else if (i == a.c) {
            this.g.clear();
            this.f.clear();
        } else {
            this.e.clear();
            this.d.clear();
        }
    }
}

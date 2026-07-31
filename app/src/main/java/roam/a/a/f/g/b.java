package roam.a.a.f.g;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public a a;
    public String[] b;

    public b(String str, a aVar) {
        this.a = aVar;
    }

    public static List<b> a(JSONObject jSONObject) {
        a aVar;
        String[] strArr;
        a aVar2 = a.None;
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null) {
            String strOptString = jSONObject.optString(Config.FEED_LIST_NAME, "");
            String[] strArrSplit = !TextUtils.isEmpty(strOptString) ? strOptString.split(";") : null;
            for (int i = 0; i < strArrSplit.length; i++) {
                String str = strArrSplit[i];
                if (TextUtils.isEmpty(str)) {
                    aVar = aVar2;
                } else {
                    a[] aVarArrValues = a.values();
                    for (int i2 = 0; i2 < 3; i2++) {
                        aVar = aVarArrValues[i2];
                        if (str.startsWith(aVar.a)) {
                            break;
                        }
                    }
                    aVar = aVar2;
                }
                if (aVar != aVar2) {
                    b bVar = new b(strArrSplit[i], aVar);
                    String str2 = strArrSplit[i];
                    ArrayList arrayList2 = new ArrayList();
                    int iIndexOf = str2.indexOf(40);
                    int iLastIndexOf = str2.lastIndexOf(41);
                    if (iIndexOf == -1 || iLastIndexOf == -1 || iLastIndexOf <= iIndexOf) {
                        strArr = null;
                    } else {
                        String[] strArrSplit2 = str2.substring(iIndexOf + 1, iLastIndexOf).split(",");
                        if (strArrSplit2 != null) {
                            for (int i3 = 0; i3 < strArrSplit2.length; i3++) {
                                if (!TextUtils.isEmpty(strArrSplit2[i3])) {
                                    arrayList2.add(strArrSplit2[i3].trim().replaceAll("'", "").replaceAll("\"", ""));
                                }
                            }
                        }
                        strArr = (String[]) arrayList2.toArray(new String[0]);
                    }
                    bVar.b = strArr;
                    arrayList.add(bVar);
                }
            }
        }
        return arrayList;
    }
}

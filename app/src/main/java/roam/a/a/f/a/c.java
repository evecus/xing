package roam.a.a.f.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static long d = -1;
    public Activity a;
    public roam.a.a.f.k.a b;
    public Map<String, a> c = new HashMap();

    public final class a {
        public String a = "";
        public String b = "";

        public a(c cVar, byte b) {
        }
    }

    public c(Activity activity) {
        this.a = activity;
        roam.a.a.f.h.b bVarA = roam.a.a.f.h.b.a();
        Activity activity2 = this.a;
        roam.a.a.f.c.c.a();
        Objects.requireNonNull(bVarA);
        bVarA.a = activity2.getApplicationContext();
        if (roam.a.a.f.a.l.a.a == null) {
            roam.a.a.f.a.l.a.a = new roam.a.a.f.a.l.c(activity);
        }
        this.b = new roam.a.a.f.k.a(activity, "去支付宝付款");
    }

    public static boolean b(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str2 = "";
                break;
            }
            String str3 = strArr[i];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i++;
        }
        if (!TextUtils.isEmpty(str2)) {
            if (z) {
                sb.append("&");
            }
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
        } else if (z2) {
            return false;
        }
        return true;
    }

    public final String a(roam.a.a.f.g.b bVar) {
        String strC;
        String[] strArr = bVar.b;
        Intent intent = new Intent(this.a, (Class<?>) roam.a.a.f.a.a.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        if (strArr.length == 2) {
            bundle.putString("cookie", strArr[1]);
        }
        intent.putExtras(bundle);
        this.a.startActivity(intent);
        synchronized (roam.a.a.f.j.c.class) {
            try {
                try {
                    roam.a.a.f.j.c.class.wait();
                } catch (InterruptedException e) {
                    strC = roam.a.a.a.b.a.c();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        strC = roam.a.a.a.b.a.a;
        if (TextUtils.isEmpty(strC)) {
            strC = roam.a.a.a.b.a.c();
        }
        return strC;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0054 A[Catch: all -> 0x00e9, IOException -> 0x00f2, TryCatch #4 {IOException -> 0x00f2, blocks: (B:8:0x0011, B:15:0x0039, B:18:0x004e, B:20:0x0054, B:22:0x0060, B:24:0x006c, B:26:0x0076, B:28:0x0088, B:31:0x0092, B:38:0x00bd, B:35:0x00b8, B:36:0x00bb, B:39:0x00c0, B:40:0x00c3, B:41:0x00c6, B:43:0x00cc, B:45:0x00d8), top: B:65:0x0011, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00cc A[Catch: all -> 0x00e9, IOException -> 0x00f2, TryCatch #4 {IOException -> 0x00f2, blocks: (B:8:0x0011, B:15:0x0039, B:18:0x004e, B:20:0x0054, B:22:0x0060, B:24:0x006c, B:26:0x0076, B:28:0x0088, B:31:0x0092, B:38:0x00bd, B:35:0x00b8, B:36:0x00bb, B:39:0x00c0, B:40:0x00c3, B:41:0x00c6, B:43:0x00cc, B:45:0x00d8), top: B:65:0x0011, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c3 A[EDGE_INSN: B:70:0x00c3->B:40:0x00c3 BREAK  A[LOOP:0: B:17:0x004b->B:39:0x00c0], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String c(java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.f.a.c.c(java.lang.String):java.lang.String");
    }

    public final void d() {
        roam.a.a.f.k.a aVar = this.b;
        if (aVar != null) {
            aVar.a();
            this.b = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x01be, code lost:
    
        if (r0.startsWith("http://mclient.alipay.com/home/exterfaceAssign.htm") != false) goto L72;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x029b A[Catch: all -> 0x04c4, TryCatch #6 {all -> 0x04c4, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x0015, B:38:0x00d3, B:40:0x00db, B:68:0x0194, B:70:0x01aa, B:103:0x028a, B:105:0x029b, B:107:0x02a9, B:109:0x02c9, B:111:0x02ee, B:113:0x02fc, B:123:0x0331, B:126:0x0368, B:128:0x03aa, B:129:0x03b8, B:147:0x0416, B:116:0x030d, B:118:0x0313, B:120:0x0321, B:150:0x042b, B:153:0x0435, B:155:0x0439, B:156:0x0447, B:174:0x04a5, B:72:0x01c0, B:74:0x01c8, B:76:0x01d0, B:42:0x00e3, B:44:0x00f5, B:46:0x00fb, B:48:0x011e, B:49:0x012c, B:67:0x018a, B:35:0x00cc, B:9:0x001d, B:11:0x002f, B:13:0x0035, B:15:0x0058, B:16:0x0066, B:34:0x00c4), top: B:192:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0435 A[Catch: all -> 0x04c4, TRY_LEAVE, TryCatch #6 {all -> 0x04c4, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x0015, B:38:0x00d3, B:40:0x00db, B:68:0x0194, B:70:0x01aa, B:103:0x028a, B:105:0x029b, B:107:0x02a9, B:109:0x02c9, B:111:0x02ee, B:113:0x02fc, B:123:0x0331, B:126:0x0368, B:128:0x03aa, B:129:0x03b8, B:147:0x0416, B:116:0x030d, B:118:0x0313, B:120:0x0321, B:150:0x042b, B:153:0x0435, B:155:0x0439, B:156:0x0447, B:174:0x04a5, B:72:0x01c0, B:74:0x01c8, B:76:0x01d0, B:42:0x00e3, B:44:0x00f5, B:46:0x00fb, B:48:0x011e, B:49:0x012c, B:67:0x018a, B:35:0x00cc, B:9:0x001d, B:11:0x002f, B:13:0x0035, B:15:0x0058, B:16:0x0066, B:34:0x00c4), top: B:192:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d3 A[Catch: all -> 0x04c4, TRY_ENTER, TryCatch #6 {all -> 0x04c4, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x0015, B:38:0x00d3, B:40:0x00db, B:68:0x0194, B:70:0x01aa, B:103:0x028a, B:105:0x029b, B:107:0x02a9, B:109:0x02c9, B:111:0x02ee, B:113:0x02fc, B:123:0x0331, B:126:0x0368, B:128:0x03aa, B:129:0x03b8, B:147:0x0416, B:116:0x030d, B:118:0x0313, B:120:0x0321, B:150:0x042b, B:153:0x0435, B:155:0x0439, B:156:0x0447, B:174:0x04a5, B:72:0x01c0, B:74:0x01c8, B:76:0x01d0, B:42:0x00e3, B:44:0x00f5, B:46:0x00fb, B:48:0x011e, B:49:0x012c, B:67:0x018a, B:35:0x00cc, B:9:0x001d, B:11:0x002f, B:13:0x0035, B:15:0x0058, B:16:0x0066, B:34:0x00c4), top: B:192:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String e(java.lang.String r14) {
        /*
            Method dump skipped, instruction units count: 1225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.f.a.c.e(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009c A[Catch: all -> 0x0146, TryCatch #2 {all -> 0x0146, blocks: (B:23:0x005b, B:25:0x006f, B:27:0x0077, B:29:0x0091, B:31:0x0097, B:33:0x00a0, B:62:0x012c, B:32:0x009c, B:34:0x00a6, B:57:0x011f, B:59:0x0125, B:37:0x00ae, B:38:0x00b6, B:40:0x00b9, B:42:0x00c3, B:44:0x00cd, B:45:0x00e1, B:47:0x00e4, B:49:0x00ee, B:51:0x00f8, B:52:0x0106, B:54:0x0110, B:55:0x0119, B:56:0x011c), top: B:82:0x005b, outer: #0, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ae A[Catch: all -> 0x012b, TryCatch #3 {all -> 0x012b, blocks: (B:34:0x00a6, B:57:0x011f, B:59:0x0125, B:37:0x00ae, B:38:0x00b6, B:40:0x00b9, B:42:0x00c3, B:44:0x00cd, B:45:0x00e1, B:47:0x00e4, B:49:0x00ee, B:51:0x00f8, B:52:0x0106, B:54:0x0110, B:55:0x0119, B:56:0x011c), top: B:83:0x00a6, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0125 A[Catch: all -> 0x012b, TRY_LEAVE, TryCatch #3 {all -> 0x012b, blocks: (B:34:0x00a6, B:57:0x011f, B:59:0x0125, B:37:0x00ae, B:38:0x00b6, B:40:0x00b9, B:42:0x00c3, B:44:0x00cd, B:45:0x00e1, B:47:0x00e4, B:49:0x00ee, B:51:0x00f8, B:52:0x0106, B:54:0x0110, B:55:0x0119, B:56:0x011c), top: B:83:0x00a6, outer: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String f(java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 404
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.f.a.c.f(java.lang.String, boolean):java.lang.String");
    }
}

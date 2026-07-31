package roam.a.a.g.a.a.d;

import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public String a;
    public String b;
    public String c;
    public String d;

    public a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.a = str2;
        this.b = str5;
        this.c = str6;
        this.d = str7;
    }

    public final String toString() {
        StringBuilder sb;
        String strSubstring;
        StringBuilder sb2;
        String strSubstring2;
        StringBuilder sb3;
        String strSubstring3;
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append("," + Build.MODEL);
        stringBuffer.append("," + this.a);
        stringBuffer.append(",APPSecuritySDK-ALIPAY");
        stringBuffer.append(",3.2.2-20180331");
        if (roam.a.a.a.b.a.o(this.b) || this.b.length() < 20) {
            sb = new StringBuilder(",");
            strSubstring = this.b;
        } else {
            sb = new StringBuilder(",");
            strSubstring = this.b.substring(0, 20);
        }
        sb.append(strSubstring);
        stringBuffer.append(sb.toString());
        if (roam.a.a.a.b.a.o(this.c) || this.c.length() < 20) {
            sb2 = new StringBuilder(",");
            strSubstring2 = this.c;
        } else {
            sb2 = new StringBuilder(",");
            strSubstring2 = this.c.substring(0, 20);
        }
        sb2.append(strSubstring2);
        stringBuffer.append(sb2.toString());
        if (roam.a.a.a.b.a.o(this.d) || this.d.length() < 20) {
            sb3 = new StringBuilder(",");
            strSubstring3 = this.d;
        } else {
            sb3 = new StringBuilder(",");
            strSubstring3 = this.d.substring(0, 20);
        }
        sb3.append(strSubstring3);
        stringBuffer.append(sb3.toString());
        return stringBuffer.toString();
    }
}

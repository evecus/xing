package roam.a.d.a;

import android.R;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class o implements Serializable, Cloneable {
    public String g;
    public String h;
    public String i;
    public Map<String, String> k;
    public boolean a = false;
    public boolean b = true;
    public int c = R.drawable.stat_sys_download;
    public int d = R.drawable.stat_sys_download_done;
    public boolean e = true;
    public boolean f = true;
    public String j = "";
    public boolean l = false;
    public long m = RecyclerView.FOREVER_NS;
    public long n = 10000;
    public long o = 600000;
    public boolean p = false;
    public String q = "";
    public String r = "";
    public int s = 3;

    public String a() {
        String str = this.q;
        return str == null ? "" : str;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return new o();
        }
    }
}

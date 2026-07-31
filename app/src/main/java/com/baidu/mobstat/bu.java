package com.baidu.mobstat;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes.dex */
public abstract class bu {
    public static int a = 2;

    private void a(int i, String str) {
        if (!b() || i < a) {
            return;
        }
        Log.println(i, a(), str);
    }

    private String b(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
            if (cause instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public abstract String a();

    public void a(String str) {
        a(3, str);
    }

    public void a(String str, Throwable th) {
        a(2, str + '\n' + b(th));
    }

    public void a(Throwable th) {
        a(6, b(th));
    }

    public void b(String str) {
        a(5, str);
    }

    public void b(String str, Throwable th) {
        a(3, str + '\n' + b(th));
    }

    public abstract boolean b();

    public void c(String str) {
        a(6, str);
    }

    public void c(String str, Throwable th) {
        a(4, str + '\n' + b(th));
    }

    public void d(String str, Throwable th) {
        a(6, str + '\n' + b(th));
    }
}

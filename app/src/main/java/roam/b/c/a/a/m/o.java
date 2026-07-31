package roam.b.c.a.a.m;

import android.webkit.ValueCallback;

/* JADX INFO: loaded from: classes.dex */
public class o implements ValueCallback<String> {
    public final ValueCallback a;

    public o(p pVar, ValueCallback valueCallback) {
        this.a = valueCallback;
    }

    @Override // android.webkit.ValueCallback
    public void onReceiveValue(String str) {
        String str2 = str;
        ValueCallback valueCallback = this.a;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(str2);
        }
    }
}

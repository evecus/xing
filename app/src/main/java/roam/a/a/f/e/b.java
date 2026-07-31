package roam.a.a.f.e;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static b b;
    public final DefaultHttpClient a;

    public b(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.a = new DefaultHttpClient(clientConnectionManager, httpParams);
    }

    public b(HttpParams httpParams) {
        this.a = new DefaultHttpClient(httpParams);
    }
}

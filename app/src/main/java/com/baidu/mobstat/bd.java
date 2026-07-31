package com.baidu.mobstat;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bd {
    private static final ByteBuffer c = ByteBuffer.allocate(0);
    private a a;
    private b b;

    public interface a {
        void a();

        void a(String str);

        void a(boolean z);

        void b();
    }

    class b extends db {
        public b(URI uri, int i, Socket socket) throws InterruptedException {
            super(uri, new de(), null, i);
            a(socket);
        }

        @Override // com.baidu.mobstat.db
        public void a(int i, String str, boolean z) {
            if (bw.c().b()) {
                bw.c().a("onClose,  reason:" + str + ", remote:" + z);
            }
            bv.c().a("autotrace: connect closed, server:" + z + " reason:" + str);
            bg.a().a(5, "remote:" + z + "|reason:" + str);
            if (bd.this.a != null) {
                bd.this.a.a(z);
            }
        }

        @Override // com.baidu.mobstat.db
        public void a(dy dyVar) {
            if (bw.c().b()) {
                bw.c().a("onOpen");
            }
            if (bd.this.a != null) {
                bd.this.a.a();
            }
        }

        @Override // com.baidu.mobstat.db
        public void a(Exception exc) {
            if (bw.c().b()) {
                bw.c().a("onError");
            }
        }

        @Override // com.baidu.mobstat.db
        public void a(String str) {
            JSONObject jSONObject;
            int iIntValue;
            if (bw.c().b()) {
                bw.c().a("onMessage: " + str);
            }
            if (TextUtils.isEmpty(str)) {
            }
            String string = null;
            try {
                jSONObject = new JSONObject(str);
            } catch (Exception e) {
                jSONObject = null;
            }
            if (jSONObject == null) {
                return;
            }
            try {
                string = jSONObject.getString("type");
            } catch (Exception e2) {
            }
            if (TextUtils.isEmpty(string)) {
                return;
            }
            if (string.equals("deploy")) {
                try {
                    bd.this.a.a(((JSONObject) jSONObject.get("data")).toString());
                    return;
                } catch (Exception e3) {
                    return;
                }
            }
            try {
                iIntValue = ((Integer) ((JSONObject) jSONObject.get("data")).get(NotificationCompat.CATEGORY_STATUS)).intValue();
            } catch (Exception e4) {
                iIntValue = -1;
            }
            switch (iIntValue) {
                case 801020:
                    bv.c().a("autotrace: connect established");
                    bg.a().a(2);
                    break;
                case 801021:
                    bv.c().a("autotrace: connect failed, connect has been established");
                    bg.a().a(5, "already connect");
                    break;
                case 801024:
                    bv.c().a("autotrace: connect confirm");
                    bg.a().a(3);
                    if (bd.this.a != null) {
                        bd.this.a.b();
                    }
                    break;
            }
        }
    }

    public class c extends IOException {
        public c(Throwable th) {
            super(th.getMessage());
        }
    }

    public bd(URI uri, a aVar) throws c {
        this.a = aVar;
        try {
            b bVar = new b(uri, 5000, uri.toString().startsWith("wss://") ? c() : null);
            this.b = bVar;
            bVar.c();
        } catch (InterruptedException e) {
            throw new c(e);
        }
    }

    private Socket c() {
        SSLSocketFactory socketFactory;
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, null, null);
            socketFactory = sSLContext.getSocketFactory();
        } catch (Exception e) {
            socketFactory = null;
        }
        if (socketFactory == null) {
            return null;
        }
        try {
            return socketFactory.createSocket();
        } catch (Exception e2) {
            return null;
        }
    }

    public void a() {
        b bVar = this.b;
        if (bVar != null) {
            bVar.d();
        }
    }

    public void a(JSONObject jSONObject) throws NotYetConnectedException {
        if (this.b != null) {
            this.b.a(jSONObject.toString().getBytes());
        }
    }

    public boolean b() {
        return (this.b.f() || this.b.g() || this.b.e()) ? false : true;
    }
}

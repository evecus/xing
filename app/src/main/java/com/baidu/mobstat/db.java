package com.baidu.mobstat;

import androidx.core.view.PointerIconCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public abstract class db extends cy implements cx, Runnable {
    static final /* synthetic */ boolean c = true;
    private cz a;
    protected URI b;
    private InputStream e;
    private OutputStream f;
    private Thread h;
    private dc i;
    private Map<String, String> j;
    private int m;
    private Socket d = null;
    private Proxy g = Proxy.NO_PROXY;
    private CountDownLatch k = new CountDownLatch(1);
    private CountDownLatch l = new CountDownLatch(1);

    class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("WebsocketWriteThread");
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer byteBufferTake = db.this.a.d.take();
                    db.this.f.write(byteBufferTake.array(), 0, byteBufferTake.limit());
                    db.this.f.flush();
                } catch (IOException e) {
                    db.this.a.b();
                    return;
                } catch (InterruptedException e2) {
                    return;
                }
            }
        }
    }

    public db(URI uri, dc dcVar, Map<String, String> map, int i) {
        this.b = null;
        this.a = null;
        this.m = 0;
        if (uri == null) {
            throw new IllegalArgumentException();
        }
        if (dcVar == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        }
        this.b = uri;
        this.i = dcVar;
        this.j = map;
        this.m = i;
        this.a = new cz(this, dcVar);
    }

    private int h() {
        int port = this.b.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.b.getScheme();
        if (scheme.equals("wss")) {
            return 443;
        }
        if (scheme.equals("ws")) {
            return 80;
        }
        throw new RuntimeException("unknown scheme: " + scheme);
    }

    private void i() throws di {
        String rawPath = this.b.getRawPath();
        String rawQuery = this.b.getRawQuery();
        if (rawPath == null || rawPath.length() == 0) {
            rawPath = "/";
        }
        if (rawQuery != null) {
            rawPath = rawPath + "?" + rawQuery;
        }
        int iH = h();
        String str = this.b.getHost() + (iH != 80 ? Config.TRACE_TODAY_VISIT_SPLIT + iH : "");
        du duVar = new du();
        duVar.a(rawPath);
        duVar.a("Host", str);
        Map<String, String> map = this.j;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                duVar.a(entry.getKey(), entry.getValue());
            }
        }
        this.a.a((ds) duVar);
    }

    @Override // com.baidu.mobstat.cx
    public InetSocketAddress a() {
        return this.a.a();
    }

    public void a(int i, String str) {
    }

    public abstract void a(int i, String str, boolean z);

    @Override // com.baidu.mobstat.da
    public void a(cx cxVar, int i, String str) {
        a(i, str);
    }

    @Override // com.baidu.mobstat.da
    public final void a(cx cxVar, int i, String str, boolean z) {
        Thread thread = this.h;
        if (thread != null) {
            thread.interrupt();
        }
        try {
            Socket socket = this.d;
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            a(this, e);
        }
        a(i, str, z);
        this.k.countDown();
        this.l.countDown();
    }

    @Override // com.baidu.mobstat.cy, com.baidu.mobstat.da
    public void a(cx cxVar, dp dpVar) {
        b(dpVar);
    }

    @Override // com.baidu.mobstat.da
    public final void a(cx cxVar, dw dwVar) {
        a((dy) dwVar);
        this.k.countDown();
    }

    @Override // com.baidu.mobstat.da
    public final void a(cx cxVar, Exception exc) {
        a(exc);
    }

    @Override // com.baidu.mobstat.da
    public final void a(cx cxVar, String str) {
        a(str);
    }

    @Override // com.baidu.mobstat.da
    public final void a(cx cxVar, ByteBuffer byteBuffer) {
        a(byteBuffer);
    }

    @Override // com.baidu.mobstat.cx
    public void a(dp dpVar) {
        this.a.a(dpVar);
    }

    public abstract void a(dy dyVar);

    public abstract void a(Exception exc);

    public abstract void a(String str);

    public void a(Socket socket) {
        if (this.d != null) {
            throw new IllegalStateException("socket has already been set");
        }
        this.d = socket;
    }

    public void a(ByteBuffer byteBuffer) {
    }

    public void a(byte[] bArr) throws NotYetConnectedException {
        this.a.a(bArr);
    }

    public void b() {
        if (this.h != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        Thread thread = new Thread(this);
        this.h = thread;
        thread.start();
    }

    public void b(int i, String str, boolean z) {
    }

    @Override // com.baidu.mobstat.da
    public final void b(cx cxVar) {
    }

    @Override // com.baidu.mobstat.da
    public void b(cx cxVar, int i, String str, boolean z) {
        b(i, str, z);
    }

    public void b(dp dpVar) {
    }

    @Override // com.baidu.mobstat.da
    public InetSocketAddress c(cx cxVar) {
        Socket socket = this.d;
        if (socket != null) {
            return (InetSocketAddress) socket.getLocalSocketAddress();
        }
        return null;
    }

    public boolean c() throws InterruptedException {
        b();
        this.k.await();
        return this.a.c();
    }

    public void d() {
        if (this.h != null) {
            this.a.a(1000);
        }
    }

    public boolean e() {
        return this.a.e();
    }

    public boolean f() {
        return this.a.f();
    }

    public boolean g() {
        return this.a.d();
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        try {
            Socket socket = this.d;
            if (socket == null) {
                this.d = new Socket(this.g);
            } else if (socket.isClosed()) {
                throw new IOException();
            }
            if (!this.d.isBound()) {
                this.d.connect(new InetSocketAddress(this.b.getHost(), h()), this.m);
            }
            this.e = this.d.getInputStream();
            this.f = this.d.getOutputStream();
            i();
            Thread thread = new Thread(new a());
            this.h = thread;
            thread.start();
            byte[] bArr = new byte[cz.b];
            while (!g() && !f() && (i = this.e.read(bArr)) != -1) {
                try {
                    this.a.a(ByteBuffer.wrap(bArr, 0, i));
                } catch (IOException e) {
                    this.a.b();
                } catch (RuntimeException e2) {
                    a(e2);
                    this.a.b(PointerIconCompat.TYPE_CELL, e2.getMessage());
                }
            }
            this.a.b();
            if (!c && !this.d.isClosed()) {
                throw new AssertionError();
            }
        } catch (Exception e3) {
            a(this.a, e3);
            this.a.b(-1, e3.getMessage());
        }
    }
}

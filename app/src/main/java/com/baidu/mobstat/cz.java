package com.baidu.mobstat;

import androidx.core.view.PointerIconCompat;
import com.baidu.mobstat.cx;
import com.baidu.mobstat.dc;
import com.baidu.mobstat.dp;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public class cz implements cx {
    public static final List<dc> a;
    public static int b;
    public static boolean c;
    static final /* synthetic */ boolean h = true;
    public final BlockingQueue<ByteBuffer> d;
    public final BlockingQueue<ByteBuffer> e;
    public SelectionKey f;
    public ByteChannel g;
    private final da i;
    private dc l;
    private cx.b m;
    private volatile boolean j = false;
    private cx.a k = cx.a.NOT_YET_CONNECTED;
    private dp n = null;
    private ByteBuffer o = ByteBuffer.allocate(0);
    private dr p = null;
    private String q = null;
    private Integer r = null;
    private Boolean s = null;
    private String t = null;

    static {
        ArrayList arrayList = new ArrayList(4);
        a = arrayList;
        b = 16384;
        c = false;
        arrayList.add(new de());
        arrayList.add(new dd());
    }

    public cz(da daVar, dc dcVar) {
        this.l = null;
        if (daVar == null || dcVar == null) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.d = new LinkedBlockingQueue();
        this.e = new LinkedBlockingQueue();
        this.i = daVar;
        this.m = cx.b.CLIENT;
        if (dcVar != null) {
            this.l = dcVar.c();
        }
    }

    private void a(dw dwVar) {
        if (c) {
            System.out.println("open using draft: " + this.l.getClass().getSimpleName());
        }
        this.k = cx.a.OPEN;
        try {
            this.i.a(this, dwVar);
        } catch (RuntimeException e) {
            this.i.a(this, e);
        }
    }

    private void a(Collection<dp> collection) {
        if (!c()) {
            throw new dl();
        }
        Iterator<dp> it = collection.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    private void a(List<ByteBuffer> list) {
        Iterator<ByteBuffer> it = list.iterator();
        while (it.hasNext()) {
            f(it.next());
        }
    }

    private void c(int i, String str, boolean z) {
        if (this.k == cx.a.CLOSING || this.k == cx.a.CLOSED) {
            return;
        }
        if (this.k == cx.a.OPEN) {
            if (i == 1006) {
                if (!h && z) {
                    throw new AssertionError();
                }
                this.k = cx.a.CLOSING;
                b(i, str, false);
                return;
            }
            if (this.l.b() != dc.a.NONE) {
                try {
                    if (!z) {
                        try {
                            this.i.a(this, i, str);
                        } catch (RuntimeException e) {
                            this.i.a(this, e);
                        }
                    }
                    a(new dn(i, str));
                } catch (dg e2) {
                    this.i.a(this, e2);
                    b(PointerIconCompat.TYPE_CELL, "generated frame is invalid", false);
                }
            }
            b(i, str, z);
        } else if (i != -3) {
            b(-1, str, false);
        } else {
            if (!h && !z) {
                throw new AssertionError();
            }
            b(-3, str, true);
        }
        if (i == 1002) {
            b(i, str, z);
        }
        this.k = cx.a.CLOSING;
        this.o = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0120  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean c(java.nio.ByteBuffer r8) {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.cz.c(java.nio.ByteBuffer):boolean");
    }

    private void d(ByteBuffer byteBuffer) {
        int iA;
        String strB;
        dp dpVar;
        try {
            for (dp dpVar2 : this.l.c(byteBuffer)) {
                if (c) {
                    System.out.println("matched frame: " + dpVar2);
                }
                dp.a aVarF = dpVar2.f();
                boolean zD = dpVar2.d();
                if (this.k == cx.a.CLOSING) {
                    return;
                }
                if (aVarF == dp.a.CLOSING) {
                    if (dpVar2 instanceof dm) {
                        dm dmVar = (dm) dpVar2;
                        iA = dmVar.a();
                        strB = dmVar.b();
                    } else {
                        iA = 1005;
                        strB = "";
                    }
                    if (this.k == cx.a.CLOSING) {
                        a(iA, strB, true);
                    } else if (this.l.b() == dc.a.TWOWAY) {
                        c(iA, strB, true);
                    } else {
                        b(iA, strB, false);
                    }
                } else if (aVarF == dp.a.PING) {
                    this.i.b(this, dpVar2);
                } else if (aVarF == dp.a.PONG) {
                    this.i.c(this, dpVar2);
                } else if (!zD || aVarF == dp.a.CONTINUOUS) {
                    if (aVarF != dp.a.CONTINUOUS) {
                        if (this.n != null) {
                            throw new dg(PointerIconCompat.TYPE_HAND, "Previous continuous frame sequence not completed.");
                        }
                        this.n = dpVar2;
                    } else if (zD) {
                        dp dpVar3 = this.n;
                        if (dpVar3 == null) {
                            throw new dg(PointerIconCompat.TYPE_HAND, "Continuous frame sequence was not started.");
                        }
                        if (dpVar3.f() == dp.a.TEXT) {
                            int iMax = Math.max(this.n.c().limit() - 64, 0);
                            this.n.a(dpVar2);
                            if (!eb.a(this.n.c(), iMax)) {
                                throw new dg(PointerIconCompat.TYPE_CROSSHAIR);
                            }
                        }
                        this.n = null;
                    } else if (this.n == null) {
                        throw new dg(PointerIconCompat.TYPE_HAND, "Continuous frame sequence was not started.");
                    }
                    if (aVarF == dp.a.TEXT && !eb.b(dpVar2.c())) {
                        throw new dg(PointerIconCompat.TYPE_CROSSHAIR);
                    }
                    if (aVarF == dp.a.CONTINUOUS && (dpVar = this.n) != null && dpVar.f() == dp.a.TEXT) {
                        int iMax2 = Math.max(this.n.c().limit() - 64, 0);
                        this.n.a(dpVar2);
                        if (!eb.a(this.n.c(), iMax2)) {
                            throw new dg(PointerIconCompat.TYPE_CROSSHAIR);
                        }
                    }
                    try {
                        this.i.a(this, dpVar2);
                    } catch (RuntimeException e) {
                        this.i.a(this, e);
                    }
                } else {
                    if (this.n != null) {
                        throw new dg(PointerIconCompat.TYPE_HAND, "Continuous frame sequence not completed.");
                    }
                    if (aVarF == dp.a.TEXT) {
                        try {
                            this.i.a(this, eb.a(dpVar2.c()));
                        } catch (RuntimeException e2) {
                            this.i.a(this, e2);
                        }
                    } else {
                        if (aVarF != dp.a.BINARY) {
                            throw new dg(PointerIconCompat.TYPE_HAND, "non control or continious frame expected");
                        }
                        try {
                            this.i.a(this, dpVar2.c());
                        } catch (RuntimeException e3) {
                            this.i.a(this, e3);
                        }
                    }
                }
            }
        } catch (dg e4) {
            this.i.a(this, e4);
            a(e4);
        }
    }

    private dc.b e(ByteBuffer byteBuffer) throws df {
        byteBuffer.mark();
        if (byteBuffer.limit() > dc.c.length) {
            return dc.b.NOT_MATCHED;
        }
        if (byteBuffer.limit() < dc.c.length) {
            throw new df(dc.c.length);
        }
        int i = 0;
        while (byteBuffer.hasRemaining()) {
            if (dc.c[i] != byteBuffer.get()) {
                byteBuffer.reset();
                return dc.b.NOT_MATCHED;
            }
            i++;
        }
        return dc.b.MATCHED;
    }

    private void f(ByteBuffer byteBuffer) {
        if (c) {
            System.out.println("write(" + byteBuffer.remaining() + "): {" + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array())) + "}");
        }
        this.d.add(byteBuffer);
        this.i.b(this);
    }

    @Override // com.baidu.mobstat.cx
    public InetSocketAddress a() {
        return this.i.c(this);
    }

    public void a(int i) {
        c(i, "", false);
    }

    public void a(int i, String str) {
        c(i, str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x002e A[Catch: all -> 0x003f, TryCatch #2 {, blocks: (B:3:0x0001, B:7:0x0009, B:9:0x000d, B:10:0x0010, B:12:0x0014, B:16:0x001e, B:20:0x002a, B:22:0x002e, B:23:0x0031, B:19:0x0025, B:15:0x0019), top: B:33:0x0001, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected synchronized void a(int r3, java.lang.String r4, boolean r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.baidu.mobstat.cx$a r0 = r2.k     // Catch: java.lang.Throwable -> L3f
            com.baidu.mobstat.cx$a r1 = com.baidu.mobstat.cx.a.CLOSED     // Catch: java.lang.Throwable -> L3f
            if (r0 != r1) goto L9
            monitor-exit(r2)
            return
        L9:
            java.nio.channels.SelectionKey r0 = r2.f     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L10
            r0.cancel()     // Catch: java.lang.Throwable -> L3f
        L10:
            java.nio.channels.ByteChannel r0 = r2.g     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L1e
            r0.close()     // Catch: java.io.IOException -> L18 java.lang.Throwable -> L3f
            goto L1e
        L18:
            r0 = move-exception
            com.baidu.mobstat.da r1 = r2.i     // Catch: java.lang.Throwable -> L3f
            r1.a(r2, r0)     // Catch: java.lang.Throwable -> L3f
        L1e:
            com.baidu.mobstat.da r0 = r2.i     // Catch: java.lang.RuntimeException -> L24 java.lang.Throwable -> L3f
            r0.a(r2, r3, r4, r5)     // Catch: java.lang.RuntimeException -> L24 java.lang.Throwable -> L3f
            goto L2a
        L24:
            r3 = move-exception
            com.baidu.mobstat.da r4 = r2.i     // Catch: java.lang.Throwable -> L3f
            r4.a(r2, r3)     // Catch: java.lang.Throwable -> L3f
        L2a:
            com.baidu.mobstat.dc r3 = r2.l     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L31
            r3.a()     // Catch: java.lang.Throwable -> L3f
        L31:
            r3 = 0
            r2.p = r3     // Catch: java.lang.Throwable -> L3f
            com.baidu.mobstat.cx$a r3 = com.baidu.mobstat.cx.a.CLOSED     // Catch: java.lang.Throwable -> L3f
            r2.k = r3     // Catch: java.lang.Throwable -> L3f
            java.util.concurrent.BlockingQueue<java.nio.ByteBuffer> r3 = r2.d     // Catch: java.lang.Throwable -> L3f
            r3.clear()     // Catch: java.lang.Throwable -> L3f
            monitor-exit(r2)
            return
        L3f:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.cz.a(int, java.lang.String, boolean):void");
    }

    protected void a(int i, boolean z) {
        a(i, "", z);
    }

    public void a(dg dgVar) {
        c(dgVar.a(), dgVar.getMessage(), false);
    }

    @Override // com.baidu.mobstat.cx
    public void a(dp dpVar) {
        if (c) {
            System.out.println("send frame: " + dpVar);
        }
        f(this.l.a(dpVar));
    }

    public void a(ds dsVar) throws di {
        boolean z = h;
        if (!z && this.k == cx.a.CONNECTING) {
            throw new AssertionError("shall only be called once");
        }
        this.p = this.l.a(dsVar);
        String strA = dsVar.a();
        this.t = strA;
        if (!z && strA == null) {
            throw new AssertionError();
        }
        try {
            this.i.a((cx) this, this.p);
            a(this.l.a(this.p, this.m));
        } catch (dg e) {
            throw new di("Handshake data rejected by client.");
        } catch (RuntimeException e2) {
            this.i.a(this, e2);
            throw new di("rejected because of" + e2);
        }
    }

    public void a(ByteBuffer byteBuffer) {
        boolean z = h;
        if (!z && !byteBuffer.hasRemaining()) {
            throw new AssertionError();
        }
        if (c) {
            System.out.println("process(" + byteBuffer.remaining() + "): {" + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining())) + "}");
        }
        if (this.k != cx.a.NOT_YET_CONNECTED) {
            d(byteBuffer);
        } else if (c(byteBuffer)) {
            if (!z && this.o.hasRemaining() == byteBuffer.hasRemaining() && byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
            if (byteBuffer.hasRemaining()) {
                d(byteBuffer);
            } else if (this.o.hasRemaining()) {
                d(this.o);
            }
        }
        if (!z && !d() && !e() && byteBuffer.hasRemaining()) {
            throw new AssertionError();
        }
    }

    public void a(byte[] bArr) throws dl, IllegalArgumentException {
        b(ByteBuffer.wrap(bArr));
    }

    public void b() {
        if (g() == cx.a.NOT_YET_CONNECTED) {
            a(-1, true);
            return;
        }
        if (this.j) {
            a(this.r.intValue(), this.q, this.s.booleanValue());
            return;
        }
        if (this.l.b() == dc.a.NONE) {
            a(1000, true);
        } else if (this.l.b() == dc.a.ONEWAY) {
            a(1000, true);
        } else {
            a(PointerIconCompat.TYPE_CELL, true);
        }
    }

    public void b(int i, String str) {
        a(i, str, false);
    }

    protected synchronized void b(int i, String str, boolean z) {
        if (this.j) {
            return;
        }
        this.r = Integer.valueOf(i);
        this.q = str;
        this.s = Boolean.valueOf(z);
        this.j = true;
        this.i.b(this);
        try {
            this.i.b(this, i, str, z);
        } catch (RuntimeException e) {
            this.i.a(this, e);
        }
        dc dcVar = this.l;
        if (dcVar != null) {
            dcVar.a();
        }
        this.p = null;
    }

    public void b(ByteBuffer byteBuffer) throws dl, IllegalArgumentException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        a(this.l.a(byteBuffer, this.m == cx.b.CLIENT));
    }

    public boolean c() {
        if (!h && this.k == cx.a.OPEN && this.j) {
            throw new AssertionError();
        }
        return this.k == cx.a.OPEN;
    }

    public boolean d() {
        return this.k == cx.a.CLOSING;
    }

    public boolean e() {
        return this.j;
    }

    public boolean f() {
        return this.k == cx.a.CLOSED;
    }

    public cx.a g() {
        return this.k;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }
}

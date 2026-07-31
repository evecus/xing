package com.baidu.mobstat;

import androidx.core.view.PointerIconCompat;
import com.baidu.mobstat.cx;
import com.baidu.mobstat.dc;
import com.baidu.mobstat.dp;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class dd extends dc {
    static final /* synthetic */ boolean f = true;
    private ByteBuffer g;
    private final Random h = new Random();

    class a extends Throwable {
        private int b;

        public a(int i) {
            this.b = i;
        }

        public int a() {
            return this.b;
        }
    }

    private byte a(dp.a aVar) {
        if (aVar == dp.a.CONTINUOUS) {
            return (byte) 0;
        }
        if (aVar == dp.a.TEXT) {
            return (byte) 1;
        }
        if (aVar == dp.a.BINARY) {
            return (byte) 2;
        }
        if (aVar == dp.a.CLOSING) {
            return (byte) 8;
        }
        if (aVar == dp.a.PING) {
            return (byte) 9;
        }
        if (aVar == dp.a.PONG) {
            return (byte) 10;
        }
        throw new RuntimeException("Don't know how to handle " + aVar.toString());
    }

    private dp.a a(byte b) throws dh {
        switch (b) {
            case 0:
                return dp.a.CONTINUOUS;
            case 1:
                return dp.a.TEXT;
            case 2:
                return dp.a.BINARY;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            default:
                throw new dh("unknow optcode " + ((int) b));
            case 8:
                return dp.a.CLOSING;
            case 9:
                return dp.a.PING;
            case 10:
                return dp.a.PONG;
        }
    }

    private String a(String str) {
        try {
            return ea.a(MessageDigest.getInstance("SHA1").digest((str.trim() + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] a(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = (i * 8) - 8;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) (j >>> (i2 - (i3 * 8)));
        }
        return bArr;
    }

    @Override // com.baidu.mobstat.dc
    public dc.b a(dr drVar, dy dyVar) throws di {
        if (drVar.c("Sec-WebSocket-Key") && dyVar.c("Sec-WebSocket-Accept")) {
            return a(drVar.b("Sec-WebSocket-Key")).equals(dyVar.b("Sec-WebSocket-Accept")) ? dc.b.MATCHED : dc.b.NOT_MATCHED;
        }
        return dc.b.NOT_MATCHED;
    }

    @Override // com.baidu.mobstat.dc
    public ds a(ds dsVar) {
        dsVar.a("Upgrade", "websocket");
        dsVar.a("Connection", "Upgrade");
        dsVar.a("Sec-WebSocket-Version", "8");
        byte[] bArr = new byte[16];
        this.h.nextBytes(bArr);
        dsVar.a("Sec-WebSocket-Key", ea.a(bArr));
        return dsVar;
    }

    @Override // com.baidu.mobstat.dc
    public ByteBuffer a(dp dpVar) {
        ByteBuffer byteBufferC = dpVar.c();
        int i = 0;
        boolean z = this.d == cx.b.CLIENT;
        int i2 = byteBufferC.remaining() <= 125 ? 1 : byteBufferC.remaining() <= 65535 ? 2 : 8;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((i2 > 1 ? i2 + 1 : i2) + 1 + (z ? 4 : 0) + byteBufferC.remaining());
        byteBufferAllocate.put((byte) (((byte) (dpVar.d() ? -128 : 0)) | a(dpVar.f())));
        byte[] bArrA = a(byteBufferC.remaining(), i2);
        if (!f && bArrA.length != i2) {
            throw new AssertionError();
        }
        if (i2 == 1) {
            byteBufferAllocate.put((byte) (bArrA[0] | (z ? (byte) -128 : (byte) 0)));
        } else if (i2 == 2) {
            byteBufferAllocate.put((byte) ((z ? (byte) -128 : (byte) 0) | 126));
            byteBufferAllocate.put(bArrA);
        } else {
            if (i2 != 8) {
                throw new RuntimeException("Size representation not supported/specified");
            }
            byteBufferAllocate.put((byte) ((z ? (byte) -128 : (byte) 0) | 127));
            byteBufferAllocate.put(bArrA);
        }
        if (z) {
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(4);
            byteBufferAllocate2.putInt(this.h.nextInt());
            byteBufferAllocate.put(byteBufferAllocate2.array());
            while (byteBufferC.hasRemaining()) {
                byteBufferAllocate.put((byte) (byteBufferC.get() ^ byteBufferAllocate2.get(i % 4)));
                i++;
            }
        } else {
            byteBufferAllocate.put(byteBufferC);
        }
        if (!f && byteBufferAllocate.remaining() != 0) {
            throw new AssertionError(byteBufferAllocate.remaining());
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // com.baidu.mobstat.dc
    public List<dp> a(ByteBuffer byteBuffer, boolean z) {
        dq dqVar = new dq();
        try {
            dqVar.a(byteBuffer);
            dqVar.a(true);
            dqVar.a(dp.a.BINARY);
            dqVar.b(z);
            return Collections.singletonList(dqVar);
        } catch (dg e) {
            throw new dk(e);
        }
    }

    @Override // com.baidu.mobstat.dc
    public void a() {
        this.g = null;
    }

    @Override // com.baidu.mobstat.dc
    public dc.a b() {
        return dc.a.TWOWAY;
    }

    @Override // com.baidu.mobstat.dc
    public dc c() {
        return new dd();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x009b, code lost:
    
        if (r6.hasRemaining() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x009d, code lost:
    
        r6.mark();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a0, code lost:
    
        r0.add(e(r6));
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a8, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a9, code lost:
    
        r6.reset();
        r1 = java.nio.ByteBuffer.allocate(a(r1.a()));
        r5.g = r1;
        r1.put(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00be, code lost:
    
        return r0;
     */
    @Override // com.baidu.mobstat.dc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.baidu.mobstat.dp> c(java.nio.ByteBuffer r6) throws com.baidu.mobstat.dg {
        /*
            r5 = this;
        L1:
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            java.nio.ByteBuffer r1 = r5.g
            if (r1 == 0) goto L97
            r6.mark()     // Catch: com.baidu.mobstat.dd.a -> L60
            int r1 = r6.remaining()     // Catch: com.baidu.mobstat.dd.a -> L60
            java.nio.ByteBuffer r2 = r5.g     // Catch: com.baidu.mobstat.dd.a -> L60
            int r2 = r2.remaining()     // Catch: com.baidu.mobstat.dd.a -> L60
            if (r2 <= r1) goto L33
            java.nio.ByteBuffer r0 = r5.g     // Catch: com.baidu.mobstat.dd.a -> L60
            byte[] r2 = r6.array()     // Catch: com.baidu.mobstat.dd.a -> L60
            int r3 = r6.position()     // Catch: com.baidu.mobstat.dd.a -> L60
            r0.put(r2, r3, r1)     // Catch: com.baidu.mobstat.dd.a -> L60
            int r0 = r6.position()     // Catch: com.baidu.mobstat.dd.a -> L60
            int r0 = r0 + r1
            r6.position(r0)     // Catch: com.baidu.mobstat.dd.a -> L60
            java.util.List r6 = java.util.Collections.emptyList()     // Catch: com.baidu.mobstat.dd.a -> L60
            return r6
        L33:
            java.nio.ByteBuffer r1 = r5.g     // Catch: com.baidu.mobstat.dd.a -> L60
            byte[] r3 = r6.array()     // Catch: com.baidu.mobstat.dd.a -> L60
            int r4 = r6.position()     // Catch: com.baidu.mobstat.dd.a -> L60
            r1.put(r3, r4, r2)     // Catch: com.baidu.mobstat.dd.a -> L60
            int r1 = r6.position()     // Catch: com.baidu.mobstat.dd.a -> L60
            int r1 = r1 + r2
            r6.position(r1)     // Catch: com.baidu.mobstat.dd.a -> L60
            java.nio.ByteBuffer r1 = r5.g     // Catch: com.baidu.mobstat.dd.a -> L60
            java.nio.ByteBuffer r1 = r1.duplicate()     // Catch: com.baidu.mobstat.dd.a -> L60
            r2 = 0
            java.nio.Buffer r1 = r1.position(r2)     // Catch: com.baidu.mobstat.dd.a -> L60
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1     // Catch: com.baidu.mobstat.dd.a -> L60
            com.baidu.mobstat.dp r1 = r5.e(r1)     // Catch: com.baidu.mobstat.dd.a -> L60
            r0.add(r1)     // Catch: com.baidu.mobstat.dd.a -> L60
            r1 = 0
            r5.g = r1     // Catch: com.baidu.mobstat.dd.a -> L60
            goto L97
        L60:
            r0 = move-exception
            java.nio.ByteBuffer r1 = r5.g
            r1.limit()
            int r0 = r0.a()
            int r0 = r5.a(r0)
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)
            boolean r1 = com.baidu.mobstat.dd.f
            if (r1 != 0) goto L89
            int r1 = r0.limit()
            java.nio.ByteBuffer r2 = r5.g
            int r2 = r2.limit()
            if (r1 <= r2) goto L83
            goto L89
        L83:
            java.lang.AssertionError r6 = new java.lang.AssertionError
            r6.<init>()
            throw r6
        L89:
            java.nio.ByteBuffer r1 = r5.g
            r1.rewind()
            java.nio.ByteBuffer r1 = r5.g
            r0.put(r1)
            r5.g = r0
            goto L1
        L97:
            boolean r1 = r6.hasRemaining()
            if (r1 == 0) goto Lbe
            r6.mark()
            com.baidu.mobstat.dp r1 = r5.e(r6)     // Catch: com.baidu.mobstat.dd.a -> La8
            r0.add(r1)     // Catch: com.baidu.mobstat.dd.a -> La8
            goto L97
        La8:
            r1 = move-exception
            r6.reset()
            int r1 = r1.a()
            int r1 = r5.a(r1)
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r1)
            r5.g = r1
            r1.put(r6)
        Lbe:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.dd.c(java.nio.ByteBuffer):java.util.List");
    }

    public dp e(ByteBuffer byteBuffer) throws a, dg {
        Cdo dqVar;
        int iRemaining = byteBuffer.remaining();
        int i = 2;
        if (iRemaining < 2) {
            throw new a(2);
        }
        byte b = byteBuffer.get();
        boolean z = (b >> 8) != 0;
        byte b2 = (byte) ((b & 127) >> 4);
        if (b2 != 0) {
            throw new dh("bad rsv " + ((int) b2));
        }
        byte b3 = byteBuffer.get();
        boolean z2 = (b3 & (-128)) != 0;
        int iIntValue = (byte) (b3 & 127);
        dp.a aVarA = a((byte) (b & 15));
        if (!z && (aVarA == dp.a.PING || aVarA == dp.a.PONG || aVarA == dp.a.CLOSING)) {
            throw new dh("control frames may no be fragmented");
        }
        if (iIntValue < 0 || iIntValue > 125) {
            if (aVarA == dp.a.PING || aVarA == dp.a.PONG || aVarA == dp.a.CLOSING) {
                throw new dh("more than 125 octets");
            }
            if (iIntValue != 126) {
                i = 10;
                if (iRemaining < 10) {
                    throw new a(10);
                }
                byte[] bArr = new byte[8];
                for (int i2 = 0; i2 < 8; i2++) {
                    bArr[i2] = byteBuffer.get();
                }
                long jLongValue = new BigInteger(bArr).longValue();
                if (jLongValue > 2147483647L) {
                    throw new dj("Payloadsize is to big...");
                }
                iIntValue = (int) jLongValue;
            } else {
                if (iRemaining < 4) {
                    throw new a(4);
                }
                iIntValue = new BigInteger(new byte[]{0, byteBuffer.get(), byteBuffer.get()}).intValue();
                i = 4;
            }
        }
        int i3 = i + (z2 ? 4 : 0) + iIntValue;
        if (iRemaining < i3) {
            throw new a(i3);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(a(iIntValue));
        if (z2) {
            byte[] bArr2 = new byte[4];
            byteBuffer.get(bArr2);
            for (int i4 = 0; i4 < iIntValue; i4++) {
                byteBufferAllocate.put((byte) (byteBuffer.get() ^ bArr2[i4 % 4]));
            }
        } else {
            byteBufferAllocate.put(byteBuffer.array(), byteBuffer.position(), byteBufferAllocate.limit());
            byteBuffer.position(byteBuffer.position() + byteBufferAllocate.limit());
        }
        if (aVarA == dp.a.CLOSING) {
            dqVar = new dn();
        } else {
            dqVar = new dq();
            dqVar.a(z);
            dqVar.a(aVarA);
        }
        byteBufferAllocate.flip();
        dqVar.a(byteBufferAllocate);
        if (aVarA != dp.a.TEXT || eb.b(dqVar.c())) {
            return dqVar;
        }
        throw new dg(PointerIconCompat.TYPE_CROSSHAIR);
    }
}

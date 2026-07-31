package com.baidu.mobstat;

import androidx.core.view.PointerIconCompat;
import com.baidu.android.common.util.HanziToPinyin;
import com.baidu.mobstat.cx;
import com.baidu.mobstat.dp;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class dc {
    public static int a = 1000;
    public static int b = 64;
    public static final byte[] c = eb.a("<policy-file-request/>\u0000");
    protected cx.b d = null;
    protected dp.a e = null;

    public enum a {
        NONE,
        ONEWAY,
        TWOWAY
    }

    public enum b {
        MATCHED,
        NOT_MATCHED
    }

    public static dt a(ByteBuffer byteBuffer, cx.b bVar) throws di, df {
        dt dtVar;
        String strB = b(byteBuffer);
        if (strB == null) {
            throw new df(byteBuffer.capacity() + 128);
        }
        String[] strArrSplit = strB.split(HanziToPinyin.Token.SEPARATOR, 3);
        if (strArrSplit.length != 3) {
            throw new di();
        }
        if (bVar == cx.b.CLIENT) {
            dv dvVar = new dv();
            dv dvVar2 = dvVar;
            dvVar2.a(Short.parseShort(strArrSplit[1]));
            dvVar2.a(strArrSplit[2]);
            dtVar = dvVar;
        } else {
            du duVar = new du();
            duVar.a(strArrSplit[1]);
            dtVar = duVar;
        }
        String strB2 = b(byteBuffer);
        while (strB2 != null && strB2.length() > 0) {
            String[] strArrSplit2 = strB2.split(Config.TRACE_TODAY_VISIT_SPLIT, 2);
            if (strArrSplit2.length != 2) {
                throw new di("not an http header");
            }
            dtVar.a(strArrSplit2[0], strArrSplit2[1].replaceFirst("^ +", ""));
            strB2 = b(byteBuffer);
        }
        if (strB2 != null) {
            return dtVar;
        }
        throw new df();
    }

    public static ByteBuffer a(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining());
        byte b2 = 48;
        while (byteBuffer.hasRemaining()) {
            byte b3 = byteBuffer.get();
            byteBufferAllocate.put(b3);
            if (b2 == 13 && b3 == 10) {
                byteBufferAllocate.limit(byteBufferAllocate.position() - 2);
                byteBufferAllocate.position(0);
                return byteBufferAllocate;
            }
            b2 = b3;
        }
        byteBuffer.position(byteBuffer.position() - byteBufferAllocate.position());
        return null;
    }

    public static String b(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferA = a(byteBuffer);
        if (byteBufferA == null) {
            return null;
        }
        return eb.a(byteBufferA.array(), 0, byteBufferA.limit());
    }

    public int a(int i) throws dg {
        if (i >= 0) {
            return i;
        }
        throw new dg(PointerIconCompat.TYPE_HAND, "Negative count");
    }

    public abstract b a(dr drVar, dy dyVar) throws di;

    public abstract ds a(ds dsVar) throws di;

    public abstract ByteBuffer a(dp dpVar);

    public List<ByteBuffer> a(dw dwVar, cx.b bVar) {
        return a(dwVar, bVar, true);
    }

    public List<ByteBuffer> a(dw dwVar, cx.b bVar, boolean z) {
        StringBuilder sb = new StringBuilder(100);
        if (dwVar instanceof dr) {
            sb.append("GET ");
            sb.append(((dr) dwVar).a());
            sb.append(" HTTP/1.1");
        } else {
            if (!(dwVar instanceof dy)) {
                throw new RuntimeException("unknown role");
            }
            sb.append("HTTP/1.1 101 ").append(((dy) dwVar).a());
        }
        sb.append("\r\n");
        Iterator<String> itB = dwVar.b();
        while (itB.hasNext()) {
            String next = itB.next();
            String strB = dwVar.b(next);
            sb.append(next);
            sb.append(": ");
            sb.append(strB);
            sb.append("\r\n");
        }
        sb.append("\r\n");
        byte[] bArrB = eb.b(sb.toString());
        byte[] bArrC = z ? dwVar.c() : null;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((bArrC == null ? 0 : bArrC.length) + bArrB.length);
        byteBufferAllocate.put(bArrB);
        if (bArrC != null) {
            byteBufferAllocate.put(bArrC);
        }
        byteBufferAllocate.flip();
        return Collections.singletonList(byteBufferAllocate);
    }

    public abstract List<dp> a(ByteBuffer byteBuffer, boolean z);

    public abstract void a();

    public void a(cx.b bVar) {
        this.d = bVar;
    }

    public abstract a b();

    public abstract dc c();

    public abstract List<dp> c(ByteBuffer byteBuffer) throws dg;

    public dw d(ByteBuffer byteBuffer) throws di {
        return a(byteBuffer, this.d);
    }
}

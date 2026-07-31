package com.baidu.mobstat;

import androidx.core.view.PointerIconCompat;
import com.baidu.mobstat.dp;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class dn extends dq implements dm {
    static final ByteBuffer a = ByteBuffer.allocate(0);
    private int f;
    private String g;

    public dn() {
        super(dp.a.CLOSING);
        a(true);
    }

    public dn(int i, String str) throws dg {
        super(dp.a.CLOSING);
        a(true);
        a(i, str);
    }

    private void a(int i, String str) throws dg {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        if (i == 1015) {
            i = 1005;
        } else {
            str2 = str;
        }
        if (i == 1005) {
            if (str2.length() > 0) {
                throw new dg(PointerIconCompat.TYPE_HAND, "A close frame must have a closecode if it has a reason");
            }
            return;
        }
        if (i > 1011 && i < 3000 && i != 1015) {
            throw new dg(PointerIconCompat.TYPE_HAND, "Trying to send an illegal close code!");
        }
        byte[] bArrA = eb.a(str2);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt(i);
        byteBufferAllocate.position(2);
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(bArrA.length + 2);
        byteBufferAllocate2.put(byteBufferAllocate);
        byteBufferAllocate2.put(bArrA);
        byteBufferAllocate2.rewind();
        a(byteBufferAllocate2);
    }

    private void g() throws dh {
        this.f = 1005;
        ByteBuffer byteBufferC = super.c();
        byteBufferC.mark();
        if (byteBufferC.remaining() >= 2) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            byteBufferAllocate.position(2);
            byteBufferAllocate.putShort(byteBufferC.getShort());
            byteBufferAllocate.position(0);
            int i = byteBufferAllocate.getInt();
            this.f = i;
            if (i == 1006 || i == 1015 || i == 1005 || i > 4999 || i < 1000 || i == 1004) {
                throw new dh("closecode must not be sent over the wire: " + this.f);
            }
        }
        byteBufferC.reset();
    }

    private void h() throws dg {
        if (this.f == 1005) {
            this.g = eb.a(super.c());
            return;
        }
        ByteBuffer byteBufferC = super.c();
        int iPosition = byteBufferC.position();
        try {
            try {
                byteBufferC.position(byteBufferC.position() + 2);
                this.g = eb.a(byteBufferC);
            } catch (IllegalArgumentException e) {
                throw new dh(e);
            }
        } finally {
            byteBufferC.position(iPosition);
        }
    }

    @Override // com.baidu.mobstat.dm
    public int a() {
        return this.f;
    }

    @Override // com.baidu.mobstat.dq, com.baidu.mobstat.Cdo
    public void a(ByteBuffer byteBuffer) throws dg {
        super.a(byteBuffer);
        g();
        h();
    }

    @Override // com.baidu.mobstat.dm
    public String b() {
        return this.g;
    }

    @Override // com.baidu.mobstat.dq, com.baidu.mobstat.dp
    public ByteBuffer c() {
        return this.f == 1005 ? a : super.c();
    }

    @Override // com.baidu.mobstat.dq
    public String toString() {
        return super.toString() + "code: " + this.f;
    }
}

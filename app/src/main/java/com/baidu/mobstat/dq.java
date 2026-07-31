package com.baidu.mobstat;

import com.baidu.mobstat.dp;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class dq implements Cdo {
    protected static byte[] b = new byte[0];
    private ByteBuffer a;
    protected boolean c;
    protected dp.a d;
    protected boolean e;

    public dq() {
    }

    public dq(dp.a aVar) {
        this.d = aVar;
        this.a = ByteBuffer.wrap(b);
    }

    public dq(dp dpVar) {
        this.c = dpVar.d();
        this.d = dpVar.f();
        this.a = dpVar.c();
        this.e = dpVar.e();
    }

    @Override // com.baidu.mobstat.Cdo
    public void a(dp.a aVar) {
        this.d = aVar;
    }

    @Override // com.baidu.mobstat.dp
    public void a(dp dpVar) throws dh {
        ByteBuffer byteBufferC = dpVar.c();
        if (this.a == null) {
            this.a = ByteBuffer.allocate(byteBufferC.remaining());
            byteBufferC.mark();
            this.a.put(byteBufferC);
            byteBufferC.reset();
        } else {
            byteBufferC.mark();
            ByteBuffer byteBuffer = this.a;
            byteBuffer.position(byteBuffer.limit());
            ByteBuffer byteBuffer2 = this.a;
            byteBuffer2.limit(byteBuffer2.capacity());
            if (byteBufferC.remaining() > this.a.remaining()) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBufferC.remaining() + this.a.capacity());
                this.a.flip();
                byteBufferAllocate.put(this.a);
                byteBufferAllocate.put(byteBufferC);
                this.a = byteBufferAllocate;
            } else {
                this.a.put(byteBufferC);
            }
            this.a.rewind();
            byteBufferC.reset();
        }
        this.c = dpVar.d();
    }

    @Override // com.baidu.mobstat.Cdo
    public void a(ByteBuffer byteBuffer) throws dg {
        this.a = byteBuffer;
    }

    @Override // com.baidu.mobstat.Cdo
    public void a(boolean z) {
        this.c = z;
    }

    @Override // com.baidu.mobstat.Cdo
    public void b(boolean z) {
        this.e = z;
    }

    @Override // com.baidu.mobstat.dp
    public ByteBuffer c() {
        return this.a;
    }

    @Override // com.baidu.mobstat.dp
    public boolean d() {
        return this.c;
    }

    @Override // com.baidu.mobstat.dp
    public boolean e() {
        return this.e;
    }

    @Override // com.baidu.mobstat.dp
    public dp.a f() {
        return this.d;
    }

    public String toString() {
        return "Framedata{ optcode:" + f() + ", fin:" + d() + ", payloadlength:[pos:" + this.a.position() + ", len:" + this.a.remaining() + "], payload:" + Arrays.toString(eb.a(new String(this.a.array()))) + "}";
    }
}

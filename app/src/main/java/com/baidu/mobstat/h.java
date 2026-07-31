package com.baidu.mobstat;

import com.baidu.mobstat.e;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
final class h extends g {
    private a f;

    static class a {
        private Class<?> a;
        private Method b;
        private Method c;

        private a() {
            a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long a(Object obj) throws e.a {
            try {
                return ((Long) this.c.invoke(obj, new Object[0])).longValue();
            } catch (Exception e) {
                throw new e.a("");
            }
        }

        private void a() {
            try {
                this.a = Class.forName(e.a(d.a()), true, Object.class.getClassLoader());
                this.b = e.a(this.a, e.a(d.b()), new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                this.c = e.a(this.a, e.a(d.c()), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Object obj, byte[] bArr, int i, int i2) throws e.a {
            try {
                this.b.invoke(obj, bArr, Integer.valueOf(i), Integer.valueOf(i2));
            } catch (Exception e) {
                throw new e.a("");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object b() {
            return this.a.newInstance();
        }
    }

    public h(int i, int i2) {
        this.a = 1099511627775L;
        this.b = 4;
        this.c = 32;
        this.d = i;
        this.e = i2;
        this.f = new a();
    }

    @Override // com.baidu.mobstat.g
    public b a(byte[] bArr, int i, int i2) {
        long jA;
        try {
            Object objB = this.f.b();
            this.f.a(objB, bArr, i, i2);
            jA = this.f.a(objB);
        } catch (Exception e) {
            jA = 4294967295L;
        }
        return b.a(new long[]{jA});
    }
}

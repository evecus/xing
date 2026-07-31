package roam.a.a.a.a;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class g implements i, j {
    @Override // roam.a.a.a.a.i, roam.a.a.a.a.j
    public final boolean a(Class<?> cls) {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    @Override // roam.a.a.a.a.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(java.lang.Object r9) throws java.lang.IllegalAccessException {
        /*
            r8 = this;
            java.util.TreeMap r0 = new java.util.TreeMap
            r0.<init>()
            java.lang.Class r1 = r9.getClass()
        L9:
            java.lang.reflect.Field[] r2 = r1.getDeclaredFields()
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L57
            if (r2 == 0) goto L52
            int r3 = r2.length
            if (r3 <= 0) goto L52
            int r3 = r2.length
            r4 = 0
        L1c:
            if (r4 >= r3) goto L52
            r5 = r2[r4]
            if (r5 == 0) goto L45
            java.lang.String r6 = "this$0"
            java.lang.String r7 = r5.getName()
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L2f
            goto L45
        L2f:
            boolean r6 = r5.isAccessible()
            r7 = 1
            r5.setAccessible(r7)
            java.lang.Object r7 = r5.get(r9)
            if (r7 == 0) goto L45
            r5.setAccessible(r6)
            java.lang.Object r6 = roam.a.a.a.a.f.b(r7)
            goto L46
        L45:
            r6 = 0
        L46:
            if (r6 == 0) goto L4f
            java.lang.String r5 = r5.getName()
            r0.put(r5, r6)
        L4f:
            int r4 = r4 + 1
            goto L1c
        L52:
            java.lang.Class r1 = r1.getSuperclass()
            goto L9
        L57:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.a.a.g.b(java.lang.Object):java.lang.Object");
    }

    @Override // roam.a.a.a.a.i
    public final Object c(Object obj, Type type) throws IllegalAccessException, InstantiationException {
        if (!obj.getClass().equals(d.b.a.c.class)) {
            return null;
        }
        d.b.a.c cVar = (d.b.a.c) obj;
        Class superclass = (Class) type;
        Object objNewInstance = superclass.newInstance();
        while (!superclass.equals(Object.class)) {
            Field[] declaredFields = superclass.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    String name = field.getName();
                    Type genericType = field.getGenericType();
                    if (cVar.a.containsKey(name)) {
                        field.setAccessible(true);
                        field.set(objNewInstance, e.a(cVar.a(name), genericType));
                    }
                }
            }
            superclass = superclass.getSuperclass();
        }
        return objNewInstance;
    }
}

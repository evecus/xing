package roam.a.e.a.a0.a0;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import roam.a.e.a.o;

/* JADX INFO: loaded from: classes.dex */
public final class c extends b {
    public static Class d;
    public final Object b;
    public final Field c;

    public c() {
        Object obj;
        Field declaredField = null;
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            d = cls;
            Field declaredField2 = cls.getDeclaredField("theUnsafe");
            declaredField2.setAccessible(true);
            obj = declaredField2.get(null);
        } catch (Exception e) {
            obj = null;
        }
        this.b = obj;
        try {
            declaredField = AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException e2) {
        }
        this.c = declaredField;
    }

    @Override // roam.a.e.a.a0.a0.b
    public void a(AccessibleObject accessibleObject) {
        if (this.b != null && this.c != null) {
            try {
                d.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.b, accessibleObject, Long.valueOf(((Long) d.getMethod("objectFieldOffset", Field.class).invoke(this.b, this.c)).longValue()), Boolean.TRUE);
                return;
            } catch (Exception e) {
            }
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e2) {
            throw new o("Gson couldn't modify fields for " + accessibleObject + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", e2);
        }
    }
}

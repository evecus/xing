package roam.a.e.a.a0;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
public class f<T> implements t<T> {
    public final y a;
    public final Class b;
    public final Type c;

    public f(g gVar, Class cls, Type type) {
        y xVar;
        this.b = cls;
        this.c = type;
        try {
            Class<?> cls2 = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls2.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            xVar = new u(cls2.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception e) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                declaredMethod.setAccessible(true);
                int iIntValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                xVar = new v(declaredMethod2, iIntValue);
            } catch (Exception e2) {
                try {
                    Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    xVar = new w(declaredMethod3);
                } catch (Exception e3) {
                    xVar = new x();
                }
            }
        }
        this.a = xVar;
    }

    @Override // roam.a.e.a.a0.t
    public T a() {
        try {
            return (T) this.a.b(this.b);
        } catch (Exception e) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Unable to invoke no-args constructor for ");
            sbO.append(this.c);
            sbO.append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
            throw new RuntimeException(sbO.toString(), e);
        }
    }
}

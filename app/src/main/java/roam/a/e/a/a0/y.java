package roam.a.e.a.a0;

import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes.dex */
public abstract class y {
    public static void a(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            StringBuilder sbO = roam.a.b.a.a.a.o("Interface can't be instantiated! Interface name: ");
            sbO.append(cls.getName());
            throw new UnsupportedOperationException(sbO.toString());
        }
        if (Modifier.isAbstract(modifiers)) {
            StringBuilder sbO2 = roam.a.b.a.a.a.o("Abstract class can't be instantiated! Class name: ");
            sbO2.append(cls.getName());
            throw new UnsupportedOperationException(sbO2.toString());
        }
    }

    public abstract <T> T b(Class<T> cls);
}

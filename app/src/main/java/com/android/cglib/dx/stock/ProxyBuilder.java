package com.android.cglib.dx.stock;

import com.android.cglib.dx.Code;
import com.android.cglib.dx.Comparison;
import com.android.cglib.dx.DexMaker;
import com.android.cglib.dx.Label;
import com.android.cglib.dx.Local;
import com.android.cglib.dx.MethodId;
import com.android.cglib.dx.TypeId;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class ProxyBuilder<T> {
    private static final String FIELD_NAME_HANDLER = "$__handler";
    private static final String FIELD_NAME_METHODS = "$__methodArray";
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_BOXED;
    private static final Map<Class<?>, MethodId<?, ?>> PRIMITIVE_TO_UNBOX_METHOD;
    private static final Map<TypeId<?>, MethodId<?, ?>> PRIMITIVE_TYPE_TO_UNBOX_METHOD;
    public static final int VERSION = 1;
    private static final Map<Class<?>, Class<?>> generatedProxyClasses;
    private final Class<T> baseClass;
    private File dexCache;
    private InvocationHandler handler;
    private ClassLoader parentClassLoader = ProxyBuilder.class.getClassLoader();
    private Class<?>[] constructorArgTypes = new Class[0];
    private Object[] constructorArgValues = new Object[0];
    private Set<Class<?>> interfaces = new HashSet();

    public static class MethodSetEntry {
        private final String name;
        private final Method originalMethod;
        private final Class<?>[] paramTypes;
        private final Class<?> returnType;

        public MethodSetEntry(Method method) {
            this.originalMethod = method;
            this.name = method.getName();
            this.paramTypes = method.getParameterTypes();
            this.returnType = method.getReturnType();
        }

        public boolean equals(Object obj) {
            if (obj instanceof MethodSetEntry) {
                MethodSetEntry methodSetEntry = (MethodSetEntry) obj;
                if (this.name.equals(methodSetEntry.name) && this.returnType.equals(methodSetEntry.returnType) && Arrays.equals(this.paramTypes, methodSetEntry.paramTypes)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int iHashCode = this.name.hashCode() + 527 + 17;
            int iHashCode2 = iHashCode + this.returnType.hashCode() + (iHashCode * 31);
            return iHashCode2 + (iHashCode2 * 31) + Arrays.hashCode(this.paramTypes);
        }
    }

    static {
        Class cls = Boolean.TYPE;
        generatedProxyClasses = Collections.synchronizedMap(new HashMap());
        HashMap map = new HashMap();
        PRIMITIVE_TO_BOXED = map;
        map.put(cls, Boolean.class);
        map.put(Integer.TYPE, Integer.class);
        map.put(Byte.TYPE, Byte.class);
        map.put(Long.TYPE, Long.class);
        map.put(Short.TYPE, Short.class);
        map.put(Float.TYPE, Float.class);
        map.put(Double.TYPE, Double.class);
        map.put(Character.TYPE, Character.class);
        PRIMITIVE_TYPE_TO_UNBOX_METHOD = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            TypeId<?> typeId = TypeId.get((Class) entry.getKey());
            TypeId typeId2 = TypeId.get((Class) entry.getValue());
            PRIMITIVE_TYPE_TO_UNBOX_METHOD.put(typeId, typeId2.getMethod(typeId2, "valueOf", typeId));
        }
        HashMap map2 = new HashMap();
        map2.put(cls, TypeId.get(Boolean.class).getMethod(TypeId.BOOLEAN, "booleanValue", new TypeId[0]));
        map2.put(Integer.TYPE, TypeId.get(Integer.class).getMethod(TypeId.INT, "intValue", new TypeId[0]));
        map2.put(Byte.TYPE, TypeId.get(Byte.class).getMethod(TypeId.BYTE, "byteValue", new TypeId[0]));
        map2.put(Long.TYPE, TypeId.get(Long.class).getMethod(TypeId.LONG, "longValue", new TypeId[0]));
        map2.put(Short.TYPE, TypeId.get(Short.class).getMethod(TypeId.SHORT, "shortValue", new TypeId[0]));
        map2.put(Float.TYPE, TypeId.get(Float.class).getMethod(TypeId.FLOAT, "floatValue", new TypeId[0]));
        map2.put(Double.TYPE, TypeId.get(Double.class).getMethod(TypeId.DOUBLE, "doubleValue", new TypeId[0]));
        map2.put(Character.TYPE, TypeId.get(Character.class).getMethod(TypeId.CHAR, "charValue", new TypeId[0]));
        PRIMITIVE_TO_UNBOX_METHOD = map2;
    }

    private ProxyBuilder(Class<T> cls) {
        this.baseClass = cls;
    }

    private static <T> Set<T> asSet(T... tArr) {
        return new CopyOnWriteArraySet(Arrays.asList(tArr));
    }

    private static Local<?> boxIfRequired(Code code, Local<?> local, Local<Object> local2) {
        MethodId<?, ?> methodId = PRIMITIVE_TYPE_TO_UNBOX_METHOD.get(local.getType());
        if (methodId == null) {
            return local;
        }
        code.invokeStatic(methodId, local2, local);
        return local2;
    }

    public static Object callSuper(Object obj, Method method, Object... objArr) throws Throwable {
        try {
            return obj.getClass().getMethod(superMethodName(method), method.getParameterTypes()).invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private static void check(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    private static TypeId<?>[] classArrayToTypeArray(Class<?>[] clsArr) {
        TypeId<?>[] typeIdArr = new TypeId[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            typeIdArr[i] = TypeId.get(clsArr[i]);
        }
        return typeIdArr;
    }

    public static <T> ProxyBuilder<T> forClass(Class<T> cls) {
        return new ProxyBuilder<>(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T, G extends T> void generateCodeForAllMethods(DexMaker dexMaker, TypeId<G> typeId, Method[] methodArr, TypeId<T> typeId2) {
        MethodId methodId;
        DexMaker dexMaker2 = dexMaker;
        TypeId<G> typeId3 = typeId;
        Method[] methodArr2 = methodArr;
        TypeId<V> typeId4 = TypeId.get(InvocationHandler.class);
        TypeId<V> typeId5 = TypeId.get(Method[].class);
        Object field = typeId3.getField(typeId4, FIELD_NAME_HANDLER);
        Object field2 = typeId3.getField(typeId5, FIELD_NAME_METHODS);
        TypeId typeId6 = TypeId.get(Method.class);
        TypeId typeId7 = TypeId.get(Object[].class);
        TypeId<Object> typeId8 = TypeId.OBJECT;
        MethodId method = typeId4.getMethod(typeId8, "invoke", typeId8, typeId6, typeId7);
        int i = 0;
        Object obj = typeId4;
        Object obj2 = typeId5;
        while (i < methodArr2.length) {
            Method method2 = methodArr2[i];
            String name = method2.getName();
            Class<?>[] parameterTypes = method2.getParameterTypes();
            int length = parameterTypes.length;
            TypeId<?>[] typeIdArr = new TypeId[length];
            for (int i2 = 0; i2 < length; i2++) {
                typeIdArr[i2] = TypeId.get(parameterTypes[i2]);
            }
            Class<?> returnType = method2.getReturnType();
            TypeId<R> typeId9 = TypeId.get(returnType);
            Object obj3 = field;
            MethodId methodId2 = method;
            MethodId method3 = typeId2.getMethod(typeId9, name, typeIdArr);
            Code codeDeclare = dexMaker2.declare(typeId3.getMethod(typeId9, name, typeIdArr), 1);
            Local local = codeDeclare.getThis(typeId3);
            Local localNewLocal = codeDeclare.newLocal(obj);
            TypeId<Object> typeId10 = TypeId.OBJECT;
            Local localNewLocal2 = codeDeclare.newLocal(typeId10);
            TypeId<Integer> typeId11 = TypeId.INT;
            Local localNewLocal3 = codeDeclare.newLocal(typeId11);
            Local localNewLocal4 = codeDeclare.newLocal(typeId7);
            TypeId typeId12 = typeId7;
            Local localNewLocal5 = codeDeclare.newLocal(typeId11);
            Local localNewLocal6 = codeDeclare.newLocal(typeId10);
            Local localNewLocal7 = codeDeclare.newLocal(typeId9);
            Local localNewLocal8 = codeDeclare.newLocal(obj2);
            Object obj4 = obj2;
            Local localNewLocal9 = codeDeclare.newLocal(typeId6);
            Local localNewLocal10 = codeDeclare.newLocal(typeId11);
            TypeId typeId13 = typeId6;
            Class<?> cls = PRIMITIVE_TO_BOXED.get(returnType);
            Local localNewLocal11 = cls != null ? codeDeclare.newLocal(TypeId.get(cls)) : null;
            int length2 = parameterTypes.length;
            Local[] localArr = new Local[length2];
            Local localNewLocal12 = codeDeclare.newLocal(typeId9);
            Local localNewLocal13 = codeDeclare.newLocal(obj);
            Object obj5 = obj;
            codeDeclare.loadConstant(localNewLocal10, Integer.valueOf(i));
            codeDeclare.sget(field2, localNewLocal8);
            codeDeclare.aget(localNewLocal9, localNewLocal8, localNewLocal10);
            codeDeclare.loadConstant(localNewLocal5, Integer.valueOf(length));
            codeDeclare.newArray(localNewLocal4, localNewLocal5);
            Object obj6 = obj3;
            codeDeclare.iget(obj6, localNewLocal, local);
            codeDeclare.loadConstant(localNewLocal13, null);
            Label label = new Label();
            codeDeclare.compare(Comparison.EQ, label, localNewLocal13, localNewLocal);
            int i3 = 0;
            while (i3 < length) {
                codeDeclare.loadConstant(localNewLocal3, Integer.valueOf(i3));
                codeDeclare.aput(localNewLocal4, localNewLocal3, boxIfRequired(codeDeclare, codeDeclare.getParameter(i3, typeIdArr[i3]), localNewLocal6));
                i3++;
                obj6 = obj6;
            }
            Object obj7 = obj6;
            codeDeclare.invokeInterface(methodId2, localNewLocal2, localNewLocal, local, localNewLocal9, localNewLocal4);
            generateCodeForReturnStatement(codeDeclare, returnType, localNewLocal2, localNewLocal7, localNewLocal11);
            codeDeclare.mark(label);
            for (int i4 = 0; i4 < length2; i4++) {
                localArr[i4] = codeDeclare.getParameter(i4, typeIdArr[i4]);
            }
            if (Void.TYPE.equals(returnType)) {
                methodId = method3;
                codeDeclare.invokeSuper(methodId, null, local, localArr);
                codeDeclare.returnVoid();
            } else {
                methodId = method3;
                invokeSuper(methodId, codeDeclare, local, localArr, localNewLocal12);
                codeDeclare.returnValue(localNewLocal12);
            }
            Code codeDeclare2 = dexMaker.declare(typeId.getMethod(typeId9, superMethodName(method2), typeIdArr), 1);
            Local<T> local2 = codeDeclare2.getThis(typeId);
            int length3 = parameterTypes.length;
            Local<?>[] localArr2 = new Local[length3];
            for (int i5 = 0; i5 < length3; i5++) {
                localArr2[i5] = codeDeclare2.getParameter(i5, typeIdArr[i5]);
            }
            if (Void.TYPE.equals(returnType)) {
                codeDeclare2.invokeSuper(methodId, null, local2, localArr2);
                codeDeclare2.returnVoid();
            } else {
                Local<T> localNewLocal14 = codeDeclare2.newLocal(typeId9);
                invokeSuper(methodId, codeDeclare2, local2, localArr2, localNewLocal14);
                codeDeclare2.returnValue(localNewLocal14);
            }
            i++;
            methodArr2 = methodArr;
            typeId3 = typeId;
            method = methodId2;
            dexMaker2 = dexMaker;
            field = obj7;
            typeId7 = typeId12;
            obj2 = obj4;
            typeId6 = typeId13;
            obj = obj5;
        }
    }

    private static void generateCodeForReturnStatement(Code code, Class cls, Local local, Local local2, Local local3) {
        if (PRIMITIVE_TO_UNBOX_METHOD.containsKey(cls)) {
            code.cast(local3, local);
            code.invokeVirtual(getUnboxMethodForPrimitive(cls), local2, local3, new Local[0]);
        } else {
            if (Void.TYPE.equals(cls)) {
                code.returnVoid();
                return;
            }
            code.cast(local2, local);
        }
        code.returnValue(local2);
    }

    private static <T, G extends T> void generateConstructorsAndFields(DexMaker dexMaker, TypeId<G> typeId, TypeId<T> typeId2, Class<T> cls) {
        TypeId<V> typeId3 = TypeId.get(InvocationHandler.class);
        TypeId<V> typeId4 = TypeId.get(Method[].class);
        dexMaker.declare(typeId.getField(typeId3, FIELD_NAME_HANDLER), 2, null);
        dexMaker.declare(typeId.getField(typeId4, FIELD_NAME_METHODS), 10, null);
        for (Constructor constructor : getConstructorsToOverwrite(cls)) {
            if (constructor.getModifiers() != 16) {
                TypeId<?>[] typeIdArrClassArrayToTypeArray = classArrayToTypeArray(constructor.getParameterTypes());
                Code codeDeclare = dexMaker.declare(typeId.getConstructor(typeIdArrClassArrayToTypeArray), 1);
                Local<T> local = codeDeclare.getThis(typeId);
                int length = typeIdArrClassArrayToTypeArray.length;
                Local<?>[] localArr = new Local[length];
                for (int i = 0; i < length; i++) {
                    localArr[i] = codeDeclare.getParameter(i, typeIdArrClassArrayToTypeArray[i]);
                }
                codeDeclare.invokeDirect(typeId2.getConstructor(typeIdArrClassArrayToTypeArray), null, local, localArr);
                codeDeclare.returnVoid();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> Constructor<T>[] getConstructorsToOverwrite(Class<T> cls) {
        return (Constructor<T>[]) cls.getDeclaredConstructors();
    }

    private TypeId<?>[] getInterfacesAsTypeIds() {
        TypeId<?>[] typeIdArr = new TypeId[this.interfaces.size()];
        Iterator<Class<?>> it = this.interfaces.iterator();
        int i = 0;
        while (it.hasNext()) {
            typeIdArr[i] = TypeId.get(it.next());
            i++;
        }
        return typeIdArr;
    }

    public static InvocationHandler getInvocationHandler(Object obj) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(FIELD_NAME_HANDLER);
            declaredField.setAccessible(true);
            return (InvocationHandler) declaredField.get(obj);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (NoSuchFieldException e2) {
            throw new IllegalArgumentException("Not a valid proxy instance", e2);
        }
    }

    private static <T> String getMethodNameForProxyOf(Class<T> cls) {
        return cls.getSimpleName() + "_Proxy";
    }

    private void getMethodsToProxy(Set<MethodSetEntry> set, Set<MethodSetEntry> set2, Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if ((method.getModifiers() & 16) != 0) {
                MethodSetEntry methodSetEntry = new MethodSetEntry(method);
                set2.add(methodSetEntry);
                set.remove(methodSetEntry);
            } else if ((method.getModifiers() & 8) == 0 && ((Modifier.isPublic(method.getModifiers()) || Modifier.isProtected(method.getModifiers())) && (!method.getName().equals("finalize") || method.getParameterTypes().length != 0))) {
                MethodSetEntry methodSetEntry2 = new MethodSetEntry(method);
                if (!set2.contains(methodSetEntry2)) {
                    set.add(methodSetEntry2);
                }
            }
        }
    }

    private Method[] getMethodsToProxyRecursive() {
        int i;
        HashSet hashSet = new HashSet();
        Set<MethodSetEntry> hashSet2 = new HashSet<>();
        for (Class<T> superclass = this.baseClass; superclass != null; superclass = superclass.getSuperclass()) {
            getMethodsToProxy(hashSet, hashSet2, superclass);
        }
        Class<T> superclass2 = this.baseClass;
        while (true) {
            i = 0;
            if (superclass2 == null) {
                break;
            }
            Class<?>[] interfaces = superclass2.getInterfaces();
            int length = interfaces.length;
            while (i < length) {
                getMethodsToProxy(hashSet, hashSet2, interfaces[i]);
                i++;
            }
            superclass2 = superclass2.getSuperclass();
        }
        Iterator<Class<?>> it = this.interfaces.iterator();
        while (it.hasNext()) {
            getMethodsToProxy(hashSet, hashSet2, it.next());
        }
        Method[] methodArr = new Method[hashSet.size()];
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            methodArr[i] = ((MethodSetEntry) it2.next()).originalMethod;
            i++;
        }
        Arrays.sort(methodArr, new Comparator<Method>(this) { // from class: com.android.cglib.dx.stock.ProxyBuilder.1
            public final ProxyBuilder this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.Comparator
            public int compare(Method method, Method method2) {
                return method.toString().compareTo(method2.toString());
            }
        });
        return methodArr;
    }

    private static MethodId<?, ?> getUnboxMethodForPrimitive(Class<?> cls) {
        return PRIMITIVE_TO_UNBOX_METHOD.get(cls);
    }

    private static void invokeSuper(MethodId methodId, Code code, Local local, Local[] localArr, Local local2) {
        code.invokeSuper(methodId, local2, local, localArr);
    }

    public static boolean isProxyClass(Class<?> cls) {
        try {
            cls.getDeclaredField(FIELD_NAME_HANDLER);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private static RuntimeException launderCause(InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        if (cause instanceof RuntimeException) {
            throw ((RuntimeException) cause);
        }
        throw new UndeclaredThrowableException(cause);
    }

    private Class<? extends T> loadClass(ClassLoader classLoader, String str) {
        return (Class<? extends T>) classLoader.loadClass(str);
    }

    public static void setInvocationHandler(Object obj, InvocationHandler invocationHandler) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(FIELD_NAME_HANDLER);
            declaredField.setAccessible(true);
            declaredField.set(obj, invocationHandler);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (NoSuchFieldException e2) {
            throw new IllegalArgumentException("Not a valid proxy instance", e2);
        }
    }

    private static void setMethodsStaticField(Class<?> cls, Method[] methodArr) {
        try {
            Field declaredField = cls.getDeclaredField(FIELD_NAME_METHODS);
            declaredField.setAccessible(true);
            declaredField.set(null, methodArr);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (NoSuchFieldException e2) {
            throw new AssertionError(e2);
        }
    }

    private static String superMethodName(Method method) {
        String name = method.getReturnType().getName();
        StringBuilder sbO = a.o("super$");
        sbO.append(method.getName());
        sbO.append("$");
        sbO.append(name.replace('.', '_').replace('[', '_').replace(';', '_'));
        return sbO.toString();
    }

    public T build() {
        check(this.handler != null, "handler == null");
        check(this.constructorArgTypes.length == this.constructorArgValues.length, "constructorArgValues.length != constructorArgTypes.length");
        try {
            try {
                T tNewInstance = buildProxyClass().getConstructor(this.constructorArgTypes).newInstance(this.constructorArgValues);
                setInvocationHandler(tNewInstance, this.handler);
                return tNewInstance;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InstantiationException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                throw launderCause(e3);
            }
        } catch (NoSuchMethodException e4) {
            StringBuilder sbO = a.o("No constructor for ");
            sbO.append(this.baseClass.getName());
            sbO.append(" with parameter types ");
            sbO.append(Arrays.toString(this.constructorArgTypes));
            throw new IllegalArgumentException(sbO.toString());
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public Class<? extends T> buildProxyClass() {
        Map<Class<?>, Class<?>> map = generatedProxyClasses;
        Class<? extends T> clsLoadClass = (Class) map.get(this.baseClass);
        if (clsLoadClass == null || clsLoadClass.getClassLoader().getParent() != this.parentClassLoader || !this.interfaces.equals(asSet(clsLoadClass.getInterfaces()))) {
            DexMaker dexMaker = new DexMaker();
            String methodNameForProxyOf = getMethodNameForProxyOf(this.baseClass);
            TypeId<?> typeId = TypeId.get("L" + methodNameForProxyOf + ";");
            TypeId<?> typeId2 = TypeId.get(this.baseClass);
            generateConstructorsAndFields(dexMaker, typeId, typeId2, this.baseClass);
            Method[] methodsToProxyRecursive = getMethodsToProxyRecursive();
            generateCodeForAllMethods(dexMaker, typeId, methodsToProxyRecursive, typeId2);
            dexMaker.declare(typeId, a.j(methodNameForProxyOf, ".generated"), 1, typeId2, getInterfacesAsTypeIds());
            try {
                clsLoadClass = loadClass(dexMaker.generateAndLoad(this.parentClassLoader, this.dexCache), methodNameForProxyOf);
                setMethodsStaticField(clsLoadClass, methodsToProxyRecursive);
                map.put((Class<?>) this.baseClass, clsLoadClass);
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            } catch (IllegalAccessError e2) {
                StringBuilder sbO = a.o("cannot proxy inaccessible class ");
                sbO.append(this.baseClass);
                throw new UnsupportedOperationException(sbO.toString(), e2);
            }
        }
        return clsLoadClass;
    }

    public ProxyBuilder<T> constructorArgTypes(Class<?>... clsArr) {
        this.constructorArgTypes = clsArr;
        return this;
    }

    public ProxyBuilder<T> constructorArgValues(Object... objArr) {
        this.constructorArgValues = objArr;
        return this;
    }

    public ProxyBuilder<T> dexCache(File file) {
        StringBuilder sbO = a.o("v");
        sbO.append(Integer.toString(1));
        File file2 = new File(file, sbO.toString());
        this.dexCache = file2;
        file2.mkdir();
        return this;
    }

    public ProxyBuilder<T> handler(InvocationHandler invocationHandler) {
        this.handler = invocationHandler;
        return this;
    }

    public ProxyBuilder<T> implementing(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            if (!cls.isInterface()) {
                StringBuilder sbO = a.o("Not an interface: ");
                sbO.append(cls.getName());
                throw new IllegalArgumentException(sbO.toString());
            }
            this.interfaces.add(cls);
        }
        return this;
    }

    public ProxyBuilder<T> parentClassLoader(ClassLoader classLoader) {
        this.parentClassLoader = classLoader;
        return this;
    }
}

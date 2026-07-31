package com.luajava;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.android.cglib.proxy.EnhancerInterface;
import com.android.cglib.proxy.MethodFilter;
import com.androlua.LuaBitmap;
import com.androlua.LuaEnhancer;
import com.androlua.LuaGcable;
import com.baidu.mobstat.Config;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class LuaJavaAPI {
    private static final HashMap<Class<?>, Method[]> a = new HashMap<>();
    private static final Map<String, Method[]> b = new HashMap();
    private static final HashMap<Class<?>, HashMap<String, ArrayList<Method>>> c = new HashMap<>();
    private static final HashMap<String, Method> d = new HashMap<>();
    private static final HashMap<String, Method> e = new HashMap<>();
    private static final HashMap<String, Method> f = new HashMap<>();
    private static final HashMap<String, Method> g = new HashMap<>();
    private static final HashMap<String, Method> h = new HashMap<>();
    private static final HashMap<Integer, Object> i = new HashMap<>();

    private static class JavaObject {
        private final Object a;

        public JavaObject(Object obj) {
            this.a = obj;
        }

        public Object getObject() {
            return this.a;
        }
    }

    private LuaJavaAPI() {
    }

    private static String a(LuaState luaState, String str, int i2, Class cls) throws LuaException {
        throw new LuaException("bad argument to '" + str + "' (" + cls.getName() + " expected, got " + w(luaState, i2) + " value)");
    }

    public static int asTable(long j, int i2) {
        int iC;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        synchronized (existingState) {
            iC = 1;
            if (existingState.isBoolean(-1) && existingState.toBoolean(-1)) {
                existingState.pop(1);
                iC = c(existingState, javaObject);
            } else {
                try {
                    existingState.newTable();
                    int top = existingState.getTop();
                    if (javaObject != null) {
                        if (javaObject.getClass().isArray()) {
                            int length = Array.getLength(javaObject);
                            int i3 = 0;
                            while (i3 <= length - 1) {
                                existingState.pushObjectValue(Array.get(javaObject, i3));
                                i3++;
                                existingState.rawSetI(-2, i3);
                            }
                        } else if (javaObject instanceof Collection) {
                            Iterator it = ((Collection) javaObject).iterator();
                            int i4 = 1;
                            while (it.hasNext()) {
                                existingState.pushObjectValue(it.next());
                                existingState.rawSetI(-2, i4);
                                i4++;
                            }
                        } else if (javaObject instanceof Map) {
                            for (Map.Entry entry : ((Map) javaObject).entrySet()) {
                                existingState.pushObjectValue(entry.getKey());
                                existingState.pushObjectValue(entry.getValue());
                                existingState.setTable(-3);
                            }
                        }
                        existingState.pushValue(top);
                    }
                } catch (Exception e2) {
                    throw new LuaException("can not astable: " + e2.getMessage());
                }
            }
        }
        return iC;
    }

    private static String b(LuaState luaState, String str, int i2, String str2) throws LuaException {
        throw new LuaException("bad argument #" + i2 + " to '" + str + "' (" + str2 + " expected, got " + w(luaState, i2 + 1) + " value)");
    }

    private static int c(LuaState luaState, Object obj) {
        synchronized (luaState) {
            try {
                luaState.newTable();
                if (obj != null) {
                    if (obj.getClass().isArray()) {
                        int length = Array.getLength(obj);
                        int i2 = 0;
                        while (i2 <= length - 1) {
                            c(luaState, Array.get(obj, i2));
                            i2++;
                            luaState.rawSetI(-2, i2);
                        }
                    } else if (obj instanceof Collection) {
                        Iterator it = ((Collection) obj).iterator();
                        int i3 = 1;
                        while (it.hasNext()) {
                            c(luaState, it.next());
                            luaState.rawSetI(-2, i3);
                            i3++;
                        }
                    } else if (obj instanceof Map) {
                        for (Map.Entry entry : ((Map) obj).entrySet()) {
                            luaState.pushObjectValue(entry.getKey());
                            c(luaState, entry.getValue());
                            luaState.setTable(-3);
                        }
                    } else {
                        luaState.pop(1);
                        luaState.pushObjectValue(obj);
                    }
                }
            } catch (Exception e2) {
                throw new LuaException("can not astable: " + e2.getMessage());
            }
        }
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x0169 A[Catch: all -> 0x027d, PHI: r0
  0x0169: PHI (r0v4 byte) = (r0v3 byte), (r0v33 byte) binds: [B:26:0x0086, B:50:0x0106] A[DONT_GENERATE, DONT_INLINE], TryCatch #5 {, blocks: (B:4:0x000d, B:6:0x001a, B:8:0x0025, B:10:0x002d, B:12:0x0039, B:14:0x003c, B:15:0x003f, B:25:0x0082, B:27:0x0088, B:51:0x0108, B:53:0x0112, B:54:0x0115, B:56:0x011b, B:59:0x0129, B:61:0x012f, B:63:0x0142, B:64:0x0146, B:65:0x0168, B:32:0x0094, B:34:0x009f, B:36:0x00a7, B:38:0x00ad, B:40:0x00b8, B:42:0x00cf, B:44:0x00da, B:46:0x00f0, B:48:0x00fb, B:66:0x0169, B:68:0x0178, B:69:0x0182, B:72:0x0187, B:121:0x0222, B:77:0x0195, B:80:0x01a0, B:84:0x01ae, B:87:0x01b9, B:117:0x0203, B:119:0x0216, B:120:0x021a, B:91:0x01c3, B:102:0x01d7, B:107:0x01e6, B:109:0x01eb, B:111:0x01f7, B:113:0x01fa, B:103:0x01da, B:104:0x01dd, B:105:0x01e0, B:106:0x01e3, B:122:0x022c, B:124:0x0232, B:126:0x0236, B:127:0x0247, B:128:0x0261, B:129:0x0262, B:130:0x027c, B:18:0x0044, B:20:0x0057, B:21:0x005b, B:22:0x007d), top: B:144:0x000d, inners: #1, #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int callMethod(long r19, int r21, java.lang.String r22) {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luajava.LuaJavaAPI.callMethod(long, int, java.lang.String):int");
    }

    public static int checkClass(LuaState luaState, Object obj, String str) {
        synchronized (luaState) {
            if (obj instanceof Class) {
                Class cls = (Class) obj;
                try {
                    luaState.pushJavaObject(Class.forName(cls.getName() + "$" + str));
                } catch (Exception e2) {
                    for (Class<?> cls2 : cls.getClasses()) {
                        if (cls2.getSimpleName().equals(str)) {
                            luaState.pushJavaObject(cls2);
                        }
                    }
                    return 0;
                }
                return 3;
            }
            return 0;
        }
    }

    public static int checkField(LuaState luaState, Object obj, String str) {
        Class<?> cls;
        boolean z;
        synchronized (luaState) {
            if (obj instanceof Class) {
                cls = (Class) obj;
                z = true;
            } else {
                cls = obj.getClass();
                z = false;
            }
            try {
                Field field = cls.getField(str);
                if (field != null && (!z || Modifier.isStatic(field.getModifiers()))) {
                    try {
                        if (!Modifier.isPublic(field.getModifiers())) {
                            field.setAccessible(true);
                        }
                        luaState.pushObjectValue(field.get(obj));
                        return Modifier.isFinal(field.getModifiers()) ? 5 : 1;
                    } catch (Exception e2) {
                        throw new LuaException(e2);
                    }
                }
            } catch (NoSuchFieldException e3) {
            }
            return 0;
        }
    }

    public static int checkMethod(LuaState luaState, Object obj, String str) {
        synchronized (luaState) {
            return q(obj, str, luaState.toString(-1)).length == 0 ? 0 : 2;
        }
    }

    public static void clearCaches() {
        b.clear();
        a.clear();
        d.clear();
        e.clear();
        f.clear();
        g.clear();
        h.clear();
    }

    public static int createArray(long j, String str) {
        int iH;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        synchronized (existingState) {
            iH = h(existingState, d(str));
        }
        return iH;
    }

    public static int createProxy(long j, String str) {
        int iO;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        synchronized (existingState) {
            iO = o(existingState, str);
        }
        return iO;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Class d(java.lang.String r2) throws com.luajava.LuaException {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luajava.LuaJavaAPI.d(java.lang.String):java.lang.Class");
    }

    private static Object e(LuaState luaState, Class<?> cls, int i2) {
        return f(luaState, cls, luaState.type(i2), i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x012f A[PHI: r0
  0x012f: PHI (r0v4 java.lang.Object) = 
  (r0v0 java.lang.Object)
  (r0v0 java.lang.Object)
  (r0v0 java.lang.Object)
  (r0v0 java.lang.Object)
  (r0v0 java.lang.Object)
  (r0v0 java.lang.Object)
  (r0v13 java.lang.Object)
 binds: [B:101:0x012c, B:89:0x00f4, B:84:0x00e1, B:80:0x00cf, B:66:0x0094, B:58:0x007b, B:55:0x0070] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x013b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Object f(com.luajava.LuaState r3, java.lang.Class<?> r4, int r5, int r6) throws com.luajava.LuaException {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luajava.LuaJavaAPI.f(com.luajava.LuaState, java.lang.Class, int, int):java.lang.Object");
    }

    private static int g(LuaState luaState, Class<?> cls) {
        try {
            EnhancerInterface enhancerInterface = (EnhancerInterface) new LuaEnhancer(cls).create(new MethodFilter() { // from class: com.luajava.LuaJavaAPI.2
                @Override // com.android.cglib.proxy.MethodFilter
                public boolean filter(Method method, String str) {
                    return (method.getModifiers() & 1024) == 0;
                }
            }).newInstance();
            enhancerInterface.setMethodInterceptor_Enhancer(new LuaAbstractMethodInterceptor(luaState.getLuaObject(-1)));
            luaState.pushJavaObject(enhancerInterface);
            return 1;
        } catch (Exception e2) {
            return 0;
        }
    }

    public static int getArrayValue(long j, int i2, int i3) {
        Object obj;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        synchronized (existingState) {
            if (javaObject.getClass().isArray()) {
                obj = Array.get(javaObject, i3);
            } else if (javaObject instanceof List) {
                obj = ((List) javaObject).get(i3);
            } else {
                if (!(javaObject instanceof Map)) {
                    throw new LuaException("can not get " + javaObject.getClass().getName() + " value in " + i3);
                }
                obj = ((Map) javaObject).get(Long.valueOf(i3));
            }
            existingState.pushObjectValue(obj);
        }
        return 1;
    }

    public static int getContext(long j) {
        LuaState existingState = LuaStateFactory.getExistingState(j);
        synchronized (existingState) {
            existingState.pushJavaObject(existingState.getContext());
        }
        return 1;
    }

    public static Object getJavaObject2(int i2) {
        return i.get(Integer.valueOf(i2));
    }

    public static ArrayList<Method> getMethod(Class<?> cls, String str, boolean z) {
        HashMap<Class<?>, HashMap<String, ArrayList<Method>>> map = c;
        HashMap<String, ArrayList<Method>> map2 = map.get(cls);
        if (map2 == null) {
            map2 = new HashMap<>();
            map.put(cls, map2);
        }
        ArrayList<Method> arrayList = map2.get(str);
        if (arrayList == null) {
            HashMap<Class<?>, Method[]> map3 = a;
            Method[] methods = map3.get(cls);
            if (methods == null) {
                methods = cls.getMethods();
                map3.put(cls, methods);
            }
            for (Method method : methods) {
                String name = method.getName();
                ArrayList<Method> arrayList2 = map2.get(name);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                    map2.put(name, arrayList2);
                }
                arrayList2.add(method);
            }
            arrayList = map2.get(str);
        }
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        if (!z) {
            return arrayList;
        }
        ArrayList<Method> arrayList3 = new ArrayList<>();
        for (Method method2 : arrayList) {
            if (Modifier.isStatic(method2.getModifiers())) {
                arrayList3.add(method2);
            }
        }
        return arrayList3.isEmpty() ? getMethod(Class.class, str, false) : arrayList3;
    }

    private static int h(LuaState luaState, Class<?> cls) {
        synchronized (luaState) {
            luaState.pushJavaObject(i(luaState, cls, 2));
        }
        return 1;
    }

    private static Object i(LuaState luaState, Class<?> cls, int i2) {
        Object objNewInstance;
        synchronized (luaState) {
            try {
                try {
                    int iObjLen = luaState.objLen(i2);
                    objNewInstance = Array.newInstance(cls, iObjLen);
                    if (cls == String.class) {
                        for (int i3 = 1; i3 <= iObjLen; i3++) {
                            luaState.pushNumber(i3);
                            luaState.getTable(i2);
                            Array.set(objNewInstance, i3 - 1, luaState.toString(-1));
                            luaState.pop(1);
                        }
                    } else if (cls == Double.TYPE) {
                        for (int i4 = 1; i4 <= iObjLen; i4++) {
                            luaState.pushNumber(i4);
                            luaState.getTable(i2);
                            Array.set(objNewInstance, i4 - 1, Double.valueOf(luaState.toNumber(-1)));
                            luaState.pop(1);
                        }
                    } else if (cls == Float.TYPE) {
                        for (int i5 = 1; i5 <= iObjLen; i5++) {
                            luaState.pushNumber(i5);
                            luaState.getTable(i2);
                            Array.set(objNewInstance, i5 - 1, Float.valueOf((float) luaState.toNumber(-1)));
                            luaState.pop(1);
                        }
                    } else if (cls == Long.TYPE) {
                        for (int i6 = 1; i6 <= iObjLen; i6++) {
                            luaState.pushNumber(i6);
                            luaState.getTable(i2);
                            Array.set(objNewInstance, i6 - 1, Long.valueOf(luaState.toInteger(-1)));
                            luaState.pop(1);
                        }
                    } else if (cls == Integer.TYPE) {
                        for (int i7 = 1; i7 <= iObjLen; i7++) {
                            luaState.pushNumber(i7);
                            luaState.getTable(i2);
                            Array.set(objNewInstance, i7 - 1, Integer.valueOf((int) luaState.toInteger(-1)));
                            luaState.pop(1);
                        }
                    } else if (cls == Short.TYPE) {
                        for (int i8 = 1; i8 <= iObjLen; i8++) {
                            luaState.pushNumber(i8);
                            luaState.getTable(i2);
                            Array.set(objNewInstance, i8 - 1, Short.valueOf((short) luaState.toInteger(-1)));
                            luaState.pop(1);
                        }
                    } else if (cls == Character.TYPE) {
                        for (int i9 = 1; i9 <= iObjLen; i9++) {
                            luaState.pushNumber(i9);
                            luaState.getTable(i2);
                            Array.set(objNewInstance, i9 - 1, Character.valueOf((char) luaState.toInteger(-1)));
                            luaState.pop(1);
                        }
                    } else if (cls == Byte.TYPE) {
                        for (int i10 = 1; i10 <= iObjLen; i10++) {
                            luaState.pushNumber(i10);
                            luaState.getTable(i2);
                            Array.set(objNewInstance, i10 - 1, Byte.valueOf((byte) luaState.toInteger(-1)));
                            luaState.pop(1);
                        }
                    } else {
                        for (int i11 = 1; i11 <= iObjLen; i11++) {
                            luaState.pushNumber(i11);
                            luaState.getTable(i2);
                            Array.set(objNewInstance, i11 - 1, e(luaState, cls, luaState.getTop()));
                            luaState.pop(1);
                        }
                    }
                } catch (Exception e2) {
                    throw new LuaException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return objNewInstance;
    }

    private static int j(LuaState luaState, Class<?> cls) {
        synchronized (luaState) {
            luaState.pushJavaObject(k(luaState, cls, 2));
        }
        return 1;
    }

    public static int javaBindClass(long j, String str) {
        LuaStateFactory.getExistingState(j).pushJavaObject(d(str));
        return 1;
    }

    public static void javaClose(long j, int i2) {
        Bitmap bitmap;
        Object javaObject = LuaStateFactory.getExistingState(j).getJavaObject(i2);
        if (javaObject == null) {
            return;
        }
        try {
            if (javaObject instanceof LuaGcable) {
                ((LuaGcable) javaObject).gc();
                return;
            }
            if (javaObject instanceof Bitmap) {
                LuaBitmap.removeBitmap((Bitmap) javaObject);
                bitmap = (Bitmap) javaObject;
            } else {
                if (!(javaObject instanceof BitmapDrawable)) {
                    if (javaObject instanceof AutoCloseable) {
                        ((AutoCloseable) javaObject).close();
                        return;
                    }
                    return;
                }
                bitmap = ((BitmapDrawable) javaObject).getBitmap();
            }
            bitmap.recycle();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static int javaCreate(long j, int i2) {
        int iH;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Class cls = (Class) existingState.getJavaObject(i2);
        synchronized (existingState) {
            if (cls.isPrimitive() || cls == String.class || cls.isArray()) {
                iH = h(existingState, cls);
            } else if (List.class.isAssignableFrom(cls)) {
                iH = j(existingState, cls);
            } else if (Map.class.isAssignableFrom(cls)) {
                iH = l(existingState, cls);
            } else if (cls.isInterface()) {
                iH = n(existingState, cls);
            } else if ((cls.getModifiers() & 1024) != 0) {
                iH = g(existingState, cls);
            } else if (existingState.objLen(-1) == 0) {
                iH = h(existingState, cls);
            } else {
                existingState.getI(-1, 1L);
                Object javaObject = existingState.toJavaObject(-1);
                existingState.pop(1);
                iH = cls.isAssignableFrom(javaObject.getClass()) ? h(existingState, cls) : r(existingState, cls);
            }
        }
        return iH;
    }

    public static int javaEquals(long j, int i2, int i3) {
        boolean zEquals;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        Object javaObject2 = existingState.getJavaObject(i3);
        synchronized (existingState) {
            zEquals = javaObject.equals(javaObject2);
        }
        return zEquals ? 1 : 0;
    }

    public static void javaGc(long j, int i2) {
        LuaStateFactory.getExistingState(j).removeJavaObject(i2);
    }

    public static Object javaGetObject(long j, int i2) {
        return LuaStateFactory.getExistingState(j).getJavaObject(i2);
    }

    public static int javaGetType(long j, int i2) {
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        synchronized (existingState) {
            existingState.pushString(javaObject == null ? "null" : javaObject.getClass().getName());
        }
        return 1;
    }

    public static int javaGetter(LuaState luaState, Object obj, String str) {
        Class<?> cls;
        boolean z;
        Method method;
        synchronized (luaState) {
            if (obj instanceof Map) {
                luaState.pushObjectValue(((Map) obj).get(str));
            } else {
                if (obj instanceof Class) {
                    cls = (Class) obj;
                    z = true;
                } else {
                    cls = obj.getClass();
                    z = false;
                }
                char cCharAt = str.charAt(0);
                if (Character.isLowerCase(cCharAt)) {
                    str = Character.toUpperCase(cCharAt) + str.substring(1);
                }
                String str2 = cls.toString() + "@<-" + str;
                Method method2 = !z ? h.get(str2) : null;
                if (method2 == null) {
                    try {
                        method = cls.getMethod("get" + str, new Class[0]);
                    } catch (NoSuchMethodException e2) {
                        try {
                            method = cls.getMethod("is" + str, new Class[0]);
                        } catch (NoSuchMethodException e3) {
                        }
                    }
                    method2 = method;
                    if (z && !Modifier.isStatic(method2.getModifiers())) {
                        return 0;
                    }
                    h.put(str2, method2);
                }
                try {
                    Object objInvoke = method2.invoke(obj, new Object[0]);
                    if (objInvoke instanceof CharSequence) {
                        luaState.pushString(objInvoke.toString());
                    } else {
                        luaState.pushObjectValue(objInvoke);
                    }
                } catch (Exception e4) {
                    throw new LuaException(e4);
                }
            }
            return 1;
        }
    }

    public static int javaInstanceof(long j, int i2, int i3) {
        boolean zIsInstance;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        Class cls = (Class) existingState.getJavaObject(i3);
        synchronized (existingState) {
            zIsInstance = cls.isInstance(javaObject);
        }
        return zIsInstance ? 1 : 0;
    }

    public static int javaLoadLib(long j, String str, String str2) {
        int iIntValue;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        synchronized (existingState) {
            try {
                try {
                    try {
                        iIntValue = 0;
                        Object objInvoke = Class.forName(str).getMethod(str2, LuaState.class).invoke(null, existingState);
                        if (objInvoke != null && (objInvoke instanceof Integer)) {
                            iIntValue = ((Integer) objInvoke).intValue();
                        }
                    } catch (Exception e2) {
                        throw new LuaException("Error on calling method. Library could not be loaded. " + e2.getMessage());
                    }
                } catch (ClassNotFoundException e3) {
                    throw new LuaException(e3);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return iIntValue;
    }

    public static int javaNew(long j, int i2) {
        int iR;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Class cls = (Class) existingState.getJavaObject(i2);
        synchronized (existingState) {
            if (cls.isPrimitive()) {
                int top = existingState.getTop();
                for (int i3 = 2; i3 <= top; i3++) {
                    v(existingState, cls, i3);
                }
                return top - 1;
            }
            if ((cls.getModifiers() & 1024) == 0) {
                iR = r(existingState, cls);
            } else {
                if (!existingState.isTable(2)) {
                    b(existingState, "javaOverride", 1, "table");
                    throw null;
                }
                iR = javaOverride(j, i2);
            }
            return iR;
        }
    }

    public static int javaNewInstance(long j, String str) {
        int iV;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        synchronized (existingState) {
            Class clsD = d(str);
            iV = clsD.isPrimitive() ? v(existingState, clsD, -1) : r(existingState, clsD);
        }
        return iV;
    }

    public static int javaObjectLength(long j, int i2) {
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        synchronized (existingState) {
            try {
                try {
                    existingState.pushInteger(javaObject instanceof CharSequence ? ((CharSequence) javaObject).length() : javaObject instanceof Collection ? ((Collection) javaObject).size() : javaObject instanceof Map ? ((Map) javaObject).size() : Array.getLength(javaObject));
                } catch (Exception e2) {
                    throw new LuaException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    public static int javaOverride(long j, int i2) {
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Class cls = (Class) existingState.getJavaObject(i2);
        synchronized (existingState) {
            LuaTable luaTable = new LuaTable(existingState, 2);
            existingState.remove(2);
            if (r(existingState, new LuaEnhancer((Class<?>) cls).create(new MethodFilter(luaTable) { // from class: com.luajava.LuaJavaAPI.1
                final LuaTable a;

                {
                    this.a = luaTable;
                }

                @Override // com.android.cglib.proxy.MethodFilter
                public boolean filter(Method method, String str) {
                    return this.a.containsKey(str);
                }
            })) == 0) {
                return 0;
            }
            EnhancerInterface enhancerInterface = (EnhancerInterface) existingState.toJavaObject(-1);
            enhancerInterface.setMethodInterceptor_Enhancer(new LuaMethodInterceptor(luaTable));
            existingState.pushJavaObject(enhancerInterface);
            return 1;
        }
    }

    public static int javaSetter(LuaState luaState, Object obj, String str) {
        int iT;
        synchronized (luaState) {
            boolean z = true;
            iT = 1;
            if (obj instanceof Map) {
                ((Map) obj).put(str, luaState.toJavaObject(-1));
            } else {
                if (obj instanceof Class) {
                } else {
                    obj.getClass();
                    z = false;
                }
                if (str.length() > 2 && str.substring(0, 2).equals("on") && luaState.type(-1) == 6) {
                    iT = s(luaState, obj, str, z);
                } else {
                    iT = t(luaState, obj, str, z);
                    if (iT == 0) {
                        iT = u(luaState, obj, str);
                    }
                }
            }
        }
        return iT;
    }

    public static int javaSetter(LuaState luaState, Object obj, String str, Object obj2) {
        luaState.pushObjectValue(obj2);
        int iJavaSetter = javaSetter(luaState, obj, str);
        luaState.pop(1);
        return iJavaSetter;
    }

    public static int javaToString(long j, int i2) {
        String name;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        synchronized (existingState) {
            if (javaObject == null) {
                name = "null";
            } else {
                String string = javaObject.toString();
                if (string != null) {
                    existingState.pushString(string);
                } else {
                    name = javaObject.getClass().getName();
                }
            }
            existingState.pushString(name);
        }
        return 1;
    }

    private static Object k(LuaState luaState, Class<List<Object>> cls, int i2) {
        List<Object> arrayList;
        synchronized (luaState) {
            int iObjLen = luaState.objLen(i2);
            try {
                arrayList = cls.equals(List.class) ? new ArrayList<>() : cls.newInstance();
                for (int i3 = 1; i3 <= iObjLen; i3++) {
                    luaState.pushNumber(i3);
                    luaState.getTable(i2);
                    arrayList.add(luaState.toJavaObject(-1));
                    luaState.pop(1);
                }
            } catch (Exception e2) {
                throw new LuaException(e2);
            }
        }
        return arrayList;
    }

    private static int l(LuaState luaState, Class<?> cls) {
        synchronized (luaState) {
            luaState.pushJavaObject(m(luaState, cls, 2));
        }
        return 1;
    }

    private static Object m(LuaState luaState, Class<Map<Object, Object>> cls, int i2) {
        Map<Object, Object> map;
        synchronized (luaState) {
            try {
                try {
                    map = cls.equals(Map.class) ? new HashMap<>() : cls.newInstance();
                    luaState.pushNil();
                    while (luaState.next(i2) != 0) {
                        map.put(luaState.toJavaObject(-2), luaState.toJavaObject(-1));
                        luaState.pop(1);
                    }
                } catch (Exception e2) {
                    throw new LuaException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return map;
    }

    private static int n(LuaState luaState, Class cls) {
        synchronized (luaState) {
            luaState.pushJavaObject(p(luaState, cls, 2));
        }
        return 1;
    }

    public static int newArray(long j, int i2) {
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Class cls = (Class) existingState.getJavaObject(i2);
        synchronized (existingState) {
            try {
                try {
                    int top = existingState.getTop() - 1;
                    int[] iArr = new int[top];
                    for (int i3 = 0; i3 < top; i3++) {
                        iArr[i3] = (int) existingState.toInteger(i3 + 2);
                    }
                    existingState.pushJavaObject(Array.newInstance((Class<?>) cls, iArr));
                } catch (Exception e2) {
                    throw new LuaException("can not create a array: " + e2.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    public static int newArray(long j, int i2, int i3) {
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Class cls = (Class) existingState.getJavaObject(i2);
        synchronized (existingState) {
            try {
                try {
                    existingState.pushJavaObject(Array.newInstance((Class<?>) cls, i3));
                } catch (Exception e2) {
                    throw new LuaException("can not create a array: " + e2.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    private static int o(LuaState luaState, String str) {
        synchronized (luaState) {
            try {
                try {
                    luaState.pushJavaObject(luaState.getLuaObject(2).createProxy(str));
                } catch (Exception e2) {
                    throw new LuaException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    public static int objectCall(long j, int i2) {
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        synchronized (existingState) {
            int iH = 1;
            if (javaObject instanceof LuaMetaTable) {
                int top = existingState.getTop();
                Object[] objArr = new Object[top - 1];
                for (int i3 = 2; i3 <= top; i3++) {
                    objArr[i3 - 2] = existingState.toJavaObject(i3);
                }
                existingState.pushObjectValue(((LuaMetaTable) javaObject).__call(objArr));
            } else {
                if (!existingState.isTable(2)) {
                    return 0;
                }
                if (javaObject.getClass().isArray() && Array.getLength(javaObject) == 0) {
                    iH = h(existingState, javaObject.getClass());
                } else {
                    existingState.pushNil();
                    if (javaObject instanceof List) {
                        List list = (List) javaObject;
                        while (existingState.next(2) != 0) {
                            list.add(existingState.toJavaObject(-1));
                            existingState.pop(1);
                        }
                    } else {
                        while (existingState.next(2) != 0) {
                            if (existingState.isNumber(-2)) {
                                setArrayValue(existingState, javaObject, (int) existingState.toInteger(-2));
                            } else {
                                javaSetter(existingState, javaObject, existingState.toString(-2));
                            }
                            existingState.pop(1);
                        }
                    }
                    existingState.setTop(1);
                }
            }
            return iH;
        }
    }

    public static int objectIndex(long j, int i2, String str, int i3) {
        int iCheckField;
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        synchronized (existingState) {
            if (i3 == 0) {
                try {
                    if (checkMethod(existingState, javaObject, str) != 0) {
                        return 2;
                    }
                } finally {
                }
            }
            if ((i3 != 0 && i3 != 1 && i3 != 5) || (iCheckField = checkField(existingState, javaObject, str)) == 0) {
                iCheckField = 4;
                if ((i3 != 0 && i3 != 4) || javaGetter(existingState, javaObject, str) == 0) {
                    iCheckField = 3;
                    if ((i3 != 0 && i3 != 3) || checkClass(existingState, javaObject, str) == 0) {
                        iCheckField = 6;
                        if ((i3 != 0 && i3 != 6) || !(javaObject instanceof LuaMetaTable)) {
                            return 0;
                        }
                        existingState.pushObjectValue(((LuaMetaTable) javaObject).__index(str));
                    }
                }
            }
            return iCheckField;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int objectNewIndex(long r1, int r3, java.lang.String r4, int r5) {
        /*
            com.luajava.LuaState r1 = com.luajava.LuaStateFactory.getExistingState(r1)
            java.lang.Object r2 = r1.getJavaObject(r3)
            monitor-enter(r1)
            r3 = 1
            if (r5 == 0) goto Le
            if (r5 != r3) goto L16
        Le:
            int r0 = setFieldValue(r1, r2, r4)     // Catch: java.lang.Throwable -> L39
            if (r0 == 0) goto L16
        L14:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L39
            goto L38
        L16:
            r3 = 2
            if (r5 == 0) goto L1b
            if (r5 != r3) goto L22
        L1b:
            int r0 = javaSetter(r1, r2, r4)     // Catch: java.lang.Throwable -> L39
            if (r0 == 0) goto L22
            goto L14
        L22:
            r3 = 3
            if (r5 == 0) goto L27
            if (r5 != r3) goto L36
        L27:
            boolean r5 = r2 instanceof com.luajava.LuaMetaTable     // Catch: java.lang.Throwable -> L39
            if (r5 == 0) goto L36
            com.luajava.LuaMetaTable r2 = (com.luajava.LuaMetaTable) r2     // Catch: java.lang.Throwable -> L39
            r5 = -1
            java.lang.Object r5 = r1.toJavaObject(r5)     // Catch: java.lang.Throwable -> L39
            r2.__newIndex(r4, r5)     // Catch: java.lang.Throwable -> L39
            goto L14
        L36:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L39
            r3 = 0
        L38:
            return r3
        L39:
            r2 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L39
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luajava.LuaJavaAPI.objectNewIndex(long, int, java.lang.String, int):int");
    }

    private static Object p(LuaState luaState, Class cls, int i2) {
        Object objCreateProxy;
        synchronized (luaState) {
            try {
                try {
                    objCreateProxy = luaState.getLuaObject(i2).createProxy(cls);
                } catch (Exception e2) {
                    throw new LuaException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return objCreateProxy;
    }

    public static void pushJavaObject(int i2, Object obj) {
        i.put(Integer.valueOf(i2), obj);
    }

    private static Method[] q(Object obj, String str, String str2) {
        Class<?> cls;
        boolean z;
        if (obj instanceof Class) {
            cls = (Class) obj;
            z = true;
        } else {
            cls = obj.getClass();
            z = false;
        }
        Map<String, Method[]> map = b;
        Method[] methodArr = map.get(str2);
        if (methodArr != null) {
            return methodArr;
        }
        ArrayList<Method> method = getMethod(cls, str, z);
        Method[] methodArr2 = new Method[method.size()];
        method.toArray(methodArr2);
        map.put(str2, methodArr2);
        return methodArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0091 A[Catch: all -> 0x00db, TryCatch #2 {, blocks: (B:3:0x0001, B:6:0x0009, B:7:0x0010, B:14:0x0033, B:18:0x0044, B:36:0x0088, B:22:0x004d, B:24:0x0050, B:28:0x005f, B:29:0x0063, B:32:0x0069, B:34:0x007c, B:35:0x0080, B:37:0x008b, B:39:0x0091, B:41:0x0094, B:42:0x00a5, B:43:0x00bf, B:44:0x00c0, B:45:0x00da, B:10:0x0013), top: B:53:0x0001, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c0 A[Catch: all -> 0x00db, TryCatch #2 {, blocks: (B:3:0x0001, B:6:0x0009, B:7:0x0010, B:14:0x0033, B:18:0x0044, B:36:0x0088, B:22:0x004d, B:24:0x0050, B:28:0x005f, B:29:0x0063, B:32:0x0069, B:34:0x007c, B:35:0x0080, B:37:0x008b, B:39:0x0091, B:41:0x0094, B:42:0x00a5, B:43:0x00bf, B:44:0x00c0, B:45:0x00da, B:10:0x0013), top: B:53:0x0001, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int r(com.luajava.LuaState r12, java.lang.Class<?> r13) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luajava.LuaJavaAPI.r(com.luajava.LuaState, java.lang.Class):int");
    }

    private static int s(LuaState luaState, Object obj, String str, boolean z) {
        synchronized (luaState) {
            for (Method method : getMethod(obj.getClass(), "setOn" + str.substring(2) + "Listener", z)) {
                if (!z || Modifier.isStatic(method.getModifiers())) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0].isInterface()) {
                        luaState.newTable();
                        luaState.pushValue(-2);
                        luaState.setField(-2, str);
                        try {
                            Object objCreateProxy = luaState.getLuaObject(-1).createProxy(parameterTypes[0]);
                            luaState.pop(1);
                            method.invoke(obj, objCreateProxy);
                            return 1;
                        } catch (Exception e2) {
                            throw new LuaException(e2);
                        }
                    }
                }
            }
            return 0;
        }
    }

    public static int setArrayValue(long j, int i2, int i3) {
        LuaState existingState = LuaStateFactory.getExistingState(j);
        Object javaObject = existingState.getJavaObject(i2);
        synchronized (existingState) {
            if (javaObject.getClass().isArray()) {
                Class<?> componentType = javaObject.getClass().getComponentType();
                try {
                    Array.set(javaObject, i3, e(existingState, componentType, 3));
                } catch (LuaException e2) {
                    a(existingState, javaObject.getClass().getName() + " [" + i3 + "]", 3, componentType);
                    throw null;
                }
            } else if (javaObject instanceof List) {
                ((List) javaObject).set(i3, existingState.toJavaObject(3));
            } else {
                if (!(javaObject instanceof Map)) {
                    throw new LuaException("can not set " + javaObject.getClass().getName() + " value: " + existingState.toJavaObject(3) + " in " + i3);
                }
                ((Map) javaObject).put(Long.valueOf(i3), existingState.toJavaObject(3));
            }
        }
        return 0;
    }

    public static int setArrayValue(LuaState luaState, Object obj, int i2) {
        synchronized (luaState) {
            if (obj.getClass().isArray()) {
                Class<?> componentType = obj.getClass().getComponentType();
                try {
                    Array.set(obj, i2, e(luaState, componentType, -1));
                } catch (LuaException e2) {
                    a(luaState, obj.getClass().getName() + " [" + i2 + "]", 3, componentType);
                    throw null;
                }
            } else if (obj instanceof List) {
                ((List) obj).set(i2, luaState.toJavaObject(-1));
            } else {
                if (!(obj instanceof Map)) {
                    throw new LuaException("can not set " + obj.getClass().getName() + " value: " + luaState.toJavaObject(-1) + " in " + i2);
                }
                ((Map) obj).put(Long.valueOf(i2), luaState.toJavaObject(-1));
            }
        }
        return 0;
    }

    public static int setFieldValue(LuaState luaState, Object obj, String str) {
        Class<?> cls;
        boolean z;
        synchronized (luaState) {
            if (obj != null) {
                if (obj instanceof Class) {
                    cls = (Class) obj;
                    z = true;
                } else {
                    cls = obj.getClass();
                    z = false;
                }
                try {
                    Field field = cls.getField(str);
                    if (field != null && (!z || Modifier.isStatic(field.getModifiers()))) {
                        Class<?> type = field.getType();
                        try {
                            if (!Modifier.isPublic(field.getModifiers())) {
                                field.setAccessible(true);
                            }
                            field.set(obj, e(luaState, type, luaState.getTop()));
                            return 1;
                        } catch (LuaException e2) {
                            a(luaState, str, -1, type);
                            throw null;
                        } catch (Exception e3) {
                            throw new LuaException(e3);
                        }
                    }
                } catch (NoSuchFieldException e4) {
                }
            }
            return 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0129, code lost:
    
        if (r14 == 1) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x012b, code lost:
    
        if (r14 == 9) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x012d, code lost:
    
        if (r14 == 3) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0130, code lost:
    
        if (r14 == 4) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0133, code lost:
    
        r4 = com.luajava.LuaJavaAPI.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0136, code lost:
    
        r4 = com.luajava.LuaJavaAPI.f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0139, code lost:
    
        r4 = com.luajava.LuaJavaAPI.e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x013c, code lost:
    
        r4 = com.luajava.LuaJavaAPI.g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x013e, code lost:
    
        r4.put(r8, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0141, code lost:
    
        r0.invoke(r18, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0149, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x014f, code lost:
    
        throw new com.luajava.LuaException(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int t(com.luajava.LuaState r17, java.lang.Object r18, java.lang.String r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luajava.LuaJavaAPI.t(com.luajava.LuaState, java.lang.Object, java.lang.String, boolean):int");
    }

    private static int u(LuaState luaState, Object obj, String str) {
        Class<?> superclass;
        boolean z;
        String str2;
        Field declaredField;
        String str3;
        synchronized (luaState) {
            if (obj != null) {
                if (obj instanceof Class) {
                    superclass = (Class) obj;
                    z = true;
                } else {
                    superclass = obj.getClass();
                    z = false;
                }
                if (str.startsWith(Config.MODEL)) {
                    str2 = null;
                    declaredField = null;
                } else {
                    char cCharAt = str.charAt(0);
                    if (Character.isLowerCase(cCharAt)) {
                        str3 = Character.toUpperCase(cCharAt) + str.substring(1);
                    } else {
                        str3 = null;
                    }
                    str2 = Config.MODEL + str3;
                    declaredField = null;
                }
                while (superclass != null) {
                    try {
                        declaredField = superclass.getDeclaredField(str);
                    } catch (NoSuchFieldException e2) {
                        if (str2 != null) {
                            try {
                                declaredField = superclass.getDeclaredField(str2);
                            } catch (NoSuchFieldException e3) {
                            }
                        }
                    }
                    if (declaredField != null) {
                        break;
                    }
                    superclass = superclass.getSuperclass();
                }
                if (declaredField != null && (!z || Modifier.isStatic(declaredField.getModifiers()))) {
                    Class<?> type = declaredField.getType();
                    try {
                        if (!Modifier.isPublic(declaredField.getModifiers())) {
                            declaredField.setAccessible(true);
                        }
                        declaredField.set(obj, e(luaState, type, luaState.getTop()));
                        return 1;
                    } catch (LuaException e4) {
                        a(luaState, str, 3, type);
                        throw null;
                    } catch (Exception e5) {
                        throw new LuaException(e5);
                    }
                }
            }
            return 0;
        }
    }

    private static int v(LuaState luaState, Class cls, int i2) throws LuaException {
        Object objValueOf;
        char integer;
        if (cls == Character.TYPE && luaState.type(i2) == 4) {
            String string = luaState.toString(i2);
            if (string.length() == 1) {
                integer = string.charAt(0);
                objValueOf = Character.valueOf(integer);
            } else {
                objValueOf = string.toCharArray();
            }
        } else {
            if (!luaState.isNumber(i2)) {
                throw new LuaException(luaState.toString(i2) + " is not number");
            }
            if (cls == Double.TYPE) {
                objValueOf = Double.valueOf(luaState.toNumber(i2));
            } else if (cls == Float.TYPE) {
                objValueOf = Float.valueOf((float) luaState.toNumber(i2));
            } else if (cls == Long.TYPE) {
                objValueOf = Long.valueOf(luaState.toInteger(i2));
            } else if (cls == Integer.TYPE) {
                objValueOf = Integer.valueOf((int) luaState.toInteger(i2));
            } else if (cls == Short.TYPE) {
                objValueOf = Short.valueOf((short) luaState.toInteger(i2));
            } else if (cls == Character.TYPE) {
                integer = (char) luaState.toInteger(i2);
                objValueOf = Character.valueOf(integer);
            } else {
                objValueOf = cls == Byte.TYPE ? Byte.valueOf((byte) luaState.toInteger(i2)) : cls == Boolean.TYPE ? Boolean.valueOf(luaState.toBoolean(i2)) : null;
            }
        }
        luaState.pushJavaObject(objValueOf);
        return 1;
    }

    private static String w(LuaState luaState, int i2) {
        if (luaState.isObject(i2)) {
            return luaState.getObjectFromUserdata(i2).getClass().getName();
        }
        switch (luaState.type(i2)) {
            case 1:
                return "boolean";
            case 2:
            case 7:
                return "userdata";
            case 3:
                return "number";
            case 4:
                return "string";
            case 5:
                return "table";
            case 6:
                return "function";
            case 8:
                return "thread";
            default:
                return "unkown";
        }
    }
}

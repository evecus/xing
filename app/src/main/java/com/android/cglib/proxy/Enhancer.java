package com.android.cglib.proxy;

import android.content.Context;
import com.android.cglib.dx.Code;
import com.android.cglib.dx.Comparison;
import com.android.cglib.dx.DexMaker;
import com.android.cglib.dx.FieldId;
import com.android.cglib.dx.Label;
import com.android.cglib.dx.Local;
import com.android.cglib.dx.MethodId;
import com.android.cglib.dx.TypeId;
import com.androlua.LuaUtil;
import com.baidu.mobstat.Config;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class Enhancer {
    private Context context;
    private MethodInterceptor interceptor;
    private MethodFilter methodFilter;
    private Class<?> superclass;

    public Enhancer(Context context) {
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <S> void generateFieldsAndMethods(DexMaker dexMaker, TypeId<?> typeId, TypeId<S> typeId2) {
        MethodFilter methodFilter;
        int i;
        Constructor<?>[] constructorArr;
        FieldId<?, MethodInterceptor> fieldId;
        TypeId<?> typeId3;
        TypeId typeId4;
        int i2;
        TypeId typeId5 = TypeId.get(MethodInterceptor.class);
        TypeId typeId6 = TypeId.get(MethodProxyExecuter.class);
        TypeId<?> typeId7 = TypeId.get(Class.class);
        TypeId<?> typeId8 = TypeId.get(Class[].class);
        TypeId<?> typeId9 = TypeId.get(String.class);
        TypeId<?> typeId10 = TypeId.get(Object.class);
        TypeId<?> typeId11 = TypeId.get(Object[].class);
        FieldId<?, MethodInterceptor> field = typeId2.getField(typeId5, "methodInterceptor");
        dexMaker.declare(field, 2, null);
        Constructor<?>[] declaredConstructors = this.superclass.getDeclaredConstructors();
        int length = declaredConstructors.length;
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            Constructor<?> constructor = declaredConstructors[i4];
            if ((constructor.getModifiers() & 8) == 0 && (constructor.getModifiers() & 16) == 0) {
                typeId4 = typeId6;
                i2 = i3;
                i = length;
                constructorArr = declaredConstructors;
                fieldId = field;
                typeId3 = typeId11;
                try {
                    hookConstructor(dexMaker, typeId, typeId2, constructor, fieldId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                i = length;
                constructorArr = declaredConstructors;
                fieldId = field;
                typeId3 = typeId11;
                typeId4 = typeId6;
                i2 = i3;
            }
            i4++;
            i3 = i2;
            typeId6 = typeId4;
            length = i;
            declaredConstructors = constructorArr;
            field = fieldId;
            typeId11 = typeId3;
        }
        FieldId<?, MethodInterceptor> fieldId2 = field;
        TypeId<?> typeId12 = typeId11;
        TypeId typeId13 = typeId6;
        int i5 = i3;
        TypeId<Void> typeId14 = TypeId.VOID;
        TypeId[] typeIdArr = new TypeId[1];
        typeIdArr[i5] = typeId5;
        Code codeDeclare = dexMaker.declare(typeId2.getMethod(typeId14, EnhancerInterface.SET_METHOD_INTERCEPTOR_ENHANCER, typeIdArr), 1);
        codeDeclare.iput(fieldId2, codeDeclare.getThis(typeId2), codeDeclare.getParameter(i5, typeId5));
        codeDeclare.returnVoid();
        TypeId<Object> typeId15 = TypeId.OBJECT;
        TypeId[] typeIdArr2 = new TypeId[3];
        typeIdArr2[i5] = typeId9;
        typeIdArr2[1] = typeId8;
        typeIdArr2[2] = typeId12;
        Code codeDeclare2 = dexMaker.declare(typeId2.getMethod(typeId15, EnhancerInterface.EXECUTE_SUPER_METHOD_ENHANCER, typeIdArr2), 1);
        Local<?> localNewLocal = codeDeclare2.newLocal(typeId10);
        Local<?> localNewLocal2 = codeDeclare2.newLocal(typeId7);
        Local<?> local = codeDeclare2.getThis(typeId2);
        codeDeclare2.invokeVirtual(typeId2.getMethod(typeId7, "getClass", new TypeId[i5]), localNewLocal2, local, new Local[i5]);
        TypeId<?>[] typeIdArr3 = new TypeId[5];
        typeIdArr3[i5] = typeId7;
        typeIdArr3[1] = typeId9;
        typeIdArr3[2] = typeId8;
        typeIdArr3[3] = typeId12;
        typeIdArr3[4] = typeId10;
        MethodId method = typeId13.getMethod(typeId15, MethodProxyExecuter.EXECUTE_METHOD, typeIdArr3);
        Local<?>[] localArr = new Local[5];
        localArr[i5] = localNewLocal2;
        localArr[1] = codeDeclare2.getParameter(i5, typeId9);
        localArr[2] = codeDeclare2.getParameter(1, typeId8);
        localArr[3] = codeDeclare2.getParameter(2, typeId12);
        localArr[4] = local;
        codeDeclare2.invokeStatic(method, localNewLocal, localArr);
        codeDeclare2.returnValue(localNewLocal);
        Method[] methods = this.superclass.getMethods();
        int length2 = methods.length;
        while (i5 < length2) {
            Method method2 = methods[i5];
            String name = method2.getName();
            if (!name.contains(Const.SUBCLASS_SUFFIX) && !name.contains(Const.SUBCLASS_INVOKE_SUPER_SUFFIX) && (method2.getModifiers() & 8) == 0 && (method2.getModifiers() & 16) == 0 && (method2.getModifiers() & 256) == 0 && ((method2.getModifiers() & 1024) != 0 || (methodFilter = this.methodFilter) == null || methodFilter.filter(method2, name))) {
                try {
                    hookMethod(dexMaker, typeId, typeId2, method2, name, fieldId2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            i5++;
        }
    }

    private void hookConstructor(DexMaker dexMaker, TypeId<?> typeId, TypeId<?> typeId2, Constructor constructor, FieldId<?, MethodInterceptor> fieldId) {
        TypeId<?>[] typeIdArr;
        MethodId<?, Void> constructor2;
        MethodId<?, Void> constructor3;
        TypeId.get(MethodInterceptor.class);
        TypeId.get(Class.class);
        TypeId.get(Class[].class);
        TypeId.get(String.class);
        TypeId.get(Object.class);
        TypeId.get(Object[].class);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        boolean z = parameterTypes != null && parameterTypes.length > 0;
        if (z) {
            typeIdArr = new TypeId[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                typeIdArr[i] = TypeId.get(parameterTypes[i]);
            }
        } else {
            typeIdArr = null;
        }
        if (z) {
            constructor2 = typeId2.getConstructor(typeIdArr);
            constructor3 = typeId.getConstructor(typeIdArr);
        } else {
            constructor2 = typeId2.getConstructor(new TypeId[0]);
            constructor3 = typeId.getConstructor(new TypeId[0]);
        }
        Code codeDeclare = dexMaker.declare(constructor2, constructor.getModifiers());
        Local local = codeDeclare.getThis(typeId2);
        if (z) {
            Local<?>[] localArr = new Local[parameterTypes.length];
            for (int i2 = 0; i2 < parameterTypes.length; i2++) {
                localArr[i2] = codeDeclare.getParameter(i2, typeIdArr[i2]);
            }
            codeDeclare.invokeDirect(constructor3, null, local, localArr);
        } else {
            codeDeclare.invokeDirect(constructor3, null, local, new Local[0]);
        }
        codeDeclare.returnVoid();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v8, types: [com.android.cglib.dx.TypeId] */
    private void hookMethod(DexMaker dexMaker, TypeId<?> typeId, TypeId<?> typeId2, Method method, String str, FieldId<?, MethodInterceptor> fieldId) {
        TypeId typeId3;
        boolean z;
        MethodId method2;
        TypeId<?>[] typeIdArr;
        Local<?> local;
        Local<?> localNewLocal;
        Class<?>[] clsArr;
        Local<?> local2;
        TypeId typeId4;
        Local<?> local3;
        TypeId typeId5;
        Local<?> local4;
        MethodId method3;
        Object method4;
        Local<?> local5;
        Class<?>[] clsArr2;
        Local<?> local6;
        TypeId typeId6 = TypeId.get(MethodInterceptor.class);
        TypeId typeId7 = TypeId.get(MethodProxyExecuter.class);
        TypeId typeId8 = TypeId.get(Class.class);
        TypeId typeId9 = TypeId.get(Class[].class);
        TypeId typeId10 = TypeId.get(String.class);
        TypeId typeId11 = TypeId.get(Object.class);
        TypeId typeId12 = TypeId.get(Object[].class);
        Class<?> returnType = method.getReturnType();
        boolean zEquals = returnType.getSimpleName().equals("void");
        TypeId typeId13 = TypeId.get(returnType);
        Class<?>[] parameterTypes = method.getParameterTypes();
        boolean z2 = parameterTypes != null && parameterTypes.length > 0;
        if (z2) {
            typeIdArr = new TypeId[parameterTypes.length];
            typeId3 = typeId7;
            z = zEquals;
            for (int i = 0; i < parameterTypes.length; i++) {
                typeIdArr[i] = TypeId.get(parameterTypes[i]);
            }
            method2 = typeId2.getMethod(typeId13, str, typeIdArr);
        } else {
            typeId3 = typeId7;
            z = zEquals;
            method2 = typeId2.getMethod(typeId13, str, new TypeId[0]);
            typeIdArr = null;
        }
        Code codeDeclare = dexMaker.declare(method2, method.getModifiers() & (-1025));
        Local<?> localNewLocal2 = codeDeclare.newLocal(typeId13);
        if (returnType.isPrimitive()) {
            localNewLocal = codeDeclare.newLocal(TypeId.get(Const.getPackedType(returnType)));
            local = localNewLocal2;
        } else {
            local = localNewLocal2;
            localNewLocal = null;
        }
        Local<Integer> localNewLocal3 = codeDeclare.newLocal(TypeId.INT);
        Local<?> localNewLocal4 = codeDeclare.newLocal(typeId6);
        Local<?> local7 = localNewLocal;
        Local<?> localNewLocal5 = codeDeclare.newLocal(TypeId.get(String.class));
        Local<?> localNewLocal6 = codeDeclare.newLocal(typeId8);
        Local<?> localNewLocal7 = codeDeclare.newLocal(typeId8);
        Local<?> localNewLocal8 = codeDeclare.newLocal(typeId9);
        Local<?> localNewLocal9 = codeDeclare.newLocal(typeId12);
        Local<?> localNewLocal10 = codeDeclare.newLocal(typeId11);
        Local<?> localNewLocal11 = codeDeclare.newLocal(TypeId.OBJECT);
        Local<?> local8 = codeDeclare.getThis(typeId2);
        Local<?> local9 = localNewLocal10;
        codeDeclare.iget(fieldId, localNewLocal4, local8);
        codeDeclare.loadConstant(localNewLocal5, str);
        codeDeclare.invokeVirtual(typeId2.getMethod(typeId8, "getClass", new TypeId[0]), localNewLocal7, local8, new Local[0]);
        if (z2) {
            codeDeclare.loadConstant(localNewLocal3, Integer.valueOf(parameterTypes.length));
            codeDeclare.newArray(localNewLocal8, localNewLocal3);
            codeDeclare.newArray(localNewLocal9, localNewLocal3);
            int i2 = 0;
            while (i2 < parameterTypes.length) {
                codeDeclare.loadConstant(localNewLocal3, Integer.valueOf(i2));
                codeDeclare.loadConstant(localNewLocal6, parameterTypes[i2]);
                codeDeclare.aput(localNewLocal8, localNewLocal3, localNewLocal6);
                if (parameterTypes[i2].isPrimitive()) {
                    TypeId typeId14 = TypeId.get(Const.getPackedType(parameterTypes[i2]));
                    local5 = localNewLocal6;
                    clsArr2 = parameterTypes;
                    local6 = local9;
                    codeDeclare.invokeStatic(typeId14.getMethod(typeId14, "valueOf", typeIdArr[i2]), local6, codeDeclare.getParameter(i2, typeIdArr[i2]));
                    codeDeclare.aput(localNewLocal9, localNewLocal3, local6);
                } else {
                    local5 = localNewLocal6;
                    clsArr2 = parameterTypes;
                    local6 = local9;
                    codeDeclare.aput(localNewLocal9, localNewLocal3, codeDeclare.getParameter(i2, typeIdArr[i2]));
                }
                i2++;
                local9 = local6;
                localNewLocal6 = local5;
                parameterTypes = clsArr2;
            }
            clsArr = parameterTypes;
        } else {
            clsArr = parameterTypes;
            codeDeclare.loadConstant(localNewLocal8, null);
            codeDeclare.loadConstant(localNewLocal9, null);
        }
        codeDeclare.invokeStatic(typeId3.getMethod(TypeId.OBJECT, MethodProxyExecuter.EXECUTE_INTERCEPTOR, typeId6, typeId8, typeId10, typeId9, typeId12, typeId11), z ? null : localNewLocal11, localNewLocal4, localNewLocal7, localNewLocal5, localNewLocal8, localNewLocal9, local8);
        if (z) {
            codeDeclare.returnVoid();
            typeId5 = typeId13;
            local3 = null;
        } else {
            if (returnType.isPrimitive()) {
                Label label = new Label();
                local3 = null;
                codeDeclare.loadConstant(local7, null);
                codeDeclare.compare(Comparison.EQ, label, localNewLocal11, local7);
                codeDeclare.cast(local7, localNewLocal11);
                TypeId typeId15 = typeId13;
                local2 = local;
                codeDeclare.invokeVirtual(TypeId.get(Const.getPackedType(returnType)).getMethod(typeId15, Const.getPrimitiveValueMethodName(returnType), new TypeId[0]), local2, local7, new Local[0]);
                codeDeclare.returnValue(local2);
                codeDeclare.mark(label);
                codeDeclare.loadConstant(local2, 0);
                typeId4 = typeId15;
            } else {
                local2 = local;
                typeId4 = typeId13;
                local3 = null;
                codeDeclare.cast(local2, localNewLocal11);
            }
            codeDeclare.returnValue(local2);
            typeId5 = typeId4;
        }
        if (z2) {
            method3 = typeId2.getMethod(typeId5, str + Const.SUBCLASS_INVOKE_SUPER_SUFFIX, typeIdArr);
            local4 = local3;
            method4 = typeId.getMethod(typeId5, str, typeIdArr);
        } else {
            local4 = local3;
            method3 = typeId2.getMethod(typeId5, a.j(str, Const.SUBCLASS_INVOKE_SUPER_SUFFIX), new TypeId[0]);
            method4 = typeId.getMethod(typeId5, str, new TypeId[0]);
        }
        Code codeDeclare2 = dexMaker.declare(method3, method.getModifiers());
        Local<?> localNewLocal12 = codeDeclare2.newLocal(typeId5);
        Local local10 = codeDeclare2.getThis(typeId2);
        if (z2) {
            Class<?>[] clsArr3 = clsArr;
            Local<?>[] localArr = new Local[clsArr3.length];
            for (int i3 = 0; i3 < clsArr3.length; i3++) {
                localArr[i3] = codeDeclare2.getParameter(i3, typeIdArr[i3]);
            }
            codeDeclare2.invokeSuper(method4, z ? local4 : localNewLocal12, local10, localArr);
        } else {
            codeDeclare2.invokeSuper(method4, z ? local4 : localNewLocal12, local10, new Local[0]);
        }
        if (z) {
            codeDeclare2.returnVoid();
        } else {
            codeDeclare2.returnValue(localNewLocal12);
        }
    }

    public Class<?> create() {
        File file;
        String strReplace = this.superclass.getName().replace(".", "/");
        StringBuilder sbE = a.e(strReplace, Const.SUBCLASS_SUFFIX, Config.replace);
        sbE.append(hashCode());
        String string = sbE.toString();
        TypeId<?> typeId = TypeId.get("L" + strReplace + ";");
        TypeId<?> typeId2 = TypeId.get("L" + string + ";");
        TypeId<?> typeId3 = TypeId.get(EnhancerInterface.class);
        String absolutePath = this.context.getExternalFilesDir("dexfiles").getAbsolutePath();
        DexMaker dexMaker = new DexMaker();
        dexMaker.declare(typeId2, a.j(strReplace, ".proxy"), 1, typeId, typeId3);
        generateFieldsAndMethods(dexMaker, typeId, typeId2);
        try {
            try {
                return dexMaker.generateAndLoad(Enhancer.class.getClassLoader(), new File(absolutePath)).loadClass(string);
            } catch (IOException e) {
                e.printStackTrace();
                file = new File(absolutePath);
                LuaUtil.rmDir(file);
                return null;
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
                file = new File(absolutePath);
                LuaUtil.rmDir(file);
                return null;
            }
        } finally {
            LuaUtil.rmDir(new File(absolutePath));
        }
    }

    public void setInterceptor(MethodInterceptor methodInterceptor) {
        this.interceptor = methodInterceptor;
    }

    public void setMethodFilter(MethodFilter methodFilter) {
        this.methodFilter = methodFilter;
    }

    public void setSuperclass(Class<?> cls) {
        this.superclass = cls;
    }
}

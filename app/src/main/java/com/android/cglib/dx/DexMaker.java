package com.android.cglib.dx;

import android.app.Application;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import com.android.cglib.dx.dex.DexFormat;
import com.android.cglib.dx.dex.DexOptions;
import com.android.cglib.dx.dex.code.RopTranslator;
import com.android.cglib.dx.dex.file.ClassDefItem;
import com.android.cglib.dx.dex.file.DexFile;
import com.android.cglib.dx.dex.file.EncodedField;
import com.android.cglib.dx.dex.file.EncodedMethod;
import com.android.cglib.dx.rop.code.RopMethod;
import com.android.cglib.dx.rop.cst.CstString;
import com.android.cglib.dx.rop.type.StdTypeList;
import com.baidu.android.common.util.HanziToPinyin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class DexMaker {
    private final Map<TypeId<?>, TypeDeclaration> types = new LinkedHashMap();

    public static class FieldDeclaration {
        private final int accessFlags;
        public final FieldId<?, ?> fieldId;
        private final Object staticValue;

        public FieldDeclaration(FieldId<?, ?> fieldId, int i, Object obj) {
            if ((i & 8) == 0 && obj != null) {
                throw new IllegalArgumentException("instance fields may not have a value");
            }
            this.fieldId = fieldId;
            this.accessFlags = i;
            this.staticValue = obj;
        }

        public boolean isStatic() {
            return (this.accessFlags & 8) != 0;
        }

        public EncodedField toEncodedField() {
            return new EncodedField(this.fieldId.constant, this.accessFlags);
        }
    }

    public static class MethodDeclaration {
        private final Code code = new Code(this);
        private final int flags;
        public final MethodId<?, ?> method;

        public MethodDeclaration(MethodId<?, ?> methodId, int i) {
            this.method = methodId;
            this.flags = i;
        }

        public boolean isDirect() {
            return (this.flags & 65546) != 0;
        }

        public boolean isStatic() {
            return (this.flags & 8) != 0;
        }

        public EncodedMethod toEncodedMethod(DexOptions dexOptions) {
            return new EncodedMethod(this.method.constant, this.flags, RopTranslator.translate(new RopMethod(this.code.toBasicBlocks(), 0), 1, null, this.code.paramSize(), dexOptions), StdTypeList.EMPTY);
        }
    }

    public static class TypeDeclaration {
        private boolean declared;
        private int flags;
        private TypeList interfaces;
        private String sourceFile;
        private TypeId<?> supertype;
        private final TypeId<?> type;
        private final Map<FieldId, FieldDeclaration> fields = new LinkedHashMap();
        private final Map<MethodId, MethodDeclaration> methods = new LinkedHashMap();

        public TypeDeclaration(TypeId<?> typeId) {
            this.type = typeId;
        }

        public ClassDefItem toClassDefItem() {
            if (!this.declared) {
                StringBuilder sbO = a.o("Undeclared type ");
                sbO.append(this.type);
                sbO.append(" declares members: ");
                sbO.append(this.fields.keySet());
                sbO.append(HanziToPinyin.Token.SEPARATOR);
                sbO.append(this.methods.keySet());
                throw new IllegalStateException(sbO.toString());
            }
            DexOptions dexOptions = new DexOptions();
            dexOptions.targetApiLevel = 13;
            ClassDefItem classDefItem = new ClassDefItem(this.type.constant, this.flags, this.supertype.constant, this.interfaces.ropTypes, new CstString(this.sourceFile));
            for (MethodDeclaration methodDeclaration : this.methods.values()) {
                EncodedMethod encodedMethod = methodDeclaration.toEncodedMethod(dexOptions);
                if (methodDeclaration.isDirect()) {
                    classDefItem.addDirectMethod(encodedMethod);
                } else {
                    classDefItem.addVirtualMethod(encodedMethod);
                }
            }
            for (FieldDeclaration fieldDeclaration : this.fields.values()) {
                EncodedField encodedField = fieldDeclaration.toEncodedField();
                if (fieldDeclaration.isStatic()) {
                    classDefItem.addStaticField(encodedField, Constants.getConstant(fieldDeclaration.staticValue));
                } else {
                    classDefItem.addInstanceField(encodedField);
                }
            }
            return classDefItem;
        }
    }

    private ClassLoader generateClassLoader(File file, File file2, ClassLoader classLoader) {
        try {
            return (ClassLoader) Class.forName("dalvik.system.DexClassLoader").getConstructor(String.class, String.class, String.class, ClassLoader.class).newInstance(file.getPath(), file2.getAbsolutePath(), null, classLoader);
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("load() requires a Dalvik VM", e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError();
        } catch (InstantiationException e3) {
            throw new AssertionError();
        } catch (NoSuchMethodException e4) {
            throw new AssertionError();
        } catch (InvocationTargetException e5) {
            throw new RuntimeException(e5.getCause());
        }
    }

    private String generateFileName() {
        Set<TypeId<?>> setKeySet = this.types.keySet();
        Iterator<TypeId<?>> it = setKeySet.iterator();
        int size = setKeySet.size();
        int[] iArr = new int[size];
        int i = 0;
        while (it.hasNext()) {
            TypeDeclaration typeDeclaration = getTypeDeclaration(it.next());
            Set setKeySet2 = typeDeclaration.methods.keySet();
            if (typeDeclaration.supertype != null) {
                iArr[i] = (typeDeclaration.supertype.hashCode() * 31) + setKeySet2.hashCode();
                i++;
            }
        }
        Arrays.sort(iArr);
        int i2 = 1;
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 * 31) + iArr[i3];
        }
        return "Generated_" + i2 + ".jar";
    }

    private static String getCurrentPackageName() {
        try {
            return ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0])).getPackageName();
        } catch (Throwable th) {
            th.printStackTrace();
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    private TypeDeclaration getTypeDeclaration(TypeId<?> typeId) {
        TypeDeclaration typeDeclaration = this.types.get(typeId);
        if (typeDeclaration != null) {
            return typeDeclaration;
        }
        TypeDeclaration typeDeclaration2 = new TypeDeclaration(typeId);
        this.types.put(typeId, typeDeclaration2);
        return typeDeclaration2;
    }

    public Code declare(MethodId<?, ?> methodId, int i) {
        TypeDeclaration typeDeclaration = getTypeDeclaration(methodId.declaringType);
        if (typeDeclaration.methods.containsKey(methodId)) {
            throw new IllegalStateException("already declared: " + methodId);
        }
        if ((i & (-64)) != 0) {
            StringBuilder sbO = a.o("Unexpected flag: ");
            sbO.append(Integer.toHexString(i));
            throw new IllegalArgumentException(sbO.toString());
        }
        if ((i & 32) != 0) {
            i = (i & (-33)) | 131072;
        }
        if (methodId.isConstructor()) {
            i |= 65536;
        }
        MethodDeclaration methodDeclaration = new MethodDeclaration(methodId, i);
        typeDeclaration.methods.put(methodId, methodDeclaration);
        return methodDeclaration.code;
    }

    public void declare(FieldId<?, ?> fieldId, int i, Object obj) {
        TypeDeclaration typeDeclaration = getTypeDeclaration(fieldId.declaringType);
        if (typeDeclaration.fields.containsKey(fieldId)) {
            throw new IllegalStateException("already declared: " + fieldId);
        }
        if ((i & (-224)) != 0) {
            StringBuilder sbO = a.o("Unexpected flag: ");
            sbO.append(Integer.toHexString(i));
            throw new IllegalArgumentException(sbO.toString());
        }
        if ((i & 8) == 0 && obj != null) {
            throw new IllegalArgumentException("staticValue is non-null, but field is not static");
        }
        typeDeclaration.fields.put(fieldId, new FieldDeclaration(fieldId, i, obj));
    }

    public void declare(TypeId<?> typeId, String str, int i, TypeId<?> typeId2, TypeId<?>... typeIdArr) {
        TypeDeclaration typeDeclaration = getTypeDeclaration(typeId);
        if ((i & (-1042)) != 0) {
            StringBuilder sbO = a.o("Unexpected flag: ");
            sbO.append(Integer.toHexString(i));
            throw new IllegalArgumentException(sbO.toString());
        }
        if (typeDeclaration.declared) {
            throw new IllegalStateException("already declared: " + typeId);
        }
        typeDeclaration.declared = true;
        typeDeclaration.flags = i;
        typeDeclaration.supertype = typeId2;
        typeDeclaration.sourceFile = str;
        typeDeclaration.interfaces = new TypeList(typeIdArr);
    }

    public byte[] generate() {
        DexOptions dexOptions = new DexOptions();
        dexOptions.targetApiLevel = 13;
        DexFile dexFile = new DexFile(dexOptions);
        Iterator<TypeDeclaration> it = this.types.values().iterator();
        while (it.hasNext()) {
            dexFile.add(it.next().toClassDefItem());
        }
        try {
            return dexFile.toDex(null, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ClassLoader generateAndLoad(ClassLoader classLoader, File file) {
        File cacheDir = null;
        try {
            try {
                cacheDir = ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0])).getCacheDir();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (cacheDir != null) {
                file = cacheDir;
            }
            File file2 = new File(file, generateFileName());
            if (file2.exists()) {
                if (Build.VERSION.SDK_INT >= 34) {
                    file2.setReadable(true, false);
                    file2.setWritable(false, false);
                }
                file2.setWritable(false);
                file2.setReadable(true);
                file2.setExecutable(false);
                return generateClassLoader(file2, file, classLoader);
            }
            byte[] bArrGenerate = generate();
            file2.createNewFile();
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(file2));
            JarEntry jarEntry = new JarEntry(DexFormat.DEX_IN_JAR_NAME);
            jarEntry.setSize(bArrGenerate.length);
            jarOutputStream.putNextEntry(jarEntry);
            jarOutputStream.write(bArrGenerate);
            jarOutputStream.closeEntry();
            jarOutputStream.close();
            file2.setReadable(true, false);
            file2.setWritable(false, false);
            file2.setWritable(false);
            file2.setReadable(true);
            file2.setExecutable(false);
            return generateClassLoader(file2, file, classLoader);
        } catch (Throwable th2) {
            th2.printStackTrace();
            throw new RuntimeException("Failed to create dex cache file");
        }
    }
}

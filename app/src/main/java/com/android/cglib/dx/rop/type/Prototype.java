package com.android.cglib.dx.rop.type;

import androidx.appcompat.widget.ActivityChooserView;
import java.util.HashMap;
import java.util.Objects;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Prototype implements Comparable<Prototype> {
    private static final HashMap<String, Prototype> internTable = new HashMap<>(500);
    private final String descriptor;
    private StdTypeList parameterFrameTypes;
    private final StdTypeList parameterTypes;
    private final Type returnType;

    private Prototype(String str, Type type, StdTypeList stdTypeList) {
        Objects.requireNonNull(str, "descriptor == null");
        Objects.requireNonNull(type, "returnType == null");
        Objects.requireNonNull(stdTypeList, "parameterTypes == null");
        this.descriptor = str;
        this.returnType = type;
        this.parameterTypes = stdTypeList;
        this.parameterFrameTypes = null;
    }

    public static Prototype intern(String str) {
        Prototype prototype;
        int i;
        Objects.requireNonNull(str, "descriptor == null");
        HashMap<String, Prototype> map = internTable;
        synchronized (map) {
            prototype = map.get(str);
        }
        if (prototype != null) {
            return prototype;
        }
        Type[] typeArrMakeParameterArray = makeParameterArray(str);
        int i2 = 1;
        int i3 = 0;
        while (true) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == ')') {
                Type typeInternReturnType = Type.internReturnType(str.substring(i2 + 1));
                StdTypeList stdTypeList = new StdTypeList(i3);
                for (int i4 = 0; i4 < i3; i4++) {
                    stdTypeList.set(i4, typeArrMakeParameterArray[i4]);
                }
                return putIntern(new Prototype(str, typeInternReturnType, stdTypeList));
            }
            int i5 = i2;
            while (cCharAt == '[') {
                i5++;
                cCharAt = str.charAt(i5);
            }
            if (cCharAt == 'L') {
                int iIndexOf = str.indexOf(59, i5);
                if (iIndexOf == -1) {
                    throw new IllegalArgumentException("bad descriptor");
                }
                i = iIndexOf + 1;
            } else {
                i = i5 + 1;
            }
            typeArrMakeParameterArray[i3] = Type.intern(str.substring(i2, i));
            i3++;
            i2 = i;
        }
    }

    public static Prototype intern(String str, Type type, boolean z, boolean z2) {
        Prototype prototypeIntern = intern(str);
        if (z) {
            return prototypeIntern;
        }
        if (z2) {
            type = type.asUninitialized(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        }
        return prototypeIntern.withFirstParameter(type);
    }

    public static Prototype internInts(Type type, int i) {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append('(');
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append('I');
        }
        stringBuffer.append(')');
        stringBuffer.append(type.getDescriptor());
        return intern(stringBuffer.toString());
    }

    private static Type[] makeParameterArray(String str) {
        int length = str.length();
        int i = 0;
        if (str.charAt(0) != '(') {
            throw new IllegalArgumentException("bad descriptor");
        }
        int i2 = 0;
        int i3 = 1;
        while (true) {
            if (i3 >= length) {
                break;
            }
            char cCharAt = str.charAt(i3);
            if (cCharAt == ')') {
                i = i3;
                break;
            }
            if (cCharAt >= 'A' && cCharAt <= 'Z') {
                i2++;
            }
            i3++;
        }
        if (i == 0 || i == length - 1) {
            throw new IllegalArgumentException("bad descriptor");
        }
        if (str.indexOf(41, i + 1) == -1) {
            return new Type[i2];
        }
        throw new IllegalArgumentException("bad descriptor");
    }

    private static Prototype putIntern(Prototype prototype) {
        HashMap<String, Prototype> map = internTable;
        synchronized (map) {
            String descriptor = prototype.getDescriptor();
            Prototype prototype2 = map.get(descriptor);
            if (prototype2 != null) {
                return prototype2;
            }
            map.put(descriptor, prototype);
            return prototype;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Prototype prototype) {
        if (this == prototype) {
            return 0;
        }
        int iCompareTo = this.returnType.compareTo(prototype.returnType);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int size = this.parameterTypes.size();
        int size2 = prototype.parameterTypes.size();
        int iMin = Math.min(size, size2);
        for (int i = 0; i < iMin; i++) {
            int iCompareTo2 = this.parameterTypes.get(i).compareTo(prototype.parameterTypes.get(i));
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (size < size2) {
            return -1;
        }
        return size > size2 ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Prototype) {
            return this.descriptor.equals(((Prototype) obj).descriptor);
        }
        return false;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public StdTypeList getParameterFrameTypes() {
        if (this.parameterFrameTypes == null) {
            int size = this.parameterTypes.size();
            StdTypeList stdTypeList = new StdTypeList(size);
            boolean z = false;
            for (int i = 0; i < size; i++) {
                Type type = this.parameterTypes.get(i);
                if (type.isIntlike()) {
                    type = Type.INT;
                    z = true;
                }
                stdTypeList.set(i, type);
            }
            if (!z) {
                stdTypeList = this.parameterTypes;
            }
            this.parameterFrameTypes = stdTypeList;
        }
        return this.parameterFrameTypes;
    }

    public StdTypeList getParameterTypes() {
        return this.parameterTypes;
    }

    public Type getReturnType() {
        return this.returnType;
    }

    public int hashCode() {
        return this.descriptor.hashCode();
    }

    public String toString() {
        return this.descriptor;
    }

    public Prototype withFirstParameter(Type type) {
        StringBuilder sbO = a.o("(");
        sbO.append(type.getDescriptor());
        sbO.append(this.descriptor.substring(1));
        String string = sbO.toString();
        StdTypeList stdTypeListWithFirst = this.parameterTypes.withFirst(type);
        stdTypeListWithFirst.setImmutable();
        return putIntern(new Prototype(string, this.returnType, stdTypeListWithFirst));
    }
}

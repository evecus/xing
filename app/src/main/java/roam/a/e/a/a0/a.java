package roam.a.e.a.a0;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final Type[] a = new Type[0];

    /* JADX INFO: renamed from: roam.a.e.a.a0.a$a, reason: collision with other inner class name */
    public static final class C0016a implements GenericArrayType, Serializable {
        public final Type a;

        public C0016a(Type type) {
            this.a = a.a(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && a.c(this, (GenericArrayType) obj);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.a;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return a.i(this.a) + "[]";
        }
    }

    public static final class b implements ParameterizedType, Serializable {
        public final Type a;
        public final Type b;
        public final Type[] c;

        public b(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z = true;
                boolean z2 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
                if (type == null && !z2) {
                    z = false;
                }
                roam.a.a.a.b.a.A(z);
            }
            this.a = type == null ? null : a.a(type);
            this.b = a.a(type2);
            Type[] typeArr2 = (Type[]) typeArr.clone();
            this.c = typeArr2;
            int length = typeArr2.length;
            for (int i = 0; i < length; i++) {
                Objects.requireNonNull(this.c[i]);
                a.b(this.c[i]);
                Type[] typeArr3 = this.c;
                typeArr3[i] = a.a(typeArr3[i]);
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && a.c(this, (ParameterizedType) obj);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.c.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.a;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.b;
        }

        public int hashCode() {
            int iHashCode = Arrays.hashCode(this.c);
            int iHashCode2 = this.b.hashCode();
            Type type = this.a;
            return (iHashCode ^ iHashCode2) ^ (type != null ? type.hashCode() : 0);
        }

        public String toString() {
            int length = this.c.length;
            if (length == 0) {
                return a.i(this.b);
            }
            StringBuilder sb = new StringBuilder((length + 1) * 30);
            sb.append(a.i(this.b));
            sb.append("<");
            sb.append(a.i(this.c[0]));
            for (int i = 1; i < length; i++) {
                sb.append(", ");
                sb.append(a.i(this.c[i]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    public static final class c implements WildcardType, Serializable {
        public final Type a;
        public final Type b;

        public c(Type[] typeArr, Type[] typeArr2) {
            Type typeA;
            roam.a.a.a.b.a.A(typeArr2.length <= 1);
            roam.a.a.a.b.a.A(typeArr.length == 1);
            if (typeArr2.length == 1) {
                Objects.requireNonNull(typeArr2[0]);
                a.b(typeArr2[0]);
                roam.a.a.a.b.a.A(typeArr[0] == Object.class);
                this.b = a.a(typeArr2[0]);
                typeA = Object.class;
            } else {
                Objects.requireNonNull(typeArr[0]);
                a.b(typeArr[0]);
                this.b = null;
                typeA = a.a(typeArr[0]);
            }
            this.a = typeA;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && a.c(this, (WildcardType) obj);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.b;
            return type != null ? new Type[]{type} : a.a;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.a};
        }

        public int hashCode() {
            Type type = this.b;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.a.hashCode() + 31);
        }

        public String toString() {
            StringBuilder sbO;
            Type type;
            if (this.b != null) {
                sbO = roam.a.b.a.a.a.o("? super ");
                type = this.b;
            } else {
                if (this.a == Object.class) {
                    return "?";
                }
                sbO = roam.a.b.a.a.a.o("? extends ");
                type = this.a;
            }
            sbO.append(a.i(type));
            return sbO.toString();
        }
    }

    public static Type a(Type type) {
        Type cVar;
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (!cls.isArray()) {
                return cls;
            }
            cVar = new C0016a(a(cls.getComponentType()));
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            cVar = new b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            cVar = new C0016a(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            cVar = new c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
        return cVar;
    }

    public static void b(Type type) {
        roam.a.a.a.b.a.A(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
    }

    public static boolean c(Type type, Type type2) {
        ParameterizedType parameterizedType;
        ParameterizedType parameterizedType2;
        Type ownerType;
        Type ownerType2;
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if ((type2 instanceof ParameterizedType) && (((ownerType = (parameterizedType = (ParameterizedType) type).getOwnerType()) == (ownerType2 = (parameterizedType2 = (ParameterizedType) type2).getOwnerType()) || (ownerType != null && ownerType.equals(ownerType2))) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments()))) {
                return true;
            }
        } else if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return c(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
        } else if (type instanceof WildcardType) {
            if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                WildcardType wildcardType2 = (WildcardType) type2;
                if (Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds())) {
                    return true;
                }
            }
        } else if ((type instanceof TypeVariable) && (type2 instanceof TypeVariable)) {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0034 A[LOOP:1: B:17:0x0034->B:26:0x0052, LOOP_START, PHI: r4
  0x0034: PHI (r4v1 java.lang.Class<?>) = (r4v0 java.lang.Class<?>), (r4v3 java.lang.Class<?>) binds: [B:16:0x0032, B:26:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.reflect.Type d(java.lang.reflect.Type r3, java.lang.Class<?> r4, java.lang.Class<?> r5) {
        /*
            if (r5 != r4) goto L3
            goto L55
        L3:
            boolean r3 = r5.isInterface()
            if (r3 == 0) goto L2e
            java.lang.Class[] r3 = r4.getInterfaces()
            int r0 = r3.length
            r1 = 0
        Lf:
            if (r1 >= r0) goto L2e
            r2 = r3[r1]
            if (r2 != r5) goto L1c
            java.lang.reflect.Type[] r3 = r4.getGenericInterfaces()
            r3 = r3[r1]
            goto L55
        L1c:
            boolean r2 = r5.isAssignableFrom(r2)
            if (r2 == 0) goto L2b
            java.lang.reflect.Type[] r4 = r4.getGenericInterfaces()
            r4 = r4[r1]
            r3 = r3[r1]
            goto L4d
        L2b:
            int r1 = r1 + 1
            goto Lf
        L2e:
            boolean r3 = r4.isInterface()
            if (r3 != 0) goto L54
        L34:
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            if (r4 == r3) goto L54
            java.lang.Class r3 = r4.getSuperclass()
            if (r3 != r5) goto L43
            java.lang.reflect.Type r3 = r4.getGenericSuperclass()
            goto L55
        L43:
            boolean r0 = r5.isAssignableFrom(r3)
            if (r0 == 0) goto L52
            java.lang.reflect.Type r4 = r4.getGenericSuperclass()
        L4d:
            java.lang.reflect.Type r3 = d(r4, r3, r5)
            goto L55
        L52:
            r4 = r3
            goto L34
        L54:
            r3 = r5
        L55:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.a0.a.d(java.lang.reflect.Type, java.lang.Class, java.lang.Class):java.lang.reflect.Type");
    }

    public static Class<?> e(Type type) {
        if (!(type instanceof Class)) {
            if (!(type instanceof ParameterizedType)) {
                if (type instanceof GenericArrayType) {
                    return Array.newInstance(e(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
                }
                if (type instanceof TypeVariable) {
                    return Object.class;
                }
                if (type instanceof WildcardType) {
                    return e(((WildcardType) type).getUpperBounds()[0]);
                }
                throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
            }
            type = ((ParameterizedType) type).getRawType();
            roam.a.a.a.b.a.A(type instanceof Class);
        }
        return (Class) type;
    }

    public static Type f(Type type, Class<?> cls, Class<?> cls2) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        roam.a.a.a.b.a.A(cls2.isAssignableFrom(cls));
        return g(type, cls, d(type, cls, cls2));
    }

    public static Type g(Type type, Class<?> cls, Type type2) {
        return h(type, cls, type2, new HashSet());
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.reflect.Type h(java.lang.reflect.Type r8, java.lang.Class<?> r9, java.lang.reflect.Type r10, java.util.Collection<java.lang.reflect.TypeVariable> r11) {
        /*
            Method dump skipped, instruction units count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.e.a.a0.a.h(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type, java.util.Collection):java.lang.reflect.Type");
    }

    public static String i(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }
}

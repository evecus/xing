package roam.a.e.a;

import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class s extends n {
    public final Object a;

    public s(Boolean bool) {
        Objects.requireNonNull(bool);
        this.a = bool;
    }

    public s(Number number) {
        Objects.requireNonNull(number);
        this.a = number;
    }

    public s(String str) {
        Objects.requireNonNull(str);
        this.a = str;
    }

    public static boolean e(s sVar) {
        Object obj = sVar.a;
        if (obj instanceof Number) {
            Number number = (Number) obj;
            if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
                return true;
            }
        }
        return false;
    }

    public boolean b() {
        Object obj = this.a;
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : Boolean.parseBoolean(d());
    }

    public Number c() {
        Object obj = this.a;
        return obj instanceof String ? new roam.a.e.a.a0.r((String) obj) : (Number) obj;
    }

    public String d() {
        Object obj = this.a;
        return obj instanceof Number ? c().toString() : obj instanceof Boolean ? ((Boolean) obj).toString() : (String) obj;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || s.class != obj.getClass()) {
                return false;
            }
            s sVar = (s) obj;
            if (this.a == null) {
                if (sVar.a != null) {
                    return false;
                }
            } else if (!e(this) || !e(sVar)) {
                Object obj2 = this.a;
                if (!(obj2 instanceof Number) || !(sVar.a instanceof Number)) {
                    return obj2.equals(sVar.a);
                }
                double dDoubleValue = c().doubleValue();
                double dDoubleValue2 = sVar.c().doubleValue();
                if (dDoubleValue != dDoubleValue2 && (!Double.isNaN(dDoubleValue) || !Double.isNaN(dDoubleValue2))) {
                    return false;
                }
            } else if (c().longValue() != sVar.c().longValue()) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        long jDoubleToLongBits;
        if (this.a == null) {
            return 31;
        }
        if (e(this)) {
            jDoubleToLongBits = c().longValue();
        } else {
            Object obj = this.a;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(c().doubleValue());
        }
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }
}

package roam.a.e.a.a0;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public final class r extends Number {
    public final String a;

    public r(String str) {
        this.a = str;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.parseDouble(this.a);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof r)) {
                return false;
            }
            String str = this.a;
            String str2 = ((r) obj).a;
            if (str != str2 && !str.equals(str2)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.parseFloat(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        try {
            return Integer.parseInt(this.a);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.a);
            } catch (NumberFormatException e2) {
                return new BigDecimal(this.a).intValue();
            }
        }
    }

    @Override // java.lang.Number
    public long longValue() {
        try {
            return Long.parseLong(this.a);
        } catch (NumberFormatException e) {
            return new BigDecimal(this.a).longValue();
        }
    }

    public String toString() {
        return this.a;
    }
}

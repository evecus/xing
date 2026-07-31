package roam.a.a.a.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.androlua.LuaApplication;
import com.androlua.LuaUtil;
import com.bumptech.glide.load.Key;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.roam.MyFileProvider;
import roam.a.a.f.a.k;
import roam.a.a.g.a.a.d.d;
import roam.a.a.g.a.a.e.b;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static String a;

    public static void A(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static int B(Context context, double d) {
        return (int) ((((double) context.getResources().getDisplayMetrics().density) * d) + 0.5d);
    }

    public static String C(String str) {
        String[] strArrSplit = str.split("=");
        if (strArrSplit.length <= 1) {
            return null;
        }
        String str2 = strArrSplit[1];
        return str2.contains("\"") ? str2.replaceAll("\"", "") : str2;
    }

    public static String D(String str) {
        try {
            if (!o(str)) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                messageDigest.update(str.getBytes(Key.STRING_CHARSET_NAME));
                byte[] bArrDigest = messageDigest.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : bArrDigest) {
                    sb.append(String.format("%02x", Byte.valueOf(b)));
                }
                return sb.toString();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static int E(float f, int i, int i2) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        return (((int) (((i2 & 255) - r5) * f)) + (i & 255)) | ((i3 + ((int) ((((i2 >> 24) & 255) - i3) * f))) << 24) | ((i4 + ((int) ((((i2 >> 16) & 255) - i4) * f))) << 16) | ((((int) ((((i2 >> 8) & 255) - i5) * f)) + i5) << 8);
    }

    public static String F(String str) {
        try {
            byte[] bArrArray = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(str.length()).array();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes(Key.STRING_CHARSET_NAME));
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            byte[] bArr = new byte[byteArrayOutputStream.toByteArray().length + 4];
            System.arraycopy(bArrArray, 0, bArr, 0, 4);
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, bArr, 4, byteArrayOutputStream.toByteArray().length);
            return Base64.encodeToString(bArr, 8);
        } catch (Exception e) {
            return "";
        }
    }

    public static roam.b.c.b.a.a.d.a.d.a G(List<roam.b.c.b.a.a.d.a.d.a> list, int i) {
        int size;
        if (i >= 0 && i <= list.size() - 1) {
            return list.get(i);
        }
        roam.b.c.b.a.a.d.a.d.a aVar = new roam.b.c.b.a.a.d.a.d.a();
        if (i < 0) {
            size = 0;
        } else {
            i = (i - list.size()) + 1;
            size = list.size() - 1;
        }
        roam.b.c.b.a.a.d.a.d.a aVar2 = list.get(size);
        aVar.a = aVar2.a + (aVar2.b() * i);
        aVar.b = aVar2.b;
        aVar.c = aVar2.c + (aVar2.b() * i);
        aVar.d = aVar2.d;
        aVar.e = aVar2.e + (aVar2.b() * i);
        aVar.f = aVar2.f;
        aVar.g = aVar2.g + (aVar2.b() * i);
        aVar.h = aVar2.h;
        return aVar;
    }

    public static String H(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            return new String(bArr, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean I(Bitmap bitmap, OutputStream outputStream) {
        if (bitmap != null) {
            try {
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return false;
                }
            } catch (Throwable th) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return false;
    }

    public static void J(String str, String str2) throws IOException {
        int length = str.length();
        ZipFile zipFile = new ZipFile(LuaApplication.getInstance().getApplicationInfo().publicSourceDir);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            String name = zipEntryNextElement.getName();
            if (name.indexOf(str) == 0) {
                String strSubstring = name.substring(length + 1);
                if (zipEntryNextElement.isDirectory()) {
                    File file = new File(roam.a.b.a.a.a.m(roam.a.b.a.a.a.o(str2), File.separator, strSubstring));
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                } else {
                    String strM = roam.a.b.a.a.a.m(roam.a.b.a.a.a.o(str2), File.separator, strSubstring);
                    File file2 = new File(strM);
                    File parentFile = new File(strM).getParentFile();
                    if (!parentFile.exists() && !parentFile.mkdirs()) {
                        StringBuilder sbO = roam.a.b.a.a.a.o("create file ");
                        sbO.append(parentFile.getName());
                        sbO.append(" fail");
                        throw new RuntimeException(sbO.toString());
                    }
                    if (!file2.exists() || zipEntryNextElement.getSize() != file2.length() || !LuaUtil.getFileMD5(zipFile.getInputStream(zipEntryNextElement)).equals(LuaUtil.getFileMD5(file2))) {
                        FileOutputStream fileOutputStream = new FileOutputStream(roam.a.b.a.a.a.m(roam.a.b.a.a.a.o(str2), File.separator, strSubstring));
                        InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, i);
                            }
                        }
                        fileOutputStream.close();
                        inputStream.close();
                    }
                }
            }
        }
        zipFile.close();
    }

    public static roam.a.a.g.a.a.e.e.a a(Context context, String str) {
        b bVar;
        if (context == null) {
            return null;
        }
        if (roam.a.a.g.a.a.e.e.b.a == null) {
            synchronized (b.class) {
                try {
                    if (b.d == null) {
                        b.d = new b(context, str);
                    }
                    bVar = b.d;
                } finally {
                }
            }
            roam.a.a.g.a.a.e.e.b.b = bVar;
            roam.a.a.g.a.a.e.e.b.a = new roam.a.a.g.a.a.e.e.b();
        }
        return roam.a.a.g.a.a.e.e.b.a;
    }

    public static Class<?> b(Type type) {
        while (!(type instanceof Class)) {
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalArgumentException("TODO");
            }
            type = ((ParameterizedType) type).getRawType();
        }
        return (Class) type;
    }

    public static String c() {
        k kVarA = k.a(6001);
        return d(kVarA.a, kVarA.b, "");
    }

    public static String d(int i, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("resultStatus={");
        sb.append(i);
        sb.append("};memo={");
        sb.append(str);
        sb.append("};result={");
        return roam.a.b.a.a.a.m(sb, str2, "}");
    }

    public static String e(Context context) {
        if (context != null) {
            TextUtils.isEmpty("https://mobilegw.alipay.com/mgw.htm");
        }
        return "https://mobilegw.alipay.com/mgw.htm";
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0044 A[Catch: all -> 0x0048, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0048, blocks: (B:11:0x002d, B:26:0x0044), top: B:32:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String f(java.lang.String r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            r2.<init>(r4, r5)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            boolean r4 = r2.exists()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            if (r4 != 0) goto L12
            goto L4d
        L12:
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            java.lang.String r2 = "UTF-8"
            r5.<init>(r3, r2)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
        L23:
            java.lang.String r5 = r4.readLine()     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L34
            if (r5 == 0) goto L2d
            r0.append(r5)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L34
            goto L23
        L2d:
            r4.close()     // Catch: java.lang.Throwable -> L48
            goto L49
        L31:
            r5 = move-exception
            r1 = r4
            goto L39
        L34:
            r5 = move-exception
            r1 = r4
            goto L42
        L37:
            r4 = move-exception
            r5 = r4
        L39:
            if (r1 == 0) goto L40
            r1.close()     // Catch: java.lang.Throwable -> L3f
            goto L40
        L3f:
            r4 = move-exception
        L40:
            throw r5
        L41:
            r4 = move-exception
        L42:
            if (r1 == 0) goto L49
            r1.close()     // Catch: java.lang.Throwable -> L48
            goto L49
        L48:
            r4 = move-exception
        L49:
            java.lang.String r1 = r0.toString()
        L4d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.a.b.a.f(java.lang.String, java.lang.String):java.lang.String");
    }

    public static String g(Map<String, String> map, String str, String str2) {
        String str3;
        return (map == null || (str3 = map.get(str)) == null) ? str2 : str3;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(144:(2:655|57)|(3:653|59|(141:61|97|661|99|(1:103)|(0)|673|107|(1:112)|(1:141)(10:114|(1:118)|119|(2:121|(1:123)(1:124))|125|(2:127|(1:129)(1:130))|131|(1:135)|136|(0)(1:140))|623|142|(1:144)(1:145)|146|150|633|151|(1:164)(4:157|(2:160|158)|677|161)|(1:166)|167|541|168|172|573|173|177|613|178|182|(1:193)(3:671|185|(3:667|187|(0))(0))|194|625|195|621|196|619|197|198|(2:199|(3:589|201|(2:203|(1:678)(1:209))(0))(0))|603|210|611|213|657|234|237|(1:239)|240|539|241|242|245|601|246|599|247|595|248|(84:250|581|251|585|254|278|(9:557|281|555|282|(3:549|283|(4:285|(2:717|713)(1:712)|577|313)(2:708|295))|545|296|577|313)|316|649|317|643|318|639|319|(2:321|322)(1:323)|637|324|669|341|344|543|345|346|349|607|350|(2:352|353)(1:355)|356|665|357|(2:359|360)(1:362)|363|569|364|(1:369)|370|593|371|(1:376)|377|551|378|(3:384|(4:387|(3:680|389|683)(1:682)|681|385)|679)|392|(1:402)(4:565|395|(2:396|(1:398)(1:707))|399)|403|675|404|(1:406)(1:409)|(1:413)|414|645|415|416|641|417|(4:420|(2:422|686)(2:423|685)|424|418)|684|425|432|(10:435|617|436|(3:609|437|(2:439|(2:689|441)(1:694))(2:688|442))|443|631|450|691|690|433)|687|454|(11:457|559|458|459|460|461|561|462|(2:475|696)(2:474|697)|476|455)|695|477|(5:479|575|480|700|484)|698|485|(10:488|571|489|490|567|491|(1:493)(1:494)|(2:503|702)(2:498|703)|504|486)|701|505|629|506|510|579|511|(1:513)(4:514|(5:516|547|517|706|521)|704|522)|526|663|527|(1:534)(1:531)|535|536)(2:591|258)|627|274|277|278|(0)|316|649|317|643|318|639|319|(0)(0)|637|324|669|341|344|543|345|346|349|607|350|(0)(0)|356|665|357|(0)(0)|363|569|364|(0)|370|593|371|(0)|377|551|378|(5:380|382|384|(1:385)|679)|392|(0)(0)|403|675|404|(0)(0)|(2:411|413)(0)|414|645|415|416|641|417|(1:418)|684|425|432|(1:433)|687|454|(1:455)|695|477|(0)|698|485|(1:486)|701|505|629|506|510|579|511|(0)(0)|526|663|527|(2:529|534)(0)|535|536))|62|69|(4:605|75|(2:77|78)(7:79|80|81|82|597|83|(0)(1:87))|95)(1:74)|(1:97)(1:98)|661|99|(3:101|103|(0))(0)|673|107|(0)|(0)(0)|623|142|(0)(0)|146|150|633|151|(2:153|164)(0)|(0)|167|541|168|172|573|173|177|613|178|182|(0)(0)|194|625|195|621|196|619|197|198|(3:199|(0)(0)|209)|603|210|611|213|657|234|237|(0)|240|539|241|242|245|601|246|599|247|595|248|(0)(0)|627|274|277|278|(0)|316|649|317|643|318|639|319|(0)(0)|637|324|669|341|344|543|345|346|349|607|350|(0)(0)|356|665|357|(0)(0)|363|569|364|(0)|370|593|371|(0)|377|551|378|(0)|392|(0)(0)|403|675|404|(0)(0)|(0)(0)|414|645|415|416|641|417|(1:418)|684|425|432|(1:433)|687|454|(1:455)|695|477|(0)|698|485|(1:486)|701|505|629|506|510|579|511|(0)(0)|526|663|527|(0)(0)|535|536) */
    /* JADX WARN: Can't wrap try/catch for region: R(148:0|2|(1:14)(4:5|(1:7)|8|(3:553|10|11)(0))|15|(1:26)(4:651|18|(1:20)(1:23)|(1:25)(0))|27|(1:38)(4:537|30|(1:32)(1:35)|(1:37)(0))|39|(1:53)(3:587|42|(3:583|44|(1:48))(0))|54|(1:56)(145:655|57|(3:653|59|(141:61|97|661|99|(1:103)|(0)|673|107|(1:112)|(1:141)(10:114|(1:118)|119|(2:121|(1:123)(1:124))|125|(2:127|(1:129)(1:130))|131|(1:135)|136|(0)(1:140))|623|142|(1:144)(1:145)|146|150|633|151|(1:164)(4:157|(2:160|158)|677|161)|(1:166)|167|541|168|172|573|173|177|613|178|182|(1:193)(3:671|185|(3:667|187|(0))(0))|194|625|195|621|196|619|197|198|(2:199|(3:589|201|(2:203|(1:678)(1:209))(0))(0))|603|210|611|213|657|234|237|(1:239)|240|539|241|242|245|601|246|599|247|595|248|(84:250|581|251|585|254|278|(9:557|281|555|282|(3:549|283|(4:285|(2:717|713)(1:712)|577|313)(2:708|295))|545|296|577|313)|316|649|317|643|318|639|319|(2:321|322)(1:323)|637|324|669|341|344|543|345|346|349|607|350|(2:352|353)(1:355)|356|665|357|(2:359|360)(1:362)|363|569|364|(1:369)|370|593|371|(1:376)|377|551|378|(3:384|(4:387|(3:680|389|683)(1:682)|681|385)|679)|392|(1:402)(4:565|395|(2:396|(1:398)(1:707))|399)|403|675|404|(1:406)(1:409)|(1:413)|414|645|415|416|641|417|(4:420|(2:422|686)(2:423|685)|424|418)|684|425|432|(10:435|617|436|(3:609|437|(2:439|(2:689|441)(1:694))(2:688|442))|443|631|450|691|690|433)|687|454|(11:457|559|458|459|460|461|561|462|(2:475|696)(2:474|697)|476|455)|695|477|(5:479|575|480|700|484)|698|485|(10:488|571|489|490|567|491|(1:493)(1:494)|(2:503|702)(2:498|703)|504|486)|701|505|629|506|510|579|511|(1:513)(4:514|(5:516|547|517|706|521)|704|522)|526|663|527|(1:534)(1:531)|535|536)(2:591|258)|627|274|277|278|(0)|316|649|317|643|318|639|319|(0)(0)|637|324|669|341|344|543|345|346|349|607|350|(0)(0)|356|665|357|(0)(0)|363|569|364|(0)|370|593|371|(0)|377|551|378|(5:380|382|384|(1:385)|679)|392|(0)(0)|403|675|404|(0)(0)|(2:411|413)(0)|414|645|415|416|641|417|(1:418)|684|425|432|(1:433)|687|454|(1:455)|695|477|(0)|698|485|(1:486)|701|505|629|506|510|579|511|(0)(0)|526|663|527|(2:529|534)(0)|535|536))|62|69|(4:605|75|(2:77|78)(7:79|80|81|82|597|83|(0)(1:87))|95)(1:74)|(1:97)(1:98)|661|99|(3:101|103|(0))(0)|673|107|(0)|(0)(0)|623|142|(0)(0)|146|150|633|151|(2:153|164)(0)|(0)|167|541|168|172|573|173|177|613|178|182|(0)(0)|194|625|195|621|196|619|197|198|(3:199|(0)(0)|209)|603|210|611|213|657|234|237|(0)|240|539|241|242|245|601|246|599|247|595|248|(0)(0)|627|274|277|278|(0)|316|649|317|643|318|639|319|(0)(0)|637|324|669|341|344|543|345|346|349|607|350|(0)(0)|356|665|357|(0)(0)|363|569|364|(0)|370|593|371|(0)|377|551|378|(0)|392|(0)(0)|403|675|404|(0)(0)|(0)(0)|414|645|415|416|641|417|(1:418)|684|425|432|(1:433)|687|454|(1:455)|695|477|(0)|698|485|(1:486)|701|505|629|506|510|579|511|(0)(0)|526|663|527|(0)(0)|535|536)|105|673|107|(0)|(0)(0)|623|142|(0)(0)|146|150|633|151|(0)(0)|(0)|167|541|168|172|573|173|177|613|178|182|(0)(0)|194|625|195|621|196|619|197|198|(3:199|(0)(0)|209)|603|210|611|213|657|234|237|(0)|240|539|241|242|245|601|246|599|247|595|248|(0)(0)|627|274|277|278|(0)|316|649|317|643|318|639|319|(0)(0)|637|324|669|341|344|543|345|346|349|607|350|(0)(0)|356|665|357|(0)(0)|363|569|364|(0)|370|593|371|(0)|377|551|378|(0)|392|(0)(0)|403|675|404|(0)(0)|(0)(0)|414|645|415|416|641|417|(1:418)|684|425|432|(1:433)|687|454|(1:455)|695|477|(0)|698|485|(1:486)|701|505|629|506|510|579|511|(0)(0)|526|663|527|(0)(0)|535|536|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(7:(2:557|281)|(2:555|282)|(3:549|283|(4:285|(2:717|713)(1:712)|577|313)(2:708|295))|545|296|577|313) */
    /* JADX WARN: Can't wrap try/catch for region: R(9:557|281|555|282|(3:549|283|(4:285|(2:717|713)(1:712)|577|313)(2:708|295))|545|296|577|313) */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x019a, code lost:
    
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0220, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0221, code lost:
    
        l(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x02ae, code lost:
    
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x02cd, code lost:
    
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x02ec, code lost:
    
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x035a, code lost:
    
        r17 = r11.substring(r11.indexOf(com.baidu.mobstat.Config.TRACE_TODAY_VISIT_SPLIT) + 1, r11.length()).trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x0372, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x0373, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x0374, code lost:
    
        r10 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x03ac, code lost:
    
        r0 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x03e5, code lost:
    
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x03e6, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x03e7, code lost:
    
        if (r9 != null) goto L615;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x03e9, code lost:
    
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x03ee, code lost:
    
        if (r7 != null) goto L627;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x042f, code lost:
    
        r10 = r0[1].trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x0483, code lost:
    
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:334:0x0484, code lost:
    
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x0485, code lost:
    
        if (r7 != null) goto L659;
     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x0487, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x048c, code lost:
    
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:340:0x048e, code lost:
    
        if (r11 != null) goto L669;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x04b8, code lost:
    
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:367:0x0549, code lost:
    
        r0 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x0560, code lost:
    
        r0 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x0682, code lost:
    
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:431:0x0684, code lost:
    
        r0 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:509:0x0876, code lost:
    
        r0 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:525:0x08d3, code lost:
    
        r0 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x017e A[Catch: all -> 0x018d, TRY_LEAVE, TryCatch #62 {all -> 0x018d, blocks: (B:99:0x0170, B:101:0x0176, B:103:0x017e), top: B:661:0x0170 }] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01e7 A[PHI: r0 r2 r10 r15 r16
  0x01e7: PHI (r0v51 java.lang.String) = (r0v50 java.lang.String), (r0v50 java.lang.String), (r0v317 java.lang.String) binds: [B:113:0x01a0, B:137:0x01db, B:139:0x01df] A[DONT_GENERATE, DONT_INLINE]
  0x01e7: PHI (r2v16 java.lang.String) = (r2v15 java.lang.String), (r2v47 java.lang.String), (r2v47 java.lang.String) binds: [B:113:0x01a0, B:137:0x01db, B:139:0x01df] A[DONT_GENERATE, DONT_INLINE]
  0x01e7: PHI (r10v4 java.lang.String) = (r10v3 java.lang.String), (r10v29 java.lang.String), (r10v29 java.lang.String) binds: [B:113:0x01a0, B:137:0x01db, B:139:0x01df] A[DONT_GENERATE, DONT_INLINE]
  0x01e7: PHI (r15v2 java.lang.String) = (r15v1 java.lang.String), (r15v13 java.lang.String), (r15v13 java.lang.String) binds: [B:113:0x01a0, B:137:0x01db, B:139:0x01df] A[DONT_GENERATE, DONT_INLINE]
  0x01e7: PHI (r16v2 java.lang.String) = (r16v1 java.lang.String), (r16v3 java.lang.String), (r16v3 java.lang.String) binds: [B:113:0x01a0, B:137:0x01db, B:139:0x01df] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0239 A[Catch: all -> 0x0279, TryCatch #48 {all -> 0x0279, blocks: (B:151:0x022f, B:153:0x0239, B:155:0x023f, B:157:0x0245, B:158:0x024e, B:160:0x0254, B:161:0x0270), top: B:633:0x022f }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x03cb A[Catch: all -> 0x03e0, TRY_LEAVE, TryCatch #29 {all -> 0x03e0, blocks: (B:248:0x03c1, B:250:0x03cb), top: B:595:0x03c1 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0467 A[Catch: all -> 0x047e, TRY_LEAVE, TryCatch #51 {all -> 0x047e, blocks: (B:319:0x0461, B:321:0x0467), top: B:639:0x0461 }] */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x04cf A[Catch: all -> 0x04e8, TRY_LEAVE, TryCatch #35 {all -> 0x04e8, blocks: (B:350:0x04c3, B:352:0x04cf), top: B:607:0x04c3 }] */
    /* JADX WARN: Removed duplicated region for block: B:355:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x0508 A[Catch: all -> 0x0511, TRY_LEAVE, TryCatch #64 {all -> 0x0511, blocks: (B:357:0x0500, B:359:0x0508), top: B:665:0x0500 }] */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0563  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0578 A[Catch: all -> 0x05ba, TryCatch #7 {all -> 0x05ba, blocks: (B:378:0x056e, B:380:0x0578, B:382:0x057e, B:384:0x0584, B:385:0x0588, B:387:0x058e, B:389:0x0596), top: B:551:0x056e }] */
    /* JADX WARN: Removed duplicated region for block: B:387:0x058e A[Catch: all -> 0x05ba, TryCatch #7 {all -> 0x05ba, blocks: (B:378:0x056e, B:380:0x0578, B:382:0x057e, B:384:0x0584, B:385:0x0588, B:387:0x058e, B:389:0x0596), top: B:551:0x056e }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x060a  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0618 A[Catch: all -> 0x061d, TRY_LEAVE, TryCatch #69 {all -> 0x061d, blocks: (B:404:0x0610, B:406:0x0618), top: B:675:0x0610 }] */
    /* JADX WARN: Removed duplicated region for block: B:409:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0621  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0629  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x063e A[Catch: all -> 0x067f, TRY_LEAVE, TryCatch #52 {all -> 0x067f, blocks: (B:417:0x0637, B:420:0x063e, B:424:0x0674, B:425:0x067a), top: B:641:0x0637 }] */
    /* JADX WARN: Removed duplicated region for block: B:435:0x06b8  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x0750  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x07bf  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x081c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d2 A[Catch: all -> 0x00d7, TRY_LEAVE, TryCatch #23 {all -> 0x00d7, blocks: (B:44:0x00c4, B:46:0x00ca, B:48:0x00d2), top: B:583:0x00c4 }] */
    /* JADX WARN: Removed duplicated region for block: B:513:0x088a  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x088d  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x08f0 A[Catch: all -> 0x090d, TryCatch #63 {all -> 0x090d, blocks: (B:527:0x08e2, B:529:0x08f0, B:531:0x08f6), top: B:663:0x08e2 }] */
    /* JADX WARN: Removed duplicated region for block: B:534:0x090e  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x03fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0442 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:565:0x05c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:589:0x033a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:591:0x03da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:615:0x03e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:635:0x0377 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:647:0x037e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:657:0x0385 A[EXC_TOP_SPLITTER, PHI: r2 r17
  0x0385: PHI (r2v22 java.io.FileInputStream) = (r2v45 java.io.FileInputStream), (r2v46 java.io.FileInputStream), (r2v46 java.io.FileInputStream) binds: [B:233:0x0383, B:215:0x036b, B:213:0x0367] A[DONT_GENERATE, DONT_INLINE]
  0x0385: PHI (r17v1 java.lang.String) = (r17v0 java.lang.String), (r17v9 java.lang.String), (r17v9 java.lang.String) binds: [B:233:0x0383, B:215:0x036b, B:213:0x0367] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:659:0x0487 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:671:0x02ff A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.String, java.lang.String> h(android.content.Context r25) {
        /*
            Method dump skipped, instruction units count: 2388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.a.b.a.h(android.content.Context):java.util.Map");
    }

    public static void i(Context context, String str, String str2, String str3) {
        String packageName;
        synchronized (a.class) {
            try {
                packageName = context.getPackageName();
            } catch (Throwable th) {
                packageName = "";
            }
            try {
                roam.a.a.g.a.a.d.a aVar = new roam.a.a.g.a.a.d.a(Build.MODEL, packageName, "APPSecuritySDK-ALIPAY", "3.2.2-20180331", str, str2, str3);
                String str4 = context.getFilesDir().getAbsolutePath() + "/log/ap";
                String str5 = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log";
                String string = aVar.toString();
                synchronized (d.class) {
                    try {
                        d.a = str4;
                        d.b = str5;
                        d.c = string;
                    } finally {
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public static void j(Context context, String str, Map<String, String> map) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        if (editorEdit != null) {
            for (String str2 : map.keySet()) {
                editorEdit.putString(str2, map.get(str2));
            }
            editorEdit.commit();
        }
    }

    public static void k(String str) {
        synchronized (a.class) {
            try {
                synchronized (d.class) {
                    try {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(str);
                        d.b(arrayList);
                    } finally {
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void l(Throwable th) {
        synchronized (a.class) {
            try {
                d.a(th);
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public static boolean m() {
        String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState != null && externalStorageState.length() > 0 && (externalStorageState.equals("mounted") || externalStorageState.equals("mounted_ro")) && Environment.getExternalStorageDirectory() != null;
    }

    public static boolean n(Class<?> cls) {
        return cls.isPrimitive() || cls.equals(String.class) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Double.class) || cls.equals(Float.class) || cls.equals(Boolean.class) || cls.equals(Short.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Void.class);
    }

    public static boolean o(String str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean p(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    public static boolean q(List<String> list) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setLenient(false);
        int iRandom = (int) (Math.random() * 24.0d * 60.0d * 60.0d);
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String[] strArrSplit = it.next().split("&");
                if (strArrSplit != null && strArrSplit.length == 2) {
                    Date date = new Date();
                    Date date2 = simpleDateFormat.parse(strArrSplit[0] + " 00:00:00");
                    Date date3 = simpleDateFormat.parse(strArrSplit[1] + " 23:59:59");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date3);
                    calendar.add(13, iRandom * 1);
                    Date time = calendar.getTime();
                    if (date.after(date2) && date.before(time)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] r(byte[] r6) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L47
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L47
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L45
            r6.<init>()     // Catch: java.lang.Throwable -> L45
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L3f
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L3f
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L3a
        L14:
            int r3 = r1.read(r0)     // Catch: java.lang.Throwable -> L3a
            r4 = -1
            if (r3 == r4) goto L20
            r4 = 0
            r2.write(r0, r4, r3)     // Catch: java.lang.Throwable -> L3a
            goto L14
        L20:
            r2.flush()     // Catch: java.lang.Throwable -> L3a
            r2.finish()     // Catch: java.lang.Throwable -> L3a
            byte[] r0 = r6.toByteArray()     // Catch: java.lang.Throwable -> L3a
            r1.close()     // Catch: java.lang.Exception -> L2e
            goto L2f
        L2e:
            r1 = move-exception
        L2f:
            r6.close()     // Catch: java.lang.Exception -> L33
            goto L34
        L33:
            r6 = move-exception
        L34:
            r2.close()     // Catch: java.lang.Exception -> L38
            goto L39
        L38:
            r6 = move-exception
        L39:
            return r0
        L3a:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L4a
        L3f:
            r2 = move-exception
            r5 = r0
            r0 = r6
            r6 = r2
            r2 = r5
            goto L4a
        L45:
            r6 = move-exception
            goto L49
        L47:
            r6 = move-exception
            r1 = r0
        L49:
            r2 = r0
        L4a:
            if (r1 == 0) goto L51
            r1.close()     // Catch: java.lang.Exception -> L50
            goto L51
        L50:
            r1 = move-exception
        L51:
            if (r0 == 0) goto L58
            r0.close()     // Catch: java.lang.Exception -> L57
            goto L58
        L57:
            r0 = move-exception
        L58:
            if (r2 == 0) goto L5f
            r2.close()     // Catch: java.lang.Exception -> L5e
            goto L5f
        L5e:
            r0 = move-exception
        L5f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.a.a.a.b.a.r(byte[]):byte[]");
    }

    public static String s(int i, String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(i, secretKeySpec);
            byte[] bArrDoFinal = cipher.doFinal(i == 2 ? roam.a.a.f.d.a.b(str) : str.getBytes(Key.STRING_CHARSET_NAME));
            return i == 2 ? new String(bArrDoFinal) : roam.a.a.f.d.a.a(bArrDoFinal);
        } catch (Exception e) {
            return null;
        }
    }

    public static String t(String str) {
        try {
            if (!o(str)) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                messageDigest.update(str.getBytes(Key.STRING_CHARSET_NAME));
                byte[] bArrDigest = messageDigest.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : bArrDigest) {
                    sb.append(String.format("%02x", Byte.valueOf(b)));
                }
                return sb.toString();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String u(String str) {
        try {
            System.clearProperty(str);
        } catch (Throwable th) {
        }
        if (!o("")) {
            return "";
        }
        String strM = roam.a.b.a.a.a.m(new StringBuilder(".SystemConfig"), File.separator, str);
        try {
            if (m()) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), strM);
                if (file.exists()) {
                    file.delete();
                    return "";
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean v(String str) {
        return !o(str);
    }

    public static byte[] w(byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        GZIPInputStream gZIPInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream = null;
        }
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                byte[] bArr2 = new byte[4096];
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int i = gZIPInputStream.read(bArr2, 0, 4096);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr2, 0, i);
                    } catch (Throwable th3) {
                        th = th3;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e) {
                        }
                        try {
                            gZIPInputStream.close();
                        } catch (Exception e2) {
                        }
                        try {
                            byteArrayInputStream.close();
                            throw th;
                        } catch (Exception e3) {
                            throw th;
                        }
                    }
                }
                byteArrayOutputStream2.flush();
                byte[] byteArray = byteArrayOutputStream2.toByteArray();
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception e4) {
                }
                try {
                    gZIPInputStream.close();
                } catch (Exception e5) {
                }
                try {
                    byteArrayInputStream.close();
                } catch (Exception e6) {
                }
                return byteArray;
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            th = th;
            gZIPInputStream = null;
            byteArrayOutputStream.close();
            gZIPInputStream.close();
            byteArrayInputStream.close();
            throw th;
        }
    }

    @Deprecated
    public static Intent x(Activity activity, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("image/*");
        intent.setFlags(268435456);
        intent.addFlags(3);
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, MyFileProvider.getAuthor(activity), file));
        return Intent.createChooser(intent, "Share to..");
    }

    public static boolean y(String str) {
        for (byte b : str.getBytes()) {
            if ((b >= 0 && b <= 31) || b >= 127) {
                return false;
            }
        }
        return true;
    }

    public static double z(int i, int i2) {
        double dDoubleValue = new BigDecimal(Double.toString(Double.parseDouble(String.valueOf(i2)))).divide(new BigDecimal(Double.toString(100.0d)), 2, 4).doubleValue();
        double d = ((double) i) * dDoubleValue;
        Log.d("fa2", "source:" + i);
        Log.d("fa2", "percent:" + i2);
        Log.d("fa2", "result:" + d);
        Log.d("fa2", "percentDecimal:" + dDoubleValue);
        return d;
    }
}

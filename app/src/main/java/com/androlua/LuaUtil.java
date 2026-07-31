package com.androlua;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import androidx.core.os.EnvironmentCompat;
import androidx.exifinterface.media.ExifInterface;
import com.baidu.mobstat.Config;
import com.luajava.LuaFunction;
import com.luajava.LuaString;
import dalvik.system.DexFile;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaUtil {
    private static final byte[] BUFFER = new byte[8192];
    public static final HashMap<String, String> mFileTypes;

    static {
        HashMap<String, String> map = new HashMap<>();
        mFileTypes = map;
        map.put("FFD8FF", "jpg");
        map.put("89504E47", "png");
        map.put("47494638", "gif");
        map.put("49492A00", "tif");
        map.put("424D", "bmp");
        map.put("41433130", "dwg");
        map.put("38425053", "psd");
        map.put("7B5C727466", "rtf");
        map.put("3C3F786D6C", "xml");
        map.put("68746D6C3E", "html");
        map.put("44656C69766572792D646174653A", "eml");
        map.put("D0CF11E0", "doc");
        map.put("5374616E64617264204A", "mdb");
        map.put("252150532D41646F6265", "ps");
        map.put("255044462D312E", "pdf");
        map.put("504B0304", "docx");
        map.put("52617221", "rar");
        map.put("57415645", "wav");
        map.put("41564920", "avi");
        map.put("2E524D46", "rm");
        map.put("000001BA", "mpg");
        map.put("000001B3", "mpg");
        map.put("6D6F6F76", "mov");
        map.put("3026B2758E66CF11", "asf");
        map.put("4D546864", "mid");
        map.put("1F8B08", "gz");
    }

    public static void assetsToSD(Context context, String str, String str2) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        InputStream inputStreamOpen = context.getAssets().open(str);
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStreamOpen.read(bArr);
            if (i <= 0) {
                fileOutputStream.flush();
                inputStreamOpen.close();
                fileOutputStream.close();
                return;
            }
            fileOutputStream.write(bArr, 0, i);
        }
    }

    private static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String upperCase = Integer.toHexString(b & ExifInterface.MARKER).toUpperCase();
            if (upperCase.length() < 2) {
                sb.append(0);
            }
            sb.append(upperCase);
        }
        return sb.toString();
    }

    public static Bitmap captureScreen(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        int pixelFormat = defaultDisplay.getPixelFormat();
        PixelFormat pixelFormat2 = new PixelFormat();
        PixelFormat.getPixelFormatInfo(pixelFormat, pixelFormat2);
        int i3 = pixelFormat2.bytesPerPixel;
        int i4 = i * i2;
        byte[] bArr = new byte[i3 * i4];
        try {
            Runtime.getRuntime().exec(new String[]{"/system/bin/su", "-c", "chmod 777 /dev/graphics/fb0"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            new DataInputStream(new FileInputStream(new File("/dev/graphics/fb0"))).readFully(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5 * 4;
            iArr[i5] = ((bArr[i6 + 3] & ExifInterface.MARKER) << 24) + ((bArr[i6] & ExifInterface.MARKER) << 16) + ((bArr[i6 + 1] & ExifInterface.MARKER) << 8) + (bArr[i6 + 2] & ExifInterface.MARKER);
        }
        return Bitmap.createBitmap(iArr, i2, i, Bitmap.Config.ARGB_8888);
    }

    private boolean check(int i, int i2, int[][] iArr, int i3, int i4) {
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i2 + i5;
            if (iArr[i][i6] != 1 || iArr[i + i4][i6] != 0) {
                return false;
            }
        }
        return true;
    }

    private static int compare(String str, String str2) {
        int length = str.length();
        int length2 = str2.length();
        if (length == 0) {
            return length2;
        }
        if (length2 == 0) {
            return length;
        }
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, length + 1, length2 + 1);
        int i = 0;
        for (int i2 = 0; i2 <= length; i2++) {
            iArr[i2][0] = i2;
        }
        for (int i3 = 0; i3 <= length2; i3++) {
            iArr[0][i3] = i3;
        }
        int i4 = 1;
        while (i4 <= length) {
            int i5 = i4 - 1;
            char cCharAt = str.charAt(i5);
            int i6 = 1;
            while (i6 <= length2) {
                int i7 = i6 - 1;
                char cCharAt2 = str2.charAt(i7);
                int i8 = (cCharAt == cCharAt2 || cCharAt == cCharAt2 + ' ' || cCharAt + ' ' == cCharAt2) ? i : 1;
                int[] iArr2 = iArr[i4];
                int[] iArr3 = iArr[i5];
                iArr2[i6] = min(iArr3[i6] + 1, iArr2[i7] + 1, i8 + iArr3[i7]);
                i6++;
                i = 0;
            }
            i4++;
            i = 0;
        }
        return iArr[length][length2];
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0064 -> B:53:0x00ac). Please report as a decompilation issue!!! */
    private static void compress(File file, ZipOutputStream zipOutputStream, String str) throws Throwable {
        File[] fileArrListFiles;
        ?? bufferedInputStream;
        ?? r0;
        if (!file.isFile()) {
            if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length <= 0) {
                return;
            }
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    compress(file2, zipOutputStream, str);
                } else {
                    StringBuilder sbO = a.o(str);
                    sbO.append(file2.getName());
                    sbO.append("/");
                    compress(file2, zipOutputStream, sbO.toString());
                }
            }
            return;
        }
        ?? r02 = 0;
        ?? r03 = 0;
        ?? r04 = 0;
        r02 = 0;
        try {
        } catch (IOException e) {
            e.printStackTrace();
            r02 = r02;
        }
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file), BUFFER.length);
                try {
                    String str2 = str + file.getName();
                    System.out.println(str2);
                    zipOutputStream.putNextEntry(new ZipEntry(str2));
                    while (true) {
                        byte[] bArr = BUFFER;
                        int i = bufferedInputStream.read(bArr, 0, bArr.length);
                        r02 = -1;
                        if (i == -1) {
                            break;
                        } else {
                            zipOutputStream.write(bArr, 0, i);
                        }
                    }
                    bufferedInputStream.close();
                } catch (FileNotFoundException e2) {
                    e = e2;
                    r03 = bufferedInputStream;
                    e.printStackTrace();
                    r02 = r03;
                    r0 = r03;
                    if (r03 != 0) {
                        r0.close();
                        r02 = r0;
                    }
                } catch (IOException e3) {
                    e = e3;
                    r04 = bufferedInputStream;
                    e.printStackTrace();
                    r02 = r04;
                    if (r04 != 0) {
                        r0 = r04;
                        r0.close();
                        r02 = r0;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (bufferedInputStream != 0) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
            } catch (IOException e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = r02;
        }
    }

    public static boolean copyDir(File file, File file2) {
        File parentFile = file2.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!file.isDirectory()) {
            try {
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                return copyFile(new FileInputStream(file), new FileOutputStream(file2));
            } catch (IOException e) {
                Log.i("lua", e.getMessage());
                return false;
            }
        }
        File[] fileArrListFiles = file.listFiles();
        boolean zCopyDir = true;
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file3 : fileArrListFiles) {
                zCopyDir = copyDir(file3, new File(file2, file3.getName()));
            }
        } else if (!file2.exists()) {
            return file2.mkdirs();
        }
        return zCopyDir;
    }

    public static boolean copyDir(String str, String str2) {
        return copyDir(new File(str), new File(str2));
    }

    public static void copyFile(File file, File file2) {
        try {
            copyFile(new FileInputStream(file), new FileOutputStream(file2));
        } catch (IOException e) {
            Log.i("lua", e.getMessage());
        }
    }

    public static void copyFile(String str, String str2) {
        try {
            copyFile(new FileInputStream(str), new FileOutputStream(str2));
        } catch (IOException e) {
            Log.i("lua", e.getMessage());
        }
    }

    public static boolean copyFile(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    return true;
                }
                outputStream.write(bArr, 0, i);
            }
        } catch (Exception e) {
            Log.i("lua", e.getMessage());
            return false;
        }
    }

    public static Object dump(LuaFunction luaFunction) {
        try {
            byte[] bArrDump = luaFunction.dump();
            int iSqrt = (int) Math.sqrt(bArrDump.length);
            int i = iSqrt + 1;
            int[] iArr = new int[iSqrt * i];
            int length = bArrDump.length / 4;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 4;
                iArr[i2] = Color.argb((int) bArrDump[i3], (int) bArrDump[i3 + 1], (int) bArrDump[i3 + 2], (int) bArrDump[i3 + 3]);
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, iSqrt, i, Bitmap.Config.ARGB_8888);
            FileOutputStream fileOutputStream = new FileOutputStream("/sdcard/a.png");
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            return bitmapCreateBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    public static String[] getAllName(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<String> enumerationEntries = new DexFile(context.getPackageCodePath()).entries();
            while (enumerationEntries.hasMoreElements()) {
                arrayList.add(enumerationEntries.nextElement());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Enumeration<? extends ZipEntry> enumerationEntries2 = new ZipFile(str).entries();
            while (enumerationEntries2.hasMoreElements()) {
                arrayList.add(enumerationEntries2.nextElement().getName().replaceAll("/", ".").replace(".class", ""));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    public static String getFileHeader(InputStream inputStream) {
        try {
            byte[] bArr = new byte[4];
            inputStream.read(bArr, 0, 4);
            String strBytesToHexString = bytesToHexString(bArr);
            try {
                inputStream.close();
                return strBytesToHexString;
            } catch (IOException e) {
                return strBytesToHexString;
            }
        } catch (Exception e2) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
            return null;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    public static String getFileMD5(File file) {
        try {
            return getFileMD5(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static String getFileMD5(InputStream inputStream) {
        MessageDigest messageDigest;
        byte[] bArr = new byte[8192];
        try {
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            try {
                inputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                String string = new BigInteger(1, messageDigest.digest()).toString(16);
                try {
                    inputStream.close();
                    return string;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return string;
                }
            }
            messageDigest.update(bArr, 0, i);
            inputStream.close();
        }
    }

    public static String getFileMD5(String str) {
        return getFileMD5(new File(str));
    }

    public static String getFileSha1(File file) {
        try {
            return getFileSha1(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static String getFileSha1(InputStream inputStream) {
        MessageDigest messageDigest;
        byte[] bArr = new byte[8192];
        try {
            try {
                messageDigest = MessageDigest.getInstance("SHA-1");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            try {
                inputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                String string = new BigInteger(1, messageDigest.digest()).toString(16);
                try {
                    inputStream.close();
                    return string;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return string;
                }
            }
            messageDigest.update(bArr, 0, i);
            inputStream.close();
        }
    }

    public static String getFileSha1(String str) {
        return getFileMD5(new File(str));
    }

    public static String getFileType(File file) {
        try {
            return mFileTypes.get(getFileHeader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static String getFileType(InputStream inputStream) {
        return mFileTypes.get(getFileHeader(inputStream));
    }

    public static String getFileType(String str) {
        try {
            return mFileTypes.get(getFileHeader(new FileInputStream(str)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static float getSimilarityRatio(String str, String str2) {
        return 1.0f - (compare(str, str2) / Math.max(str.length(), str2.length()));
    }

    private static int min(int i, int i2, int i3) {
        if (i >= i2) {
            i = i2;
        }
        return i < i3 ? i : i3;
    }

    public static byte[] readAll(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static LuaString readApkFile(String str) {
        ZipFile zipFile = new ZipFile(LuaApplication.getInstance().getPackageCodePath());
        return new LuaString(readAll(zipFile.getInputStream(zipFile.getEntry(str))));
    }

    public static byte[] readAsset(Context context, String str) throws IOException {
        InputStream inputStreamOpen = context.getAssets().open(str);
        byte[] all = readAll(inputStreamOpen);
        inputStreamOpen.close();
        return all;
    }

    public static byte[] readZip(String str, String str2) {
        ZipFile zipFile = new ZipFile(str);
        return readAll(zipFile.getInputStream(zipFile.getEntry(str2)));
    }

    public static LuaString readZipFile(String str, String str2) {
        ZipFile zipFile = new ZipFile(str);
        return new LuaString(readAll(zipFile.getInputStream(zipFile.getEntry(str2))));
    }

    public static void rmDir(File file, String str) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                rmDir(file2, str);
            }
            file.delete();
        }
        if (file.getName().endsWith(str)) {
            file.delete();
        }
    }

    public static boolean rmDir(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                rmDir(file2);
            }
        }
        return file.delete();
    }

    public static void unZip(String str) throws IOException {
        unZip(str, new File(str).getParent(), "");
    }

    public static void unZip(String str, String str2) throws IOException {
        unZip(str, str2, "");
    }

    public static void unZip(String str, String str2, String str3) throws IOException {
        ZipFile zipFile = new ZipFile(str);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            String name = zipEntryNextElement.getName();
            if (name.startsWith(str3)) {
                if (zipEntryNextElement.isDirectory()) {
                    File file = new File(a.m(a.o(str2), File.separator, name));
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                } else {
                    StringBuilder sbO = a.o(str2);
                    String str4 = File.separator;
                    File parentFile = new File(a.m(sbO, str4, name)).getParentFile();
                    if (!parentFile.exists() && !parentFile.mkdirs()) {
                        StringBuilder sbO2 = a.o("create file ");
                        sbO2.append(parentFile.getName());
                        sbO2.append(" fail");
                        throw new RuntimeException(sbO2.toString());
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(a.k(str2, str4, name));
                    InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                    byte[] bArr = new byte[8192];
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
        zipFile.close();
    }

    public static void unZip(String str, boolean z) throws IOException {
        if (!z) {
            unZip(str);
            return;
        }
        String name = new File(str).getName();
        int iLastIndexOf = name.lastIndexOf(".");
        if (iLastIndexOf > 0) {
            name = name.substring(0, iLastIndexOf);
        }
        int iIndexOf = name.indexOf(Config.replace);
        if (iIndexOf > 0) {
            name = name.substring(0, iIndexOf);
        }
        int iIndexOf2 = name.indexOf("(");
        if (iIndexOf2 > 0) {
            name = name.substring(0, iIndexOf2);
        }
        unZip(str, new File(str).getParent() + File.separator + name, "");
    }

    public static boolean zip(String str) {
        return zip(str, new File(str).getParent());
    }

    public static boolean zip(String str, String str2) {
        return zip(str, str2, new File(str).getName() + ".zip");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x007b -> B:60:0x007e). Please report as a decompilation issue!!! */
    public static boolean zip(String str, String str2, String str3) throws Throwable {
        ?? r4;
        ZipOutputStream zipOutputStream;
        File file = new File(str);
        File file2 = new File(str2, str3);
        boolean z = false;
        if (file2.getParentFile().exists() || file2.getParentFile().mkdirs()) {
            if (file2.exists()) {
                try {
                    file2.createNewFile();
                    r4 = 0;
                    ZipOutputStream zipOutputStream2 = null;
                    r4 = 0;
                    try {
                    } catch (IOException e) {
                        e.printStackTrace();
                        r4 = r4;
                    }
                    try {
                        try {
                            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(new FileOutputStream(file2), new Adler32());
                            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(checkedOutputStream));
                            try {
                                String str4 = "";
                                compress(file, zipOutputStream, "");
                                checkedOutputStream.getChecksum().getValue();
                                try {
                                    zipOutputStream.closeEntry();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                                z = true;
                                zipOutputStream.close();
                                r4 = str4;
                            } catch (FileNotFoundException e3) {
                                e = e3;
                                zipOutputStream2 = zipOutputStream;
                                e.printStackTrace();
                                r4 = zipOutputStream2;
                                if (zipOutputStream2 != null) {
                                    try {
                                        zipOutputStream2.closeEntry();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                    zipOutputStream2.close();
                                    r4 = zipOutputStream2;
                                }
                            } catch (Throwable th) {
                                th = th;
                                r4 = zipOutputStream;
                                if (r4 != 0) {
                                    try {
                                        r4.closeEntry();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                    try {
                                        r4.close();
                                    } catch (IOException e6) {
                                        e6.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (FileNotFoundException e7) {
                            e = e7;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e8) {
                }
            } else {
                r4 = 0;
                ZipOutputStream zipOutputStream22 = null;
                r4 = 0;
                CheckedOutputStream checkedOutputStream2 = new CheckedOutputStream(new FileOutputStream(file2), new Adler32());
                zipOutputStream = new ZipOutputStream(new BufferedOutputStream(checkedOutputStream2));
                String str42 = "";
                compress(file, zipOutputStream, "");
                checkedOutputStream2.getChecksum().getValue();
                zipOutputStream.closeEntry();
                z = true;
                zipOutputStream.close();
                r4 = str42;
            }
        }
        return z;
    }

    public int checkPixel(int i, int i2, int[][] iArr) {
        int[] iArr2 = iArr[i];
        int i3 = iArr2[i2];
        if (i2 + 30 >= iArr2.length) {
            return i3;
        }
        int i4 = 0;
        for (int i5 = 1; i5 <= 30; i5++) {
            if (iArr[i][i2 + i5] == 0) {
                i4++;
            }
        }
        if (i4 > 15) {
            return 0;
        }
        return i3;
    }

    public int getDifferenceValue(String str, String str2) {
        new File(str);
        new File(str2);
        try {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            Bitmap bitmapDecodeFile2 = BitmapFactory.decodeFile(str2);
            int width = bitmapDecodeFile.getWidth();
            int height = bitmapDecodeFile.getHeight();
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, width, height);
            for (int i = 1; i < width; i++) {
                for (int i2 = 1; i2 < height; i2++) {
                    if (bitmapDecodeFile.getPixel(i, i2) == bitmapDecodeFile2.getPixel(i, i2)) {
                        iArr[i - 1][i2 - 1] = 0;
                    } else {
                        iArr[i - 1][i2 - 1] = 1;
                    }
                }
            }
            int i3 = 999;
            int i4 = -1;
            for (int i5 = 0; i5 < iArr.length; i5++) {
                int i6 = 0;
                while (true) {
                    int[] iArr2 = iArr[i5];
                    if (i6 < iArr2.length) {
                        if (iArr2[i6] == 1) {
                            iArr2[i6] = checkPixel(i5, i6, iArr);
                            if (iArr[i5][i6] == 1) {
                                if (i5 > i4) {
                                    i4 = i5;
                                } else if (i5 < i3) {
                                    i3 = i5;
                                }
                            }
                        }
                        i6++;
                    }
                }
            }
            return (i4 + i3) / 2;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public BitmapDrawable toBlack(String str, float f, int i, int i2) {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        int width = bitmapDecodeFile.getWidth();
        int height = bitmapDecodeFile.getHeight();
        Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int i3 = width * height;
        int[] iArr = new int[i3];
        float[] fArr = new float[i3];
        float[] fArr2 = new float[3];
        float f2 = 0.0f;
        for (int i4 = 0; i4 < height; i4++) {
            for (int i5 = 0; i5 < width; i5++) {
                Color.colorToHSV(bitmapDecodeFile.getPixel(i5, i4), fArr2);
                fArr[(width * i4) + i5] = fArr2[2];
                f2 += fArr2[2];
            }
        }
        float f3 = f2 / i3;
        int[][] iArr2 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, width, height);
        for (int i6 = 0; i6 < height; i6++) {
            for (int i7 = 0; i7 < width; i7++) {
                int i8 = (width * i6) + i7;
                if (fArr[i8] > f3 * f) {
                    iArr[i8] = -1;
                    iArr2[i7][i6] = 1;
                } else {
                    iArr[i8] = -16777216;
                    iArr2[i7][i6] = 0;
                }
            }
        }
        for (int i9 = width / 2; i9 < width - 10; i9++) {
            int i10 = width / 3;
            while (true) {
                if (i10 >= width) {
                    break;
                }
                if (check(i9, i10, iArr2, i, i2)) {
                    Log.i("find_color", i9 + "");
                    break;
                }
                i10++;
            }
        }
        return new BitmapDrawable(Bitmap.createBitmap(iArr, width, height, Bitmap.Config.RGB_565));
    }
}

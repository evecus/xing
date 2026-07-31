package com.androlua;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.baidu.mobstat.Config;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class LuaBitmap {
    private static int l;
    public static WeakHashMap<String, WeakReference<Bitmap>> cache = new WeakHashMap<>();
    private static long mCacheTime = Config.MAX_LOG_DATA_EXSIT_TIME;

    public static boolean checkCache(LuaContext luaContext, String str) {
        File file = new File(luaContext.getLuaExtDir("cache") + "/" + str.hashCode());
        return file.exists() && mCacheTime != -1 && System.currentTimeMillis() - file.lastModified() < mCacheTime;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int i, int i2) {
        int iMin;
        double d = options.outWidth;
        double d2 = options.outHeight;
        int iCeil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        if (i == -1) {
            iMin = 128;
        } else {
            double d3 = i;
            iMin = (int) Math.min(Math.floor(d / d3), Math.floor(d2 / d3));
        }
        if (iMin >= iCeil) {
            if (i2 == -1 && i == -1) {
                return 1;
            }
            if (i != -1) {
                return iMin;
            }
        }
        return iCeil;
    }

    private static int computeSampleSize(BitmapFactory.Options options, int i, int i2) {
        int iComputeInitialSampleSize = computeInitialSampleSize(options, i, i2);
        if (iComputeInitialSampleSize > 8) {
            return 8 * ((iComputeInitialSampleSize + 7) / 8);
        }
        int i3 = 1;
        while (i3 < iComputeInitialSampleSize) {
            i3 <<= 1;
        }
        return i3;
    }

    public static Bitmap decodeScale(int i, File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        int iPow = (options.outHeight > i * 4 || options.outWidth > i) ? (int) Math.pow(2.0d, (int) Math.round(Math.log(((double) i) / ((double) Math.max(r2, options.outWidth))) / Math.log(0.5d))) : 1;
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = iPow;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options2);
    }

    public static Bitmap getAssetBitmap(Context context, String str) throws IOException {
        InputStream inputStreamOpen = context.getAssets().open(str);
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
        inputStreamOpen.close();
        return bitmapDecodeStream;
    }

    public static Bitmap getBitmap(LuaContext luaContext, String str) throws IOException {
        Bitmap httpBitmap;
        Bitmap bitmap;
        WeakReference<Bitmap> weakReference = cache.get(str);
        if (weakReference != null && (bitmap = weakReference.get()) != null) {
            return bitmap;
        }
        if (str.toLowerCase().startsWith("http://") || str.toLowerCase().startsWith("https://")) {
            httpBitmap = getHttpBitmap(luaContext, str);
        } else if (str.charAt(0) != '/') {
            httpBitmap = getLocalBitmap(luaContext, luaContext.getLuaDir() + "/" + str);
        } else {
            httpBitmap = getLocalBitmap(luaContext, str);
        }
        Bitmap bitmap2 = httpBitmap;
        cache.put(str, new WeakReference<>(bitmap2));
        return bitmap2;
    }

    public static Bitmap getBitmapFromFile(File file, int i, int i2) {
        BitmapFactory.Options options;
        if (file == null || !file.exists()) {
            return null;
        }
        if (i <= 0 || i2 <= 0) {
            options = null;
        } else {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getPath(), options);
            options.inSampleSize = computeSampleSize(options, Math.min(i, i2), i * i2);
            options.inJustDecodeBounds = false;
            options.inInputShareable = true;
            options.inPurgeable = true;
        }
        try {
            return BitmapFactory.decodeFile(file.getPath(), options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getCacheTime() {
        return mCacheTime;
    }

    public static Bitmap getHttpBitmap(LuaContext luaContext, String str) throws IOException {
        int screenWidth;
        File file;
        String str2 = luaContext.getLuaExtDir("cache") + "/" + str.hashCode();
        File file2 = new File(str2);
        if (!file2.exists() || mCacheTime == -1 || System.currentTimeMillis() - file2.lastModified() >= mCacheTime) {
            new File(str2).delete();
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(120000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            boolean zCopyFile = LuaUtil.copyFile(inputStream, fileOutputStream);
            fileOutputStream.close();
            inputStream.close();
            if (!zCopyFile) {
                new File(str2).delete();
                throw new RuntimeException("LoadHttpBitmap Error.");
            }
            screenWidth = luaContext.getScreenWidth();
            file = new File(str2);
        } else {
            screenWidth = luaContext.getScreenWidth();
            file = new File(str2);
        }
        return decodeScale(screenWidth, file);
    }

    public static Bitmap getHttpBitmap(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return bitmapDecodeStream;
    }

    public static Bitmap getImageFromPath(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = computeSampleSize(options, -1, 62500);
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap getLocalBitmap(LuaContext luaContext, String str) {
        return decodeScale(luaContext.getScreenWidth(), new File(str));
    }

    public static Bitmap getLocalBitmap(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream);
        fileInputStream.close();
        return bitmapDecodeStream;
    }

    public static void removeBitmap(Bitmap bitmap) {
        for (Map.Entry<String, WeakReference<Bitmap>> entry : cache.entrySet()) {
            if (bitmap.equals(entry.getValue().get())) {
                cache.remove(entry.getKey());
                return;
            }
        }
    }

    public static void setCacheTime(long j) {
        mCacheTime = j;
    }
}

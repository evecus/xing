package com.androlua;

import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.androlua.GifDecoder;
import com.androlua.util.AsyncTaskX;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class LuaBitmapDrawable extends Drawable implements Runnable, LuaGcable {
    public static final int CENTER = 5;
    public static final int CENTER_CROP = 6;
    public static final int CENTER_INSIDE = 7;
    public static final int FIT_CENTER = 3;
    public static final int FIT_END = 4;
    public static final int FIT_START = 2;
    public static final int FIT_XY = 1;
    public static final int MATRIX = 0;
    private static long mCacheTime = 604800000;
    private Drawable mBitmapDrawable;
    private ColorFilter mColorFilter;
    private int mCurrentAnimationTime;
    private int mDelay;
    private int mDuration;
    private int mFillColor;
    private boolean mGc;
    private GifDecoder mGifDecoder;
    private GifDecoder mGifDecoder2;
    private GifDecoder.GifFrame mGifFrame;
    private Handler mHandler;
    private LoadingDrawable mLoadingDrawable;
    private LuaContext mLuaContext;
    private Movie mMovie;
    private long mMovieStart;
    private NineBitmapDrawable mNineBitmapDrawable;
    private int mScaleType;

    public LuaBitmapDrawable(LuaContext luaContext, String str) {
        this.mScaleType = 1;
        this.mLuaContext = luaContext;
        this.mLoadingDrawable = new LoadingDrawable(luaContext.getContext());
        if (str.toLowerCase().startsWith("http://") || str.toLowerCase().startsWith("https://")) {
            initHttp(luaContext, str);
        } else {
            init(str.startsWith("/") ? str : luaContext.getLuaPath(str));
        }
    }

    public LuaBitmapDrawable(LuaContext luaContext, String str, Drawable drawable) {
        this(luaContext, str);
        this.mBitmapDrawable = drawable;
    }

    public static long getCacheTime() {
        return mCacheTime;
    }

    public static String getHttpBitmap(LuaContext luaContext, String str) throws IOException {
        String str2 = luaContext.getLuaExtDir("cache") + "/" + str.hashCode();
        File file = new File(str2);
        if (!file.exists() || System.currentTimeMillis() - file.lastModified() >= mCacheTime) {
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
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init(String str) {
        try {
            GifDecoder gifDecoder = new GifDecoder(new FileInputStream(str), new GifDecoder.GifAction(this, str) { // from class: com.androlua.LuaBitmapDrawable.2
                public final LuaBitmapDrawable this$0;
                public final String val$path;

                {
                    this.this$0 = this;
                    this.val$path = str;
                }

                @Override // com.androlua.GifDecoder.GifAction
                public void parseOk(boolean z, int i) {
                    if (!z && i < 0) {
                        this.this$0.init2(this.val$path);
                    } else if (z && this.this$0.mGifDecoder2 == null && this.this$0.mGifDecoder.getFrameCount() > 1) {
                        LuaBitmapDrawable luaBitmapDrawable = this.this$0;
                        luaBitmapDrawable.mGifDecoder2 = luaBitmapDrawable.mGifDecoder;
                    }
                }
            });
            this.mGifDecoder = gifDecoder;
            gifDecoder.start();
        } catch (Exception e) {
            e.printStackTrace();
            init2(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init2(String str) {
        Handler handler;
        Runnable runnable;
        if (!str.isEmpty()) {
            Movie movie = this.mMovie;
            if (movie != null) {
                int iDuration = movie.duration();
                this.mDuration = iDuration;
                if (iDuration == 0) {
                    this.mDuration = 1000;
                }
            } else {
                try {
                    this.mNineBitmapDrawable = new NineBitmapDrawable(str);
                } catch (Exception e) {
                    try {
                        this.mBitmapDrawable = new BitmapDrawable(LuaBitmap.getLocalBitmap(this.mLuaContext, str));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (this.mMovie == null && this.mBitmapDrawable == null && this.mNineBitmapDrawable == null) {
                handler = new Handler();
                runnable = new Runnable(this) { // from class: com.androlua.LuaBitmapDrawable.4
                    public final LuaBitmapDrawable this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.this$0.mLoadingDrawable.setState(-1);
                    }
                };
            }
            invalidateSelf();
        }
        handler = new Handler();
        runnable = new Runnable(this) { // from class: com.androlua.LuaBitmapDrawable.3
            public final LuaBitmapDrawable this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$0.mLoadingDrawable.setState(-1);
            }
        };
        handler.postDelayed(runnable, 1000L);
        invalidateSelf();
    }

    private void initHttp(LuaContext luaContext, String str) {
        new AsyncTaskX<String, String, String>(this, luaContext, str) { // from class: com.androlua.LuaBitmapDrawable.1
            public final LuaBitmapDrawable this$0;
            public final LuaContext val$context;
            public final String val$path;

            {
                this.this$0 = this;
                this.val$context = luaContext;
                this.val$path = str;
            }

            @Override // com.androlua.util.AsyncTaskX
            public String doInBackground(String... strArr) {
                try {
                    return LuaBitmapDrawable.getHttpBitmap(this.val$context, this.val$path);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "";
                }
            }

            @Override // com.androlua.util.AsyncTaskX
            public void onPostExecute(String str2) {
                this.this$0.init(str2);
            }
        }.execute(new String[0]);
    }

    public static void setCacheTime(long j) {
        mCacheTime = j;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01b9  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(android.graphics.Canvas r11) {
        /*
            Method dump skipped, instruction units count: 508
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaBitmapDrawable.draw(android.graphics.Canvas):void");
    }

    public void finalize() {
        GifDecoder gifDecoder = this.mGifDecoder2;
        if (gifDecoder != null) {
            gifDecoder.free();
        }
    }

    @Override // com.androlua.LuaGcable
    public void gc() {
        GifDecoder gifDecoder = this.mGifDecoder2;
        if (gifDecoder != null) {
            gifDecoder.free();
        }
        Drawable drawable = this.mBitmapDrawable;
        if (drawable != null && (drawable instanceof BitmapDrawable)) {
            ((BitmapDrawable) drawable).getBitmap().recycle();
        }
        NineBitmapDrawable nineBitmapDrawable = this.mNineBitmapDrawable;
        if (nineBitmapDrawable != null) {
            nineBitmapDrawable.gc();
        }
        this.mGifDecoder2 = null;
        this.mBitmapDrawable = null;
        this.mNineBitmapDrawable = null;
        this.mLoadingDrawable.setState(-1);
        this.mGc = true;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Movie movie = this.mMovie;
        if (movie != null) {
            return movie.height();
        }
        Drawable drawable = this.mBitmapDrawable;
        if (drawable != null || (drawable = this.mNineBitmapDrawable) != null) {
            drawable.getIntrinsicHeight();
        }
        return super.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Movie movie = this.mMovie;
        if (movie != null) {
            return movie.width();
        }
        Drawable drawable = this.mBitmapDrawable;
        if (drawable != null || (drawable = this.mNineBitmapDrawable) != null) {
            drawable.getIntrinsicWidth();
        }
        return super.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // com.androlua.LuaGcable
    public boolean isGc() {
        return this.mGc;
    }

    @Override // java.lang.Runnable
    public void run() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mColorFilter = colorFilter;
    }

    public void setFillColor(int i) {
        if (i == this.mFillColor) {
            return;
        }
        this.mFillColor = i;
    }

    public void setScaleType(int i) {
        if (this.mScaleType != i) {
            this.mScaleType = i;
            invalidateSelf();
        }
    }
}

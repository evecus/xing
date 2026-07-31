package com.androlua;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.ArrayListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import java.io.IOException;
import java.util.HashMap;
import org.roam.Application;

/* JADX INFO: loaded from: classes.dex */
public class LuaArrayAdapter extends ArrayListAdapter {
    private LuaState L;
    private HashMap<String, Boolean> loaded;
    private LuaObject loadlayout;
    private Animation mAnimation;
    private LuaContext mContext;
    private Drawable mDraw;
    private Handler mHandler;
    private Resources mRes;
    private LuaObject mResource;

    public class AsyncLoader extends Thread {
        private LuaContext mContext;
        private String mPath;
        public final LuaArrayAdapter this$0;

        private AsyncLoader(LuaArrayAdapter luaArrayAdapter) {
            this.this$0 = luaArrayAdapter;
        }

        public Drawable getBitmap(LuaContext luaContext, String str) {
            this.mContext = luaContext;
            this.mPath = str;
            if ((str.toLowerCase().startsWith("http://") || str.toLowerCase().startsWith("https://")) && !LuaBitmap.checkCache(luaContext, str)) {
                if (!this.this$0.loaded.containsKey(this.mPath)) {
                    start();
                    this.this$0.loaded.put(this.mPath, Boolean.TRUE);
                }
                return new LoadingDrawable(this.mContext.getContext());
            }
            return new BitmapDrawable(this.this$0.mRes, LuaBitmap.getBitmap(luaContext, str));
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                LuaBitmap.getBitmap(this.mContext, this.mPath);
                this.this$0.mHandler.sendEmptyMessage(0);
            } catch (IOException e) {
                e.printStackTrace();
                this.mContext.sendError("AsyncLoader", e);
            }
        }
    }

    public LuaArrayAdapter(LuaContext luaContext, LuaObject luaObject) {
        this(luaContext, luaObject, new String[0]);
    }

    public LuaArrayAdapter(LuaContext luaContext, LuaObject luaObject, Object[] objArr) {
        super(luaContext.getContext(), 0, objArr);
        this.mHandler = new Handler(this) { // from class: com.androlua.LuaArrayAdapter.1
            public final LuaArrayAdapter this$0;

            {
                this.this$0 = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                this.this$0.notifyDataSetChanged();
            }
        };
        this.loaded = new HashMap<>();
        this.mContext = luaContext;
        this.mResource = luaObject;
        this.mRes = luaContext.getContext().getResources();
        LuaState luaState = luaContext.getLuaState();
        this.L = luaState;
        this.loadlayout = luaState.getLuaObject("loadlayout");
        this.L.newTable();
        this.loadlayout.call(this.mResource, this.L.getLuaObject(-1), AbsListView.class);
        this.L.pop(1);
    }

    public LuaArrayAdapter(Application application, LuaObject luaObject) {
        this(application.getLuaSupport(), luaObject, new String[0]);
    }

    public LuaArrayAdapter(Application application, LuaObject luaObject, Object[] objArr) {
        this(application.getLuaSupport(), luaObject, objArr);
    }

    private void setHelper(View view, Object obj) {
        Drawable bitmap;
        ViewGroup.LayoutParams layoutParams;
        if (view instanceof TextView) {
            ((TextView) view).setText(obj instanceof CharSequence ? (CharSequence) obj : obj.toString());
            return;
        }
        if (view instanceof ImageView) {
            try {
                ImageView imageView = (ImageView) view;
                if (obj instanceof Bitmap) {
                    bitmap = new BitmapDrawable(this.mRes, (Bitmap) obj);
                } else {
                    bitmap = obj instanceof String ? new AsyncLoader().getBitmap(this.mContext, (String) obj) : obj instanceof Drawable ? (Drawable) obj : obj instanceof Number ? this.mRes.getDrawable(((Number) obj).intValue()) : null;
                }
                imageView.setImageDrawable(bitmap);
                if (bitmap instanceof BitmapDrawable) {
                    Bitmap bitmap2 = ((BitmapDrawable) bitmap).getBitmap();
                    int width = bitmap2.getWidth();
                    int height = bitmap2.getHeight();
                    if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                        return;
                    } else {
                        layoutParams = new ViewGroup.LayoutParams(this.mContext.getScreenWidth(), (int) ((height * this.mContext.getScreenWidth()) / width));
                    }
                } else if (bitmap instanceof LoadingDrawable) {
                    layoutParams = new ViewGroup.LayoutParams(this.mContext.getScreenWidth(), this.mContext.getScreenWidth() / 4);
                } else {
                    if (!(bitmap instanceof Drawable)) {
                        return;
                    }
                    Rect bounds = bitmap.getBounds();
                    int iWidth = bounds.width();
                    int iHeight = bounds.height();
                    if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                        return;
                    } else {
                        layoutParams = new ViewGroup.LayoutParams(this.mContext.getScreenWidth(), (int) ((iHeight * this.mContext.getScreenWidth()) / iWidth));
                    }
                }
                imageView.setLayoutParams(layoutParams);
            } catch (Exception e) {
                Log.i("lua", e.getMessage());
            }
        }
    }

    public Animation getAnimation() {
        return this.mAnimation;
    }

    @Override // android.widget.ArrayListAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup);
    }

    @Override // android.widget.ArrayListAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            this.L.newTable();
            LuaObject luaObject = this.L.getLuaObject(-1);
            this.L.pop(1);
            try {
                view = (View) this.loadlayout.call(this.mResource, luaObject, AbsListView.class);
            } catch (LuaException e) {
                return new View(this.mContext.getContext());
            }
        }
        setHelper(view, getItem(i));
        Animation animation = this.mAnimation;
        if (animation != null) {
            view.startAnimation(animation);
        }
        return view;
    }

    public void setAnimation(Animation animation) {
        this.mAnimation = animation;
    }
}

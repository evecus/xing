package com.androlua;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaJavaAPI;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaTable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.roam.Application;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaMultiAdapter extends BaseAdapter {
    private LuaState L;
    private LuaFunction insert;
    private LuaFunction<View> loadLayout;
    private HashMap<String, Boolean> loaded;
    private HashMap<View, Animation> mAnimCache;
    private LuaTable<Integer, LuaFunction<Animation>> mAnimationUtil;
    private LuaContext mContext;
    private LuaTable<Integer, LuaTable<String, Object>> mData;
    private BitmapDrawable mDraw;
    private Handler mHandler;
    private LuaTable<Integer, LuaTable> mLayout;
    private boolean mNotifyOnChange;
    private Resources mRes;
    private HashMap<View, Boolean> mStyleCache;
    private LuaTable<String, Object> mTheme;
    private LuaFunction remove;
    private boolean updateing;

    public class AsyncLoader extends Thread {
        private LuaContext mContext;
        private String mPath;
        public final LuaMultiAdapter this$0;

        private AsyncLoader(LuaMultiAdapter luaMultiAdapter) {
            this.this$0 = luaMultiAdapter;
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
                this.mContext.sendError("AsyncLoader", e);
            }
        }
    }

    public LuaMultiAdapter(LuaContext luaContext, LuaTable luaTable) {
        this(luaContext, (LuaTable<Integer, LuaTable<String, Object>>) null, (LuaTable<Integer, LuaTable>) luaTable);
    }

    public LuaMultiAdapter(LuaContext luaContext, LuaTable<Integer, LuaTable<String, Object>> luaTable, LuaTable<Integer, LuaTable> luaTable2) {
        this.mAnimCache = new HashMap<>();
        this.mStyleCache = new HashMap<>();
        this.mNotifyOnChange = true;
        this.mHandler = new Handler(this) { // from class: com.androlua.LuaMultiAdapter.1
            public final LuaMultiAdapter this$0;

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
        this.mLayout = luaTable2;
        this.mRes = luaContext.getContext().getResources();
        LuaState luaState = luaContext.getLuaState();
        this.L = luaState;
        this.mData = luaTable == null ? new LuaTable<>(luaState) : luaTable;
        this.loadLayout = this.L.getLuaObject("loadlayout").getFunction();
        this.insert = this.L.getLuaObject("table").getField("insert").getFunction();
        this.remove = this.L.getLuaObject("table").getField("remove").getFunction();
        int length = this.mLayout.length();
        for (int i = 1; i <= length; i++) {
            this.L.newTable();
            this.loadLayout.call(this.mLayout.get(Integer.valueOf(i)), this.L.getLuaObject(-1), AbsListView.class);
            this.L.pop(1);
        }
    }

    public LuaMultiAdapter(Application application, LuaTable luaTable) {
        this(application.getLuaSupport(), (LuaTable<Integer, LuaTable<String, Object>>) null, (LuaTable<Integer, LuaTable>) luaTable);
    }

    public LuaMultiAdapter(Application application, LuaTable<Integer, LuaTable<String, Object>> luaTable, LuaTable<Integer, LuaTable> luaTable2) {
        this(application.getLuaSupport(), luaTable, luaTable2);
    }

    private int javaSetListener(Object obj, String str, Object obj2) throws LuaException {
        StringBuilder sbO = a.o("setOn");
        sbO.append(str.substring(2));
        sbO.append("Listener");
        for (Method method : LuaJavaAPI.getMethod(obj.getClass(), sbO.toString(), false)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].isInterface()) {
                this.L.newTable();
                this.L.pushObjectValue(obj2);
                this.L.setField(-2, str);
                try {
                    method.invoke(obj, this.L.getLuaObject(-1).createProxy(parameterTypes[0]));
                    return 1;
                } catch (Exception e) {
                    throw new LuaException(e);
                }
            }
        }
        return 0;
    }

    private int javaSetMethod(Object obj, String str, Object obj2) throws LuaException {
        if (Character.isLowerCase(str.charAt(0))) {
            str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
        String strJ = a.j("set", str);
        Class<?> cls = obj2.getClass();
        StringBuilder sb = new StringBuilder();
        for (Method method : LuaJavaAPI.getMethod(obj.getClass(), strJ, false)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1) {
                if (parameterTypes[0].isPrimitive()) {
                    try {
                        if ((obj2 instanceof Double) || (obj2 instanceof Float)) {
                            method.invoke(obj, LuaState.convertLuaNumber(Double.valueOf(((Number) obj2).doubleValue()), parameterTypes[0]));
                        } else if ((obj2 instanceof Long) || (obj2 instanceof Integer)) {
                            method.invoke(obj, LuaState.convertLuaNumber(Long.valueOf(((Number) obj2).longValue()), parameterTypes[0]));
                        } else if (obj2 instanceof Boolean) {
                            method.invoke(obj, (Boolean) obj2);
                        } else {
                            continue;
                        }
                        return 1;
                    } catch (Exception e) {
                        sb.append(e.getMessage());
                        sb.append("\n");
                    }
                } else {
                    if (parameterTypes[0].isAssignableFrom(cls)) {
                        method.invoke(obj, obj2);
                        return 1;
                    }
                    continue;
                }
            }
        }
        if (sb.length() <= 0) {
            throw new LuaException(a.k("Invalid setter ", str, " is not a method.\n"));
        }
        StringBuilder sbE = a.e("Invalid setter ", str, ". Invalid Parameters.\n");
        sbE.append(sb.toString());
        sbE.append(cls.toString());
        throw new LuaException(sbE.toString());
    }

    private int javaSetter(Object obj, String str, Object obj2) {
        return (str.length() > 2 && str.substring(0, 2).equals("on") && (obj2 instanceof LuaFunction)) ? javaSetListener(obj, str, obj2) : javaSetMethod(obj, str, obj2);
    }

    private void setFields(View view, LuaTable<String, Object> luaTable) {
        for (Map.Entry<String, Object> entry : luaTable.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.toLowerCase().equals("src")) {
                setHelper(view, value);
            } else {
                javaSetter(view, key, value);
            }
        }
    }

    private void setHelper(View view, Object obj) {
        ImageView imageView;
        Drawable bitmap;
        TextView textView;
        CharSequence string;
        try {
            if (obj instanceof LuaTable) {
                setFields(view, (LuaTable) obj);
                return;
            }
            if (view instanceof TextView) {
                if (obj instanceof CharSequence) {
                    textView = (TextView) view;
                    string = (CharSequence) obj;
                } else {
                    textView = (TextView) view;
                    string = obj.toString();
                }
                textView.setText(string);
                return;
            }
            if (view instanceof ImageView) {
                if (obj instanceof Bitmap) {
                    ((ImageView) view).setImageBitmap((Bitmap) obj);
                    return;
                }
                if (obj instanceof String) {
                    imageView = (ImageView) view;
                    bitmap = new AsyncLoader().getBitmap(this.mContext, (String) obj);
                } else {
                    if (!(obj instanceof Drawable)) {
                        if (obj instanceof Number) {
                            ((ImageView) view).setImageResource(((Number) obj).intValue());
                            return;
                        }
                        return;
                    }
                    imageView = (ImageView) view;
                    bitmap = (Drawable) obj;
                }
                imageView.setImageDrawable(bitmap);
            }
        } catch (Exception e) {
            this.mContext.sendError("setHelper", e);
        }
    }

    public void add(LuaTable<String, Object> luaTable) {
        this.insert.call(this.mData, luaTable);
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void addAll(LuaTable<Integer, LuaTable<String, Object>> luaTable) {
        int length = luaTable.length();
        for (int i = 1; i <= length; i++) {
            this.insert.call(this.mData, luaTable.get(Integer.valueOf(i)));
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.mData.clear();
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mData.length();
    }

    public LuaTable<Integer, LuaTable<String, Object>> getData() {
        return this.mData;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mData.get(Integer.valueOf(i + 1));
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i + 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        int iIntValue = ((Long) this.mData.get(Integer.valueOf(i + 1)).get("__type")).intValue() - 1;
        if (iIntValue < 0) {
            return 0;
        }
        return iIntValue;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        LuaObject luaObject;
        View viewCall;
        Animation animationCall;
        Exception e;
        int i2 = i + 1;
        int iIntValue = ((Long) this.mData.get(Integer.valueOf(i2)).get("__type")).intValue();
        if (iIntValue < 1) {
            iIntValue = 1;
        }
        if (view == null) {
            try {
                LuaTable luaTable = this.mLayout.get(Integer.valueOf(iIntValue));
                this.L.newTable();
                luaObject = this.L.getLuaObject(-1);
                this.L.pop(1);
                viewCall = this.loadLayout.call(luaTable, luaObject, AbsListView.class);
                viewCall.setTag(luaObject);
            } catch (LuaException e2) {
                return new View(this.mContext.getContext());
            }
        } else {
            luaObject = (LuaObject) view.getTag();
            viewCall = view;
        }
        LuaTable<String, Object> luaTable2 = this.mData.get(Integer.valueOf(i2));
        if (luaTable2 == null) {
            Log.i("lua", i + " is null");
        } else {
            boolean z = this.mStyleCache.get(viewCall) == null;
            if (z) {
                this.mStyleCache.put(viewCall, Boolean.TRUE);
            }
            for (Map.Entry<String, Object> entry : luaTable2.entrySet()) {
                try {
                    String key = entry.getKey();
                    if (!key.equals("type")) {
                        Object value = entry.getValue();
                        LuaObject field = luaObject.getField(key);
                        if (field.isJavaObject()) {
                            if (this.mTheme != null && z) {
                                setHelper((View) field.getObject(), this.mTheme.get(key));
                            }
                            setHelper((View) field.getObject(), value);
                        }
                    }
                } catch (Exception e3) {
                    Log.i("lua", e3.getMessage());
                }
            }
            if (!this.updateing && this.mAnimationUtil != null && view != null) {
                Animation animation = this.mAnimCache.get(view);
                if (animation == null) {
                    try {
                        animationCall = this.mAnimationUtil.get(Integer.valueOf(iIntValue)).call(new Object[0]);
                        try {
                            this.mAnimCache.put(view, animationCall);
                        } catch (Exception e4) {
                            e = e4;
                            this.mContext.sendError("setAnimation", e);
                        }
                    } catch (Exception e5) {
                        animationCall = animation;
                        e = e5;
                    }
                    animation = animationCall;
                }
                if (animation != null) {
                    viewCall.clearAnimation();
                    viewCall.startAnimation(animation);
                }
            }
        }
        return viewCall;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return this.mLayout.length();
    }

    public void insert(int i, LuaTable<String, Object> luaTable) {
        this.insert.call(this.mData, Integer.valueOf(i + 1), luaTable);
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (this.updateing) {
            return;
        }
        this.updateing = true;
        new Handler().postDelayed(new Runnable(this) { // from class: com.androlua.LuaMultiAdapter.2
            public final LuaMultiAdapter this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$0.updateing = false;
            }
        }, 500L);
    }

    public void remove(int i) {
        this.remove.call(this.mData, Integer.valueOf(i + 1));
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void setAnimation(LuaTable<Integer, LuaFunction<Animation>> luaTable) {
        setAnimationUtil(luaTable);
    }

    public void setAnimationUtil(LuaTable<Integer, LuaFunction<Animation>> luaTable) {
        this.mAnimCache.clear();
        this.mAnimationUtil = luaTable;
    }

    public void setNotifyOnChange(boolean z) {
        this.mNotifyOnChange = z;
        if (z) {
            notifyDataSetChanged();
        }
    }

    public void setStyle(LuaTable<String, Object> luaTable) {
        this.mStyleCache.clear();
        this.mTheme = luaTable;
    }
}

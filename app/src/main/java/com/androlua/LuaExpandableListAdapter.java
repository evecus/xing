package com.androlua;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaJavaAPI;
import com.luajava.LuaState;
import com.luajava.LuaTable;
import com.roamexplore.MainActivity;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaExpandableListAdapter extends BaseExpandableListAdapter {
    private LuaState L;
    private LuaFunction<?> insert;
    private HashMap<String, Boolean> loaded;
    private LuaFunction<View> loadlayout;
    private HashMap<View, Animation> mAnimCache;
    private LuaFunction<Animation> mAnimationUtil;
    private LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> mChildData;
    private LuaTable mChildLayout;
    private LuaContext mContext;
    private BitmapDrawable mDraw;
    private LuaTable<Integer, LuaTable<String, Object>> mGroupData;
    private LuaTable mGroupLayout;
    private Handler mHandler;
    private boolean mNotifyOnChange;
    private Resources mRes;
    private LuaFunction<?> remove;
    private boolean updateing;

    public class AsyncLoader extends Thread {
        private LuaContext mContext;
        private String mPath;
        public final LuaExpandableListAdapter this$0;

        private AsyncLoader(LuaExpandableListAdapter luaExpandableListAdapter) {
            this.this$0 = luaExpandableListAdapter;
        }

        public Drawable getBitmap(LuaContext luaContext, String str) {
            this.mContext = luaContext;
            this.mPath = str;
            if ((str.toLowerCase().startsWith("http://") || str.toLowerCase().startsWith("https://")) && !LuaBitmap.checkCache(luaContext, str)) {
                if (!this.this$0.loaded.containsKey(this.mPath)) {
                    start();
                    this.this$0.loaded.put(this.mPath, Boolean.TRUE);
                }
                return this.this$0.mDraw;
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

    public class GroupItem {
        private LuaTable<Integer, LuaTable<String, Object>> mData;
        public final LuaExpandableListAdapter this$0;

        public GroupItem(LuaExpandableListAdapter luaExpandableListAdapter, LuaTable<Integer, LuaTable<String, Object>> luaTable) {
            this.this$0 = luaExpandableListAdapter;
            this.mData = luaTable;
        }

        public void add(LuaTable<String, Object> luaTable) {
            LuaTable<Integer, LuaTable<String, Object>> luaTable2 = this.mData;
            luaTable2.put(Integer.valueOf(luaTable2.length() + 1), luaTable);
            if (this.this$0.mNotifyOnChange) {
                this.this$0.notifyDataSetChanged();
            }
        }

        public void clear() {
            this.mData.clear();
            if (this.this$0.mNotifyOnChange) {
                this.this$0.notifyDataSetChanged();
            }
        }

        public LuaTable<Integer, LuaTable<String, Object>> getData() {
            return this.mData;
        }

        public void insert(int i, LuaTable<String, Object> luaTable) {
            this.this$0.insert.call(this.mData, Integer.valueOf(i + 1), luaTable);
            if (this.this$0.mNotifyOnChange) {
                this.this$0.notifyDataSetChanged();
            }
        }

        public void remove(int i) {
            this.this$0.remove.call(this.mData, Integer.valueOf(i + 1));
            if (this.this$0.mNotifyOnChange) {
                this.this$0.notifyDataSetChanged();
            }
        }
    }

    public LuaExpandableListAdapter(LuaContext luaContext, LuaTable luaTable, LuaTable luaTable2) {
        this(luaContext, null, null, luaTable, luaTable2);
    }

    public LuaExpandableListAdapter(LuaContext luaContext, LuaTable<Integer, LuaTable<String, Object>> luaTable, LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> luaTable2, LuaTable luaTable3, LuaTable luaTable4) {
        this.mAnimCache = new HashMap<>();
        this.mHandler = new Handler(this) { // from class: com.androlua.LuaExpandableListAdapter.1
            public final LuaExpandableListAdapter this$0;

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
        this.L = luaContext.getLuaState();
        this.mRes = this.mContext.getContext().getResources();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mRes, getClass().getResourceAsStream("/res/drawable/icon.png"));
        this.mDraw = bitmapDrawable;
        bitmapDrawable.setColorFilter(-1996488705, PorterDuff.Mode.SRC_ATOP);
        this.mGroupLayout = luaTable3;
        this.mChildLayout = luaTable4;
        luaTable = luaTable == null ? new LuaTable<>(this.L) : luaTable;
        luaTable2 = luaTable2 == null ? new LuaTable<>(this.L) : luaTable2;
        this.mGroupData = luaTable;
        this.mChildData = luaTable2;
        this.loadlayout = this.L.getLuaObject("loadlayout").getFunction();
        this.insert = this.L.getLuaObject("table").getField("insert").getFunction();
        this.remove = this.L.getLuaObject("table").getField("remove").getFunction();
        this.L.newTable();
        this.loadlayout.call(this.mGroupLayout, this.L.getLuaObject(-1), AbsListView.class);
        this.loadlayout.call(this.mChildLayout, this.L.getLuaObject(-1), AbsListView.class);
        this.L.pop(1);
    }

    public LuaExpandableListAdapter(MainActivity mainActivity, LuaTable luaTable, LuaTable luaTable2) {
        this(mainActivity.getLuaSupport(), null, null, luaTable, luaTable2);
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
            try {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.toLowerCase().equals("src")) {
                    setHelper(view, value);
                } else {
                    javaSetter(view, key, value);
                }
            } catch (Exception e) {
                Log.i("lua", e.getMessage());
            }
        }
    }

    private void setHelper(View view, Object obj) {
        ImageView imageView;
        Drawable bitmap;
        if (obj instanceof LuaTable) {
            setFields(view, (LuaTable) obj);
            return;
        }
        if (view instanceof TextView) {
            ((TextView) view).setText(obj instanceof CharSequence ? (CharSequence) obj : obj.toString());
            return;
        }
        if (view instanceof ImageView) {
            try {
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
            } catch (Exception e) {
                Log.i("lua", e.getMessage());
            }
        }
    }

    public GroupItem add(LuaTable<String, Object> luaTable) {
        LuaTable<Integer, LuaTable<String, Object>> luaTable2 = this.mGroupData;
        luaTable2.put(Integer.valueOf(luaTable2.length() + 1), luaTable);
        LuaTable<Integer, LuaTable<String, Object>> luaTable3 = new LuaTable<>(this.L);
        this.mChildData.put(Integer.valueOf(this.mGroupData.length()), luaTable3);
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
        return new GroupItem(this, luaTable3);
    }

    public GroupItem add(LuaTable<String, Object> luaTable, LuaTable<Integer, LuaTable<String, Object>> luaTable2) {
        LuaTable<Integer, LuaTable<String, Object>> luaTable3 = this.mGroupData;
        luaTable3.put(Integer.valueOf(luaTable3.length() + 1), luaTable);
        this.mChildData.put(Integer.valueOf(this.mGroupData.length()), luaTable2);
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
        return new GroupItem(this, luaTable2);
    }

    public void clear() {
        this.mGroupData.clear();
        this.mChildData.clear();
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        return this.mChildData.get(Integer.valueOf(i + 1)).get(Integer.valueOf(i2 + 1));
    }

    public LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> getChildData() {
        return this.mChildData;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2 + 1;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        LuaTable luaTable;
        View viewCall;
        Animation animationCall;
        Exception e;
        if (view == null) {
            try {
                luaTable = new LuaTable(this.L);
                viewCall = this.loadlayout.call(this.mChildLayout, luaTable, AbsListView.class);
                viewCall.setTag(luaTable);
            } catch (LuaException e2) {
                return new View(this.mContext.getContext());
            }
        } else {
            luaTable = (LuaTable) view.getTag();
            viewCall = view;
        }
        LuaTable<String, Object> luaTable2 = this.mChildData.get(Integer.valueOf(i + 1)).get(Integer.valueOf(i2 + 1));
        if (luaTable2 == null) {
            Log.i("lua", i2 + " is null");
        } else {
            for (Map.Entry<String, Object> entry : luaTable2.entrySet()) {
                try {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    View view2 = (View) luaTable.get(key);
                    if (view2 != null) {
                        setHelper(view2, value);
                    }
                } catch (Exception e3) {
                    Log.i("lua", e3.getMessage());
                }
            }
            if (!this.updateing && this.mAnimationUtil != null && view != null) {
                Animation animation = this.mAnimCache.get(view);
                if (animation == null) {
                    try {
                        animationCall = this.mAnimationUtil.call(new Object[0]);
                        try {
                            this.mAnimCache.put(view, animationCall);
                        } catch (Exception e4) {
                            e = e4;
                            Log.i("lua", e.getMessage());
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

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        return this.mChildData.get(Integer.valueOf(i + 1)).length();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        return this.mGroupData.get(Integer.valueOf(i + 1));
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.mGroupData.length();
    }

    public LuaTable<Integer, LuaTable<String, Object>> getGroupData() {
        return this.mGroupData;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i + 1;
    }

    public GroupItem getGroupItem(int i) {
        return new GroupItem(this, this.mChildData.get(Integer.valueOf(i + 1)));
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        LuaTable luaTable;
        View viewCall;
        Animation animationCall;
        Exception e;
        if (view == null) {
            try {
                luaTable = new LuaTable(this.L);
                viewCall = this.loadlayout.call(this.mGroupLayout, luaTable, AbsListView.class);
                viewCall.setTag(luaTable);
            } catch (LuaException e2) {
                return new View(this.mContext.getContext());
            }
        } else {
            luaTable = (LuaTable) view.getTag();
            viewCall = view;
        }
        LuaTable<String, Object> luaTable2 = this.mGroupData.get(Integer.valueOf(i + 1));
        if (luaTable2 == null) {
            Log.i("lua", i + " is null");
        } else {
            for (Map.Entry<String, Object> entry : luaTable2.entrySet()) {
                try {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    View view2 = (View) luaTable.get(key);
                    if (view2 != null) {
                        setHelper(view2, value);
                    }
                } catch (Exception e3) {
                    Log.i("lua", e3.getMessage());
                }
            }
            if (!this.updateing && this.mAnimationUtil != null && view != null) {
                Animation animation = this.mAnimCache.get(view);
                if (animation == null) {
                    try {
                        animationCall = this.mAnimationUtil.call(new Object[0]);
                        try {
                            this.mAnimCache.put(view, animationCall);
                        } catch (Exception e4) {
                            e = e4;
                            Log.i("lua", e.getMessage());
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

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    public GroupItem insert(int i, LuaTable<String, Object> luaTable, LuaTable<Integer, LuaTable<String, Object>> luaTable2) {
        int i2 = i + 1;
        this.insert.call(this.mGroupData, Integer.valueOf(i2), luaTable);
        this.insert.call(this.mChildData, Integer.valueOf(i2), luaTable2);
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
        return new GroupItem(this, luaTable2);
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    public void remove(int i) {
        this.remove.call(this.mGroupData, Integer.valueOf(i + 1));
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void setAnimationUtil(LuaFunction<Animation> luaFunction) {
        this.mAnimCache.clear();
        this.mAnimationUtil = luaFunction;
    }

    public void setNotifyOnChange(boolean z) {
        this.mNotifyOnChange = z;
        if (z) {
            notifyDataSetChanged();
        }
    }
}

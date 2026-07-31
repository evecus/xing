package com.androlua;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.androlua.LuaRecyclerAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaJavaAPI;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaTable;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.roam.Application;
import org.roam.R;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaRecyclerAdapter extends RecyclerView.Adapter<LuaViewHolder> implements Filterable {
    private LuaState L;
    private LuaFunction<?> insert;
    private HashMap<String, Boolean> loaded;
    private LuaFunction<View> loadlayout;
    private AdapterInterface mAdapterInterface;
    private HashMap<View, Animation> mAnimCache;
    private LuaFunction<Animation> mAnimationUtil;
    private String mClickViewId;
    private LuaTable<Integer, LuaTable<String, Object>> mData;
    private Drawable mDraw;
    private ArrayFilter mFilter;
    private Application mFusionApp;
    private final Handler mHandler;
    private LuaTable mLayout;
    private LuaFunction mLuaFilter;
    private LuaContext mLuaSupport;
    private boolean mNotifyOnChange;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private CharSequence mPrefix;
    private Resources mRes;
    private HashMap<View, Boolean> mStyleCache;
    private LuaTable<String, Object> mTheme;
    private LuaFunction<?> remove;
    private boolean updateing;

    public interface AdapterInterface {
        void onBindViewHolder(LuaViewHolder luaViewHolder, int i);
    }

    public class ArrayFilter extends Filter {
        public final LuaRecyclerAdapter this$0;

        private ArrayFilter(LuaRecyclerAdapter luaRecyclerAdapter) {
            this.this$0 = luaRecyclerAdapter;
        }

        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            this.this$0.mPrefix = charSequence;
            if (this.this$0.mData == null) {
                return filterResults;
            }
            if (this.this$0.mLuaFilter != null) {
                this.this$0.mHandler.sendEmptyMessage(1);
                return null;
            }
            filterResults.values = this.this$0.mData;
            filterResults.count = this.this$0.mData.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        }
    }

    public static class LuaViewHolder extends RecyclerView.ViewHolder {
        public LuaObject tag;

        public LuaViewHolder(View view) {
            super(view);
        }

        public LuaObject getTag() {
            return this.tag;
        }

        public void setTag(LuaObject luaObject) {
            this.tag = luaObject;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(LuaRecyclerAdapter luaRecyclerAdapter, View view, View view2, int i);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(LuaRecyclerAdapter luaRecyclerAdapter, View view, View view2, int i);
    }

    public LuaRecyclerAdapter(Application application, LuaTable luaTable) {
        this(application, null, luaTable);
    }

    public LuaRecyclerAdapter(Application application, LuaTable<Integer, LuaTable<String, Object>> luaTable, LuaTable luaTable2) {
        this.loaded = new HashMap<>();
        this.mStyleCache = new HashMap<>();
        this.mAnimCache = new HashMap<>();
        this.mNotifyOnChange = true;
        this.mClickViewId = null;
        this.mHandler = new Handler(this) { // from class: com.androlua.LuaRecyclerAdapter.2
            public final LuaRecyclerAdapter this$0;

            {
                this.this$0 = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    this.this$0.notifyDataSetChanged();
                    return;
                }
                try {
                    LuaTable luaTable3 = new LuaTable(this.this$0.mData.getLuaState());
                    this.this$0.mLuaFilter.call(this.this$0.mData, luaTable3, this.this$0.mPrefix);
                    this.this$0.mData = luaTable3;
                    this.this$0.notifyDataSetChanged();
                } catch (LuaException e) {
                    e.printStackTrace();
                    this.this$0.mLuaSupport.sendError("performFiltering", e);
                }
            }
        };
        this.mFusionApp = application;
        LuaContext luaSupport = application.getLuaSupport();
        this.mLuaSupport = luaSupport;
        this.mLayout = luaTable2;
        this.mRes = luaSupport.getContext().getResources();
        this.mDraw = ContextCompat.getDrawable(application.getActivity(), R.drawable.r);
        LuaState luaState = this.mLuaSupport.getLuaState();
        this.L = luaState;
        this.mData = luaTable == null ? new LuaTable<>(luaState) : luaTable;
        this.loadlayout = this.L.getLuaObject("loadlayout").getFunction();
        this.insert = this.L.getLuaObject("table").getField("insert").getFunction();
        this.remove = this.L.getLuaObject("table").getField("remove").getFunction();
        this.L.newTable();
        this.loadlayout.call(this.mLayout, this.L.getLuaObject(-1), ViewGroup.class);
        this.L.pop(1);
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
        RequestBuilder<Drawable> requestBuilderLoad;
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
                ImageView imageView = (ImageView) view;
                if (obj instanceof Number) {
                    imageView.setImageResource(((Number) obj).intValue());
                    return;
                }
                if ((obj instanceof String) || (obj instanceof File)) {
                    String strValueOf = String.valueOf(obj);
                    if (strValueOf.startsWith("/") && this.mFusionApp.getLoader() != null) {
                        File file = new File(this.mLuaSupport.getFusionDir() + strValueOf);
                        File file2 = new File(this.mFusionApp.getLoader().getImagesDir(strValueOf));
                        if (file.exists()) {
                            strValueOf = file.getAbsolutePath();
                        } else if (file2.exists()) {
                            strValueOf = file2.getAbsolutePath();
                        }
                    }
                    requestBuilderLoad = Glide.with((FragmentActivity) this.mFusionApp.getActivity()).load(strValueOf);
                } else {
                    requestBuilderLoad = Glide.with(this.mLuaSupport.getContext()).load(obj);
                }
                requestBuilderLoad.into(imageView);
            }
        } catch (Exception e) {
            this.mLuaSupport.sendError("setHelper", e);
        }
    }

    public /* synthetic */ void a(LuaViewHolder luaViewHolder, View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(this, luaViewHolder.itemView, view, luaViewHolder.getAdapterPosition());
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

    public /* synthetic */ boolean b(LuaViewHolder luaViewHolder, View view) {
        OnItemLongClickListener onItemLongClickListener = this.mOnItemLongClickListener;
        return onItemLongClickListener != null && onItemLongClickListener.onItemLongClick(this, luaViewHolder.itemView, view, luaViewHolder.getAdapterPosition());
    }

    public void chageData() {
        super.notifyDataSetChanged();
        if (this.updateing) {
            return;
        }
        this.updateing = true;
        new Handler().postDelayed(new Runnable(this) { // from class: com.androlua.LuaRecyclerAdapter.1
            public final LuaRecyclerAdapter this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$0.updateing = false;
            }
        }, 500L);
    }

    public void clear() {
        this.mData.clear();
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void filter(CharSequence charSequence) {
        getFilter().filter(charSequence);
    }

    public LuaTable<Integer, LuaTable<String, Object>> getData() {
        return this.mData;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new ArrayFilter();
        }
        return this.mFilter;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.length();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public void insert(int i, LuaTable<String, Object> luaTable) {
        this.insert.call(this.mData, Integer.valueOf(i + 1), luaTable);
        if (this.mNotifyOnChange) {
            chageData();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final LuaViewHolder luaViewHolder, int i) {
        Animation animationCall;
        Exception e;
        View view = luaViewHolder.itemView;
        LuaObject tag = luaViewHolder.getTag();
        for (Map.Entry<String, Object> entry : this.mData.get(Integer.valueOf(i + 1)).entrySet()) {
            try {
                String key = entry.getKey();
                Object value = entry.getValue();
                LuaObject field = tag.getField(key);
                if (field.isJavaObject()) {
                    if (this.mTheme != null) {
                        setHelper((View) field.getObject(), this.mTheme.get(key));
                    }
                    setHelper((View) field.getObject(), value);
                }
            } catch (Exception e2) {
                Log.i("lua", e2.getMessage());
            }
        }
        if (this.mAnimationUtil != null && view != null) {
            Animation animation = this.mAnimCache.get(view);
            if (animation == null) {
                try {
                    animationCall = this.mAnimationUtil.call(new Object[0]);
                    try {
                        this.mAnimCache.put(view, animationCall);
                    } catch (Exception e3) {
                        e = e3;
                        this.mLuaSupport.sendError("setAnimation", e);
                    }
                } catch (Exception e4) {
                    animationCall = animation;
                    e = e4;
                }
                animation = animationCall;
            }
            if (animation != null) {
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
        String str = this.mClickViewId;
        Object obj = null;
        if (str != null) {
            try {
                LuaObject field2 = tag.getField(str);
                if (field2 != null) {
                    Object object = field2.getObject();
                    if (object instanceof View) {
                        obj = object;
                    }
                }
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        if (obj == null) {
            obj = luaViewHolder.itemView;
        }
        if (obj instanceof View) {
            View view2 = (View) obj;
            view2.setOnClickListener(new View.OnClickListener(this, luaViewHolder) { // from class: roam.a.c.a
                public final LuaRecyclerAdapter a;
                public final LuaRecyclerAdapter.LuaViewHolder b;

                {
                    this.a = this;
                    this.b = luaViewHolder;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    this.a.a(this.b, view3);
                }
            });
            view2.setOnLongClickListener(new View.OnLongClickListener(this, luaViewHolder) { // from class: roam.a.c.b
                public final LuaRecyclerAdapter a;
                public final LuaRecyclerAdapter.LuaViewHolder b;

                {
                    this.a = this;
                    this.b = luaViewHolder;
                }

                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view3) {
                    return this.a.b(this.b, view3);
                }
            });
        }
        AdapterInterface adapterInterface = this.mAdapterInterface;
        if (adapterInterface != null) {
            adapterInterface.onBindViewHolder(luaViewHolder, i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public LuaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LuaObject luaObject;
        View view;
        Exception e;
        Animation animationCall;
        try {
            this.L.newTable();
            luaObject = this.L.getLuaObject(-1);
            try {
                this.L.pop(1);
                view = this.loadlayout.call(this.mLayout, luaObject, ViewGroup.class);
                Log.d("fa2", "holder:" + luaObject.getClass().getSimpleName());
            } catch (LuaException e2) {
                view = new View(this.mLuaSupport.getContext());
            }
        } catch (LuaException e3) {
            luaObject = null;
        }
        if (this.mData.get(Integer.valueOf(i + 1)) == null) {
            Log.i("lua", i + " is null");
            return new LuaViewHolder(view);
        }
        if (this.mStyleCache.get(view) == null) {
            this.mStyleCache.put(view, Boolean.TRUE);
        }
        if (this.updateing) {
            return new LuaViewHolder(view);
        }
        if (this.mAnimationUtil != null && view != null) {
            Animation animation = this.mAnimCache.get(view);
            if (animation == null) {
                try {
                    animationCall = this.mAnimationUtil.call(new Object[0]);
                    try {
                        this.mAnimCache.put(view, animationCall);
                    } catch (Exception e4) {
                        e = e4;
                        this.mLuaSupport.sendError("setAnimation", e);
                    }
                } catch (Exception e5) {
                    e = e5;
                    animationCall = animation;
                }
                animation = animationCall;
            }
            if (animation != null) {
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
        LuaViewHolder luaViewHolder = new LuaViewHolder(view);
        luaViewHolder.setTag(luaObject);
        return luaViewHolder;
    }

    public void remove(int i) {
        this.remove.call(this.mData, Integer.valueOf(i + 1));
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void setAdapterInterface(AdapterInterface adapterInterface) {
        this.mAdapterInterface = adapterInterface;
    }

    public void setAnimation(LuaFunction<Animation> luaFunction) {
        setAnimationUtil(luaFunction);
    }

    public void setAnimationUtil(LuaFunction<Animation> luaFunction) {
        this.mAnimCache.clear();
        this.mAnimationUtil = luaFunction;
    }

    public void setClickViewId(String str) {
        this.mClickViewId = str;
    }

    public void setFilter(LuaFunction luaFunction) {
        this.mLuaFilter = luaFunction;
    }

    public void setNotifyOnChange(boolean z) {
        this.mNotifyOnChange = z;
        if (z) {
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setStyle(LuaTable<String, Object> luaTable) {
        this.mStyleCache.clear();
        this.mTheme = luaTable;
    }
}

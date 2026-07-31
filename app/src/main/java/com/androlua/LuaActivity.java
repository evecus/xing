package com.androlua;

import android.R;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.FileProvider;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ArrayListAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.androlua.LuaBroadcastReceiver;
import com.androlua.LuaService;
import com.androlua.Ticker;
import com.baidu.mobstat.Config;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import dalvik.system.DexClassLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.roam.loader.Loader;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaActivity extends AppCompatActivity implements LuaBroadcastReceiver.OnReceiveListener, LuaContext {
    private static final String ARG = "arg";
    private static final String DATA = "data";
    private static final String NAME = "name";
    private static String sKey;
    private LuaState L;
    private ArrayListAdapter<String> adapter;
    private String fusionDir;
    private Handler handler;
    private boolean isSetViewed;
    private boolean isUpdata;
    private long lastShow;
    private LinearLayout layout;
    private String libDir;
    private ListView list;
    private String localDir;
    private String luaCpath;
    private String luaDir;
    private String luaExtDir;
    private String luaLpath;
    private String luaMdDir;
    private String luaPath;
    private int mHeight;
    private LuaDexLoader mLuaDexLoader;
    private LuaObject mOnKeyDown;
    private LuaObject mOnKeyLongPress;
    private LuaObject mOnKeyShortcut;
    private LuaObject mOnKeyUp;
    private LuaObject mOnTouchEvent;
    private LuaBroadcastReceiver mReceiver;
    private LuaResources mResources;
    private Resources.Theme mTheme;
    private int mWidth;
    private String odexDir;
    private Menu optionsMenu;
    private TextView status;
    private Toast toast;
    private static ArrayList<String> prjCache = new ArrayList<>();
    private static final HashMap<String, LuaActivity> sLuaActivityMap = new HashMap<>();
    private StringBuilder toastbuilder = new StringBuilder();
    private Boolean isCreate = Boolean.FALSE;
    private boolean mDebug = true;
    private ArrayList<LuaGcable> gclist = new ArrayList<>();
    private String pageName = "pages/main";

    public class MainHandler extends Handler {
        public final LuaActivity this$0;

        public MainHandler(LuaActivity luaActivity) {
            this.this$0 = luaActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                String string = message.getData().getString(LuaActivity.DATA);
                if (this.this$0.mDebug) {
                    this.this$0.showToast(string);
                }
                this.this$0.status.append(string + "\n");
                this.this$0.adapter.add(string);
                return;
            }
            if (i == 1) {
                Bundle data = message.getData();
                this.this$0.setField(data.getString(LuaActivity.DATA), ((Object[]) data.getSerializable("args"))[0]);
            } else if (i == 2) {
                this.this$0.runFunction(message.getData().getString(LuaActivity.DATA), new Object[0]);
            } else {
                if (i != 3) {
                    return;
                }
                this.this$0.runFunction(message.getData().getString(LuaActivity.DATA), (Object[]) message.getData().getSerializable("args"));
            }
        }
    }

    public static String errorReason(int i) {
        switch (i) {
            case 1:
                return "Yield error";
            case 2:
                return "Runtime error";
            case 3:
                return "Syntax error";
            case 4:
                return "Out of memory";
            case 5:
                return "GC error";
            case 6:
                return "error error";
            default:
                return a.h("Unknown error ", i);
        }
    }

    public static LuaActivity getActivity(String str) {
        return sLuaActivityMap.get(str);
    }

    private String getType(File file) {
        int iLastIndexOf = file.getName().lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.getName().substring(iLastIndexOf + 1));
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
        }
        return "application/octet-stream";
    }

    private void initENV() {
        if (new File(a.m(new StringBuilder(), this.luaDir, "/init.lua")).exists()) {
            try {
                int iLloadFile = this.L.LloadFile(this.luaDir + "/init.lua");
                if (iLloadFile == 0) {
                    this.L.newTable();
                    LuaObject luaObject = this.L.getLuaObject(-1);
                    this.L.setUpValue(-2, 1);
                    int iPcall = this.L.pcall(0, 0, 0);
                    if (iPcall == 0) {
                        if (sKey == null) {
                            LuaObject field = luaObject.getField("app_key");
                            if (field.isString()) {
                                sKey = field.toString();
                            }
                            luaObject.getField("app_channel");
                        }
                        LuaObject field2 = luaObject.getField("appname");
                        if (field2.isString()) {
                            setTitle(field2.getString());
                        }
                        LuaObject field3 = luaObject.getField("app_name");
                        if (field3.isString()) {
                            setTitle(field3.getString());
                        }
                        LuaObject field4 = luaObject.getField("debugmode");
                        if (field4.isBoolean()) {
                            this.mDebug = field4.getBoolean();
                        }
                        LuaObject field5 = luaObject.getField("debug_mode");
                        if (field5.isBoolean()) {
                            this.mDebug = field5.getBoolean();
                        }
                        LuaObject field6 = luaObject.getField(Loader.THEME_DIR);
                        if (field6.isNumber()) {
                            setTheme((int) field6.getInteger());
                            return;
                        } else {
                            if (field6.isString()) {
                                setTheme(R.style.class.getField(field6.getString()).getInt(null));
                                return;
                            }
                            return;
                        }
                    }
                    iLloadFile = iPcall;
                }
                throw new LuaException(errorReason(iLloadFile) + ": " + this.L.toString(-1));
            } catch (Exception e) {
                sendMessage(e.getMessage());
            }
        }
    }

    private void initLua() {
        LuaState luaStateNewLuaState = LuaStateFactory.newLuaState();
        this.L = luaStateNewLuaState;
        luaStateNewLuaState.openLibs();
        this.L.pushJavaObject(this);
        this.L.setGlobal(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
        this.L.getGlobal(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
        this.L.setGlobal("this");
        this.L.pushContext(this);
        this.L.getGlobal("luajava");
        this.L.pushString(this.luaExtDir);
        this.L.setField(-2, "luaextdir");
        this.L.pushString(this.luaDir);
        this.L.setField(-2, "luadir");
        this.L.pushString(this.luaPath);
        this.L.setField(-2, "luapath");
        this.L.pop(1);
        initENV();
        new LuaPrint(this, this.L).register("print");
        this.L.getGlobal("package");
        this.L.pushString(this.luaLpath);
        this.L.setField(-2, Config.FEED_LIST_ITEM_PATH);
        this.L.pushString(this.luaCpath);
        this.L.setField(-2, "cpath");
        this.L.pop(1);
        new JavaFunction(this, this.L) { // from class: com.androlua.LuaActivity.4
            public final LuaActivity this$0;

            {
                this.this$0 = this;
            }

            @Override // com.luajava.JavaFunction
            public int execute() {
                ((LuaThread) ((JavaFunction) this).L.toJavaObject(2)).set(((JavaFunction) this).L.toString(3), ((JavaFunction) this).L.toJavaObject(4));
                return 0;
            }
        }.register("set");
        new JavaFunction(this, this.L) { // from class: com.androlua.LuaActivity.5
            public final LuaActivity this$0;

            {
                this.this$0 = this;
            }

            @Override // com.luajava.JavaFunction
            public int execute() {
                LuaThread luaThread = (LuaThread) ((JavaFunction) this).L.toJavaObject(2);
                int top = ((JavaFunction) this).L.getTop();
                if (top <= 3) {
                    if (top != 3) {
                        return 0;
                    }
                    luaThread.call(((JavaFunction) this).L.toString(3));
                    return 0;
                }
                Object[] objArr = new Object[top - 3];
                for (int i = 4; i <= top; i++) {
                    objArr[i - 4] = ((JavaFunction) this).L.toJavaObject(i);
                }
                luaThread.call(((JavaFunction) this).L.toString(3), objArr);
                return 0;
            }
        }.register(NotificationCompat.CATEGORY_CALL);
    }

    private static byte[] readAll(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        byte[] bArr = new byte[4096];
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

    /* JADX INFO: Access modifiers changed from: private */
    public void setField(String str, Object obj) {
        synchronized (this.L) {
            try {
                this.L.pushObjectValue(obj);
                this.L.setGlobal(str);
            } catch (LuaException e) {
                sendError("runFunction", e);
            }
        }
    }

    public void assetsToSD(String str, String str2) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        InputStream inputStreamOpen = getAssets().open(str);
        byte[] bArr = new byte[4096];
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

    public boolean bindService(int i) {
        return bindService(new ServiceConnection(this) { // from class: com.androlua.LuaActivity.2
            public final LuaActivity this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                this.this$0.runFunction("onServiceConnected", componentName, ((LuaService.LuaBinder) iBinder).getService());
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                this.this$0.runFunction("onServiceDisconnected", componentName);
            }
        }, i);
    }

    public boolean bindService(ServiceConnection serviceConnection, int i) {
        Intent intent = new Intent(this, (Class<?>) LuaService.class);
        intent.putExtra("luaDir", this.luaDir);
        intent.putExtra("luaPath", this.luaPath);
        return super.bindService(intent, serviceConnection, i);
    }

    public void call(String str) {
        push(2, str);
    }

    @Override // com.androlua.LuaContext
    public void call(String str, Object... objArr) {
        if (objArr.length == 0) {
            push(2, str);
        } else {
            push(3, str, objArr);
        }
    }

    public Object doAsset(String str, Object... objArr) {
        Exception e;
        int i = 0;
        try {
            byte[] asset = readAsset(str);
            this.L.setTop(0);
            int iLloadBuffer = this.L.LloadBuffer(asset, str);
            if (iLloadBuffer == 0) {
                try {
                    this.L.getGlobal("debug");
                    this.L.getField(-1, "traceback");
                    this.L.remove(-2);
                    this.L.insert(-2);
                    int length = objArr.length;
                    for (Object obj : objArr) {
                        this.L.pushObjectValue(obj);
                    }
                    iLloadBuffer = this.L.pcall(length, 0, (-2) - length);
                    if (iLloadBuffer == 0) {
                        return this.L.toJavaObject(-1);
                    }
                } catch (Exception e2) {
                    e = e2;
                    i = iLloadBuffer;
                    setTitle(errorReason(i));
                    setContentView(this.layout);
                    sendMessage(e.getMessage());
                    return null;
                }
            }
            throw new LuaException(errorReason(iLloadBuffer) + ": " + this.L.toString(-1));
        } catch (Exception e3) {
            e = e3;
        }
    }

    @Override // com.androlua.LuaContext
    public Object doFile(String str) {
        return doFile(str, new Object[0]);
    }

    @Override // com.androlua.LuaContext
    public Object doFile(String str, Object[] objArr) {
        LuaException e;
        int iLloadFile;
        int i;
        int iIndexOf;
        try {
            if (str.charAt(0) != '/') {
                str = this.luaDir + "/" + str;
            }
            this.L.setTop(0);
            iLloadFile = this.L.LloadFile(str);
            if (iLloadFile == 0) {
                try {
                    this.L.getGlobal("debug");
                    this.L.getField(-1, "traceback");
                    this.L.remove(-2);
                    this.L.insert(-2);
                    int length = objArr.length;
                    for (Object obj : objArr) {
                        this.L.pushObjectValue(obj);
                    }
                    iLloadFile = this.L.pcall(length, 1, (-2) - length);
                    if (iLloadFile == 0) {
                        return this.L.toJavaObject(-1);
                    }
                } catch (LuaException e2) {
                    e = e2;
                    setTitle(errorReason(iLloadFile));
                    setContentView(this.layout);
                    sendMessage(e.getMessage());
                    String message = e.getMessage();
                    int iIndexOf2 = message.indexOf("android.permission.");
                    if (iIndexOf2 > 0 && (iIndexOf = message.indexOf(".", (i = iIndexOf2 + 19))) > i) {
                        String strSubstring = message.substring(i, iIndexOf);
                        this.L.getGlobal("require");
                        this.L.pushString("permission");
                        this.L.pcall(1, 0, 0);
                        this.L.getGlobal("permission_info");
                        this.L.getField(-1, strSubstring);
                        if (this.L.isString(-1)) {
                            StringBuilder sbD = a.d(strSubstring, " (");
                            sbD.append(this.L.toString(-1));
                            sbD.append(")");
                            strSubstring = sbD.toString();
                        }
                        sendMessage("权限错误: " + strSubstring);
                    }
                    return null;
                }
            }
            Intent intent = new Intent();
            intent.putExtra(DATA, this.L.toString(-1));
            setResult(iLloadFile, intent);
            throw new LuaException(errorReason(iLloadFile) + ": " + this.L.toString(-1));
        } catch (LuaException e3) {
            e = e3;
            iLloadFile = 0;
        }
    }

    public Object doString(String str, Object... objArr) {
        try {
            this.L.setTop(0);
            int iLloadString = this.L.LloadString(str);
            if (iLloadString == 0) {
                this.L.getGlobal("debug");
                this.L.getField(-1, "traceback");
                this.L.remove(-2);
                this.L.insert(-2);
                int length = objArr.length;
                for (Object obj : objArr) {
                    this.L.pushObjectValue(obj);
                }
                iLloadString = this.L.pcall(length, 1, (-2) - length);
                if (iLloadString == 0) {
                    return this.L.toJavaObject(-1);
                }
            }
            throw new LuaException(errorReason(iLloadString) + ": " + this.L.toString(-1));
        } catch (LuaException e) {
            sendMessage(e.getMessage());
            return null;
        }
    }

    public void finish(boolean z) {
        Intent intent;
        if (!z || (intent = getIntent()) == null || (intent.getFlags() & 524288) == 0) {
            super.finish();
        } else {
            finishAndRemoveTask();
        }
    }

    public Object get(String str) {
        Object javaObject;
        synchronized (this.L) {
            this.L.getGlobal(str);
            javaObject = this.L.toJavaObject(-1);
        }
        return javaObject;
    }

    public Object getArg(int i) {
        Object[] objArr = (Object[]) getIntent().getSerializableExtra(ARG);
        if (objArr == null || objArr.length >= i) {
            return null;
        }
        return objArr[i];
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        LuaDexLoader luaDexLoader = this.mLuaDexLoader;
        return (luaDexLoader == null || luaDexLoader.getAssets() == null) ? super.getAssets() : this.mLuaDexLoader.getAssets();
    }

    @Override // com.androlua.LuaContext
    public Context getContext() {
        return this;
    }

    public View getDecorView() {
        return getWindow().getDecorView();
    }

    @Override // com.androlua.LuaContext
    public String getFusionDir() {
        return this.fusionDir;
    }

    @Override // com.androlua.LuaContext
    public Map getGlobalData() {
        return ((LuaApplication) getApplication()).getGlobalData();
    }

    public HashMap<String, String> getLibrarys() {
        return this.mLuaDexLoader.getLibrarys();
    }

    public String getLocalDir() {
        return this.localDir;
    }

    @Override // com.androlua.LuaContext
    public String getLuaCpath() {
        return this.luaCpath;
    }

    @Override // com.androlua.LuaContext
    public LuaDexLoader getLuaDexLoader() {
        return this.mLuaDexLoader;
    }

    @Override // com.androlua.LuaContext
    public String getLuaDir() {
        return this.luaDir;
    }

    @Override // com.androlua.LuaContext
    public String getLuaDir(String str) {
        File file = new File(this.luaDir + "/" + str);
        if (file.exists() || file.mkdirs()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtDir() {
        return this.luaExtDir;
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtDir(String str) {
        File file = new File(getLuaExtDir(), str);
        if (file.exists() || file.mkdirs()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtPath(String str) {
        return new File(getLuaExtDir(), str).getAbsolutePath();
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtPath(String str, String str2) {
        return new File(getLuaExtDir(str), str2).getAbsolutePath();
    }

    @Override // com.androlua.LuaContext
    public String getLuaLpath() {
        return this.luaLpath;
    }

    @Override // com.androlua.LuaContext
    public String getLuaPath() {
        Uri data = getIntent().getData();
        if (data == null) {
            return null;
        }
        String path = data.getPath();
        if (!new File(path).exists() && new File(getLuaPath(path)).exists()) {
            path = getLuaPath(path);
        }
        this.luaPath = path;
        File file = new File(path);
        this.luaDir = new File(this.luaPath).getParent();
        if (!file.getName().equals(LuaBaseActivity.MAIN_LUA) || !new File(this.luaDir, "init.lua").exists()) {
            for (String parent = this.luaDir; parent != null; parent = new File(parent).getParent()) {
                if (prjCache.contains(parent)) {
                    this.luaDir = parent;
                    return path;
                }
                if (new File(parent, LuaBaseActivity.MAIN_LUA).exists() && new File(parent, "init.lua").exists()) {
                    this.luaDir = parent;
                    if (prjCache.contains(parent)) {
                        return path;
                    }
                }
            }
            return path;
        }
        if (prjCache.contains(this.luaDir)) {
            return path;
        }
        prjCache.add(this.luaDir);
        return path;
    }

    @Override // com.androlua.LuaContext
    public String getLuaPath(String str) {
        return new File(getLuaDir(), str).getAbsolutePath();
    }

    @Override // com.androlua.LuaContext
    public String getLuaPath(String str, String str2) {
        return new File(getLuaDir(str), str2).getAbsolutePath();
    }

    public LuaResources getLuaResources() {
        Resources resources = super.getResources();
        LuaDexLoader luaDexLoader = this.mLuaDexLoader;
        if (luaDexLoader != null && luaDexLoader.getResources() != null) {
            resources = this.mLuaDexLoader.getResources();
        }
        LuaResources luaResources = new LuaResources(getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.mResources = luaResources;
        luaResources.setSuperResources(resources);
        return this.mResources;
    }

    @Override // com.androlua.LuaContext
    public LuaState getLuaState() {
        return this.L;
    }

    public Menu getOptionsMenu() {
        return this.optionsMenu;
    }

    public String getPathFromUri(Uri uri) {
        Cursor cursorQuery;
        int columnIndexOrThrow;
        if (uri != null) {
            String scheme = uri.getScheme();
            scheme.hashCode();
            if (scheme.equals("file")) {
                return uri.getPath();
            }
            if (scheme.equals("content") && (cursorQuery = getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) != null && (columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow(getPackageName())) >= 0) {
                String string = cursorQuery.getString(columnIndexOrThrow);
                cursorQuery.moveToFirst();
                cursorQuery.close();
                return string;
            }
        }
        return null;
    }

    @Override // com.androlua.LuaContext
    public String getProjectDir() {
        return getLuaDir();
    }

    public String getQuery(String str) {
        Uri data = getIntent().getData();
        if (data == null) {
            return null;
        }
        return data.getQueryParameter(str);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        LuaDexLoader luaDexLoader = this.mLuaDexLoader;
        if (luaDexLoader != null && luaDexLoader.getResources() != null) {
            return this.mLuaDexLoader.getResources();
        }
        LuaResources luaResources = this.mResources;
        return luaResources == null ? super.getResources() : luaResources;
    }

    @Override // com.androlua.LuaContext
    public int getScreenHeight() {
        return this.mHeight;
    }

    @Override // com.androlua.LuaContext
    public int getScreenWidth() {
        return this.mWidth;
    }

    @Override // com.androlua.LuaContext
    public Object getSharedData(String str) {
        return LuaApplication.getInstance().getSharedData(str);
    }

    @Override // com.androlua.LuaContext
    public Object getSharedData(String str, Object obj) {
        return LuaApplication.getInstance().getSharedData(str, obj);
    }

    public Resources getSuperResources() {
        return super.getResources();
    }

    public Uri getUriForFile(File file) {
        return FileProvider.getUriForFile(this, getPackageName(), file);
    }

    public Uri getUriForPath(String str) {
        return FileProvider.getUriForFile(this, getPackageName(), new File(str));
    }

    public void initMain() {
        prjCache.add(getLocalDir());
    }

    public void installApk(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        File file = new File(str);
        intent.setFlags(1);
        intent.setDataAndType(getUriForFile(file), getType(file));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public DexClassLoader loadApp(String str) {
        return this.mLuaDexLoader.loadApp(str);
    }

    public Bitmap loadBitmap(String str) {
        return LuaBitmap.getBitmap(this, str);
    }

    public DexClassLoader loadDex(String str) {
        return this.mLuaDexLoader.loadDex(str);
    }

    @Override // com.androlua.LuaContext
    public Object loadLib(String str) throws LuaException {
        int iIndexOf = str.indexOf(".");
        String strSubstring = iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
        if (!new File(a.n(new StringBuilder(), this.libDir, "/lib", strSubstring, ".so")).exists()) {
            if (!new File(a.n(new StringBuilder(), this.luaDir, "/lib", strSubstring, ".so")).exists()) {
                throw new LuaException(a.j("can not find lib ", str));
            }
            LuaUtil.copyFile(a.n(new StringBuilder(), this.luaDir, "/lib", strSubstring, ".so"), a.n(new StringBuilder(), this.libDir, "/lib", strSubstring, ".so"));
        }
        return this.L.getLuaObject("require").call(str);
    }

    public void loadResources(String str) {
        this.mLuaDexLoader.loadResources(str);
    }

    public void newActivity(int i, String str) throws FileNotFoundException {
        newActivity(i, str, new Object[0]);
    }

    public void newActivity(int i, String str, int i2, int i3) throws FileNotFoundException {
        newActivity(i, str, i2, i3, new Object[0]);
    }

    public void newActivity(int i, String str, int i2, int i3, boolean z) throws FileNotFoundException {
        newActivity(i, str, i2, i3, null, z);
    }

    public void newActivity(int i, String str, int i2, int i3, Object... objArr) throws FileNotFoundException {
        newActivity(i, str, i2, i3, objArr, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void newActivity(int i, String str, int i2, int i3, Object[] objArr, boolean z) throws FileNotFoundException {
        Intent intent = new Intent(this, (Class<?>) LuaActivity.class);
        if (z) {
            intent = new Intent(this, (Class<?>) LuaActivityX.class);
        }
        intent.putExtra("name", str);
        if (str.charAt(0) != '/') {
            str = this.luaDir + "/" + str;
        }
        File file = new File(str);
        if (file.isDirectory() && new File(a.j(str, "/main.lua")).exists()) {
            str = a.j(str, "/main.lua");
        } else if ((file.isDirectory() || !file.exists()) && !str.endsWith(".lua")) {
            str = a.j(str, ".lua");
        }
        if (!new File(str).exists()) {
            throw new FileNotFoundException(str);
        }
        intent.setData(Uri.parse("file://" + str));
        if (z) {
            intent.addFlags(524288);
            intent.addFlags(134217728);
        }
        if (objArr != 0) {
            intent.putExtra(ARG, (Serializable) objArr);
        }
        if (z) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, i);
        }
        overridePendingTransition(i2, i3);
    }

    public void newActivity(int i, String str, boolean z) throws FileNotFoundException {
        newActivity(i, str, (Object[]) null, z);
    }

    public void newActivity(int i, String str, Object... objArr) throws FileNotFoundException {
        newActivity(i, str, objArr, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void newActivity(int i, String str, Object[] objArr, boolean z) throws FileNotFoundException {
        Intent intent = new Intent(this, (Class<?>) LuaActivity.class);
        if (z) {
            intent = new Intent(this, (Class<?>) LuaActivityX.class);
        }
        intent.putExtra("name", str);
        if (str.charAt(0) != '/') {
            str = this.luaDir + "/" + str;
        }
        File file = new File(str);
        if (file.isDirectory() && new File(a.j(str, "/main.lua")).exists()) {
            str = a.j(str, "/main.lua");
        } else if ((file.isDirectory() || !file.exists()) && !str.endsWith(".lua")) {
            str = a.j(str, ".lua");
        }
        if (!new File(str).exists()) {
            throw new FileNotFoundException(str);
        }
        if (z) {
            intent.addFlags(524288);
            intent.addFlags(134217728);
        }
        intent.setData(Uri.parse("file://" + str));
        if (objArr != 0) {
            intent.putExtra(ARG, (Serializable) objArr);
        }
        if (z) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, i);
        }
    }

    public void newActivity(String str) throws FileNotFoundException {
        newActivity(1, str, new Object[0]);
    }

    public void newActivity(String str, int i, int i2) throws FileNotFoundException {
        newActivity(1, str, i, i2, new Object[0]);
    }

    public void newActivity(String str, int i, int i2, boolean z) throws FileNotFoundException {
        newActivity(1, str, i, i2, null, z);
    }

    public void newActivity(String str, int i, int i2, Object... objArr) throws FileNotFoundException {
        newActivity(1, str, i, i2, objArr);
    }

    public void newActivity(String str, int i, int i2, Object[] objArr, boolean z) throws FileNotFoundException {
        newActivity(1, str, i, i2, objArr, z);
    }

    public void newActivity(String str, boolean z) throws FileNotFoundException {
        newActivity(1, str, (Object[]) null, z);
    }

    public void newActivity(String str, Object... objArr) throws FileNotFoundException {
        newActivity(1, str, objArr);
    }

    public void newActivity(String str, Object[] objArr, boolean z) throws FileNotFoundException {
        newActivity(1, str, objArr, z);
    }

    public LuaAsyncTask newTask(LuaObject luaObject) {
        return newTask(luaObject, null, null);
    }

    public LuaAsyncTask newTask(LuaObject luaObject, LuaObject luaObject2) {
        return newTask(luaObject, null, luaObject2);
    }

    public LuaAsyncTask newTask(LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3) {
        return new LuaAsyncTask(this, luaObject, luaObject2, luaObject3);
    }

    public LuaThread newThread(LuaObject luaObject) {
        return newThread(luaObject, new Object[0]);
    }

    public LuaThread newThread(LuaObject luaObject, Object... objArr) {
        return new LuaThread((LuaContext) this, luaObject, true, objArr);
    }

    public LuaTimer newTimer(LuaObject luaObject) {
        return newTimer(luaObject, new Object[0]);
    }

    public LuaTimer newTimer(LuaObject luaObject, Object... objArr) {
        return new LuaTimer(this, luaObject, objArr);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        String stringExtra;
        if (intent != null && (stringExtra = intent.getStringExtra("name")) != null) {
            Object[] objArr = (Object[]) intent.getSerializableExtra(DATA);
            if (objArr == null) {
                runFunction("onResult", stringExtra);
            } else {
                Object[] objArr2 = new Object[objArr.length + 1];
                int i3 = 0;
                objArr2[0] = stringExtra;
                while (i3 < objArr.length) {
                    int i4 = i3 + 1;
                    objArr2[i4] = objArr[i3];
                    i3 = i4;
                }
                Object objRunFunction = runFunction("onResult", objArr2);
                if (objRunFunction != null && objRunFunction.getClass() == Boolean.class && ((Boolean) objRunFunction).booleanValue()) {
                    return;
                }
            }
        }
        runFunction("onActivityResult", Integer.valueOf(i), Integer.valueOf(i2), intent);
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        WindowManager windowManager = (WindowManager) getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.mWidth = displayMetrics.widthPixels;
        this.mHeight = displayMetrics.heightPixels;
        runFunction("onConfigurationChanged", configuration);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        super.onContentChanged();
        this.isSetViewed = true;
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        runFunction("onContextItemSelected", menuItem);
        return super.onContextItemSelected(menuItem);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setTheme(R.style.Theme.Holo.Light.NoActionBar);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        super.onCreate(null);
        WindowManager windowManager = (WindowManager) getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.mWidth = displayMetrics.widthPixels;
        this.mHeight = displayMetrics.heightPixels;
        this.layout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        TextView textView = new TextView(this);
        this.status = textView;
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        scrollView.addView(this.status, new ViewGroup.LayoutParams(-1, -2));
        this.status.setText("");
        this.status.setTextIsSelectable(true);
        ListView listView = new ListView(this);
        this.list = listView;
        listView.setFastScrollEnabled(true);
        ArrayListAdapter<String> arrayListAdapter = new ArrayListAdapter<String>(this, this, R.layout.simple_list_item_1) { // from class: com.androlua.LuaActivity.1
            public final LuaActivity this$0;

            {
                this.this$0 = this;
            }

            @Override // android.widget.ArrayListAdapter, android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView textView2 = (TextView) super.getView(i, view, viewGroup);
                if (view == null) {
                    textView2.setTextIsSelectable(true);
                }
                return textView2;
            }
        };
        this.adapter = arrayListAdapter;
        this.list.setAdapter((ListAdapter) arrayListAdapter);
        this.layout.addView(this.list, new ViewGroup.LayoutParams(-1, -2));
        LuaApplication luaApplication = (LuaApplication) getApplication();
        if (luaApplication.getClass() != LuaApplication.class) {
            while (luaApplication.getClass() != LuaApplication.class) {
            }
        }
        this.localDir = luaApplication.getLocalDir();
        this.odexDir = luaApplication.getOdexDir();
        this.libDir = luaApplication.getLibDir();
        this.luaMdDir = luaApplication.getMdDir();
        this.luaCpath = luaApplication.getLuaCpath();
        this.luaDir = this.localDir;
        this.luaLpath = luaApplication.getLuaLpath();
        this.luaExtDir = luaApplication.getLuaExtDir();
        this.fusionDir = luaApplication.getFusionDir();
        this.handler = new MainHandler(this);
        try {
            this.status.setText("");
            this.adapter.clear();
            Object[] objArr = (Object[]) getIntent().getSerializableExtra(ARG);
            if (objArr == null) {
                objArr = new Object[0];
            }
            this.luaPath = getLuaPath();
            String name = new File(this.luaPath).getName();
            this.pageName = name;
            int iLastIndexOf = name.lastIndexOf(".");
            if (iLastIndexOf > 0) {
                this.pageName = this.pageName.substring(0, iLastIndexOf);
            }
            this.luaLpath = this.luaDir + "/?.lua;" + this.luaDir + "/lua/?.lua;" + this.luaDir + "/?/init.lua;" + this.luaLpath;
            initLua();
            LuaDexLoader luaDexLoader = new LuaDexLoader(this);
            this.mLuaDexLoader = luaDexLoader;
            luaDexLoader.loadLibs();
            sLuaActivityMap.put(this.pageName, this);
            this.isCreate = Boolean.TRUE;
            if (!this.pageName.equals(Loader.LAUNCH_PAGE)) {
                runFunction(Loader.LAUNCH_PAGE, objArr);
            }
            runFunction(this.pageName, objArr);
            runFunction("onCreate", bundle);
            if (!this.isSetViewed) {
                TypedArray typedArrayObtainStyledAttributes = getTheme().obtainStyledAttributes(new int[]{R.attr.colorBackground, R.attr.textColorPrimary, R.attr.textColorHighlightInverse});
                int color = typedArrayObtainStyledAttributes.getColor(0, 16711935);
                int color2 = typedArrayObtainStyledAttributes.getColor(1, 16711935);
                typedArrayObtainStyledAttributes.recycle();
                this.status.setTextColor(color2);
                this.layout.setBackgroundColor(color);
                setContentView(this.layout);
            }
            LuaObject luaObject = this.L.getLuaObject("onKeyShortcut");
            this.mOnKeyShortcut = luaObject;
            if (luaObject.isNil()) {
                this.mOnKeyShortcut = null;
            }
            LuaObject luaObject2 = this.L.getLuaObject("onKeyDown");
            this.mOnKeyDown = luaObject2;
            if (luaObject2.isNil()) {
                this.mOnKeyDown = null;
            }
            LuaObject luaObject3 = this.L.getLuaObject("onKeyUp");
            this.mOnKeyUp = luaObject3;
            if (luaObject3.isNil()) {
                this.mOnKeyUp = null;
            }
            LuaObject luaObject4 = this.L.getLuaObject("onKeyLongPress");
            this.mOnKeyLongPress = luaObject4;
            if (luaObject4.isNil()) {
                this.mOnKeyLongPress = null;
            }
            LuaObject luaObject5 = this.L.getLuaObject("onTouchEvent");
            this.mOnTouchEvent = luaObject5;
            if (luaObject5.isNil()) {
                this.mOnTouchEvent = null;
            }
            LuaObject luaObject6 = this.L.getLuaObject("onAccessibilityEvent");
            if (luaObject6.isFunction()) {
                LuaAccessibilityService.onAccessibilityEvent = luaObject6.getFunction();
            }
            try {
                throw new RuntimeException("");
            } catch (Exception e) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(byteArrayOutputStream));
                String[] strArrSplit = byteArrayOutputStream.toString().split("\n");
                for (int i = 1; i < strArrSplit.length; i++) {
                    String str = strArrSplit[i];
                    if (!str.contains("com.androlua") && !str.contains("com.nirenr.talkman") && !str.contains("android.app") && !str.contains("android.os") && !str.contains("java.lang") && !str.contains("com.android")) {
                        runFunction("onHook", new Object[0]);
                        return;
                    }
                }
            }
        } catch (Exception e2) {
            sendMessage(e2.getMessage());
            setContentView(this.layout);
        }
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        runFunction("onCreateContextMenu", contextMenu, view, contextMenuInfo);
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;
        runFunction("onCreateOptionsMenu", menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LuaBroadcastReceiver luaBroadcastReceiver = this.mReceiver;
        if (luaBroadcastReceiver != null) {
            unregisterReceiver(luaBroadcastReceiver);
        }
        Iterator<LuaGcable> it = this.gclist.iterator();
        while (it.hasNext()) {
            it.next().gc();
        }
        sLuaActivityMap.remove(this.pageName);
        runFunction("onDestroy", new Object[0]);
        super.onDestroy();
        System.gc();
        this.L.gc(2, 1);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        LuaObject luaObject = this.mOnKeyDown;
        if (luaObject != null) {
            try {
                Object objCall = luaObject.call(Integer.valueOf(i), keyEvent);
                if (objCall != null && objCall.getClass() == Boolean.class) {
                    if (((Boolean) objCall).booleanValue()) {
                        return true;
                    }
                }
            } catch (LuaException e) {
                sendError("onKeyDown", e);
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        LuaObject luaObject = this.mOnKeyLongPress;
        if (luaObject != null) {
            try {
                Object objCall = luaObject.call(Integer.valueOf(i), keyEvent);
                if (objCall != null && objCall.getClass() == Boolean.class) {
                    if (((Boolean) objCall).booleanValue()) {
                        return true;
                    }
                }
            } catch (LuaException e) {
                sendError("onKeyLongPress", e);
            }
        }
        return super.onKeyLongPress(i, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        LuaObject luaObject = this.mOnKeyShortcut;
        if (luaObject != null) {
            try {
                Object objCall = luaObject.call(Integer.valueOf(i), keyEvent);
                if (objCall != null && objCall.getClass() == Boolean.class) {
                    if (((Boolean) objCall).booleanValue()) {
                        return true;
                    }
                }
            } catch (LuaException e) {
                sendError("onKeyShortcut", e);
            }
        }
        return super.onKeyShortcut(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        LuaObject luaObject = this.mOnKeyUp;
        if (luaObject != null) {
            try {
                Object objCall = luaObject.call(Integer.valueOf(i), keyEvent);
                if (objCall != null && objCall.getClass() == Boolean.class) {
                    if (((Boolean) objCall).booleanValue()) {
                        return true;
                    }
                }
            } catch (LuaException e) {
                sendError("onKeyUp", e);
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Object objRunFunction = !menuItem.hasSubMenu() ? runFunction("onOptionsItemSelected", menuItem) : null;
        if (objRunFunction != null && objRunFunction.getClass() == Boolean.class && ((Boolean) objRunFunction).booleanValue()) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        runFunction("onPause", new Object[0]);
    }

    @Override // com.androlua.LuaBroadcastReceiver.OnReceiveListener
    public void onReceive(Context context, Intent intent) {
        runFunction("onReceive", context, intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        runFunction("onResume", new Object[0]);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        runFunction("onStart", new Object[0]);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        runFunction("onStop", new Object[0]);
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        LuaObject luaObject = this.mOnTouchEvent;
        if (luaObject != null) {
            try {
                Object objCall = luaObject.call(motionEvent);
                if (objCall != null && objCall.getClass() == Boolean.class) {
                    if (((Boolean) objCall).booleanValue()) {
                        return true;
                    }
                }
            } catch (LuaException e) {
                sendError("onTouchEvent", e);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void push(int i, String str) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(DATA, str);
        message.setData(bundle);
        message.what = i;
        this.handler.sendMessage(message);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void push(int i, String str, Object[] objArr) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(DATA, str);
        bundle.putSerializable("args", objArr);
        message.setData(bundle);
        message.what = i;
        this.handler.sendMessage(message);
    }

    public byte[] readAsset(String str) throws IOException {
        InputStream inputStreamOpen = getAssets().open(str);
        byte[] all = readAll(inputStreamOpen);
        inputStreamOpen.close();
        return all;
    }

    @Override // com.androlua.LuaContext
    public void regGc(LuaGcable luaGcable) {
        this.gclist.add(luaGcable);
    }

    public Intent registerReceiver(IntentFilter intentFilter) {
        BroadcastReceiver broadcastReceiver = this.mReceiver;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        LuaBroadcastReceiver luaBroadcastReceiver = new LuaBroadcastReceiver(this);
        this.mReceiver = luaBroadcastReceiver;
        return super.registerReceiver((BroadcastReceiver) luaBroadcastReceiver, intentFilter);
    }

    public Intent registerReceiver(LuaBroadcastReceiver.OnReceiveListener onReceiveListener, IntentFilter intentFilter) {
        return super.registerReceiver((BroadcastReceiver) new LuaBroadcastReceiver(onReceiveListener), intentFilter);
    }

    public Intent registerReceiver(LuaBroadcastReceiver luaBroadcastReceiver, IntentFilter intentFilter) {
        return super.registerReceiver((BroadcastReceiver) luaBroadcastReceiver, intentFilter);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void result(Object[] objArr) {
        Intent intent = new Intent();
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra(DATA, (Serializable) objArr);
        setResult(0, intent);
        finish();
    }

    @Override // com.androlua.LuaContext
    public Object runFunction(String str, Object... objArr) {
        LuaState luaState = this.L;
        if (luaState != null) {
            synchronized (luaState) {
                try {
                    this.L.setTop(0);
                    this.L.pushGlobalTable();
                    this.L.pushString(str);
                    this.L.rawGet(-2);
                    if (this.L.isFunction(-1)) {
                        this.L.getGlobal("debug");
                        this.L.getField(-1, "traceback");
                        this.L.remove(-2);
                        this.L.insert(-2);
                        int length = objArr.length;
                        for (Object obj : objArr) {
                            this.L.pushObjectValue(obj);
                        }
                        int iPcall = this.L.pcall(length, 1, (-2) - length);
                        if (iPcall == 0) {
                            return this.L.toJavaObject(-1);
                        }
                        throw new LuaException(errorReason(iPcall) + ": " + this.L.toString(-1));
                    }
                } catch (LuaException e) {
                    sendError("runFunction", e);
                }
            }
        }
        return null;
    }

    @Override // com.androlua.LuaContext
    public void sendError(String str, Exception exc) {
    }

    @Override // com.androlua.LuaContext
    public void sendMessage(String str) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(DATA, str);
        message.setData(bundle);
        message.what = 0;
        this.handler.sendMessage(message);
        Log.i("lua", str);
    }

    @Override // com.androlua.LuaContext
    public void set(String str, Object obj) {
        push(1, str, new Object[]{obj});
    }

    public void setContentView(LuaObject luaObject) throws LuaException {
        setContentView(luaObject, (LuaObject) null);
    }

    public void setContentView(LuaObject luaObject, LuaObject luaObject2) throws LuaException {
        Object objCall;
        LuaObject luaObject3 = this.L.getLuaObject("loadlayout");
        if (luaObject.isString()) {
            objCall = luaObject3.call(luaObject.getString(), luaObject2);
        } else {
            if (!luaObject.isTable()) {
                throw new LuaException("layout may be table or string.");
            }
            objCall = luaObject3.call(luaObject, luaObject2);
        }
        super.setContentView((View) objCall);
    }

    public void setContentView(String str) {
        setContentView(str, (LuaObject) null);
    }

    public void setContentView(String str, LuaObject luaObject) {
        super.setContentView((View) this.L.getLuaObject("loadlayout").call(str, luaObject));
    }

    public void setDebug(boolean z) {
        this.mDebug = z;
    }

    public void setFragment(Fragment fragment) {
        this.isSetViewed = true;
        getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }

    public void setLuaDir(String str) {
        this.luaDir = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    @Override // com.androlua.LuaContext
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setLuaExtDir(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = android.os.Environment.getExternalStorageState()
            java.lang.String r1 = "mounted"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L1e
            java.io.File r0 = new java.io.File
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r1 = r1.getAbsolutePath()
            r0.<init>(r1, r8)
            java.lang.String r8 = r0.getAbsolutePath()
            goto L55
        L1e:
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/storage"
            r0.<init>(r1)
            java.io.File[] r0 = r0.listFiles()
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L2c:
            if (r3 >= r1) goto L49
            r4 = r0[r3]
            java.lang.String[] r5 = r4.list()
            if (r5 != 0) goto L37
            goto L46
        L37:
            int r5 = r5.length
            r6 = 5
            if (r5 <= r6) goto L46
            java.io.File r5 = new java.io.File
            r5.<init>(r4, r8)
            java.lang.String r4 = r5.getAbsolutePath()
            r7.luaExtDir = r4
        L46:
            int r3 = r3 + 1
            goto L2c
        L49:
            java.lang.String r0 = r7.luaExtDir
            if (r0 != 0) goto L57
            java.io.File r8 = r7.getDir(r8, r2)
            java.lang.String r8 = r8.getAbsolutePath()
        L55:
            r7.luaExtDir = r8
        L57:
            java.io.File r8 = new java.io.File
            java.lang.String r0 = r7.luaExtDir
            r8.<init>(r0)
            boolean r0 = r8.exists()
            if (r0 != 0) goto L67
            r8.mkdirs()
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.setLuaExtDir(java.lang.String):void");
    }

    @Override // com.androlua.LuaContext
    public boolean setSharedData(String str, Object obj) {
        return LuaApplication.getInstance().setSharedData(str, obj);
    }

    @Override // android.app.Activity
    public void setTitle(CharSequence charSequence) {
        ActivityManager.TaskDescription taskDescription;
        super.setTitle(charSequence);
        try {
            taskDescription = new ActivityManager.TaskDescription(charSequence.toString(), loadBitmap(getLuaPath("icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
            taskDescription = new ActivityManager.TaskDescription(charSequence.toString());
        }
        setTaskDescription(taskDescription);
    }

    public void shareFile(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        File file = new File(str);
        intent.setType(getType(file));
        intent.setFlags(1);
        intent.putExtra("android.intent.extra.STREAM", getUriForFile(file));
        startActivity(Intent.createChooser(intent, file.getName()));
    }

    public void showToast(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.toast == null || jCurrentTimeMillis - this.lastShow > 1000) {
            this.toastbuilder.setLength(0);
            this.toast = Toast.makeText(this, str, 1);
            this.toastbuilder.append(str);
            this.toast.show();
        } else {
            this.toastbuilder.append("\n");
            this.toastbuilder.append(str);
            this.toast.setText(this.toastbuilder.toString());
            this.toast.setDuration(1);
        }
        this.lastShow = jCurrentTimeMillis;
    }

    public ComponentName startService() {
        return startService(null, null);
    }

    public ComponentName startService(String str) {
        return startService(str, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ComponentName startService(String str, Object[] objArr) {
        StringBuilder sbO;
        Intent intent = new Intent(this, (Class<?>) LuaService.class);
        intent.putExtra("luaDir", this.luaDir);
        intent.putExtra("luaPath", this.luaPath);
        if (str != null) {
            if (str.charAt(0) != '/') {
                sbO = a.o("file://");
                sbO.append(this.luaDir);
                sbO.append("/");
                sbO.append(str);
                str = ".lua";
            } else {
                sbO = a.o("file://");
            }
            sbO.append(str);
            intent.setData(Uri.parse(sbO.toString()));
        }
        if (objArr != 0) {
            intent.putExtra(ARG, (Serializable) objArr);
        }
        return super.startService(intent);
    }

    public ComponentName startService(Object[] objArr) {
        return startService(null, objArr);
    }

    public boolean stopService() {
        return stopService(new Intent(this, (Class<?>) LuaService.class));
    }

    public LuaAsyncTask task(long j, LuaObject luaObject) {
        return task(j, (Object[]) null, (LuaObject) null);
    }

    public LuaAsyncTask task(long j, Object[] objArr, LuaObject luaObject) {
        LuaAsyncTask luaAsyncTask = new LuaAsyncTask(this, j, luaObject);
        luaAsyncTask.execute(objArr);
        return luaAsyncTask;
    }

    public LuaAsyncTask task(LuaObject luaObject) {
        return task(luaObject, null, null, null);
    }

    public LuaAsyncTask task(LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3) {
        return task(luaObject, null, luaObject2, luaObject3);
    }

    public LuaAsyncTask task(LuaObject luaObject, Object[] objArr) {
        return task(luaObject, objArr, null, null);
    }

    public LuaAsyncTask task(LuaObject luaObject, Object[] objArr, LuaObject luaObject2) {
        return task(luaObject, null, null, luaObject2);
    }

    public LuaAsyncTask task(LuaObject luaObject, Object[] objArr, LuaObject luaObject2, LuaObject luaObject3) {
        LuaAsyncTask luaAsyncTask = new LuaAsyncTask(this, luaObject, luaObject2, luaObject3);
        luaAsyncTask.execute(objArr);
        return luaAsyncTask;
    }

    public long test(String str, int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (int i2 = 0; i2 < i; i2++) {
            this.L.LdoString(str);
        }
        return System.currentTimeMillis() - jCurrentTimeMillis;
    }

    public LuaThread thread(LuaObject luaObject) {
        LuaThread luaThreadNewThread = newThread(luaObject, new Object[0]);
        luaThreadNewThread.start();
        return luaThreadNewThread;
    }

    public LuaThread thread(LuaObject luaObject, Object... objArr) {
        LuaThread luaThread = new LuaThread((LuaContext) this, luaObject, true, objArr);
        luaThread.start();
        return luaThread;
    }

    public Ticker ticker(LuaObject luaObject, long j) {
        Ticker ticker = new Ticker();
        ticker.setOnTickListener(new Ticker.OnTickListener(this, luaObject) { // from class: com.androlua.LuaActivity.3
            public final LuaActivity this$0;
            public final LuaObject val$func;

            {
                this.this$0 = this;
                this.val$func = luaObject;
            }

            @Override // com.androlua.Ticker.OnTickListener
            public void onTick() {
                try {
                    this.val$func.call(new Object[0]);
                } catch (LuaException e) {
                    e.printStackTrace();
                    this.this$0.sendError("ticker", e);
                }
            }
        });
        ticker.start();
        ticker.setPeriod(j);
        return ticker;
    }

    public LuaTimer timer(LuaObject luaObject, long j) {
        return timer(luaObject, 0L, j, new Object[0]);
    }

    public LuaTimer timer(LuaObject luaObject, long j, long j2) {
        return timer(luaObject, j, j2, new Object[0]);
    }

    public LuaTimer timer(LuaObject luaObject, long j, long j2, Object... objArr) {
        LuaTimer luaTimer = new LuaTimer(this, luaObject, objArr);
        luaTimer.start(j, j2);
        return luaTimer;
    }

    public LuaTimer timer(LuaObject luaObject, long j, Object... objArr) {
        return timer(luaObject, 0L, j, objArr);
    }

    public void unZipAssets(String str, String str2) throws IOException {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            ZipInputStream zipInputStream = new ZipInputStream(getAssets().open(str));
            byte[] bArr = new byte[4096];
            for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                if (nextEntry.isDirectory()) {
                    StringBuilder sbO = a.o(str2);
                    sbO.append(File.separator);
                    sbO.append(nextEntry.getName());
                    new File(sbO.toString()).mkdir();
                } else {
                    StringBuilder sbO2 = a.o(str2);
                    sbO2.append(File.separator);
                    sbO2.append(nextEntry.getName());
                    File file2 = new File(sbO2.toString());
                    file2.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    while (true) {
                        int i = zipInputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, i);
                        }
                    }
                    fileOutputStream.close();
                }
            }
            zipInputStream.close();
        } catch (IOException e) {
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        try {
            super.unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            Log.i("lua", "unregisterReceiver: " + broadcastReceiver);
            e.printStackTrace();
        }
    }
}

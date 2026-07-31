package com.androlua;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.androlua.LuaBroadcastReceiver;
import com.androlua.Ticker;
import com.baidu.android.common.util.HanziToPinyin;
import com.baidu.mobstat.Config;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import dalvik.system.DexClassLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaService extends Service implements LuaContext, LuaBroadcastReceiver.OnReceiveListener {
    private static LuaService _this;
    private LuaState L;
    private MainHandler handler;
    private long lastShow;
    private String libDir;
    private String localDir;
    public String luaCpath;
    public String luaDir;
    private String luaExtDir;
    private String luaLpath;
    private String luaMdDir;
    private String luaPath;
    private LuaDexLoader mLuaDexLoader;
    private BroadcastReceiver mReceiver;
    private LuaResources mResources;
    private String odexDir;
    private Toast toast;
    public LuaBinder mBinder = new LuaBinder(this);
    private ArrayList<LuaGcable> gclist = new ArrayList<>();
    private StringBuilder output = new StringBuilder();
    private StringBuilder toastbuilder = new StringBuilder();

    public class LuaBinder extends Binder {
        public final LuaService this$0;

        public LuaBinder(LuaService luaService) {
            this.this$0 = luaService;
        }

        public LuaService getService() {
            return this.this$0;
        }
    }

    public class MainHandler extends Handler {
        public final LuaService this$0;

        public MainHandler(LuaService luaService) {
            this.this$0 = luaService;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                this.this$0.showToast(message.getData().getString("data"));
                return;
            }
            if (i == 1) {
                Bundle data = message.getData();
                this.this$0.setField(data.getString("data"), ((Object[]) data.getSerializable("args"))[0]);
            } else if (i == 2) {
                this.this$0.runFunction(message.getData().getString("data"), new Object[0]);
            } else {
                if (i != 3) {
                    return;
                }
                this.this$0.runFunction(message.getData().getString("data"), (Object[]) message.getData().getSerializable("args"));
            }
        }
    }

    private void copyFile(String str, String str2) {
        try {
            if (!new File(str).exists()) {
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[4096];
            int i = 0;
            while (true) {
                int i2 = fileInputStream.read(bArr);
                if (i2 == -1) {
                    fileInputStream.close();
                    return;
                } else {
                    i += i2;
                    System.out.println(i);
                    fileOutputStream.write(bArr, 0, i2);
                }
            }
        } catch (Exception e) {
            System.out.println("复制文件操作出错");
            e.printStackTrace();
        }
    }

    private String errorReason(int i) {
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

    public static LuaService getService() {
        return _this;
    }

    private void initLua() {
        LuaState luaStateNewLuaState = LuaStateFactory.newLuaState();
        this.L = luaStateNewLuaState;
        luaStateNewLuaState.openLibs();
        this.L.pushJavaObject(this);
        this.L.setGlobal(NotificationCompat.CATEGORY_SERVICE);
        this.L.getGlobal(NotificationCompat.CATEGORY_SERVICE);
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
        new LuaAssetLoader(this, this.L);
        this.L.getGlobal("package");
        this.L.pushString(this.luaLpath);
        this.L.setField(-2, Config.FEED_LIST_ITEM_PATH);
        this.L.pushString(this.luaCpath);
        this.L.setField(-2, "cpath");
        this.L.pop(1);
        new JavaFunction(this, this.L) { // from class: com.androlua.LuaService.2
            public final LuaService this$0;

            {
                this.this$0 = this;
            }

            @Override // com.luajava.JavaFunction
            public int execute() {
                String string;
                if (((JavaFunction) this).L.getTop() < 2) {
                    this.this$0.sendMessage("");
                } else {
                    for (int i = 2; i <= ((JavaFunction) this).L.getTop(); i++) {
                        String strTypeName = ((JavaFunction) this).L.typeName(((JavaFunction) this).L.type(i));
                        strTypeName.hashCode();
                        if (strTypeName.equals("userdata")) {
                            Object javaObject = ((JavaFunction) this).L.toJavaObject(i);
                            string = javaObject != null ? javaObject.toString() : null;
                        } else {
                            string = !strTypeName.equals("boolean") ? ((JavaFunction) this).L.toString(i) : ((JavaFunction) this).L.toBoolean(i) ? "true" : "false";
                        }
                        if (string != null) {
                            strTypeName = string;
                        }
                        this.this$0.output.append("\t");
                        this.this$0.output.append(strTypeName);
                        this.this$0.output.append("\t");
                    }
                    LuaService luaService = this.this$0;
                    luaService.sendMessage(luaService.output.toString().substring(1, this.this$0.output.length() - 1));
                    this.this$0.output.setLength(0);
                }
                return 0;
            }
        }.register("print");
        new JavaFunction(this, this.L) { // from class: com.androlua.LuaService.3
            public final LuaService this$0;

            {
                this.this$0 = this;
            }

            @Override // com.luajava.JavaFunction
            public int execute() {
                ((LuaThread) ((JavaFunction) this).L.toJavaObject(2)).set(((JavaFunction) this).L.toString(3), ((JavaFunction) this).L.toJavaObject(4));
                return 0;
            }
        }.register("set");
        new JavaFunction(this, this.L) { // from class: com.androlua.LuaService.4
            public final LuaService this$0;

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
        try {
            this.L.pushObjectValue(obj);
            this.L.setGlobal(str);
        } catch (LuaException e) {
            sendMessage(e.getMessage());
        }
    }

    public void call(String str) {
        push(2, str);
    }

    @Override // com.androlua.LuaContext
    public void call(String str, Object[] objArr) {
        if (objArr.length == 0) {
            push(2, str);
        } else {
            push(3, str, objArr);
        }
    }

    public Object doAsset(String str, Object... objArr) {
        int length;
        int i;
        try {
            byte[] asset = readAsset(str);
            this.L.setTop(0);
            int iLloadBuffer = this.L.LloadBuffer(asset, str);
            if (iLloadBuffer == 0) {
                this.L.getGlobal("debug");
                this.L.getField(-1, "traceback");
                this.L.remove(-2);
                this.L.insert(-2);
                if (objArr != null) {
                    length = objArr.length;
                    i = 0;
                } else {
                    length = 0;
                    i = 0;
                }
                while (i < length) {
                    this.L.pushObjectValue(objArr[i]);
                    i++;
                }
                iLloadBuffer = this.L.pcall(length, 0, (-2) - length);
                if (iLloadBuffer == 0) {
                    return this.L.toJavaObject(-1);
                }
            }
            throw new LuaException(errorReason(iLloadBuffer) + ": " + this.L.toString(-1));
        } catch (Exception e) {
            sendMessage(e.getMessage());
            return null;
        }
    }

    @Override // com.androlua.LuaContext
    public Object doFile(String str) {
        return doFile(str, new Object[0]);
    }

    @Override // com.androlua.LuaContext
    public Object doFile(String str, Object[] objArr) {
        try {
            if (str.charAt(0) != '/') {
                str = this.luaDir + "/" + str;
            }
            this.L.setTop(0);
            int iLloadFile = this.L.LloadFile(str);
            if (iLloadFile == 0) {
                this.L.getGlobal("debug");
                this.L.getField(-1, "traceback");
                this.L.remove(-2);
                this.L.insert(-2);
                int length = objArr != null ? objArr.length : 0;
                for (int i = 0; i < length; i++) {
                    this.L.pushObjectValue(objArr[i]);
                }
                iLloadFile = this.L.pcall(length, 1, (-2) - length);
                if (iLloadFile == 0) {
                    return this.L.toJavaObject(-1);
                }
            }
            throw new LuaException(errorReason(iLloadFile) + ": " + this.L.toString(-1));
        } catch (LuaException e) {
            sendMessage(e.getMessage());
            return null;
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
                int length = objArr != null ? objArr.length : 0;
                for (int i = 0; i < length; i++) {
                    this.L.pushObjectValue(objArr[i]);
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

    public Object get(String str) {
        this.L.getGlobal(str);
        return this.L.toJavaObject(-1);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        LuaDexLoader luaDexLoader = this.mLuaDexLoader;
        return (luaDexLoader == null || luaDexLoader.getAssets() == null) ? super.getAssets() : this.mLuaDexLoader.getAssets();
    }

    public LuaBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.androlua.LuaContext
    public Context getContext() {
        return this;
    }

    @Override // com.androlua.LuaContext
    public String getFusionDir() {
        return null;
    }

    @Override // com.androlua.LuaContext
    public Map getGlobalData() {
        return LuaApplication.getInstance().getGlobalData();
    }

    public HashMap<String, String> getLibrarys() {
        return this.mLuaDexLoader.getLibrarys();
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
        File file = new File(this.luaExtDir + "/" + str);
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
        return this.luaPath;
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

    @Override // com.androlua.LuaContext
    public String getProjectDir() {
        return null;
    }

    @Override // android.content.ContextWrapper, android.content.Context
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
        return getResources().getDisplayMetrics().heightPixels;
    }

    @Override // com.androlua.LuaContext
    public int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
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
            copyFile(a.n(new StringBuilder(), this.luaDir, "/lib", strSubstring, ".so"), a.n(new StringBuilder(), this.libDir, "/lib", strSubstring, ".so"));
        }
        return this.L.getLuaObject("require").call(str);
    }

    public void loadResources(String str) {
        this.mLuaDexLoader.loadResources(str);
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
        return newThread(luaObject, null);
    }

    public LuaThread newThread(LuaObject luaObject, Object[] objArr) {
        return new LuaThread((LuaContext) this, luaObject, true, objArr);
    }

    public LuaTimer newTimer(LuaObject luaObject) {
        return newTimer(luaObject, null);
    }

    public LuaTimer newTimer(LuaObject luaObject, Object[] objArr) {
        return new LuaTimer(this, luaObject, objArr);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        startForeground(1, new Notification());
        return new LuaBinder(this);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        _this = this;
        LuaApplication luaApplication = (LuaApplication) getApplication();
        this.localDir = luaApplication.getLocalDir();
        this.odexDir = luaApplication.getOdexDir();
        this.libDir = luaApplication.getLibDir();
        this.luaMdDir = luaApplication.getMdDir();
        this.luaCpath = luaApplication.getLuaCpath();
        this.luaDir = this.localDir;
        this.luaLpath = luaApplication.getLuaLpath();
        this.luaExtDir = luaApplication.getLuaExtDir();
        this.handler = new MainHandler(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        runFunction("onDestroy", new Object[0]);
        BroadcastReceiver broadcastReceiver = this.mReceiver;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        super.onDestroy();
    }

    @Override // com.androlua.LuaBroadcastReceiver.OnReceiveListener
    public void onReceive(Context context, Intent intent) {
        runFunction("onReceive", context, intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        _this = this;
        if (this.L == null) {
            startForeground(1, new Notification());
            this.luaPath = intent.getStringExtra("luaPath");
            this.luaDir = intent.getStringExtra("luaDir");
            this.luaLpath = this.luaDir + "/?.lua;" + this.luaDir + "/lua/?.lua;" + this.luaDir + "/?/init.lua;" + this.luaLpath;
            Uri data = intent.getData();
            try {
                initLua();
                LuaDexLoader luaDexLoader = new LuaDexLoader(this);
                this.mLuaDexLoader = luaDexLoader;
                luaDexLoader.loadLibs();
                doFile(data != null ? data.getPath() : "service.lua");
            } catch (Exception e) {
                sendMessage(e.getMessage());
            }
        }
        runFunction("onStartCommand", intent, Integer.valueOf(i), Integer.valueOf(i2));
        runFunction("onStart", (Object[]) intent.getSerializableExtra("arg"));
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void push(int i, String str) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        message.setData(bundle);
        message.what = i;
        this.handler.sendMessage(message);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void push(int i, String str, Object[] objArr) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
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

    @Override // com.androlua.LuaContext
    public Object runFunction(String str, Object... objArr) {
        LuaState luaState = this.L;
        if (luaState != null) {
            try {
                luaState.setTop(0);
                this.L.getGlobal(str);
                if (this.L.isFunction(-1)) {
                    this.L.getGlobal("debug");
                    this.L.getField(-1, "traceback");
                    this.L.remove(-2);
                    this.L.insert(-2);
                    int length = objArr != null ? objArr.length : 0;
                    for (int i = 0; i < length; i++) {
                        this.L.pushObjectValue(objArr[i]);
                    }
                    int iPcall = this.L.pcall(length, 1, (-2) - length);
                    if (iPcall == 0) {
                        return this.L.toJavaObject(-1);
                    }
                    throw new LuaException(errorReason(iPcall) + ": " + this.L.toString(-1));
                }
            } catch (LuaException e) {
                StringBuilder sbD = a.d(str, HanziToPinyin.Token.SEPARATOR);
                sbD.append(e.getMessage());
                sendMessage(sbD.toString());
            }
        }
        return null;
    }

    @Override // com.androlua.LuaContext
    public void sendError(String str, Exception exc) {
        runFunction("onError", str, exc);
    }

    @Override // com.androlua.LuaContext
    public void sendMessage(String str) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        message.setData(bundle);
        message.what = 0;
        this.handler.sendMessage(message);
        Log.i("lua", str);
    }

    @Override // com.androlua.LuaContext
    public void set(String str, Object obj) {
        push(1, str, new Object[]{obj});
    }

    public void setBinder(LuaBinder luaBinder) {
        this.mBinder = luaBinder;
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
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaService.setLuaExtDir(java.lang.String):void");
    }

    @Override // com.androlua.LuaContext
    public boolean setSharedData(String str, Object obj) {
        return LuaApplication.getInstance().setSharedData(str, obj);
    }

    public void showToast(String str) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.toast == null || jCurrentTimeMillis - this.lastShow > 1000) {
                this.toastbuilder.setLength(0);
                this.toast = Toast.makeText(this, str, 1);
                this.toastbuilder.append(str);
            } else {
                this.toastbuilder.append("\n");
                this.toastbuilder.append(str);
                this.toast.setText(this.toastbuilder.toString());
                this.toast.setDuration(1);
            }
            this.lastShow = jCurrentTimeMillis;
            this.toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public LuaThread thread(LuaObject luaObject) {
        LuaThread luaThreadNewThread = newThread(luaObject, null);
        luaThreadNewThread.start();
        return luaThreadNewThread;
    }

    public LuaThread thread(LuaObject luaObject, Object[] objArr) {
        LuaThread luaThread = new LuaThread((LuaContext) this, luaObject, true, objArr);
        luaThread.start();
        return luaThread;
    }

    public Ticker ticker(LuaObject luaObject, long j) {
        Ticker ticker = new Ticker();
        ticker.setOnTickListener(new Ticker.OnTickListener(this, luaObject) { // from class: com.androlua.LuaService.1
            public final LuaService this$0;
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
                    this.this$0.sendError("onTick", e);
                }
            }
        });
        ticker.setPeriod(j);
        ticker.start();
        return ticker;
    }

    public LuaTimer timer(LuaObject luaObject, long j) {
        return timer(luaObject, 0L, j, null);
    }

    public LuaTimer timer(LuaObject luaObject, long j, long j2) {
        return timer(luaObject, j, j2, null);
    }

    public LuaTimer timer(LuaObject luaObject, long j, long j2, Object[] objArr) {
        LuaTimer luaTimer = new LuaTimer(this, luaObject, objArr);
        luaTimer.start(j, j2);
        return luaTimer;
    }

    public LuaTimer timer(LuaObject luaObject, long j, Object[] objArr) {
        return timer(luaObject, 0L, j, objArr);
    }
}

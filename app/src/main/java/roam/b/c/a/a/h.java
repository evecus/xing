package roam.b.c.a.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.app.NotificationCompat;
import androidx.core.os.HandlerCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.androlua.LuaActivity;
import com.androlua.LuaApplication;
import com.androlua.LuaContext;
import com.androlua.LuaDexLoader;
import com.androlua.LuaGcable;
import com.androlua.LuaPrint;
import com.androlua.LuaThread;
import com.androlua.LuaUtil;
import com.baidu.mobstat.Config;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import roam.b.c.a.a.g;

/* JADX INFO: loaded from: classes.dex */
public class h implements LuaContext {
    public Activity a;
    public String b;
    public String c;
    public LuaDexLoader d;
    public int e;
    public int f;
    public LuaState g;
    public String h;
    public String i;
    public String j;
    public String m;
    public String o;
    public LuaFunction p;
    public boolean k = false;
    public ArrayList<LuaGcable> l = new ArrayList<>();
    public Handler n = new d(this);

    public class a extends JavaFunction {
        public a(h hVar, LuaState luaState) {
            super(luaState);
        }

        @Override // com.luajava.JavaFunction
        public int execute() {
            ((LuaThread) ((JavaFunction) this).L.toJavaObject(2)).set(((JavaFunction) this).L.toString(3), ((JavaFunction) this).L.toJavaObject(4));
            return 0;
        }
    }

    public class b extends JavaFunction {
        public b(h hVar, LuaState luaState) {
            super(luaState);
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
    }

    public class c implements Runnable {
        public final String a;
        public final h b;

        public c(h hVar, String str) {
            this.b = hVar;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.sendMessage(this.a);
        }
    }

    public class d extends Handler {
        public final h a;

        public d(h hVar) {
            this.a = hVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                String string = message.getData().getString("data");
                h hVar = this.a;
                if (hVar.k) {
                    Toast.makeText(hVar.a, string, 0).show();
                    return;
                }
                return;
            }
            if (i != 1) {
                if (i == 2) {
                    this.a.runFunction(message.getData().getString("data"), new Object[0]);
                    return;
                } else {
                    if (i != 3) {
                        return;
                    }
                    this.a.runFunction(message.getData().getString("data"), (Object[]) message.getData().getSerializable("args"));
                    return;
                }
            }
            Bundle data = message.getData();
            h hVar2 = this.a;
            String string2 = data.getString("data");
            Object obj = ((Object[]) data.getSerializable("args"))[0];
            synchronized (hVar2.g) {
                try {
                    hVar2.g.pushObjectValue(obj);
                    hVar2.g.setGlobal(string2);
                } catch (LuaException e) {
                    hVar2.sendError("setField", e);
                }
            }
        }
    }

    static {
        new ArrayList();
    }

    public h(AppCompatActivity appCompatActivity) {
        this.a = appCompatActivity;
        WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.e = displayMetrics.widthPixels;
        this.f = displayMetrics.heightPixels;
        LuaApplication luaApplication = (LuaApplication) this.a.getApplication();
        luaApplication.getLocalDir();
        this.h = luaApplication.getLibDir();
        this.c = luaApplication.getLuaCpath();
        String fusionDir = luaApplication.getFusionDir();
        this.m = fusionDir;
        this.b = fusionDir;
        this.j = luaApplication.getLuaLpath();
        this.i = luaApplication.getLuaExtDir();
        appCompatActivity.getLifecycle().addObserver(new LifecycleEventObserver(this) { // from class: roam.b.c.a.a.c
            public final h a;

            {
                this.a = this;
            }

            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                h hVar = this.a;
                Objects.requireNonNull(hVar);
                if (event == Lifecycle.Event.ON_DESTROY) {
                    for (LuaGcable luaGcable : hVar.l) {
                        if (!luaGcable.isGc()) {
                            luaGcable.gc();
                        }
                    }
                    System.gc();
                    hVar.g.gc(2, 1);
                }
            }
        });
    }

    public void a(String str) {
        this.j = str + "/?.lua;" + str + "/lua/?.lua;" + str + "/?/init.lua;" + this.j;
    }

    public Object b(String str, Object... objArr) {
        try {
            this.g.setTop(0);
            int iLloadString = this.g.LloadString(str);
            if (iLloadString == 0) {
                this.g.getGlobal("debug");
                this.g.getField(-1, "traceback");
                this.g.remove(-2);
                this.g.insert(-2);
                int length = objArr.length;
                for (Object obj : objArr) {
                    this.g.pushObjectValue(obj);
                }
                iLloadString = this.g.pcall(length, 1, (-2) - length);
                if (iLloadString == 0) {
                    return this.g.toJavaObject(-1);
                }
            }
            throw new LuaException(LuaActivity.errorReason(iLloadString) + ": " + this.g.toString(-1));
        } catch (LuaException e) {
            sendMessage(e.getMessage());
            return null;
        }
    }

    public final void c() {
        try {
            LuaState luaStateNewLuaState = LuaStateFactory.newLuaState();
            this.g = luaStateNewLuaState;
            luaStateNewLuaState.openLibs();
            this.g.pushJavaObject(this.a);
            this.g.setGlobal(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
            this.g.getGlobal(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
            this.g.setGlobal("this");
            this.g.pushContext(this);
            this.g.getGlobal("luajava");
            this.g.pushString(this.i);
            this.g.setField(-2, "luaextdir");
            this.g.pushString(this.b);
            this.g.setField(-2, "luadir");
            this.g.pushString((String) null);
            this.g.setField(-2, "luapath");
            this.g.pop(1);
            new LuaPrint(this, this.g).register("print");
            this.g.getGlobal("package");
            this.g.pushString(this.j);
            this.g.setField(-2, Config.FEED_LIST_ITEM_PATH);
            this.g.pushString(this.c);
            this.g.setField(-2, "cpath");
            this.g.pop(1);
            this.p = this.g.getFunction("onError");
        } catch (Exception e) {
        }
        new a(this, this.g).register("set");
        new b(this, this.g).register(NotificationCompat.CATEGORY_CALL);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.androlua.LuaContext
    public void call(String str, Object... objArr) {
        if (objArr.length == 0) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("data", str);
            message.setData(bundle);
            message.what = 2;
            this.n.sendMessage(message);
            return;
        }
        Message message2 = new Message();
        Bundle bundle2 = new Bundle();
        bundle2.putString("data", str);
        bundle2.putSerializable("args", objArr);
        message2.setData(bundle2);
        message2.what = 3;
        this.n.sendMessage(message2);
    }

    public void d() {
        try {
            a(this.b);
            a(this.m);
            c();
            LuaDexLoader luaDexLoader = new LuaDexLoader(this);
            this.d = luaDexLoader;
            luaDexLoader.loadLibs();
        } catch (Exception e) {
            sendError("initLuaEnv", e);
        }
    }

    @Override // com.androlua.LuaContext
    public Object doFile(String str) {
        return doFile(str, new Object[0]);
    }

    @Override // com.androlua.LuaContext
    public Object doFile(String str, Object... objArr) {
        try {
            if (TextUtils.isEmpty(str)) {
                throw new LuaException(": " + this.g.toString(-1));
            }
            if (str.charAt(0) != '/') {
                str = this.b + "/" + str;
            }
            this.g.setTop(0);
            int iLloadFile = this.g.LloadFile(str);
            if (iLloadFile == 0) {
                this.g.getGlobal("debug");
                this.g.getField(-1, "traceback");
                this.g.remove(-2);
                this.g.insert(-2);
                int length = objArr.length;
                for (Object obj : objArr) {
                    this.g.pushObjectValue(obj);
                }
                iLloadFile = this.g.pcall(length, 1, (-2) - length);
                if (iLloadFile == 0) {
                    return this.g.toJavaObject(-1);
                }
            }
            Intent intent = new Intent();
            intent.putExtra("data", this.g.toString(-1));
            this.a.setResult(iLloadFile, intent);
            throw new LuaException(LuaActivity.errorReason(iLloadFile) + ": " + this.g.toString(-1));
        } catch (LuaException e) {
            sendError("doFile", e);
            if (this.k) {
                if (g.b == null) {
                    g.b = new g();
                }
                g.b.a.add(new g.a());
            }
            return null;
        }
    }

    @Override // com.androlua.LuaContext
    public Context getContext() {
        return this.a;
    }

    @Override // com.androlua.LuaContext
    public String getFusionDir() {
        return this.m;
    }

    @Override // com.androlua.LuaContext
    public Map getGlobalData() {
        return ((LuaApplication) this.a.getApplication()).getGlobalData();
    }

    @Override // com.androlua.LuaContext
    public String getLuaCpath() {
        return this.c;
    }

    @Override // com.androlua.LuaContext
    public LuaDexLoader getLuaDexLoader() {
        return this.d;
    }

    @Override // com.androlua.LuaContext
    public String getLuaDir() {
        return this.m;
    }

    @Override // com.androlua.LuaContext
    public String getLuaDir(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.m);
        return roam.a.b.a.a.a.l(sb, File.separatorChar, str);
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtDir() {
        return this.i;
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtDir(String str) {
        File file = new File(this.i, str);
        if (file.exists() || file.mkdirs()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtPath(String str) {
        return new File(this.i, str).getAbsolutePath();
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtPath(String str, String str2) {
        return new File(getLuaExtDir(str), str2).getAbsolutePath();
    }

    @Override // com.androlua.LuaContext
    public String getLuaLpath() {
        return this.j;
    }

    @Override // com.androlua.LuaContext
    public String getLuaPath() {
        return null;
    }

    @Override // com.androlua.LuaContext
    public String getLuaPath(String str) {
        return null;
    }

    @Override // com.androlua.LuaContext
    public String getLuaPath(String str, String str2) {
        return null;
    }

    @Override // com.androlua.LuaContext
    public LuaState getLuaState() {
        return this.g;
    }

    @Override // com.androlua.LuaContext
    public String getProjectDir() {
        return this.o;
    }

    @Override // com.androlua.LuaContext
    public int getScreenHeight() {
        return this.f;
    }

    @Override // com.androlua.LuaContext
    public int getScreenWidth() {
        return this.e;
    }

    @Override // com.androlua.LuaContext
    public Object getSharedData(String str) {
        return LuaApplication.getInstance().getSharedData(str);
    }

    @Override // com.androlua.LuaContext
    public Object getSharedData(String str, Object obj) {
        return LuaApplication.getInstance().getSharedData(str, obj);
    }

    @Override // com.androlua.LuaContext
    public Object loadLib(String str) throws LuaException {
        int iIndexOf = str.indexOf(".");
        String strSubstring = iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
        if (!new File(roam.a.b.a.a.a.n(new StringBuilder(), this.h, "/lib", strSubstring, ".so")).exists()) {
            if (!new File(roam.a.b.a.a.a.n(new StringBuilder(), this.b, "/lib", strSubstring, ".so")).exists()) {
                throw new LuaException(roam.a.b.a.a.a.j("can not find lib ", str));
            }
            LuaUtil.copyFile(roam.a.b.a.a.a.n(new StringBuilder(), this.b, "/lib", strSubstring, ".so"), roam.a.b.a.a.a.n(new StringBuilder(), this.h, "/lib", strSubstring, ".so"));
        }
        return this.g.getLuaObject("require").call(str);
    }

    @Override // com.androlua.LuaContext
    public void regGc(LuaGcable luaGcable) {
        this.l.add(luaGcable);
    }

    @Override // com.androlua.LuaContext
    public Object runFunction(String str, Object... objArr) {
        LuaState luaState = this.g;
        if (luaState != null) {
            synchronized (luaState) {
                try {
                    this.g.setTop(0);
                    this.g.pushGlobalTable();
                    this.g.pushString(str);
                    this.g.rawGet(-2);
                    if (this.g.isFunction(-1)) {
                        this.g.getGlobal("debug");
                        this.g.getField(-1, "traceback");
                        this.g.remove(-2);
                        this.g.insert(-2);
                        int length = objArr.length;
                        for (Object obj : objArr) {
                            this.g.pushObjectValue(obj);
                        }
                        int iPcall = this.g.pcall(length, 1, (-2) - length);
                        if (iPcall == 0) {
                            return this.g.toJavaObject(-1);
                        }
                        throw new LuaException(LuaActivity.errorReason(iPcall) + ": " + this.g.toString(-1));
                    }
                } catch (LuaException e) {
                    sendError("runFunction", e);
                }
            }
        }
        return null;
    }

    @Override // com.androlua.LuaContext
    public void sendError(final String str, final Exception exc) {
        Log.d("fa2", "from:" + str + ",msg:" + exc);
        Runnable runnable = new Runnable(this, str, exc) { // from class: roam.b.c.a.a.d
            public final h a;
            public final String b;
            public final Exception c;

            {
                this.a = this;
                this.b = str;
                this.c = exc;
            }

            @Override // java.lang.Runnable
            public final void run() {
                h hVar = this.a;
                String str2 = this.b;
                Exception exc2 = this.c;
                Activity activity = hVar.a;
                StringBuilder sbD = roam.a.b.a.a.a.d(str2, Config.TRACE_TODAY_VISIT_SPLIT);
                sbD.append(exc2.getMessage());
                Toast.makeText(activity, sbD.toString(), 0).show();
            }
        };
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.a.runOnUiThread(runnable);
        }
        LuaFunction luaFunction = this.p;
        if (luaFunction != null) {
            try {
                luaFunction.call(str, exc);
            } catch (Exception e) {
            }
        }
    }

    @Override // com.androlua.LuaContext
    public void sendMessage(String str) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            HandlerCompat.createAsync(Looper.getMainLooper()).post(new c(this, str));
        } else {
            Toast.makeText(this.a, str, 0).show();
        }
    }

    @Override // com.androlua.LuaContext
    public void set(String str, Object obj) {
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0066  */
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
            goto L57
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
            r7.i = r4
        L46:
            int r3 = r3 + 1
            goto L2c
        L49:
            java.lang.String r0 = r7.i
            if (r0 != 0) goto L59
            android.app.Activity r0 = r7.a
            java.io.File r8 = r0.getDir(r8, r2)
            java.lang.String r8 = r8.getAbsolutePath()
        L57:
            r7.i = r8
        L59:
            java.io.File r8 = new java.io.File
            java.lang.String r0 = r7.i
            r8.<init>(r0)
            boolean r0 = r8.exists()
            if (r0 != 0) goto L69
            r8.mkdirs()
        L69:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: roam.b.c.a.a.h.setLuaExtDir(java.lang.String):void");
    }

    @Override // com.androlua.LuaContext
    public boolean setSharedData(String str, Object obj) {
        return LuaApplication.getInstance().setSharedData(str, obj);
    }
}

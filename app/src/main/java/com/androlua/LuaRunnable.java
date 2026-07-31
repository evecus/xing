package com.androlua;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.app.NotificationCompat;
import com.baidu.android.common.util.HanziToPinyin;
import com.baidu.mobstat.Config;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaMetaTable;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import java.io.IOException;
import java.util.regex.Pattern;
import org.roam.Application;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaRunnable extends Thread implements Runnable, LuaMetaTable, LuaGcable {
    private LuaState L;
    public boolean isRun;
    private Object[] mArg;
    private byte[] mBuffer;
    private boolean mGc;
    private boolean mIsLoop;
    private LuaContext mLuaContext;
    private String mSrc;
    private Handler thandler;

    public class ThreadHandler extends Handler {
        public final LuaRunnable this$0;

        private ThreadHandler(LuaRunnable luaRunnable) {
            this.this$0 = luaRunnable;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Bundle data = message.getData();
            int i = message.what;
            if (i == 0) {
                this.this$0.newLuaRunnable(data.getString("data"), (Object[]) data.getSerializable("args"));
                return;
            }
            if (i == 1) {
                this.this$0.runFunc(data.getString("data"), (Object[]) data.getSerializable("args"));
                return;
            }
            if (i == 2) {
                this.this$0.newLuaRunnable(data.getString("data"), new Object[0]);
            } else if (i == 3) {
                this.this$0.runFunc(data.getString("data"), new Object[0]);
            } else {
                if (i != 4) {
                    return;
                }
                this.this$0.setField(data.getString("data"), ((Object[]) data.getSerializable("args"))[0]);
            }
        }
    }

    public LuaRunnable(LuaContext luaContext, LuaObject luaObject) {
        this(luaContext, luaObject, false, (Object[]) null);
    }

    public LuaRunnable(LuaContext luaContext, LuaObject luaObject, boolean z) {
        this(luaContext, luaObject, z, (Object[]) null);
    }

    public LuaRunnable(LuaContext luaContext, LuaObject luaObject, boolean z, Object[] objArr) {
        this.isRun = false;
        this.mArg = new Object[0];
        this.mLuaContext = luaContext;
        if (objArr != null) {
            this.mArg = objArr;
        }
        this.mIsLoop = z;
        this.mBuffer = luaObject.dump();
    }

    public LuaRunnable(LuaContext luaContext, LuaObject luaObject, Object[] objArr) {
        this(luaContext, luaObject, false, objArr);
    }

    public LuaRunnable(LuaContext luaContext, String str) {
        this(luaContext, str, false, (Object[]) null);
    }

    public LuaRunnable(LuaContext luaContext, String str, boolean z) {
        this(luaContext, str, z, (Object[]) null);
    }

    public LuaRunnable(LuaContext luaContext, String str, boolean z, Object[] objArr) {
        this.isRun = false;
        this.mArg = new Object[0];
        luaContext.regGc(this);
        this.mLuaContext = luaContext;
        this.mSrc = str;
        this.mIsLoop = z;
        if (objArr != null) {
            this.mArg = objArr;
        }
    }

    public LuaRunnable(LuaContext luaContext, String str, Object[] objArr) {
        this(luaContext, str, false, objArr);
    }

    private void doFile(String str, Object... objArr) throws LuaException {
        this.L.setTop(0);
        int iLloadFile = this.L.LloadFile(str);
        if (iLloadFile == 0) {
            this.L.getGlobal("debug");
            this.L.getField(-1, "traceback");
            this.L.remove(-2);
            this.L.insert(-2);
            int length = objArr.length;
            for (Object obj : objArr) {
                this.L.pushObjectValue(obj);
            }
            iLloadFile = this.L.pcall(length, 0, (-2) - length);
            if (iLloadFile == 0) {
                return;
            }
        }
        throw new LuaException(errorReason(iLloadFile) + ": " + this.L.toString(-1));
    }

    private void doString(String str, Object... objArr) throws LuaException {
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
            iLloadString = this.L.pcall(length, 0, (-2) - length);
            if (iLloadString == 0) {
                return;
            }
        }
        throw new LuaException(errorReason(iLloadString) + ": " + this.L.toString(-1));
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

    private void initLua() {
        LuaState luaState;
        String str;
        LuaState luaStateNewLuaState = LuaStateFactory.newLuaState();
        this.L = luaStateNewLuaState;
        luaStateNewLuaState.openLibs();
        this.L.pushJavaObject(this.mLuaContext.getContext());
        LuaContext luaContext = this.mLuaContext;
        if (!(luaContext instanceof LuaActivity) && !(luaContext.getContext() instanceof Application)) {
            if (this.mLuaContext instanceof LuaService) {
                luaState = this.L;
                str = NotificationCompat.CATEGORY_SERVICE;
            }
            this.L.pushJavaObject(this);
            this.L.setGlobal("this");
            this.L.pushContext(this.mLuaContext);
            new LuaPrint(this.mLuaContext, this.L).register("print");
            this.L.getGlobal("package");
            this.L.pushString(this.mLuaContext.getLuaLpath());
            this.L.setField(-2, Config.FEED_LIST_ITEM_PATH);
            this.L.pushString(this.mLuaContext.getLuaCpath());
            this.L.setField(-2, "cpath");
            this.L.pop(1);
            new JavaFunction(this, this.L) { // from class: com.androlua.LuaRunnable.2
                public final LuaRunnable this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.luajava.JavaFunction
                public int execute() {
                    this.this$0.mLuaContext.set(((JavaFunction) this).L.toString(2), ((JavaFunction) this).L.toJavaObject(3));
                    return 0;
                }
            }.register("set");
            new JavaFunction(this, this.L) { // from class: com.androlua.LuaRunnable.3
                public final LuaRunnable this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.luajava.JavaFunction
                public int execute() {
                    int top = ((JavaFunction) this).L.getTop();
                    if (top > 2) {
                        Object[] objArr = new Object[top - 2];
                        for (int i = 3; i <= top; i++) {
                            objArr[i - 3] = ((JavaFunction) this).L.toJavaObject(i);
                        }
                        this.this$0.mLuaContext.call(((JavaFunction) this).L.toString(2), objArr);
                    } else if (top == 2) {
                        this.this$0.mLuaContext.call(((JavaFunction) this).L.toString(2), new Object[0]);
                    }
                    return 0;
                }
            }.register(NotificationCompat.CATEGORY_CALL);
        }
        luaState = this.L;
        str = ActivityChooserModel.ATTRIBUTE_ACTIVITY;
        luaState.setGlobal(str);
        this.L.pushJavaObject(this);
        this.L.setGlobal("this");
        this.L.pushContext(this.mLuaContext);
        new LuaPrint(this.mLuaContext, this.L).register("print");
        this.L.getGlobal("package");
        this.L.pushString(this.mLuaContext.getLuaLpath());
        this.L.setField(-2, Config.FEED_LIST_ITEM_PATH);
        this.L.pushString(this.mLuaContext.getLuaCpath());
        this.L.setField(-2, "cpath");
        this.L.pop(1);
        new JavaFunction(this, this.L) { // from class: com.androlua.LuaRunnable.2
            public final LuaRunnable this$0;

            {
                this.this$0 = this;
            }

            @Override // com.luajava.JavaFunction
            public int execute() {
                this.this$0.mLuaContext.set(((JavaFunction) this).L.toString(2), ((JavaFunction) this).L.toJavaObject(3));
                return 0;
            }
        }.register("set");
        new JavaFunction(this, this.L) { // from class: com.androlua.LuaRunnable.3
            public final LuaRunnable this$0;

            {
                this.this$0 = this;
            }

            @Override // com.luajava.JavaFunction
            public int execute() {
                int top = ((JavaFunction) this).L.getTop();
                if (top > 2) {
                    Object[] objArr = new Object[top - 2];
                    for (int i = 3; i <= top; i++) {
                        objArr[i - 3] = ((JavaFunction) this).L.toJavaObject(i);
                    }
                    this.this$0.mLuaContext.call(((JavaFunction) this).L.toString(2), objArr);
                } else if (top == 2) {
                    this.this$0.mLuaContext.call(((JavaFunction) this).L.toString(2), new Object[0]);
                }
                return 0;
            }
        }.register(NotificationCompat.CATEGORY_CALL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void newLuaRunnable(String str, Object... objArr) {
        try {
            if (Pattern.matches("^\\w+$", str)) {
                doAsset(str + ".lua", objArr);
            } else if (Pattern.matches("^[\\w\\.\\_/]+$", str)) {
                this.L.getGlobal("luajava");
                this.L.pushString(this.mLuaContext.getLuaDir());
                this.L.setField(-2, "luadir");
                this.L.pushString(str);
                this.L.setField(-2, "luapath");
                this.L.pop(1);
                doFile(str, objArr);
            } else {
                doString(str, objArr);
            }
        } catch (Exception e) {
            this.mLuaContext.sendMessage(toString() + HanziToPinyin.Token.SEPARATOR + e.getMessage());
            quit();
        }
    }

    private void newLuaRunnable(byte[] bArr, Object... objArr) {
        try {
            this.L.setTop(0);
            int iLloadBuffer = this.L.LloadBuffer(bArr, "TimerTask");
            if (iLloadBuffer == 0) {
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
                    return;
                }
            }
            throw new LuaException(errorReason(iLloadBuffer) + ": " + this.L.toString(-1));
        } catch (Exception e) {
            this.mLuaContext.sendMessage(toString() + HanziToPinyin.Token.SEPARATOR + e.getMessage());
            quit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runFunc(String str, Object... objArr) {
        try {
            this.L.setTop(0);
            this.L.getGlobal(str);
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
                    return;
                }
                throw new LuaException(errorReason(iPcall) + ": " + this.L.toString(-1));
            }
        } catch (LuaException e) {
            LuaContext luaContext = this.mLuaContext;
            StringBuilder sbD = a.d(str, HanziToPinyin.Token.SEPARATOR);
            sbD.append(e.getMessage());
            luaContext.sendMessage(sbD.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setField(String str, Object obj) {
        try {
            this.L.pushObjectValue(obj);
            this.L.setGlobal(str);
        } catch (LuaException e) {
            this.mLuaContext.sendMessage(e.getMessage());
        }
    }

    @Override // com.luajava.LuaMetaTable
    public Object __call(Object[] objArr) {
        return null;
    }

    @Override // com.luajava.LuaMetaTable
    public Object __index(String str) {
        return new LuaMetaTable(this, str) { // from class: com.androlua.LuaRunnable.1
            public final LuaRunnable this$0;
            public final String val$key;

            {
                this.this$0 = this;
                this.val$key = str;
            }

            @Override // com.luajava.LuaMetaTable
            public Object __call(Object[] objArr) {
                this.this$0.call(this.val$key, objArr);
                return null;
            }

            @Override // com.luajava.LuaMetaTable
            public Object __index(String str2) {
                return null;
            }

            @Override // com.luajava.LuaMetaTable
            public void __newIndex(String str2, Object obj) {
            }
        };
    }

    @Override // com.luajava.LuaMetaTable
    public void __newIndex(String str, Object obj) {
        set(str, obj);
    }

    public void call(String str) {
        push(3, str);
    }

    public void call(String str, Object[] objArr) {
        if (objArr.length == 0) {
            push(3, str);
        } else {
            push(1, str, objArr);
        }
    }

    public void doAsset(String str, Object... objArr) throws LuaException, IOException {
        byte[] asset = LuaUtil.readAsset(this.mLuaContext.getContext(), str);
        this.L.setTop(0);
        int iLloadBuffer = this.L.LloadBuffer(asset, str);
        if (iLloadBuffer == 0) {
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
                return;
            }
        }
        throw new LuaException(errorReason(iLloadBuffer) + ": " + this.L.toString(-1));
    }

    @Override // com.androlua.LuaGcable
    public void gc() {
        quit();
        this.mGc = true;
    }

    public Object get(String str) {
        this.L.getGlobal(str);
        return this.L.toJavaObject(-1);
    }

    @Override // com.androlua.LuaGcable
    public boolean isGc() {
        return this.mGc;
    }

    public void push(int i, String str) {
        if (!this.isRun) {
            this.mLuaContext.sendMessage("thread is not running");
            return;
        }
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        message.setData(bundle);
        message.what = i;
        this.thandler.sendMessage(message);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void push(int i, String str, Object[] objArr) {
        if (!this.isRun) {
            this.mLuaContext.sendMessage("thread is not running");
            return;
        }
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        bundle.putSerializable("args", objArr);
        message.setData(bundle);
        message.what = i;
        this.thandler.sendMessage(message);
    }

    public void quit() {
        if (this.isRun) {
            this.isRun = false;
            this.thandler.getLooper().quit();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            if (this.L == null) {
                initLua();
                byte[] bArr = this.mBuffer;
                if (bArr != null) {
                    newLuaRunnable(bArr, this.mArg);
                } else {
                    newLuaRunnable(this.mSrc, this.mArg);
                }
            }
            if (this.mIsLoop) {
                Looper.prepare();
                this.thandler = new ThreadHandler();
                this.isRun = true;
                this.L.getGlobal("run");
                if (!this.L.isNil(-1)) {
                    this.L.pop(1);
                    runFunc("run", new Object[0]);
                }
                Looper.loop();
            }
            this.isRun = false;
            this.L.gc(2, 1);
            System.gc();
        } catch (LuaException e) {
            this.mLuaContext.sendMessage(e.getMessage());
        }
    }

    public void set(String str, Object obj) {
        push(4, str, new Object[]{obj});
    }
}

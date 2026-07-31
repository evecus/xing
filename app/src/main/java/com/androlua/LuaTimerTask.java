package com.androlua;

import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.app.NotificationCompat;
import com.androlua.util.TimerTaskX;
import com.baidu.android.common.util.HanziToPinyin;
import com.baidu.mobstat.Config;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import java.io.IOException;
import java.util.regex.Pattern;
import org.roam.Application;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaTimerTask extends TimerTaskX {
    private LuaState L;
    private Object[] mArg;
    private byte[] mBuffer;
    private boolean mEnabled;
    private LuaContext mLuaContext;
    private String mSrc;

    public LuaTimerTask(LuaContext luaContext, LuaObject luaObject) {
        this(luaContext, luaObject, (Object[]) null);
    }

    public LuaTimerTask(LuaContext luaContext, LuaObject luaObject, Object[] objArr) {
        this.mArg = new Object[0];
        this.mEnabled = true;
        this.mLuaContext = luaContext;
        if (objArr != null) {
            this.mArg = objArr;
        }
        this.mBuffer = luaObject.dump();
    }

    public LuaTimerTask(LuaContext luaContext, String str) {
        this(luaContext, str, (Object[]) null);
    }

    public LuaTimerTask(LuaContext luaContext, String str, Object[] objArr) {
        this.mArg = new Object[0];
        this.mEnabled = true;
        this.mLuaContext = luaContext;
        this.mSrc = str;
        if (objArr != null) {
            this.mArg = objArr;
        }
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
            new JavaFunction(this, this.L) { // from class: com.androlua.LuaTimerTask.1
                public final LuaTimerTask this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.luajava.JavaFunction
                public int execute() {
                    this.this$0.mLuaContext.set(((JavaFunction) this).L.toString(2), ((JavaFunction) this).L.toJavaObject(3));
                    return 0;
                }
            }.register("set");
            new JavaFunction(this, this.L) { // from class: com.androlua.LuaTimerTask.2
                public final LuaTimerTask this$0;

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
        new JavaFunction(this, this.L) { // from class: com.androlua.LuaTimerTask.1
            public final LuaTimerTask this$0;

            {
                this.this$0 = this;
            }

            @Override // com.luajava.JavaFunction
            public int execute() {
                this.this$0.mLuaContext.set(((JavaFunction) this).L.toString(2), ((JavaFunction) this).L.toJavaObject(3));
                return 0;
            }
        }.register("set");
        new JavaFunction(this, this.L) { // from class: com.androlua.LuaTimerTask.2
            public final LuaTimerTask this$0;

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

    private void newLuaThread(String str, Object... objArr) {
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
            this.mLuaContext.sendError(toString(), e);
        }
    }

    private void newLuaThread(byte[] bArr, Object... objArr) throws LuaException {
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
    }

    private void runFunc(String str, Object... objArr) {
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
            this.mLuaContext.sendError(toString() + HanziToPinyin.Token.SEPARATOR + str, e);
        }
    }

    private void setField(String str, Object obj) {
        try {
            this.L.pushObjectValue(obj);
            this.L.setGlobal(str);
        } catch (LuaException e) {
            this.mLuaContext.sendError(toString(), e);
        }
    }

    @Override // com.androlua.util.TimerTaskX
    public boolean cancel() {
        return super.cancel();
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

    public Object get(String str) {
        this.L.getGlobal(str);
        return this.L.toJavaObject(-1);
    }

    @Override // com.androlua.util.TimerTaskX
    public boolean isEnabled() {
        return this.mEnabled;
    }

    @Override // com.androlua.util.TimerTaskX, java.lang.Runnable
    public void run() {
        if (this.mEnabled) {
            try {
                LuaState luaState = this.L;
                if (luaState == null) {
                    initLua();
                    byte[] bArr = this.mBuffer;
                    if (bArr != null) {
                        newLuaThread(bArr, this.mArg);
                    } else {
                        newLuaThread(this.mSrc, this.mArg);
                    }
                } else {
                    luaState.getGlobal("run");
                    if (this.L.isNil(-1)) {
                        byte[] bArr2 = this.mBuffer;
                        if (bArr2 != null) {
                            newLuaThread(bArr2, this.mArg);
                        } else {
                            newLuaThread(this.mSrc, this.mArg);
                        }
                    } else {
                        runFunc("run", new Object[0]);
                    }
                }
            } catch (LuaException e) {
                this.mLuaContext.sendError(toString(), e);
            }
            this.L.gc(2, 1);
            System.gc();
        }
    }

    public void set(String str, Object obj) {
        this.L.pushObjectValue(obj);
        this.L.setGlobal(str);
    }

    public void setArg(LuaObject luaObject) {
        this.mArg = luaObject.asArray();
    }

    public void setArg(Object[] objArr) {
        this.mArg = objArr;
    }

    @Override // com.androlua.util.TimerTaskX
    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }
}

package com.androlua;

import com.androlua.util.AsyncTaskX;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class LuaAsyncTask extends AsyncTaskX implements LuaGcable {
    private LuaState L;
    private Object[] loadeds;
    private byte[] mBuffer;
    private LuaObject mCallback;
    private long mDelay;
    private boolean mGc;
    private LuaContext mLuaContext;
    private LuaObject mUpdate;

    static {
        AsyncTaskX.setDefaultExecutor(AsyncTaskX.THREAD_POOL_EXECUTOR);
    }

    public LuaAsyncTask(LuaContext luaContext, long j, LuaObject luaObject) {
        this.mDelay = 0L;
        luaContext.regGc(this);
        this.mLuaContext = luaContext;
        this.mDelay = j;
        this.mCallback = luaObject;
    }

    public LuaAsyncTask(LuaContext luaContext, LuaObject luaObject, LuaObject luaObject2) {
        this.mDelay = 0L;
        luaContext.regGc(this);
        this.mLuaContext = luaContext;
        this.mBuffer = luaObject.dump();
        this.mCallback = luaObject2;
        LuaObject field = luaObject.getLuaState().getLuaObject("luajava").getField("imported");
        if (field.isNil()) {
            return;
        }
        this.loadeds = field.asArray();
    }

    public LuaAsyncTask(LuaContext luaContext, LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3) {
        this.mDelay = 0L;
        luaContext.regGc(this);
        this.mLuaContext = luaContext;
        this.mBuffer = luaObject.dump();
        this.mUpdate = luaObject2;
        this.mCallback = luaObject3;
    }

    public LuaAsyncTask(LuaContext luaContext, String str, LuaObject luaObject) {
        this.mDelay = 0L;
        luaContext.regGc(this);
        this.mLuaContext = luaContext;
        this.mBuffer = str.getBytes();
        this.mCallback = luaObject;
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

    /* JADX WARN: Can't wrap try/catch for region: R(8:8|(6:(1:16)(12:13|(1:15)|18|52|19|23|(4:25|47|26|(2:28|29))|49|31|(5:33|(1:35)|54|36|(5:38|(1:40)|55|41|56))|42|43)|49|31|(0)|42|43)|17|18|52|19|23|(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00c5, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00c6, code lost:
    
        r9.mLuaContext.sendError("AsyncTask", r1);
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0110 A[Catch: Exception -> 0x017e, TryCatch #1 {Exception -> 0x017e, blocks: (B:31:0x00fe, B:33:0x0110, B:35:0x012c, B:36:0x0136, B:38:0x0140, B:40:0x014b, B:42:0x015a, B:43:0x017d), top: B:49:0x00fe }] */
    @Override // com.androlua.util.AsyncTaskX
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object doInBackground(java.lang.Object[] r10) {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaAsyncTask.doInBackground(java.lang.Object[]):java.lang.Object");
    }

    public void execute() {
        super.execute(new Object[0]);
    }

    @Override // com.androlua.LuaGcable
    public void gc() {
        if (getStatus() == AsyncTaskX.Status.RUNNING) {
            cancel(true);
        }
        this.mGc = true;
    }

    @Override // com.androlua.LuaGcable
    public boolean isGc() {
        return this.mGc;
    }

    @Override // com.androlua.util.AsyncTaskX
    public void onPostExecute(Object obj) {
        if (isCancelled()) {
            return;
        }
        try {
            LuaObject luaObject = this.mCallback;
            if (luaObject != null) {
                luaObject.call((Object[]) obj);
            }
        } catch (LuaException e) {
            this.mLuaContext.sendError("onPostExecute", e);
        }
        LuaState luaState = this.L;
        if (luaState != null) {
            luaState.gc(2, 1);
        }
        System.gc();
    }

    @Override // com.androlua.util.AsyncTaskX
    public void onProgressUpdate(Object[] objArr) {
        try {
            LuaObject luaObject = this.mUpdate;
            if (luaObject != null) {
                luaObject.call(objArr);
            }
        } catch (LuaException e) {
            this.mLuaContext.sendError("onProgressUpdate", e);
        }
        super.onProgressUpdate(objArr);
    }

    public void update(int i) {
        publishProgress(Integer.valueOf(i));
    }

    public void update(Object obj) {
        publishProgress(obj);
    }

    public void update(String str) {
        publishProgress(str);
    }
}

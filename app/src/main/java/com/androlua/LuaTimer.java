package com.androlua;

import com.androlua.util.TimerX;
import com.luajava.LuaObject;

/* JADX INFO: loaded from: classes.dex */
public class LuaTimer extends TimerX implements LuaGcable {
    private boolean mGc;
    private LuaTimerTask task;

    public LuaTimer(LuaContext luaContext, LuaObject luaObject) {
        this(luaContext, luaObject, (Object[]) null);
    }

    public LuaTimer(LuaContext luaContext, LuaObject luaObject, Object[] objArr) {
        super("LuaTimer");
        luaContext.regGc(this);
        this.task = new LuaTimerTask(luaContext, luaObject, objArr);
    }

    public LuaTimer(LuaContext luaContext, String str) {
        this(luaContext, str, (Object[]) null);
    }

    public LuaTimer(LuaContext luaContext, String str, Object[] objArr) {
        super("LuaTimer");
        luaContext.regGc(this);
        this.task = new LuaTimerTask(luaContext, str, objArr);
    }

    @Override // com.androlua.LuaGcable
    public void gc() {
        stop();
        this.mGc = true;
    }

    public boolean getEnabled() {
        return this.task.isEnabled();
    }

    public long getPeriod() {
        return this.task.getPeriod();
    }

    public boolean isEnabled() {
        return this.task.isEnabled();
    }

    @Override // com.androlua.LuaGcable
    public boolean isGc() {
        return this.mGc;
    }

    public void setEnabled(boolean z) {
        this.task.setEnabled(z);
    }

    public void setPeriod(long j) {
        this.task.setPeriod(j);
    }

    public void start(long j) {
        schedule(this.task, j);
    }

    public void start(long j, long j2) {
        schedule(this.task, j, j2);
    }

    public void stop() {
        this.task.cancel();
    }
}

package com.luajava;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class LuaStateFactory {
    private static final Map<Long, LuaState> a = new HashMap();

    private LuaStateFactory() {
    }

    public static LuaState getExistingState(long j) {
        LuaState luaState;
        synchronized (LuaStateFactory.class) {
            try {
                Map<Long, LuaState> map = a;
                luaState = map.get(Long.valueOf(j));
                if (luaState == null) {
                    luaState = new LuaState(j);
                    map.put(Long.valueOf(j), luaState);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return luaState;
    }

    public static long insertLuaState(LuaState luaState) {
        long pointer;
        synchronized (LuaStateFactory.class) {
            try {
                a.put(Long.valueOf(luaState.getPointer()), luaState);
                pointer = luaState.getPointer();
            } catch (Throwable th) {
                throw th;
            }
        }
        return pointer;
    }

    public static LuaState newLuaState() {
        LuaState luaState;
        synchronized (LuaStateFactory.class) {
            try {
                luaState = new LuaState();
                a.put(Long.valueOf(luaState.getPointer()), luaState);
            } catch (Throwable th) {
                throw th;
            }
        }
        return luaState;
    }

    public static void removeLuaState(long j) {
        synchronized (LuaStateFactory.class) {
            try {
                a.put(Long.valueOf(j), null);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

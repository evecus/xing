package com.androlua;

import android.content.Context;
import com.luajava.LuaState;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface LuaContext {
    void call(String str, Object... objArr);

    Object doFile(String str);

    Object doFile(String str, Object... objArr);

    Context getContext();

    String getFusionDir();

    Map getGlobalData();

    String getLuaCpath();

    LuaDexLoader getLuaDexLoader();

    String getLuaDir();

    String getLuaDir(String str);

    String getLuaExtDir();

    String getLuaExtDir(String str);

    String getLuaExtPath(String str);

    String getLuaExtPath(String str, String str2);

    String getLuaLpath();

    String getLuaPath();

    String getLuaPath(String str);

    String getLuaPath(String str, String str2);

    LuaState getLuaState();

    String getProjectDir();

    int getScreenHeight();

    int getScreenWidth();

    Object getSharedData(String str);

    Object getSharedData(String str, Object obj);

    Object loadLib(String str);

    void regGc(LuaGcable luaGcable);

    Object runFunction(String str, Object... objArr);

    void sendError(String str, Exception exc);

    void sendMessage(String str);

    void set(String str, Object obj);

    void setLuaExtDir(String str);

    boolean setSharedData(String str, Object obj);
}

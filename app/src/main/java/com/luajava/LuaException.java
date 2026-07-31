package com.luajava;

/* JADX INFO: loaded from: classes.dex */
public class LuaException extends Exception {
    public LuaException(Exception exc) {
        super(exc.getCause() != null ? exc.getCause() : exc);
    }

    public LuaException(String str) {
        super(str);
    }
}

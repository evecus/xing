package com.androlua;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ZipUtil {
    public static boolean unzip(String str, String str2) {
        try {
            LuaUtil.unZip(str, str2);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean zip(String str, String str2) {
        return LuaUtil.zip(str, str2);
    }
}

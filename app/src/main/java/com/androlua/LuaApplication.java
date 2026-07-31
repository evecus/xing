package com.androlua;

import android.app.Application;
import android.content.Context;
import android.content.FileProvider;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.Toast;
import com.luajava.LuaState;
import com.luajava.LuaTable;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class LuaApplication extends Application implements LuaContext {
    private static HashMap<String, Object> data = new HashMap<>();
    private static LuaApplication mApp;
    private String fusionDir;
    private boolean isUpdata;
    public String libDir;
    public String localDir;
    public String luaCpath;
    public String luaExtDir;
    public String luaLpath;
    public String luaMdDir;
    private SharedPreferences mSharedPreferences;
    public String odexDir;

    public static LuaApplication getInstance() {
        return mApp;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        Context contextCreateDeviceProtectedStorageContext = context.createDeviceProtectedStorageContext();
        return contextCreateDeviceProtectedStorageContext != null ? PreferenceManager.getDefaultSharedPreferences(contextCreateDeviceProtectedStorageContext) : PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override // com.androlua.LuaContext
    public void call(String str, Object[] objArr) {
    }

    @Override // com.androlua.LuaContext
    public Object doFile(String str) {
        return null;
    }

    @Override // com.androlua.LuaContext
    public Object doFile(String str, Object[] objArr) {
        return null;
    }

    public Object get(String str) {
        return data.get(str);
    }

    @Override // com.androlua.LuaContext
    public Context getContext() {
        return this;
    }

    @Override // com.androlua.LuaContext
    public String getFusionDir() {
        return this.fusionDir;
    }

    @Override // com.androlua.LuaContext
    public Map getGlobalData() {
        return data;
    }

    public String getLibDir() {
        return this.libDir;
    }

    public String getLocalDir() {
        return this.localDir;
    }

    @Override // com.androlua.LuaContext
    public String getLuaCpath() {
        return this.luaCpath;
    }

    @Override // com.androlua.LuaContext
    public LuaDexLoader getLuaDexLoader() {
        return null;
    }

    @Override // com.androlua.LuaContext
    public String getLuaDir() {
        return this.localDir;
    }

    @Override // com.androlua.LuaContext
    public String getLuaDir(String str) {
        return this.localDir;
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtDir() {
        return this.luaExtDir;
    }

    @Override // com.androlua.LuaContext
    public String getLuaExtDir(String str) {
        File file = new File(getLuaExtDir(), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
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
        return null;
    }

    @Override // com.androlua.LuaContext
    public String getLuaPath(String str) {
        return new File(getLuaDir(), str).getAbsolutePath();
    }

    @Override // com.androlua.LuaContext
    public String getLuaPath(String str, String str2) {
        return new File(getLuaDir(str), str2).getAbsolutePath();
    }

    @Override // com.androlua.LuaContext
    public LuaState getLuaState() {
        return null;
    }

    public String getMdDir() {
        return this.luaMdDir;
    }

    public String getOdexDir() {
        return this.odexDir;
    }

    public String getPathFromUri(Uri uri) {
        Cursor cursorQuery;
        int columnIndexOrThrow;
        if (uri != null) {
            String packageName = getPackageName();
            String scheme = uri.getScheme();
            scheme.hashCode();
            if (scheme.equals("file")) {
                return uri.getPath();
            }
            if (scheme.equals("content") && (cursorQuery = getContentResolver().query(uri, new String[]{packageName}, null, null, null)) != null && (columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow(getPackageName())) >= 0) {
                String string = cursorQuery.getString(columnIndexOrThrow);
                cursorQuery.moveToFirst();
                cursorQuery.close();
                return string;
            }
        }
        return null;
    }

    @Override // com.androlua.LuaContext
    public String getProjectDir() {
        return null;
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
        return this.mSharedPreferences.getAll().get(str);
    }

    @Override // com.androlua.LuaContext
    public Object getSharedData(String str, Object obj) {
        Object obj2 = this.mSharedPreferences.getAll().get(str);
        return obj2 == null ? obj : obj2;
    }

    public Uri getUriForFile(File file) {
        return FileProvider.getUriForFile(this, getPackageName(), file);
    }

    public Uri getUriForPath(String str) {
        return FileProvider.getUriForFile(this, getPackageName(), new File(str));
    }

    @Override // com.androlua.LuaContext
    public Object loadLib(String str) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0083  */
    @Override // android.app.Application
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCreate() {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaApplication.onCreate():void");
    }

    @Override // com.androlua.LuaContext
    public void regGc(LuaGcable luaGcable) {
    }

    @Override // com.androlua.LuaContext
    public Object runFunction(String str, Object... objArr) {
        return null;
    }

    @Override // com.androlua.LuaContext
    public void sendError(String str, Exception exc) {
    }

    @Override // com.androlua.LuaContext
    public void sendMessage(String str) {
        Toast.makeText(this, str, 0).show();
    }

    @Override // com.androlua.LuaContext
    public void set(String str, Object obj) {
        data.put(str, obj);
    }

    @Override // com.androlua.LuaContext
    public void setLuaExtDir(String str) {
        String absolutePath;
        if (Environment.getExternalStorageState().equals("mounted")) {
            absolutePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), str).getAbsolutePath();
        } else {
            for (File file : new File("/storage").listFiles()) {
                String[] list = file.list();
                if (list != null && list.length > 5) {
                    this.luaExtDir = new File(file, str).getAbsolutePath();
                }
            }
            if (this.luaExtDir != null) {
                return;
            } else {
                absolutePath = getDir(str, 0).getAbsolutePath();
            }
        }
        this.luaExtDir = absolutePath;
    }

    @Override // com.androlua.LuaContext
    public boolean setSharedData(String str, Object obj) {
        Set<String> set;
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        if (obj == null) {
            editorEdit.remove(str);
        } else if (obj instanceof String) {
            editorEdit.putString(str, obj.toString());
        } else if (obj instanceof Long) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Float) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else {
            if (obj instanceof Set) {
                set = (Set) obj;
            } else if (obj instanceof LuaTable) {
                set = (HashSet) ((LuaTable) obj).values();
            } else {
                if (!(obj instanceof Boolean)) {
                    return false;
                }
                editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
            }
            editorEdit.putStringSet(str, set);
        }
        editorEdit.apply();
        return true;
    }
}

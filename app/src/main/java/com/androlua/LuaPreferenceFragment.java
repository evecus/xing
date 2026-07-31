package com.androlua;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import com.luajava.LuaException;
import com.luajava.LuaJavaAPI;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaTable;

/* JADX INFO: loaded from: classes.dex */
public class LuaPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private Preference.OnPreferenceChangeListener mOnPreferenceChangeListener;
    private Preference.OnPreferenceClickListener mOnPreferenceClickListener;
    private LuaTable<Integer, LuaTable> mPreferences;

    private void init(LuaTable<Integer, LuaTable> luaTable) {
        LuaObject i;
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int length = luaTable.length();
        LuaState luaState = luaTable.getLuaState();
        for (int i2 = 1; i2 <= length; i2++) {
            LuaTable luaTable2 = luaTable.get(Integer.valueOf(i2));
            try {
                i = luaTable2.getI(1L);
            } catch (Exception e) {
                luaState.getContext().sendError("LuaPreferenceFragment", e);
            }
            if (i.isNil()) {
                throw new IllegalArgumentException("Fist value Must be a Class<Preference>, checked import package.");
            }
            Preference preference = (Preference) i.call(getActivity());
            for (LuaTable.LuaEntry luaEntry : luaTable2.entrySet()) {
                Object key = luaEntry.getKey();
                if (key instanceof String) {
                    try {
                        LuaJavaAPI.javaSetter(luaState, preference, (String) key, luaEntry.getValue());
                    } catch (LuaException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            preference.setOnPreferenceChangeListener(this);
            preference.setOnPreferenceClickListener(this);
            preferenceScreen.addPreference(preference);
            luaState.getContext().sendError("LuaPreferenceFragment", e);
        }
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getPreferenceManager().setStorageDeviceProtected();
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));
        init(this.mPreferences);
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        Preference.OnPreferenceChangeListener onPreferenceChangeListener = this.mOnPreferenceChangeListener;
        if (onPreferenceChangeListener != null) {
            return onPreferenceChangeListener.onPreferenceChange(preference, obj);
        }
        return true;
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        Preference.OnPreferenceClickListener onPreferenceClickListener = this.mOnPreferenceClickListener;
        if (onPreferenceClickListener != null) {
            return onPreferenceClickListener.onPreferenceClick(preference);
        }
        return false;
    }

    public void setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener onPreferenceChangeListener) {
        this.mOnPreferenceChangeListener = onPreferenceChangeListener;
    }

    public void setOnPreferenceClickListener(Preference.OnPreferenceClickListener onPreferenceClickListener) {
        this.mOnPreferenceClickListener = onPreferenceClickListener;
    }

    public void setPreference(LuaTable<Integer, LuaTable> luaTable) {
        this.mPreferences = luaTable;
    }
}

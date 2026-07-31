package com.androlua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.luajava.LuaObject;
import com.luajava.LuaTable;

/* JADX INFO: loaded from: classes.dex */
public class LuaFragment extends Fragment {
    private LuaTable mLayout = null;
    private LuaObject mLoadLayout = null;
    private View mView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            View view = this.mView;
            if (view != null) {
                return view;
            }
            LuaTable luaTable = this.mLayout;
            return luaTable != null ? (View) ((LuaObject) luaTable.getLuaState().getLuaObject("require").call("loadlayout")).call(this.mLayout) : new TextView(getActivity());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void setLayout(View view) {
        this.mView = view;
        this.mLayout = null;
    }

    public void setLayout(LuaTable luaTable) {
        this.mLayout = luaTable;
        this.mView = null;
    }
}

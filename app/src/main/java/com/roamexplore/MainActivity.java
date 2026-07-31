package com.roamexplore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.androlua.LuaApplication;
import com.androlua.LuaContext;
import com.androlua.LuaDexLoader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaTable;
import com.roamexplore.MainActivity;
import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.roam.Application;
import org.roam.R;
import org.roam.config.ViewConfig;
import org.roam.loader.Loader;
import org.roam.loader.StorageLoader;
import org.roam.ui.FusionUiCoreManger;
import org.roam.ui.UiManager;
import org.roam.util.UiUtil;
import roam.a.b.a.a.a;
import roam.b.c.a.a.h;
import roam.b.c.a.a.k.s.c;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity implements Application {
    public static final int k = 0;
    public UiManager a;
    public h b;
    public Loader c;
    public LuaObject d;
    public LuaObject e;
    public LuaObject f;
    public LuaObject g;
    public LuaObject h;
    public File i;
    public Object[] j;

    public static Intent getIntent(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.putExtra("project_key", str);
        intent.putExtra("page_name_key", str2);
        return intent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Intent getLuaIntent(Context context, File file, Object... objArr) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.putExtra("file_lua_key", file.getAbsolutePath());
        if (objArr != 0) {
            intent.putExtra("args", (Serializable) objArr);
        }
        return intent;
    }

    public final void a() {
        LuaState luaState;
        h hVar = this.b;
        if (hVar == null || (luaState = hVar.g) == null) {
            return;
        }
        LuaObject luaObject = luaState.getLuaObject("onKeyShortcut");
        this.h = luaObject;
        if (luaObject.isNil()) {
            this.h = null;
        }
        LuaObject luaObject2 = luaState.getLuaObject("onKeyDown");
        this.d = luaObject2;
        if (luaObject2.isNil()) {
            this.d = null;
        }
        LuaObject luaObject3 = luaState.getLuaObject("onKeyUp");
        this.e = luaObject3;
        if (luaObject3.isNil()) {
            this.e = null;
        }
        LuaObject luaObject4 = luaState.getLuaObject("onKeyLongPress");
        this.f = luaObject4;
        if (luaObject4.isNil()) {
            this.f = null;
        }
        LuaObject luaObject5 = luaState.getLuaObject("onTouchEvent");
        this.g = luaObject5;
        if (luaObject5.isNil()) {
            this.g = null;
        }
    }

    public final File b() {
        if (!isDevMode()) {
            return new File(LuaApplication.getInstance().getMdDir());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getExternalFilesDir(null).getAbsolutePath());
        File file = new File(a.l(sb, File.separatorChar, "modules"));
        if (file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    public final boolean c(LuaObject luaObject, int i, KeyEvent keyEvent) {
        if (luaObject == null) {
            return false;
        }
        try {
            Object objCall = luaObject.call(Integer.valueOf(i), keyEvent);
            if (objCall == null || objCall.getClass() != Boolean.class) {
                return false;
            }
            return ((Boolean) objCall).booleanValue();
        } catch (LuaException e) {
            this.b.sendError("key event:", e);
            return false;
        }
    }

    public final void d() {
        if (getLoader() == null) {
            return;
        }
        String str = getLoader().getFusionDir() + File.separatorChar + Loader.LAUNCH_PAGE + ".lua";
        Object[] objArr = this.j;
        if (objArr != null) {
            this.b.doFile(str, objArr);
        } else {
            this.b.doFile(str, new Object[0]);
        }
        a();
    }

    @Deprecated
    public void doString(String str, Object... objArr) {
        this.b.b(str, objArr);
    }

    @Override // org.roam.Application
    public AppCompatActivity getActivity() {
        return this;
    }

    public LuaTable<Integer, String> getAutoLoadModuleNameList() {
        File[] fileArrListFiles;
        LuaTable<Integer, String> luaTable = new LuaTable<>(getLuaSupport().getLuaState());
        if (getLoader() != null && (fileArrListFiles = b().listFiles(new FileFilter(this) { // from class: roam.b.c.a.a.b
            public final MainActivity a;

            {
                this.a = this;
            }

            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                MainActivity mainActivity = this.a;
                Objects.requireNonNull(mainActivity);
                return file.isDirectory() && mainActivity.getLoader().getAppConfig().getModules().contains(file.getName());
            }
        })) != null && fileArrListFiles.length > 0) {
            for (File file : fileArrListFiles) {
                if (file != null) {
                    File file2 = new File(file.getAbsolutePath() + File.separatorChar + "_autoload.lua");
                    if (file2.exists()) {
                        luaTable.put(Integer.valueOf(luaTable.size() + 1), file2.getParentFile().getName() + "/_autoload");
                    }
                }
            }
        }
        return luaTable;
    }

    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Deprecated
    public int getHeight() {
        return UiUtil.getScreenHeight(this);
    }

    @Override // org.roam.Application
    public Loader getLoader() {
        return this.c;
    }

    public LuaDexLoader getLuaDexLoader() {
        return getLuaSupport().getLuaDexLoader();
    }

    @Deprecated
    public String getLuaDir() {
        return getLuaSupport().getLuaDir();
    }

    @Deprecated
    public String getLuaDir(String str) {
        return getLuaSupport().getLuaDir(str);
    }

    @Override // org.roam.Application
    public LuaContext getLuaSupport() {
        return this.b;
    }

    @Deprecated
    public Object getSharedData(String str) {
        Objects.requireNonNull(this.b);
        return LuaApplication.getInstance().getSharedData(str);
    }

    @Deprecated
    public Object getSharedData(String str, Object obj) {
        Objects.requireNonNull(this.b);
        Object sharedData = LuaApplication.getInstance().getSharedData(str);
        return sharedData == null ? obj : sharedData;
    }

    @Override // org.roam.Application
    public UiManager getUiManager() {
        return this.a;
    }

    @Override // org.roam.Application
    public ViewConfig getViewConfig() {
        return this.a.getViewConfig();
    }

    @Deprecated
    public int getWidth() {
        return UiUtil.getScreenWidth(this);
    }

    @Override // org.roam.Application
    public boolean isDevMode() {
        return getPackageName().equals("net.fusionapp");
    }

    @Deprecated
    public void loadDex(String str) {
        try {
            this.b.d.loadDex(str);
        } catch (LuaException e) {
            e.printStackTrace();
            this.b.sendError("loadDex", e);
        }
    }

    @Deprecated
    public Object loadLib(String str) {
        try {
            return getLuaSupport().loadLib(str);
        } catch (LuaException e) {
            getLuaSupport().sendError("loadLib:", e);
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public void newActivity(String str) {
        newActivity(str, new Object[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void newActivity(String str, Object... objArr) {
        Intent intent;
        if (str.endsWith(".lua")) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.i.getAbsolutePath());
            intent = getLuaIntent(this, new File(a.l(sb, File.separatorChar, str)), objArr);
        } else {
            intent = getIntent(this, getLoader().getProjectDir().getAbsolutePath(), str);
            if (objArr != 0) {
                intent.putExtra("args", (Serializable) objArr);
            }
        }
        startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.b.runFunction("onActivityResult", Integer.valueOf(i), Integer.valueOf(i2), intent);
    }

    public void onClearSearchEditTextClick(View view) {
        EditText editText = (EditText) findViewById(R.id.r);
        if (editText != null) {
            editText.setText("");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view instanceof FloatingActionButton) {
            getLuaSupport().runFunction("onFloatingActionButtonClick", new Object[0]);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        h hVar = new h(this);
        this.b = hVar;
        hVar.a(b().getAbsolutePath());
        this.b.k = isDevMode();
        String stringExtra = getIntent().getStringExtra("file_lua_key");
        if (getIntent().hasExtra("args")) {
            this.j = (Object[]) getIntent().getSerializableExtra("args");
        }
        if (!TextUtils.isEmpty(stringExtra)) {
            File parentFile = new File(stringExtra).getParentFile();
            this.i = parentFile;
            this.b.a(parentFile.getAbsolutePath());
            this.b.d();
            Object[] objArr = this.j;
            if (objArr != null) {
                this.b.doFile(stringExtra, objArr);
            } else {
                this.b.doFile(stringExtra, new Object[0]);
            }
            a();
            return;
        }
        try {
            Intent intent = getIntent();
            if (intent != null) {
                this.c = new StorageLoader(this, intent.getStringExtra("project_key"), intent.getStringExtra("page_name_key"));
            }
            this.i = new File(this.c.getFusionDir());
            this.b.m = getLoader().getFusionDir();
            this.b.o = getLoader().getProjectDir().getAbsolutePath();
            Loader loader = this.c;
            if (loader != null && loader.isConfigAvailable()) {
                try {
                    FusionUiCoreManger fusionUiCoreManger = new FusionUiCoreManger(this);
                    fusionUiCoreManger.b(this.c);
                    fusionUiCoreManger.d();
                    this.a = fusionUiCoreManger;
                } catch (FusionUiCoreManger.a e) {
                    Toast.makeText(this, e.toString(), 0).show();
                    e.printStackTrace();
                }
            }
            this.b.d();
            UiManager uiManager = this.a;
            if (uiManager == null || !uiManager.isNeedPost()) {
                d();
            } else {
                this.a.getRootView().post(new Runnable(this) { // from class: roam.b.c.a.a.a
                    public final MainActivity a;

                    {
                        this.a = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        MainActivity mainActivity = this.a;
                        int i = MainActivity.k;
                        mainActivity.d();
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (getUiManager() != null) {
                if (getUiManager().getViewPager() != null && getUiManager().getViewPager().getAdapter() != null && (getUiManager().getViewPager().getAdapter() instanceof c)) {
                    getUiManager().getViewPager().setOffscreenPageLimit(1);
                    ((c) getUiManager().getViewPager().getAdapter()).a();
                    getUiManager().getViewPager().setAdapter(null);
                }
                getUiManager().removeAllViews();
            }
        } catch (Exception e) {
            Log.d(getClass().getSimpleName(), e.toString());
        }
        super.onDestroy();
        this.b.runFunction("onDestroy", new Object[0]);
    }

    @Override // org.roam.Application, roam.b.c.a.a.k.s.e
    public void onDrawerItemClick(List<List<ViewConfig.DrawerBean.ListBean>> list, int i, int i2) {
        getLuaSupport().runFunction("onDrawerListItemClick", list, getUiManager().getDrawerRecyclerView(), Integer.valueOf(i), Integer.valueOf(i2));
    }

    @Override // org.roam.Application, roam.b.c.a.a.k.p, android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        Object objRunFunction = getLuaSupport().runFunction("onSearchEvent", textView.getText().toString());
        if (objRunFunction != null && objRunFunction.getClass() == Boolean.class && ((Boolean) objRunFunction).booleanValue()) {
            return true;
        }
        this.a.closeSearchBar();
        return true;
    }

    @Override // org.roam.Application, roam.b.c.a.a.k.p
    public void onHomeButtonClick(View view) {
        this.b.runFunction("onHomeButtonClick", view);
        if (this.a.getViewConfig().getDrawer().isEnabled()) {
            this.a.toggleDrawer();
        } else {
            finish();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onKeyDown(int r2, android.view.KeyEvent r3) {
        /*
            r1 = this;
            r0 = 4
            if (r2 != r0) goto L58
            org.roam.ui.UiManager r0 = r1.getUiManager()
            if (r0 == 0) goto L58
            org.roam.ui.UiManager r0 = r1.getUiManager()
            boolean r0 = r0.isUnfoldSearchBar()
            if (r0 == 0) goto L1b
            org.roam.ui.UiManager r2 = r1.getUiManager()
            r2.closeSearchBar()
            goto L65
        L1b:
            org.roam.ui.UiManager r0 = r1.getUiManager()
            org.roam.ui.fragment.IFusionPage r0 = r0.getCurrentFragment()
            if (r0 == 0) goto L33
            org.roam.ui.UiManager r0 = r1.getUiManager()
            org.roam.ui.fragment.IFusionPage r0 = r0.getCurrentFragment()
            boolean r0 = r0.onFragmentKeyDown(r2, r3)
            if (r0 != 0) goto L65
        L33:
            org.roam.ui.UiManager r0 = r1.getUiManager()
            androidx.viewpager.widget.ViewPager r0 = r0.getViewPager()
            if (r0 == 0) goto L58
            org.roam.ui.UiManager r0 = r1.getUiManager()
            androidx.viewpager.widget.ViewPager r0 = r0.getViewPager()
            int r0 = r0.getCurrentItem()
            if (r0 == 0) goto L58
            org.roam.ui.UiManager r2 = r1.getUiManager()
            androidx.viewpager.widget.ViewPager r2 = r2.getViewPager()
            r3 = 0
            r2.setCurrentItem(r3)
            goto L65
        L58:
            com.luajava.LuaObject r0 = r1.d
            boolean r0 = r1.c(r0, r2, r3)
            if (r0 != 0) goto L65
            boolean r2 = super.onKeyDown(r2, r3)
            goto L66
        L65:
            r2 = 1
        L66:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.roamexplore.MainActivity.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (c(this.f, i, keyEvent)) {
            return true;
        }
        return super.onKeyLongPress(i, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        if (c(this.h, i, keyEvent)) {
            return true;
        }
        return super.onKeyShortcut(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (c(this.e, i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // org.roam.Application, roam.b.c.a.a.k.p
    public void onMenuItemClick(String str) {
        getLuaSupport().runFunction("onMenuItemClick", str);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.b.runFunction("onPause", new Object[0]);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.b.runFunction("onRequestPermissionsResult", Integer.valueOf(i), strArr, iArr);
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        this.b.runFunction("onRestart", new Object[0]);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.b.runFunction("onResume", new Object[0]);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.b.runFunction("onStart", new Object[0]);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.b.runFunction("onStop", new Object[0]);
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        LuaObject luaObject = this.g;
        if (luaObject != null) {
            try {
                Object objCall = luaObject.call(motionEvent);
                if (objCall != null && objCall.getClass() == Boolean.class) {
                    if (((Boolean) objCall).booleanValue()) {
                        return true;
                    }
                }
            } catch (LuaException e) {
                this.b.sendError("onTouchEvent", e);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Deprecated
    public Object setSharedData(String str, Object obj) {
        Objects.requireNonNull(this.b);
        return Boolean.valueOf(LuaApplication.getInstance().setSharedData(str, obj));
    }

    @Override // android.app.Activity
    public boolean shouldShowRequestPermissionRationale(String str) {
        getLuaSupport().runFunction("shouldShowRequestPermissionRationale", str);
        return super.shouldShowRequestPermissionRationale(str);
    }

    public void startActivityForResult(String str, int i) {
        startActivityForResult(str, i, (Bundle) null);
    }

    public void startActivityForResult(String str, int i, Bundle bundle) {
        Intent intent = getIntent(this, getLoader().getProjectDir().getAbsolutePath(), str);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, i);
    }

    public void startFusionActivity(String str) {
        startFusionActivity(str, null);
    }

    public void startFusionActivity(String str, Bundle bundle) {
        Intent intent = getIntent(this, getLoader().getProjectDir().getAbsolutePath(), str);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}

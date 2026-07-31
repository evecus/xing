package org.roam.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.androlua.LuaApplication;
import com.google.android.material.snackbar.Snackbar;
import org.roam.Application;
import org.roam.R;
import org.roam.ui.UiManager;

/* JADX INFO: loaded from: classes.dex */
public class UiUtil {
    private static int STATUSBAR_HEIGHT;

    public static int dp2px(float f) {
        return dp2px(LuaApplication.getInstance(), f);
    }

    public static int dp2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static CoordinatorLayout getCoordinator(Activity activity) {
        return activity instanceof Application ? ((Application) activity).getUiManager().getCoordinatorLayout() : (CoordinatorLayout) activity.findViewById(R.id.r);
    }

    public static Drawable getDrawable(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return context.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{i}).getDrawable(0);
    }

    public static Drawable getRippleBorderlessDrawable(Context context) {
        return getDrawable(context, android.R.attr.selectableItemBackgroundBorderless);
    }

    public static Drawable getRippleDrawable(Context context) {
        return getDrawable(context, android.R.attr.selectableItemBackground);
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int identifier;
        if (STATUSBAR_HEIGHT <= 0 && (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
            try {
                STATUSBAR_HEIGHT = context.getResources().getDimensionPixelSize(identifier);
            } catch (Exception e) {
            }
        }
        return STATUSBAR_HEIGHT;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static UiManager getUiManagerByContext(Context context) {
        if (context instanceof Application) {
            return ((Application) context).getUiManager();
        }
        return null;
    }

    public static void hideKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void notify(Activity activity, int i) {
        CoordinatorLayout coordinator = getCoordinator(activity);
        if (coordinator == null) {
            Toast.makeText(activity, i, 0).show();
        } else {
            Snackbar.make(coordinator, i, -1).show();
        }
    }

    public static int px2dp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void setLightStatusBar(boolean z, View view) {
        view.setSystemUiVisibility(z ? 8192 : view.getSystemUiVisibility() & (-8193));
    }

    public static void setTranslucentStatus(Window window) {
        window.getDecorView().setSystemUiVisibility(1280);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
    }

    public static void showKeyboard(Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(0, 2);
    }
}

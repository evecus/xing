package com.androlua.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

/* JADX INFO: loaded from: classes.dex */
public class ScreenMetrics {
    private static int deviceScreenDensity;
    private static int deviceScreenHeight;
    private static int deviceScreenWidth;
    private static Display display;
    private static boolean initialized;
    private int mDesignHeight;
    private int mDesignWidth;

    public ScreenMetrics() {
    }

    public ScreenMetrics(int i, int i2) {
        this.mDesignWidth = i;
        this.mDesignHeight = i2;
    }

    public static int getDeviceScreenDensity() {
        return deviceScreenDensity;
    }

    public static int getDeviceScreenHeight() {
        return deviceScreenHeight;
    }

    public static int getDeviceScreenWidth() {
        return deviceScreenWidth;
    }

    public static void initIfNeeded(Activity activity) {
        if (initialized) {
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        deviceScreenHeight = displayMetrics.heightPixels;
        deviceScreenWidth = displayMetrics.widthPixels;
        deviceScreenDensity = displayMetrics.densityDpi;
        display = activity.getWindowManager().getDefaultDisplay();
        initialized = true;
    }

    public static int rescaleX(int i, int i2) {
        return (i2 == 0 || !initialized) ? i : (i * i2) / deviceScreenWidth;
    }

    public static int rescaleY(int i, int i2) {
        return (i2 == 0 || !initialized) ? i : (i * i2) / deviceScreenHeight;
    }

    public static int scaleX(int i, int i2) {
        return (i2 == 0 || !initialized) ? i : (deviceScreenWidth * i) / i2;
    }

    public static int scaleY(int i, int i2) {
        return (i2 == 0 || !initialized) ? i : (deviceScreenHeight * i) / i2;
    }

    public int rescaleX(int i) {
        return rescaleX(i, this.mDesignWidth);
    }

    public int rescaleY(int i) {
        return rescaleY(i, this.mDesignHeight);
    }

    public int scaleX(int i) {
        return scaleX(i, this.mDesignWidth);
    }

    public int scaleY(int i) {
        return scaleY(i, this.mDesignHeight);
    }

    public void setDesignHeight(int i) {
        this.mDesignHeight = i;
    }

    public void setDesignWidth(int i) {
        this.mDesignWidth = i;
    }

    public void setScreenMetrics(int i, int i2) {
        this.mDesignWidth = i;
        this.mDesignHeight = i2;
    }
}

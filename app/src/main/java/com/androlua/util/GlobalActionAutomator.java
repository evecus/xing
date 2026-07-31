package com.androlua.util;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class GlobalActionAutomator {
    private Handler mHandler;
    private ScreenMetrics mScreenMetrics;
    private AccessibilityService mService;

    public GlobalActionAutomator(AccessibilityService accessibilityService, Handler handler) {
        this.mService = accessibilityService;
        this.mHandler = handler;
    }

    private boolean gesturesWithHandler(GestureDescription gestureDescription) {
        VolatileDispose volatileDispose = new VolatileDispose();
        Log.i("GlobalActionAutomator", "dispatchGesture");
        return this.mService.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback(this, volatileDispose) { // from class: com.androlua.util.GlobalActionAutomator.1
            public final GlobalActionAutomator this$0;
            public final VolatileDispose val$result;

            {
                this.this$0 = this;
                this.val$result = volatileDispose;
            }

            @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
            public void onCancelled(GestureDescription gestureDescription2) {
                Log.i("GlobalActionAutomator", "onCancelled");
                this.val$result.setAndNotify(Boolean.FALSE);
            }

            @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
            public void onCompleted(GestureDescription gestureDescription2) {
                Log.i("GlobalActionAutomator", "onCompleted");
                this.val$result.setAndNotify(Boolean.TRUE);
            }
        }, this.mHandler);
    }

    private boolean gesturesWithoutHandler(GestureDescription gestureDescription) {
        prepareLooperIfNeeded();
        VolatileBox volatileBox = new VolatileBox(Boolean.FALSE);
        this.mService.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback(this, volatileBox) { // from class: com.androlua.util.GlobalActionAutomator.2
            public final GlobalActionAutomator this$0;
            public final VolatileBox val$result;

            {
                this.this$0 = this;
                this.val$result = volatileBox;
            }

            @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
            public void onCancelled(GestureDescription gestureDescription2) {
                this.val$result.set(Boolean.FALSE);
                this.this$0.quitLoop();
            }

            @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
            public void onCompleted(GestureDescription gestureDescription2) {
                this.val$result.set(Boolean.TRUE);
                this.this$0.quitLoop();
            }
        }, new Handler(Looper.myLooper()));
        Looper.loop();
        return ((Boolean) volatileBox.get()).booleanValue();
    }

    private boolean performGlobalAction(int i) {
        AccessibilityService accessibilityService = this.mService;
        if (accessibilityService == null) {
            return false;
        }
        return accessibilityService.performGlobalAction(i);
    }

    private Path pointsToPath(int[][] iArr) {
        Path path = new Path();
        path.moveTo(scaleX(iArr[0][0]), scaleY(iArr[0][1]));
        for (int i = 1; i < iArr.length; i++) {
            int[] iArr2 = iArr[i];
            path.lineTo(scaleX(iArr2[0]), scaleY(iArr2[1]));
        }
        return path;
    }

    private void prepareLooperIfNeeded() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void quitLoop() {
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            looperMyLooper.quit();
        }
    }

    private int scaleX(int i) {
        ScreenMetrics screenMetrics = this.mScreenMetrics;
        return screenMetrics == null ? i : screenMetrics.scaleX(i);
    }

    private int scaleY(int i) {
        ScreenMetrics screenMetrics = this.mScreenMetrics;
        return screenMetrics == null ? i : screenMetrics.scaleY(i);
    }

    public boolean back() {
        return performGlobalAction(1);
    }

    public boolean click(int i, int i2) {
        return press(i, i2, ViewConfiguration.getTapTimeout());
    }

    public boolean gesture(long j, long j2, Path path) {
        return gestures(new GestureDescription.StrokeDescription(path, j, j2));
    }

    public boolean gesture(long j, long j2, int[]... iArr) {
        return gestures(new GestureDescription.StrokeDescription(pointsToPath(iArr), j, j2));
    }

    public void gestureAsync(long j, long j2, int[]... iArr) {
        gesturesAsync(new GestureDescription.StrokeDescription(pointsToPath(iArr), j, j2));
    }

    public boolean gestures(GestureDescription.StrokeDescription... strokeDescriptionArr) {
        if (this.mService == null) {
            return false;
        }
        GestureDescription.Builder builder = new GestureDescription.Builder();
        for (GestureDescription.StrokeDescription strokeDescription : strokeDescriptionArr) {
            builder.addStroke(strokeDescription);
        }
        return this.mHandler == null ? gesturesWithoutHandler(builder.build()) : gesturesWithHandler(builder.build());
    }

    public void gesturesAsync(GestureDescription.StrokeDescription... strokeDescriptionArr) {
        if (this.mService == null) {
            return;
        }
        GestureDescription.Builder builder = new GestureDescription.Builder();
        for (GestureDescription.StrokeDescription strokeDescription : strokeDescriptionArr) {
            builder.addStroke(strokeDescription);
        }
        this.mService.dispatchGesture(builder.build(), null, null);
    }

    public boolean home() {
        return performGlobalAction(2);
    }

    public boolean longClick(int i, int i2) {
        return gesture(0L, ViewConfiguration.getLongPressTimeout() + 200, new int[]{i, i2});
    }

    public boolean notifications() {
        return performGlobalAction(4);
    }

    public boolean powerDialog() {
        return performGlobalAction(6);
    }

    public boolean press(int i, int i2, int i3) {
        return gesture(0L, i3, new int[]{i, i2});
    }

    public boolean quickSettings() {
        return performGlobalAction(5);
    }

    public boolean recents() {
        return performGlobalAction(3);
    }

    public void setScreenMetrics(ScreenMetrics screenMetrics) {
        this.mScreenMetrics = screenMetrics;
    }

    public void setService(AccessibilityService accessibilityService) {
        this.mService = accessibilityService;
    }

    public boolean splitScreen() {
        return performGlobalAction(7);
    }

    public boolean swipe(int i, int i2, int i3, int i4, int i5) {
        return gesture(0L, i5, new int[]{i, i2}, new int[]{i3, i4});
    }
}

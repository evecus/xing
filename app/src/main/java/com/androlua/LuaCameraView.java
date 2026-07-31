package com.androlua;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class LuaCameraView extends SurfaceView {
    private SurfaceHolder holder;

    public LuaCameraView(Context context) {
        super(context);
        this.holder = null;
        SurfaceHolder holder = getHolder();
        this.holder = holder;
        holder.addCallback(new SurfaceHolder.Callback(this) { // from class: com.androlua.LuaCameraView.1
            private Camera mCamera;
            public final LuaCameraView this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                Camera.Parameters parameters = this.mCamera.getParameters();
                parameters.setPictureFormat(256);
                parameters.setPreviewSize(854, 480);
                parameters.setFocusMode("auto");
                parameters.setPictureSize(2592, 1456);
                this.mCamera.startPreview();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Camera cameraOpen = Camera.open();
                this.mCamera = cameraOpen;
                try {
                    cameraOpen.setPreviewDisplay(surfaceHolder);
                } catch (IOException e) {
                    this.mCamera = null;
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                this.mCamera.stopPreview();
                this.mCamera.release();
                this.mCamera = null;
            }
        });
        this.holder.setType(3);
    }
}

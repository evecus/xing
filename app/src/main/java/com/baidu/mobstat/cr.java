package com.baidu.mobstat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import com.baidu.mobstat.cw;

/* JADX INFO: loaded from: classes.dex */
public class cr {
    public static void a(Context context, final cu cuVar) {
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        context.bindService(intent, new ServiceConnection() { // from class: com.baidu.mobstat.cr.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                cu cuVar2;
                try {
                    cw cwVarA = cw.a.a(iBinder);
                    if (TextUtils.isEmpty(cwVarA.a()) || (cuVar2 = cuVar) == null) {
                        return;
                    }
                    cuVar2.a(cwVarA.a());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
            }
        }, 1);
    }
}

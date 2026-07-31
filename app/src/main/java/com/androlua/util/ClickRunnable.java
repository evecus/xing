package com.androlua.util;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import com.androlua.LuaAccessibilityService;
import com.luajava.LuaTable;
import roam.a.b.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class ClickRunnable implements Runnable {
    private LuaTable mButtons;
    private ClickRunnable mClick;
    private ClickCallback mClickCallback;
    private final LuaAccessibilityService mService;
    private int mIdx = 1;
    private int mN = -1;
    private int mM = -1;
    private boolean mIsCancel = false;

    public interface ClickCallback {
        void onDone(boolean z, LuaTable luaTable, String str, int i);
    }

    public ClickRunnable(LuaAccessibilityService luaAccessibilityService, LuaTable luaTable) {
        this.mService = luaAccessibilityService;
        this.mButtons = luaTable;
    }

    private boolean postClick(String str) {
        if (str == null) {
            return false;
        }
        int iLastIndexOf = str.lastIndexOf("$");
        long jLongValue = 1000;
        if (iLastIndexOf > 0) {
            try {
                jLongValue = Long.valueOf(str.substring(iLastIndexOf + 1)).longValue();
            } catch (Exception e) {
            }
            str = str.substring(0, iLastIndexOf);
        }
        int iLastIndexOf2 = str.lastIndexOf(">");
        if (iLastIndexOf2 > 0) {
            if (this.mN < 0) {
                try {
                    this.mN = Integer.valueOf(str.substring(iLastIndexOf2 + 1)).intValue();
                } catch (Exception e2) {
                    this.mN = -1;
                }
            }
            str = str.substring(0, iLastIndexOf2);
        }
        int iLastIndexOf3 = str.lastIndexOf("<");
        if (iLastIndexOf3 > 0) {
            if (this.mM < 0) {
                try {
                    this.mM = Integer.valueOf(str.substring(iLastIndexOf3 + 1)).intValue();
                } catch (Exception e3) {
                    this.mM = -1;
                }
            }
            str = str.substring(0, iLastIndexOf3);
        }
        this.mM--;
        this.mN--;
        AccessibilityNodeInfo accessibilityNodeInfoFindAccessibilityNodeInfo = this.mService.findAccessibilityNodeInfo(str);
        StringBuilder sbE = a.e("findAccessibilityNodeInfo ", str, ",");
        sbE.append(this.mN);
        sbE.append(",");
        sbE.append(this.mM);
        sbE.append(",");
        sbE.append(accessibilityNodeInfoFindAccessibilityNodeInfo);
        Log.i("lua", sbE.toString());
        if (accessibilityNodeInfoFindAccessibilityNodeInfo != null) {
            this.mN = -1;
            this.mService.toClick2(accessibilityNodeInfoFindAccessibilityNodeInfo);
        } else if (this.mN <= 0 && this.mM <= 0) {
            ClickCallback clickCallback = this.mClickCallback;
            if (clickCallback == null) {
                return false;
            }
            clickCallback.onDone(true, this.mButtons, str, this.mIdx);
            return false;
        }
        this.mService.getHandler().postDelayed(this, jLongValue);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001e, code lost:
    
        r0.onDone(false, r7.mButtons, null, -1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:?, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean canClick() {
        /*
            r7 = this;
            com.luajava.LuaTable r0 = r7.mButtons
            int r0 = r0.length()
            r1 = 0
            if (r0 != 0) goto Lb
            goto L78
        Lb:
            com.luajava.LuaTable r0 = r7.mButtons
            int r0 = r0.length()
            r2 = r1
        L12:
            r3 = -1
            r4 = 0
            if (r2 >= r0) goto L73
            boolean r5 = r7.mIsCancel
            if (r5 == 0) goto L24
            com.androlua.util.ClickRunnable$ClickCallback r0 = r7.mClickCallback
            if (r0 == 0) goto L78
        L1e:
            com.luajava.LuaTable r2 = r7.mButtons
            r0.onDone(r1, r2, r4, r3)
            goto L78
        L24:
            com.luajava.LuaTable r3 = r7.mButtons
            int r4 = r2 + 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            java.lang.Object r3 = r3.get(r5)
            boolean r5 = r3 instanceof com.luajava.LuaTable
            r6 = 1
            if (r5 == 0) goto L53
            com.luajava.LuaTable r3 = (com.luajava.LuaTable) r3
            int r2 = r3.length()
            if (r2 != 0) goto L3e
            goto L71
        L3e:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            java.lang.Object r2 = r3.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L71
            boolean r2 = r7.postClick(r2)
            if (r2 == 0) goto L71
            r7.mButtons = r3
            goto L6f
        L53:
            boolean r5 = r3 instanceof java.lang.String
            if (r5 == 0) goto L71
            java.lang.String r3 = (java.lang.String) r3
            com.androlua.LuaAccessibilityService r5 = r7.mService
            android.view.accessibility.AccessibilityNodeInfo r5 = r5.findAccessibilityNodeInfo(r3)
            if (r5 == 0) goto L71
            com.androlua.LuaAccessibilityService r0 = r7.mService
            r0.toClick2(r5)
            com.androlua.util.ClickRunnable$ClickCallback r0 = r7.mClickCallback
            if (r0 == 0) goto L6f
            com.luajava.LuaTable r1 = r7.mButtons
            r0.onDone(r6, r1, r3, r2)
        L6f:
            r1 = r6
            goto L78
        L71:
            r2 = r4
            goto L12
        L73:
            com.androlua.util.ClickRunnable$ClickCallback r0 = r7.mClickCallback
            if (r0 == 0) goto L78
            goto L1e
        L78:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.util.ClickRunnable.canClick():boolean");
    }

    public boolean canClick(ClickCallback clickCallback) {
        this.mClickCallback = clickCallback;
        return canClick();
    }

    public void cancel() {
        this.mIsCancel = true;
        ClickRunnable clickRunnable = this.mClick;
        if (clickRunnable != null) {
            clickRunnable.cancel();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        ClickCallback clickCallback;
        LuaTable luaTable;
        int i;
        if (this.mIsCancel) {
            clickCallback = this.mClickCallback;
            if (clickCallback == null) {
                return;
            }
            luaTable = this.mButtons;
            i = -1;
        } else {
            if (this.mN < 0 && this.mM < 0) {
                this.mIdx++;
            }
            Object obj = this.mButtons.get(Integer.valueOf(this.mIdx));
            if (obj != null) {
                if (!(obj instanceof LuaTable)) {
                    if (obj instanceof String) {
                        postClick((String) obj);
                        return;
                    }
                    return;
                } else {
                    LuaTable luaTable2 = (LuaTable) obj;
                    if (luaTable2.length() != 0) {
                        ClickRunnable clickRunnable = new ClickRunnable(this.mService, luaTable2);
                        this.mClick = clickRunnable;
                        clickRunnable.canClick(new ClickCallback(this) { // from class: com.androlua.util.ClickRunnable.1
                            public final ClickRunnable this$0;

                            {
                                this.this$0 = this;
                            }

                            @Override // com.androlua.util.ClickRunnable.ClickCallback
                            public void onDone(boolean z, LuaTable luaTable3, String str, int i2) {
                                this.this$0.mClick = null;
                                this.this$0.run();
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            clickCallback = this.mClickCallback;
            if (clickCallback == null) {
                return;
            }
            z = this.mIdx == this.mButtons.length();
            luaTable = this.mButtons;
            i = this.mIdx;
        }
        clickCallback.onDone(z, luaTable, null, i);
    }
}

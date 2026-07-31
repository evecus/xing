package com.baidu.mobstat;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class bc {
    private static View a(ViewGroup viewGroup) {
        Object tag;
        if (viewGroup == null) {
            return null;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null && (tag = childAt.getTag(-5000)) != null && (tag instanceof String) && ((String) tag).equals("baidu_mtj_edit_txtview")) {
                return childAt;
            }
        }
        return null;
    }

    public static void a(Activity activity) {
        ViewGroup viewGroup;
        View viewA;
        try {
            viewGroup = (ViewGroup) cc.a(activity).findViewById(R.id.content);
        } catch (Exception e) {
            viewGroup = null;
        }
        if (viewGroup == null || (viewA = a(viewGroup)) == null) {
            return;
        }
        viewGroup.removeView(viewA);
    }

    private static void a(final Activity activity, TextView textView) {
        final View view = (View) textView.getParent();
        textView.setOnTouchListener(new View.OnTouchListener() { // from class: com.baidu.mobstat.bc.2
            int a = 0;
            int b = 0;
            int c = 0;
            int d = 0;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                switch (motionEvent.getAction()) {
                    case 0:
                        this.a = rawX;
                        this.b = rawY;
                        this.c = rawX - view2.getLeft();
                        this.d = rawY - view2.getTop();
                        break;
                    case 1:
                        if (bc.b(this.a, (int) motionEvent.getRawX(), this.b, (int) motionEvent.getRawY())) {
                            bc.b((Context) activity);
                        }
                        break;
                    case 2:
                        int i = rawX - this.c;
                        int i2 = rawY - this.d;
                        Rect rect = new Rect();
                        view.getLocalVisibleRect(rect);
                        if (rect.contains(new Rect(i, i2, view2.getWidth() + i, view2.getHeight() + i2))) {
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
                            marginLayoutParams.leftMargin = i;
                            marginLayoutParams.topMargin = i2;
                            view2.setLayoutParams(marginLayoutParams);
                            break;
                        }
                        break;
                }
                return true;
            }
        });
    }

    public static void a(Activity activity, boolean z) {
        ViewGroup viewGroup;
        View viewA;
        try {
            viewGroup = (ViewGroup) cc.a(activity).findViewById(R.id.content);
        } catch (Exception e) {
            viewGroup = null;
        }
        if (viewGroup == null || (viewA = a(viewGroup)) == null) {
            return;
        }
        viewA.setVisibility(z ? 0 : 4);
    }

    public static boolean a(View view) {
        Object tag = view.getTag(-5000);
        return tag != null && (tag instanceof String) && ((String) tag).equals("baidu_mtj_edit_txtview");
    }

    public static void b(final Activity activity) {
        final ViewGroup viewGroup;
        try {
            viewGroup = (ViewGroup) cc.a(activity).findViewById(R.id.content);
        } catch (Exception e) {
            viewGroup = null;
        }
        if (viewGroup == null || viewGroup == null || a(viewGroup) != null) {
            return;
        }
        final az azVar = new az(activity);
        azVar.setBackgroundColor(-16745729);
        azVar.setGravity(17);
        azVar.setText("连接中");
        azVar.setTag(-5000, "baidu_mtj_edit_txtview");
        viewGroup.post(new Runnable() { // from class: com.baidu.mobstat.bc.1
            @Override // java.lang.Runnable
            public void run() {
                int width = viewGroup.getWidth();
                int height = viewGroup.getHeight();
                int iC = bb.c(activity, 55.0f);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iC, iC);
                layoutParams.leftMargin = (width - iC) / 6;
                layoutParams.topMargin = ((height - iC) * 5) / 6;
                azVar.setLayoutParams(layoutParams);
            }
        });
        viewGroup.addView(azVar);
        a(activity, azVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("是否确认退出连接?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.baidu.mobstat.bc.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                bg.a().c();
                bv.c().a("autotrace: connect close, app close");
                bg.a().a(4);
                bg.a().d();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.baidu.mobstat.bc.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(float f, float f2, float f3, float f4) {
        return Math.abs(f - f2) <= 5.0f && Math.abs(f3 - f4) <= 5.0f;
    }
}

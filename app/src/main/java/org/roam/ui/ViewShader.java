package org.roam.ui;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public class ViewShader {
    private int mColorAccent;
    private int mColorPrimary;

    public ViewShader(int i, int i2) {
        this.mColorPrimary = i;
        this.mColorAccent = i2;
    }

    public ColorStateList getDefaultColorStateList(int i) {
        return new ColorStateList(new int[][]{new int[]{R.attr.state_enabled}, new int[]{-16842910}, new int[]{-16842912}, new int[]{R.attr.state_pressed}}, new int[]{i, i, i, i});
    }

    public void tint(View view) {
        if (view instanceof ViewGroup) {
            return;
        }
        if (view instanceof SwitchCompat) {
            tintSwitch((SwitchCompat) view);
        } else if (view instanceof FloatingActionButton) {
            tintFloatingActionButton((FloatingActionButton) view);
        } else if (view instanceof MaterialButton) {
            tintMaterialButton((MaterialButton) view);
        }
    }

    public void tintByGroup(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null) {
                if (childAt instanceof ViewGroup) {
                    tintByGroup((ViewGroup) childAt);
                } else {
                    tint(childAt);
                }
            }
        }
    }

    public void tintDialogButton(AlertDialog alertDialog) {
        Button button = alertDialog.getButton(-3);
        Button button2 = alertDialog.getButton(-2);
        Button button3 = alertDialog.getButton(-1);
        if (button != null) {
            tintMaterialButton(button);
        }
        if (button2 != null) {
            tintMaterialButton(button2);
        }
        if (button3 != null) {
            tintMaterialButton(button3);
        }
    }

    public void tintFloatingActionButton(FloatingActionButton floatingActionButton) {
        floatingActionButton.setSupportBackgroundTintList(getDefaultColorStateList(this.mColorAccent));
        floatingActionButton.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
    }

    public void tintMaterialButton(Button button) {
        button.getBackground().setTint(this.mColorAccent);
    }

    public void tintSwitch(SwitchCompat switchCompat) {
        int i = this.mColorAccent;
        int iArgb = Color.argb(77, Color.red(i), Color.green(i), Color.blue(i));
        DrawableCompat.setTintList(switchCompat.getThumbDrawable(), new ColorStateList(new int[][]{new int[]{R.attr.state_checked}, new int[0]}, new int[]{i, -1}));
        DrawableCompat.setTintList(switchCompat.getTrackDrawable(), new ColorStateList(new int[][]{new int[]{R.attr.state_checked}, new int[0]}, new int[]{iArgb, Color.parseColor("#4D000000")}));
    }
}

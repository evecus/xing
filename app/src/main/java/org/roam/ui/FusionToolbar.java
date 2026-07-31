package org.roam.ui;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.androlua.LuaApplication;
import java.util.List;
import org.roam.R;
import org.roam.config.ViewConfig;
import org.roam.loader.Loader;
import org.roam.ui.FusionToolbar;
import org.roam.util.UiUtil;
import roam.b.c.a.a.k.l;
import roam.b.c.a.a.k.n;
import roam.b.c.a.a.k.o;
import roam.b.c.a.a.k.p;

/* JADX INFO: loaded from: classes.dex */
public class FusionToolbar extends Toolbar {
    public static final int q = 0;
    public ImageView a;
    public int b;
    public p c;
    public LinearLayout d;
    public ImageView e;
    public int f;
    public int g;
    public int h;
    public int i;
    public TextView j;
    public TextView k;
    public boolean l;
    public Loader m;
    public boolean n;
    public boolean o;
    public EditText p;

    public FusionToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = ViewCompat.MEASURED_STATE_MASK;
        this.g = ViewCompat.MEASURED_STATE_MASK;
        this.h = -1;
        this.i = ViewCompat.MEASURED_STATE_MASK;
    }

    public EditText getSearchEditText() {
        return this.p;
    }

    public int getStyle() {
        return this.b;
    }

    public TextView getSubtitleView() {
        return this.k;
    }

    public TextView getTitleView() {
        return this.j;
    }

    public void setDrawerEnable(boolean z) {
        this.o = z;
    }

    public void setHomeButtonEnabled(boolean z) {
        this.n = z;
    }

    public void setLoader(Loader loader) {
        this.m = loader;
    }

    public void setMenus(List<ViewConfig.ToolbarBean.MenusBean> list) {
        LinearLayout linearLayout = this.d;
        ImageView imageView = this.e;
        final PopupMenu popupMenu = new PopupMenu(getContext(), imageView, 48);
        Menu menu = popupMenu.getMenu();
        int i = 0;
        for (final ViewConfig.ToolbarBean.MenusBean menusBean : list) {
            if (menusBean.getType() == 1) {
                menu.add(menusBean.getTitle());
                i++;
            } else if (menusBean.getType() == 0 && linearLayout.getChildCount() != 5) {
                AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.rightMargin = linearLayout.getChildCount() == 0 ? 0 : UiUtil.dp2px(getContext(), 16.0f);
                int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.r);
                layoutParams.width = dimensionPixelSize;
                layoutParams.height = dimensionPixelSize;
                linearLayout.addView(appCompatImageView, 0, layoutParams);
                this.m.loadImage(appCompatImageView, menusBean.getIcon());
                appCompatImageView.setColorFilter(this.b != 2 ? this.f : this.g);
                ViewCompat.setBackground(appCompatImageView, UiUtil.getRippleBorderlessDrawable(getContext()));
                appCompatImageView.setOnClickListener(new View.OnClickListener(this, menusBean) { // from class: roam.b.c.a.a.k.b
                    public final FusionToolbar a;
                    public final ViewConfig.ToolbarBean.MenusBean b;

                    {
                        this.a = this;
                        this.b = menusBean;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.a.c.onMenuItemClick(this.b.getTitle());
                    }
                });
            }
        }
        if (i == 0) {
            linearLayout.removeView(imageView);
            if (linearLayout.getChildCount() != 0 && this.b == 1) {
                ((LinearLayout.LayoutParams) linearLayout.getChildAt(linearLayout.getChildCount() - 1).getLayoutParams()).rightMargin = 0;
            }
        } else if (this.b != 1) {
            ((LinearLayout.LayoutParams) imageView.getLayoutParams()).rightMargin = UiUtil.dp2px(8.0f);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(this) { // from class: roam.b.c.a.a.k.f
            public final FusionToolbar a;

            {
                this.a = this;
            }

            @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                this.a.c.onMenuItemClick(menuItem.getTitle().toString());
                return false;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener(popupMenu) { // from class: roam.b.c.a.a.k.e
            public final PopupMenu a;

            {
                this.a = popupMenu;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PopupMenu popupMenu2 = this.a;
                int i2 = FusionToolbar.q;
                popupMenu2.show();
            }
        });
    }

    public void setOnToolbarWidgetClickListener(p pVar) {
        this.c = pVar;
    }

    public void setSearchBarBackgroundColor(int i) {
        this.h = i;
    }

    public void setSearchBarTextColor(int i) {
        this.g = i;
    }

    public void setSearchEnable(boolean z) {
        this.l = z;
    }

    public void setStyle(int i) {
        ImageView imageView;
        this.b = i;
        if (this.m == null) {
            return;
        }
        final ViewGroup viewGroup = null;
        ViewGroup viewGroup2 = (ViewGroup) ((Activity) getContext()).getLayoutInflater().inflate(i != 1 ? i != 2 ? R.layout.r : R.layout.r : R.layout.r, (ViewGroup) null);
        addViewInLayout(viewGroup2, 0, new Toolbar.LayoutParams(-1, -1));
        this.d = (LinearLayout) viewGroup2.findViewById(R.id.r);
        this.j = (TextView) viewGroup2.findViewById(R.id.r);
        this.k = (TextView) viewGroup2.findViewById(R.id.r);
        this.e = (ImageView) viewGroup2.findViewById(R.id.r);
        ImageView imageView2 = (ImageView) viewGroup2.findViewById(R.id.r);
        final ImageView imageView3 = (ImageView) viewGroup2.findViewById(R.id.r);
        this.p = null;
        this.a = null;
        if (this.l) {
            this.p = (EditText) viewGroup2.findViewById(R.id.r);
            this.a = (ImageView) viewGroup2.findViewById(R.id.r);
            ImageView imageView4 = (ImageView) viewGroup2.findViewById(R.id.r);
            ViewGroup viewGroup3 = (ViewGroup) viewGroup2.findViewById(R.id.r);
            if (this.c != null) {
                this.p.setOnEditorActionListener(new TextView.OnEditorActionListener(this) { // from class: roam.b.c.a.a.k.a
                    public final FusionToolbar a;

                    {
                        this.a = this;
                    }

                    @Override // android.widget.TextView.OnEditorActionListener
                    public final boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                        return this.a.c.onEditorAction(textView, i2, keyEvent);
                    }
                });
            }
            viewGroup = viewGroup3;
            imageView = imageView4;
        } else {
            if (imageView3 != null && i == 0) {
                this.d.removeView(imageView3);
            }
            imageView = null;
        }
        int i2 = this.f;
        int i3 = this.g;
        if (viewGroup != null) {
            if (viewGroup instanceof CardView) {
                ((CardView) viewGroup).setCardBackgroundColor(this.h);
            } else {
                viewGroup.setBackgroundColor(this.h);
            }
        }
        if (i == 0) {
            if (this.l) {
                if (imageView3 != null) {
                    imageView3.setColorFilter(i2);
                }
                this.p.setTextColor(i2);
                viewGroup.setOnClickListener(new View.OnClickListener() { // from class: roam.b.c.a.a.k.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i4 = FusionToolbar.q;
                    }
                });
                imageView.setColorFilter(i3);
                this.a.setColorFilter(i3);
                this.m.loadAppImage(imageView, "delete");
                this.m.loadAppImage(this.a, "arrow_left");
                final EditText editText = this.p;
                ImageView imageView5 = this.a;
                l lVar = new l(imageView);
                imageView3.setOnClickListener(new n(this, imageView3, viewGroup, editText));
                imageView5.setOnClickListener(new View.OnClickListener(editText, imageView3, viewGroup) { // from class: roam.b.c.a.a.k.c
                    public final EditText a;
                    public final ImageView b;
                    public final ViewGroup c;

                    {
                        this.a = editText;
                        this.b = imageView3;
                        this.c = viewGroup;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        EditText editText2 = this.a;
                        ImageView imageView6 = this.b;
                        ViewGroup viewGroup4 = this.c;
                        int i4 = FusionToolbar.q;
                        UiUtil.hideKeyboard(editText2);
                        int left = ((View) imageView6.getParent()).getLeft();
                        int left2 = imageView6.getLeft();
                        int width = imageView6.getWidth() / 2;
                        int bottom = (imageView6.getBottom() + imageView6.getTop()) / 2;
                        int screenWidth = LuaApplication.getInstance().getScreenWidth();
                        int height = viewGroup4.getHeight();
                        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(viewGroup4, left + left2 + width, bottom, ((int) Math.sqrt((height * height) + (screenWidth * screenWidth))) + 1, 0.0f);
                        animatorCreateCircularReveal.setDuration(500L);
                        animatorCreateCircularReveal.addListener(new k(viewGroup4));
                        animatorCreateCircularReveal.start();
                    }
                });
                editText.addTextChangedListener(new o(this, imageView, lVar));
            }
        } else if (i == 2) {
            i2 = i3;
        } else {
            i2 = this.f;
            if (imageView3 != null) {
                imageView3.setColorFilter(i3);
            }
        }
        EditText editText2 = this.p;
        if (editText2 != null) {
            editText2.setTextColor(i3);
        }
        this.m.loadAppImage(this.e, "more_vert");
        this.m.loadAppImage(imageView2, this.o ? "menu" : "arrow_left");
        this.e.setColorFilter(i2);
        imageView2.setColorFilter(i2);
        if (imageView3 != null) {
            this.m.loadAppImage(imageView3, "search");
        }
        if (this.b != 2) {
            this.j.setTextColor(i2);
            this.k.setTextColor(this.i);
        }
        if (this.n) {
            return;
        }
        imageView2.setVisibility(8);
        if (this.b == 1) {
            if (this.n) {
                ((ViewGroup.MarginLayoutParams) ((ConstraintLayout.LayoutParams) this.j.getLayoutParams())).leftMargin = 0;
            } else {
                ((LinearLayout.LayoutParams) this.j.getLayoutParams()).leftMargin = 0;
            }
        }
        if (this.c != null) {
            imageView2.setOnClickListener(new View.OnClickListener(this) { // from class: roam.b.c.a.a.k.g
                public final FusionToolbar a;

                {
                    this.a = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.a.c.onHomeButtonClick(view);
                }
            });
        }
    }

    public void setSubtitleText(String str) {
        TextView textView;
        int i;
        if (this.b == 2) {
            return;
        }
        if (str == null || str.trim().equals("")) {
            textView = this.k;
            i = 8;
        } else {
            this.k.setText(str);
            textView = this.k;
            i = 0;
        }
        textView.setVisibility(i);
    }

    public void setTitleText(String str) {
        if (this.b == 2 || str == null) {
            return;
        }
        this.j.setText(str);
    }

    public void setToolbarSubtitleColor(int i) {
        this.i = i;
    }

    public void setToolbarTextColor(int i) {
        this.f = i;
    }
}

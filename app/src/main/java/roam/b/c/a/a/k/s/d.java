package roam.b.c.a.a.k.s;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import org.roam.R;

/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.ItemDecoration {
    public Drawable a;
    public int b;
    public int c;
    public Paint d;

    public d(Context context, int i, Drawable drawable, int i2) {
        this.a = drawable;
        this.c = i2;
        Paint paint = new Paint();
        this.d = paint;
        paint.setColor(context.getResources().getColor(R.color.r));
        this.d.setStyle(Paint.Style.FILL);
        this.d.setAntiAlias(true);
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation");
        }
        this.b = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
        if (this.b == 1) {
            rect.set(0, 0, 0, this.a.getIntrinsicHeight());
        } else {
            rect.set(0, 0, this.a.getIntrinsicWidth(), 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView) {
        if (this.b != 1) {
            int paddingTop = recyclerView.getPaddingTop();
            int height = recyclerView.getHeight();
            int paddingBottom = recyclerView.getPaddingBottom();
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View childAt = recyclerView.getChildAt(i);
                int right = ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).rightMargin + childAt.getRight();
                this.a.setBounds(right, paddingTop, this.a.getIntrinsicHeight() + right, height - paddingBottom);
                this.a.draw(canvas);
            }
            return;
        }
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount2 = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount2 - 1; i2++) {
            View childAt2 = recyclerView.getChildAt(i2);
            int bottom = childAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt2.getLayoutParams())).bottomMargin;
            int intrinsicHeight = this.a.getIntrinsicHeight() + bottom;
            if (this.c > 0) {
                canvas.drawRect(paddingLeft, bottom, width, intrinsicHeight, this.d);
                Drawable drawable = this.a;
                int i3 = this.c;
                drawable.setBounds(paddingLeft + i3, bottom, width - i3, intrinsicHeight);
            } else {
                this.a.setBounds(paddingLeft, bottom, width, intrinsicHeight);
            }
            this.a.draw(canvas);
        }
    }
}

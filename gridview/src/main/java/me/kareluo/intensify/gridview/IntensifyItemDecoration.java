package me.kareluo.intensify.gridview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class IntensifyItemDecoration extends RecyclerView.ItemDecoration {

    private int mOrientation = IntensifyGridView.VERTICAL;
    private int mVerticalSpacing = 0, mHorizontalSpacing = 0;

    public IntensifyItemDecoration(int verticalSpacing, int horizontalSpacing) {
        this.mVerticalSpacing = verticalSpacing;
        this.mHorizontalSpacing = horizontalSpacing;
    }

    public void set(int verticalSpacing, int horizontalSpacing) {
        mVerticalSpacing = verticalSpacing;
        mHorizontalSpacing = horizontalSpacing;
    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mVerticalSpacing == 0 && mHorizontalSpacing == 0) return;

        IntensifyGridLayoutManager gridLayoutManager = (IntensifyGridLayoutManager) parent.getLayoutManager();
        int spanCount = gridLayoutManager.getSpanCount();
        int position = parent.getChildLayoutPosition(view);

        if (mOrientation == IntensifyGridView.HORIZONTAL) {
            if (position >= spanCount) {
                outRect.left = mHorizontalSpacing;
            }

            if (position % spanCount > 0) {
                outRect.top = mVerticalSpacing;
            }
        } else {
            if (position >= spanCount) {
                outRect.top = mVerticalSpacing;
            }

            if (position % spanCount > 0) {
                outRect.left = mHorizontalSpacing;
            }
        }
    }
}
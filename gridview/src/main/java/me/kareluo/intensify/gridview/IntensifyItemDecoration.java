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

        int width = parent.getWidth(), height = parent.getHeight();

        IntensifyGridLayoutManager gridLayoutManager = (IntensifyGridLayoutManager) parent.getLayoutManager();
        int spanCount = gridLayoutManager.getSpanCount();
        int position = parent.getChildLayoutPosition(view);

        int blockWidth = gridLayoutManager.getBlockWidth();
        int blockHeight = gridLayoutManager.getBlockHeight();


        if (mOrientation == IntensifyGridView.HORIZONTAL) {
            int spacing = height - blockHeight * spanCount;
            spacing = spacing / spanCount + spacing % spanCount;

            if (position >= spanCount) {
                outRect.left = mHorizontalSpacing;
            }

            int i = position % spanCount;
            if (i > 0) {
                int sSpanCount = spanCount - 1;
                int j = spacing % (sSpanCount);
                if (i <= j) {
                    outRect.top = i * (spacing / sSpanCount + 1);
                    outRect.bottom = -i * (spacing / sSpanCount + 1);
                } else {
                    outRect.top = i * (spacing / sSpanCount) + j;
                    outRect.bottom = -i * (spacing / sSpanCount) - j;
                }
            }

        } else {
            int spacing = width - blockWidth * spanCount;
            spacing = spacing / spanCount + spacing % spanCount;

            if (position >= spanCount) {
                outRect.top = mVerticalSpacing;
            }

            int i = position % spanCount;
            if (i > 0) {
                int sSpanCount = spanCount - 1;
                int j = spacing % (sSpanCount);
                if (i <= j) {
                    outRect.left = i * (spacing / sSpanCount + 1);
                    outRect.right = -i * (spacing / sSpanCount + 1);
                } else {
                    outRect.left = i * (spacing / sSpanCount) + j;
                    outRect.right = -i * (spacing / sSpanCount) - j;
                }
            }
        }
    }
}
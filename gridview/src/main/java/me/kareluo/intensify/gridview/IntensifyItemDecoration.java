package me.kareluo.intensify.gridview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
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
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        IntensifyGridView intensifyGridView = (IntensifyGridView) parent;
        IntensifyGridLayoutManager layoutManager = intensifyGridView.getLayoutManager();
        Drawable divider = intensifyGridView.getDivider();
        int spanCount = layoutManager.getSpanCount();
        int childCount = parent.getChildCount();
        if (divider != null) {
            if (mOrientation == IntensifyGridView.VERTICAL) {
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                for (int i = spanCount; i < childCount; i += spanCount) {
                    View child = parent.getChildAt(i);
                    int bottom = child.getTop() + Math.round(ViewCompat.getTranslationY(child));
                    int top = bottom - mVerticalSpacing;
                    divider.setBounds(left, top, right, bottom);
                    divider.draw(canvas);
                }
            } else {
                int top = parent.getPaddingTop();
                int bottom = parent.getHeight() - parent.getPaddingBottom();
                for (int i = spanCount; i < childCount; i += spanCount) {
                    View child = parent.getChildAt(i);
                    int right = child.getLeft() + Math.round(ViewCompat.getTranslationX(child));
                    int left = right - mHorizontalSpacing;
                    divider.setBounds(left, top, right, bottom);
                    divider.draw(canvas);
                }
            }
        }

        Drawable spacer = intensifyGridView.getSpacer();
        if (spacer != null) {
            if (mOrientation == IntensifyGridView.VERTICAL) {
                for (int i = 0; i < childCount; i++) {
                    if (i % spanCount > 0) {
                        View child = parent.getChildAt(i);
                        int top = child.getTop();
                        int bottom = child.getBottom();
                        int right = child.getLeft();
                        int left = parent.getChildAt(i - 1).getRight();
                        spacer.setBounds(left, top, right, bottom);
                        spacer.draw(canvas);
                    }
                }
            } else {
                for (int i = 0; i < childCount; i++) {
                    if (i % spanCount > 0) {
                        View child = parent.getChildAt(i);
                        int left = child.getLeft();
                        int right = child.getRight();
                        int bottom = child.getTop();
                        int top = parent.getChildAt(i - 1).getBottom();
                        spacer.setBounds(left, top, right, bottom);
                        spacer.draw(canvas);
                    }
                }
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mVerticalSpacing == 0 && mHorizontalSpacing == 0) return;
        IntensifyGridLayoutManager manager = (IntensifyGridLayoutManager) parent.getLayoutManager();
        int position = parent.getChildLayoutPosition(view);

        switch (manager.getSpacingGravity()) {
            case IntensifyGridView.SHARE:
                getShareItemOffsets(manager, outRect, position);
                break;
            case IntensifyGridView.START:
            case IntensifyGridView.END:
                getGravityItemOffsets(manager, outRect, position);
                break;
        }
    }

    private void getGravityItemOffsets(IntensifyGridLayoutManager manager, Rect outRect, int position) {
        int spanCount = manager.getSpanCount();
        int spacingGravity = manager.getSpacingGravity();
        int spacing = manager.getSpacing() / spanCount;
        int extra = manager.getSpacing() % spanCount;
        if (mOrientation == IntensifyGridView.HORIZONTAL) {
            if (position >= spanCount) outRect.left = mHorizontalSpacing;
            outRect.top = spacing - mVerticalSpacing;
            switch (spacingGravity) {
                case IntensifyGridView.START:
                    outRect.top *= spanCount - position % spanCount;
                    outRect.top += extra + mVerticalSpacing;
                    break;
                case IntensifyGridView.END:
                    outRect.top *= -(position % spanCount);
                    break;
            }
            outRect.bottom = -outRect.top;
        } else {
            if (position >= spanCount) outRect.top = mVerticalSpacing;
            outRect.left = spacing - mHorizontalSpacing;
            switch (spacingGravity) {
                case IntensifyGridView.START:
                    outRect.left *= spanCount - position % spanCount;
                    outRect.left += extra + mHorizontalSpacing;
                    break;
                case IntensifyGridView.END:
                    outRect.left *= -(position % spanCount);
                    break;
            }
            outRect.right = -outRect.left;
        }
    }

    private void getShareItemOffsets(IntensifyGridLayoutManager manager, Rect outRect, int position) {
        int spanCount = manager.getSpanCount();
        int extra = manager.getSpacingExtra();
        int offset = manager.getSpacingOffset();
        if (mOrientation == IntensifyGridView.HORIZONTAL) {
            if (position >= spanCount) {
                outRect.left = mHorizontalSpacing;
            }

            int cfc = position % spanCount;
            outRect.top = cfc * offset + Math.min(cfc, extra);
            outRect.bottom = -outRect.top;
        } else {
            if (position >= spanCount) {
                outRect.top = mVerticalSpacing;
            }

            int cfc = position % spanCount;
            outRect.left = cfc * offset + Math.min(cfc, extra);
            outRect.right = -outRect.left;
        }
    }
}
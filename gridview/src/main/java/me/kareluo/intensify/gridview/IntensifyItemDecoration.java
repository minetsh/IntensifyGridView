package me.kareluo.intensify.gridview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class IntensifyItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        IntensifyGridView intensifyGridView = (IntensifyGridView) parent;
        IntensifyGridLayoutManager layoutManager = intensifyGridView.getLayoutManager();
        Drawable divider = intensifyGridView.getDivider();
        int orientation = layoutManager.getOrientation();
        int spanCount = layoutManager.getSpanCount();
        int childCount = parent.getChildCount();
        int verticalSpacing = layoutManager.getVerticalSpacing();
        int horizontalSpacing = layoutManager.getHorizontalSpacing();

        if (divider != null) {
            if (orientation == IntensifyGridView.VERTICAL) {
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                for (int i = spanCount; i < childCount; i += spanCount) {
                    View child = parent.getChildAt(i);
                    int bottom = child.getTop() + Math.round(ViewCompat.getTranslationY(child));
                    int top = bottom - verticalSpacing;
                    divider.setBounds(left, top, right, bottom);
                    divider.draw(canvas);
                }
            } else {
                int top = parent.getPaddingTop();
                int bottom = parent.getHeight() - parent.getPaddingBottom();
                for (int i = spanCount; i < childCount; i += spanCount) {
                    View child = parent.getChildAt(i);
                    int right = child.getLeft() + Math.round(ViewCompat.getTranslationX(child));
                    int left = right - horizontalSpacing;
                    divider.setBounds(left, top, right, bottom);
                    divider.draw(canvas);
                }
            }
        }

        Drawable spacer = intensifyGridView.getSpacer();
        if (spacer != null) {
            if (orientation == IntensifyGridView.VERTICAL) {
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
        IntensifyGridLayoutManager manager = (IntensifyGridLayoutManager) parent.getLayoutManager();

        if (manager.getVerticalSpacing() == 0 && manager.getHorizontalSpacing() == 0) return;
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
        int verticalSpacing = manager.getVerticalSpacing();
        int horizontalSpacing = manager.getHorizontalSpacing();
        if (manager.getOrientation() == IntensifyGridView.HORIZONTAL) {
            if (position >= spanCount) outRect.left = horizontalSpacing;
            outRect.top = spacing - verticalSpacing;
            switch (spacingGravity) {
                case IntensifyGridView.START:
                    outRect.top *= spanCount - position % spanCount;
                    outRect.top += extra + verticalSpacing;
                    break;
                case IntensifyGridView.END:
                    outRect.top *= -(position % spanCount);
                    break;
            }
            outRect.bottom = -outRect.top;
        } else {
            if (position >= spanCount) outRect.top = verticalSpacing;
            outRect.left = spacing - horizontalSpacing;
            switch (spacingGravity) {
                case IntensifyGridView.START:
                    outRect.left *= spanCount - position % spanCount;
                    outRect.left += extra + horizontalSpacing;
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
        int verticalSpacing = manager.getVerticalSpacing();
        int horizontalSpacing = manager.getHorizontalSpacing();
        if (manager.getOrientation() == IntensifyGridView.HORIZONTAL) {
            if (position >= spanCount) {
                outRect.left = horizontalSpacing;
            }

            int cfc = position % spanCount;
            outRect.top = cfc * offset + Math.min(cfc, extra);
            outRect.bottom = -outRect.top;
        } else {
            if (position >= spanCount) {
                outRect.top = verticalSpacing;
            }

            int cfc = position % spanCount;
            outRect.left = cfc * offset + Math.min(cfc, extra);
            outRect.right = -outRect.left;
        }
    }
}
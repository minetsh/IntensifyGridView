package me.kareluo.intensify.gridview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by felix on 16/7/4.
 */
public class IntensifyGridLayoutManager extends GridLayoutManager {

    private int mVerticalSpacing = 0, mHorizontalSpacing = 0;

    private int mSpacingGravity = IntensifyGridView.SHARE;

    private boolean mAutoFit = false;
    private int mBlockWidth = LayoutParams.WRAP_CONTENT, mBlockHeight = LayoutParams.WRAP_CONTENT;
    private int mBlockType = IntensifyGridView.RECTANGLE;

    private int mSpacingExtra = 0;

    private int mSpacingOffset = 0;

    private int mSpacing = 0;

    public IntensifyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IntensifyGridView);
        mAutoFit = a.getBoolean(R.styleable.IntensifyGridView_autoFit, false);

        mBlockType = a.getInt(R.styleable.IntensifyGridView_blockType, IntensifyGridView.RECTANGLE);

        int blockSize = a.getLayoutDimension(R.styleable.IntensifyGridView_blockSize, LayoutParams.WRAP_CONTENT);
        mBlockWidth = mBlockHeight = blockSize;

        mBlockWidth = a.getLayoutDimension(R.styleable.IntensifyGridView_blockWidth, mBlockWidth);
        mBlockHeight = a.getLayoutDimension(R.styleable.IntensifyGridView_blockHeight, mBlockHeight);

        mSpacingGravity = a.getInt(R.styleable.IntensifyGridView_spacingGravity, IntensifyGridView.SHARE);
        a.recycle();
    }

    public IntensifyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public IntensifyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    public void setSpacing(int verticalSpacing, int horizontalSpacing) {
        mVerticalSpacing = verticalSpacing;
        mHorizontalSpacing = horizontalSpacing;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void setSpanCount(int spanCount) {
        super.setSpanCount(Math.max(spanCount, 1));
    }

    @Override
    public int getSpanCount() {
        return Math.max(1, super.getSpanCount());
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        computeSize();
        super.onLayoutChildren(recycler, state);
    }

    public int getValidWidth() {
        return getContentWidth() - (getOrientation() == HORIZONTAL ? 0 : (getSpanCount() - 1) * mHorizontalSpacing);
    }

    public int getValidHeight() {
        return getContentHeight() - (getOrientation() == HORIZONTAL ? (getSpanCount() - 1) * mVerticalSpacing : 0);
    }

    public int getContentWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    public int getContentHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    public void computeSize() {
        int width = getContentWidth(), height = getContentHeight();
        if (width <= 0 || height <= 0) return;

        if (getOrientation() == HORIZONTAL) {
            if (mAutoFit) {
                switch (mBlockHeight) {
                    case LayoutParams.WRAP_CONTENT:
                    case LayoutParams.MATCH_PARENT:
                        mBlockHeight = height;
                    default:
                        setSpanCount((height - mBlockHeight) / (mBlockHeight + mVerticalSpacing) + 1);
                        break;
                }
            } else {
                mBlockHeight = (height - (getSpanCount() - 1) * mVerticalSpacing) / getSpanCount();
            }
            int spanCount = getSpanCount();
            if (mBlockType == IntensifyGridView.SQUARE) {
                mBlockWidth = mBlockHeight;
            } else if (mBlockWidth == LayoutParams.MATCH_PARENT) {
                mBlockWidth = width;
            }
            mSpacing = getContentHeight() - mBlockHeight * spanCount;
            int spacing = mSpacing / spanCount + mSpacing % spanCount;
            if (spanCount > 1) {
                mSpacingOffset = spacing / (spanCount - 1);
                mSpacingExtra = spacing % (spanCount - 1);
            }
        } else {
            if (mAutoFit) {
                switch (mBlockWidth) {
                    case LayoutParams.WRAP_CONTENT:
                    case LayoutParams.MATCH_PARENT:
                        mBlockWidth = width;
                    default:
                        setSpanCount((width - mBlockWidth) / (mBlockWidth + mHorizontalSpacing) + 1);
                        break;
                }
            } else {
                mBlockWidth = (width - (getSpanCount() - 1) * mHorizontalSpacing) / getSpanCount();
            }
            int spanCount = getSpanCount();
            if (mBlockType == IntensifyGridView.SQUARE) {
                mBlockHeight = mBlockWidth;
            } else if (mBlockHeight == LayoutParams.MATCH_PARENT) {
                mBlockHeight = height;
            }
            mSpacing = getContentWidth() - mBlockWidth * spanCount;
            int spacing = mSpacing / spanCount + mSpacing % spanCount;
            if (spanCount > 1) {
                mSpacingOffset = spacing / (spanCount - 1);
                mSpacingExtra = spacing % (spanCount - 1);
            }
        }
    }

    public int getBlockWidth() {
        return mBlockWidth;
    }

    public int getBlockHeight() {
        return mBlockHeight;
    }

    public int getSpacingOffset() {
        return mSpacingOffset;
    }

    public int getSpacingExtra() {
        return mSpacingExtra;
    }

    public int getSpacingGravity() {
        return mSpacingGravity;
    }

    public int getSpacing() {
        return mSpacing;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(mBlockWidth, mBlockHeight);
    }
}
